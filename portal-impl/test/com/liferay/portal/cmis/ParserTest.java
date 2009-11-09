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

package com.liferay.portal.cmis;

import com.liferay.portal.cmis.model.CMISConstants;
import com.liferay.portal.cmis.model.CMISExtensionFactory;
import com.liferay.portal.cmis.model.CMISObject;
import com.liferay.portal.cmis.model.CMISRepositoryInfo;

import java.io.FileInputStream;

import junit.framework.TestCase;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.i18n.iri.IRI;
import org.apache.abdera.model.Collection;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Service;
import org.apache.abdera.model.Workspace;
import org.apache.abdera.parser.Parser;

/**
 * <a href="ParserTest.java.html"><b><i>View Source</i></b></a>
 *
 * @author Alexander Chow
 */
public class ParserTest extends TestCase {

	public void setUp() throws Exception {
		_abdera = Abdera.getInstance();

		Factory factory = _abdera.getFactory();

		factory.registerExtension(new CMISExtensionFactory());
	}

	public void testDocument() throws Exception {
		Entry entry = (Entry)getElement("cmis-document.xml");

		assertNotNull(entry);

		CMISObject cmisObject = entry.getFirstChild(_cmisConstants.OBJECT);

		assertNotNull(cmisObject);
		assertNotNull(cmisObject.getObjectId());
		assertEquals(
			_cmisConstants.BASE_TYPE_DOCUMENT, cmisObject.getBaseType());
	}

	public void testFolder() throws Exception {
		Entry entry = (Entry)getElement("cmis-folder.xml");

		assertNotNull(entry);

		CMISObject cmisObject = entry.getFirstChild(_cmisConstants.OBJECT);

		assertNotNull(cmisObject);
		assertNotNull(cmisObject.getObjectId());
		assertEquals(_cmisConstants.BASE_TYPE_FOLDER, cmisObject.getBaseType());
	}

	public void testService() throws Exception {
		Service service = (Service)getElement("cmis-service.xml");

		assertNotNull(service);

		for (Workspace workspace : service.getWorkspaces()) {
			assertNotNull(workspace);

			CMISRepositoryInfo cmisRepositoryInfo = workspace.getFirstChild(
				_cmisConstants.REPOSITORY_INFO);

			assertNotNull(cmisRepositoryInfo.getId());
			assertEquals(
				_cmisConstants.VERSION,
				cmisRepositoryInfo.getVersionSupported());

			IRI rootChildrenIRI = null;

			for (Collection collection : workspace.getCollections()) {
				String type = collection.getAttributeValue(
					_cmisConstants.COLLECTION_TYPE);

				if (_cmisConstants.COLLECTION_ROOT_CHILDREN.equals(type)) {
					rootChildrenIRI = collection.getHref();
				}
			}

			assertNotNull(rootChildrenIRI);
		}
	}

	protected Element getElement(String filename) throws Exception {
		String path =
			"portal-impl/test/com/liferay/portal/cmis/dependencies/" + filename;

		Parser parser = _abdera.getParser();

		return parser.parse(new FileInputStream(path)).getRoot();
	}

	private static CMISConstants _cmisConstants = CMISConstants.getInstance();

	private Abdera _abdera;

}