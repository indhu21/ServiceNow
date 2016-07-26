package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class GLIM_V2_Stry000000_IM48 extends SuiteMethods {

	
	@Test(dataProvider = "GLIM_V2_Stry000000_IM48",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String configItem, String repCust, 
								String asgGroup, String desc, String aUser, String causingCI,
								String causingCIComponent, String resolutionCode, 
								String resolutionNotes, String repoptName, String CustomerComms) {

	
		try {

			String repName= repoptName+getCurrentTime();
			
					launchApp(browserName, true);
					MenuPage home = 
							new LoginPage()
							.loginAs(regUser, regPwd);
						
					IncidentPage incident = 
							home.clickCreateNewforFailure();

						String incNumber = 
								incident.getIncidentNumber();
						System.out.println(incNumber);
							
							incident.createIncidentWithWorkInProcessAndOpenIncident(configItem,
									repCust, asgGroup, desc, incNumber, aUser)
							.clickResolveIncidentWithAlertAccept()
							.enterAllFieldsWithResolveButton(causingCI, causingCIComponent, resolutionCode, resolutionNotes);
					
						home.expandReportsMenu()
							.clickViewRun()
							.clickCreateRecord()
							.clickDataAndFiltersforVersion2(incNumber)
							.saveReport(repName)
							.verifyRecordsData(incNumber);	
						home.clickAll()
							.searchAndOpenIncident(incNumber)
							.clickReopen(CustomerComms)
							.verifystatestatus("Open")
							.isNotResolutionInformationTab()
							.enterAssignedTo(regUser)
							.clickWIP()
							.verifyResolutionInformationNonManFields()
							.clearAllFieldsForResolveFields();
						home.expandReportsMenu()
							.clickViewRun()
							.clickCreateRecord()
							.clickDataAndFiltersforVersion2(incNumber)
							.verifyNoRecordsData();	
						
							status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM48")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM48");
		return arrayObject;
	}
}
