package com.uganda.pru.payments.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:workbench-connection.properties")
public class HttpClientService {

	static final Logger logger = Logger.getLogger(HttpClientService.class);


	@Value("${url}")
	private String url;

	@Value("${workbenchUsername}")
	private String workbenchUsername;

	@Value("${workbenchPassword}")
	private String workbenchPassword;

	public void sendJsonToWorkbench(String jsonWorkbenchData) {

		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);

		StringEntity entity;
		try {
			entity = new StringEntity(jsonWorkbenchData);

			httpPost.setEntity(entity);
			httpPost.setHeader("Content-type", "application/json");

			UsernamePasswordCredentials creds = new UsernamePasswordCredentials(workbenchUsername,workbenchPassword);

			httpPost.addHeader(new BasicScheme().authenticate(creds, httpPost, null));
			CloseableHttpResponse response = client.execute(httpPost);

			logger.debug("Resonse from workbench is " + response);
			System.out.println(response);
			client.close();

		} catch (UnsupportedEncodingException e) {
			logger.error("Unsupported Data Encoding while sending request to workbench " + e.getMessage());
			e.printStackTrace();
		} catch (AuthenticationException e) {
			logger.error("Error in authentication while sending request to workbench " + e.getMessage());
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			logger.error("Error while executing request for workbench server " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Error while fetching response from workbench server " + e.getMessage());
			e.printStackTrace();
		}
	}
}
