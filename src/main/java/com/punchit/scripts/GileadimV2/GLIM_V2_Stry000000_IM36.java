package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM36 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_IM36",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd, String noroleUser, String filterValue, String menu){

		// Pre-requisities

		try {

				launchApp(browserName, true);

			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd)
					.enterFilter(filterValue);
			
			home.verifyExistanceOfIncidentMenus()
				.clickIncidentsMapandVerifyMapExists();
			home.clickLogout();
//			.changeUrlForIMFornaNavigation();
			
			
			new LoginPage()
			.loginSparcWithMyProfile(noroleUser, regPwd);			
			
			home.enterFilter(filterValue)
				.isIncidentMenuExists(menu)
				.clickLogout();
			


			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM36")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM36");
		return arrayObject;
	}
}
