package com.punchit.scripts.gileadven;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_Stry0011034_Tc01 extends SuiteMethods {

	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "Ven_GLOD_Stry0011034_Tc01")
	public void testName(String regUser, String regPwd) throws InterruptedException {

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

			// Step 2: Open Alert Console under user consoles
			if(snW.selectMenu("Ops_Consoles", "Alert_Console"))
				Reporter.reportStep("Step 2: The Alert Console under OpsConsole - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Alert Console under OpsConsole - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");

			if(snW.getTextByXpath("ALERT_AlertId_Xpath").equals("No records to display")){
				status = "Insufficient Data";
				Reporter.reportStep("Step 3: There is no records to display for new Alerts","FAILURE");
			}

			//Step 3: Click on Alert to open an Alert the Alert Record 
			String alertId = snW.getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
			
			if(snW.clickLink(alertId, false))
				Reporter.reportStep("Step 3: Click on first the alert: "+ alertId +" is  clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 3: Click on first the alert: "+ alertId +" could not be clicked","FAILURE");

			String[] expectedValues = {"Monitoring System"};
			//snW.Wait(5000);

			if(!snW.clickByXpath("ALERT_RecrList_Xpath"))
				Reporter.reportStep("Step 4: List under to Alert Recurrence Section could not be clicked","FAILURE");
			
			Thread.sleep(4000);
			snW.getDriver().switchTo().activeElement();
			
			// The expected value validation against the list of the items
			if(snW.verifyListContents("ALERT_SlushSelected_Xpath",expectedValues) || snW.verifyListContents("ALERT_SlushAvailable_Xpath",expectedValues))
				Reporter.reportStep("Step 4: Monitring system is  available in the Alert Recurrence ","SUCCESS");
			else
				Reporter.reportStep("Step 4: Monitring system could not be found  in the Alert Recurrence","FAILURE");
			
			// Click Ok
			if(!snW.clickById("Ok_Id"))
				Reporter.reportStep("Step 4: Ok button could not be clicked","FAILURE");

						
			//Step 4: Validate that “Monitoring System”  field is listed in Alert Recurrence tab
			/*if(snW.isExistByXpath("ALERT_MoniSystem_Xpath"))
				Reporter.reportStep("Step 4: Monitring system could be availabe","SUCCESS");
			else
				Reporter.reportStep("Step 4: Monitring system could not be availabe","FAILURE");*/
												
			// go out of the frame
			snW.switchToDefault();

			// Log out
			if(snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("Step 5: The Log out is clicked successfully.","SUCCESS");
			else
				Reporter.reportStep("Step 5: The logout Failed", "FAILURE");		
						
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "Ven_GLOD_Stry0011034_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("Ven_GLOD_Stry0011034_Tc01");
		return arrayObject;
	}

	

}
