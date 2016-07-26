package com.punchit.scripts.im;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.IncidentsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class IM_Stry000000_Tc28A extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc28A",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String configItem, String repCust, 
			String asgGroup, String desc,String aUser, String causingCI1,String causingCI2,String causingCI3,String causingCI4) {

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);

			// Step 2: click on WIP		
			IncidentPage incident = home.clickCreateNew();

			// Take a note of the INC number.
			String incNumber = incident.getIncidentNumber();
			System.out.println(incNumber);

			// Step 8: Enter all Mandatory fields
			incident
			.populateMandatoryFields(configItem, repCust, asgGroup, desc)
			.enterAssignedTo(aUser)
			.submit()
			.searchIncident(incNumber)
			.clickFirstIncident()
			.clickResolveIncident().alertAccept();
			incident
			.clickResolutionInformation()			
			.verifyResolutionInformationCausingCIIsEnabled()
			.verifyCausingCIMandatory()
			.enterAndChooseCausingCI(causingCI1)
			.enterAndChooseCausingCI(causingCI2)
			.enterAndChooseCausingCI(causingCI4)
			.enterAndChooseCausingCI(causingCI3);
			


			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc28A")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc28A");
		return arrayObject;
	}
}
