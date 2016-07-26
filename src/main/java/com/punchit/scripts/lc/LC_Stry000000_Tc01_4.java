package com.punchit.scripts.lc;

import java.io.IOException;
import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import pages.IncidentPage;
import pages.IncidentsListPage;
import pages.LoginPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.SystemApplicationsPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class LC_Stry000000_Tc01_4 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_Stry000000_Tc01_4",groups="IncidentManagement")
	public void createIncident( String regUser, String regPwd, String filterType1,String filterCondition1,
								String filterValue1,String filterType2,String filterCondition2,String filterValue2,
								String filterType3,String filterCondition3,String filterValue3, String filterType4,
								String filterCondition4,String filterValue4,String operationalstatus,
								String text, String systemManager, String changeRequestId, String operationalstatus1, String ciName){

		// Pre-requisities
		try {


			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = 
				new LoginSparcLCPage()
					.loginAs(regUser, regPwd);	

			// Step 2: Verify the Menus
				SystemApplicationsPage sa=
				home.clickSystemApplications()
//					.addFilterforFourValues(filterType1,  filterCondition1, filterValue1, filterType2, filterCondition2, filterValue2, 
//							filterType3, filterCondition3, filterValue3, filterType4, filterCondition4, filterValue4)
//					.clickFirstNamelink()
					.searchandClickSyAppName(ciName)
					.verifyOperationalstatus(operationalstatus)
					.clickSetRetire()
					.getBuildConfirmation(text);
				String CMDBNum = sa.getCMDBTaskNumberforSA();
				String appName= sa.getCMDBName();
					home.clickSparcLCPageLogout();
		
					new LoginSparcLCPage()
					.loginAs(systemManager, regPwd)
					.clickMyCMDBApprovals()
					.searchCMDBandSelectforSysApp(CMDBNum)
					.enterChangeRequestId(changeRequestId)
					.clickApprove()
					.clickLinkName(appName)
					.verifyOperationalstatus(operationalstatus1);
					home.clickSparcLCPageLogout();

					status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_Stry000000_Tc01_4")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_Stry000000_Tc01_4");
		return arrayObject;
	}
}
