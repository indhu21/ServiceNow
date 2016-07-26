package com.punchit.scripts.gileadven;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_STRY0010939_Tc01 extends SuiteMethods{
	// Create Instance
		ServiceNowWrappers snW;

		@Test(dataProvider = "Ven_GLOD_STRY0010939_Tc01",groups="OpsDirector")
		public void testName(String regUser, String regPwd) {
			
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
				if(snW.selectMenu("Ops_Director","Ops_Consoles", "My_Alert_Console"))
					Reporter.reportStep("Step 2: The 'My Alert Console' under OpsConsole - menu selected successfully","SUCCESS");
				else
					Reporter.reportStep("Step 2: The 'My Alert Console' under OpsConsole - menu could not be selected","FAILURE");

				// Switch to the main frame
				snW.switchToFrame("Frame_Main");

				snW.Wait(2000);
				// Step 3: Click on settings to display
				if(!snW.clickByXpath("ALERT_SettingsIcon_Xpath"))	
					//Reporter.reportStep("Step 5: The settings in Alert section has been clicked successfully","SUCCESS");
				//else
					Reporter.reportStep("Step 3: The settings in Alert section could not be clicked","FAILURE");

				// Step 4:	Validate that the fields of Related Tasks – Related Task and State are available in the list
				String[] expectedValues = {"Related Task","State"};
				snW.Wait(5000);

				// The expected value validation against the list of the items
				if(snW.verifyListContents("ALERT_SlushSelected_Xpath",expectedValues) || snW.verifyListContents("ALERT_SlushAvailable_Xpath",expectedValues))
					Reporter.reportStep("Step 3: Settings in Alert Section is clicked and the fields : Related Task, State are found in the available list.", "SUCCESS");
				else
					Reporter.reportStep("Step 3: The fields :Related Task, Related Task.State are not found in the available list.", "FAILURE");

				// Click Ok
				if(!snW.clickById("Ok_Id"))
					Reporter.reportStep("Step 4: Ok button could not be clicked","FAILURE");

				snW.Wait(5000);

				// Validate that Related Task and Related Task, State fields are present in the view
				String[] expectedTableValues = {"Related Task","State"};

				snW.Wait(2000);
				if(snW.verifyTableHeaders("ALERT_MyAlertsTableHeader_Xpath",expectedTableValues))
					Reporter.reportStep("Step 4: The fields :Related Task, State are found in the table columns.", "SUCCESS");
				else
					Reporter.reportStep("Step 4: The fields :Related Task, State are not found in the table columns.", "FAILURE");

				// go out of the frame
				snW.switchToDefault();

				// Log out
				if(!snW.clickByXpath("Logout_Xpath"))
					Reporter.reportStep("The logout Failed", "FAILURE");

				status = "PASS";			

			} finally {

				// close the browser
				snW.quitBrowser();

			}

		}

		@DataProvider(name = "Ven_GLOD_STRY0010939_Tc01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider
					.getSheet("Ven_GLOD_STRY0010939_Tc01");
			return arrayObject;
		}



}
