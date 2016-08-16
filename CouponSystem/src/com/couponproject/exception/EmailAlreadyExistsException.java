package com.couponproject.exception;

public class EmailAlreadyExistsException extends DBErrorException {

	public EmailAlreadyExistsException() {

	}

	public EmailAlreadyExistsException(String message) {
		super(message);

	}

	public EmailAlreadyExistsException(Throwable cause) {
		super(cause);

	}

	public EmailAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);

	}

	public EmailAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
