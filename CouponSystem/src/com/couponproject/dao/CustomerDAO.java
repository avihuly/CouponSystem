package com.couponproject.dao;

import java.util.Collection;
import com.couponproject.beans.*;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.CustomerAlreadyExistsException;
import com.couponproject.exception.CustomerDoesNotExistException;
import com.couponproject.exception.IllegalPasswordException;

/**
 * This interface provides a list of C.R.U.D methods that can be performed on and with a Customer Bean 
 * 
 * @author Avi Huly and Orit Blum
 * @version 1.0
 *
 */
public interface CustomerDAO {
	// ************
	// Methods list
	// ************
	/**
	 * Creates a new Customer in a Database with the parameters of the given customer bean instance  
	 * @param custumer An instance of a Bean Customer
	 * @throws CouponSystemException
	 * @throws IllegalPasswordException
	 * @throws CustomerAlreadyExistsException
	 */
	public void createCustomer(Customer custumer) 
			throws CouponSystemException, 
			IllegalPasswordException, 
			CustomerAlreadyExistsException;
	
	/**
	 * Removes a Customer from the Database with the parameters of the given customer bean instance
	 * @param custumer An instance of a Bean Customer
	 * @throws CouponSystemException
	 * @throws CustomerDoesNotExistException
	 */
	public void removeCustomer(Customer custumer) 
			throws CouponSystemException, 
			CustomerDoesNotExistException;
	
	/**
	 * Updates a Customer in the Database with the parameters of the given customer bean instance
	 * @param custumer An instance of a Bean Customer
	 * @throws CouponSystemException
	 * @throws IllegalPasswordException
	 * @throws CustomerAlreadyExistsException
	 */
	public void updateCustomer(Customer custumer) 
			throws CouponSystemException, 
			IllegalPasswordException, 
			CustomerAlreadyExistsException;
	
	/**
	 * Returns an instance of a Bean Customer based on a giver Cutomer's ID
	 * @param id Existing Customer's ID
	 * @return An instance of a Bean Customer
	 * @throws CouponSystemException
	 * @throws CustomerAlreadyExistsException
	 */
	public Customer getCustomer(long id) 
			throws CouponSystemException, 
			CustomerAlreadyExistsException;
	
	/**
	 * Returns an instance of a Bean Customer based on a giver Cutomer's Name and Password
	 * @param name Existing Customer's Name
	 * @param password Existing Customer's Password
	 * @return An instance of a Bean Customer
	 * @throws CouponSystemException
	 * @throws CustomerAlreadyExistsException
	 */
	public Customer getCustomer(String name, String password) 
			throws CouponSystemException, 
			CustomerAlreadyExistsException;

	/**
	 * Returns a Collection of all the Customer instances
	 * @return Collection of Customer instances
	 * @throws CouponSystemException
	 */
	public Collection<Customer> getAllCustomer() throws CouponSystemException;
	
	/**
	 * Returns a Collection of all Coupon instances of a specific Customer instance
	 * @param id Existing Customer's ID
	 * @return Collection of Coupon instances 
	 * @throws CouponSystemException
	 */
	public Collection<Coupon> getCoupons(long id) throws CouponSystemException;
	
	/**
	 * Returns a boolean indicating a successful login or not based on customer's given
	 *  name and password as argument
	 * @param custNmae Customer's Name
	 * @param password Customer's Password
	 * @return true for successful login and false for unsuccessful login
	 * @throws CouponSystemException
	 */
	public boolean login(String custNmae, String password) throws CouponSystemException;
	
	/**
	 * Removes a line from the joined custome_coupon table based on a given Customer's and Coupon's IDs
	 * @param custId This Customer's ID
	 * @param couponId This Coupon's ID
	 */
	void removeCustomerCoupon(long custId, long couponId);
	
}
