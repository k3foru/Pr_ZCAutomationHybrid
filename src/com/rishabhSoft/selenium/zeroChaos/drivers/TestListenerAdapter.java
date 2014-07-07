package com.rishabhSoft.selenium.zeroChaos.drivers;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

/** 
 * @author Abhishek Singh
 * 
 * This Listener used by 'SoftVerificationTestListener' for Verification (Soft Verification) with verify command and run ahead as well as give error in Report.
 */

public class TestListenerAdapter implements IInvokedMethodListener {
	
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {}

	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {}
}