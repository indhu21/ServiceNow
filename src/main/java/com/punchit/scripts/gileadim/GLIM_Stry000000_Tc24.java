package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_Stry000000_Tc24 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc24",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String CustomerComms, 
								String CustomerComms2, String configItem, String repCust, 
								String asgGroup, String desc, String aUser) {

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
			
			IncidentPage incident =
					home.clickCreateNewforFailure();
			
			String IncNum = 
					incident.getIncidentNumber();

			// Step 2: click on create new
			
			incident.createIncidentWithAssignedToAndOpenIncident(configItem, repCust, asgGroup, desc, IncNum, aUser)
//					.clickFirstIncident()
//					.isCustomerCommsAvailable()
					.verifyCusscmmsAvailable()
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


	@DataProvider(name = "GLIM_Stry000000_Tc24")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc24");
		return arrayObject;
	}
}
