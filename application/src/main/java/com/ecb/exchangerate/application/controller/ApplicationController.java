package com.ecb.exchangerate.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecb.exchangerate.application.configuration.MessageReader;


@RestController
public class ApplicationController {

	@GetMapping(value = "/")
	public String ping() {
		return MessageReader.getLocaleMessage("APP_STATUS");
	}
}
