package com.ecb.exchangerate.application.service;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ecb.exchangerate.application.data.ImportDataModel;
import com.ecb.exchangerate.application.data.ImportResponseModel;
import com.ecb.exchangerate.application.enums.DataSourceEnum;
import com.ecb.exchangerate.application.exception.ServiceApplicationException;
import com.ecb.exchangerate.application.processor.AbstractDataProcessorService;
import com.ecb.exchangerate.application.processor.CsvDataProcessorService;
import com.ecb.exchangerate.application.utils.SpringUtils;

@Component
@Scope("request")
public class ImportDataProcessFactory {


	public ImportResponseModel executeService(DataSourceEnum dataSourceEnum, ImportDataModel importDataModel) {
		AbstractDataProcessorService abstractDataProcessorService = null;
		if (DataSourceEnum.CSV.equals(dataSourceEnum)) {
			abstractDataProcessorService =getCSVAbstractProcessorService();
		}
		if (abstractDataProcessorService == null)
			throw new ServiceApplicationException("APPLICATION_ERROR_OCCURED", HttpStatus.INTERNAL_SERVER_ERROR);
		return abstractDataProcessorService.run(importDataModel);

	}

	protected AbstractDataProcessorService getCSVAbstractProcessorService(){
		return SpringUtils.getApplicationContext().getBean(CsvDataProcessorService.class);
	}
	
	
	
	
}
