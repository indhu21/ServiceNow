package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_Stry000000_Tc81B extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc81B",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String configItem, String repCust, 
			String asgGroup, String desc,
			String asgTo,String errorMessage) {

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);
			
			IncidentPage incident = home.clickCreateNewforFailure();
			String incNumber = incident.getIncidentNumber();
			
			incident.createIncidentWithAssignedToAndOpenIncident(configItem, repCust, asgGroup, desc,incNumber,asgTo)

			// Step 2: click on create new
//			IncidentPage incident = home.clickCreateNew();
//
//			// Take a note of the INC number.
//			String incNumber = incident.getIncidentNumber();
//			System.out.println(incNumber);
//			incident			
//			.populateMandatoryFields(configItem, repCust, asgGroup, desc)
//			.enterAssignedTo(asgTo)
//			.submit()
//			.searchIncident(incNumber)			
//			.clickFirstIncident()
			.verifyAddAttachmentAvailable()
			.uploadFile("GLIM_Stry000000_Tc81B")			
			.RemoveAttachment()			
			.verifyUploadErrorMessage("Data",errorMessage);
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}
	}

	@DataProvider(name = "GLIM_Stry000000_Tc81B")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc81B");
		return arrayObject;
	}
}
