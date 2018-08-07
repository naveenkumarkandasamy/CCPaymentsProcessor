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
import com.uganda.pru.payments.model.MobileMoney;
import com.uganda.pru.payments.model.ILSoapModel;

@Component
public class MobileMoneyParserService extends PaymentParserService {

	static final Logger logger = Logger.getLogger(MobileMoneyParserService.class);

	final private String PRODUCT_DESCRIPTION = "PRU\t*/?\t*EDU.*";

	@Resource(name = "MobileMoneyToWorkbench")
	private Map<String, String> MobileMoneyToWorkbench;

	@Autowired
	Mapper workbenchMapper;

	public void parseExcelForMobileMoney(HttpServletResponse response, MultipartFile pasFile) throws Exception {
		File mobileMoneyFile = convert(pasFile);
		List<MobileMoney> mobileMoneyList = xlsxToObjectConvertor.xlsxToJavaObject(mobileMoneyFile, MobileMoney.class);
		splitMobileMoneyList(response, mobileMoneyList);
	}

	public void splitMobileMoneyList(HttpServletResponse response, List<MobileMoney> mobileMoneyList) {
		final List<MobileMoney> ilList = new ArrayList<>();
		final List<MobileMoney> workbenchMobileList = new ArrayList<>();
		List<ILSoapModel> ilSoapModelList = new ArrayList<>();
		mobileMoneyList.stream().forEach(mobilePayment -> {
			mobilePayment.setBank("Mobile Money");
			mobilePayment.setTransactionType("Mobile Money");
			String reference = mobilePayment.getReference();
			if (null != reference && reference.matches(PRODUCT_DESCRIPTION)) {
				ilList.add(mobilePayment);
			} else {
				workbenchMobileList.add(mobilePayment);
			}
		});

		File file = writeToILFile(workbenchMobileList, "IlListForMobilePayment", MobileMoney.class);
		doCSVResponse(response, file);
		ilSoapModelList = workbenchMapper.mapToWorkbenchObject(ilList, MobileMoney.class, MobileMoneyToWorkbench);
		System.out.println(ilSoapModelList);
		if(ilSoapModelList!=null)
		{
			for (ILSoapModel workbench : ilSoapModelList) {
				new SoapRequestService().sendSoapRequest(workbench);;
			}
		}
	}
}
