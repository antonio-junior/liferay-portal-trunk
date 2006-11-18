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

package com.liferay.taglib.portletext;

import com.liferay.taglib.ui.IconTag;
import com.liferay.util.servlet.StringServletResponse;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

/**
 * <a href="TitleTag.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class TitleTag extends IconTag {

	public static void doTag(
			ServletContext ctx, HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException {

		doTag(_PAGE, _EDITABLE, ctx, req, res);
	}

	public static void doTag(
			boolean editable, ServletContext ctx, HttpServletRequest req,
			HttpServletResponse res)
		throws IOException, ServletException {

		doTag(_PAGE, editable, ctx, req, res);
	}

	public static void doTag(
			String page, boolean editable, ServletContext ctx,
			HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException {

		req.setAttribute(
			"liferay-portlet:title:editable", String.valueOf(editable));

		RequestDispatcher rd = ctx.getRequestDispatcher(page);

		rd.include(req, res);
	}

	public int doEndTag() throws JspException {
		try {
			ServletContext ctx = getServletContext();
			HttpServletRequest req = getServletRequest();
			StringServletResponse res = getServletResponse();

			doTag(_editable, ctx, req, res);

			pageContext.getOut().print(res.getString());

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
	}

	public void setEditable(boolean editable) {
		_editable = editable;
	}

	protected String getDefaultPage() {
		return _PAGE;
	}

	private static final String _PAGE = "/html/taglib/portlet/title/page.jsp";

	private static final boolean _EDITABLE = true;

	private boolean _editable = _EDITABLE;

}