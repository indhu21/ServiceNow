package com.punchit.scripts.gileadim_V2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_Tc11 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_Tc11",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String configItem, String repCust, 
			String asgGroup, String desc, String asgTo, String asgTo2, String CustomerComms, 
			String CustomerComms2, String configItem1, String repCust1, 
			String asgGroup1, String desc1, String aUser) {

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

			//			// Step 8: Enter all Mandatory fields
			//			incident.populateMandatoryFields(configItem, repCust, asgGroup, desc)
			//
			//			.submitIncident();
			//
			//			// Step 15B: Click Open and search for the incident
			//			home.clickOpen().searchIncident(incNumber).clickFirstIncident();
			//
			//			// 
			//			incident.verifyCustomerWatchlist()
			//
			//			//Customer Commsbox should be in Red 
			//			.verifyColorOfWorkNotes().verifyColorOfCustomerComms()


			incident.createIncidentAndOpen(configItem, repCust, asgGroup, desc, incNumber)

			//			.clickCustomerWatchlist()

			.verifyColorOfCustomerCommsandClick()

			//Type in Active User with Role
			//			.enterCustomerWatchlist(asgTo)
			.enterCustomerWatchlistForInActiveUser(asgTo2)
			//Type Non Active user
			//			.enterCustomerWatchlistForFailure(asgTo2)

			.enterCustomerWatchlistForActiveUser(asgTo)

			//		.lockCustomerWatchlist() // Click this button   to lock down your selected users

			.enterCustomerWatchlistMyself(regUser) // Click on   next to Customer Watch list to add yourself

			//Click unlock button   
			//			.unlockCustomerWatchlist()

			//click on your name. Click the X to remove users from the list
			//			.selectUserAndRemoveFromCustomerWatchlist();
			.selectRemoveAndLockUserFromCustomerWatchlist(asgTo);

			// select the   button to save
			//.lockCustomerWatchlist();
			
			snW.clickById("Save_Id");
	/*		
			home.clickCreateNewforFailure();

			String IncNum = 
					incident.getIncidentNumber();

			// Step 2: click on create new

			incident.createIncidentWithAssignedToAndOpenIncident(configItem1, repCust1, asgGroup1, desc1, IncNum, aUser)
			
*/			
			
			//					.clickFirstIncident()
			//					.isCustomerCommsAvailable()
			incident.verifyCusscmmsAvailable()
			//					.enterCustomerComms(CustomerComms)
			//					.clickSave()
			.enterCustomerCommsAndSave(CustomerComms)
			.getLatestCustomerComms(CustomerComms)
			//					.clickActivityLog()
			.getBackgroundofActivityCustomerComms(CustomerComms)
			//					.getActivityText(CustomerComms)
			//					.clickNotes()
			.enterCustomerCommsAndSave(CustomerComms2)
			//					.clickSave()
			.getLatestCustomerComms(CustomerComms2)
			//					.clickActivityLog()
			.getBackgroundofActivityCustomerComms(CustomerComms2);
			//					.getActivityText(CustomerComms2);




			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_Tc11")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_Tc11");
		return arrayObject;
	}
}
