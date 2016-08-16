package com.couponproject.exception;

public class CustomerAlreadyExistsException extends Exception {

	public CustomerAlreadyExistsException() {

	}

	public CustomerAlreadyExistsException(String message) {
		super(message);

	}

	public CustomerAlreadyExistsException(Throwable cause) {
		super(cause);

	}

	public CustomerAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);

	}

	public CustomerAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
