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

public class IM_Stry000000_Tc09 extends SuiteMethods {

	// Create Instance
	@Test(dataProvider = "IM_Stry000000_Tc09",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
							   String configItem1,
							   String configItem2, 
							   String businessService2,
							   String configItem3,
							   String configItem4){

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
			incident.enterConfigurationItemForSuccess(configItem1).Wait(5000);
			
			// Check that the Business Service field appears below CI field only when a CI has been selected
			incident.verifyBusinessServiceEditable().verifyBusinessServiceContent("");
			
			// Step 4: Type CI 2 into the field
			incident.enterConfigurationItemForSuccess(configItem2).Wait(5000);
			
			// That particular business service should appear in the Business Service field and the field should be read only
			incident.verifyBusinessServiceReadOnly().verifyBusinessServiceContent(businessService2);
			
			// Step 5: Type CI 3 into the field
			incident.enterConfigurationItemForSuccess(configItem3).Wait(5000);
			
			// The Business Service field is mandatory to fill and the multiple business services should be available via drop down
			incident.verifyBusinessServiceEditable().verifyBusinessServiceContent("");
			
			// Step 6: Type CI 4 into the field
			incident.enterConfigurationItemForSuccess(configItem4).Wait(5000);
			
			// The business service field appears blank and should be read only
			incident.verifyBusinessServiceEditable().verifyBusinessServiceContent("");
											
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc09")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc09");
		return arrayObject;
	}
}
