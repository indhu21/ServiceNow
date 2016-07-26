package com.punchit.scripts.od;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;




public class OD_Stry0010140_Tc01 extends SuiteMethods {

	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider="OD_Stry0010140_Tc01",groups="OpsDirector")
	public void implementSLA(String regUser, String regPwd,String name, String desc, String owningGroup, String value1,String value2, String sec) {
		try {
			// Pre-requisities 
			snW = new ServiceNowWrappers(entityId);

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Log in to application
			if(snW.login(regUser, regPwd))
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");

			// Step 2: Go to User Consoles and click on Alert Consoles
			if(snW.selectMenu("Ops_Director","Registration","Prof_Reg"))
				Reporter.reportStep("Step 2: The Alert Profile - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Alert Profile - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");

			// Step 3: Enter Name
			if(!snW.enterById("Name",name))
				Reporter.reportStep("Step 3: The "+name+" could not be entered ","FAILURE");			

			// Step 3: Enter Description
			if(!snW.enterById("Description",desc))
				Reporter.reportStep("Step 3: The "+desc+" could not be entered ","FAILURE");

			// Step 3: Choose Owning group
			if(!snW.enterAndChoose("Own_Grp_Xpath", owningGroup))
				Reporter.reportStep("Step 3: The "+owningGroup+" could not be selected ","FAILURE");

			// Step 3: Choose Owning group
			if(!snW.selectByVisibleTextById("Dy_Inc_Asn_Grp", value1))
				Reporter.reportStep("Step 3: The "+value1+" could not be selected ","FAILURE");

			// Step 3: Choose Owning group
			if(snW.selectByVisibleTextById("Inc_Asn_Area", value2))
				Reporter.reportStep("Step 3: All mandatory fields are entered successfully ","SUCCESS");
			else				
				Reporter.reportStep("Step 3: The "+value2+" could not be selected ","FAILURE");

			// Step 4: Click update to save record
			if(snW.clickById("submit_button"))
				Reporter.reportStep("Step 4: The submit button is clicked successfully ","SUCCESS");
			else
				Reporter.reportStep("Step 4: The submit button could not be clicked ","FAILURE");
			
			// Step 5: Specify Alert hold time from reference data
			if(snW.enterByXpath("Alertprofile_HoldTimeSec_Xpath", sec))
				Reporter.reportStep("Step 5: Alert Hold Time  is entered successfully  with value " +sec,"SUCCESS");
			else
				Reporter.reportStep("Step 5: The submit button could not be clicked ","FAILURE");
			
			// Step 6: Click update to save hold time and then to save the record
			if(snW.clickById("Update_Button"))
				Reporter.reportStep("Step 6: The submit button is clicked successfully ","SUCCESS");
			else
				Reporter.reportStep("Step 6: The submit button could not be clicked ","FAILURE");
			
			// go out of the frame
			snW.switchToDefault();

			// Step 7 :Log out
			if(snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("Step 7: The logout button is clicked successfully ","SUCCESS");
			else
				Reporter.reportStep("Step 7: The logout Failed", "FAILURE");

			status = "PASS";

		} finally{

			// close the browser
			snW.quitBrowser();	
		}

	}
	@DataProvider(name="OD_Stry0010140_Tc01")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OD_Stry0010140_Tc01");
		return arrayObject;
	}
}
