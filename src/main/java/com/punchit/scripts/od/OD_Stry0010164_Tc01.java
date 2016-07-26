package com.punchit.scripts.od;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;




import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

// for using ATU reporting -- added the listeners

public class OD_Stry0010164_Tc01 extends SuiteMethods {

	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "OD_Stry0010164_Tc01")
	public void testName(String regUser, String regPwd, String reaType, 
						 String callerName, String closeMess,
						 String wrkNotes,String f2Section,String f3Section, 
							String f2Value,String assignment ) throws InterruptedException {

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
		          
			snW.Wait(2000);
			if(!snW.selectByVisibleTextByXpath("NEW_ALERT_FirstFilterType2_Xpath",f3Section))
				Reporter.reportStep("Step 3: second CI Filter section  could not be selected","FAILURE");
		     
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
		    
			//Step 4:Capturing the first element value and acknowledging
			String numberAlertText = snW.getTextByXpath("Paralt_xpath");
			System.out.println(numberAlertText);
			if (!snW.rightClickByXpath("Paralt_xpath"))
				Reporter.reportStep("Step 4a: Acknowledge option selected sucessfully", "SUCCESS");
			else
			{
				status="INSUFFICIENT DATA";
				Reporter.reportStep("Step 4a: Right Click Unsuccessful", "FAILURE");
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
			
			//Step 8:Verifying the reaction type as create incident
			String reactionTypeText=snW.getAttributeByXpath("MyAlerts_ReactionType_Xpath","value");
			System.out.println(reactionTypeText);
			if(reactionTypeText.equalsIgnoreCase("Create Incident"))
				Reporter.reportStep("Step 8: Reaction Type is Create Incident", "SUCCESS");
			else
				Reporter.reportStep("Step 8: Reaction Type is other than Create Incident", "Failure");
			
			//Step 9: Clicking on Run Reaction button
			if(snW.clickByXpath("MyAlerts_RunReactionButton_Xpath"))
				Reporter.reportStep("Step 9: Run Reaction Button clicked successfully", "SUCCESS");
			else
				Reporter.reportStep("Step 9: Run Reaction Button not clicked", "FAILURE");
			
			//Step 10: On the Caller field, key in user name from Reference Data and click sumbit/update
			if(snW.enterAndChoose("CREATEINC_AffectedUser_Xpath", callerName))
				Reporter.reportStep("Step 10: Caller Name : " + callerName +"  is entered successfully","SUCCESS");
			else
				Reporter.reportStep("Step 10: Caller Name could not be entered","FAILURE");
            
			//Step 11: Filling in the assignment group
			if(!snW.enterAndChoose("MyAlerts_AssignmentGroup_Xpath", assignment))
				Reporter.reportStep("Step 11: Assignment group could not be entered","FAILURE");
			
			//Step 11: Note Incident Number and click Update on Incident form to save the incident.
			String incidentNum=snW.getAttributeByXpath("CREATEINC_IncidentNumber_Xpath", "value");

			if(snW.clickById("CIS_UpdateButton_Id"))
				Reporter.reportStep("Step 11: Update button is clicked sucessfully", "SUCCESS");
			else
				Reporter.reportStep("Step 11: Update button could not be clicked sucessfully","FAILURE");

			snW.Wait(2000);
			//Step 12: Go to back to Alert Console, right click on the alert used in step 5 and choose ‘Close ‘ from drop down menu
			if(!snW.selectMenu("Ops_Director","Ops_Consoles", "My_Alert_Console"))
				Reporter.reportStep("Step 12: The My Alerts under OpsConsole - menu could not be selected","FAILURE");
			
			snW.switchToFrame("Frame_Main");
			
			if(!snW.clickLink("MY_ALERTS_LINK"))
			     Reporter.reportStep("Step 12: My Alerts lik could not be clicked","FAILURE");
			
			snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Number");
			if(!snW.enterAndChoose("MyAlerts_Number_Search_Xpath", numberAlertText))
				Reporter.reportStep("Step 12: Acknowleged alert number could not be filled in the search box","FAILURE");
			snW.Wait(2000);
			
			if(snW.clickByXpath("ALERT_AllAlertsChkBox_Xpath"))
				Reporter.reportStep("Step 12: All alerts checkbox is checked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 12: All alerts checkbox could not be checked","FAILURE");
			
			snW.clickByXpath("ALERT_AllAlertsSelect_Xpath");		
			Thread.sleep(2000);
			if(snW.selectByVisibleTextByXpath("ALERT_AllAlertsSelect_Xpath","   Close..."))
				Reporter.reportStep("Step 13: The close option is selected as expected","SUCCESS");
			else
			    Reporter.reportStep("Step 13: The close option could not be selected; hence failed","FAILURE");
		  
		     snW.getDriver().switchTo().activeElement();
			//Step 14: Enter closure remark as “Attempting to close alert with incident still open
			if(snW.enterById("close_Id", closeMess))
				Reporter.reportStep("Step 14: Closure Message is entered successfully","SUCCESS");
			else	
				Reporter.reportStep("Step 14: Closure Remark as "+ closeMess +"could not be entered","FAILURE");
			
			if(snW.clickById("Ok_Id"))
				Reporter.reportStep("Step 15: OK button is clicked successfully.","SUCCESS");
			else
				Reporter.reportStep("Step 15: OK button could not be clicked.","FAILURE");
			
		   //Step 16: Click Incidents under Servicedesk
			if(snW.selectMenuFromMainHeader("Service_Desk", "Incidents"))
				Reporter.reportStep("Step 16: The Incidents under Service Desk - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 16: The Incidents under Service Desk - menu could not be selected","FAILURE");
			
			//Step 17: Open the incident record from Step 9
			snW.switchToFrame("Frame_Main");
			
			snW.selectByVisibleTextByXpath("Runbook_SearchKey_Xpath", "Number");
			if(!snW.enterAndChoose("MyAlerts_Number_Search_Xpath", incidentNum))
				Reporter.reportStep("Step 17: Incident number could not be filled in the search box","FAILURE");
			snW.Wait(2000);
			
			if(snW.clickByXpath("Alert_Profiles_LastCreatedProfile_Xpath"))
				Reporter.reportStep("Step 17: The Incident record created in  Step 12 is clicked successfully", "SUCCESS");
			else
				Reporter.reportStep("Step 17: The Incident record from Step 12 "+ incidentNum +" could not be clicked","FAILURE");
			
		//Step 18: Scroll down to Activity Section and look for an activity entry indicating worknotes as Related alert has been closed
			snW.scrollToElementByXpath("INCIDENT_WorkNotes_Xpath", 0, 4000);
			String workNote=snW.getTextByXpath("INCIDENT_WorkNotes_Xpath");
			System.out.println(workNote);
			if(workNote.contains(wrkNotes))
				Reporter.reportStep("Step 18: Activity Section  Worknotes as : "+ wrkNotes +" is  matched sucessfully", "SUCCESS");
			else
				Reporter.reportStep("Step 18: Activity Section  Worknotes as "+ wrkNotes +" colud not be matched","FAILURE");
			
			// go out of the frame
			snW.switchToDefault();

			// Log out
			if(snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("Step 19: The Log out is clicked successfully.","SUCCESS");
			else
				Reporter.reportStep("Step 19: The logout Failed", "FAILURE");		


			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "OD_Stry0010164_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("OD_Stry0010164_Tc01");
		return arrayObject;
	}



}