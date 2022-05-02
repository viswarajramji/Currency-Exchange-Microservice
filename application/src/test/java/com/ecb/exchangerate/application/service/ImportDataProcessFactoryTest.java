package com.ecb.exchangerate.application.service;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

import com.ecb.exchangerate.application.data.ImportDataModel;
import com.ecb.exchangerate.application.data.ImportResponseModel;
import com.ecb.exchangerate.application.enums.DataSourceEnum;
import com.ecb.exchangerate.application.exception.ServiceApplicationException;
import com.ecb.exchangerate.application.processor.AbstractDataProcessorService;
import com.ecb.exchangerate.application.processor.CsvDataProcessorService;


public class ImportDataProcessFactoryTest {

    private MultipartFile multipartFile;
    private CsvDataProcessorService csvDataProcessorService;
    @Test
    public void testImportDataProcessFactoryTest(){
        multipartFile=Mockito.mock(MultipartFile.class);
        csvDataProcessorService=Mockito.mock(CsvDataProcessorService.class);
        ImportResponseModel importResponseModel=new ImportResponseModel();
        ImportDataModel importDataModel =new ImportDataModel("EUR",multipartFile);
        ImportDataProcessFactory importDataProcessFactory=new ImportDataProcessFactory(){
            @Override
            protected AbstractDataProcessorService getCSVAbstractProcessorService() {
               return csvDataProcessorService;
            }
        };
        Mockito.doReturn(importResponseModel).when(csvDataProcessorService).run(importDataModel);
        Assert.assertEquals(importDataProcessFactory.executeService(DataSourceEnum.CSV,importDataModel),importResponseModel);
    }

    @Test
    public void testImportDataProcessFactoryValue(){
        multipartFile=Mockito.mock(MultipartFile.class);
        csvDataProcessorService=Mockito.mock(CsvDataProcessorService.class);
        ImportResponseModel importResponseModel=new ImportResponseModel();
        ImportDataModel importDataModel =new ImportDataModel("EUR",multipartFile);
        ImportDataProcessFactory importDataProcessFactory=new ImportDataProcessFactory();
        Mockito.doReturn(null).when(csvDataProcessorService).run(importDataModel);
        try{
           importDataProcessFactory.executeService(null,importDataModel);
            Assert.assertEquals(false,true);
        }
        catch (ServiceApplicationException ex){
            Assert.assertNotNull(ex);
        }

    }
}
