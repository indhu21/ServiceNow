package com.punchit.scripts.od;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class OD_STRY0010110_TC01_V2 extends SuiteMethods {
	
		// Create Instance
		ServiceNowWrappers snW;

		@Test(dataProvider = "OD_STRY0010110_TC01_V2",groups="OpsDirector")
		public void profval(String regUser, String regPwd) throws COSVisitorException, IOException {
			
			// Prerequisites
			snW = new ServiceNowWrappers(entityId);
			
			try {

					// Step 0: Launch the application
					snW.launchApp(browserName, true);
	
					// Step 1: Log in to application
					if (snW.login(regUser, regPwd))
						Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
					else
						Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");
					
					// Step 2: In application navigator expand OpsDirector/Registration to select Profile Registration
					if (snW.selectMenu("Ops_Director","Registration", "Prof_Reg"))
						Reporter.reportStep("Step 2: The Profile Registration menu selected successfully","SUCCESS");
					else
						Reporter.reportStep("Step 2: The Profile Registration menu could not be selected","FAILURE");
					
					// Switch to the main frame
					snW.switchToFrame("Frame_Main");
					
					// Step 3: Fill the fields and click submit
					snW.enterById("Name", "Punch Profile 110");
					snW.selectByVisibleTextById("CI_Scope", "CIAnythingScope");
					snW.selectByVisibleTextById("Dy_Inc_Asn_Grp", "Yes");
					snW.selectByVisibleTextById("Inc_Asn_Area", "Infrastructure");
					snW.enterById("Own_Grp", "Punch Group1");
					snW.selectByVisibleTextById("Alert_Reaction", "Run Runbook");
					snW.enterById("Description", "Test ticket for STRY0010110");
					snW.Wait(5000);
					
					snW.clickById("submit_button");
					snW.Wait(10000);
					
					String profnum = snW.getAttributeById("Prof_Num", "value");
					if (profnum != null)
						Reporter.reportStep("Step 3: Profile - "+profnum+ " created successfully","SUCCESS");
					else
						Reporter.reportStep("Step 3: Profile creation failed","FAILURE");
					
					//Step 4: Set Incident Assignment Area to -- None --
					snW.selectByVisibleTextById("Inc_Asn_Area_Input", "-- None --");
					String incarea = snW.getAttributeById("Inc_Asn_Area_Mandatory", "mandatory");
					if ("true".equals(incarea))
						Reporter.reportStep("Step 4: Incident Assignment Area set to '-- None --' and is mandatory","SUCCESS");
					else
						Reporter.reportStep("Step 4: Incident Assignment Area is not mandatory","FAILURE");
					
					// Step 5: Set Incident Assignment Group
					snW.enterById("Inc_Asn_Grp_Input", "Punch Group1");
					snW.Wait(3000);
					snW.clickByXpath("Alert_Reaction_Xpath");
					if (!snW.isExistById("Inc_Asn_Area_Input"))
						Reporter.reportStep("Step 5: Incident Assignment Group set to 'Punch Group1' and Incident Assignment Area is hidden","SUCCESS");
					else
						Reporter.reportStep("Step 5: Incident Assignment Group value not set","FAILURE");
					
					// Step 6: Clear Incident Assignment Group
					snW.enterById("Inc_Asn_Grp_Input", "");
					snW.clickByXpath("Alert_Reaction_Xpath");
					if (snW.isExistById("Inc_Asn_Area_Input"))
						Reporter.reportStep("Step 6: Incident Assignment Group cleared and Incident Assignment Area is shown","SUCCESS");
					else
						Reporter.reportStep("Step 6: Incident Assignment Group value not cleared","FAILURE");
					
					// Step 7: Re-enter previous Assignment Group
					snW.enterById("Inc_Asn_Grp_Input", "Punch Group1");
					snW.Wait(3000);
					snW.clickByXpath("Alert_Reaction_Xpath");
					if (!snW.isExistById("Inc_Asn_Area_Input"))
						Reporter.reportStep("Step 7: Incident Assignment Group set to 'Punch Group1' and Incident Assignment Area is hidden","SUCCESS");
					else
						Reporter.reportStep("Step 7: Incident Assignment Group value not set","FAILURE");
					
					// Step 8: Check 'Allow Incident Reopen' is present or not
					/*if (snW.isExistById("Allow_Inc_Reopen"))
						Reporter.reportStep("Step 8: Allow Incident Reopen is present","SUCCESS");
					else
						Reporter.reportStep("Step 8: Allow Incident Reopen is not present","FAILURE");*/
					
					// Step 9: Set Reaction Type
					snW.selectByVisibleTextById("Reac_Type", "Run Runbook");
					
					// Step 10: Right and Save Profile
					if (!snW.rightClickById("Prof_Rclick"))
						Reporter.reportStep("Step 8: The Right click could not be clicked","FAILURE");
					
					snW.Wait(500);

					if (snW.clickByXpath("Prof_Save_Xpath"))
						Reporter.reportStep("Step 8: The Save is clicked successfully","SUCCESS");
					else
						Reporter.reportStep("Step 8: The Save could not be clicked","FAILURE");
					
					snW.Wait(5000);

					// go out of the frame
					snW.switchToDefault();

					// Step 11: Log out
					if (snW.clickByXpath("Logout_Xpath"))
						Reporter.reportStep("Step 9: The Log out is clicked successfully","SUCCESS");
					else
						Reporter.reportStep("Step 9: The Log out could not be clicked","FAILURE");

					// Wait for few seconds
					snW.Wait(5000);
					
					status = "PASS";
									
				} finally {
				// close the browser
				snW.quitBrowser();
				}
			
		}
		
		@DataProvider(name = "OD_STRY0010110_TC01_V2")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("OD_STRY0010110_TC01_V2");
			return arrayObject;
		}

}