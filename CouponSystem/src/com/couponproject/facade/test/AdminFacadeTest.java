package com.couponproject.facade.test;

import com.couponproject.beans.Company;
import com.couponproject.exception.AdminFacadeException;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.facade.AdminFacade;


public class AdminFacadeTest {
	// CouponClientFacade instance for testing
	private static AdminFacade adminFacade;

	public static void main(String[] args) {
		
		testLogin();
		testCreateCompany();
		testGetAllCompanies();
	}
	
	// Test login
	private static void testLogin() {
		try {
			adminFacade = (AdminFacade) AdminFacade.login("admin", "admin");
		} catch (CouponSystemException e) {
			e.toString();
			e.printStackTrace();
		}

		System.out.println("Test login:");
		System.out.println(adminFacade);
		System.out.println();
	}
	
	
	// createCompany test
	private static void testCreateCompany(){
		for (int i = 0; i < 25; i++) {
			// Company instance
			Company company = new Company(
					"company " + (i + 7100), 
					"password " + (i + 7100),
					"Email@" + (i + 7100) + ".com");
			try {
				// This is the hart of the test
				// converting company object into a sql query and running it
				adminFacade.createCompany(company);

			} catch (CouponSystemException e) {
				System.out.println("createCustomerTest Error");
				e.printStackTrace();
			}
		}
	}

	private static void testGetAllCompanies(){
		try {
			System.out.println(adminFacade.getAllCompanies());
		} catch (AdminFacadeException e) {
			e.toString();
			e.printStackTrace();
		}
	}
	
	
	
	
}
