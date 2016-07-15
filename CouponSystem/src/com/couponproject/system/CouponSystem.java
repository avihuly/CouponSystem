package com.couponproject.system;

import com.couponproject.facade.*;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

import com.couponproject.dbdao.ConnectionPool;
import com.couponproject.exception.CompanyFacadeException;
import com.couponproject.exception.CustomerAlreadyExistsException;
import com.couponproject.exception.FacadeException;
import com.couponproject.threads.DailyCouponExportationTask;

public class CouponSystem {
	
	// **********
	// Attributes
	// **********	
	private static CouponSystem instance = new CouponSystem();
	
	private Thread dailyExportationTask = new Thread(new DailyCouponExportationTask());
	
	// ***********
	// constructor
	// ***********
	private CouponSystem(){
		dailyExportationTask.start();
	}
	

	//***************
	//*****Methods***
	//***************
	
	// Get instance
	public static CouponSystem getInstance(){
		return instance;
	}
	
	// Shut down
	public void shutDown(){
		try {
			ConnectionPool.getInstance().shutDown();
			// TODO: dailyExportationTask.stopTask();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// ******************
	// ****Login Methods*
	// ******************
	public CustomerFacade loginAsCustomer(String name, String password) {
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
			return AdminFacade.login(name, password);
	}
}