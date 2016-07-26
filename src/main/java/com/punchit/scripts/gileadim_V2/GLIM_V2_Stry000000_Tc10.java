package com.punchit.scripts.gileadim_V2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_Tc10 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_Tc10",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd, String configItem, String repCust, 
			String asgGroup, String desc, String asgTo, String asgTo2, String email, String assTo3,
			String workNotes, String workNotes2, String configItem1, String repCust1, String asgGroup1, 
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

			// Step 8: Enter all Mandatory fields
//			incident.populateMandatoryFields(configItem, repCust, asgGroup, desc)
//
//			.submitIncident();
//
//			// Step 15B: Click Open and search for the incident
//			home.clickOpen().searchIncident(incNumber).clickFirstIncident();
//	
			incident.createIncidentAndOpen(configItem, repCust, asgGroup, desc, incNumber)
			// Step 3: Select Notes Tab
				
//				.verifyEditWorkNotesList()

			// Step 3: Work notes box should be in Green
//				.verifyColorOfWorkNotes()
//				.verifyColorOfCustomerComms()

			//Step 3: Click on EditWorkNotesList next to work notes list 
//				.clickEditWorkNotesList()		
				.verifyColorAndClickWorkNotes()
				
			// Step 3:Click on the spyglass button Try to add
//				.enterEditWorkNotesList(asgTo)
				.enterEditWorkNotesListForInActiveUser(asgTo)
//				.lockNotes()// Step 5: Click lock button  to lock down your selected users
				.enterEditWorkNotesListForUserWithNoRole(assTo3)
				
				.enterEditWorkNotesListForActiveUser(asgTo2)
				
				.addMetoNotes(regUser)// Step 4: Click on  Add me icon next to work notes list to add yourself

			// Step 6: Click Unlock button 
//				.unlockNotes()

			// Step 6: Click the X to remove users from the list
				.selectUserAndRemoveFromNotes(asgTo2)	

//				.lockNotes() // Step 6: Click lock button again	


//				.isExistsAddUser() // Step 7: Verify add me button reappear

//				.unlockNotes()

			// Step 7: Try to add(Non active user)			
//				.enterEditWorkNotesListForFailure(asgTo2);
				.enterNonGileademailInWorkNotes(email);
			
			snW.clickById("Save_Id");
			
		/*				
			home.clickCreateNewforFailure();
			
			String IncNum = 
					incident.getIncidentNumber();
			
		incident.createIncidentWithAssignedToAndOpenIncident(configItem1, repCust1, asgGroup1, desc1, IncNum, aUser)
		
		
		*/
//				.isWorkNotesAvailable()
//				.verifyColorOfWorkNotes()
//				.verifyColorOfCustomerComms()
			incident.verifyWorkNotesAvailable()
//				.enterWorkNotes(workNotes)
//				.clickSave()
				.enterWorkNotesAndSave(workNotes)
				.getLatestWorkNotes(workNotes)
//				.clickActivityLog()
				.getBackgroundofActivityWorkNotes(workNotes)
//				.getActivityText(workNotes)			
//				.clickNotes()
				.enterWorkNotesAndSave(workNotes2)
//				.clickSave()
				.getLatestWorkNotes(workNotes2)
//				.clickActivityLog()
				.getBackgroundofActivityWorkNotes(workNotes2);
//				.getActivityText(workNotes2);
					
			
				status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_Tc10")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_Tc10");
		return arrayObject;
	}
}
