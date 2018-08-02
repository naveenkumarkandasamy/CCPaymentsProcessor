package com.uganda.pru.payments.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uganda.pru.payments.services.BarclaysPaymentParserService;
import com.uganda.pru.payments.services.CentenaryBankParserService;
import com.uganda.pru.payments.services.MobileMoneyParserService;

@RestController
@RequestMapping("/api/converter")
public class PaymentsRestContoller {

	@Autowired
	BarclaysPaymentParserService barclaysPaymentParserService;
	@Autowired
	MobileMoneyParserService mobileMoneyParserService;
	@Autowired
	CentenaryBankParserService centenaryBankParserService;

	static final Logger logger = Logger.getLogger(PaymentsRestContoller.class);

	@RequestMapping(value = "/barclays", method = RequestMethod.POST)
	@ResponseBody
	public void generateILFileForCashPayments(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "file") final MultipartFile pasFile) {
		try {
			barclaysPaymentParserService.parseExcelForBarclaysPayments(response, pasFile);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while parsing bank payments file for cash payments " + e);
		}
	}

	@RequestMapping(value = "/mobileMoney", method = RequestMethod.POST)
	@ResponseBody
	public void generateILFileForMobileMoney(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "file") final MultipartFile pasFile) {
		try {
			mobileMoneyParserService.parseExcelForMobileMoney(response, pasFile);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while parsing payments file for mobile money " + e);
		}
	}

	@RequestMapping(value = "/centenaryUGX", method = RequestMethod.POST)
	@ResponseBody
	public void generateILFileForCentenaryBank(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "file") final MultipartFile pasFile) {
		try {
			centenaryBankParserService.parseExcelForCentenaryBank(response, pasFile);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while parsing bank payments file for Centenary Bank " + e);
		}
	}

}
