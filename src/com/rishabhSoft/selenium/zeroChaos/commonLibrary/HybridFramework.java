package com.rishabhSoft.selenium.zeroChaos.commonLibrary;

import org.openqa.selenium.NoSuchElementException;

import com.rishabhSoft.selenium.zeroChaos.drivers.Keywords;

/**
 * Execute test suite from Excel sheet.
 * 
 * @author Abhishek Singh
 */

public class HybridFramework extends SuiteReaderWriter{
		
	// Declared global variables.
	public static String vKeyword, vObject, vTetsData;
	
	//public static void main(String[] args) throws Exception {
	public static void executeTestSuite(String xlPath) throws Exception {

		String vTS_res = null, vTC_res = null, vTD_res, vTS_error, vT_flag;
		
		// Read Test Cases and Test Steps from XL sheet.
		xlRead_TC(xlPath, "TestCases");  		// Read Test Case data
		xlRead_TS(xlPath, "TestSteps");     	// Read Test Step data
				
		for (int j = 1; j < xTCRows; j++) { 		// Go to each row in the TC Sheet and see if the execute flag is Y
			if (xTCdata[j][getCell(columns_TC, "Runmode")].equals("Y")) {  			//If y then go to each row in TS Sheet
				// Read the Test Data of Test Case which have Runmode is 'Y'.
				xlRead_TD(xlPath, xTCdata[j][getCell(columns_TC, "TCID")]);
				vTC_res = "PASSED";
				// Do Iteration for All Test Data which have Runmode is 'Y'.
				for(int k = 1; k < xTDdata.length; k++){
					// Perform All steps of corresponding Test Case.
					for (int i = 1; i < xTSRows; i++){
						vTD_res = "PASSED";
						if(xTDdata[k][getCell(columns_TD, "Runmode")].equals("Y")){
							if (xTCdata[j][getCell(columns_TC, "TCID")].equals(xTSdata[i][getCell(columns_TS, "TCID")])){	// and see if TCID's match						
								vTS_res = "PASSED";
								vTS_error = "No Error";
								vT_flag = "PASSED";
								vTetsData = null;
								vKeyword = xTSdata[i][getCell(columns_TS, "Keyword")];
								vObject = xTSdata[i][getCell(columns_TS, "Object")];
								vTetsData = xTSdata[i][getCell(columns_TS, "Data")];
								// Read the Data from Data Sheet corresponding Test case.
								String []vDataSplit = vTetsData.split("\\|");
								if(vDataSplit[0].equals("Sheet")){
									vTetsData = vDataSplit[1];
									vTetsData = xTDdata[k][getCell(columns_TD, vTetsData)];
								}
								else if(vDataSplit[0].equals("Propr")){
									vTetsData = readProperties_TD(vDataSplit[1]);
								}
								else{
									vTetsData = vDataSplit[0];
								}
								// Identify the keyword and execute the corresponding function
								try {
									vT_flag = Keywords.keywordExecutor();
									if (vT_flag.equals("FAILED")) {
										vTS_res = "FAILED";
										vTS_error = "Element check failed";
									}
								}
								catch (NoSuchElementException e) {
									vTS_res = "FAILED";
									vTS_error = "Element missing " + e;
									if (vT_flag.equals("FAILED")) {
										vTS_res = "FAILED";
										vTS_error = "Element check failed";
									}
								}
								xlWrite_TS(xlPath, vTS_res, vTS_error, i);  			// Update Execution Status of test Step in Excel sheet.
								if (vTS_res.equals("FAILED")) { 							// Set TC to fail, if any Test Step fails.
									vTC_res = "FAILED";
									vTD_res = "FAILED";
								}
							}xlWrite_TD(xlPath, xTCdata[j][getCell(columns_TC, "TCID")], vTD_res, k);	// Update Execution Status of Test Data in Excel sheet.
						}
			        }
				}
				xlWrite_TC(xlPath, vTC_res, j);								// Update Execution Status of test Case in Excel sheet.
			}
		}
	}
}