package com.punchit.scripts.gileadrb;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLRB_STRY0011050_TC01 extends SuiteMethods {
ServiceNowWrappers snW;
	
	@Test(dataProvider = "RB_STRY0011050_TC01",groups="OpsDirector")
	public void appProperties(String regUser, String regPwd) throws COSVisitorException, IOException, InterruptedException {
		
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
			
			snW.Wait(2000);
			// Step 2: In application navigator expand OpsDirector/Registration to select Assigned_to_me
			if (snW.selectMenuFromMainHeader("RunBook", "Assigned_to_me"))
				Reporter.reportStep("Step 2: The Assigned_To_Me - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Assigned_To_Me - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			
			String Runbookno=snW.getTextByXpath("RunBook_Unassigned_firstrunbbok_xpath");
			System.out.println("Run book is " +Runbookno);
			
			// Right click 
			if(snW.rightClickByXpath("RunBook_Unassigned_firstrunbbok_xpath"))
				Reporter.reportStep("Step 3: Right click on the Runbook is successful ","SUCCESS");
			else
			{   status = "INSUFFICIENT DATA";
				Reporter.reportStep("Step 3: Right click on the Runbook could not be performed ","FAILURE");
			}
			
			
			//Take run book
			if(!snW.clickByXpath("RunBook_assignedtome_rightclicTakeRunbook_xpath"))
						Reporter.reportStep("Step 4: Take Runbook could not be selected","FAILURE");
			 
			// check if run book steps are displayed
			String step=snW.getTextByXpath("RunBook_assigntome_takerunbbokpage_stepcolumn_xpath");
			System.out.println("text is "+step);
	        
			if(step.equalsIgnoreCase("Step"))
			Reporter.reportStep("Step 4:Take Runbook is selected and the page with steps of selected Runbook is displayed","SUCCESS");
			else
			Reporter.reportStep("Step 4: Runbook page with steps of selected Runbook is  not displayed","FAILURE");
	        	
			// Step 6: In application navigator expand OpsDirector/Registration to select Assigned_to_me
			if (snW.selectMenuFromMainHeader("RunBook", "Assigned_to_me"))
				Reporter.reportStep("Step 5: The Assigned_To_Me - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 5: The Assigned_To_Me - menu could not be selected","FAILURE");
			
			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			
			// step 7 : click on Second run book
			
			if(!snW.clickByXpath("RunBook_Unassigned_firstrunbbok_xpath"))
			{
				status = "INSUFFICIENT DATA";
				Reporter.reportStep("Step 6: Runbook could not be clicked ","FAILURE");
			}
			// click on take run book button
			if(snW.isExistById("RunBook_assigntome_takerunbookbuton_id"))
				Reporter.reportStep("Step 6: RunBook is selected successfully and Take Runbook button is displayed","SUCCESS");
			else
				Reporter.reportStep("Step 6: RunBook is selected successfully and Take Runbook button is not displayed ","FAILURE");
			
				
			if(!snW.clickById("RunBook_assigntome_takerunbookbuton_id"))
				Reporter.reportStep("Step 7: Runbook is selected and  Take Runbook  button is displayed ","FAILURE");
			
			
			// validate if run book opens 
			String step1=snW.getTextByXpath("RunBook_assigntome_takerunbbokpage_stepcolumn_xpath");
			System.out.println("text is "+step1);
	        
			if(step1.equalsIgnoreCase("Step"))
			Reporter.reportStep("Step 7:Take Runbook button is clicked and the page with steps of selected Runbook is displayed successfully","SUCCESS");
			else
			Reporter.reportStep("Step 7:Take Runbook button is clicked but the page with steps of selected Runbook is  not displayed","FAILURE");
			
				
			// go out of the frame
			snW.switchToDefault();

			// Log out
			if(!snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("The logout Failed", "FAILURE");


			status = "PASS";
		//	
            

		}
		finally{
			// close the browser
		snW.quitBrowser();		
		}
	}
		@DataProvider(name = "RB_STRY0011050_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011050_TC01");
			return arrayObject;
		}
}
	




