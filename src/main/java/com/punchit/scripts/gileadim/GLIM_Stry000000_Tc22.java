package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_Stry000000_Tc22 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc22",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String workNotes, 
								String workNotes2, String configItem, String repCust, String asgGroup, 
								String desc, String aUser) {

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);

			// Step 2: click on create new
				IncidentPage incident =
						home.clickCreateNewforFailure();
				
				String IncNum = 
						incident.getIncidentNumber();
				
			incident.createIncidentWithAssignedToAndOpenIncident(configItem, repCust, asgGroup, desc, IncNum, aUser)
//					.isWorkNotesAvailable()
//					.verifyColorOfWorkNotes()
//					.verifyColorOfCustomerComms()
					.verifyWorkNotesAvailable()
//					.enterWorkNotes(workNotes)
//					.clickSave()
					.enterWorkNotesAndSave(workNotes)
					.getLatestWorkNotes(workNotes)
//					.clickActivityLog()
					.getBackgroundofActivityWorkNotes(workNotes)
//					.getActivityText(workNotes)			
//					.clickNotes()
					.enterWorkNotesAndSave(workNotes2)
//					.clickSave()
					.getLatestWorkNotes(workNotes2)
//					.clickActivityLog()
					.getBackgroundofActivityWorkNotes(workNotes2);
//					.getActivityText(workNotes2);

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_Stry000000_Tc22")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc22");
		return arrayObject;
	}
}

