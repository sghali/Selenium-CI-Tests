package com.app.qa.core;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.app.qa.utilitites.Accessories;
import com.app.qa.utilitites.HtmlReporters;
import com.app.qa.utilitites.Log;
import com.app.qa.utilitites.Property;
import com.app.qa.utilitites.Reporters;

import org.apache.log4j.xml.DOMConfigurator;

/**
 * Selenium/Web driver initiations 
 * @author shari
 * @Date  10/06/2014
 * 
 */
public class BaseTest {
	
	public static WebDriver driver;
	public static Property config;
	public static Property obj;
	public static HtmlReporters reporter;
	public static String currentSuite="";
	public static String method="";
	public static String timeStamp=Accessories.timeStamp().replace(" ","_").replace(":","_").replace(".", "_");
	public static boolean flag =false;
	
	public BaseTest(){
		config = new Property(".\\config.properties");
		obj = new Property(".\\object.properties");
		DOMConfigurator.configure("log4j.xml");
	}
	
	/*
	 * This method is start the web driver instance pulling properties from config file
	 */
	@BeforeSuite(alwaysRun = true)
	public static void setupSuite(ITestContext ctx) throws Throwable{
        if(config.getProperty("browserType").equalsIgnoreCase("firefox"))
        {

                        driver= new FirefoxDriver();
                        Log.info("Firefox driver initalized");
                        method="POST";
        }

        else if(config.getProperty("browserType").equals("chrome"))
        {

                        System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe"); 
                        Log.info("Chrome driver initalized");
                        driver=new ChromeDriver();
        }

        else
        {                              
                        File file = new File("Drivers\\IEDriverServer.exe");
                        System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
                        driver= new InternetExplorerDriver();
                        Log.info("By Defauly IE driver initalized");
                        method="post";
        }
        driver.get(config.getProperty("URL"));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Reporters.reportCreater();
        HtmlReporters.currentSuit = ctx.getCurrentXmlTest().getSuite().getName();

		
	}
	
	
	/*
	 * This method is stop the web driver instance
	 */
	@AfterSuite(alwaysRun = true)
	public static void tearDown(ITestContext ctx) throws Throwable{	
		HtmlReporters.createHtmlSummaryReport();
		Log.info("HTML Summary Report Created");
        driver.quit();		
        System.out.println(ctx.getFailedConfigurations());
	}
	
	/*
	 * Write results to Browser specific path
	 *
	 */
	@Parameters({"browserType"})
	public static String filePath()
	{
		String strDirectoy="";
		if(config.getProperty("browserType").equals("ie"))
		{
			strDirectoy="IE\\IE";	

		}
		else if(config.getProperty("browserType").equals("firefox"))
		{
			strDirectoy="Firefox\\Firefox";

		}
		else
		{
			strDirectoy="Chrome\\Chrome";

		}

		if(strDirectoy!="")
		{
			new File(config.getProperty("screenShotPath")+strDirectoy+"_"+timeStamp).mkdirs();
		}

		return config.getProperty("screenShotPath")+strDirectoy+"_"+timeStamp+"\\";

	}
	/**
	 * Browser type prefix
	 *
	 */
	public static String result_browser()
	{
		if(config.getProperty("browserType").equals("ie"))
		{
			return "IE";
		}
		else if(config.getProperty("browserType").equals("firefox"))
		{
			return "Firefox";
		}
		else
		{
			return "Chrome";
		}
	}
	/**
	 * Related to Xpath
	 * 
	 */
	public static String methodName()
	{
		if(config.getProperty("browserType").equals("ie"))
		{
			return "post";
		}
		else
		{
			return "POST";
		}
	}

	/*
	 * creates report header
	 */
	@BeforeMethod(alwaysRun=true)
	public void reportHeader(Method method){
		flag=false;
		HtmlReporters.tc_name=method.getName().toString();
		String[] ts_Name=this.getClass().getName().toString().split("\\.");
		HtmlReporters.packageName=ts_Name[0];
		HtmlReporters.testHeader(ts_Name[ts_Name.length-2].concat(" : ").concat(HtmlReporters.tc_name));
	}
	
	public static WebDriver getDriver(){
		return driver;
	}
	
	public static Property getConfigObject(){
		return config;
	}
	
	public static Property getObjRepository(){
		return obj;
	}
}


