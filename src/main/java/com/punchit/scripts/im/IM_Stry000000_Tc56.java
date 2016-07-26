package com.punchit.scripts.im;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class IM_Stry000000_Tc56 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc56",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);
			String[] filter={"SPARC Active = true", "Assigned To = TEST_INC_SD"};

			//Step 1A: Navigate to incident and click assign to me 
			home.expandIncidentMenu().
			clickAssignedToMe()
			.verifyFilterText(filter)
			//Step 1B: Only Incidents assigned to you that are Active but not in a state of Resolved will be shown 
			.verifyNonResolved(filter, regUser.toUpperCase())
			.isNewButtonExists()
			.rightClickonFirstAlert()
			.ClickAssignToMe()
			.verifyAssignedToError(regUser.toUpperCase())
			.isFieldEditable()			
			.isDeleteIncidentUsingActionsOnSelectedRows()
			.addPersonalizedListColumn()
			.isFilterEditable();
			
			home.clickAssignedToMe()
			.verifyFilterText(filter);

			



			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc56")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc56");
		return arrayObject;
	}
}
