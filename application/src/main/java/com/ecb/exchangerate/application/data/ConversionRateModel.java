package com.ecb.exchangerate.application.data;

import java.util.Date;

import com.ecb.exchangerate.application.entity.ExchangeReferenceRateDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversionRateModel {

	private Double conversionRate;
	
	private Date effectiveDate;

	private ExchangeReferenceRateDetail sourceCurrency;

	private ExchangeReferenceRateDetail targetCurrency;

}
