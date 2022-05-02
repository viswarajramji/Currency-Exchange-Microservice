package com.ecb.exchangerate.application.enums;

public enum QueryInfoEnum {
	SOURCE_CURRENCY("from"), TARGET_CURRENCY("to"), CHART_VIEW("view");

	private String name;

	QueryInfoEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
