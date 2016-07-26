package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_Stry000000_Tc59 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc59",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Go to backend and navigate Incident
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);

			//Step 1A: Select Open link
			//Step 1B: There should not be a New button available at the top of the view

			String[] filter={"Assignment Group = (TESM_INC_GROUP, Reporting)", "Assigned To is empty"};
//			String groupName="Punch Group1";
			home.expandIncidentMenu().
			clickOpenUnAssigned()
//			.verifyNonResolvedforGroup(filter, regUser.toUpperCase(), groupName)
			.verifyNonResolvedforthirdFilter(regUser.toUpperCase());
		home.clickOpenUnAssignedWithoutReport()	
			.verifyFilterText(filter)
			.isNewButtonExists()

			//Step 2: Double click on any field and try to edit	You should not be able to edit the text in any field from this view
			.isFieldEditable()
			
			//Step 3: Right hand click an Incident, You should be able to perform the Assign to me function 		
//			.rightClickonFirstAlert().
//			ClickAssignToMe().
			.rightClickAndAssignToMe()
			.verifyAssigned(regUser.toUpperCase())
			
			//Step 4: Locate the cog button at the top of the form and select, Should have the ability to change display columns using the cog
			.addPersonalizedListColumn()

			//Step 5: Change filters for view using the funnel button, Should be able to change filters  
			.isFilterEditable();
			
			//Step 6: Re select Open Unassigned link Filters should reset when Open link is reselected 
			home.clickOpenUnAssignedWithoutReport().
			verifyFilterTextAterReset(filter)
			
			//Step 7: Select a row and go to the ‘Actions on selected row’ drop down box. You should not be able to perform delete actions
			.isDeleteIncidentUsingActionsOnSelectedRows();

			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_Stry000000_Tc59")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc59");
		return arrayObject;
	}
}
