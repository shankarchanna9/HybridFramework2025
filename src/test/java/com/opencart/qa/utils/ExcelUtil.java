package com.opencart.qa.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	static String testDataSheetPath = "./src/test/resources/testdata/OpenCartTestData.xlsx";
	static Workbook book;
	static Sheet sheet;
	
	public static Object [][] getTestDataUsingExcel(String sheetName) {
		Object [][] data =null;
		
		try {
	
			FileInputStream fs = new FileInputStream(testDataSheetPath);
			book = WorkbookFactory.create(fs);
			sheet = book.getSheet(sheetName);
			
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];//[6][2]
			
			
			for(int i=0;i<sheet.getLastRowNum();i++) {
				for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
				
				
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			catch(InvalidFormatException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}						
			
		return data;
		
	}
	
	
}
