package com.couponproject.exception;

public class CustomerDoesNotExistException extends RuntimeException {

	public CustomerDoesNotExistException() {

	}

	public CustomerDoesNotExistException(String message) {
		super(message);

	}

	public CustomerDoesNotExistException(Throwable cause) {
		super(cause);

	}

	public CustomerDoesNotExistException(String message, Throwable cause) {
		super(message, cause);

	}

	public CustomerDoesNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
