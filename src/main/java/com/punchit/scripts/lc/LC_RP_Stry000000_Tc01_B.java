package com.punchit.scripts.lc;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LinuxPage;
import pages.ListPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.OracleInstancesPage;
import pages.SystemApplicationsPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class LC_RP_Stry000000_Tc01_B extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_RP_Stry000000_Tc01_B", groups="CMDB")
	public void createIncident(	String regUser, String regPwd, String filterType1, String filterCondition1, 
								String filterValue1, String filterType2, String filterCondition2, String filterValue2,
								String filterType3,String  filterCondition3, String filterValue3, String filterType4, 
								String  filterCondition4, String filterValue4, String filterType5, String  filterCondition5, 
								String filterValue5, String gxp, String filterType6, String filterCondition6, String filterValue6,
								String filterType7, String filterCondition7, String filterValue7, String filterType8, 
								String  filterCondition8, String filterValue8, String filterType9, String  filterCondition9,
								String filterValue9, String filterType10, String  filterCondition10, String filterValue10,
								String gxp1, String gxp2, String filter1, String filter2, String ciName1, String ciName2, 
								String gxpNo, String gxpYes){

		// Pre-requisities
		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = 
				new LoginSparcLCPage()
					.loginAs(regUser, regPwd);	

			// Step 2: Verify the Menus
			LinuxPage linux=
				home.expandCMDBMenu()
					.verifyExistanceOfCMDBOptions()
					.expandServers()
					.clickLinux()
//					.addFilterforFiveValuesWithEnteringValues(filterType1, filterCondition1, filterValue1, filterType2, filterCondition2, filterValue2,
//											filterType3, filterCondition3, filterValue3, filterType4, filterCondition4, filterValue4,
//											filterType5, filterCondition5, filterValue5)
//					.clickFirstNamelinkforLinux()
					.searchandClickLinuxName(ciName1)
					.VerifyGxpValue1(gxpNo);
		
//			String name = 
//					linux.getName();
//			name=name.trim();
//			System.out.println(name);
				SystemApplicationsPage sA = 
				home.clickSystemApplications()
//					.addFilterforFiveValues(filterType6, filterCondition6, filterValue6, filterType7, filterCondition7, filterValue7,
//											filterType8, filterCondition8, filterValue8, filterType9, filterCondition9, filterValue9, 
//											filterType10, filterCondition10, filterValue10)
//					.clickFirstNamelink()
					.searchandClickSyAppName(ciName2)
					.VerifyGxpValue(gxpYes);
			
//			String name1 = 
//						sA.getName();
						sA.clickAddCIRelationship()
						  .selectDependsOn()
						  .enterAvailableCIs(ciName1)
						  .selectFirstAvailableCIs()
						  .verifyDependsOnRelationshipAppears();

						home.clickLinux()
							.deleteFilters()
							.addFirstFilterByEnterValues(filter1, filter2, ciName1)
							.clickRun()
							.clickFirstNamelinkforLinux()
							.VerifyGxpValue(gxpYes);
						
						home.clickSystemApplications()
							.deleteFilters()
							.addFirstFilterByEnterValues(filter1, filter2, ciName2)
							.clickRun()
							.clickFirstNamelink()
							.selectGxP(gxpNo)
							.clickSave();
						
					String CMDBNum = 
								sA.getCMDBTask();
						
						home.clickMyCMDBApprovals()
							.searchCMDBandSelectforSysApp(CMDBNum)
							.enterChangeRequestId("123")
							.clickApprove();

						home.clickLinux()
							.deleteFilters()
							.addFirstFilterByEnterValues(filter1, filter2, ciName1)
							.clickRun()
							.clickFirstNamelinkforLinux()
							.VerifyGxpValue(gxpNo)
//							.verifyDependsOnRelationshipAppears(ciName2)
							.verifybaseline();
						home.clickSparcLCPageLogout();
							
						status = "PASS";

		} finally {
			// close the browser
			//snW.quitBrowser();
		}
	}

	@DataProvider(name = "LC_RP_Stry000000_Tc01_B")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_RP_Stry000000_Tc01_B");
		return arrayObject;
	}
}
