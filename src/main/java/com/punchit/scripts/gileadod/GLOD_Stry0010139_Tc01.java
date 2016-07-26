package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertsProfilesListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class GLOD_Stry0010139_Tc01 extends SuiteMethods {


	@Test(dataProvider = "GLOD_Stry0010139_Tc01")
	public void testName(	String regUser, String regPwd, String filterType, String filterCondition,
							String filterValue, String scenario1, String state1, String severity1,
							String scenario2, String state2, String severity2, String closurecode) {

		try {

			snW.launchApp(browserName, true);

			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
				AlertsProfilesListPage list = 
						home.clickAlertProfiles()
							.addNewFilterinAlertUsingSelect(filterType, filterCondition, filterValue)
							.clickFirstProfile()
							.verifyAutoCloseValue()
							.clickUpdateButton();
							
						home.playScenarios(scenario1);
						home.clickAlerts()
							.addFirstFilter("Alert Profile", filterCondition, filterValue)
							.clickFirstAlert()
							.verifyAlertState(state1)
							.verifyAlertSeverity(severity1);

						home.playScenarios(scenario2);
						home.clickAlerts()
							.addFirstFilter("Alert Profile", filterCondition, filterValue)
							.clickFirstAlert()
							.verifyAlertState(state2)
							.verifyAlertSeverity(severity2)
							.verifyAlertClosureCode(closurecode);
						
						status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLOD_Stry0010139_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("GLOD_Stry0010139_Tc01");
		return arrayObject;
	}



}