package com.punchit.scripts.rb;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class RB_STRY0011057_TC01 extends SuiteMethods {

ServiceNowWrappers snW;
	
	@Test(dataProvider = "RB_STRY0011057_TC01",groups="OpsDirector")
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
			
			// Step 2: In application navigator expand OpsDirector/Registration to select Assigned_to_me
			if (snW.selectMenuFromMainHeader("RunBook", "RunBook_Definition"))
				Reporter.reportStep("RunBook_Definition - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("RunBook_Definition - menu could not be selected","FAILURE");
						
			// Step 2: check Data menu
			if (!snW.selectMenu("RunBook","RunBook_Definition", "Data"))
/*				Reporter.reportStep("The Data  menu is a available","SUCCESS");
			else
*/				Reporter.reportStep("The Data  menu is not  available","FAILURE");

			
			// Step 2: check steps menu
			if (!snW.selectMenu("RunBook","RunBook_Definition", "Steps"))
/*				Reporter.reportStep("The Steps  menu is a available","SUCCESS");
			else
*/				Reporter.reportStep("The Steps  menu is not  available","FAILURE");
			
			// step 4:new active window menu 
			
			if (!snW.selectMenu("RunBook","RunBook_Definition", "New_Active_Window"))
/*				Reporter.reportStep("The New Active Window  menu is a available","SUCCESS");
			else
*/				Reporter.reportStep("The New Active Window  menu is not  available","FAILURE");
			
            // step 5 : Active windows
			if (snW.selectMenu("RunBook","RunBook_Definition", "Active_Windows"))
				Reporter.reportStep("The Data, Step, New Active Window and Active Window  menu is a available","SUCCESS");
			else
				Reporter.reportStep("The Data, Step, New Active Window are available expect Active Window  menu which is not  available","FAILURE");
			
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
		@DataProvider(name = "RB_STRY0011057_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011057_TC01");
			return arrayObject;
		}
}

