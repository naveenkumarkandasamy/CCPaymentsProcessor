package com.uganda.pru.payments.services;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVWriter;
import com.uganda.pru.CashPaymentApplication;
import com.uganda.pru.payments.model.ILInputModel;
import com.uganda.pru.payments.model.InputSchema;
import com.uganda.pru.payments.utility.ExcelUtility;

@Component
public class PaymentParserService extends GenericPaymentParserService {
	
	@Autowired 
	SoapEnquiryRequest soapEnquiryRequest;
	
	@Autowired 
	SoapRequestService soapRequestService;
	
	@Autowired 
	ExcelUtility excelUtility;
	
	static final Logger logger = Logger.getLogger(PaymentParserService.class);

	final static HashMap<Integer, String[]> csvMap = new HashMap<>();

	public void parseExcelForBarclaysPayments(HttpServletResponse response, MultipartFile pasFile)
			throws Exception {
		File barclaysFile = convert(pasFile);
		String bankName=barclaysFile.getName().split("_")[0];
		splitBarclaysPaymentList(response, barclaysFile, bankName.toUpperCase());
	}

	public void splitBarclaysPaymentList(HttpServletResponse response, File barclaysFile, String bankName) {
		List<ILInputModel> ilList = new ArrayList<>();
		List<String[]> ilBarclayList = new ArrayList<>();
		HashMap<String, Boolean> status = new HashMap<>();
		ILInputModel inputModel;
		try {
			Workbook workbook = WorkbookFactory.create(barclaysFile);
			Sheet sheet = workbook.getSheetAt(0);
			excelUtility.echoAsCSV(sheet, workbook,csvMap);
			Iterator<Row> rowIterator = sheet.rowIterator();
			Row row = null;
			rowIterator.next();
			HashMap<String, InputSchema> inputSchemas = CashPaymentApplication.schemaMap.get(bankName);
			System.out.println("mapo Is" + inputSchemas);
			String description = null;
			while (rowIterator.hasNext()) {
				try {
					inputModel = new ILInputModel();
					row = rowIterator.next();
					inputModel.setBank(bankName);
					inputModel.setTransactionType("Cash/Cheque");
					if (HSSFDateUtil
							.isCellDateFormatted(row.getCell(inputSchemas.get("transactionDate").getCellNumber()))) {
						Date inputDate = row.getCell(inputSchemas.get("transactionDate").getCellNumber())
								.getDateCellValue();
						inputModel.setYear(String.valueOf(inputDate.getYear() + 1900));
						inputModel.setMonth(String.valueOf(inputDate.getMonth() + 1));
						inputModel.setDate(String.valueOf(inputDate.getDate()));
					} else {
						Double inputDate = row.getCell(inputSchemas.get("transactionDate").getCellNumber())
								.getNumericCellValue();
						String formatDate = String.format("%.8f", inputDate);
						Pattern patternDate = Pattern.compile(inputSchemas.get("transactionDate").getExpression());
						Matcher matcherDate = null;
						if (formatDate.contains("."))
							matcherDate = patternDate.matcher(formatDate.subSequence(0, formatDate.indexOf(".")));
						else
							matcherDate = patternDate.matcher(formatDate);
						System.out.println(formatDate + " " + matcherDate.matches());
						while (matcherDate.matches()) {
							inputModel.setYear(matcherDate.group("yyyy"));
							inputModel.setMonth(matcherDate.group("mm"));
							inputModel.setDate(matcherDate.group("dd"));
							break;
						}
					}

					if (row.getCell(inputSchemas.get("productDescription").getCellNumber()) != null) {
						row.getCell(inputSchemas.get("productDescription").getCellNumber())
								.setCellType(CellType.STRING);
						description = row.getCell(inputSchemas.get("productDescription").getCellNumber())
								.getStringCellValue();
						inputModel.setProductDescription(description);
						Pattern patternDescription = Pattern
								.compile(inputSchemas.get("productDescription").getExpression());
						Matcher matcherDescription = patternDescription.matcher(description);
						while (matcherDescription.matches()) {
							inputModel.setCompany(matcherDescription.group("com"));
							inputModel.setPolicyName(matcherDescription.group("pol"));
							inputModel.setPolicyNumber(matcherDescription.group("num"));
							break;
						}
						System.out.println("*****" + inputModel.getPolicyNumber());
					}
					if (row.getCell(inputSchemas.get("creditAmount").getCellNumber()) != null) {
						if (row.getCell(inputSchemas.get("creditAmount").getCellNumber())
								.getCellTypeEnum() == CellType.NUMERIC) {
							inputModel.setCreditAmount(row.getCell(inputSchemas.get("creditAmount").getCellNumber())
									.getNumericCellValue());
						} else {
							String creditAmount = row.getCell(inputSchemas.get("creditAmount").getCellNumber())
									.getStringCellValue();
							if (creditAmount.contains("."))
								inputModel.setCreditAmount(Double.valueOf(
										creditAmount.replaceAll(",", "").substring(0, creditAmount.indexOf("."))));
						}
					}

					if (row.getCell(inputSchemas.get("chequeNumber").getCellNumber()) != null)
						if (row.getCell(inputSchemas.get("chequeNumber").getCellNumber())
								.getCellTypeEnum() == CellType.NUMERIC)
							inputModel.setChequeNumber(String.valueOf(row
									.getCell(inputSchemas.get("chequeNumber").getCellNumber()).getNumericCellValue()));
						else
							inputModel.setChequeNumber(
									row.getCell(inputSchemas.get("chequeNumber").getCellNumber()).getStringCellValue());
					if (null != description && inputModel.getPolicyName() != null) {
						inputModel.setRowNum(row.getRowNum() + "");
						ilList.add(inputModel);
						status.put(row.getRowNum() + "_" + inputModel.getPolicyNumber(), Boolean.TRUE);
					} else {
						status.put(row.getRowNum() + "_" + null+"_"+null, Boolean.FALSE);
						if (ilBarclayList.isEmpty())
							ilBarclayList.add(csvMap.get(0));
						ilBarclayList.add(csvMap.get(row.getRowNum()));
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!ilBarclayList.isEmpty()) {
			try {
				CSVWriter csvWriter = new CSVWriter(new FileWriter("output.csv"));
				csvWriter.writeAll(ilBarclayList);
				csvWriter.close();
				excelUtility.csvToEXCEL("output.csv", "output.xlsx");
				File file = new File("output.csv");
				doCSVResponse(response, file);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		if (ilList != null) {
			for (ILInputModel ilSoapModel : ilList) {
				String customerNumber = soapEnquiryRequest.sendSoapEnquiryRequest(ilSoapModel.getPolicyNumber());
				System.out.println("**************" + customerNumber);
				if (customerNumber == null) {
					status.remove(ilSoapModel.getRowNum() + "_" + ilSoapModel.getPolicyNumber());
					status.put(ilSoapModel.getRowNum() + "_" + ilSoapModel.getPolicyNumber()+"_"+ilSoapModel.getReceiptNumber(), Boolean.FALSE);
				} else {
					String receiptNumber = soapRequestService.sendSoapRequest(ilSoapModel, customerNumber);
					if(receiptNumber==null)
					{
						status.put(ilSoapModel.getRowNum() + "_" + ilSoapModel.getPolicyNumber()+"_"+ilSoapModel.getReceiptNumber(), Boolean.FALSE);
					}
					else
					{
						status.remove(ilSoapModel.getRowNum() + "_" + ilSoapModel.getPolicyNumber());
						ilSoapModel.setReceiptNumber(receiptNumber);
						status.put(ilSoapModel.getRowNum() + "_" + ilSoapModel.getPolicyNumber()+"_"+ilSoapModel.getReceiptNumber(), Boolean.TRUE);

					}
				}
			}

		}
		System.out.println(status);
		excelUtility.updateSheet(status, barclaysFile);
	}
	
}
