package com.ecb.exchangerate.application.view;

import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ReadReportViewTest {

    @Test
    public void testReadReportView(){
        ReadReportView readReportView=new ReadReportView();
        readReportView.setReadBy("TEST");
        readReportView.setReadOn(new Date());
        readReportView.setCurrencyId("EUR");
        readReportView.setEffectiveDate(new Date());
        readReportView.setExchangeReferenceRateId("123");
        Assert.assertNotNull(readReportView.getReadBy());
        Assert.assertNotNull(readReportView.getReadOn());
        Assert.assertNotNull(readReportView.getCurrencyId());
        Assert.assertNotNull(readReportView.getEffectiveDate());
        Assert.assertNotNull(readReportView.getExchangeReferenceRateId());
    }
}
