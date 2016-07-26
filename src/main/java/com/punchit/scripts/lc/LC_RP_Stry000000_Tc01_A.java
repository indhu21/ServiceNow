package com.punchit.scripts.lc;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginSparcLCPage;
import pages.MSSQLInstancesPage;
import pages.MenuPage;
import pages.SystemApplicationsPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class LC_RP_Stry000000_Tc01_A extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_RP_Stry000000_Tc01_A",groups="CMDB")
	public void createIncident(	String regUser, String regPwd, String filterType1, String filterCondition1, 
								String filterValue1, String filterType2, String filterCondition2, String filterValue2,
								String filterType3,String  filterCondition3, String filterValue3, String filterType4,
								String filterCondition4,String filterValue4,String filterType5,String filterCondition5,String filterValue5, 
								String gxp1, String sox1,String businessCriticality1,String gxp2, String sox2, String businessCriticality2,
								String filterType1forCI, String filterCondition1forCI, String filterValue1forCI, String filterValue2forCI,
								String filterValue3forCI, String ciName, String name, String gxpNo, String soxNo, String businessCriticality3Low,
								String gxpYes, String soxYes, String businessCriticality2Medium, 
								String gxpYes1, String soxNo1, String buss3Low){

		// Pre-requisities
		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = 
					new LoginSparcLCPage()
			.loginAs(regUser, regPwd);	

			// Step 2: Verify the Menus
			MSSQLInstancesPage MSSQL=
					home.expandCMDBMenu()
					.verifyExistanceOfCMDBOptions()
					.expandCMDBDatabaseInstances()
//					.clickDataBaseAll()
					.clickMSSQL()
					
//					.addFilterforThreeValues(filterType1, filterCondition1, filterValue1, filterType2, 
//							filterCondition2, filterValue2, filterType3, filterCondition3, filterValue3)
//					.clickFirstNamelinkforMSSQL()
					.searchandClickMSSQLName(ciName)
					.verifyGxpSoxBusinessValuesbyFirstSelectedValue(gxpNo, 
							soxNo, businessCriticality3Low);
//			String name =
//					MSSQL.getNameReadOnly();

			SystemApplicationsPage sA = 
					home.clickSystemApplications()
//					.addFilterforFiveValues(filterType1forCI, filterCondition1forCI, filterValue1forCI, filterType2,
//							filterCondition2, filterValue2forCI, filterType3, filterCondition3, filterValue3forCI, filterType4, filterCondition4, filterValue4, filterType5, filterCondition5, filterValue5)
//					.clickFirstNamelink()
					.searchandClickSyAppName(name)
					.verifySOXGxPBusinessCriticalityFileds(soxYes, gxpYes, businessCriticality2Medium)
					.clickAddCIRelationship()
					.selectDependsOn()
					.enterAvailableCIs(ciName)
					.selectFirstAvailableCIs()
					.verifyDependsOnRelationshipAppears();
				
				home.clickMSSQL()
					.searchandClickFirstnameforMSSQL(ciName)
					.VerifyGxpSoxBusinessValues(soxYes, gxpYes, businessCriticality2Medium);
				
				home.clickSystemApplications()
					.searchandClickFirstnameforSyApp(name)
					.sysAppPageValuesAndSave(soxNo1, gxpYes1, buss3Low);
		
			String CMDBNum = 
						sA.getCMDBTask();
				
				home.clickMyCMDBApprovals()
					.searchCMDBandSelectSP(CMDBNum)
					.enterChangeRequestId("123")
					.clickApprove();
				
				home.clickMSSQL()
					.searchandClickFirstnameforMSSQL(ciName)
					.VerifyGxpSoxBusinessValues(soxNo1, gxpYes1, buss3Low)
					.verifybaseline();
				
				home.clickSparcLCPageLogout();
					
				status = "PASS";

		} finally {
			// close the browser
			//snW.quitBrowser();
		}


	}

	@DataProvider(name = "LC_RP_Stry000000_Tc01_A")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_RP_Stry000000_Tc01_A");
		return arrayObject;
	}
}
