package com.punchit.scripts.gileadim1;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_Stry000000_Tc42 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc42",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd, String configItem,
								String repCust, String asgGroup, String desc,
								String asgTo, String datetime, String reason) {

		// Pre-requisities
		try {

			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);	


//			IncidentPage incident =
					home.clickCreateNew()
						.populateMandatoryFields(configItem, repCust, asgGroup, desc)
						.enterMyselfSave(asgTo)
						.clickOnHold()
						.clickHoldInformationTab()
						.verifyHoldTypeOptions()
						.enterOnHoldUntil(datetime)
						.verifyHoldTypeError()
						.IsOnHoldReasonManAndAllowAlphaNum(reason);
						
		
					status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLIM_Stry000000_Tc42")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc42");
		return arrayObject;
	}





}



