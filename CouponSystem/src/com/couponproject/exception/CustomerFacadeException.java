package com.couponproject.exception;

public class CustomerFacadeException extends RuntimeException{

	public CustomerFacadeException() {
		super();

	}

	public CustomerFacadeException(String message, Throwable cause) {
		super(message, cause);

	}

	public CustomerFacadeException(String message) {
		super(message);

	}

	public CustomerFacadeException(Throwable cause) {
		super(cause);

	}

}
