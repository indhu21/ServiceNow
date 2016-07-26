package com.punchit.scripts.od;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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

// for using ATU reporting -- added the listeners

public class OD_Stry0010141_Tc01 extends SuiteMethods {

	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "OD_Stry0010141_Tc01")
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


			// Step 3: Open Alert Console under user consoles
			if(snW.selectMenu("Ops_Director","Ops_Consoles", "Alert_Console"))
				Reporter.reportStep("Step 3: The Alert Consol under OpsConsole - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 3: The Alert Console under OpsConsole - menu could not be selected","FAILURE");

			// Switch to the main frame
			Thread.sleep(3000);
			snW.switchToFrame("Frame_Main");

			//Check the Alert Console table for the given condition for the alers to exist.
			snW.getDriver().findElementByLinkText("New Alerts").click(); 
		       Thread.sleep(2000);
		       snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Child Count");
		    	snW.enterAndChoose("Alert_Num_Filter_Xpath", ">1");
			
			Thread.sleep(3000);//wait for the records to load.
			
			String ChildCount = snW.getTextByXpath("New_Alerts_ChildCount_ListView_Xpath");
			if(snW.clickByXpath("Select_alert"))
				Reporter.reportStep("Step 4: Alert with Child step greater than 0 is Selected successfully", "SUCCESS");
			else
				{
				status = "INSUFFICIENT DATA";
				Reporter.reportStep("Step 4: Alert with Child step Greater than 0 was not available", "FAILURE");
				
					
				}	
			Thread.sleep(3000);// Wait for the alert ticket to be displayed.
			
			String ChildCountForm = snW.getTextByXpath("New_Alerts_ChildCount_FormView_Xpath");
			
			//Step 5 Verify the child count on the List view and the form view of the Alert
			if(ChildCount.contentEquals(ChildCountForm))
				Reporter.reportStep("Step 5: The Child Count is a Match", "SUCCESS");
			else
				Reporter.reportStep("Step 5: The Child Count is not Match", "WARNING");
			
			//Open Alert console and check for child count is 0
			
			if(snW.selectMenu("Ops_Director","Ops_Consoles", "Alert_Console"))
				Reporter.reportStep("Step 6: The Alert Console under OpsConsole - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 6: The Alert Console under OpsConsole - menu could not be selected","FAILURE");

			// Switch to the main frame
			Thread.sleep(3000);
			snW.switchToFrame("Frame_Main");
			
			//Check the Alert Console table for the given condition for the alers to exist.
			snW.getDriver().findElementByLinkText("New Alerts").click(); 
		       Thread.sleep(2000);
		       snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Child Count");
		    	snW.enterAndChoose("Alert_Num_Filter_Xpath", "0");
			
			Thread.sleep(3000);//wait for the records to load.
			
			String ChildCount0 = snW.getTextByXpath("New_Alerts_ChildCount_ListView_Xpath");
			
			if(ChildCount0.contentEquals("0"))
				Reporter.reportStep("Step 5: The Child Count is 0", "SUCCESS");
			else
				Reporter.reportStep("Step 5: The Child Count is not 0", "FAILURE");
			
			// Step 11: Log out
			// go out of the frame
			snW.switchToDefault();

			if(snW.clickByXpath("Logout_Xpath"))

				Reporter.reportStep("Step 6: The Log out is clicked successfully.","SUCCESS");
			else
				Reporter.reportStep("Step 6: The logout Failed", "FAILURE");		


			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "OD_Stry0010141_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("OD_Stry0010141_Tc01");
		return arrayObject;
	}



}