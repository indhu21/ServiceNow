package com.punchit.scripts.demo2;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.IncidentPage_knowlwge16;
import pages.IncidentsListPage;
import pages.IncidentsListPage_DEMO;
import pages.LoginPage;
import pages.LoginPage_knowlwge16;
import pages.MenuPage;
import pages.MenuPage_knowlwge16;
import testng.SuiteMethods;
import testng.SuiteMethods_1;
import testng.SuiteMethods_demo1;
import utils.DataInputProvider;
import utils.Reporter;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Demo_Story_TC01 extends  SuiteMethods_demo1  {
	
	
	@Test(dataProvider = "Demo_Story_TC01",groups="DemoIncident")
	public void createIncident (String regUser, String regPwd, String affuser, String cat,
			String subcat, String ci, String impact, String urgency, String asngrp,
			String shrtdes) {
		
		try {

				launchApp(browserName, true);

				MenuPage_knowlwge16 home = 
								new LoginPage_knowlwge16()
								.loginAsForDemo(regUser, regPwd);
			
				IncidentPage_knowlwge16 incident =
							home.clickCreateNew();
				String incNumber =
						incident.getIncidentNumber();
						
						incident.createIncidentForDemo(affuser, cat, subcat, impact, urgency, asngrp, shrtdes)
								.searchIncidentforDemo(incNumber);
						
							home.clickLogoutdemo();
				
			status="PASS";
			
		} finally {
//			snW.logout();
			snW.quitBrowser();
			}

}

	@DataProvider(name = "Demo_Story_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("Demo_Story_TC01");
		return arrayObject;
	}
}