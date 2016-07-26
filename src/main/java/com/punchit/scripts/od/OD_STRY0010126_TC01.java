package com.punchit.scripts.od;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;


public class OD_STRY0010126_TC01 extends SuiteMethods{

	// Create Instance
	ServiceNowWrappers snW;
	
	@Test(dataProvider = "OD_STRY0010126_TC01",groups="OpsDirector")
	public void appProperties(String regUser, String regPwd, String ReactionType, String User2, String Pwd2, String Alert_Prof_Name) throws COSVisitorException, IOException, InterruptedException {
		
		// Prerequisites
		snW = new ServiceNowWrappers(entityId);
		
		try {
			
			if (snW.launchApp(browserName, true))
				Reporter.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
			else
				Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

			
			
			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("Step 1: Login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: Login with username:"+ regUser + " is not successful", "FAILURE");
			
			
			
			// Step 2: In application navigator expand OpsDirector/Administration to select Application Properties
			if (snW.selectMenu("Ops_Director", "Configurations", "Alert_Profiles"))
				Reporter.reportStep("Step 2: Alert Profiles menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: Alert Profiles menu is not available for selection","FAILURE");
			
			//Wait for the alert Profiles to load.
			Thread.sleep(2000);
			//Switch to the main frame.
			snW.switchToFrame("Frame_Main");
	
		    
			//Get the Alert Profile number of the first alert.
			String AlertProfile_no= snW.getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
			
			//Step3a: Select the Alert Profile.
			if(snW.clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			{}
			else
			{   status="INSUFFICIENT DATA";
				Reporter.reportStep("Step 3a: Alert Profile selection Unsuccessfull, No Alerts Available", "FAILURE");
			}
		    
			
			//Wait for the Alert Profile to load.
			Thread.sleep(3000);
			
			if(snW.selectByVisibleTextByXpath("Reaction_TypeSelect_Xpath", ReactionType))
				Reporter.reportStep("Step 4: The reaction--"+ ReactionType+ "--is sucessfully selected", "SUCCESS");
			else
				Reporter.reportStep("Step 4: The reaction--"+ ReactionType+ "--Could not be selected", "FAILURE");
			
			//snW.pressKey(Keys.RETURN);
			Thread.sleep(2000);
			
			WebElement Icon = snW.getDriver().findElement(By.xpath("//*[@id='view.x_tori2_opd_alert_profiles.reaction_type']"));
			snW.doubleCick(Icon);
			
			//Wait for the Reaction type Window to Open.
			Thread.sleep(3000);
			
			//Step5a : Input the Reaction type Name.
			if(snW.enterById("Reaction_Lable_ID", "Create Problem"))
				Reporter.reportStep("Step5a: Alert Reaction Label is entered succcessfully", "SUCCESS");
			else
				Reporter.reportStep("Step5a: Alert Reaction Label could not be entered", "FAILURE");
			
			
			//Step5b Enter the Table details for the Reaction Type.
			if(snW.selectByVisibleTextById("Reaction_TableSelect_ID", "Problem [problem]"))
				Reporter.reportStep("Step5b: Table details for Alert reaction entered successfully","SUCCESS");
			else
				Reporter.reportStep("Step5b: Table details for Alert reaction was not successfull","FAILURE");
			
			//Step
			 
		  String Active = snW.getAttributeById("Reaction_active_ID", "value");
		  if(Active == "true")  	
		  Reporter.reportStep("Step5c: Alert Reaction is Active", "SUCCESS");
		  else snW.clickById("Reaction_active_ID");
			  Reporter.reportStep("Step5c: Active is checked true", "SUCCESS");
		  
		  String Default = snW.getAttributeById("Reaction_Default_ID", "value");
		  if(Default == "true")  	
		  Reporter.reportStep("Step5c: Alert Reaction is Default reaction", "SUCCESS");
		  else snW.clickById("Reaction_Default_ID");
			  Reporter.reportStep("Step5c: Alert Reaction is set as Default", "SUCCESS");
		    
			  snW.rightClickByXpath("Header_rClick_Xpath");
	     
		if(snW.clickByXpath("CIS_InsertRecord_Xpath"))
			Reporter.reportStep("Step5d: New Alert Reaction is saved Successsfully","SUCCESS");
		else
			Reporter.reportStep("Step5d: New Alert Reaction could not be saved","FAILURE");
		
		Thread.sleep(3000);
		
		//Select the New Alert Reaction type created.
		if(snW.selectByVisibleTextByXpath("Reaction_TypeSelect_Xpath","Create Problem"))
			Reporter.reportStep("Step 6a: The reaction type is sucessfully selected", "SUCCESS");
		else
			Reporter.reportStep("Step 6a: The reaction type could not be selected", "FAILURE");
//		snW.pressKey(Keys.ARROW_DOWN);
//		snW.pressKey(Keys.RETURN);
		Thread.sleep(2000);

		
		
		//Check For CI Scope is Filled or not and fill it if empty.
		snW.clickByXpath("ALERTPROFILE_CIScope_Xpath");
		//("CIS_InsertRecord_Xpath");
		if(!snW.IsElementNotPresentById("ALERTPROFILE_CIscope_Class_ID"))
		{
			//Click the search icon and select the first CI Scope Available.
			snW.clickById("ALERTPROFILE_CIscope_lookup_ID");
			Thread.sleep(3000);
			snW.switchToSecondWindow();
			snW.clickByXpath("ALERTPROFILE_CIscope_FirstCIscope_Xpath");
			Thread.sleep(2000);
			snW.switchToPrimary();
			Reporter.reportStep("Step 6b: CI Scope is selected successfully", "SUCCESS");	
		}
		else 
			Reporter.reportStep("Step 6b: CI scope is already present", "SUCCESS");
		
		
		// Save the Alert Profile with the new Alert Reaction Type.
		snW.rightClickByXpath("Header_rClick_Xpath");
		if(snW.clickByXpath("CIS_SaveRecord_Xpath"))
			Reporter.reportStep("Step6b: Alert Reaction is saved Successsfully","SUCCESS");
		else
			Reporter.reportStep("Step6b: Alert Reaction could not be saved","FAILURE");
		
		
		Thread.sleep(3000);
		// go out of the frame
		snW.switchToDefault();
		// Step 10: Log out
		if (snW.clickByXpath("Logout_Xpath"))
			Reporter.reportStep("Step 7: The Log out is clicked successfully","SUCCESS");
		else
			Reporter.reportStep("Step 7: The Log out could not be clicked","FAILURE");

		// Wait for few seconds
		snW.Wait(5000);
		// Step 13: Log in as user 2
		if (snW.login(User2, Pwd2))
			Reporter.reportStep("Step 8: The login with username:"+ User2 + " is successful", "SUCCESS");
		else
			Reporter.reportStep("Step 8: The login with username:"+ User2 + " is not successful", "FAILURE");
		
		
		//Step 9 Create a new alert profile form registrations
		if (snW.selectMenu("Registration", "Prof_Reg"))
			Reporter.reportStep("Step 9: The Alert Profiles menu selected successfully","SUCCESS");
		else
			Reporter.reportStep("Step 9: The Alert Profiles menu could not be selected","FAILURE");
		
		
		//Wait for the alert Profiles to load.
		Thread.sleep(2000);
		//Switch to the main frame.
		snW.switchToFrame("Frame_Main");
		
		//Step10: Create a New Alert Profile as Operator.
		snW.enterByXpath("Reaction_Name_Xpath", Alert_Prof_Name);
		snW.selectByVisibleTextById("CI_Scope", "CIAnythingScope");
		snW.selectByVisibleTextById("Dy_Inc_Asn_Grp", "Yes");
		snW.selectByVisibleTextById("Inc_Asn_Area", "Infrastructure");
		snW.enterById("Own_Grp", "Punch Group1");
		snW.selectByVisibleTextById("Alert_Reaction","Create Problem" );
		snW.enterById("Description", "Test ticket for Alert Reaction Type");
		Reporter.reportStep("Step10g: Alert Profile Details were inserted successfully", "SUCCESS");
		snW.Wait(5000);
		
		
		//Step 10g: Click on Submit
		if(snW.clickById("submit_button"))
			Reporter.reportStep("Step10g: Alert Profile is Submitted successfully", "SUCCESS");
		else
			Reporter.reportStep("Step10g: Alert Profile could not be Submitted", "SUCCESS");
		
		Thread.sleep(3000);
		
		//Step11: Check the Alert Profile-  Alert Reaction is Read-only.
		WebElement Icon1 = snW.getDriver().findElement(By.xpath("//*[@id='view.x_tori2_opd_alert_profiles.reaction_type']"));
		
		snW.doubleCick(Icon1);
		Reporter.reportStep("Step11: Alert Reaction is Selected", "SUCCESS");
		
		Thread.sleep(3000);
		
		
		String[] Alert_reaction = {"Reaction_Label_Xpath","Reaction_Condition_Xpath",};
		String[] Alert_reaction_fields= {"Label","Conditions",};
		snW.verifyDisabledFieldsByXpath(Alert_reaction, Alert_reaction_fields);
		Reporter.reportStep("Step11: The Fields are Read Only", "SUCCESS");
		
		// go out of the frame
        snW.switchToDefault();

        // Log out
        if(!snW.clickByXpath("Logout_Xpath"))
         Reporter.reportStep("The logout Failed", "FAILURE");

        status="PASS";
		
		} 
		finally{
			// close the browser
			snW.quitBrowser();
			
		}
	}
		@DataProvider(name = "OD_STRY0010126_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("OD_STRY0010126_TC01");
			return arrayObject;
		}
}
