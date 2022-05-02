package com.ecb.exchangerate.application.data;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class ExchangeRateValueResponseModelTest {

    private Date effectiveDate =new Date();


    private String sourceCurrency="EUR";

    private String targetCurrency="USD";

    private Double sourceValue = 2.0;

    private Double targetValue = 2.5;

    @Test
    public void testExchangeRateValueResponseModel(){
        ExchangeRateValueResponseModel exchangeRateValueModel=new ExchangeRateValueResponseModel(sourceValue,targetValue);
        exchangeRateValueModel.setSourceCurrency(sourceCurrency);
        exchangeRateValueModel.setTargetCurrency(targetCurrency);
        exchangeRateValueModel.setEffectiveDate(effectiveDate);
        Assert.assertEquals(exchangeRateValueModel.getEffectiveDate(),effectiveDate);
        Assert.assertEquals(exchangeRateValueModel.getSourceCurrency(),sourceCurrency);
        Assert.assertEquals(exchangeRateValueModel.getTargetCurrency(),targetCurrency);
        Assert.assertEquals(exchangeRateValueModel.getSourceValue(),sourceValue);
        Assert.assertEquals(exchangeRateValueModel.getTargetValue(),targetValue);

    }

    @Test
    public void testExchangeRateValueResponseModelGetter(){
        ExchangeRateValueResponseModel exchangeRateValueModel=new ExchangeRateValueResponseModel();
        exchangeRateValueModel.setSourceCurrency(sourceCurrency);
        exchangeRateValueModel.setTargetCurrency(targetCurrency);
        exchangeRateValueModel.setEffectiveDate(effectiveDate);
        exchangeRateValueModel.setSourceValue(sourceValue);
        exchangeRateValueModel.setTargetValue(targetValue);
        Assert.assertEquals(exchangeRateValueModel.getEffectiveDate(),effectiveDate);
        Assert.assertEquals(exchangeRateValueModel.getSourceCurrency(),sourceCurrency);
        Assert.assertEquals(exchangeRateValueModel.getTargetCurrency(),targetCurrency);
        Assert.assertEquals(exchangeRateValueModel.getSourceValue(),sourceValue);
        Assert.assertEquals(exchangeRateValueModel.getTargetValue(),targetValue);

    }
}
