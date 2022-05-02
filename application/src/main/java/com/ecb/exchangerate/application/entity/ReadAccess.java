package com.ecb.exchangerate.application.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class ReadAccess {

	@Id
	@Column(name = "READ_ACCESS_ID", length = 36)
	private String exchangeReferenceRateId = UUID.randomUUID().toString();

	@Column(name = "EXCHANGE_REFERENCE_RATE_DETAIL_ID", nullable = false, length = 36)
	private String exchangeReferenceRateDetailId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "READ_ON", nullable = false)
	private Date readOn;
	
	@Column(name = "READ_BY", nullable = false)
	private String readBy;
	
	@ManyToOne
	@JoinColumn(name = "EXCHANGE_REFERENCE_RATE_DETAIL_ID", referencedColumnName = "EXCHANGE_REFERENCE_RATE_DETAIL_ID", updatable = false, insertable = false)
	private ExchangeReferenceRateDetail exchangeReferenceRateDetail;

}
