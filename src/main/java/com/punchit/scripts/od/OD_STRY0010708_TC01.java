package com.punchit.scripts.od;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class OD_STRY0010708_TC01 extends SuiteMethods {
	
	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "OD_STRY0010708_TC01",groups="OpsDirector")
	public void createCIScope(String regUser, String regPwd) throws COSVisitorException,
			IOException {
		// Pre-requisities
		snW = new ServiceNowWrappers(entityId);
		try {

			// Step 0: Launch the application
			if (snW.launchApp(browserName, true))
				Reporter.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
			else
				Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");
		
			// Step 2: In application navigator expand Ops Consoles to select Alert Console
			if (snW.selectMenu("Ops_Director","Ops Consoles", "ALERT_CONSOLE"))
				Reporter.reportStep("Step 2: Alert Console  - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: Alert Console - menu could not be selected","FAILURE");

			// Switch to the main frame
			 snW.switchToFrame("Frame_Main");
			 
			//Step 3: Click on My Alerts
			 //if(snW.clickByXpath("MY_ALERT_Xpath"))
				 if(snW.clickLink("NEW_ALERTS_LINK"))
				 Reporter.reportStep("Step 3: Alert Console - New alert selected successfully","SUCCESS");
				else
					Reporter.reportStep("Step 3: Alert Console - New alert could not be selected","FAILURE");
			//Step 4: Click on Funnel icon
			 if(snW.clickById("ALERT_FunnelIcon_Id"))
				 Reporter.reportStep("Step 4: New Alert - Funnel selected successfully","SUCCESS");
				else
					Reporter.reportStep("Step 4: New Alert - Funnel could not be selected","FAILURE");
			 
			//Step 5: To check the State is Flapping or state is new 
				
			 String filter_values = snW.getTextByXpath("FUNNEL_FILTER_Xpath");
				System.out.println("Filter elements="+filter_values);
				
				String[] arrSplit=filter_values.split(">");
				
				for (int i=0; i<arrSplit.length;i++)
				{
					System.out.println("Splited array list - "+arrSplit[i]);
					if ("State = Flapping .or. State = New".equals(arrSplit[i]))
					{
					Reporter.reportStep("Step 5:State is Flapping or state is New","SUCCESS");
					break;
					}
					
				else if(i==arrSplit.length)
				{
					Reporter.reportStep("Step 5:State is Flapping or state is New filter is not present","FAILURE");  
				}
				}
				
				status = "PASS";
				
				
	} finally {
		// close the browser
		snW.quitBrowser();
	}
	}	
		@DataProvider(name = "OD_STRY0010708_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("OD_STRY0010708_TC01");
			return arrayObject;
		}
		}


