package com.punchit.scripts.sn;

 
import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
 
public class GetAction {
	public static void main(String[] args) throws IOException, HttpException {
 	 
 	//public void getRequest() throws HttpException, IOException {
 		CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(new HttpHost("dev12057.service-now.com")),
                new UsernamePasswordCredentials("ashish.rw", "ashishrw1"));
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();
 
        try {
            HttpGet httpget = new HttpGet("https://dev12057.service-now.com/api/now/table/u_testcase_run/fa9de3884fc412009b1a30318110c78c");
            httpget.setHeader("Accept", "application/json");
            System.out.println("Executing request " + httpget.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                System.out.println("----------------------------------------");
              //  System.out.println(response.getEntity("sys_id))
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