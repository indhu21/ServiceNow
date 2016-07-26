package com.punchit.scripts.od;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class OD_Stry0010706_Tc01 extends SuiteMethods {

	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "OD_Stry0010706_Tc01",groups="OpsDirector")
	public void acknowledgingUser(String regUser, String regPwd) {

		try {
			// Pre-requisities 
			snW = new ServiceNowWrappers(entityId);

			// Step 0: Launch the application
			if(snW.launchApp(browserName, true))
				Reporter.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
			else
				Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

			// Step 1: Log in to application
			if(snW.login(regUser, regPwd))
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");

			snW.Wait(2000);
			snW.switchToDefault();
			String fullname = snW.getTextById("FullName_Id");
			// Step 2: In application navigator expand OpsDirector/Registration to
			// select CI Scope Registration
			if(snW.selectMenu("Ops_Director","Ops_Consoles", "Alert_Console"))
				Reporter.reportStep("Step 2: The Alert console under OpsConsole - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Alert console under OpsConsole - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
            
			// Step 3: Clicking on new alert 
			if(!snW.clickLink("NEW_ALERTS_LINK"))
			    Reporter.reportStep("Step 3: New alert link could be clicked","FAILURE");
			
			 String alertId=snW.getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
			 System.out.println(alertId);
			 
			//if(snW.getTextByXpath("ALERT_AlertId_Xpath").equals("No records to display")){
			if (snW.rightClickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
				Reporter.reportStep("Step 3: Right click on the Alert is Performed Successfully","SUCCESS");
			else{
			   status = "Insufficient Data";
				Reporter.reportStep("Step 3: There are no records available in new Alerts","FAILURE");
			}

			// Step 3: From My Alerts section, select the same alert and right click
			// to select acknowledge
			//String alertId = snW.getAttributeByXpath("ALERT_AlertId_Xpath", "data-title-value");
			
			snW.Wait(5000);

		/*	if(!snW.rightClickByXpath("ALERT_ListBody_Xpath"))
				Reporter.reportStep("The Right click upon My Alert failed.","FAILURE");
*/
			if(snW.clickByXpath("AlertConsole_Rightclick_selectAcknowledge_xpath"))
				Reporter.reportStep("Step 4: Acknowledge option selected", "SUCCESS");
			else
				Reporter.reportStep("Step 4: Acknowledge option not selected", "FAILURE");
			
			snW.Wait(5000);

			// go out of the frame
			snW.switchToDefault();	

			if(!snW.selectMenu("Ops_Director","Ops_Consoles", "My_Alerts"))
				Reporter.reportStep("The My Alerts under OpsConsole - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			
			snW.clickLink("MY_ALERTS_LINK");
			System.out.println("My link clicked");

			// Step 4: From My Alerts section, select the same alert
			 snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Number");
	          snW.enterAndChoose("Alert_Profiles_Search_Xpath",alertId);
	          
			if(snW.clickByXpath("Select_alert"))
				Reporter.reportStep("Step 4: My Alert:"+alertId+" is clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 4: My Alert:"+alertId+" is not clicked","FAILURE");

			// Step 5: Validate that the �Assigned To� field has the value of login ID
			String AssignedTo=snW.getAttributeByXpath("ALERT_AssignedTo_Xpath", "value");
			System.out.println(AssignedTo);
			if(AssignedTo.equalsIgnoreCase(fullname))
				Reporter.reportStep("Step 5: The Assigned To field has the value of login ID as expected","SUCCESS");
			else
				Reporter.reportStep("Step 5: The Assigned To field does not have the value of login ID","FAILURE");			

			// Step 6: Scroll down to the Activity Log of alert record has the entry:
			snW.isExistByXpath("Activity_Xpath");
			snW.scrollToElementByXpath("Activity_Xpath", 0, 4000);
			snW.Wait(2000);
			String Activity=snW.getTextByXpath("ALERT_ActivityLog_Xpath");
			System.out.println(Activity);
			String[] arrSplit=Activity.split("\n");
			
			for (int i=0; i<arrSplit.length;i++)
			{   
				System.out.println(arrSplit[i]);
			   if(("Assigned to: "+fullname).equalsIgnoreCase(arrSplit[i]))
		      {
				   Reporter.reportStep("Step 6: The Activity Log of alert record has the entry as expected","SUCCESS");
			       break;
			  }
			else if(i==arrSplit.length)
			   {
				Reporter.reportStep("Step 6: The Activity Log of alert record does not have the entry","FAILURE");
			   }
		    }
			
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

	@DataProvider(name = "OD_Stry0010706_Tc01")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OD_Stry0010706_Tc01");
		return arrayObject;
	}

}
