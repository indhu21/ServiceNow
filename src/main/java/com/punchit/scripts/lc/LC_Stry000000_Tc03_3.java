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

public class LC_Stry000000_Tc03_3 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_Stry000000_Tc03_3",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String filterType1, String filterCondition1,
								String filterValue1, String filterType2, String filterCondition2,
								String filterValue2, String filterType3, String filterCondition3,
								String filterValue3, String filterType4, String filterCondition4,
								String filterValue4, String filterType1forCI, String filterCondition1forCI,
								String filterValue1forCI, String filterValue2forCI, String filterValue3forCI,
								String filterValue4forCI, String filterType5forCI, String filterCondition5forCI,
								String filterType6forCI,String filterCondition6forCI, String filterValue5forCI, 
								String filterValue6forCI, String text, String changeRequestId, 
								String operationalstatus, String ciName){

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
//						.addFilterforFourValues(filterType1,  filterCondition1, filterValue1, filterType2, filterCondition2, filterValue2, 
//							filterType3, filterCondition3, filterValue3, filterType4, filterCondition4, filterValue4)
//						.clickFirstNamelinkforOracle()
						.searchandClickOracleName(ciName)
						.clickAddCIRelationship()
						.selectDependsOn()
						.addFilterforFourValues(filterType1forCI, filterCondition1forCI, filterValue1forCI, filterValue2forCI, filterValue3forCI, filterValue4forCI,
								filterType5forCI, filterCondition5forCI, filterType6forCI, filterCondition6forCI)
						.selectFirstAvailableCIs()
						.verifyDependsOnRelationshipAppears()
						.clickAddCIRelationship()
						.selectUsedBy()
						.addFilterforTwoValues(filterType1forCI, filterCondition1forCI, filterValue1forCI, filterValue2forCI, 
								filterType5forCI, filterCondition5forCI, filterType6forCI, filterCondition6forCI)
						.selectFirstAvailableCIs()
						.verifyUsedByRelationshipAppears()
						.clickSetActive()
						.getBuildConfirmation(text);
		 String CMDBNum =
				 oracle.getCMDBTaskNumberforOracle();
					
		 			home.clickMyCMDBApprovals()
						.searchCMDBandSelectOracle(CMDBNum)
						.enterChangeRequestId(changeRequestId)
						.clickApprove()
						.clickLinkName(ciName)
						.verifyOperationalstatus(operationalstatus);
					
		 			
		 			home.clickOracle()
						.searchandClickOracleName(ciName)
						.verifyAddCIRelationship();
					
		 			home.clickSparcLCPageLogout();
								
					status = "PASS";

		} finally {
			// close the browser
			//snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_Stry000000_Tc03_3")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_Stry000000_Tc03_3");
		return arrayObject;
	}
}
