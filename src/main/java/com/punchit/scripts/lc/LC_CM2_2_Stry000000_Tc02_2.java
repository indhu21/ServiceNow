package com.punchit.scripts.lc;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class LC_CM2_2_Stry000000_Tc02_2 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_CM2_2_Stry000000_Tc02_2",groups="IncidentManagement")
	public void createIncident(String systemManager, String regPwd,String name, String appliesTo, String operationalStatus , String environment	){

		// Pre-requisities
		try {
			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = 
					new LoginSparcLCPage()
					.loginAs(systemManager, regPwd);	

			// Step 2: Verify the Menus
			home.expandCMDBMenu()
				.expandCMDBControlMenu()
				.clickCriticalAttributes()
				.clickFirstCriticalAttributelink()
				.verifyErrorMess();
			home.clickSparcLCPageLogout();
			
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_CM2_2_Stry000000_Tc02_2")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_CM2_2_Stry000000_Tc02_2");
		return arrayObject;
	}
}
