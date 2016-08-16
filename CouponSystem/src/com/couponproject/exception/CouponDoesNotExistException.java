package com.couponproject.exception;

public class CouponDoesNotExistException extends DBErrorException {

	public CouponDoesNotExistException() {

	}

	public CouponDoesNotExistException(String message) {
		super(message);

	}

	public CouponDoesNotExistException(Throwable cause) {
		super(cause);

	}

	public CouponDoesNotExistException(String message, Throwable cause) {
		super(message, cause);

	}

	public CouponDoesNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
