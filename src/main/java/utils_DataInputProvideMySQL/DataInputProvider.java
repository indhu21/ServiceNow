package utils_DataInputProvideMySQL;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import testng.SuiteMethods_ServiceNowFrontEnd;
import utils_DataInputProvideMySQL.QueryDB;
import wrapper.GenericWrappers;

public class DataInputProvider  extends  SuiteMethods_ServiceNowFrontEnd{
	


		static int  size_of_array=0;
		static int count1=0;
		static int count2=0;
		static int  size_of_array_json=0;
		
		
		//****************************************************************//
		//Function :
		// a. Get count of Records from Mapping table based on type
		// b. Prepares an array of Definition Id's from Mapping table
		// c. Two Dimensional array to store the values sorted based on the Parameters
		//****************************************************************//
		
		public static String[][] GetArraySize(String Testcase_ID,String[] parameters)
		{

			count1=QueryDB.getcountofRecordsinMappingTable(Testcase_ID,"simple"); // Count of Records available for specific type

			size_of_array=count1;

			String[] DefinitionID=GetDefinitionID(Testcase_ID,"simple",count1);   // Prepares an array of definition ids
			
			String[][] values=getValues(DefinitionID,parameters); // prepares an two dimensional array of values and parameters
			
			return values; 
			
		}
		
		//************************************************************************************************/
		public static String[] GetJSONArraySize(String Testcase_Name)
		{
		count2=QueryDB.getcountofRecordsinMappingTable(Testcase_Name,"JSON");
		
		String[] JSONDefinitionID=GetJSONDefinitionID(Testcase_Name);  
		
		//size_of_array_json=count2+1;
		
		return JSONDefinitionID;
		}
		
		//************************************************************************************************/
		public static String[] GetJSON(String TestCase,String Type)
		
		{
			String[] jsonData=new String[2] ;
			String[] JSONDefinitionIDs=GetJSONArraySize(TestCase);
			for(int i=0;i<JSONDefinitionIDs.length;i++)
			{
				String status=QueryDB.sortDefinitionID(JSONDefinitionIDs[i],Type);
				if(status.equalsIgnoreCase("true"))
				{
					jsonData[0]=QueryDB.GetJSON_Object(JSONDefinitionIDs[i]);
					System.out.println("Rest"+jsonData[0]);
					jsonData[1]=QueryDB.GetTableName(JSONDefinitionIDs[i]);
					System.out.println("Rest"+jsonData[1]);
					break;
					
				}
			}
			return jsonData;
			
		}
		//************************************************************************************************/	
		//****************************************************************//
		//Function :
		// a. Function actually been called from Test cases
		//****************************************************************//
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
		//************************************************************************************************/
		//****************************************************************//
		//Function :
		// a. Receives test case , Type and count(size of array)
		// b. Returns the array of specific type definition id's with the size same as of count
		//****************************************************************//
		public static String[] GetDefinitionID(String Testcase_ID,String type,int count)
		{
			String[] DefinitionID=QueryDB.GetDefinitionIDfromMappingTable(Testcase_ID,type,count);

			return DefinitionID;
		}

		//************************************************************************************************/
		public static String[] GetJSONDefinitionID(String Testcase_ID)
		{
			String[] DefinitionID=QueryDB.GetDefinitionIDfromMappingTable(Testcase_ID,"JSON",count2);

			return DefinitionID;
		}

		
		//********************************************************************************************************************//
		//Function :
		// a. Function receives the array of definition id's and Parameters 
		// b. Function Reads the definition id's value and element from the definition table and stores the same in a hash map
		// c. Hash map is queried for the keys and values of the keys are stored n a two dimensional array with row size as 1
		// d. Hash map keys are nothing but the element values being queried from definition table
		//******************************************************************************************************************//
		//************************************************************************************************/
		public static String[][] getValues(String[] DefinitionID,String[] parameters){

			String[][] result = new String[1][DefinitionID.length]; // Two Dimenshional array
			HashMap<String,String> values=QueryDB.GetValuesandParametersfromDefinitionTable(DefinitionID); // Hash map is returned from the query . Includes key as elements and values as the values of the keys
			try 
			{
				for(int i=0;i<parameters.length;i++)
				{
					result[0][i]=(String) values.get(parameters[i]); // Prepares the two dimensional aray based on parameters sequence passed
					System.out.println("value is "+result[0][i]);
				}
			}catch(Exception e)
			{
				System.out.println(e);
				System.out.println("All the values does not exist in the database");	
			}
			return result;
		}
		//************************************************************************************************/
		//********************************************************************************************************************//
		//Function :
		// a. Function receives the array of definition id's  
		// b. Function Reads the identifier,identifier value,field type,value and data_element  values 
		//******************************************************************************************************************//
		
		public static HashMap<String, List<String>> getValues(String[] DefinitionID){

			 // Two Dimenshional array
			HashMap<String,List<String>> values=QueryDB.GetValuesForDefinitionIDs(DefinitionID); 
			return values;
		
    		}
		
		//************************************************************************************************/
		//****************************************************************//
		//Function :
		// a. Get count of Records from Mapping table based on type
		// b. Prepares an array of Definition Id's from Mapping table
		// c. Prepare the hashmap of identifier,identifier value,field type,value and data_element  values 
		//****************************************************************//
		
		public static HashMap<String, List<String>> GetData(String Testcase_ID)
		{

			count1=QueryDB.getcountofRecordsinMappingTable(Testcase_ID,"simple"); // Count of Records available for specific type

			size_of_array=count1;

			String[] DefinitionID=GetDefinitionID(Testcase_ID,"simple",count1);   // Prepares an array of definition ids
			
			HashMap<String,List<String>> values=getValues(DefinitionID); // prepares an hash map 
			
			return values; 
			
		}
		//************************************************************************************************/
		//****************************************************************//
		//Function :
		// a. Extract the values from the List
		//****************************************************************//
		
		public static String GetInputDataValue(HashMap<String, List<String>> values,String valuetobeFetched)
		
		{
			
			 List<String> result= values.get(valuetobeFetched); // Prepares the two dimensional aray based on parameters sequence passed
			 System.out.println("value is "+result.get(0));
		
			return result.get(0);
			
		}
		//************************************************************************************************/
   }
		
		
		
		

