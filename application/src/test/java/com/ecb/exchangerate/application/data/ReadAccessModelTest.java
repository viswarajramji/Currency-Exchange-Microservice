package com.ecb.exchangerate.application.data;

import org.junit.Assert;
import org.junit.Test;

public class ReadAccessModelTest {

    private String currencyId ="EUR";
    private Long readCount=2L;

    @Test
    public void testReadAccessModel(){
        ReadAccessModel readAccessModel=new ReadAccessModel(currencyId,readCount);
        Assert.assertEquals(readAccessModel.getCurrencyId(),currencyId);
        Assert.assertEquals(readAccessModel.getReadCount(),readCount);
    }

    @Test
    public void testExternalLinkModelTestGetter(){
        ReadAccessModel readAccessModel=new ReadAccessModel();
        readAccessModel.setCurrencyId(currencyId);
        readAccessModel.setReadCount(readCount);
        Assert.assertEquals(readAccessModel.getCurrencyId(),currencyId);
        Assert.assertEquals(readAccessModel.getReadCount(),readCount);

    }
}
