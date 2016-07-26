package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0010110_TC01 extends SuiteMethods {
	
	
		@Test(dataProvider = "GLOD_STRY0010110_TC01",groups="OpsDirector")
		public void profval(String regUser, String regPwd) throws COSVisitorException, IOException {
			
			
			try {

					// Step 0: Launch the application
					snW.launchApp(browserName, true);
	
					// Step 1: Log in to application
					if (snW.login(regUser, regPwd))
						Reporter.reportStep("The login with username:"+ regUser + " is successful", "SUCCESS");
					else
						Reporter.reportStep("The login with username:"+ regUser + " is not successful", "FAILURE");
					
					// Step 2: In application navigator expand OpsDirector/Registration to select Profile Registration
					if (snW.selectMenu("Ops_Director","Registration", "Prof_Reg"))
						Reporter.reportStep("The Profile Registration menu selected successfully","SUCCESS");
					else
						Reporter.reportStep("The Profile Registration menu could not be selected","FAILURE");
					
					// Switch to the main frame
					snW.switchToFrame("Frame_Main");
					
					// Step 3: Fill the fields and click submit
					snW.enterById("Name", "Punch Profile 110");
					if(snW.doubleCickByXpath("ALERTPROFILE_Registartion_CIscopes_xpath"))
						Reporter.reportStep("CI Scope is selected successfully", "SUCCESS");
					else
						Reporter.reportStep("CI scope could not be selected", "FAILURE");


					snW.selectByVisibleTextById("Dy_Inc_Asn_Grp", "Yes");
					
					snW.selectByVisibleTextById("Inc_Asn_Area", "Infrastructure");
					if(snW.enterAndChoose1("Alert_Profile_OwningGroup_Xpath_new_sparc", "**"))
					Reporter.reportStep("Owning group is selected successfully", "SUCCESS");
					else
					Reporter.reportStep("Owning group could not be selected", "FAILURE");
						
					snW.selectByVisibleTextById("Alert_Reaction", "Create Incident");
					snW.enterById("Description", "Test ticket for STRY0010110");
//					snW.Wait(5000);
					
					snW.clickById("submit_button");
//					snW.Wait(10000);
					
					String profnum = snW.getAttributeById("Prof_Num", "value");
					if (profnum != "")
						Reporter.reportStep("Profile - "+profnum+ " created successfully","SUCCESS");
					else
						Reporter.reportStep("Profile creation failed","FAILURE");
					
					//Step 4: Set Incident Assignment Area to -- None --
					
					if(!snW.clickByXpath("AlertProfile_AlertReaction_Xpath"))
						Reporter.reportStep("The Alert Reaction Tab is not clicked or not found.","FAILURE");
					
					snW.selectByVisibleTextById("Inc_Asn_Area_Input", "-- None --");
					
					String incarea = snW.getAttributeById("Inc_Asn_Area_Mandatory", "mandatory");
					
					if ("true".equals(incarea))
						Reporter.reportStep("Incident Assignment Area set to -- None -- and is mandatory","SUCCESS");
					else
						Reporter.reportStep("Incident Assignment Area is not mandatory","FAILURE");
					
					// Step 5: Set Incident Assignment Group
					snW.enterAndChoose1("Inc_Asn_Grp_xpath", "**");
					snW.Wait(3000);
//					snW.clickByXpath("Alert_Reaction_Xpath");
					snW.Wait(5000);
					if (!snW.isExistById("Inc_Asn_Area_Input"))
						Reporter.reportStep("Incident Assignment Group set  and Incident Assignment Area is hidden","SUCCESS");
					else
						Reporter.reportStep("Incident Assignment Group value not set","FAILURE");
					
					// Step 6: Clear Incident Assignment Group
					snW.enterByIdAndTab("Inc_Asn_Grp_Input", null);
//					snW.clickByXpath("Alert_Reaction_Xpath");
					snW.Wait(1000);
					if (snW.isExistById("Inc_Asn_Area_Input"))
						Reporter.reportStep("Incident Assignment Group cleared and Incident Assignment Area is shown","SUCCESS");
					else
						Reporter.reportStep("Incident Assignment Group value not cleared","FAILURE");
					
					// Step 7: Re-enter previous Assignment Group
					snW.enterAndChoose1("Inc_Asn_Grp_xpath", "**");
					snW.Wait(3000);
//					snW.clickByXpath("Alert_Reaction_Xpath");
					snW.Wait(1000);
					if (!snW.isExistById("Inc_Asn_Area_Input"))
						Reporter.reportStep("Incident Assignment Group set and Incident Assignment Area is hidden","SUCCESS");
					else
						Reporter.reportStep("Incident Assignment Group value not set","FAILURE");
					
					// Step 9: Set Reaction Type
					snW.selectByVisibleTextById("Reac_Type", "Create Incident");
					
					// Step 10: Right and Save Profile
					if (!snW.rightClickById("Prof_Rclick"))
						Reporter.reportStep("The Right click could not be clicked","FAILURE");
					
					snW.Wait(500);

					if (snW.clickByXpath("Prof_Save_Xpath"))
						Reporter.reportStep("The Save is clicked successfully","SUCCESS");
					else
						Reporter.reportStep("The Save could not be clicked","FAILURE");
					
					snW.Wait(5000);

					// go out of the frame
					snW.switchToDefault();

					// Step 11: Log out
					if (snW.clickByXpath("Logout_Xpath"))
						Reporter.reportStep("The Log out is clicked successfully","SUCCESS");
					else
						Reporter.reportStep("The Log out could not be clicked","FAILURE");

					// Wait for few seconds
					snW.Wait(5000);
					
					status = "PASS";
									
				} finally {
				// close the browser
				snW.quitBrowser();
				}
			
		}
		
		@DataProvider(name = "GLOD_STRY0010110_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010110_TC01");
			return arrayObject;
		}

}