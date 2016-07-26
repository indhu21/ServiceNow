package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;

public class GLIM_Stry000000_Tc26 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc26",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd, String causingCI, String causingCIComponent,
							   String resolutionCode, String resolutionNotes, String CI, String Reportingcustomer,
							   String Group,String Description, String Assignto) {

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);

			IncidentPage incident = home.clickCreateNewforFailure();
		
			
			String incNumber = incident.getIncidentNumber();
			System.out.println(incNumber);
			
			incident.createIncidentWithWorkInProcessAndOpenIncident(CI,
					Reportingcustomer, Group, Description, incNumber, Assignto)
					.clickResolutionInformationForNegative()
					.enterAllFieldsWithoutSave(causingCI, causingCIComponent, resolutionCode, resolutionNotes)
					.clickResolveIncident()
					.verifyActivityLogDisplyed();	

//
//			// Step 8: Enter all Mandatory fields
//			incident.populateMandatoryFields(CI, Reportingcustomer, Group, Description)
//	          .enterAssignedTo(Assignto)
//			.submitIncident();
//			
//			home.clickOpen().searchIncident(incNumber).clickFirstIncident();
//
//		    if(snW.clickByXpath("CREATEINC_WIP_Xpath"))
//		    	Reporter.reportStep("Work in Progress Button is clicked successfully", "SUCCESS");
//		    else 
//		    	Reporter.reportStep("Work in Progress Button could not be clicked", "FAILURE");
//		
//			
//			// Step 2: click on WIP		
//		    home.clickWIP().searchIncident(incNumber).clickFirstIncident();
//		   // incident.clickFirstIncident()			
//			incident.clickResolutionInformation()
//			.enterAndChooseCausingCI(causingCI)
//			.enterAndChooseCausingCIComponent(causingCIComponent)
//			.enterAndChooseResolutionCode(resolutionCode)
//			.enterResolutionNotes(resolutionNotes)	
//			.clickResolveIncident()
//			.clickActivityLog()
//			.verifyActivityLogDisplyed();

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_Stry000000_Tc26")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc26");
		return arrayObject;
	}
}
