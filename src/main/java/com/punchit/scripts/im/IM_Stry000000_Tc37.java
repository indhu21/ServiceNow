package com.punchit.scripts.im;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class IM_Stry000000_Tc37 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc37",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String causingCI ) {

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);

			// Step 2: click on WIP		
			home.clickWIP()
			.clickFirstIncident()
			.clickResolutionInformation()
			.enterAndChooseCausingCI(causingCI)
			.clickCausingCIComponentSpyGlass()
			.verifyCIComponentStorageLookUpValues();
			
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc37")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc37");
		return arrayObject;
	}
}
