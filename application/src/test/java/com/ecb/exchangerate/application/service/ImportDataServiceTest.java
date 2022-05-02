package com.ecb.exchangerate.application.service;

import static org.mockito.ArgumentMatchers.any;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

import com.ecb.exchangerate.application.data.ImportResponseModel;


public class ImportDataServiceTest {

    private ImportDataProcessFactory importDataProcessFactory;

    private MultipartFile multipartFile;

    @Test
    public void testImportDataServiceTest(){
        ImportResponseModel importResponseModel=new ImportResponseModel();
        importDataProcessFactory=Mockito.mock(ImportDataProcessFactory.class);
        multipartFile=Mockito.mock(MultipartFile.class);
        ImportDataService importDataService=new ImportDataService(importDataProcessFactory);
        Mockito.doReturn(importResponseModel).when(importDataProcessFactory).executeService(any(),any());
        Assert.assertEquals(importResponseModel, importDataService.csvFileProcessor("EUR",multipartFile));

    }
}
