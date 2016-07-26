package utils;

import java.io.IOException;

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

import test.SendHTMLEmail;
import utils.PutActionUploadPdf;
import utils_DataCreation.readFrom_ConfigFile;
//**************************************************************************************//
//Class to Update the values in Service now instance  
//**************************************************************************************//
public class PutAction {
	
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
	
	public static void updatestatus(String sysid,String status,long execution_time) throws IOException, AddressException, MessagingException, InterruptedException{
		System.out.println("Sys Id "+sysid);
		System.out.println("Status "+status);
			System.out.println("Time  "+execution_time);
				
					
		String sysidnew=sysid;
		String statusnew=status;
		String execution_status="Completed";
		String format1 = "{\"status\":\"";
		String format2 ="\"";
		String format3=""+"";
		String format4=",";
		String format5="\"execution_status\":\"";
		String format6="\"}";
		String format7="\"execution_time\":\"";
		String postData= format1+status+format2+format3+format4+format3+format5+execution_status+format2+format3+format4+format3+format7+execution_time+format6;
		
		System.out.println(postData);

		String credentials[]=readFrom_ConfigFile.GetCredentials_TESMDEMO();
		
		System.out.println("credentials 0"+credentials[0]);
		System.out.println("credentials 1"+credentials[1]);
		System.out.println("credentials 2"+credentials[2]);
		
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		
		credsProvider.setCredentials(
				new AuthScope(new HttpHost(credentials[2])),
				new UsernamePasswordCredentials(credentials[0], credentials[1]));
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCredentialsProvider(credsProvider)
				.build();

		try 
		{	
			HttpPut httpPut = new HttpPut("https://"+credentials[2]+
					"/api/now/table/x_tori2_automated_test_case_run/"+sysid);	
			httpPut.setHeader("Accept", "application/json");
			httpPut.setHeader("Content-Type", "application/json");
			HttpEntity entity = new ByteArrayEntity(postData.getBytes("utf-8"));
			httpPut.setEntity(entity);

			System.out.println("Executing request " + httpPut.getRequestLine());
			
			CloseableHttpResponse response = httpclient.execute(httpPut);
			System.out.println("Responce is "+ response.getStatusLine().getStatusCode());
			Integer responsecode=response.getStatusLine().getStatusCode();
			System.out.println("Respose code: "+responsecode);
			String errorDescription=response.getStatusLine().getReasonPhrase();

			if(responsecode==201||responsecode==204||responsecode==400||responsecode==401||responsecode==403||responsecode==404||responsecode==405)
			
			{
				if(i==3)  // Third Try
				{
					System.out.println("Three tries are completed");  
					QueryDB_ServiceNowFrontEnd.updateErrorLog(postData, URL, sysidnew);  // update the log in error log table 
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
				QueryDB_ServiceNowFrontEnd.updateWebserviceFlag("True",sysid);  // update  the flag with true 
			}
			String PDFsysid=QueryDB_ServiceNowFrontEnd.readTestinsID(sysid);
			PutActionUploadPdf.uploadPdf(PDFsysid,sysid,credentials);
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
	public static void main(String[] args) throws AddressException, IOException, HttpException, InterruptedException, MessagingException {
		updatestatus("7ed41d374f4b1a005bf37d218110c76c", "FAIL", 20);
	}
}

