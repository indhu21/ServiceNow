package com.punchit.scripts.im;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class IM_Stry000000_Tc74 extends SuiteMethods {
	
	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc74",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
							   String configItem, String repCust, 
				               String asgGroup, String desc,
				               String aUser, String filter1,
				               String filter2, String filter3) {
		
		String [] ele = {"Account Locked",
				"Cancelled � SR",
				"Cancelled � By User",
				"Capacity Management", 
				"Data retrieved/restored",
				"DHCP reset",
				"DNS restored",
				"Documentation Updated"};
		String [] ele1={"Emergency Fix",
				"Passed to external vendor",
				"Pwd Change",
				"Software upgrade/install",
				"Training suggested",
				"Training/kowledge provided",
				"Unabled to reproduce"};
		
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
			.clickResolveIncident()
			.alertAcceptforResolve()
			.isExistResolutionInformation()
			.isExistResolutionCodefield()
			.clickCausingCISpyGlass()
			.selectFilter(filter1, filter2, filter3)
			.clickFirstName()
			.clickResolutioCodeSpyGlass()
			.verifyResolutionCodeSpyGlass(ele);
	incident.clickResolutioCodeSpyGlass1()
			.verifyResolutionCodeSpyGlass(ele);

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc74")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc74");
		return arrayObject;
	}
}
