package com.couponproject.dbdao.tests;

import com.couponproject.beans.Coupon;
import com.couponproject.beans.Customer;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.CustomerAlreadyExistsException;
import com.couponproject.exception.CustomerDoesNotExistException;
import com.couponproject.exception.IllegalPasswordException;
import com.couponproject.system.CouponSystem;

// The main method of this class 
// contains a test program for all the methods in CustomerDBDAO calls
public class TestCustomerDBDAO {

	// Main
	public static void main(String[] args) {
		// Each method in CustomerDBDAO is being teased in a separate private
		// method

		createCustomerTest();
		// removeCustomerTest();
		// updateCustomerTest();
		// getCustomerTest();
		// getAllCustomerTest();
		// getCouponsTest();
		// loginTest();
	}

	// *******
	// Methods
	// *******

	// Testing getCustomerTest()
	private static void getCustomerTest() {

		try {
			System.out.println(CouponSystem.getInstance().getCustomerDBDAO().getCustomer(99488));
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}

	}

	// Testing login()
	private static void loginTest() {
		try {
			if (CouponSystem.getInstance().getCustomerDBDAO().login("customer 115", "password 116")) {
				System.out.println("Successful LOGIN");
			} else {
				System.out.println("unSuccessful Login");
			}
		} catch (CouponSystemException e) {
			System.out.println("loginTest Error");
			e.printStackTrace();
		}

	}

	// Testing getAllCustomer()
	private static void getAllCustomerTest() {
		try {
			// This is the hart of the test
			for (Customer customer : CouponSystem.getInstance().getCustomerDBDAO().getAllCustomer()) {
				System.out.println(customer);
			}
		} catch (CouponSystemException e) {
			System.out.println("getAllCustomerTest Error");
			e.printStackTrace();
		}
	}

	// Testing createCustomer()
	private static void createCustomerTest() {
		// Add new 'Customer's to DB
		for (int i = 0; i < 1; i++) {
			// Customer instance
			Customer customer = new Customer("customer " + (i + 99), "A" + (i + 99));
			try {
				// This is the hart of the test
				// converting costumer object into a sql query and running it
				CouponSystem.getInstance().getCustomerDBDAO().createCustomer(customer);
			} catch (CouponSystemException | CustomerAlreadyExistsException | IllegalPasswordException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// Testing removeCustomer()
	private static void removeCustomerTest() {
		// new Customer's for deletion
		for (int i = 0; i < 25; i++) {
			// Customer instance
			Customer customer = new Customer(i + 1, "customer " + (i + 1), "password " + (i + 1));
			try {
				// This is the hart of the test
				// converting costumer object into a sql query and urning it
				CouponSystem.getInstance().getCustomerDBDAO().removeCustomer(customer);
			} catch (CouponSystemException | CustomerDoesNotExistException e) {
				System.out.println("removeCustomerTest Error");
				e.printStackTrace();
			}
		}
	}

	// Testing getCoupons()
	private static void getCouponsTest() {
		try {
			// This is the hart of the test
			for (Coupon coupon : CouponSystem.getInstance().getCustomerDBDAO().getCoupons(108)) {
				System.out.println(coupon);
			}
		} catch (CouponSystemException e) {
			System.out.println("getCouponsTest Error");
			e.printStackTrace();
		}

	}

}
