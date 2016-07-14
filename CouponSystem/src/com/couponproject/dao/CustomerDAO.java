package com.couponproject.dao;

import java.util.Collection;
import com.couponproject.beans.*;
import com.couponproject.dbdao.CustomerAlreadyExistsException;
import com.couponproject.dbdao.CustomerDoesNotExistException;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.IllegalPasswordException;


// this interface specify's all the functions 
// of a Customer in relation to the data base
public interface CustomerDAO {
	// ************
	// Methods list
	// ************
	
	// This method should take a Customer instance as argument 
	// and add its details to DB
	public void createCustomer(Customer custumer) 
			throws CouponSystemException, 
			IllegalPasswordException, 
			CustomerAlreadyExistsException;
	
	// This method should take a Customer instance as argument 
	// and remove its details from DB
	public void removeCustomer(Customer custumer) 
			throws CouponSystemException, 
			CustomerDoesNotExistException;
	
	// This method should take a Customer instance as argument 
	// and update its details to DB
	public void updateCustomer(Customer custumer) 
			throws CouponSystemException, 
			IllegalPasswordException, 
			CustomerAlreadyExistsException;
	
	// This method should take id (long) as argument 
	// and return a corresponding Customer instance
	public Customer getCustomer(long id) 
			throws CouponSystemException, 
			CustomerAlreadyExistsException;
	
	// This method should take String name and String password as argument 
	// and return a corresponding Customer instance
	public Customer getCustomer(String name, String password) 
			throws CouponSystemException, 
			CustomerAlreadyExistsException;
	
	// This method should return a Collection of all Customer instances
	public Collection<Customer> getAllCustomer() throws CouponSystemException;
	
	// This method should return a Collection of all Coupon instances of a specific Customer instance
	public Collection<Coupon> getCoupons(long id) throws CouponSystemException;
	
	// This method should take customer name and password as argument 
	// and return a boolean indicating a successful login or not
	public boolean login(String custNmae, String password) throws CouponSystemException;
	
}
