package com.ecb.exchangerate.application.processor;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ecb.exchangerate.application.entity.ExchangeReferenceRate;
import com.ecb.exchangerate.application.repo.ExchangeReferenceRateRepo;

@Component
@Scope("request")
public class ImportDataProcessorService {

	private ExchangeReferenceRateRepo exchangeReferenceRateRepo;

	@Autowired
	public ImportDataProcessorService(ExchangeReferenceRateRepo exchangeReferenceRateRepo) {
		this.exchangeReferenceRateRepo = exchangeReferenceRateRepo;
	}

	public void deleteExchangeReferenceRateByEffectiveDates(List<Date> effectiveDates) {
		if (effectiveDates != null && !effectiveDates.isEmpty()) {
			exchangeReferenceRateRepo.deleteByEffectiveDateIn(effectiveDates);
		}
	}

	public void saveAllExchangeReferenceRates(List<ExchangeReferenceRate> exchangeReferenceRates) {
		if (exchangeReferenceRates != null && !exchangeReferenceRates.isEmpty()) {
			exchangeReferenceRateRepo.saveAll(exchangeReferenceRates);
		}
	}
}
