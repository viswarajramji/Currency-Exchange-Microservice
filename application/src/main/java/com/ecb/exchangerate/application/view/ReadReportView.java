package com.ecb.exchangerate.application.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class ReadReportView {
	
	@Id
	@Column(name = "READ_ACCESS_ID", length = 36)
	private String exchangeReferenceRateId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "READ_ON", nullable = false)
	private Date readOn;
	
	@Column(name = "READ_BY", nullable = false)
	private String readBy;
	
	@Column(name = "CURRENCY_ID", nullable = false, length = 3)
	private String currencyId;
	
	@Temporal(TemporalType.DATE) 
	@Column(name = "EFFECTIVE_DATE", nullable = false, unique = true)
	private Date effectiveDate;

}

