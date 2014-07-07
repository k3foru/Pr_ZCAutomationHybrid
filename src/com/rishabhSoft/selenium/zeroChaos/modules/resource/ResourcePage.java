package com.rishabhSoft.selenium.zeroChaos.modules.resource;

import java.io.IOException;

import jxl.read.biff.BiffException;

import com.rishabhSoft.selenium.zeroChaos.commonLibrary.XLsReaderUtility;
import com.rishabhSoft.selenium.zeroChaos.drivers.CommonActions;
import static com.rishabhSoft.selenium.zeroChaos.drivers.SetObjectProperties.resource;

public class ResourcePage extends CommonActions{

	static XLsReaderUtility xlsReader;
	static int rowCnt;
	
	public static void memberSearch_EnterReasourceID() throws BiffException, IOException{
		//Load the Excel Sheet Col in to Dictionary for Further use in our Test cases. ExcelSheet
		XLsReaderUtility.columnDictionary("DataTable/Resource.xls", "Resource");
		for(rowCnt=0; rowCnt < XLsReaderUtility.rowCount(); rowCnt++)
		{
				if(XLsReaderUtility.readCell(XLsReaderUtility.getCell("RunMode"), rowCnt).equalsIgnoreCase("Yes")){
					sendKeys(resource.getLocatorType("MemberSearch_TxtSmartSearchHelp"), XLsReaderUtility.readCell(XLsReaderUtility.getCell("ResourceID"), rowCnt));
				}// end of if block
		}//end of for loop rowCnt=2; rowCnt < XLsReaderUtility.rowCount(); rowCnt++)
	}
	
	public static void clickOnSearchButton(){
		clickElement(resource.getLocatorType("MemberSearch_BtnSearch"));
	}
}
