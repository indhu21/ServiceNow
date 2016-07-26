package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewTest2 {
	public static String  input = "488 Hours 9 Minutes 2 Seconds";

	public static void main(String[] args) throws ParseException {
		
//        int output = extractInt(input);

//        System.out.println("input [" + input + "], output [" + output + "]");
        
//        System.out.println(Integer.getInteger(input));
	/*	if(input.contains("Hours")){
			String hour=input.substring(0, input.indexOf("Hours"));
			System.out.println(hour);
					
		}
		else if(input.contains("Minutes")){
			String min=input.substring(input.indexOf("Hours"), input.indexOf("Minutes"));
			System.out.println(min);
					
		}
		String[] name=input.split("\\d+");
		System.out.println(name[1]);*/
		
		
//		Scanner in = new Scanner("Hello123").useDelimiter("[^0-9]+");
//		String value = "Hello123";
		String intValue = input.replaceAll("[^0-9]", " ");
		String[] values=intValue.split(" ");
		StringBuilder builder = new StringBuilder();
		List<String> list=new ArrayList<String>();
		for(String s : values) {
			s=s.trim();
			if(!s.isEmpty()){
				builder.append(s).append(",");
				list.add(s);
				System.out.println(s);}
		}
		
		System.out.println(list);
		String date=list.get(list.size()-3)+":"+list.get(list.size()-2)+":"+list.get(list.size()-1);
		
		System.out.println(date);
		
		SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
		Date date1 = parser.parse(date);
		Date date2 = parser.parse("487:20:01");
	

	    if(date1.after(date2)||date2.equals(date1))
	    	System.out.println("Pass");
	    else
	    	System.out.println("FAIL");
		
		
//		System.out.println(intValue);
		
    }

    //
    // Parses first group of consecutive digits found into an int.
    //
    public static int extractInt(String str) {
        Matcher matcher = Pattern.compile("\\d+").matcher(str);

        if (!matcher.find())
            throw new NumberFormatException("For input string [" + str + "]");

        return Integer.parseInt(matcher.group());
    }
   }