package com.punchit.scripts.od;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;




import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

// for using ATU reporting -- added the listeners

public class OD_Stry0010145_Tc01 extends SuiteMethods {

	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "OD_Stry0010145_Tc01")
	public void testName(String regUser, String regPwd, String closedBy) throws InterruptedException {

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
			if(snW.selectMenu("Ops_Direcor","Ops_Consoles", "Alert_Console"))
				Reporter.reportStep("Step 2: The Alert Console under OpsConsole - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Alert Console under OpsConsole - menu could not be selected","FAILURE");

			// Switch to the main frame
			Thread.sleep(3000);
			snW.switchToFrame("Frame_Main");

			//Check for the alerts availability.
			if(snW.IsElementNotPresentByXpath("AlertConsole_FirstNewAlert_Xpath")){
				status = "Insufficient Data";
				Reporter.reportStep("Step 3: There is no records to display for new Alerts","FAILURE");
			}
			
			//Step 5: Check for the value on Field “child” Should be greater than 0
			String firstAlertId = snW.getTextByXpath("AlertConsole_FirstNewAlert_Xpath");

			//Step : 6 Click on Number to open the alert
			if(!snW.rightClickByXpath("AlertConsole_FirstNewAlert_Xpath"))
				Reporter.reportStep("Step 3a: Right click on the alert could not be clicked","FAILURE");

			if(!snW.clickByXpath("Close_xpath"))
				Reporter.reportStep("Step 3a: Close alert could not be clicked","FAILURE");

			if(snW.clickByXpath("Closeas_noise_xpath"))
				Reporter.reportStep("Step 3: Right click to choose close as others  is peformed  successfully","SUCCESS");
			else
				Reporter.reportStep("Step 3: Right click  could not be performed","FAILURE");

			
			Thread.sleep(3000);
			
			//Check for the alerts availability.
			if(snW.IsElementNotPresentByXpath("AlertConsole_FirstNewAlert_Xpath")){
				status = "Insufficient Data";
				Reporter.reportStep("Step 4: There is no records to display for new Alerts","FAILURE");
			}
			
			String secondAlertId = snW.getTextByXpath("AlertConsole_FirstNewAlert_Xpath");

			//Step : 6 Click on Number to open the alert
			if(!snW.rightClickByXpath("AlertConsole_FirstNewAlert_Xpath"))
				Reporter.reportStep("Step 4: Right click on the alert could not be clicked","FAILURE");

			if(!snW.clickByXpath("Close_xpath"))
				Reporter.reportStep("Step 4: Close alert could not be clicked","FAILURE");

			if(!snW.clickByXpath("Closeas_other_xpath"))
				Reporter.reportStep("Step 4: Right click on an Close as Other from Alert could not be clicked","FAILURE");

			//Switch to active element.
	        Thread.sleep(1000);
	        snW.getDriver().switchTo().activeElement();
	        
	        //Enter the input text.
	        
	      

	        if(snW.enterById("Close_value_ID", "PUNCH IT"))
	        Reporter.reportStep("Step 4: Text is sucessfully entered", "SUCCESS");
			else
				Reporter.reportStep("Step 4: Text entry was not possible", "FAILURE");
			
			if(snW.clickByXpath("Ok_Button_Xpath"))
				Reporter.reportStep("Step 5: Close as Other is performed successfully","SUCCESS");
			else
				Reporter.reportStep("Step 5: OK Button could not be clicked.","FAILURE");
			
			//Check for the closed alerts in the above steps.
			
			if(snW.selectMenu("Ops_Director","Data", "Alerts"))
				Reporter.reportStep("Step 6: Alerts menu is selected Successfully.", "SUCCESS");
			else
				Reporter.reportStep("Step 6: Alerts menu could not be selected Successfully.", "FAILURE");
			
			//Switch to main frame.
			Thread.sleep(3000);
		        snW.switchToFrame("Frame_Main");
		        // Select the Alert number closed in the above steps.
		        
		        snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Number");
		        snW.enterAndChoose("Alert_Num_Filter_Xpath",firstAlertId);
		        
		        Thread.sleep(2000);

		        if(snW.clickByXpath("Paralt_xpath"))
		        	Reporter.reportStep("Step 7: Closed Alert Displayed", "SUCCESS");
				else
					Reporter.reportStep("Step 7: Closed alert could not be displayed", "FAILURE");
			
			//Step 8: Validate Closed by Value matched with login Operator
			Thread.sleep(3000);;
			
			//Check the value of Closed by should be same as Logged in user.
			String Closedby = snW.getAttributeByXpath("ALERTRECORD_ClosedBy_Xpath", "value");
			System.out.println(Closedby);
			snW.switchToDefault();
			String UserName = snW.getTextById("FullName_Id");
			System.out.println(UserName);
			if(UserName.equalsIgnoreCase(Closedby))
				Reporter.reportStep("Step 8: Closed by Name is the  current loggged in user name", "SUCCESS");
			else
				Reporter.reportStep("Step 8: Closed by Name is not same as currently looged in user name", "FAILURE");

			// go out of the frame
			snW.switchToDefault();

			// Step 12: Log out
			if(snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("Step 9: The Log out is clicked successfully.","SUCCESS");
			else
				Reporter.reportStep("Step 9: The logout Failed", "FAILURE");		


			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "OD_Stry0010145_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("OD_Stry0010145_Tc01");
		return arrayObject;
	}



}