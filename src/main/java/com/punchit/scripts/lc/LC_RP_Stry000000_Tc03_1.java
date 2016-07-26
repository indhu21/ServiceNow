package com.punchit.scripts.lc;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.SystemApplicationsPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class LC_RP_Stry000000_Tc03_1 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_RP_Stry000000_Tc03_1",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String filterType1, String filterCondition1, 
								String filterValue1, String filterType2, String filterCondition2, String filterValue2,
								String filterType3, String filterCondition3, String filterValue3,
								String filterType4, String filterCondition4, String filterValue4,
								String filterType1forCI, String filterCondition1forCI, String filterValue1forCI,
								String filterValue2forCI, String filterValue3forCI, String filterValue4forCI, 
								String filterType5forCI, String filterCondition5forCI, String filterType6forCI,
								String filterCondition6forCI, String filterValue5forCI, String text, String filter, 
								String changeRequestId, String operationalstatus, String level, String ciName,
								String gxp, String sox, String businessCriticality){

		// Pre-requisities
		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = 
				new LoginSparcLCPage()
					.loginAs(regUser, regPwd);	


			// Step 2: Verify the Menus
				home.expandCMDBMenu()
					.verifyExistanceOfCMDBOptions();

	SystemApplicationsPage sa=
					home.clickSystemApplications()
//						.addFilterforFourValues(filterType1, filterCondition1, filterValue1, filterType2, filterCondition2, filterValue2,
//								filterType3, filterCondition3, filterValue3, filterType4, filterCondition4, filterValue4)
//						.clickFirstNamelink()
						.searchandClickSyAppName(ciName)
						.clickAddCIRelationship()
						.selectDependsOn()
						.addFilterForCAR2_3DependsOn(filterType1forCI, filterCondition1forCI, filterValue1forCI, filterValue2forCI, filterValue3forCI, 
								filterValue4forCI, filterType5forCI, filterCondition5forCI, filterType6forCI, filterCondition6forCI)
						.selectFirstAvailableCIs()
						.verifyDependsOnRelationshipAppears()
						.clickAddCIRelationship()
						.selectUsedBy()
						.deleteFilters()
						.addFilterForCAR2_3UsedBy(filterType1forCI, filterCondition1forCI, filterValue5forCI, filterType5forCI,
								filterCondition5forCI, filterType6forCI, filterCondition6forCI)
						.selectFirstAvailableCIs()
						.verifyUsedByRelationshipAppears()
						.clickBusinessFunctionsTab()
						.clickEditButton1()
						.verifyRelatedLinks()
						.clickTechnologyEnablersTab()
						.clickEditButton2()
						.verifyRelatedLinksforTec()
						.clickGroupsTab()
						.clickEditButton3()
						.verifyRelatedLinks()
						.changeLevel(level)
						.clickDepartmentsTab()
						.clickEditButton4()
						.enterFilter(filter)
						.verifyRelatedLinks()
						.enterSOXGxPBusinessCriticalityFileds(gxp, sox, businessCriticality)
//						.sysAppPageValues("No", "No", "Prod", "Foster City", "3 - Low")
						.clickSetActive()
						.getBuildConfirmation(text);
			String CMDBNum =
					sa.getCMDBTaskNumberforSA();
//			String CMDBName =
//					sa.getCMDBName();
				home.clickMyCMDBApprovals()
					.searchCMDBandSelectforSysApp(CMDBNum)
					.enterChangeRequestId(changeRequestId)
					.clickApprove()
					.clickLinkName(ciName)
					.verifyOperationalstatus(operationalstatus);
				
				home.clickSparcLCPageLogout();
				status = "PASS";

		} finally {
			// close the browser
			//snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_RP_Stry000000_Tc03_1")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_RP_Stry000000_Tc03_1");
		return arrayObject;
	}
}
