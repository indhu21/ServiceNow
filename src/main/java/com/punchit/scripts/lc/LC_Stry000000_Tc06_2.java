
package com.punchit.scripts.lc;

import java.io.IOException;
import java.util.Date;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.CmdbListPage;
import pages.IncidentPage;
import pages.ListPage;
import pages.LoginPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.PDUsPage;
import pages.UPSPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class LC_Stry000000_Tc06_2 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_Stry000000_Tc06_2",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String CIOwnerGroup, String SystemManager,
								String sNumber, String location, String ipAddress,String sysManager, String operationalstatus,			
								String filterType7, String filterCondition7, String filterValue7, String filterType8, 
								String filterCondition8, String filterValue8, String filterType9, String filterCondition9, 
								String filterValue9, String filterType10, String filterCondition10, String filterValue10,
								String text, String ciName){

		// Pre-requisities
		try {

			snW.launchApp(browserName, true);

			MenuPage home = 
					new LoginSparcLCPage()
					.loginAs(regUser, regPwd);	

//				home.expandCMDBMenu()
//					.verifyExistanceOfCMDBOptions()
//					.expandDataCenter();
//
			UPSPage ups =
					home.clickUPS()
//						.addFilterforFourValues(filterType7, filterCondition7, filterValue7, filterType9, filterCondition9, filterValue9,
//												filterType8, filterCondition8, filterValue8,filterType10, filterCondition10, filterValue10)
//						.clickFirstlinkofUPS(name)
						.searchandClickFirstUPSName(ciName)
					  	.enterSNumberLocIpAdd(sNumber, location, ipAddress)
					  	.clickSetBuild()
					  	.getBuildConfirmation(text);
			String CMDBNum = 
					ups.getCMDBTaskNumberforUPS();
				  	
					home.clickSparcLCPageLogout();

						new LoginSparcLCPage()
						.loginAs(sysManager, regPwd)
						.expandCMDBControl()
						.clickMyCMDBApprovals()
						.searchCMDBandSelectUPS(CMDBNum)
						.clickApprove()
						.clickLinkName(ciName)
						.verifyOperationalstatus(operationalstatus)
						.verifyalltext(sNumber,location,ipAddress);
					
					home.clickSparcLCPageLogout();	

					status = "PASS";

		} finally {

			//close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_Stry000000_Tc06_2")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_Stry000000_Tc06_2");
		return arrayObject;
	}
}
