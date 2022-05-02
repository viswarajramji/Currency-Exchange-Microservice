package com.ecb.exchangerate.application.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = { @UniqueConstraint(name = "uniqueExchangeReferenceAndCurrency", columnNames = {"EXCHANGE_REFERENCE_RATE_ID", "CURRENCY_ID" }) })
public class ExchangeReferenceRateDetail {

	@Id
	@Column(name = "EXCHANGE_REFERENCE_RATE_DETAIL_ID", nullable = false, length = 36)
	private String exchangeReferenceRateDetailId = UUID.randomUUID().toString();

	@Column(name = "EXCHANGE_REFERENCE_RATE_ID", nullable = false, length = 36)
	private String exchangeReferenceRateId;

	@Column(name = "CURRENCY_ID", nullable = false, length = 3)
	private String currencyId;

	@Column(name = "EXCHANGE_RATE", nullable = false)
	private Double exchangeRate;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "EXCHANGE_REFERENCE_RATE_ID", referencedColumnName = "EXCHANGE_REFERENCE_RATE_ID", updatable = false, insertable = false)
	private ExchangeReferenceRate exchangeReferenceRate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "exchangeReferenceRateDetail", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReadAccess> readAccessDetails;

}
