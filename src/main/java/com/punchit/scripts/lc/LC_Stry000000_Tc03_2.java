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

public class LC_Stry000000_Tc03_2 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_Stry000000_Tc03_2",groups="IncidentManagement")
	public void createIncident( String regUser, String regPwd, String filterType1, String filterCondition1,
								String filterValue1, String filterType2, String filterCondition2,
								String filterValue2, String filterType3, String filterCondition3,
								String filterValue3, String filterType4, String filterCondition4,
								String filterValue4, String environment, String location, String text,
								String systemManager, String operationalstatus, String ciName){

		// Pre-requisities
		try {


			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new 
					LoginSparcLCPage()
					.loginAs(regUser, regPwd);	

			OracleInstancesPage oracle =
				home.clickOracle()
//				cmbd.addFilterforFourValues(filterType1,  filterCondition1, filterValue1, filterType2, filterCondition2, filterValue2, 
//						filterType3, filterCondition3, filterValue3, filterType4, filterCondition4, filterValue4)
//					.clickFirstNamelinkforOracle()
					.searchandClickOracleName(ciName)
					.selectOtherValues(environment, location)
					.clickSetBuild()
					.getBuildConfirmation(text);
			String CMDBNum =
					oracle.getCMDBTaskNumberforOracle();
			
			home.clickSparcLCPageLogout();

				new LoginSparcLCPage()
				.loginAs(systemManager, regPwd)
				.expandCMDBControl()
				.clickMyCMDBApprovals()
				.searchCMDBandSelectOracle(CMDBNum)
				.clickApprove()
				.clickLinkName(ciName)
				.verifyOperationalstatus(operationalstatus)
				.verifyalltext(environment, location);
				
			home.clickSparcLCPageLogout();	

			status = "PASS";

		} finally {
			// close the browser
			//snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_Stry000000_Tc03_2")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_Stry000000_Tc03_2");
		return arrayObject;
	}
}
