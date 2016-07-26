package com.punchit.scripts.im;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.IncidentsListPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.MyProfilePage;
import pages.SPARCPortalPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class IM_Stry000000_Tc03C extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc03C",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);

			MyProfilePage MyProfile = home.selectMyProfile();

			// Step 2: Check that you are able to see the “Extension number” field  and field is unable to edit 
			MyProfile.verifyExtensionNumberField().verifyExtensionNumberFieldReadOnly().BacktoMainFrame();

			// Step 3A: Go to the Portal view
			SPARCPortalPage SPARCPortal  = home.clickSPARCPortal();

			// Step 3B: click on the “My Profile” button
			SPARCPortal.clickMyProfile();

			// Step 4: Check that you are able to see the “Extension number” field and field is unable to edit 
			SPARCPortal.verifyExtensionNumberField().verifyExtensionNumberFieldReadOnly();

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc03C")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc03C");
		return arrayObject;
	}
}
