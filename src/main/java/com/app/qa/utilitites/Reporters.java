package com.app.qa.utilitites;

import com.app.qa.framework.web.Actiondriver;
import com.app.qa.core.BaseTest;


public class Reporters{

	public static Property configProps=new Property("config.properties");
	static String  timeStamp=Accessories.timeStamp().replace(":", "_").replace(".", "_");

	/**
	 * Creates report
	 */
	public static void reportCreater() {
		int intReporterType=Integer.parseInt(configProps.getProperty("reportsType"));
		try {

		switch (intReporterType) {
		case 1:
			//ExcelReporter.excelTestReportCreator();
			break;
		case 2:
			 HtmlReporters.htmlCreateReport();
			HtmlReporters.createDetailedReport();

			break;
		default:
			//ExcelReporter.excelTestReportCreator();
			HtmlReporters.htmlCreateReport();
			break;
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	/**Enters success step details in report
	 * @param strStepName
	 * @param strStepDes
	 * @throws Throwable
	 */
	public static void SuccessReport(String strStepName, String strStepDes) throws Throwable{
		int intReporterType=Integer.parseInt(configProps.getProperty("reportsType"));
		switch (intReporterType) {
		case 1:

			break;
		case 2:

			HtmlReporters.onSuccess(strStepName, strStepDes);

			break;

		default:

			HtmlReporters.onSuccess(strStepName, strStepDes);
			break;
		}
	}	

	
	/**Enters failure step details in the report
	 * @param strStepName
	 * @param strStepDes
	 * @throws Throwable
	 */
	public static void failureReport(String strStepName, String strStepDes) throws Throwable{
		int intReporterType=Integer.parseInt(configProps.getProperty("reportsType"));
		String desc ="";
		if(desc.indexOf(":")>0){
			desc = strStepDes.substring(0,strStepDes.indexOf(":")).replace(" ", "_").replace(":", "_");
		}else{
			desc = strStepDes.replace(" ", "_").replace(":", "_");
		}
		switch (intReporterType) {
		case 1:
			break;
		case 2:
			Actiondriver.screenShot(BaseTest.filePath()+desc+"_"+BaseTest.timeStamp+".jpeg");
			HtmlReporters.onFailure(strStepName, strStepDes);
			break;

		default:
			Actiondriver.screenShot(BaseTest.filePath()+strStepDes.replace(" ", "_")+"_"+BaseTest.timeStamp+".jpeg");				
			HtmlReporters.onFailure(strStepName, strStepDes);
			break;
		}
	}
}

