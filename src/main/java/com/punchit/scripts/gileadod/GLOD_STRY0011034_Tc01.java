package com.punchit.scripts.gileadod;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertPage;
import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0011034_Tc01 extends SuiteMethods {

	@Test(dataProvider = "GLOD_Stry0011034_Tc01")
	public void testName(String regUser, String regPwd) {

		try {
				
			snW.launchApp(browserName, true);

			MenuPage home =
					new LoginPage().
					loginAs(regUser, regPwd);
				home.clickUserAlertConsole()
//					.verifyIsRecordDisplays("Sumerian Capacity Alerts")
					.verifyData()
					.clickFirstAlert()
					.verifyMonitoringSystem();
				home.clickLogout();
					
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLOD_Stry0011034_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("GLOD_Stry0011034_Tc01");
		return arrayObject;
	}

	

}