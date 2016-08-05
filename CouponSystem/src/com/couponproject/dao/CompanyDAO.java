package com.couponproject.dao;

import java.util.Collection;

import com.couponproject.beans.*;
import com.couponproject.exception.CompanyAlreadyExistsException;
import com.couponproject.exception.CompanyCouponDoesNotExistsException;
import com.couponproject.exception.CompanyDoesNotExistException;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.EmailAlreadyExistsException;
import com.couponproject.exception.IllegalPasswordException;

//This interface specify's all the functions 
//of a Company in relation to the data base
/**
 * This interface provides a list of C.R.U.D methods that can be performed on and with a Company Bean 
 * 
 * @author Avi Huly and Orit Blum
 * @version 1.0
 *
 */
public interface CompanyDAO extends DAO {
	// ************
	// Methods list
	// ************

	// This method gets instance of a Company and adds it to the Company table
	// in the DB
	/**
	 * Creates a new Company in a Database with the parameters of the given company bean instance
	 * @param company An instance of a Bean Company
	 * @throws CouponSystemException
	 * @throws IllegalPasswordException
	 * @throws CompanyAlreadyExistsException
	 * @throws EmailAlreadyExistsException
	 */
	public void createCompany(Company company) throws CouponSystemException, IllegalPasswordException,
			CompanyAlreadyExistsException, EmailAlreadyExistsException;

	// This method gets instance of a Company and removes it from the Company
	// table in the DB
	/**
	 * Removes a Company from the Database with the parameters of the given company bean instance
	 * 
	 * @param company An instance of a Bean Company
	 * @throws CouponSystemException
	 * @throws CompanyDoesNotExistException
	 */
	public void removeCompany(Company company) throws CouponSystemException, CompanyDoesNotExistException;

	// This method gets instance of a Company and updates it in the Company
	// table in the DB
	/**
	 * Updates a Company in the Database with the parameters of the given company bean instance
	 * 
	 * @param company An instance of a Bean Company
	 * @throws CouponSystemException
	 * @throws IllegalPasswordException
	 * @throws CompanyAlreadyExistsException
	 * @throws EmailAlreadyExistsException
	 */
	public void updateCompany(Company company) throws CouponSystemException, IllegalPasswordException,
			CompanyAlreadyExistsException, EmailAlreadyExistsException;

	// This method gets company's ID and return the Company instance with that ID
	/**
	 * Returns an instance of a Bean Company based on a giver Company's ID
	 * 
	 * @param id Existing Company's ID
	 * @return An instance of a Bean Comapny
	 * @throws CouponSystemException
	 */
	public Company getCompany(long id) throws CouponSystemException;

	// This method the Collection of all the companies in theCompany table in
	// the DB
	/**
	 * Returns a Collection of all the Company instances
	 * 
	 * @return Collection of Company instances
	 * @throws CouponSystemException
	 */
	public Collection<Company> getAllCompanies() throws CouponSystemException;

	// This method returns a Collection of all the coupons of a specific Company
	/**
	 * Returns a Collection of all Coupon instances of a specific Company instance
	 * @param id Existing Company's ID
	 * @return Collection of Coupon instances
	 * @throws CouponSystemException
	 */
	public Collection<Coupon> getCoupons(long id) throws CouponSystemException;

	// The login method gets String compName and String password and returns
	// whether password fits compName (true) or not (false)
	/**
	 * Returns a boolean indicating a successful login or not based on company's given
	 *  name and password as argument
	 * 
	 * @param compName Company's Name
	 * @param password Company's Password
	 * @return true for successful login and false for unsuccessful login
	 * @throws CouponSystemException
	 */
	public boolean login(String compName, String password) throws CouponSystemException;

	// This method gets a company name and password and return an instance of
	// company with the related information in the company
	// table in the DB
	/**
	 * Returns an instance of a Bean Company based on a giver Company's Name and Password
	 * 
	 * @param compName Existing Company's name
	 * @param password Existing Company's password
	 * @return An instance of a Bean Company
	 * @throws CouponSystemException
	 */
	public Company getCompany(String compName, String password) throws CouponSystemException;

	// This method adds coupon ID and company ID to the joined company_coupon
	// table in the DB
	/**
	 * Updates a joined Company-Coupon table based on a gives Company's and Coupon's IDs
	 * 
	 * @param compId Existing Company's ID
	 * @param couponId Existing Coupon's ID
	 * @throws CouponSystemException
	 */
	void addCompanyCoupon(long compId, long couponId) throws CouponSystemException;

	// This method removes a line from the company_coupon table with a specific
	// company ID and coupon ID
	/**
	 * Removes a line from a joined Company-Coupon table based on a gives Company's and Coupon's IDs
	 * 
	 * @param compId Existing Company's ID
	 * @param couponId Existing Coupon's ID
	 * @throws CouponSystemException
	 * @throws CompanyCouponDoesNotExistsException
	 */
	void removeCompanyCoupon(long compId, long couponId)
			throws CouponSystemException, CompanyCouponDoesNotExistsException;
}
