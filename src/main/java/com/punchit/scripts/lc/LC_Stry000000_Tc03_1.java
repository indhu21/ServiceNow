package com.punchit.scripts.lc;

import java.io.IOException;
import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import pages.CmdbListPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.OracleInstancesPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class LC_Stry000000_Tc03_1 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_Stry000000_Tc03_1",groups="IncidentManagement")
	public void createIncident( String regUser, String regPwd,String name, String CIOwnerGroup,
								String SystemManager,String operationalstatus){


		
		// Pre-requisities
		try {

			String appName = name+snW.getCurrentTime();
			
			// Step 0: Launch the application
				snW.launchApp(browserName, true);

			// Step 1: Login to the application
				MenuPage home = new 
						 LoginSparcLCPage()
						.loginAs(regUser, regPwd);	

					home.expandCMDBMenu()
						.verifyExistanceOfCMDBOptions()
						.expandCMDBDatabaseInstances();

					home.clickOracleandClickNewButton()
						.verifyAllFieldsforOracleIns()
						.verifyAllReadOnlyFieldsforOracleIns()
						.verifyOperationalstatusReadOnly(operationalstatus)
						.verifyAllMandatoryFieldsforOracleIns()
						.enterAllMandatoryFields(appName, CIOwnerGroup, SystemManager)
						.clickSubmit()
						.searchandClickFirstnameforOracle(appName);
					
					home.clickSparcLCPageLogout();
					
					status = "PASS";

		} finally {
			// close the browser
			//snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_Stry000000_Tc03_1")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_Stry000000_Tc03_1");
		return arrayObject;
	}
}
