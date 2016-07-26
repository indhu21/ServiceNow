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

public class GLIM_Stry000000_Tc25B extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc25B",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String configItem, String repCust, 
			   String asgGroup, String desc,
			   String asgTo,String workNotes,String CustomerComms) {

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
//					.submit()
//					.searchIncident(incNumber)
//					.clickFirstIncident()
					.submitIncidentAndOpen(incNumber)
//					.clickActivityLog()
					.clickActivityLogForFailure()
					.verifyActivityLog(asgTo,asgGroup)
					.uploadFile("GLIM_Stry000000_Tc81A")
					.clickActivityLogForFailure()
					.verifyAttachmentUploaded()
					.RemoveAttachment()
					.clickActivityLogForFailure()
					.verifyAttachmentRemoved()
					.verifyComments(workNotes, CustomerComms);
//					.clickNotes()
//					.isWorkNotesAvailable()
//					.isCustomerCommsAvailable()
//					.clickNotes()
//					.enterWorkNotes(workNotes)
//					.enterCustomerComms(CustomerComms)			
//					.saveIncident().	
//					clickActivityLog().
//					getLatestWorkNotes(workNotes).
//					getLatestCustomerComms(CustomerComms);
		
				status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_Stry000000_Tc25B")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc25B");
		return arrayObject;
	}
}

