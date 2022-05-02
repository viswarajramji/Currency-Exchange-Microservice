package com.ecb.exchangerate.application.enums;

public enum ChartView {
	TWELVE_HOUR("12H"), ONE_DAY("1D"), ONE_WEEK("1W"), ONE_MONTH("1M"), ONE_YEAR("1Y"), TWO_YEAR("2Y"), FIVE_YEAR("5Y"),
	TEN_YEAR("10Y");

	private String duration;

	ChartView(String duration) {
		this.duration = duration;
	}

	public String getDuration() {
		return duration;
	}

	@Override
	public String toString() {
		return duration;
	}
}
