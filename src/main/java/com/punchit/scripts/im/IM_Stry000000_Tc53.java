package com.punchit.scripts.im;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.CreateIncidentPage;
import pages.IncidentPage;
import pages.IncidentsListPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.SPARCPortalPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class IM_Stry000000_Tc53 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc53",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
			String regUser2,String regPwd2,
			String issue,String desc){

		// Pre-requisities

		try {

			createAndVerifyPriority(regUser, regPwd, regUser2, regPwd2, issue, desc, "Me",  "Yes", "4 - Low");
			createAndVerifyPriority(regUser, regPwd, regUser2, regPwd2, issue, desc, "My Group",  "Yes", "4 - Low");
			createAndVerifyPriority(regUser, regPwd, regUser2, regPwd2, issue, desc, "My Department",  "Yes", "3 - Moderate");
			createAndVerifyPriority(regUser, regPwd, regUser2, regPwd2, issue, desc, "My Location",  "Yes", "3 - Moderate");
			createAndVerifyPriority(regUser, regPwd, regUser2, regPwd2, issue, desc, "Me",  "No", "3 - Moderate");
			createAndVerifyPriority(regUser, regPwd, regUser2, regPwd2, issue, desc, "My Group",  "No", "3 - Moderate");
			createAndVerifyPriority(regUser, regPwd, regUser2, regPwd2, issue, desc, "My Department",  "No", "2 - High");
			createAndVerifyPriority(regUser, regPwd, regUser2, regPwd2, issue, desc, "My Location",  "No", "2 - High");

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	private void createAndVerifyPriority(String regUser, String regPwd,
			String regUser2,String regPwd2,
			String issue,String desc,
			String number, String areYou, 
			String priority){
		// Step 0: Launch the application
		snW.launchApp(browserName, true);

		// Step 1: Login to the application as user 1
		LoginPage loginPage = new LoginPage(); 
		MenuPage home= loginPage.loginAs(regUser, regPwd);

		// Step 2A: Go to the Portal view
		SPARCPortalPage SPARCMenu = home.clickSPARCPortal();
		IncidentPage incPage = SPARCMenu.clickCreateIncident()
				.RaiseIncident(number, issue, areYou, desc);
		String incNumber1 = incPage.getIncidentNumber();

		// Verify the priority and save
		incPage.verifyPriorityFieldInSparc(priority)
		.clickSave();

		SPARCMenu.clickSPARCHomeMenu() 
		.clickLogout();

		snW.quitBrowser();			
		snW.launchApp(browserName, true);

		new LoginPage().loginAs(regUser2, regPwd2)
		.searchIncident(incNumber1)
		.clickSetPriority()
		.verifyPriorityField(priority);
//		.isFieldsAutomaticallyGenerated();

		// Logout			 
		home.clickLogout();

		snW.quitBrowser();			
	}


	@DataProvider(name = "IM_Stry000000_Tc53")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc53");
		return arrayObject;
	}
}
