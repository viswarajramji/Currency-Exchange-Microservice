package com.ecb.exchangerate.application.data;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportDataModel {

	private String baseCurrency;

	private MultipartFile file;

}
