package com.ecb.exchangerate.application.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecb.exchangerate.application.data.ReadAccessModel;
import com.ecb.exchangerate.application.view.ReadReportView;

@Repository
public interface ReadReportViewRepo extends JpaRepository<ReadReportView, String> {

	@Query(value = "select new com.ecb.exchangerate.application.data.ReadAccessModel(b.currencyId as currencyId , count(b) as readCount) from ReadReportView b group by b.currencyId")
	public List<ReadAccessModel> findReadReportViewCount();

	@Query(value = "select new com.ecb.exchangerate.application.data.ReadAccessModel(b.currencyId as currencyId , count(b) as readCount) from ReadReportView b where b.effectiveDate = :effectiveDate group by b.currencyId ")
	public List<ReadAccessModel> findReadReportViewCountByEffectiveDate(Date effectiveDate);

}