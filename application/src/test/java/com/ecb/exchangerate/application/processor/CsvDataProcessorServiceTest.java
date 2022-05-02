package com.ecb.exchangerate.application.processor;

import com.ecb.exchangerate.application.data.ImportDataModel;
import com.ecb.exchangerate.application.exception.ServiceApplicationException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;


import static org.mockito.Mockito.times;

public class CsvDataProcessorServiceTest {

    private CsvDataProcessorService csvDataProcessorService=null;

    private MultipartFile multipartFile;

    public ImportDataProcessorService importDataProcessorService;

    @Test
    public void testCsvDataProcessorService(){
        multipartFile= Mockito.mock(MultipartFile.class);
        ImportDataModel importDataModel=new ImportDataModel();
        importDataModel.setBaseCurrency("test");
        importDataModel.setFile(multipartFile);

        Mockito.doReturn("text/xml").when(multipartFile).getContentType();

        csvDataProcessorService=new CsvDataProcessorService();
        try{
                csvDataProcessorService.run(importDataModel);
                Assert.assertEquals(false,true);
        }catch (ServiceApplicationException ex){
            Assert.assertNotNull(ex);
        }
    }

    @Test
    public void testBaseCurrency(){
        multipartFile= Mockito.mock(MultipartFile.class);
        ImportDataModel importDataModel=new ImportDataModel();
        importDataModel.setBaseCurrency("EUR");
        importDataModel.setFile(multipartFile);

        Mockito.doReturn("text/xml").when(multipartFile).getContentType();
        csvDataProcessorService=new CsvDataProcessorService();


        try{
            csvDataProcessorService.run(importDataModel);
            Assert.assertEquals(false,true);
        }catch (ServiceApplicationException ex){
            Assert.assertNotNull(ex);
        }
    }


    @Test
    public void testCSVProcessNoColumns() throws IOException {
        multipartFile= Mockito.mock(MultipartFile.class);
        importDataProcessorService=Mockito.mock(ImportDataProcessorService.class);
        csvDataProcessorService=new CsvDataProcessorService();
        csvDataProcessorService.importDataProcessorService=importDataProcessorService;

        File initialFile = new File("src/test/resources/testData.csv");
        InputStream targetStream = new FileInputStream(initialFile);

        ImportDataModel importDataModel=new ImportDataModel();
        importDataModel.setBaseCurrency("EUR");
        importDataModel.setFile(multipartFile);

        Mockito.doReturn("text/csv").when(multipartFile).getContentType();
        Mockito.doReturn(targetStream).when(multipartFile).getInputStream();

        Mockito.doNothing().when(importDataProcessorService).deleteExchangeReferenceRateByEffectiveDates(Mockito.anyList());
        Mockito.doNothing().when(importDataProcessorService).saveAllExchangeReferenceRates(Mockito.anyList());

        try{
            csvDataProcessorService.run(importDataModel);
        }catch (ServiceApplicationException ex){
            Assert.assertNotNull(ex);
        }
        Mockito.verify(importDataProcessorService, times(1)).saveAllExchangeReferenceRates(Mockito.anyList());
        Mockito.verify(importDataProcessorService, times(1)).deleteExchangeReferenceRateByEffectiveDates(Mockito.anyList());
    }

}
