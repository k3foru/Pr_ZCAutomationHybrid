package com.rishabhSoft.selenium.zeroChaos.drivers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.Predicate;

import static com.rishabhSoft.selenium.zeroChaos.drivers.ReadObjectProperties.*;

/**
 * Common Actions use in web application.
 * 
 * @author Abhishek Singh
 */
public class CommonActions extends TestNG_WebDriver {

	/*************** Common Custom Methods for ReUsability in Framework. *******************************************/

	public static void getBaseUrl(String aURL) {

		webDriver.get(aURL);
	}
		
	public static void navigateTo(String aURL) {

		webDriver.navigate().to(aURL);
	}

	public static void sendKeys(LocatorType locator, String aText) {
		webDriver.findElement(locator.execute(objLocatorValue)).sendKeys(aText);
	}

	public static void clickElement(LocatorType locator) {
		webDriver.findElement(locator.execute(objLocatorValue)).click();
	}

	public static void clear(LocatorType locator) {
		webDriver.findElement(locator.execute(objLocatorValue)).clear();
	}
	
	public static void clickLinkText(String aText) {

		webDriver.findElement(By.linkText(aText)).click();
	}

	public static String getText(LocatorType locator) {

		return webDriver.findElement(locator.execute(objLocatorValue)).getText();
	}

	// Customize Method to verify Element present on current page.
	public static boolean isElementPresent(By by) {
		try {
			webDriver.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Customize Method to verify Text present on current page.
	public static boolean isTextPresent(String text) {
		if (webDriver.findElement(By.tagName("body")).getText().contains(text))
			return true;
		else
			return false;
	}

	public static String verifyTextPresent(LocatorType locator, String aText) {

		String fTextOut = webDriver.findElement(locator.execute(objLocatorValue)).getText();
		if (fTextOut.equals(aText)) {
			return "PASSED";
		} else {
			return "FAILED";
		}
	}

	public static String verifyElementPresent(LocatorType locator) {
		if (webDriver.findElement(locator.execute(objLocatorValue)).isDisplayed()) {
			return "PASSED";
		} else {
			return "FAILED";
		}
	}

	public static String virifyLinkPresent(String linkText) {
		if (webDriver.findElement(By.linkText(linkText)).isDisplayed()) {
			return "PASSED";
		} else {
			return "FAILED";
		}
	}

	// To get the label of selected option in drop down.
	protected static String getSelectedLabel(LocatorType locator) {
		String selectedLabel = new Select(webDriver.findElement(locator.execute(objLocatorValue)))
				.getFirstSelectedOption().getText();
		return selectedLabel;
	}

	// Customize Method for select Item by Index in Drop down.
	public static void selectByIndex(LocatorType locator, String index) {
		Select select = new Select(webDriver.findElement(locator.execute(objLocatorValue)));
		int byIndex = Integer.parseInt(index);
		select.selectByIndex(byIndex);
	}

	// Customize Method for select Item by Label in Drop down.
	public static void selectBylabel(LocatorType locator, String label) {
		Select select = new Select(webDriver.findElement(locator.execute(objLocatorValue)));
		select.selectByVisibleText(label);
	}

	// Customize Method for Compare And select Item by Label in Drop down if
	// present.
	public static void selectExistingOption(LocatorType locator, String label) {
		WebElement selectElement = webDriver.findElement(locator.execute(objLocatorValue));
		List<WebElement> componentList = selectElement.findElements(By.tagName("option"));
		for (WebElement component : componentList) {
			if (label.equals(component.getText())) {
				component.click();
				break;
			} else {
				System.out.println(label + " not Exist");
			}
		}
	}

	// Mouse Over to an Element
	public static void mouseOverElement(LocatorType locator) {
		Actions actions = new Actions(webDriver);
		actions.moveToElement(webDriver.findElement(locator.execute(objLocatorValue))).perform();
		// actions.moveToElement(Mainmenu).moveToElement(Submenu).moveToElement(Childsubmenu)click().build().perform();
	}

	// Handle child windows and get focus on that.
	public static void childWindowHandler() throws InterruptedException {
		Set<String> allWindows = webDriver.getWindowHandles();
		allWindows.remove(parentWindowHandle);
		String childWindow = allWindows.iterator().next();
		webDriver.switchTo().window(childWindow);
		webDriver.manage().window().maximize();
		System.out.println("Title of window ::" + webDriver.getTitle());
	}

	// Handle Java Script Element and click to that Element.
	public static void handelJavaScript(String elementID) {

		((JavascriptExecutor) webDriver)
				.executeScript("document.getElementById('elementID').click();");
	}

	// Handle JQuery Element.
	public static void isAjaxElementPresent(By by) {

		FluentWait<By> fluentWait = new FluentWait<By>(by);
		fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(1000, TimeUnit.MILLISECONDS);
		fluentWait.until(new Predicate<By>() {
			public boolean apply(By by) {
				try {
					return webDriver.findElement(by).isDisplayed();
				} catch (NoSuchElementException ex) {
					return false;
				}
			}
		});
		// webDriver.findElement(By.tagName("TEXTAREA")).sendKeys("text to enter");
	}

	public static String getAttribute(LocatorType locator, String aAttribute) {
		return webDriver.findElement(locator.execute(objLocatorValue)).getAttribute(aAttribute);
	}

	public static String verifyAttribute(LocatorType locator, String aAttribute, String aText) {
		String fTextOut = webDriver.findElement(locator.execute(objLocatorValue)).getAttribute(aAttribute);
		if (fTextOut.equals(aText)) {
			return "PASSED";
		} else {
			return "FAILED";
		}
	}
	
	// Handle Alert on current page.
	public static void handleAlert(){
		Alert alert = webDriver.switchTo().alert();
		//String alertMsg = alert.getText();
		alert.accept();
	}

	// Handle new tab in same window of browser.
	public static void switchToTab(String url) {
		new Actions(webDriver)
				.sendKeys(webDriver.findElement(By.tagName(url)), Keys.CONTROL)
				.sendKeys(webDriver.findElement(By.tagName(url)), Keys.NUMPAD2)
				.build().perform();
	}
	
	public static void closeBrowser(){
		webDriver.close();
	}
}
