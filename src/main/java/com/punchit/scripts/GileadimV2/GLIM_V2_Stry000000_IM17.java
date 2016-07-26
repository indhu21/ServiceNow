package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM17 extends SuiteMethods {

	@Test(dataProvider = "GLIM_V2_Stry000000_IM17",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
							   String configItem, String repCust, 
							   String asgGroup, String desc,
							   String aUser ){

		try {

				launchApp(browserName, true);

			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
		
			IncidentPage incident = 
					home.clickCreateNew();
			
			String incNumber = incident.getIncidentNumber();
			System.out.println(incNumber);						
		
			incident.createIncidentWithAffectedUser(configItem, repCust, asgGroup, desc, aUser, incNumber, regUser)
					.isReportingCustomerDisabled()
					.isAffectedUserDisbled();
			
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM17")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM17");
		return arrayObject;
	}
}
