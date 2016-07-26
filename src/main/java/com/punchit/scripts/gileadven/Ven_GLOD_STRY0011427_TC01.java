package com.punchit.scripts.gileadven;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_STRY0011427_TC01 extends SuiteMethods {

	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "Ven_GLOD_STRY0011427_TC01")
	public void testName(String regUser, String regPwd,String f2Section, 
							String f2Value,String oprRegUser, String oprRegPwd, String callerName, String assignment,
							String comments) throws InterruptedException {

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

			//Step 2: Click on Alert Console menu
			if (snW.selectMenu("Ops_Director","Ops_Consoles", "ALERT_CONSOLE"))
				Reporter.reportStep("Step 2: The Alert Console - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Alert Console - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			
			// Step 3: Clicking on new alert 
						if(!snW.clickLink("NEW_ALERTS_LINK"))
						    Reporter.reportStep("Step 3: New alert link could be clicked","FAILURE");
						
						// Setting filter condition
						if(!snW.clickById("ALERT_FunnelIcon_Id"))
							Reporter.reportStep("Step 3: Filter icon couldn't be clicked","FAILURE");
						 snW.Wait(2000);
						// snW.clickByXpath("ALERT_Actionsandselcet_Xpath");
						if(!snW.clickByXpath("Runbook_Asignedtome_Filter_AND_Xpath"))
							Reporter.reportStep("Step 3: AND button couldn't be clicked","FAILURE");
						
						if(!snW.selectByVisibleTextByXpath("NEW_ALERT_FirstFilterType2_Xpath",f2Section))
							Reporter.reportStep("Step 3: CI Filter section  could not be selected","FAILURE");
					          
						snW.pressKey(Keys.TAB);
						snW.Wait(1000);
						/*if(!snW.sendKey(f2Condition))
						    Reporter.reportStep("Step 3: Filter condition could not be selected","FAILURE");
					   */ 
						snW.pressKey(Keys.TAB);
					    snW.Wait(1000);
					    if(!snW.sendKey(f2Value))
					       Reporter.reportStep("Step 3: Filter value could not be selected","FAILURE");
					    snW.Wait(1000);
						
					    if(snW.clickById("MY_ALERT_RUN_Buttn_ID"))
					    	Reporter.reportStep("Step 3: Filter set successfully","SUCCESS");
					    else
					    	Reporter.reportStep("Step 3: Run button could not be selected","FAILURE");
						snW.Wait(3000);
						switchToFrame("Frame_Main");
					
						if(isExistByXpath("NoRecords_xpath")){
						//if(isExistByXpath("//*[text()='No records to display']", false)){		
							status="Insuffient Data";
							Reporter.reportStep("Insufficient Data, hence failure.", "INSUFFICIENT DATA");}

						
						//Step 4:Capturing the first element value and acknowledging
						String numberAlertText = snW.getTextByXpath("Paralt_xpath");
						System.out.println(numberAlertText);
						if (!snW.rightClickByXpath("Paralt_xpath"))
						{
							status="INSUFFICIENT DATA";
							Reporter.reportStep("Step 4: Right Click Unsuccessful", "FAILURE");
						}

						if(snW.clickByXpath("AlertConsole_Rightclick_selectAcknowledge_xpath"))
							Reporter.reportStep("Step 4: Acknowledge option selected sucessfully", "SUCCESS");
						else
							Reporter.reportStep("Step 4: Acknowledge option could not be selected", "FAILURE");
						
						snW.switchToFrame("Frame_Nav");
						
						// Step 5: Selecting My alerts from my alert menu
						if (snW.selectMenu("Ops_Director","Ops_Consoles", "My_Alerts"))
							Reporter.reportStep("Step 5: My Alerts - menu selected successfully","SUCCESS");
						else
							Reporter.reportStep("Step 5: My Alerts - menu could not be selected","FAILURE");
						
						snW.switchToFrame("Frame_Main");
						
						// Step 6: CLicking on my alert link
						if(snW.clickLink("MY_ALERTS_LINK"))
							Reporter.reportStep("Step 6: My Alerts link selected successfully","SUCCESS");
						else
						    Reporter.reportStep("Step 6: My Alerts lik culd not be clicked","FAILURE");
						
						// Step 7: Search the acknowledged alert number in the search box
						snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Number");
						if(!snW.enterAndChoose("MyAlerts_Number_Search_Xpath", numberAlertText))
							Reporter.reportStep("Step 7: Acknowleged alert number could not be filled in the search box","FAILURE");
						snW.Wait(2000);
						
					    //Step 7: selecting the acknowledged alert
						if(snW.clickByXpath("MyAlerts_SelectedAcknowledgedAlert_Xpath"))
							Reporter.reportStep("Step 7: Acknowledged Alert found in My Alerts", "SUCCESS");
						else
							Reporter.reportStep("Step 7: Acknowledged Alert not found in My Alerts", "FAILURE");
						snW.Wait(2000);
						
/*						//Step 8:Verifying the reaction type as create incident
						String reactionTypeText=snW.getAttributeByXpath("MyAlerts_ReactionType_Xpath","value");
						System.out.println(reactionTypeText);
						if(reactionTypeText.equalsIgnoreCase("Create Incident"))
							Reporter.reportStep("Step 8: Reaction Type is Create Incident", "SUCCESS");
						else
							Reporter.reportStep("Step 8: Reaction Type is other than Create Incident", "Failure");
	*/					
						//Step 8: Clicking on Run Reaction button
						if(snW.clickByXpath("MyAlerts_RunReactionButton_Xpath"))
							Reporter.reportStep("Step 8: Run Reaction Button clicked successfully", "SUCCESS");
						else
							Reporter.reportStep("Step 8: Run Reaction Button not clicked", "FAILURE");
						
						//Step 9: Note Incident Number and click Update on Incident form to save the incident.
						String incidentNum=snW.getAttributeByXpath("CREATEINC_IncidentNumber_Xpath", "value");

						//Step 9: On the Caller field, key in user name from Reference Data and click sumbit/update
						if(snW.enterAndChoose("CREATEINC_AffectedUser_Xpath", callerName))
							Reporter.reportStep("Step 9: 'Caller Name' callerName could be entered","SUCCESS");
						else
							Reporter.reportStep("Step 9: 'Caller Name' could not be entered","FAILURE");
			            
						//Step 10: Filling in the assignment group
						if(!snW.enterAndChoose("MyAlerts_AssignmentGroup_Xpath", assignment))
							Reporter.reportStep("Step 10: Assignment group could not be entered","FAILURE");
						
						//Step 10: Note Incident Number and click Update on Incident form to save the incident.
						//String incidentNum=snW.getAttributeByXpath("CREATEINC_IncidentNumber_Xpath", "value");

						if(snW.clickById("CIS_UpdateButton_Id"))
							Reporter.reportStep("Step 10: Update button could be clicked sucessfully", "SUCCESS");
						else
							Reporter.reportStep("Step 10: Update button could not be clicked sucessfully","FAILURE");

						snW.Wait(2000);
						snW.switchToDefault();
						// Step 11: Logout  
						if(snW.clickByXpath("Logout_Xpath"))
							Reporter.reportStep("Step 11: The Log out is clicked successfully.","SUCCESS");
						else
							Reporter.reportStep("Step 11: The logout Failed", "FAILURE");		

						// Step 12: Log in to application
						if (snW.login(oprRegUser, oprRegPwd))
							Reporter.reportStep("Step 12: The login with username:"
									+ oprRegUser + " is successful", "SUCCESS");
						else
							Reporter.reportStep("Step 12: The login with username:"
									+ oprRegUser + " is not successful", "FAILURE");
						snW.Wait(2000);
						//Step 13: Click Incidents under Self_Service
						if(snW.selectMenuFromMainHeader("Self_Service", "Incidents"))
							Reporter.reportStep("Step 13: The 'Incidents' under Self_Service  - menu selected successfully","SUCCESS");
						else
							Reporter.reportStep("Step 13: The 'Incidents' under Self_Service - menu could not be selected","FAILURE");
						
						
						//Step 14: Open the incident record from Step 9
						snW.switchToFrame("Frame_Main");
						
						snW.selectByVisibleTextByXpath("Runbook_SearchKey_Xpath", "Number");
						if(!snW.enterAndChoose("MyAlerts_Number_Search_Xpath", incidentNum))
							Reporter.reportStep("Step 14: Incident number could not be filled in the search box","FAILURE");
						snW.Wait(2000);
						
						if(snW.clickByXpath("Alert_Profiles_LastCreatedProfile_Xpath"))
							Reporter.reportStep("Step 14: The Incident record clicked successfully", "SUCCESS");
						else
							Reporter.reportStep("Step 14: The Incident record could not be clicked","FAILURE");
						
			// Filling in the additional comment
						
					if(snW.enterAndChoose("Incident_Comment_Xpath", comments))
						Reporter.reportStep("Step 15: Incident comment entered sucessfully", "SUCCESS");
					else
						Reporter.reportStep("Step 15: Incident comment could not be entered","FAILURE");
			
					// clicking on reolve button
					
					if(snW.clickByXpath("ResolveIncident_Xpath"))
						Reporter.reportStep("Step 16: Resolve button clicked sucessfully", "SUCCESS");
					else
						Reporter.reportStep("Step 16: Resolve button could not be clicked","FAILURE");
			
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

				
	     @DataProvider(name = "Ven_GLOD_STRY0011427_TC01")
		    public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider
				.getSheet("Ven_GLOD_STRY0011427_TC01");
					return arrayObject;
	}
}
