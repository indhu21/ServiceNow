package com.punchit.scripts.lc;

import java.io.IOException;
import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import pages.IncidentPage;
import pages.IncidentsListPage;
import pages.ListPage;
import pages.LoginPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.SystemApplicationsPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class LC_Stry000000_Tc01_1 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_Stry000000_Tc01_1",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String operationalstatus, 
								String name, String CIOwnerGroup, String SystemManager){

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
					.verifyExistanceOfCMDBOptions()
					.clickSystemApplicationsandClickNewButton()
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
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_Stry000000_Tc01_1")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_Stry000000_Tc01_1");
		return arrayObject;
	}
}
