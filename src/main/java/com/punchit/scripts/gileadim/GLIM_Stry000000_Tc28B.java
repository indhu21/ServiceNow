package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class GLIM_Stry000000_Tc28B extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc28B",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd,String configItem, String repCust, 
								String asgGroup, String desc,String aUser,String causingCI1,
								String causingCI2, String filter1, String filter2) {

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);


			// Step 2: click on WIP		
			IncidentPage incident = home.clickCreateNewforFailure();

			// Take a note of the INC number.
			String incNumber = incident.getIncidentNumber();
			System.out.println(incNumber);

			// Step 8: Enter all Mandatory fields
			incident.createIncidentAndOpenIncident(configItem, repCust, asgGroup, desc, incNumber, aUser)
					.clickResolveIncidentWithAlertAccept()	
//					.verifyResolutionInformationCausingCIIsEnabled()
//					.verifyCausingCIMandatory()
					.clickResolutionInformationForNegative()
					.clickCausingCISpyGlass()
					.selectFilter(filter1,filter2, causingCI1)
					.verifyNORecordsDisplayed(causingCI1)
					.clickCausingCISpyGlass()
					.selectFilter(filter1,filter2, causingCI2)
					.verifyNORecordsDisplayed(causingCI2);
	

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_Stry000000_Tc28B")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc28B");
		return arrayObject;
	}
}

