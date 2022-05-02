package com.ecb.exchangerate.application.data;

import java.util.LinkedHashMap;
import java.util.Map;

import com.ecb.exchangerate.application.enums.ChartView;
import com.ecb.exchangerate.application.enums.QueryInfoEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExternalLinkModel extends BaseModel {

	public ChartView chartView;

	public Map<QueryInfoEnum, String> getQueryInfo() {
		Map<QueryInfoEnum, String> values = new LinkedHashMap<>();
		if(this.getSourceCurrency()!=null && !this.getSourceCurrency().isEmpty()){
			values.put(QueryInfoEnum.SOURCE_CURRENCY, this.getSourceCurrency());
		}
		if(this.getTargetCurrency()!=null && !this.getTargetCurrency().isEmpty()){
			values.put(QueryInfoEnum.TARGET_CURRENCY, this.getTargetCurrency());
		}
		if (this.getChartView() != null) {
			values.put(QueryInfoEnum.CHART_VIEW, this.getChartView().getDuration());
		}
		return values;
	}
}
