package com.punchit.scripts.im;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class IM_Stry000000_Tc04B extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc04B",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
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
			
			// Step 3: Enter all Mandatory fields
			incident.populateMandatoryFields(configItem, repCust, asgGroup, desc).
			
			// Enter Testuser2 into the Affected User field.  
			enterAffectedUser(aUser).
			submit(). // Save the ticket
			searchIncident(incNumber).
			clickFirstIncident().
			
			enterAffectedUser(repCust).
			enterReportingCustomer(aUser).
			isReportingCustomerEnabled(). // Verify the Reporting Customer Field is enabled
			isAffectedUserEnabled(); // Verify the Affected User Field is enabled
					
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc04B")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc04B");
		return arrayObject;
	}
}
