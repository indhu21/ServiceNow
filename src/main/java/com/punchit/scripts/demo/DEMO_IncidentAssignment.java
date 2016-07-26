package com.punchit.scripts.demo;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import testng.SuiteMethods_1;
import utils.DataInputProvider;
import utils.Reporter;
import utils.Reporter1;
import wrapper.ServiceNowWrappers;

public class DEMO_IncidentAssignment extends SuiteMethods_1 {
	
	
	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "DEMO_STRY0000004_TC01",groups="DemoIncident")
	public void incidentAssignment (String regUser, String regPwd) throws COSVisitorException, IOException {
		
		// Prerequisites
		snW = new ServiceNowWrappers(entityId);
		
		try {
			
			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter1.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter1.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");
			
//			snW.clickByXpath("Banner_Min_Xpath");
//			snW.Wait(500);
//			snW.clickByXpath("Banner_Min_Xpath");
//			
//			snW.switchToFrame("Frame_Nav");
//			snW.Wait(3000);
//			
//			if(snW.clickLink("INCMENU_UNASSIGN"))
//				Reporter.reportStep("Step 2: The Open - Unassigned incidents menu selected successfully","SUCCESS");
//			else if(snW.clickById("INCMENU_ALLMENU_Id")){
//				snW.clickLink("INCMENU_UNASSIGN");
//				Reporter.reportStep("Step 2: The Open - Unassigned incidents menu selected successfully","SUCCESS");
//			}else
//				Reporter.reportStep("Step 2: The Open - Unassigned incidents menu could not be selected","FAILURE");
//			
			if (snW.selectMenuFromMainHeader("Incident", "Unassigned_DEMO"))
				Reporter1.reportStep("Step 2: The Open Unassigned - menu selected successfully","SUCCESS");
			else
				Reporter1.reportStep("Step 2: The Open Unassigned - menu could not be selected","FAILURE");
			
			snW.switchToFrame("Frame_Main");
			if(snW.clickByXpath("First_Searched_Record_Xpath")){
				snW.Wait(5000);
				Reporter1.reportStep("Step 3: Incident to be Assigned opened","SUCCESS");
			}else
				Reporter1.reportStep("Step 3: Incident to be Assigned could not be opened","FAILURE");
			
			if(snW.clickById("AssignToMe_Button_Id"))
				Reporter1.reportStep("Step 4: Assign to me button clicked","SUCCESS");
			else
				Reporter1.reportStep("Step 4: Assign to me button could not be clicked","FAILURE");
				
			snW.Wait(3000);
			snW.switchToDefault();
			String user = snW.getTextById("FullName_Id");
			snW.switchToFrame("Frame_Main");
			String assnTo = snW.getAttributeByXpath("CREATEINC_AssignedTo_Xpath", "value");
			
			if(user.equals(assnTo))
				Reporter1.reportStep("Step 5: Incident successfully assigned to "+user,"SUCCESS");
			else
				Reporter1.reportStep("Step 5: Incident could not be assigned to "+user,"FAILURE");
			
			snW.switchToDefault();
			
			if (!snW.clickByXpath("Logout_Xpath"))
				Reporter1.reportStep("Step 6: The Log out could not be clicked","FAILURE");

			// Wait for few seconds
			snW.Wait(5000);
			
			status="PASS";
			
		} finally {
			// close the browser
			snW.quitBrowser();
			}

}

	@DataProvider(name = "DEMO_STRY0000004_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("DEMO_STRY0000004_TC01");
		return arrayObject;
	}

}
