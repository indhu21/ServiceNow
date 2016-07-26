package com.punchit.scripts.od;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class OD_STRY0010125_TC01 extends SuiteMethods{
	ServiceNowWrappers snW;

	@Test(dataProvider = "OD_STRY0010125_TC01",groups="OpsDirector")
	public void ModifyCI(String regUser, String regPwd) throws COSVisitorException,
			IOException, InterruptedException {

		// Pre-requisities
		snW = new ServiceNowWrappers(entityId);

		try {
			snW.launchApp(browserName, true);

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");

			// Step 2: In application navigator expand OpsDirector/configuration to select Alert profile
			if (snW.selectMenu("Ops_Director","Configurations", "Alert_Profiles"))
				Reporter.reportStep("Step 2: The Alert Profile - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Alert Profile - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");

			//Step 3: Click on First Alert
			String Alertno=snW.getTextByXpath("ALERT_profiles_FirstAlertNumber_XPATH");

			if(snW.clickByXpath("ALERT_profiles_FirstAlertNumber_XPATH"))
				Reporter.reportStep("Step 3: Alert Profile Selected ","SUCCESS");
			else
				Reporter.reportStep("Step 3: Alert Profile could not be selected","FAILURE");

			String oldCI=snW.getAttributeById("ALERTPROFILE_CIScope_Id", "value");
			System.out.println("Old ci scope is "+  oldCI);
			//ALERTPROFILE_CIScope_xpath

			// step 4 : select new ci scope  
			if(!snW.clickById("ALERTPROFILE_CIScopeLookup_ID"))
				Reporter.reportStep("Step 4: CIScope search icon could not be clicked","FAILURE");
			
			snW.switchToSecondWindow();
			snW.Wait(1000);
			String supname1 = "Name";
			if(!snW.selectByVisibleTextByXpath("Runbook_SearchKey_Xpath", supname1))
				Reporter.reportStep("Step 4: CIscope - Search Key could not be selected","FAILURE");
			snW.Wait(500);
			if(!snW.enterAndChoose("Header_SearchBox_Xpath", "!="+oldCI))
				Reporter.reportStep("Step 4: !="+oldCI+" could not be entered in search box","FAILURE");
			snW.Wait(3000);
			if(!snW.clickByXpath("First_Searched_Record_Xpath"))
				Reporter.reportStep("Step 4: First searched user could not be selected","FAILURE");
			
			snW.switchToPrimary();
			snW.switchToFrame("Frame_Main");
			snW.Wait(1000);
//			if(snW.enterAndChoose("ALERTPROFILE_CIScope_xpath", "**"))
//				Reporter.reportStep("Step 4: New CI scope is selected successfully ","SUCCESS");
//			else
//				Reporter.reportStep("Step 4: Alert Profile could not be selected","FAILURE");
			String NewAssignment=snW.getAttributeById("ALERTPROFILE_CIScope_Id","value");
			if(NewAssignment.contentEquals(oldCI))
				Reporter.reportStep("Step 4: New CIScope could not be selected in CIScope field","FAILURE");
			else
				Reporter.reportStep("Step 4: New CIScope is successfully selected in CIScope field","SUCCESS");
			
			//String updatedCI=snW.getAttributeById("ALERTPROFILE_CIScope_Id", "value");
             
			//step 6 : click on Update button
			if(snW.clickByXpath("ALERT_profiles_UpdateButton_xpath"))
				Reporter.reportStep("Step 5: Update button is clicked Successfully","SUCCESS");
			else
				Reporter.reportStep("Step 5: Update button could not be clicked ","FAILURE");
            Thread.sleep(3000);
			
			if(snW.enterByXpath("CIS_SearchReferenceData_Xpath", Alertno))
				Reporter.reportStep("Step 6: Alert Profle is entered Successfully","SUCCESS");
			else
				Reporter.reportStep("Step 6: Alert Profle could not be entered Successfuly ","FAILURE");

			
			snW.PresEnter();
			
			// Press enter Key
			snW.getDriver().findElementByLinkText("Number").click();
			Thread.sleep(2000);
			// Step 8:  Select the Searched Alert
			if(snW.clickByXpath("ALERT_profiles_FirstAlertNumber_XPATH"))
				Reporter.reportStep("Step 7: Alert Profle Selected Successfully","SUCCESS");
			else
				Reporter.reportStep("Step 7: Alert Profle could not be Selected ","FAILURE");

			Thread.sleep(3000);

			//Step 9: Verify the Updates
			String UpdatedCIScope;
			UpdatedCIScope=snW.getAttributeById("ALERTPROFILE_CIScope_Id", "value");
			System.out.println(UpdatedCIScope);
			if(UpdatedCIScope.equalsIgnoreCase(NewAssignment))
				Reporter.reportStep("Step 8: Updates are Saved Successfully","SUCCESS");
			else
				Reporter.reportStep("Step 8: Updates are not saved ","FAILURE");


			status = "PASS";



		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "OD_STRY0010125_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OD_STRY0010125_TC01");
		return arrayObject;
	}
}