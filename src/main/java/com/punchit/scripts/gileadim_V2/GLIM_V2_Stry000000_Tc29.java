package com.punchit.scripts.gileadim_V2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_Tc29 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_Tc29",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
			String configItem, String repCust, 
			String asgGroup, String desc,
			String aUser, String filter1,
			String filter2, String filter3) {

		String [] ele = {"Account unlocked",	
				"Cancelled - SR",	
				"Cancelled by User",	
				"Capacity Management",	
				"Cooling restored",	
				"Data retrieved/restored",
				"DHCP reset",	
		"Documentation updated"};
		String[] ele1={"Hardware replaced/fixed",	
				"Passed to External Vendor",	
				"Pwd Change",
				"Software upgrade/install",	
				"Training suggested",	
				"Training/Knowledge provided",
		"Unable to reproduce"};

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
//			incident
//			.populateMandatoryFields(configItem, repCust, asgGroup, desc)
//			.enterAssignedTo(aUser.toUpperCase())
//			.submit()
//			.searchIncident(incNumber)
//			.clickFirstIncident()
			incident.createIncidentWithAssignedToAndOpenIncident(configItem, repCust, asgGroup, desc, incNumber, aUser)
//			.clickResolveIncidentWithAlertAccept()
////			.alertAcceptforResolve()
//			.isExistResolutionInformation()
//			.isExistResolutionCodefield()
			.VerifyResolutionInformationAndCodefieldExists()
//			.clickCausingCISpyGlass()
//			.selectFilter(filter1, filter2, filter3)
//			.clickFirstName()
//			.clickCausingCIAndSelectFilter(filter1, filter2, filter3)
//			.clickResolutioCodeSpyGlass()
			.enterCausingCIandClickResolutionCodeSpyglass(filter3)
//			.clickResolutioCodeSpyGlass()
			/*.verifyResolutionCodeSpyGlass(ele, false)
			.verifyResolutionCodeSpyGlass(ele1, true);*/
			.verifyResolutionCodeSpyGlassCIcounts();

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_Tc29")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_Tc29");
		return arrayObject;
	}
}
