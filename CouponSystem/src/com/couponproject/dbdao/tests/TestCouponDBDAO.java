package com.couponproject.dbdao.tests;

import java.time.LocalDate;

import com.couponproject.beans.Coupon;
import com.couponproject.constants.CouponType;
import com.couponproject.dbdao.CouponDBDAO;
import com.couponproject.exception.CouponSystemException;

//the main method of this calls 
//contains a test program for all the methods in CouponDBDAO class
public class TestCouponDBDAO {

	// Main
	public static void main(String[] args) {
		// Each method in CustomerDBDAO is being teased in a separate private
		// method

		createCouponTest();
		// removeCouponTest();
		// updateCouponTest();
		// getCouponTest();
		// getAllCouponsTest();
		// getCouponsTest();
		// getCouponsByTypeTest();

	}

	// Testing createCouponTest()
	private static void createCouponTest() {
		// Add new 'Coupon' to DB
		for (int i = 50; i < 98; i++) {
			// Coupon instance
			Coupon coupon = new Coupon(
					"title" + (i+1),
					LocalDate.now(),
					LocalDate.of(2016, 9, 23),
					((int)Math.random()*102),
					CouponType.RESTAURANT,
					"message" + (i+1),
					(Math.random()*10+20.99),
					"image" + (i+1));
			try {
				// This is the hart of the test
				// converting coupon object into a sql query and running it
				CouponDBDAO.getInstace().createCoupon(coupon);
			} catch (CouponSystemException e) {
				System.out.println("createCouponTest Error");
			}
		}

	}
}
