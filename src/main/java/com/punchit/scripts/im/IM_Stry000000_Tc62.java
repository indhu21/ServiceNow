package com.punchit.scripts.im;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class IM_Stry000000_Tc62 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc62",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);
			String[] filter={"All"};

			//Step 1A: Navigate to incident and click assign to me 
			home.expandIncidentMenu()
			.clickAll()
			.verifyFilterText(filter)
			.isNewButtonExists()
			.isFieldEditable()

			.addPersonalizedListColumn()
			.isFilterEditable();

			home.clickAll()
			.verifyFilterText(filter)
			.rightClickonFirstAlert()
			.ClickAssignToMe()
			.verifyAssignedToError(regUser.toUpperCase())
			.isDeleteIncidentUsingActionsOnSelectedRows();

			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc62")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc62");
		return arrayObject;
	}
}
