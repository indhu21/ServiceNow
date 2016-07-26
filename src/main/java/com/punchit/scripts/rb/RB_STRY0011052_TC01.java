package com.punchit.scripts.rb;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class RB_STRY0011052_TC01 extends SuiteMethods {
ServiceNowWrappers snW;
	
	@Test(dataProvider = "RB_STRY0011052_TC01",groups="OpsDirector")
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
			
			snW.Wait(2000);
			// Step 2: check if My_Template Link is available
			if (snW.selectMenuFromMainHeader("RunBook", "My_Template"))
				Reporter.reportStep("The My Template  - menu is displayed ","SUCCESS");
			else
				Reporter.reportStep("The My Template  - menu is not  displayed ","FAILURE");

			// Switch to the main frame
			//snW.switchToFrame("Frame_Main");
			 
			// step 3 :check if My Schedules link is available
			if (snW.selectMenuFromMainHeader("RunBook", "My_Schedules"))
				Reporter.reportStep("The My Schedule  - menu is displayed ","SUCCESS");
			else
				Reporter.reportStep("The My Schedule  - menu is not  displayed ","FAILURE");
			
			// step 5 :check if Templates link is available
			if (snW.selectMenu("RunBook","RunBook_Definition", "Templates"))
				Reporter.reportStep("The Templates  - menu is displayed ","SUCCESS");
			else
				Reporter.reportStep("The Templates  - menu is not  displayed ","FAILURE");	
			//
/*			if (snW.selectMenu("RunBook","RunBook_Definition", "Schedules"))
				Reporter.reportStep("Step 5: The Schedules  - menu is displayed ","SUCCESS");
			else
				Reporter.reportStep("Step 5: The Schedules  - menu is not  displayed ","FAILURE");	
	*/		// go out of the frame
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
		@DataProvider(name = "RB_STRY0011052_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011052_TC01");
			return arrayObject;
		}
}
	







