package com.ecb.exchangerate.application.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ecb.exchangerate.application.configuration.ProjectConstants;
import com.ecb.exchangerate.application.exception.ServiceApplicationException;

public class DataUtils {

	private DataUtils() {

	}
	
	public static boolean isEmptyOrNull(String value) {
		return value != null && !value.isEmpty();
	}

	public static boolean isCurrencyIdValid(String value) {
		return isEmptyOrNull(value) && value.length() == 3;
	}

	public static Double handleInfinityOrNan(Double value) {
		if (value != null && !value.isInfinite() && !value.isNaN()) {
			return value;
		}
		return ProjectConstants.DEFAULT_VALUE;
	}

	public static Date convertStringToDate(String dateString) {
		DateFormat batchImportFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat singleImportFormat = new SimpleDateFormat("dd MMMM yyyy");
		Date date = null;
		try {
			date = batchImportFormat.parse(dateString);
		} catch (ParseException ex) {
			try {
				date = singleImportFormat.parse(dateString);
			} catch (ParseException e) {
				throw new ServiceApplicationException("INVALID_DATE_VALUE");
			}
		}

		return date;

	}

	public static String trimValue(String value) {
		if (value != null) {
			return value.trim();
		}
		return value;
	}

}
