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

import com.uganda.pru.payments.services.PaymentParserService;

@RestController
@RequestMapping("/api/converter")
public class PaymentsRestContoller {

	@Autowired
	PaymentParserService paymentParserService;
	/*@Autowired 
	PaymentParserService1 paymentParserService;
	 */
	static final Logger logger = Logger.getLogger(PaymentsRestContoller.class);

	@RequestMapping(value = "/payments", method = RequestMethod.POST)
	@ResponseBody
	public void generateILFileForCashPayments(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "file") final MultipartFile pasFile) {
		try {
			paymentParserService.parseExcelForBarclaysPayments(response, pasFile);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while parsing bank payments file for cash payments " + e);
		}
	}

}
