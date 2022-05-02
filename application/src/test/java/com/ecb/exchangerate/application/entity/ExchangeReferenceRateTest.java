package com.ecb.exchangerate.application.entity;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ExchangeReferenceRateTest {
    @Test
    public void testExchangeReferenceRate(){
        ExchangeReferenceRate exchangeReferenceRate=new ExchangeReferenceRate();
        exchangeReferenceRate.setBaseCurrencyId("EUR");
        exchangeReferenceRate.setEffectiveDate(new Date());
        exchangeReferenceRate.setExchangeReferenceRateId("123");
        exchangeReferenceRate.setExchangeReferenceRateDetails(new ArrayList<>());

        Assert.assertNotNull(exchangeReferenceRate.getExchangeReferenceRateDetails());
        Assert.assertNotNull(exchangeReferenceRate.getExchangeReferenceRateId());
        Assert.assertNotNull(exchangeReferenceRate.getEffectiveDate());
        Assert.assertNotNull(exchangeReferenceRate.getBaseCurrencyId());
    }
}
