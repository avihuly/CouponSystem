package com.couponproject.facade;

import java.util.Collection;
import java.util.HashSet;

import com.couponproject.beans.*;
import com.couponproject.constants.CouponType;
import com.couponproject.dbdao.*;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.CustomerFacadeException;
import com.couponproject.exception.FacadeException;

public class CustomerFacade {
	// **********
	// Attribute
	// **********
	private Customer customer;
	
	// ***********
	// constructor
	// ***********
	
	// constructor loading customer after login
	public CustomerFacade(String name, String password) throws CustomerFacadeException {
		try {
			customer = CustomerDBDAO.getInstace().getCustomer(name, password);
		} catch (CouponSystemException e){
			// In case of a problem throw new CustomerFacadeException  
			throw new CustomerFacadeException("CustomerFacadeException - "
					+ "Constructor error", e);
		}
	}
	
	
	//***************
	//*****Methods***
	//***************
	
	// Login
	public static CustomerFacade login(String name, String password) throws FacadeException {
		try {
			// Invoking the login method in CustomerDBDAO
			// if true - return new CustomerFacade instance with a specific Customer 
			if (CustomerDBDAO.getInstace().login(name, password)) {
				return new CustomerFacade(name, password);
			} 
			
			return null;
		} catch (CouponSystemException e){
				// In case of a problem throw new CustomerFacadeException  
				throw new CustomerFacadeException("CustomerFacadeException - "
						+ "login() Error", e);
			}
	}
	
	
	public void purchaseCoupon(Coupon coupon) throws CustomerFacadeException{
		// TODO check if coupon exist
		try {
			// Invoking the addCouponToCustomer method in CustomerDBDAO
			CustomerDBDAO.getInstace().addCouponToCustomer(customer.getId(), coupon.getId());
			
			// Catching couponSystemException
			} catch (CouponSystemException e){

				// In case of a problem throw new CustomerFacadeException  
				throw new CustomerFacadeException("CustomerFacadeException - "
					+ "addCouponToCustomer() Error", e);
			}
	}
	
	
	public Collection<Coupon> getAllPurchasedCoupons() throws CustomerFacadeException{
		try {
			// Invoking (and return the result of) 
			// the getCoupons method in CustomerDBDAO
			return CustomerDBDAO.getInstace().getCoupons(customer.getId());
		
		// Catching couponSystemException
		} catch (CouponSystemException e){
			// In case of a problem throw new CustomerFacadeException  
			throw new CustomerFacadeException("CustomerFacadeException - "
					+ "getAllPurchasedCoupons() Error", e);
		}
	}
	
	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType type) throws CustomerFacadeException {
		try {
			// Invoking the getCoupons method in CustomerDBDAO
			Collection<Coupon> coupons = CustomerDBDAO.getInstace().getCoupons(customer.getId());
			Collection<Coupon> couponsByType = new HashSet<>(); 
			// Iterating coupons collection and 
			// removing coupons that not match relevant type
			
			for (Coupon coupon : coupons) {
				if (coupon.getType() == type){
					couponsByType.add(coupon);
				}
			}
			
			//return couponsByType collection
			return couponsByType;

		// Catching couponSystemException
		} catch (CouponSystemException e) {

			// In case of a problem throw new CustomerFacadeException
			throw new CustomerFacadeException("CustomerFacadeException - " 
					+ "getAllPurchasedCouponsByType() Error", e);
		}
	}
	
	public Collection<Coupon> getAllPurchasedCouponsByPrice(Double price) throws CustomerFacadeException {
		try {
			// Invoking the getCoupons method in CustomerDBDAO
			Collection<Coupon> coupons = CustomerDBDAO.getInstace().getCoupons(customer.getId());

			// Iterating coupons collection and 
			// removing coupons that not match relevant price
			for (Coupon coupon : coupons) {
				if (!(coupon.getPrice() == price)){
					coupons.remove(coupon);
				}
			}
			
			//return couponsByType collection
			return coupons;

		// Catching couponSystemException
		} catch (CouponSystemException e) {

			// In case of a problem throw new CustomerFacadeException
			throw new CustomerFacadeException("CustomerFacadeException - " 
					+ "getAllPurchasedCouponsByType() Error", e);
		}
	}

	// toString
	@Override
	public String toString() {
		return "CustomerFacade [customer=" + customer + "]";
	}
	
	
	
	
}
