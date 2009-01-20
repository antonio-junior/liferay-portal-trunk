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

package com.liferay.portal.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MimeTypes;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;

/**
 * <a href="MimeTypesImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 *
 */
public class MimeTypesImpl implements MimeTypes {

	public MimeTypesImpl() {
		_mimeTypes = new MimetypesFileTypeMap();

		String[] customMimeTypes = PropsUtil.getArray(PropsKeys.MIME_TYPES);

		for (int i = 0; i < customMimeTypes.length; i++) {
			_mimeTypes.addMimeTypes(customMimeTypes[i]);
		}
	}

	public String getContentType(File file) {
		String contentType = _mimeTypes.getContentType(file);

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Content type " + contentType + " returned for file " +
					file.toString());
		}

		return contentType;
	}

	public String getContentType(String fileName) {
		String contentType = _mimeTypes.getContentType(fileName);

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Content type " + contentType + " returned for file name " +
					fileName);
		}

		return contentType;
	}

	private static Log _log = LogFactoryUtil.getLog(MimeTypesImpl.class);

	private MimetypesFileTypeMap _mimeTypes;

}