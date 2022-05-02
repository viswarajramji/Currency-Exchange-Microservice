package com.ecb.exchangerate.application.data;

import org.junit.Assert;
import org.junit.Test;

public class BaseModelTest {

	public String sourceCurrency = "EUR";
	public String targetCurrency = "USD";

	@Test
	public void testBaseModelSetterGetter() {
		BaseModel baseModel = new BaseModel();
		baseModel.setSourceCurrency(sourceCurrency);
		baseModel.setTargetCurrency(targetCurrency);
		Assert.assertEquals(baseModel.getSourceCurrency(), sourceCurrency);
		Assert.assertEquals(baseModel.getTargetCurrency(), targetCurrency);

	}

	@Test
	public void testBaseModelAllArgs() {
		BaseModel baseModel = new BaseModel(sourceCurrency, targetCurrency);
		Assert.assertEquals(baseModel.getSourceCurrency(), sourceCurrency);
		Assert.assertEquals(baseModel.getTargetCurrency(), targetCurrency);

	}

	
}
