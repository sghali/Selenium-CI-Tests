package com.app.qa.utilitites;


import java.applet.Applet;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.app.qa.core.BaseTest;


public class HtmlReporters extends Applet {

	static String startedAt="";
	public static String tc_name="";
	public static String packageName="";
	static Map<String, String> map= new LinkedHashMap<String, String>();
	static Property config = new Property("config.properties");
	public static String currentSuit="";

	static String workingDir = "";

	public static void createDetailedReport() throws Exception
	{
		String[] s=Accessories.timeStamp().split(":");
		for(int i=0;i<s.length-1;i++){
			startedAt=startedAt+"-"+s[i];
		}
		startedAt=startedAt.substring(1,startedAt.length());
		startedAt=startedAt.replace(" ", "_");
		File file = new File(BaseTest.filePath()+"/"+"Results_"+BaseTest.timeStamp+".html"); 

		Writer writer = new FileWriter(file,true);
		writer.write("<HTML>");
		writer.write("<BODY>");
		writer.write("<TABLE border=0 cellSpacing=1 cellPadding=1 width='100%'>");

		workingDir = System.getProperty("user.dir").replace(File.separator, "/");
		writer.write("<TR><TD align='left'><img src='file:///"+workingDir+"/Logos/"+config.getProperty("Clint_logo")+".jpeg' alt='"+config.getProperty("Clint_logo")+"' height='75' width='150'> </TD>");
		writer.write("<TD align='center'><H4 align='center'><font color='660066' face='arial' color='#E0E0E0' size=5><b>Detailed report</b></H4></TD>");
		
		writer.write("</TABLE>");
		writer.write("</BODY>");
		writer.write("</HTML>");
		writer.flush();  
		writer.close();
	}
	@SuppressWarnings("rawtypes")
	public static void createHtmlSummaryReport() throws Exception{
		File file = new File(BaseTest.filePath()+"/"+"SummaryResults_"+BaseTest.timeStamp+".html");//"SummaryReport.html"
		Writer writer = null;

		if(file.exists())
		{
			file.delete();
		}
		//Create Summary report first table
		writer = new FileWriter(file,true); 
		try{
			writer.write("<HTML>");
			writer.write("<HEAD>");
			/*writer.write("<script type='text/javascript' src='excanvas.js'></script>");
			writer.write("<script type='text/javascript' src='piecanvas.js'></script>");*/
			writer.write("</HEAD>");
			writer.write("<BODY>");
			writer.write("<TABLE border=0 cellSpacing=1 cellPadding=1 width='100%'>");
			writer.write("<TR><TD align='left'><img src='file:///"+workingDir+"/Logos/"+config.getProperty("Clint_logo")+".jpeg' alt='"+config.getProperty("Clint_logo")+"' height='75' width='150'> </TD>");
			writer.write("<TD align='center'><H4 align='center'><font color='660066' face='arial' color='#E0E0E0' size=5><b>Execution Summary Report</b></H4></TD>");
			
			writer.write("<TABLE border=1 align='center' cellSpacing=1 cellPadding=1 width='35%' font='arial'>");		 
			//writer.write("<tr><td colspan='2' align='center'><FONT COLOR='#153E7E' FACE='Arial' SIZE=2><b>Example</b></td></tr>");
			writer.write("<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>Build Version</b></td>");
			writer.write("<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"+config.getProperty("BuildVersion")+"</b></td></tr>");
			writer.write("<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>Run ID</b></td>");
			writer.write("<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"+config.getProperty("RunID")+"</b></td></tr>");
			writer.write("<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>Run Date&Time</b></td>");
			writer.write("<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"+Accessories.timeStamp()+"</b></td></tr>");
			writer.write("</table><hr/>");

			writer.write("<TABLE border=1 cellSpacing=1 cellPadding=1 width='35%' font='arial'>");		 
			writer.write("<tr><td colspan='2' align='center'><FONT COLOR='#153E7E' FACE='Arial' SIZE=2><b>Environment</b></td></tr>");
			writer.write("<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>Host Name</b></td>");
			writer.write("<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"+Accessories.getHostName()+"</b></td></tr>");
			writer.write("<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>OS Name</b></td>");
			writer.write("<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"+System.getProperty("os.name")+"</b></td></tr>");
			writer.write("<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>OS Version</b></td>");
			writer.write("<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"+System.getProperty("os.version")+"</b></td></tr>");

			writer.write("</table><br/>");

			//Create Summary report Second table
			writer.write("<table>");
			writer.write("<TABLE border=1 cellSpacing=1 cellPadding=1 width='35%' font='arial' align='left' >");		 
			writer.write("<tr><td colspan='2' align='center'><FONT COLOR='#153E7E' FACE='Arial' SIZE=2><b>Execution Status</b></td></tr>");

			int pCount=0;
			int fCount=0;

			Iterator<Entry<String, String>> iterator=map.entrySet().iterator();
			while(iterator.hasNext()) 
			{
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				String value= mapEntry.getValue().toString();
				if(value.equals("PASS"))
				{
					pCount+=1; 
				}
				else
				{
					fCount+=1;       			  
				}

				writer.write("</tr>");
			}

			writer.write("<tr>");
			writer.write("<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>Suite</b></td>");
			writer.write("<td width=150 align='left'><FONT FACE='Arial' SIZE=1.85><b>"+currentSuit+"</b></td></tr>");
			writer.write("<tr><td width=150 align='left'bgcolor='#153E7E' ><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>No. of  Scripts Executed</b></td>");
			writer.write("<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"+map.size()+"</b></td></tr>");
			writer.write("<tr>");
			writer.write("<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>No. of  Scripts Passed</b></td>");
			writer.write("<td width=150 align='left' color='#E0E0E0'><FONT FACE='Arial' SIZE=1.85><b>"+ pCount +"</b></td></tr>");
			writer.write("<tr>");
			writer.write("<tr><td width=150 align='left'bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>No. of  Scripts Failed</b></td>");
			writer.write("<td width=150 align='left'color='red' ><FONT FACE='Arial' SIZE=1.85><b>"+ fCount +"</b></td></tr>");
			writer.write("</tr></table>");	 
			writer.write("<br/><br/><br/>");
			writer.write("</table><br/><br/><br/>");

			//Create Summary report third table
			writer.write("<TABLE border=1 cellSpacing=1 cellPadding=1 width='50%' font='arial'>");		 
			writer.write("<tr><td colspan='3' align='center'><FONT COLOR='#153E7E' FACE='Arial' SIZE=2><b>Summary Report</b></td></tr>"); 
			writer.write("<tr><td align='center'><FONT COLOR='#153E7E' FACE='Arial' SIZE=2.25><b>Module</b></td>" +
					"<td align='center'><FONT COLOR='#153E7E' FACE='Arial' SIZE=2.25><b>Test Case</b></td>" +
					"<td align='center'><FONT COLOR='#153E7E' FACE='Arial' SIZE=2.25><b>Status</b></td></tr>");

			Iterator<Entry<String, String>> iterator1=map.entrySet().iterator();
			while(iterator1.hasNext())
			{

				Map.Entry mapEntry1 = (Map.Entry) iterator1.next();
				String[] key = mapEntry1.getKey().toString().split(":");
				String value= mapEntry1.getValue().toString();
				writer.write("<tr>");
				writer.write("<td><FONT color=#153e7e size=1 face=Arial><B>"+key[0]+"</B></td>");
				writer.write("<td><FONT color=#153e7e size=1 face=Arial><B>"+key[1]+"</B></td>");

				if(value.equals("PASS"))
				{
					writer.write("<TD width='30%' bgcolor=green align=center><FONT color=white size=1 face=Arial><B><a href='Results_"+BaseTest.timeStamp+".html#"+key+"'>"+value+"</a></B></td>"); 
				}
				else
				{
					writer.write("<TD width='30%' bgcolor=red align=center><FONT color=white size=1 face=Arial><B><a href='Results_"+BaseTest.timeStamp+".html#"+key+"'>"+value+"</a></B></td>");	
					
				}

				writer.write("</tr>");
			}

			writer.write("</table></BODY></HTML>");
			writer.flush();  
			writer.close();
			map.clear();

		}
		catch (Exception e) {
			//System.out.println(e);
			writer.flush();
			writer.close();
		}
	}

	//Crate a report file
	public static void htmlCreateReport() throws Exception
	{
		map.clear();
		File file = new File(BaseTest.filePath()+"Results_"+BaseTest.timeStamp+".html");//"Results.html"

		if(file.exists())
		{
			file.delete();
		}

	}
	public static void onSuccess(String strStepName,String strStepDes){
		File file = new File(BaseTest.filePath()+"/"+"Results_"+BaseTest.timeStamp+".html");//"SummaryReport.html"
		Writer writer = null;



		try{
			writer = new FileWriter(file,true); 
			writer.write("<tr><TD width='30%'><FONT color=#153e7e size=1 face=Arial><B>" + strStepName
					+ " </b></td><TD><FONT color=#153e7e size=1 face=Arial><B>" + strStepDes + "</B></td>"
					+ " </td><TD width='10%' bgcolor=green align=center><FONT color=white size=1 face=Arial><B>" + "Passed" + "</td></tr>");

			writer.flush();
			writer.close();

			if(!map.get(packageName+":"+tc_name).equals("FAIL"))
			{
				map.put(packageName+":"+tc_name, "PASS");
			}

		}
		catch(Exception e){

		}
	}

	public static void onFailure(String strStepName,String strStepDes){
		File file = new File(BaseTest.filePath()+"/"+"Results_"+BaseTest.timeStamp+".html");//"SummaryReport.html"
		Writer writer = null;

		try{
			writer = new FileWriter(file,true); 
			writer.write("<tr><TD width='30%'><FONT color=#153e7e size=1 face=Arial><B>" + strStepName
					+ " </b></td><TD><FONT color=#153e7e size=1 face=Arial><B>" + strStepDes + "</B></td>"
					+ " </td><TD width='10%' bgcolor=red align=center><FONT color=white size=1 face=Arial><B><a href="+strStepDes.replace(" ", "_").replace(":", "_")+"_"+BaseTest.timeStamp+".jpeg>" + "Fail" + "</a></td></tr>");

			writer.flush();
			writer.close();

			map.put(packageName+":"+tc_name, "FAIL");
		}
		catch(Exception e){

		}
	}

	public static void testHeader(String strTestName){
		Writer writer=null;
		try{
			File file = new File(BaseTest.filePath()+"Results_"+BaseTest.timeStamp+".html");//"Results.html"
			writer = new FileWriter(file,true);
			writer.write("<div name='"+tc_name+"'><TABLE border=1 cellSpacing=1 cellPadding=1 width='80%' font='arial'>");
			writer.write("<tr ><td colspan=3><H4 align=center><FONT color=black size=4 face=Arial><B>" +strTestName + "</B></H4></td></tr>");
			writer.write("<tr><TD bgColor='#153e7e' width='30%' align=middle><FONT color='#e0e0e0' size=2 face='Arial'><B>Step</b></td>" 						
					+"<TD bgColor=#153e7e align=middle><FONT color=#e0e0e0 size=2 face=Arial><B>" +"Description</b></td>"						 
					+"<TD bgColor=#153e7e width='10%' align=middle><FONT color=#e0e0e0 size=2 face=Arial><B>Status</b></td></tr>");
			map.put(packageName+":"+tc_name, "status");
		}
		catch(Exception e)
		{

		}
		finally
		{
			try{
				writer.flush();
				writer.close();
			}
			catch(Exception e){

			}
		}
	}
}
