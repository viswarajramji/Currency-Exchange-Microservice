package com.ecb.exchangerate.application.processor;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ecb.exchangerate.application.configuration.ProjectConstants;
import com.ecb.exchangerate.application.data.ImportDataModel;
import com.ecb.exchangerate.application.data.ImportResponseModel;
import com.ecb.exchangerate.application.entity.ExchangeReferenceRate;
import com.ecb.exchangerate.application.entity.ExchangeReferenceRateDetail;
import com.ecb.exchangerate.application.exception.ServiceApplicationException;
import com.ecb.exchangerate.application.utils.DataUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Scope("request")
public class CsvDataProcessorService extends AbstractDataProcessorService {

	@Override
	public ImportResponseModel run(ImportDataModel importDataModel) {

		this.validateCurrency(importDataModel.getBaseCurrency());

		return processFileContent(importDataModel);
	}

	private ImportResponseModel processFileContent(ImportDataModel importDataModel) {
		InputStream is = null;
		Reader reader = null;
		CSVParser parser = null;
		try {

			if (!"text/csv".equalsIgnoreCase(importDataModel.getFile().getContentType())) {
				throw new ServiceApplicationException("IMPORT_SERVICE_UNSUPPORT_FILE_FORMAT");
			}
			is = importDataModel.getFile().getInputStream();
			reader = getReader(is);
			parser = getCSVParser(reader);
			return processParserValues(parser, importDataModel);

		} catch (IOException ex) {
			log.error("Exception Occured : {}", ex.toString());
			throw new ServiceApplicationException("APPLICATION_ERROR_OCCURED",HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			closeInputStream(is);
			closeReader(reader);
			closeParser(parser);
		}
	}

	private ImportResponseModel processParserValues(CSVParser parser, ImportDataModel importDataModel) {

		Map<String, Integer> headerVsIndex = parser.getHeaderMap();

		if (headerVsIndex == null || headerVsIndex.isEmpty()) {
			throw new ServiceApplicationException("NO_DATA_IMPORT");
		}

		if (!headerVsIndex.containsKey(ProjectConstants.DATE_FIELD)) {
			throw new ServiceApplicationException("NO_DATE_FIELD");
		}

		headerVsIndex.remove(ProjectConstants.DATE_FIELD);

		return processFieldValues(headerVsIndex, parser, importDataModel);

	}

	private ImportResponseModel processFieldValues(Map<String, Integer> headerVsIndex, CSVParser parser,
			ImportDataModel importDataModel) {
		List<ExchangeReferenceRate> exchangeReferenceRates = new ArrayList<>();
		parser.forEach(csvRow -> {
			String dateString = csvRow.get(ProjectConstants.DATE_FIELD);
			if (dateString == null || dateString.trim().isEmpty()) {
				throw new ServiceApplicationException("INVALID_DATE_VALUE");
			}

			Date dateField = DataUtils.convertStringToDate(dateString);
			ExchangeReferenceRate exchangeReferenceRate = new ExchangeReferenceRate();
			exchangeReferenceRate.setBaseCurrencyId(importDataModel.getBaseCurrency());
			exchangeReferenceRate.setEffectiveDate(dateField);
			exchangeReferenceRate.setExchangeReferenceRateDetails(new ArrayList<>());
			processExchangeRateDetails(csvRow, headerVsIndex, exchangeReferenceRate);
			exchangeReferenceRates.add(exchangeReferenceRate);
		});
		return persistExchangeReferenceRates(exchangeReferenceRates);
	}

	private void processExchangeRateDetails(CSVRecord csvRow, Map<String, Integer> headerVsIndex,
			ExchangeReferenceRate exchangeReferenceRate) {

		for (String currencyCode : headerVsIndex.keySet()) {
			String rate = csvRow.get(currencyCode);
			if (rate == null || rate.trim().isEmpty() || "N/A".equals(rate.trim())) {
				continue;
			}
			currencyCode = DataUtils.trimValue(currencyCode);
			rate = DataUtils.trimValue(rate);
			validateCurrency(currencyCode);
			ExchangeReferenceRateDetail exchangeReferenceRateDetail = new ExchangeReferenceRateDetail();
			exchangeReferenceRateDetail.setExchangeReferenceRateId(exchangeReferenceRate.getExchangeReferenceRateId());
			exchangeReferenceRateDetail.setCurrencyId(currencyCode);
			exchangeReferenceRateDetail.setExchangeRate(DataUtils.handleInfinityOrNan(Double.valueOf(rate)));
			exchangeReferenceRate.getExchangeReferenceRateDetails().add(exchangeReferenceRateDetail);
		}
		
		addBaseCurrencyToExchangeReferenceRateDetail(exchangeReferenceRate);
	}
	
	private void addBaseCurrencyToExchangeReferenceRateDetail(ExchangeReferenceRate exchangeReferenceRate) {
		ExchangeReferenceRateDetail exchangeReferenceRateDetail = new ExchangeReferenceRateDetail();
		exchangeReferenceRateDetail.setExchangeReferenceRateId(exchangeReferenceRate.getExchangeReferenceRateId());
		exchangeReferenceRateDetail.setCurrencyId(exchangeReferenceRate.getBaseCurrencyId());
		exchangeReferenceRateDetail.setExchangeRate(Double.valueOf(1.000000));
		exchangeReferenceRate.getExchangeReferenceRateDetails().add(exchangeReferenceRateDetail);
	}

	private ImportResponseModel persistExchangeReferenceRates(List<ExchangeReferenceRate> exchangeReferenceRates) {
		List<Date> effectiveDates = exchangeReferenceRates.stream().map(ExchangeReferenceRate::getEffectiveDate).collect(Collectors.toList());
		importDataProcessorService.deleteExchangeReferenceRateByEffectiveDates(effectiveDates);
		importDataProcessorService.saveAllExchangeReferenceRates(exchangeReferenceRates);
		return new ImportResponseModel(exchangeReferenceRates.size());
	}


	protected CSVParser getCSVParser(Reader reader) throws IOException {
		return new CSVParser(reader, CSVFormat.EXCEL.withHeader());
	}
	private Reader getReader(InputStream is){
		return new InputStreamReader(is, StandardCharsets.UTF_8);
	}

	private void closeInputStream(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException ex) {
				log.error("Cannot close InputStream");
			}
		}
	}

	private void closeReader(Reader reader) {
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException ex) {
				log.error("Cannot close reader");
			}
		}
	}

	private void closeParser(CSVParser parser) {
		if (parser != null) {
			try {
				parser.close();
			} catch (IOException ex) {
				log.error("Cannot close CSVParser");
			}
		}
	}

}
