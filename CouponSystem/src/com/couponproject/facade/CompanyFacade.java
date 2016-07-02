package com.couponproject.facade;

import java.util.Collection;

import com.couponproject.beans.*;
import com.couponproject.exception.*;

import com.couponproject.dbdao.CompanyDBDAO;
import com.couponproject.dbdao.CouponDBDAO;



//TODO: limits cheking!!!
public class CompanyFacade implements CouponClientFacade{
	// **********
	// Attributes
	// **********
	
	// Static DB access
	private static CompanyDBDAO compDbDao = new CompanyDBDAO();
	private static CouponDBDAO coupDbDao = new CouponDBDAO();
	
	// Company instance variable
	private Company company;
	
	// ***********
	// constructor
	// ***********
	
	// constructor loading company after login
	public CompanyFacade(String name, String password) throws CompanyFacadeException {
		try {
			company = compDbDao.getCompany(name, password);
			// Catching couponSystemException
		} catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException
			throw new CompanyFacadeException("CompanyFacadeException - " 
					+ "constructor Error", e);
		}
	}
	
	//***************
	//*****Methods***
	//***************
	
	// Login
	public static CouponClientFacade login(String name, String password) throws CompanyFacadeException {
		
		try {
			// Invoking the login method in CustomerDBDAO
			// if true - return new CustomerFacade instance with a specific Company
			if (compDbDao.login(name, password)) {
				return new CompanyFacade(name, password);
			}
			return null;
			// Catching couponSystemException
			} catch (CouponSystemException e){
				// In case of a problem throw new CompanyFacadeException  
				throw new CompanyFacadeException("CompanyFacadeException - "
						+ "login() Error", e);
			}
	
	}
	
	//A method that gets coupon instance and add the coupon to the coupon table in the DB and adds coupon's and company's
	//ID to company_coupon table in the DB
	public void createCoupon(Coupon coupon) throws CompanyFacadeException{
		//TODO: check if the coupon doesn't exist - to add a function in CouponDBDAO that checks the title resultSet - checkCouponTitle
		
		//adding the coupon to the coupon table in the DB
		try {
			coupDbDao.createCoupon(coupon);
		} catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException  
			throw new CompanyFacadeException("CompanyFacadeException - "
					+ "createCoupon Error", e);
		}		
		// updating company_coupon table in the DB
		long couponId = coupon.getId();
		long compId = company.getId();
		try {
			compDbDao.addCompanyCoupon(compId, couponId);
		} 
		// Catching couponSystemException
		catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException  
			throw new CompanyFacadeException("CompanyFacadeException - "
					+ "createCoupon Error", e);
		}
	}
	
	//A methods that gets a coupon instance and removes it from the coupon table and company_coupon table in the DB
	//TODO: check if the coupon exists before removing
	public void removeCoupon(Coupon coupon) throws CompanyFacadeException{
		//TODO: check if the coupon exists
		//remove from coupon table in the DB
		try {
			coupDbDao.removeCoupon(coupon);
		} 
		// Catching couponSystemException
		catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException  
			throw new CompanyFacadeException("CompanyFacadeException - "
					+ "removeCoupon Error", e);
		}
		//remove from company_coupon table
		long couponId = coupon.getId();
		long compId = company.getId();
		try {
			compDbDao.removeCompanyCoupon(compId, couponId);
		} 
		// Catching couponSystemException
		catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException  
			throw new CompanyFacadeException("CompanyFacadeException - "
					+ "removeCoupon Error", e);
		}
	}
	
	
	//A method that update coupon details - EndDate and Price
	public void updateCoupon(Coupon coupon) throws CompanyFacadeException{
		//TODO: check if coupon exists
		try {
			//updating the coupon
			coupDbDao.updateCoupon(coupon);
		} 
		// Catching couponSystemException
		catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException
			throw new CompanyFacadeException("CompanyFacadeException - "
					+ "updateCoupon Error", e);
		}
		
	}
	
	//A method that gets coupon's ID and returns that coupon's instance
	public Coupon getCoupon(long id) throws CompanyFacadeException{
		try {
			return coupDbDao.getCoupon(id);
		} 
		// Catching couponSystemException
		catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException
			throw new CompanyFacadeException("CompanyFacadeException - "
					+ "getCoupon Error", e);
		}
	}
	
	
	//A method that returns a list of all the Coupons of the company
	public Collection<Coupon> getAllCoupons() throws CompanyFacadeException{
		
		try {
			return compDbDao.getCoupons(company.getId());
		} 
		// Catching couponSystemException
		catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException
			throw new CompanyFacadeException("CompanyFacadeException - "
					+ "getAllCoupons Error", e);
		}
	}
	
	//A method that returns all of the company's coupons with a specific type
	public Collection<Coupon> getCouponByType(CouponType type) throws CustomerFacadeException{
		try {
			// Invoking the getCoupons method in CompanyDBDAO
			Collection<Coupon> coupons = compDbDao.getCoupons(company.getId());

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

			// In case of a problem throw new CompanyFacadeException
			throw new CustomerFacadeException("CustomerFacadeException - " 
					+ "getCouponsByType() Error", e);
		}

	}

	// toString
	@Override
	public String toString() {
		return "CompanyFacade [company=" + company + "]";
	}

	
	
}
