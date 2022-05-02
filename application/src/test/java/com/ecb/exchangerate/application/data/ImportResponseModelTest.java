package com.ecb.exchangerate.application.data;

import org.junit.Assert;
import org.junit.Test;

public class ImportResponseModelTest {
    @Test
    public void testImportDataModelTest(){
        ImportResponseModel importResponseModel=new ImportResponseModel(100);
        Assert.assertNotNull(importResponseModel.getRecordsProcessed());
        importResponseModel=new ImportResponseModel();
        importResponseModel.setRecordsProcessed(100);
        Assert.assertNotNull(importResponseModel.getRecordsProcessed());
    }
}
