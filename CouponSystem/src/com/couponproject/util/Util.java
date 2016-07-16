package com.couponproject.util;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import com.couponproject.beans.*;
import com.couponproject.dbdao.*;
import com.couponproject.exception.CouponSystemException;

// This calls provides Utilities static method for the Coupon System
public class Util {
	
	/* Password validation - returns true of password is valid
	 *  			  # start-of-string
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
		} catch (CouponSystemException e) {
			return false;
		}
	}

		
	// Returns true if Company name of id already exist in DB
	public static boolean isCompany(Company company) {
		try {
			Company dbCompany = CompanyDBDAO.getInstace().getCompany(company.getCompName(),
					company.getPassword());

			if (company.getCompName().equals(dbCompany.getCompName())) {
				return true;
			} else {
				return (company.getId() == dbCompany.getId());
			} 
		} catch (CouponSystemException e) {
			return false;
		}
	}
	
	
	
	
	// Returns true if argument coupon name or ID already exist in DB
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
	
	
	
	public static boolean isPurchased(Coupon coupon, Customer customer) {
		try {
			// Get all customers coupon 
			Collection<Coupon> purchasedCoupons = CustomerDBDAO.getInstace().getCoupons(customer.getId());
			// Iterating and checking if coupon alredy Purchased 
			boolean result = false;
			for (Coupon purchasedCoupon : purchasedCoupons){
				if (purchasedCoupon.equals(coupon)){
					result = true;
				}
			}
			return result;
		} catch (CouponSystemException e) {return true;}
	}

	
	
	
	
	// A method that gets an instance of a coupon and checks if a coupon with
	// the same title already exists.
	// The method returns true if the coupon exists and false if it does not
	// Exists
	@Deprecated
	public boolean checkCouponTitle(Coupon coupon) throws CouponSystemException {
		// getting a connection to DB from pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()) {

			// Select prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement("select * from coupon ");

			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();

			while (myRs.next()) {
				if (myRs.getString("TITLE") == coupon.getTitle()) {
					return true;
				}
			}
			return false;
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
	}

}
