/**
 * Copyright (c) 2000-2006 Liferay, LLC. All rights reserved.
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

package com.liferay.portlet.shopping.service.impl;

import com.liferay.mail.service.spring.MailServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.persistence.CompanyUtil;
import com.liferay.portal.service.persistence.UserUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.shopping.BillingCityException;
import com.liferay.portlet.shopping.BillingCountryException;
import com.liferay.portlet.shopping.BillingEmailAddressException;
import com.liferay.portlet.shopping.BillingFirstNameException;
import com.liferay.portlet.shopping.BillingLastNameException;
import com.liferay.portlet.shopping.BillingPhoneException;
import com.liferay.portlet.shopping.BillingStateException;
import com.liferay.portlet.shopping.BillingStreetException;
import com.liferay.portlet.shopping.BillingZipException;
import com.liferay.portlet.shopping.CCExpirationException;
import com.liferay.portlet.shopping.CCNameException;
import com.liferay.portlet.shopping.CCNumberException;
import com.liferay.portlet.shopping.CCTypeException;
import com.liferay.portlet.shopping.CartMinOrderException;
import com.liferay.portlet.shopping.NoSuchOrderException;
import com.liferay.portlet.shopping.ShippingCityException;
import com.liferay.portlet.shopping.ShippingCountryException;
import com.liferay.portlet.shopping.ShippingEmailAddressException;
import com.liferay.portlet.shopping.ShippingFirstNameException;
import com.liferay.portlet.shopping.ShippingLastNameException;
import com.liferay.portlet.shopping.ShippingPhoneException;
import com.liferay.portlet.shopping.ShippingStateException;
import com.liferay.portlet.shopping.ShippingStreetException;
import com.liferay.portlet.shopping.ShippingZipException;
import com.liferay.portlet.shopping.model.ShoppingCart;
import com.liferay.portlet.shopping.model.ShoppingCartItem;
import com.liferay.portlet.shopping.model.ShoppingItem;
import com.liferay.portlet.shopping.model.ShoppingItemField;
import com.liferay.portlet.shopping.model.ShoppingOrder;
import com.liferay.portlet.shopping.model.ShoppingOrderItem;
import com.liferay.portlet.shopping.service.persistence.ShoppingItemUtil;
import com.liferay.portlet.shopping.service.persistence.ShoppingOrderFinder;
import com.liferay.portlet.shopping.service.persistence.ShoppingOrderItemPK;
import com.liferay.portlet.shopping.service.persistence.ShoppingOrderItemUtil;
import com.liferay.portlet.shopping.service.persistence.ShoppingOrderUtil;
import com.liferay.portlet.shopping.service.spring.ShoppingItemFieldLocalServiceUtil;
import com.liferay.portlet.shopping.service.spring.ShoppingItemLocalServiceUtil;
import com.liferay.portlet.shopping.service.spring.ShoppingOrderItemLocalServiceUtil;
import com.liferay.portlet.shopping.service.spring.ShoppingOrderLocalService;
import com.liferay.portlet.shopping.util.ShoppingPreferences;
import com.liferay.portlet.shopping.util.ShoppingUtil;
import com.liferay.portlet.shopping.util.comparator.OrderDateComparator;
import com.liferay.util.CreditCard;
import com.liferay.util.GetterUtil;
import com.liferay.util.PwdGenerator;
import com.liferay.util.StringUtil;
import com.liferay.util.Validator;
import com.liferay.util.cal.CalendarUtil;
import com.liferay.util.mail.MailMessage;

import java.io.IOException;

import java.util.Currency;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;

/**
 * <a href="ShoppingOrderLocalServiceImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class ShoppingOrderLocalServiceImpl
	implements ShoppingOrderLocalService {

	public void completeOrder(
			String orderId, String ppTxnId, String ppPaymentStatus,
			double ppPaymentGross, String ppReceiverEmail, String ppPayerEmail,
			boolean updateInventory)
		throws PortalException, SystemException {

		// Order

		ShoppingOrder order = ShoppingOrderUtil.findByPrimaryKey(orderId);

		order.setModifiedDate(new Date());
		order.setPpTxnId(ppTxnId);
		order.setPpPaymentStatus(ppPaymentStatus);
		order.setPpPaymentGross(ppPaymentGross);
		order.setPpReceiverEmail(ppReceiverEmail);
		order.setPpPayerEmail(ppPayerEmail);

		ShoppingOrderUtil.update(order);

		// Inventory

		if (updateInventory &&
			ppPaymentStatus.equals(ShoppingOrder.STATUS_COMPLETED)) {

			List orderItems = ShoppingOrderItemLocalServiceUtil.getOrderItems(
				order.getOrderId());

			for (int i = 0; i < orderItems.size(); i++) {
				ShoppingOrderItem orderItem =
					(ShoppingOrderItem)orderItems.get(i);

				ShoppingItem item = ShoppingItemLocalServiceUtil.getItem(
					ShoppingUtil.getItemId(orderItem.getItemId()));

				if (!item.isFields()) {
					int quantity =
						item.getStockQuantity() - orderItem.getQuantity();

					item.setStockQuantity(quantity);
				}
				else {
					List itemFields =
						ShoppingItemFieldLocalServiceUtil.getItemFields(
							item.getItemId());

					ShoppingItemField[] itemFieldsArray =
						(ShoppingItemField[])itemFields.toArray(
							new ShoppingItemField[0]);

					String[] fieldsArray = ShoppingCartItem.getFieldsArray(
						ShoppingUtil.getItemFields(orderItem.getItemId()));

					int rowPos = ShoppingUtil.getFieldsQuantitiesPos(
						item, itemFieldsArray, fieldsArray);

					String[] fieldsQuantities = item.getFieldsQuantitiesArray();

					try {
						int quantity =
							GetterUtil.getInteger(fieldsQuantities[rowPos]) -
							orderItem.getQuantity();

						fieldsQuantities[rowPos] = Integer.toString(quantity);

						item.setFieldsQuantitiesArray(fieldsQuantities);
					}
					catch (Exception e) {
					}
				}

				ShoppingItemUtil.update(item);
			}
		}

		// Email

		sendEmail(order, "confirmation");
	}

	public void deleteOrder(String orderId)
		throws PortalException, SystemException {

		ShoppingOrder order = ShoppingOrderUtil.findByPrimaryKey(orderId);

		deleteOrder(order);
	}

	public void deleteOrder(ShoppingOrder order)
		throws PortalException, SystemException {

		// Items

		ShoppingOrderItemUtil.removeByOrderId(order.getOrderId());

		// Order

		ShoppingOrderUtil.remove(order.getOrderId());
	}

	public ShoppingOrder getLatestOrder(String userId, String groupId)
		throws PortalException, SystemException {

		List orders = ShoppingOrderUtil.findByG_U_PPPS(
			groupId, userId, ShoppingOrder.STATUS_LATEST, 0, 1);

		ShoppingOrder order = null;

		if (orders.size() == 1) {
			order = (ShoppingOrder)orders.get(0);
		}
		else {
			User user = UserUtil.findByPrimaryKey(userId);
			Date now = new Date();

			List pastOrders = ShoppingOrderUtil.findByG_U_PPPS(
				groupId, userId, ShoppingOrder.STATUS_CHECKOUT, 0, 1);

			if (pastOrders.size() == 1) {
				ShoppingOrder pastOrder = (ShoppingOrder)pastOrders.get(0);

				order = (ShoppingOrder)pastOrder.clone();
			}

			String orderId = getOrderId();

			if (order == null) {
				order = ShoppingOrderUtil.create(orderId);
			}
			else {
				order.setOrderId(orderId);
			}

			order.setGroupId(groupId);
			order.setCompanyId(user.getCompanyId());
			order.setUserId(user.getUserId());
			order.setUserName(user.getFullName());
			order.setCreateDate(now);
			order.setModifiedDate(now);
			order.setBillingFirstName(user.getFirstName());
			order.setBillingLastName(user.getLastName());
			order.setBillingEmailAddress(user.getEmailAddress());
			order.setShippingFirstName(user.getFirstName());
			order.setShippingLastName(user.getLastName());
			order.setShippingEmailAddress(user.getEmailAddress());
			order.setCcName(user.getFullName());
			order.setPpPaymentStatus(ShoppingOrder.STATUS_LATEST);
			order.setSendOrderEmail(true);
			order.setSendShippingEmail(true);

			ShoppingOrderUtil.update(order);
		}

		return order;
	}

	public ShoppingOrder getOrder(String orderId)
		throws PortalException, SystemException {

		return ShoppingOrderUtil.findByPrimaryKey(orderId);
	}

	public ShoppingOrder saveLatestOrder(ShoppingCart cart)
		throws PortalException, SystemException {

		Map items = cart.getItems();
		Date now = new Date();

		ShoppingPreferences shoppingPrefs = ShoppingPreferences.getInstance(
			cart.getCompanyId(), cart.getGroupId());

		if (!ShoppingUtil.meetsMinOrder(shoppingPrefs, items)) {
			throw new CartMinOrderException();
		}

		ShoppingOrder order = getLatestOrder(
			cart.getUserId(), cart.getGroupId());

		order.setCreateDate(now);
		order.setModifiedDate(now);
		order.setPpPaymentStatus(ShoppingOrder.STATUS_CHECKOUT);

		ShoppingOrderUtil.update(order);

		boolean requiresShipping = false;

		Iterator itr = items.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry entry = (Map.Entry)itr.next();

			ShoppingCartItem cartItem = (ShoppingCartItem)entry.getKey();
			Integer count = (Integer)entry.getValue();

			ShoppingItem item = cartItem.getItem();

			if (item.isRequiresShipping()) {
				requiresShipping = true;
			}

			ShoppingOrderItemPK orderItemPK = new ShoppingOrderItemPK(
				order.getOrderId(), cartItem.getCartItemId());

			ShoppingOrderItem orderItem =
				ShoppingOrderItemUtil.create(orderItemPK);

			orderItem.setSku(item.getSku());
			orderItem.setName(item.getName());
			orderItem.setDescription(item.getDescription());
			orderItem.setProperties(item.getProperties());
			orderItem.setPrice(
				ShoppingUtil.calculateActualPrice(item, count.intValue()) /
					count.intValue());
			orderItem.setQuantity(count.intValue());

			ShoppingOrderItemUtil.update(orderItem);
		}

		order.setModifiedDate(new Date());
		order.setTax(
			ShoppingUtil.calculateTax(items, order.getBillingState()));
		order.setShipping(
			ShoppingUtil.calculateAlternativeShipping(
				items, cart.getAltShipping()));
		order.setAltShipping(
			shoppingPrefs.getAlternativeShippingName(cart.getAltShipping()));
		order.setRequiresShipping(requiresShipping);
		order.setInsure(cart.isInsure());
		order.setInsurance(ShoppingUtil.calculateInsurance(items));
		order.setCouponIds(cart.getCouponIds());
		order.setCouponDiscount(
			ShoppingUtil.calculateCouponDiscount(
				items, order.getBillingState(), cart.getCoupon()));
		order.setSendOrderEmail(true);
		order.setSendShippingEmail(true);

		ShoppingOrderUtil.update(order);

		return order;
	}

	public List search(
			String orderId, String groupId, String companyId, String userId,
			String billingFirstName, String billingLastName,
			String billingEmailAddress, String shippingFirstName,
			String shippingLastName, String shippingEmailAddress,
			String ppPaymentStatus, boolean andOperator, int begin, int end)
		throws PortalException, SystemException {

		OrderDateComparator obc = new OrderDateComparator(false);

		return ShoppingOrderFinder.findByO_G_C_U_PPPS(
			orderId, groupId, companyId, userId, billingFirstName,
			billingLastName, billingEmailAddress, shippingFirstName,
			shippingLastName, shippingEmailAddress, ppPaymentStatus,
			andOperator, begin, end, obc);
	}

	public int searchCount(
			String orderId, String groupId, String companyId, String userId,
			String billingFirstName, String billingLastName,
			String billingEmailAddress, String shippingFirstName,
			String shippingLastName, String shippingEmailAddress,
			String ppPaymentStatus, boolean andOperator)
		throws PortalException, SystemException {

		return ShoppingOrderFinder.countByO_G_C_U_PPPS(
			orderId, groupId, companyId, userId, billingFirstName,
			billingLastName, billingEmailAddress, shippingFirstName,
			shippingLastName, shippingEmailAddress, ppPaymentStatus,
			andOperator);
	}

	public void sendEmail(ShoppingOrder order, String emailType)
		throws PortalException, SystemException {

		try {
			ShoppingPreferences shoppingPrefs =
				ShoppingPreferences.getInstance(
					order.getCompanyId(), order.getGroupId());

			if (emailType.equals("confirmation") &&
				shoppingPrefs.getEmailOrderConfirmationEnabled()) {
			}
			else if (emailType.equals("shipping") &&
					 shoppingPrefs.getEmailOrderShippingEnabled()) {
			}
			else {
				return;
			}

			Company company = CompanyUtil.findByPrimaryKey(
				order.getCompanyId());

			User user = UserUtil.findByPrimaryKey(order.getUserId());

			Currency currency =
				Currency.getInstance(shoppingPrefs.getCurrencyId());

			String billingAddress =
				order.getBillingFirstName() + " " + order.getBillingLastName() +
					"<br>" +
				order.getBillingEmailAddress() + "<br>" +
				order.getBillingStreet() + "<br>" +
				order.getBillingCity() + "<br>" +
				order.getBillingState() + "<br>" +
				order.getBillingZip() + "<br>" +
				order.getBillingCountry() + "<br>" +
				order.getBillingPhone() + "<br>";

			String shippingAddress =
				order.getShippingFirstName() + " " +
					order.getShippingLastName() + "<br>" +
				order.getShippingEmailAddress() + "<br>" +
				order.getShippingStreet() + "<br>" +
				order.getShippingCity() + "<br>" +
				order.getShippingState() + "<br>" +
				order.getShippingZip() + "<br>" +
				order.getShippingCountry() + "<br>" +
				order.getShippingPhone() + "<br>";

			double total = ShoppingUtil.calculateTotal(order);

			String portletName = PortalUtil.getPortletTitle(
				PortletKeys.SHOPPING, user);

			String fromName = shoppingPrefs.getEmailFromName();
			String fromAddress = shoppingPrefs.getEmailFromAddress();

			String toName = user.getFullName();
			String toAddress = user.getEmailAddress();

			String subject = null;
			String body = null;

			if (emailType.equals("confirmation")) {
				subject = shoppingPrefs.getEmailOrderConfirmationSubject();
				body = shoppingPrefs.getEmailOrderConfirmationBody();
			}
			else if (emailType.equals("shipping")) {
				subject = shoppingPrefs.getEmailOrderShippingSubject();
				body = shoppingPrefs.getEmailOrderShippingBody();
			}

			subject = StringUtil.replace(
				subject,
				new String[] {
					"[$FROM_ADDRESS$]",
					"[$FROM_NAME$]",
					"[$ORDER_BILLING_ADDRESS$]",
					"[$ORDER_CURRENCY$]",
					"[$ORDER_NUMBER$]",
					"[$ORDER_SHIPPING_ADDRESS$]",
					"[$ORDER_TOTAL$]",
					"[$PORTAL_URL$]",
					"[$PORTLET_NAME$]",
					"[$TO_ADDRESS$]",
					"[$TO_NAME$]"
				},
				new String[] {
					fromAddress,
					fromName,
					billingAddress,
					currency.getSymbol(),
					order.getOrderId(),
					shippingAddress,
					String.valueOf(total),
					company.getPortalURL(),
					portletName,
					toAddress,
					toName
				});

			body = StringUtil.replace(
				body,
				new String[] {
					"[$FROM_ADDRESS$]",
					"[$FROM_NAME$]",
					"[$ORDER_BILLING_ADDRESS$]",
					"[$ORDER_CURRENCY$]",
					"[$ORDER_NUMBER$]",
					"[$ORDER_SHIPPING_ADDRESS$]",
					"[$ORDER_TOTAL$]",
					"[$PORTAL_URL$]",
					"[$PORTLET_NAME$]",
					"[$TO_ADDRESS$]",
					"[$TO_NAME$]"
				},
				new String[] {
					fromAddress,
					fromName,
					billingAddress,
					currency.getSymbol(),
					order.getOrderId(),
					shippingAddress,
					String.valueOf(total),
					company.getPortalURL(),
					portletName,
					toAddress,
					toName
				});

			InternetAddress from = new InternetAddress(fromAddress, fromName);

			InternetAddress to = new InternetAddress(toAddress, toName);

			MailMessage message = new MailMessage(
				from, to, subject, body, true);

			MailServiceUtil.sendEmail(message);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
		catch (PortalException pe) {
			throw pe;
		}
	}

	public ShoppingOrder updateLatestOrder(
			String userId, String groupId, String billingFirstName,
			String billingLastName, String billingEmailAddress,
			String billingCompany, String billingStreet, String billingCity,
			String billingState, String billingZip, String billingCountry,
			String billingPhone, boolean shipToBilling,
			String shippingFirstName, String shippingLastName,
			String shippingEmailAddress, String shippingCompany,
			String shippingStreet, String shippingCity, String shippingState,
			String shippingZip, String shippingCountry, String shippingPhone,
			String ccName, String ccType, String ccNumber, int ccExpMonth,
			int ccExpYear, String ccVerNumber, String comments)
		throws PortalException, SystemException {

		ShoppingOrder order = getLatestOrder(userId, groupId);

		return updateOrder(
			order.getOrderId(), billingFirstName, billingLastName,
			billingEmailAddress, billingCompany, billingStreet, billingCity,
			billingState, billingZip, billingCountry, billingPhone,
			shipToBilling, shippingFirstName, shippingLastName,
			shippingEmailAddress, shippingCompany, shippingStreet, shippingCity,
			shippingState, shippingZip, shippingCountry, shippingPhone,
			ccName, ccType, ccNumber, ccExpMonth, ccExpYear, ccVerNumber,
			comments);
	}

	public ShoppingOrder updateOrder(
			String orderId, String billingFirstName, String billingLastName,
			String billingEmailAddress, String billingCompany,
			String billingStreet, String billingCity, String billingState,
			String billingZip, String billingCountry, String billingPhone,
			boolean shipToBilling, String shippingFirstName,
			String shippingLastName, String shippingEmailAddress,
			String shippingCompany, String shippingStreet, String shippingCity,
			String shippingState, String shippingZip, String shippingCountry,
			String shippingPhone, String ccName, String ccType, String ccNumber,
			int ccExpMonth, int ccExpYear, String ccVerNumber, String comments)
		throws PortalException, SystemException {

		ShoppingOrder order = ShoppingOrderUtil.findByPrimaryKey(orderId);

		ShoppingPreferences shoppingPrefs = ShoppingPreferences.getInstance(
			order.getCompanyId(), order.getGroupId());

		validate(
			shoppingPrefs, billingFirstName, billingLastName,
			billingEmailAddress, billingStreet, billingCity, billingState,
			billingZip, billingCountry, billingPhone, shipToBilling,
			shippingFirstName, shippingLastName, shippingEmailAddress,
			shippingStreet, shippingCity, shippingState, shippingZip,
			shippingCountry, shippingPhone, ccName, ccType, ccNumber,
			ccExpMonth, ccExpYear, ccVerNumber);

		order.setModifiedDate(new Date());
		order.setBillingFirstName(billingFirstName);
		order.setBillingLastName(billingLastName);
		order.setBillingEmailAddress(billingEmailAddress);
		order.setBillingCompany(billingCompany);
		order.setBillingStreet(billingStreet);
		order.setBillingCity(billingCity);
		order.setBillingState(billingState);
		order.setBillingZip(billingZip);
		order.setBillingCountry(billingCountry);
		order.setBillingPhone(billingPhone);
		order.setShipToBilling(shipToBilling);

		if (shipToBilling) {
			order.setShippingFirstName(billingFirstName);
			order.setShippingLastName(billingLastName);
			order.setShippingEmailAddress(billingEmailAddress);
			order.setShippingCompany(billingCompany);
			order.setShippingStreet(billingStreet);
			order.setShippingCity(billingCity);
			order.setShippingState(billingState);
			order.setShippingZip(billingZip);
			order.setShippingCountry(billingCountry);
			order.setShippingPhone(billingPhone);
		}
		else {
			order.setShippingFirstName(shippingFirstName);
			order.setShippingLastName(shippingLastName);
			order.setShippingEmailAddress(shippingEmailAddress);
			order.setShippingCompany(shippingCompany);
			order.setShippingStreet(shippingStreet);
			order.setShippingCity(shippingCity);
			order.setShippingState(shippingState);
			order.setShippingZip(shippingZip);
			order.setShippingCountry(shippingCountry);
			order.setShippingPhone(shippingPhone);
		}

		order.setCcName(ccName);
		order.setCcType(ccType);
		order.setCcNumber(ccNumber);
		order.setCcExpMonth(ccExpMonth);
		order.setCcExpYear(ccExpYear);
		order.setCcVerNumber(ccVerNumber);
		order.setComments(comments);

		ShoppingOrderUtil.update(order);

		return order;
	}

	public ShoppingOrder updateOrder(
			String orderId, String ppTxnId, String ppPaymentStatus,
			double ppPaymentGross, String ppReceiverEmail, String ppPayerEmail)
		throws PortalException, SystemException {

		ShoppingOrder order = ShoppingOrderUtil.findByPrimaryKey(orderId);

		order.setModifiedDate(new Date());
		order.setPpTxnId(ppTxnId);
		order.setPpPaymentStatus(ppPaymentStatus);
		order.setPpPaymentGross(ppPaymentGross);
		order.setPpReceiverEmail(ppReceiverEmail);
		order.setPpPayerEmail(ppPayerEmail);

		ShoppingOrderUtil.update(order);

		return order;
	}

	protected String getOrderId() throws SystemException {
		String orderId =
			PwdGenerator.getPassword(PwdGenerator.KEY1 + PwdGenerator.KEY2, 12);

		try {
			ShoppingOrderUtil.findByPrimaryKey(orderId);

			return getOrderId();
		}
		catch (NoSuchOrderException nsoe) {
			return orderId;
		}
	}

	protected void validate(
			ShoppingPreferences shoppingPrefs, String billingFirstName,
			String billingLastName, String billingEmailAddress,
			String billingStreet, String billingCity, String billingState,
			String billingZip, String billingCountry, String billingPhone,
			boolean shipToBilling, String shippingFirstName,
			String shippingLastName, String shippingEmailAddress,
			String shippingStreet, String shippingCity, String shippingState,
			String shippingZip, String shippingCountry, String shippingPhone,
			String ccName, String ccType, String ccNumber, int ccExpMonth,
			int ccExpYear, String ccVerNumber)
		throws PortalException {

		if (Validator.isNull(billingFirstName)) {
			throw new BillingFirstNameException();
		}
		else if (Validator.isNull(billingLastName)) {
			throw new BillingLastNameException();
		}
		else if (!Validator.isEmailAddress(billingEmailAddress)) {
			throw new BillingEmailAddressException();
		}
		else if (Validator.isNull(billingStreet)) {
			throw new BillingStreetException();
		}
		else if (Validator.isNull(billingCity)) {
			throw new BillingCityException();
		}
		else if (Validator.isNull(billingState)) {
			throw new BillingStateException();
		}
		else if (Validator.isNull(billingZip)) {
			throw new BillingZipException();
		}
		else if (Validator.isNull(billingCountry)) {
			throw new BillingCountryException();
		}
		else if (Validator.isNull(billingPhone)) {
			throw new BillingPhoneException();
		}

		if (!shipToBilling) {
			if (Validator.isNull(shippingFirstName)) {
				throw new ShippingFirstNameException();
			}
			else if (Validator.isNull(shippingLastName)) {
				throw new ShippingLastNameException();
			}
			else if (!Validator.isEmailAddress(shippingEmailAddress)) {
				throw new ShippingEmailAddressException();
			}
			else if (Validator.isNull(shippingStreet)) {
				throw new ShippingStreetException();
			}
			else if (Validator.isNull(shippingCity)) {
				throw new ShippingCityException();
			}
			else if (Validator.isNull(shippingState)) {
				throw new ShippingStateException();
			}
			else if (Validator.isNull(shippingZip)) {
				throw new ShippingZipException();
			}
			else if (Validator.isNull(shippingCountry)) {
				throw new ShippingCountryException();
			}
			else if (Validator.isNull(shippingPhone)) {
				throw new ShippingPhoneException();
			}
		}

		if ((!shoppingPrefs.usePayPal()) &&
			(shoppingPrefs.getCcTypes().length > 0)) {

			if (Validator.isNull(ccName)) {
				throw new CCNameException();
			}
			else if (Validator.isNull(ccType)) {
				throw new CCTypeException();
			}
			else if (!CreditCard.isValid(ccNumber, ccType)) {
				throw new CCNumberException();
			}
			else if (!CalendarUtil.isFuture(ccExpMonth, ccExpYear)) {
				throw new CCExpirationException();
			}
		}
	}

}