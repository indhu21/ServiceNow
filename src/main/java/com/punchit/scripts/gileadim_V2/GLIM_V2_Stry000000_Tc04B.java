package com.punchit.scripts.gileadim_V2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_Tc04B extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_Tc04B",groups="IncidentManagement")
	public void IncReportingCustAndAffUserLockOfFieldsSD(String regUser, String regPwd,
							   String configItem, String repCust, 
							   String asgGroup, String desc,
							   String aUser, String user1, String user2){

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
			
			// Step 3: Enter all Mandatory fields
			incident.createIncidentWithAffectedUser(configItem, repCust, asgGroup, desc, aUser, incNumber, regUser);
			
			// Enter Testuser2 into the Affected User field.  
//			enterAffectedUser(aUser).
//			submitWithoutReport(). // Save the ticket
//			
//			searchandClickIncident(incNumber).
//			
//			
//			
//			searchIncident(incNumber).
//			clickFirstIncident().
			
			incident.enterReportingCustomerAndSave(user1)
					.enterAffectedUserAndSave(user2);
			
//			enterReportingCustomer(aUser).saveIncidentExpectingFailure().
//			incident.isReportingCustomerEnabled(). // Verify the Reporting Customer Field is enabled
//			isAffectedUserEnabled(); // Verify the Affected User Field is enabled
//					
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_Tc04B")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_Tc04B");
		return arrayObject;
	}
}

