package com.couponproject.util;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import com.couponproject.beans.*;
import com.couponproject.constants.CouponTableColumnNames;
import com.couponproject.constants.CouponType;
import com.couponproject.dbdao.*;
import com.couponproject.exception.CouponSystemException;



// This calls provides Utilities static method for the Coupon System
public class Util {
	
	// resultSetToCoupn 
	public static Coupon resultSetToCoupn(ResultSet myRs) throws SQLException{
		return new Coupon(
				myRs.getLong(CouponTableColumnNames.ID.name()),
				myRs.getString(CouponTableColumnNames.TITLE.name()), 
				// converting Date to LocalDate
				myRs.getDate(CouponTableColumnNames.START_DATE.name()).toLocalDate(),
				// converting Date to LocalDate
				myRs.getDate(CouponTableColumnNames.END_DATE.name()).toLocalDate(),
				myRs.getInt(CouponTableColumnNames.AMOUNT.name()), 
				CouponType.valueOf(myRs.getString(CouponTableColumnNames.TYPE.name())),
				myRs.getString(CouponTableColumnNames.MESSAGE.name()), 
				myRs.getDouble(CouponTableColumnNames.PRICE.name()), 
				myRs.getString(CouponTableColumnNames.IMAGE.name()));
	}
	
	
	
	/*
	 * Password validation - returns true of password is valid # start-of-string
	 * (?=.*[0-9]) # a digit must occur at least once (?=.*[a-z]) # a lower case
	 * letter must occur at least once (?=.*[A-Z]) # an upper case letter must
	 * occur at least once (?=\S+$) # no whitespace allowed in the entire string
	 * .{8,} # anything, at least eight places though $ # end-of-string
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

	// Returns true if Customer name exist in DB
	public static boolean isCustomerNameExist(Customer customer) {
		boolean result = false;
		try {
			for (Customer dbCustomer : CustomerDBDAO.getInstace().getAllCustomer()) {
				if ((customer.getCustName().equals(dbCustomer.getCustName()))
						&& (customer.getId() != dbCustomer.getId())) {
					result = true;
				}
			}
			return result;
		} catch (CouponSystemException e) {
			return false;
		}
	}

	// Returns true if Company name of id already exist in DB
	public static boolean isCompany(Company company) {
		try {
			Company dbCompany = CompanyDBDAO.getInstace().getCompany(company.getCompName(), company.getPassword());

			if (company.getCompName().equals(dbCompany.getCompName())) {
				return true;
			} else {
				return (company.getId() == dbCompany.getId());
			}
		} catch (CouponSystemException e) {
			return false;
		}
	}
	
	// Returns true if Customer name exist in DB
	public static boolean isEmailExist(Company company) {
		try {
			for (Company dbCompany : CompanyDBDAO.getInstace().getAllCompanies()) {
				if ((company.getEmail().equals(dbCompany.getEmail()))
						&& (company.getId() != dbCompany.getId())) {
					return true;
				}
			}
			return false;
		} catch (CouponSystemException e) {
			e.printStackTrace();
			return true;
		}
	}
	
	// Returns true if argument coupon name or ID already exist in DB
	public static boolean isCouponNameExist(Coupon coupon) {
		try {
			for (Coupon dbCoupon : CouponDBDAO.getInstace().getAllCoupons()) {
				if (coupon.getTitle().equals(dbCoupon.getTitle())){
					return coupon.getId() != dbCoupon.getId();
				}
			}
			return false;
		} catch (CouponSystemException e) {
			// TODO: what if there is a problem with the connection
			return true;
		}
	}
	
	// Returns true if argument coupon name or ID already exist in DB
	public static boolean isCoupon(Coupon coupon) {
		try {
			for (Coupon dbCoupon : CouponDBDAO.getInstace().getAllCoupons()) {
				if (coupon.getTitle().equals(dbCoupon.getTitle())) {
					return true;
				}
			}
			return false;
		} catch (CouponSystemException e) {
			// TODO: what if there is a problem with the connection
			return true;
		}
	}

	public static boolean isPurchased(Coupon coupon, Customer customer) {
		try {
			// Get all customers coupons
			Collection<Coupon> purchasedCoupons = CustomerDBDAO.getInstace().getCoupons(customer.getId());
			// Iterating and checking if coupon already Purchased
			boolean result = false;
			for (Coupon Coupon : purchasedCoupons) {
				if (Coupon.equals(coupon)) {
					result = true;
				}
			}
			return result;
		} catch (CouponSystemException e) {
			return true;
		}
	}

	public static boolean isCompanyCoupon(long couponID, long companyID) {
		try {
			// Get all company's coupon
			Collection<Coupon> companysCoupons = CompanyDBDAO.getInstace().getCoupons(companyID);
			// Iterating and checking if coupon exist for company
			boolean result = false;
			for (Coupon companysCoupon : companysCoupons) {
				if (companysCoupon.getId() == couponID) {
					result = true;
				}
			}
			return result;
		} catch (CouponSystemException e) {
			return true;
		}
	}
}
