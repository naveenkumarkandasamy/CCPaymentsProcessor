package com.uganda.pru.payments.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
import com.uganda.pru.payments.model.CashPayment;



@Component
@PropertySource("classpath:cashPayment.properties")
public class CashPaymentParserService {
	@Autowired
	XLSXtoObjectConvertor xlsxToObjectConvertor;	
	
	@Autowired
	ObjectToCSVConvertor objectToCSVConvertor;	
	
	@Value( "${processDefinitionKey}" )
	private String processDefinitionKey;
	@Value( "${processDefinitionValue}" )
	private String processDefinitionValue;
	@Value( "${businessKey}" )
	private String businessKey;
	@Value( "${businessKeyValue}" )
	private String businessKeyValue;
	@Value( "${variablesKey}" )
	private String variablesKey;
	@Value( "${jsonObjectName}" )
	private String jsonObjectName;
	@Value( "${jsonObjectValue}" )
	private String jsonObjectValue;
	
	
	static final Logger logger = Logger.getLogger(CashPaymentParserService.class);
	
	public void parseExcel(HttpServletResponse response, MultipartFile pasFile) throws Exception {
		List<CashPayment> cashPaymentsList = xlsxToObjectConvertor.xlsxToJavaObject(convert(pasFile));
		parseCashPaymentList(response, cashPaymentsList);
	}	

	public void parseCashPaymentList(HttpServletResponse response, List<CashPayment> cashPaymentsList) {

		final List<CashPayment> ilList = new ArrayList<>();
		final List<CashPayment> workbenchList = new ArrayList<>();
		
		cashPaymentsList.stream()
		.forEach(
            cashPayment -> {
            	String description = null;
				try {
					description = cashPayment.getDescription();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
            	if(description.startsWith("PRU EDU")||description.startsWith("PRU/EDU")||description.startsWith("PRUEDU") ){
            //	if(description.matches("PRU\t*\\?\t*EDU.*")){
            		ilList.add(cashPayment);
            	}
            	else{
            		workbenchList.add(cashPayment);
            	}
            }
				);
		
	writeToILFile(ilList,"ilList");
	sendToWorkbench(workbenchList);
		
		
	}
	public void writeToILFile(List<CashPayment> cashPaymentList, String filename) {
		File file;
		file = objectToCSVConvertor.convertObjectToCSV(cashPaymentList, filename);
	}

	
	private File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		Boolean isFileNotCreated = convFile.createNewFile();
		if (isFileNotCreated) {
			logger.error("Unable to convert form mulitpart to file");
			return null;
		}
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}	
	
	
	public void sendToWorkbench(List<CashPayment> workbenchList){
		String workbenchData = null;
		JSONObject cashPaymentObject = null;
		
		//convert cashPayment list to json array		
		JSONArray cashPaymentArray = convertBankPaymentToJsonArray(workbenchList);
		
		try {
			//convert json array to workbench json format
			for(int i =0; i<cashPaymentArray.length(); i++){
				cashPaymentObject = cashPaymentArray.getJSONObject(i);
				workbenchData = convertJsonObjToWorkbenchObj(cashPaymentObject);
				logger.debug("Workbench Data "+workbenchData);
				System.out.println(workbenchData);
				//send to esb
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

	private JSONArray convertBankPaymentToJsonArray(List<CashPayment> workbenchList) {
		String bankPaymentJsonList = null;
		JSONArray bankPaymentArray = null;
		ObjectMapper objectMapper = new ObjectMapper();
		 try {
			bankPaymentJsonList = objectMapper.writeValueAsString(workbenchList);
			 bankPaymentArray = new JSONArray(bankPaymentJsonList);
		} catch (JsonProcessingException | JSONException e) {
			e.printStackTrace();
		}
		  return bankPaymentArray;
	}

	public String convertJsonObjToWorkbenchObj(JSONObject jsonObj){
		JSONArray workbenchArray;
		JSONObject workbenchObj;
		String name;
		String value;
		
		try{
			workbenchArray	= new JSONArray();
			Iterator keys = jsonObj.keys();
			while(keys.hasNext()){
				workbenchObj = new JSONObject();
			    name = (String) keys.next();
				value = jsonObj.get(name).equals(null)?"null":(String) jsonObj.get(name);
				workbenchObj.put("name",name);
				workbenchObj.put("value", value);
				workbenchArray.put(workbenchObj);
			}	
			
			JSONObject cashPaymentWorkbenchObject = new JSONObject();
			cashPaymentWorkbenchObject.put(processDefinitionKey, processDefinitionValue);
			cashPaymentWorkbenchObject.put(businessKey, businessKeyValue);
			cashPaymentWorkbenchObject.put(variablesKey, workbenchArray);
			
			return cashPaymentWorkbenchObject.toString();
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return null;

	}

}
