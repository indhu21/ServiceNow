package com.punchit.scripts.od;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class OD_STRY0010106_TC01 extends SuiteMethods{
	
	// Create Instance
				ServiceNowWrappers snW;

				@Test(dataProvider = "OD_STRY0010106_TC01",groups="OpsDirector")
				public void createCIScope(String regUser, String regPwd, String name,
						String shortDescription, String filter, String owningGroup,
						String f2Section, String f2Condition, String f2Value,
						String CI_Class) throws COSVisitorException,
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

						if (!snW.enterById("CIS_ShortDesc_Id", shortDescription))
							Reporter.reportStep("Step 3: The CI Scope description could not be entered","FAILURE");

						// Step 3A: Choose Type as Static Filter in the drop down
						if (!snW.selectByVisibleTextById("CIS_Filter_Id", filter))
							Reporter.reportStep("Step 3: The CI Scope Filter could not be selected","FAILURE");

						// Step 3B: Choose Owning Group as Punch Group from the list that
						// appears by clicking the Search icon
						if (snW.enterAndChoose("CIS_OwningGroup_Xpath", owningGroup))
							Reporter.reportStep("Step 3: Name, short description,CI filter and Owning Group has been selected successfully as per the reference data", "SUCCESS");
						else
							Reporter.reportStep("Step 3: The Owning Group could not be selected","FAILURE");

						// Step 4: Click submit and switch to the frame in new window
						if (snW.clickById("CIS_SubmitButton_Id"))
							Reporter.reportStep("Step 4: The submit button has been clicked successfully","SUCCESS");
						else
							Reporter.reportStep("Step 4: The submit button could not be clicked","FAILURE");
                        
						snW.Wait(3000);	
						//Step 5: Select CI Class Windows Service [cmdb_ci_windows_service]
						if(snW.selectByVisibleTextById("CI_CLASS_ID", CI_Class))
							Reporter.reportStep("Step 5: The CI Class has been selected successfully with value "+CI_Class,"SUCCESS");
						else
							Reporter.reportStep("Step 5: The CI Class has been not been selected","FAILURE");
		              
						snW.Wait(3000);
						
						// Step 6: Run the selected filter condition by clicking Update
						if (snW.clickById("CIS_UpdateButton_Id")) {
							snW.Wait(5000);
							Reporter.reportStep("Step 6: The Update is successful","SUCCESS");
						} else
							Reporter.reportStep("Step 6: The Update button could not be clicked","FAILURE");

						
						//Step 7: Scroll down to Targeted CI’s section and look for Configuration Items with class type of Linux and Windows Servers
						
						if(snW.verifyallElementText("CIS_TargetCIs_Xpath", "Windows Server", "Linux Server"))
							Reporter.reportStep("Step 7: All Elements are listed matched with 'Windows Server & Linux Server' successful","SUCCESS");
						else
							Reporter.reportStep("Step 7: All Elements are listed could not matched with 'Windows Server & Linux Server'","FAILURE");

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
						snW.Wait(3000);

						// Step 9: Save Record by clicking update at top of the screen
						if (!snW.rightClickById("CIS_Menu_Id"))
							Reporter.reportStep("Step 9: The Right click could not be clicked","FAILURE");

						if (snW.clickByXpath("CIS_SaveRecord_Xpath"))
							Reporter.reportStep("Step 9: The Save is clicked successfully","SUCCESS");
						else
							Reporter.reportStep("Step 9: The Save could not be clicked","FAILURE");

						// verify all the elements in target CI are window server
						//Step 10: Scroll down to Targeted CI’s section and look for Configuration Items with class type of Linux and Windows Servers
						
						snW.scrollToElementByXpath("CIS_TargetCIs_Xpath");
						if(snW.verifyallElementText("CIS_TargetCIs_Xpath", "Windows Server"))
							Reporter.reportStep("Step 10: All Elements are listed matched with Windows Server","SUCCESS");
						else
							Reporter.reportStep("Step 10: All Elements are listed could not matched with Windows Server","FAILURE");

						// go out of the frame
						snW.switchToDefault();

						
						// Step 11: Log out
						if (snW.clickByXpath("Logout_Xpath"))
							Reporter.reportStep("Step 11: The Log out is clicked successfully.","SUCCESS");
						else
							Reporter.reportStep("Step 11: The Log out could not be clicked.", "FAILURE");

						status = "PASS";

					} finally {
						// close the browser
						snW.quitBrowser();
					}

				}


				@DataProvider(name = "OD_STRY0010106_TC01")
				public Object[][] fetchData() throws IOException {
					Object[][] arrayObject = DataInputProvider.getSheet("OD_STRY0010106_TC01");
					return arrayObject;
				}
			}








