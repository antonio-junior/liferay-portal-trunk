/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.softwarecatalog.action;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Image;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.softwarecatalog.DuplicateProductEntryModuleIdException;
import com.liferay.portlet.softwarecatalog.NoSuchProductEntryException;
import com.liferay.portlet.softwarecatalog.ProductEntryAuthorException;
import com.liferay.portlet.softwarecatalog.ProductEntryLicenseException;
import com.liferay.portlet.softwarecatalog.ProductEntryNameException;
import com.liferay.portlet.softwarecatalog.ProductEntryPageURLException;
import com.liferay.portlet.softwarecatalog.ProductEntryScreenshotsException;
import com.liferay.portlet.softwarecatalog.ProductEntryShortDescriptionException;
import com.liferay.portlet.softwarecatalog.ProductEntryTypeException;
import com.liferay.portlet.softwarecatalog.model.SCProductEntry;
import com.liferay.portlet.softwarecatalog.model.SCProductScreenshot;
import com.liferay.portlet.softwarecatalog.service.SCProductEntryServiceUtil;
import com.liferay.portlet.softwarecatalog.service.SCProductScreenshotLocalServiceUtil;

import java.io.File;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EditProductEntryAction extends PortletAction {

	public void processAction(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				updateProductEntry(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteProductEntry(actionRequest);
			}

			if (Validator.isNotNull(cmd)) {
				sendRedirect(actionRequest, actionResponse);
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchProductEntryException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass().getName());

				setForward(actionRequest, "portlet.software_catalog.error");
			}
			else if (e instanceof DuplicateProductEntryModuleIdException ||
					 e instanceof ProductEntryAuthorException ||
					 e instanceof ProductEntryNameException ||
					 e instanceof ProductEntryLicenseException ||
					 e instanceof ProductEntryPageURLException ||
					 e instanceof ProductEntryScreenshotsException ||
					 e instanceof ProductEntryShortDescriptionException ||
					 e instanceof ProductEntryTypeException) {

				SessionErrors.add(actionRequest, e.getClass().getName());
			}
			else {
				throw e;
			}
		}
	}

	public ActionForward render(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		try {
			ActionUtil.getProductEntry(renderRequest);
		}
		catch (Exception e) {
			if (e instanceof NoSuchProductEntryException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass().getName());

				return mapping.findForward("portlet.software_catalog.error");
			}
			else {
				throw e;
			}
		}

		return mapping.findForward(getForward(
			renderRequest, "portlet.software_catalog.edit_product_entry"));
	}

	protected void deleteProductEntry(ActionRequest actionRequest)
		throws Exception {

		long productEntryId = ParamUtil.getLong(
			actionRequest, "productEntryId");

		SCProductEntryServiceUtil.deleteProductEntry(productEntryId);
	}

	protected List<byte[]> getFullImages(UploadPortletRequest uploadRequest)
		throws Exception {

		return getImages(uploadRequest, "fullImage");
	}

	protected List<byte[]> getImages(
			UploadPortletRequest uploadRequest, String imagePrefix)
		throws Exception {

		List<byte[]> images = new ArrayList<byte[]>();

		for (String name :
				getSortedParameterNames(uploadRequest, imagePrefix)) {

			int priority = GetterUtil.getInteger(
				name.substring(imagePrefix.length(), name.length()));

			File file = uploadRequest.getFile(name);
			byte[] bytes = FileUtil.getBytes(file);

			boolean preserveScreenshot = ParamUtil.getBoolean(
				uploadRequest, "preserveScreenshot" + priority);

			if (preserveScreenshot) {
				SCProductScreenshot productScreenshot = getProductScreenshot(
					uploadRequest, priority);

				Image image = null;

				if (imagePrefix.equals("fullImage")) {
					image = ImageLocalServiceUtil.getImage(
						productScreenshot.getFullImageId());
				}
				else {
					image = ImageLocalServiceUtil.getImage(
						productScreenshot.getThumbnailId());
				}

				bytes = image.getTextObj();
			}

			if ((bytes != null) && (bytes.length > 0)) {
				images.add(bytes);
			}
			else {
				throw new ProductEntryScreenshotsException();
			}
		}

		return images;
	}

	protected SCProductScreenshot getProductScreenshot(
			UploadPortletRequest uploadRequest, int priority)
		throws Exception {

		long productEntryId = ParamUtil.getLong(
			uploadRequest, "productEntryId");

		try {
			return SCProductScreenshotLocalServiceUtil.getProductScreenshot(
				productEntryId, priority);
		}
		catch (Exception e) {
			throw new ProductEntryScreenshotsException();
		}
	}

	protected List<String> getSortedParameterNames(
			UploadPortletRequest uploadRequest, String imagePrefix)
		throws Exception {

		List<String> parameterNames = new ArrayList<String>();

		Enumeration<String> enu = uploadRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			if (name.startsWith(imagePrefix)) {
				parameterNames.add(name);
			}
		}

		return ListUtil.sort(parameterNames);
	}

	protected List<byte[]> getThumbnails(UploadPortletRequest uploadRequest)
		throws Exception {

		return getImages(uploadRequest, "thumbnail");
	}

	protected void updateProductEntry(ActionRequest actionRequest)
		throws Exception {

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(
			actionRequest);

		long productEntryId = ParamUtil.getLong(
			actionRequest, "productEntryId");

		String name = ParamUtil.getString(actionRequest, "name");
		String type = ParamUtil.getString(actionRequest, "type");
		String tags = ParamUtil.getString(actionRequest, "tags");
		String shortDescription = ParamUtil.getString(
			actionRequest, "shortDescription");
		String longDescription = ParamUtil.getString(
			actionRequest, "longDescription");
		String pageURL = ParamUtil.getString(actionRequest, "pageURL");
		String author = ParamUtil.getString(actionRequest, "author");
		String repoGroupId = ParamUtil.getString(actionRequest, "repoGroupId");
		String repoArtifactId = ParamUtil.getString(
			actionRequest, "repoArtifactId");

		long[] licenseIds = ParamUtil.getLongValues(actionRequest, "licenses");

		List<byte[]> thumbnails = getThumbnails(uploadRequest);
		List<byte[]> fullImages = getFullImages(uploadRequest);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			SCProductEntry.class.getName(), actionRequest);

		if (productEntryId <= 0) {

			// Add product entry

			SCProductEntryServiceUtil.addProductEntry(
				name, type, tags, shortDescription, longDescription, pageURL,
				author, repoGroupId, repoArtifactId, licenseIds, thumbnails,
				fullImages, serviceContext);
		}
		else {

			// Update product entry

			SCProductEntryServiceUtil.updateProductEntry(
				productEntryId, name, type, tags, shortDescription,
				longDescription, pageURL, author, repoGroupId, repoArtifactId,
				licenseIds, thumbnails, fullImages);
		}
	}

}