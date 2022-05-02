package com.ecb.exchangerate.application.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ecb.exchangerate.application.configuration.ProjectConstants;
import com.ecb.exchangerate.application.data.ConversionRateModel;
import com.ecb.exchangerate.application.data.ExchangeRateModel;
import com.ecb.exchangerate.application.entity.ExchangeReferenceRateDetail;
import com.ecb.exchangerate.application.exception.ServiceApplicationException;
import com.ecb.exchangerate.application.processor.ReadDataProcessor;

@Component
@Scope("request")
public class ConversionRateService {

	private ReadDataProcessor readDataProcessor;

	@Autowired
	public ConversionRateService(ReadDataProcessor readDataProcessor) {
		this.readDataProcessor = readDataProcessor;
	}

	public ConversionRateModel processExchangeRateModel(ExchangeRateModel exchangeRateModel) {
		Map<String, ExchangeReferenceRateDetail> currencyIdVsExchangeReferenceRateDetail = readDataProcessor
				.getExchangeReferenceRateDetails(exchangeRateModel);
		if (!currencyIdVsExchangeReferenceRateDetail.containsKey(exchangeRateModel.getSourceCurrency())) {
			throw new ServiceApplicationException("NO_DATA_WITH_SOURCE_CURRENCY",
					new Object[] { exchangeRateModel.getSourceCurrency() });
		}

		if (!currencyIdVsExchangeReferenceRateDetail.containsKey(exchangeRateModel.getTargetCurrency())) {
			throw new ServiceApplicationException("NO_DATA_WITH_TARGET_CURRENCY",
					new Object[] { exchangeRateModel.getTargetCurrency() });
		}
		return performCurrencyConversionComputation(currencyIdVsExchangeReferenceRateDetail, exchangeRateModel);
	}

	private ConversionRateModel performCurrencyConversionComputation(
			Map<String, ExchangeReferenceRateDetail> currencyIdVsExchangeReferenceRateDetail,
			ExchangeRateModel exchangeRateModel) {
		ConversionRateModel conversionRateModel = new ConversionRateModel();
		conversionRateModel
				.setSourceCurrency(currencyIdVsExchangeReferenceRateDetail.get(exchangeRateModel.getSourceCurrency()));
		conversionRateModel
				.setTargetCurrency(currencyIdVsExchangeReferenceRateDetail.get(exchangeRateModel.getTargetCurrency()));
		conversionRateModel.setEffectiveDate(exchangeRateModel.getEffectiveDate());
		conversionRateModel.setConversionRate(getConversionRate(conversionRateModel));
		return conversionRateModel;
	}

	private Double getConversionRate(ConversionRateModel conversionRateModel) {
		DecimalFormat decimalFormat = new DecimalFormat(ProjectConstants.DECIMAL_FORMAT);
		Double conversionRate = (conversionRateModel.getTargetCurrency().getExchangeRate()/ conversionRateModel.getSourceCurrency().getExchangeRate());
		if (conversionRate.isInfinite() || conversionRate.isNaN()) {
			conversionRate = ProjectConstants.DEFAULT_VALUE;
		}
		return Double.valueOf(decimalFormat.format(conversionRate));

	}

}
