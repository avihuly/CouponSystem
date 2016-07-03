package com.couponproject.facade.test;

import com.couponproject.exception.FacadeException;
import com.couponproject.facade.CompanyFacade;
import com.couponproject.facade.CouponClientFacade;

public class CompanyFacadeTest {
	
	// CouponClientFacade instance for testing
	private static CouponClientFacade facade;
	
	public static void main(String[] args) {
		
		try {
			
			// test login
			facade = CompanyFacade.login("company 7000", "password 7000");
			System.out.println(facade);
 
			
		} catch (FacadeException e) {
			e.toString();
			e.printStackTrace();
		}	
	}
}
