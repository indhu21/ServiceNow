package com.punchit.scripts.gileadodNew;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertsListPage;
import pages.CorrelationProfilesPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0011149_Tc01 extends SuiteMethods {
	@Test(dataProvider="GLOD_STRY0011149_Tc01",groups="OpsDirector")

	public void assignAlert(String regUser, String regPwd, String option) {
		try {

			snW.launchApp(browserName, true);

			new LoginPage().loginAs(regUser, regPwd)
			.clickCorrelatedProfile()
			.checkImpactToServiceMandatory()
			.checkOptionInImpactToServiceMandatory(option)
			.clickLogout();

			status = "PASS";

		} finally{

			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name="GLOD_STRY0011149_Tc01")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0011149_Tc01");
		return arrayObject;
	}	
}
