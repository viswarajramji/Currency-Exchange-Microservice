package com.ecb.exchangerate.application.controller;

import org.junit.Assert;
import org.junit.Test;

public class ApplicationControllerTest {
	@Test
	public void testApplicationControllerTest() {
		ApplicationController applicationController = new ApplicationController();
		Assert.assertNotNull(applicationController.ping());

	}
}
