package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_Stry000000_Tc61 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc61",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);
			String[] filter={"Active = false", "Assignment Group = Contact Center"};
			
			//Step 1A: Navigate to incident and click closed 
			home.clickClosed()
				.verifyFilterText(filter)
				.isNewButtonExists()
				.verifyData()
				.isFieldEditable()
				.addPersonalizedListColumn()
				.isDeleteIncidentUsingActionsOnSelectedRows()
				.isFilterEditable();
			home.clickClosedWithoutReport()
				 .verifyFilterTextAterReset(filter);
			
			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_Stry000000_Tc61")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc61");
		return arrayObject;
	}
}
