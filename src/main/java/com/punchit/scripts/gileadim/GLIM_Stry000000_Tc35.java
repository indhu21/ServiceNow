package com.punchit.scripts.gileadim;

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

public class GLIM_Stry000000_Tc35 extends SuiteMethods{
	@Test(dataProvider = "GLIM_Stry000000_Tc35",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String causingCI, 			
			String configItem , String repCust, String asgGroup,
			String desc, String assTo){


		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);
			MenuPage home = 
					new LoginSparcLCPage()
					.loginAs(regUser, regPwd);
			

			IncidentPage incident =
					home.clickCreateNewforFailure();
		String incNumber = 
				incident.getIncidentNumber();
			
			incident.createIncidentWithWorkInProcessAndOpenIncident(configItem, repCust, asgGroup, desc, incNumber, assTo)	
			.enterCausingCIandClickCausingCIComponentSpyGlass(causingCI)

//			// Step 2: click on WIP		
//				home.clickWIP()
//					.clickFunnel()
//					.clickANDCondition()
//					.addThirdFilterbyEnterValues(filterType, filterCondition, filterValue)
//					.clickRun()
//					.clickFirstIncident()
//					.clickResolutionInformation()
////					.clickCausingCISpyGlass()
////					.selectFilter(filter1, filter2, filter3)
////					.clickFirstCIClass()
//					.enterAndChooseCausingCI(filter3)
//					.clickCausingCIComponentSpyGlass()
//					.verifyCIComponentDatabaseLookUpValues();
			        .verifyCausingCIComponentFieldLookUpValuesForDatabaseCIcounts();
				status="PASS";
		}
		finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLIM_Stry000000_Tc35")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc35");
		return arrayObject;
	}
}
