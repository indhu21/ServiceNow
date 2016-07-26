package com.punchit.scripts.od;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.exceptions.COSVisitorException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;




public class OD_Stry0010709_Tc01 extends SuiteMethods {

	@Test(dataProvider = "OD_Stry0010709_Tc01")
	public void acknowledgingUser(String regUser, String regPwd) throws InterruptedException,IOException, COSVisitorException {
		// Create Instance
		ServiceNowWrappers snW = new ServiceNowWrappers(entityId);

		try{
			int a=0;
			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Log in to application
			if(snW.login(regUser, regPwd))
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");

			// get the full name
			String fullName = snW.getTextById("FullName_Id");
			
			// Step 2: In application navigator expand OpsDirector/Registration to
			// select CI Scope Registration
			if(snW.selectMenu("Ops_Director","Ops_Consoles", "My_Alert_Console"))
				Reporter.reportStep("Step 2: The My Alerts under OpsConsole - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The New Alerts under OpsConsole - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");

			//Step 3:Validate that there is a box with heading ‘My  Alerts’ and click on My Alerts link
			if(snW.clickByXpath("ALERT_MyAlertHeader_Xpath"))
				Reporter.reportStep("Step 3: The My Alerts Link has been clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 3: The My Alerts Link could not be clicked","FAILURE");


			//Step 4:Expand the filter by clicking on the funnel icon
			if(snW.clickById("ALERT_FunnelIcon_Id"))
				Reporter.reportStep("Step 4: The funnel icon has been clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 4: The funnel icon could not be clicked","FAILURE");

			snW.Wait(10000);

			// Step 5:Validate that filter is set to:Assigned to is me, 
			// State is Acknowledged or In Progress, 
			// Related Task is Not Resolved or Closed.
			String Activity = snW.getTextByXpath("ALERT_BREADCRUMB_Xpath");
			System.out.println(Activity);
			Boolean bSuccess = false;
			String[] arrSplit=Activity.split(">");
			//Boolean bSuccess = false;
			for (int i=0; i<arrSplit.length;i++)
				
			{
			   System.out.println(arrSplit[i]);
			   if(("Assigned to is "+fullName).equalsIgnoreCase(arrSplit[i]))
		      {
				 bSuccess = true;
				 Reporter.reportStep("Step 5a: The filter Assigned to is set with the value: "+fullName, "SUCCESS"); 
				 break; 
		      }
			   else
				   bSuccess = false;
		    }
			
			 for (int j=0; j<arrSplit.length;j++)
			   {
			   if(("State = Acknowledged").equalsIgnoreCase(arrSplit[j])||("State = In Progress").equalsIgnoreCase(arrSplit[j]))
			   {
				   bSuccess = true;
				   Reporter.reportStep("Step 5b: The filter State is set with the value: Acknowledged or In Progress", "SUCCESS");
			       break;	  
			  }
			   else
				   bSuccess = false;
		    }
			   
			   for (int k=0; k<arrSplit.length;k++)
			   {
			   if(("Related Task != Resolved").equalsIgnoreCase(arrSplit[k])||("Related Task != Closed").equalsIgnoreCase(arrSplit[k]))
			     {
				   bSuccess = true;
				   Reporter.reportStep("Step 5c: The filter Related Task is set with the value: Resolved or Closed", "SUCCESS");
			       break;
			      }
			   else
				   bSuccess = false;
		    }
		     
		 if(bSuccess == false)
		 {
		        Reporter.reportStep("Step 6: The filter condition did not match, hence failed", "FAILURE");
		 
		 }
			 
			// go out of the frame
			snW.switchToDefault();

			// Step 7: Log out
			if(snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("Step 7: The Log out is clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 7: The Log out could not be clicked", "FAILURE");

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}
	}

	@DataProvider(name = "OD_Stry0010709_Tc01")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OD_Stry0010709_Tc01");
		return arrayObject;
	}

}
