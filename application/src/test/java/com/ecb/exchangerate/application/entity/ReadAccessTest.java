package com.ecb.exchangerate.application.entity;

import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ReadAccessTest {
    @Test
    public void testExchangeReferenceRate(){
        ReadAccess readAccess=new ReadAccess();
        readAccess.setReadBy("ADMIN");
        readAccess.setReadOn(new Date());
        readAccess.setExchangeReferenceRateId("123");
        readAccess.setExchangeReferenceRateDetailId("123");
        readAccess.setExchangeReferenceRateDetail(new ExchangeReferenceRateDetail());
        Assert.assertNotNull(readAccess.getReadBy());
        Assert.assertNotNull(readAccess.getReadOn());
        Assert.assertNotNull(readAccess.getExchangeReferenceRateId());
        Assert.assertNotNull(readAccess.getExchangeReferenceRateDetailId());
        Assert.assertNotNull(readAccess.getExchangeReferenceRateDetail());
    }
}
