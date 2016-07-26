package com.punchit.scripts.gileadven;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.AlertPage;
import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;




public class Ven_GLOD_Stry0010409_Tc01 extends SuiteMethods {

	
	@Test(dataProvider="Ven_GLOD_Stry0010409_Tc01",groups="OpsDirector")
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
	@DataProvider(name="Ven_GLOD_Stry0010409_Tc01")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("Ven_GLOD_Stry0010409_Tc01");
		return arrayObject;
	}
}
