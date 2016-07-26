package com.punchit.scripts.gileadod;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertsEnrichersListPage;
import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

// for using ATU reporting -- added the listeners

public class GLOD_STRY0010936_Tc01 extends SuiteMethods {


	@Test(dataProvider = "GLOD_Stry0010936_Tc01")
	public void testName(String devRegUser, String devRegPwd, String label, 
			String order, String decoratorType, String recurBehav,
			String operator, String modification, String oprRegUser,
			String oprRegPwd, String sceData) {

		try {

			snW.launchApp(browserName, true);
			
			String labelTm=label+snW.getCurrentTime();
			
			MenuPage home = 
					new LoginPage()
					.loginAs(devRegUser, devRegPwd);
				home.clickAdminAlertEnrichers()
					.clickEnrichersNewButton()
					.enterEnricherForm(labelTm, order, decoratorType, recurBehav, operator, modification)
					.clickSubmitButton();
				home.clickLogout();
					
					new LoginPage()
					.loginAs(oprRegUser, oprRegPwd)
					.playScenarios(sceData)
					.clickOpsAlertConsole()
					.clickFirstAlert()
					.compareReferenceData(modification);
					
					home.clickLogout();

					status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLOD_Stry0010936_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("GLOD_Stry0010936_Tc01");
		return arrayObject;
	}



}