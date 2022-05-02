package com.ecb.exchangerate.application.configuration;

public class ProjectConstants {
	private ProjectConstants() {
		
	}
	public static final String LOCALE_PATH = "classpath:locale/messages";
	public static final int LOCALE_CACHE_DURATION = 3600; // refresh cache once
	public static final String DECIMAL_FORMAT = "#.####";
	public static final String PUBLIC_URL_TEMPLATE = "https://www.xe.com/currencycharts/";
	public static final String DATE_FIELD = "Date";
	public static final Double DEFAULT_VALUE=0.0000;
}
