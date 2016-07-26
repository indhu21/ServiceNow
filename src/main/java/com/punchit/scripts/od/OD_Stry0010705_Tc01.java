package com.punchit.scripts.od;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;




import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class OD_Stry0010705_Tc01 extends SuiteMethods {

	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "OD_Stry0010705_Tc01",groups="OpsDirector")
	public void acknowledgingUser(String regUser, String regPwd) throws InterruptedException {

		try {
			// Pre-requisities 
			snW = new ServiceNowWrappers(entityId);

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Log in to application
			if(snW.login(regUser, regPwd))
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");

			// Step 2: In application navigator expand Ops Consoles and Alert Console
			snW.Wait(2000);
			if(snW.selectMenu("User_Consoles", "Alert_Console"))
				Reporter.reportStep("Step 2: The Alert Console under OpsConsole - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Alert Console under OpsConsole - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");

			if(snW.getTextByXpath("ALERT_AlertId_Xpath").equals("No records to display")){

				snW.switchToFrame("Frame_Nav");

				if(!snW.enterById("filter_Id", "Ops Director Testing"))
					Reporter.reportStep("Step 3: Alert Console could not be entered in filter box","FAILURE");

				if(!snW.selectMenu("Ops_Director_Testing", "Scenarios"))
					Reporter.reportStep("Step 3: The Scenarios under Ops Director Testing - menu could not be selected","FAILURE");

				snW.switchToFrame("Frame_Main");
				//Step 7: Run a Scenario from reference data
				if(!snW.clickLink("Payments Plus Digital Response Times", false))
					Reporter.reportStep("Step 3: Payments Plus Digital Response Times under Scenarios could not be clicked","FAILURE");
				snW.Wait(5000);
				if(!snW.clickByXpath("SCENARIOS_PlayScenarios_Xpath"))
					Reporter.reportStep("Step 3: The Play Scenarios could not be clicked","FAILURE");
				snW.Wait(5000);
				if(!snW.clickByXpath("SCENARIOS_Close_Xpath"))
					Reporter.reportStep("Step 3: The Close Button could not be clicked","FAILURE");

				snW.switchToFrame("Frame_Nav");

				if(!snW.enterById("filter_Id", "Alert Console"))
					Reporter.reportStep("Step 3: Alert Console could not be entered in filter box","FAILURE");

				if(!snW.clickById("filter_Clear_Id"))
					Reporter.reportStep("Step 3: Filter Box could not be cleared","FAILURE");

				if(!snW.selectMenu("Ops_Director","Ops_Consoles", "Alert_Console"))
					Reporter.reportStep("Step 3: The Alert Console under OpsConsole - menu could not be selected","FAILURE");

				snW.switchToFrame("Frame_Main");

			}


			//Click My Group Alerts
			if(!snW.clickByXpath("ALERT_MyGroupAlertHeader_Xpath"))
				Reporter.reportStep("Step 3: My Group Alerts in the alert console", "FAILURE");

			snW.Wait(5000);

			// Click filter
			if(!snW.clickById("ALERT_FunnelIcon_Id"))
				Reporter.reportStep("Step 3: The funnel icon could not be clicked","FAILURE");

			snW.Wait(5000);

			// Remove All filters
			if(!snW.deleteAllFilters())
				Reporter.reportStep("Step 3: The filter could not be removed","FAILURE");

			snW.Wait(2000);

			// Add new filter with status as NEW
			snW.addNewFilterUsingSelect("State", "is", "New");

			// Click Run
			if(!snW.clickByXpath("ALERT_RunFilter_Xpath"))
				Reporter.reportStep("Step 3: Run could not be clicked", "FAILURE");

			snW.Wait(2000);
			// Select an alert with “State” New and attempt to acknowledge
			String alertId = snW.getAttributeByXpath("ALERT_AlertId_Xpath", "data-title-value");
		
			if(snW.clickByXpath("First_Searched_Record_Xpath"))
				Reporter.reportStep("Step 3: Alert Profile is selected successfully","SUCCESS");
			else
				
			{
				status="INSUFFICIENT DATA";
				Reporter.reportStep("Step 3: Alert profile sould not be selected", "FAILURE");
			}
			
			if(snW.IsElementPresentByXpath("Ackhowledge_button_Xpath"))
				Reporter.reportStep("Step 4: Acknowledge button is present, hence failed","FAILURE");
			else                              
				Reporter.reportStep("Step 4: Acknowledge button is not present as expected","SUCCESS");
			// Return to Alert Console and Select one or more new alerts by checking in the box to the left of the alert.
			//snW.clickByXpath("ALERT_AllAlertsSelect_Xpath"); //just to keep the mouse out of the right click

			snW.clickByXpath("BackButton_Xpath");
			snW.Wait(2000);
			snW.switchToFrame("Frame_Main");
			
			if(snW.clickByXpath("ALERT_AllAlertsChkBox_Xpath"))
				Reporter.reportStep("Step 5: All alerts checkbox is checked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 5: All alerts checkbox could not be checked","FAILURE");
			
			snW.clickByXpath("ALERT_AllAlertsSelect_Xpath");	
			Thread.sleep(3000);
			if(!snW.selectByVisibleTextByXpath("ALERT_AllAlertsSelect_Xpath","Acknowledgment"))
				Reporter.reportStep("Step 6: The All alerts do not have Acknowledgment options as expected","SUCCESS");
			else
			    Reporter.reportStep("Step 6: The All alerts has Acknowledgment options; hence failed","FAILURE");
		  
			// go out of the frame
			snW.switchToDefault();

			// Log out
			if(!snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("The logout Failed", "FAILURE");

			status = "PASS";

		} finally{

			// close the browser
			snW.quitBrowser();	

		}

	}

	@DataProvider(name = "OD_Stry0010705_Tc01")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OD_Stry0010705_Tc01");
		return arrayObject;
	}

}
