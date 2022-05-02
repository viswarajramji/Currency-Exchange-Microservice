package com.ecb.exchangerate.application.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = { @UniqueConstraint(name = "uniqueEffectiveDate", columnNames = {"EFFECTIVE_DATE" }) })
public class ExchangeReferenceRate {

	@Id
	@Column(name = "EXCHANGE_REFERENCE_RATE_ID", length = 36)
	private String exchangeReferenceRateId = UUID.randomUUID().toString();
	
	@Temporal(TemporalType.DATE) 
	@Column(name = "EFFECTIVE_DATE", nullable = false, unique = true)
	private Date effectiveDate;

	@Column(name = "BASE_CURRENCY_ID", nullable = false, length = 3)
	private String baseCurrencyId;

	@OneToMany(mappedBy = "exchangeReferenceRate", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ExchangeReferenceRateDetail> exchangeReferenceRateDetails;

}
