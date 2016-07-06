package com.couponproject.system;

import com.couponproject.dbdao.*;
import com.couponproject.exception.CompanyFacadeException;
import com.couponproject.exception.FacadeException;
import com.couponproject.facade.*;
import com.couponproject.threads.DailyCouponExportationTask;

public class CouponSystem {
	
	// **********
	// Attributes
	// **********	
	private static CouponSystem instance = null;
	
	private static CouponDBDAO custDbdao;
	private static CompanyDBDAO compDbdao;
	private static CustomerDBDAO coupDbdao;
	
	private static Thread dailyExportationTask = new Thread(new DailyCouponExportationTask());
	
	// ***********
	// constructor
	// ***********
	private CouponSystem(){
		// TODO: why i need to load daos here??!?! i load them in the facades
		// custDbdao = new CouponDBDAO();
		// compDbdao = new CompanyDBDAO();
		// coupDbdao = new CustomerDBDAO();

		dailyExportationTask.start();
	}
	

	//***************
	//*****Methods***
	//***************
	public static CouponSystem getInstance(){
		if (instance == null){
			instance = new CouponSystem();
		}
		return instance;
	}
	
	public void shutDown(){
		
	}
	
	
	// ******************
	// ****Login Methods*
	// ******************
	public CouponClientFacade loginAsCustomer(String name, String password){
		try {
			return CustomerFacade.login(name, password);
		} catch (FacadeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
	
	public CouponClientFacade loginAsCompany(String name, String password){
		try {
			return CompanyFacade.login(name, password);
		} catch (CompanyFacadeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
	
	public CouponClientFacade loginAsAdmin(String name, String password){
			try {
				return AdminFacade.login(name, password);
			} catch (FacadeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null; 
	}
}