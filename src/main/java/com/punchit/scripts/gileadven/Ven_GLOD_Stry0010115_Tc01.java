package com.punchit.scripts.gileadven;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_Stry0010115_Tc01 extends SuiteMethods {
	// Create Instance
		ServiceNowWrappers snW;

		@Test(dataProvider = "Ven_GLOD_Stry0010115_Tc01")
		public void testName(String regUser, String regPwd, String reactionType, String alertRecordState,
				String relTaskState,String f2Section,String f3Section, 
				String f2Value) throws InterruptedException {

			try {

				// Pre-requisities 
				snW = new ServiceNowWrappers(entityId);

				// Step 0: Launch the application
				snW.launchApp(browserName, true);

				// Step 1: Log in to application
				if (snW.login(regUser, regPwd))
					Reporter.reportStep("The login with username:"
							+ regUser + " is successful", "SUCCESS");
				else
					Reporter.reportStep("The login with username:"
							+ regUser + " is not successful", "FAILURE");

				
				// Step 2: Open Alert Console under user consoles
				if(snW.selectMenu("Ops_Consoles", "Alert_Console"))
					Reporter.reportStep("The 'Alert Console' under OpsConsole - menu selected successfully","SUCCESS");
				else
					Reporter.reportStep("The 'Alert Console' under OpsConsole - menu could not be selected","FAILURE");

				// Switch to the main frame
				snW.switchToFrame("Frame_Main");
				
				// Step 3: Clicking on new alert 
				if(!snW.clickLink("NEW_ALERTS_LINK"))
				    Reporter.reportStep("New alert link could be clicked","FAILURE");
				
				// Setting filter condition
				if(!snW.clickById("ALERT_FunnelIcon_Id"))
					Reporter.reportStep("Filter icon couldn't be clicked","FAILURE");
				
				snW.Wait(2000);
				// snW.clickByXpath("ALERT_Actionsandselcet_Xpath");
				if(!snW.clickByXpath("Runbook_Asignedtome_Filter_AND_Xpath"))
					Reporter.reportStep("AND button couldn't be clicked","FAILURE");
				
				//Thread.sleep(3000);
				//snW.clickByXpath("NEW_ALERT_FirstFilterType2_Xpath");
				if(!snW.selectByVisibleTextByXpath("NEW_ALERT_FirstFilterType2_Xpath",f2Section))
					Reporter.reportStep("CI Filter section  could not be selected","FAILURE");
			          
				snW.Wait(2000);
				if(!snW.selectByVisibleTextByXpath("NEW_ALERT_FirstFilterType2_Xpath",f3Section))
					Reporter.reportStep("second CI Filter section  could not be selected","FAILURE");
			     
				snW.pressKey(Keys.TAB);
				snW.Wait(1000);
			    
				snW.pressKey(Keys.TAB);
			    snW.Wait(1000);
			    if(snW.sendKey(f2Value))
			        Reporter.reportStep("Filter is set successfully ", "SUCCESS");
			    else
			        Reporter.reportStep("Filter value could not be selected","FAILURE");
			    snW.Wait(1000);
				
			    if(!snW.clickById("MY_ALERT_RUN_Buttn_ID"))
			    	Reporter.reportStep("Run button could not be selected","FAILURE");
				snW.Wait(3000);
			    
				//Step 4:Capturing the first element value and acknowleding
				String numberAlertText = snW.getTextByXpath("Paralt_xpath");
				System.out.println(numberAlertText);
				if (!snW.clickByXpath("Paralt_xpath"))
				{
					status="INSUFFICIENT DATA";
					Reporter.reportStep("Right Click Unsuccessful", "FAILURE");
				}

				// Click on Acknowledge
				if(!snW.clickByXpath("Ackhowledge_button_Xpath"))
					Reporter.reportStep("Acknowledge button could not be clicked","FAILURE");
				
				//Validating reaction type is run runbook
				String RunRunbook = snW.getAttributeByXpath("Reaction_Type_Xpath", "value");
				
				if(RunRunbook.equalsIgnoreCase(RunRunbook))
					Reporter.reportStep("Reaction type is run runbook","SUCCESS");
				else
					Reporter.reportStep("Reaction type is not run runbook","FAILURE");
				
				//Step : 4 Clicking on run reaction button
				if(snW.clickByXpath("MyAlerts_RunReactionButton_Xpath"))
					Reporter.reportStep("Run Reaction is Clicked sucessfully","SUCCESS");
				else	
					Reporter.reportStep("Run Reaction could not be Clicked ","FAILURE");
				
				snW.Wait(2000);

				//Step : 5 Validate that the RunBook is launched, Note the RunBook number. 
				snW.Wait(5000);	
				String runreactionnum=snW.getTextByXpath("RunReaction_Text_Xpath");
				System.out.println(runreactionnum);
				String RunbookNum = runreactionnum.substring(8, 17);
				System.out.println(RunbookNum);
				
				snW.switchToFrame("Frame_Nav");
				
				// Step 5: Selecting My alerts from my alert menu
				if (snW.selectMenu("Ops_Director","Ops_Consoles", "My_Alerts"))
					Reporter.reportStep("My Alerts - menu selected successfully","SUCCESS");
				else
					Reporter.reportStep("My Alerts - menu could not be selected","FAILURE");
				
				snW.switchToFrame("Frame_Main");
				
				// Step 6: CLicking on my alert link
				if(snW.clickLink("MY_ALERTS_LINK"))
					Reporter.reportStep("My Alerts link selected successfully","SUCCESS");
				else
				    Reporter.reportStep("My Alerts link could not be clicked","FAILURE");
				
				// Step 7: Search the acknowledged alert number in the search box
				snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Number");
				if(!snW.enterAndChoose("MyAlerts_Number_Search_Xpath", numberAlertText))
					Reporter.reportStep("Acknowleged alert number could not be filled in the search box","FAILURE");
				snW.Wait(2000);
				
			    //Step 8: selecting the acknowledged alert
				if(snW.clickByXpath("MyAlerts_SelectedAcknowledgedAlert_Xpath"))
					Reporter.reportStep("Acknowledged Alert found in My Alerts", "SUCCESS");
				else
					Reporter.reportStep("Acknowledged Alert not found in My Alerts", "FAILURE");
				snW.Wait(2000);
				
				snW.scrollToElementByXpath("ALERTRECORD_AlertState_Xpath");
				//Step 9: Validate that Alert State is ‘In Progress’
				String alertState= snW.getDefaultValueByXpath("Alert_State1_Xpath");
				System.out.println(alertState);
				if(alertState.equalsIgnoreCase("In progress"))
					Reporter.reportStep("Alert state is in progress","SUCCESS");
				else
					Reporter.reportStep("Alert state is not in progress, hence failure","FAILURE");
				
				snW.Wait(3000);
				
				// Step 10:validate to search related task field
				if(!snW.scrollToElementByXpath("ALERTPROFILE_RelatedTask_Xpath"))
					Reporter.reportStep("Related task tab could not be found ","FAILURE");
				//Step 10: Validate that runbook is present in related task field
				if(!snW.selectByVisibleTextByXpath("My_Alert_Relatedtask_SearchBox_Xpath", "Task"))
					Reporter.reportStep("Searchbox could not be found ","FAILURE");
				
				if(!snW.enterAndChoose("My_Alert_Relatedtask_SearchValue_Xpath", RunbookNum))
					Reporter.reportStep("Value could not be entered in the search value","FAILURE");
				
				if(!snW.isExistByXpath("Relatedtask_FirstRunbook_Xpath", true))
					Reporter.reportStep("Runbook doesn't exist, hence fail","FAILURE");
				
				// Step10 Searching the runbook in the related task field
				String RelatedTaskrunbook = snW.getTextByXpath("Relatedtask_FirstRunbook_Xpath");
				    if(RelatedTaskrunbook.equalsIgnoreCase(RunbookNum))
				    	Reporter.reportStep("Runbook number is same as created in above step ","SUCCESS");
					else
						Reporter.reportStep("Runbook is not present, hence failed", "FAILURE");		
	            
				String State= snW.getTextByXpath("Relatedtask_RunbookState_Xpath");
				if(State.equalsIgnoreCase("Open"))
			    	Reporter.reportStep("Runbook is in open state ","SUCCESS");
				else
					Reporter.reportStep("Runbook is not in open state, hence failed", "FAILURE");		
	        

	               	// go out of the frame
							snW.switchToDefault();

				// Log out
				if(snW.clickByXpath("Logout_Xpath"))
					Reporter.reportStep("The Log out is clicked successfully.","SUCCESS");
				else
					Reporter.reportStep("The logout Failed", "FAILURE");		


			status = "PASS";
				
			} finally {
				// close the browser
				snW.quitBrowser();
			}

		}

		@DataProvider(name = "Ven_GLOD_Stry0010115_Tc01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider
					.getSheet("Ven_GLOD_Stry0010115_Tc01");
			return arrayObject;
		}



	}

