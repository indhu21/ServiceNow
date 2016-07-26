package com.punchit.scripts.lc;

import java.io.IOException;
import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import pages.CmdbListPage;
import pages.IncidentPage;
import pages.IncidentsListPage;
import pages.LoginPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.NetworkSwitchesPage;
import pages.ServerWindowsPage;
import pages.StoragePoolsPage;
import pages.SystemApplicationsPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class LC_Stry000000_Tc05_1 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_Stry000000_Tc05_1",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String operationalstatus1, String name,
								String CIOwnerGroup, String systemManager){

		// Pre-requisities
		try {


			String appName = name+snW.getCurrentTime();

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginSparcLCPage().loginAs(regUser, regPwd);

			// Step 2: Verify the Menus
						home.expandCMDBMenu()
							.verifyExistanceOfCMDBOptions()
							.expandNetwork();
					
						home.clickNetworkSwitchesandClickNewButton()
							.verifyAllNetworkSwitchesFields()
							.verifyAllReadOnlyFields()
							.verifyOperationalstatus(operationalstatus1)
							.verifyAllMandatoryFields()
							.enterAllMandatoryFields(appName, CIOwnerGroup, systemManager)
							.clickSubmit()
							.searchandClickFirstnameforNW(appName);
						
						home.clickSparcLCPageLogout();

						  status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_Stry000000_Tc05_1")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_Stry000000_Tc05_1");
		return arrayObject;
	}
}
