package com.ecb.exchangerate.application.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseModel {

    @NonNull
	private String sourceCurrency;

	@NonNull
	private String targetCurrency;
}
