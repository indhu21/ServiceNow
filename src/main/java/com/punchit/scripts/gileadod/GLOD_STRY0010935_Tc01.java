package com.punchit.scripts.gileadod;

import java.io.IOException;

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


public class GLOD_STRY0010935_Tc01 extends SuiteMethods {

	@Test(dataProvider = "GLOD_Stry0010935_Tc01", groups="OpsDirector")
	public void testName(String regUser, String regPwd) {

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Log in to application
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
				home.clickAdminApplicationProperties()
					.selectNewRecurrencesSeverity()	
					.verifysevRecValue()
					.selectWorstSeverityEncountered()
					.verifyWorstSevRecValue();
					
				home.clickLogout();
			status = "PASS";			

		} finally {
			
			// close the browser
			snW.quitBrowser();
			
		}

	}

	@DataProvider(name = "GLOD_Stry0010935_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("GLOD_Stry0010935_Tc01");
		return arrayObject;
	}

}