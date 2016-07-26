package com.punchit.scripts.lc;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginSparcLCPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class LC_CAR2_1_Stry000000_Tc02_1 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_CAR2_1_Stry000000_Tc02_1",groups="IncidentManagement")
	public void createIncident(	String systemManager, String regPwd,String name, String CIOwnerGroup
			){

		// Pre-requisities
		try {

			String appName = name+snW.getCurrentTime();

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = 
					new LoginSparcLCPage()
					.loginAs(systemManager, regPwd);	

			// Step 2: Verify the Menus
			home.expandCMDBMenu()
				.verifyExistanceOfCMDBOptions()
				.clickSystemApplicationsAndVerifyNewButtonExists()
				.clickSysAppNewButton()
				.verifyAllMandatoryFields()
				.enterAllMandatoryFields(appName, CIOwnerGroup, systemManager)
				.clickSubmit()
				.searchandClickFirstnameforSyApp(appName)
				.verifyOperationalstatus("New");
			home.clickSparcLCPageLogout();
			
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_CAR2_1_Stry000000_Tc02_1")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_CAR2_1_Stry000000_Tc02_1");
		return arrayObject;
	}
}
