package com.ecb.exchangerate.application.data;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateValueResponseModel extends ExchangeRateResponseModel {

	@NonNull
	private Double sourceValue;
	
	@NonNull
	private Double targetValue;

}
