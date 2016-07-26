package com.punchit.scripts.gileadven;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_STRY0010114_TC01 extends SuiteMethods{

	
	// Create Instance
	ServiceNowWrappers snW;
	
	@Test(dataProvider = "Ven_GLOD_STRY0010114_TC01",groups="OpsDirector")
	public void appProperties(String regUser, String regPwd, String Filter1, String Filter2, String Value) throws COSVisitorException, IOException, InterruptedException {
	
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
	
			if(snW.selectMenu("Ops_Director","Ops_Consoles","Alert_Console"))
				Reporter.reportStep("Step2: Alert Console is  selected successfully", "SUCCESS");
			else
				Reporter.reportStep("Step2: Alert Console could not be selected", "FAILURE");
			
			//Switch to Main Frame
			snW.switchToFrame("Frame_Main");
			Thread.sleep(2000);// wait for the alerts page to load.
			
			//Check the Alert Console table for the given condition for the alers to exist.
			snW.getDriver().findElementByLinkText("New Alerts").click(); 
		       Thread.sleep(2000);
		       
		     //Add Filter to Get Runbook with Steps available.
			      snW.clickByXpath("Filter_Icon_Xpath");
			      
			      Thread.sleep(3000);
			      
			      snW.clickByXpath("Filter_AND_Xpath");
			      
			      Thread.sleep(2000);
			      
			      snW.selectByVisibleTextByXpath("AlertConsole_newAlert_Filteradd_Xpath", Filter1);
			      Thread.sleep(1000);
			      
			      snW.selectByVisibleTextByXpath("AlertConsole_newAlert_Filteradd_Xpath", Filter2);
			      snW.pressKey(Keys.TAB);
			      snW.pressKey(Keys.TAB);
			      
			     snW.enterAndChoose("AlertConsole_newAlert_Filtervalue_Xpath", Value);
			     Thread.sleep(2000);
			     snW.clickByXpath("Runfilter_Xpath");
			     Thread.sleep(3000);
			      
			//Step 3 Acknowledge the first alert in Alert Console:
			//Step 3a: Select the first alert profile to right click on it.
			String Alert_no = (snW.getTextByXpath("Select_alert"));
			if(Alert_no == "")
			{   status="INSUFFICIENT DATA";
				Reporter.reportStep("Step 3a: No Alerts Present under Alert Console ","FAILURE");
			}
			//Step 3b: Right Click on the the first alert available.
			if(snW.rightClickByXpath("Select_alert"))
				Reporter.reportStep("Step 3b: Right Click is performed on the alert ticket--"+ Alert_no+ "--in Alert Console", "SUCCESS");
			else
				Reporter.reportStep("Step 3b: Right Click was not performed on the alert ticket--"+ Alert_no+ "--in Alert Console", "FAILURE");
	
			//Switch to active element to Link to Parent.
			Thread.sleep(1000);
			snW.getDriver().switchTo().activeElement();
				
		   //Step 4: Click on the Magnification Symbol to display the alert tickets to make a parent ticket.
			if(snW.clickByXpath("ALERT_Acknowledge_Xpath"))
				Reporter.reportStep("Step 4: Alert  Acknowledged Successfully clicked", "SUCCESS");
			else
				Reporter.reportStep("Step 4: Alert was not Acknowledged", "FAILURE");
			
			Thread.sleep(3000);
			//Step 5: Open the alert Acknowledge in Step 4.
			if(snW.selectMenu("Ops_Director","Ops_Consoles","My_Alerts"))
				Reporter.reportStep("Step2: My Alerts is successfully selected", "SUCCESS");
			else
				Reporter.reportStep("Step2: My Alerts could not be selected", "FAILURE");
			
			Thread.sleep(2000);
			snW.switchToFrame("Frame_Main");
			
		       
		       //Search the alert number using the filter.
			snW.getDriver().findElementByLinkText("My Alerts").click(); 
		       Thread.sleep(2000);
		    snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Number");
		    snW.enterAndChoose("Alert_Num_Filter_Xpath", Alert_no);   
		    
		    //Click on the Alert
		    if(snW.clickByXpath("Select_alert"))
		    	Reporter.reportStep("Step 5: Acknowledge alert selected successfully", "SUCCESS");
		    else
		    	Reporter.reportStep("Step 5: Acknowledged alert could not be selected", "FAILURE");
			 
			
			//Check for reaction button availability.
			if(snW.IsElementPresentByXpath("RunReaction_Xpath"))
				Reporter.reportStep("Step 6: Run Reaction button is available", "SUCCESS");
			else
				Reporter.reportStep("Step 6: Run Reaction button is not availble", "FAILURE");
			
			//Check the alert profile details and its Assignment group.
			String AlertProf = snW.getAttributeByXpath("AlertProfile_profileName_Xpath", "value");
			
			//Select the alert profile and check its assignment group.
			if (snW.selectMenu("Ops_Director", "Configurations", "Alert_Profiles"))
				Reporter.reportStep("Step 2: The Alert Profiles menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Alert Profiles menu could not be selected","FAILURE");
			
			//Switch to main frame
			snW.switchToFrame("Frame_Main");
			Thread.sleep(3000);
			
			//Filter the Selected alert profile.
			snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Name");
			snW.enterAndChoose("Alert_Num_Filter_Xpath",AlertProf );
			
			Thread.sleep(2000);
			
			if(snW.clickByXpath("Select_alert"))
				Reporter.reportStep("Step 6a: Alert Profile selected successfully", "SUCCESS");
			else
				Reporter.reportStep("Step 6a: Alert Porfile could not be selected", "FAILURE");
			
			String APAssignment_Grp = snW.getAttributeByXpath("AlertProfile_IncAssignment_Grp_Xpath", "value");
			
			
			//Reopen the Alert ticket acknowledge in the above steps.
			
			
			snW.selectMenu("Ops_Director","Ops_Consoles","My_Alerts");
			Thread.sleep(2000);
			snW.switchToFrame("Frame_Main");
			
		       
		       //Search the alert number using the filter.
			snW.getDriver().findElementByLinkText("My Alerts").click(); 
		       Thread.sleep(2000);
		    snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Number");
		    snW.enterAndChoose("Alert_Num_Filter_Xpath", Alert_no);   
		    
		    //Click on the Alert
		    if(snW.clickByXpath("Select_alert"))
		    	Reporter.reportStep("Step 6b: Acknowledge alert selected successfully", "SUCCESS");
		    else
		    	Reporter.reportStep("Step 6b: Acknowledged alert could not be selected", "FAILURE");
		    
		    Thread.sleep(3000);
			
		    snW.clickByXpath("RunReaction_Xpath");
			 Thread.sleep(3000);
			 
			 String Assignment_grp = snW.getAttributeByXpath("Incident_Assignment_Group_Xpath", "value");
			 
			 //Set the CAller field to current logged in user name.
			 snW.switchToDefault();
				String UserName = snW.getTextById("FullName_Id");
				snW.switchToFrame("Frame_Main");
				
				//enter the Caller value on the incident Form.
				if(snW.enterAndChoose("Incident_Caller_Xpath", UserName))
					Reporter.reportStep("Step 6c: Caller value is successfully inserted", "SUCCESS");
				else
					Reporter.reportStep("Step 6c: The value in the Caller field could not be inserted", "FAILURE");
				
				String Inc_Num = snW.getAttributeByXpath("Incident_Number_Xpath", "value");
				System.out.println(Inc_Num);
				
				if(Assignment_grp == "")
					Reporter.reportStep("Step 6d: Incident assignment group is not autopopulated", "FAILURE");
				
				if(APAssignment_Grp.equalsIgnoreCase(Assignment_grp))
					Reporter.reportStep("Step 6d: Incident Assignment group is as expected ", "SUCCESS");
				else
					Reporter.reportStep("Step 6d: Incident assignment group is not same as its Alert Profile's Assignment group", "FAILURE");
				
				
				//Click on the update button
				if(snW.clickById("Update_Button"))
					Reporter.reportStep("Step 6e: Update was clicked successfully", "SUCCESS");
				else
					Reporter.reportStep("Step 6e: Update was not clicked successfully", "FAILURE");
				
				Thread.sleep(3000);
				
				
				snW.selectMenu("Ops_Director","Ops_Consoles","My_Alerts");
				Thread.sleep(2000);
				snW.switchToFrame("Frame_Main");
				
				 //Search the alert number using the filter.
				snW.getDriver().findElementByLinkText("My Alerts").click(); 
			       Thread.sleep(2000);
			    snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Number");
			    snW.enterAndChoose("Alert_Num_Filter_Xpath", Alert_no);   
			    
			    snW.clickByXpath("Select_alert");
			    Thread.sleep(3000);
			// FInd the Alert in My Alerts and click on the 
				snW.selectByVisibleTextByXpath("AlertProfile_RelatedTask_searchbox_Xpath", "Number");
				snW.enterAndChoose("AlertProfile_RelatedTask_searchboxValue_Xpath", Inc_Num);
				
				//Step 7 search incident under related tasks.
				String relTask = snW.getTextByXpath("AlertProfile_RelatedTask_firstRec_Xpath");
				
				
				if(Inc_Num.equalsIgnoreCase(relTask))
					Reporter.reportStep("Step 7a: Incident created is available under Realted tasks", "SUCCESS");
				else
					Reporter.reportStep("Step 7a: Incident created is not available under Realted tasks", "FAILURE");
				
				//Step7b 
				if(snW.selectMenu("Incident", "All_Incidents"))
					Reporter.reportStep("Step 7b: All incidents menu is selected successfully", "SUCCESS");
				else
					Reporter.reportStep("Step 7b: All Incidents menu was not selected successfully", "FAILURE");
				
				//Search for the incident number
				
				 //Switch to main frame.
		        Thread.sleep(3000);
		        snW.switchToFrame("Frame_Main");
		        
		        //Select the Alert Number.
		        if(snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Number"))
					Reporter.reportStep("Step 8a: Number is selected as dropdown option","SUCCESS");
				else
					Reporter.reportStep("Step 8a: Number is not selected as dropdown option","FAILURE");
		        
		        if(snW.enterAndChoose("Alert_Num_Filter_Xpath",Inc_Num ))
					Reporter.reportStep("Step 8b: Incident is selected successfully","SUCCESS");
				else
					Reporter.reportStep("Step 8b: Incident could not be selected","FAILURE");
		        
		        Thread.sleep(3000);
		        
		        //Click on the incident number
		        
		        if(snW.clickByXpath("Paralt_xpath"))
					Reporter.reportStep("Step 6d: Incident No--"+Inc_Num+"-- is selected Successfully", "SUCCESS");
				else
					Reporter.reportStep("Step 6d: Incident Could no be selected", "FAILURE");
		        
		        // Check the Incident Activity Log to validate the aert no:
		        
		        WebElement Alert = snW.getDriver().findElementByLinkText(Alert_no);
		        snW.scrollToElement(Alert);
		        
		        
		        if(snW.VerifyByLink(Alert_no, true))
		        	Reporter.reportStep("step 9: Alert Profile number is present in the activity log of the incident", "SUCCESS");
		        else
		        	Reporter.reportStep("Step 9: Alert profile number is not present in the activity log of the incident", "FAILURE");
		        
		        
		     // go out of the frame
	            snW.switchToDefault();

	            // Log out
	            if(!snW.clickByXpath("Logout_Xpath"))
	             Reporter.reportStep("The logout Failed", "FAILURE");
	    
	            status="PASS";
	
	}
				finally {
					// close the browser
					snW.quitBrowser();
				}
	}
				@DataProvider(name = "Ven_GLOD_STRY0010114_TC01")
				public Object[][] fetchData() throws IOException {
					Object[][] arrayObject = DataInputProvider.getSheet("Ven_GLOD_STRY0010114_TC01");
					return arrayObject;
				}
	}
	
