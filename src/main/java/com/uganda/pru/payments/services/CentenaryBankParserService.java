package com.uganda.pru.payments.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
public class CentenaryBankParserService extends PaymentParserService{

	static final Logger logger = Logger.getLogger(CentenaryBankParserService.class);

	final private String PRODUCT_DESCRIPTION = "PRU\t*/?\t*EDU.*";

	@Resource(name = "CentenaryToWorkbench")
	private Map<String, String> CentenaryToWorkbench;

	@Autowired
	Mapper workbenchMapper;

	public void parseExcelForCentenaryBank(HttpServletResponse response, MultipartFile pasFile) throws Exception{
		List<CentenaryBank> centenaryBankPaymentsList;
		centenaryBankPaymentsList = xlsxToObjectConvertor.xlsxToJavaObject(convert(pasFile), CentenaryBank.class);
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
			if(null!= comments && comments.matches(PRODUCT_DESCRIPTION)){
				ilList.add(centenaryPayment);
			} else {
				centenaryPayment.setBank("Centenary");
				centenaryPayment.setTransactionType("Cash/Cheque");
				workbenchCentenaryList.add(centenaryPayment);
			}
		});
		File file = writeToILFile(ilList, "IlListForCenetnaryBank", CentenaryBank.class);
		doCSVResponse(response, file);
		workbenchList = workbenchMapper.mapToWorkbenchObject(workbenchCentenaryList, CentenaryBank.class, CentenaryToWorkbench);
		sendToWorkbench(workbenchList);
	}

	private File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		Boolean isFileNotCreated = convFile.createNewFile();
		if (isFileNotCreated) {
			logger.error("Unable to convert from mulitpart to file");
			return null;
		}
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
}
