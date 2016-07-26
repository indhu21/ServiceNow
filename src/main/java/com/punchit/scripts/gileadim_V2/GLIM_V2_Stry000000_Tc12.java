package com.punchit.scripts.gileadim_V2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;

public class GLIM_V2_Stry000000_Tc12 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_Tc12",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String configItem, String repCust, 
			String asgGroup, String desc, String asgTo,String workNotes,String CustomerComms, String causingCI, String causingCIComponent,
			String resolutionCode, String resolutionNotes, String CI, String Reportingcustomer, String Group,String Description, String Assignto) {

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home =
					new LoginPage().loginAs(regUser, regPwd);

			// Step 2: click on create new
			IncidentPage incident = home.clickCreateNew();

			// Take a note of the INC number.
			String incNumber = incident.getIncidentNumber();
			System.out.println(incNumber);
			incident.verifyStateAndPriority()			
					.populateMandatoryFields(configItem, repCust, asgGroup, desc)
					.enterAssignedTo(asgTo)
			//		.submit()
			//		.searchIncident(incNumber)
			//		.clickFirstIncident()
					.submitIncidentAndOpen(incNumber)
			//		.clickActivityLog()
					.clickActivityLogForFailure()
					.verifyActivityLog(asgTo,asgGroup)
					.uploadFile("GLIM_Stry000000_Tc81A")
					.clickActivityLogForFailure()
					.verifyAttachmentUploaded()
					.RemoveAttachment()
					.clickActivityLogForFailure()
					.verifyAttachmentRemoved()
					.verifyComments(workNotes, CustomerComms);
			//		.clickNotes()
			//		.isWorkNotesAvailable()
			//		.isCustomerCommsAvailable()
			//		.clickNotes()
			//		.enterWorkNotes(workNotes)
			//		.enterCustomerComms(CustomerComms)			
			//		.saveIncident().	
			//		clickActivityLog().
			//		getLatestWorkNotes(workNotes).
			//		getLatestCustomerComms(CustomerComms);
		
			home.clickCreateNewforFailure();


			incNumber = incident.getIncidentNumber();
			System.out.println(incNumber);

			incident.createIncidentWithWorkInProcessAndOpenIncident(CI,
					Reportingcustomer, Group, Description, incNumber, Assignto)
			.clickResolutionInformationForNegative()
			.enterAllFieldsWithoutSave(causingCI, causingCIComponent, resolutionCode, resolutionNotes)
			.clickResolveIncident()
			.verifyActivityLogDisplyed();	

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_Tc12")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_Tc12");
		return arrayObject;
	}
}

