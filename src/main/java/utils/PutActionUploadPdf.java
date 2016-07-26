package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
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

//import org.json.simple.JSONObject;




import testng.SuiteMethods_ServiceNowFrontEnd;
import utils_DataCreation.readFrom_ConfigFile;
import wrapper.GenericWrappers;

public class PutActionUploadPdf extends SuiteMethods_ServiceNowFrontEnd{

	
	public static void uploadPdf(String Testinstanceid,String sysId,String[] credentials) throws ClientProtocolException, IOException
	{
		System.out.println("Testinstanceid "+Testinstanceid);
		System.out.println("sysId "+sysId);
		
		String pdfname=QueryDB_ServiceNowFrontEnd.readPDFname(sysId);

        pdfname=pdfname+".pdf";
		
//		pdfname="TMTI0001221_INFRSPriorityField"+".pdf";
		//File originalFile = new File("G:/Punch it/DEMO/ServiceNow/reports/"+pdf1);
//	    System.out.println("PDF Upload"+GenericWrappers.getAbsolutePath()+"reports/");	
//        File originalFile = new File(GenericWrappers.getAbsolutePath()+"reports/"+pdfname);
        File originalFile = new File("/home/ubuntu/ServiceNow/reports/"+pdfname);
        
        System.out.println(pdfname);
//	    File originalFile = new File("./reports/"+pdfname);
	    	
        String encodedBase64 = null;
        
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
            byte[] bytes = new byte[(int)originalFile.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = DatatypeConverter.printBase64Binary(bytes);
//            encodedBase64 = new String(Base64.getEncoder().encodeToString(bytes));
            System.out.println("Encode "+encodedBase64);
            
/*            byte[] message = encodedBase64.getBytes("UTF-8");
            String encoded = DatatypeConverter.printBase64Binary(message);
            byte[] decoded = DatatypeConverter.parseBase64Binary(encoded);

            System.out.println(encoded);
            System.out.println(new String(decoded, "UTF-8"));
*/            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        String pdfName=pdfname;
        
    	String postData = "{'agent':'AttachmentCreator','topic':'AttachmentCreator',"
    			+ "'name':'"+pdfName+"','source':'tm_test_instance:"+Testinstanceid+"',"
    					+ "'payload':'"+encodedBase64+"'}";
    		
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
	        credsProvider.setCredentials(
	        		 new AuthScope(new HttpHost(credentials[2])),
	        		 new UsernamePasswordCredentials(credentials[0], credentials[1]));
	        CloseableHttpClient httpclient = HttpClients.custom()
	                .setDefaultCredentialsProvider(credsProvider)
	                .build();
	
	try {
       	    HttpPost httpPost = new HttpPost("https://"+credentials[2]+"/api/now/table/ecc_queue");
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
	public static void main(String[] args) throws ClientProtocolException, FileNotFoundException, IOException {
		uploadPdf("TMTI0001221_INFRSPriorityField.pdf", "0acb50c44f1f1a005bf37d218110c717", readFrom_ConfigFile.GetCredentials_TESMDEMO());
	}
}




