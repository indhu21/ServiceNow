package com.punchit.scripts.GileadimV2;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class GLIM_V2_Stry000000_IM14 extends SuiteMethods {

	@Test(dataProvider = "GLIM_V2_Stry000000_IM14",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd, String configItem, 
							   String repCust, String asgGroup, String desc, String aUser,
							   String causingCI, String resolutioncode, String causingCIComp,
							   String notes){

		// Pre-requisities

		try {

			snW.launchApp(browserName, true);

			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
			
			IncidentPage incident =
					home.clickCreateNewforFailure();
			
			String IncNum = 
					incident.getIncidentNumber();

			incident.createIncidentWithWorkInProcessAndOpenIncident(configItem, repCust, asgGroup, desc, IncNum, aUser)
					.isAlertPresentforClickResolveButton()
					.enterCausingCI(causingCI)
					.isAlertPresentforClickResolveButton()
					.enterAndChooseCausingCIComponent(causingCIComp)
					.isAlertPresentforClickResolveButton()
					.enterAndChooseResolutionCode(resolutioncode)
					.isAlertPresentforClickResolveButton()
					.enterResolutionNotes(notes)
					.clickResolveButton();
					
								status = "PASS";
				} finally {
					// close the browser
					snW.quitBrowser();
				}
		
			}


	@DataProvider(name = "GLIM_V2_Stry000000_IM14")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM14");
		return arrayObject;
	}
}
