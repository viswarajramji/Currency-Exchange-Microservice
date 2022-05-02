package com.ecb.exchangerate.application.exception;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class ServiceApplicationExceptionTest {

    @Test
    public void testServiceApplicationExceptionTest(){
        ServiceApplicationException serviceApplicationException=new ServiceApplicationException();
        ServiceApplicationException serviceApplicationException1=new ServiceApplicationException("APPLICATION_ERROR_OCCURED");
        ServiceApplicationException serviceApplicationException4=new ServiceApplicationException("APPLICATION_ERROR_OCCURED",new Object[]{});
        ServiceApplicationException serviceApplicationException2=new ServiceApplicationException("APPLICATION_ERROR_OCCURED", HttpStatus.BAD_REQUEST);
        ServiceApplicationException serviceApplicationException3=new ServiceApplicationException("APPLICATION_ERROR_OCCURED",new Object[]{}, HttpStatus.BAD_REQUEST);
        Assert.assertNotNull(serviceApplicationException.getMessage());
        Assert.assertNotNull(serviceApplicationException1.getMessage());
        Assert.assertEquals(serviceApplicationException2.getHttpStatus(),HttpStatus.BAD_REQUEST);
        Assert.assertEquals(serviceApplicationException3.getHttpStatus(),HttpStatus.BAD_REQUEST);
        Assert.assertNotNull(serviceApplicationException4.getHttpStatus());

    }
}
