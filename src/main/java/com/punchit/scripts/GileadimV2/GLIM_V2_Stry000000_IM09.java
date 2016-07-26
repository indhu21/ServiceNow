package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.IncidentPage;
import pages.LoginPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.SPARCPortalPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;

public class GLIM_V2_Stry000000_IM09 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_IM09",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String desc, String desc1,
								String number,String issue, String ableToWork,
								String regUser1,String regPwd1) {

		// Pre-requisities

		try {

			snW.launchApp(browserName, true);

			SPARCPortalPage home = 
					new LoginSparcLCPage()
					.loginAsSparcportal1(regUser, regPwd);
					
				
			IncidentPage incident=
				home.clickSomethingIsBroken()
					.enterValueInShortDescForPositive(desc)
					.enterValueInShortDescForNegative(desc1)
					.createIncident(number, issue, ableToWork);
			String incNum =
					incident.getIncidentNumber();
				
				new MenuPage(driver)
					   	.clickLogout();
				
			MenuPage menu = 
					new LoginPage()
					.loginAs(regUser1, regPwd1);
				
				menu.searchIncidentFromMenu(incNum)
					.verifyShortDescription(desc1);

					status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM09")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM09");
		return arrayObject;
	}
}
