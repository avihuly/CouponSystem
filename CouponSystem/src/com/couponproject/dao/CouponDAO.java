package com.couponproject.dao;


import java.util.Collection;

import com.couponproject.beans.*;
import com.couponproject.constants.CouponType;
import com.couponproject.exception.CouponDoesNotExistException;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.CouponTitleAlreadyExistException;

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
	/**
	 * Creates a new Coupon in a Database with the parameters of the given coupon bean instance
	 * @param coupon An instance of a Bean coupon
	 * @throws CouponSystemException
	 * @throws CouponTitleAlreadyExistException
	 */
	public void createCoupon(Coupon coupon) 
			throws CouponSystemException, 
			CouponTitleAlreadyExistException;

	/**
	 * Removes a Coupon from the Database with the parameters of the given coupon bean instance
	 * @param coupon An instance of a Bean Coupon
	 * @throws CouponSystemException
	 * @throws CouponDoesNotExistException
	 */
	public void removeCoupon(Coupon coupon) 
			throws CouponSystemException, 
			CouponDoesNotExistException;

	/**
	 * Updates a Coupon in the Database with the parameters of the given coupon bean instance
	 * @param coupon An instance of a Bean Coupon
	 * @throws CouponSystemException
	 * @throws CouponTitleAlreadyExistException
	 */
	public void updateCoupon(Coupon coupon) 
			throws CouponSystemException, 
			CouponTitleAlreadyExistException;
	
	/**
	 * Returns an instance of a Coupon Bean based on a given Coupon's ID
	 * @param id Existing Coupon's ID
	 * @return An instance of a Bean Coupon
	 * @throws CouponSystemException
	 */
	public Coupon getCoupon(long id) throws CouponSystemException;
	
	/**
	 * Returns a Collection of all the Coupons instances
	 * @return Collection of Coupon instances
	 * @throws CouponSystemException
	 */
	public Collection<Coupon> getAllCoupons() throws CouponSystemException;
	
	/**
	 * Returns a Collection of all the Coupons instances with a specific CouponType
	 * @param couponType The Coupon Type of which a collection of coupons is requested
	 * @return Collection of Coupon instances
	 * @throws CouponSystemException
	 */
	public Collection<Coupon> getCouponsByType(CouponType couponType) throws CouponSystemException;
	
}
