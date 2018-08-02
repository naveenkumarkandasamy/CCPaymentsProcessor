package com.uganda.pru.payments.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uganda.pru.payments.converter.ObjectToCSVConvertor;
import com.uganda.pru.payments.converter.XLSXtoObjectConvertor;

@Component
@PropertySource("classpath:cashPayment.properties")
public class PaymentParserService {
	static final Logger logger = Logger.getLogger(PaymentParserService.class);

	@Autowired
	HttpClientService httpClientService;
	@Autowired
	XLSXtoObjectConvertor xlsxToObjectConvertor;

	@Autowired
	ObjectToCSVConvertor objectToCSVConvertor;

	@Value("${processDefinitionKey}")
	private String processDefinitionKey;
	@Value("${processDefinitionValue}")
	private String processDefinitionValue;
	@Value("${businessKey}")
	private String businessKey;
	@Value("${businessKeyValue}")
	private String businessKeyValue;
	@Value("${variablesKey}")
	private String variablesKey;
	@Value("${jsonObjectName}")
	private String jsonObjectName;
	@Value("${jsonObjectValue}")
	private String jsonObjectValue;

	public <T> File writeToILFile(List<T> paymentList, String filename, Class<T> classType) {
		File file;
		file = objectToCSVConvertor.convertObjectToCSV(paymentList, filename, classType);
		return file;
	}

	public <T> void sendToWorkbench(List<T> paymentList) {
		String workbenchData = null;
		JSONObject paymentObject = null;

		// convert payment list to json array
		JSONArray jsonPaymentArray = convertPaymentListToJsonArray(paymentList);

		try {

			// convert json array to workbench json format
			for (int i = 0; i < jsonPaymentArray.length(); i++) {

				paymentObject = jsonPaymentArray.getJSONObject(i);
				workbenchData = convertJsonObjToWorkbenchObj(paymentObject);

				logger.debug("Workbench Data " + workbenchData);
				System.out.println(workbenchData);

				// send to workbench
				httpClientService.sendJsonToWorkbench(workbenchData);
			}
		} catch (JSONException e) {
			logger.error("Error parsing the list data to workbench json format data " + e.getMessage());
		}

	}

	private <T> JSONArray convertPaymentListToJsonArray(List<T> workbenchList) {
		String bankPaymentJsonList = null;
		JSONArray bankPaymentArray = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			bankPaymentJsonList = objectMapper.writeValueAsString(workbenchList);
			bankPaymentArray = new JSONArray(bankPaymentJsonList);
		} catch (JsonProcessingException | JSONException e) {
			logger.error("Error converting the cash payments lists to json array " + e.getMessage());
		}
		return bankPaymentArray;
	}

	public String convertJsonObjToWorkbenchObj(JSONObject jsonObj) {
		JSONArray workbenchArray;
		JSONObject workbenchObj;
		String name;
		String value;

		try {
			workbenchArray = new JSONArray();
			Iterator keys = jsonObj.keys();
			while (keys.hasNext()) {
				workbenchObj = new JSONObject();
				name = (String) keys.next();
				value = jsonObj.get(name).equals(null) ? "null" : (String) jsonObj.get(name);
				workbenchObj.put("name", name);
				workbenchObj.put("value", value);
				workbenchArray.put(workbenchObj);
			}

			JSONObject cashPaymentWorkbenchObject = new JSONObject();
			cashPaymentWorkbenchObject.put(processDefinitionKey, processDefinitionValue);
			cashPaymentWorkbenchObject.put(businessKey, businessKeyValue);
			cashPaymentWorkbenchObject.put(variablesKey, workbenchArray);

			return cashPaymentWorkbenchObject.toString();
		} catch (JSONException e) {
			logger.error("Error converting the json object to workbench json format " + e.getMessage());
		}
		return null;
	}

	public void doCSVResponse(HttpServletResponse response, File file) {
		byte[] contents = fileToByte(file);
		String responseFile = "response.csv";
		response.setContentType("text/plain");
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", responseFile);
		response.setHeader(headerKey, headerValue);
		OutputStream os;
		try {
			os = response.getOutputStream();
			os.write(contents);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
		}

		response.setContentLength(contents.length);
		response.setHeader("Content-Disposition", "attachment;filename= " + responseFile);

		if (!file.delete()) {
			logger.error("Unable to delete file");
		}
	}

	public byte[] fileToByte(File file) {
		FileInputStream fileInputStream = null;

		byte[] bFile = new byte[(int) file.length()];

		try {
			fileInputStream = new FileInputStream(file);
			int count = fileInputStream.read(bFile);
			fileInputStream.close();
			if (count > 0)
				return bFile;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while reading file inputStream" + e);
		} finally {
			if (fileInputStream != null)
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		return bFile;
	}

	public File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

}
