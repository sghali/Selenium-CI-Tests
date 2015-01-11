package com.app.qa.utilitites;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

	//Bblog Instantiation
	//protected static BbLog bbLog = new BbLog(new DateTimeUtil().getClass());
	
	/**
	 * Static method to format the date in specified format
	 * @param dateObj
	 * @param format
	 * 		See SimpleDateFormat API for formats
	 * @return
	 */
	public static String toStringFormat(Date dateObj, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
		String dateStr = sdf.format(dateObj);

		return dateStr;		
	}
	
	/**
	 * To pause the execution for a specific period.
	 * @param interval
	 * @throws InterruptedException 
	 */
	public static void waitInterval(long interval) throws InterruptedException {

		try {
			Thread.currentThread().sleep(interval);
		} catch (InterruptedException e) {
			//bbLog.error("Error occured while waiting  for interval time to elapse.");
			throw new InterruptedException("Interval time is elapsed! "+e);
		}
	}
}
