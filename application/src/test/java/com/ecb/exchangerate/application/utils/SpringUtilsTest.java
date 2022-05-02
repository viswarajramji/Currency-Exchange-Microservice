package com.ecb.exchangerate.application.utils;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;

public class SpringUtilsTest {

    private ApplicationContext appcontext;

    @Test
    public void testIsCurrencyIdValid(){
        appcontext= Mockito.mock(ApplicationContext.class);
        SpringUtils springUtils=new SpringUtils();
        springUtils.setApplicationContext(appcontext);
        Assert.assertEquals(SpringUtils.getApplicationContext(),appcontext);

    }
}
