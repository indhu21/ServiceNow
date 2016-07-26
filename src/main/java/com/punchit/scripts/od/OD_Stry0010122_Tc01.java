package com.punchit.scripts.od;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class OD_Stry0010122_Tc01 extends SuiteMethods {

	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "OD_Stry0010122_Tc01")
	public void testName(String regUser, String regPwd) {

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

			// Step 2: Open Alert Console under user consoles
			if(snW.selectMenu("Ops_Director","Configurations", "CIS_Scopes"))
				Reporter.reportStep("Step 2: The 'CIS Scopes' under Configurations - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The 'CIS Scopes' under Configurations - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");

			//Step 3: Open CI Scope as per reference data – owned by parent group - by clicking on Number 
			
			if(!snW.clickById("CIS_FunnelIcon_Id"))
				Reporter.reportStep("Step 3: The funnel icon could not be clicked","FAILURE");
			
			snW.Wait(5000);
			
			/*if(!snW.deleteAllFilters())
				Reporter.reportStep("Step 3: The 'All Filters' could not be removed","WARNING");
			*/
			snW.Wait(5000);

			if(!snW.addNewFilter("Name", "is", "Punch TC122 User1"))
				Reporter.reportStep("Step 3: The 'New Filters' could not be selected","WARNING");
			snW.Wait(5000);
			
			if(!snW.clickByXpath("ALERT_RunFilter_Xpath"))
				Reporter.reportStep("Step 3: 'Run' could not be clicked", "FAILURE");

			snW.Wait(5000);			

			String alertCISco = snW.getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");

			if(snW.clickLink(alertCISco, false))
				Reporter.reportStep("Step 3: The 'First Alert CI Scope: ' "+ alertCISco +" could be selected","SUCCESS");
			else
				Reporter.reportStep("Step 3: The 'First Alert CI Scope:' "+ alertCISco +" could not be selected","FAILURE");

			
			String[] elements={"CIS_Number_Xpath","CIS_Name1_Xpath", "CIS_ParentScope_Xpath", "CIS_Owning_group_Xpath", "CIS_shortDesc_Xpath", "CIS_ScopeType_Xpath" };
			String[] values={"Number", "Name", "Parent Scope", "Owning Group", "Short Decription", "Scope Type"  };
			
			snW.verifyDisabledFieldsByXpath(elements, values);
				
			if(snW.selectMenu("Ops_Director","Configurations", "CIS_Scopes"))
				Reporter.reportStep("Step 5: The 'CIS Scopes' under Configurations - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 5: The 'CIS Scopes' under Configurations - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");

			//Step 3: Open CI Scope as per reference data – owned by parent group - by clicking on Number 
			
			if(!snW.clickById("CIS_FunnelIcon_Id"))
				Reporter.reportStep("Step 6: The funnel icon could not be clicked","FAILURE");
			
			snW.Wait(5000);
			
			if(!snW.deleteAllFilters())
				Reporter.reportStep("Step 6: The 'All Filters' could not be removed","WARNING");
			
			snW.Wait(5000);

			if(!snW.addNewFilter("Name", "is", "Punch TC122 User 1.1.1"))
				Reporter.reportStep("Step 6: The 'New Filters' could not be selected","WARNING");
			snW.Wait(5000);
			
			if(!snW.clickByXpath("ALERT_RunFilter_Xpath"))
				Reporter.reportStep("Step 6: 'Run' could not be clicked", "FAILURE");

			snW.Wait(5000);			

			alertCISco = snW.getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");

			if(snW.clickLink(alertCISco, false))
				Reporter.reportStep("Step 6: The 'First Alert CI Scope: ' "+ alertCISco +" could be selected","SUCCESS");
			else
				Reporter.reportStep("Step 6: The 'First Alert CI Scope:' "+ alertCISco +" could not be selected","FAILURE");

			
			snW.verifyEnabledFieldsByXpath(elements, values);
			
			// go out of the frame
			snW.switchToDefault();

			// Log out
			if(snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("Step 8: The Log out is clicked successfully.","SUCCESS");
			else
				Reporter.reportStep("Step 8: The logout Failed", "FAILURE");		
						
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "OD_Stry0010122_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("OD_Stry0010122_Tc01");
		return arrayObject;
	}

	

}