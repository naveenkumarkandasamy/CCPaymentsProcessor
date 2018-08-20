package com.uganda.pru.payments.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelUtility {

	public void updateSheet(HashMap<String, Boolean> status, File barclaysFile) {
		HSSFWorkbook workbook = null;
		try {
			FileInputStream file = new FileInputStream(barclaysFile);
			workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(0);
			int cellNumtoAdd = row.getPhysicalNumberOfCells();
			row.createCell(cellNumtoAdd).setCellValue("Status");
			row.createCell(cellNumtoAdd + 1).setCellValue("Receipt Number");

			for (Map.Entry<String, Boolean> entry : status.entrySet()) {
				Integer rowNum = Integer.valueOf(entry.getKey().split("_")[0]);
				String receiptNumber = entry.getKey().split("_")[2];
				Boolean value = entry.getValue();
				row = sheet.getRow(rowNum);
				row.createCell(cellNumtoAdd).setCellValue(value);
				row.createCell(cellNumtoAdd + 1).setCellValue(receiptNumber);
			}
			file.close();
			FileOutputStream outFile = new FileOutputStream(new File("updated_" + barclaysFile.getName()));
			workbook.write(outFile);
			// workbook.close();
			outFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (workbook != null)
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public void echoAsCSV(Sheet sheet, Workbook workbook, HashMap<Integer, String[]> csvMap) {
		Row row = null;
		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			StringBuilder format = new StringBuilder();
			for (int j = 0; row != null && j < row.getLastCellNum(); j++) {
				if (row.getCell(j) != null && row.getCell(j).getCellTypeEnum() == CellType.FORMULA) {
					FormulaEvaluator formulaEval = workbook.getCreationHelper().createFormulaEvaluator();
					CellValue c = formulaEval.evaluate(row.getCell(j));
					format.append(c.getNumberValue() + "#@");
				} else
					format.append(row.getCell(j) + "#@");
			}
			String[] builder = format.toString().split("#@");
			csvMap.put(i, builder);
		}
	}

	public  void csvToEXCEL(String csvFileName, String excelFileName) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFileName)));
		HSSFWorkbook myWorkBook = new HSSFWorkbook();
		FileOutputStream writer = new FileOutputStream(new File(excelFileName));
		HSSFSheet mySheet = myWorkBook.createSheet();
		String line = "";
		int rowNo = 0;
		while ((line = reader.readLine()) != null) {
			String[] columns = line.split("\",\"");
			HSSFRow myRow = mySheet.createRow(rowNo);
			for (int i = 0; i < columns.length; i++) {
				HSSFCell myCell = myRow.createCell(i);
				myCell.setCellValue(columns[i].replaceAll("\"", ""));
			}
			rowNo++;
		}
		myWorkBook.write(writer);
		writer.close();
		reader.close();
		myWorkBook.close();
	}
}
