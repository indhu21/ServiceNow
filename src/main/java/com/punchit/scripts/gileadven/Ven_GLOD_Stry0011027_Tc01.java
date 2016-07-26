package com.punchit.scripts.gileadven;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_Stry0011027_Tc01 extends SuiteMethods {

	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "Ven_GLOD_Stry0011027_Tc01")
	public void testName(String regUser, String regPwd, String grpName) throws InterruptedException {

		try {

			// Pre-requisities 
			snW = new ServiceNowWrappers(entityId);

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Log in to appli cation
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("Step 1: The login with username:"
						+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"
						+ regUser + " is not successful", "FAILURE");

			// Step 2: Open Alert Console under user consoles
			if(snW.selectMenu("Ops_Director","User_Consoles", "Alert_Console"))
				Reporter.reportStep("Step 2: The Alert Console under OpsConsole - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Alert Console under OpsConsole - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			
			//Wait for the page to load
			Thread.sleep(3000);

			//Step 3a: Select the first alert profile to right click on it.
			String Alert_no = (snW.getTextByXpath("AlertConsole_FirstNewAlert_Xpath"));
			if(Alert_no == "")
		     {
				status = "INSUFFICIENT DATA";
				Reporter.reportStep("Step 3a: No Data Present under My Alerts","FAILURE");
			  
		     }   

			
			//Click and open the alert.
			if(snW.clickByXpath("AlertConsole_FirstNewAlert_Xpath"))
				{Thread.sleep(2000);
				Reporter.reportStep("Step 3b: Alert record no:" +Alert_no+" displayed successfully", "SUCCESS");
				}
				else
				Reporter.reportStep("Step 3b: Alert record could not be displayed", "FAILURE");
			
			//Check the Alert Records Alert Profile name
			String Alert_Profile = snW.getAttributeByXpath("ALERTRECORD_AlertProfile_Xpath", "value");
			System.out.println(Alert_Profile);
			
			//Get the alert profile Group name.
			snW.selectMenu("Ops_Director", "Configurations", "Alert_Profiles");
			//Switch to main frame
			snW.switchToFrame("Frame_Main");
			Thread.sleep(2000);
			//Search for the Alert Profile name.
			snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Name");
				
			snW.enterAndChoose("Alert_Num_Filter_Xpath", "="+Alert_Profile);
			snW.pressKey(Keys.ENTER);// Click Enter for the search to complete
			Thread.sleep(2000);
			
			//Click the Alert Profile
			if(snW.clickByXpath("Select_alert"))
				Reporter.reportStep("Step 4: Alert Profile : "+ Alert_Profile+" is Displayed", "SUCCESS");
			else
				Reporter.reportStep("Step 4: "+ Alert_Profile+" could not be displayed", "FAILURE");
			
			//Get the Group name of the alert profile
			String GroupName = snW.getAttributeByXpath("ALERTPROFILE_OwningGroup_Name_Xpath", "value");
			
			//Go to the alert Console and check for the group name to which the user belongs to:
			if(snW.selectMenu("Ops_Director","User_Consoles", "Alert_Console"))
				Reporter.reportStep("Step 5:Alert Console under OpsConsole - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 5:Alert Console under OpsConsole - menu could not be selected","FAILURE");

			//Find the filter and send Parse it to get the group name:
			
			snW.switchToFrame("Frame_Main");
			Thread.sleep(2000);
			
			snW.getDriver().findElementByLinkText("My Groups Alerts").click();
			Thread.sleep(2000);

			//Find the group name from the filter path:
			String filter_values = snW.getTextByXpath("FUNNEL_FILTER_Xpath");
		    System.out.println("Filter elements="+filter_values);
		    
		    String[] arrSplit=filter_values.split(">");
		    
		    for (int i=0; i<arrSplit.length;i++)
		    {
		     System.out.println("Splited array list - "+arrSplit[i]);
		     if ((arrSplit[i]).contains(GroupName))
		     {
		     Reporter.reportStep("Step 6: Alert records belonging to the User groups are displayed","SUCCESS");
		     break;
		     }
		     
		    else if(i==arrSplit.length)
		    {
		     Reporter.reportStep("Step 6:Alert records belonging only to the user groups are not displayed","FAILURE");  
		    }
		    }			
			
		    //Open the Particular Alert record Selected in the Above steps:
		    snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Number");
			
			snW.enterAndChoose("Alert_Num_Filter_Xpath", "="+Alert_no);
			snW.pressKey(Keys.ENTER);// Click Enter for the search to complete
			Thread.sleep(2000);
			
			// Open the Alert Ticket
			if(snW.clickByXpath("Select_alert"))
				Reporter.reportStep("Step 7: Alert Record "+ Alert_no+" is Displayed", "SUCCESS");
			else
				Reporter.reportStep("Step 7:Alert Record "+ Alert_no+" could not be displayed", "FAILURE");
			
			Thread.sleep(2000);
			
			// Step: Verify all ReadOnly Fields
			String[] readOnlyFields={"ALERTRECORD_Num_Xpath", "ALERTRECORD_Assignto_Xpath", "ALERTRECORD_AlertProfile_Xpath", 
					"ALERTRECORD_ReactionType_Xpath", "ALERTRECORD_ConfItem_Xpath", "ALERTRECORD_AlertSeverity_Xpath", 
					"ALERTRECORD_AlertState_Xpath", "ALERTRECORD_ClosedBy_Xpath", "ALERTRECORD_ClosureCode_Xpath",
					"ALERTRECORD_Tally_Xpath", "ALERTRECORD_Rating_Xpath", "ALERTRECORD_ShortDesc_Xpath", "ALERTRECORD_AlertDesc_Xpath"};
			
			String[] readOnlyFieldsDesc = {"Reporting Customer",
					"Configuration Item",
					"Assignment Group",
			"Short Description"};

			

			//snW.verifyDisabledFieldsByXpath(readOnlyFields);			

			// go out of the frame
			snW.switchToDefault();

			// Log out
			if(snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("Step 8: The Log out is clicked successfully.","SUCCESS");
			else
				Reporter.reportStep("Step 8: The logout Failed", "FAILURE");		


			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "Ven_GLOD_Stry0011027_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("Ven_GLOD_Stry0011027_Tc01");
		return arrayObject;
	}


}
