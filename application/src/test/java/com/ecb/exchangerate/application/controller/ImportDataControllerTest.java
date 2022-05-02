package com.ecb.exchangerate.application.controller;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import com.ecb.exchangerate.application.data.ImportResponseModel;
import com.ecb.exchangerate.application.service.ImportDataService;

@RunWith(MockitoJUnitRunner.class)
public class ImportDataControllerTest {

	@Mock
	private MultipartFile multipartFile;

	@Mock
	private HttpServletRequest httpServletRequest;


	@Test
	public void testImportDataControllter() {
		ImportDataService importDataService = mock(ImportDataService.class);
		ImportDataController importDataController = new ImportDataController(importDataService);
		Mockito.doReturn(new ImportResponseModel()).when(importDataService).csvFileProcessor("test", multipartFile);
		Assert.assertNotNull(importDataController.uploadDataSet(multipartFile, "test", httpServletRequest));

	}
}