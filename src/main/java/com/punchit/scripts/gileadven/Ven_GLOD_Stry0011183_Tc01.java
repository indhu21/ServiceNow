package com.punchit.scripts.gileadven;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.AlertPage;
import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;



import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;


public class Ven_GLOD_Stry0011183_Tc01 extends SuiteMethods {

	@Test(dataProvider = "Ven_GLOD_Stry0011183_Tc01",groups="OpsDirector")
	public void testName(   String regUser, String regPwd, String refRate, String groupName, 
							String filterType, String filterCondition, String filterValue) {
		try {

			snW.launchApp(browserName, true);

			MenuPage home = 
						new LoginPage()
						.loginAs(regUser, regPwd);
					
					home.clickUserAlertConsole()
						.verifyRefreshCycle(refRate)
						.clickMyGroupAlert()					
						.verifyValCondition(groupName)
						.addNewFilterinAlertUsingSelect(filterType, filterCondition, filterValue)
						.verifycountOfSeverity(filterValue);
					home.clickLogout();

			status = "PASS";	

		} finally {

			// close the browser
			snW.quitBrowser();

		}

	}
	
	@DataProvider(name = "Ven_GLOD_Stry0011183_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("Ven_GLOD_Stry0011183_Tc01");
		return arrayObject;
	}

}
