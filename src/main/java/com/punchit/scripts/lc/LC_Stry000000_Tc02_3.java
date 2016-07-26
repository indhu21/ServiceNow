package com.punchit.scripts.lc;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CmdbListPage;
import pages.ListPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.ServerWindowsPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class LC_Stry000000_Tc02_3 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_Stry000000_Tc02_3",groups="IncidentManagement")
	public void createIncident(	String systemManager, String regPwd, String sysManager, String operationalstatus, 								String changeRequestId, String filterType1, String filterCondition1,
								String filterValue1,String filterValue2,String filterValue3,String filterValue4,
								String filterType5, String filterCondition5, String filterType6, String filterCondition6,
								String filterValue5, String filterValue6, String filterType7, String filterCondition7, 
								String filterValue7, String filterType8, String filterCondition8, String filterValue8,
								String filterType9, String filterCondition9, String filterValue9, String filterType10, 
								String filterCondition10, String filterValue10, String availableCI, String text,
								String ciName, String gxp){

		// Pre-requisities
		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginSparcLCPage().loginAs(systemManager, regPwd);	

			ServerWindowsPage sW =
					home.clickWinserver()
//						.addFilterforFourValues(filterType7, filterCondition7, filterValue7, filterType9, filterCondition9, filterValue9,
//								filterType8, filterCondition8, filterValue8, filterType10, filterCondition10, filterValue10)
//						.clickFirstWindowServerNamelink()
						
						.searchandClickSWName(ciName)
						.clickAddCIRelationship().
						selectDependsOn().addFilterForDependsOn(filterType1, filterCondition1, filterValue1, filterValue2, filterValue3, filterValue4,
																filterType5, filterCondition5, filterType6, filterCondition6)
						.searchAvailableCI(availableCI)
						.selectFirstAvailableCIs()
						.verifyDependsOnRelationshipAppears()
						.clickAddCIRelationship()
						.selectUsedBy()
						.addFilterForUsedBy(filterType1, filterCondition1, filterValue5, filterType1, filterCondition1, 
											filterValue6, filterType5,filterCondition5, filterType6, filterCondition6)
						.selectFirstAvailableCIs()
						.verifyUsedByRelationshipAppears()
						.selectGXP(gxp)
						.clickSetActive()
						.getBuildConfirmation(text);

			String CMDBNum = 
							sW.getCMDBTask();
					  	home.clickMyCMDBApprovals()
//							.clickAll()
							.searchCMDBandSelectWS(CMDBNum)
							.enterChangeRequestId(changeRequestId)
							.clickApprove()
							.clickLinkName(ciName)
							.verifyOperationalstatus(operationalstatus);
						
					  	home.clickWinserver()
							.searchandClickSWName(ciName)
							.verifyAddCIRelationship();
					  	
					  	home.clickSparcLCPageLogout();

			

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_Stry000000_Tc02_3")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_Stry000000_Tc02_3");
		return arrayObject;
	}
}
