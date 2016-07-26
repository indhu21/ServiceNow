package com.punchit.scripts.im;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class IM_Stry000000_Tc82 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc82",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);
			
			
			//Step 1A: Navigate to incident and click assign to me 
			IncidentPage incPage=home.clickCreateNew()
			//.doubleClickonFirstAlertFourthField()
			.enterAffectedUser("AJ Jones");
			String incNumber=incPage.getIncidentNumber();
			
			
			incPage.populateMandatoryFields("Prolo", regUser, "Contact Center", "Automated test")
			.isVipFlagExistsNearReportingCustomer()
			.submitIncident()
			.searchIncident(incNumber)
			.isVipFlagExistsNextToIncidentNumber();
			
			home.clickCreateNew()
			.populateMandatoryFields("Prolo", "Alma Garcia","Contact Center", "Automated test" )
			.isVipFlagExistsNearReportingCustomer();
			String incNumber1=incPage.getIncidentNumber();
			incPage.submitIncident()
			.searchIncident(incNumber1)
			.isVipFlagExistsNextToIncidentNumber();
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			

			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc82")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc82");
		return arrayObject;
	}
}
