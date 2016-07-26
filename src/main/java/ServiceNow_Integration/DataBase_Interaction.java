package ServiceNow_Integration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import com.punchit.scripts.sn.SN_ExecuteTestcases;

import wrapper.ServiceNowWrappers;

public class DataBase_Interaction extends ServiceNowWrappers{
	
	static String dbName;
	static String serverip;
    static String url;
    static String prefix;
    static String username;
    static String password;
    static String browserName = "";
    static String  TestcaseNo="";
    static String sysid="bb254b91b0730e40b74a113014848f77";
    
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

        public static void getData()
        {
        	Connection conn = null;
        	Statement stmt = null;
        	ResultSet result = null;
        	

        	setUp(); // initialize 
        	String driver = "com.mysql.jdbc.Driver";
        	try {
        		Class.forName(driver).newInstance();
        		conn = DriverManager.getConnection(url);

        		stmt = conn.createStatement();
        		result = null;
        		// get the browser name
        		result = stmt.executeQuery("SELECT browser AS browserName FROM x_tori2_automated_test_case_run where sys_id = '"+sysid+"'");
        		while(result.next())
        		{
        			browserName = result.getString("browserName");
        		}
        		System.out.println("browserName is "+ browserName);
        		// get the test class
        		result = stmt.executeQuery("SELECT testcase_id AS TestcaseNo FROM x_tori2_automated_test_case_run where sys_id = '"+sysid+"'");
        		while(result.next())
        		{
        			TestcaseNo = result.getString("TestcaseNo");
        		}
        		System.out.println("Test case  is "+ TestcaseNo);
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
        
        
        /*
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
        		ResultSet result = stmt.executeQuery("Select sys_id,browser,testcase_id  from x_tori2_automated_test_case_run where sys_id = '"+sysid+"'");
        		while(result.next()){
        			Map<String, String> testcase = new HashMap<String, String>();
        			System.out.println("sys id :"+result.getString("sys_id"));
        			//System.out.println(result.getString("test_run_id"));
        			System.out.println(result.getString("testcase_id"));
        			System.out.println(result.getString("browser"));
        			//System.out.println(result.getString("execution_status"));

        			testcase.put("sysid", ""+result.getString("sys_id"));
        			//testcase.put("runid", result.getString("test_run_id"));
        			testcase.put("testcaseid", result.getString("testcase_id"));
        			testcase.put("browser", result.getString("browser"));
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

        */
        
       
//        public static void main(String args[])
//        {
//        //	TestExecution.TestExecution();
//        	    
//        	}
//        
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

                result = stmt.executeQuery("Select browser as browserName from x_tori2_automated_test_case_run where sys_id = '"+entityId+"'");
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
        public static void updateResultNew(String sysid, int status, long execTime) {
        	Connection conn = null;
            Statement stmt = null;
            
            setUp();

            String driver = "com.mysql.jdbc.Driver";
             try {
                Class.forName(driver).newInstance();
                conn = DriverManager.getConnection(url);
                stmt = conn.createStatement();
                stmt.executeUpdate("Update x_tori2_automated_test_case_run Set status ="+status+", status = 2,  execution_time = "+execTime+" where sys_id = '"+sysid+"'");
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
    	

        

public static void main(String args[])
{
	String bname=getBrowserName("bb254b91b0730e40b74a113014848f77");
	System.out.println("Browser name is "+ bname);
}
}

 

