package com.couponproject.exception;

public class OutOfDateException extends RuntimeException {

	public OutOfDateException() {

	}

	public OutOfDateException(String message) {
		super(message);

	}

	public OutOfDateException(Throwable cause) {
		super(cause);

	}

	public OutOfDateException(String message, Throwable cause) {
		super(message, cause);

	}

	public OutOfDateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
