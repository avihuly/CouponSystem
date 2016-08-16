package com.couponproject.system;

import com.couponproject.facade.*;

import java.beans.PropertyVetoException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import com.couponproject.dbdao.*;
import com.couponproject.exception.CompanyFacadeException;
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
	private static CouponSystem instance;
	/**
	 * Holds the single CompanyDBDAO instance  
	 */
	private CompanyDBDAO companyDbdao = new CompanyDBDAO();
	/**
	 * Holds the single CustomerDBDAO instance  
	 */
	private CustomerDBDAO customerDbdao = new CustomerDBDAO();
	/**
	 * Holds the single CouponDBDAO instance  
	 */
	private CouponDBDAO couponDbdao = new CouponDBDAO();
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
		if (instance == null){
			instance = new CouponSystem();
		}
		return instance;
	}
	
	/**
	 * Returns the single CompanyDBDAO instance
	 * @return CompanyDBDAO instance
	 */
	public CompanyDBDAO getCompanyDBDAO(){
		return companyDbdao;
	}
	
	/**
	 * Returns the single CustomerDBDAO instance
	 * @return CustomerDBDAO instance
	 */
	public CustomerDBDAO getCustomerDBDAO(){
		return customerDbdao;
	}
	
	/**
	 * Returns the single CouponDBDAO instance
	 * @return CouponDBDAO instance
	 */
	public CouponDBDAO getCouponDBDAO(){
		return couponDbdao;
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
		} catch (IOException | SQLException | PropertyVetoException e) {
			try (FileWriter fstream = new FileWriter("/logs/DailyCouponExpirationTaskLOG.txt");){
				BufferedWriter out = new BufferedWriter(fstream);
				out.write(e.toString());
				out.close();
			} catch (IOException e1) {}
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
			return null; 	
		}
	}
	
	/**
	 * Returns CompanyFacade following successful login of Company type client or null for unsuccessful login.
	 * @param name Company's User Name
	 * @param password Company's Password
	 * @return CompanyFacade instance or null
	 */
	public CompanyFacade loginAsCompany(String name, String password){
		try {
			return CompanyFacade.login(name, password);
		} catch (CompanyFacadeException e) {
			return null;
		} 
	}
	
	/**
	 * Returns AdminFacade following successful login of Admin type client or null for unsuccessful login.
	 * @param name Admin's User Name
	 * @param password Admin's Password
	 * @return AdminFacade instance or null
	 */
	public AdminFacade loginAsAdmin(String name, String password){
			return AdminFacade.login(name, password);
	}
}