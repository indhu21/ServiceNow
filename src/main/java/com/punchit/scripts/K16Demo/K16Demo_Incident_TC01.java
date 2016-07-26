package com.punchit.scripts.K16Demo;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import pages.IncidentPage_knowlwge16;
import pages.LoginPage_knowlwge16;
import pages.MenuPage_knowlwge16;
import testng.SuiteMethods_ServiceNowFrontEnd;
import utils.Reporter;
import utils.Reporter_ServiceNow;
import utils_DataInputProvideMySQL.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class K16Demo_Incident_TC01 extends SuiteMethods_ServiceNowFrontEnd {
	
	

	@Test
	public void createIncident () throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		 snW = new ServiceNowWrappers(entityId);
		 
		 testDescription = "Create Incident";
		 
		try {
			 System.out.println("Sixth loop" +entityId);
			
			HashMap<String,List<String>> values=DataInputProvider.GetData("Create an Incident");

			snW.launchApp(browserName, true);

			MenuPage_knowlwge16 home = 
					new LoginPage_knowlwge16()
			.loginAsForDemo(DataInputProvider.GetInputDataValue(values,"User_ID"), DataInputProvider.GetInputDataValue(values,"Password"));

			values.remove("User_ID");

			values.remove("Password");

			IncidentPage_knowlwge16 incident =
					home.clickCreateNew();

			String incNumber =
					incident.getIncidentNumber();
			
			Object[] Keys=values.keySet().toArray();
	        
			System.out.println("size is"+values.size());
	        
			int i;int j=0;
		
	        for(i=0;i<values.size()+1;i++){
				try{
			
				//InvokeMethods.CreateField(values.keySet().toArray()array[i].toString(),values);
				InvokeMethods.CreateField(Keys[j].toString(),values);
				
				}catch(Exception e){
					System.out.println(e);
				}
				
				values.remove(Keys[j].toString());
				j=j+1;
			}
		
			if(snW.clickByXpath("Submit_Incident_Xpath"))
				Reporter_ServiceNow.reportStep("Submit Button is Clicked Successfully", "SUCCESS");
			else
				Reporter.reportStep("Submit Button could not be clicked", "FAILURE");

			if(!snW.selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
				Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

			if(snW.enterByXpathAndClick("CIS_SearchReferenceData_Xpath",incNumber)){
				Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" is created successfully", "SUCCESS");}
			else
				Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");
			
			
			home.clickLogoutdemo();

			status="PASS";

		} finally {
			snW.quitBrowser();
		}

	}


}