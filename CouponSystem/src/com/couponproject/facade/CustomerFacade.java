package com.couponproject.facade;

;
import com.couponproject.dbdao.*;
import com.couponproject.exception.CouponSystemException;

public class CustomerFacade implements CouponClientFacade {
	// **********
	// Attributes
	// **********

	// Instants variables
	// CustomerDBDAO dao
	
	private String name;
	private String password;
	private ClientType clientType; 
	
	private CustomerDBDAO custDbdao;
	private CouponDBDAO coupDbdao;
	
	// Constructor
	public CustomerFacade() {
		
	}

	
	//***************
	//*****LOGIN*****
	//***************
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		try {
			if (custDbdao.login(name, password)) {
				
				custDbdao = new CustomerDBDAO();
				coupDbdao = new CouponDBDAO();
				this.name = name;
				this.password = password;
				this.clientType = clientType;
				
				return this;
			}
		} catch (CouponSystemException e) {
			// TODO
		}
	}

}
