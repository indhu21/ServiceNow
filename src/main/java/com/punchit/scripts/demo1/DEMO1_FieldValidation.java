package com.punchit.scripts.demo1;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class DEMO1_FieldValidation extends SuiteMethods {
	
	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "DEMO1_STRY0000002_TC01",groups="DemoIncident")
	public void fieldValidation (String regUser, String regPwd) throws COSVisitorException, IOException {
		
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
//			if(snW.clickLink("INCMENU_NEW"))
//				Reporter.reportStep("Step 2: The Create Incident menu selected successfully","SUCCESS");
//			else if(snW.clickById("INCMENU_ALLMENU_Id")){
//				snW.clickLink("INCMENU_NEW");
//				Reporter.reportStep("Step 2: The Create Incident menu selected successfully","SUCCESS");
//			}
//			else
//				Reporter.reportStep("Step 2: The Create Incident menu could not be selected","FAILURE");
//			
//			if (snW.selectMenuFromMainHeader("Incident", "Create_New"))
			snW.switchToFrame("Frame_Nav");
		//   snW.Wait(6000);
		   
		   if (snW.clickLink("Create_New"))
				Reporter.reportStep("The Create New - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("The Create New - menu could not be selected","FAILURE");

			
			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			/*JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,350)");*/
		
			String[] ReadFields = {"CREATEINC_IncidentNumber_Xpath","CREATEINC_IncidentPriority_Xpath",
					"CREATEINC_Opened_Xpath","CREATEINC_OpenedBy_Xpath"};
			String[] ReadLabels = {"Number","Priority","Opened","Opened by"};
			
			snW.verifyDisabledFieldsByXpath(ReadFields, ReadLabels);
			
			snW.scrollToElementByXpath("CREATEINC_IncidentPriority_Xpath");
//			String[] ManFields = {"CREATEINC_AffectedUserStar_Xpath","CREATEINC_AsgGroupStar_Xpath","CREATEINC_shortDescStar_Xpath"};
//			String[] ManLabels = {"Caller","Assignment Group","Short Description"};
			
			String[] ManFields = {"CREATEINC_shortDescStar_Xpath"};
			String[] ManLabels = {"Short Description"};
			
			snW.verifyMandatoryFields(ManFields, ManLabels);
					
			snW.switchToDefault();
			
			if (!snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("The Log out could not be clicked","FAILURE");

			// Wait for few seconds
			snW.Wait(5000);
			
			status = "PASS";
			
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