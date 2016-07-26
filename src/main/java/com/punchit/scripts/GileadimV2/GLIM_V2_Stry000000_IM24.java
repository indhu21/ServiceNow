package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;

public class GLIM_V2_Stry000000_IM24 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_IM24",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String causingCI ,String CI, String Reportingcustomer,
			String Group, String Description, String Assignto, String causingCI1 ,String CI1,String Reportingcustomer1,
			String Group1,String Description1, String Assignto1, String CI2,String Reportingcustomer2,
			String Group2,String Description2, String Assignto2, String filter1, String filter2, String filter3, String causingCI2, 			
			String configItem , String repCust, String asgGroup,
			String desc, String assTo, String causingCI3) {


		try {

			snW.launchApp(browserName, true);

			MenuPage home =
					new LoginPage()
					.loginAs(regUser, regPwd);

			IncidentPage incident = 
					home.clickCreateNewforFailure();

			String incNumber = 
					incident.getIncidentNumber();

			System.out.println(incNumber);

			incident.createIncidentWithWorkInProcessAndOpenIncident(CI, Reportingcustomer, Group, Description, incNumber, regUser)
					.enterCausingCIandClickCausingCIComponentSpyGlass(causingCI)
					.verifyCIComponentBusinessServiceLookUpCIcountsWithWarning();
			incident.enterCausingCIandClickCausingCIComponentSpyGlass(causingCI1)
					.verifyCIComponentApplicationLookUpValuesCIcountsWithWarning();
			incident.enterCausingCIandClickCausingCIComponentSpyGlass(causingCI3)
					.verifyCausingCIComponentFieldLookUpValuesForServerCIcountsWithWarning();
			incident.enterCausingCIandClickCausingCIComponentSpyGlass(causingCI2)
					.verifyCausingCIComponentFieldLookUpValuesForDatabaseCIcountsWithWarning();

				status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM24")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM24");
		return arrayObject;
	}
}
