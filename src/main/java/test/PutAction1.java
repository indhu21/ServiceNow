package test;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;

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
//import org.json.JSONException;
//import org.json.JSONObject;

public class PutAction1 {
	public static void main(String[] args) throws IOException, HttpException {

       //String sysid="91dde7884fc412009b1a30318110c730";
//		String status="PASS";
//		//String pdf="http://52.88.205.5/downloadpdf.php?runtcid="+sysid+"&tccode="+sysid;
//	//	String format=""";
//		String execution_status="Completed";
//		String execution_time="100";
//		String execution_status1="completed";
//		String format1 = "{\"u_state\":\"";
//		String format2 ="\"";
//		String format3=""+"";
//		String format4=",";
//		String format5="\"u_execution_status\":\"";
//		String format6="\"}";
//		String format7="\"u_execution_time\":\"";
//		String format8="\"u_url_1\":\"";
		//"https%3A%2F%2Fmywebsite%2Fdocs%2Fenglish%2Fsite%2Fmybook.do" +
        //"%3Frequest_type%3D%26type%3Dprivate";
		URL url=new URL("http://52.88.205.5/downloadpdf.php?runtcid=1396&tccode=ERER");
		
	//	String url="https%3A%2F%2F52.88.205.52Fdownloadpdf.phpdownloadpdf.php3xruntcid3d13793Ftccode3dERER";
	    System.out.println(url.toString());
//		 System.out.println(url+"Hello World!------->" +
//	                new String(url.getBytes("UTF-8"),"ASCII"));
//		//String pdf1="http://52.88.205.5/downloadpdf.php?runtcid=1379&tccode=ERER";
//		System.out.println("PDf is "+pdf1);
//		String postData= format1+status+format2+format3+format4+format3+format5+execution_status1+format2+format3+format4+format3+format7+execution_time+format2+format3+format4+format3+format8+pdf1+format6;
		String postData = "{\"status\":\":\"PASS\"}";
		//String postData = "{\"status\":\"PASS\"}";
			//	+ "" + "," + "\"u_execution_status\":\"Completed\"" + ","+ "\"u_execution_time\":\"10\"" + "," + "\"u_url_1\":\"http://52.88.205.5/downloadpdf.php?runtcid=1396&tccode=ERER\"}";			 
		System.out.println(postData);
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(
				new AuthScope(new HttpHost("tesmdemo.service-now.com")),
			//	new UsernamePasswordCredentials("punchagent", "punchagent"));
		         new UsernamePasswordCredentials("ashish.rw", "ashishrw1"));
	          	 CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCredentialsProvider(credsProvider)
				.build();
		try {
			
		    HttpPut httpPut = new HttpPut("https://dev12057.service-now.com/api/now/table/u_testcase_run/9345b6f5c8841600b74a306bb2c446ca");
			httpPut.setHeader("Accept", "application/json");
			httpPut.setHeader("Content-Type", "application/json");
		   // httpPut.setHeader("Authorization","BASIC cHVuY2hhZ2VudDpwdW5jaGFnZW50");
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

