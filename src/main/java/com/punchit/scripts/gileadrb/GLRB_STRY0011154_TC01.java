package com.punchit.scripts.gileadrb;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLRB_STRY0011154_TC01 extends SuiteMethods {
ServiceNowWrappers snW;
	
	@Test(dataProvider = "RB_STRY0011154_TC01",groups="Runbook")
	public void Create_Child_Step(String regUser, String regPwd) throws COSVisitorException, IOException, InterruptedException {
		
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
			
			Thread.sleep(2000);
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
				Reporter.reportStep("Step 3: Already created template is click ","SUCCESS");
			else
				Reporter.reportStep("Step 3: Already created template could not be clicked ","FAILURE");	
				
			// Step 4:Click on new click
			if(!snW.IsElementPresentByXpath("Runbook_Templates_Step_new_Xpath"))
				Reporter.reportStep("Step 4A: Runbook - existing step field is not available","FAILURE");	
			
			//Click on new button to add new step
			if(snW.clickByXpath("Runbook_Templates_AddButton_Xpath"))
				Reporter.reportStep("Step 4B: NEW button is clicked ","SUCCESS");
			else
				Reporter.reportStep("Step 4B: NEW button is clicked","FAILURE");				
          
			//Step 5: enter the Step Name
			String StepName = "Punch Test Step"+snW.getCurrentTime();
		     
		     if(snW.enterByXpath("Runbook_Templates_Step_Xpath",StepName))
		      Reporter.reportStep("Step 5: New Step is added ","SUCCESS");
		     else
		      Reporter.reportStep("Step 5: New step is not added","FAILURE"); 
		    
		     Thread.sleep(1000);
			// Step 6: click on submit
			if(snW.clickById("Runbook_Templates_new_submit_id"))
				Reporter.reportStep("Step 6: Submit button is clicked ","SUCCESS");
			else
				Reporter.reportStep("Step 6: Submit button could not be clicked  ","FAILURE");
			
			 if(!snW.scrollToElementByXpath("Runbook_Tempelates_step_Searchbox_Xpath"))
				 Reporter.reportStep("Element could not be set in scroll view", "FAILURE");
			//Step 7 :Finding the step that is been added	
		     if(!snW.selectByVisibleTextByXpath("Runbook_Tempelates_step_Searchbox_Xpath", "Step"))
		         Reporter.reportStep("Step7: Step could not be selected in the searchbox", "FAILURE");
		     
		    // if(!snW.enterByXpathAndClick("Runbook_Tempelates_step_Searchbox_Value_Xpath", StepName))
		      //   Reporter.reportStep("Step7: The Step name could not be entered in the search field","FAILURE");
		     if(snW.enterByXpath("Runbook_Tempelates_step_Searchbox_Value_Xpath", StepName))
			 {}
			 else
				 Reporter.reportStep("Step 7 : The Step Name is not entered to search box", "FAILURE");
			 //click enter
			 snW.pressKey(Keys.RETURN);
			 Thread.sleep(3000);
		     //Thread.sleep(3000);
		     // Validate the Ticket Number.
		     
		     String ValidateStep = snW.getTextByXpath("Runbook_Tempelates_FirstStep_Xpath");
		       System.out.println(ValidateStep);
		     
		       if(ValidateStep.equalsIgnoreCase(StepName))
		     Reporter.reportStep("Step7: Newly added step is saved successfully", "SUCCESS");
		      else
		     Reporter.reportStep("Step7: Newly added step is not saved", "FAILURE");
		     
		     //Step 8: Clicking on newly added step
		      if(snW.clickByXpath("Runbook_Tempelates_FirstStep_Xpath"))
		    	  Reporter.reportStep("Step8: Newly added step is clicked", "SUCCESS");
		      else
		    	  Reporter.reportStep("Step8: Newly added step could not be clicked", "FAILURE"); 
		     
		      //Step 9:Click on create child button
		      if(snW.clickByXpath("Runbook_CreateChild_Xpath"))
		    	  Reporter.reportStep("Step 9: Create child button is clicked ","SUCCESS");
				else
					Reporter.reportStep("Step 9: Create child button could not be clicked  ","FAILURE");
				
		      
		      String ChildName = "Punch Test child Step"+snW.getCurrentTime();
		      //Step 10:Enter the child step name
		      if(snW.enterByXpathAndClick("Runbook_Template_StepChildStep_Xpath",ChildName))
		    	  Reporter.reportStep("Step 10:Child step name entered sucessfully ","SUCCESS");
				else
					Reporter.reportStep("Step 10:Child step name could not be entered  ","FAILURE");
			
		      //Step 11 click on submit
				if(snW.clickById("Runbook_Templates_new_submit_id"))
					Reporter.reportStep("Step 11: Submit button clicked ","SUCCESS");
				else
					Reporter.reportStep("Step 11: Submit button could not be clicked  ","FAILURE");
				
			//Step 12:Find the child step elements that was added
			     snW.IsElementPresentByXpath("Runbook_Templates_Step_new_Xpath");
			     if(!snW.scrollToElementByXpath("Runbook_Tempelates_step_Searchbox_Xpath"))
					 Reporter.reportStep("Element could not be set in scroll view", "FAILURE");
			     
			     if(!snW.selectByVisibleTextByXpath("Runbook_Tempelates_step_Searchbox_Xpath", "Step"))
			      Reporter.reportStep("Step 12: Step could not be selected in the searchbox field", "FAILURE");
			     
			     if(!snW.enterByXpathAndClick("Runbook_Tempelates_step_Searchbox_Value_Xpath", ChildName))
			       Reporter.reportStep("Step 12:Child name was not entered to search field", "FAILURE");
			     
			     snW.Wait(2000);
			     // Validate the Ticket Number.
			     
			       String ValidateChildStep = snW.getTextByXpath("Runbook_Templates_FirstChildStep_Xpath");
			       System.out.println(ValidateChildStep);
			       if(ValidateChildStep.equalsIgnoreCase(ChildName))
			     Reporter.reportStep("Step 12: New child Step is added successfully", "SUCCESS");
			    else
			     Reporter.reportStep("Step 12: New child Step is not added successfully", "FAILURE");
			     
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
		@DataProvider(name = "RB_STRY0011154_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011154_TC01");
			return arrayObject;
		}
}

