package com.punchit.scripts.demo;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class DEMO_FieldValidation extends SuiteMethods {
	
	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "DEMO_STRY0000002_TC01",groups="DemoIncident")
	public void fieldValidation (String regUser, String regPwd) throws COSVisitorException, IOException {
		
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
			
//			snW.clickByXpath("Banner_Min_Xpath");
//			snW.Wait(500);
//			snW.clickByXpath("Banner_Min_Xpath");
//			
//			snW.switchToFrame("Frame_Nav");
//			snW.Wait(3000);
//			
//			if(snW.clickLink("INCMENU_NEW"))
//				Reporter.reportStep("Step 2: The Create Incident menu selected successfully","SUCCESS");
//			else if(snW.clickById("INCMENU_ALLMENU_Id")){
//				snW.clickLink("INCMENU_NEW");
//				Reporter.reportStep("Step 2: The Create Incident menu selected successfully","SUCCESS");
//			}
//			else
//				Reporter.reportStep("Step 2: The Create Incident menu could not be selected","FAILURE");
//			
			if (snW.selectMenuFromMainHeader("Incident", "Create_New"))
				Reporter.reportStep("Step 2: The Create New - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Create New - menu could not be selected","FAILURE");

			
			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			
			String[] ReadFields = {"CREATEINC_IncidentNumber_Xpath","CREATEINC_IncidentPriority_Xpath",
					"CREATEINC_Opened_Xpath","CREATEINC_OpenedBy_Xpath"};
			String[] ReadLabels = {"Number","Priority","Opened","Opened by"};
			
			snW.verifyDisabledFieldsByXpath(ReadFields, ReadLabels);
			
			String[] ManFields = {"CREATEINC_AffectedUserStar_Xpath","CREATEINC_AsgGroupStar_Xpath","CREATEINC_shortDescStar_Xpath"};
			String[] ManLabels = {"Caller","Assignment Group","Short Description"};
			
			snW.verifyMandatoryFields(ManFields, ManLabels);
					
			status="PASS";
				
			
		} finally {
			// close the browser
			snW.quitBrowser();
			}

}

	@DataProvider(name = "DEMO_STRY0000002_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("DEMO_STRY0000002_TC01");
		return arrayObject;
	}
}