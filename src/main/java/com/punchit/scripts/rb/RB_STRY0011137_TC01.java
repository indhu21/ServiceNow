package com.punchit.scripts.rb;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class RB_STRY0011137_TC01 extends SuiteMethods {
ServiceNowWrappers snW;
	
	@Test(dataProvider = "RB_STRY0011137_TC01",groups="runbook")
	public void Open_Incident_from_runbook(String regUser, String regPwd, String resp,String comments) throws COSVisitorException, IOException, InterruptedException {
		
		// Prerequisites
		snW = new ServiceNowWrappers(entityId);
		
		try {
			
			if (snW.launchApp(browserName, true))
				Reporter.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
			else
				Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("The login with username:"+ regUser + " is not successful", "FAILURE");
			Thread.sleep(3000);
			// Step 2: In application navigator expand OpsDirector/Registration to select Assigned_to_me
			if (snW.selectMenuFromMainHeader("RunBook", "Assigned_to_me"))
				Reporter.reportStep("The Assigned_To_Me - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("The Assigned_To_Me - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");

			//Add Filter to Get Runbook with Steps available.
		    snW.clickByXpath("Runbook_Asignedtome_Filter_Icon_Xpath");
		    Thread.sleep(4000);
		    snW.clickByXpath("Runbook_Asignedtome_Filter_AND_Xpath");
		    Thread.sleep(2000);
		    snW.selectByVisibleTextByXpath("Runbook_Asignedtome_FilterCond1_Xpath", "Template");
		    snW.pressKey(Keys.TAB);
		    snW.selectByVisibleTextByXpath("Runbook_Asignedtome_FilterCond2_Xpath", "is not empty");
		    //snW.addNewFilter("CI List", "is not empty", "");
		    snW.clickByXpath("Runbook_Asignedtome_Filter_RUN_Xpath");
		    Thread.sleep(3000);
		    
			
			// Step 3 Right click as selecting take run book

			String Runbookno=snW.getTextByXpath("RunBook_Unassigned_firstrunbbok_xpath");
			System.out.println("Run book is " +Runbookno);
		   // Right click 
		   if(snW.rightClickByXpath("RunBook_Unassigned_firstrunbbok_xpath"))
		 	    Reporter.reportStep("Right click on the Runbook is successful ","SUCCESS");
		   else
 			{   status = "INSUFFICIENT DATA";
     			Reporter.reportStep("Right click on the Runbook could not be performed because of insufficient data ","FAILURE");
			}
		   
			//Step Take run book
			if(!snW.clickByXpath("RunBook_assignedtome_rightclicTakeRunbook_xpath"))
			Reporter.reportStep("Take Runbook is successfully selected ","SUCCESS");
			//else
			//Reporter.reportStep("Step 3A: Take Runbook could not be selected","FAILURE");
			 snW.Wait(5000);
			//Step 4: check if run book steps are displayed
			String step=snW.getTextByXpath("RunBook_assigntome_takerunbbokpage_stepcolumn_xpath");
			//System.out.println("text is "+step);
	        
			if(step.equalsIgnoreCase("Step"))
				Reporter.reportStep("Take runbook is selected and Take Runbook page is displayed successfully","SUCCESS");
			else
				Reporter.reportStep("Take runbook is selected but Take Runbook page could not be displayed successfully ","FAILURE");
	       
			
			//Step 5: selecting response as fail
			if(snW.selectByVisibleTextByXpath("Runbook_AssignedToMe_Response_Xpath", resp))
			{
				snW.Wait(2000);
				Reporter.reportStep("Take runbook: One task is selected as false","SUCCESS");
			}else
				{
				Reporter.reportStep("Take runbook: task could  not selected as false","FAILURE");
				}
			 snW.getDriver().switchTo().activeElement();
			 snW.Wait(2000);
			 if(snW.IsElementPresentByXpath("Runbook_AssignToMe_Slushbucket_Left_FirstValue_Xpath"))
		        {
			if(snW.clickByXpath("Runbook_AssignToMe_Slushbucket_Left_FirstValue_Xpath"))
			   {}
			   else
				   Reporter.reportStep("The CI is Not Selected", "FAILURE");
			   
			   if(snW.clickByXpath("Runbook_AssignToMe_Slushbucket_Select_Xpath"))
			   {}
			   else
				   Reporter.reportStep("The CI is not selected successfully", "FAILURE");
			   
     		   //Click Done.
			   if(snW.clickByXpath("Runbook_AssignToMe_Slushbucket_Done_Xpath"))
			   {
			   Reporter.reportStep("The CI to fail the step is selected successfully", "SUCCESS");
			   }
			   else
			   {
				   Reporter.reportStep("Done button is not clicked successfully", "FAILURE");
			   }
	        }
			//Step 6: Close the runbook
			if(snW.clickByXpath("Runbook_CloseRunbook_Xpath"))
				Reporter.reportStep("Close runbook is clicked sucessfully ","SUCCESS");
			else
			Reporter.reportStep("Close runbook could not be clicked","FAILURE");
	        
			//Step 7: Fill in the comments
			snW.getDriver().switchTo().activeElement();
			
			// enter the comments 
			if(snW.enterById("RunBook_assigntome_comments_ID", comments))
				Reporter.reportStep("Comments are entered successfully","SUCCESS");
			else
				Reporter.reportStep("Comments could not be entered","FAILURE");
			
			//Step 8: Click on raise incident
			if(snW.clickByXpath("Runbook_AssignToMe_RaiseIncident_Xpath"))
				Reporter.reportStep("Raise an incident clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Raise an incident not clicked successfully","FAILURE");
			
//			//Step 9: Incident should be created
			String IncidentID= snW.getAttributeByXpath("CREATEINC_IncidentNumber_Xpath", "value");
			
			//if(snW.IsElementPresentById("CREATEINC_IncidentNumber_Xpath"))
			if(!IncidentID.equalsIgnoreCase(""))
				Reporter.reportStep("Incident created sucessfully with incident number "+IncidentID,"SUCCESS");
			else
				Reporter.reportStep("Incident could not be created","FAILURE");
			
			
			// go out of the frame
            snW.switchToDefault();
            
            // Log out
            if(!snW.clickByXpath("Logout_Xpath"))
            	Reporter.reportStep("The logout Failed", "FAILURE");


            status="PASS";
		}
		finally{
			// close the browser
		snW.quitBrowser();		
		}
	}
		@DataProvider(name = "RB_STRY0011137_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011137_TC01");
			return arrayObject;
		}
}

