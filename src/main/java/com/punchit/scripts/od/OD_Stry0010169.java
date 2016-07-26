package com.punchit.scripts.od;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;




import testng.NewSuiteMethods;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

// for using ATU reporting -- added the listeners

public class OD_Stry0010169 extends NewSuiteMethods {

	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "OD_Stry0010169")
	public void testName(String regUser, String regPwd, String Search, String Overrides, String NewSearch) {

		try {
			
			// Pre-requisities 
			snW = new ServiceNowWrappers(entityId);

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("Step 1: The login with username:"
						+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"
						+ regUser + " is not successful", "FAILURE");

			// Step 2: Expand OpsDirector/OpsConsole/under application navigator to select Alert Console
			if(snW.selectMenu("Configurations", "Alert_Profiles"))
				Reporter.reportStep("Step 2: The Alert Profiles under Configurations - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Alert Profiles under Configurations - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			
			// Step 3: Open an Alert  Profile by clicking on Number link
			snW.enterByXpathAndClick("ALERTPROFILE_Search_Xpath", Search+"_Fail");	
			snW.Wait(2000);

			String alertId = snW.getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
			if(snW.clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
				Reporter.reportStep("Step 3: Alert Profile is Opened by clicking on the first alert" , "SUCCESS");
			else
				Reporter.reportStep("Step 3: The Alert Profile could not be opened","FAILURE");
			


			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "OD_Stry0010169_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("OD_Stry0010169_Tc01");
		return arrayObject;
	}

	

}