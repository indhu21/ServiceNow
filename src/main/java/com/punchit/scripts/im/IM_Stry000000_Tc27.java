package com.punchit.scripts.im;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.IncidentsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class IM_Stry000000_Tc27 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc27",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String masterIncident,String gTrackChange,String causingCI4,String causingCI3,String causingCI2,String causingCI1) {

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);

			// Step 2: click on WIP		
			 home.clickWIP()
			.clickFirstIncident()
			.clickProcess()
			.enterandChooseMasterIncident(masterIncident)
			.hoverMasterIncident()
			.enterGTrackChange(gTrackChange)
			.clickResolutionInformation()
			.enterAndChooseCausingCI(causingCI4)
			.clickSave()
			.clickProcess()
			.verifySOXSystemDisabled()
			.isSOXSystemChecked()
			.clickResolutionInformation()
			.enterAndChooseCausingCI(causingCI3)
			.clickSave()
			.clickProcess()
			.verifySOXSystemDisabled()
			.isSOXSystemChecked()
			.clickResolutionInformation()
			.enterAndChooseCausingCI(causingCI2)
			.clickSave()
			.clickProcess()
			.verifyGxPSystemDisabled()
			.isGXpSystemChecked()
			.clickResolutionInformation()
			.enterAndChooseCausingCI(causingCI1)
			.clickSave()
			.clickProcess()
			.verifyGxPSystemDisabled()
			.isGXpSystemChecked();


			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc27")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc27");
		return arrayObject;
	}
}
