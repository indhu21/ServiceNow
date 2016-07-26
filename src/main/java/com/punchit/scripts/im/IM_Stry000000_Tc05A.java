package com.punchit.scripts.im;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class IM_Stry000000_Tc05A extends SuiteMethods {

	// Create Instance
	@Test(dataProvider = "IM_Stry000000_Tc05A",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
							   String configItem1, String configItem2, 
							   String configItem3, String configItem4){

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
			incident.enterConfigurationItemForSuccess(configItem1);
			
			// Step 4: Type CI 2 into the field
			incident.enterConfigurationItemForSuccess(configItem2);
			
			// Step 5: Type CI 3 into the field
			incident.enterConfigurationItemForSuccess(configItem3);
			
			// Step 6: Type CI 4 into the field
			incident.enterConfigurationItemForSuccess(configItem4);
										
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc05A")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc05A");
		return arrayObject;
	}
}
