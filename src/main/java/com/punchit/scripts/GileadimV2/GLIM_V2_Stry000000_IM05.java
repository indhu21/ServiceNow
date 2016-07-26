package com.punchit.scripts.GileadimV2;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.IncidentPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM05 extends SuiteMethods {

	@Test(dataProvider = "GLIM_V2_Stry000000_IM05",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
							   String configItem1,
							   String configItem2, 
							   String configItem3,
							   String configItem4){

		try {

			snW.launchApp(browserName, true);

			MenuPage home = new LoginSparcLCPage().loginAs(regUser, regPwd);
		
			// Step 2: click on create new
			IncidentPage incident = home.clickCreateNew();
			
			// Take a note of the INC number.
			String incNumber = incident.getIncidentNumber();
			System.out.println(incNumber);
			
			// Step 3: Type CI 1 into the field
			incident.enterConfigurationItemForSuccess(configItem1);
			
			// Check that the Business Service field appears below CI field only when a CI has been selected
//			incident.verifyBusinessServiceEditable();
			incident.isVerifyBusinessServiceAvilable();
			
			// Step 4: Type CI 2 into the field
			incident.enterConfigurationItemForSuccess(configItem2);
			
			// That particular business service should appear in the Business Service field and the field should be read only
			incident.verifyBusinessServiceReadOnly();
			
			// Step 5: Type CI 3 into the field
			incident.enterConfigurationItemForSuccess(configItem3).Wait(5000);
			
			// The Business Service field is mandatory to fill and the multiple business services should be available via drop down
			incident.verifyBusinessServiceMandatory().clickBusinessServiceSpyGlass().verifyBusinessService()
			.clickFirstBusinessServiceName();
			
			// Step 6: Type CI 4 into the field
			incident.enterConfigurationItemForSuccess(configItem4).Wait(5000);
			
			// The business service field appears blank and should be read only
//			incident.verifyBusinessServiceReadOnly()
			incident.verifyBusinessServiceisBlankAndReadOnly();
											
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM05")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM05");
		return arrayObject;
	}
}
