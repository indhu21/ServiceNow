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

public class GLIM_Stry000000_Tc76 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc76",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String causingCI ,String CI,
								String Reportingcustomer, String Group, String Description, String Assignto ) {

		// Pre-requisities

		try {
			
			
			String[] elements={"Automated Job Failure",
					"Connectivity",
					"Data Issue",
					"Error message",
					"Integration Issue",
					"Login failure",
					"Memory",
					"Not responding/Frozen",
					"Performance degradation",
					"Security breach",
					"Storage",
					"UI issue",
					"Virus",
					"indhu"};
/*			String[] elements = {"Account Locked",
					"Cancelled - By User",
					"Cancelled - SR",
					"Capacity Management",
					"Cooling restored",
					"Data retrieved/restored",
					"DHCP reset",
					"DNS restored",
					"Documentation Updated",
					"Emergency Fix",
					"Hardware replaced/fixed",
					"Passed to external vendor",
					"Pwd Change",
					"Software upgrade/install",
					"Training suggested",
					"Training/knowledge provided",
					"Unabled to reproduce"
};
*/			

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


	@DataProvider(name = "GLIM_Stry000000_Tc76")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc76");
		return arrayObject;
	}
}
