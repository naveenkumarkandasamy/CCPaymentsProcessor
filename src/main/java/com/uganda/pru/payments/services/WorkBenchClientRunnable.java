package com.uganda.pru.payments.services;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.uganda.pru.payments.converter.ObjectToCSVConvertor;
import com.uganda.pru.payments.converter.XLSXtoObjectConvertor;

@Component
public class WorkBenchClientRunnable implements Runnable {

	private String workBenchData;
	private HttpClientService httpClientService;
	private PaymentParserService paymentParserService;

	public void setWorkBenchData(String workBenchData) {
		this.workBenchData = workBenchData;
	}

	public void setHttpClient(HttpClientService httpClientService) {
		this.httpClientService = httpClientService;
	}

	@Override
	public void run() {

		System.out.println(workBenchData);

		httpClientService.sendJsonToWorkbench(workBenchData);

	}
}