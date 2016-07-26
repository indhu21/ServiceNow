package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_Stry000000_Tc56 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc56",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);
			String[] filter={"SPARC Active = true", "Assigned To = "+regUser};

			//Step 1A: Navigate to incident and click assign to me 
			home.expandIncidentMenu().
			clickAssignedToMe()
			.verifyFilterText(filter)
			//Step 1B: Only Incidents assigned to you that are Active but not in a state of Resolved will be shown 
			.verifyNonResolvedforthirdFilter(regUser.toUpperCase());
		home.clickAssignedToMeWithoutReport()
			.isNewButtonExists()
//			.rightClickonFirstAlert()
//			.ClickAssignToMe()
			.rightClickAndAssignToMe()
			.verifyAssignedToError(regUser.toUpperCase())
			.isFieldEditable()			
			.isDeleteIncidentUsingActionsOnSelectedRows()
			.addPersonalizedListColumn()
			.isFilterEditable();
			
//			home.clickAssignedToMe()
			home.clickAssignedToMeWithoutReport()
				.verifyFilterTextAterReset(filter);
//			.verifyFilterText(filter);
			

			



			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_Stry000000_Tc56")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc56");
		return arrayObject;
	}
}
