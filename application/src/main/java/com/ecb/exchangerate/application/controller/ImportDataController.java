package com.ecb.exchangerate.application.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecb.exchangerate.application.data.ImportResponseModel;
import com.ecb.exchangerate.application.service.ImportDataService;

@RestController
@RequestMapping("/import")
@Scope("request")
public class ImportDataController {

	private ImportDataService importDataService;

	@Autowired
	public ImportDataController(ImportDataService importDataService) {
		this.importDataService = importDataService;
	}

	@PostMapping(value = "/upload/{baseCurrency}")
	public ImportResponseModel uploadDataSet(@RequestParam(value = "CSVFile", required = true) MultipartFile clientFile,
			@PathVariable(value = "baseCurrency", required = true) String baseCurrency, HttpServletRequest request) {
		return importDataService.csvFileProcessor(baseCurrency, clientFile);
	}
	
	
}
