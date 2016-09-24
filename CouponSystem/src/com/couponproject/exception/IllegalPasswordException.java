package com.couponproject.exception;


public class IllegalPasswordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalPasswordException() {
		super();

	}

	public IllegalPasswordException(String message, Throwable cause) {
		super(message, cause);

	}

	public IllegalPasswordException(String message) {
		super(message);

	}

	public IllegalPasswordException(Throwable cause) {
		super(cause);

	}



}
