package com.punchit.scripts.gileadim_V2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_Tc04A extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_Tc04A",groups="IncidentManagement")
	public void IncReportingCustAndAffUserLockOfAieldsITIL(String regUser, String regPwd,
							   String configItem, String repCust, 
							   String asgGroup, String desc,
							   String aUser ){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);
		
			// Step 2: click on create new
			IncidentPage incident = home.clickCreateNew();
			
			// Take a note of the INC number.
			String incNumber = incident.getIncidentNumber();
			System.out.println(incNumber);						
		
			incident.createIncidentWithAffectedUser(configItem, repCust, asgGroup, desc, aUser, incNumber, regUser);
			
			// Step 2: click on create new
			incident = home.clickCreateNew();
			
			incNumber = incident.getIncidentNumber();
			System.out.println(incNumber);
			
			incident.createIncidentWithoutReportingCus(configItem, repCust, asgGroup, desc, aUser, incNumber, regUser);
			
			incident = home.clickCreateNewwithAlertAccept();

			incNumber = incident.getIncidentNumber();
			System.out.println(incNumber);
			
			incident.createIncidentWithoutaffectedUser(configItem, repCust, asgGroup, desc, aUser, incNumber, regUser)	;	
			incident.isReportingCustomerDisabled(). //
			isAffectedUserDisbled();
			
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_Tc04A")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_Tc04A");
		return arrayObject;
	}
}
