package com.couponproject.facade;

import com.couponproject.beans.Company;
import com.couponproject.beans.Coupon;
import com.couponproject.dbdao.CompanyDBDAO;
import com.couponproject.dbdao.CouponDBDAO;
import com.couponproject.exception.CouponSystemException;

public class CompanyFacade implements CouponClientFacade{

	//
	//Attributes
	//
	
	// Instants variables TODO: dont know if they should be of the instance
	private Company company = new Company();
	private CompanyDBDAO compDbDao = new CompanyDBDAO();
	private CouponDBDAO coupDbDao = new CouponDBDAO();
	
	// Constructors
	public CompanyFacade(String name, String password){
		try {
			this.company = compDbDao.getCompany(name, password);
		} catch (CouponSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//
	//Methods
	//
	
	//A method that gets coupon instance and add the coupon to the coupon table in the DB and adds coupon' and company's
	//ID to company_coupon table in the DB
	//TODO: should we add throw to the functions?
	public void createCoupon(Coupon coupon){
		//TODO: check if the coupon doesn't exist - to add a function in CouponDBDAO that checks the title resultSet
		//adding the coupon to the coupon table in the DB
		try {
			coupDbDao.createCoupon(coupon);
		} catch (CouponSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		// updating company_coupon table in the DB
		long couponId = coupon.getId();
		long compId = company.getId();
		try {
			compDbDao.addCompanyCoupon(compId, couponId);
		} catch (CouponSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		//cheking if the login is of a company
		try {
		if(clientType == ClientType.Company){
			
				if(compDbDao.login(name, password))
				{
					return new CompanyFacade(name, password);
				}
				else return null;
		}
		else return null;
		} catch (CouponSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	
	
	
	

}
