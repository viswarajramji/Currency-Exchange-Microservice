package com.ecb.exchangerate.application.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecb.exchangerate.application.data.ExchangeRateModel;
import com.ecb.exchangerate.application.data.ExchangeRateResponseModel;
import com.ecb.exchangerate.application.data.ExchangeRateValueModel;
import com.ecb.exchangerate.application.data.ExchangeRateValueResponseModel;
import com.ecb.exchangerate.application.data.ExternalLinkModel;
import com.ecb.exchangerate.application.data.ReadAccessModel;
import com.ecb.exchangerate.application.entity.ExchangeReferenceRate;
import com.ecb.exchangerate.application.service.ReadDataService;

@RestController
@RequestMapping("/read")
@Scope("request")
public class ReadDataController {

	private ReadDataService readDataService;

	@Autowired
	public ReadDataController(ReadDataService readDataService) {
		this.readDataService = readDataService;
	}

	@GetMapping(value = "/getReferenceRate")
	public ExchangeRateResponseModel getReferenceRates(@RequestBody ExchangeRateModel exchangeRateModel) {
		return readDataService.performPreCheck(exchangeRateModel).getExchangeReferenceRate(exchangeRateModel);
	}

	@GetMapping(value = "/getConvertedValue")
	public ExchangeRateValueResponseModel getConvertedValue(
			@RequestBody ExchangeRateValueModel exchangeRateValueModel) {
		return readDataService.performPreCheck(exchangeRateValueModel)
				.getConversionRateForValue(exchangeRateValueModel);
	}

	@GetMapping(value = "/getServiceURL")
	public String getPublicURL(@RequestBody ExternalLinkModel externalLinkModel) {
		return readDataService.performPreCheck(externalLinkModel).getPublicURLLink(externalLinkModel);
	}

	@GetMapping(value = "/getAllSupportedCurrency")
	public List<String> getAllSupportedCurrency(
			@RequestParam(value = "effectiveDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date effectiveDate) {
		return readDataService.getAllSupportedCountriesByEffectiveDate(effectiveDate);
	}

	@GetMapping(value = "/getReadAccessDetails")
	public List<ReadAccessModel> getReadAccessDetails(
			@RequestParam(value = "effectiveDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date effectiveDate) {
		return readDataService.getReadAccessDetails(effectiveDate);
	}
	
	@GetMapping(value = "/getAllSavedData")
	public List<ExchangeReferenceRate>  getAllData() {
		return readDataService.getAllSavedData();
	}
}
