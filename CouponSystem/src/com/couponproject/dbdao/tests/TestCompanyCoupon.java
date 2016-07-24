package com.couponproject.dbdao.tests;

import com.couponproject.dbdao.CompanyDBDAO;
import com.couponproject.exception.CouponSystemException;

public class TestCompanyCoupon {

	public static void main(String[] args) {
		couponCompanyTest();
	}
	
	private static void couponCompanyTest() {
		for(int i=40; i<55;i++){
			try {
				// This is the hart of the test
				// converting costumer object into a sql query and running it
				CompanyDBDAO.getInstace().addCompanyCoupon(1002, 3000+i);
			} catch (CouponSystemException e) {
				System.out.println(e.getMessage());
				}
		}	
	}
}
