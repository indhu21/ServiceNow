package com.punchit.scripts.od;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;




import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

// for using ATU reporting -- added the listeners

public class OD_Stry0010169_Tc01 extends SuiteMethods {

	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "OD_Stry0010169_Tc01")
	public void testName(String regUser, String regPwd,
			String alertProfileName, String alertProfileDescription, String incidentAssignmentGroup, String owningGroup, String attribute)throws COSVisitorException,
	IOException {

		try {
			
			// Pre-requisities 
			snW = new ServiceNowWrappers(entityId);

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("Step 1: The login with username:"
						+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"
						+ regUser + " is not successful", "FAILURE");
            
			if (snW.selectMenu("Ops_Director","Registration_Menu", "Prof_Reg"))
				Reporter.reportStep("Step 2: The Alert Profile - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Alert Profile - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh-mm-ss");
			String formattedDate = sdf.format(date);
			String alertname1=alertProfileName+" "+formattedDate;
			
			if(snW.enterByXpath("Alert_Profile_Name_Xpath", alertname1))
				Reporter.reportStep("Step 3: Name given to the first alert profile", "SUCCESS");
			else
				Reporter.reportStep("Step 3: Name not given to the first alert profile", "FAILURE");
			
			if(snW.enterByXpath("Alert_Profile_Description_Xpath", alertProfileDescription))
				Reporter.reportStep("Step 4: Description given for the new alert profile", "SUCCESS");
			else
				Reporter.reportStep("Step 4: Description not given for the new alert profile", "FAILURE");
			
			if(snW.selectByVisibleTextById("autoClose", "Yes"))
				Reporter.reportStep("Step 5: Yes selected for Autoclose", "SUCCESS");
			else
				Reporter.reportStep("Step 5: Yes not selected for Autoclose", "FAILURE");
			
			if(snW.selectByVisibleTextById("Alert_Profile_CIScope_Id", "CIAnythingScope"))
				Reporter.reportStep("Step 6: CIAnythingScope selected in CI Scope", "SUCCESS");
			else
				Reporter.reportStep("Step 6: CIAnythingScope not selected in CI Scope", "FAILURE");

			if(snW.selectByVisibleTextById("Dy_Inc_Asn_Grp", "No"))
				Reporter.reportStep("Step 7: No selected for Dynamic Assignment Group", "SUCCESS");
			else
				Reporter.reportStep("Step 7: No not selected for Dynamic Assignment Group", "FAILURE");

			if(snW.enterByXpath("Alert_Profile_IncidentAssignmentGroup_Xpath", incidentAssignmentGroup))
				Reporter.reportStep("Step 8: Incident Assignment Group selected", "SUCCESS");
			else
				Reporter.reportStep("Step 8: Incident Assignment Group not selected", "FAILURE");

			if(snW.enterByXpath("Alert_Profile_OwningGroup_Xpath", owningGroup))
				Reporter.reportStep("Step 9: Owning Group selected", "SUCCESS");
			else
				Reporter.reportStep("Step 9: Owning Group selected", "FAILURE");
			
			snW.Wait(3000);
			
			if(snW.selectByVisibleTextById("Alert_Reaction", "Create Incident"))
				Reporter.reportStep("Step 10: Default value selected", "SUCCESS");
			else
				Reporter.reportStep("Step 10: Default value not selected", "FAILURE");
			
			if(snW.clickByXpath("Alert_Profile_SubmitButton_Xpath"))
				Reporter.reportStep("Step 11: Submit Button clicked successfully", "SUCCESS");
			else
				Reporter.reportStep("Step 11: Submit Button not clicked", "FAILURE");
			
			snW.Wait(3000);
			
			String Alert1=snW.getAttributeById("Prof_Num","value");
			System.out.println(Alert1);
			
			// Second alert profile creation
			if (snW.selectMenu("Ops_Director","Registration_Menu", "Prof_Reg"))
				Reporter.reportStep("Step 12: The Alert Profile - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 12: The Alert Profile - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			Date date1 = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy hh-mm-ss");
			String formattedDate1 = sdf.format(date1);
			String alertname2=alertProfileName+" "+formattedDate;
			
			if(snW.enterByXpath("Alert_Profile_Name_Xpath", alertname2))
				Reporter.reportStep("Step 13: Name given to the second alert profile", "SUCCESS");
			else
				Reporter.reportStep("Step 13: Name not set to the second alert profile", "FAILURE");
			
			if(snW.enterByXpath("Alert_Profile_Description_Xpath", alertProfileDescription))
				Reporter.reportStep("Step 14: Description given for the new alert profile", "SUCCESS");
			else
				Reporter.reportStep("Step 14: Description not given for the new alert profile", "FAILURE");
			
			if(snW.selectByVisibleTextById("autoClose", "Yes"))
				Reporter.reportStep("Step 15: Yes selected for Autoclose", "SUCCESS");
			else
				Reporter.reportStep("Step 15: Yes could not be selected for Autoclose", "FAILURE");
			
			if(snW.selectByVisibleTextById("Alert_Profile_CIScope_Id", "CIAnythingScope"))
				Reporter.reportStep("Step 16: CIAnythingScope selected in CI Scope", "SUCCESS");
			else
				Reporter.reportStep("Step 16: CIAnythingScope not selected in CI Scope", "FAILURE");

			if(snW.selectByVisibleTextById("Dy_Inc_Asn_Grp", "No"))
				Reporter.reportStep("Step 17: No selected for Dynamic Assignment Group", "SUCCESS");
			else
				Reporter.reportStep("Step 17: No not selected for Dynamic Assignment Group", "FAILURE");

			if(snW.enterByXpath("Alert_Profile_IncidentAssignmentGroup_Xpath", incidentAssignmentGroup))
				Reporter.reportStep("Step 18: Incident Assignment Group selected", "SUCCESS");
			else
				Reporter.reportStep("Step 18: Incident Assignment Group not selected", "FAILURE");

			if(snW.enterByXpath("Alert_Profile_OwningGroup_Xpath", owningGroup))
				Reporter.reportStep("Step 19: Owning Group selected", "SUCCESS");
			else
				Reporter.reportStep("Step 19: Owning Group selected", "FAILURE");
			
			snW.Wait(3000);
			
			if(snW.selectByVisibleTextById("Alert_Reaction", "Create Incident"))
				Reporter.reportStep("Step 20: Default value selected", "SUCCESS");
			else
				Reporter.reportStep("Step 20: Default value not selected", "FAILURE");
			
			if(snW.clickByXpath("Alert_Profile_SubmitButton_Xpath"))
				Reporter.reportStep("Step 21: Submit Button clicked successfully", "SUCCESS");
			else
				Reporter.reportStep("Step 21: Submit Button not clicked", "FAILURE");
			
			snW.Wait(3000);
			
			String Alert2=snW.getAttributeById("Prof_Num","value");
			System.out.println(Alert2);
			
			// Step 22: Expand OpsDirector/OpsConsole/under application navigator to select Alert Console
			if(snW.selectMenu("Ops_Director","Configurations", "Alert_Profiles"))
				Reporter.reportStep("Step 22: The Alert Profiles under Configurations - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 22: The Alert Profiles under Configurations - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			
			// Step 23: Open an Alert  Profile by clicking on Number link
			if(!snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Number"))
				Reporter.reportStep("Step 23: Number could not be selected from the search dropdown", "FAILURE");
			if(!snW.enterByXpathAndClick("ALERTPROFILE_Search_Xpath", Alert1))
				Reporter.reportStep("Step 23: Alert number could not be entered in the search value", "FAILURE");
			snW.Wait(2000);

			if(!snW.clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
				Reporter.reportStep("Step 23: Alert profile could not be clicked", "FAILURE");
			
			// Switch to the main frame
			snW.switchToFrame("Frame_Main");

			// Step 24: Go to Overrides field and click on look up link		
			if (snW.enterAndChoose("ALERTPROFILE_Overrides_Xpath", alertname2))
				Reporter.reportStep("Step 23: The Overrides has been selected successfully with value: "+alertname2 , "SUCCESS");
			else
				Reporter.reportStep("Step 23: The Overrides Group could not be entered","FAILURE");
			
			
			// Step 25: Save Record by clicking update at top of the screen
			
			if (!snW.rightClickById("CIS_Menu_Id"))
				Reporter.reportStep("Step 24: The Right click could not be clicked","FAILURE");

			if (snW.clickByXpath("CIS_SaveRecord_Xpath"))
				Reporter.reportStep("Step 24: The Save is clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 24: The Save could not be clicked","FAILURE");
			
			snW.Wait(5000);
			
			
			// Step 26: Go back to Alert Profiles and open the Alert Profile selected 
			if(!snW.clickByXpath("AlertProfile_Overrides_Xpath"))
				Reporter.reportStep("Step 25: Overrides tab couldnt be clicked", "FAILURE");	
			
			//Scrolling down
			//snW.scrollToElementByXpath("AlertConfiguration_OverriddingProfilestab_Xpath", 0, 4000);
			if(snW.scrollToElementByXpath("AlertConfiguration_OverriddingProfilestab_Xpath"))
			     Reporter.reportStep("Step 25: Overridding profiles is displayed","SUCCESS");
			else
			     Reporter.reportStep("Step 25: Overridding profiles could not be displayed", "FAILURE");		

			if(!snW.VerifyByLink(Alert1, true))
				Reporter.reportStep("Step 26: Overridding was unsuccessful", "FAILURE");		

			// go out of the frame
				snW.switchToDefault();

		   // Log out
		   if(!snW.clickByXpath("Logout_Xpath"))
			  Reporter.reportStep("Step 26: The logout Failed", "FAILURE");		

						status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "OD_Stry0010169_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("OD_Stry0010169_Tc01");
		return arrayObject;
	}

	

}