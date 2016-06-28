package com.couponproject.dbdao.test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.couponproject.beans.Coupon;
import com.couponproject.beans.Customer;
import com.couponproject.dbdao.CouponDBDAO;

//the main method of this calls 
//contains a test program for all the methods in CouponDBDAO class
public class TestCouponDBDAO {

	// CouponDBDAO instance for testing
	private static final CouponDBDAO coupDbDao = new CouponDBDAO();

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
		for (int i = 0; i < 25; i++) {
			// Coupon instance
			Coupon coupon = new Coupon(
					"title" + (i+1),
					LocalDate.now(),
					new LocalDate,
					"amount" + (i+1),
					"type" + (i+1),
					"message" + (i+1),
					"price" + (i+1),
					"image" + (i+1));
			try {
				// This is the hart of the test
				// converting coupon object into a sql query and running it
				coupDbDao.createCoupon(coupon);
			} catch (SQLException e) {
				System.out.println("createCouponTest Error");
				e.printStackTrace();
			}
		}

	}
}
