package com.ecb.exchangerate.application.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadAccessModel {

	private String currencyId;
	private Long readCount;
}
