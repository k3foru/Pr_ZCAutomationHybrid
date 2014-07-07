package com.rishabhSoft.selenium.zeroChaos.commonLibrary;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class XLsWriterUtility {
	
	private static HSSFWorkbook workbook = null;
	
    public static void writeDataInXLs(String filePath, String sheet, int rowNum, int columnNum, String value) throws Exception{

		try{
			FileInputStream fis = new FileInputStream(filePath);
			workbook = new HSSFWorkbook(fis);
			HSSFSheet st = workbook.getSheet(sheet);
			HSSFRow row = st.createRow(rowNum);
			row.createCell(columnNum).setCellValue(value);
			FileOutputStream fileOut =  new FileOutputStream(filePath);
			workbook.write(fileOut);
			fileOut.close();
			System.out.println("You have successfuly write data in Excelsheet.");
		}
		catch ( Exception ex ) {
			ex.getStackTrace();
		    System.out.println(ex.getMessage());
		}
    }

}
