/*package com.punchit.scripts.im;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.IncidentsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class IM_Stry000000_Tc34 extends SuiteMethods{
	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "IM_Stry000000_Tc34",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd) throws InterruptedException{

		// Pre-requisities
		snW = new ServiceNowWrappers(entityId);

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, false);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);
			
			IncidentsListPage incident = home.clickWIP();
			
			if(!snW.clickByXpath("Incident_WIP_NumberFirstRecord_Xpath"))
				Reporter.reportStep("Incident not selected", "FAILURE");
			if(!snW.clickByXpath("Incident_WIP_ResolutionInformation_Xpath"))
				Reporter.reportStep("User not able to select Resolution Information tab", "FAILURE");
			if(!snW.clickById("Incident_WIP_CausingCILookup_Id"))
				Reporter.reportStep("User not able to click the lookup for Causing CI field", "FAILURE");
			snW.switchToSecondWindow();
			if(!snW.clickById("Incident_CausingCILookupFunnelIcon_Id"))
				Reporter.reportStep("User not able to click the funnel icon", "FAILURE");
			Thread.sleep(3000);
			if(!snW.selectByVisibleTextByXpath("Incident_CausingCILookupChooseDropdown_Xpath", "Class"))
				Reporter.reportStep("Class not selected from the drop down", "FAILURE");
			if(!snW.selectByVisibleTextByXpath("Incident_CausingCILookupOperatorDropdown_Xpath", "is"))
				Reporter.reportStep("is not selected from the drop down", "FAILURE");
			if(!snW.selectByVisibleTextByXpath("Incident_CausingCILookupValueDropdown_Xpath", "Server"))
				Reporter.reportStep("Server not selected from the drop down", "FAILURE");
			if(!snW.clickByXpath("Incident_CausingCILookupRunButton_Xpath"))
				Reporter.reportStep("Run Button not clicked", "FAILURE");
			Thread.sleep(4000);
			if(snW.clickByXpath("Incident_CausingCILookupAnyServer_Xpath"))
				Reporter.reportStep("Causing CI with Server class selected", "SUCCESS");
			else
			{
				status="INSUFFICIENT DATA";
				Reporter.reportStep("No Records displayed", "FAILURE");
			}
			//snW.clickByXpath("Incident_CausingCILookupAnyServer_Xpath");
			Thread.sleep(2000);
			snW.switchToPrimary();
			snW.switchToFrame("Frame_Main");
			if(!snW.clickById("Incident_CausingCIComponentLookup_Id"))
				Reporter.reportStep("User not able to click the lookup for Causing CI Component field", "FAILURE");
			//snW.switchToSecondWindow();
			//Incident incident1=new Incident();
			incident.verifyCausingCIComponentFieldLookUpValuesForServer();
			status="PASS";
		}
		finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "IM_Stry000000_Tc34")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc34");
		return arrayObject;
	}
}*/
