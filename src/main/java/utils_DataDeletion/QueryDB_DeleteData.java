package utils_DataDeletion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class QueryDB_DeleteData {

	static String dbName;
	static String serverip;
    protected static String url;
    static String prefix;
    static String username;
    static String password;
    static String Testinstanceid;
    static String PDFName;

    
public static void setUp() {
	    // Load the property file
		Properties prop = new Properties();
        //	prop.load(new FileInputStream(getAbsolutePath()+"config.properties"));	
		
		dbName = "punchit";
		serverip = "52.88.27.203";
		username = "punch";
		password = "qwerty123";
		url = "jdbc:mysql://"+serverip+":3306/"+dbName+"?user="+username+"&password="+password;	
	}


// get the Record count based on type and test case from MApping table
public static int getcountofRecordsinMappingTable(String TestcaseID,String type)
{
		Connection conn = null;
	    Statement stmt = null;
	    ResultSet result = null;
	    int count = 0;
	  
	    setUp(); // initialize 

	    String driver = "com.mysql.jdbc.Driver";
	     try {
	        Class.forName(driver).newInstance();
	        conn = DriverManager.getConnection(url);
	        
	        stmt = conn.createStatement();
	        result = null;

	        result = stmt.executeQuery("SELECT defination_id as count FROM x_tori2_automated_test_case_definition_mapping where test_case_id = '"+TestcaseID+"'"+"and type = '"+type+"'");
	                                  
	        while(result.next()){
	        	count++;
	        	
	        }
	 
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally{
	    	try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		return count;
	}


 // Get the definition id based on the type from MApping table
 public static String[] GetDefinitionIDfromMappingTable(String TestCaseID,String Type,int Array_size) {
	
	Connection conn = null;
    Statement stmt = null;
    ResultSet result = null;
    String[] DefinitionID = new String[Array_size] ;
    int i=0;
    
    setUp(); // initialize 

    String driver = "com.mysql.jdbc.Driver";
     try {
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url);
        
        stmt = conn.createStatement();
        result = null;

        result = stmt.executeQuery("SELECT defination_id AS DefinitionID FROM x_tori2_automated_test_case_definition_mapping where test_case_id = '"+TestCaseID+"'"+"and type = '"+Type+"'");
        while(result.next()){
        	DefinitionID[i] = result.getString("DefinitionID");
        	  System.out.println(DefinitionID);
        	  i++;
        }
            
    } catch (Exception e) {
        e.printStackTrace();
    } finally{
    	try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	return DefinitionID;
}
 
// Get Values from Definition table
 public static String[] GetValuesfromDefinitionTable(String[] DefinitionID) {
		
		Connection conn = null;
	    Statement stmt = null;
	    ResultSet result = null;
	    String[] values = new String[DefinitionID.length];
	    
	    setUp(); // initialize 
	     
	    String driver = "com.mysql.jdbc.Driver";
	     try {
	        Class.forName(driver).newInstance();
	        conn = DriverManager.getConnection(url);
	        
	        stmt = conn.createStatement();
	        result = null;
	        for(int i=0;i<DefinitionID.length;i++)
	        {
	        result = stmt.executeQuery("SELECT value FROM x_tori2_automated_test_case_data_definition where defination_id = '"+DefinitionID[i]+"'");                        
	        while(result.next()){
        	values[i]=result.getString("value");
	       	        }
	        }
	       	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally{
	    	try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		return values;
	}

 public static void main(String args[]){
	int count= getcountofRecordsinMappingTable("LC_CreatePreRequisite","CI") ;
	System.out.println("count is"+count);
	String[] Definitionid=GetDefinitionIDfromMappingTable("LC_CreatePreRequisite","CI",count);
	for(int i=0;i<Definitionid.length;i++){
		System.out.println("Definition id is"+Definitionid[i]);
	}
	String[] values=GetValuesfromDefinitionTable(Definitionid);
	for(int i=0;i<values.length;i++){
		System.out.println("Value are id"+values[i]);
	}
 }
 
 
 
}
