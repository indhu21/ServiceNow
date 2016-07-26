package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.util.ArrayList;

import utils.QueryDB_1;
//import QueryDB_1;
//**************************************************************************************//
//Function to set the automated Schedule Job 
// Schedule job will check the records in error log table and resend the rest request
// If the request is success record is deleted from the error log table
// In the case of success flag is set to true
//**************************************************************************************//

public class ReUpdate_Instance extends QueryDB_1{

	static int i=0;
    
  
	//************************//
	//Function to Resend the Rest Request
	// Author : Aman
	// Parameters : Array of Rest object and URL to hit
	// Date : 04-02-2016
	//**********************//
	public static Integer resendRestRequest(String Object[]) throws ClientProtocolException, IOException
    {
    	Integer flag=1; // flag for true value
       	CredentialsProvider credsProvider = new BasicCredentialsProvider();
    	credsProvider.setCredentials(
    			new AuthScope(new HttpHost("tesmdemo.service-now.com")),
    			new UsernamePasswordCredentials("punchagent", "punchagent"));

    	CloseableHttpClient httpclient = HttpClients.custom()
    			.setDefaultCredentialsProvider(credsProvider)
    			.build();
    	try {
			HttpPut httpPut = new HttpPut(Object[1]); // rest object    
			httpPut.setHeader("Accept", "application/json");
			httpPut.setHeader("Content-Type", "application/json");
			HttpEntity entity = new ByteArrayEntity(Object[0].getBytes("utf-8"));
			httpPut.setEntity(entity);

			System.out.println("Executing request " + httpPut.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpPut);
			System.out.println("Responce is "+ response.getStatusLine().getStatusCode());
			Integer responsecode=response.getStatusLine().getStatusCode();
			String errorDescription=response.getStatusLine().getReasonPhrase();
			if(responsecode!=200) // If status code is not equal to 200 ... then this is error 
			{
				System.out.println("Flag is false");
				flag=0;   // set the flag to false if error status is not equal to 200
			}
			try {
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				String responseBody = EntityUtils.toString(response.getEntity());
				System.out.println(responseBody);
			} finally {
				response.close();
			}

		} finally {
			httpclient.close();
		}
		return flag;
	}   //************************//
        //End of Function
    	//***********************//
	
	
    	//********************************************************************************//
        //Function to Read the values from error log table based on the flag in data table
        // Author : Aman
        // Data " 04-02-2016
        // Date : 04-02-2016
        //********************************************************************************//
        	
    	public static void getsysIDforFLag(){
    		String[] logdetails=new String[3];
    		Connection conn = null;
    		Statement stmt = null;
    		String sysid = null;
    		QueryDB_1.setUp();

    		String driver = "com.mysql.jdbc.Driver";
    		try {
    			Class.forName(driver).newInstance();
    			conn = DriverManager.getConnection(url);
    			stmt = conn.createStatement();
                // Query to fetch the sys id based on the flag 'true'
    			String qry = "SELECT sys_id as sysid FROM x_tori2_automated_test_case_run where webservice_update_failure='True'";
    			ResultSet rs = stmt.executeQuery(qry);
			while(rs.next())
			{
				sysid = rs.getString("sysid"); // sys id is stored 
				logdetails=readErrorLog(sysid); // URL and Rest Object fetched from the Log table based on the sys id 
				if(!(logdetails[0] == null)) // No record in Log table
				{
					System.out.println(sysid);
					Integer status=resendRestRequest(logdetails);  // Resend the Request 
					if(status==1)  // if the rest request is successfull
					{
						System.out.println("Delete the Record");
						updateWebserviceFlag("False",sysid); // set the flag as false
						deleteRecords(sysid); // delete the record from Error log table

					}
					else
					{
						System.out.println("Instance is still down");	
						//					
					}

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
	}//************************//
    //End of Function
    //**********************//
    

          
	public static void main(String args[])
	{
		getsysIDforFLag();
	}
}
