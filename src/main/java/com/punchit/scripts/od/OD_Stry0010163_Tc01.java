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

public class OD_Stry0010163_Tc01 extends SuiteMethods {

	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "OD_Stry0010163_Tc01")
	public void testName(String oprRegUser, String oprRegPwd, String reaType, 
						 String callerName, String assignGrp,
						 String usrRegUser, String usrRegPwd ) {

		try {

			// Pre-requisities 
			snW = new ServiceNowWrappers(entityId);

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Log in to application
			if (snW.login(oprRegUser, oprRegPwd))
				Reporter.reportStep("Step 1: The login with username:"
						+ oprRegUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"
						+ oprRegUser + " is not successful", "FAILURE");

			// Step 2: Open Alert Console under user consoles
			if(snW.selectMenu("Ops_Director","Ops_Consoles", "Alert_Console"))
				Reporter.reportStep("Step 2: The 'Alert Console' under OpsConsole - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The 'Alert Console' under OpsConsole - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");

			if(snW.getTextByXpath("ALERT_AlertId_Xpath").equals("No records to display")){
				status = "Insufficient Data";
				Reporter.reportStep("Step 3: There is no records to display for new Alerts","FAILURE");
			}

			String alertId = snW.getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
			String alertCI= snW.getTextByXpath("ALERTPROFILE_FirstProfile_Xpath");
			
			if(!snW.clickLink(alertCI, false))
				Reporter.reportStep("Step 3: 'Alert Profile' for Alert "+ alertId +" could not be selected","FAILURE");
			
			snW.Wait(5000);
			
			if(!snW.selectByVisibleTextByXpath("ALERT_ReationType_Xpath", "Create Incident 1"))
				Reporter.reportStep("Step 3: 'Create Incident' for Alert "+ alertId +" could not be selected","FAILURE");
			
			if(!snW.clickById("CIS_UpdateButton_Id"))
				Reporter.reportStep("Step 3: 'Update' for Alert "+ alertId +" could not be clicked","FAILURE");
				
			snW.Wait(5000);
			// Step 3: Right click a new alert and ‘Acknowledge’ the alert	
			if(!snW.clickLink(alertId, false))
				Reporter.reportStep("Step 3: The alert "+ alertId +" could not be clicked","FAILURE");

			// suppress alerts from Alert Profile
			if(snW.clickByXpath("Acknowledge_Xpath"))
				Reporter.reportStep("Step 3: Acknowledge for alert: "+ alertId +" could be clicked sucessfully","SUCCESS");
			else	
				Reporter.reportStep("Step 3: Acknowledge for alert: "+ alertId +" could not be clicked","FAILURE");
			
			if(snW.clickByXpath("RunReaction_Xpath"))
				Reporter.reportStep("Step 3: Run Reaction for alert: "+ alertId +" could be clicked sucessfully","SUCCESS");
			else	
				Reporter.reportStep("Step 3: Run Reaction for alert: "+ alertId +" could not be clicked","FAILURE");

			String incidentNum=snW.getAttributeByXpath("CREATEINC_IncidentNumber_Xpath", "value");
			
			if(snW.enterAndChoose("CREATEINC_AffectedUser_Xpath", callerName))
				Reporter.reportStep("Step 8: 'Caller Name' "+ callerName +" could be entered","SUCCESS");
			else
				Reporter.reportStep("Step 8: 'Caller Name' "+ callerName +" could not be entered","FAILURE");

			if(snW.enterAndChoose("CREATEINC_AsgGroup_Xpath", assignGrp))
				Reporter.reportStep("Step 8: 'Assignment Group' "+ assignGrp +" could be entered","SUCCESS");
			else
				Reporter.reportStep("Step 8: 'Assignment Group' "+ assignGrp +" could not be entered","FAILURE");
		
			if(snW.clickById("CIS_UpdateButton_Id"))
				Reporter.reportStep("Step 9: 'Update' button could be clicked sucessfully", "SUCCESS");
			else
				Reporter.reportStep("Step 9: 'Update' button could not be clicked sucessfully","FAILURE");

			snW.Wait(2000);

			// go out of the frame
			snW.switchToDefault();

			// Log out
			if(snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("Step 17: The Log out is for username "+ oprRegUser +" clicked successfully.","SUCCESS");
			else
				Reporter.reportStep("Step 17: The logout for username "+ oprRegUser +" Failed", "FAILURE");		
			
			if (snW.login(usrRegUser, usrRegPwd))
				Reporter.reportStep("Step 1: The login with username:"
						+ usrRegUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"
						+ usrRegUser + " is not successful", "FAILURE");
			snW.Wait(5000);
			//Step 14: Click Incidents under Servicedesk
			if(snW.selectMenuFromMainHeader("Self_Service", "Incidents"))
				Reporter.reportStep("Step 14: The 'Incidents' under Self Service - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 14: The 'Incidents' under Self Service - menu could not be selected","FAILURE");
			
			//Step 15: Open the incident record from Step 9
			snW.switchToFrame("Frame_Main");
			
			if(snW.clickLink(incidentNum, false))
				Reporter.reportStep("Step 15: The 'Incident record' from Step 9 "+ incidentNum +" could be clicked successfully", "SUCCESS");
			else
				Reporter.reportStep("Step 15: The 'Incident record' from Step 9 "+ incidentNum +" could not be clicked","FAILURE");

			if(!snW.enterByXpath("CREATEINC_CustomerComms_Xpath", "Resolved the Incident"))
				Reporter.reportStep("Step 15: The 'Additional comments' could not be entered","FAILURE");

			if(snW.clickById("Incident_ResolInci_Id"))	
				Reporter.reportStep("Step 15: The 'Resolve Incident' could be clicked successfully", "SUCCESS");
			else
				Reporter.reportStep("Step 15: The 'Resolve Incident' could not be clicked","FAILURE");
			
			if(snW.clickLink(incidentNum, false))
				Reporter.reportStep("Step 15: The 'Incident record' same "+ incidentNum +" could be clicked successfully", "SUCCESS");
			else
				Reporter.reportStep("Step 15: The 'Incident record' same "+ incidentNum +" could not be clicked","FAILURE");
	
			if(snW.clickById("Incident_CloseInci_Id"))	
				Reporter.reportStep("Step 15: The 'Close Incident' could be clicked successfully", "SUCCESS");
			else
				Reporter.reportStep("Step 15: The 'Close Incident' could not be clicked","FAILURE");
			
			//go out of the frame
			snW.switchToDefault();
			
			// Log out
			if(snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("Step 17: The Log out is for username "+ usrRegUser +" clicked successfully.","SUCCESS");
			else
				Reporter.reportStep("Step 17: The logout for username "+ usrRegUser +" Failed", "FAILURE");		
			
			if (snW.login(oprRegUser, oprRegPwd))
				Reporter.reportStep("Step 1: The login with username:"
						+ oprRegUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"
						+ oprRegUser + " is not successful", "FAILURE");
			snW.Wait(5000);
			//Step 14: Click Incidents under Servicedesk
			if(snW.selectMenu("Ops_Director","Ops_Consoles", "Alerts_By_State"))
				Reporter.reportStep("Step 14: The 'Incidents' under Self Service - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 14: The 'Incidents' under Self Service - menu could not be selected","FAILURE");
			
			
			snW.switchToFrame("Frame_Main");
			
			if(!snW.selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
				Reporter.reportStep("Step 6A: 'Number' could not be selected in Goto section, hence failure","FAILURE");

			if(!snW.enterByXpathAndClick("CIS_SearchReferenceData_Xpath", alertId))
				Reporter.reportStep("Step 6A: Closed Incident: "+ alertId +" could not be entered for search, hence failure","FAILURE");

			if(snW.clickLink(alertId, false))
				Reporter.reportStep("Step 15: The 'Alert record' for same alert: "+ alertId +" could be clicked successfully", "SUCCESS");
			else
				Reporter.reportStep("Step 15: The 'Alert record' for same alert: "+ alertId +" could not be clicked","FAILURE");
			
			if(snW.isExistByXpath("INCIDENT_WorkNotes_Xpath"))
				Reporter.reportStep("Step 16: Activity Section  Worknotes as  could be matched sucessfully", "SUCCESS");
			else
				Reporter.reportStep("Step 16: Activity Section  Worknotes as colud not be matched","FAILURE");
			
			snW.Wait(2000);

			// go out of the frame
			snW.switchToDefault();

			// Log out
			if(snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("Step 17: The Log out is clicked successfully.","SUCCESS");
			else
				Reporter.reportStep("Step 17: The logout Failed", "FAILURE");		


			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "OD_Stry0010163_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("OD_Stry0010163_Tc01");
		return arrayObject;
	}



}