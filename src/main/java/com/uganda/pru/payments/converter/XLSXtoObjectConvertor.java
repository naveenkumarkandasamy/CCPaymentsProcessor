package com.uganda.pru.payments.converter;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.poiji.bind.Poiji;

@Component
public class XLSXtoObjectConvertor {
	static final Logger logger = Logger.getLogger(XLSXtoObjectConvertor.class);

	@SuppressWarnings("unchecked")
	public <T> List<T> xlsxToJavaObject(File file, Class<T> classType) {
		List<T> paymentsList = null;
		try {
			logger.debug("converting xlsx to POJO");
			paymentsList = (List<T>) Poiji.fromExcel(file, classType);
		} catch (Exception e) {
			logger.error("error while reading from XLSX file and converting into Object " + e);
			e.printStackTrace();
		}
		return paymentsList;
	}

}
