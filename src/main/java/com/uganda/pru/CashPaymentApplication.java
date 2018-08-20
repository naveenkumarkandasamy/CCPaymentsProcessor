package com.uganda.pru;

import java.io.File;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.ResourceUtils;

import com.uganda.pru.payments.model.InputSchema;

@SpringBootApplication
@ComponentScan(basePackages = { "com.uganda.pru" })
public class CashPaymentApplication {

	public static HashMap<String,HashMap<String,InputSchema>> schemaMap = new HashMap<>();

	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(1);
		pool.setMaxPoolSize(10);
		pool.setWaitForTasksToCompleteOnShutdown(true);
		return pool;
	}

	public static void main(String[] args) {
		SpringApplication.run(CashPaymentApplication.class, args);
		readExcel();

	}

	public static  void readExcel() {
		try {
			File file = ResourceUtils.getFile("classpath:InputSchema.xlsx");			
			Workbook workbook = WorkbookFactory.create(file);
			int noOfSheets = workbook.getNumberOfSheets();
			System.out.println(noOfSheets);
			for (int i = 0; i < noOfSheets; i++) {
				String sheetName = workbook.getSheetAt(i).getSheetName();
				Sheet sheet = workbook.getSheet(sheetName);
				InputSchema inputSchema = null;
				HashMap<String,InputSchema> inputSchemaMap = new HashMap<>();
				int noOfRows = sheet.getPhysicalNumberOfRows();
				for (int j = 1; j < noOfRows; j++) {
					Row row = sheet.getRow(j);

					try {
						inputSchema=new InputSchema();
						inputSchema.setFieldName(row.getCell(0).getStringCellValue());
						inputSchema.setCellNumber((int) row.getCell(1).getNumericCellValue());
						inputSchema.setExpression(row.getCell(2).getStringCellValue());
						inputSchemaMap.put(row.getCell(0).getStringCellValue(),inputSchema);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				schemaMap.put(sheetName.toUpperCase(), inputSchemaMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(schemaMap);
	}
}
