package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class GLIM_Stry000000_Tc37 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc37",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String causingCI, 
								String configItem , String repCust, String asgGroup,
								String desc, String assTo) {

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = 
					new LoginSparcLCPage()
					.loginAs(regUser, regPwd);
			
				IncidentPage incident =
						home.clickCreateNewforFailure();
			String incNumber = 
					incident.getIncidentNumber();
			incident.createIncidentWithWorkInProcessAndOpenIncident(configItem, repCust, asgGroup, desc, incNumber, assTo)	
					.enterCausingCIandClickCausingCIComponentSpyGlass(causingCI)
//					.verifyCIComponentStorageLookUpValues();
			        .verifyCIComponentStorageLookUpValuesCIcounts();
			
			// Step 2: click on WIP		
//				home.clickWIP()
//					.clickFunnel()
//					.clickANDCondition()
//					.addThirdFilterbyEnterValues(filterType, filterCondition, filterValue)
//					.clickRun()
//					.clickFirstIncident()
//					.clickResolutionInformation()
//					.enterAndChooseCausingCI(causingCI)
//					.clickCausingCIComponentSpyGlass()
//					.enterCausingCIandClickResolutionCodeSpyglass(causingCI)
//					.verifyCIComponentStorageLookUpValues();
//					
//					.clickCausingCISpyGlass()
//					.searchConfigItem("Class", "Storage Pool")
//					.clickFirstCIClass()
//					.enterAndChooseCausingCI(causingCI)
//					.clickCausingCIComponentSpyGlass()
//					.verifyCIComponentStorageLookUpValues();
					
				status="PASS";
			
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_Stry000000_Tc37")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc37");
		return arrayObject;
	}
}
