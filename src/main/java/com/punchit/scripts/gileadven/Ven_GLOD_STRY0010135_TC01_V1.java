package com.punchit.scripts.gileadven;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_STRY0010135_TC01_V1 extends SuiteMethods {
	
	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "Ven_GLOD_STRY0010135_TC01_V1",groups="OpsDirector")
	public void alertSuppressor (String regUser, String regPwd, String scheduleType, String shortDesc, 
			 String startHr, String startMn, String startSc, 
			 String endHr, String endMn,String endSc, String supresstype,
			 String tablename, String document, String stopday) 
					throws COSVisitorException, IOException {
		
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
			
			if (snW.selectMenu("Ops_Director","Administration", "Alert_Suppressors"))
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
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh-mm-ss");
			String formattedDate = sdf.format(date);	
			String supressName = "Punch Test 135 - "+formattedDate;
			
			if(!snW.enterById("ALERT_Suppress_Name_Id",supressName))
				Reporter.reportStep("Step 3: Name could not be entered","FAILURE");
						
			// Short Description:  Suppressing the Alert profile < profile name>
			if(!snW.enterById("ALERT_Suppress_ShortDesc_Id", shortDesc))
				Reporter.reportStep("Step 3: Short Description could not be entered","FAILURE");
			
			// Suppression Type: CI Scope
			/*if(!snW.selectByVisibleTextById("ALERT_Supress_Sup_Type_Id", supresstype))
				Reporter.reportStep("Step 3: 'Suppression Type' could not be selected","FAILURE");*/
			
			// Suppression Target: Any record from CI Scope
			if (!snW.clickById("ALERT_Supress_Sup_Type_Search_Id"))
				Reporter.reportStep("Step 3: Suppression Target search icon could not be clicked'","FAILURE");
			
			snW.Wait(2000);
			snW.getDriver().switchTo().activeElement();
			snW.Wait(1000);
			
			// Select table as CI Scopes
			if (!snW.selectByVisibleTextByXpath("ALERT_Sup_Tar_Table_Xpath", tablename))
				Reporter.reportStep("Step 3: Suppression Target table could not be selected","FAILURE");
			
			if (!snW.enterAndChoose("ALERT_Sup_Doc_Xpath", document))
				Reporter.reportStep("Step 3: Suppression Target document could not be selected","FAILURE");
			
			if (!snW.clickById("Ok_Id"))
				Reporter.reportStep("Step 3: OK could not be clicked","FAILURE");
			
			snW.Wait(500);
			snW.switchToPrimary();
			snW.switchToDefault();
			snW.switchToFrame("Frame_Main");
			
			// Schedule Type: Run Daily
			if(!snW.selectByVisibleTextById("ALERT_Suppress_Type_Id", scheduleType))
				Reporter.reportStep("Step 3: Alert Suppress Schedule Type could not be selected","FAILURE");
			
			// Stop Day: Tuesday
			if (!snW.selectByVisibleTextById("Stop_Day", stopday))
				Reporter.reportStep("Step 3: Alert Suppress Stop Day could not be selected","FAILURE");
						
			// Start Time: Hours 08 00 00
			if(!snW.enterById("ALERT_Suppress_StartHour_Id", startHr))
				Reporter.reportStep("Step 3: Alert Suppress Start Hour could not be entered","FAILURE");
						
			if(!snW.enterById("ALERT_Suppress_StartMin_Id", startMn))
				Reporter.reportStep("Step 3: Alert Suppress Start Minutes could not be entered","FAILURE");
						
			if(!snW.enterById("ALERT_Suppress_StartSec_Id", startSc))
				Reporter.reportStep("Step 3: Alert Suppress Start Seconds could not be entered","FAILURE");
						
			// Stop Time: 08 00 00
			if(!snW.enterById("ALERT_Suppress_StopHour_Id", endHr))
				Reporter.reportStep("Step 3: Alert Suppress End Hour could not be entered","FAILURE");
						
			if(!snW.enterById("ALERT_Suppress_StopMin_Id", endMn))
				Reporter.reportStep("Step 3: Alert Suppress End Minutes could not be entered","FAILURE");
						
			if(snW.enterById("ALERT_Suppress_StopSec_Id", endSc))
				Reporter.reportStep("Step 4: Alert Supression details are entered successfully","SUCCESS");
			else
				Reporter.reportStep("Step 4: Alert Suppress End Seconds could not be entered","FAILURE");

						
			// and click submit button
			if(snW.clickById("Submit_Id"))
				Reporter.reportStep("Step 5: Alert Supression - Submit button clicked","SUCCESS");
			else
				Reporter.reportStep("Step 5: Alert Supression - Submit button could not be clicked","FAILURE");
			
			snW.Wait(5000);
			
			// Validate the record created in step 5 exist.
			String supname = "Name";
			if(!snW.selectByVisibleTextByXpath("Runbook_SearchKey_Xpath", supname))
				Reporter.reportStep("Step 6: Alert Supression - Search Key could not be selected","FAILURE");
			snW.Wait(500);
			if(!snW.enterAndChoose("CIS_SearchReferenceData_Xpath", supressName))
				Reporter.reportStep("Step 6: Alert Supression -" +supressName+ " could not be entered in search box","FAILURE");
			snW.Wait(5000);

			if(snW.getTextByXpath("ALERT_Sup_First_Prof_Xpath").equals(supressName))
				Reporter.reportStep("Step 6: The Alert Suppressors successfully found the newly created record :"+supressName,"SUCCESS");
			else
				Reporter.reportStep("Step 6: The Alert Suppressors could not find the newly created record :"+supressName,"FAILURE");
												
			// go out of the frame
			snW.switchToDefault();

			// Log out
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

		@DataProvider(name = "Ven_GLOD_STRY0010135_TC01_V1")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("Ven_GLOD_STRY0010135_TC01_V1");
			return arrayObject;
		}
}
