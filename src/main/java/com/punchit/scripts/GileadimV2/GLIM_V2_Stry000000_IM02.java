package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM02 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_IM02",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String configItem, String repCust, 
								String asgGroup, String desc, String asgTo, String asgTo2, String CustomerComms, 
								String CustomerComms2, String configItem1, String repCust1, 
								String asgGroup1, String desc1, String aUser, String firstName,
								String lastName, String companyName, String email) {

		// Pre-requisities

		try {
			
				firstName=firstName+getCurrentTime();
				lastName=lastName+getCurrentTime();
				launchApp(browserName, true);
			
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);

			
			IncidentPage incident = 
					home.clickCreateNewforFailure();
			
			String incNumber = 
					incident.getIncidentNumber();
			
			System.out.println(incNumber);

			
			incident.createIncidentAndOpen(configItem, repCust, asgGroup, desc, incNumber)
					.verifyColorOfCustomerCommsandClick()
					.enterCustomerWatchlistForInActiveUser(asgTo2)
					.enterCustomerWatchlistForActiveUser(asgTo)
					.enterCustomerWatchlistMyself(regUser)
					.selectRemoveAndLockUserFromCustomerWatchlist(asgTo)
					.clickSave()
					.verifyCusscmmsAvailable()
					.enterCustomerCommsAndSave(CustomerComms)
					.getLatestCustomerComms(CustomerComms)
					.getBackgroundofActivityCustomerComms(CustomerComms)
					.enterCustomerCommsAndSave(CustomerComms2)
					.getLatestCustomerComms(CustomerComms2)
					.getBackgroundofActivityCustomerComms(CustomerComms2)
					.clickNotes()
					.clickCustomerWatchlist()
					.clickCusWatchSpyGlass()
					.clickNewButton1()
					.registerNewCustomer(firstName, lastName, companyName, email)
					.verifyEmail(firstName+" "+lastName+"(EXT)");
				
				
			
				status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM02")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM02");
		return arrayObject;
	}
}
