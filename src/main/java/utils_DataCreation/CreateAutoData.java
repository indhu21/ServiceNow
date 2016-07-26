package utils_DataCreation;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;



public class CreateAutoData {
	
	public static Integer CreateCI_Demo(String TestCaseName)throws IOException, HttpException
	{
//             String newName=ModifyJSON.getName();
//             System.out.print(newName);
//		
		    
	    	int responsecode=0;
			String[] credentials=new String[2]; // Array to Store the User id and Password
			
			String[] json=DataInputProvide_CreateData.GetJSON(TestCaseName,"CI");
			
			credentials=readFrom_ConfigFile.GetCredentials_TESMDEMO(); // Fetch credentials for CreateData Class 
			
			//String modify_JSON=ModifyJSON.Modify_JSON("name", newName, json[0]);  // ReadJson Object and Table name
			
			String postData = json[0]; // JSON[0] = JSON Object                               

			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(
					new AuthScope(new HttpHost(credentials[2])),
					new UsernamePasswordCredentials(credentials[0], credentials[1]));
			CloseableHttpClient httpclient = HttpClients.custom()
					.setDefaultCredentialsProvider(credsProvider)
					.build();

			try {
				// JSON[1] = Table Name
				String URL="https://"+credentials[2]+"/"+json[1]+".do?JSONv2&sysparm_action=insertMultiple";
				HttpPost httpPost = new HttpPost(URL);
				httpPost.setHeader("Accept", "application/json");
				httpPost.setHeader("Content-Type", "application/json");
				HttpEntity entity = new ByteArrayEntity(postData.getBytes("utf-8"));
				httpPost.setEntity(entity);

				System.out.println("Executing request " + httpPost.getRequestLine());
				
				CloseableHttpResponse response = httpclient.execute(httpPost);
				
				responsecode=response.getStatusLine().getStatusCode();
				try {
					System.out.println("----------------------------------------");
					System.out.println(response.getStatusLine());
					String responseBody = EntityUtils.toString(response.getEntity());
					System.out.println(responseBody);

					int sys_index=responseBody.indexOf("sys_id"); // Get the sys_id index

					int sys_start=sys_index+9; // Increment counter to reach at the start of the sys_id
					
					int sys_end=sys_index+41; // Increment counter to reach at the end of the sys_id
					
					char[] value = new char[32]; // char array to store sys_id
					
					responseBody.getChars(sys_start, sys_end, value, 0);  // response functon to read the sys_id charcter
					
					QueryDB_CreateData.Update_TestInstance(TestCaseName, "CI", String.valueOf(value), "Pre-requisite"); // Update the test instance table for the sys_id
				
				} finally {
					response.close(); 
				}
			} finally {
				httpclient.close();
			}
			return responsecode;
	}
	
	
	
	
}
