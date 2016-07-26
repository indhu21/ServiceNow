package utils_DataCreation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import wrapper.GenericWrappers;



public class DataInputProvide_CreateData {


	static int  size_of_array=0;
	static int count1=0;
	static int count2=0;
	static int  size_of_array_json=0;
	
	
	public static String[] GetJSONArraySize(String Testcase_Name)
	{
	count2=QueryDB_CreateData.getcountofJSONRecordsinMappingTable(Testcase_Name);
	
	String[] JSONDefinitionID=GetJSONDefinitionID(Testcase_Name);  
	
	//size_of_array_json=count2+1;
	
	return JSONDefinitionID;
	}
	
	
	public static String[] GetJSON(String TestCase,String Type)
	
	{
		String[] jsonData=new String[2] ;
		String[] JSONDefinitionIDs=GetJSONArraySize(TestCase);
		for(int i=0;i<JSONDefinitionIDs.length;i++)
		{
			String status=QueryDB_CreateData.getDefinitionIDofSpecificType(JSONDefinitionIDs[i],Type);
			if(status.equalsIgnoreCase("true"))
			{
				jsonData[0]=QueryDB_CreateData.GetJSON_Object(JSONDefinitionIDs[i]);
				System.out.println("Rest"+jsonData[0]);
				jsonData[1]=QueryDB_CreateData.GetTableName(JSONDefinitionIDs[i]);
				System.out.println("Rest"+jsonData[1]);
				break;
				
			}
		}
		return jsonData;
		
	}
	
	

	
	public static String[] GetJSONDefinitionID(String Testcase_ID)
	{
		String[] DefinitionID=QueryDB_CreateData.GetJSONDefinitionIDfromMappingTable(Testcase_ID,count2);

		return DefinitionID;
	}

		 
	    
	}
