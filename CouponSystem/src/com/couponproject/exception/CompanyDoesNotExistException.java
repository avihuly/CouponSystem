package com.couponproject.exception;

public class CompanyDoesNotExistException extends DBErrorException{
	
	public CompanyDoesNotExistException() {
		// TODO Auto-generated constructor stub
	}

	public CompanyDoesNotExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CompanyDoesNotExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CompanyDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CompanyDoesNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
