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

public class GLIM_V2_Stry000000_IM25 extends SuiteMethods{
	// Create Instance


	@Test(dataProvider = "GLIM_V2_Stry000000_IM25",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd ,String causingCI,			
			String configItem , String repCust, String asgGroup,
			String desc, String assTo, String causingCI1,
			String causingCI2, String causingCI3) {


		try {

				launchApp(browserName, true);

			MenuPage home = 
					new LoginSparcLCPage()
					.loginAs(regUser, regPwd);

			IncidentPage incident =
					home.clickCreateNewforFailure();
			String incNumber = 
					incident.getIncidentNumber();

			incident.createIncidentWithWorkInProcessAndOpenIncident(configItem, repCust, 
					asgGroup, desc, incNumber, assTo)	
					.enterCausingCIandClickCausingCIComponentSpyGlass(causingCI)
					.verifyCIComponentLookUpValues_NetworksCIcountsWithWarning();
			incident.enterCausingCIandClickCausingCIComponentSpyGlass(causingCI1)
					.verifyCIComponentStorageLookUpValuesCIcountsWithWarning();
			incident.enterCausingCIandClickCausingCIComponentSpyGlass(causingCI2)
					.verifyCIComponentStorageLookUpValuesCIcountsWithWarning();
			incident.enterCausingCIandClickCausingCIComponentSpyGlass(causingCI3)
					.verifyCIComponentStorageLookUpValuesCIcountsWithWarning();


			status="PASS";
		}
		finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLIM_V2_Stry000000_IM25")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM25");
		return arrayObject;
	}
}
