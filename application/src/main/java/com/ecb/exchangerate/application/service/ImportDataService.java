package com.ecb.exchangerate.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ecb.exchangerate.application.data.ImportDataModel;
import com.ecb.exchangerate.application.data.ImportResponseModel;
import com.ecb.exchangerate.application.enums.DataSourceEnum;

@Component
@Scope("request")
public class ImportDataService {

	private ImportDataProcessFactory importDataProcessFactory;

	@Autowired
	public ImportDataService(ImportDataProcessFactory importDataProcessFactory) {
		this.importDataProcessFactory = importDataProcessFactory;
	}

	public ImportResponseModel csvFileProcessor(String baseCurrency, MultipartFile clientFile) {
		ImportDataModel importDataModel = new ImportDataModel(baseCurrency,clientFile);
		return importDataProcessFactory.executeService(DataSourceEnum.CSV, importDataModel);
	}

}
