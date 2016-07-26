package com.punchit.scripts.od;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class OD_STRY0010133_TC02 extends SuiteMethods {
	
		// Create Instance
		ServiceNowWrappers snW;

		@Test(dataProvider = "OD_STRY0010133_TC02",groups="OpsDirector")
		public void alertSuppressor (String regUser, String regPwd, String scheduleType,
				String shortDesc, String tablename, String document, 
				String description) throws COSVisitorException, IOException {
			
			// Prerequisites
			snW = new ServiceNowWrappers(entityId);
			
			try {

				// Step 0: Launch the application
				snW.launchApp(browserName, true);

				// Step 1: Log in to application
				if (snW.login(regUser, regPwd))
					Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
				else
					Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");
				
				if (snW.selectMenu("Ops_Director", "Administration", "Alert_Suppressors"))
					Reporter.reportStep("Step 2: The Alert Suppressors menu selected successfully","SUCCESS");
				else
					Reporter.reportStep("Step 2: The Alert Suppressors menu could not be selected","FAILURE");
				
				// Switch to the main frame
				snW.switchToFrame("Frame_Main");
				
				if (snW.clickById("New_Button"))
					Reporter.reportStep("Step 3: New button clicked successfully","SUCCESS");
				else
					Reporter.reportStep("Step 3: New button could not be clicked successfully","FAILURE");
				
				// Key in a name for Alert Suppressor. Use the Alert Profile Name appended by <timestame>-suppressor. For example Link Down 07102015 Suppressor
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
				String formattedDate = sdf.format(date);
				
				Calendar c = Calendar.getInstance();
				c.add(Calendar.MINUTE, 10);  // number of days to add
				String nextday = sdf.format(c.getTime());
				
				String supressName = "Punch Test 133 - "+formattedDate;
				
				if(!snW.enterById("ALERT_Suppress_Name_Id",supressName))
					Reporter.reportStep("Step 4: Name could not be entered","FAILURE");
				
				if(!snW.clickByXpath("AlertSuppressor_ActiveField_Xpath"))
					Reporter.reportStep("Step 4: Active could not be clicked","FAILURE");
							
				// Short Description:  Suppressing the Alert profile < profile name>
				if(!snW.enterById("ALERT_Suppress_ShortDesc_Id", shortDesc))
					Reporter.reportStep("Step 4: 'Short Description' could not be entered","FAILURE");
				
				// Suppression Type: CI Scope
				/*if(!snW.selectByVisibleTextById("ALERT_Supress_Sup_Type_Id", supresstype))
					Reporter.reportStep("Step 3: 'Suppression Type' could not be selected","FAILURE");*/
				
				// Suppression Target: Any record from CI
				if (!snW.clickById("ALERT_Supress_Sup_Type_Search_Id"))
					Reporter.reportStep("Step 4: Suppression Target search icon could not be clicked'","FAILURE");
				
				snW.Wait(2000);
				snW.getDriver().switchTo().activeElement();
				snW.Wait(1000);
				
				// Select table as CI
				if (!snW.selectByVisibleTextByXpath("ALERT_Sup_Tar_Table_Xpath", tablename))
					Reporter.reportStep("Step 4: Suppression Target table could not be selected","FAILURE");
				
				if (!snW.enterAndChoose("ALERT_Sup_Doc_Xpath", document))
					Reporter.reportStep("Step 4: Suppression Target document could not be selected","FAILURE");
				
				snW.Wait(3000);
				
				if (!snW.clickById("Ok_Id"))
					Reporter.reportStep("Step 4: 'OK' could not be clicked","FAILURE");
				
				snW.Wait(500);
				snW.switchToPrimary();
				snW.switchToDefault();
				snW.switchToFrame("Frame_Main");
				
				// Schedule Type: Run Once
				if(!snW.selectByVisibleTextById("ALERT_Suppress_Type_Id", scheduleType))
					Reporter.reportStep("Step 4: Alert Suppress 'Schedule Type' could not be selected","FAILURE");
				
				if(!snW.enterById("Sch_Start_Id", formattedDate))
					Reporter.reportStep("Step 4: Alert Suppress 'Scheduled Start' could not be entered","FAILURE");
							
				if(!snW.enterById("Sch_End_Id", nextday))
					Reporter.reportStep("Step 4: Alert Suppress 'Scheduled Stop' could not be entered","FAILURE");
				
				// and click submit button
				if(snW.clickById("Submit_Id"))
					Reporter.reportStep("Step 4: Alert Supression - Submit button clicked","SUCCESS");
				else
					Reporter.reportStep("Step 4: Alert Supression - Submit button could not be clicked","FAILURE");
							
				snW.Wait(5000);
							
				// Validate the record created in step 5 exist.
				String supname = "Name";
				if(!snW.selectByVisibleTextByXpath("Runbook_SearchKey_Xpath", supname))
					Reporter.reportStep("Step 5: Alert Supression - Search Key could not be selected","FAILURE");
				snW.Wait(500);
				if(!snW.enterAndChoose("CIS_SearchReferenceData_Xpath", supressName))
					Reporter.reportStep("Step 5: Alert Supression -" +supressName+ " could not be entered in search box","FAILURE");
				snW.Wait(5000);

				if(snW.getTextByXpath("ALERT_Sup_First_Prof_Xpath").equals(supressName))
					Reporter.reportStep("Step 5: The 'Alert Suppressors' successfully found the newly created record :"+supressName,"SUCCESS");
				else
					Reporter.reportStep("Step 5: The 'Alert Suppressors' could not find the newly created record :"+supressName,"FAILURE");
				
				snW.switchToFrame("Frame_Nav");
				
				if(snW.selectMenuFromMainHeader("Ops_Director_Testing", "Scenarios"))
					Reporter.reportStep("Step 6: The Scenarios menu selected successfully","SUCCESS");
				else
					Reporter.reportStep("Step 6: The Scenarios menu could not be selected","FAILURE");
				
				snW.Wait(3000);
				snW.switchToFrame("Frame_Main");
				String scenario = "Description";
				if(!snW.selectByVisibleTextByXpath("Runbook_SearchKey_Xpath", scenario))
					Reporter.reportStep("Step 7: Scenario - Search Key could not be selected","FAILURE");
				snW.Wait(500);
				if(!snW.enterAndChoose("Header_SearchBox_Xpath", description))
					Reporter.reportStep("Step 7: Scenario -" +description+ " could not be entered in search box","FAILURE");
				snW.Wait(3000);
				
				if(snW.getTextByXpath("First_Searched_Record_Xpath").equals(description))
					snW.clickByXpath("First_Searched_Record_Xpath");
				else
					Reporter.reportStep("Step 7: Scenario - "+description+ " is not found","FAILURE");
				
				snW.Wait(3000);
				if(snW.clickByXpath("PlayScenario_Button_Xpath"))
					Reporter.reportStep("Step 7: The Play Scenario button clicked successfully","SUCCESS");
				else
					Reporter.reportStep("Step 7: The Play Scenario button could not be clicked","FAILURE");
				
				snW.Wait(3000);
				snW.switchToFrame("Frame_Nav");
				if(snW.selectMenu("Ops_Director", "Data", "OD_Alerts_Menu"))
					Reporter.reportStep("Step 8: The Alerts menu selected successfully","SUCCESS");
				else
					Reporter.reportStep("Step 8: The Alerts menu could not be selected","FAILURE");
				
				snW.switchToFrame("Frame_Main");
				snW.Wait(3000);
				if(!snW.clickById("ALERT_FunnelIcon_Id"))
					Reporter.reportStep("Step 9: The Filter icon could not be clicked","FAILURE");
				
				snW.addNewFilter("Configuration item", "is", "server1.punch.com");
				snW.Wait(3000);
				if(!snW.clickByXpath("Runbook_Templates_Steps_runbutton_Xpath"))
					Reporter.reportStep("Step 9: The Run button could not be clicked","FAILURE");
				
				snW.Wait(3000);
				if(!snW.clickByXpath("First_Searched_Record_Xpath"))
					Reporter.reportStep("Step 9: The Alert record could not be opened","FAILURE");
				
				snW.Wait(3000);
				String state = snW.getDefaultValueByXpath("ALERTRECORD_AlertState_Xpath");
				String clcode = snW.getDefaultValueByXpath("ALERTRECORD_ClosureCode_Xpath");
				String exstate = "Closed";
				String exclcode = "Alert Suppression";
				
				if(state.equals(exstate))
					Reporter.reportStep("Step 9: Alert State is "+state,"SUCCESS");
				else
					Reporter.reportStep("Step 9: Alert State is "+state+", should be "+exstate+" ,hence fail","FAILURE");
				
				if(clcode.equals(exclcode))
					Reporter.reportStep("Step 10: Alert - Closure code is "+clcode,"SUCCESS");
				else
					Reporter.reportStep("Step 10: Alert Closure code is "+clcode+", should be "+exclcode+", hence fail","FAILURE");
				
				/*if(state.equals(exstate) && clcode.equals(exclcode))
					Reporter.reportStep("Step 9: Alert was suppressed successfully","SUCCESS");
				else
					Reporter.reportStep("Step 9: Alert was not suppressed successfully","FAILURE");*/
				
				snW.switchToDefault();
				
				if (!snW.clickByXpath("Logout_Xpath"))
					Reporter.reportStep("Step 11: The Log out could not be clicked","FAILURE");

				// Wait for few seconds
				snW.Wait(5000);
				
				status = "PASS";			
				
			} finally {
				// close the browser
				snW.quitBrowser();
				}

	}

		@DataProvider(name = "OD_STRY0010133_TC02")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("OD_STRY0010133_TC02");
			return arrayObject;
		}

}
