package com.ecb.exchangerate.application.service;

import com.ecb.exchangerate.application.data.ConversionRateModel;
import com.ecb.exchangerate.application.data.ExchangeRateModel;
import com.ecb.exchangerate.application.entity.ExchangeReferenceRateDetail;
import com.ecb.exchangerate.application.repo.ReadAccessRepo;
import com.ecb.exchangerate.application.repo.ReadReportViewRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;


public class ReadAccessLogServiceTest {

    private ReadAccessRepo readAccessRepo;

    private ReadReportViewRepo readReportViewRepo;

    @Test
    public void testGetReadAccessDetails(){
        Date date=new Date();
        readAccessRepo= Mockito.mock(ReadAccessRepo.class);
        readReportViewRepo= Mockito.mock(ReadReportViewRepo.class);
        ReadAccessLogService readAccessLogService=new ReadAccessLogService(readAccessRepo,readReportViewRepo);
        Mockito.doReturn(new ArrayList<>()).when(readReportViewRepo).findReadReportViewCountByEffectiveDate(date);
        Assert.assertEquals(readAccessLogService.getReadAccessDetails(date).size(),0);

    }


    @Test
    public void testFindReadReportViewCount(){
        readAccessRepo= Mockito.mock(ReadAccessRepo.class);
        readReportViewRepo= Mockito.mock(ReadReportViewRepo.class);
        ReadAccessLogService readAccessLogService=new ReadAccessLogService(readAccessRepo,readReportViewRepo);
        Mockito.doReturn(new ArrayList<>()).when(readReportViewRepo).findReadReportViewCount();
        Assert.assertEquals(readAccessLogService.getReadAccessDetails(null).size(),0);

    }


    @Test
    public void testLogReadAccess(){
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

        readAccessRepo= Mockito.mock(ReadAccessRepo.class);
        readReportViewRepo= Mockito.mock(ReadReportViewRepo.class);

        ReadAccessLogService readAccessLogService=new ReadAccessLogService(readAccessRepo,readReportViewRepo);
        readAccessLogService.logReadAccess(conversionRateModel);
        Mockito.doReturn(new ArrayList<>()).when(readAccessRepo).saveAll(anyList());
        Mockito.verify(readAccessRepo, times(1)).saveAll(anyList());

    }



    @Test
    public void testNullValue(){
        readAccessRepo= Mockito.mock(ReadAccessRepo.class);
        readReportViewRepo= Mockito.mock(ReadReportViewRepo.class);
        ReadAccessLogService readAccessLogService=new ReadAccessLogService(readAccessRepo,readReportViewRepo);
        readAccessLogService.logReadAccess(null);

        Mockito.doReturn(new ArrayList<>()).when(readAccessRepo).saveAll(anyList());
        Mockito.verify(readAccessRepo, times(0)).saveAll(anyList());

    }
}
