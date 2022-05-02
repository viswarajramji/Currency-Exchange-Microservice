package com.ecb.exchangerate.application.entity;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ExchangeReferenceRateDetailTest {
    @Test
    public void testExchangeReferenceRate(){
        ExchangeReferenceRateDetail exchangeReferenceRateDetail=new ExchangeReferenceRateDetail();
        exchangeReferenceRateDetail.setExchangeRate(10.00);
        exchangeReferenceRateDetail.setExchangeReferenceRateDetailId("123");
        exchangeReferenceRateDetail.setExchangeReferenceRate(new ExchangeReferenceRate());
        exchangeReferenceRateDetail.setExchangeReferenceRateId("123");
        exchangeReferenceRateDetail.setCurrencyId("EUR");
        exchangeReferenceRateDetail.setReadAccessDetails(new ArrayList<>());

        Assert.assertNotNull(exchangeReferenceRateDetail.getExchangeRate());
        Assert.assertNotNull(exchangeReferenceRateDetail.getExchangeReferenceRateDetailId());
        Assert.assertNotNull(exchangeReferenceRateDetail.getExchangeReferenceRate());
        Assert.assertNotNull(exchangeReferenceRateDetail.getExchangeReferenceRateId());
        Assert.assertNotNull(exchangeReferenceRateDetail.getCurrencyId());
        Assert.assertNotNull(exchangeReferenceRateDetail.getReadAccessDetails());
    }
}
