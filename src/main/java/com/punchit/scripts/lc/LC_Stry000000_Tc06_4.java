package com.punchit.scripts.lc;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CmdbListPage;
import pages.ListPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.UPSPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class LC_Stry000000_Tc06_4 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_Stry000000_Tc06_4",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd,String systemManager, String operationalstatus1,
								String changeRequestId1, String operationalstatus2, String filterType7, String filterCondition7, 
								String filterValue7, String filterType8, String filterCondition8, String filterValue8,
								String filterType9, String filterCondition9, String filterValue9, String filterType10,
								String filterCondition10, String filterValue10, String text, String ciName){ 

		// Pre-requisities
		try {


			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginSparcLCPage()
			.loginAs(regUser, regPwd);	

			UPSPage ups =
						home.enterFilter("Data Center")
							.clearFilter()
							.clickUPS()
//							.addFilterforFourValues(filterType7, filterCondition7, filterValue7, filterType9, filterCondition9, filterValue9,
//								filterType8, filterCondition8, filterValue8,filterType10, filterCondition10, filterValue10)
//							.clickFirstlinkofUPS(name)
							.searchandClickFirstUPSName(ciName)
							.verifyOperationalstatus(operationalstatus1)
							.clickSetRetire()
							.getBuildConfirmation(text);
			
					String CMDBNum =
							ups.getCMDBTask();
							home.clickSparcLCPageLogout();
							
								new LoginSparcLCPage()
								.loginAs(systemManager, regPwd)
								.clickMyCMDBApprovals()
								.searchCMDBandSelectUPS(CMDBNum)
								.enterChangeRequestId(changeRequestId1)
								.clickApprove()
								.clickLinkName(ciName)
								.verifyOperationalstatus(operationalstatus2);			
							home.clickSparcLCPageLogout();

								status = "PASS";

		} finally {
			// close the browser
			//snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_Stry000000_Tc06_4")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_Stry000000_Tc06_4");
		return arrayObject;
	}
}
