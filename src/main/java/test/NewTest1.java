package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.testng.annotations.Test;

public class NewTest1 {
  @Test
  public void test1() throws ParseException {
	/*  Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

	  DateFormat formatter = new SimpleDateFormat("dd MM yyyy HH:mm:ss");    
	  formatter.setTimeZone(TimeZone.getTimeZone("US/Pacific"));  

	  String newZealandTime = formatter.format(calendar.getTime());
	  System.out.println(newZealandTime);*/
	  
	 /* SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
		Date date1 = parser.parse("00:20:00");
		Date date2 = parser.parse("00:21:00");
		
		
		    if(date1.after(date2)||date2.equals(date1))
		    	System.out.println("PASS");
		    else
		    	System.out.println("FAIL");*/
	  
	 /* 
	  String time="488 Hours 9 Minutes 2 Seconds";
	  time=time.replace(" ", "");
	  System.out.println(time);
		String[] times=time.split([^1-0]);
		System.out.println(times[0]);
		System.out.println(times[1]);
		System.out.println(times[2]);*/
		
	  }
}
