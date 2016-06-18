package com.couponproject.dbdao.test;

import java.sql.SQLException;

import com.couponproject.beans.Coupon;
import com.couponproject.beans.Customer;
import com.couponproject.dbdao.CustomerDBDAO;

// the main method of this class 
// contains a test program for all the methods in CustomerDBDAO calls
// in addition: Customer, Coupon & CouponType class are being used
public class TestCustomerDBDAO {

	// CustomerDBDAO instance for testing
	private static final CustomerDBDAO custDbDao = new CustomerDBDAO();

	// Main
	public static void main(String[] args) {
		System.out.println("i am adding this line to check github, it should be deleted");

		// Each method in CustomerDBDAO is being teased in a separate private method

		 //createCustomerTest();
		 //removeCustomerTest();
		 //updateCustomerTest();
		 //getCustomerTest();
		 //getAllCustomerTest();
		 getCouponsTest();
		 //loginTest();

	}



	///////////////////////////////
	///////// METHODS/////////////
	//////////////////////////////

	// Testing login()
	private static void loginTest() {
		try {
			if (custDbDao.login("customer 18", "password 18")) {
				System.out.println("Successful LOGIN");
			} else {
				System.out.println("unSuccessful Login");
			}
		} catch (SQLException e) {
			System.out.println("loginTest Error");
			e.printStackTrace();
		}

	}

	// Testing getAllCustomer()
	private static void getAllCustomerTest() {
		try {
			// This is the hart of the test
			for (Customer customer : custDbDao.getAllCustomer()) {
				System.out.println(customer);
			}
		} catch (SQLException e) {
			System.out.println("getAllCustomerTest Error");
			e.printStackTrace();
		}
	}

	// Testing createCustomer()
	private static void createCustomerTest() {
		// Add new 'Customer's to DB
		for (int i = 0; i < 25; i++) {
			// Customer instance
			Customer customer = new Customer("customer " + (i + 1), "password " + (i + 1));
			try {
				// This is the hart of the test
				// converting costumer object into a sql query and running it
				custDbDao.createCustomer(customer);
			} catch (SQLException e) {
				System.out.println("createCustomerTest Error");
				e.printStackTrace();
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
				custDbDao.removeCustomer(customer);
			} catch (SQLException e) {
				System.out.println("removeCustomerTest Error");
				e.printStackTrace();
			}
		}
	}

	// Testing getCoupons()
	private static void getCouponsTest() {
		try {
			// This is the hart of the test
			for(Coupon coupon:custDbDao.getCoupons(108)){
				System.out.println(coupon);
			}
		} catch (SQLException e) {
			System.out.println("getCouponsTest Error");
			e.printStackTrace();
		}
		
	}

}
