package test;

import java.io.IOException;

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

import utils.PutActionUploadPdf;

public class putaction_demoinstance {
public static void main(String args[]) throws IOException, HttpException {
		
		String execution_status="completed";
	    String format1 = "{\"status\":\"";
		//String format1 = "{\"u_state\":\"";
		String format2 ="\"";
		String format3=""+"";
		String format4=",";
		String format5="\"execution_status\":\"";
		//String format5="\"u_execution_status\":\"";
		String format6="\"}";
		String format7="\"execution_time\":\"";
		//String format7="\"u_execution_time\":\"";
		//String format8="\"u_url_1\":\"";
		//String pdf="http://52.88.205.5/downloadpdf.php?runtcid="+sysid+"&tccode="+sysid;
		String status="PASS";
		String execution_time="11";
		String postData= format1+status+format2+format3+format4+format3+format5+execution_status+format2+format3+format4+format3+format7+execution_time+format6;
				//format3+format4+format3+format8+pdf+format6;
		System.out.println(postData);
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
	    credsProvider.setCredentials(
	    new AuthScope(new HttpHost("tesmdemo.service-now.com")),
	    		//new AuthScope(new HttpHost("dev12057.service-now.com")),
	    //new UsernamePasswordCredentials("ashish.rw", "ashishrw1"));
		new UsernamePasswordCredentials("punchagent", "punchagent"));
	        CloseableHttpClient httpclient = HttpClients.custom()
	                .setDefaultCredentialsProvider(credsProvider)
	                .build();

    try {
    	//HttpPut httpPut = new HttpPut("https://dev12057.service-now.com/api/now/table/u_testcase_run/"+sysid);    
    	HttpPut httpPut = new HttpPut("https://tesmdemo.service-now.com/api/now/table/x_tori2_automated_test_case_run/dd323d9bc8d8da00b74a306bb2c446e8");	
    	    httpPut.setHeader("Accept", "application/json");
	 		httpPut.setHeader("Content-Type", "application/json");
	        HttpEntity entity = new ByteArrayEntity(postData.getBytes("utf-8"));
	 		httpPut.setEntity(entity);

        System.out.println("Executing request " + httpPut.getRequestLine());
          String sysid="dd323d9bc8d8da00b74a306bb2c446e8";
    //    PutActionUploadPdf.uploadPdf(sysid);
        putaction_demoinstance_uploadpdf.uploadPdf(sysid);
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



