package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.SPARCPortalPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_Stry000000_Tc03B extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc03B",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			SPARCPortalPage home = 
							new LoginSparcLCPage()
							.loginAsSparcportal1(regUser, regPwd);

			// Step 2A: Go to the Portal view
//			SPARCPortalPage SPARCPortal  = home.clickMyProfileofSPARCPortal();

			// Step 2B: click on the “My Profile” button
			home.clickMyProfile()
			
//			switchToFrame("Frame_Main");

			// Step 3: Check that you are able to see the “Extension number” field and field is unable to edit 
			
			.verifyExtensionNumberFieldExistsandReadOnly();
//			.verifyExtensionNumberField().verifyExtensionNumberFieldReadOnly();

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_Stry000000_Tc03B")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc03B");
		return arrayObject;
	}
}
