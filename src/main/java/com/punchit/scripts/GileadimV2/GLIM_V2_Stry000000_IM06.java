package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM06 extends SuiteMethods {

	// Create Instance
	@Test(dataProvider = "GLIM_V2_Stry000000_IM06",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
			String configItem1, String configItem2, 
			String configItem3, String configItem4,
			String configItem5, String configItem6){

		// Pre-requisities
		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);

			// Step 2: click on create new
			IncidentPage incident = home.clickCreateNew();

			// Take a note of the INC number.
			String incNumber = incident.getIncidentNumber();
			System.out.println(incNumber);

			// Step 3: Type CI 1 into the field
			incident.enterConfigurationItemForSuccessWithWarning(configItem1);

			// Step 4: Type CI 2 into the field
			incident.enterConfigurationItemForSuccessWithWarning(configItem2);

			// Step 5: Type CI 3 into the field
			incident.enterConfigurationItemForSuccessWithWarning(configItem3);

			// Step 6: Type CI 4 into the field
			incident.enterConfigurationItemForSuccessWithWarning(configItem4);

//			home.clickCreateNew();

//			// Take a note of the INC number.
//			String incNumber1 = incident.getIncidentNumber();
//			System.out.println(incNumber1);
			incident.verifyCiMandatory();

			// Step 3: Type CI 1 into the field
			incident.enterConfigurationItemForFailureWithWarning(configItem5);

			// Step 4: Type CI 2 into the field
			incident.enterConfigurationItemForFailureWithWarning(configItem6);			


			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM06")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM06");
		return arrayObject;
	}
}
