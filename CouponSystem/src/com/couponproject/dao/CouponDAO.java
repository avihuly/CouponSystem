package com.couponproject.dao;


import java.util.Collection;

import com.couponproject.beans.*;
import com.couponproject.exception.CouponSystemException;

//this interface specify's all the functions 
//of a Coupon in relation to the data base
public interface CouponDAO {
	// ************
	// Methods list
	// ************
	
	//This method gets instance of Coupon and adds it in to the Coupons table in the DB
	public void createCoupon(Coupon coupon) throws CouponSystemException;

	//This method gets instance of Coupon and removes it from the Coupons table in the DB
	public void removeCoupon(Coupon coupon) throws CouponSystemException;

	//This method gets instance of Coupon and updates it in the Coupons table in the DB
	public void updateCoupon(Coupon coupon) throws CouponSystemException;
	
	//This method gets Coupon's ID and returns instance of Coupon that this ID belongs to
	public Coupon getCoupon(long id) throws CouponSystemException;
	
	//This method returns a Collection of all the Coupons in the coupon table in the DB
	public Collection<Coupon> getAllCoupons() throws CouponSystemException;
	
	//This method gets a couponType and returns a Collection of Coupons that have that couponType
	public Collection<Coupon> getCouponsByType(CouponType couponType) throws CouponSystemException;
	
}
