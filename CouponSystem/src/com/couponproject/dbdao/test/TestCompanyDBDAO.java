package com.couponproject.dbdao.test;

import com.couponproject.beans.Company;
import com.couponproject.dbdao.CompanyDBDAO;
import com.couponproject.exception.CouponSystemException;

public class TestCompanyDBDAO {
	
	// CustomerDBDAO instance for testing
		private static final CompanyDBDAO compDbDao = new CompanyDBDAO();
		
		
		public static void main(String[] args) {
			
			//testing create company
			Company c1 = new Company(1011, "TEVA", "TEVA", "Teva@teva.co.il" );
			try {
				compDbDao.createCompany(c1);
			} catch (CouponSystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


}
