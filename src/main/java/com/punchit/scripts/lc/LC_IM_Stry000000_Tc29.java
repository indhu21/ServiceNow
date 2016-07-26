package com.punchit.scripts.lc;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class LC_IM_Stry000000_Tc29 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_IM_Stry000000_Tc29",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
							   String configItem ,String repCust ,
							   String asgGroup,String desc,String asgTo,
							   String addCIdesc, String resCode,String CausingCIComponent,
							   String resolutionNotes ,String configManager, String confItem) {

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
			incident.populateMandatoryFields(configItem, repCust, asgGroup, desc)
			.enterAssignedTo(asgTo)
			.submit()
			.searchIncident(incNumber)
			.clickFirstIncident()
			.clickResolveIncident()
			.alertAcceptforResolve()
			.clickCausingCISpyGlass()
			.clickMissingCI()
			.clickCancelAddCIDesc()
			.clickCausingCISpyGlass()
			.clickMissingCI()
			.enterAddCIDescription(addCIdesc)
			.clickOkAddCIDesc()
			.enterResolutionInformationMandatoryfields(resCode, CausingCIComponent, resolutionNotes)			
			.clickSave()
			.clickActivityLog()
			.getActivityText(addCIdesc);

			String missingCausingCNumber = incident.getmissingCausingCINumber();
			//System.out.println(missingCausingCNumber);
			
			home.clickSparcLCPageLogout();
			new LoginSparcLCPage().loginAs(configManager , regPwd)
			.expandCMDBControl()
			.clickMissingCITasks()
			.searchIncident(missingCausingCNumber)
			.clickFirstIncident()
			.enterCMDBConfItem(confItem)
			.closeTask();
			
			home.clickSparcLCPageLogout();
			new LoginSparcLCPage()
			.loginAs(regUser, regPwd)
			.enterFilter("Incident")
			.clickAll()			
			.searchIncident(incNumber)
			.clickFirstIncident()
			.verifyConfigItemValue(confItem);
			home.clearFilter();

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "LC_IM_Stry000000_Tc29")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_IM_Stry000000_Tc29");
		return arrayObject;
	}
}
