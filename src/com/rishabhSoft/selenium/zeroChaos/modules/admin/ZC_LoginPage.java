package com.rishabhSoft.selenium.zeroChaos.modules.admin;

import static com.rishabhSoft.selenium.zeroChaos.drivers.SetObjectProperties.common;
import static com.rishabhSoft.selenium.zeroChaos.drivers.SetObjectProperties.appConfig;

import com.rishabhSoft.selenium.zeroChaos.drivers.CommonActions;

public class ZC_LoginPage extends CommonActions {

	public static void userLogin() {
		enterUserName(appConfig.getPropertyValue("UserName"));
		enterPassword(appConfig.getPropertyValue("Password"));
		clickOnLoginBtn();
	}

	public static void enterUserName(String aUserName) {
		sendKeys(common.getLocatorType("txtUsername"), aUserName);
	}

	public static void enterPassword(String aPassword) {
		sendKeys(common.getLocatorType("txtPassword"), aPassword);
	}

	public static void clickOnLoginBtn() {
		clickElement(common.getLocatorType("btnLogin"));
	}

}
