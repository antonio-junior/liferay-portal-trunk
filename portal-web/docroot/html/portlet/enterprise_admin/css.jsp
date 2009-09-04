<%
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
%>

<%@ include file="/html/portlet/css_init.jsp" %>

.portlet-enterprise-admin .aui-form .aui-block-labels textarea {
	width: 90%;
}

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
	border: 1px solid #88C5D9;
	clear: both;
	width: 100px;
}

.portlet-enterprise-admin .avatar img {
	display: block;
}

.portlet-enterprise-admin .change-avatar img {
	display: block;
	margin: 10px auto;
}

.portlet-enterprise-admin .change-company-logo {
	background: url(<%= themeImagesPath %>/common/checkerboard.png);
	display: block;
	margin-bottom: 10px;
}

.portlet-enterprise-admin .change-company-logo img {
	border-width: 0;
	display: block;
	width: auto;
}

.portlet-enterprise-admin .company-logo {
	border: none;
	width: 100px;
}

.portlet-enterprise-admin .aui-ctrl-holder.action-ctrl, .portlet-enterprise-admin .aui-ctrl-holder.mailing-ctrl, .portlet-enterprise-admin .aui-ctrl-holder.primary-ctrl {
	margin: 1.8em 0;
}

.portlet-enterprise-admin .email-user-add .password-changed-notification {
	display: none;
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
	width: 30%;
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
	background: url(<%= themeImagesPath %>/control_panel/selected.png) no-repeat 0 50%;
	font-weight: bold;
	left: -22px;
	margin-right: -22px;
	padding: 0;
	position: relative;
}

.portlet-enterprise-admin .form-navigation li.selected a {
	background-color: #5C696E;
	color: #FFF;
	display: block;
	margin: 0 0 0 11px;
	padding: 2px 10px;
	text-decoration: none;
}

.ie6 .portlet-enterprise-admin .form-navigation li.selected a:hover {
	background-color: #5C696E;
}

.portlet-enterprise-admin .form-navigation .modified-notice {
	display: none;
	font-weight: bold;
}

.portlet-enterprise-admin .form-navigation .section-modified .modified-notice {
	color: #090;
	display: inline;
}

.portlet-enterprise-admin .form-navigation .selected.section-modified .modified-notice {
	color: #9F3;
	display: inline;
}

.portlet-enterprise-admin .form-navigation li.section-error a {
	background-image: url(<%= themeImagesPath %>/messages/alert.png);
	background-position: 5px 50%;
	background-repeat: no-repeat;
	color: #F00;
	font-weight: bold;
	padding-left: 25px;
}

.portlet-enterprise-admin .form-navigation li.selected.section-error a {
	background-position: 15px 50%;
	color: #F99;
	margin-left: 11px;
	padding-left: 35px;
}

.portlet-enterprise-admin .form-navigation li a .error-notice {
	display: none;
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
	width: 35px;
}

.portlet-enterprise-admin .form-navigation .aui-button-holder {
	margin-top: 20px;
}

.portlet-enterprise-admin .form-section {
	display: none;
	float: left;
	width: 63%;
}

.ie6 .portlet-enterprise-admin .form-section {
	width: 60%;
}

.portlet-enterprise-admin .form-section.selected {
	display: block;
}

.portlet-enterprise-admin .form-section h3 {
	border-bottom: 1px solid #000;
	clear: both;
	font-size: 14px;
	font-weight: 700;
	margin: 10px 0;
}

.portlet-enterprise-admin .instant-messenger, .portlet-enterprise-admin .social-network {
	clear: both;
	overflow: auto;
}

.portlet-enterprise-admin .instant-messenger img, .portlet-enterprise-admin .social-network img {
	margin-left: 1em;
	margin-top: 1.8em;
}

.portlet-enterprise-admin .instant-messenger .aui-ctrl-holder, .portlet-enterprise-admin .social-network .aui-ctrl-holder {
	float: left;
}

.portlet-enterprise-admin .portrait-icons {
	margin-bottom: 20px;
	text-align: center;
	width: 200px;
}

.portlet-enterprise-admin table.org-labor-table {
	margin-bottom: 30px;
}

.portlet-enterprise-admin .aui-form .aui-block-labels table.org-labor-table td .aui-ctrl-holder {
	margin-bottom: 0;
	padding: 3px 1px;
}

.portlet-enterprise-admin .aui-form .aui-block-labels label, .portlet-enterprise-admin .aui-form .aui-block-labels .label {
	font-weight: bold;
}

.portlet-enterprise-admin .aui-form .aui-block-labels label input {
	vertical-align: middle;
}

.portlet-enterprise-admin .aui-form .aui-block-labels .aui-ctrl-holder {
	margin-bottom: 10px;
}

.portlet-enterprise-admin .aui-form .aui-block-labels .aui-ctrl-holder.mailing-ctrl {
	clear: both;
}

.portlet-enterprise-admin .aui-form .aui-ctrl-holder.input-text-search {
	float: left;
}

.portlet-enterprise-admin .aui-form .aui-block-labels .aui-ctrl-holder.mailing-ctrl span {
	float: left;
	margin-right: 0.5em;
}

.portlet-enterprise-admin .aui-form .aui-block-labels .aui-ctrl-holder.localized-language-selector {
	margin-bottom: 0;
}

.portlet-enterprise-admin .aui-form fieldset {
	border: none;
	padding: 0;
	width: auto;
}

.portlet-enterprise-admin #addresses .aui-ctrl-holder {
	float: none;
	width: auto;
}

.portlet-enterprise-admin .aui-form .row-container {
}

.portlet-enterprise-admin .user-table, .portlet-enterprise-admin .organization-table {
	border-collapse: collapse;
}

.portlet-enterprise-admin .permission-scopes {
	display: block;
}

.portlet-enterprise-admin .permission-scopes:after {
	clear: both;
	content: ".";
	display: block;
	height: 0;
	visibility: hidden;
}

.ie .portlet-enterprise-admin .permission-scopes {
	height: 1%;
}

.portlet-enterprise-admin .permission-scopes.empty {
	display: none;
}

.portlet-enterprise-admin .permission-scopes .permission-scope {
	background: #DFF4FF;
	border: 1px solid #A7CEDF;
	float: left;
	margin-right: 3px;
	padding: 3px 20px 3px 6px;
	padding-right: 20px;
	position: relative;
}

.portlet-enterprise-admin .permission-scopes .permission-scope:hover {
	border-color: #AEB8BC;
}

.portlet-enterprise-admin .permission-scopes .permission-scope-delete {
	display: block;
	padding: 6px;
	position: absolute;
	right: 0;
	top: 3px;
}

.portlet-enterprise-admin .permission-scopes .permission-scope-delete span {
	background: url(<%= themeImagesPath %>/application/close_small.png) no-repeat 0 0;
	cursor: pointer;
	display: block;
	font-size: 0;
	height: 7px;
	text-indent: -9999em;
	width: 7px;
}

.portlet-enterprise-admin .permission-scopes .permission-scope-delete:hover span {
	background-position: 0 100%;
}

.lfr-floating-container .aui-ctrl-holder input, .lfr-floating-container .aui-ctrl-holder img {
	vertical-align: top;
}

.lfr-portlet-toolbar {
	background: #F6F8FB;
	margin-bottom: 5px;
	overflow: hidden;
	padding: 2px;
}

.ie6 .lfr-portlet-toolbar {
	width: 100%;
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
	background-color: #DFF4FF;
	border: 1px solid #A7CEDF;
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
	background-color: #D6DCDD;
	border-color: #B7C0C2;
}

.lfr-portlet-toolbar .lfr-toolbar-button.add-button a {
	background-image: url(<%= themeImagesPath %>/common/add.png);
}

.lfr-portlet-toolbar .lfr-toolbar-button.custom-attributes-button a {
	background-image: url(<%= themeImagesPath %>/common/attributes.png);
}

.lfr-portlet-toolbar .lfr-toolbar-button.view-button a {
	background-image: url(<%= themeImagesPath %>/common/view_users.png);
}

.lfr-portlet-toolbar .lfr-toolbar-button.export-button a {
	background-image: url(<%= themeImagesPath %>/common/export.png);
}

.lfr-portlet-toolbar .lfr-toolbar-button.back-button {
	float: right;
}

.lfr-portlet-toolbar .lfr-toolbar-button.back-button a {
	padding: 4px 14px;
}