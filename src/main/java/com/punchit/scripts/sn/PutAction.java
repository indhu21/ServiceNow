package com.punchit.scripts.sn;


	import java.io.IOException;
	 


	import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
	 
	public class PutAction {
		public static void main(String[] args) throws IOException, HttpException {
	 		//PutAction restAction = new PutAction();
	 		//restAction.putRequest();
	 	//}
	 
	 //	public void putRequest() throws HttpException, IOException {
	 	// This must be valid json string with valid fields and values from table
			String postData = "{\"u_state\":\"FAIL\"" + "," + "\"u_execution_status\":\"New\"" + ","+ "\"u_execution_time\":\"120\" }";
					//+ \"u_execution_time\":\"120\" }";
	 	 		CredentialsProvider credsProvider = new BasicCredentialsProvider();
	 	        credsProvider.setCredentials(
	 	                new AuthScope(new HttpHost("dev12057.service-now.com")),
	 	               new UsernamePasswordCredentials("ashish.rw", "ashishrw1"));
	 	        CloseableHttpClient httpclient = HttpClients.custom()
	 	                .setDefaultCredentialsProvider(credsProvider)
	 	                .build();
	 
	        try {
	        	HttpPut httpPut = new HttpPut("https://dev12057.service-now.com/api/now/table/u_testcase_run/fa9de3884fc412009b1a30318110c78c");
	 	 		httpPut.setHeader("Accept", "application/json");
	 	 		httpPut.setHeader("Content-Type", "application/json");
	 	        HttpEntity entity = new ByteArrayEntity(postData.getBytes("utf-8"));
	 	 		httpPut.setEntity(entity);
	 
	            System.out.println("Executing request " + httpPut.getRequestLine());
	            CloseableHttpResponse response = httpclient.execute(httpPut);
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
	 
	}

