package com.couponproject.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.couponproject.beans.*;
import com.couponproject.exception.CouponSystemException;

public interface CompanyDAO {
	//TODO: Exception??

	// this method gets instance of a Company and adds it to the Company table in the DB
	public void createCompany(Company company) throws SQLException, CouponSystemException;
	
	//this method gets instance of a Company and removes it from the Company table in the DB
	public void removeCompany(Company company) throws SQLException, CouponSystemException;
	
	//this method gets instance of a Company and updates it in the Company table in the DB
	public void updateCompany(Company company) throws SQLException, CouponSystemException;
	
	//this method gets company's ID and return the Company instance with that ID
	public Company getCompany(long id) throws SQLException, CouponSystemException;
	
	//this method the Collection of all the companies in theCompany table in the DB
	public Collection<Company> getAllCompanies() throws SQLException, CouponSystemException;
	
	//this method returns a Collection of all the coupons of a specific Company 	
	public Collection<Coupon> getCoupons(long id) throws SQLException, CouponSystemException;
	
	//the login method gets String compName and String password and returns whether password fits compName (true) or not (false) 
	public boolean login(String compName, String password) throws SQLException, CouponSystemException;
}
