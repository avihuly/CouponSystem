package com.couponproject.dao;


import java.util.Collection;

import com.couponproject.beans.*;
import com.couponproject.constants.CouponType;
import com.couponproject.exception.CouponDoesNotExistException;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.CouponTitleAlreadyExistException;

//this interface specify's all the functions 
//of a Coupon in relation to the data base
/**
 * This interface provides a list of C.R.U.D methods that can be performed on and with a Coupon Bean 
 * 
 * @author Avi Huly and Orit Blum
 * @version 1.0
 */
public interface CouponDAO {
	// ************
	// Methods list
	// ************
	
	//This method gets instance of Coupon and adds it in to the Coupons table in the DB
	/**
	 * Creates a new Coupon in a Database with the parameters of the given coupon bean instance
	 * @param coupon An instance of a Bean coupon
	 * @throws CouponSystemException
	 * @throws CouponTitleAlreadyExistException
	 */
	public void createCoupon(Coupon coupon) 
			throws CouponSystemException, 
			CouponTitleAlreadyExistException;

	//This method gets instance of Coupon and removes it from the Coupons table in the DB
	/**
	 * Removes a Coupon from the Database with the parameters of the given coupon bean instance
	 * @param coupon An instance of a Bean Coupon
	 * @throws CouponSystemException
	 * @throws CouponDoesNotExistException
	 */
	public void removeCoupon(Coupon coupon) 
			throws CouponSystemException, 
			CouponDoesNotExistException;

	//This method gets instance of Coupon and updates it in the Coupons table in the DB
	/**
	 * Updates a Coupon in the Database with the parameters of the given coupon bean instance
	 * @param coupon An instance of a Bean Coupon
	 * @throws CouponSystemException
	 * @throws CouponTitleAlreadyExistException
	 */
	public void updateCoupon(Coupon coupon) 
			throws CouponSystemException, 
			CouponTitleAlreadyExistException;
	
	//This method gets Coupon's ID and returns instance of Coupon that this ID belongs to
	/**
	 * Returns an instance of a Bean Coupon based on a giver Coupon's ID
	 * @param id Existing Coupon's ID
	 * @return An instance of a Bean Coupon
	 * @throws CouponSystemException
	 */
	public Coupon getCoupon(long id) throws CouponSystemException;
	
	//This method returns a Collection of all the Coupons in the coupon table in the DB
	/**
	 * Returns a Collection of all the Coupons instances
	 * @return Collection of Coupon instances
	 * @throws CouponSystemException
	 */
	public Collection<Coupon> getAllCoupons() throws CouponSystemException;
	
	//This method gets a couponType and returns a Collection of Coupons that have that couponType
	/**
	 * eturns a Collection of all the Coupons instances with a specific CouponType
	 * @param couponType The Coupon Type of which a collection of coupons is requested
	 * @return Collection of Coupon instances
	 * @throws CouponSystemException
	 */
	public Collection<Coupon> getCouponsByType(CouponType couponType) throws CouponSystemException;
	
}
