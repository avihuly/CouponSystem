package com.couponproject.exception;

public class CompanyCouponDoesNotExistsException extends Exception {

	public CompanyCouponDoesNotExistsException() {

	}

	public CompanyCouponDoesNotExistsException(String message) {
		super(message);

	}

	public CompanyCouponDoesNotExistsException(Throwable cause) {
		super(cause);

	}

	public CompanyCouponDoesNotExistsException(String message, Throwable cause) {
		super(message, cause);

	}

	public CompanyCouponDoesNotExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
