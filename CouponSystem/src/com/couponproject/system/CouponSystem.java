package com.couponproject.system;

import com.couponproject.facade.*;

import com.couponproject.exception.CompanyFacadeException;
import com.couponproject.exception.FacadeException;
import com.couponproject.threads.DailyCouponExportationTask;

public class CouponSystem {
	
	// **********
	// Attributes
	// **********	
	private static CouponSystem instance = null;
	
	private Thread dailyExportationTask = new Thread(new DailyCouponExportationTask());
	
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
	
	// Get instance
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
	public CustomerFacade loginAsCustomer(String name, String password){
		try {
			return CustomerFacade.login(name, password);
		} catch (FacadeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
	
	public CompanyFacade loginAsCompany(String name, String password){
		try {
			return CompanyFacade.login(name, password);
		} catch (CompanyFacadeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
	
	public AdminFacade loginAsAdmin(String name, String password){
			try {
				return AdminFacade.login(name, password);
			} catch (FacadeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null; 
	}
}