package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0010144_TC01 extends SuiteMethods {
	

		@Test(dataProvider = "GLOD_STRY0010144_TC01",groups="OpsDirector")
		public void appProperties(String regUser, String regPwd, String value) {
			
			
			try {
				snW.launchApp(browserName, true);

				MenuPage home =
						new LoginPage()
						.loginAs(regUser, regPwd);
					home.clickAdminApplicationProperties()
						.enterAndSaveAlertCloseData(value)
						.enterAndSaveEmptyValueAlertCloseData()
						.verifyDefaultValueAlertCloseData();
					home.clickLogout();
				status = "PASS";
			
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


		@DataProvider(name = "GLOD_STRY0010144_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010144_TC01");
			return arrayObject;
		}
}