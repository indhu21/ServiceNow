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

public class Ven_GLOD_STRY0010133_TC01_V2 extends SuiteMethods {
	
	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "Ven_GLOD_STRY0010133_TC01_V2",groups="OpsDirector")
	public void alertSuppressor (String regUser, String regPwd, String scheduleType,
			String shortDesc, String supresstype, String tablename, String document,
			String syssch) throws COSVisitorException, IOException {
		
		// Prerequisites
		snW = new ServiceNowWrappers(entityId);
		
		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("The login with username:"+ regUser + " is not successful", "FAILURE");
			
			if (snW.selectMenu("Ops_Director","Administration", "Alert_Suppressors"))
				Reporter.reportStep("The Alert Suppressors menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("The Alert Suppressors menu could not be selected","FAILURE");
			
			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			
			if (snW.clickById("New_Button"))
				Reporter.reportStep("New button clicked successfully","SUCCESS");
			else
				Reporter.reportStep("New button could not be clicked successfully","FAILURE");
			
			// Key in a name for Alert Suppressor. Use the Alert Profile Name appended by <timestame>-suppressor. For example Link Down 07102015 Suppressor
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh-mm-ss");
			String formattedDate = sdf.format(date);
			
			String supressName = "Punch Test 133 - "+formattedDate;
			
			if(!snW.enterById("ALERT_Suppress_Name_Id",supressName))
				Reporter.reportStep("Name could not be entered","FAILURE");
						
			// Short Description:  Suppressing the Alert profile < profile name>
			if(!snW.enterById("ALERT_Suppress_ShortDesc_Id", shortDesc))
				Reporter.reportStep("Short Description could not be entered","FAILURE");
			
			// Suppression Type: CI Scope
			/*if(!snW.selectByVisibleTextById("ALERT_Supress_Sup_Type_Id", supresstype))
				Reporter.reportStep("Step 3: 'Suppression Type' could not be selected","FAILURE");*/
			
			// Suppression Target: Any record from CI Scope
			if (!snW.clickById("ALERT_Supress_Sup_Type_Search_Id"))
				Reporter.reportStep("Suppression Target search icon could not be clicked'","FAILURE");
			
			snW.Wait(2000);
			snW.getDriver().switchTo().activeElement();
			snW.Wait(1000);
			
			// Select table as CI Scopes
			if (!snW.selectByVisibleTextByXpath("ALERT_Sup_Tar_Table_Xpath", tablename))
				Reporter.reportStep("Suppression Target table could not be selected","FAILURE");
			
			if (!snW.enterAndChoose("ALERT_Sup_Doc_Xpath", document))
				Reporter.reportStep("Suppression Target document could not be selected","FAILURE");
			
			snW.Wait(3000);
			
			if (!snW.clickById("Ok_Id"))
				Reporter.reportStep("OK could not be clicked","FAILURE");
			
			snW.Wait(500);
			snW.switchToPrimary();
			snW.switchToDefault();
			snW.switchToFrame("Frame_Main");
			
			// Schedule Type: Run Once
			if(!snW.selectByVisibleTextById("ALERT_Suppress_Type_Id", scheduleType))
				Reporter.reportStep("Alert Suppress 'Schedule Type' could not be selected","FAILURE");
			
			if(!snW.enterById("Sys_Sch_Id", syssch))
				Reporter.reportStep("Alert Suppress Scheduled Start could not be entered","FAILURE");
			
			snW.Wait(3000);
			// and click submit button
			if(snW.clickById("Submit_Id"))
				Reporter.reportStep("Alert Supression - Submit button clicked","SUCCESS");
			else
				Reporter.reportStep("Alert Supression - Submit button could not be clicked","FAILURE");
						
			snW.Wait(5000);
						
			// Validate the record created in step 5 exist.
			String supname = "Name";
			if(!snW.selectByVisibleTextByXpath("Runbook_SearchKey_Xpath", supname))
				Reporter.reportStep("Alert Supression - Search Key could not be selected","FAILURE");
			snW.Wait(500);
			if(!snW.enterAndChoose("CIS_SearchReferenceData_Xpath", supressName))
				Reporter.reportStep("Alert Supression -" +supressName+ " could not be entered in search box","FAILURE");
			snW.Wait(5000);

			if(snW.getTextByXpath("ALERT_Sup_First_Prof_Xpath").equals(supressName))
				Reporter.reportStep("The Alert Suppressors successfully found the newly created record :"+supressName,"SUCCESS");
			else
				Reporter.reportStep("The Alert Suppressors could not find the newly created record :"+supressName,"FAILURE");
															
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

	@DataProvider(name = "Ven_GLOD_STRY0010133_TC01_V2")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("Ven_GLOD_STRY0010133_TC01_V2");
		return arrayObject;
	}
}