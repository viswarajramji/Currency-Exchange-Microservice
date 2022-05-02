package com.ecb.exchangerate.application.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils implements ApplicationContextAware {

	private static ApplicationContext appcontext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		appcontext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return appcontext;
	}
}
