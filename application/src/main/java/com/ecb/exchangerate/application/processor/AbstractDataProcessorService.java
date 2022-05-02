package com.ecb.exchangerate.application.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ecb.exchangerate.application.data.ImportDataModel;
import com.ecb.exchangerate.application.data.ImportResponseModel;
import com.ecb.exchangerate.application.exception.ServiceApplicationException;
import com.ecb.exchangerate.application.utils.DataUtils;

@Component
@Scope("request")
public abstract class AbstractDataProcessorService {

	@Autowired
	public ImportDataProcessorService importDataProcessorService;


	public void validateCurrency(String currency) {
		if (!DataUtils.isCurrencyIdValid(currency)) {
			throw new ServiceApplicationException("INVALID_CURRENCY_VALUE");
		}
	}
	
	public abstract ImportResponseModel run(ImportDataModel importDataModel);

}
