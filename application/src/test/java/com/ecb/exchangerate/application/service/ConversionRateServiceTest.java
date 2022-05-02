package com.ecb.exchangerate.application.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.ecb.exchangerate.application.configuration.ProjectConstants;
import com.ecb.exchangerate.application.data.ConversionRateModel;
import com.ecb.exchangerate.application.data.ExchangeRateModel;
import com.ecb.exchangerate.application.entity.ExchangeReferenceRateDetail;
import com.ecb.exchangerate.application.exception.ServiceApplicationException;
import com.ecb.exchangerate.application.processor.ReadDataProcessor;


public class ConversionRateServiceTest {

    private ReadDataProcessor readDataProcessor;

    private ConversionRateService conversionRateService;

    @Test
    public void testProcessExchangeRateModel(){
        ExchangeRateModel exchangeRateModel =new ExchangeRateModel(null);
        exchangeRateModel.setSourceCurrency("EUR");
        exchangeRateModel.setTargetCurrency("USD");

        readDataProcessor= Mockito.mock(ReadDataProcessor.class);
        conversionRateService=new ConversionRateService(readDataProcessor);

        Mockito.doReturn(new HashMap<>()).when(readDataProcessor).getExchangeReferenceRateDetails(any());

        try{
            conversionRateService.processExchangeRateModel(exchangeRateModel);
            Assert.assertEquals(false,true);
        }catch (ServiceApplicationException ex){
            Assert.assertNotNull(ex.getMessage());
        }
    }


    @Test
    public void testProcessExchangeRateModelTargetCurrency(){
        ExchangeRateModel exchangeRateModel =new ExchangeRateModel(null);
        exchangeRateModel.setSourceCurrency("EUR");
        exchangeRateModel.setTargetCurrency("USD");

        readDataProcessor= Mockito.mock(ReadDataProcessor.class);
        conversionRateService=new ConversionRateService(readDataProcessor);
        Map<String, ExchangeReferenceRateDetail> currencyIdVsExchangeReferenceRateDetail=new HashMap<>();
        currencyIdVsExchangeReferenceRateDetail.put("EUR",new ExchangeReferenceRateDetail());
        Mockito.doReturn(currencyIdVsExchangeReferenceRateDetail).when(readDataProcessor).getExchangeReferenceRateDetails(any());
        try{
            conversionRateService.processExchangeRateModel(exchangeRateModel);
            Assert.assertEquals(false,true);
        }catch (ServiceApplicationException ex){
            Assert.assertNotNull(ex.getMessage());
        }
    }


    @Test
    public void testProcessExchangeRateModelValues(){
        ExchangeRateModel exchangeRateModel =new ExchangeRateModel(null);
        exchangeRateModel.setSourceCurrency("EUR");
        exchangeRateModel.setTargetCurrency("USD");

        ExchangeReferenceRateDetail exchangeReferenceRateDetail1=new ExchangeReferenceRateDetail();
        exchangeReferenceRateDetail1.setExchangeReferenceRateId("123");
        exchangeReferenceRateDetail1.setExchangeRate(10.0);
        exchangeReferenceRateDetail1.setCurrencyId("EUR");

        ExchangeReferenceRateDetail exchangeReferenceRateDetail2=new ExchangeReferenceRateDetail();
        exchangeReferenceRateDetail2.setExchangeReferenceRateId("456");
        exchangeReferenceRateDetail2.setExchangeRate(10.0);
        exchangeReferenceRateDetail2.setCurrencyId("USD");


        readDataProcessor= Mockito.mock(ReadDataProcessor.class);
        conversionRateService=new ConversionRateService(readDataProcessor);
        Map<String, ExchangeReferenceRateDetail> currencyIdVsExchangeReferenceRateDetail=new HashMap<>();
        currencyIdVsExchangeReferenceRateDetail.put("EUR",exchangeReferenceRateDetail1);
        currencyIdVsExchangeReferenceRateDetail.put("USD",exchangeReferenceRateDetail2);
        Mockito.doReturn(currencyIdVsExchangeReferenceRateDetail).when(readDataProcessor).getExchangeReferenceRateDetails(any());

        ConversionRateModel conversionRateModel= conversionRateService.processExchangeRateModel(exchangeRateModel);
        Double result=exchangeReferenceRateDetail2.getExchangeRate()/exchangeReferenceRateDetail1.getExchangeRate();
        Assert.assertEquals(result,conversionRateModel.getConversionRate());


    }



    @Test
    public void testProcessExchangeRateModelValuesInfinity(){
        ExchangeRateModel exchangeRateModel =new ExchangeRateModel(null);
        exchangeRateModel.setSourceCurrency("EUR");
        exchangeRateModel.setTargetCurrency("USD");

        ExchangeReferenceRateDetail exchangeReferenceRateDetail1=new ExchangeReferenceRateDetail();
        exchangeReferenceRateDetail1.setExchangeReferenceRateId("123");
        exchangeReferenceRateDetail1.setExchangeRate(0.0);
        exchangeReferenceRateDetail1.setCurrencyId("EUR");

        ExchangeReferenceRateDetail exchangeReferenceRateDetail2=new ExchangeReferenceRateDetail();
        exchangeReferenceRateDetail2.setExchangeReferenceRateId("456");
        exchangeReferenceRateDetail2.setExchangeRate(1.0);
        exchangeReferenceRateDetail2.setCurrencyId("USD");


        readDataProcessor= Mockito.mock(ReadDataProcessor.class);
        conversionRateService=new ConversionRateService(readDataProcessor);
        Map<String, ExchangeReferenceRateDetail> currencyIdVsExchangeReferenceRateDetail=new HashMap<>();
        currencyIdVsExchangeReferenceRateDetail.put("EUR",exchangeReferenceRateDetail1);
        currencyIdVsExchangeReferenceRateDetail.put("USD",exchangeReferenceRateDetail2);
        Mockito.doReturn(currencyIdVsExchangeReferenceRateDetail).when(readDataProcessor).getExchangeReferenceRateDetails(any());

        ConversionRateModel conversionRateModel= conversionRateService.processExchangeRateModel(exchangeRateModel);
        Double result=exchangeReferenceRateDetail2.getExchangeRate()/exchangeReferenceRateDetail1.getExchangeRate();
        Assert.assertEquals(ProjectConstants.DEFAULT_VALUE,conversionRateModel.getConversionRate());


    }
}
