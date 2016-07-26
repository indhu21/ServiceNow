package com.punchit.scripts.im;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class IM_Stry000000_Tc29 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc29",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String configItem , String repCust ,String asgGroup,String desc,String asgTo) {

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);

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
			.clickFirstIncident();
			

			//



			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc29")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc29");
		return arrayObject;
	}
}
