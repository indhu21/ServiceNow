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

public class OD_Stry0010139_Tc01 extends SuiteMethods {

	// Create Instance
	

	@Test(dataProvider = "OD_Stry0010139_Tc01")
	public void testName(String regUser, String regPwd) {

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

			//Step 2: Navigate to OpsDirector->Configurations-.> Alert Profiles
			if(snW.selectMenu("Ops_Director","Configurations", "Alert_Profiles"))
				Reporter.reportStep("Step 2: The 'Alert Profiles' under Configurations - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The 'Alert Profiles' under Configurations - menu could not be selected","FAILURE");

			//Step 3: Open Alert Profile from reference data and validate that Auto Close if selected
			snW.switchToFrame("Frame_Main");
			
			//if(!snW.clickById("ALERTPROFILE_FunnelIcon_Id"))
				//Reporter.reportStep("Step 3: The funnel icon could not be clicked","FAILURE");
			
		//	if(!snW.deleteAllFilters())
			//	Reporter.reportStep("Step 3: 'All Filters' could not be removed","FAILURE");
				
			//if(!snW.addNewFilterUsingInput("Name", "is", "Node Status"))
				//Reporter.reportStep("Step 3: 'New Filter' could not be selected","FAILURE");
			
			//if(!snW.clickByXpath("ALERT_RunFilter_Xpath"))
				//Reporter.reportStep("Step 3: 'Run' could not be clicked", "FAILURE");

			//snW.Wait(3000);
			String alertProfile=snW.getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
			if(alertProfile.equalsIgnoreCase(""))
			{
				status="INSUFFICIENT DATA";
				Reporter.reportStep("No Records available in Alert Profiles","FAILURE");

			}
			if(snW.clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
				Reporter.reportStep(alertProfile+ "is clicked successfully","SUCCESS");
			else
				Reporter.reportStep(alertProfile+ "could not be clicked","FAILURE");
				
//			if(!snW.clickLink(alertProfile, false))
//				Reporter.reportStep("Step 3: 'Alert Profile' could not be clicked", "FAILURE");
//			snW.Wait(3000);
			String autoCloseValue=snW.getAttributeById("ALERTPROFILE_Autoclose_Id", "value");
			
			if(autoCloseValue.equals("true"))
				Reporter.reportStep("Step 3: The 'Auto Closed' for Node Status profile checked successfully","SUCCESS");
			else
				if(!snW.clickById("ALERTPROFILE_AutocloseClick_Id"))
				Reporter.reportStep("Step 3: The 'Auto Closed' for Node Status profile could not checked","FAILURE");
		
			snW.switchToFrame("Frame_Nav");
			
		
			//Step 4: Navigate to Scenarios  under Ops Director Testing to select Scenarios
			if(snW.selectMenu("Ops_Director_Testing", "Scenarios"))
				Reporter.reportStep("Step 4: The 'Scenarios' under Ops Director Testing - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 4: The 'Scenarios' under Ops Director Testing - menu could not be selected","FAILURE");

			//Step 5: Select the Critical Scenario from Reference data and play it.
			snW.switchToFrame("Frame_Main");
			
			if(snW.clickLink("Punch Node Status Critical", false))
				Reporter.reportStep("Step 5A: The 'Punch Node Status Critical' in Scenarios clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 5A: The 'Punch Node Status Critical' in Scenarios could not be clicked","FAILURE");

			if(!snW.clickByXpath("SCENARIOS_PlayScenarios_Xpath"))
				Reporter.reportStep("Step 5B: The 'Play Scenarios' could not be clicked","FAILURE");
			snW.Wait(5000);
			
			if(snW.clickByXpath("SCENARIOS_Close_Xpath"))
				Reporter.reportStep("Step 5B: The 'Close Button' could be clicked sucessfully","SUCCESS");
			else	
				Reporter.reportStep("Step 5B: The 'Close Button' could not be clicked","FAILURE");

			
			//Step 6: Navigate to OpsDirector-> Alerts - > Data in Application Navigator to select Data	
			
			snW.switchToFrame("Frame_Nav");
			
			if(snW.enterById("filter_Id", "Alerts"))
				Reporter.reportStep("Step 6A: 'Alert Console' could be entered in filter box sucessfully","SUCCESS");
			else	
				Reporter.reportStep("Step 6A: 'Alert Console' could not be entered in filter box","FAILURE"); 
            
			if(snW.selectMenu("Data", "Alerts"))
				Reporter.reportStep("Step 6B: The 'Alerts' under Date - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 6B: The 'Alert' under Date - menu could not be selected","FAILURE");

			snW.switchToFrame("Frame_Main");
			
			//Step 7: Apply the filter Alert Profile = Reference Data
			
			if(!snW.clickById("ALERT_FunnelIcon_Id"))
				Reporter.reportStep("Step 7: The funnel icon could not be clicked","FAILURE");
			
			if(!snW.deleteAllFilters())
				Reporter.reportStep("Step 7: 'All Filters' could not be removed","FAILURE");
				
			
			if(!snW.addNewFilterUsingInput1("Alert Profile", "is", "Node Status"))
				Reporter.reportStep("Step 7: 'New Filter' could not be selected","FAILURE");
			snW.Wait(3000);
			
			if(snW.clickByXpath("ALERT_RunFilter_Xpath"))
				Reporter.reportStep("Step 7: Filter could be applied successfully", "SUCCESS");
			else	
				Reporter.reportStep("Step 7: Filter could not be applied successfully", "FAILURE");
			
			//Step 8: Open the most recent alert and take a note of status
			String lastOccuSor=snW.getAttributeByXpath("ALERTPROFILE_LastOccurenceSort_Xpath", "class");
			
			snW.Wait(3000);
			
			if(lastOccuSor.contains("down"))
				Reporter.reportStep("Step 8A: 'Alert Profiles' could be sorted Most recently","SUCCESS");
			else
				if(!snW.clickByXpath("ALERTPROFILE_LastOccurenceSort_Xpath"))
					Reporter.reportStep("Step 8A: 'Alert Profiles' could not be sorted Most recently","FAILURE");
			
			
			String alertId=snW.getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
			
			if(snW.clickLink(alertId, false))
				Reporter.reportStep("Step 8B: The 'Alert':"+alertId+" in alert profile could be selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 8B: The 'Alert':"+alertId+" in alert profile could not be selected ","FAILURE");
			
			snW.Wait(5000);
			
			String state=snW.getDefaultValueByXpath("ALERTRECORD_AlertState_Xpath");

			String severity=snW.getDefaultValueByXpath("ALERTRECORD_AlertSeverity_Xpath");
			
			if(severity.contains("Critical") && state.equals("New"))
				Reporter.reportStep("Step 8C: The 'Alert Severity': "+severity+" 'Alert State': "+ state +" could be matched successfully","SUCCESS");
			else
				Reporter.reportStep("Step 8C: The 'Alert Severity': "+severity+" 'Alert State': "+ state +" could not be matched","WARNING");
            
			//Step 9:Navigate to Scenarios under Ops Director Testing to select Scenarios
			snW.switchToFrame("Frame_Nav");
			//Step 13: Click Incidents in the Application Navigator Search box 
			if(!snW.enterById("filter_Id", "Ops Director Testing"))
				Reporter.reportStep("Step 8A: 'Ops Director Testing' could not be entered in filter box","FAILURE");
			
			if(snW.selectMenu("Ops_Director_Testing", "Scenarios"))
				Reporter.reportStep("Step 9: The 'Scenarios' under Ops Director Testing - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 9: The 'Scenarios' under Ops Director Testing - menu could not be selected","FAILURE");

			//Step 10: Select the Clear Scenario from Reference data and play it.
			snW.switchToFrame("Frame_Main");
			
			if(snW.clickLink("Punch Node status Clear", false))
				Reporter.reportStep("Step 10A: The 'Punch Node Status Clear' in Scenarios clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 10A: The 'Punch Node Status Clear' in Scenarios could not be clicked","FAILURE");

			snW.switchToFrame("Frame_Main");
			//Step 7: Run a Scenario from reference data
			
			if(!snW.clickByXpath("SCENARIOS_PlayScenarios_Xpath"))
				Reporter.reportStep("Step 10B: The 'Play Scenarios' could not be clicked","FAILURE");
			snW.Wait(5000);
			if(snW.clickByXpath("SCENARIOS_Close_Xpath"))
				Reporter.reportStep("Step 10B: The 'Close Button' could be clicked sucessfully","SUCCESS");
			else	
				Reporter.reportStep("Step 10B: The 'Close Button' could not be clicked","FAILURE");
			
			//Step 11: Repeat step 8 and 9	
			
			snW.switchToFrame("Frame_Nav");
			
			if(snW.enterById("filter_Id", "Alerts"))
				Reporter.reportStep("Step 11A: 'Alert Console' could be entered in filter box sucessfully","SUCCESS");
			else	
				Reporter.reportStep("Step 11A: 'Alert Console' could not be entered in filter box","FAILURE");
            
			if(snW.selectMenu("Data", "Alerts"))
				Reporter.reportStep("Step 11B: The 'Scenarios' under Ops Director Testing - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 11B: The 'Scenarios' under Ops Director Testing - menu could not be selected","FAILURE");

			snW.switchToFrame("Frame_Main");
			
			//Step 7: Apply the filter Alert Profile = Reference Data
			
			if(!snW.clickById("ALERT_FunnelIcon_Id"))
				Reporter.reportStep("Step 11C: The funnel icon could not be clicked","FAILURE");
			
			if(!snW.deleteAllFilters())
				Reporter.reportStep("Step 11C: 'All Filters' could not be removed","FAILURE");
				
			if(!snW.addNewFilterUsingInput1("Alert Profile", "is", "Node Status"))
				Reporter.reportStep("Step 11C: 'New Filter' could not be selected","FAILURE");
			
			snW.Wait(3000);
			
			if(!snW.clickByXpath("ALERT_RunFilter_Xpath"))
				Reporter.reportStep("Step 11C: 'Run' could not be clicked", "FAILURE");

		/*	lastOccuSor=snW.getAttributeByXpath("ALERTPROFILE_LastOccurenceSort_Xpath", "class");
			
			snW.Wait(3000);
			
			if(lastOccuSor.contains("down"))
				Reporter.reportStep("Step11C: 'Alert Profiles' could be sorted Most recently","SUCCESS");
			else
				if(!snW.clickByXpath("ALERTPROFILE_LastOccurenceSort_Xpath"))
					Reporter.reportStep("Step 11C: 'Alert Profiles' could not be sorted Most recently","FAILURE");
			*/	
			
			snW.Wait(3000);
			
			alertId=snW.getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
			
			if(snW.clickLink(alertId, false))
				Reporter.reportStep("Step 11C: The 'Alert':"+alertId+" in alert profile could be selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 11C: The 'Alert':"+alertId+" in alert profile could not be selected ","FAILURE");
			
			snW.Wait(5000);
			
			//Step 12:Open the most recent alert record and take a note of severity, status and closure code
			state=snW.getDefaultValueByXpath("ALERTRECORD_AlertState_Xpath");

			severity=snW.getDefaultValueByXpath("ALERTRECORD_AlertSeverity_Xpath");
			
			
			if(severity.equals("Clear") && state.equals("Closed"))
				Reporter.reportStep("Step 12: The 'Alert Severity': "+severity +" 'Alert State': "+ state +" could be matched successfully","SUCCESS");
			else
				Reporter.reportStep("Step 12: The 'Alert Severity': "+ severity +" 'Alert State': "+ state +" could not be matched","WARNING");
				
			snW.Wait(2000);
			// Step 9: Log out
			// go out of the frame
			snW.switchToDefault();

			if(snW.clickByXpath("Logout_Xpath"))

				Reporter.reportStep("Step 13: The Log out is clicked successfully.","SUCCESS");
			else
				Reporter.reportStep("Step 13: The logout Failed", "FAILURE");		


			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "OD_Stry0010139_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("OD_Stry0010139_Tc01");
		return arrayObject;
	}



}