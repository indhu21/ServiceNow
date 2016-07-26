package com.punchit.scripts.im;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class IM_Stry000000_Tc57 extends SuiteMethods {

	// Create Instance
	@Test(dataProvider = "IM_Stry000000_Tc57",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd, String UserName){

		// Pre-requisities
		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Go to backend and navigate Incident
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);
			String[] filter={"Work in Progress",UserName};
			
			//Step 1A: Select Work in Progress link, There should not be a New button available at the top of the view
			home.expandIncidentMenu()
				.clickWIP()
				.verifyFilterText(filter)
				.isNewButtonExists()

			//Step 2: Right hand click an Incident, You should not be able to perform the Assign to me function and a message should show to advise you this 		
				.rightClickonFirstAlert()
				.ClickAssignToMe()
				.verifyAssignedToError(UserName)

			//Step 3: Double click on any field and try to edit	You should not be able to edit the text in any field from this view
			.isFieldEditable()
			
			//Step 4: Locate the cog button at the top of the form and select, Should have the ability to change display columns using the cog
			.addPersonalizedListColumn()
			
			//Step 5: Change filters for view using the funnel button, 
			.isDeleteIncidentUsingActionsOnSelectedRows()
			
			// Should be able to change filters  
			.isFilterEditable();

			//Step 6: Re select Open Unassigned link Filters should reset when Open link is reselected 
			home.clickWIP().verifyFilterText(filter);
			status = "PASS";
			
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc57")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc57");
		return arrayObject;
	}
}
