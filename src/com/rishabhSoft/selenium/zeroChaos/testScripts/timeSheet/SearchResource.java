package com.rishabhSoft.selenium.zeroChaos.testScripts.timeSheet;

import static com.rishabhSoft.selenium.zeroChaos.drivers.SetObjectProperties.*;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.rishabhSoft.selenium.zeroChaos.drivers.CommonActions;
import com.rishabhSoft.selenium.zeroChaos.drivers.TestNG_WebDriver;
import com.rishabhSoft.selenium.zeroChaos.modules.resource.ResourcePage;
import com.rishabhSoft.selenium.zeroChaos.modules.timeSheet.ZCLoginPage;

public class SearchResource extends TestNG_WebDriver{
	
	@Test  (description="User Login in ZC Application", groups = { "regression" })
	public void TC_ZCLoginTest() throws Exception{
		testSummary("Verify User Logged in");
		ZCLoginPage.loginAsAdmin();
		verifyEquals(CommonActions.getText(common.getLocatorType("MemberSearch_lblAdminName")), "Abhijeet Patil");
		ResourcePage.memberSearch_EnterReasourceID();
		ResourcePage.clickOnSearchButton();
		webDriver.findElement(By.tagName("div"));
		waitForElement(resource.getLocatorType("MemberSearch_ResourceIDLink"));
		testLog("Verified : User Logged in");
	}
}
