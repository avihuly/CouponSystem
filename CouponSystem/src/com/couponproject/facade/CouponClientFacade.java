package com.couponproject.facade;

import com.couponproject.exception.FacadeException;

public interface CouponClientFacade {
	public CouponClientFacade login(String name, String password, ClientType clientType) throws FacadeException; 
}
