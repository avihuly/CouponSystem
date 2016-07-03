package com.couponproject.facade.test;

import com.couponproject.exception.FacadeException;
import com.couponproject.facade.CouponClientFacade;
import com.couponproject.facade.CustomerFacade;

public class CustomerFacadeTest {
	
	// CouponClientFacade instance for testing
	private static CouponClientFacade facade;
	
	public static void main(String[] args) {
		
		try {
			
			// test login
			facade = CustomerFacade.login("customer 100", "password 100");
			System.out.println(facade);
 
			
		} catch (FacadeException e) {
			e.toString();
			e.printStackTrace();
		}	
	}
}
