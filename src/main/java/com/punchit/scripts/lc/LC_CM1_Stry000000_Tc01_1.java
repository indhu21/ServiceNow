package com.punchit.scripts.lc;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.CmdbListPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.OracleInstancesPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class LC_CM1_Stry000000_Tc01_1 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_CM1_Stry000000_Tc01_1",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String name,
							   String CIOwnerGroup, String SystemManager,
							   String operationalstatus1, String text,
							   String environment){

		// Pre-requisities
		try {

			String appName = name+snW.getCurrentTime();

			// Step 0: Launch the application
				snW.launchApp(browserName, true);

			// Step 1: Login to the application
				MenuPage home = new 
						 LoginSparcLCPage()
						.loginAs(regUser, regPwd);	

			// Step 2: Verify the Menus
					home.expandCMDBMenu();
						//.expandCMDBDatabaseInstances();
			
			CmdbListPage cmbd =
					home.clickOracle();
			OracleInstancesPage oracle =
					cmbd.clickNewButtonforOracle()
						.verifyAllFieldsforOracleIns()
						.enterAllMandatoryFields(appName, CIOwnerGroup, SystemManager)
						.clickSubmit()
						.searchandClickFirstnameforOracle(appName)
						.isPageEditable(environment)
						.clickSetBuild()
						.getBuildConfirmation(text);
			String CMDBNum =
				  oracle.getCMDBTaskNumberforOracle();
					oracle.getTextFromScheduleChange(CMDBNum);
				
					home.clickSparcLCPageLogout();
					
					status = "PASS";

		} finally {
			// close the browser
			//snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_CM1_Stry000000_Tc01_1")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_CM1_Stry000000_Tc01_1");
		return arrayObject;
	}
}
