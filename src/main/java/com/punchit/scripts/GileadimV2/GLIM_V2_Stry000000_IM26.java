package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.IncidentsListPage;
import pages.LoginPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLIM_V2_Stry000000_IM26 extends SuiteMethods{
	// Create Instance


	@Test(dataProvider = "GLIM_V2_Stry000000_IM26",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String configItem,
							String repCust, String asgGroup, String desc, 
							String assTo, String causingCI1, String addDesc) {

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
					.clickResolutionInformationForNegative()
					.enterAndChooseCausingCI(causingCI1)
					.enterAddCIDescriptionAndOk(addDesc)
					.clickCausingCIComponentSpyGlass1()
					.verifyCIComponentLookUpValues_NetworksCIcountsWithWarning();
		

			status="PASS";
		}
		finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLIM_V2_Stry000000_IM26")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM26");
		return arrayObject;
	}
}
