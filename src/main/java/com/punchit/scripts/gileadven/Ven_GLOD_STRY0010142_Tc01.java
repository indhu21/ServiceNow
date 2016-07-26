package com.punchit.scripts.gileadven;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_STRY0010142_Tc01 extends SuiteMethods {
	// Create Instance
				ServiceNowWrappers snW;

				@Test(dataProvider = "Ven_GLOD_STRY0010142_Tc01",groups="OpsDirector")
				public void appProperties(String regUser, String regPwd, String value) throws COSVisitorException, IOException {
					
					// Prerequisites
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
						
						// Step 2: In application navigator expand OpsDirector/Administration to select Application Properties
						if (snW.selectMenu("Ops_Director", "Administration", "Application_Properties"))
							Reporter.reportStep("Step 2: The Application Properties menu selected successfully","SUCCESS");
						else
							Reporter.reportStep("Step 2: The Application Properties menu could not be selected","FAILURE");

						// Switch to the main frame
						snW.switchToFrame("Frame_Main");
						if (snW.enterById("History", value))
							Reporter.reportStep("Step 3: The History property value Entered successfully","SUCCESS");
						else
							Reporter.reportStep("Step 3: The History property value could not be Entered","FAILURE");
						
						if(snW.clickByXpath("Save_Xpath"))
							Reporter.reportStep("Step 4: Save clicked","SUCCESS");
						else
							Reporter.reportStep("Step 4: Property save failed","FAILURE");

											
						// Step 3: Change the value of 'Stale' property to null
						if (snW.enterById("History", ""))
							Reporter.reportStep("Step 5: The History property value cleared successfully","SUCCESS");
						else
							Reporter.reportStep("Step 5: The History property value could not be cleared","FAILURE");
						
						if(snW.clickByXpath("Save_Xpath"))
							Reporter.reportStep("Step 6: Save clicked","SUCCESS");
						else
							Reporter.reportStep("Step 6: Property save failed","FAILURE");
						
						// Step 4: Check the default value of the History property
						snW.scrollToElementById("History");
						String defval = snW.getAttributeById("History", "value");
						if("7".equals(defval))
							Reporter.reportStep("Step 7: Default value of History property as expected","SUCCESS");
						
						else
							Reporter.reportStep("Step 7: Default value of History property not as expected","FAILURE");
						
						/*
						// Step 5: Change the value of 'Stale' property to 
						if (snW.enterById("History", value))
							Reporter.reportStep("Step 5: The History property value Entered successfully","SUCCESS");
						else
							Reporter.reportStep("Step 5: The History property value could not be Entered","FAILURE");
						
						if(snW.clickByXpath("Save_Xpath"))
							Reporter.reportStep("Step 5a: Save clicked","SUCCESS");
						else
							Reporter.reportStep("Step 5a: Property save failed","FAILURE");
*/
						// go out of the frame
				        snW.switchToDefault();

				        // Log out
				        if(!snW.clickByXpath("Logout_Xpath"))
				         Reporter.reportStep("The logout Failed", "FAILURE");

				        status="PASS";
						
					
				} finally {
					// close the browser
					snW.quitBrowser();
				}

			}


				@DataProvider(name = "Ven_GLOD_STRY0010142_Tc01")
				public Object[][] fetchData() throws IOException {
					Object[][] arrayObject = DataInputProvider.getSheet("Ven_GLOD_STRY0010142_Tc01");
					return arrayObject;
				}
	}

