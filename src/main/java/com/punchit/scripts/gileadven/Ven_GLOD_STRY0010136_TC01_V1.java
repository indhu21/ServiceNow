package com.punchit.scripts.gileadven;

import java.io.IOException;
import java.text.ParseException;
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

public class Ven_GLOD_STRY0010136_TC01_V1 extends SuiteMethods {
	
	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "Ven_GLOD_STRY0010136_TC01_V1",groups="OpsDirector")
	public void alertSuppressor (String regUser, String regPwd, String scheduleType,
			String shortDesc, String tablename, String document) throws COSVisitorException, IOException, ParseException {
		
		// Prerequisites
		snW = new ServiceNowWrappers(entityId);
		
		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("Step"
						+ " 1: The login with username:"+ regUser + " is successful", "SUCCESS");
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
			
			//Alert Profile Name appended by <timestame>-suppressor. For example Link Down 07102015 Suppressor
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh-mm-ss");
			String formattedDate = sdf.format(date);
			
			String supressName = "Punch Test 136 - "+formattedDate;
			
			if(!snW.enterById("ALERT_Suppress_Name_Id",supressName))
				Reporter.reportStep("Step 4: Name could not be entered","FAILURE");
						
			// Short Description:  Suppressing the Alert profile < profile name>
			if(snW.enterById("ALERT_Suppress_ShortDesc_Id", shortDesc))
				Reporter.reportStep("Step 4: Name and Short Description entered successfully","SUCCESS");
			else
				Reporter.reportStep("Step 4: Short Description could not be entered","FAILURE");
			
			//Click o suppression target
			if(!snW.clickByXpath("AlertSuppressors_SuppressionTargetLookup_Xpath"))
				Reporter.reportStep("Step 5: Suppression look up icon could not be clicked'","FAILURE");
			
			System.out.println("1");
			snW.Wait(2000);
			snW.getDriver().switchTo().activeElement();
			snW.Wait(1000);
			
			// Select table as CI Scopes
			if (!snW.selectByVisibleTextByXpath("ALERT_Sup_Tar_Table_Xpath", tablename))
				Reporter.reportStep("Step 5: Suppression Target table could not be selected","SUCCESS");
			System.out.println("2");		
			if (!snW.enterAndChoose("ALERT_Sup_Doc_Xpath", document))
				Reporter.reportStep("Step 5: Suppression Target document could not be selected","FAILURE");
			System.out.println("3");		
			snW.Wait(3000);
						
			if (snW.clickById("Ok_Id"))
				Reporter.reportStep("Step 5: OK button clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 5: OK button could not be clicked","FAILURE");
						
			snW.Wait(500);
				snW.switchToPrimary();
				snW.switchToDefault();
				snW.switchToFrame("Frame_Main");
						
			// Schedule Type: Run Once
			if(!snW.selectByVisibleTextById("ALERT_Suppress_Type_Id", scheduleType))
				Reporter.reportStep("Step 6: Alert Suppress 'Schedule Type' could not be selected","FAILURE");
			
			Date date1 = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy-h-mm-ss");
			String formattedDate1 = sdf1.format(date1);	
			String ScheduleStart = formattedDate1;
			
			
			SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MM-yyyy-h-mm-ss" );   
			Calendar cal = Calendar.getInstance();    
			cal.setTime( dateFormat.parse(ScheduleStart));    
			cal.add( Calendar.DATE, 1 );    
			String convertedDate=dateFormat.format(cal.getTime());    
			
			
			if(!snW.enterByXpath("AlertSuppressors_ScheduledStart_Xpath", ScheduleStart))
				Reporter.reportStep("Step 6: Alert Suppress 'Scheduled Start' could not be entered","FAILURE");
				snW.Wait(3000);
				
			if(!snW.enterByXpath("AlertSuppressors_ScheduledStop_Xpath", convertedDate))
				Reporter.reportStep("Step 6: Alert Suppress 'Scheduled Stop' could not be entered","FAILURE");
			
			// and click submit button
		   // if(snW.clickById("Submit_Id"))
			if(snW.clickByXpath("Runbook_Schedule_Submit_Xpath"))	
				Reporter.reportStep("Step 6: Alert Supression - Submit button clicked","SUCCESS");
			else
				Reporter.reportStep("Step 6: Alert Supression - Submit button could not be clicked","FAILURE");
									
			snW.Wait(5000);
			
			snW.clickByXpath("Back_xpath");
			// Validate the record created in step 5 exist
			String supname = "Name";
			if(!snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", supname))
				Reporter.reportStep("Step 7: Alert Supression - Search Key could not be selected","FAILURE");
			
			snW.Wait(500);
			if(!snW.enterAndChoose("RunBook_Templates_Searchbox_xpath", supressName))
				Reporter.reportStep("Step 7: Alert Supression -" +supressName+ " could not be entered in search box","FAILURE");
			snW.Wait(5000);

			if(snW.getTextByXpath("Paralt_xpath").equals(supressName))
				Reporter.reportStep("Step 7: The 'Alert Suppressors' successfully found the newly created record :"+supressName,"SUCCESS");
			else
				Reporter.reportStep("Step 7: The 'Alert Suppressors' could not find the newly created record :"+supressName,"FAILURE");
												
			// Switch to the main frame
		    snW.switchToFrame("Frame_Main");
	        
		    // go out of the frame
			snW.switchToDefault();

			// Log out
			if(snW.clickByXpath("Logout_Xpath"))
			   Reporter.reportStep("Step 8: The Log out is clicked successfully.","SUCCESS");
			else
			   Reporter.reportStep("Step 8: The logout Failed", "FAILURE");		


				status = "PASS";


		}
		finally {
			// close the browser
			snW.quitBrowser();
		}
		}
			@DataProvider(name = "Ven_GLOD_STRY0010136_TC01_V1")
			public Object[][] fetchData() throws IOException {
				Object[][] arrayObject = DataInputProvider.getSheet("Ven_GLOD_STRY0010136_TC01_V1");
				return arrayObject;
			}
		}




