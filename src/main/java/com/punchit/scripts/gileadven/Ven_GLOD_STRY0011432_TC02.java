package com.punchit.scripts.gileadven;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_STRY0011432_TC02 extends SuiteMethods {
	
	// Create Instance
			ServiceNowWrappers snW;

			@Test(dataProvider = "Ven_GLOD_STRY0011432_TC02",groups="OpsDirector")
			public void profreg(String regUser, String regPwd, String verUser, String verPwd,
					String tcon, String sevded, String auto, String cscope, String owgp,
					String alre) throws COSVisitorException, IOException {
				
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
					System.out.println("Severity deduplication is "+ sevDef);
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
					
					// Step 6: Check whether CI Scope is mandatory or not
					String ciscope = snW.getAttributeByXpath("ALERTPROFILE_Registartion_CIscopes_mendatory_xpath", cscope);
					if ("true".equals(ciscope))
						Reporter.reportStep("Step 6: CI Scope is mandatory","SUCCESS");
					else
						Reporter.reportStep("Step 6: CI Scope is not mandatory","FAILURE");
					
					// Step 7: Check whether Owning Group is mandatory or not
					String owngrp = snW.getAttributeById("owningGroup", owgp);
					if ("true".equals(owngrp))
						Reporter.reportStep("Step 7: Owning Group is mandatory","SUCCESS");
					else
						Reporter.reportStep("Step 7: Owning Group is not mandatory","FAILURE");
					
					// Step 8: Check whether Alert Reaction is mandatory or not
					String alreac = snW.getAttributeById("alertReaction", alre);
					if ("true".equals(alreac))
						Reporter.reportStep("Step 8: Alert Reaction is mandatory","SUCCESS");
					else
						Reporter.reportStep("Step 8: Alert Reaction is not mandatory","FAILURE");
					
					// Step 9: Fill the fields and click submit
					snW.enterById("Name", "Punch Profile 104");
					if(snW.doubleCickByXpath("ALERTPROFILE_Registartion_CIscopes_xpath"))
						Reporter.reportStep("Step 9:  CI Scope is selected successfully", "SUCCESS");
						else
						Reporter.reportStep("Step 9: CI Scope could not be selected", "FAILURE");
					snW.selectByVisibleTextById("Dy_Inc_Asn_Grp", "Yes");
					snW.selectByVisibleTextById("Inc_Asn_Area", "Infrastructure");
					if(snW.enterAndChoose("Alert_Profile_OwningGroup_Xpath_new_sparc", "**"))
						Reporter.reportStep("Step 9a: Owning group is selected successfully", "SUCCESS");
					else
						Reporter.reportStep("Step 9a: Owning group could not be selected", "FAILURE");
					snW.selectByVisibleTextById("Alert_Reaction", "Run Runbook");
					if(!snW.enterById("Description", "Test ticket for STRY0010104"))
						Reporter.reportStep("Description could not be entered", "FAILURE");
					snW.Wait(5000);
					
				//	Reporter.reportStep("Step 9: Values entered successfull","SUCCESS");
					
					snW.clickById("submit_button");
					snW.Wait(5000);
					
					String profnum = snW.getAttributeById("Prof_Num", "value");
					if (profnum != null)
						Reporter.reportStep("Step 10: Profile created successfully","SUCCESS");
					else
						Reporter.reportStep("Step 10: Profile creation failed","FAILURE");
					
					snW.switchToDefault();

					// Step 10: Log out
					if (snW.clickByXpath("Logout_Xpath"))
						Reporter.reportStep("Step 11: The Log out is clicked successfully","SUCCESS");
					else
						Reporter.reportStep("Step 11: The Log out could not be clicked","FAILURE");
					
					// Wait for few seconds
					snW.Wait(5000);
					
					status = "PASS";	
					
									
				} finally {
				// close the browser
				snW.quitBrowser();
				}
			}

			
			@DataProvider(name = "Ven_GLOD_STRY0011432_TC02")
			public Object[][] fetchData() throws IOException {
				Object[][] arrayObject = DataInputProvider.getSheet("Ven_GLOD_STRY0011432_TC02");
				return arrayObject;
			}
}