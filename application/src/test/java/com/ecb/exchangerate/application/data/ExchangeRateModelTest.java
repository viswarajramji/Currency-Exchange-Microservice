package com.ecb.exchangerate.application.data;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class ExchangeRateModelTest {

    private Date effectiveDate =new Date();

    private String sourceCurrency="EUR";

    private String targetCurrency="USD";

    @Test
    public void testExchangeRateModelTest(){
        ExchangeRateModel exchangeRateModel=new ExchangeRateModel(effectiveDate);
        exchangeRateModel.setSourceCurrency(sourceCurrency);
        exchangeRateModel.setTargetCurrency(targetCurrency);
        Assert.assertEquals(exchangeRateModel.getEffectiveDate(),effectiveDate);
        Assert.assertEquals(exchangeRateModel.getSourceCurrency(),sourceCurrency);
        Assert.assertEquals(exchangeRateModel.getTargetCurrency(),targetCurrency);

    }

    @Test
    public void testExchangeRateModelGetter(){
        ExchangeRateModel exchangeRateModel=new ExchangeRateModel();
        exchangeRateModel.setSourceCurrency(sourceCurrency);
        exchangeRateModel.setTargetCurrency(targetCurrency);
        exchangeRateModel.setEffectiveDate(effectiveDate);
        Assert.assertEquals(exchangeRateModel.getEffectiveDate(),effectiveDate);
        Assert.assertEquals(exchangeRateModel.getSourceCurrency(),sourceCurrency);
        Assert.assertEquals(exchangeRateModel.getTargetCurrency(),targetCurrency);

    }



}
