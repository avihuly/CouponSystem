package com.couponproject.facade;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

import com.couponproject.beans.*;
import com.couponproject.constants.CouponType;
import com.couponproject.exception.*;
import com.couponproject.system.CouponSystem;
import com.couponproject.util.Util;

/**
 * This Class represents the Customer client of the Coupon System. 
 * <p>This class includes all the operations Customer can perform in the system</P>
 * 
 * @author Avi Huly and Orit Blum
 * @version 1.0
 */
public class CustomerFacade {
	// **********
	// Attribute
	// **********
	/**
	 * Holds the Customer instance with the details of the Logged in customer 
	 */
	private Customer customer;
	
	// ***********
	// constructor
	// ***********
	/**
	 * Construct CustomerFacade based on given name and password.
	 * <p>A Customer instance of the logged in customer is created.</p>
	 * @param name Customer's User Name
	 * @param password Customer's Password
	 * @throws CustomerFacadeException
	 */
	public CustomerFacade(String name, String password) throws CustomerFacadeException {
		try {
			customer = CouponSystem.getInstance().getCustomerDBDAO().getCustomer(name, password);
		} catch (CouponSystemException e){
			throw new CustomerFacadeException("CustomerFacadeException - "
					+ "CustomerFacade Error: " + e.getMessage(), e);
		}
	}
	
	
	//***************
	//*****Methods***
	//***************
	/**
	 * Returns CustomerFacade instance upon a successful login and null if login fails
	 * @param name Customer's User Name
	 * @param password Customer's Password
	 * @return CustomerFacade instance
	 * @throws FacadeException
	 */
	public static CustomerFacade login(String name, String password) throws FacadeException {
		try {
			// Invoking the login method in CustomerDBDAO
			// if true - return new CustomerFacade instance with a specific Customer 
			if (CouponSystem.getInstance().getCustomerDBDAO().login(name, password)) {
				return new CustomerFacade(name, password);
			} 
			return null;
		} catch (CouponSystemException e){
				// In case of a problem throw new CustomerFacadeException  
				throw new CustomerFacadeException("CustomerFacadeException - "
						+ "login() Error: " + e.getMessage(), e);
			}
	}
	
	// -----------------
	// UniqueCouponTypes
	// -----------------
	/**
	 * Returns a collection of of all the CouponTaypes of the Coupons purchased by this Customer
	 * @return Collection of CouponType
	 * @throws CouponSystemException
	 */
	public Collection<CouponType> getUniqueCouponTypes() throws CouponSystemException{
		return CouponSystem.getInstance().getCustomerDBDAO().getUniqueCouponTypes(customer);
	}
	
	// ---------------
	// purchase Coupon
	// ---------------
	/**
	 * Adds a coupon, based on his ID, to the customer_coupon table
	 * @param id Coupon's ID
	 * @throws CouponAlreadyPurchasedException
	 * @throws OutOfStockException
	 * @throws OutOfDateException
	 * @throws CouponSystemException
	 */
	public void purchaseCoupon(long id)
			throws CouponAlreadyPurchasedException, OutOfStockException, OutOfDateException, CouponSystemException {
		Coupon coupon = CouponSystem.getInstance().getCouponDBDAO().getCoupon(id);
		// 
		if (LocalDate.now().isAfter(coupon.getEndDate())) {
			throw new OutOfDateException("Coupon is out of date");
		} else if (coupon.getAmount() == 0) {
			throw new OutOfStockException("Coupon is out of stock");
		} else if (Util.isPurchased(coupon, customer)) {
			throw new CouponAlreadyPurchasedException("Coupon already purchased");
		} else {
			try {
				// Invoking the addCouponToCustomer method in CustomerDBDAO
				CouponSystem.getInstance().getCustomerDBDAO().addCouponToCustomer(customer.getId(), coupon.getId());
				// Invoking the removeOneFromAmount method in CustomerDBDAO
				CouponSystem.getInstance().getCustomerDBDAO().removeOneFromAmount(coupon.getId());
			} catch (CouponSystemException | SQLException | IOException | PropertyVetoException e) {
				throw new CustomerFacadeException("CustomerFacadeException - "
						+ "purchaseCoupon() Error: " + e.getMessage(), e);
			}
		}
	}

	// ----------------------
	// getAllPurchasedCoupons
	// ----------------------
	/**
	 * Returns a collection of all the Coupons purchased by this Customer
	 * @return Collection of Coupon
	 * @throws CustomerFacadeException
	 */
	public Collection<Coupon> getAllPurchasedCoupons() throws CustomerFacadeException{
		try {
			// Invoking (and return the result of) the getCoupons method in CustomerDBDAO
			return CouponSystem.getInstance().getCustomerDBDAO().getCoupons(customer.getId());
		} catch (CouponSystemException e){  
			throw new CustomerFacadeException("CustomerFacadeException - "
					+ "getAllPurchasedCoupons() Error: " + e.getMessage(), e);
		}
	}

	// ----------------------------
	// getAllPurchasedCouponsByType
	// ----------------------------
	/**
	 * Returns a collection of all the Coupons purchased by this Customer with a specific CouponType
	 * @param type CouponType
	 * @return Collection on Coupon
	 * @throws CustomerFacadeException
	 */
	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType type) throws CustomerFacadeException {
		try {
			// Invoking the getCoupons method in CustomerDBDAO and saving result coupons
			Collection<Coupon> coupons = CouponSystem.getInstance().getCustomerDBDAO().getCoupons(customer.getId());
			Collection<Coupon> couponsByType = new HashSet<>(); 
			// Iterating coupons collection and removing coupons that not match relevant type
			for (Coupon coupon : coupons) {
				if (coupon.getType() == type){
					couponsByType.add(coupon);
				}
			}
			//return couponsByType collection
			return couponsByType;
		} catch (CouponSystemException e) {
			throw new CustomerFacadeException("CustomerFacadeException - " 
					+ "getAllPurchasedCouponsByType() Error: "  + e.getMessage(), e);
		}
	}
	
	/**
	 * Returns a collection of all the Coupons purchased by this Customer up to a specific price
	 * @param price The highest possible price of the coupons in the returned collection
	 * @return Collection of Coupon
	 * @throws CustomerFacadeException
	 */
	public Collection<Coupon> getAllPurchasedCouponsByPrice(Double price) throws CustomerFacadeException {
		try {
			// Invoking the getCoupons method in CustomerDBDAO and saving result coupons
			Collection<Coupon> coupons = CouponSystem.getInstance().getCustomerDBDAO().getCoupons(customer.getId());
			Collection<Coupon> inRangCoupons = new HashSet<>();
			// Iterating coupons collection and removing coupons that not match relevant price
			for (Coupon coupon : coupons) {
				if (coupon.getPrice() <= price){
					inRangCoupons.add(coupon);
				}
			}
			//return couponsByType collection
			return inRangCoupons;
		} catch (CouponSystemException e) {
			throw new CustomerFacadeException("CustomerFacadeException - " 
					+ "getAllPurchasedCouponsByType() Error: " + e.getMessage(), e);
		}
	}

	// toString
	@Override
	public String toString() {
		return "CustomerFacade [customer=" + customer + "]";
	}
}
