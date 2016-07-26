package com.punchit.scripts.lc;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CmdbListPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.ServerWindowsPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class LC_Stry000000_Tc02_1 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_Stry000000_Tc02_1",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd,String name, String CIOwnerGroup,
								String systemManager, String operationalstatus){

		// Pre-requisities
		try {

			String appName = name+snW.getCurrentTime();

			snW.launchApp(browserName, true);

			MenuPage home = 
					new LoginSparcLCPage()
					.loginAs(regUser, regPwd);

				home.expandCMDBMenu()
					.verifyExistanceOfCMDBOptions()
					.enterFilter("Servers")
					.expandServers()
					.clearFilter();
		
				home.clickWinServerandClickNewButton()
					.verifyAllServerWindowsFields()
					.verifyAllReadOnlyFields()
					.verifyOperationalstatus(operationalstatus)
					.verifyAllMandatoryFields()
					.enterAllMandatoryFields(appName, CIOwnerGroup, systemManager)
					.clickSubmit()
					.searchNameandClickFirstNameWS(appName);
			
				home.clickSparcLCPageLogout();
					status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_Stry000000_Tc02_1")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_Stry000000_Tc02_1");
		return arrayObject;
	}
}
