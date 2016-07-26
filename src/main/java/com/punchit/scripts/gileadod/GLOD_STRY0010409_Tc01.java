package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLOD_STRY0010409_Tc01 extends SuiteMethods {

	
	@Test(dataProvider="GLOD_Stry0010409_Tc01",groups="OpsDirector")
	public void implementSLA(String regUser, String regPwd) {

		try {
			
			snW.launchApp(browserName, true);

			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
				home.clickOPSAlertDashBoard()
					.verifyIsSLAChartDisplayed()
					.clickAlertsbreachedSLADashboard()
					.clickFirstLink()
					.moveHasBreached()
					.verifycolumnValue("Has breached", "true");
					home.clickLogout();			
			
			status = "PASS";

		} finally{

			// close the browser
			snW.quitBrowser();	

		}

	}
	@DataProvider(name="GLOD_Stry0010409_Tc01")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_Stry0010409_Tc01");
		return arrayObject;
	}
}
