package com.couponproject.util;

import com.couponproject.beans.Company;
import com.couponproject.beans.Customer;

public class UtilTest {
	public static void main(String[] args) {
		// test isCustomer?
		System.out.println(Util.isCustomer(new Customer(1 ,"customer 99", "password 99")));
		
		// test isCompany?
		System.out.println(Util.isCompany(new Company(7026, "company 7000", "password 7000", "Email@7000.com")));
		
	}
}
