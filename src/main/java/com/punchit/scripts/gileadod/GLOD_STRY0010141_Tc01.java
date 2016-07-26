package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

// for using ATU reporting -- added the listeners

public class GLOD_STRY0010141_Tc01 extends SuiteMethods {

	@Test(dataProvider = "GLOD_Stry0010141_Tc01")
	public void testName(String regUser, String regPwd) {

		try {
			
			snW.launchApp(browserName, true);
			
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);	
				home.verifyOpsConsolesMenu()
					.clickOpsAlertConsole()
					.clickNewAlert()
					.addFirstFilterEnter("Child Count", "greater than", "0")
					.verifyChildCount();
				
				home.clickOpsAlertConsole()
					.clickNewAlert()
					.addFirstFilterEnter("Child Count", "is", "0")
					.verifyChildCount();
				


			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLOD_Stry0010141_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("GLOD_Stry0010141_Tc01");
		return arrayObject;
	}



}