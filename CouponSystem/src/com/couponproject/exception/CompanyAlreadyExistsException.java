package com.couponproject.exception;

public class CompanyAlreadyExistsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompanyAlreadyExistsException() {

	}

	public CompanyAlreadyExistsException(String message) {
		super(message);

	}

	public CompanyAlreadyExistsException(Throwable cause) {
		super(cause);

	}

	public CompanyAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);

	}

	public CompanyAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
