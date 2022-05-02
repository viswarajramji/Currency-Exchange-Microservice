package com.ecb.exchangerate.application.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ecb.exchangerate.application.data.ExchangeRateModel;
import com.ecb.exchangerate.application.entity.ExchangeReferenceRate;
import com.ecb.exchangerate.application.entity.ExchangeReferenceRateDetail;
import com.ecb.exchangerate.application.repo.ExchangeReferenceRateDetailRepo;
import com.ecb.exchangerate.application.repo.ExchangeReferenceRateRepo;

@Component
@Scope("request")
public class ReadDataProcessor {

	private ExchangeReferenceRateDetailRepo exchangeReferenceRateDetailRepo;
	
	private ExchangeReferenceRateRepo exchangeReferenceRateRepo;

	@Autowired
	public ReadDataProcessor(ExchangeReferenceRateDetailRepo exchangeReferenceRateDetailRepo,ExchangeReferenceRateRepo exchangeReferenceRateRepo) {
		this.exchangeReferenceRateDetailRepo = exchangeReferenceRateDetailRepo;
		this.exchangeReferenceRateRepo=exchangeReferenceRateRepo;
	}
	
	
	public List<ExchangeReferenceRate> getAllDataFromDB(){
		return exchangeReferenceRateRepo.findAll();
	}
	

	public List<String> getAllSupportedCurrencies(Date effectiveDate) {
		List<String> supportedCurrenyList = null;
		if (effectiveDate != null) {
			supportedCurrenyList = getAllListedCurrenciesByEffectiveDate(effectiveDate);
		} else {
			supportedCurrenyList = getAllListedCurrencies();
		}
		if (supportedCurrenyList == null) {
			return new ArrayList<>();
		}
		return supportedCurrenyList;

	}

	private List<String> getAllListedCurrencies() {
		return this.exchangeReferenceRateDetailRepo.findAllCurrencyId();
	}

	private List<String> getAllListedCurrenciesByEffectiveDate(Date effectiveDate) {
		return this.exchangeReferenceRateDetailRepo.findAllCurrencyIdByEffectiveDate(effectiveDate);
	}

	public Map<String, ExchangeReferenceRateDetail> getExchangeReferenceRateDetails(
			ExchangeRateModel exchangeRateModel) {
		List<String> currencyList = new ArrayList<>();
		currencyList.add(exchangeRateModel.getSourceCurrency());
		currencyList.add(exchangeRateModel.getTargetCurrency());
		if (exchangeRateModel.getEffectiveDate() == null) {
			exchangeRateModel.setEffectiveDate(new Date());
		}
		return getExchangeReferenceRateDetails(currencyList, exchangeRateModel.getEffectiveDate());
	}

	private Map<String, ExchangeReferenceRateDetail> getExchangeReferenceRateDetails(List<String> currencyList,
			Date effectiveDate) {
		List<ExchangeReferenceRateDetail> exchangeReferenceRateDetails = exchangeReferenceRateDetailRepo
				.findAllByExchangeReferenceRate_EffectiveDateAndCurrencyIdIn(effectiveDate, currencyList);
		if (exchangeReferenceRateDetails == null || exchangeReferenceRateDetails.isEmpty()) {
			return new HashMap<>();
		}
		return exchangeReferenceRateDetails.stream()
				.collect(Collectors.toMap(ExchangeReferenceRateDetail::getCurrencyId, Function.identity()));

	}

}
