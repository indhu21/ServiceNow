package com.punchit.scripts.od;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class OD_STRY0010121_TC01_V1 extends SuiteMethods {
	// Create Instance
			ServiceNowWrappers snW;

			@Test(dataProvider = "OD_STRY0010121_TC01_V1",groups="OpsDirector")
			public void createCIScope(String regUser, String regPwd, String name,
					String shortDescription, String filter, String owningGroup,
					String f2Section, String f2Condition, String f2Value,
					String verUser, String verPwd,String CI_Class) throws COSVisitorException,
					IOException {

				// Pre-requisities
				snW = new ServiceNowWrappers(entityId);

				try {

					// Step 0: Launch the application
					snW.launchApp(browserName, true);
						

					// Step 1: Log in to application
					if (snW.login(regUser, regPwd))
						Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
					else
						Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");

					// Step 2: In application navigator expand OpsDirector/Registration to select CI Scope Registration
					if (snW.selectMenu("Ops_Director","Registration", "CIS_Registration"))
						Reporter.reportStep("Step 2: The CI Scope Registration - menu selected successfully","SUCCESS");
					else
						Reporter.reportStep("Step 2: The CI Scope Registration - menu could not be selected","FAILURE");

					// Switch to the main frame
					snW.switchToFrame("Frame_Main");

					// Step 3: Fill in Name and Short Description from reference data
					if (!snW.enterById("CIS_Name_Id", name))
						Reporter.reportStep("Step 3: The CI Scope Name - could not be entered","FAILURE");

					if (snW.enterById("CIS_ShortDesc_Id", shortDescription))
						Reporter.reportStep("Step 3: The CI Scope name:" + name	+ " and description:" + shortDescription + " entered successfully", "SUCCESS");
					else
						Reporter.reportStep("Step 3: The CI Scope description could not be entered","FAILURE");

					// Step 4: Choose Type as Static Filter in the drop down
					if (snW.selectByVisibleTextById("CIS_Filter_Id", filter))
						Reporter.reportStep("Step 4: The CI Scope Filter has been selected successfully with value:"+ filter, "SUCCESS");
					else
						Reporter.reportStep("Step 4: The CI Scope Filter could not be selected","FAILURE");

					// Step 5: Choose Owning Group as Punch Group from the list that
					// appears by clicking the Search icon
					if (snW.enterAndChoose("CIS_OwningGroup_Xpath", owningGroup))
						Reporter.reportStep("Step 5: The Owning Group has been selected successfully with value:"+ owningGroup, "SUCCESS");
					else
						Reporter.reportStep("Step 5: The Owning Group could not be selected","FAILURE");

					// Step 6: Click submit and switch to the frame in new window
					if (snW.clickById("CIS_SubmitButton_Id"))
						Reporter.reportStep("Step 6: The submit button has been clicked successfully","SUCCESS");
					else
						Reporter.reportStep("Step 6: The submit button could not be clicked","FAILURE");

					// Step 6B: Read the scope number
					String scopeNum = snW.getAttributeById("CIS_ScopeNumber_Id", "value");
					snW.Wait(5000);
					
					//Step 7: Select CI Class Windows Service [cmdb_ci_windows_service]
					if(snW.selectByVisibleTextById("CI_CLASS_ID", CI_Class))
						Reporter.reportStep("Step 7: The CI Class has been selected successfully with value "+CI_Class,"SUCCESS");
					else
						Reporter.reportStep("Step 7: The CI Class has been not been selected","FAILURE");
	              
					snW.Wait(5000);

					// Step 8: In the CI Filter section, select filter condition from
					// reference data
					if(!snW.selectByVisibleTextByXpath("CIS_FirstFilterType1_Xpath",f2Section))
						Reporter.reportStep("Step 8: CI Filter section  could not be selected","FAILURE");
	                      
					snW.Wait(3000);
					if(!snW.selectByVisibleTextByXpath("CIS_FilterCondition1_Xpath",f2Condition))
						Reporter.reportStep("Step 8: CI Filter Condition  could not be selected","FAILURE");
					
					if(snW.selectByVisibleTextByXpath("CIS_FilterValue_Xpath", f2Value))
						Reporter.reportStep("Step 8: CI Filter selected sucessfully ","SUCCESS");
					else
						Reporter.reportStep("Step 8: CI Filter Value could not be selected","FAILURE");

					// Wait for few seconds
					snW.Wait(5000);

					// Step 9: Run the selected filter condition by clicking Update
					if (snW.clickById("CIS_UpdateButton_Id")) {
						snW.Wait(5000);
						Reporter.reportStep("Step 9: The Update is successful","SUCCESS");
					} else
						Reporter.reportStep("Step 9: The Update button could not be clicked","FAILURE");

					// Step 9: Save Record by clicking update at top of the screen
					if (!snW.rightClickById("CIS_Menu_Id"))
						Reporter.reportStep("Step 9: The Right click could not be clicked","FAILURE");

					if (snW.clickByXpath("CIS_SaveRecord_Xpath"))
						Reporter.reportStep("Step 9: The Save is clicked successfully","SUCCESS");
					else
						Reporter.reportStep("Step 9: The Save could not be clicked","FAILURE");

					// go out of the frame
					snW.switchToDefault();

					// Step 10: Log out
					if (snW.clickByXpath("Logout_Xpath"))
						Reporter.reportStep("Step 10: The Log out is clicked successfully","SUCCESS");
					else
						Reporter.reportStep("Step 10: The Log out could not be clicked","FAILURE");

					// Wait for few seconds
					snW.Wait(5000);

					// Step 11: Log in as a different group
					if (snW.login(verUser, verPwd))
						Reporter.reportStep("Step 11: The login with username:"+ verUser + " is successful", "SUCCESS");
					else
						Reporter.reportStep("Step 11: The login with username:"+ verUser + " is not successful", "FAILURE");

					// Step 12: Expand OpsDirector/Configurations under application
					if (snW.selectMenu("Ops_Director","Configurations", "CIS_Scopes"))
						Reporter.reportStep("Step 12: The CI Scopes - menu selected successfully","SUCCESS");
					else
						Reporter.reportStep("Step 12: The CI Scopes - menu could not be selected","FAILURE");

					// Switch to the main frame
					snW.switchToFrame("Frame_Main");

					// Step 13: Searching for data that was created
					if (!snW.enterByXpathAndClick("CIS_SearchReferenceData_Xpath", scopeNum))
						Reporter.reportStep("Step 13: The created Data: " + scopeNum + " could not be found", "FAILURE");

					// Wait for few seconds
					snW.Wait(5000);

					// Step 13B: Click on the scope Number
					if (snW.clickLink(scopeNum, false))
						Reporter.reportStep("Step 13B: The created scope number : " + scopeNum + " has been found and clicked successfully", "SUCCESS");
					else
						Reporter.reportStep("Step 13B: The created scope number : " + scopeNum + " could not be found", "FAILURE");

					// Step 14: Validate that there is no Update button and no Save
					if (snW.IsElementNotPresentById("CIS_UpdateButton_Id"))
						Reporter.reportStep("Step 14: The Update button could not been found as expected.","SUCCESS");
					else
						Reporter.reportStep("Step 14: The Update button has been found.", "FAILURE");

					// option when right click in the form
					if (!snW.rightClickById("CIS_Menu_Id"))
						Reporter.reportStep("Step 14A: The Right click on menu could not be performed.","FAILURE");

					if (snW.IsElementNotPresentByXpath("CIS_SaveRecord_Xpath"))
						Reporter.reportStep("Step 14A: The Save option could not been found as expected.","SUCCESS");
					else
						Reporter.reportStep("Step 14A: The Save option has been found.","FAILURE");

					// go out of the frame
					snW.switchToDefault();

					// Step 15: Log out
					if (snW.clickByXpath("Logout_Xpath"))
						Reporter.reportStep("Step 15: The Log out is clicked successfully.","SUCCESS");
					else
						Reporter.reportStep("Step 15: The Log out could not be clicked.", "FAILURE");

					status = "PASS";

				} finally {
					// close the browser
					snW.quitBrowser();
				}

			}


			@DataProvider(name = "OD_STRY0010121_TC01_V1")
			public Object[][] fetchData() throws IOException {
				Object[][] arrayObject = DataInputProvider.getSheet("OD_STRY0010121_TC01_V1");
				return arrayObject;
			}
		}





