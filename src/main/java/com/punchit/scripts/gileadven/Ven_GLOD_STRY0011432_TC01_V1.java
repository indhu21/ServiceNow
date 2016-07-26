package com.punchit.scripts.gileadven;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_STRY0011432_TC01_V1 extends SuiteMethods {
	
				// Create Instance
				ServiceNowWrappers snW;

				@Test(dataProvider = "Ven_GLOD_STRY0011432_TC01_V1",groups="OpsDirector")
				public void profreg (String regUser, String regPwd, 
						String tcon, String sevded, String auto) 
								throws COSVisitorException, IOException {
					
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
						
						// Step 2: In application navigator expand OpsDirector/Registration to select Profile Registration
						if (snW.selectMenu("Ops_Director", "Registration", "Prof_Reg"))
							Reporter.reportStep("Step 2: The Profile Registration menu selected successfully","SUCCESS");
						else
							Reporter.reportStep("Step 2: The Profile Registration menu could not be selected","FAILURE");
						
						// Switch to the main frame
						snW.switchToFrame("Frame_Main");
						
						// Step 3: Check default value of trigger condition
						String trigDef = snW.getDefaultValueById("trigCon");
						if (tcon.equals(trigDef))
							Reporter.reportStep("Step 3: Trigger Condition default value as expected","SUCCESS");
						else
							Reporter.reportStep("Step 3: Trigger Condition default value not as expected","FAILURE");
						
						// Step 4: Check default value of severity deduplication
						String sevDef = snW.getDefaultValueById("sevDedup");
						if (sevded.equals(sevDef))
							Reporter.reportStep("Step 4: Severity Deduplication default value as expected","SUCCESS");
						else
							Reporter.reportStep("Step 4: Severity Deduplication default value not as expected","FAILURE");
						
						// Step 5: Check default value of auto close
						String autoDef = snW.getDefaultValueById("autoClose");
						if (auto.equals(autoDef))
							Reporter.reportStep("Step 5: Auto Close default value as expected","SUCCESS");
						else
							Reporter.reportStep("Step 5: Auto Close default value not as expected","FAILURE");
						
						// Step 6: Fill the fields and click submit
						snW.enterById("Name", "Punch Profile 104");
						snW.selectByVisibleTextById("trigCon", "All");
						if(snW.doubleCickByXpath("ALERTPROFILE_Registartion_CIscopes_xpath"))
							Reporter.reportStep("Step 6:  CI Scope is selected successfully", "SUCCESS");
						else
							Reporter.reportStep("Step 6: CI Scope could not be selected", "FAILURE");
						snW.selectByVisibleTextById("Dy_Inc_Asn_Grp", "No");
						if(snW.enterAndChoose("Alert_Profile_IncidentAssignmentGroup_Xpath_new_sparc", "**"))
							Reporter.reportStep("Step 6a: Incident Assignment Group selected", "SUCCESS");
						else
							Reporter.reportStep("Step 6a: Incident Assignment Group not selected", "FAILURE");

						if(snW.enterAndChoose("Alert_Profile_OwningGroup_Xpath_new_sparc", "**"))
							Reporter.reportStep("Step 6b: Owning Group selected", "SUCCESS");
						else
							Reporter.reportStep("Step 6b: Owning Group could not be selected", "FAILURE");
						
						
						snW.selectByVisibleTextById("Alert_Reaction", "Run Runbook");
						if(!snW.enterById("Description", "Test ticket for STRY0010104"))
							Reporter.reportStep("Description could not be entered", "FAILURE");
						snW.Wait(5000);
						
					//	Reporter.reportStep("Step 6: Values entered in the fields as per reference data","SUCCESS");
						
						snW.clickById("submit_button");
						snW.Wait(5000);
						
						String profnum = snW.getAttributeById("Prof_Num", "value");
						if (profnum != null)
							Reporter.reportStep("Step 7: Profile created successfully","SUCCESS");
						else
							Reporter.reportStep("Step 7: Profile creation failed","FAILURE");
						
						status = "PASS";
					
					} finally {
						// close the browser
						snW.quitBrowser();
					}

				}
				
				@DataProvider(name = "Ven_GLOD_STRY0011432_TC01_V1")
				public Object[][] fetchData() throws IOException {
					Object[][] arrayObject = DataInputProvider.getSheet("Ven_GLOD_STRY0011432_TC01_V1");
					return arrayObject;
				}
}
