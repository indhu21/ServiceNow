package com.punchit.scripts.im;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class IM_Stry000000_Tc63 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc63",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: As an Incident user sign into backend and navigate Incident
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);
			
			//Step 2: Find and select Overview 
			home.expandIncidentMenu().clickIncidentsMap()
			
			//Step 3: Find and select Critical Incidents Map
		
			.isMapPresent();
			
			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc63")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc63");
		return arrayObject;
	}
}
