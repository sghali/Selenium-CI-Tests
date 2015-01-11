package com.app.qa.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.app.qa.core.BaseTest;
import com.app.qa.objectrepository.TemperatureHomePage;
import com.app.qa.utilitites.Data_Provider;
import com.app.qa.utilitites.Reporters;

public class TemperatureServicesTest extends BaseTest {
	
	TemperatureHomePage hPage;
	
	public TemperatureServicesTest(){
		hPage = new TemperatureHomePage();
	}
	
	@DataProvider
	public Object[][] dp_SearchZip() throws Exception
	{
		return Data_Provider.getTableArray("Temperatures", "ZIP Code");
	}

	@Parameters({ "emptyZip" })
	@Test(groups={"functest"})
	public  void verifyTemperaturNoValue(String emptyZip) throws Throwable{		
		try{
		hPage.enterZipCode(" ");
		hPage.homePageSubmit();
		Assert.assertTrue(hPage.isPopUpPresent(emptyZip));
		}catch(Exception e){
			Assert.fail(e.getMessage(),e);
	}
	}
	
	@Test(groups={"functest"},dataProvider="dp_SearchZip")
	public void verifyTemperature(String zipCode,String high,String low) throws Throwable{
		try{
			hPage.enterZipCode(zipCode);
			hPage.homePageSubmit();		
			Assert.assertTrue(hPage.validateTemperatureValues(zipCode,high,low));
		}catch(Exception e){
			Assert.fail(e.getMessage(),e);
		}
	}
	
	@Parameters({ "invalidZip" })
	@Test(groups={"functest"})
	public void verifyTemperatureForMissingZip(String invalidZip) throws Throwable{
		try{
			hPage.enterZipCode(invalidZip);
			hPage.homePageSubmit();
			Assert.assertTrue(hPage.validateNoMatchText(invalidZip));			
			}catch(Exception e){
				Assert.fail(e.getMessage(),e);
		}
	}
	
	@Parameters({ "zipString" })
	@Test(groups={"functest"})
	public void verifyTemperatureWithInvalidDetails(String zipString) throws Throwable{
		try{
			hPage.enterZipCode(zipString);
			hPage.homePageSubmit();
			Assert.assertTrue(hPage.isPopUpPresent(zipString));			
			}catch(Exception e){
				Assert.fail(e.getMessage(),e);
		}
	}
	
	@Test(groups={"guitest"})
	public void verifyPageTitle() throws Throwable{
		try{
			Assert.assertTrue(hPage.validateHomeTitle());
		}catch(Exception e){
			Assert.fail(e.getMessage(),e);
	}
	}
	
	@Test(groups={"guitest"})
	public void verifyPageHeader() throws Throwable{
		try{
			Assert.assertTrue(hPage.validateHomePageheader());
		}catch(Exception e){
			Assert.fail(e.getMessage(),e);
	}
	
	
	}
}

