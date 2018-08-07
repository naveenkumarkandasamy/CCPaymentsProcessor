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

import com.uganda.pru.payments.converter.XLSXtoObjectConvertor;
import com.uganda.pru.payments.mapper.Mapper;
import com.uganda.pru.payments.model.Barclays;
import com.uganda.pru.payments.model.ILSoapModel;

@Component
public class BarclaysPaymentParserService extends PaymentParserService {

	@Resource(name = "BarclaysToWorkbench")
	private Map<String, String> BarclaysToWorkbench;

	@Autowired
	Mapper workbenchMapper;

	@Autowired
	XLSXtoObjectConvertor xlsxToObjectConvertor;

	static final Logger logger = Logger.getLogger(BarclaysPaymentParserService.class);

	final private String PRODUCT_DESCRIPTION = "PRU\t*/?\t*EDU.*";

	public void parseExcelForBarclaysPayments(HttpServletResponse response, MultipartFile pasFile) throws Exception {
		File barclaysFile = convert(pasFile);
		List<Barclays> barclaysPaymentsList = xlsxToObjectConvertor.xlsxToJavaObject(barclaysFile, Barclays.class);
		splitBarclaysPaymentList(response, barclaysPaymentsList);
	}

	public void splitBarclaysPaymentList(HttpServletResponse response, List<Barclays> barclaysList) {
		final List<Barclays> ilList = new ArrayList<>();
		final List<Barclays> ilBarclayList = new ArrayList<>();
		List<ILSoapModel> ilSoapModelList = new ArrayList<>();
		barclaysList.stream().forEach(barclayPayment -> {
			String description = "";
			barclayPayment.setBank("Barclays");
			barclayPayment.setTransactionType("Cash/Cheque");
			description = barclayPayment.getDescription();
			if (null != description && description.matches(PRODUCT_DESCRIPTION)) {
				ilList.add(barclayPayment);
			} else {
				ilBarclayList.add(barclayPayment);
			}
		});
		File file = writeToILFile(ilBarclayList, "IlListForBarclaysPayment", Barclays.class);
		doCSVResponse(response, file);
		ilSoapModelList = workbenchMapper.mapToWorkbenchObject(ilList, Barclays.class, BarclaysToWorkbench);
		System.out.println(ilSoapModelList);
		if(ilSoapModelList!=null)
		{
			for (ILSoapModel ilSoapModel : ilSoapModelList) {
				new SoapRequestService().sendSoapRequest(ilSoapModel);;
			}
		}
		
	}

}
