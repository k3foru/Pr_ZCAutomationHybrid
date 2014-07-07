package com.rishabhSoft.selenium.zeroChaos.modules.timeSheet;

import com.rishabhSoft.selenium.zeroChaos.drivers.CommonActions;

import static com.rishabhSoft.selenium.zeroChaos.drivers.SetObjectProperties.*;

public class ZCLoginPage extends CommonActions{
	
	public static void loginAsAdmin(){
		enterUserName(appConfig.getPropertyValue("UserName"));
		enterPassword(appConfig.getPropertyValue("Password"));
		clickOnLoginBtn();
	}

	public static void enterUserName(String aUserName) {
		sendKeys(common.getLocatorType("Login_TxtUsername"), aUserName);
	}
	
	public static void enterPassword(String aPassword){
		sendKeys(common.getLocatorType("Login_TxtPassword"), aPassword);
	}
	
	public static void clickOnLoginBtn(){
		clickElement(common.getLocatorType("Login_BtnLogin"));
	}
}
