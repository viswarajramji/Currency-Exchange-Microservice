package com.ecb.exchangerate.application.data;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class ExchangeRateResponseModelTest {

    private Date effectiveDate =new Date();

    private Double conversionRate=1.0;

    private String sourceCurrency="EUR";

    private String targetCurrency="USD";

    @Test
    public void testExchangeRateResponseModel(){
        ExchangeRateResponseModel exchangeRateModel=new ExchangeRateResponseModel(effectiveDate,conversionRate);
        exchangeRateModel.setSourceCurrency(sourceCurrency);
        exchangeRateModel.setTargetCurrency(targetCurrency);
        Assert.assertEquals(exchangeRateModel.getEffectiveDate(),effectiveDate);
        Assert.assertEquals(exchangeRateModel.getSourceCurrency(),sourceCurrency);
        Assert.assertEquals(exchangeRateModel.getTargetCurrency(),targetCurrency);
        Assert.assertEquals(exchangeRateModel.getConversionRate(),conversionRate);

    }

    @Test
    public void testExchangeRateModelGetter(){
        ExchangeRateResponseModel exchangeRateModel=new ExchangeRateResponseModel();
        exchangeRateModel.setSourceCurrency(sourceCurrency);
        exchangeRateModel.setTargetCurrency(targetCurrency);
        exchangeRateModel.setEffectiveDate(effectiveDate);
        exchangeRateModel.setConversionRate(conversionRate);
        Assert.assertEquals(exchangeRateModel.getEffectiveDate(),effectiveDate);
        Assert.assertEquals(exchangeRateModel.getSourceCurrency(),sourceCurrency);
        Assert.assertEquals(exchangeRateModel.getTargetCurrency(),targetCurrency);
        Assert.assertEquals(exchangeRateModel.getConversionRate(),conversionRate);

    }
}
