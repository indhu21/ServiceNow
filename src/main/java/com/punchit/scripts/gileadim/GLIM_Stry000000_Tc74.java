package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class GLIM_Stry000000_Tc74 extends SuiteMethods {
	
	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc74",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
							   String configItem, String repCust, 
				               String asgGroup, String desc,
				               String aUser, String filter1,
				               String filter2, String filter3,String selectBy, String searchItem) {
		
		String [] ele = {"Account unlocked",
						"Cancelled - SR",
						"Cancelled by User",
						"Capacity Management", 
						"Data retrieved/restored",
						"DHCP reset",
						"DNS restored",
						"Documentation Updated"};
		String [] ele1={"Emergency Fix",
						"Passed to external vendor",
						"Pwd Change",
						"Software upgrade/install",
						"Training suggested",
						"Training/knowledge provided",
						"Unabled to reproduce"};
		
		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);


			// Step 2: click on WIP		
			IncidentPage incident = home.clickCreateNew();

			// Take a note of the INC number.
			String incNumber = incident.getIncidentNumber();
			System.out.println(incNumber);

			// Step 8: Enter all Mandatory fields
//			incident
//			.populateMandatoryFields(configItem, repCust, asgGroup, desc)
//			.enterAssignedTo(aUser)
//			.submit()
//			.searchIncident(incNumber)
//			.clickFirstIncident()
			incident.createIncidentWithAssignedToAndOpenIncident(configItem, repCust, asgGroup, desc, incNumber, aUser)
//			.clickResolveIncidentWithAlertAccept()
//			.alertAcceptforResolve()
//			.isExistResolutionInformation()
//			.isExistResolutionCodefield()
			.VerifyResolutionInformationAndCodefieldExists()
//			.enterAndChooseCausingCI(filter3)
//			.clickCausingCISpyGlass()
//			.selectFilter(filter1, filter2, filter3)
//			.selectFilter(filter1, filter2, filter3)
//			.clickFirstName()
//			.clickResolutioCodeSpyGlass()
			.enterCausingCIandClickResolutionCodeSpyglass(filter3)
			/*.verifyResolutionCodeSpyGlass(ele)	
			.verifyResolutionCodeSpyGlass(ele1);*/
			.verifyResolutionCodeSpyGlassCIcounts();

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_Stry000000_Tc74")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc74");
		return arrayObject;
	}
}
