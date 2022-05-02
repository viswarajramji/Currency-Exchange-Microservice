package com.ecb.exchangerate.application.processor;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.ecb.exchangerate.application.entity.ExchangeReferenceRate;
import com.ecb.exchangerate.application.repo.ExchangeReferenceRateRepo;

public class ImportDataProcessorServiceTest {




    @Test
    public void testDeleteExchangeReferenceRateByEffectiveDates(){
        List<Date> dates= new ArrayList<>();
        ExchangeReferenceRateRepo exchangeReferenceRateRepo= Mockito.mock(ExchangeReferenceRateRepo.class);
        ImportDataProcessorService importDataProcessorService=new ImportDataProcessorService(exchangeReferenceRateRepo);
        importDataProcessorService.deleteExchangeReferenceRateByEffectiveDates(dates);
        Mockito.verify(exchangeReferenceRateRepo, times(0)).deleteByEffectiveDateIn(dates);
    }


    @Test
    public void testDeleteExchangeReferenceRateByEffectiveDatesValues(){
        List<Date> dates= List.of(new Date());
        ExchangeReferenceRateRepo exchangeReferenceRateRepo= Mockito.mock(ExchangeReferenceRateRepo.class);
        ImportDataProcessorService importDataProcessorService=new ImportDataProcessorService(exchangeReferenceRateRepo);
        Mockito.doNothing().when(exchangeReferenceRateRepo).deleteByEffectiveDateIn(anyList());
        importDataProcessorService.deleteExchangeReferenceRateByEffectiveDates(dates);
        Mockito.verify(exchangeReferenceRateRepo, times(1)).deleteByEffectiveDateIn(dates);
    }

    @Test
    public void testSaveAllExchangeReferenceRates(){
        List<ExchangeReferenceRate> values= new ArrayList<>();
        ExchangeReferenceRateRepo exchangeReferenceRateRepo= Mockito.mock(ExchangeReferenceRateRepo.class);
        ImportDataProcessorService importDataProcessorService=new ImportDataProcessorService(exchangeReferenceRateRepo);
        importDataProcessorService.saveAllExchangeReferenceRates(values);
        Mockito.verify(exchangeReferenceRateRepo, times(0)).saveAll(values);
    }


    @Test
    public void testSaveAllExchangeReferenceRatesValue(){
        List<ExchangeReferenceRate> values= List.of(new ExchangeReferenceRate());
        ExchangeReferenceRateRepo exchangeReferenceRateRepo= Mockito.mock(ExchangeReferenceRateRepo.class);
        ImportDataProcessorService importDataProcessorService=new ImportDataProcessorService(exchangeReferenceRateRepo);
        importDataProcessorService.saveAllExchangeReferenceRates(values);
        Mockito.doReturn(new ArrayList<>()).when(exchangeReferenceRateRepo).saveAll(anyList());
        Mockito.verify(exchangeReferenceRateRepo, times(1)).saveAll(values);
    }
}
