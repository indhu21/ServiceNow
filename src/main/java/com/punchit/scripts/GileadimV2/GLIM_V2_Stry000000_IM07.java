package com.punchit.scripts.GileadimV2;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class GLIM_V2_Stry000000_IM07 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_IM07",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String configItem ,String repCust ,
								String asgGroup,String desc,String asgTo, String addCIdesc, String resCode,
								String CausingCIComponent, String resolutionNotes,
								String configManager, String confItem) {

		// Pre-requisities

		try {

			snW.launchApp(browserName, true);

			MenuPage home = 
					new LoginSparcLCPage()
					.loginAs(regUser, regPwd);

			IncidentPage incident = 
					home.clickCreateNew();

			String incNumber = 
					incident.getIncidentNumber();
			System.out.println(incNumber);

						incident.createIncidentAndOpenIncident(configItem, repCust, asgGroup, addCIdesc, incNumber, asgTo)
								.clickConfigurationItemSpyGlass()
								.clickMissingCI()
								.clickCancelAddCIDesc()
								.clickConfigurationItemSpyGlass()
								.clickMissingCI()
								.enterAddCIDescriptionAndOk(addCIdesc)
								.clickSave();

			String missingCausingCNumber =
						incident.getmissingCausingCINumber();
							
							home.clickSparcLCPageLogout();
								new LoginSparcLCPage()
								.loginAs(configManager , regPwd)
								.expandCMDBControl()
								.clickMissingCITasks()
								.searchAndOpenTask(missingCausingCNumber)
								.enterCMDBConfItemAndClose(confItem);

							home.clickSparcLCPageLogout();
								new LoginSparcLCPage()
								.loginAs(regUser, regPwd)
								.clickAll()			
								.searchAndOpenIncident(incNumber)
								.verifyReadOnlyConfigItemValue(confItem);

								status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM07")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM07");
		return arrayObject;
	}
}
