package com.ecb.exchangerate.application.configuration;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class MessageReader {
	

	private static final MessageSource messageSource;

	private MessageReader() {

	}

	static {
		ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		reloadableResourceBundleMessageSource.setBasename(ProjectConstants.LOCALE_PATH);
		reloadableResourceBundleMessageSource.setCacheSeconds(ProjectConstants.LOCALE_CACHE_DURATION);
		messageSource = reloadableResourceBundleMessageSource;
	}

	public static String getLocaleMessage(String messageId) {
		Locale locale = getLocale();
		return messageSource.getMessage(messageId, null, locale);
	}

	private static Locale getLocale() {
		return Locale.ENGLISH;
	}

	public static String getLocaleMessage(String messageId, Object[] args) {
		Locale locale = getLocale();
		return messageSource.getMessage(messageId, args, locale);
	}
}