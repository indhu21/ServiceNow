package com.punchit.scripts.gileadim;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class GLIM_Stry000000_Tc29 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc29",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String configItem ,String repCust ,
								String asgGroup,String desc,String asgTo, String addCIdesc, String resCode,
								String CausingCIComponent, String resolutionNotes ,String configManager, String confItem) {

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginSparcLCPage().loginAs(regUser, regPwd);

			// Step 2: click on Create New		
			IncidentPage incident = home.clickCreateNew();

			// Take a note of the INC number.
			String incNumber = incident.getIncidentNumber();
			System.out.println(incNumber);

			// Step 8: Enter all Mandatory fields
			//			incident.populateMandatoryFields(configItem, repCust, asgGroup, desc)
			//			.enterAssignedTo(asgTo)
			//			.submit()
			//			.searchIncident(incNumber)
			//			.clickFirstIncident()
			incident
//			.createIncidentWithWorkInProcessAndOpenIncident(configItem, repCust, asgGroup, addCIdesc, incNumber, asgTo)
			.createIncidentAndOpenIncident(configItem, repCust, asgGroup, addCIdesc, incNumber, asgTo)
								.clickResolveIncidentWithAlertAccept()
			//			.alertAcceptforResolve()
								.enterAllFields(confItem, CausingCIComponent, resCode, resolutionNotes)
								.clickReopen(desc)
								.enterAssigned(asgTo)
								.clickWIPAndVerify()
								.clickResolutionInformation()
			//					.clickResolutionInformationForNegative()
			.clickCausingCISpyGlass()
			.clickMissingCI()
			.clickCancelAddCIDesc()
			.clickCausingCISpyGlass()
			.clickMissingCI()
			//					.enterAddCIDescription(addCIdesc)
			//					.clickOkAddCIDesc()
			.enterAddCIDescriptionAndOk(addCIdesc)
			//					.enterResolutionInformationMandatoryfields(resCode, CausingCIComponent, resolutionNotes)			
			.clickSave();
			//					.clickActivityLog()
			//					.getActivityText(addCIdesc);

			String missingCausingCNumber = incident.getmissingCausingCINumber();
			//System.out.println(missingCausingCNumber);

			home.clickSparcLCPageLogout();
			new LoginSparcLCPage()
			.loginAs(configManager , regPwd)
			.expandCMDBControl()
			.clickMissingCITasks()
			//					.searchIncident(missingCausingCNumber)
			//					.clickFirstIncident()
			.searchAndOpenTask(missingCausingCNumber)
			//					.enterCMDBConfItem(confItem)
			//					.closeTask();
			.enterCMDBConfItemAndClose(confItem);

			home.clickSparcLCPageLogout();
			new LoginSparcLCPage()
			.loginAs(regUser, regPwd)
			//					.enterFilter("Incident")
			.clickAll()			
			//					.searchIncident(incNumber)
			//					.clickFirstIncident()
			.searchAndOpenIncident(incNumber)
			.verifyConfigItemValue(confItem);

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_Stry000000_Tc29")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc29");
		return arrayObject;
	}
}
