package com.uganda.pru.payments.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@PropertySource("classpath:cashPayment.properties")
public class GenericPaymentParserService {
	static final Logger logger = Logger.getLogger(GenericPaymentParserService.class);

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

		/*
		 * if (!file.delete()) { logger.error("Unable to delete file"); }
		 */
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
