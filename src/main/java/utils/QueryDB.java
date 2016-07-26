package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import wrapper.GenericWrappers;


public class QueryDB extends GenericWrappers{
	
	static String dbName;
	static String serverip;
    static String url;
    static String prefix;
    static String username;
    static String password;
	
	public static void setUp() {
		
		try {
			// Load the property file
			Properties prop = new Properties();
			prop.load(new FileInputStream(getAbsolutePath()+"config.properties"));	
			
			dbName = prop.getProperty("DBNAME");
			serverip = prop.getProperty("DBIP");
			username = prop.getProperty("DBUSERNAME");
			password = prop.getProperty("DBPASSWORD");
			url = "jdbc:mysql://"+serverip+":3306/"+dbName+"?user="+username+"&password="+password;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
   
	public static String getBrowserName(String entityId) {
		
    	Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
        String browserName = "";
        
        setUp(); // initialize 

        String driver = "com.mysql.jdbc.Driver";
         try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url);
            
            stmt = conn.createStatement();
            result = null;

            result = stmt.executeQuery("SELECT browser AS browserName FROM wa_testrun_run run "
            		+ "join wa_testrun_runtc runtc on run.entity_id = runtc.run_id where runtc.entity_id = "+entityId);

            while(result.next()){
            	browserName = result.getString("browserName");
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
         System.out.println(browserName);
		return browserName;
    }
   
public static String getURL(String entityId) {
		
    	Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
        String browserName = "";
        
        setUp(); // initialize 

        String driver = "com.mysql.jdbc.Driver";
         try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url);
            
            stmt = conn.createStatement();
            result = null;

            result = stmt.executeQuery("SELECT browser AS browserName FROM wa_testrun_run run "
            		+ "join wa_testrun_runtc runtc on run.entity_id = runtc.run_id where runtc.entity_id = "+entityId);
           
            while(result.next()){
            	browserName = result.getString("browserName");
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
		return browserName;
    }
	// Status - PASS, FAIL, ERROR, INSUCFFICENT DATA
	// @ add - time for execution  
	public static void updateResult(String entityId, String status, String errorMsg, long execTime) {
    	Connection conn = null;
        Statement stmt = null;
        
        setUp();

        String driver = "com.mysql.jdbc.Driver";
         try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            stmt.executeUpdate("Update wa_testrun_runtc Set result ='"+status+"', status = 2,  execution ='"+errorMsg+"', execTime = "+execTime+" where entity_id = "+entityId);
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
  
	public static String verifyResult(String entityId) {
		
    	Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
        String browserName = "";
        
        setUp(); // initialize 

        String driver = "com.mysql.jdbc.Driver";
         try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url);
            
            stmt = conn.createStatement();
            result = null;

            result = stmt.executeQuery("SELECT result, status, execution, exectime FROM wa_testrun_runtc where entity_id = "+entityId);
            while(result.next()){
            	System.out.println(result.getString("result"));
            	System.out.println(result.getInt("status"));
            	System.out.println(result.getString("execution"));
            	System.out.println(result.getInt("exectime"));
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
		return browserName;
    }
   
	public static Set<Map<String,String>> fetchTestcasesToBeExecuted() {
    	Connection conn = null;
        Statement stmt = null;
        Set<Map<String,String>> testcases = new HashSet<Map<String,String>>();
        setUp();

        String driver = "com.mysql.jdbc.Driver";
         try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("Select execution_status,entity_id, test_run_id, testcase_id, browser_name, browser_version from testcase_run  where execution_status = 0");
            while(result.next()){
        		Map<String, String> testcase = new HashMap<String, String>();
            	System.out.println("entity id :"+result.getInt("entity_id"));
            	System.out.println(result.getString("test_run_id"));
            	System.out.println(result.getString("testcase_id"));
            	System.out.println(result.getString("browser_name"));
            	System.out.println(result.getString("execution_status"));

            	testcase.put("entityid", ""+result.getInt("entity_id"));
            	testcase.put("runid", result.getString("test_run_id"));
            	testcase.put("testcaseid", result.getString("testcase_id"));
            	testcase.put("browser", result.getString("browser_name"));
            	testcases.add(testcase);
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
        return testcases;
    }
	
	public static Set<Map<String,String>> fetchTestcasesToBeUpdated() {
    	Connection conn = null;
        Statement stmt = null;
        Set<Map<String,String>> testcases = new HashSet<Map<String,String>>();
        setUp();

        String driver = "com.mysql.jdbc.Driver";
         try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("Select test_run_id, execution_status, execution_time from testcase_run where execution_status <> 0 AND ui_status <> 'Complete' "); //
            while(result.next()){
        		Map<String, String> testcase = new HashMap<String, String>();
        		System.out.println(result.getInt("execution_time"));
        		System.out.println(result.getInt("execution_status"));
        		
            	testcase.put("execTime", ""+result.getInt("execution_time"));
            	testcase.put("runid", result.getString("test_run_id"));
            	testcase.put("exeStatus", ""+result.getInt("execution_status"));
            	testcases.add(testcase);
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
        return testcases;
    }
  
	public static void insertTestcases(String testRunId, int clientId, String tcId, String browserName) {
    	Connection conn = null;
        Statement stmt = null;
        setUp();

        String driver = "com.mysql.jdbc.Driver";
         try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            
            ResultSet result0 = stmt.executeQuery("Select count(test_run_id) as cnt from testcase_run Where test_run_id = '"+testRunId+"'");
            int noOfRecords = 0;
            if(result0.next()){
            	noOfRecords = result0.getInt("cnt");
           	}
            if(noOfRecords == 0){
            
            ResultSet result = stmt.executeQuery("Select entity_id from testcase_run Where status = 0 Order By entity_id DESC");
            int entityId = 0;
            if(result.next()){
            	entityId = result.getInt("entity_id")+1;
            	System.out.println("ent "+entityId);
           	}
              
            System.out.println("INSERT INTO testcase_run (entity_id,test_run_id,client_id,testcase_id,execution_status,status,ui_status,execution_time,browser_name,browser_version) "
            		+ "VALUES ("+entityId+",'"+testRunId+"',"+clientId+",'"+tcId+"',0,0,'0',0,'"+browserName+"','0')");
            stmt.executeUpdate("INSERT INTO testcase_run (entity_id,test_run_id,client_id,testcase_id,execution_status,status,ui_status,execution_time,browser_name,browser_version) "
            		+ "VALUES ("+entityId+",'"+testRunId+"',"+clientId+",'"+tcId+"',0,0,'0',0,'"+browserName.toLowerCase()+"','0')");

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
    }
	
	public static String getBrowserNameFromNew(String entityId) {
		
    	Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
        String browserName = "";
        
        setUp(); // initialize 

        String driver = "com.mysql.jdbc.Driver";
         try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url);
            
            stmt = conn.createStatement();
            result = null;

            result = stmt.executeQuery("SELECT browser_name AS browserName FROM testcase_run where test_run_id = '"+entityId+"'");
            while(result.next()){
            	browserName = result.getString("browserName");
            }
            System.out.println(browserName);
           
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
		return browserName;
    }
	
	public static void updateResultNew(String entityId, int status, long execTime) {
    	Connection conn = null;
        Statement stmt = null;
        
        setUp();

        String driver = "com.mysql.jdbc.Driver";
         try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            stmt.executeUpdate("Update testcase_run Set execution_status ="+status+", status = 2,  execution_time = "+execTime+" where test_run_id = '"+entityId+"'");
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
	
	public static void updateUiStatus(String entityId) {
    	Connection conn = null;
        Statement stmt = null;
        
        setUp();

        String driver = "com.mysql.jdbc.Driver";
         try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            stmt.executeUpdate("Update testcase_run Set ui_status = 'Complete' where test_run_id = '"+entityId+"'");
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
  
	public static void deleteAll() {
    	Connection conn = null;
        Statement stmt = null;
        
        setUp();

        String driver = "com.mysql.jdbc.Driver";
         try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            stmt.executeUpdate("Delete from testcase_run");
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
	
	public static String getUrl(String entityId)
	{
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;
		String Instance=null;
		setUp(); // initialize 

		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url);

			stmt = conn.createStatement();
			result = null;

//			result = stmt.executeQuery("SELECT instance_url As url FROM wa_testrun_run where entity_id = 10353");
            
			result = stmt.executeQuery("SELECT instance_url AS url FROM wa_testrun_run run "
			+ "join wa_testrun_runtc runtc on run.entity_id = runtc.run_id where runtc.entity_id = "+entityId);

            
			System.out.println(result);
			while(result.next()){
				Instance = result.getString("url");
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
		System.out.println("Instance name "+Instance);
		return Instance;

	}

	public static void main(String[] args) {
		getUrl("8423");
	}


}