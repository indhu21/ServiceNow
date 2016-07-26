package com.punchit.scripts.gileadod;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;



import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

// for using ATU reporting -- added the listeners

public class GLOD_STRY0011027_Tc01 extends SuiteMethods {

	@Test(dataProvider = "GLOD_Stry0011027_Tc01")
	public void testName(String regUser, String regPwd, String grpName) {

		try {
			
			snW.launchApp(browserName, true);

			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
				AlertsListPage list = 
						home.clickUserAlertConsole()
							.verifyData();
				String alertId = 
						list.getAlertId();
					list.clickColumnValue("Alert Profile", alertId)
						.enterAndChooseAssGroup(grpName)
						.clickUpdate()
						.clickFirstAlertId(alertId)
						.verifyreadOnlyFields();
					home.clickLogout();
			
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLOD_Stry0011027_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("GLOD_Stry0011027_Tc01");
		return arrayObject;
	}



}