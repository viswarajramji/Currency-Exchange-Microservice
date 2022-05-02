package com.ecb.exchangerate.application.data;

import org.junit.Assert;
import org.junit.Test;

import com.ecb.exchangerate.application.enums.ChartView;

public class ExternalLinkModelTest {



    private String sourceCurrency="EUR";

    private String targetCurrency="USD";

    private ChartView chartView=ChartView.ONE_DAY;

    @Test
    public void testExchangeRateValueResponseModel(){
        ExternalLinkModel externalLinkModel=new ExternalLinkModel(chartView);
        externalLinkModel.setSourceCurrency(sourceCurrency);
        externalLinkModel.setTargetCurrency(targetCurrency);
        Assert.assertEquals(externalLinkModel.getChartView(),chartView);
        Assert.assertEquals(externalLinkModel.getSourceCurrency(),sourceCurrency);
        Assert.assertEquals(externalLinkModel.getTargetCurrency(),targetCurrency);
    }

    @Test
    public void testExternalLinkModelTestGetter(){
        ExternalLinkModel externalLinkModel=new ExternalLinkModel();
        externalLinkModel.setSourceCurrency(sourceCurrency);
        externalLinkModel.setTargetCurrency(targetCurrency);
        externalLinkModel.setChartView(chartView);
        Assert.assertEquals(externalLinkModel.getChartView(),chartView);
        Assert.assertEquals(externalLinkModel.getSourceCurrency(),sourceCurrency);
        Assert.assertEquals(externalLinkModel.getTargetCurrency(),targetCurrency);
        Assert.assertEquals(externalLinkModel.getQueryInfo().size(),3);

    }
}
