package com.punchit.scripts.gileadod;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLOD_STRY0011296_Tc01 extends SuiteMethods {

		@Test(dataProvider = "GLOD_Stry0011296_Tc01",groups="IncidentManagement")
		public void createIncident(String regUser, String regPwd){

			// Pre-requisities
			try {

				// Step 0: Launch the application
				snW.launchApp(browserName, true);

				// Step 1: Login to the application
				MenuPage home = new LoginPage().loginAs(regUser, regPwd);	
						home.clickOpsAlertConsole()
							.isPresntAlertVolumebyApplication()
							.refreshPage()
							.clickOpsAlertConsole()
							.isPresntAlertVolumebyApplication()
							.clickLogout();


							
				status = "PASS";

			} finally {
				// close the browser
				snW.quitBrowser();
			}

		}

		@DataProvider(name = "GLOD_Stry0011296_Tc01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("GLOD_Stry0011296_Tc01");
			return arrayObject;
		}
}
