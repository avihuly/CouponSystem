package com.couponproject.facade.test;

import com.couponproject.beans.Coupon;
import com.couponproject.constants.CouponType;
import com.couponproject.exception.FacadeException;
import com.couponproject.facade.CustomerFacade;

public class CustomerFacadeTest {
	
	// CouponClientFacade instance for testing
	private static CustomerFacade facade;
	
	public static void main(String[] args) {
		
		try {
			// test login
			facade = CustomerFacade.login("9", "9");
			if (facade !=null)System.out.println("LOGIG!!!!");
			
			for (Coupon coupon :facade.getAllPurchasedCouponsByType(CouponType.RESTAURANT)){
//				System.out.println(coupon);
			}
			
			
			
			
		} catch (FacadeException e) {
			e.toString();
			e.printStackTrace();
		}	
	}
}
