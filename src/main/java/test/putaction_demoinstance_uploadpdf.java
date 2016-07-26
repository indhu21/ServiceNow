package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import testng.SuiteMethods_1;

public class putaction_demoinstance_uploadpdf extends SuiteMethods_1{

	public static void uploadPdf(String sysId) throws ClientProtocolException, IOException
	{
		String pdf1=sysId+".pdf";
		//File originalFile = new File("I:/New Workspace/ServiceNow/reports/"+pdf1);
		File originalFile = new File("G:/Punch it/DEMO/ServiceNow/reports/"+pdf1);
        String encodedBase64 = null;
        
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
            byte[] bytes = new byte[(int)originalFile.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = new String(Base64.getEncoder().encodeToString(bytes));
            //System.out.println(encodedBase64);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        String pdfName=pdf1;
		String postData = "{'agent':'AttachmentCreator','topic':'AttachmentCreator','name':'"+pdfName+"','source':'tm_test_instance:"+sysId+"','payload':'"+encodedBase64+"'}";
		//String postData = "{'agent':'AttachmentCreator','topic':'AttachmentCreator','name':'"+pdfName+"','source':'u_testcase_run:"+sysId+"','payload':'"+encodedBase64+"'}";
		//String postData = "{'agent':'AttachmentCreator','topic':'AttachmentCreator','name':'Test.pdf','source':'u_testcase_run:fa9de3884fc412009b1a30318110c78c','u_testcase_run.u_state':'"+encodedBase64Status+"'}";
	 		CredentialsProvider credsProvider = new BasicCredentialsProvider();
	        credsProvider.setCredentials(
	        		 new AuthScope(new HttpHost("tesmdemo.service-now.com")),
	        		 new UsernamePasswordCredentials("punchagent", "punchagent"));
	              //  new AuthScope(new HttpHost("dev12057.service-now.com")),
	               // new UsernamePasswordCredentials("ashish.rw", "ashishrw1"));
	        CloseableHttpClient httpclient = HttpClients.custom()
	                .setDefaultCredentialsProvider(credsProvider)
	                .build();
	
	try {
    	/*HttpPut httpPut = new HttpPut("https://dev12057.service-now.com/api/now/table/u_testcase_run/91dde7884fc412009b1a30318110c730");
	 		httpPut.setHeader("Accept", "application/json");
	 		httpPut.setHeader("Content-Type", "application/json");
	        HttpEntity entity = new ByteArrayEntity(postData.getBytes("utf-8"));
	 		httpPut.setEntity(entity);*/
	 		
    	HttpPost httpPost = new HttpPost("https://tesmdemo.service-now.com/api/now/table/ecc_queue");
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
public static void main(String args[]) throws ClientProtocolException, IOException
{
	uploadPdf("5e8cf63ac8e41240b74a306bb2c44639");
}

	
}




