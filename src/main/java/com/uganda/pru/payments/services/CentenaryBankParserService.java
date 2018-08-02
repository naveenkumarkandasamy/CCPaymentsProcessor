package com.uganda.pru.payments.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.uganda.pru.payments.mapper.Mapper;
import com.uganda.pru.payments.model.CentenaryBank;
import com.uganda.pru.payments.model.Workbench;

@Component
public class CentenaryBankParserService extends PaymentParserService {

	static final Logger logger = Logger.getLogger(CentenaryBankParserService.class);

	final private String PRODUCT_DESCRIPTION = "PRU\t*/?\t*EDU.*";

	@Resource(name = "CentenaryToWorkbench")
	private Map<String, String> CentenaryToWorkbench;

	@Autowired
	Mapper workbenchMapper;

	public void parseExcelForCentenaryBank(HttpServletResponse response, MultipartFile pasFile) throws Exception {
		List<CentenaryBank> centenaryBankPaymentsList;
		File centenaryFile = convert(pasFile);
		centenaryBankPaymentsList = xlsxToObjectConvertor.xlsxToJavaObject(centenaryFile, CentenaryBank.class);
		splitCentenaryBankPaymentList(response, centenaryBankPaymentsList);
	}

	public void splitCentenaryBankPaymentList(HttpServletResponse response,
			List<CentenaryBank> centenaryBankPaymentsList) {
		final List<CentenaryBank> ilList = new ArrayList<>();
		final List<CentenaryBank> workbenchCentenaryList = new ArrayList<>();
		List<Workbench> workbenchList = new ArrayList<>();
		centenaryBankPaymentsList.stream().forEach(centenaryPayment -> {
			String comments = "";
			comments = centenaryPayment.getComments();
			centenaryPayment.setBank("Centenary");
			centenaryPayment.setTransactionType("Cash/Cheque");
			if (null != comments && comments.matches(PRODUCT_DESCRIPTION)) {
				ilList.add(centenaryPayment);
			} else {
				workbenchCentenaryList.add(centenaryPayment);
			}
		});
		File file = writeToILFile(ilList, "IlListForCenetnaryBank", CentenaryBank.class);
		doCSVResponse(response, file);
		workbenchList = workbenchMapper.mapToWorkbenchObject(workbenchCentenaryList, CentenaryBank.class,
				CentenaryToWorkbench);
		sendToWorkbench(workbenchList);
	}
}
