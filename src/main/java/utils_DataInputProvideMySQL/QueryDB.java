package utils_DataInputProvideMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class QueryDB {
	
		static String dbName;
		static String serverip;
	    protected static String url;
	    static String prefix;
	    static String username;
	    static String password;
	    static String Testinstanceid;
	    static String PDFName;
	    
		
	    //***************************************************//
		//Set up function for Data Base
		//***************************************************//
	    
		public static void setUp() {
			
			// Load the property file
			Properties prop = new Properties();
            // prop.load(new FileInputStream(getAbsolutePath()+"config.properties"));	
			
			dbName = "punchit";
			serverip = "52.88.27.203";
			username = "punch";
			password = "qwerty123";
			url = "jdbc:mysql://"+serverip+":3306/"+dbName+"?user="+username+"&password="+password;	

		}
		//************************************************************************************************/
	
		   //****************************************************************//
			//Get JSON Object from json table based on the definition provided
		   //****************************************************************//
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

	//************************************************************************************************/

	//****************************************************************//
	//Get JSON table name from json table based on the definition provided
   //****************************************************************//

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

	//************************************************************************************************/
	//****************************************************************//
	//Get Update test instance table for the Runtime/Prerequisite data created
	//****************************************************************//

	
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
		
	//************************************************************************************************/
	//****************************************************************//
	//Get count of records From Mapping table based on any specific type
	//****************************************************************//
	
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

	//************************************************************************************************/
	//****************************************************************//
	//Get Array of Definition ids from mapping table based on the type 
	//****************************************************************//
	
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
	 
		//************************************************************************************************/
		//****************************************************************//
		//Get values from definition tables based on the definition ids 
		//****************************************************************//
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
	 
		//************************************************************************************************/
		//****************************************************************//
		//Prepare Map for values and Parameters from Definition table
		//****************************************************************//
	
		 public static HashMap<String, String> GetValuesandParametersfromDefinitionTable(String[] DefinitionID) {
				
			 Connection conn = null;
			    Statement stmt = null;
			    ResultSet result = null;
			    String[] values = new String[2];
				HashMap<String,String> data=new HashMap<String,String>(); 
			    
			    setUp(); // initialize 
			     
			    String driver = "com.mysql.jdbc.Driver";
			     try {
			        Class.forName(driver).newInstance();
			        conn = DriverManager.getConnection(url);
			        
			        stmt = conn.createStatement();
			        result = null;
			        for(int i=0;i<DefinitionID.length;i++)
			        {
			        result = stmt.executeQuery("SELECT data_element,value FROM x_tori2_automated_test_case_data_definition where defination_id = '"+DefinitionID[i]+"'");
			                                  
			        while(result.next())
			        {
			        	values[0] = result.getString("data_element");
			        	values[1]=result.getString("value");
//			        	System.out.println(values[0]);
//			        	System.out.println(values[1]);
			        	data.put(values[0],values[1]); 
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
				return data;
			}
		 
			//************************************************************************************************/
			//****************************************************************//
			//Get the Definitions ids for specific type
			//****************************************************************//
		 
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
			//************************************************************************************************/
		 
		//****************************************************************//
		//Read identifier/locator type type from definition table
		// input: Definition id of the record from definition table
		// Author : Aman
		// Last modified : 24-04-2016
	   //****************************************************************//
		 
		 public static String getLocatorType(String DefinitionID)
		 {
			    String status="false";
		   		Connection conn = null;
		   	    Statement stmt = null;
		   	    ResultSet result = null;
		   	    String Locator_Type = null;
		   	  
		   	    setUp(); // initialize 

		   	    String driver = "com.mysql.jdbc.Driver";
		   	     try {
		   	        Class.forName(driver).newInstance();
		   	        conn = DriverManager.getConnection(url);
		   	        
		   	        stmt = conn.createStatement();
		   	        result = null;

		   	        result = stmt.executeQuery("SELECT locator_type as Locator_Type FROM x_tori2_automated_test_case_data_definition where defination_id = '"+DefinitionID+"'");
		   	                                  
		   	        while(result.next()){
		   	        	
		   	        	Locator_Type=result.getString("Locator_Type"); 
		   	        }
		   	        System.out.println("Locator type is "+Locator_Type);
		   	       
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
			//************************************************************************************************/
	//****************************************************************//
			//Read identifier value /locator value from definition table
			// input: Definition id of the record from definition table
			// Author : Aman
			// Last modified : 24-04-2016
	 //****************************************************************//
		 public static String getLocatorValue(String DefinitionID)
		 {
			    String status="false";
		   		Connection conn = null;
		   	    Statement stmt = null;
		   	    ResultSet result = null;
		   	    String Locator_Value = null;
		   	  
		   	    setUp(); // initialize 

		   	    String driver = "com.mysql.jdbc.Driver";
		   	     try {
		   	        Class.forName(driver).newInstance();
		   	        conn = DriverManager.getConnection(url);
		   	        
		   	        stmt = conn.createStatement();
		   	        result = null;

		   	        result = stmt.executeQuery("SELECT Locator_Value as locator_value FROM x_tori2_automated_test_case_data_definition where defination_id = '"+DefinitionID+"'");
		   	                                  
		   	        while(result.next()){
		   	        	
		   	        	Locator_Value=result.getString("Locator_Value"); 
		   	        }
		   	        System.out.println("Locator type is "+Locator_Value);
		   	       
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
			//************************************************************************************************/
		//****************************************************************//
			//Read Field Type from definition table
			// input: Definition id of the record from definition table
			// Author : Aman
			// Last modified : 24-04-2016
	 //****************************************************************//
		 public static String getFieldType(String DefinitionID)
		 {
			    String status="false";
		   		Connection conn = null;
		   	    Statement stmt = null;
		   	    ResultSet result = null;
		   	    String Field_type = null;
		   	  
		   	    setUp(); // initialize 

		   	    String driver = "com.mysql.jdbc.Driver";
		   	     try {
		   	        Class.forName(driver).newInstance();
		   	        conn = DriverManager.getConnection(url);
		   	        
		   	        stmt = conn.createStatement();
		   	        result = null;

		   	        result = stmt.executeQuery("SELECT field_type as Field_type FROM x_tori2_automated_test_case_data_definition where defination_id = '"+DefinitionID+"'");
		   	                                  
		   	        while(result.next()){
		   	        	
		   	        	Field_type=result.getString("Field_type"); 
		   	        }
		   	        System.out.println("Locator type is "+Field_type);
		   	       
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
			//************************************************************************************************/
			//******************************************************************************************************************************//
			//Prepare a hashmap with data_element as the key and identifier,identifier value , field type and value are the values stored wrt to each key
			// input: array of definition id's
			// Author : Aman
			// Last modified : 24-04-2016
	        //****************************************************************//
		 public static HashMap<String, List<String>> GetValuesForDefinitionIDs(String[] DefinitionID) {

			 Connection conn = null;
			 Statement stmt = null;
			 ResultSet result = null;
			 Map<String, List<String>> data = new HashMap<String, List<String>>();

			 setUp(); // initialize 

			 String driver = "com.mysql.jdbc.Driver";
			 try {
				 Class.forName(driver).newInstance();
				 conn = DriverManager.getConnection(url);

				 stmt = conn.createStatement();
				 result = null;
				 for(int i=0;i<DefinitionID.length;i++)
				 {
					 result = stmt.executeQuery("SELECT data_element,value,locator_type,locator_value,field_type FROM x_tori2_automated_test_case_data_definition where defination_id = '"+DefinitionID[i]+"'");

					 while(result.next())
					 {
						 String[] Storevalue=new String[5]; // Array to store  data_element,value,locator_type,locator_value,field_type 
						 List<String> values = new ArrayList<String>(); // List to Store multiple values for each key
						 
						 // store values in array
						 Storevalue[0] = result.getString("data_element"); 
						 Storevalue[1] = result.getString("value");
						 Storevalue[2] = result.getString("locator_type");
						 Storevalue[3] = result.getString("locator_value");
						 Storevalue[4] = result.getString("field_type");
						 
						 // add values to list
						 values.add(Storevalue[1]);
						 values.add(Storevalue[2]);
						 values.add(Storevalue[3]);
						 values.add(Storevalue[4]);
						 
						 // add keys with values in Hash map
						 data.put( Storevalue[0] ,values);
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
			 return (HashMap<String, List<String>>) data;
		 }

			//************************************************************************************************/
	
	public static void main(String args[])
	{
//		String[] definition={"ATSDD0001057","ATSDD0001027"};
//		Map<String, List<String>> data = new HashMap<String, List<String>>();
//		data=GetValuesForDefinitionIDs(definition);
//		   for (Map.Entry<String, List<String>> entry : data.entrySet()) {
//	            String key = entry.getKey();
//	            List<String> values = entry.getValue();
//	            //System.out.println("Key = " + key);
//	            //System.out.println("Values = " + values + "n");
////	            for(int i=0;i<values.size();i++){
////	                System.out.println("First value is "+values.get(i));
////	            }
////	       List<String> value= data.get("37d1a9dc4ffa12005bf37d218110c773"); // Prepares the two dimensional aray based on parameters sequence passed
////				System.out.println("value is "+value.get(1));
//		}
//		  int hSize = data.size(); 
//		  System.out.println("Size of Hash map is"+hSize);
//		  
//		  System.out.println("Size of Hash map is"+data.keySet().toArray()[0].toString());
//		  System.out.println("Size of Hash map is"+data.keySet().toArray()[1].toString());
//	}
		 HashMap<String,List<String>> values=DataInputProvider.GetData("Create an Incident");
		 List<String> result= values.get("User_ID"); // Prepares the two dimensional aray based on parameters sequence passed
		 System.out.println("value is "+result.get(2));
		 List<String> result1= values.get("Password");
		 System.out.println("value is "+result1.get(2));
		 List<String> result2= values.get("Caller");
		 System.out.println("value is "+result2.get(2));
	}
}

