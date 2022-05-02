package com.ecb.exchangerate.application.controller;

import java.util.ArrayList;
import java.util.Date;

import com.ecb.exchangerate.application.data.ExternalLinkModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ecb.exchangerate.application.data.ExchangeRateModel;
import com.ecb.exchangerate.application.data.ExchangeRateResponseModel;
import com.ecb.exchangerate.application.data.ExchangeRateValueModel;
import com.ecb.exchangerate.application.data.ExchangeRateValueResponseModel;
import com.ecb.exchangerate.application.data.ExternalLinkModelTest;
import com.ecb.exchangerate.application.service.ReadDataService;

@RunWith(MockitoJUnitRunner.class)
public class ReadDataControllerTest {

	@Mock
	private ReadDataService readDataService;

	@Test
	public void testGetExchangeReferenceRate() {
		ReadDataController readDataController = new ReadDataController(readDataService);
		ExchangeRateModel exchangeModel = new ExchangeRateModel();
		Mockito.doReturn(readDataService).when(readDataService).performPreCheck(exchangeModel);
		Mockito.doReturn(new ExchangeRateResponseModel()).when(readDataService).getExchangeReferenceRate(exchangeModel);
		Assert.assertNotNull(readDataController.getReferenceRates(exchangeModel));
	}

	@Test
	public void testGetConvertedValue() {
		ReadDataController readDataController = new ReadDataController(readDataService);
		ExchangeRateValueModel exchangeModel = new ExchangeRateValueModel();
		Mockito.doReturn(readDataService).when(readDataService).performPreCheck(exchangeModel);
		Mockito.doReturn(new ExchangeRateValueResponseModel()).when(readDataService)
				.getConversionRateForValue(exchangeModel);
		Assert.assertNotNull(readDataController.getConvertedValue(exchangeModel));
	}

	@Test
	public void testGetPublicURL() {
		ReadDataController readDataController = new ReadDataController(readDataService);
		ExternalLinkModel externalLinkModel = new ExternalLinkModel();
		Mockito.doReturn(readDataService).when(readDataService).performPreCheck(externalLinkModel);
		Mockito.doReturn(new String()).when(readDataService).getPublicURLLink(externalLinkModel);
		Assert.assertNotNull(readDataController.getPublicURL(externalLinkModel));
	}

	@Test
	public void testGetAllSupportedCurrency() {
		ReadDataController readDataController = new ReadDataController(readDataService);
		Date effectiveDate = new Date();
		Mockito.doReturn(new ArrayList<>()).when(readDataService).getAllSupportedCountriesByEffectiveDate(effectiveDate);
		Assert.assertNotNull(readDataController.getAllSupportedCurrency(effectiveDate));
	}
	
	@Test
	public void testGetReadAccessDetails() {
		ReadDataController readDataController = new ReadDataController(readDataService);
		Date effectiveDate = new Date();
		Mockito.doReturn(new ArrayList<>()).when(readDataService).getReadAccessDetails(effectiveDate);
		Assert.assertNotNull(readDataController.getReadAccessDetails(effectiveDate));
	}
}
