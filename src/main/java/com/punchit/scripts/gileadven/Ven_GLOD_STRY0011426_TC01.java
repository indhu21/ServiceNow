package com.punchit.scripts.gileadven;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_STRY0011426_TC01 extends SuiteMethods {
	// Create Instance
		ServiceNowWrappers snW;

		@Test(dataProvider = "Ven_GLOD_STRY0011426_TC01",groups="OpsDirector")
		public void createCIScope(String regUser, String regPwd, String name,
				String shortDescription, String filter, String owningGroup,
				String f1Section) throws COSVisitorException,
		IOException {

			// Pre-requisities
			snW = new ServiceNowWrappers(entityId);

			try {

				// Step 0: Launch the application
				snW.launchApp(browserName, true);

				// Step 1: Log in to application
				if (snW.login(regUser, regPwd))
					Reporter.reportStep("The login with username:"+ regUser + " is successful", "SUCCESS");
				else
					Reporter.reportStep("The login with username:"+ regUser + " is not successful", "FAILURE");

				// Step 2: In application navigator expand OpsDirector/Registration to select CI Scope Registration
				if (snW.selectMenu("Registration", "CIS_Registration"))
					Reporter.reportStep("The CI Scope Registration - menu selected successfully","SUCCESS");
				else
					Reporter.reportStep("The CI Scope Registration - menu could not be selected","FAILURE");

				// Switch to the main frame
				snW.switchToFrame("Frame_Main");

				// Step 3: Fill in Name and Short Description from reference data
				if (!snW.enterById("CIS_Name_Id", name))
					Reporter.reportStep("The CI Scope Name - could not be entered","FAILURE");

				if (!snW.enterById("CIS_ShortDesc_Id", shortDescription))
					Reporter.reportStep("The CI Scope description could not be entered","FAILURE");

				// Step 3: Choose Type as Dynamic Filter in the drop down
				if (!snW.selectByVisibleTextById("CIS_Filter_Id", filter))
					Reporter.reportStep("The CI Scope Filter could not be selected","FAILURE");

				// Step 3: Choose Owning Group as Punch Group from the list that
				// appears by clicking the Search icon
				if (!snW.enterAndChoose("CIS_OwningGroup_Xpath", owningGroup))
					Reporter.reportStep("The Owning Group could not be selected","FAILURE");

				// Step 3: Click ‘submit’ and switch to the frame in new window
				if (snW.clickById("CIS_SubmitButton_Id"))
					Reporter.reportStep("The submit button has been clicked successfully","SUCCESS");
				else
					Reporter.reportStep("The submit button could not be clicked","FAILURE");

				// Step 4: Select the CI Class  from Reference Data
				// reference data
				if(snW.selectByVisibleTextByXpath("CIS_CIClass_Xpath",f1Section))
					Reporter.reportStep("In CI Class section "+ f1Section+ " could be selected","SUCCESS");
				else	
					Reporter.reportStep("In CI Class section "+ f1Section+ " could not be selected","FAILURE");
				
				String scopeNum = snW.getAttributeById("CIS_ScopeNumber_Id", "value");
				System.out.print(scopeNum);
				//Step 5: Click update
				if (snW.clickById("CIS_UpdateButton_Id"))
					Reporter.reportStep("The Update is successful","SUCCESS");
				else
					Reporter.reportStep("The Update button could not be clicked","FAILURE");

				//Step 6: Scroll down to Targeted CI’s section and look for Configuration Items with class type of Linux and Windows Servers
				
				snW.scrollToElementByXpath("CIS_TargetCIs_Xpath");
				
				if(snW.verifyallElementText("CIS_TargetCIs_Xpath", "ESX Server", "Linux Server"))
//				if(snW.verifyallElementText("CIS_TargetCIs_Xpath", "ESX Server"))
					Reporter.reportStep("All Elements are listed matched with ESX Server successful","SUCCESS");
				else
					Reporter.reportStep("All Elements are listed could not matched with ESX Server","FAILURE");

				
				/*Boolean bReturn=false;
				List<WebElement> CIclasses=snW.getAllElementsByXpath("CIS_TargetCIs_Xpath");
				for(WebElement CIclass:CIclasses){
					if(CIclass.getText().equals("Windows Server") || CIclass.getText().equals("Linux Server")){					
					}else
						System.out.println("Fail");
				}	
				*/

				if(!snW.addNewFilterUsingSelect("Class", "is", "ESX Server"))
					Reporter.reportStep("Class is ESX Server could not be selected","FAILURE");

				if (snW.clickById("CIS_UpdateButton_Id"))
					Reporter.reportStep("The Update Button could be clicked successful","SUCCESS");
				else
					Reporter.reportStep("The The Update Button could not be clicked","FAILURE");
				
				snW.scrollToElementByXpath("CIS_TargetCIs_Xpath");
				
				if(!snW.verifyallElementText("CIS_TargetCIs_Xpath", "ESX Server"))
					Reporter.reportStep("All Elements are listed could not matched with ESX Server","FAILURE");

				/*
				
				CIclasses=snW.getAllElementsByXpath("CIS_TargetCIs_Xpath");
				for(WebElement CIclass:CIclasses){
					if(!CIclass.getText().equals("Windows Server"))					
						System.out.println("Fail");				
				}
				 */
				if(snW.selectMenu("Configurations", "CIS_Scopes"))
					Reporter.reportStep("The CI Scope Registration - menu selected successfully","SUCCESS");
				else
					Reporter.reportStep("The CI Scope Registration - menu could not be selected","FAILURE");
				
				snW.switchToFrame("Frame_Main");
				
//				if(!snW.selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Name"))
//					Reporter.reportStep("Step 9: 'Name' could not be selected in Goto section, hence failure","FAILURE");
//
//				if(!snW.enterByXpathAndClick("CIS_SearchReferenceData_Xpath", scopeNum))
//					Reporter.reportStep("Step 9: 'CI Scope name: ' "+ scopeNum +" could not be entered, hence failure","FAILURE");
//
//				snW.Wait(5000);
//				String ciScopes=snW.getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
				String supname1 = "Number";
				if(!snW.selectByVisibleTextByXpath("Runbook_SearchKey_Xpath", supname1))
					Reporter.reportStep("Runbook - Search Key could not be selected","FAILURE");
				snW.Wait(500);
				if (!snW.enterByXpathAndClick("CIS_SearchReferenceData_Xpath", scopeNum))
					Reporter.reportStep("The created Data: " + scopeNum + " could not be found", "FAILURE");
      
				// Wait for few seconds
				snW.Wait(5000);
                snW.PresEnter();
				
				//snW.scrollToElementByXpath("ALERTPROFILE_FirstAlert_Xpath");
				
				if(snW.clickLink(scopeNum, false))
					Reporter.reportStep("The CI Scope: "+ scopeNum +" selected successfully","SUCCESS");
				else
					Reporter.reportStep("The CI Scope: "+ scopeNum +" could not be selected","FAILURE");

				snW.scrollToElementByXpath("CIS_TargetCIs_Xpath");
				
				if(snW.verifyallElementText("CIS_TargetCIs_Xpath", "ESX Server"))
					Reporter.reportStep("All Elements are listed matched with ESX Server successful","SUCCESS");
				else
					Reporter.reportStep("All Elements are listed could not matched with ESX Server","FAILURE");

				// go out of the frame
				snW.switchToDefault();

				// Step 15: Log out
				if (snW.clickByXpath("Logout_Xpath"))
					Reporter.reportStep("The Log out is clicked successfully.","SUCCESS");
				else
					Reporter.reportStep("The Log out could not be clicked.", "FAILURE");

				status = "PASS";

			} finally {
				// close the browser
				snW.quitBrowser();
			}

		}


		@DataProvider(name = "Ven_GLOD_STRY0011426_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("Ven_GLOD_STRY0011426_TC01");
			return arrayObject;
		}
	}



