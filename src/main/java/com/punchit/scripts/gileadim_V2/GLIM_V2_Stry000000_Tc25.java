package com.punchit.scripts.gileadim_V2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_Tc25 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_Tc25",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd, String UserName){

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

			
			switchToMenu();
			
			
			String[] filter1={"Work in Progress",UserName};

			//Step 1A: Select Work in Progress link, There should not be a New button available at the top of the view
			home.expandIncidentMenu()
			.clickWIP()
			.verifyFilterText(filter1)
			.verifyNonResolvedforthirdFilter(regUser.toUpperCase());
			home.clickWIPWithoutReport()
			.isNewButtonExists()

			//Step 2: Right hand click an Incident, You should not be able to perform the Assign to me function and a message should show to advise you this 		
			//				.rightClickonFirstAlert()
			//				.ClickAssignToMe()
			.rightClickAndAssignToMe()
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
			home.clickWIPWithoutReport().verifyFilterTextAterReset(filter1);
			
			switchToMenu();

			//Step 1A: Select Open link
			String[] filter3={"SPARC Active = true"};
			home.expandIncidentMenu().
			clickOpen()
			.verifyFilterText(filter3)

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
			.verifyFilterTextAterReset(filter3);
			
			switchToMenu();

			String[] filter4={"Assignment Group = (TESM_INC_GROUP, Reporting)", "Assigned To is empty"};
			//			String groupName="Punch Group1";
			home.expandIncidentMenu().
			clickOpenUnAssigned()
			//			.verifyNonResolvedforGroup(filter, regUser.toUpperCase(), groupName)
			.verifyNonResolvedforthirdFilter(regUser.toUpperCase());
			home.clickOpenUnAssignedWithoutReport()	
			.verifyFilterText(filter4)
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
			verifyFilterTextAterReset(filter4)

			//Step 7: Select a row and go to the ‘Actions on selected row’ drop down box. You should not be able to perform delete actions
			.isDeleteIncidentUsingActionsOnSelectedRows();



			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_Tc25")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_Tc25");
		return arrayObject;
	}
}
