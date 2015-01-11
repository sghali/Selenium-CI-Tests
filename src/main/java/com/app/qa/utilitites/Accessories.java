package com.app.qa.utilitites;



import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Accessories {

	//	return date
	public static String dateStamp(){
		DateFormat dateFormat = new SimpleDateFormat();
		Date date = new Date();
		return dateFormat.format(date).substring(0,7);
	}
	//return time and date
	public static String timeStamp(){
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime()).toString();
	}
	// return environmental details
	public static String osEnvironment(){

		return "Current suit exicuted on : "+System.getProperty("os.name")
				+"/version : "+System.getProperty("os.version")
				+"/Architecture : "+System.getProperty("os.arch");
	}
	public static String getHostName() throws UnknownHostException{
		InetAddress addr = InetAddress.getLocalHost();
		String hostname = addr.getHostName();

		return hostname;
	}
}
