package com.ecb.exchangerate.application.utils;


import com.ecb.exchangerate.application.configuration.ProjectConstants;
import com.ecb.exchangerate.application.exception.ServiceApplicationException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


public class DataUtilsTest {

    @Test
    public void testIsCurrencyIdValid(){
        Assert.assertEquals(DataUtils.isCurrencyIdValid(null),false);
        Assert.assertEquals(DataUtils.isCurrencyIdValid("EUR"),true);
    }

    @Test
    public void testIsEmptyOrNull(){
        Assert.assertEquals(DataUtils.isEmptyOrNull(null),false);
        Assert.assertEquals(DataUtils.isEmptyOrNull(""),false);
        Assert.assertEquals(DataUtils.isEmptyOrNull("EUR"),true);
    }

    @Test
    public void testHandleInfinityOrNan(){
        Assert.assertEquals(DataUtils.handleInfinityOrNan(null), ProjectConstants.DEFAULT_VALUE);
        Assert.assertEquals(DataUtils.handleInfinityOrNan(10.0),Double.valueOf(10.0));
    }


    @Test
    public void testConvertToDate(){
        Assert.assertNotNull(DataUtils.convertStringToDate("2022-01-01"));
        Assert.assertNotNull(DataUtils.convertStringToDate("01 April 2022"));
        try{
            Assert.assertNotNull(DataUtils.convertStringToDate("123 123 April"));
            Assert.assertTrue(false);
        }catch (ServiceApplicationException ex){
            Assert.assertNotNull(ex);
        }

    }

    @Test
    public void testTrimValue(){
        Assert.assertEquals(DataUtils.trimValue("EUR "),"EUR");
        Assert.assertEquals(DataUtils.trimValue(null),null);

    }
}
