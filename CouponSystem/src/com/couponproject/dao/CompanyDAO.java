package com.couponproject.dao;

import java.util.Collection;

import com.couponproject.beans.*;
import com.couponproject.exception.CompanyAlreadyExistsException;
import com.couponproject.exception.CompanyCouponDoesNotExistsException;
import com.couponproject.exception.CompanyDoesNotExistException;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.EmailAlreadyExistsException;
import com.couponproject.exception.IllegalPasswordException;

/**
 * This interface provides a list of C.R.U.D methods that can be performed on and with a Company Bean 
 * 
 * @author Avi Huly and Orit Blum
 * @version 1.0
 *
 */
public interface CompanyDAO {
	// ************
	// Methods list
	// ************
	/**
	 * Creates a new Company in a Database with the parameters of the given company bean instance
	 *
	 * @param company An instance of a Bean Company
	 * @throws CouponSystemException
	 * @throws IllegalPasswordException
	 * @throws CompanyAlreadyExistsException
	 * @throws EmailAlreadyExistsException
	 */
	public void createCompany(Company company) throws CouponSystemException, IllegalPasswordException,
			CompanyAlreadyExistsException, EmailAlreadyExistsException;

	/**
	 * Removes a Company from the Database with the parameters of the given company bean instance
	 * 
	 * @param company An instance of a Bean Company
	 * @throws CouponSystemException
	 * @throws CompanyDoesNotExistException
	 */
	public void removeCompany(Company company) throws CouponSystemException, CompanyDoesNotExistException;

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

	/**
	 * Returns an instance of a Bean Company based on a given Company's ID
	 * 
	 * @param id Existing Company's ID
	 * @return An instance of a Bean Comapny
	 * @throws CouponSystemException
	 */
	public Company getCompany(long id) throws CouponSystemException;

	/**
	 * Returns a Collection of all the Company instances from DB
	 * 
	 * @return Collection of Company instances
	 * @throws CouponSystemException
	 */
	public Collection<Company> getAllCompanies() throws CouponSystemException;

	/**
	 * Returns a Collection of all Coupon instances of a specific Company instance
	 * 
	 * @param id Existing Company's ID
	 * @return Collection of Coupon instances
	 * @throws CouponSystemException
	 */
	public Collection<Coupon> getCoupons(long id) throws CouponSystemException;

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

	/**
	 * Returns an instance of a Bean Company based on a giver Company's Name and Password
	 * 
	 * @param compName Existing Company's name
	 * @param password Existing Company's password
	 * @return An instance of a Bean Company
	 * @throws CouponSystemException
	 */
	public Company getCompany(String compName, String password) throws CouponSystemException;

	/**
	 * Updates the joined Company-Coupon table based on a gives Company's and Coupon's IDs
	 * 
	 * @param compId Existing Company's ID
	 * @param couponId Existing Coupon's ID
	 * @throws CouponSystemException
	 */
	void addCompanyCoupon(long compId, long couponId) throws CouponSystemException;

	/**
	 * Removes a line from the joined Company-Coupon table based on a given Company's and Coupon's IDs
	 * 
	 * @param compId Existing Company's ID
	 * @param couponId Existing Coupon's ID
	 * @throws CouponSystemException
	 * @throws CompanyCouponDoesNotExistsException
	 */
	void removeCompanyCoupon(long compId, long couponId)
			throws CouponSystemException, CompanyCouponDoesNotExistsException;
}
