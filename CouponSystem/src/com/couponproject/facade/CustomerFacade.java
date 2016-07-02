package com.couponproject.facade;

import java.util.Collection;

import com.couponproject.beans.*;
import com.couponproject.dbdao.*;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.CustomerFacadeException;

public class CustomerFacade implements CouponClientFacade {
	// **********
	// Attributes
	// **********

	// Instants variables
	private Customer customer;
	private CustomerDBDAO custDbdao;
	
	// Empty constructor 
	public CustomerFacade() {
		// The Constructor is Empty to prevent access to the DB 
		// before login   
	}

	//***************
	//*****Methods***
	//***************
	
	// Login
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) throws CustomerFacadeException {
		try {
			// Invoking the login method in CustomerDBDAO
			// if true - load DAO's and relevant Customer
			if (custDbdao.login(name, password)) {
				
				// loading Dao's
				custDbdao = new CustomerDBDAO();
				// loading Customer by invoking the getCustomer method in CustomerDBDAO
				customer = custDbdao.getCustomer(name, password);
			} 
			// if login is successful 'this' will have Costumer asses to DB
			// else 'this' attributes will by null
			return this;
			// Catching couponSystemException
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
			custDbdao.addCouponToCustomer(customer.getId(), coupon.getId());
			
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
			return custDbdao.getCoupons(customer.getId());
		
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
			Collection<Coupon> coupons = custDbdao.getCoupons(customer.getId());

			// Iterating coupons collection and 
			// removing coupons that not match relevant type
			for (Coupon coupon : coupons) {
				if (!(coupon.getType() == type)){
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
	
	public Collection<Coupon> getAllPurchasedCouponsByPrice(Double price) throws CustomerFacadeException {
		try {
			// Invoking the getCoupons method in CustomerDBDAO
			Collection<Coupon> coupons = custDbdao.getCoupons(customer.getId());

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
}
