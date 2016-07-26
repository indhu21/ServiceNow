package com.punchit.scripts.od;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;




import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class OD_Stry0010105_Tc01 extends SuiteMethods {

	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "OD_Stry0010105_Tc01",groups="OpsDirector")
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
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");

			// Step 2: In application navigator expand OpsDirector/Registration to select CI Scope Registration
			if (snW.selectMenu("Registration", "CIS_Registration"))
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

			// Step 3: Choose Type as Dynamic Filter in the drop down
			if (!snW.selectByVisibleTextById("CIS_Filter_Id", filter))
				Reporter.reportStep("Step 3: The CI Scope Filter could not be selected","FAILURE");

			// Step 3: Choose Owning Group as Punch Group from the list that
			// appears by clicking the Search icon
			if (!snW.enterAndChoose("CIS_OwningGroup_Xpath", owningGroup))
				Reporter.reportStep("Step 3: The Owning Group could not be selected","FAILURE");

			// Step 3: Click ‘submit’ and switch to the frame in new window
			if (snW.clickById("CIS_SubmitButton_Id"))
				Reporter.reportStep("Step 3: The submit button has been clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 3: The submit button could not be clicked","FAILURE");

			// Step 4: Select the CI Class  from Reference Data
			// reference data
			if(snW.selectByVisibleTextByXpath("CIS_CIClass_Xpath",f1Section))
				Reporter.reportStep("Step 4: In CI Class section "+ f1Section+ " could be selected","SUCCESS");
			else	
				Reporter.reportStep("Step 4: In CI Class section "+ f1Section+ " could not be selected","FAILURE");
			
			//Step 5: Click update
			if (snW.clickById("CIS_UpdateButton_Id"))
				Reporter.reportStep("Step 5: The Update is successful","SUCCESS");
			else
				Reporter.reportStep("Step 5: The Update button could not be clicked","FAILURE");

			//Step 6: Scroll down to Targeted CI’s section and look for Configuration Items with class type of Linux and Windows Servers
			
			snW.scrollToElementByXpath("CIS_TargetCIs_Xpath");
			
			if(snW.verifyallElementText("CIS_TargetCIs_Xpath", "Windows Server", "Linux Server"))
				Reporter.reportStep("Step 6: All Elements are listed matched with 'Windows Server & Linux Server' successful","SUCCESS");
			else
				Reporter.reportStep("Step 6: All Elements are listed could not matched with 'Windows Server & Linux Server'","FAILURE");

			
			/*Boolean bReturn=false;
			List<WebElement> CIclasses=snW.getAllElementsByXpath("CIS_TargetCIs_Xpath");
			for(WebElement CIclass:CIclasses){
				if(CIclass.getText().equals("Windows Server") || CIclass.getText().equals("Linux Server")){					
				}else
					System.out.println("Fail");
			}	
			*/

			if(!snW.addNewFilterUsingSelect("Class", "is", "Windows Server"))
				Reporter.reportStep("Step 7: 'Class is Windows Server' could not be selected","FAILURE");

			if (snW.clickById("CIS_UpdateButton_Id"))
				Reporter.reportStep("Step 7: The Update Button could be clicked successful","SUCCESS");
			else
				Reporter.reportStep("Step 7: The The Update Button could not be clicked","FAILURE");
			
			snW.scrollToElementByXpath("CIS_TargetCIs_Xpath");
			
			if(!snW.verifyallElementText("CIS_TargetCIs_Xpath", "Windows Server"))
				Reporter.reportStep("Step 6: All Elements are listed could not matched with 'Windows Server'","FAILURE");

			/*
			
			CIclasses=snW.getAllElementsByXpath("CIS_TargetCIs_Xpath");
			for(WebElement CIclass:CIclasses){
				if(!CIclass.getText().equals("Windows Server"))					
					System.out.println("Fail");				
			}
			 */
			if(snW.selectMenu("Configurations", "CIS_Scopes"))
				Reporter.reportStep("Step 8: The CI Scope Registration - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 8: The CI Scope Registration - menu could not be selected","FAILURE");
			
			snW.switchToFrame("Frame_Main");
			
			if(!snW.selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Name"))
				Reporter.reportStep("Step 9: 'Name' could not be selected in Goto section, hence failure","FAILURE");

			if(!snW.enterByXpathAndClick("CIS_SearchReferenceData_Xpath", name))
				Reporter.reportStep("Step 9: 'CI Scope name: ' "+ name +" could not be entered, hence failure","FAILURE");

			snW.Wait(5000);
			String ciScopes=snW.getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
			
			//snW.scrollToElementByXpath("ALERTPROFILE_FirstAlert_Xpath");
			
			if(snW.clickLink(ciScopes, false))
				Reporter.reportStep("Step 9: The 'CI Scope:' "+ ciScopes +" selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 9: The 'CI Scope:' "+ ciScopes +" could not be selected","FAILURE");

			snW.scrollToElementByXpath("CIS_TargetCIs_Xpath");
			
			if(snW.verifyallElementText("CIS_TargetCIs_Xpath", "Windows Server"))
				Reporter.reportStep("Step 10: All Elements are listed matched with 'Windows Server' successful","SUCCESS");
			else
				Reporter.reportStep("Step 10: All Elements are listed could not matched with 'Windows Server'","FAILURE");

			// go out of the frame
			snW.switchToDefault();

			// Step 15: Log out
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


	@DataProvider(name = "OD_Stry0010105_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OD_Stry0010105_Tc01");
		return arrayObject;
	}
}
