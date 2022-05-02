package com.ecb.exchangerate.application.repo;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.ecb.exchangerate.application.entity.ExchangeReferenceRate;

@Repository
public interface ExchangeReferenceRateRepo extends JpaRepository<ExchangeReferenceRate, String> {

	@Transactional
	@Modifying
	void deleteByEffectiveDateIn(List<Date> effectiveDate);
	

}
