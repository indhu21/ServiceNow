package utils;

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

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.http.HttpException;

import wrapper.GenericWrappers;

public class QueryDB_1 extends GenericWrappers{
	
	static String dbName;
	static String serverip;
    protected static String url;
    static String prefix;
    static String username;
    static String password;
    static String Testinstanceid;
    static String PDFName;
    
	
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

            //result = stmt.executeQuery("SELECT browser AS browserName FROM wa_testrun_run run join wa_testrun_runtc runtc on run.entity_id = runtc.run_id where runtc.entity_id = "+entityId);
           // SELECT browser_name AS browserName FROM testcase_run where test_run_id = '"+entityId+"'"
            result = stmt.executeQuery("SELECT browser AS browserName FROM x_tori2_automated_test_case_run where sys_id = '"+entityId+"'");
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
	public static void updateResult(String entityId, String status, String errorMsg, long execTime) throws IOException, AddressException, MessagingException, InterruptedException {
    	Connection conn = null;
        Statement stmt = null;
        
        setUp();

        String driver = "com.mysql.jdbc.Driver";
         try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            stmt.executeUpdate("Update x_tori2_automated_test_case_run Set status ='"+status+"', execution_status = 3, execution_time = "+execTime+" where sys_id = '"+entityId+"'");
          //  entityId);
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
        PutAction.updatestatus(entityId, status, execTime);
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
            ResultSet result = stmt.executeQuery("Select status,sys_id, testcase_id, browser, browser_version from x_tori2_automated_test_case_run  where sys_id = 0");
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
  
	public static String readTestinsID(String entityId)
	{
		Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
       // String browserName = "";
        
        setUp(); // initialize 

        String driver = "com.mysql.jdbc.Driver";
         try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url);
            
            stmt = conn.createStatement();
            result = null;

            //result = stmt.executeQuery("SELECT browser AS browserName FROM wa_testrun_run run join wa_testrun_runtc runtc on run.entity_id = runtc.run_id where runtc.entity_id = "+entityId);
           // SELECT browser_name AS browserName FROM testcase_run where test_run_id = '"+entityId+"'"
            result = stmt.executeQuery("SELECT test_instance_id AS Testinstanceid FROM x_tori2_automated_test_case_run where sys_id = '"+entityId+"'");
            while(result.next()){
            	Testinstanceid = result.getString("Testinstanceid");
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
		return Testinstanceid;
    }
		
	
	
	public static String readPDFname(String entityId)
	{
		Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
       // String browserName = "";
        
        setUp(); // initialize 

        String driver = "com.mysql.jdbc.Driver";
         try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url);
            
//            stmt = conn.createStatement();
            result = null;

            //result = stmt.executeQuery("SELECT browser AS browserName FROM wa_testrun_run run join wa_testrun_runtc runtc on run.entity_id = runtc.run_id where runtc.entity_id = "+entityId);
           // SELECT browser_name AS browserName FROM testcase_run where test_run_id = '"+entityId+"'"
            result = stmt.executeQuery("SELECT pdf_name AS PDFName FROM x_tori2_automated_test_case_run where sys_id = '"+entityId+"'");
            while(result.next()){
            	PDFName = result.getString("PDFName");
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
		return PDFName;
    }
	

	
	/////////  Update post error log
	public static void updateErrorLog(String post_data, String InstanceURL, String sys_id) {
		
		Connection conn = null;
        Statement stmt = null;
        
        setUp();

        String driver = "com.mysql.jdbc.Driver";
         try {
        	 
            Class.forName(driver).newInstance();
             
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            String url=InstanceURL;
            String sql ="INSERT INTO x_tori2_automated_test_case_webservice_update_log (post_data, sys_id, url) " + "VALUES ('"+post_data+"', '"+sys_id+"', '"+InstanceURL+"')";
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
       // PutAction.updatestatus(entityId, status, execTime);
    }
	
	//////// Delete the records from 
	public static void TruncateTable() throws IOException, HttpException {
    	Connection conn = null;
        Statement stmt = null;
        
        setUp();

        String driver = "com.mysql.jdbc.Driver";
         try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            stmt.executeUpdate("TRUNCATE x_tori2_automated_test_case_webservice_update_log");
          //  entityId);
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
       // PutAction.updatestatus(entityId, status, execTime);
    }
	
	
	//// set the flag
	public static void updateWebserviceFlag(String flag,String sysid) {
    	Connection conn = null;
        Statement stmt = null;
        
        setUp();

        String driver = "com.mysql.jdbc.Driver";
         try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            stmt.executeUpdate("Update x_tori2_automated_test_case_run Set webservice_update_failure = '"+flag+"' "+" where sys_id = '"+sysid+"'");
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
	
//	////// get the sysid for the flag with status false
public static void getsysIDforFLag(Integer value) {
    	Connection conn = null;
        Statement stmt = null;
        String sysid = null;
        setUp();

        String driver = "com.mysql.jdbc.Driver";
         try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
           
            String qry = "SELECT sys_id as sysid FROM x_tori2_automated_test_case_run where webservice_update_failure='true'";
            ResultSet rs = stmt.executeQuery(qry);
            while(rs.next())
			   {
            	sysid = rs.getString("sysid");
            	System.out.println(sysid);
			   }
          //  stmt.executeUpdate(qry);
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
    
public static String[] readErrorLog(String entityId)
{
	Connection conn = null;
	Statement stmt = null;
	ResultSet result = null;
	// String browserName = "";
	String[] details=new String[3];

	setUp(); // initialize 

	String driver = "com.mysql.jdbc.Driver";
	try {
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url);

		stmt = conn.createStatement();
		result = null;

		result = stmt.executeQuery("SELECT post_data,url  FROM x_tori2_automated_test_case_webservice_update_log where sys_id = '"+entityId+"'");
		while(result.next())
		{
			details[0] = result.getString("post_data");
			details[1] = result.getString("url");
    		System.out.println(details[0]);
     		System.out.println(details[1]);
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
	return details;
}
 

//Delete the records from 
public static void deleteRecords(String entityId) throws IOException, HttpException {
	Connection conn = null;
	Statement stmt = null;

	setUp();

	String driver = "com.mysql.jdbc.Driver";
	try {
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url);
		stmt = conn.createStatement();
		stmt.executeUpdate("DELETE from x_tori2_automated_test_case_webservice_update_log where sys_id = '"+entityId+"'");
		//  entityId);
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
	// PutAction.updatestatus(entityId, status, execTime);
}

	
//public static void main(String args[]) throws IOException, HttpException
//{
//	//String idd=readTestinsID("001c2486c8419a40b74a306bb2c4468e");
//	deleteRecords("8fcadf34c89d5e40b74a306bb2c4462a");
//	//System.out.println(idd);
//	
//
//}
}