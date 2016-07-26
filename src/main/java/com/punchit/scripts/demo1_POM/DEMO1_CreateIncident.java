package com.punchit.scripts.demo1_POM;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import demo.IncidentPage_ServiceNowDemo;
import demo.LoginPage_ServiceNowDemo;
import demo.MenuPage_ServiceNowDemo;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;


public class DEMO1_CreateIncident extends  SuiteMethods{
	
	
	@Test(dataProvider = "DEMO1_STRY0000001_TC01",groups="DemoIncident")
	public void createIncident (String regUser, String regPwd, String affuser, String cat,
			String subcat, String ci, String impact, String urgency, String asngrp,
			String shrtdes) {
		
		try {

				launchApp(browserName, true);

				MenuPage_ServiceNowDemo home = 
								new LoginPage_ServiceNowDemo()
								.loginAs(regUser, regPwd);
			
				IncidentPage_ServiceNowDemo incident =
							home.clickCreateNew();
				String incNumber =
						incident.getIncidentNumber();
						
						incident.createIncidentForDemo(affuser, cat, subcat, impact, urgency, asngrp, shrtdes)
								.searchIncidentforDemo(incNumber);
						
							home.clickLogout();
				
			status="PASS";
			
		} finally {
			snW.switchToDefault();
			if(snW.isExistByXpath("Logout_Xpath"))
				{if (!snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("The Log out could not be clicked","FAILURE");}
			snW.quitBrowser();
			}

}

	@DataProvider(name = "DEMO1_STRY0000001_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("DEMO1_STRY0000001_TC01");
		return arrayObject;
	}
}