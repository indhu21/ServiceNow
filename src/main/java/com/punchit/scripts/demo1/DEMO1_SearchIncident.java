package com.punchit.scripts.demo1;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import testng.SuiteMethods_1;
import testng.SuiteMethods_demo1;
import utils.DataInputProvider;
import utils.Reporter;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class DEMO1_SearchIncident extends SuiteMethods {

	
			// Create Instance
			ServiceNowWrappers snW;

			@Test(dataProvider = "DEMO1_STRY0000005_TC01",groups="DemoIncident")
			public void searchIncident (String regUser, String regPwd, String valid,
					String invalid) throws COSVisitorException, IOException {
				
				// Prerequisites
				snW = new ServiceNowWrappers(entityId);
				
				try {
					
					// Step 0: Launch the application
					snW.launchApp(browserName, true);

					// Step 1: Log in to application
					if (snW.login(regUser, regPwd))
						Reporter.reportStep("The login with username:"+ regUser + " is successful", "SUCCESS");
					else
						Reporter.reportStep("The login with username:"+ regUser + " is not successful", "FAILURE");
					
//					snW.clickByXpath("Banner_Min_Xpath");
//					snW.Wait(500);
//					snW.clickByXpath("Banner_Min_Xpath");
//					
//					snW.switchToFrame("Frame_Nav");
//					snW.Wait(3000);
//					
//					if(snW.clickLink("INCMENU_ALL"))
//						Reporter.reportStep("Step 2: The All incidents menu selected successfully","SUCCESS");
//					else if(snW.clickById("INCMENU_ALLMENU_Id")){
//						snW.clickLink("INCMENU_ALL");
//						Reporter.reportStep("Step 2: The All incidents menu selected successfully","SUCCESS");
//					}else
//						Reporter.reportStep("Step 2: The All incidents menu could not be selected","FAILURE");
					if (snW.selectMenuFromMainHeader("Incident", "INCMENU_ALL"))
						Reporter.reportStep("The All - menu selected successfully","SUCCESS");
					else
						Reporter.reportStep("The All - menu could not be selected","FAILURE");
					
					snW.Wait(2000);
					snW.switchToFrame("Frame_Main");
					
/*					String supname = "Number";
					if(!snW.selectByVisibleTextByXpath("Search_Key_Xpath", supname))
						Reporter.reportStep("Incident - Search Key could not be selected","FAILURE");
					snW.Wait(500);
	*/				if(!snW.enterAndChoose("Demo_SearchReferenceData_Xpath", valid))
						Reporter.reportStep("Incident -" +valid+ " could not be entered in search box","FAILURE");
	                snW.PresEnter();
	                snW.Wait(3000);
					
					if(snW.getTextByXpath("FirstLink_Xpath").equals(valid))
						Reporter.reportStep("Incident - "+valid+ " found","SUCCESS");
					else
						Reporter.reportStep("Incident - "+valid+ " is not found","FAILURE");
					
					if(!snW.enterAndChoose("Demo_SearchReferenceData_Xpath", invalid))
						Reporter.reportStep("Incident -" +invalid+ " could not be entered in search box","FAILURE");
					snW.PresEnter();
					snW.Wait(3000);
					
					if(snW.getTextByXpath("FirstLink_Xpath").equals(invalid))
						Reporter.reportStep("Incident - "+invalid+ " found, hence failed","FAILURE");
					else
						Reporter.reportStep("Incident - "+invalid+ " is not found as expected","SUCCESS");
					
					snW.switchToDefault();
					
					if (!snW.clickByXpath("Logout_Xpath"))
						Reporter.reportStep("The Log out could not be clicked","FAILURE");

					// Wait for few seconds
					snW.Wait(5000);
					
					status="PASS";
						
					
				} finally {
					snW.switchToDefault();
					if(snW.isExistByXpath("Logout_Xpath"))
						{if (!snW.clickByXpath("Logout_Xpath"))
						Reporter.reportStep("The Log out could not be clicked","FAILURE");}
					snW.quitBrowser();
					}

		}

			@DataProvider(name = "DEMO1_STRY0000005_TC01")
			public Object[][] fetchData() throws IOException {
				Object[][] arrayObject = DataInputProvider.getSheet("DEMO1_STRY0000005_TC01");
				return arrayObject;
			}
}
