/**
 * Copyright (c) 2000-2006 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.imagegallery.action;

import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.util.Constants;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.LiferayWindowState;
import com.liferay.portlet.imagegallery.ImageNameException;
import com.liferay.portlet.imagegallery.ImageSizeException;
import com.liferay.portlet.imagegallery.NoSuchFolderException;
import com.liferay.portlet.imagegallery.NoSuchImageException;
import com.liferay.portlet.imagegallery.service.IGImageServiceUtil;
import com.liferay.util.ParamUtil;
import com.liferay.util.Validator;
import com.liferay.util.servlet.SessionErrors;
import com.liferay.util.servlet.UploadPortletRequest;

import java.io.File;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <a href="EditImageAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class EditImageAction extends PortletAction {

	public void processAction(
			ActionMapping mapping, ActionForm form, PortletConfig config,
			ActionRequest req, ActionResponse res)
		throws Exception {

		String cmd = ParamUtil.getString(req, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				updateImage(req);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteImage(req);

				sendRedirect(req, res);
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchFolderException ||
				e instanceof NoSuchImageException ||
				e instanceof PrincipalException) {

				SessionErrors.add(req, e.getClass().getName());

				setForward(req, "portlet.image_gallery.error");
			}
			else if (e instanceof ImageNameException ||
					 e instanceof ImageSizeException) {

				SessionErrors.add(req, e.getClass().getName());
			}
			else {
				throw e;
			}
		}
	}

	public ActionForward render(
			ActionMapping mapping, ActionForm form, PortletConfig config,
			RenderRequest req, RenderResponse res)
		throws Exception {

		try {
			ActionUtil.getImage(req);
		}
		catch (Exception e) {
			if (e instanceof NoSuchImageException ||
				e instanceof PrincipalException) {

				SessionErrors.add(req, e.getClass().getName());

				return mapping.findForward("portlet.image_gallery.error");
			}
			else {
				throw e;
			}
		}

		String forward = "portlet.image_gallery.edit_image";

		if (req.getWindowState().equals(LiferayWindowState.POP_UP)) {
			forward = "portlet.image_gallery.edit_image_form";
		}

		return mapping.findForward(getForward(req, forward));
	}

	protected void deleteImage(ActionRequest req) throws Exception {
		String companyId = PortalUtil.getCompanyId(req);
		String imageId = ParamUtil.getString(req, "imageId");

		IGImageServiceUtil.deleteImage(companyId, imageId);
	}

	protected void updateImage(ActionRequest req) throws Exception {
		UploadPortletRequest uploadReq =
			PortalUtil.getUploadPortletRequest(req);

		String imageId = ParamUtil.getString(uploadReq, "imageId");

		String folderId = ParamUtil.getString(uploadReq, "folderId");
		String description = ParamUtil.getString(uploadReq, "description");

		if (Validator.isNull(description)) {
			description = uploadReq.getFileName("file");
		}

		File file = uploadReq.getFile("file");
		String contentType = uploadReq.getContentType("file");

		String[] communityPermissions = req.getParameterValues(
			"communityPermissions");
		String[] guestPermissions = req.getParameterValues(
			"guestPermissions");

		if (Validator.isNull(imageId)) {

			// Add image

			IGImageServiceUtil.addImage(
				folderId, description, file, contentType, communityPermissions,
				guestPermissions);
		}
		else {

			// Update image

			IGImageServiceUtil.updateImage(
				imageId, folderId, description, file, contentType);
		}
	}

}