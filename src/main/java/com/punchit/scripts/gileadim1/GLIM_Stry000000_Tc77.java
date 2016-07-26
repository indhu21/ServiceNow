package com.punchit.scripts.gileadim1;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;

public class GLIM_Stry000000_Tc77 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc77",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String causingCI ,String CI,
								String Reportingcustomer, String Group, String Description, String Assignto ) {

		// Pre-requisities

		try {
			
			String[] elements = {"Cancelled - By User",
					"Cancelled - SR",
					"Capacity Management",
					"Cooling restored",
					"Documentation Updated",
					"Emergency Fix",
					"Hardware replaced/fixed",
					"Passed to external vendor",
					"Software upgrade/install",
					"Training suggested",
					"Training/knowledge provided",
					"Unabled to reproduce"};
			

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home =
					new LoginPage()
					.loginAs(regUser, regPwd);
			
			IncidentPage incident = 
					home.clickCreateNewforFailure();

			// Take a note of the INC number.
			String incNumber = 
					incident.getIncidentNumber();
			
			System.out.println(incNumber);
				
				incident.createIncidentWithWorkInProcessAndOpenIncident(CI, Reportingcustomer, Group, Description, incNumber, regUser)
						.VerifyResolutionInformationAndCodefieldExists()
						.enterCausingCIandClickCausingCIComponentSpyGlass(causingCI)
						.verifyCIComponentLookUpValues(elements);
			


			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_Stry000000_Tc77")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc77");
		return arrayObject;
	}
}
