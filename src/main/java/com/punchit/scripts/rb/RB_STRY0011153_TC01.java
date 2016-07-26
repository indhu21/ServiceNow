package com.punchit.scripts.rb;


	import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;
	
	public class RB_STRY0011153_TC01 extends SuiteMethods{


		// Create Instance
		ServiceNowWrappers snW;

		@Test(dataProvider = "RB_STRY0011153_TC01",groups="OpsDirector")
		public void appProperties(String regUser, String regPwd
				) throws COSVisitorException, InterruptedException {

			// Prerequisites
			snW = new ServiceNowWrappers(entityId);

			try {

				if (snW.launchApp(browserName, true))
					Reporter.reportStep("The browser:" + browserName + " launched successfully", "WARNING");
				else
					Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

				// Step 1: Log in to application
				if (snW.login(regUser, regPwd))
					Reporter.reportStep("The login with username:"+ regUser + " is successful", "SUCCESS");
				else
					Reporter.reportStep("The login with username:"+ regUser + " is not successful", "FAILURE");
				
				Thread.sleep(2000);
				
				//Step 2: Open Tempelates Under Runbook Definition.
				if(snW.selectMenu("RunBook","Runbook_Definition", "Templates"))
					Reporter.reportStep("Template menu was selected successfully", "SUCCESS");
				else
					Reporter.reportStep("Template menu could not be selected ", "FAILURE");
				
				//Wait for the alert Profiles to load.
				Thread.sleep(2000);
				//Switch to the main frame.
				snW.switchToFrame("Frame_Main");
				
				snW.clickByXpath("Runbook_Firsttemplate_Xpath");
				
				Thread.sleep(2000);// Wait for the Tempelate to load
				Reporter.reportStep("RunBook Template is displayed", "SUCCESS");

				Thread.sleep(3000);
				
				//Step 4: Click New on the Steps TAB
				 if(snW.IsElementPresentByXpath("Runbook_Templates_Step_new_Xpath"))
				 {}
				 else
					 Reporter.reportStep("Runbook - Step field is not available","FAILURE"); 
				
				 //Step 4b Click on New Button
				 if(snW.clickByXpath("Runbook_Templates_AddButton_Xpath"))
					    Reporter.reportStep("New Button to add a new step is clicked successfully ","SUCCESS");
					   else
					    Reporter.reportStep("New Button could not be clicked","FAILURE");    
				 
				 //Wait for the new page to load.
				 Thread.sleep(3000);
				String StepName = "Punch Test Step"+snW.getCurrentTime();
				 // enter the Step Name
				 if(snW.enterByXpath("Runbook_Templates_Step_Xpath",StepName))
					 Reporter.reportStep("Step Name is entered successfully ","SUCCESS");
				 else
					 Reporter.reportStep("Step Name could not be added","FAILURE"); 
				Thread.sleep(2000);	
				 //Click On Submit
				 if(snW.clickById("Runbook_Templates_new_submit_id"))
				 {}
					   else
					    Reporter.reportStep("Submit button could not be clicked  ","FAILURE");
				 
				
				 //Find the step elements:
				 snW.IsElementPresentByXpath("Runbook_Templates_Step_new_Xpath");
				 
				 if(!snW.scrollToElementByXpath("Runbook_Tempelates_step_Searchbox_Xpath"))
					 Reporter.reportStep("Element could not be set in scroll view", "FAILURE");
				 
				 if(snW.selectByVisibleTextByXpath("Runbook_Tempelates_step_Searchbox_Xpath", "Step"))
				 {}
				 else
					 Reporter.reportStep("Step was not slected as the filter", "FAILURE");
				 
				 if(snW.enterByXpath("Runbook_Tempelates_step_Searchbox_Value_Xpath", StepName))
				 {}
				 else
					 Reporter.reportStep("The Step Name was not entered to search box", "FAILURE");
				 //click enter
				 snW.pressKey(Keys.RETURN);
				 Thread.sleep(3000);
				 
				 // Validate the Ticket Number.
				//i
					//((JavascriptExecutor) snW.getDriver()).executeScript(0,250);
			    String ValidateStep = snW.getTextByXpath("Runbook_Tempelates_FirstStep_Xpath");
			    System.out.println(ValidateStep);
			    
			    if(ValidateStep.equalsIgnoreCase(StepName))
					Reporter.reportStep("New Step Is added successfully and displayed in the Steps List", "SUCCESS");
				else
					Reporter.reportStep("SNew Step is not added to the Steps List under tempelate", "FAILURE");
			
			
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
		@DataProvider(name = "RB_STRY0011153_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011153_TC01");
			return arrayObject;
		}
	
	}
	
