package com.couponproject.exception;

public class CompanyDoesNotExistException extends RuntimeException{
	
	public CompanyDoesNotExistException() {

	}

	public CompanyDoesNotExistException(String message) {
		super(message);

	}

	public CompanyDoesNotExistException(Throwable cause) {
		super(cause);

	}

	public CompanyDoesNotExistException(String message, Throwable cause) {
		super(message, cause);

	}

	public CompanyDoesNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
