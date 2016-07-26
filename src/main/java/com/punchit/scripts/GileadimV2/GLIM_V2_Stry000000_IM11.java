package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM11 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_IM11",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String configItem, String repCust, 
								String asgGroup, String desc, String asgTo, String asgTo2, 
								String email, String assTo3, String workNotes, String workNotes2,
								String configItem1, String repCust1, String asgGroup1, 
								String desc1, String aUser) {

			
		
		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);

			// Step 2: click on create new
			IncidentPage incident = home.clickCreateNewforFailure(); 

			// Take a note of the INC number.
			String incNumber = incident.getIncidentNumber();
			System.out.println(incNumber);

			incident.createIncidentAndOpen(configItem, repCust, asgGroup, desc, incNumber)
				.verifyColorAndClickWorkNotes()
				.enterEditWorkNotesListForInActiveUser(asgTo)
				.enterEditWorkNotesListForUserWithNoRole(assTo3)
				.enterEditWorkNotesListForActiveUser(asgTo2)
				.addMetoNotes(regUser)
				.selectUserAndRemoveFromNotes(asgTo2)	
				.enterNonGileademailInWorkNotes(email);
			snW.clickById("Save_Id");
			
			incident.verifyWorkNotesAvailable()
				.enterWorkNotesAndSave(workNotes)
				.getLatestWorkNotes(workNotes)
				.getBackgroundofActivityWorkNotes(workNotes)
				.enterWorkNotesAndSave(workNotes2)
				.getLatestWorkNotes(workNotes2)
				.getBackgroundofActivityWorkNotes(workNotes2);
					
			
				status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM11")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM11");
		return arrayObject;
	}
}
