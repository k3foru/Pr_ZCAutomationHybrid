package com.rishabhSoft.selenium.zeroChaos.testScripts.timeSheet;

import org.testng.annotations.Test;

import com.rishabhSoft.selenium.zeroChaos.commonLibrary.HybridFramework;
import com.rishabhSoft.selenium.zeroChaos.drivers.TestNG_WebDriver;
import com.rishabhSoft.selenium.zeroChaos.modules.timeSheet.ZCLoginPage;

public class HybridFrameworkTest extends TestNG_WebDriver {

	@Test (description="Resource Search", groups = { "Resource" })
	public void resourceTest() throws Exception
	{
		testSummary("Search for Resource");
		ZCLoginPage.loginAsAdmin();
		HybridFramework.executeTestSuite("TestCases/SearchResourceTest.xls");
		testLog("Resoucre Module Test PASSED");
	}
}