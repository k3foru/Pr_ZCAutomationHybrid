package com.rishabhSoft.selenium.zeroChaos.commonLibrary;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.rishabhSoft.selenium.zeroChaos.drivers.SetObjectProperties;
import com.rishabhSoft.selenium.zeroChaos.drivers.TestNG_WebDriver;

/** @author Abhishek Singh
 * To write Data in to Excel sheet.
 * */

public class XLsReportUtility{
	
	private static HSSFWorkbook workbook = null;
	/**
	 *  	
	 * @param tsetCaseID
	 * @param testCase
	 * @param summary
	 * @param desc
	 * @param status
	 * @param tester
	 * @throws Exception 
	 */
    public static void writeDataInXLs(int TestCaseNo, String tsetCaseID, String summary, String desc, String status, String tester) throws Exception{

		try{
			String filename = SetObjectProperties.appConfig.getPropertyValue("TestReportFile");
			FileInputStream fis = new FileInputStream(filename);
			workbook = new HSSFWorkbook(fis);
			HSSFSheet st = workbook.getSheet(SetObjectProperties.appConfig.getPropertyValue("TestReport_SheetName"));
			HSSFRow row = st.createRow(TestNG_WebDriver.testCaseNo);
			row.createCell(0).setCellValue(TestCaseNo);
			row.createCell(1).setCellValue(tsetCaseID);
			row.createCell(2).setCellValue(summary);
			row.createCell(3).setCellValue(desc);
			row.createCell(4).setCellValue(DateFormatUtilities.getCurrentDateTimeFormatted());
			row.createCell(5).setCellValue(status);
			row.createCell(6).setCellValue(tester);
			FileOutputStream fileOut =  new FileOutputStream(filename);
			workbook.write(fileOut);
			fileOut.close();
			System.out.println("Your excel file has been generated!");
		}
		catch ( Exception ex ) {
			ex.getStackTrace();
		    System.out.println(ex.getMessage());
		}
    }
}