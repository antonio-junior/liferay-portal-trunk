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

package com.liferay.portal.service.base;

import com.liferay.portal.service.AccountLocalService;
import com.liferay.portal.service.AccountService;
import com.liferay.portal.service.AddressLocalService;
import com.liferay.portal.service.AddressService;
import com.liferay.portal.service.ClassNameLocalService;
import com.liferay.portal.service.ClassNameService;
import com.liferay.portal.service.CompanyLocalService;
import com.liferay.portal.service.CompanyService;
import com.liferay.portal.service.ContactLocalService;
import com.liferay.portal.service.ContactService;
import com.liferay.portal.service.CountryService;
import com.liferay.portal.service.EmailAddressLocalService;
import com.liferay.portal.service.EmailAddressService;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.GroupService;
import com.liferay.portal.service.ImageLocalService;
import com.liferay.portal.service.LayoutLocalService;
import com.liferay.portal.service.LayoutService;
import com.liferay.portal.service.LayoutSetLocalService;
import com.liferay.portal.service.LayoutSetService;
import com.liferay.portal.service.LayoutTemplateLocalService;
import com.liferay.portal.service.ListTypeService;
import com.liferay.portal.service.MembershipRequestLocalService;
import com.liferay.portal.service.MembershipRequestService;
import com.liferay.portal.service.OrgLaborLocalService;
import com.liferay.portal.service.OrgLaborService;
import com.liferay.portal.service.OrganizationLocalService;
import com.liferay.portal.service.OrganizationService;
import com.liferay.portal.service.PasswordPolicyLocalService;
import com.liferay.portal.service.PasswordPolicyRelLocalService;
import com.liferay.portal.service.PasswordPolicyService;
import com.liferay.portal.service.PasswordTrackerLocalService;
import com.liferay.portal.service.PermissionLocalService;
import com.liferay.portal.service.PermissionService;
import com.liferay.portal.service.PhoneLocalService;
import com.liferay.portal.service.PhoneService;
import com.liferay.portal.service.PluginSettingLocalService;
import com.liferay.portal.service.PluginSettingService;
import com.liferay.portal.service.PortalLocalService;
import com.liferay.portal.service.PortalService;
import com.liferay.portal.service.PortletItemLocalService;
import com.liferay.portal.service.PortletLocalService;
import com.liferay.portal.service.PortletPreferencesLocalService;
import com.liferay.portal.service.PortletPreferencesService;
import com.liferay.portal.service.PortletService;
import com.liferay.portal.service.QuartzLocalService;
import com.liferay.portal.service.RegionService;
import com.liferay.portal.service.ReleaseLocalService;
import com.liferay.portal.service.ResourceCodeLocalService;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.RoleLocalService;
import com.liferay.portal.service.RoleService;
import com.liferay.portal.service.ServiceComponentLocalService;
import com.liferay.portal.service.SubscriptionLocalService;
import com.liferay.portal.service.ThemeLocalService;
import com.liferay.portal.service.ThemeService;
import com.liferay.portal.service.UserGroupLocalService;
import com.liferay.portal.service.UserGroupRoleLocalService;
import com.liferay.portal.service.UserGroupRoleService;
import com.liferay.portal.service.UserGroupService;
import com.liferay.portal.service.UserIdMapperLocalService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.UserTrackerLocalService;
import com.liferay.portal.service.UserTrackerPathLocalService;
import com.liferay.portal.service.WebDAVPropsLocalService;
import com.liferay.portal.service.WebsiteLocalService;
import com.liferay.portal.service.WebsiteService;
import com.liferay.portal.service.persistence.AccountPersistence;
import com.liferay.portal.service.persistence.AddressPersistence;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.CompanyPersistence;
import com.liferay.portal.service.persistence.ContactPersistence;
import com.liferay.portal.service.persistence.CountryPersistence;
import com.liferay.portal.service.persistence.EmailAddressPersistence;
import com.liferay.portal.service.persistence.GroupFinder;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.ImagePersistence;
import com.liferay.portal.service.persistence.LayoutFinder;
import com.liferay.portal.service.persistence.LayoutPersistence;
import com.liferay.portal.service.persistence.LayoutSetPersistence;
import com.liferay.portal.service.persistence.ListTypePersistence;
import com.liferay.portal.service.persistence.MembershipRequestPersistence;
import com.liferay.portal.service.persistence.OrgGroupPermissionFinder;
import com.liferay.portal.service.persistence.OrgGroupPermissionPersistence;
import com.liferay.portal.service.persistence.OrgGroupRolePersistence;
import com.liferay.portal.service.persistence.OrgLaborPersistence;
import com.liferay.portal.service.persistence.OrganizationFinder;
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.PasswordPolicyFinder;
import com.liferay.portal.service.persistence.PasswordPolicyPersistence;
import com.liferay.portal.service.persistence.PasswordPolicyRelPersistence;
import com.liferay.portal.service.persistence.PasswordTrackerPersistence;
import com.liferay.portal.service.persistence.PermissionFinder;
import com.liferay.portal.service.persistence.PermissionPersistence;
import com.liferay.portal.service.persistence.PermissionUserFinder;
import com.liferay.portal.service.persistence.PhonePersistence;
import com.liferay.portal.service.persistence.PluginSettingPersistence;
import com.liferay.portal.service.persistence.PortletItemPersistence;
import com.liferay.portal.service.persistence.PortletPersistence;
import com.liferay.portal.service.persistence.PortletPreferencesFinder;
import com.liferay.portal.service.persistence.PortletPreferencesPersistence;
import com.liferay.portal.service.persistence.RegionPersistence;
import com.liferay.portal.service.persistence.ReleasePersistence;
import com.liferay.portal.service.persistence.ResourceCodePersistence;
import com.liferay.portal.service.persistence.ResourceFinder;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.RoleFinder;
import com.liferay.portal.service.persistence.RolePersistence;
import com.liferay.portal.service.persistence.ServiceComponentPersistence;
import com.liferay.portal.service.persistence.SubscriptionPersistence;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserGroupFinder;
import com.liferay.portal.service.persistence.UserGroupPersistence;
import com.liferay.portal.service.persistence.UserGroupRolePersistence;
import com.liferay.portal.service.persistence.UserIdMapperPersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.UserTrackerPathPersistence;
import com.liferay.portal.service.persistence.UserTrackerPersistence;
import com.liferay.portal.service.persistence.WebDAVPropsPersistence;
import com.liferay.portal.service.persistence.WebsitePersistence;

/**
 * <a href="LayoutTemplateLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class LayoutTemplateLocalServiceBaseImpl
	implements LayoutTemplateLocalService {
	public AccountLocalService getAccountLocalService() {
		return accountLocalService;
	}

	public void setAccountLocalService(AccountLocalService accountLocalService) {
		this.accountLocalService = accountLocalService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public AccountPersistence getAccountPersistence() {
		return accountPersistence;
	}

	public void setAccountPersistence(AccountPersistence accountPersistence) {
		this.accountPersistence = accountPersistence;
	}

	public AddressLocalService getAddressLocalService() {
		return addressLocalService;
	}

	public void setAddressLocalService(AddressLocalService addressLocalService) {
		this.addressLocalService = addressLocalService;
	}

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public AddressPersistence getAddressPersistence() {
		return addressPersistence;
	}

	public void setAddressPersistence(AddressPersistence addressPersistence) {
		this.addressPersistence = addressPersistence;
	}

	public ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	public void setClassNameLocalService(
		ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	public ClassNameService getClassNameService() {
		return classNameService;
	}

	public void setClassNameService(ClassNameService classNameService) {
		this.classNameService = classNameService;
	}

	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	public CompanyLocalService getCompanyLocalService() {
		return companyLocalService;
	}

	public void setCompanyLocalService(CompanyLocalService companyLocalService) {
		this.companyLocalService = companyLocalService;
	}

	public CompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public CompanyPersistence getCompanyPersistence() {
		return companyPersistence;
	}

	public void setCompanyPersistence(CompanyPersistence companyPersistence) {
		this.companyPersistence = companyPersistence;
	}

	public ContactLocalService getContactLocalService() {
		return contactLocalService;
	}

	public void setContactLocalService(ContactLocalService contactLocalService) {
		this.contactLocalService = contactLocalService;
	}

	public ContactService getContactService() {
		return contactService;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

	public ContactPersistence getContactPersistence() {
		return contactPersistence;
	}

	public void setContactPersistence(ContactPersistence contactPersistence) {
		this.contactPersistence = contactPersistence;
	}

	public CountryService getCountryService() {
		return countryService;
	}

	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}

	public CountryPersistence getCountryPersistence() {
		return countryPersistence;
	}

	public void setCountryPersistence(CountryPersistence countryPersistence) {
		this.countryPersistence = countryPersistence;
	}

	public EmailAddressLocalService getEmailAddressLocalService() {
		return emailAddressLocalService;
	}

	public void setEmailAddressLocalService(
		EmailAddressLocalService emailAddressLocalService) {
		this.emailAddressLocalService = emailAddressLocalService;
	}

	public EmailAddressService getEmailAddressService() {
		return emailAddressService;
	}

	public void setEmailAddressService(EmailAddressService emailAddressService) {
		this.emailAddressService = emailAddressService;
	}

	public EmailAddressPersistence getEmailAddressPersistence() {
		return emailAddressPersistence;
	}

	public void setEmailAddressPersistence(
		EmailAddressPersistence emailAddressPersistence) {
		this.emailAddressPersistence = emailAddressPersistence;
	}

	public GroupLocalService getGroupLocalService() {
		return groupLocalService;
	}

	public void setGroupLocalService(GroupLocalService groupLocalService) {
		this.groupLocalService = groupLocalService;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public GroupPersistence getGroupPersistence() {
		return groupPersistence;
	}

	public void setGroupPersistence(GroupPersistence groupPersistence) {
		this.groupPersistence = groupPersistence;
	}

	public GroupFinder getGroupFinder() {
		return groupFinder;
	}

	public void setGroupFinder(GroupFinder groupFinder) {
		this.groupFinder = groupFinder;
	}

	public ImageLocalService getImageLocalService() {
		return imageLocalService;
	}

	public void setImageLocalService(ImageLocalService imageLocalService) {
		this.imageLocalService = imageLocalService;
	}

	public ImagePersistence getImagePersistence() {
		return imagePersistence;
	}

	public void setImagePersistence(ImagePersistence imagePersistence) {
		this.imagePersistence = imagePersistence;
	}

	public LayoutLocalService getLayoutLocalService() {
		return layoutLocalService;
	}

	public void setLayoutLocalService(LayoutLocalService layoutLocalService) {
		this.layoutLocalService = layoutLocalService;
	}

	public LayoutService getLayoutService() {
		return layoutService;
	}

	public void setLayoutService(LayoutService layoutService) {
		this.layoutService = layoutService;
	}

	public LayoutPersistence getLayoutPersistence() {
		return layoutPersistence;
	}

	public void setLayoutPersistence(LayoutPersistence layoutPersistence) {
		this.layoutPersistence = layoutPersistence;
	}

	public LayoutFinder getLayoutFinder() {
		return layoutFinder;
	}

	public void setLayoutFinder(LayoutFinder layoutFinder) {
		this.layoutFinder = layoutFinder;
	}

	public LayoutSetLocalService getLayoutSetLocalService() {
		return layoutSetLocalService;
	}

	public void setLayoutSetLocalService(
		LayoutSetLocalService layoutSetLocalService) {
		this.layoutSetLocalService = layoutSetLocalService;
	}

	public LayoutSetService getLayoutSetService() {
		return layoutSetService;
	}

	public void setLayoutSetService(LayoutSetService layoutSetService) {
		this.layoutSetService = layoutSetService;
	}

	public LayoutSetPersistence getLayoutSetPersistence() {
		return layoutSetPersistence;
	}

	public void setLayoutSetPersistence(
		LayoutSetPersistence layoutSetPersistence) {
		this.layoutSetPersistence = layoutSetPersistence;
	}

	public LayoutTemplateLocalService getLayoutTemplateLocalService() {
		return layoutTemplateLocalService;
	}

	public void setLayoutTemplateLocalService(
		LayoutTemplateLocalService layoutTemplateLocalService) {
		this.layoutTemplateLocalService = layoutTemplateLocalService;
	}

	public ListTypeService getListTypeService() {
		return listTypeService;
	}

	public void setListTypeService(ListTypeService listTypeService) {
		this.listTypeService = listTypeService;
	}

	public ListTypePersistence getListTypePersistence() {
		return listTypePersistence;
	}

	public void setListTypePersistence(ListTypePersistence listTypePersistence) {
		this.listTypePersistence = listTypePersistence;
	}

	public MembershipRequestLocalService getMembershipRequestLocalService() {
		return membershipRequestLocalService;
	}

	public void setMembershipRequestLocalService(
		MembershipRequestLocalService membershipRequestLocalService) {
		this.membershipRequestLocalService = membershipRequestLocalService;
	}

	public MembershipRequestService getMembershipRequestService() {
		return membershipRequestService;
	}

	public void setMembershipRequestService(
		MembershipRequestService membershipRequestService) {
		this.membershipRequestService = membershipRequestService;
	}

	public MembershipRequestPersistence getMembershipRequestPersistence() {
		return membershipRequestPersistence;
	}

	public void setMembershipRequestPersistence(
		MembershipRequestPersistence membershipRequestPersistence) {
		this.membershipRequestPersistence = membershipRequestPersistence;
	}

	public OrganizationLocalService getOrganizationLocalService() {
		return organizationLocalService;
	}

	public void setOrganizationLocalService(
		OrganizationLocalService organizationLocalService) {
		this.organizationLocalService = organizationLocalService;
	}

	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	public OrganizationPersistence getOrganizationPersistence() {
		return organizationPersistence;
	}

	public void setOrganizationPersistence(
		OrganizationPersistence organizationPersistence) {
		this.organizationPersistence = organizationPersistence;
	}

	public OrganizationFinder getOrganizationFinder() {
		return organizationFinder;
	}

	public void setOrganizationFinder(OrganizationFinder organizationFinder) {
		this.organizationFinder = organizationFinder;
	}

	public OrgGroupPermissionPersistence getOrgGroupPermissionPersistence() {
		return orgGroupPermissionPersistence;
	}

	public void setOrgGroupPermissionPersistence(
		OrgGroupPermissionPersistence orgGroupPermissionPersistence) {
		this.orgGroupPermissionPersistence = orgGroupPermissionPersistence;
	}

	public OrgGroupPermissionFinder getOrgGroupPermissionFinder() {
		return orgGroupPermissionFinder;
	}

	public void setOrgGroupPermissionFinder(
		OrgGroupPermissionFinder orgGroupPermissionFinder) {
		this.orgGroupPermissionFinder = orgGroupPermissionFinder;
	}

	public OrgGroupRolePersistence getOrgGroupRolePersistence() {
		return orgGroupRolePersistence;
	}

	public void setOrgGroupRolePersistence(
		OrgGroupRolePersistence orgGroupRolePersistence) {
		this.orgGroupRolePersistence = orgGroupRolePersistence;
	}

	public OrgLaborLocalService getOrgLaborLocalService() {
		return orgLaborLocalService;
	}

	public void setOrgLaborLocalService(
		OrgLaborLocalService orgLaborLocalService) {
		this.orgLaborLocalService = orgLaborLocalService;
	}

	public OrgLaborService getOrgLaborService() {
		return orgLaborService;
	}

	public void setOrgLaborService(OrgLaborService orgLaborService) {
		this.orgLaborService = orgLaborService;
	}

	public OrgLaborPersistence getOrgLaborPersistence() {
		return orgLaborPersistence;
	}

	public void setOrgLaborPersistence(OrgLaborPersistence orgLaborPersistence) {
		this.orgLaborPersistence = orgLaborPersistence;
	}

	public PasswordPolicyLocalService getPasswordPolicyLocalService() {
		return passwordPolicyLocalService;
	}

	public void setPasswordPolicyLocalService(
		PasswordPolicyLocalService passwordPolicyLocalService) {
		this.passwordPolicyLocalService = passwordPolicyLocalService;
	}

	public PasswordPolicyService getPasswordPolicyService() {
		return passwordPolicyService;
	}

	public void setPasswordPolicyService(
		PasswordPolicyService passwordPolicyService) {
		this.passwordPolicyService = passwordPolicyService;
	}

	public PasswordPolicyPersistence getPasswordPolicyPersistence() {
		return passwordPolicyPersistence;
	}

	public void setPasswordPolicyPersistence(
		PasswordPolicyPersistence passwordPolicyPersistence) {
		this.passwordPolicyPersistence = passwordPolicyPersistence;
	}

	public PasswordPolicyFinder getPasswordPolicyFinder() {
		return passwordPolicyFinder;
	}

	public void setPasswordPolicyFinder(
		PasswordPolicyFinder passwordPolicyFinder) {
		this.passwordPolicyFinder = passwordPolicyFinder;
	}

	public PasswordPolicyRelLocalService getPasswordPolicyRelLocalService() {
		return passwordPolicyRelLocalService;
	}

	public void setPasswordPolicyRelLocalService(
		PasswordPolicyRelLocalService passwordPolicyRelLocalService) {
		this.passwordPolicyRelLocalService = passwordPolicyRelLocalService;
	}

	public PasswordPolicyRelPersistence getPasswordPolicyRelPersistence() {
		return passwordPolicyRelPersistence;
	}

	public void setPasswordPolicyRelPersistence(
		PasswordPolicyRelPersistence passwordPolicyRelPersistence) {
		this.passwordPolicyRelPersistence = passwordPolicyRelPersistence;
	}

	public PasswordTrackerLocalService getPasswordTrackerLocalService() {
		return passwordTrackerLocalService;
	}

	public void setPasswordTrackerLocalService(
		PasswordTrackerLocalService passwordTrackerLocalService) {
		this.passwordTrackerLocalService = passwordTrackerLocalService;
	}

	public PasswordTrackerPersistence getPasswordTrackerPersistence() {
		return passwordTrackerPersistence;
	}

	public void setPasswordTrackerPersistence(
		PasswordTrackerPersistence passwordTrackerPersistence) {
		this.passwordTrackerPersistence = passwordTrackerPersistence;
	}

	public PermissionLocalService getPermissionLocalService() {
		return permissionLocalService;
	}

	public void setPermissionLocalService(
		PermissionLocalService permissionLocalService) {
		this.permissionLocalService = permissionLocalService;
	}

	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public PermissionPersistence getPermissionPersistence() {
		return permissionPersistence;
	}

	public void setPermissionPersistence(
		PermissionPersistence permissionPersistence) {
		this.permissionPersistence = permissionPersistence;
	}

	public PermissionFinder getPermissionFinder() {
		return permissionFinder;
	}

	public void setPermissionFinder(PermissionFinder permissionFinder) {
		this.permissionFinder = permissionFinder;
	}

	public PermissionUserFinder getPermissionUserFinder() {
		return permissionUserFinder;
	}

	public void setPermissionUserFinder(
		PermissionUserFinder permissionUserFinder) {
		this.permissionUserFinder = permissionUserFinder;
	}

	public PhoneLocalService getPhoneLocalService() {
		return phoneLocalService;
	}

	public void setPhoneLocalService(PhoneLocalService phoneLocalService) {
		this.phoneLocalService = phoneLocalService;
	}

	public PhoneService getPhoneService() {
		return phoneService;
	}

	public void setPhoneService(PhoneService phoneService) {
		this.phoneService = phoneService;
	}

	public PhonePersistence getPhonePersistence() {
		return phonePersistence;
	}

	public void setPhonePersistence(PhonePersistence phonePersistence) {
		this.phonePersistence = phonePersistence;
	}

	public PortalLocalService getPortalLocalService() {
		return portalLocalService;
	}

	public void setPortalLocalService(PortalLocalService portalLocalService) {
		this.portalLocalService = portalLocalService;
	}

	public PortalService getPortalService() {
		return portalService;
	}

	public void setPortalService(PortalService portalService) {
		this.portalService = portalService;
	}

	public PluginSettingLocalService getPluginSettingLocalService() {
		return pluginSettingLocalService;
	}

	public void setPluginSettingLocalService(
		PluginSettingLocalService pluginSettingLocalService) {
		this.pluginSettingLocalService = pluginSettingLocalService;
	}

	public PluginSettingService getPluginSettingService() {
		return pluginSettingService;
	}

	public void setPluginSettingService(
		PluginSettingService pluginSettingService) {
		this.pluginSettingService = pluginSettingService;
	}

	public PluginSettingPersistence getPluginSettingPersistence() {
		return pluginSettingPersistence;
	}

	public void setPluginSettingPersistence(
		PluginSettingPersistence pluginSettingPersistence) {
		this.pluginSettingPersistence = pluginSettingPersistence;
	}

	public PortletLocalService getPortletLocalService() {
		return portletLocalService;
	}

	public void setPortletLocalService(PortletLocalService portletLocalService) {
		this.portletLocalService = portletLocalService;
	}

	public PortletService getPortletService() {
		return portletService;
	}

	public void setPortletService(PortletService portletService) {
		this.portletService = portletService;
	}

	public PortletPersistence getPortletPersistence() {
		return portletPersistence;
	}

	public void setPortletPersistence(PortletPersistence portletPersistence) {
		this.portletPersistence = portletPersistence;
	}

	public PortletPreferencesLocalService getPortletPreferencesLocalService() {
		return portletPreferencesLocalService;
	}

	public void setPortletPreferencesLocalService(
		PortletPreferencesLocalService portletPreferencesLocalService) {
		this.portletPreferencesLocalService = portletPreferencesLocalService;
	}

	public PortletPreferencesService getPortletPreferencesService() {
		return portletPreferencesService;
	}

	public void setPortletPreferencesService(
		PortletPreferencesService portletPreferencesService) {
		this.portletPreferencesService = portletPreferencesService;
	}

	public PortletPreferencesPersistence getPortletPreferencesPersistence() {
		return portletPreferencesPersistence;
	}

	public void setPortletPreferencesPersistence(
		PortletPreferencesPersistence portletPreferencesPersistence) {
		this.portletPreferencesPersistence = portletPreferencesPersistence;
	}

	public PortletPreferencesFinder getPortletPreferencesFinder() {
		return portletPreferencesFinder;
	}

	public void setPortletPreferencesFinder(
		PortletPreferencesFinder portletPreferencesFinder) {
		this.portletPreferencesFinder = portletPreferencesFinder;
	}

	public QuartzLocalService getQuartzLocalService() {
		return quartzLocalService;
	}

	public void setQuartzLocalService(QuartzLocalService quartzLocalService) {
		this.quartzLocalService = quartzLocalService;
	}

	public RegionService getRegionService() {
		return regionService;
	}

	public void setRegionService(RegionService regionService) {
		this.regionService = regionService;
	}

	public RegionPersistence getRegionPersistence() {
		return regionPersistence;
	}

	public void setRegionPersistence(RegionPersistence regionPersistence) {
		this.regionPersistence = regionPersistence;
	}

	public ReleaseLocalService getReleaseLocalService() {
		return releaseLocalService;
	}

	public void setReleaseLocalService(ReleaseLocalService releaseLocalService) {
		this.releaseLocalService = releaseLocalService;
	}

	public ReleasePersistence getReleasePersistence() {
		return releasePersistence;
	}

	public void setReleasePersistence(ReleasePersistence releasePersistence) {
		this.releasePersistence = releasePersistence;
	}

	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	public ResourceFinder getResourceFinder() {
		return resourceFinder;
	}

	public void setResourceFinder(ResourceFinder resourceFinder) {
		this.resourceFinder = resourceFinder;
	}

	public ResourceCodeLocalService getResourceCodeLocalService() {
		return resourceCodeLocalService;
	}

	public void setResourceCodeLocalService(
		ResourceCodeLocalService resourceCodeLocalService) {
		this.resourceCodeLocalService = resourceCodeLocalService;
	}

	public ResourceCodePersistence getResourceCodePersistence() {
		return resourceCodePersistence;
	}

	public void setResourceCodePersistence(
		ResourceCodePersistence resourceCodePersistence) {
		this.resourceCodePersistence = resourceCodePersistence;
	}

	public RoleLocalService getRoleLocalService() {
		return roleLocalService;
	}

	public void setRoleLocalService(RoleLocalService roleLocalService) {
		this.roleLocalService = roleLocalService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public RolePersistence getRolePersistence() {
		return rolePersistence;
	}

	public void setRolePersistence(RolePersistence rolePersistence) {
		this.rolePersistence = rolePersistence;
	}

	public RoleFinder getRoleFinder() {
		return roleFinder;
	}

	public void setRoleFinder(RoleFinder roleFinder) {
		this.roleFinder = roleFinder;
	}

	public ServiceComponentLocalService getServiceComponentLocalService() {
		return serviceComponentLocalService;
	}

	public void setServiceComponentLocalService(
		ServiceComponentLocalService serviceComponentLocalService) {
		this.serviceComponentLocalService = serviceComponentLocalService;
	}

	public ServiceComponentPersistence getServiceComponentPersistence() {
		return serviceComponentPersistence;
	}

	public void setServiceComponentPersistence(
		ServiceComponentPersistence serviceComponentPersistence) {
		this.serviceComponentPersistence = serviceComponentPersistence;
	}

	public PortletItemLocalService getPortletItemLocalService() {
		return portletItemLocalService;
	}

	public void setPortletItemLocalService(
		PortletItemLocalService portletItemLocalService) {
		this.portletItemLocalService = portletItemLocalService;
	}

	public PortletItemPersistence getPortletItemPersistence() {
		return portletItemPersistence;
	}

	public void setPortletItemPersistence(
		PortletItemPersistence portletItemPersistence) {
		this.portletItemPersistence = portletItemPersistence;
	}

	public SubscriptionLocalService getSubscriptionLocalService() {
		return subscriptionLocalService;
	}

	public void setSubscriptionLocalService(
		SubscriptionLocalService subscriptionLocalService) {
		this.subscriptionLocalService = subscriptionLocalService;
	}

	public SubscriptionPersistence getSubscriptionPersistence() {
		return subscriptionPersistence;
	}

	public void setSubscriptionPersistence(
		SubscriptionPersistence subscriptionPersistence) {
		this.subscriptionPersistence = subscriptionPersistence;
	}

	public ThemeLocalService getThemeLocalService() {
		return themeLocalService;
	}

	public void setThemeLocalService(ThemeLocalService themeLocalService) {
		this.themeLocalService = themeLocalService;
	}

	public ThemeService getThemeService() {
		return themeService;
	}

	public void setThemeService(ThemeService themeService) {
		this.themeService = themeService;
	}

	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public UserFinder getUserFinder() {
		return userFinder;
	}

	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	public UserGroupLocalService getUserGroupLocalService() {
		return userGroupLocalService;
	}

	public void setUserGroupLocalService(
		UserGroupLocalService userGroupLocalService) {
		this.userGroupLocalService = userGroupLocalService;
	}

	public UserGroupService getUserGroupService() {
		return userGroupService;
	}

	public void setUserGroupService(UserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}

	public UserGroupPersistence getUserGroupPersistence() {
		return userGroupPersistence;
	}

	public void setUserGroupPersistence(
		UserGroupPersistence userGroupPersistence) {
		this.userGroupPersistence = userGroupPersistence;
	}

	public UserGroupFinder getUserGroupFinder() {
		return userGroupFinder;
	}

	public void setUserGroupFinder(UserGroupFinder userGroupFinder) {
		this.userGroupFinder = userGroupFinder;
	}

	public UserGroupRoleLocalService getUserGroupRoleLocalService() {
		return userGroupRoleLocalService;
	}

	public void setUserGroupRoleLocalService(
		UserGroupRoleLocalService userGroupRoleLocalService) {
		this.userGroupRoleLocalService = userGroupRoleLocalService;
	}

	public UserGroupRoleService getUserGroupRoleService() {
		return userGroupRoleService;
	}

	public void setUserGroupRoleService(
		UserGroupRoleService userGroupRoleService) {
		this.userGroupRoleService = userGroupRoleService;
	}

	public UserGroupRolePersistence getUserGroupRolePersistence() {
		return userGroupRolePersistence;
	}

	public void setUserGroupRolePersistence(
		UserGroupRolePersistence userGroupRolePersistence) {
		this.userGroupRolePersistence = userGroupRolePersistence;
	}

	public UserIdMapperLocalService getUserIdMapperLocalService() {
		return userIdMapperLocalService;
	}

	public void setUserIdMapperLocalService(
		UserIdMapperLocalService userIdMapperLocalService) {
		this.userIdMapperLocalService = userIdMapperLocalService;
	}

	public UserIdMapperPersistence getUserIdMapperPersistence() {
		return userIdMapperPersistence;
	}

	public void setUserIdMapperPersistence(
		UserIdMapperPersistence userIdMapperPersistence) {
		this.userIdMapperPersistence = userIdMapperPersistence;
	}

	public UserTrackerLocalService getUserTrackerLocalService() {
		return userTrackerLocalService;
	}

	public void setUserTrackerLocalService(
		UserTrackerLocalService userTrackerLocalService) {
		this.userTrackerLocalService = userTrackerLocalService;
	}

	public UserTrackerPersistence getUserTrackerPersistence() {
		return userTrackerPersistence;
	}

	public void setUserTrackerPersistence(
		UserTrackerPersistence userTrackerPersistence) {
		this.userTrackerPersistence = userTrackerPersistence;
	}

	public UserTrackerPathLocalService getUserTrackerPathLocalService() {
		return userTrackerPathLocalService;
	}

	public void setUserTrackerPathLocalService(
		UserTrackerPathLocalService userTrackerPathLocalService) {
		this.userTrackerPathLocalService = userTrackerPathLocalService;
	}

	public UserTrackerPathPersistence getUserTrackerPathPersistence() {
		return userTrackerPathPersistence;
	}

	public void setUserTrackerPathPersistence(
		UserTrackerPathPersistence userTrackerPathPersistence) {
		this.userTrackerPathPersistence = userTrackerPathPersistence;
	}

	public WebDAVPropsLocalService getWebDAVPropsLocalService() {
		return webDAVPropsLocalService;
	}

	public void setWebDAVPropsLocalService(
		WebDAVPropsLocalService webDAVPropsLocalService) {
		this.webDAVPropsLocalService = webDAVPropsLocalService;
	}

	public WebDAVPropsPersistence getWebDAVPropsPersistence() {
		return webDAVPropsPersistence;
	}

	public void setWebDAVPropsPersistence(
		WebDAVPropsPersistence webDAVPropsPersistence) {
		this.webDAVPropsPersistence = webDAVPropsPersistence;
	}

	public WebsiteLocalService getWebsiteLocalService() {
		return websiteLocalService;
	}

	public void setWebsiteLocalService(WebsiteLocalService websiteLocalService) {
		this.websiteLocalService = websiteLocalService;
	}

	public WebsiteService getWebsiteService() {
		return websiteService;
	}

	public void setWebsiteService(WebsiteService websiteService) {
		this.websiteService = websiteService;
	}

	public WebsitePersistence getWebsitePersistence() {
		return websitePersistence;
	}

	public void setWebsitePersistence(WebsitePersistence websitePersistence) {
		this.websitePersistence = websitePersistence;
	}

	@javax.annotation.Resource(name = "com.liferay.portal.service.AccountLocalService.impl")
	protected AccountLocalService accountLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.AccountService.impl")
	protected AccountService accountService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.AccountPersistence.impl")
	protected AccountPersistence accountPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.AddressLocalService.impl")
	protected AddressLocalService addressLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.AddressService.impl")
	protected AddressService addressService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.AddressPersistence.impl")
	protected AddressPersistence addressPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.ClassNameLocalService.impl")
	protected ClassNameLocalService classNameLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.ClassNameService.impl")
	protected ClassNameService classNameService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.ClassNamePersistence.impl")
	protected ClassNamePersistence classNamePersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.CompanyLocalService.impl")
	protected CompanyLocalService companyLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.CompanyService.impl")
	protected CompanyService companyService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.CompanyPersistence.impl")
	protected CompanyPersistence companyPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.ContactLocalService.impl")
	protected ContactLocalService contactLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.ContactService.impl")
	protected ContactService contactService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.ContactPersistence.impl")
	protected ContactPersistence contactPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.CountryService.impl")
	protected CountryService countryService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.CountryPersistence.impl")
	protected CountryPersistence countryPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.EmailAddressLocalService.impl")
	protected EmailAddressLocalService emailAddressLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.EmailAddressService.impl")
	protected EmailAddressService emailAddressService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.EmailAddressPersistence.impl")
	protected EmailAddressPersistence emailAddressPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.GroupLocalService.impl")
	protected GroupLocalService groupLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.GroupService.impl")
	protected GroupService groupService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.GroupPersistence.impl")
	protected GroupPersistence groupPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.GroupFinder.impl")
	protected GroupFinder groupFinder;
	@javax.annotation.Resource(name = "com.liferay.portal.service.ImageLocalService.impl")
	protected ImageLocalService imageLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.ImagePersistence.impl")
	protected ImagePersistence imagePersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.LayoutLocalService.impl")
	protected LayoutLocalService layoutLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.LayoutService.impl")
	protected LayoutService layoutService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.LayoutPersistence.impl")
	protected LayoutPersistence layoutPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.LayoutFinder.impl")
	protected LayoutFinder layoutFinder;
	@javax.annotation.Resource(name = "com.liferay.portal.service.LayoutSetLocalService.impl")
	protected LayoutSetLocalService layoutSetLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.LayoutSetService.impl")
	protected LayoutSetService layoutSetService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.LayoutSetPersistence.impl")
	protected LayoutSetPersistence layoutSetPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.LayoutTemplateLocalService.impl")
	protected LayoutTemplateLocalService layoutTemplateLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.ListTypeService.impl")
	protected ListTypeService listTypeService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.ListTypePersistence.impl")
	protected ListTypePersistence listTypePersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.MembershipRequestLocalService.impl")
	protected MembershipRequestLocalService membershipRequestLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.MembershipRequestService.impl")
	protected MembershipRequestService membershipRequestService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.MembershipRequestPersistence.impl")
	protected MembershipRequestPersistence membershipRequestPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.OrganizationLocalService.impl")
	protected OrganizationLocalService organizationLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.OrganizationService.impl")
	protected OrganizationService organizationService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.OrganizationPersistence.impl")
	protected OrganizationPersistence organizationPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.OrganizationFinder.impl")
	protected OrganizationFinder organizationFinder;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.OrgGroupPermissionPersistence.impl")
	protected OrgGroupPermissionPersistence orgGroupPermissionPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.OrgGroupPermissionFinder.impl")
	protected OrgGroupPermissionFinder orgGroupPermissionFinder;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.OrgGroupRolePersistence.impl")
	protected OrgGroupRolePersistence orgGroupRolePersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.OrgLaborLocalService.impl")
	protected OrgLaborLocalService orgLaborLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.OrgLaborService.impl")
	protected OrgLaborService orgLaborService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.OrgLaborPersistence.impl")
	protected OrgLaborPersistence orgLaborPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.PasswordPolicyLocalService.impl")
	protected PasswordPolicyLocalService passwordPolicyLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.PasswordPolicyService.impl")
	protected PasswordPolicyService passwordPolicyService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.PasswordPolicyPersistence.impl")
	protected PasswordPolicyPersistence passwordPolicyPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.PasswordPolicyFinder.impl")
	protected PasswordPolicyFinder passwordPolicyFinder;
	@javax.annotation.Resource(name = "com.liferay.portal.service.PasswordPolicyRelLocalService.impl")
	protected PasswordPolicyRelLocalService passwordPolicyRelLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.PasswordPolicyRelPersistence.impl")
	protected PasswordPolicyRelPersistence passwordPolicyRelPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.PasswordTrackerLocalService.impl")
	protected PasswordTrackerLocalService passwordTrackerLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.PasswordTrackerPersistence.impl")
	protected PasswordTrackerPersistence passwordTrackerPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.PermissionLocalService.impl")
	protected PermissionLocalService permissionLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.PermissionService.impl")
	protected PermissionService permissionService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.PermissionPersistence.impl")
	protected PermissionPersistence permissionPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.PermissionFinder.impl")
	protected PermissionFinder permissionFinder;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.PermissionUserFinder.impl")
	protected PermissionUserFinder permissionUserFinder;
	@javax.annotation.Resource(name = "com.liferay.portal.service.PhoneLocalService.impl")
	protected PhoneLocalService phoneLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.PhoneService.impl")
	protected PhoneService phoneService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.PhonePersistence.impl")
	protected PhonePersistence phonePersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.PortalLocalService.impl")
	protected PortalLocalService portalLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.PortalService.impl")
	protected PortalService portalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.PluginSettingLocalService.impl")
	protected PluginSettingLocalService pluginSettingLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.PluginSettingService.impl")
	protected PluginSettingService pluginSettingService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.PluginSettingPersistence.impl")
	protected PluginSettingPersistence pluginSettingPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.PortletLocalService.impl")
	protected PortletLocalService portletLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.PortletService.impl")
	protected PortletService portletService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.PortletPersistence.impl")
	protected PortletPersistence portletPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.PortletPreferencesLocalService.impl")
	protected PortletPreferencesLocalService portletPreferencesLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.PortletPreferencesService.impl")
	protected PortletPreferencesService portletPreferencesService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.PortletPreferencesPersistence.impl")
	protected PortletPreferencesPersistence portletPreferencesPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.PortletPreferencesFinder.impl")
	protected PortletPreferencesFinder portletPreferencesFinder;
	@javax.annotation.Resource(name = "com.liferay.portal.service.QuartzLocalService.impl")
	protected QuartzLocalService quartzLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.RegionService.impl")
	protected RegionService regionService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.RegionPersistence.impl")
	protected RegionPersistence regionPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.ReleaseLocalService.impl")
	protected ReleaseLocalService releaseLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.ReleasePersistence.impl")
	protected ReleasePersistence releasePersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.ResourceLocalService.impl")
	protected ResourceLocalService resourceLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.ResourceService.impl")
	protected ResourceService resourceService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.ResourcePersistence.impl")
	protected ResourcePersistence resourcePersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.ResourceFinder.impl")
	protected ResourceFinder resourceFinder;
	@javax.annotation.Resource(name = "com.liferay.portal.service.ResourceCodeLocalService.impl")
	protected ResourceCodeLocalService resourceCodeLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.ResourceCodePersistence.impl")
	protected ResourceCodePersistence resourceCodePersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.RoleLocalService.impl")
	protected RoleLocalService roleLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.RoleService.impl")
	protected RoleService roleService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.RolePersistence.impl")
	protected RolePersistence rolePersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.RoleFinder.impl")
	protected RoleFinder roleFinder;
	@javax.annotation.Resource(name = "com.liferay.portal.service.ServiceComponentLocalService.impl")
	protected ServiceComponentLocalService serviceComponentLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.ServiceComponentPersistence.impl")
	protected ServiceComponentPersistence serviceComponentPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.PortletItemLocalService.impl")
	protected PortletItemLocalService portletItemLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.PortletItemPersistence.impl")
	protected PortletItemPersistence portletItemPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.SubscriptionLocalService.impl")
	protected SubscriptionLocalService subscriptionLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.SubscriptionPersistence.impl")
	protected SubscriptionPersistence subscriptionPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.ThemeLocalService.impl")
	protected ThemeLocalService themeLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.ThemeService.impl")
	protected ThemeService themeService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.UserLocalService.impl")
	protected UserLocalService userLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.UserService.impl")
	protected UserService userService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.UserPersistence.impl")
	protected UserPersistence userPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.UserFinder.impl")
	protected UserFinder userFinder;
	@javax.annotation.Resource(name = "com.liferay.portal.service.UserGroupLocalService.impl")
	protected UserGroupLocalService userGroupLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.UserGroupService.impl")
	protected UserGroupService userGroupService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.UserGroupPersistence.impl")
	protected UserGroupPersistence userGroupPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.UserGroupFinder.impl")
	protected UserGroupFinder userGroupFinder;
	@javax.annotation.Resource(name = "com.liferay.portal.service.UserGroupRoleLocalService.impl")
	protected UserGroupRoleLocalService userGroupRoleLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.UserGroupRoleService.impl")
	protected UserGroupRoleService userGroupRoleService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.UserGroupRolePersistence.impl")
	protected UserGroupRolePersistence userGroupRolePersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.UserIdMapperLocalService.impl")
	protected UserIdMapperLocalService userIdMapperLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.UserIdMapperPersistence.impl")
	protected UserIdMapperPersistence userIdMapperPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.UserTrackerLocalService.impl")
	protected UserTrackerLocalService userTrackerLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.UserTrackerPersistence.impl")
	protected UserTrackerPersistence userTrackerPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.UserTrackerPathLocalService.impl")
	protected UserTrackerPathLocalService userTrackerPathLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.UserTrackerPathPersistence.impl")
	protected UserTrackerPathPersistence userTrackerPathPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.WebDAVPropsLocalService.impl")
	protected WebDAVPropsLocalService webDAVPropsLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.WebDAVPropsPersistence.impl")
	protected WebDAVPropsPersistence webDAVPropsPersistence;
	@javax.annotation.Resource(name = "com.liferay.portal.service.WebsiteLocalService.impl")
	protected WebsiteLocalService websiteLocalService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.WebsiteService.impl")
	protected WebsiteService websiteService;
	@javax.annotation.Resource(name = "com.liferay.portal.service.persistence.WebsitePersistence.impl")
	protected WebsitePersistence websitePersistence;
}