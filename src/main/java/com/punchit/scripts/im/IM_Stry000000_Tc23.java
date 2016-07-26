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

public class IM_Stry000000_Tc23 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc23",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String configItem, String repCust, 
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

			// 
			incident.verifyCustomerWatchlist()

			//Customer Commsbox should be in Red 
			.verifyColorOfWorkNotes().verifyColorOfCustomerComms()

			//Click on   next to Customer Watch List
			.clickCustomerWatchlist()

			//Type in Active User with Role
			.enterCustomerWatchlist(asgTo)

			//Type Non Active user
			.enterCustomerWatchlistForFailure(asgTo2)
			
			.lockCustomerWatchlist() // Click this button   to lock down your selected users

//			.addMetoCustomerWatchlist() // Click on   next to Customer Watch list to add yourself

			//Click unlock button   
			.unlockCustomerWatchlist()

			//click on your name. Click the X to remove users from the list
			.selectUserAndRemoveFromCustomerWatchlist()

			// select the   button to save
			.lockCustomerWatchlist();





			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc23")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc23");
		return arrayObject;
	}
}
