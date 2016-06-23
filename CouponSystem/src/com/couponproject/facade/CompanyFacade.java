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
	public CompanyFacade(){};
	
	//
	//Methods
	//
	
	public void createCoupon(Coupon coupon){
		//TODO: check if the coupon doesn't exist
		//adding the coupon to the coupon table in the DB
		try {
			coupDbDao.createCoupon(coupon);
		} catch (CouponSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		// u
		long coupId = coupon.getId();
	}
	
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		//cheking if the login is of a company
		try {
		if(clientType == ClientType.Company){
			
				if(compDbDao.login(name, password))
				{
					return new CompanyFacade();
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
