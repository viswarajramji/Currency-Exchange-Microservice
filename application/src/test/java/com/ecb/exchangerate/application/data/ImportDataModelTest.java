package com.ecb.exchangerate.application.data;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.multipart.MultipartFile;

public class ImportDataModelTest {

    @Mock
    private MultipartFile multipartFile;

    private String baseCurrency="EUR";
    @Test
    public void testImportDataModelTest(){
        ImportDataModel importDataModel=new ImportDataModel(baseCurrency,multipartFile);
        Assert.assertEquals(importDataModel.getBaseCurrency(),baseCurrency);
        Assert.assertEquals(importDataModel.getFile(),multipartFile);
    }

    @Test
    public void testImportDataModelTestGetter(){
        ImportDataModel importDataModel=new ImportDataModel();
        importDataModel.setBaseCurrency(baseCurrency);
        importDataModel.setFile(multipartFile);
        Assert.assertEquals(importDataModel.getBaseCurrency(),baseCurrency);
        Assert.assertEquals(importDataModel.getFile(),multipartFile);

    }
}
