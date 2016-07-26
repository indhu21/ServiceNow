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



public class DataInputProvide_MySQL {


	static int  size_of_array=0;
	static int count1=0;
	static int count2=0;
	static int  size_of_array_json=0;
	
	public static String[][] GetArraySize(String Testcase_ID,String[] parameters)
	{

		count1=QueryDB_SQL.getcountofsimpleRecordsinMappingTable(Testcase_ID);

		size_of_array=count1;

		String[] DefinitionID=GetDefinitionID(Testcase_ID);   
		
		String[][] values=getValues(DefinitionID,parameters);
		
		return values; 
		
	}
	
	public static String[] GetJSONArraySize(String Testcase_Name)
	{
	count2=QueryDB_SQL.getcountofJSONRecordsinMappingTable(Testcase_Name);
	
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
			String status=QueryDB_SQL.sortDefinitionID(JSONDefinitionIDs[i],Type);
			if(status.equalsIgnoreCase("true"))
			{
				jsonData[0]=QueryDB_SQL.GetJSON_Object(JSONDefinitionIDs[i]);
				System.out.println("Rest"+jsonData[0]);
				jsonData[1]=QueryDB_SQL.GetTableName(JSONDefinitionIDs[i]);
				System.out.println("Rest"+jsonData[1]);
				break;
				
			}
		//	jsonData[1]
			//ModifyJSON.Modify_JSON(element, newValue, JSON);Modify_JSON	
		}
		return jsonData;
		
	}
	
	
	public static String[][] getDataValues(String Testcase,String[] parameters) throws IOException{

		String[][] data = null;

		try {
			data=GetArraySize(Testcase,parameters);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				

		return data;

	}

	public static String[] GetDefinitionID(String Testcase_ID)
	{
		String[] DefinitionID=QueryDB_SQL.GetSimpleDefinitionIDfromMappingTable(Testcase_ID,count1);

		return DefinitionID;
	}

	public static String[] GetJSONDefinitionID(String Testcase_ID)
	{
		String[] DefinitionID=QueryDB_SQL.GetJSONDefinitionIDfromMappingTable(Testcase_ID,count2);

		return DefinitionID;
	}

	public static String[][] getValues(String[] DefinitionID,String[] parameters)
	{
		//String[] values=new String[parameters.length];
		String[] array=new String[2];
		String[][] result = new String[1][size_of_array];
		HashMap<String,String> hm=new HashMap<String,String>();  
	
		for(int l=0;l<DefinitionID.length;l++)
		{
			array=QueryDB_SQL.GetValuesfromDefinitionID(DefinitionID[l]);
    		if(array.length!=0)
     		{
      			hm.put(array[0],array[1]);  
			}
		}
		
    		try 
		{
			for(int i=0;i<parameters.length;i++)
			{
				result[0][i]=(String) hm.get(parameters[i]);
				System.out.println("value is "+result[0][i]);
			}
		}catch(Exception e)
		{
			System.out.println("All the values does not exist in the database");	
		}
		
		return result;

	} 
	 
	    public static void main(String args[]) throws IOException
	    { 
	    	GetJSON("DataCreationDEMOLC_Stry000000_Tc01_1","CI");
	    }
	    
	}
