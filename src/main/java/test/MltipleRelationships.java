package test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class MltipleRelationships 
{
	
public static void  attachrelationships(String Parentname) throws ClientProtocolException, IOException
{
String string1="{\"records\":[{\"__status\":\"success\"" + "," + "\"parent\":\"";
String string2="\"" + "," + "\"type\":\"Connects to::Connected by\"" + "," + "\"sys_created_by\":\"Punch\"" + "," + "\"child\":\"ldnsw034.tesm.com\"" + "," + "\"connection_strength\":\"Always\"" + "," + "\"sys_mod_count\":\"0\"}" + "," + "{\"__status\":\"success\"" + "," + "\"parent\":\"";
String string3="\"" + "," + "\"type\":\"Connects to::Connected by\"" + "," + "\"sys_created_by\":\"Punch\"" + "," + "\"child\":\"nkyrt0471.tesm.com\"" + "," + "\"connection_strength\":\"Always\"" + "," + "\"sys_mod_count\":\"0\"}]}";

String postData = string1+Parentname+string2+Parentname+string3;
	                                                                                                                                                                                                                                                                                                                         	                                                                                                                                                                                               
CredentialsProvider credsProvider = new BasicCredentialsProvider();
credsProvider.setCredentials(
new AuthScope(new HttpHost("tesmdemo.service-now.com")),
new UsernamePasswordCredentials("punchagent", "punchagent"));
//  new UsernamePasswordCredentials("WebServiceUser", "WebServiceUserPassword"));
CloseableHttpClient httpclient = HttpClients.custom()
.setDefaultCredentialsProvider(credsProvider)
.build();

try
{
HttpPost httpPost = new HttpPost("https://tesmdemo.service-now.com/cmdb_rel_ci.do?JSONv2&sysparm_action=insertMultiple");
httpPost.setHeader("Accept", "application/json");
httpPost.setHeader("Content-Type", "application/json");
HttpEntity entity = new ByteArrayEntity(postData.getBytes("utf-8"));
httpPost.setEntity(entity);

System.out.println("Executing request " + httpPost.getRequestLine());
CloseableHttpResponse response = httpclient.execute(httpPost);
try 
{
System.out.println("----------------------------------------");
System.out.println(response.getStatusLine());
String responseBody = EntityUtils.toString(response.getEntity());
System.out.println(responseBody);
}
finally {
response.close();
}
}
finally {
httpclient.close();
}
}


}
	




