package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;

public class GLIM_V2_Stry000000_IM03 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_IM03",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd,String configItem, String repCust, 
								String asgGroup, String desc, String asgTo,String workNotes,String CustomerComms,
								String causingCI, String causingCIComponent, String resolutionCode, String resolutionNotes,
								String CI, String Reportingcustomer, String Group,String Description, String Assignto,
								String regUser1, String regPwd1, String emailId) {

		// Pre-requisities

		try {

			snW.launchApp(browserName, true);

			MenuPage home =
					new LoginPage()
					.loginAs(regUser, regPwd);

			IncidentPage incident = 
					home.clickCreateNew();

			String incNumber = 
					incident.getIncidentNumber();
			System.out.println(incNumber);
			incident.verifyStateAndPriority()			
					.populateMandatoryFields(configItem, repCust, asgGroup, desc)
					.enterAssignedTo(asgTo)
					.submitIncidentAndOpen(incNumber)
					.clickActivityLogForFailure()
					.verifyActivityLog(asgTo,asgGroup)
					.uploadFile("GLIM_V2_Stry000000_IM03")
					.clickActivityLogForFailure()
					.verifyAttachmentUploaded()
					.RemoveAttachment()
					.clickActivityLogForFailure()
					.verifyAttachmentRemoved()
					.verifyComments(workNotes, CustomerComms)
					.clickWIP()
					.clickResolutionInformationForNegative()
					.enterAllFieldsWithoutSave(causingCI, causingCIComponent, resolutionCode, resolutionNotes)
					.clickResolveIncident()
					.verifyActivityLogDisplyed();
				home.clickLogout();
					new LoginPage()
					.loginAs(regUser1, regPwd1);
				home.clickCreateNewforFailure();
				String incNumber1 = 
						incident.getIncidentNumber();
						incident.createIncidentAndOpen(configItem, repCust, 
								asgGroup, Description, incNumber1)
								.sendEmail(emailId)
								.rightClickReload()
								.clickActivityLogForFailure()
								.verifyEmailInActivityLog();
					
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM03")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM03");
		return arrayObject;
	}
}

