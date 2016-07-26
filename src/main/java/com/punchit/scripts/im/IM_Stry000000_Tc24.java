package com.punchit.scripts.im;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.IncidentsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class IM_Stry000000_Tc24 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc24",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String CustomerComms,String CustomerComms2) {

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);

			// Step 2: click on create new
			home.clickOpen()
			.clickFirstIncident()
			.isCustomerCommsAvailable()
			.enterCustomerComms(CustomerComms)
			.clickSave()
			.getLatestCustomerComms(CustomerComms)
			.clickActivityLog()
			.getBackgroundofActivityCustomerComms(regUser)
			.getActivityText(CustomerComms)
			.clickNotes()
			.enterCustomerComms(CustomerComms2)
			.clickSave()
			.getLatestCustomerComms(CustomerComms2)
			.clickActivityLog()
			.getBackgroundofActivityCustomerComms(regUser)
			.getActivityText(CustomerComms2);


			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc24")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc24");
		return arrayObject;
	}
}
