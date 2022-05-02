package com.ecb.exchangerate.application.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ecb.exchangerate.application.data.ConversionRateModel;
import com.ecb.exchangerate.application.data.ReadAccessModel;
import com.ecb.exchangerate.application.entity.ReadAccess;
import com.ecb.exchangerate.application.repo.ReadAccessRepo;
import com.ecb.exchangerate.application.repo.ReadReportViewRepo;

@Component
@Scope("request")
public class ReadAccessLogService {

	private ReadAccessRepo readAccessRepo;

	private ReadReportViewRepo readReportViewRepo;

	@Autowired
	public ReadAccessLogService(ReadAccessRepo readAccessRepo, ReadReportViewRepo readReportViewRepo) {
		this.readAccessRepo = readAccessRepo;
		this.readReportViewRepo=readReportViewRepo;
	}

	public List<ReadAccessModel> getReadAccessDetails(Date effectiveDate) {
		if(effectiveDate!=null) {
			return getReadAccessModelByEffectiveDate(effectiveDate);
		}
		return getReadAccessModel();
	}
	
	public List<ReadAccessModel> getReadAccessModelByEffectiveDate(Date effectiveDate){
		return this.readReportViewRepo.findReadReportViewCountByEffectiveDate(effectiveDate);
	}
	
	public List<ReadAccessModel> getReadAccessModel(){
		return this.readReportViewRepo.findReadReportViewCount();
	}
	
	

	public void logReadAccess(ConversionRateModel conversionRateModel) {
		Date readOn = new Date();
		List<ReadAccess> readAccessList = new ArrayList<>();
		if (conversionRateModel == null) {
			return;
		}
		readAccessList.add(createReadAccessModel(readOn,
				conversionRateModel.getSourceCurrency().getExchangeReferenceRateDetailId()));
		readAccessList.add(createReadAccessModel(readOn,
				conversionRateModel.getTargetCurrency().getExchangeReferenceRateDetailId()));
		readAccessRepo.saveAll(readAccessList);
	}

	private ReadAccess createReadAccessModel(Date readOn, String exchangeReferenceRateDetailId) {
		ReadAccess readAccessSourceCurrency = new ReadAccess();
		readAccessSourceCurrency.setReadBy("ADMIN");
		readAccessSourceCurrency.setReadOn(readOn);
		readAccessSourceCurrency.setExchangeReferenceRateDetailId(exchangeReferenceRateDetailId);
		return readAccessSourceCurrency;
	}
}
