package com.punchit.scripts.gileadven;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_STRY0011171_TC01 extends SuiteMethods{
	// Create Instance
		ServiceNowWrappers snW;

		@Test(dataProvider = "Ven_GLOD_STRY0011171_TC01",groups="OpsDirector")
		public void Configure_Business_CI_Scope(String regUser, String regPwd,String name,
				String shortDescription, String Type, String Group,String CI_Class, String CI_Filter,String CI_Service, String Target_CI, String User2, String Pwd2,String F1field, String F1Operator,String F1Value) throws COSVisitorException,
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

				// Step 2: In application navigator expand OpsDirector/Registration to select CI Scope Registration
				if (snW.selectMenu("Ops_Director","Registration", "CIS_Registration"))
					Reporter.reportStep("Step 2: The CI Scope Registration - menu selected successfully","SUCCESS");
				else
					Reporter.reportStep("Step 2: The CI Scope Registration - menu could not be selected","FAILURE");

				// Switch to the main frame
				snW.switchToFrame("Frame_Main");

				// Step 3: Fill in Name from reference data
				if (!snW.enterById("CIS_Name_Id", name))
					Reporter.reportStep("Step 3A: The CI Scope Name - could not be entered","FAILURE");

				// Fill Short Description from reference data
				if (!snW.enterById("CIS_ShortDesc_Id", shortDescription))
					Reporter.reportStep("Step 3B: The CI Scope description could not be entered","FAILURE");

				/*	// Choose Type as Dynamic Filter from reference data
					if (!snW.selectByVisibleTextById("CIS_Filter_Id", Type))
						Reporter.reportStep("Step 3C: The CI Scope Filter could not be selected","FAILURE");
				 */  
				// Choose Owning Group as Punch Group from the list that
				if (snW.enterAndChoose("CIS_OwningGroup_Xpath",Group))
					Reporter.reportStep("Step 3: Fileds are entered successfully", "SUCCESS");
				else
					Reporter.reportStep("Step 3: The Owning Group could not be selected","FAILURE");

				// Step 4: Click ‘submit’ and switch to the frame in new window
				if (snW.clickById("CIS_SubmitButton_Id"))
					Reporter.reportStep("Step 4: The submit button has been clicked successfully","SUCCESS");
				else
					Reporter.reportStep("Step 4: The submit button could not be clicked","FAILURE");

				snW.Wait(5000);
				snW.switchToDefault();
				snW.switchToFrame("Frame_Main");

				//Step 5: Select CI Class Linux Server [cmdb_ci_linux_server]
				if(snW.selectByVisibleTextById("CI_CLASS_ID", CI_Class))

					Reporter.reportStep("Step 5: The CI Class has been selected successfully with value "+CI_Class,"SUCCESS");
				else
					Reporter.reportStep("Step 5: The CI Class has been not been selected","FAILURE");

				snW.Wait(3000);
				//Step 6: Select Type field as Related to Application 
				if(snW.selectByVisibleTextById("CI_TYPE_ID", CI_Filter))
					Reporter.reportStep("Step 6: The CI Filter type has been selected successfully with value "+CI_Filter,"SUCCESS");
				else
					Reporter.reportStep("Step 6: The CI Filter type has been not been selected","FAILURE");

				snW.Wait(2000);
				//Step 7: Select Application as Dovetail Payments Hub
				if(snW.enterAndChoose("CI_Application_Xpath", CI_Service))
					Reporter.reportStep("Step 7: The CI Application has been selected successfully ","SUCCESS");
				else
					Reporter.reportStep("Step 7: The CI Application has been not been selected","FAILURE");

				// Step 8: Save Record by clicking update at top of the screen

				snW.Wait(2000);
				if (snW.rightClickById("CIS_Menu_Id"))
					Reporter.reportStep("Step 8: Right click has been perfromed successfully","SUCCESS");
				else
					Reporter.reportStep("Step 8: Right click could not be performed","FAILURE");

				if (snW.clickByXpath("CIS_SaveRecord_Xpath"))
					Reporter.reportStep("Step 9: The Save is clicked successfully","SUCCESS");
				else
					Reporter.reportStep("Step 9: The Save could not be clicked","FAILURE");

				snW.Wait(5000);

				String ScopeNum = snW.getAttributeById("CI_SCOPEID_ID", "value");
				   // Step 9: Checking Class with linux server is present at the Targeted CI
				      if(!snW.clickByXpath("CI_SCOPE_FILTER_Xpath"))
				            Reporter.reportStep("Step 9: Filter icon could not be  selected","FAILURE");
				      snW.Wait(2000);

				    //Setting up the filter
				      //Select the class
				      if(!snW.selectByVisibleTextByXpath("CIS_Targeted_FilterType1_Xpath", F1field))
				      Reporter.reportStep("Step 9: Class is not selected ","FAILURE");
				      //Select the is condition
				      if(!snW.selectByVisibleTextByXpath("CIS_Targeted_FilterCondition1_Xpath", F1Operator))
				      Reporter.reportStep("Step 9: Is condition is not selected","FAILURE");
				      snW.Wait(3000);
				      //Select the Linux server
				      if(!snW.selectByVisibleTextByXpath("CIS_Targeted_FilterValue1_Xpath", F1Value))
				      Reporter.reportStep("Step 9: Linux server condition is not selected","FAILURE");
				      snW.Wait(3000);
				      
				    //Click on RUN button
				    if(!snW.clickById("MY_ALERT_RUN_Buttn_ID"))
				   // Reporter.reportStep("Step 10: Run button is clicked ","SUCCESS");
				    //else
				      Reporter.reportStep("Step 10: Run button is not clicked ","FAILURE");
				       
				     			snW.Wait(2000);

				String element= snW.getTextByXpath("Application_Target_CI_Xpath_new");
				System.out.println("Element is"+element);
				if(element.equalsIgnoreCase(""))
					Reporter.reportStep("Step 10: Targeted CI is not present Successful","FAILURE");
				else
					Reporter.reportStep("Step 10: Targeted CI is present as Expected","SUCCESS");

				// go out of the frame
				snW.switchToDefault();
				// Step 10: Log out
				if (!snW.clickByXpath("Logout_Xpath"))
					//Reporter.reportStep("Step 11: The Log out is clicked successfully","SUCCESS");
				//else
					Reporter.reportStep("Step 11: The Log out could not be clicked","FAILURE");
/*
				// Wait for few seconds
				snW.Wait(5000);
				// Step 12: Log in as user 2
				if (snW.login(User2, Pwd2))
					Reporter.reportStep("Step 12: The login with username:"+ User2 + " is successful", "SUCCESS");
				else
					Reporter.reportStep("Step 12: The login with username:"+ User2 + " is not successful", "FAILURE");

				// Step 13: Expand OpsDirector/Configurations under application
				if (snW.selectMenu("Configurations", "CIS_Scopes"))
					Reporter.reportStep("Step 13: The CI Scopes - menu selected successfully","SUCCESS");
				else
					Reporter.reportStep("Step 13: The CI Scopes - menu could not be selected","FAILURE");

				// Switch to the main frame
				snW.switchToFrame("Frame_Main");
				// Step 14: Select CI scope which was created by 1st user
				snW.clickByXpath("CI_Header_Number_Xpath");
				if (!snW.enterByXpathAndClick("CIS_SearchReferenceData_Xpath", ScopeNum))
					Reporter.reportStep("Step 14: The created Data: " + ScopeNum + " could not be found", "FAILURE");

				// Wait for few seconds
				snW.Wait(5000);

				// Step 15: Click on the scope Number
				if (snW.clickLink(ScopeNum, false))
					Reporter.reportStep("Step 14: The created scope number : " + ScopeNum + " has been found and clicked successfully", "SUCCESS");
				else
					Reporter.reportStep("Step 14: The created scope number : " + ScopeNum + " could not be found", "FAILURE");

				//Step 15: Business services Field should read only

				String[] ReadField = {"CI_Application_xpath_readonly"};
				String[] ReadLabel = {"Application"};
				snW.verifyDisabledFieldsByXpath(ReadField,ReadLabel);
				//				String excel=snW.getAttributeByXpath("CI_Application_Xpath", "readonly");
				//				System.out.print("Attribue value is " + excel);
				 * 
				 */
				status = "PASS";

				///html/body/div[2]/form/span[1]/span/div[5]/div[1]/div[2]/div[6]/div[2]/input[2]

			} 
			finally {
				// close the browser
				snW.quitBrowser();
			}	
		}

		@DataProvider(name = "Ven_GLOD_STRY0011171_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("Ven_GLOD_STRY0011171_TC01");
			return arrayObject;
		}

}
