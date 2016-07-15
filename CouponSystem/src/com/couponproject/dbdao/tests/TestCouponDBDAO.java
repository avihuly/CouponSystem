package com.couponproject.dbdao.tests;

import java.time.LocalDate;

import com.couponproject.beans.Coupon;
import com.couponproject.constants.CouponType;
import com.couponproject.dbdao.CouponDBDAO;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.CouponTitleAlreadyExistException;

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
		for (int i = 49; i < 54; i++) {
			// Coupon instance
			Coupon coupon = new Coupon(
					"title" + ("ELECTRICITY " +i+1),
					LocalDate.now(),
					LocalDate.of(2017, 11, 23),
					((int)Math.random()*102),
					CouponType.ELECTRICITY,
					"message" + (i+1),
					(Math.random()*10+20.99),
					"image" + (i+1));
			try {
				// This is the hart of the test
				// converting coupon object into a sql query and running it
				try {
					CouponDBDAO.getInstace().createCoupon(coupon);
				} catch (CouponTitleAlreadyExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (CouponSystemException e) {
				System.out.println("createCouponTest Error");
			}
		}

	}
}
