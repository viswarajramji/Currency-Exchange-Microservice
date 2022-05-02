package com.ecb.exchangerate.application.service;

import com.ecb.exchangerate.application.data.BaseModel;
import com.ecb.exchangerate.application.data.ConversionRateModel;
import com.ecb.exchangerate.application.data.ExchangeRateModel;
import com.ecb.exchangerate.application.data.ExchangeRateValueModel;
import com.ecb.exchangerate.application.data.ExternalLinkModel;
import com.ecb.exchangerate.application.entity.ExchangeReferenceRateDetail;
import com.ecb.exchangerate.application.enums.ChartView;
import com.ecb.exchangerate.application.exception.ServiceApplicationException;
import com.ecb.exchangerate.application.processor.ReadDataProcessor;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;

public class ReadDataServiceTest {
    private ConversionRateService conversionRateService;

    private ReadAccessLogService readAccessLogService;

    private ReadDataProcessor readDataProcessor;

    public void intialize(){
        conversionRateService= Mockito.mock(ConversionRateService.class);
        readAccessLogService= Mockito.mock(ReadAccessLogService.class);
        readDataProcessor= Mockito.mock(ReadDataProcessor.class);
    }

    @Test
    public void testPerformPreCheck1(){
        intialize();
        ReadDataService readDataService=new ReadDataService(conversionRateService,readAccessLogService,readDataProcessor);
        readDataService.performPreCheck(new BaseModel("EUR","USD"));
    }
    @Test
    public void testPerformPreCheck2(){
        intialize();
        ReadDataService readDataService=new ReadDataService(conversionRateService,readAccessLogService,readDataProcessor);
        try{
            readDataService.performPreCheck(new BaseModel("EUR1","USD"));
            Assert.assertEquals(false,true);
        }catch (ServiceApplicationException ex){
            Assert.assertNotNull(ex);
        }

    }
    @Test
    public void testPerformPreCheck3(){
        intialize();
        ReadDataService readDataService=new ReadDataService(conversionRateService,readAccessLogService,readDataProcessor);
        try{
            readDataService.performPreCheck(new BaseModel("EUR","USD1"));
            Assert.assertEquals(false,true);
        }catch (ServiceApplicationException ex){
            Assert.assertNotNull(ex);
        }
    }


    @Test
    public void testPublicLink1(){
        intialize();
        ReadDataService readDataService=new ReadDataService(conversionRateService,readAccessLogService,readDataProcessor);
        ExternalLinkModel externalLinkModel=new ExternalLinkModel(ChartView.FIVE_YEAR);
        externalLinkModel.setSourceCurrency("EUR");
        externalLinkModel.setTargetCurrency("USD");
        Assert.assertNotNull(readDataService.getPublicURLLink(externalLinkModel));
    }

    @Test
    public void testPublicLink2(){
        intialize();
        ReadDataService readDataService=new ReadDataService(conversionRateService,readAccessLogService,readDataProcessor);
        ExternalLinkModel externalLinkModel=new ExternalLinkModel();
        try{
            Assert.assertNotNull(readDataService.getPublicURLLink(externalLinkModel));
            Assert.assertEquals(false,true);
        }catch (ServiceApplicationException ex) {
            Assert.assertNotNull(ex);
        }
    }


    @Test
    public void testGetReadAccessDetails(){
        intialize();
        Date date=new Date();
        ReadDataService readDataService=new ReadDataService(conversionRateService,readAccessLogService,readDataProcessor);
        Mockito.doReturn(new ArrayList<>()).when(readAccessLogService).getReadAccessDetails(date);
        Assert.assertEquals(readDataService.getReadAccessDetails(date).size(),0);
    }

    @Test
    public void testGetAllSupportedCountriesByEffectiveDate(){
        intialize();
        Date date=new Date();
        ReadDataService readDataService=new ReadDataService(conversionRateService,readAccessLogService,readDataProcessor);
        Mockito.doReturn(new ArrayList<>()).when(readDataProcessor).getAllSupportedCurrencies(date);
        Assert.assertEquals(readDataService.getAllSupportedCountriesByEffectiveDate(date).size(),0);
    }

    @Test
    public void testGetExchangeReferenceRate(){
        intialize();
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


        ConversionRateModel conversionRateModel =new ConversionRateModel();
        conversionRateModel.setSourceCurrency(exchangeReferenceRateDetail1);
        conversionRateModel.setTargetCurrency(exchangeReferenceRateDetail2);
        conversionRateModel.setConversionRate(exchangeReferenceRateDetail2.getExchangeRate()/exchangeReferenceRateDetail1.getExchangeRate());


        ReadDataService readDataService=new ReadDataService(conversionRateService,readAccessLogService,readDataProcessor);
        Mockito.doReturn(conversionRateModel).when(conversionRateService).processExchangeRateModel(any());
        Mockito.doNothing().when(readAccessLogService).logReadAccess(any());

        Assert.assertNotNull(readDataService.getExchangeReferenceRate(exchangeRateModel));

    }



    @Test
    public void testGetExchangeReferenceRateValues(){
        intialize();
        ExchangeRateValueModel exchangeRateValueModel =new ExchangeRateValueModel();
        exchangeRateValueModel.setSourceCurrency("EUR");
        exchangeRateValueModel.setTargetCurrency("USD");
        exchangeRateValueModel.setSourceValue(10.0);

        ExchangeReferenceRateDetail exchangeReferenceRateDetail1=new ExchangeReferenceRateDetail();
        exchangeReferenceRateDetail1.setExchangeReferenceRateId("123");
        exchangeReferenceRateDetail1.setExchangeRate(10.0);
        exchangeReferenceRateDetail1.setCurrencyId("EUR");

        ExchangeReferenceRateDetail exchangeReferenceRateDetail2=new ExchangeReferenceRateDetail();
        exchangeReferenceRateDetail2.setExchangeReferenceRateId("456");
        exchangeReferenceRateDetail2.setExchangeRate(10.0);
        exchangeReferenceRateDetail2.setCurrencyId("USD");


        ConversionRateModel conversionRateModel =new ConversionRateModel();
        conversionRateModel.setSourceCurrency(exchangeReferenceRateDetail1);
        conversionRateModel.setTargetCurrency(exchangeReferenceRateDetail2);
        conversionRateModel.setConversionRate(exchangeReferenceRateDetail2.getExchangeRate()/exchangeReferenceRateDetail1.getExchangeRate());


        ReadDataService readDataService=new ReadDataService(conversionRateService,readAccessLogService,readDataProcessor);
        Mockito.doReturn(conversionRateModel).when(conversionRateService).processExchangeRateModel(any());
        Mockito.doNothing().when(readAccessLogService).logReadAccess(any());

        Assert.assertNotNull(readDataService.getConversionRateForValue(exchangeRateValueModel));

    }
}

