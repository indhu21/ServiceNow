package test;
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

public class CICreationDemo {
	public static void createCI(String name) throws IOException, HttpException {
 	         // This must be valid json string with valid fields and values from table
		
 	 		//String postData = "{\"short_description\":\"Test with java post\"}";
   String string1="{\"cpu_count\":\"12\"" + "," + "\"checked_in\":\"test\"" + "," + "\"po_number\":\"4152\"" + "," + "\"correlation_id\":\"4125\"" + "," + "\"supported_by\":\"4152\"" + "," + "\"first_discovered\":\"\"" + "," + "\"owned_by\":\"\"" + "," + "\"os\":\"AIX\"" + "," + "\"gl_account\":\"\"" + "," + "\"dr_backup\":\"\"" + "," + "\"name\":\"";
	 String string2="\"" + "," + "\"first_discovered\":\"\"" + "," + "\"owned_by\":\"\"" + "," + "\"asset\":\"\"" + "," + "\"managed_by\":\"\"" + "," + "\"maintenance_schedule\":\"\"" + "," + "\"hardware_status\":\"Installed\"" + "," + "\"category\":\"Hardware\"" + "," + "\"os_address_width\":\"\"" + "," + "\"cpu_name\":\"\"" + "," + "\"used_for\":\"Production\"" + "," + "\"delivery_date\":\"\"" + "," + "\"install_status\":\"Installed\"" + "," + "\"virtual\":\"false\"" + "," + "\"cd_rom\":\"false\"" + "," + "\"dns_domain\":\"\"" + "," + "\"disk_space\":\"40\"" + "," + "\"default_gateway\":\"\"" + "," + "\"change_control\":\"\"" + "," + "\"os_domain\":\"\"" + "," + "\"checked_out\":\"\"" + "," + "\"purchase_date\":\"\"" + "," + "\"order_date\":\"\"" + "," + "\"cpu_speed\":\"1,650\"" + "," + "\"skip_sync\":\"false\"" + "," + "\"__status\":\"success\"" + "," + "\"firewall_status\":\"Intranet\"" + "," + "\"lease_id\":\"\"" + "," + "\"vendor\":\"\"" + "," + "\"cpu_type\":\"Power5+\"" + "," + "\"cd_speed\":\"\"" + "," + "\"host_name\":\"\"" + "," + "\"sys_created_by\":\"glide.maint\"" + "," + "\"cpu_core_count\":\"\"" + "," + "\"subcategory\":\"Computer\"" + "," + "\"classification\":\"Production\"" + "," + "\"os_service_pack\":\"\"" + "," + "\"start_date\":\"\"" + "," + "\"comments\":\"\"" + "," + "\"location\":\"\"" + "," + "\"unverified\":\"false\"" + "," + "\"justification\":\"\"" + "," + "\"cpu_core_thread\":\"\"" + "," + "\"sys_domain\":\"global\"" + "," + "\"sys_tags\":\"\"" + "," + "\"sys_mod_count\":\"9\"" + "," + "\"cost_cc\":\"USD\"" + "," + "\"monitor\":\"false\"" + "," + "\"cpu_manufacturer\":\"\"" + "," + "\"sys_updated_on\":\"2015-01-26 06:05:30\"" + "," + "\"warranty_expiration\":\"\"" + "," + "\"invoice_number\":\"\"" + "," + "\"fqdn\":\"\"" + "," + "\"cost\":\"3,199.99\"" + "," + "\"ip_address\":\"\"" + "," + "\"last_discovered\":\"\"" + "," + "\"manufacturer\":\"\"" + "," + "\"model_id\":\"IBM BladeCenter Blade HS22\"" + "," + "\"company\":\"\"" + "," + "\"due\":\"\"" + "," + "\"chassis_type\":\"null\"" + "," + "\"asset_tag\":\"P1000055\"" + "," + "\"discovery_source\":\"null\"" + "," + "\"assignment_group\":\"\"" + "," + "\"can_print\":\"false\"" + "," + "\"department\":\"\"" + "," + "\"support_group\":\"\"" + "," + "\"sys_created_on\":\"2006-07-11 14:28:47\"" + "," + "\"hardware_substatus\":\"null\"" + "," + "\"cost_center\":\"\"\"short_description\":\"\"" + "," + "\"sys_updated_by\":\"alex.wells\"" + "," + "\"sys_class_name\":\"UNIX Serve\"}";
	     String postData = string1+name+string2;                                     
     
               System.out.println(postData)  ;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           // ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,                                                                                                                                                                                                                                                                                            ,,,,,,"name":"dbaix900nyc","form_factor":null,"due_in":null,"install_date":"2013-09-14 01:00:00","assigned":"2014-01-25 23:00:00","os_version":"5L","serial_number":"","assigned_to":"","mac_address":"","model_number":"","schedule":"","ram":"8,192",,"attributes":"","fault_count":"0","operational_status":"Operational"}";
      //String postData = string1+name+string2;
      CredentialsProvider credsProvider = new BasicCredentialsProvider();
 	        credsProvider.setCredentials(
 	        		  new AuthScope(new HttpHost("tesmdemo.service-now.com")),
 	              //  new AuthScope(new HttpHost("sparctest.service-now.com")),
 	             //  new UsernamePasswordCredentials("TEST_CMDB_SYS_MGR", "tesmtest"));
 	                new UsernamePasswordCredentials("punchagent", "punchagent"));
 	        CloseableHttpClient httpclient = HttpClients.custom()
 	                .setDefaultCredentialsProvider(credsProvider)
 	                .build();
 
        try {
        	HttpPost httpPost = new HttpPost("https://tesmdemo.service-now.com/cmdb_ci_unix_server.do?JSONv2&sysparm_action=insertMultiple");
        //	HttpPost httpPost = new HttpPost("https://sparctest.service-now.com/cmdb_ci_linux_server.do?JSON&sysparm_action=insertMultiple");
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
               /// MltipleRelationships.attachrelationships(name);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
 	}
 public static void main(String args[]) throws IOException, HttpException
 {
	 createCI("Punch Test server123_test");
 }
}


