package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_Stry000000_Tc55 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc55",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd, String noroleUser, String filterValue, String menu){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd).enterFilter(filterValue);
			
			home.verifyExistanceOfIncidentMenus()
			.clickLogout()
			.loginSparcWithMyProfile(noroleUser, regPwd);			
			
			home.enterFilter(filterValue)
			.isIncidentMenuExists(menu);
//			.clickLogout();
			


			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_Stry000000_Tc55")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc55");
		return arrayObject;
	}
}
