package com.app.qa.objectrepository;

import org.openqa.selenium.By;

import com.app.qa.framework.web.Actiondriver;
import com.app.qa.utilitites.Property;
import com.app.qa.utilitites.Reporters;

/**
 * @author sree
 *
 */

public class TemperatureHomePage extends Actiondriver {

	Property pro;
	public TemperatureHomePage(){
		pro = getObjRepository();
	}


	/**Validates whether page  is loaded or not
	 * @return boolean
	 */
	public boolean isHomePageLoaded(){
		return asserttitle(pro.getProperty("home_title"));
	}

	/**Enters zip value
	 * @param zipValue
	 * @throws Throwable
	 */
	public void enterZipCode(String zipValue) throws Throwable{
		try{
			type(By.xpath(pro.getProperty("temperature_text_field")),zipValue);
			Reporters.SuccessReport("enterZipCode","Entered Zip values successfully");
		}catch(Exception e){
			Reporters.failureReport("enterZipCode", e.getMessage());
			throw(e);
		}

	}

	/**clicks on the home page submit button
	 * @throws Throwable
	 */
	public void homePageSubmit() throws Throwable{
		click(By.xpath(pro.getProperty("home_submit")));
		Reporters.SuccessReport("homePageSubmit","Clicked on submit button");
	}

	/**verifies the present of right alert box
	 * @param invalidZip
	 * @return boolean
	 * @throws Throwable
	 */
	public boolean isPopUpPresent(String invalidZip) throws Throwable{
		boolean isPresent =false;
		try {
			if(invalidZip.equalsIgnoreCase(" ")){
				isPresent= isAlertPresentWithGivenText(pro.getProperty("alert_empty"));
			}else {
				isPresent= isAlertPresentWithGivenText(pro.getProperty("alert_invalid"));
			}if(isPresent){
				Reporters.SuccessReport("isPopUpPresent","Pop Up Present and Text matched");
			}
			else{
				Reporters.failureReport("isPopUpPresent","Pop Up Text Not matched");
			}

		} catch (Throwable e) {
			Reporters.failureReport("isPopUpPresent", e.getMessage());
			throw(e);
		}
		return isPresent;		
	}

	/**validates temperature values
	 * @param zipCode
	 * @param high
	 * @param low
	 * @return boolean
	 * @throws Throwable
	 */
	public boolean validateTemperatureValues(String zipCode,String high,String low) throws Throwable{
		boolean isValidated = false;
		try{
			if(getText(By.xpath(pro.getProperty("zip_text"))).equalsIgnoreCase(zipCode) && getText(By.xpath(pro.getProperty("high_temperature"))).equalsIgnoreCase(high) && getText(By.xpath(pro.getProperty("low_temperature"))).equalsIgnoreCase(low)){
				Reporters.SuccessReport("validateTemperatureValues", "Temperature ZIP, High and Low Values Matached");
				isValidated = true;
			}else{
				Reporters.failureReport("validateTemperatureValues", "Temperature ZIP , High and Low Values are not Matached");
				isValidated = false;
			}
		}catch(Exception e){
			Reporters.failureReport("validateTemperatureValues", e.getMessage());
		}
		return isValidated;
	}

	/**validates home page title
	 * @return boolean
	 * @throws Throwable
	 */
	public boolean validateHomeTitle() throws Throwable{
		boolean isTitleMatched = false;
		try{
			isTitleMatched = asserttitle(pro.getProperty("home_title"));
			if(isTitleMatched){
				Reporters.SuccessReport("validateHomeTitle", "Title Matched");
			}else{
				Reporters.failureReport("validateHomeTitle", "Title not matched as expected");
			}
		}catch(Exception e){
			Reporters.failureReport("validateHomeTitle", e.getMessage());
			throw(e);
		}
		return isTitleMatched;		
	}

	/**Validates home page header text
	 * @return boolean
	 * @throws Throwable
	 */
	public boolean validateHomePageheader() throws Throwable{
		boolean isHeaderMatched = false;
		try{
			isHeaderMatched = assertText(pro.getProperty("home_header_Text"),By.xpath(pro.getProperty("home_header")));
			if(isHeaderMatched){
				Reporters.SuccessReport("validateHomePageheader", "Header Matched");
			}else{
				Reporters.failureReport("validateHomePageheader", "Header not matched as expected");
			}
		}catch(Exception e){
			Reporters.failureReport("validateHomePageheader", e.getMessage());
			throw(e);
		}
		return isHeaderMatched;		
	}


	/**validates the text if no elements found on home page
	 * @param inValidZip
	 * @return boolean
	 * @throws Throwable
	 */
	public boolean validateNoMatchText(String inValidZip) throws Throwable{
		boolean isTextMatched = false;
		try{
			isTextMatched = assertText(pro.getProperty("text_not_match")+inValidZip,By.xpath(pro.getProperty("text_not_match_locator")));
			if(isTextMatched){
				Reporters.SuccessReport("validateNoMatchText", "Text Matched");
			}else{
				Reporters.failureReport("validateNoMatchText", "Text not matched as expected");
			}
		}catch(Exception e){
			Reporters.failureReport("validateNoMatchText", e.getMessage());
			throw(e);
		}
		return isTextMatched;		
	}			

}
