package test;

//import SendHTMLEmail;

import java.io.IOException;

//import javax.mail.MessagingException;
//import javax.mail.internet.AddressException;




import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import utils.QueryDB_1;
//**************************************************************************************//
//Class to Update the values in Service now instance  
//**************************************************************************************//
public class Errorhandeling {
	
		static int i=0;
		static String postData=null;
		static String URL=null;
		//**************************************************************************************//
		// Function
		// Parameters : Sysid , status , execution time
		// Error handling : if(status!==200) , Resend the request. 
		// Resend the request @ 1st attempt : 5 Second
		// Resend the request @ 2st attempt : 5 Second
		// Resend the request @ 2st attempt : 20 Second
		// Third Attempt fail @ Log the request in error log table and set the error flag to true
		// @ Author : Aman
		// @ Date : 04-02-2016
		//**************************************************************************************//
		
		public static void updatestatus(String sysid,String status,long execution_time) throws IOException, HttpException,  InterruptedException, AddressException, MessagingException {

			String sysidnew=sysid;
			String statusnew=status;
			long execution_timenew=execution_time;

			String execution_status="Completed";
			String format1 = "{\"status\":\"";
			String format2 ="\"";
			String format3=""+"";
			String format4=",";
			String format5="\"execution_status\":\"";
			String format6="\"}";
			String format7="\"execution_time\":\"";
            // Rest Post Message 
			postData= format1+status+format2+format3+format4+format3+format5+execution_status+format2+format3+format4+format3+format7+execution_time+format6;
			System.out.println(postData);
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(
					new AuthScope(new HttpHost("tesmdemo.service-now.com")),
					new UsernamePasswordCredentials("punchagent", "punchagent"));

			CloseableHttpClient httpclient = HttpClients.custom()
					.setDefaultCredentialsProvider(credsProvider)
					.build();
			try {
				// Application URL
				URL="https://tes.service-now.com/api/now/table/x_tori2_automated_test_case_run/"+sysid;
				HttpPut httpPut = new HttpPut(URL);   
				httpPut.setHeader("Accept", "application/json");
				httpPut.setHeader("Content-Type", "application/json");
				HttpEntity entity = new ByteArrayEntity(postData.getBytes("utf-8"));
				httpPut.setEntity(entity);

				System.out.println("Executing request " + httpPut.getRequestLine());
				CloseableHttpResponse response = httpclient.execute(httpPut);
				System.out.println("Responce is "+ response.getStatusLine().getStatusCode());
				Integer responsecode=response.getStatusLine().getStatusCode();
				String errorDescription=response.getStatusLine().getReasonPhrase();
				if(responsecode==201||responsecode==204||responsecode==400||responsecode==401||responsecode==403||responsecode==404||responsecode==405)
				{
					if(i==3)  // Third Try
					{
						System.out.println("Three tries are completed");  
						QueryDB_1.updateErrorLog(postData, URL, sysidnew);  // update the log in error log table 
						SendHTMLEmail.sendHardCoded(sysidnew,responsecode,errorDescription); // send an email to the user
					}
					else
					{
						i++;
						System.out.println("Try " +i );
						long time=5000;  // Time for First and Second Try
						if(i==3) 
						{
							time=time+15000;  // Time for Third try
						}

						Thread.sleep(time);
						System.out.println("Wait time is "+time);
						responsecode=0;
						updatestatus(sysidnew,statusnew,execution_time);   // resend the rest request
					}
				}
				if(responsecode!=200)
				{
					QueryDB_1.updateWebserviceFlag("True",sysid);  // update  the flag with true 
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
		}
		//**************************************************************************************//
		//End  Function
		//**************************************************************************************//


		public static void main(String args[]) throws IOException, HttpException, InterruptedException, AddressException, MessagingException
		{
			updatestatus("3dfc94644f2dd200a3b35a701310c745","PASS",100);
		}


	}


