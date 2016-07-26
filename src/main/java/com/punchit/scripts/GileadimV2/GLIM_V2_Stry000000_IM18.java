package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM18 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_IM18",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
							   String configItem, String repCust, 
							   String asgGroup, String desc,
							   String aUser, String user1, String user2){

		// Pre-requisities
		try {

			launchApp(browserName, true);

			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
		
			IncidentPage incident = 
					home.clickCreateNew();
			
			String incNumber = 
					incident.getIncidentNumber();
			System.out.println(incNumber);
			
			incident.createIncidentWithAffectedUser(configItem, repCust, asgGroup, desc, aUser, incNumber, regUser)
					.enterReportingCustomerAndSave(user1)
					.enterAffectedUserAndSave(user2)
					.createIncidentWithAffectedUser1(aUser, aUser)
					.isNotVisibleRepCustomer();
					
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM18")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM18");
		return arrayObject;
	}
}

