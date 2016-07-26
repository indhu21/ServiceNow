package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM16 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_IM16",groups="IncidentManagement")
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
						
//			// Step 3: Enter all Mandatory fields
//			incident.populateMandatoryFields(configItem, repCust, asgGroup, desc);
//			
//			// Enter Testuser2 into the Affected User field.  
//			incident.enterAffectedUser(aUser);
//			
//			// Save the ticket
//			incident.submit();
			
			incident.createIncidentWithAffectedUser(configItem, repCust, asgGroup, desc, aUser, incNumber, regUser);
			
			// Step 2: click on create new
			incident = home.clickCreateNew();
			
			incNumber = incident.getIncidentNumber();
			System.out.println(incNumber);
			
			incident.createIncidentWithoutReportingCus(configItem, repCust, asgGroup, desc, aUser, incNumber, regUser);
			
			
			// Step 3: Enter all Mandatory fields
//			incident.enterConfigurationItem(configItem).enterAssignmentGroup(asgGroup).enterShortDescription(desc);
			
			// Enter Testuser2 into the Affected User field.  
//			incident.enterAffectedUser(aUser);
			
			// Save the ticket
//			incident.submitIncidentExpectingFailure();
			
			// Confirm the error Message
//			incident.verifyErrorMessage("The following mandatory fields are not filled in: Reporting Customer");
//			
//			// Remove the name from the Affected User field..  
//			incident.enterAffectedUserAndReportingCustomer(aUser);
//			.enterAffectedUser("");
//			
//			// Enter “Testuser1” into the Reporting customer field and Save the ticket.
//			incident.enterReportingCustomer(aUser);
			incident = home.clickCreateNewwithAlertAccept();

			incNumber = incident.getIncidentNumber();
			System.out.println(incNumber);
			
			// Save the ticket
//			incident.submitWithoutReport().searchandClickIncident(incNumber).verifyReportingCustomerAffectedUser();
			incident.createIncidentWithoutaffectedUser(configItem, repCust, asgGroup, desc, aUser, incNumber, regUser)	;	
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM16")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM16");
		return arrayObject;
	}
}
