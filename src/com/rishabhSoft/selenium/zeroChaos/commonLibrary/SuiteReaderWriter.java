package com.rishabhSoft.selenium.zeroChaos.commonLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.rishabhSoft.selenium.zeroChaos.drivers.SetObjectProperties;

/** @author Abhishek Singh
 * To Read the data from Test Suite (Excel sheet) and update the status of Test in Test Suite (Excel sheet).
 * */

public class SuiteReaderWriter {
	
	// Declared global variables
	public static int xTCRows, xTCCols;
	public static int xTSRows, xTSCols;
	public static int xTDRows, xTDCols;
	public static String[][] xTCdata;
	public static String[][] xTSdata;
	public static String[][] xTDdata;
 
	static HSSFWorkbook hssfWorkbook = null;
	static HSSFSheet hssfWorkSheet;
	static Workbook wrkbook =null;
	static Sheet wrksheet;
	static Hashtable<String, Integer> columns_TC = new Hashtable<String, Integer>();
	static Hashtable<String, Integer> columns_TS = new Hashtable<String, Integer>();
	static Hashtable<String, Integer> columns_TD = new Hashtable<String, Integer>();
 
	//Returns the Cell value by taking row and Column values as argument
	public static String readCell(int row, int column)
	{
		return wrksheet.getCell(row, column).getContents();
	}
 
	//Create Column Dictionary to hold all the Column Names
	public static void columnDictionary(String xlsPath, String sheet, Hashtable<String, Integer> columns) throws BiffException, IOException
	{
		wrkbook = Workbook.getWorkbook(new File(xlsPath));
		wrksheet = wrkbook.getSheet(sheet);
		//Iterate through all the columns in the Excel sheet and store the value in Hashtable
		for(int col=0; col < wrksheet.getColumns(); col++)
		{
			columns.put(readCell(col,0), col);
		}
	}
 
	//Read Column Names
	public static int getCell(Hashtable<String, Integer> columns, String colName)
	{
		try {
			int value;
			value = ((Integer)columns.get(colName)).intValue();
			return value;
		} catch (NullPointerException e) {
			return (0);
		}
	}
	
	// Read the Test Case from TestCases Sheet.
	public static void xlRead_TC(String xlPath, String sheet) throws Exception{
		
		columnDictionary(xlPath, sheet, columns_TC);				// To get All columns Text in Hashtable corresponding to sheet.
		File myxl = new File(xlPath);
		FileInputStream myStream = new FileInputStream(myxl);
		hssfWorkbook = new HSSFWorkbook(myStream);
		hssfWorkSheet = hssfWorkbook.getSheet(sheet); 				// Referring to 1st sheet of Test Case
		xTCRows = hssfWorkSheet.getLastRowNum()+1;
		xTCCols = hssfWorkSheet.getRow(0).getLastCellNum();
		xTCdata = new String[xTCRows][xTCCols];
        for (int i = 0; i < xTCRows; i++) {
           HSSFRow row = hssfWorkSheet.getRow(i);
            for (int j = 0; j < xTCCols; j++) {
               HSSFCell cell = row.getCell(j); 						// To read value from each column in each row
               try {
	               String value = cellToString(cell);
	               xTCdata[i][j] = value;
               }
               catch (Exception e) {}
            }
        }
	}
	
	// Read the Test Steps from TestSteps Sheet corresponding to Test Cases.
	public static void xlRead_TS(String xlPath, String sheet) throws Exception{
		
		columnDictionary(xlPath, sheet, columns_TS);				// To get All columns Text in Hashtable corresponding to sheet.
		File myxl = new File(xlPath);
		FileInputStream myStream = new FileInputStream(myxl);
		hssfWorkbook = new HSSFWorkbook(myStream);
		hssfWorkSheet = hssfWorkbook.getSheet(sheet);  				// Referring to 2nd sheet of Test Steps
		xTSRows = hssfWorkSheet.getLastRowNum()+1;
		xTSCols = hssfWorkSheet.getRow(0).getLastCellNum();
		xTSdata = new String[xTSRows][xTSCols];
        for (int i = 0; i < xTSRows; i++) {
           HSSFRow row = hssfWorkSheet.getRow(i);
           for (int j = 0; j < xTSCols; j++) {
        	   HSSFCell cell = row.getCell(j); 						// To read value from each column in each row
               try{
	               String value = cellToString(cell);
	               xTSdata[i][j] = value;
               }
               catch (Exception e) {}
            }
        }	
	}
	
	// Read the Data from Data Sheet corresponding to Test Cases.
	public static void xlRead_TD(String xlPath, String sheet) throws Exception{
		
		columnDictionary(xlPath, sheet, columns_TD);			// To get All columns Text in Hashtable corresponding to sheet.
		File myxl = new File(xlPath);
		FileInputStream myStream = new FileInputStream(myxl);
		hssfWorkbook = new HSSFWorkbook(myStream);
		hssfWorkSheet = hssfWorkbook.getSheet(sheet);			// Referring to Test Data Sheet corresponding to Test Case.
		xTDRows = hssfWorkSheet.getLastRowNum()+1;
		xTDCols = hssfWorkSheet.getRow(0).getLastCellNum();
		xTDdata = new String[xTDRows][xTDCols]; 
        for (int i = 1; i < xTDRows; i++) {
	       HSSFRow row = hssfWorkSheet.getRow(i);  
	       for (int j = 0; j < xTDCols; j++) {
	    	   HSSFCell cell = row.getCell(j); 					// To read value from each column in each row
	           try{
	               String value = cellToString(cell);
	               xTDdata[i][j] = value;
	           }
	           catch (Exception e) {}
	       }
        }
	}
	
	// Update Execution of Test Case Status in Excel sheet.
	public static void xlWrite_TC(String xlPath, String statusTC, int rowNum) throws Exception {

		FileInputStream fis= new FileInputStream(xlPath);
		hssfWorkbook = new HSSFWorkbook(fis);
		hssfWorkSheet = hssfWorkbook.getSheet("TestCases");
		HSSFRow row = hssfWorkSheet.getRow(rowNum);
		row.createCell(getCell(columns_TC, "TestStatus")).setCellValue(statusTC);
		row.createCell(getCell(columns_TC, "TimeStamp")).setCellValue(DateFormatUtilities.getCurrentDateTimeFormatted());
		row.createCell(getCell(columns_TC, "Tester")).setCellValue(SetObjectProperties.appConfig.getPropertyValue("TesterName"));
	    FileOutputStream fileOut =  new FileOutputStream(xlPath);
	    hssfWorkbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }
	
	// Update Execution of test Step Status in Excel sheet.
	public static void xlWrite_TS(String xlPath, String statusTS, String errorStatus, int rowNum) throws Exception {
		
		FileInputStream fis= new FileInputStream(xlPath);
		hssfWorkbook = new HSSFWorkbook(fis);
		hssfWorkSheet = hssfWorkbook.getSheet("TestSteps");
		HSSFRow row = hssfWorkSheet.getRow(rowNum);
		row.createCell(getCell(columns_TS, "Proceed_on_Fail")).setCellValue(errorStatus);
		row.createCell(getCell(columns_TS, "TestStepStatus")).setCellValue(statusTS);
	    FileOutputStream fileOut =  new FileOutputStream(xlPath);
	    hssfWorkbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }
	
	// Update Execution of test Data Status in Excel sheet.
	public static void xlWrite_TD(String xlPath, String sheet, String statusTD, int rowNum) throws Exception {
		
		FileInputStream fis= new FileInputStream(xlPath);
		hssfWorkbook = new HSSFWorkbook(fis);
		hssfWorkSheet = hssfWorkbook.getSheet(sheet);
		HSSFRow row = hssfWorkSheet.getRow(rowNum);
		row.createCell(getCell(columns_TD, "TestDataStatus")).setCellValue(statusTD);
	    FileOutputStream fileOut =  new FileOutputStream(xlPath);
	    hssfWorkbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }
	
	// get the test data from properties file for corresponding test steps.
	public static String readProperties_TD(String data) throws Exception{

		return SetObjectProperties.appConfig.getPropertyValue(data);
	}
	
	// This function will convert an object of type excel cell to a string value
	public static String cellToString(HSSFCell cell) throws NullPointerException{
		
		// This function will convert an object of type excel cell to a string value
		int type = cell.getCellType();
        Object result;
        switch (type) {
            case HSSFCell.CELL_TYPE_NUMERIC: //0
                result = cell.getNumericCellValue();
                break;
            case HSSFCell.CELL_TYPE_STRING: //1
                result = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_FORMULA: //2
                throw new RuntimeException("We can't evaluate formulas in Java");
            case HSSFCell.CELL_TYPE_BLANK: //3
                result = "-";
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN: //4
                result = cell.getBooleanCellValue();
                break;
            case HSSFCell.CELL_TYPE_ERROR: //5
                throw new RuntimeException ("This cell has an error");
            default:
                throw new RuntimeException("We don't support this cell type: " + type);
        }
        return result.toString(); 
    }
}