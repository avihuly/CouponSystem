package com.couponproject.util;

import com.couponproject.beans.*;
import com.couponproject.dbdao.*;
import com.couponproject.exception.CouponSystemException;

// This calls provides Utilities static method for the Coupon System
public class Util {

	// Returns true if Customer exist in DB
	public static boolean isCustomer(Customer customer) {
		try {
			Customer dbCustomer = CustomerDBDAO.getInstace().getCustomer(customer.getCustName(),
					customer.getPassword());

			if (customer.getCustName().equals(dbCustomer.getCustName())) {
				return true;
			} else {
				return (customer.getId() == dbCustomer.getId());
			} 
		} catch (CouponSystemException | CustomerAlreadyExistsException e) {
			return false;
		}
	}

	// Returns true if Customers name of id already exist in DB
	public static boolean isCompany(Company company) {
		try {
			Company dbCompany = CompanyDBDAO.getInstace().getCompany(company.getId());
			if (company.equals(dbCompany)) {
				return true;
			} else {
				return (company.getId() == dbCompany.getId());
			}
		} catch (CouponSystemException e) {
			// TODO: what if there is a problem with the connection
			return false;
		}
	}
	
	public static boolean isCoupon(Coupon coupon){
		try {
			Coupon dbCoupon = CouponDBDAO.getInstace().getCoupon(coupon.getId());
			if (coupon.getTitle().equals(dbCoupon.getTitle())) {
				return true;
			} else {
				return (coupon.getId() == dbCoupon.getId());
			}
		} catch (CouponSystemException e) {
			// TODO: what if there is a problem with the connection
			return false;
		}
	}
	
	
	
	
	
	/* Password validation
	 *  # start-of-string
	(?=.*[0-9])       # a digit must occur at least once
	(?=.*[a-z])       # a lower case letter must occur at least once
	(?=.*[A-Z])       # an upper case letter must occur at least once
	(?=\S+$)          # no whitespace allowed in the entire string
	.{8,}             # anything, at least eight places though
	$                 # end-of-string
	 */
	public static boolean passwordvalidation(String password) {
		String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,10}";
		return password.matches(pattern);
	}
}
