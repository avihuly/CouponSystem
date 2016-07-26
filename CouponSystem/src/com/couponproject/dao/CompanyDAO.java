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
public interface CompanyDAO extends DAO {
	// ************
	// Methods list
	// ************

	// This method gets instance of a Company and adds it to the Company table
	// in the DB
	public void createCompany(Company company) throws CouponSystemException, IllegalPasswordException,
			CompanyAlreadyExistsException, EmailAlreadyExistsException;

	// This method gets instance of a Company and removes it from the Company
	// table in the DB
	public void removeCompany(Company company) throws CouponSystemException, CompanyDoesNotExistException;

	// This method gets instance of a Company and updates it in the Company
	// table in the DB
	public void updateCompany(Company company) throws CouponSystemException, IllegalPasswordException,
			CompanyAlreadyExistsException, EmailAlreadyExistsException;

	// This method gets company's ID and return the Company instance with that
	// ID
	public Company getCompany(long id) throws CouponSystemException;

	// This method the Collection of all the companies in theCompany table in
	// the DB
	public Collection<Company> getAllCompanies() throws CouponSystemException;

	// This method returns a Collection of all the coupons of a specific Company
	public Collection<Coupon> getCoupons(long id) throws CouponSystemException;

	// The login method gets String compName and String password and returns
	// whether password fits compName (true) or not (false)
	public boolean login(String compName, String password) throws CouponSystemException;

	// This method gets a company name and password and return an instance of
	// company with the related information in the company
	// table in the DB
	public Company getCompany(String compName, String password) throws CouponSystemException;

	// This method adds coupon ID and company ID to the joined company_coupon
	// table in the DB
	void addCompanyCoupon(long compId, long couponId) throws CouponSystemException;

	// This method removes a line from the company_coupon table with a specific
	// company ID and coupon ID
	void removeCompanyCoupon(long compId, long couponId)
			throws CouponSystemException, CompanyCouponDoesNotExistsException;
}
