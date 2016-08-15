package com.couponproject.facade;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

import com.couponproject.beans.*;
import com.couponproject.constants.CouponType;
import com.couponproject.exception.*;
import com.couponproject.system.CouponSystem;
import com.couponproject.dbdao.CompanyDBDAO;
import com.couponproject.dbdao.CouponDBDAO;

/**
 * This Class represents the Company client of the Coupon System. 
 * <p>This class includes all the operations Company can perform in the system</P>
 * 
 * @author Avi Huly and Orit Blum
 * @version 1.0
 */
public class CompanyFacade {
	// **********
	// Attributes
	// **********
	/**
	 * Holds the Company instance with the details of the Logged in company 
	 */
	private Company company;
	
	// ***********
	// constructor
	// ***********
	
	/**
	 * Construct CompanyFacade based on given name and password.
	 * <p>A Company instance of the logged in company is created.</p>
	 * @param name Company's User Name
	 * @param password Company's Password
	 * @throws CompanyFacadeException
	 */
	public CompanyFacade(String name, String password) throws CompanyFacadeException {
		try {
			company = CouponSystem.getInstance().getCompanyDBDAO().getCompany(name, password);
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
	
	/**
	 * Returns CompanyFacade instance upon a successful login and null if login fails
	 * @param name Comapny's User Name
	 * @param password Company's Password
	 * @return CompanyFacade instance
	 * @throws CompanyFacadeException
	 */
	public static CompanyFacade login(String name, String password) throws CompanyFacadeException {
		try {
			// Invoking the login method in CustomerDBDAO
			// if true - return new CustomerFacade instance with a specific Company
			if (CouponSystem.getInstance().getCompanyDBDAO().login(name, password)) {
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
	
	/**
	 * Creates new Coupon in the DB based on a given Coupon instance
	 * The Coupon is added to the coupon table and the company_coupon table
	 * @param coupon Coupon instance
	 * @throws CompanyFacadeException
	 * @throws CouponTitleAlreadyExistException
	 */
	public void createCoupon(Coupon coupon) throws CompanyFacadeException, CouponTitleAlreadyExistException{
		//adding the coupon to the coupon table in the DB
		try {
			CouponSystem.getInstance().getCouponDBDAO().createCoupon(coupon);
		} catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException  
			throw new CompanyFacadeException("Create coupon Error", e);
		}
		
		// updating company_coupon table in the DB
		//TODO: receiving the new coupon's id from the db
		long couponId = coupon.getId();
		long compId = company.getId();
		
		
		try {
			CouponSystem.getInstance().getCompanyDBDAO().addCompanyCoupon(compId, couponId);
		} 
		// Catching couponSystemException
		catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException  
			throw new CompanyFacadeException("Create coupon Error", e);
		}
	}
	
	/**
	 * Removes an existing Coupon from the DB based on a given Coupon instance.
	 * <p>This Coupon is deleted from the DB from coupon table, company_coupon table</p>
	 * and customer_coupon table 
	 * @param coupon Coupon instance
	 * @throws CompanyFacadeException
	 * @throws CouponDoesNotExistException
	 * @throws CompanyCouponDoesNotExistsException
	 */
	public void removeCoupon(Coupon coupon)
			throws CompanyFacadeException, CouponDoesNotExistException, CompanyCouponDoesNotExistsException {
		// remove from company_coupon table
		long couponId = coupon.getId();
		long compId = company.getId();
		try {
			CouponSystem.getInstance().getCompanyDBDAO().removeCompanyCoupon(compId, couponId);
		}
		// Catching couponSystemException
		catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException
			throw new CompanyFacadeException("CompanyFacadeException - " + "removeCoupon Error", e);
		}
		// remove coupon from customer_coupon table
		try {
			CouponSystem.getInstance().getCouponDBDAO().removeCouponCustomerByCouponID(couponId);
		}
		// Catching couponSystemException
		catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException
			throw new CompanyFacadeException("CompanyFacadeException - " + "removeCoupon Error", e);
		}

		// remove from coupon table in the DB
		try {
			CouponSystem.getInstance().getCouponDBDAO().removeCoupon(coupon);
		}
		// Catching couponSystemException
		catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException
			throw new CompanyFacadeException("CompanyFacadeException - " + "removeCoupon Error", e);
		}
	}
	
	/**
	 * Updates an existing Coupon in the DB based on a given Coupon instance
	 * @param coupon Coupon instance
	 * @throws CompanyFacadeException
	 * @throws CouponTitleAlreadyExistException
	 */
	public void updateCoupon(Coupon coupon) throws CompanyFacadeException, CouponTitleAlreadyExistException{
		try {
			//updating the coupon
			CouponSystem.getInstance().getCouponDBDAO().updateCoupon(coupon);
		} 
		// Catching couponSystemException
		catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException
			throw new CompanyFacadeException("CompanyFacadeException - "
					+ "updateCoupon Error", e);
		}
		
	}
	
	/**
	 * Returns Coupon instance based on a given Coupon's ID
	 * @param id Coupon's ID
	 * @return Coupon Instance
	 * @throws CompanyFacadeException
	 */
	public Coupon getCoupon(long id) throws CompanyFacadeException{
		try {
			return CouponSystem.getInstance().getCouponDBDAO().getCoupon(id);
		} 
		// Catching couponSystemException
		catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException
			throw new CompanyFacadeException(e.getMessage(), e);
		}
	}
	
	// -----------------
	// UniqueCouponTypes
	// -----------------
	/**
	 * Returns a collection of of all the CouponTaypes of the Coupons owned by this Company
	 * @return Collection of CouponType
	 * @throws CouponSystemException
	 */
	public Collection<CouponType> getUniqueCouponTypes() throws CouponSystemException{
		return CouponSystem.getInstance().getCompanyDBDAO().getUniqueCouponTypes(company);
	} 
	
	/**
	 * Returns a collection of all the Coupons of this Company
	 * @return Collection of Coupon
	 * @throws CompanyFacadeException
	 */
	public Collection<Coupon> getAllCoupons() throws CompanyFacadeException{
	
		try {
			return CouponSystem.getInstance().getCompanyDBDAO().getCoupons(company.getId());
		} 
		// Catching couponSystemException
		catch (CouponSystemException e) {
			// In case of a problem throw new CompanyFacadeException
			throw new CompanyFacadeException("CompanyFacadeException - "
					+ "getAllCoupons Error", e);
		}
	}
	
	/**
	 * Returns a collection of all the Coupons of this Company with a specific CouponType
	 * @param type CouponType
	 * @return Collection of Coupon
	 * @throws CompanyFacadeException
	 */
	public Collection<Coupon> getCouponByType(CouponType type) throws CompanyFacadeException{
		try {
			// Invoking the getCoupons method in CompanyDBDAO
			Collection<Coupon> coupons = CouponSystem.getInstance().getCompanyDBDAO().getCoupons(company.getId());
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

			// In case of a problem throw new CompanyFacadeException
			throw new CompanyFacadeException("CompanyFacadeException - " 
					+ "getCouponsByType() Error", e);
		}
	}
	
	/**
	 * Returns a collection of all the Coupons of this Company up to a specified price
	 * @param price The highest possible price of the coupons in the returned collection
	 * @return Collection on Coupon
	 * @throws CompanyFacadeException
	 */
	public Collection<Coupon> getCouponByPrice(double price) throws CompanyFacadeException{
		try {
			// Invoking the getCoupons method in CompanyDBDAO
			Collection<Coupon> coupons = CouponSystem.getInstance().getCompanyDBDAO().getCoupons(company.getId());

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
	
	/**
	 * Returns a collection of all the Coupons of this Company that have a Start date after a specific date
	 * @param date The lowest possible Start date of coupons on the returned collection
	 * @return Collection of Coupon
	 * @throws CompanyFacadeException
	 */
	public Collection<Coupon> getCouponByStartDate(LocalDate date) throws CompanyFacadeException{
		try {
			// Invoking the getCoupons method in CompanyDBDAO
			Collection<Coupon> coupons = CouponSystem.getInstance().getCompanyDBDAO().getCoupons(company.getId());

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
	
	/**
	 * Returns a collection of all the Coupons of this Company that have a End date before a specific date
	 * @param date The highest possible End date of coupons on the returned collection
	 * @return Collection of Coupon
	 * @throws CompanyFacadeException
	 */
	public Collection<Coupon> getCouponByEndDate(LocalDate date) throws CompanyFacadeException{
		try {
			// Invoking the getCoupons method in CompanyDBDAO
			Collection<Coupon> coupons = CouponSystem.getInstance().getCompanyDBDAO().getCoupons(company.getId());

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
