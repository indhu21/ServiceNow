package com.punchit.scripts.lc;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ListPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.SystemApplicationsPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class LC_RP_Stry000000_Tc02_1 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_RP_Stry000000_Tc02_1",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String name, String CIOwnerGroup,
								String SystemManager, String operationalstatus){

		// Pre-requisities
		try {

			String appName = name+snW.getCurrentTime();

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = 
				new LoginSparcLCPage()
					.loginAs(regUser, regPwd);	

			// Step 2: Verify the Menus
				home.expandCMDBMenu()
					.verifyExistanceOfCMDBOptions();

			// Step 3: click on create new
				home.clickSystemApplicationsandClickNewButton()

			// Step 4: On the new Incident Screen, check the following fields are read only
					.verifyAllSystemApplicationFields()					
					.verifyAllReadOnlyFields()
					.verifyOperationalstatus(operationalstatus)
					.verifyAllMandatoryFields()
					.enterAllMandatoryFields(appName, CIOwnerGroup, SystemManager)
					.clickSubmit()
					.searchandClickFirstnameforSyApp(appName);
				
				home.clickSparcLCPageLogout();	
												
				status = "PASS";

		} finally {
			// close the browser
			//snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_RP_Stry000000_Tc02_1")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_RP_Stry000000_Tc02_1");
		return arrayObject;
	}
}
