package com.couponproject.exception;

import com.couponproject.dbdao.CustomerDBDAO;

public class IllegalPasswordException extends DBErrorException {

	public IllegalPasswordException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IllegalPasswordException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IllegalPasswordException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public IllegalPasswordException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}



}
