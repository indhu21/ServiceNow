package utils_DataCreation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//import utils_DataCreation.QueryDB_JSON;

public class ModifyJSON {
	
	private static String JSONString = null; 
	public static String Modify_JSON(String element,String newValue,String JSON)
	{
		try{
    		
			//JSONParser parse=
	        JSONParser parser = new JSONParser();
	        
	       // String s = "{\"sys_id\":\"abcdef1234567890\",\"os_address_width\":\"\",\"firewall_status\":\"Intranet\",\"skip_sync\":\"false\",\"operational_status\":\"Operational\",\"os_service_pack\":\"\",\"cpu_core_thread\":\"\",\"cpu_manufacturer\":\"\",\"sys_updated_on\":\"2015-01-26 06:05:30\",\"name\":\"DEMO CI\",\"assigned\":\"2014-01-25 23:00:00\",\"purchase_date\":\"\",\"subcategory\":\"Computer\",\"default_gateway\":\"\",\"cd_speed\":\"\",\"short_description\":\"\",\"virtual\":\"false\",\"chassis_type\":null,\"floppy\":null,\"assignment_group\":\"\",\"managed_by\":\"\",\"os_domain\":\"\",\"last_discovered\":\"\",\"can_print\":\"false\",\"sys_class_name\":\"UNIX Server\",\"cpu_count\":\"12\",\"manufacturer\":\"\",\"po_number\":\"\",\"checked_in\":\"\" }";
	       // String value=QueryDB_SQL.GetJSON_Object("D14");
	        
	        JSONObject obj2 = (JSONObject)parser.parse(JSON);
	                
	      //  System.out.println("Before the change:" + obj2);
	        
	        //set the name
	        obj2.put(element, newValue);
	        //remove the sys_id
	        obj2.remove("sys_id");
	        
	     //   System.out.println("After  the change:" + obj2);
	        JSONString=obj2.toString();
	        }
	        catch(ParseException pe){
	            
	        	System.out.println("error");
	        	System.out.println("position: " + pe.getPosition());
	            System.out.println(pe);
	        	
	        }
		//JSONObject obj2;
		return JSONString;
	    
	}
	
	
	public static String getName()
    {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		String Name= "Test_"+dateFormat.format(date);
		//System.out.println(Name);


		return Name;
		
	}

	public static void main(String args[])
	{
		getName();
	}
}
