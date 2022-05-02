package com.ecb.exchangerate.application.data;

import com.ecb.exchangerate.application.entity.ExchangeReferenceRateDetail;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class ConversionRateModelTest {


    private Double conversionRate = 1.00;

    private Date effectiveDate=new Date();

    private ExchangeReferenceRateDetail sourceCurrency=new ExchangeReferenceRateDetail();

    private ExchangeReferenceRateDetail targetCurrency=new ExchangeReferenceRateDetail();
    @Test
    public void testConversionRateModel(){
        ConversionRateModel conversionRateModel=new ConversionRateModel(1.0,effectiveDate,sourceCurrency,targetCurrency);
        Assert.assertEquals(conversionRateModel.getConversionRate(),conversionRate);
        Assert.assertEquals(conversionRateModel.getEffectiveDate(),effectiveDate);
        Assert.assertEquals(conversionRateModel.getSourceCurrency(),sourceCurrency);
        Assert.assertEquals(conversionRateModel.getTargetCurrency(),targetCurrency);
    }

    @Test
    public void testConversionRateModelSetter(){
        ConversionRateModel conversionRateModel=new ConversionRateModel();
        conversionRateModel.setConversionRate(conversionRate);
        conversionRateModel.setEffectiveDate(effectiveDate);
        conversionRateModel.setSourceCurrency(sourceCurrency);
        conversionRateModel.setTargetCurrency(targetCurrency);
        Assert.assertEquals(conversionRateModel.getConversionRate(),conversionRate);
        Assert.assertEquals(conversionRateModel.getEffectiveDate(),effectiveDate);
        Assert.assertEquals(conversionRateModel.getSourceCurrency(),sourceCurrency);
        Assert.assertEquals(conversionRateModel.getTargetCurrency(),targetCurrency);
    }
}
