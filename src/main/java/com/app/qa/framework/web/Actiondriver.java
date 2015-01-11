package com.app.qa.framework.web;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.app.qa.core.BaseTest;
import com.app.qa.utilitites.Log;
import com.app.qa.utilitites.Reporters;

/**
 * All customized/ Generic functions 
 * @author shari
 * @Date  10/06/2014
 * 
 */
public class Actiondriver extends BaseTest{


	public static WebDriverWait wait;

	/**
	 * This  Method is to perform click operation on (link,button,check box,radio button) 
	 * @param locator --Element locator
	 * @author shari
	 * @throws Throwable 
	 * @Date  10/07/2014
	 * @Revision History
	 * 
	 */
	public static boolean click(By locator) throws Throwable 
	{
		boolean isClicked =false;
		try {
			if( isElementPresent(locator)){
				driver.findElement(locator).click();
				isClicked = true;
				return true;
			}else{
				Reporters.failureReport("click", "Unable to click as element is not found on the page");
				isClicked = false;
			}
		} catch (Exception e) {       
			Log.error(e.getMessage());
			throw new NoSuchElementException(locator +" Element not found");

		}
		return isClicked;
	}


	/**
	 * Verify element present or not
	 * @param locator --Element locator
	 * @return TrueIf the element is Present, False other wise
	 * @author shari
	 * @throws Throwable 
	 * @Date  10/07/2014
	 * @Revision History
	 * 
	 */
	public static boolean isElementPresent(By by) throws Throwable {
		boolean isElementPresent =false;
		try {
			driver.findElement(by);
			isElementPresent =true;
		} catch (NoSuchElementException e) {
			Reporters.failureReport("Is Element Present", "Unable to identfiy the specific locator");
			isElementPresent= false;
			Log.error(e.getMessage());
			throw(e);
		}
		return isElementPresent;

	}


	/**
	 * This method for type in to text box or text area
	 * @param locator --Element locator
	 * @param testdata -- input text
	 * @author shari
	 * @Date  10/07/2014
	 * @Revision History
	 * 
	 */
	public static void type(By locator, String testdata)
	{
		try {
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(testdata);
		} catch (NoSuchElementException e) {
			Log.error(e.getMessage());
			throw(e);
		}
	}

	/**
	 * This method to wait for certain time for identify the locator
	 * @param locator --Element locator
	 * @param testdata -- input text
	 * @author shari
	 * @throws Throwable 
	 * @Date  10/07/2014
	 * @Revision History
	 * 
	 */
	public static void waitForElementPresent(By locator) throws Throwable
	{
		for(int i=0; i<200; i++)
		{
			if (isElementPresent(locator))
			{
				break;
			} else
			{
				Thread.sleep(50);
			}
			{
				try
				{
					driver.wait(5000);
				} catch (Exception e)
				{
					Log.error(e.getMessage());
					throw(e);
				}
			}
		}
	}

	/**
	 * Click and wait for an element
	 * @param locator -- Element locator
	 * @param waitElement  -- Element which you are waiting for
	 * @author shari
	 * @throws Throwable 
	 * @Date  10/07/2014
	 * @Revision History
	 * 
	 */
	public static void clickAndWaitForElementPresent(By locator, By waitElement) throws Throwable
	{
		click(locator);
		waitForElementPresent(waitElement);
	}

	/**
	 * Verify alert present or not
	 * @return True if alert is present false other wise
	 * @author shari
	 * @Date  10/07/2014
	 * @Revision History
	 * 
	 */
	public static boolean isAlertPresent()  throws Throwable {

		boolean presentFlag = false;

		try {
			Alert alert = driver.switchTo().alert();
			// Alert present; set the flag
			presentFlag = true;
			// if present consume the alert
			alert.accept();                                                
		} catch (Exception ex) {
			// Alert not present
			Reporters.failureReport("Alert Present", "No Alert Displayed");
			presentFlag =false;
			Log.error(ex.getMessage());
			throw(ex);

		}
		return presentFlag;

	}

	/**
	 * Verify alert present or not
	 * @return True if alert is present false other wise
	 * @author shari
	 * @Date  10/07/2014
	 * @Revision History
	 * 
	 */
	public static boolean isAlertPresentWithGivenText(String alertText)  throws Throwable {

		boolean presentFlag = false;

		try {
			Alert alert = driver.switchTo().alert();
			// Alert present; set the flag
			if(getAlertText().equalsIgnoreCase(alertText)){
				presentFlag = true;
			}
			// if present consume the alert
			alert.accept();                                                
		} catch (Exception ex) {
			// Alert not present
			Reporters.failureReport("Alert Present", "No Alert Displayed");
			presentFlag =false;
			Log.error(ex.getMessage());
			throw(ex);

		}
		return presentFlag;

	}



	/**
	 * Element is editable or not
	 * @param locator ---- Element locator
	 * @return True if the element is enabled, false otherwise.
	 * @author shari
	 * @Date  10/07/2014
	 * @Revision History
	 * 
	 */

	public Boolean isEditable(By locator)
	{
		Boolean value = false;
		if (driver.findElement(locator).isEnabled())
		{
			value = true;
		}
		return value;
	}
	/**
	 *Element visible or not 
	 * @param locator ---- Element locator 
	 * @return True if the element is visible false other wise
	 * @author shari
	 * @throws Throwable 
	 * @Date  10/07/2014
	 * @Revision History
	 * 
	 */

	public Boolean IsVisible(By locator) throws Throwable
	{
		Boolean value = false;
		if (isElementPresent(locator))
		{
			value = driver.findElement(locator).isDisplayed();
		}
		return value;
	}
	/**
	 * Get the CSS value of an element          
	 * @param locator ---- Element locator
	 * @return CSS value of the element
	 * @author shari
	 * @throws Throwable 
	 * @Date  10/07/2014
	 * @Revision History
	 * 
	 */

	public String GetValue(By locator) throws Throwable
	{
		String value = "";
		if (isElementPresent(locator))
		{
			value = driver.findElement(locator).getCssValue(value);
		}
		return value;
	}
	/**
	 * Check the expected value is available or not
	 * @param expvalue 
	 * @param locator
	 * @param attribute
	 * @author shari
	 * @throws Throwable 
	 * @Date  10/07/2014
	 * @Revision History

	 * 
	 */
	public static boolean assertValue(String expvalue, By locator, String attribute) throws Throwable
	{
		Assert.assertEquals(expvalue, getAttribute(locator, attribute));
		return true;
	}



	/**
	 * Assert text
	 * @param text
	 * @param by
	 * @author shari
	 * @Date  10/07/2014
	 * @Revision History
	 * 
	 */

	public static boolean assertText(String text,By by)
	{
		try
		{
			Assert.assertEquals(text, getText(by));
			System.out.println("Expected Text is available");
			return true;
		}
		catch(Throwable e)
		{
			Log.error(e.getMessage());
			System.out.println("Expected text is not available");
		}
		return false;

	}

	/**
	 * assert title
	 * @param title
	 * @author shari
	 * @Date  10/07/2014
	 * @Revision History
	 * 
	 */
	public static boolean asserttitle(String title)
	{
		try
		{
			Assert.assertEquals(title, driver.getTitle());
			return true;
		}

		catch(Error e)
		{
			Log.error(e.getMessage());
			System.out.println("Title does not match");
			return false;
		}


	}

	/**
	 * Verify text present or not
	 * @param text
	 * @return  true if text is present false other wise
	 * @author shari
	 * @Date  10/07/2014
	 * @Revision History
	 * 
	 */
	public static boolean verifyTextPresent(String text)
	{
		try {
			Assert.assertTrue(driver.getPageSource().contains(text));
			System.out.println(text + " is Available");
			return true;
		} catch (Error e) {
			Log.error(e.getMessage());
			System.out.println(text +"Not avaliable");

		}
		return false;
	}
	/**
	 * Verify the 404 error or broken links
	 * @param text
	 * @author shari
	 * @Date  10/07/2014
	 * @Revision History
	 * 
	 */
	public static void verify404(String text)
	{
		if(driver.getPageSource().contains("404"))
		{
			System.out.println("404 error");
		}

	}
	/**
	 * Get the value of a the given attribute of the element. 
	 * @param by
	 * @param attribute
	 * @return  Will return the current value
	 * @author shari
	 * @throws Throwable 
	 * @Date  10/07/2014
	 * @Revision History
	 * 
	 */

	public static String getAttribute(By by, String attribute) throws Throwable
	{
		String value = "";
		if (isElementPresent(by))
		{
			value = driver.findElement(by).getAttribute(attribute);
		}
		return value;
	}

	/**
	 *Text present or not 
	 * @param text
	 * @return
	 * @author shari
	 * @Date  10/07/2014
	 * @Revision History
	 * 
	 */

	public boolean isTextPresent(String text) {

		return driver.getPageSource().contains("sometext");
	}
	/**
	 *The innerText of this element.
	 * @param locator
	 * @return
	 * @author shari
	 * @throws Throwable 
	 * @Date  10/07/2014
	 * @Revision History
	 * 
	 */

	public static String getText(By locator) throws Throwable
	{
		String text = "";
		if (isElementPresent(locator))
		{
			text = driver.findElement(locator).getText();
		}
		return text;
	}

	/**
	 * Capture Screenshot
	 * @param fileName
	 * @author shari
	 * @Date  10/07/2014
	 * @Revision History
	 * 
	 */
	public static void screenShot(String fileName) 
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			// Now you can do whatever you need to do with it, for example copy somewhere
			FileUtils.copyFile(scrFile, new File(fileName));
		} catch (IOException e) {
			Log.error(e.getMessage());
		}
	}


	/**
	 * Gets Text Present on Alert Box
	 * @param fileName
	 * @author shari
	 * @Date  10/07/2014
	 * @Revision History
	 * 
	 */    

	public static String getAlertText() throws Throwable{	
		String isTextPresent ="";
		try{
			Alert alert = driver.switchTo().alert();
			isTextPresent =  alert.getText();
		}
		catch(Exception e){
			Log.error(e.getMessage());
			Reporters.failureReport("getAlertText","Alert box is not available");
		}
		return isTextPresent;

	}

}