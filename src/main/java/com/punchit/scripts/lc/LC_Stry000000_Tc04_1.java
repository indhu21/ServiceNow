package com.punchit.scripts.lc;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class LC_Stry000000_Tc04_1 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_Stry000000_Tc04_1",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String name, String CIOwnerGroup, 
			String systemManager,String operationalstatus){

		// Pre-requisities
		try {

			String appName = name+snW.getCurrentTime();

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home =
					new LoginSparcLCPage()
					.loginAs(regUser, regPwd);
			
				home.expandCMDBMenu()
					.verifyExistanceOfCMDBOptions()
					.expandStorage()
					.clickStoragePoolsPageAndVerifyNewButtonExists()
					.clickStrPoolsNewButton()
					.verifyAllStoragePoolsFields()
					.verifyAllReadOnlyFields()
					.verifyOperationalstatus(operationalstatus)
					.verifyAllMandatoryFields()
					.enterAllMandatoryFields(appName, CIOwnerGroup, systemManager)
					.clickSubmit()
					.searchandClickFirstnameforSP(appName);
				
				home.clickSparcLCPageLogout();
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_Stry000000_Tc04_1")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_Stry000000_Tc04_1");
		return arrayObject;
	}
}
