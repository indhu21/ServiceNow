package com.punchit.scripts.GileadimV2;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM13 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_IM13",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd, String configItem,
								String repCust, String asgGroup, String desc,
								String asgTo, String datetime, String reason,
								String holdType) throws ParseException {

		// Pre-requisities
		try {

			String date=increamentOneDay();
			
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
						.enterOnHoldUntil(date)
						.selectHoldType(holdType)
						.enterOnHoldReason(reason)
						.clickSave()
						.enterOnHoldUntil(datetime)
						.verifyHoldTypeError()
						.IsOnHoldReasonManAndAllowAlphaNum(reason)
						.enterOnHoldUntil(date)
						.clickSave()
						.verifystatestatus("On Hold");
					home.clickLogout();
						
		
					status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLIM_V2_Stry000000_IM13")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM13");
		return arrayObject;
	}





}



