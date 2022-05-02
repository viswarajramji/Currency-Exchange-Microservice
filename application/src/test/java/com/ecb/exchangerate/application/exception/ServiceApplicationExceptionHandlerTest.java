package com.ecb.exchangerate.application.exception;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public class ServiceApplicationExceptionHandlerTest {

    private ServiceApplicationException ex;
    private WebRequest request;

    @Test
    public void testExceptionHandler(){
        ex=new ServiceApplicationException();
        request=Mockito.mock(WebRequest.class);
        ServiceApplicationExceptionHandler exceptionHandler=new ServiceApplicationExceptionHandler(){
            @Override
            protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
                return new ResponseEntity(status);
            }
        };
        Assert.assertNotNull(exceptionHandler.onImportExecption(ex,request));
    }
}
