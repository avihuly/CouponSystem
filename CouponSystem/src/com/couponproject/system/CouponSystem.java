package com.couponproject.system;

import com.couponproject.facade.*;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

import com.couponproject.dbdao.ConnectionPool;
import com.couponproject.exception.CompanyFacadeException;
import com.couponproject.exception.CustomerAlreadyExistsException;
import com.couponproject.exception.FacadeException;
import com.couponproject.threads.DailyCouponExpirationTask;

/**
 * This class is the entrance point of the different clients to the coupon system.
 * <p> The CouponSystem class is a Singleton. Upon loading of the Singleton the DailyCouponExpirationTask is deleted.</p>
 * @author Avi Huli and Orit Blum
 * @version 1.0
 */
public class CouponSystem {
	
	// **********
	// Attributes
	// **********
	/**
	 * Holds the CouponSystem single instance
	 */
	private static CouponSystem instance = new CouponSystem();
	
	/**
	 * Holds the dailyExportationTask thread
	 */
	private DailyCouponExpirationTask dailyExportationTask = new DailyCouponExpirationTask();
	
	// ***********
	// constructor
	// ***********
	/**
	 * Construct the CouponSystem singleton. Upon construction a dailyExportationTask thread is being started.
	 */
	private CouponSystem(){
		dailyExportationTask.start();
	}
	

	//***************
	//*****Methods***
	//***************
	
	/**
	 * Returns the CouponSystem single instance
	 * @return CouponSystem instance
	 */
	public static CouponSystem getInstance(){
		return instance;
	}
	
	/**
	 * Shut Down of the Coupon System. 
	 * <p>The dailyExpirationTask is being stopped.</p>
	 * <p>The connection to the data base is being closed.</p>
	 */
	public void shutDown(){
		try {
			dailyExportationTask.stopTask();
			ConnectionPool.getInstance().shutDown();
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
	/**
	 * Returns CustomerFacade following successful login of Customer type client or null for unsuccessful login. 
	 * @param name Customer's User Name
	 * @param password Customer's Password
	 * @return CustomerFscade instance
	 */
	public CustomerFacade loginAsCustomer(String name, String password) {
		try {
			return CustomerFacade.login(name, password);
		} catch (FacadeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
	
	/**
	 * Returns CompanyFacade following successful login of Company type client or null for unsuccessful login.
	 * @param name Company's User Name
	 * @param password Company's Password
	 * @return CompanyFacade instance
	 */
	public CompanyFacade loginAsCompany(String name, String password){
		try {
			return CompanyFacade.login(name, password);
		} catch (CompanyFacadeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
	
	/**
	 * Returns AdminFacade following successful login of Admin type client or null for unsuccessful login.
	 * @param name Admin's User Name
	 * @param password Admin's Password
	 * @return AdminFacade instance
	 */
	public AdminFacade loginAsAdmin(String name, String password){
			return AdminFacade.login(name, password);
	}
}