package com.punchit.scripts.gileadrb;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLRB_STRY0011059_TC01 extends SuiteMethods{
ServiceNowWrappers snW;
	
	@Test(dataProvider = "RB_STRY0011059_TC01",groups="OpsDirector")
	public void appProperties(String regUser, String regPwd,String text1,String text2) throws COSVisitorException, IOException, InterruptedException {
		
		// Prerequisites
		snW = new ServiceNowWrappers(entityId);
		
		try {
			
			if (snW.launchApp(browserName, true))
				Reporter.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
			else
				Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

			//			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");

			//			// Step 2: check Data menu
			if (snW.selectMenu("RunBook","Admin", "Properties"))
				Reporter.reportStep("Step 2: The Properties -  menu Selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Properties -  menu could not Selected ","FAILURE");
			
			snW.switchToFrame("Frame_Main");
			
            // step 3:check for the texts 
			String x=snW.getTextByXpath("RunBook_Properties_showcancelbuttontext_xpath");
			System.out.println("text is "+ x);
			if(!x.equalsIgnoreCase(text1))
				Reporter.reportStep("Step 3: The option Show Cancel Button in Take Runbook is not available","FAILURE");
			String x1=snW.getTextByXpath("RunBook_Properties_RaiseIncidenttext_xpath");
			System.out.println("text is "+ x1);
			if(x1.equalsIgnoreCase(text2))
				Reporter.reportStep("Step 3: The options Show Cancel Button... and Raise Incident Mandatory .... are available hence passed ","SUCCESS");
			else
				Reporter.reportStep("Step 3: The option Show Cancel Button... or Raise Incident Mandatory .... is not  available hence Failed ","FAILURE");
			
			snW.switchToDefault();
			//
			//Log out
			if(!snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("The logout Failed", "FAILURE");

			status = "PASS";
						
					}
		finally{
			// close the browser
			snW.quitBrowser();		
		}
	}
	@DataProvider(name = "RB_STRY0011059_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011059_TC01");
		return arrayObject;
	}
}



