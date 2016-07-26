package utils_DataCreation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class QueryDB_SQL {
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
	
// Get Definition from Map table	
public static String[] GetDefinitionIDfromMappingTable(String TestCaseID,int count) {
		
    	Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
        String[] DefinitionID = new String[count] ;
        int i=0;
        
        setUp(); // initialize 

        String driver = "com.mysql.jdbc.Driver";
         try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url);
            
            stmt = conn.createStatement();
            result = null;

            result = stmt.executeQuery("SELECT defination_id AS DefinitionID FROM x_tori2_automated_test_case_definition_mapping where test_case_id = '"+TestCaseID+"'");
            while(result.next()){
            	DefinitionID[i] = result.getString("DefinitionID");
            	  System.out.println(DefinitionID);
            	  i++;
            }
           // System.out.println(DefinitionID);
           
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

public static String[] GetSimpleDefinitionIDfromMappingTable(String TestCaseID,int string) {
	
	Connection conn = null;
    Statement stmt = null;
    ResultSet result = null;
    String[] DefinitionID = new String[string] ;
    int i=0;
    
    setUp(); // initialize 

    String driver = "com.mysql.jdbc.Driver";
     try {
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url);
        
        stmt = conn.createStatement();
        result = null;

        result = stmt.executeQuery("SELECT defination_id AS DefinitionID FROM x_tori2_automated_test_case_definition_mapping where test_case_id = '"+TestCaseID+"'"+"and type = 'simple'");
        while(result.next()){
        	DefinitionID[i] = result.getString("DefinitionID");
        	  System.out.println(DefinitionID);
        	  i++;
        }
       // System.out.println(DefinitionID);
       
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

public static String[] GetJSONDefinitionIDfromMappingTable(String TestCaseID,int string) {
	
	Connection conn = null;
    Statement stmt = null;
    ResultSet result = null;
    String[] DefinitionID = new String[string] ;
    int i=0;
    
    setUp(); // initialize 

    String driver = "com.mysql.jdbc.Driver";
     try {
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url);
        
        stmt = conn.createStatement();
        result = null;

        result = stmt.executeQuery("SELECT defination_id AS DefinitionID FROM x_tori2_automated_test_case_definition_mapping where test_case_id = '"+TestCaseID+"'"+"and type = 'JSON'");
        while(result.next()){
        	DefinitionID[i] = result.getString("DefinitionID");
        	  System.out.println(DefinitionID);
        	  i++;
        }
       // System.out.println(DefinitionID);
       
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




// get definition id from definition table   
public static String[] GetDefinitionIDfromDefinitionTable(String TestCaseID,int count) {
	
	Connection conn = null;
    Statement stmt = null;
    ResultSet result = null;
    String[] DefinitionID = new String[count];
    int i=0;
    setUp(); // initialize 

    String driver = "com.mysql.jdbc.Driver";
     try {
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url);
        
        stmt = conn.createStatement();
        result = null;

        result = stmt.executeQuery("SELECT defination_id AS DefinitionID FROM x_tori2_automated_test_case_data_definition where test_case_id = '"+TestCaseID+"'");
        while(result.next()){
        	DefinitionID[i] = result.getString("DefinitionID");
        	i++;
        	  System.out.println(DefinitionID);
        }
       // System.out.println(DefinitionID);
       
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

// Get the  datadefinition values 
public static String[] GetValuesfromDefinitionID(String DefinitionID) {
	
	Connection conn = null;
    Statement stmt = null;
    ResultSet result = null;
    String[] values = new String[2];
    
    setUp(); // initialize 
     
    String driver = "com.mysql.jdbc.Driver";
     try {
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url);
        
        stmt = conn.createStatement();
        result = null;
      
        result = stmt.executeQuery("SELECT data_element,value FROM x_tori2_automated_test_case_data_definition where defination_id = '"+DefinitionID+"'");
                                  
        while(result.next()){
        	values[0] = result.getString("data_element");
        	values[1]=result.getString("value");
        //  break;	
        	
        }
//       System.out.println("Data for definitionID"+values[0]);
//  	  System.out.println("Identifire for definition id"+values[1]);
      //  i++;
        
       // System.out.println(DefinitionID);
       
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


// get the record count from mapping table
   public static int getcountofRecordsinMappingtable(String TestcaseID)
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

    	        result = stmt.executeQuery("SELECT defination_id as count FROM x_tori2_automated_test_case_definition_mapping where test_case_id = '"+TestcaseID+"'");
    	                                  
    	        while(result.next()){
    	        	count++;
    	        	//= result.getString("count");
    	        			//("data_element");
    	          //	data=result.getString("value");
    	        	 
    	        	//  System.out.println(Identifier);
    	        }
    	//        System.out.println("Total number of rows "+count);
    	       // System.out.println(DefinitionID);
    	       
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
        
// get the record count from definition table
   public static int getcountofsimpleRecordsinMappingTable(String TestcaseID)
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

    	        result = stmt.executeQuery("SELECT defination_id as count FROM x_tori2_automated_test_case_definition_mapping where test_case_id = '"+TestcaseID+"'"+"and type = 'simple'");
    	        		//+ "
    	
    	                                  
    	        while(result.next()){
    	        	count++;
    	        	//= result.getString("count");
    	        			//("data_element");
    	          //	data=result.getString("value");
    	        	 
    	        	//  System.out.println(Identifier);
    	        }
    	  //      System.out.println("Total number of rows "+count);
    	       // System.out.println(DefinitionID);
    	       
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
     
   static int getcountofJSONRecordsinMappingTable(String TestcaseID)
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

	        result = stmt.executeQuery("SELECT defination_id as count FROM x_tori2_automated_test_case_definition_mapping where test_case_id = '"+TestcaseID+"'"+"and type = 'JSON'");
	        		//+ "
	
	                                  
	        while(result.next()){
	        	count++;
	        	//= result.getString("count");
	        			//("data_element");
	          //	data=result.getString("value");
	        	 
	        	//  System.out.println(Identifier);
	        }
	  //      System.out.println("Total number of rows "+count);
	       // System.out.println(DefinitionID);
	       
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
   
   
   
   
   public static Set<Map<String,String>> fetchvalues(String DefinitionID) {
   	Connection conn = null;
       Statement stmt = null;
       Set<Map<String,String>> data = new HashSet<Map<String,String>>();
       setUp();

       String driver = "com.mysql.jdbc.Driver";
        try {
           Class.forName(driver).newInstance();
           conn = DriverManager.getConnection(url);
           stmt = conn.createStatement();     
           

           ResultSet result = stmt.executeQuery("SELECT data_element,value FROM x_tori2_automated_test_case_data_definition where defination_id = '"+DefinitionID+"'");
           while(result.next()){
       		Map<String, String> testcase = new HashMap<String, String>();
           	testcase.put("element", ""+result.getString("data_element"));
           	testcase.put("value", result.getString("value"));
           	data.add(testcase);
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
       return data;
   }
   
   
   public static String sortDefinitionID(String DefinitionID, String Filter_Type)
   {
	
	    String status="false";
   		Connection conn = null;
   	    Statement stmt = null;
   	    ResultSet result = null;
   	    String Type = null;
   	  
   	    setUp(); // initialize 

   	    String driver = "com.mysql.jdbc.Driver";
   	     try {
   	        Class.forName(driver).newInstance();
   	        conn = DriverManager.getConnection(url);
   	        
   	        stmt = conn.createStatement();
   	        result = null;

   	        result = stmt.executeQuery("SELECT type as Type FROM x_tori2_automated_test_case_json_object where defination_id = '"+DefinitionID+"'");
   	                                  
   	        while(result.next()){
   	        	
   	        	Type=result.getString("Type"); 
   	        }
   	        if(Type.equalsIgnoreCase(Filter_Type))
   	        {
   	        	status="true";	
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
   		return status;
   	}
   
   
   
   
   
   
   
// get the JSON from JSON table based on the Definition ID
public static String GetJSON_Object(String DefinitionID)
{
	Connection conn = null;
    Statement stmt = null;
    ResultSet result = null;
    String Json_Object=null;
    
    setUp(); // initialize 

    String driver = "com.mysql.jdbc.Driver";
     try {
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url);
        
        stmt = conn.createStatement();
        result = null;

        result = stmt.executeQuery("SELECT json AS Json_Object FROM x_tori2_automated_test_case_json_object where defination_id = '"+DefinitionID+"'");
        while(result.next()){
        	Json_Object=result.getString("Json_Object");
        	System.out.println(Json_Object);
        }
       // System.out.println(DefinitionID);
       
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
	return Json_Object;
	
}



// Read the table name for the JSON Definiton ID

public static String GetTableName(String DefinitionID)
{
	Connection conn = null;
    Statement stmt = null;
    ResultSet result = null;
    String tablename=null;
    
    setUp(); // initialize 

    String driver = "com.mysql.jdbc.Driver";
     try {
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url);
        
        stmt = conn.createStatement();
        result = null;

        result = stmt.executeQuery("SELECT table_name AS tablename FROM x_tori2_automated_test_case_json_object where defination_id = '"+DefinitionID+"'");
        while(result.next()){
        	tablename=result.getString("tablename");
        	System.out.println(tablename);
        }
       // System.out.println(DefinitionID);
       
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
	return tablename;
	
}

public static void Update_TestInstance(String testcase,String data_element,String value,String type)
{
	Connection conn = null;
    Statement stmt = null;
    ResultSet result = null;
    String tablename=null;
    
    setUp(); // initialize 

    String driver = "com.mysql.jdbc.Driver";
     try {
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url);
        
        stmt = conn.createStatement();
        result = null;
        String sql ="INSERT INTO x_tori2_automated_test_case_data_instance (test_case, data_element, value,type) " + "VALUES ('"+testcase+"', '"+data_element+"', '"+value+"','"+type+"')";
        stmt.executeUpdate(sql);
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
}
	   
   
   
   
   
// Read values from Definition ID 
public static void main(String args[])
{
	//String[] values={"D1","D2","D3","D4","D5"};
//	String[] values=GetJSONDefinitionIDfromMappingTable("DataCreationDEMOLC_Stry000000_Tc01_1",2);
//	for(int i=0;i<values.length;i++)
//	{
//		System.out.print(values[i]);
//	}
	int count=getcountofJSONRecordsinMappingTable("DataCreationDEMOLC_Stry000000_Tc01_1");
	System.out.print(count);
	
}
}
