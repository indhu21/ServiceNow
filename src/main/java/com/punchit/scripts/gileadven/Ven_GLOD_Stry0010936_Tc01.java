package com.punchit.scripts.gileadven;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_Stry0010936_Tc01 extends SuiteMethods {

	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "Ven_GLOD_Stry0010936_Tc01")
	public void testName(String devRegUser, String devRegPwd, String label, 
			String order, String decoratorType, String recurBehav,
			String operator, String modification, String oprRegUser,
			String oprRegPwd, String sceData) {

		try {

			// Pre-requisities 
			snW = new ServiceNowWrappers(entityId);
			
			// Step 0: Launch the application
			snW.launchApp(browserName, true);
			

			// Step 1: Log in to application
			if (snW.login(devRegUser, devRegPwd))
				Reporter.reportStep("Step 1: The login with username:"
						+ devRegUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"
						+ devRegUser + " is not successful", "FAILURE");


			// Step 2: In Ops Director>Administration click on Alert Enrichers
			if(snW.selectMenu("Ops_Director","Administration", "Alert_Enrichers"))
				Reporter.reportStep("Step 2: The Alert_Enrichers under Administration - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Alert_Enrichers under Administration - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");

			//Step 3: Click on New
			if(snW.clickById("New_Button"))
				Reporter.reportStep("Step 3: The New Button in Alert Enrichers is  clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 3: The New Button  in Alert Enrichers could not be clicked","FAILURE");

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy-h-mm-ss");
			String formattedDate = sdf.format(date);	
			String labelTm = label+" - "+formattedDate;

			//Step 4: Fill the form with reference data 
			if(!snW.enterById("ALERT_Label_Id", labelTm))
				Reporter.reportStep("Step 4: The Label: "+ labelTm +" in Alert Enrichers could not be entered","FAILURE");

			if(!snW.clickById("ALERT_Active_Id"))
				Reporter.reportStep("Step 4: The Active in Alert Enrichers could not be clicked","FAILURE");

			if(!snW.enterById("ALERT_Order_Id", order))
				Reporter.reportStep("Step 4: The Order: "+ order +" in Alert Enrichers could not be entered","FAILURE");

			if(!snW.selectByVisibleTextById("ALERT_DecoratorType_Id", decoratorType))
				Reporter.reportStep("Step 4: The Decorator Type: "+ decoratorType +" in Alert Enrichers could not be selected","FAILURE");

			if(!snW.selectByVisibleTextById("ALERT_RecurrenceBehaviour_Id", recurBehav ))
				Reporter.reportStep("Step 4: The Recurrence Behaviour: "+ recurBehav +" in Alert Enrichers could not be selected","FAILURE");

			snW.Wait(2000);

/*			if(!snW.addNewFilter("Alert Attribute", "contains", "Node Status"))
				Reporter.reportStep("Step 4: The 'Alert Attribute contains Node Status' could not be selected","SUCCESS");

*/			
			if(!snW.selectByVisibleTextById("ALERT_Operator_Id", operator))
				Reporter.reportStep("Step 4: The 'Operator': "+ operator +" in Alert Enrichers could not be selected","FAILURE");

			if(snW.enterById("ALERT_Modification_Id", modification))
				Reporter.reportStep("Step 4: The 'Modification': "+ modification +" in Alert Enrichers could be entered","SUCCESS");
			else
				Reporter.reportStep("Step 4: The 'Modification': "+ modification +" in Alert Enrichers could not be entered","FAILURE");
			
			//Step 5: Click Submit to save to Enricher
			if(snW.clickById("Submit_Id"))
				Reporter.reportStep("Step 5: The 'Submit Button' in Alert Enrichers could be clicked sucessfully","SUCCESS");
			else
				Reporter.reportStep("Step 5: The 'Submit Button' in Alert Enrichers could not be clicked ","FAILURE");

			// Step 6: Log out
			// go out of the frame
			snW.switchToDefault();

			if(snW.clickByXpath("Logout_Xpath"))

				Reporter.reportStep("Step 6: The Log out is clicked successfully.","SUCCESS");
			else
				Reporter.reportStep("Step 6: The logout Failed", "FAILURE");		

			
//			// Step 7: Log in to application
//			if (snW.login(oprRegUser, oprRegPwd))
//				Reporter.reportStep("Step 7: The login with username:"
//						+ oprRegUser + " is successful", "SUCCESS");
//			else
//				Reporter.reportStep("Step 7: The login with username:"
//						+ oprRegUser + " is not successful", "FAILURE");
//			
//			//Step 8: Navigate to Ops Director Testing in the application Navigator
//			snW.Wait(5000);
//			if(snW.selectMenuFromMainHeader("Ops_Director_Testing", "Scenarios"))
//				Reporter.reportStep("Step 8: The 'Scenarios' under Ops Director Testing - menu selected successfully","SUCCESS");
//			else
//				Reporter.reportStep("Step 8: The 'Scenarios' under Ops Director Testing - menu could not be selected","FAILURE");
//
//			snW.switchToFrame("Frame_Main");
//
//			//Step 7: Run a Scenario from reference data
///*			if(!snW.clickLink(sceData, false))
//				Reporter.reportStep("Step 8: The "+ sceData +" under Scenarios could not be clicked","FAILURE");
//			
//*/			if(!snW.enterAndChoose("Scenario_Search_Xpath", sceData))
//	          Reporter.reportStep("Step 9: The "+ sceData +" under Scenarios could not be clicked","FAILURE");
//
//			snW.Wait(2000);
//			if(snW.clickByXpath("Select_alert"))
//				Reporter.reportStep("Step 9: New York Datacentre Unreachable scenario clicked sucessfully","SUCCESS");
//			else	
//				Reporter.reportStep("Step 9: New York Datacentre Unreachable scenariocould not be clicked","FAILURE");
//
//			
//			if(!snW.clickByXpath("SCENARIOS_PlayScenarios_Xpath"))
//				Reporter.reportStep("Step 10: The 'Play Scenarios' could not be clicked","FAILURE");
//			
//			snW.Wait(10000);
//			
//			
///*			if(snW.clickByXpath("SCENARIOS_Close_Xpath"))
//				Reporter.reportStep("Step 10: The 'Close Button' could be clicked sucessfully","SUCCESS");
//			else	
//				Reporter.reportStep("Step 10: The 'Close Button' could not be clicked","FAILURE");
//*/
//			snW.switchToFrame("Frame_Nav");
//
//			//Step 13: Click Incidents in the Application Navigator Search box 
//			if(snW.enterById("filter_Id", "Alert Console"))
//				Reporter.reportStep("Step 11: 'Alert Console' could be entered in filter box sucessfully","SUCCESS");
//			else	
//				Reporter.reportStep("Step 11: 'Alert Console' could not be entered in filter box","FAILURE");
//			
//			// Step 9: Open Alert Console under user consoles
//			if(snW.selectMenu("Ops_Director","Ops_Consoles", "Alert_Console"))
//				Reporter.reportStep("Step 12: The 'Alert Console' under OpsConsole - menu selected successfully","SUCCESS");
//			else
//				Reporter.reportStep("Step 12: The 'Alert Console' under OpsConsole - menu could not be selected","FAILURE");
//
//			snW.Wait(5000);
//			snW.switchToFrame("Frame_Main");
//
//			if(!snW.clickLink("NEW_ALERTS_LINK"))
//				Reporter.reportStep("Step 10: The 'New Alerts' could not be clicked","FAILURE");
//			
//			snW.Wait(5000);
//			if(!snW.clickById("ALERTPROFILE_FunnelIcon_Id"))
//				Reporter.reportStep("Step 10: The funnel icon could not be clicked","FAILURE");
//			snW.Wait(5000);
//			
//			if(!snW.deleteAllFilters())
//				Reporter.reportStep("Step 9: The 'All Filters' could not be removed","FAILURE");
//			snW.Wait(5000);
//			
//			if(!snW.addNewFilter("Number", "starts with", "ALT"))
//				Reporter.reportStep("Step 9: The 'New Filters' could not be selected","SUCCESS");
//			
//			snW.Wait(5000);
//			
//			if(!snW.clickByXpath("ALERT_RunFilter_Xpath"))
//				Reporter.reportStep("Step 9: 'Run' could not be clicked", "FAILURE");
//
//			snW.Wait(5000);			
//
//			
//			//Step 9: Select the top most alert
//			String alertId = snW.getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
//
//			if(snW.clickLink(alertId, false))
//				Reporter.reportStep("Step 10: The 'First Alert: ' "+ alertId +" could be selected","SUCCESS");
//			else
//				Reporter.reportStep("Step 10: The 'First Alert: ' "+ alertId +" could not be selected","FAILURE");
//
//			snW.Wait(5000);	
//			
//			String rate=snW.getAttributeByXpath("ALERTRECORD_Rating_Xpath", "value");
//			
//			rate=rate.replaceAll(",", "");
//			
//			System.out.println(rate);
//			
//			snW.Wait(5000);	
//
//			/*int rate1=Integer.parseInt(rate);
//			int mod=Integer.parseInt(modification);
//			
//			System.out.println(rate1);
//			System.out.println(mod);
//			*/
//			
//			if(rate.compareTo(modification)>=0)
//				Reporter.reportStep("Step 11: Alert Rate could be greater than "+ modification +" checking from reference data sucessfully","SUCCESS");
//			else
//				Reporter.reportStep("Step 11: Alert Rate could not be greater than "+ modification +" cheking from reference data","FAILURE");
//
//			// Step 9: Log out
//			// go out of the frame
//			snW.switchToDefault();
//
//			if(snW.clickByXpath("Logout_Xpath"))
//
//				Reporter.reportStep("Step 11: The Log out is clicked successfully.","SUCCESS");
//			else
//				Reporter.reportStep("Step 11: The logout Failed", "FAILURE");		
//

			status = "PASS";

		} 
		finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "Ven_GLOD_Stry0010936_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("Ven_GLOD_Stry0010936_Tc01");
		return arrayObject;
	}



}