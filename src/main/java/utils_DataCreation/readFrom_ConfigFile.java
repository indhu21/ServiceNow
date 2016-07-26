package utils_DataCreation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class readFrom_ConfigFile {
	
	
	public static String[] GetCredentials_TESMDEMO()throws FileNotFoundException, IOException {

		String[] credetails=new String[3];

		//Reading properties file in Java example
		Properties props = new Properties();
		
		FileInputStream fis = new FileInputStream("/home/ubuntu/ServiceNow/Rest_credetials_demo.xml");
		
//	    	FileInputStream fis = new FileInputStream("D:/ServiceNow/Rest_credetials_demo.xml");
	
		props.loadFromXML(fis);

		//reading proeprty
		
			credetails[0] = props.getProperty("CreateData_UserID");
			credetails[1]=props.getProperty("CreateData.password");
			credetails[2]=props.getProperty("Instance");
		
		//  return the user id and password
		return credetails;

	}
	
	// get the Instance and Login credentials for Gilead
	public static String[] GetCredentials_Delete_sparc()throws FileNotFoundException, IOException {

		String[] credetails=new String[3];

		//Reading properties file in Java example
		Properties props = new Properties();
	    FileInputStream fis = new FileInputStream("/home/ubuntu/ServiceNow/Rest_Credentials_sparc.xml");
//		FileInputStream fis = new FileInputStream("D:/ServiceNow/Rest_Credentials_sparc.xml");
		//loading properites from properties file
		props.loadFromXML(fis);

		//reading proeprty
		
			credetails[0] = props.getProperty("DeleteData_UserID");
			credetails[1]=props.getProperty("DeleteData.password");
			credetails[2]=props.getProperty("DeleteData.Instance");
		
		//  retun the user id and password
		return credetails;

	}
	
	public static String[] GetCredentials_Delete_DEMO()throws FileNotFoundException, IOException {

		String[] credetails=new String[3];

		Properties props = new Properties();
		FileInputStream fis = new FileInputStream("/home/ubuntu/ServiceNow/Rest_credetials_demo.xml");
//		FileInputStream fis = new FileInputStream("D:/ServiceNow/Rest_credetials_demo.xml");
		props.loadFromXML(fis);

		//reading proeprty
		
			credetails[0] = props.getProperty("DeleteData_UserID");
			credetails[1]=props.getProperty("DeleteData.password");
			credetails[2]=props.getProperty("DeleteData.Instance");
		
		//  retun the user id and password
		return credetails;

	}
	
	
	
	// Get the Table Name 
	public static String GetCMDBSparc_Table_names(String Table_type)throws FileNotFoundException, IOException {

		String Table_Name;

		//Reading properties file in Java example
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream("/home/ubuntu/ServiceNow/SparcCMDB_TableNames.xml");
//		FileInputStream fis = new FileInputStream("D:/ServiceNow/SparcCMDB_TableNames.xml");
		
		//loading properites from properties file
		props.loadFromXML(fis);

		Table_Name =props.getProperty(Table_type);
				
		//  retun the user id and password
		return Table_Name;

	}
	
	
}
