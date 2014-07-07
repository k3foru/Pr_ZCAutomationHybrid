package com.rishabhSoft.selenium.zeroChaos.drivers;

import static com.rishabhSoft.selenium.zeroChaos.drivers.SetObjectProperties.getObjectReference;
import static com.rishabhSoft.selenium.zeroChaos.commonLibrary.HybridFramework.*;

public class Keywords {
	
	public static String keywordExecutor() throws Exception {
		String vtemp_Return = "PASSED";
		ReadObjectProperties obj = null;
		String[] reference = new String[2];
		if(vObject.contains("|")){
			reference = vObject.split("\\|");
			obj = getObjectReference(reference[0]);
		}
		if (vKeyword.equals("EnterText")){
			CommonActions.sendKeys(obj.getLocatorType(reference[1]), vTetsData);
		}		
		if (vKeyword.equals("click")){
			CommonActions.clickElement(obj.getLocatorType(reference[1]));
		}
		if (vKeyword.equals("clickLink")){
			CommonActions.clickLinkText(vObject);
		}
		if (vKeyword.equals("SelectValue")){
			CommonActions.selectBylabel(obj.getLocatorType(reference[1]), vTetsData);
		}
		if (vKeyword.equals("SelectIndex")){
			CommonActions.selectByIndex(obj.getLocatorType(reference[1]), vTetsData);
		}
		if (vKeyword.equals("verifyText")){
			vtemp_Return = CommonActions.verifyTextPresent(obj.getLocatorType(reference[1]), vTetsData);
		}
		if(vKeyword.equals("navigate")){
			CommonActions.navigateTo(vTetsData);
		}
		if(vKeyword.equals("focusChildWindow")){
			CommonActions.childWindowHandler();
		}
		if(vKeyword.equals("handleAlert")){
			CommonActions.handleAlert();
		}
		if (vKeyword.equals("closeBrowser")){
			CommonActions.closeBrowser();
		}
		if (vKeyword.equals("waitForElement")){
			CommonActions.waitForElement(obj.getLocatorType(reference[1]));
		}
		if (vKeyword.equals("wait")){
			Thread.sleep(18000);
		}
		return vtemp_Return;
	}
}
