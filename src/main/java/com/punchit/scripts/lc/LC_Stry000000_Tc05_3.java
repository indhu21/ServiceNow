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

public class LC_Stry000000_Tc05_3 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_Stry000000_Tc05_3",groups="IncidentManagement")
	public void createIncident( String regUser, String regPwd, String filterType1, String filterCondition1, 
								String filterValue1, String filterType2, String filterCondition2, 
								String filterValue2, String filterType3, String filterCondition3, 
								String filterValue3, String filterType4, String filterCondition4, 
								String filterValue4, String filterType1forCI, String filterCondition1forCI,
								String filterValue1forCI, String filterValue2forCI, String filterType2forCI, 
								String filterCondition2forCI, String filterType3forCI, String filterCondition3forCI,
								String filterValue3forCI, String text, String changeRequestId, String operationalstatus,
								String ciName, String gxp){

		// Pre-requisities
		try {

				// Step 0: Launch the application
				snW.launchApp(browserName, true);

				// Step 1: Login to the application
				MenuPage home = 
						new LoginSparcLCPage()
						.loginAs(regUser, regPwd);
		

				NetworkSwitchesPage nW =
						home.clickNetworkSwitches()
//							.addFilterforFourValues(filterType1, filterCondition1, filterValue1, filterType2, filterCondition2, filterValue2,
//							  filterType3, filterCondition3, filterValue3, filterType4, filterCondition4, filterValue4)
//							.clickNetworkSwitches()
							.searchandClickNWName(ciName)
							.clickAddCIRelationship()
							.selectDependsOn()
							.addFilterforTwoValues(filterType1forCI, filterCondition1forCI, filterValue1forCI, filterValue2forCI,
									filterType2forCI, filterCondition2forCI, filterType3forCI, filterCondition3forCI)
							.selectFirstAvailableCIs()
							.verifyDependsOnRelationshipAppears()
							.clickAddCIRelationship()
							.selectUsedBy()
							.addFilterforOneValues(filterType1forCI, filterCondition1forCI, filterValue1forCI, filterType2forCI, filterCondition2forCI, filterType3forCI, filterCondition3forCI)
							.selectFirstAvailableCIs()
							.verifyUsedByRelationshipAppears()
							.selectGxpWithouReport(gxp)
							.clickSetActive()
							.getBuildConfirmation(text);
					
					String CMDBNum = 
							nW.getCMDBTaskNumberforSA();
							
						home.clickMyCMDBApprovals()
							.searchCMDBandSelectNW(CMDBNum)
							.enterChangeRequestId(changeRequestId)
							.clickApprove()
							.clickLinkName(ciName)
							.verifyOperationalstatus(operationalstatus);
						
						
							home.clickNetworkSwitches()
								.searchandClickNWName(ciName)
								.verifyAddCIRelationship();
							
							home.clickSparcLCPageLogout();
							
							status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_Stry000000_Tc05_3")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_Stry000000_Tc05_3");
		return arrayObject;
	}
}
