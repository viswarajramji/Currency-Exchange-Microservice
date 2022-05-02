package com.ecb.exchangerate.application.exception;

import org.springframework.http.HttpStatus;

import com.ecb.exchangerate.application.configuration.MessageReader;

public class ServiceApplicationException extends RuntimeException {

	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	private static final long serialVersionUID = 1L;

	public ServiceApplicationException() {
		super(MessageReader.getLocaleMessage("APPLICATION_ERROR_OCCURED"));
	}

	public ServiceApplicationException(String errorMsg) {
		super(MessageReader.getLocaleMessage(errorMsg));
	}

	public ServiceApplicationException(String errorMsg, Object[] args) {
		super(MessageReader.getLocaleMessage(errorMsg, args));
	}

	public ServiceApplicationException(String errorMsg, HttpStatus httpStatus) {
		super(MessageReader.getLocaleMessage(errorMsg));
		this.httpStatus = httpStatus;
	}

	public ServiceApplicationException(String errorMsg, Object[] args, HttpStatus httpStatus) {
		super(MessageReader.getLocaleMessage(errorMsg, args));
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
