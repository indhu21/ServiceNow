package com.punchit.scripts.gileadven;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_Stry0010935_Tc01 extends SuiteMethods {


	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "Ven_GLOD_Stry0010935_Tc01",groups="OpsDirector")
	public void testName(String regUser, String regPwd) {

		// Pre-requisities
		snW = new ServiceNowWrappers(entityId);

		try {
               
			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("Step 1: The login with username:"
						+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"
						+ regUser + " is not successful", "FAILURE");

			// Step 2: Write a code to select the menu using 
			if(snW.selectMenu("Ops_Director","Administration", "Application_Properties"))
				Reporter.reportStep("Step 2: The Application Properties under OpsConsole - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Application Properties under OpsConsole - menu could not be selected","FAILURE");

			// Move to main menu
			snW.switchToFrame("Frame_Main");
			snW.scrollToElementByXpath("AP_AlertSevRec_Xpath", 0, 05);

			// Step 3: Select Newest alert severity
			if(snW.selectByVisibleTextByXpath("AP_AlertSevRec_Xpath", "Severity is always updated with new Alert Recurrences"))
				Reporter.reportStep("Step 3:The Option : Severity is always updated with new Alert Recurrences is selected  successfully from the dropdown","SUCCESS");
			else
				Reporter.reportStep("Step 3: Severity is always updated with new Alert Recurrences could not be selected from the dropdown","FAILURE");

			if(snW.clickByXpath("Save_Xpath"))
				Reporter.reportStep("Step 4:Save is clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 4:Save could not be clicked","FAILURE");

			//snW.selectMenuFromMainHeader("Ops_Consoles", menu)
			if(snW.selectMenu("Ops_Director","Ops_Consoles", "Alert_Console"))
				Reporter.reportStep("Step 5: The Alert Consoles under OpsConsole - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 5: The Alert Consoles under OpsConsole - menu could not be selected","FAILURE");

			snW.switchToFrame("Frame_Main");

			String Tally = snW.getTextByXpath("Alert_console_FirstTally_xpath");
			System.out.println("Tally is " + Tally);

			Integer x = Integer.valueOf(Tally);
			if(x<2)
			{
				snW.clickLink("Tally");
			}

			String alertId = snW.getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
			// click on the Alert Profile 
			if(snW.clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
				Reporter.reportStep("Step 6: The Alert Profile "+alertId+" is clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 6: The Alert Profile "+alertId+" could not be clicked ","FAILURE");

			//snW.scrollToElementByXpath("Alert_console_alertrecurrences_firstseverity_xpath");
			snW.scrollToElementByXpath("Alert_console_alertrecurrences_firstseverity_xpath", 0, 50);
			String Severity1 = snW.getTextByXpath("Alert_console_alertrecurrences_firstseverity_xpath");
			
			if(!Severity1.equalsIgnoreCase(""))
				Reporter.reportStep("Step 7: Newset Alert severity in Recurrences section is "+Severity1,"SUCCESS");
			else
			{
				status="INSUFFICIENT DATA";
				Reporter.reportStep("Step 7: Newset Alert severity in Recurrences section could not be found ","FAILURE");
			}
			System.out.print("Severity is " +Severity1);
			// Move to main menu
			//snW.Wait(5000);
			// Step 2: Write a code to select the menu using 
			if(snW.selectMenu("Ops_Director","Administration", "Application_Properties"))
				Reporter.reportStep("Step 8: The Application Properties under OpsConsole - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 8: The Application Properties under OpsConsole - menu could not be selected","FAILURE");

			// Move to main menu
			snW.switchToFrame("Frame_Main");
			snW.scrollToElementByXpath("AP_AlertSevRec_Xpath", 0, 05);

			
			// Step 3: Severity is always updated with new alert recurrences
//			String sevRecValue = snW.getDefaultValueByXpath("AP_AlertSevRec_Xpath");
//
//			if(sevRecValue.equals("Severity is always updated with new Alert Recurrences"))	
//				Reporter.reportStep("Step 3: The Severity is always updated with new Alert Recurrences is selected successfully","SUCCESS");
//			else
//				Reporter.reportStep("Step 3: The Severity is always updated with new Alert Recurrences could not be Selected","FAILURE");


			// Step 4: Select worst alert severity
			if(snW.selectByVisibleTextByXpath("AP_AlertSevRec_Xpath", "Severity always shows the worst Severity encountered"))
				Reporter.reportStep("Step 9:The Option :Severity always shows the worst Severity encountered is selected  successfully from the dropdown","SUCCESS");
			else
				Reporter.reportStep("Step 9: Severity always shows the worst Severity encountered could not be selected from the dropdown","FAILURE");

			if(snW.clickByXpath("Save_Xpath"))
				Reporter.reportStep("Step 10:Save is clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 10:Save could not be clicked","FAILURE");

			//snW.selectMenuFromMainHeader("Ops_Consoles", menu)
			if(snW.selectMenu("Ops_Director","Ops_Consoles", "Alert_Console"))
				Reporter.reportStep("Step 11: The Alert Consoles under OpsConsole - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 11: The Alert Consoles under OpsConsole - menu could not be selected","FAILURE");

			snW.switchToFrame("Frame_Main");

//			String Tally1 = snW.getTextByXpath("Alert_console_FirstTally_xpath");
//			System.out.println("Tally is " + Tally);
//           
//			Integer x1 = Integer.valueOf(Tally);
//			if(Tally.equalsIgnoreCase(""))
//			{
//				status="INSUFFICIENT DATA";
//				Reporter.reportStep("No Alert profile is available to be selected","FAILURE");
//			}
//			if(x1<2)
//			{
//				snW.clickLink("Tally");
//			}

			String alertId1 = snW.getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
			// click on the Alert Profile 
			if(snW.clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
				Reporter.reportStep("Step 12: The Alert Profile "+alertId1+" is clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 12: The Alert Profile "+alertId1+" could not be clicked ","FAILURE");
			snW.scrollToElementByXpath("Alert_console_alertrecurrences_firstseverity_xpath", 0, 50);
			
			String Severity2 = snW.getTextByXpath("Alert_console_alertrecurrences_firstseverity_xpath");
			
			if(!Severity2.equalsIgnoreCase(""))
				Reporter.reportStep("Step 13: Newset Alert severity in Recurrences section is "+Severity1,"SUCCESS");
			else
			{
				status="INSUFFICIENT DATA";
				Reporter.reportStep("Step 13: Newset Alert severity in Recurrences section could not be found ","FAILURE");
			}
			System.out.print("Severity is " +Severity1);
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

	@DataProvider(name = "Ven_GLOD_Stry0010935_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("Ven_GLOD_Stry0010935_Tc01");
		return arrayObject;
	}

}