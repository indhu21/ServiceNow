package com.punchit.scripts.demo;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import testng.SuiteMethods_1;
import utils.DataInputProvider;
import utils.Reporter;
import utils.Reporter1;
import wrapper.ServiceNowWrappers;

public class DEMO_SearchIncident extends SuiteMethods_1 {

	
			// Create Instance
			ServiceNowWrappers snW;

			@Test(dataProvider = "DEMO_STRY0000005_TC01",groups="DemoIncident")
			public void searchIncident (String regUser, String regPwd, String valid,
					String invalid) throws COSVisitorException, IOException {
				
				// Prerequisites
				snW = new ServiceNowWrappers(entityId);
				
				try {
					
					// Step 0: Launch the application
					snW.launchApp(browserName, true);

					// Step 1: Log in to application
					if (snW.login(regUser, regPwd))
						Reporter1.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
					else
						Reporter1.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");
					
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
						Reporter1.reportStep("Step 2: The All - menu selected successfully","SUCCESS");
					else
						Reporter1.reportStep("Step 2: The All - menu could not be selected","FAILURE");
					
					
					snW.switchToFrame("Frame_Main");
					
					String supname = "Number";
					if(!snW.selectByVisibleTextByXpath("Search_Key_Xpath", supname))
						Reporter1.reportStep("Step 3: Incident - Search Key could not be selected","FAILURE");
					snW.Wait(500);
					if(!snW.enterAndChoose("Header_SearchBox_Xpath", valid))
						Reporter1.reportStep("Step 3: Incident -" +valid+ " could not be entered in search box","FAILURE");
					snW.Wait(3000);
					
					if(snW.getTextByXpath("First_Searched_Record_Xpath").equals(valid))
						Reporter1.reportStep("Step 3: Incident - "+valid+ " found","SUCCESS");
					else
						Reporter1.reportStep("Step 3: Incident - "+valid+ " is not found","FAILURE");
					
					if(!snW.enterAndChoose("Header_SearchBox_Xpath", invalid))
						Reporter1.reportStep("Step 4: Incident -" +invalid+ " could not be entered in search box","FAILURE");
					snW.Wait(3000);
					
					if(snW.getTextByXpath("First_Searched_Record_Xpath").equals(invalid))
						Reporter1.reportStep("Step 4: Incident - "+invalid+ " found, hence failed","FAILURE");
					else
						Reporter1.reportStep("Step 4: Incident - "+invalid+ " is not found as expected","SUCCESS");
					
					snW.switchToDefault();
					
					if (!snW.clickByXpath("Logout_Xpath"))
						Reporter1.reportStep("Step 5: The Log out could not be clicked","FAILURE");

					// Wait for few seconds
					snW.Wait(5000);
					
					status="PASS";
						
					
				} finally {
					// close the browser
					snW.quitBrowser();
					}

		}

			@DataProvider(name = "DEMO_STRY0000005_TC01")
			public Object[][] fetchData() throws IOException {
				Object[][] arrayObject = DataInputProvider.getSheet("DEMO_STRY0000005_TC01");
				return arrayObject;
			}
}
