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

public class LC_Stry000000_Tc04_2 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_Stry000000_Tc04_2",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String systemManager, String gxp, String environment, 
								String location, String sysManager,String CIOwnerGroup, String operationalstatus, String filterType7,
								String filterCondition7, String filterValue7, String filterType8, String filterCondition8, 
								String filterValue8, String filterType9, String filterCondition9, String filterValue9,
								String filterType10, String filterCondition10, String filterValue10, String text, String ciName){

		// Pre-requisities
		try {


			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home =
					new LoginSparcLCPage()
					.loginAs(regUser, regPwd);
			
			StoragePoolsPage sP = 
						home.clickStoragePools()
//							.addFilterforFourValues(filterType7, filterCondition7, filterValue7,filterType9, filterCondition9, filterValue9,
//									filterType8, filterCondition8, filterValue8,filterType10, filterCondition10, filterValue10)
//							.clickFirstStoragePoolsNamelink()
							.searchandClickPoolsName(ciName)
							.EnterGxpEnvLoc(gxp, environment, location)
							.clickSetBuild()
							.getBuildConfirmation(text);
				
					String CMDBNum = 
							sP.getCMDBTaskNumber();

						home.clickSparcLCPageLogout();
			
							new LoginSparcLCPage()
							.loginAs(systemManager, regPwd)
							.expandCMDBControl()
							.clickMyCMDBApprovals()
							.searchCMDBandSelectSP(CMDBNum)
							.clickApprove()
							.clickLinkName(ciName)
							.verifyOperationalstatus(operationalstatus)
							.verifyEnteredFileds(gxp,location,environment);
							
						home.clickSparcLCPageLogout();	
							status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	

	@DataProvider(name = "LC_Stry000000_Tc04_2")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_Stry000000_Tc04_2");
		return arrayObject;
	}
}
