package com.punchit.scripts.lc;

import java.io.IOException;
import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import pages.CmdbListPage;
import pages.IncidentPage;
import pages.IncidentsListPage;
import pages.ListPage;
import pages.LoginPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.ServerWindowsPage;
import pages.StoragePoolsPage;
import pages.SystemApplicationsPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class LC_Stry000000_Tc04_3 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_Stry000000_Tc04_3",groups="CMDB")
	public void createIncident(	String systemManager, String regPwd,String operationalstatus, String changeRequestId,
								String filterType1, String filterCondition1,String filterValue1, String filterType5, 
								String filterCondition5, String filterType6,String filterCondition6, String filterType7,
								String filterCondition7, String filterValue7, String filterType8, String filterCondition8, 
								String filterValue8, String filterType9, String filterCondition9, String filterValue9,
								String filterType10, String filterCondition10, String filterValue10, String text, String ciName,
								String ciOwner, String ciPwd){

		// Pre-requisities
		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			
					
			MenuPage home =	new LoginSparcLCPage()
							.loginAs(systemManager, regPwd);
			
			StoragePoolsPage sP = 
					home.clickStoragePools()
//						.addFilterforFourValues(filterType7, filterCondition7, filterValue7,filterType9, filterCondition9, filterValue9,
//								filterType8, filterCondition8, filterValue8,filterType10, filterCondition10, filterValue10)
//						.clickFirstStoragePoolsNamelink()
						.searchandClickPoolsName(ciName)
						.clickAddCIRelationship()
						.selectDependsOn()
						.addFilterforOneValues(filterType1,filterCondition1,filterValue1,filterType5,filterCondition5,filterType6,filterCondition6)			
						.selectFirstAvailableCIs()
						.verifyDependsOnRelationshipAppears()
						.clickAddCIRelationship()
						.selectUsedBy()
						.addFilterforOneValues(filterType1,filterCondition1,filterValue1,filterType5,filterCondition5,filterType6,filterCondition6)
						.selectFirstAvailableCIs()
						.verifyUsedByRelationshipAppears()
						.clickSetActive()
						.getBuildConfirmation(text);
			
			String CMDBNum = 
					sP.getCMDBTask();
			
					home.clickMyCMDBApprovals()
						.searchCMDBandSelectSP(CMDBNum)
						.enterChangeRequestId(changeRequestId)
						.clickApprove()
						.clickLinkName(ciName)
						.verifyOperationalstatus(operationalstatus);
					
					home.clickStoragePools()
						.searchandClickPoolsName(ciName)
						.verifyAddCIRelationship();
					
					home.clickSparcLCPageLogout();

					
						
						
					status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_Stry000000_Tc04_3")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_Stry000000_Tc04_3");
		return arrayObject;
	}
}
