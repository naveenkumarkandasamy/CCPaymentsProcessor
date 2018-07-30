package com.uganda.pru.payments.converter;

import java.io.File;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.poiji.bind.Poiji;
import com.uganda.pru.payments.model.CashPayment;

@Component
public class XLSXtoObjectConvertor {
	static final Logger logger = Logger.getLogger(XLSXtoObjectConvertor.class);

	@SuppressWarnings("unchecked")
	public List<CashPayment> xlsxToJavaObject(File file) {
		List<CashPayment> cashPayments = null;
		try {
			logger.debug("converting xlsx to POJO");
			cashPayments = Poiji.fromExcel(file, CashPayment.class);
		} catch (Exception e) {
			logger.error("error while reading from XLSX file and converting into Object " + e);
			e.printStackTrace();
		}
		return cashPayments;
	}

}
