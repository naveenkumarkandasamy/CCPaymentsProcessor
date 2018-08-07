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
import com.uganda.pru.payments.model.MobileMoney;
import com.uganda.pru.payments.model.StandardChartered;
import com.uganda.pru.payments.model.ILSoapModel;

@Component
public class StandardCharteredParserService extends PaymentParserService {

	static final Logger logger = Logger.getLogger(StandardCharteredParserService.class);

	final private String PRODUCT_DESCRIPTION = "PRU\t*/?\t*EDU.*";

	@Resource(name = "StandardCharteredToWorkbench")
	private Map<String, String> StandardCharteredToWorkbench;

	@Autowired
	Mapper workbenchMapper;

	public void parseExcelForStandardChartered(HttpServletResponse response, MultipartFile pasFile) throws Exception {
		File standardCharteredFile = convert(pasFile);
		List<StandardChartered> standardCharteredList = xlsxToObjectConvertor.xlsxToJavaObject(standardCharteredFile,
				StandardChartered.class);
		splitStandardCharteredList(response, standardCharteredList);
	}

	public void splitStandardCharteredList(HttpServletResponse response,
			List<StandardChartered> standardCharteredList) {
		final List<StandardChartered> ilList = new ArrayList<>();
		final List<StandardChartered> workbenchStandardChartered = new ArrayList<>();
		List<ILSoapModel> ilSoapModelList = new ArrayList<>();

		// Removing header and footer
		standardCharteredList.remove(standardCharteredList.size() - 1);

		standardCharteredList.stream().forEach(standardCharteredPayment -> {
			String description = standardCharteredPayment.getDescription();
			standardCharteredPayment.setBank("Standard Chartered");
			standardCharteredPayment.setTransactionType("Cash/Cheque");
			if (null != description && description.matches(PRODUCT_DESCRIPTION)) {
				ilList.add(standardCharteredPayment);
			} else {
				workbenchStandardChartered.add(standardCharteredPayment);
			}
		});

		File file = writeToILFile(workbenchStandardChartered, "IlListForStandardChartered", StandardChartered.class);
		doCSVResponse(response, file);
		ilSoapModelList = workbenchMapper.mapToWorkbenchObject(ilList, StandardChartered.class, StandardCharteredToWorkbench);
		System.out.println(ilSoapModelList);
		if(ilSoapModelList!=null)
		{
			for (ILSoapModel ilSoapModel : ilSoapModelList) {
				new SoapRequestService().sendSoapRequest(ilSoapModel);;
			}
		}
		
	}
}
