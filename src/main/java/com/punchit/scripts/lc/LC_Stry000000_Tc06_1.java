package com.punchit.scripts.lc;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CmdbListPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.UPSPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class LC_Stry000000_Tc06_1 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_Stry000000_Tc06_1",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String name,
			String CIOwnerGroup, String SystemManager, String operationalstatus1){

		// Pre-requisities
		try {

			String appName = name+snW.getCurrentTime();

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
					MenuPage home = new LoginSparcLCPage()
					.loginAs(regUser, regPwd);	

			// Step 2: Verify the Menus
						home.expandCMDBMenu()
						.verifyExistanceOfCMDBOptions()
						.expandDataCenter();

					home.clickUPSPageandClickNewButton()
						.verifyAllFieldsforUPS()
						.verifyAllReadOnlyFieldsforUPS()
						.verifyReadOnlyOperationalstatus(operationalstatus1)
						.verifyAllMandatoryFieldsforUPS()
						.enterAllMAndatoryFields(appName, CIOwnerGroup, SystemManager)
						.clickSubmit()
						.searchandClickFirstnameforUPS(appName)
						.verifyReadOnlyOperationalstatus(operationalstatus1);
					
					home.clickSparcLCPageLogout();
						status = "PASS";

		} finally {
			// close the browser
			//snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_Stry000000_Tc06_1")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_Stry000000_Tc06_1");
		return arrayObject;
	}
}
