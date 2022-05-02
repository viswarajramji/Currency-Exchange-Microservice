package com.ecb.exchangerate.application.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ecb.exchangerate.application.configuration.ProjectConstants;
import com.ecb.exchangerate.application.data.BaseModel;
import com.ecb.exchangerate.application.data.ConversionRateModel;
import com.ecb.exchangerate.application.data.ExchangeRateModel;
import com.ecb.exchangerate.application.data.ExchangeRateResponseModel;
import com.ecb.exchangerate.application.data.ExchangeRateValueModel;
import com.ecb.exchangerate.application.data.ExchangeRateValueResponseModel;
import com.ecb.exchangerate.application.data.ExternalLinkModel;
import com.ecb.exchangerate.application.data.ReadAccessModel;
import com.ecb.exchangerate.application.entity.ExchangeReferenceRate;
import com.ecb.exchangerate.application.enums.QueryInfoEnum;
import com.ecb.exchangerate.application.exception.ServiceApplicationException;
import com.ecb.exchangerate.application.processor.ReadDataProcessor;
import com.ecb.exchangerate.application.utils.DataUtils;

@Component
@Scope("request")
public class ReadDataService {

	private ConversionRateService conversionRateService;

	private ReadAccessLogService readAccessLogService;

	private ReadDataProcessor readDataProcessor;

	@Autowired
	public ReadDataService(ConversionRateService conversionRateService, ReadAccessLogService readAccessLogService,
			ReadDataProcessor readDataProcessor) {
		this.conversionRateService = conversionRateService;
		this.readAccessLogService = readAccessLogService;
		this.readDataProcessor = readDataProcessor;
	}

	public List<ExchangeReferenceRate> getAllSavedData() {
	    return readDataProcessor.getAllDataFromDB();

	}

	public ReadDataService performPreCheck(BaseModel baseModel) {
		if (!DataUtils.isCurrencyIdValid(baseModel.getSourceCurrency())) {
			throw new ServiceApplicationException("INVALID_SOURCE_CURRENCY");
		}

		if (!DataUtils.isCurrencyIdValid(baseModel.getTargetCurrency())) {
			throw new ServiceApplicationException("INVALID_TARGET_CURRENCY");
		}
		return this;
	}

	public String getPublicURLLink(ExternalLinkModel externalLinkModel) {
		StringBuilder sb = new StringBuilder(ProjectConstants.PUBLIC_URL_TEMPLATE);
		Map<QueryInfoEnum, String> queryOptions = externalLinkModel.getQueryInfo();
		if (!queryOptions.isEmpty()) {
			boolean isFirstOption = true;
			for (Map.Entry<QueryInfoEnum, String> entry : queryOptions.entrySet()) {
				sb.append(isFirstOption ? "?" : "&");
				sb.append(entry.getKey().getName()).append("=");
				String encodedUri = entry.getValue();
				sb.append(encodedUri);
				isFirstOption = false;
			}
			return sb.toString();
		}
		throw new ServiceApplicationException("APPLICATION_ERROR_OCCURED", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public List<ReadAccessModel> getReadAccessDetails(Date effectiveDate) {
		return readAccessLogService.getReadAccessDetails(effectiveDate);
	}

	public List<String> getAllSupportedCountriesByEffectiveDate(Date effectiveDate) {
		return readDataProcessor.getAllSupportedCurrencies(effectiveDate);
	}

	public ExchangeRateResponseModel getExchangeReferenceRate(ExchangeRateModel exchangeRateModel) {
		ExchangeRateResponseModel exchangeRateResponseModel = new ExchangeRateResponseModel();
		ConversionRateModel conversionRateModel = conversionRateService.processExchangeRateModel(exchangeRateModel);
		getExchangeRateResponseModel(exchangeRateResponseModel, conversionRateModel);
		readAccessLogging(conversionRateModel);
		return exchangeRateResponseModel;
	}

	public ExchangeRateValueResponseModel getConversionRateForValue(ExchangeRateValueModel exchangeRateValueModel) {
		ConversionRateModel conversionRateModel = conversionRateService
				.processExchangeRateModel(exchangeRateValueModel);
		ExchangeRateValueResponseModel exchangeRateValueResponseModel = new ExchangeRateValueResponseModel();
		getExchangeRateResponseModel(exchangeRateValueResponseModel, conversionRateModel);
		exchangeRateValueModel.setSourceValue(DataUtils.handleInfinityOrNan(exchangeRateValueModel.getSourceValue()));
		exchangeRateValueResponseModel.setSourceValue(exchangeRateValueModel.getSourceValue());
		exchangeRateValueResponseModel
				.setTargetValue(exchangeRateValueModel.getSourceValue() * conversionRateModel.getConversionRate());
		readAccessLogging(conversionRateModel);
		return exchangeRateValueResponseModel;
	}

	private ExchangeRateResponseModel getExchangeRateResponseModel(ExchangeRateResponseModel exchangeRateResponseModel,
			ConversionRateModel conversionRateModel) {
		exchangeRateResponseModel.setEffectiveDate(conversionRateModel.getEffectiveDate());
		exchangeRateResponseModel.setSourceCurrency(conversionRateModel.getSourceCurrency().getCurrencyId());
		exchangeRateResponseModel.setTargetCurrency(conversionRateModel.getTargetCurrency().getCurrencyId());
		exchangeRateResponseModel.setConversionRate(conversionRateModel.getConversionRate());
		return exchangeRateResponseModel;
	}

	private void readAccessLogging(ConversionRateModel conversionRateModel) {
		readAccessLogService.logReadAccess(conversionRateModel);
	}

}
