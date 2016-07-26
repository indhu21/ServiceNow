package com.punchit.scripts.rb;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class RB_STRY0011049_TC01 extends SuiteMethods {
ServiceNowWrappers snW;
	
	@Test(dataProvider = "RB_STRY0011049_TC01",groups="OpsDirector")
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
				Reporter.reportStep("The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("The login with username:"+ regUser + " is not successful", "FAILURE");
			
			snW.Wait(3000);
			// Step 2: In application navigator expand OpsDirector/Registration to select Assigned_to_me
			if (snW.selectMenuFromMainHeader("RunBook", "Unassigned"))
				Reporter.reportStep("The Unassigned - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("The Unassigned - menu could not be selected","FAILURE");

			
			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
		 
			String Runbookno=snW.getTextByXpath("RunBook_Unassigned_firstrunbbok_xpath");
			System.out.println("Run book is " +Runbookno);
			
			// Right click 
			if(snW.rightClickByXpath("RunBook_Unassigned_firstrunbbok_xpath"))
				Reporter.reportStep("Right click on the Runbook is successful ","SUCCESS");
			else
			{   status = "INSUFFICIENT DATA";
				Reporter.reportStep("Right click on the Runbook could not be performed ","FAILURE");
			}
			
			//Assign to me
			if(snW.clickByXpath("RunBook_Unassigned_rightclickassigntome_xpath"))
			Reporter.reportStep("Runbook is assigned to me successfully ","SUCCESS");
			else
			Reporter.reportStep("Runbook could not be assigned to me  ","FAILURE");
	
		//	
            
			// Search for the Runbook
			snW.enterByXpath("RunBook_Unassigned_SearchonNumer_xpath", Runbookno);
			snW.PresEnter();
			String message=snW.getTextByXpath("RunBook_Unassigned_NoRecordstoDisplay_xpath");
			System.out.println(message);
			if(message.equalsIgnoreCase("No records to display"))
				Reporter.reportStep("Runbook is assigned to me since it is not visible in Unassgined Runbook list ","SUCCESS");
			else
			    Reporter.reportStep("Runbook is not assigned to me since it can be looked in Unassgined Runbook list ","FAILURE");
			
			
			// click on assigned to me 
			if (snW.selectMenu("RunBook", "Assigned_to_me"))
				Reporter.reportStep("The Assigned_To_Me - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("The Assigned_To_Me - menu could not be selected","FAILURE");
			snW.switchToFrame("Frame_Main");
			
			snW.enterByXpath("RunBook_Unassigned_SearchonNumer_xpath", Runbookno);
			snW.PresEnter();
			
			// Right click 
			String message1=snW.getTextByXpath("RunBook_Unassigned_firstrunbbok_xpath");
			if(message1.equalsIgnoreCase(Runbookno))
				Reporter.reportStep("Runbook can be looked in Assigned to me , hence passed  ","SUCCESS");
			else
				Reporter.reportStep("Runbook could not be looked in Assigned to me hence failed  ","FAILURE");
	       
			// go out of the frame
			snW.switchToDefault();

			// Log out
			if(!snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("The logout Failed", "FAILURE");


			status = "PASS";
		}
		
		finally{
			// close the browser
		snW.quitBrowser();		
		}
	}
		@DataProvider(name = "RB_STRY0011049_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011049_TC01");
			return arrayObject;
		}
}
	





