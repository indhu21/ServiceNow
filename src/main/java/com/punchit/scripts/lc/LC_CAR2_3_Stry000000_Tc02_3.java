package com.punchit.scripts.lc;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.CmdbListPage;
import pages.IncidentsListPage;
import pages.ListPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.SystemApplicationsPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class LC_CAR2_3_Stry000000_Tc02_3 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_CAR2_3_Stry000000_Tc02_3",groups="IncidentManagement")
	public void createIncident(	String systemManager, String regPwd, String gxp, String sox, String environment,
								String location, String businessCriticality, String businessProcessOwner, 
								String operationalstatus,String changeRequestId1, String filterType1,
								String filterCondition1,String filterValue1, String filterValue2, String filterValue3,
								String filterValue4,String filterValue5, String filterType5, String filterCondition5,
								String filterType6,	String filterCondition6, String errorMessage, String qaOwner,
								String validationlead, String filterType7, String filterCondition7, String filterValue7,
								String filterType8, String filterCondition8, String filterValue8, String filterType9, 
								String filterCondition9, String filterValue9, String filterType10, String filterCondition10,
								String text, String ciName){

		// Pre-requisities
		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = 
					new LoginSparcLCPage()
					.loginAs(systemManager, regPwd);	
			
			SystemApplicationsPage sa= 
					home.expandCMDBMenu()
						.clickSystemApplications()
//						.addFilterForCAR2(filterType7, filterCondition7, filterValue7, filterType8, filterCondition8,
//							filterValue8, filterType9, filterCondition9, filterValue9)
//						.clickANDCondition()					
//						.addFourthFilterbyEnteringvalues(filterType10, filterCondition10, "")
//						.clickRun()
//						.clickFirstlink()
						.searchandClickSyAppName(ciName)
						.clickAddCIRelationship()
						.selectUsedBy()	
						.addFilterForCAR2_3UsedBy(filterType1,  filterCondition1,  filterValue5, filterType5, filterCondition5, 
								filterType6, filterCondition6)
						.selectFirstAvailableCIs()	
						.verifyUsedByRelationshipAppears()
						.clickAddCIRelationship()
						.selectDependsOn()
						.addFilterForCAR2_3DependsOn(filterType1, filterCondition1, filterValue1, filterValue2, filterValue3, filterValue4, filterType5, filterCondition5, filterType6, filterCondition6)
						.selectFirstAvailableCIs()
						.verifyDependsOnRelationshipAppears()
						.enterAllOtherFields(gxp, sox, environment, location, businessCriticality, businessProcessOwner)
//						.enterQAOwnerAndValidationLead("", "")
						.clickSetActiveandVerifyErrormsgAppears()
						.getAlertMessage(errorMessage)
						.enterAndChooseQAOwnerAndValidationLead(qaOwner, validationlead)
						.clickSetActive()
						.getBuildConfirmation(text);

			String CMDBNum = 
					sa.getCMDBTaskNumberforSA();
					home.clickMyCMDBApprovals()
//						clickAll().
						.searchCMDBandSelectforSysApp(CMDBNum)
						.enterChangeRequestId(changeRequestId1)
						.clickApprove()
						.clickLinkName(ciName)
						.verifyOperationalstatus(operationalstatus);
					
					home.clickSparcLCPageLogout();


					status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_CAR2_3_Stry000000_Tc02_3")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_CAR2_3_Stry000000_Tc02_3");
		return arrayObject;
	}
}
