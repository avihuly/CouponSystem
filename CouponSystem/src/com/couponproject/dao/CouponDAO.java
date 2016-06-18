package com.couponproject.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.couponproject.beans.*;

public interface CouponDAO {
	
	//TODO: Exception??
	
	//this method gets instance of Coupon and adds it in to the Coupons table in the DB
	public void createCoupon(Coupon coupon) throws SQLException;

	//this method gets instance of Coupon and removes it from the Coupons table in the DB
	public void removeCoupon(Coupon coupon);

	//this method gets instance of Coupon and updates it in the Coupons table in the DB
	public void updateCoupon(Coupon coupon);
	
	//this method gets Coupon's ID and returns instance of Coupon that this ID belongs to
	public Coupon getCoupon(long id);
	
	//this method returns a Collection of all the Coupons in the couopon table in the DB
	public Collection<Coupon> getAllCoupons();
	
	//this method gets a couponType and returns a Collection of Coupons that have that couponType
	public Collection<Coupon> getCouponsByType(CouponType couponType);
	
}
