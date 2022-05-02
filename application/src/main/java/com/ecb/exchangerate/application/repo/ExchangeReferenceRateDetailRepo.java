package com.ecb.exchangerate.application.repo;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Repository;

import com.ecb.exchangerate.application.entity.ExchangeReferenceRateDetail;

@Repository
public interface ExchangeReferenceRateDetailRepo extends JpaRepository<ExchangeReferenceRateDetail, String> {

	public List<ExchangeReferenceRateDetail> findAllByExchangeReferenceRate_EffectiveDateAndCurrencyIdIn(@Temporal(TemporalType.DATE) Date effectiveDate, List<String> currencyList);

	@Query(value = "select distinct b.currencyId from ExchangeReferenceRateDetail b")
	public List<String> findAllCurrencyId();

	@Query(value = "select b.currencyId from ExchangeReferenceRate a INNER JOIN ExchangeReferenceRateDetail b ON a.exchangeReferenceRateId = b.exchangeReferenceRateId where a.effectiveDate = :effectiveDate")
	public List<String> findAllCurrencyIdByEffectiveDate(@Temporal(TemporalType.DATE) Date effectiveDate);
	
	


}




