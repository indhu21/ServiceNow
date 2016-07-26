package com.punchit.scripts.demo1;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import testng.SuiteMethods_1;
import testng.SuiteMethods_demo1;
import utils.DataInputProvider;
import utils.Reporter;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class DEMO1_IncidentAssignment extends SuiteMethods {
	
	
	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "DEMO1_STRY0000002_TC01",groups="DemoIncident")
	public void incidentAssignment (String regUser, String regPwd) throws COSVisitorException, IOException {
		
		// Prerequisites
		snW = new ServiceNowWrappers(entityId);
		
		try {
			
			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("The login with username:"+ regUser + " is not successful", "FAILURE");
			
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
				Reporter.reportStep("The Open Unassigned - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("The Open Unassigned - menu could not be selected","FAILURE");
			
			snW.switchToFrame("Frame_Main");
/*			if(snW.clickByXpath("First_Searched_Record_Xpath")){
				snW.Wait(5000);
				Reporter.reportStep("Incident to be 'Assigned' opened","SUCCESS");
			}else
				Reporter.reportStep("Incident to be 'Assigned' could not be opened","FAILURE");
*/			
/*			if(snW.clickById("AssignToMe_Button_Id"))
				Reporter.reportStep("Assign to me button clicked","SUCCESS");
			else
				Reporter.reportStep("Assign to me button could not be clicked","FAILURE");
*/			
			String incidentnumber = snW.getTextByXpath("First_Searched_Record_Xpath");
			System.out.println(incidentnumber);
			if(snW.rightClickByXpath("FirstLink_Xpath"))
				Reporter.reportStep("Right click on the incident is successful ","SUCCESS");
			else
			{   status = "INSUFFICIENT DATA";
				Reporter.reportStep("Right click on the incident could not be performed ","FAILURE");
			}
			
			//Assign to me
			if(snW.clickByXpath("Demo_AssignToMe_Xpath"))
			Reporter.reportStep("Incident is assigned to me successfully ","SUCCESS");
			else
			Reporter.reportStep("Incident could not be assigned to me ","FAILURE");
	
			snW.Wait(3000);
			snW.switchToDefault();
			String user = snW.getTextById("FullName_Id");
			System.out.println(user);
			snW.switchToFrame("Frame_Nav");
			
			if (snW.selectMenuFromMainHeader("Incident", "Assignedtome"))
				Reporter.reportStep("The Assigned to me - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("The Assigned to me - menu could not be selected","FAILURE");
			
			snW.Wait(2000);
			snW.switchToDefault();
			snW.switchToFrame("Frame_Main");
			snW.enterByXpath("Demo_SearchReferenceData_Xpath", incidentnumber);
			snW.PresEnter();
			snW.Wait(2000);
			String assnTO = snW.getTextByXpath("FirstLink_Xpath");
			System.out.println(assnTO);
			if(incidentnumber.equals(assnTO))
				Reporter.reportStep("Incident successfully assigned to "+user,"SUCCESS");
			else
				Reporter.reportStep("Incident could not be assigned to "+user,"FAILURE");
		
			
			snW.switchToDefault();
			
			if (!snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("The Log out could not be clicked","FAILURE");

			// Wait for few seconds
			snW.Wait(5000);
			
			status="PASS";
			
		} finally {
			snW.switchToDefault();
			if(snW.isExistByXpath("Logout_Xpath"))
				{if (!snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("The Log out could not be clicked","FAILURE");}
			snW.quitBrowser();
			}

}

	@DataProvider(name = "DEMO1_STRY0000002_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("DEMO1_STRY0000002_TC01");
		return arrayObject;
	}

}
