package com.punchit.scripts.gileadod;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0010764_Tc01 extends SuiteMethods {

	@Test(dataProvider = "GLOD_Stry0010764_Tc01")
	public void testName(String regUser, String regPwd, String usrUser, String usrPwd){ 

		try {

			snW.launchApp(browserName, true);

			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
			AlertsListPage	list = 
					home.clickOpsAlertConsole();
//						.verifyIsRecordDisplays("Payments Plus Digital Response Times");
			
			String alertId_Optr = 
					list.getAlertId();
					list.clickFirstAlertId(alertId_Optr)
						.isDeleteButtonExists(regUser);
					home.clickLogout();
			
	/*					new LoginPage()
						.loginAs(usrUser, regPwd)
						.clickOpsAlertConsole()
						.verifyIsRecordDisplays("Payments Plus Digital Response Times");
			
			String alertId_Usr =
					list.getAlertId();
					list.clickFirstAlertId(alertId_Usr)
						.isDeleteButtonExists(usrUser);
					home.clickLogout();*/
		
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLOD_Stry0010764_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("GLOD_Stry0010764_Tc01");
		return arrayObject;
	}



}