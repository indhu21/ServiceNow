package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_Stry000000_Tc82 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc82",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String vipUser1, String vipUser2,
								String configItem, String repCust, String asgGroup, String desc){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home =
					new LoginPage()
					.loginAs(regUser, regPwd);
			
			
			//Step 1A: Navigate to incident and click assign to me 
			IncidentPage incPage = 
					home.clickCreateNew()
			//.doubleClickonFirstAlertFourthField()
						.enterAffectedUserForFailure(vipUser1)
						.isVipFlagExistsNearAffectedUser(vipUser1);
			
			String incNumber = 
					incPage.getIncidentNumber();
				
			incPage.createIncident(configItem, repCust, asgGroup, desc, incNumber)
					.isVipFlagExistsNextToIncidentNumber();

			
//			incPage.populateMandatoryFields("Prolo", regUser, "Contact Center", "Automated test")
//					.isVipFlagExistsNearAffectedUser()
//					.submitIncident()
//					.searchIncident(incNumber)
//					.isVipFlagExistsNextToIncidentNumber();
			
			home.clickCreateNew()
//				.populateMandatoryFields("Prolo", "Alma Garcia","Contact Center", "Automated test" )
//				.isVipFlagExistsNearReportingCustomer();

				.enterAffectedUserForFailure(vipUser2)
				.isVipFlagExistsNearAffectedUser(vipUser2);
			
			String incNumber1 = 
					incPage.getIncidentNumber();
			
			incPage.createIncident(configItem, repCust, asgGroup, desc, incNumber1)
					.isVipFlagExistsNextToIncidentNumber();			
//			incPage.submitIncident()
//					.searchIncident(incNumber1)
//					.isVipFlagExistsNextToIncidentNumber();
		
			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_Stry000000_Tc82")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc82");
		return arrayObject;
	}
}
