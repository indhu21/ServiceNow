package utils_DataDeletion;

import java.io.IOException;

import org.apache.http.HttpEntity;
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

import java.lang.Object;

public class Delete implements java.io.Serializable {

	//******************//
	//query = post data entry to delete records ,
	// Table_Name = Table name from where records are to be deleted
	//******************//

	public static void delete(String query,String Table_Name) throws Exception {

		String[] Instance_Details=new String[3];

		String postData=null;

		Instance_Details=utils_DataCreation.readFrom_ConfigFile.GetCredentials_Delete_sparc();  
		//******************//
		//Instance_Details[0] = User Id ,
		//Instance_Details[1] = Password , 
		//Instance_Details[2] = Instance
		//******************//
		System.out.println(query);
		postData=query;

		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(
				new AuthScope(new HttpHost(Instance_Details[2])),
				new UsernamePasswordCredentials(Instance_Details[0], Instance_Details[1]));
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCredentialsProvider(credsProvider)
				.build();

		try {

			String PostURL="https://"+Instance_Details[2]+"/"+Table_Name+".do?JSONv2&sysparm_action=deleteMultiple";  
			HttpPost httpPost = new HttpPost(PostURL);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			HttpEntity entity = new ByteArrayEntity(postData.getBytes("utf-8"));
			httpPost.setEntity(entity);
			System.out.println("Executing request " + httpPost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpPost);
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
		
		public static void delete_DEMO(String query,String Table_Name) throws Exception {

			String[] Instance_Details=new String[3];

			String postData=null;

			Instance_Details=utils_DataCreation.readFrom_ConfigFile.GetCredentials_Delete_DEMO();  
			//******************//
			//Instance_Details[0] = User Id ,
			//Instance_Details[1] = Password , 
			//Instance_Details[2] = Instance
			//******************//
			System.out.println(query);
			postData=query;

			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(
					new AuthScope(new HttpHost(Instance_Details[2])),
					new UsernamePasswordCredentials(Instance_Details[0], Instance_Details[1]));
			CloseableHttpClient httpclient = HttpClients.custom()
					.setDefaultCredentialsProvider(credsProvider)
					.build();

			try {

				String PostURL="https://"+Instance_Details[2]+"/"+Table_Name+".do?JSONv2&sysparm_action=deleteMultiple";  
				HttpPost httpPost = new HttpPost(PostURL);
				httpPost.setHeader("Accept", "application/json");
				httpPost.setHeader("Content-Type", "application/json");
				HttpEntity entity = new ByteArrayEntity(postData.getBytes("utf-8"));
				httpPost.setEntity(entity);
				System.out.println("Executing request " + httpPost.getRequestLine());
				CloseableHttpResponse response = httpclient.execute(httpPost);
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




//  public static void main(String args[]) throws Exception
//  {
////	  delete("{\"sysparm_query\":\"assigned_to=fed27f134fef0a00c7e5495d0210c7ad^approval!=approved\"}","u_configuration_task") ;  // query for task
////      delete("{\"sysparm_query\":\"sys_idINf9ddc9424f215240a0392ae6f110c7f0,5010dd464f215240a0392ae6f110c770\"}","cmdb_rel_ci")  ;  // query for relationship table
////	 
//	//  delete("{\"sysparm_query\":\"sys_idINf9ddc9424f215240a0392ae6f110c7f0,5010dd464f215240a0392ae6f110c770,ae5191864f215240a0392ae6f110c733,19f4e78b4f715200c7e5495d0210c79e,47cfe6b64ff15200a0392ae6f110c7b8,cfcfe6b64ff15200a0392ae6f110c7b7,e97d76934f355200c7e5495d0210c7f5,8c5a47974f355200c7e5495d0210c79f,1d9259864f215240a0392ae6f110c7cc,82b6ae6e4f695240a0392ae6f110c7f9,6909bc024f215240a0392ae6f110c7b4\"}","cmdb_rel_ci");
//
//	  delete("{\"sysparm_query\":\"sys_idIN48fcbc424f215240a0392ae6f110c75c,1dfcbc424f215240a0392ae6f110c78b,620dbc424f215240a0392ae6f110c7ba,a36d4f534ff95200a0392ae6f110c794,4f76e62e4f695240a0392ae6f110c784,230506b64fb15200a0392ae6f110c7de,01f1262e4f695240a0392ae6f110c78a\"}","cmdb_ci")  ;  // query for ci
//
//
//
//
//
//
//
//
//  }
  
}









































































































































































  
  // query for Configuration task :
  // postData="{\"sysparm_query\":\"assigned_to=TESM_CMDB_SYS_MGR\""+","+"\"approval != approved\"}";
  // query for Relationships
  //  String postData="{\"sysparm_query\":\"sys_idINf9ddc9424f215240a0392ae6f110c7f0,5010dd464f215240a0392ae6f110c770\"}";
  // query for CI
  //  String postData="{\"sysparm_query\":\"sys_idIN1dfcbc424f215240a0392ae6f110c78b,bcecbc424f215240a0392ae6f110c74a\"}";
  