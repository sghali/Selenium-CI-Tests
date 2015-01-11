/*package com.app.qa.tests;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.app.qa.core.BaseTest;
import com.app.qa.utilitites.Data_Provider;
import com.app.qa.utilitites.Property;
import com.app.qa.utilitites.Reporters;

public class RESTTest extends BaseTest{
	
	private static Property pro;
	private static String restURL;
	
	public RESTTest(){
		 pro = new Property(".\\config.properties");
		 restURL = pro.getProperty("rest_URL");
	}
	
	@DataProvider
	public Object[][] dp_restContent() throws Exception
	{
		return Data_Provider.getTableArray("Temperatures", "ZIP Code");
	}
	
	@Parameters({"restZIPCode" })
	@BeforeTest
	public void set(String restZIPCode ){
		 restURL = pro.getProperty("rest_URL")+restZIPCode;
	}
		
	@Test
	public static void testStatusCode() throws Throwable {
		
		try{		
		HttpUriRequest request = new HttpGet(restURL);
	    HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);	
	    Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),HttpStatus.SC_OK);
	    Reporters.SuccessReport("testStatusCode", "Successfully recevied response from REST client");
		}catch(Exception e){
			Reporters.failureReport("testStatusCode", "Failed to receive response from REST client");
			Assert.fail(e.getMessage(),e);
		}
	}
	
	@Test
	public static void testStatus404Code() throws Throwable {
		
		try{		
		HttpUriRequest request = new HttpGet(pro.getProperty("rest_URL"));
	    HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);	
	    Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),HttpStatus.SC_NOT_FOUND);
	    Reporters.SuccessReport("testStatus404Code", "Successfully validated Not found code from REST client");
		}catch(Exception e){
			Reporters.failureReport("testStatus404Code", "Failed to validate Not found code from REST client");
			Assert.fail(e.getMessage(),e);
		}
	}
	
	@Parameters({"invalidZip"})
	@Test
	public static void testStatusNoConentCode(String invalidZip) throws Throwable {
		
		try{		
		HttpUriRequest request = new HttpGet(pro.getProperty("rest_URL")+invalidZip);
	    HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);	
	    Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),HttpStatus.SC_NO_CONTENT);
	    Reporters.SuccessReport("testStatusNoConentCode", "Successfully validated NO Content code from REST client");
	   	}catch(Exception e){
	   		Reporters.failureReport("testStatusNoConentCode", "Failed to validate No Content code from REST client");
			Assert.fail(e.getMessage(),e);
		}
	}
	
	@Parameters({"expectedResponseType"})
	@Test(dependsOnMethods = { "testStatusCode" })
	public static void testResponseType(String expectedResponseType) throws Throwable {
        
		try{
	    HttpUriRequest request = new HttpGet(restURL);
	    HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);	         
	    Assert.assertEquals(expectedResponseType,ContentType.getOrDefault(httpResponse.getEntity()).getMimeType());
	    Reporters.SuccessReport("testResponseType", "Successfully validated JSON Response type");
		}catch(Exception e){
			Reporters.failureReport("testResponseType", "Failed to validate JSON Response type");
			Assert.fail(e.getMessage(),e);
			
		}
	}
	
	
	@Test(dependsOnMethods={"testStatusCode","testResponseType"},dataProvider="dp_restContent")
	public static void testContentJSON(String zipCode,String high,String low ) throws Throwable {		
		try{
		String url="";
		HashMap<String, String> expectedValue = (HashMap<String, String>) getHashMap(zipCode,high,low);		
		Iterator<Map.Entry<String, String>> iterator = expectedValue.entrySet().iterator();
		url = pro.getProperty("rest_URL")+zipCode;		 
	    HttpUriRequest request = new HttpGet(url);
	    HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
	 
	    // Convert the response to a String format
	    String result = EntityUtils.toString(httpResponse.getEntity());
	 
	    // Convert the result as a String to a JSON object
	    JSONObject jo = new JSONObject(result);
	    
	    while(iterator.hasNext()){
	    	Map.Entry<String, String> entry = iterator.next();
	    	  Assert.assertEquals(entry.getValue(), jo.getString(entry.getKey()));
	    	  Reporters.SuccessReport("testContentJSON", "Successfully validated JSON Response content");
	    }
		}catch(Exception e){
			Reporters.failureReport("testResponseType", "Failed to validate JSON Response content");
			Assert.fail(e.getMessage(),e);
		}
	}
	

	public static Map<String, String> getHashMap(String zipCode,String high,String low){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("zipcode", zipCode);
		map.put("high",high);
		map.put("low",low);
		return map;
		
	}

}

*/