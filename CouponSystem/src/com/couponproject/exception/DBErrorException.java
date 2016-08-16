package com.couponproject.exception;

public class DBErrorException extends Exception {

	public DBErrorException() {

	}

	public DBErrorException(String message) {
		super(message);

	}

	public DBErrorException(Throwable cause) {
		super(cause);

	}

	public DBErrorException(String message, Throwable cause) {
		super(message, cause);

	}

	public DBErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
