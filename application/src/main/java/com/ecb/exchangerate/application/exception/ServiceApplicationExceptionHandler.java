package com.ecb.exchangerate.application.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ServiceApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ServiceApplicationException.class)
	public ResponseEntity<Object> onImportExecption(ServiceApplicationException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), ex.getHttpStatus(), request);
	}
}
