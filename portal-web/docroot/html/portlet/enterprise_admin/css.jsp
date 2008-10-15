<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/html/portlet/css_init.jsp" %>

.portlet-enterprise-admin #header-bottom {
	background-color: #F6F8FB;
	height: 34px;
	margin: 0 0 10px;
}

.portlet-enterprise-admin #header-menu {
	background-color: #F3F5F5;
	font-size: 11px;
	line-height: 34px;
	margin: 0 0 10px;
	padding: 0 10px;
	text-align: right;
}

.portlet-enterprise-admin #header-title {
	background-color: #C1CABC;
	font-size: 20px;
	font-weight: 500;
	margin: 0 0 10px;
	padding: 7px 10px;
}

.portlet-enterprise-admin .avatar {
	clear: both;
	float: left;
	padding: 15px 0 5px;
}

.portlet-enterprise-admin .avatar img {
	display: block;
}

.portlet-enterprise-admin .avatar .change-avatar {
	display: block;
	text-align: center;
}

.portlet-enterprise-admin .ctrl-holder.primary-ctrl, .portlet-enterprise-admin .ctrl-holder.action-ctrl {
	margin: 1.8em 0;
}

.portlet-enterprise-admin .label-holder {
	font-weight: 700;
	padding: 15px 0 5px;
}

.portlet-enterprise-admin .radio-holder {
	line-height: 12px;
}

.portlet-enterprise-admin .form-navigation {
	background-color: #D7F1FF;
	border: 1px solid #88C5D9;
	float: right;
	margin: 0 0 0 15px;
	padding: 10px;
	width: 230px;
}

.portlet-enterprise-admin .form-navigation h3 {
	color: #036;
	font-size: 14px;
	font-weight: bold;
	margin: 0;
}

.portlet-enterprise-admin .form-navigation ul {
	margin-bottom: 10px;
}

.portlet-enterprise-admin .form-navigation li {
}

.portlet-enterprise-admin .form-navigation li a {
	cursor: pointer;
	display: block;
	padding: 2px 0 2px 5px;
}

.portlet-enterprise-admin .form-navigation li a:hover {
	background-color: #88C5D9;
	text-decoration: none;
}

.portlet-enterprise-admin .form-navigation li.selected {
	background: url(<%= themeImagesPath %>/control_panel/selected.png) no-repeat 0 0;
	font-weight: bold;
	margin: 0 0 0 -23px;
	padding: 0;
}

.portlet-enterprise-admin .form-navigation li.selected a {
	background-color: #5C696E;
	color: #FFF;
	display: block;
	margin: 0 0 0 15px;
	padding: 2px 10px;
	text-decoration: none;
}

.portlet-enterprise-admin .form-navigation .user-info, .portlet-enterprise-admin .form-navigation .organization-info {
	font-weight: bold;
	margin-bottom: 15px;
}

.portlet-enterprise-admin .form-navigation .user-info p span, .portlet-enterprise-admin .form-navigation .organization-info p span {
	color: #036;
	display: block;
	font-size: 14px;
}

.portlet-enterprise-admin .form-navigation .user-info .avatar, .portlet-enterprise-admin .form-navigation .organization-info .avatar {
	float: left;
	margin-right: 10px;
	padding: 0;
}

.portlet-enterprise-admin .form-navigation .button-holder {
	margin-top: 20px;
}

.portlet-enterprise-admin .lfr-form-row {
	width: 97%;
}

.portlet-enterprise-admin .lfr-form-row:hover {
	background-color: #DFFCCB;
	border-bottom: 1px solid #B2FF3A;
	border-top: 1px solid #B2FF3A;
	padding-top: 0;
}

.portlet-enterprise-admin .lfr-form-row .ctrl-holder {
	clear: none;
	float: left;
}

.portlet-enterprise-admin .form-section {
	display: none;
}

.portlet-enterprise-admin .form-section.selected {
	display: block;
	float: left;
	width: 63%;
}

.portlet-enterprise-admin .form-section h3 {
	border-bottom: 1px solid #000;
	clear: both;
	font-size: 14px;
	font-weight: 700;
	margin-top: 10px;
}

.portlet-enterprise-admin .row-container {
	margin-top: -15px;
}

.portlet-enterprise-admin .uni-form .block-labels label, .portlet-enterprise-admin .uni-form .block-labels .label {
	font-weight: bold;
}

.portlet-enterprise-admin .uni-form .block-labels label input {
	vertical-align: middle;
}

.portlet-enterprise-admin .uni-form .block-labels .ctrl-holder {
	margin-bottom: 10px;
}

.portlet-enterprise-admin .uni-form fieldset {
	border: none;
	width: 100%;
}

.portlet-enterprise-admin .uni-form fieldset.col {
	margin-right: 10px;
	width: 32.7%;
}

.portlet-enterprise-admin .uni-form .row-container .lfr-form-row {
	margin-bottom: 10px;
}

.portlet-enterprise-admin .user-table, .portlet-enterprise-admin .organization-table {
	border-collapse: collapse;
}

.lfr-portlet-toolbar {
	background: #F6F8FB;
	margin-bottom: 5px;
	overflow: hidden;
	padding: 2px;
}

.lfr-portlet-toolbar .lfr-toolbar-button a {
	background: url() no-repeat 5px 50%;
	border: 1px solid #F6F8FB;
	color: #9EA8AD;
	display: block;
	float: left;
	font-size: 12px;
	font-weight: bold;
	padding: 4px 14px 4px 29px;
	text-decoration: none;
}

.lfr-portlet-toolbar .lfr-toolbar-button a:hover {
	border: 1px solid #346799;
	color: #346799;
	padding: 4px 14px 4px 29px;
}

.ie6 .lfr-portlet-toolbar .lfr-toolbar-button a {
}

.lfr-portlet-toolbar .lfr-toolbar-button.current a {
	background-color: #CFD5D7;
	border: 1px solid #CFD5D7;
	color: #000;
}

.lfr-portlet-toolbar .lfr-toolbar-button.current a:hover {
	border: 1px solid #346799;
}

.lfr-portlet-toolbar .lfr-toolbar-button.add-button a {
	background-image: url(<%= themeImagesPath %>/common/add.png);
}

.lfr-portlet-toolbar .lfr-toolbar-button.custom-attributes-button a {
	background-image: url(<%= themeImagesPath %>/common/add.png);
}

.lfr-portlet-toolbar .lfr-toolbar-button.view-button a {
	background-image: url(<%= themeImagesPath %>/common/view_users.png);
}

.lfr-portlet-toolbar .lfr-toolbar-button.export-button a {
	background-image: url(<%= themeImagesPath %>/common/export.png);
}