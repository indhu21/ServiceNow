package com.punchit.scripts.gileadod;

import java.io.IOException;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AlertPage;
import pages.CIScopePage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class GLOD_AlertGenerator extends SuiteMethods {

	@Test(dataProvider = "AlertGenerator",groups="OpsDirector")
	public void createCIScope(	String regUser, String regPwd){


		try {

			launchApp(browserName, true);

			MenuPage home =
					new LoginPage()
					.loginAs(regUser, regPwd);
				home.clickIncomingAlerts()
					.clickReProcessIncomingAlerts()
					.clickOpsAlertConsole()
					.verifyAlerts();
					home.clickLogout();
		
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "AlertGenerator")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("AlertGenerator");
		return arrayObject;
	}
}
