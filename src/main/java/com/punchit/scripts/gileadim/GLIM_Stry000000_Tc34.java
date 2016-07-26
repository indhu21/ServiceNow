package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.IncidentsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLIM_Stry000000_Tc34 extends SuiteMethods{
	
	@Test(dataProvider = "GLIM_Stry000000_Tc34",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd,String CI,String Reportingcustomer,
								String Group,String Description, String Assignto, String filter1,
								String filter2, String filter3) {

	
		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
			IncidentPage incident = 
					home.clickCreateNewforFailure();

			// Take a note of the INC number.
			String incNumber = 
					incident.getIncidentNumber();
			System.out.println(incNumber);
			
			
			incident.createIncidentWithWorkInProcessAndOpenIncident(CI, Reportingcustomer, Group, Description, incNumber, regUser)
					.enterCausingCIandClickCausingCIComponentSpyGlass(filter3)
//					.verifyCausingCIComponentFieldLookUpValuesForServer();
					.verifyCausingCIComponentFieldLookUpValuesForServerCIcounts();

//			// Step 8: Enter all Mandatory fields
//				incident.populateMandatoryFields(CI, Reportingcustomer, Group, Description)
//			          .enterAssignedTo(Assignto)
//			          .submitIncident()
//			          .searchIncident(incNumber)
//			          .clickFirstIncident()
//			          .clickResolveIncidentWithAlertAccept()
//			          .isExistResolutionInformation()
//			          .isExistResolutionCodefield()
//			          .enterAndChooseCausingCI(filter3)	
//			          .clickCausingCISpyGlass()
//			          .selectFilter(filter1, filter2, filter3)
//			          .clickFirstName()
//			          .clickCausingCIComponentSpyGlass()
//			          .verifyCausingCIComponentFieldLookUpValuesForServer();
			
						status="PASS";
		}
		finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLIM_Stry000000_Tc34")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc34");
		return arrayObject;
	}
}

