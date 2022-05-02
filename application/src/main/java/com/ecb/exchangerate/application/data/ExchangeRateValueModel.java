package com.ecb.exchangerate.application.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateValueModel extends ExchangeRateModel {

	@NonNull
	private Double sourceValue;

}
