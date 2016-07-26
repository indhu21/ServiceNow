package com.punchit.scripts.od;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;




import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class OD_Stry0010149_Tc01 extends SuiteMethods {
	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider="OD_Stry0010149_Tc01",groups="OpsDirector")

	public void assignAlert(String regUser, String regPwd, String assignedTo, String verUser, String verPwd) {
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
			
			if(snW.selectMenu("Ops_Director","Ops_Consoles", "My_Alerts"))
				Reporter.reportStep("Step 2: My Alert Console under OpsConsole - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: My Alert Console under OpsConsole - menu could not be selected","FAILURE");
			snW.Wait(1000);

			snW.switchToFrame("Frame_Main");

			String alert = snW.getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
			if(alert.equalsIgnoreCase(""))
			{
				status="INSUFFICIENT DATA";
				Reporter.reportStep("No records available under My Alerts","FAILURE");					
			}


			//step 4: select an alert
			if(snW.clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
				Reporter.reportStep("Step 3: Alert "+alert +" is clicked successfully","SUCCESS");
			else	
				Reporter.reportStep("Step 3: Alert "+alert +" could not be clicked","FAILURE");
			snW.Wait(1000);
			
			String Assignedto=snW.getAttributeByXpath("MY_ALERT_AssignedTo_xpath", "value");
			
			System.out.println("Assigned to "+ Assignedto);
			
			// click on Lookup
			if(!snW.clickById("MY_ALERT_Assignedto_lookup_ID"))
				Reporter.reportStep("Step 4: Assigned to lookup could not be clicked","FAILURE");
			
			snW.switchToSecondWindow();
			snW.Wait(1000);
			String supname1 = "Name";
			if(!snW.selectByVisibleTextByXpath("Runbook_SearchKey_Xpath", supname1))
				Reporter.reportStep("Step 4: Runbook - Search Key could not be selected","FAILURE");
			snW.Wait(500);
			if(!snW.enterAndChoose("Header_SearchBox_Xpath", "!="+Assignedto))
				Reporter.reportStep("Step 4: !="+Assignedto+" could not be entered in search box","FAILURE");
			snW.Wait(3000);
			if(!snW.clickByXpath("First_Searched_Record_Xpath"))
				Reporter.reportStep("Step 4: First searched user could not be selected","FAILURE");
			
			snW.switchToPrimary();
			snW.switchToFrame("Frame_Main");
			snW.Wait(3000);
			
			String NewAssignment=snW.getAttributeById("MY_ALERT_AssignedTo_id","value");
			if(NewAssignment.contentEquals(Assignedto))
				Reporter.reportStep("Step 4: Assigned to could not be selected with new value  ","FAILURE");
			else
				Reporter.reportStep("Step 4: Assigned to is selected successfully with "+NewAssignment,"SUCCESS");
			
			//sysverb_update
			//step 6: click update to save
			if(snW.clickById("CIS_UpdateButton_Id")) 
				Reporter.reportStep("Step 5: Update has been clicked successfully", "SUCCESS");
			else
				Reporter.reportStep("Step 5: Update could not be clicked","FAILURE");
			snW.Wait(5000);
			
			// go out of the frame
			snW.switchToDefault();

			// Log out
			if(!snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("The logout Failed", "FAILURE");
			snW.Wait(5000);
            /*
			// Step 7: Log in as a different group
			if(snW.login(verUser,verPwd))
				Reporter.reportStep("Step 6: The login with username:"+ verUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 6: The login with username:"+ verUser + " is not successful", "FAILURE");

			// Step 8 : Expand OpsDirector/OpsConsole/ under application navigator to select Alert Console
			if(snW.selectMenu("Ops_Director","Ops_Consoles", "My_Alerts"))
				Reporter.reportStep("Step 7: The My Alerts Console under OpsConsole - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 7: The My Alerts Console under OpsConsole - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			
			if(snW.enterByXpath("RunBook_Templates_Searchbox_xpath", alert))
				Reporter.reportStep("Step 6: Alert No is entered successfully to be searched ","SUCCESS");
			else
				Reporter.reportStep("Step 6: Alert No could not be entered","FAILURE");
			// Press enter key
            snW.PresEnter();
            
           if(!snW.clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
        	   Reporter.reportStep("Step 7: Searched Alert could not be found","FAILURE");
            

			// Step 9 :Click Alert Console and select the alert record you assigned 
//			if(snW.clickLink(alert))
//				Reporter.reportStep("Step 9: The assigned alert record  has been clicked successfully","SUCCESS");
//			else
//				Reporter.reportStep("Step 9: The assigned alert record could not be clicked","FAILURE");

			snW.scrollToElementByXpath("ALERT_ActivityUpdateGroup_Xpath");
			
			// Step 10 :Code to verify the status change
			String sAssignedTo = snW.getTextByXpath("ALERT_ActivityUpdateGroup_Xpath"); 

			if(sAssignedTo.toLowerCase().contains(assignedTo.toLowerCase()))
				Reporter.reportStep("Step 10: The Alert has been assigned to "+ assignedTo ,"SUCCESS");
			else
				Reporter.reportStep("Step 10: The Alert could not be assigned to "+ assignedTo ,"FAILURE");

			// go out of the frame
			snW.switchToDefault();

			// Log out
			if(snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("Step 11: The Log out is clicked successfully.","SUCCESS");
			else
				Reporter.reportStep("Step 11: The logout Failed", "FAILURE");	
				*/	

			status = "PASS";

		} finally{

			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name="OD_Stry0010149_Tc01")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OD_Stry0010149_Tc01");
		return arrayObject;
	}	
}
