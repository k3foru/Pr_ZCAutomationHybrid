package com.rishabhSoft.selenium.zeroChaos.drivers;

import java.util.List;

import org.testng.IInvokedMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.Utils;

/**  
 * @author Abhishek Singh
 * 
 * To Check Verification (Soft Verification) with verify command and run ahead as well as give error in Report.
 */

public class SoftVerificationTestListener extends TestListenerAdapter {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {

        Reporter.setCurrentTestResult(result);

        if (method.isTestMethod()) {

            List<Throwable> verificationFailures = TestNG_WebDriver.getVerificationFailures();

            //if there are verification failures...
            if (verificationFailures.size() > 0) {

                //set the test to failed
                result.setStatus(ITestResult.FAILURE);

                //if there is an assertion failure add it to verificationFailures
                if (result.getThrowable() != null) {
                    verificationFailures.add(result.getThrowable());
                }

                int size = verificationFailures.size();
                //if there's only one failure just set that
                if (size == 1) {
                    result.setThrowable(verificationFailures.get(0));
                } else {
                    //create a failure message with all failures and stack traces (except last failure)
                    StringBuffer failureMessage = new StringBuffer("Multiple failures (").append(size).append("):nn");
                    for (int i = 0; i < size - 1; i++) {
                        failureMessage.append("Failure ").append(i + 1).append(" of ").append(size).append(":n");
                        Throwable t = verificationFailures.get(i);
                        String fullStackTrace = Utils.stackTrace(t, false)[1];
                        failureMessage.append(fullStackTrace).append("nn");
                    }

                    //final failure
                    Throwable last = verificationFailures.get(size - 1);
                    failureMessage.append("Failure ").append(size).append(" of ").append(size).append(":n");
                    failureMessage.append(last.toString());

                    //set merged throwable
                    Throwable merged = new Throwable(failureMessage.toString());
                    merged.setStackTrace(last.getStackTrace());

                    result.setThrowable(merged);
                }
            }
        }
    }
}
