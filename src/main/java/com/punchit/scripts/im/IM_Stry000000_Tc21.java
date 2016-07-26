package com.punchit.scripts.im;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class IM_Stry000000_Tc21 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc21",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd, String configItem, String repCust, 
			String asgGroup, String desc, String asgTo, String asgTo2) {

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);

			// Step 2: click on create new
			IncidentPage incident = home.clickCreateNew();

			// Take a note of the INC number.
			String incNumber = incident.getIncidentNumber();
			System.out.println(incNumber);

			// Step 8: Enter all Mandatory fields
			incident.populateMandatoryFields(configItem, repCust, asgGroup, desc)

			.submitIncident();

			// Step 15B: Click Open and search for the incident
			home.clickOpen().searchIncident(incNumber).clickFirstIncident();

			// Step 3: Select Notes Tab
			incident.verifyEditWorkNotesList()

			// Step 3: Work notes box should be in Green
			.verifyColorOfWorkNotes().verifyColorOfCustomerComms()

			//Step 3: Click on EditWorkNotesList next to work notes list 
			.clickEditWorkNotesList()		

			// Step 3:Click on the spyglass button Try to add
			.enterEditWorkNotesList(asgTo)

			.lockNotes()// Step 5: Click lock button  to lock down your selected users

			.addMetoNotes(regUser)// Step 4: Click on  Add me icon next to work notes list to add yourself

			// Step 6: Click Unlock button 
			.unlockNotes()

			// Step 6: Click the X to remove users from the list
			.selectUserAndRemoveFromNotes(regUser)	

			.lockNotes() // Step 6: Click lock button again	


			.isExistsAddUser() // Step 7: Verify add me button reappear

			.unlockNotes()

			// Step 7: Try to add(Non active user)			
			.enterEditWorkNotesListForFailure(asgTo2);

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc21")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc21");
		return arrayObject;
	}
}
