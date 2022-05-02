package com.ecb.exchangerate.application.data;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class ExchangeRateValueModelTest {

    private Date effectiveDate =new Date();

    private String sourceCurrency="EUR";

    private String targetCurrency="USD";

    private Double sourceValue = 2.0;

    @Test
    public void testExchangeRateValueModel(){
        ExchangeRateValueModel exchangeRateValueModel=new ExchangeRateValueModel(sourceValue);
        exchangeRateValueModel.setSourceCurrency(sourceCurrency);
        exchangeRateValueModel.setTargetCurrency(targetCurrency);
        exchangeRateValueModel.setEffectiveDate(effectiveDate);
        Assert.assertEquals(exchangeRateValueModel.getEffectiveDate(),effectiveDate);
        Assert.assertEquals(exchangeRateValueModel.getSourceCurrency(),sourceCurrency);
        Assert.assertEquals(exchangeRateValueModel.getTargetCurrency(),targetCurrency);
        Assert.assertEquals(exchangeRateValueModel.getSourceValue(),sourceValue);

    }

    @Test
    public void testExchangeRateValueModelTest(){
        ExchangeRateValueModel exchangeRateValueModel=new ExchangeRateValueModel();
        exchangeRateValueModel.setSourceCurrency(sourceCurrency);
        exchangeRateValueModel.setTargetCurrency(targetCurrency);
        exchangeRateValueModel.setEffectiveDate(effectiveDate);
        exchangeRateValueModel.setSourceValue(sourceValue);
        Assert.assertEquals(exchangeRateValueModel.getEffectiveDate(),effectiveDate);
        Assert.assertEquals(exchangeRateValueModel.getSourceCurrency(),sourceCurrency);
        Assert.assertEquals(exchangeRateValueModel.getTargetCurrency(),targetCurrency);
        Assert.assertEquals(exchangeRateValueModel.getSourceValue(),sourceValue);

    }
}
