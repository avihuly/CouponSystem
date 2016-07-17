package com.couponproject.facade;

import java.time.LocalDate;
import java.util.Collection;

import com.couponproject.beans.*;
import com.couponproject.constants.CouponType;
import com.couponproject.exception.*;

import com.couponproject.dbdao.CompanyDBDAO;
import com.couponproject.dbdao.CouponDBDAO;

//TODO: limits cheking!!!
public class CompanyFacade {
	// **********
	// Attributes
	// **********
	private Company company;
	
	// ***********
	// constructor
	// ***********
	
	// constructor loading company after login
	public CompanyFacade(String name, String password) throws CompanyFacadeException {
		try {
			company = CompanyDBDAO.getInstace().getCompany(name, password);
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
	public static CompanyFacade login(String name, String password) throws CompanyFacadeException {
		
		try {
			// Invoking the login method in CustomerDBDAO
			// if true - return new CustomerFacade instance with a specific Company
			if (CompanyDBDAO.getInstace().login(name, password)) {
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
	public void createCoupon(Coupon coupon) throws CompanyFacadeException, CouponTitleAlreadyExistException{
		//adding the coupon to the coupon table in the DB
		try {
			CouponDBDAO.getInstace().createCoupon(coupon);
		} catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException  
			throw new CompanyFacadeException("CompanyFacadeException - "
					+ "createCoupon Error", e);
		}		
		// updating company_coupon table in the DB
		long couponId = coupon.getId();
		long compId = company.getId();
		try {
			CompanyDBDAO.getInstace().addCompanyCoupon(compId, couponId);
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
	public void removeCoupon(Coupon coupon) throws CompanyFacadeException, CouponDoesNotExistException, CompanyCouponDoesNotExistsException{
		//remove from coupon table in the DB
		try {
			CouponDBDAO.getInstace().removeCoupon(coupon);
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
			CompanyDBDAO.getInstace().removeCompanyCoupon(compId, couponId);
		} 
		// Catching couponSystemException
		catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException  
			throw new CompanyFacadeException("CompanyFacadeException - "
					+ "removeCoupon Error", e);
		}
		//remove coupon from customer_coupon table
		try {
			CouponDBDAO.getInstace().removeCouponCustomerByCouponID(couponId);
		} 
		// Catching couponSystemException
		catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException  
			throw new CompanyFacadeException("CompanyFacadeException - "
					+ "removeCoupon Error", e);
		}
	}
	
	
	//A method that update coupon details - EndDate and Price
	public void updateCoupon(Coupon coupon) throws CompanyFacadeException, CouponTitleAlreadyExistException{
		//TODO: check if coupon exists
		try {
			//updating the coupon
			CouponDBDAO.getInstace().updateCoupon(coupon);
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
			return CouponDBDAO.getInstace().getCoupon(id);
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
			return CompanyDBDAO.getInstace().getCoupons(company.getId());
		} 
		// Catching couponSystemException
		catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException
			throw new CompanyFacadeException("CompanyFacadeException - "
					+ "getAllCoupons Error", e);
		}
	}
	
	//A method that returns all of the company's coupons with a specific type
	public Collection<Coupon> getCouponByType(CouponType type) throws CompanyFacadeException{
		try {
			// Invoking the getCoupons method in CompanyDBDAO
			Collection<Coupon> coupons = CompanyDBDAO.getInstace().getCoupons(company.getId());

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
			throw new CompanyFacadeException("CompanyFacadeException - " 
					+ "getCouponsByType() Error", e);
		}
	}
	
	//A method that returns all of the company's coupons up to a specific price
	public Collection<Coupon> getCouponByPrice(double price) throws CompanyFacadeException{
		try {
			// Invoking the getCoupons method in CompanyDBDAO
			Collection<Coupon> coupons = CompanyDBDAO.getInstace().getCoupons(company.getId());

			// Iterating coupons collection and 
			// removing coupons that above the specified price
			for (Coupon coupon : coupons) {
				if (coupon.getPrice() > price){
					coupons.remove(coupon);
				}
			}
			
			//return couponsByPrice collection
			return coupons;

		// Catching couponSystemException
		} catch (CouponSystemException e) {

			// In case of a problem throw new CompanyFacadeException
			throw new CompanyFacadeException("CompanyFacadeException - " 
					+ "getCouponsByPrice() Error", e);
		}
	}
	
	//A method that returns all of the company's coupons after a specific start date
	public Collection<Coupon> getCouponByStartDate(LocalDate date) throws CompanyFacadeException{
		try {
			// Invoking the getCoupons method in CompanyDBDAO
			Collection<Coupon> coupons = CompanyDBDAO.getInstace().getCoupons(company.getId());

			// Iterating coupons collection and 
			// removing coupons that start before specified date
			for (Coupon coupon : coupons) {
				if (coupon.getStartDate().isBefore(date)){
					coupons.remove(coupon);
				}
			}
			
			//return couponsByStartDate collection
			return coupons;

		// Catching couponSystemException
		} catch (CouponSystemException e) {

			// In case of a problem throw new CompanyFacadeException
			throw new CompanyFacadeException("CompanyFacadeException - " 
					+ "getCouponsByStartDate() Error", e);
		}
	}
	
	//A method that returns all of the company's coupons that expire before a specific end date
	public Collection<Coupon> getCouponByEndDate(LocalDate date) throws CompanyFacadeException{
		try {
			// Invoking the getCoupons method in CompanyDBDAO
			Collection<Coupon> coupons = CompanyDBDAO.getInstace().getCoupons(company.getId());

			// Iterating coupons collection and 
			// removing coupons that start before specified date
			for (Coupon coupon : coupons) {
				if (coupon.getEndDate().isAfter(date)){
					coupons.remove(coupon);
				}
			}
			
			//return couponsByEndDate collection
			return coupons;

		// Catching couponSystemException
		} catch (CouponSystemException e) {

			// In case of a problem throw new CompanyFacadeException
			throw new CompanyFacadeException("CompanyFacadeException - " 
					+ "getCouponsByEndDate() Error", e);
		}
	}

	// toString
	@Override
	public String toString() {
		return "CompanyFacade [company=" + company + "]";
	}
}
