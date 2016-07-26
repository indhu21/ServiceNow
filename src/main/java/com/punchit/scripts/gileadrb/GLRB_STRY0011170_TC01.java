package com.punchit.scripts.gileadrb;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLRB_STRY0011170_TC01 extends SuiteMethods {
ServiceNowWrappers snW;
	
	@Test(dataProvider = "RB_STRY0011170_TC01",groups="OpsDirector")
	public void Manual_Runbook(String regUser, String regPwd) throws COSVisitorException, IOException, InterruptedException {
		
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
			
			// step 2 :check if Schedules link is available
			if (snW.selectMenu("RunBook","RunBook_Definition", "Schedules"))
				Reporter.reportStep("Step 2: The Schedules  - menu selected successfully ","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Schedules  - menu is not  displayed ","FAILURE");	
						
			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			 
			// step 3: click on Existing schedules
			if(snW.clickByXpath("RunBook_Unassigned_firstrunbbok_xpath"))
			    Reporter.reportStep("Step 3: Schedule is clicked ","SUCCESS");
			else
				Reporter.reportStep("Step 3: Schedule ink could not be clicked ","FAILURE");	
					
			//Step 4: Click on manual run book button
			if(snW.clickById("Schedule_ManualRunbook_ID"))
				Reporter.reportStep("Step 4: Manual runbook is clicked ","SUCCESS");
			else
				Reporter.reportStep("Step 4: Manual runbook could not be clicked ","FAILURE");	
			
			//snW.Wait(2000);
			//Step 5: Runbook open 
			String element=snW.getTextByXpath("ManualRunbook_PageOpenStep_Xpath");
			System.out.println(element);
			if(element.equalsIgnoreCase("Step"))
				Reporter.reportStep("Step 5:Runbook is open ","SUCCESS");
			else
				Reporter.reportStep("Step 5: Runbook is not open","FAILURE");	
	
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
		@DataProvider(name = "RB_STRY0011170_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011170_TC01");
			return arrayObject;
		}
}
