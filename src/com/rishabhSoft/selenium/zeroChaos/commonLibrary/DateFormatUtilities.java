package com.rishabhSoft.selenium.zeroChaos.commonLibrary;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** @author Abhishek Singh
 * Costume Methods of different Date format. 
 * */

public class DateFormatUtilities {
	
	public static String sYear;
	public static String sMonth = "";
	public static String sDay;
	
	// Separate Year, Month and Day from Date
	public static void separateYearMonthDayFromDate(String dateString) throws ParseException{

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = (Date)formatter.parse(dateString);
		DateFormat year = new SimpleDateFormat("yyyy");
		DateFormat month = new SimpleDateFormat("M");
		DateFormat day = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
		sYear = year.format(date);
		sMonth = month.format(date);
		sDay = day.format(date);
		System.out.println("The day is: " + sDay);
	}
	
	// Get Todays Date in '07/26/2013' Format
	public static String getCurrentDate() {
 	 
	  	Calendar currentDate = Calendar.getInstance();
	  	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	  	String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	
	// Get Todays Date in '7/26/2013' Format
	public static String getTodaysDate() {
	 	 
	  	Calendar currentDate = Calendar.getInstance();
	  	SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
	  	String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	// get the date with Day in 'Friday, July 26, 2013' format
	public static String getCurrentDayWithDate() {
	 	 
	  	Calendar currentDate = Calendar.getInstance();
	  	SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
	  	String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	// get the date with time in '2013.Jul.26 02.58.55.PM' format
	public static String getCurrentDateTime() {
	 	 
	  	Calendar currentDate = Calendar.getInstance();
	  	SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MMM.dd hh.mm.ss.a");
	  	String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	// get the date with time in '7/26/2013 14:24' format
	public static String getTodaysDateTime() {
	 	 
	  	Calendar currentDate = Calendar.getInstance();
	  	SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy HH:mm");
	  	String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	// get the date with time in 'July 26, 2013 02:16:45 PM' format
	public static String getCurrentDateTimeFormatted() {
	 	 
	  	Calendar currentDate = Calendar.getInstance();
	  	SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss a");
	  	String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	// get the before Date of number of day
	public static String getBeforeDate(int beforeDay) {
	 	 
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DATE, -beforeDay);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	// get the after Date of number of day
	public static String getAfterDate(int afterDay) {
	 	 
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DATE, afterDay);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	// get the Yesterday Date
	public static String getYesterdayDate() {
	 	 
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DATE, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	// get the Tomorrow Date
	public static String getTomorrowDate() {
	 	 
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DATE, 1);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
		
	public static void main(String a[]) throws ParseException{
		//DateFormatUtilities.separateYearMonthDayFromDate("07/26/2013");
		//System.out.println(DateFormatUtilities.getCurrentDateTimeFormatted());
	}
}
