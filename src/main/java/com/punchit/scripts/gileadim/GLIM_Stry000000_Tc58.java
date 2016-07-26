package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_Stry000000_Tc58 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc58",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Go to backend and navigate Incident
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);

			//Step 1A: Select Open link
			String[] filter={"SPARC Active = true"};
			home.expandIncidentMenu().
			clickOpen()
			.verifyFilterText(filter)
			
			//Step 1B: Only Incident that are Active and do not have a state of resolve are shown
			.verifyNonResolvedforsecondFilter(regUser.toUpperCase());
		home.clickOpenWithoutReport()	
			//Step 1C: There should not be a New button available at the top of the view
			.isNewButtonExists()
			
			//Step 2: Right hand click an Incident	You should not be able to perform the Assign to me function and a message will appear advising you this 		
//			.rightClickonFirstAlert()
//			.ClickAssignToMe()
			.rightClickAndAssignToMe()
			.verifyAssignedToError(regUser.toUpperCase())
			
			//Step 3: Double click on any field and try to edit	You should not be able to edit the text in any field from this view
			.isFieldEditable()
			
			//Step 4: Locate the cog button  at the top of the form and select, Should have the ability to change display columns using the cog
			.addPersonalizedListColumn()
			
			//Step 5: Select a row and go to the ‘Actions on selected row’ drop down box	You should not be able to perform delete actions  
			.isDeleteIncidentUsingActionsOnSelectedRows()

			//Step 6: Change filters for view using the funnel button Should be able to change filters 
			.isFilterEditable();

			//Step 7: Re select Open link	Filters should reset when Open link is reselected
			home.clickOpenWithoutReport()
			.verifyFilterTextAterReset(filter);

			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_Stry000000_Tc58")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc58");
		return arrayObject;
	}
}
