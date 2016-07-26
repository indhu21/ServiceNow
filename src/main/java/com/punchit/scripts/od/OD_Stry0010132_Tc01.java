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

public class OD_Stry0010132_Tc01 extends SuiteMethods {

	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "OD_Stry0010132_Tc01")
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

			// Step 2: Expand OpsDirector/OpsConsole/under application navigator to select Alert Console
			if(snW.selectMenu("Ops_Director","Ops_Consoles", "Alert_Console"))
				Reporter.reportStep("Step 2: The 'Alert Console' under OpsConsole - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The 'Alert Console' under OpsConsole - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");

			//Step 4: Scroll down to the referred Alert Number per reference data which has child alert
			if(snW.clickLink("New_Alerts"))
				Reporter.reportStep("Step 3A: 'New Alerts' in the alert console could be clicked sucessfully", "SUCCESS");
			else	
				Reporter.reportStep("Step 3A: 'New Alerts' in the alert console could not be clicked", "FAILURE");

			snW.Wait(5000);

			// Remove All filters
			if(!snW.clickByXpath("All_Xpath"))
				Reporter.reportStep("Step 3B: The filter could not be removed","FAILURE");
			snW.Wait(5000);
			// Click filter
			if(!snW.clickById("ALERT_FunnelIcon_Id"))
				Reporter.reportStep("Step 3B: The funnel icon could not be clicked","FAILURE");

			snW.Wait(5000);

			// Add new filter with status as NEW
			if(!snW.addNewFilter("Tally", "greater than", "2"))
				Reporter.reportStep("Step 3B: The filter could not be selected 'Tally greater than 2'","FAILURE");

			//Step : 3 Click Alert Console and identify a New alert which has a Tally count of more than 2 from the “New Alerts” 
			if(snW.clickByXpath("ALERT_RunFilter_Xpath"))
				Reporter.reportStep("Step 3B: Filter value  could be set to Alerts that has 'Tally greater than 2'", "SUCCESS");
			else
				Reporter.reportStep("Step 3B: 'Filter could not be set to Alerts that has 'Tally greater than 2'", "FAILURE");

			snW.Wait(5000);			

			if(snW.getTextByXpath("ALERT_AlertId_Xpath").equals("No records to display")){
				status = "Insufficient Data";
				Reporter.reportStep("Step 4: There is no records to display for Child Alerts","FAILURE");
			}

			//Step 4: If there is an alert, Take a note of the Last occurrence time.

			String lastOccurence = snW.getTextByXpath("ALERTPROFILE_LastOccurrence_Xpath");
			String created= snW.getTextByXpath("ALERTPROFILE_Created_Xpath");
			String alertId = snW.getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");

			if(lastOccurence!=null && created!=null)
				Reporter.reportStep("Step 4: 'Last Occurence and created' could be noted "+ lastOccurence+" & "+ created +" sucessfully", "SUCCESS");
			else
				Reporter.reportStep("Step 4: 'Last Occurence' could not be noted", "FAILURE");
			
			//Step : 6 Click on Number to open the alert	Alert Record opens
			if(snW.clickLink(alertId, false))
				Reporter.reportStep("Step 5: 'First Alert:' "+ alertId +" could be clicked, sucessfully","SUCCESS");
			else	
				Reporter.reportStep("Step 5: 'First Alert:' "+ alertId +" could not be clicked","FAILURE");


			String alertFirstOccurence=snW.getTextByXpath("ALERT_ActivityLog_Xpath");
			snW.scrollToElementByXpath("ALERT_ActivityLog_Xpath", 0, 500);
			snW.Wait(2000);
			
			String alertLastOccurence=snW.getTextByXpath("ALERTRECURRENCES_AlertTime_Xpath");
			snW.scrollToElementByXpath("ALERTRECURRENCES_AlertTime_Xpath",0, 1000);
			//System.out.println(timeInActivity);
			
			if(alertFirstOccurence.contains(created) && alertLastOccurence.contains(lastOccurence))
				Reporter.reportStep("Step 6: 'First & Last Occurence:' "+ lastOccurence +" & "+ created +" could be matched, sucessfully","SUCCESS");
			else
				Reporter.reportStep("Step 6: 'First & Last Occurence:' "+ lastOccurence +" & "+ created +" could not be matched","FAILURE");


			// Step 11: Log out
			// go out of the frame
			snW.switchToDefault();

			if(snW.clickByXpath("Logout_Xpath"))

				Reporter.reportStep("Step 7: The Log out is clicked successfully.","SUCCESS");
			else
				Reporter.reportStep("Step 7: The logout Failed", "FAILURE");		


			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "OD_Stry0010132_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("OD_Stry0010132_Tc01");
		return arrayObject;
	}



}