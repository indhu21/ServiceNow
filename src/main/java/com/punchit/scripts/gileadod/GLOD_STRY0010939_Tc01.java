package com.punchit.scripts.gileadod;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.AlertsListPage;
import pages.LoginPage;
import testng.SuiteMethods;



import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;


public class GLOD_STRY0010939_Tc01 extends SuiteMethods {


	@Test(dataProvider = "GLOD_Stry0010939_Tc01",groups="OpsDirector")
	public void testName(String regUser, String regPwd) {
		
		try {

			snW.launchApp(browserName, true);

			LoginPage home = new LoginPage().
							loginAs(regUser, regPwd).
							clickMyAlertConsole().
							clickMyAlertslink().
							clickSettingIcon().
							verifyListContents().
							verifyTableHeaders().
							clickLogout();

			
			status = "PASS";			

		} finally {

			// close the browser
			snW.quitBrowser();

		}

	}

	@DataProvider(name = "GLOD_Stry0010939_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("GLOD_Stry0010939_Tc01");
		return arrayObject;
	}

}