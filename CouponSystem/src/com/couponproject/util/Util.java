package com.couponproject.util;

import com.couponproject.beans.*;
import com.couponproject.dbdao.*;
import com.couponproject.exception.CouponSystemException;

// This calls provides Utilities static method for the Coupon System
public class Util {
	
	// Returns true if Customer exist in DB
	public static boolean isCustomer(Customer customer){
		try {
			Customer dbCustomer = CustomerDBDAO.getInstace().getCustomer(customer.getCustName(),customer.getPassword());
			return (customer.equals(dbCustomer));
		} catch (CouponSystemException e) {
			return false;
		}
	}
	
	// Returns true if Customer exist in DB
	public static boolean isCompany (Company company){
		try {
			Company dbCompany = CompanyDBDAO.getInstace().getCompany(company.getId());
			return (company.equals(dbCompany));
		} catch (CouponSystemException e) {
			return false;
		}
	}

}
