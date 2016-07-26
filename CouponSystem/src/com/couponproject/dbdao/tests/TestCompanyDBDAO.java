package com.couponproject.dbdao.tests;

import com.couponproject.beans.Company;
import com.couponproject.dbdao.CompanyDBDAO;
import com.couponproject.exception.CompanyAlreadyExistsException;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.EmailAlreadyExistsException;
import com.couponproject.exception.IllegalPasswordException;

public class TestCompanyDBDAO {	
		public static void main(String[] args) {
			// Add new Company's to DB
			for (int i = 0; i < 25; i++) {
				// Company instance
				Company company = new Company(
						"company " + (i + 7000), 
						"password " + (i + 7000),
						"Email@"+(i + 7000)+".com");
				try {
					// This is the hart of the test
					// converting company object into a sql query and running it
					CompanyDBDAO.getInstace().createCompany(company);
				
				} catch (CouponSystemException | IllegalPasswordException | CompanyAlreadyExistsException e) {
					System.out.println("createCustomerTest Error");
					e.printStackTrace();
				} catch (EmailAlreadyExistsException e) {
					System.out.println("createCustomerTest Error");
					e.printStackTrace();
				}
			}
		}


}
