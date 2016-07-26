package com.punchit.scripts.gileadrb;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLRB_STRY0011065_TC02 extends SuiteMethods
{
ServiceNowWrappers snW;
	
	@Test(dataProvider = "RB_STRY0011065_TC02",groups="Runbook")
	public void Schedule_Runbook_MultipleCI(String regUser, String regPwd,String citype,String table ,String f1Section,String f1Condition,String f1Value,String SearchID) throws COSVisitorException, IOException, InterruptedException
	{
		  
		  // Prerequisites
		  snW = new ServiceNowWrappers(entityId);
		  
		  try {
		   
		   if (snW.launchApp(browserName, true))
		    Reporter.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
		   else
		    Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

		   // Step 1: Log in to application
		   if (snW.login(regUser, regPwd))
		    Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
		   else
		    Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");
		   
		   // step 2 :check if Templates link is available
		   if (snW.selectMenu("RunBook","RunBook_Definition", "Templates"))
		    Reporter.reportStep("Step 2: The Templates  - menu selected successfully ","SUCCESS");
		   else
		    Reporter.reportStep("Step 2: The Templates  - menu is not  displayed ","FAILURE"); 
		      
		   // Switch to the main frame
		   snW.switchToFrame("Frame_Main");
		    
		   snW.Wait(2000);
		   // step 3: click on First template
		   if(snW.clickByXpath("Runbook_Templates_Firsttemplate_Xpath"))
		    Reporter.reportStep("Step 3: Already created template is clicked ","SUCCESS");
		   else
		    Reporter.reportStep("Step 3: Already created template could not be clicked ","FAILURE"); 
		   
		   //Step 4:Searching schedule navigation is present or no Runbook_Templates_AddButton_Xpath
		   
		   if(snW.scrollToElementByXpath("Runbook_schedule_navigation_Xpath"))
		       Reporter.reportStep("Step 4: Schedule list is present","SUCCESS");
		   else
		    Reporter.reportStep("Step 4: Schedule list is not present ","FAILURE"); 
		   
		   //Step 5:Click NEW button 
		   if(snW.clickByXpath("Runbook_Schedule_AddButton_Xpath"))
		    Reporter.reportStep("Step 5: NEW button is clicked","SUCCESS");
		   else
		    Reporter.reportStep("Step 5: NEW button could not be clicked ","FAILURE");
		   
		   // Step 6: Select the CI type
		   String ScheduleNumber=snW.getAttributeByXpath("Runbook_Schedule_ScheduleNumber_Xpath", "value");
		   System.out.println(ScheduleNumber);
		   
		   if(snW.selectByVisibleTextByXpath("Runbook_Schedule_CIType_Xpath", citype))
			     Reporter.reportStep("Step 6:CI Type is selected as "+ citype ,"SUCCESS");
			    else
			     Reporter.reportStep("Step 6:CI Type as multiple could be selected","FAILURE");
		   
		   // Step 7: Validating table and condition field is present
		   if(!snW.isExistById("Runbook_Templeate_Schedule__Table_id"))
				Reporter.reportStep("Step 7: Configuration item is not present","FAILURE");
			
		   
		   if(snW.isExistByXpath("Runbook_Schedule_FilterCondition_Xpath"))
			   Reporter.reportStep("Step 7: Table and Condition fields is present","SUCCESS");
		   else
				Reporter.reportStep("Step 7: Configuration item is not present","FAILURE");
			
		   //Step 8:  select table
		   if(snW.selectByVisibleTextById("Runbook_Templeate_Schedule__Table_id", table))
		    Reporter.reportStep("Step 8: Table is selected with the value "+ table ,"SUCCESS");
		    else
		    Reporter.reportStep("Step 8: Table could not be selected with the value "+ table ,"FAILURE");
		   
		   if(!snW.selectByVisibleTextByXpath("CIS_FirstFilterType1_Xpath",f1Section))
				Reporter.reportStep("Step 8A: CI Filter type could not be selected","FAILURE");

			if(!snW.selectByVisibleTextByXpath("CIS_FilterCondition1_Xpath",f1Condition))
				Reporter.reportStep("Step 8B: CI Filter Condition could not be selected","FAILURE");

			if(snW.enterByXpath("CIS_FilterValue1_Xpath", f1Value))
				 Reporter.reportStep("Step 8A: Filter conditons are set successfully","SUCCESS");
		    else
		     Reporter.reportStep("Step 8A: Filter values could not be selected","FAILURE");
			
			//Step 9: Click on Submit button
			if (!snW.clickByXpath("Runbook_Schedule_Submit_Xpath"))
				Reporter.reportStep("Step 9: Submit button could not be clicked","FAILURE");

			//Step 10: Validating the added Schedule is displayed
		     if(!snW.selectByVisibleTextByXpath("Runbook_Schedule_SearchBox_Xpath", SearchID))
		      Reporter.reportStep("Step 10: Searchbox as number could not selected", "FAILURE");
		     
		     if(!snW.enterByXpathAndClick("Runbook_Schedule_SearchValue_Xpath", ScheduleNumber))
		      Reporter.reportStep("Step 10: Schedule that was created was not entered to search value field", "FAILURE");
		     
		     snW.Wait(2000);
		     // Validate the Ticket Number.
		     
		       String ValidateID = snW.getTextByXpath("Runbook_Schedule_FirstNumber_Xpath");
		       System.out.println(ValidateID);
		       if(ValidateID.equalsIgnoreCase(ScheduleNumber))
		     Reporter.reportStep("Step 10: Newly created schedule is saved successfully", "SUCCESS");
		    else
		     Reporter.reportStep("Step 10: Newly created schedule is not saved", "FAILURE");
		     
		       snW.Wait(2000);
				// go out of the frame#
	            snW.switchToDefault();

	         // Log out
	            if(!snW.clickByXpath("Logout_Xpath"))
	            	Reporter.reportStep("The logout Failed", "FAILURE");


	            status="PASS";

		       
			}
		finally{
			// close the browser
			//snW.quitBrowser();		
		}
	}
	@DataProvider(name = "RB_STRY0011065_TC02")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011065_TC02");
		return arrayObject;
	}
}


