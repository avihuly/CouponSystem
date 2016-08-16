package com.couponproject.exception;

import com.couponproject.dbdao.CustomerDBDAO;

public class IllegalPasswordException extends DBErrorException {

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
