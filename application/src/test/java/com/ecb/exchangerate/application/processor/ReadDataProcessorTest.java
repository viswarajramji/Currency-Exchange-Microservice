package com.ecb.exchangerate.application.processor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ecb.exchangerate.application.data.ExchangeRateModel;
import com.ecb.exchangerate.application.entity.ExchangeReferenceRateDetail;
import com.ecb.exchangerate.application.repo.ExchangeReferenceRateDetailRepo;
import com.ecb.exchangerate.application.repo.ExchangeReferenceRateRepo;

public class ReadDataProcessorTest {



    @Test
    public void testReadProcessorEmptyNoDate(){
        ExchangeReferenceRateDetailRepo exchangeReferenceRateDetailRepo=mock(ExchangeReferenceRateDetailRepo.class);
        ExchangeReferenceRateRepo ExchangeReferenceRateRepo=mock(ExchangeReferenceRateRepo.class);
        ReadDataProcessor readDataProcessor=new ReadDataProcessor(exchangeReferenceRateDetailRepo,ExchangeReferenceRateRepo);
        Mockito.doReturn(null).when(exchangeReferenceRateDetailRepo).findAllCurrencyId();
        Assert.assertEquals(readDataProcessor.getAllSupportedCurrencies(null).size(),0);
    }


    @Test
    public void testReadProcessorEmptyDate(){
        ExchangeReferenceRateDetailRepo exchangeReferenceRateDetailRepo=mock(ExchangeReferenceRateDetailRepo.class);
        ExchangeReferenceRateRepo ExchangeReferenceRateRepo=mock(ExchangeReferenceRateRepo.class);
        ReadDataProcessor readDataProcessor=new ReadDataProcessor(exchangeReferenceRateDetailRepo,ExchangeReferenceRateRepo);
        Mockito.doReturn(new ArrayList<>()).when(exchangeReferenceRateDetailRepo).findAllCurrencyIdByEffectiveDate(any());
        Assert.assertEquals(readDataProcessor.getAllSupportedCurrencies(new Date()).size(),0);
    }

    @Test
    public void testReadProcessorValueNoDate(){
        List<ExchangeReferenceRateDetail>  exchangeReferenceRateDetailList=List.of(new ExchangeReferenceRateDetail());
        ExchangeReferenceRateDetailRepo exchangeReferenceRateDetailRepo=mock(ExchangeReferenceRateDetailRepo.class);
        ExchangeReferenceRateRepo ExchangeReferenceRateRepo=mock(ExchangeReferenceRateRepo.class);
        ReadDataProcessor readDataProcessor=new ReadDataProcessor(exchangeReferenceRateDetailRepo,ExchangeReferenceRateRepo);
        Mockito.doReturn(exchangeReferenceRateDetailList).when(exchangeReferenceRateDetailRepo).findAllCurrencyId();
        Assert.assertEquals(readDataProcessor.getAllSupportedCurrencies(null).size(),1);
    }

    @Test
    public void testReadProcessorValueDate(){
        List<ExchangeReferenceRateDetail>  exchangeReferenceRateDetailList=List.of(new ExchangeReferenceRateDetail());
        ExchangeReferenceRateDetailRepo exchangeReferenceRateDetailRepo=mock(ExchangeReferenceRateDetailRepo.class);
        ExchangeReferenceRateRepo ExchangeReferenceRateRepo=mock(ExchangeReferenceRateRepo.class);
        ReadDataProcessor readDataProcessor=new ReadDataProcessor(exchangeReferenceRateDetailRepo,ExchangeReferenceRateRepo);
        Mockito.doReturn(exchangeReferenceRateDetailList).when(exchangeReferenceRateDetailRepo).findAllCurrencyIdByEffectiveDate(any());
        Assert.assertEquals(readDataProcessor.getAllSupportedCurrencies(new Date()).size(),1);
    }


    @Test
    public void testGetExchangeReferenceRateDetails(){
        ExchangeReferenceRateDetailRepo exchangeReferenceRateDetailRepo=mock(ExchangeReferenceRateDetailRepo.class);
        ExchangeReferenceRateRepo ExchangeReferenceRateRepo=mock(ExchangeReferenceRateRepo.class);
        ReadDataProcessor readDataProcessor=new ReadDataProcessor(exchangeReferenceRateDetailRepo,ExchangeReferenceRateRepo);
        ExchangeRateModel exchangeRateModel =new ExchangeRateModel(null);
        exchangeRateModel.setSourceCurrency("EUR");
        exchangeRateModel.setTargetCurrency("USD");
        Mockito.doReturn(new ArrayList<>()).when(exchangeReferenceRateDetailRepo).findAllByExchangeReferenceRate_EffectiveDateAndCurrencyIdIn(any(),anyList());
        Assert.assertEquals( readDataProcessor.getExchangeReferenceRateDetails(exchangeRateModel).size(),0);
    }

    @Test
    public void testGetExchangeReferenceRateDetailsWithValues(){
        ExchangeReferenceRateDetail exchangeReferenceRateDetail1=new ExchangeReferenceRateDetail();
        exchangeReferenceRateDetail1.setExchangeReferenceRateId("123");
        exchangeReferenceRateDetail1.setExchangeRate(10.0);
        exchangeReferenceRateDetail1.setCurrencyId("EUR");

        ExchangeReferenceRateDetail exchangeReferenceRateDetail2=new ExchangeReferenceRateDetail();
        exchangeReferenceRateDetail2.setExchangeReferenceRateId("456");
        exchangeReferenceRateDetail2.setExchangeRate(10.0);
        exchangeReferenceRateDetail2.setCurrencyId("USD");


        List<ExchangeReferenceRateDetail>  exchangeReferenceRateDetailList=List.of(exchangeReferenceRateDetail1,exchangeReferenceRateDetail2);
        ExchangeReferenceRateDetailRepo exchangeReferenceRateDetailRepo=mock(ExchangeReferenceRateDetailRepo.class);
        ExchangeReferenceRateRepo ExchangeReferenceRateRepo=mock(ExchangeReferenceRateRepo.class);
        ReadDataProcessor readDataProcessor=new ReadDataProcessor(exchangeReferenceRateDetailRepo,ExchangeReferenceRateRepo);
        ExchangeRateModel exchangeRateModel =new ExchangeRateModel(new Date());
        exchangeRateModel.setSourceCurrency("EUR");
        exchangeRateModel.setTargetCurrency("USD");

        Mockito.doReturn(exchangeReferenceRateDetailList).when(exchangeReferenceRateDetailRepo).findAllByExchangeReferenceRate_EffectiveDateAndCurrencyIdIn(any(),anyList());
        Assert.assertEquals( readDataProcessor.getExchangeReferenceRateDetails(exchangeRateModel).size(),2);
    }



}
