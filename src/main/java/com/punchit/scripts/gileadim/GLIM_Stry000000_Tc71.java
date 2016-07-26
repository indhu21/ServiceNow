package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_Stry000000_Tc71 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc71",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd, 
							   String configItem, String repCust,
			                   String asgGroup, String desc,
			                   String asgTo, String filter1,
			                   String filter2, String filter3){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
						MenuPage home = 
								new LoginPage()
								.loginAs(regUser, regPwd);
						String[] ele={"Cancelled - SR",
								      "Cancelled by User",
								      "Documentation updated",
								      "Emergency Fix",
								      "Software upgrade/install",
								      "Training suggested",
								      "Training/Knowledge provided",
								      "Unable to reproduce"};
					//Step 1A: Navigate to incident and click assign to me 
					IncidentPage incident =
							home.clickCreateNewforFailure();
//								.populateMandatoryFields(CI, reportCus, assGroup, shortDec)
//								.enterAssignedTo(assTo);
		  String incNumber=incident.getIncidentNumber();
//						incident.submitIncident()
						
						incident.createIncidentWithAssignedToAndOpenIncident(configItem, repCust, asgGroup, desc,incNumber,asgTo)
//								.searchIncident(incNumber)
//								.clickFirstIncident()
//								.clickResolveIncidentWithAlertAccept()
////								.alertAcceptforResolve()
//								.isExistResolutionInformation()
//								.isExistResolutionCodefield()
								.VerifyResolutionInformationAndCodefieldExists()
//								.enterAndChooseCausingCI(filter3)
//								.clickCausingCISpyGlass()
//								.selectFilter(filter1, filter2, filter3)
//								.clickFirstName()
//								.clickResolutioCodeSpyGlass()
								.enterCausingCIandClickResolutionCodeSpyglass(filter3)
//								.verifyResolutionCodeSpyGlass(ele);
								.verifyResolutionCodeSpyGlassCIcounts();
								status = "PASS";
				} finally {
					// close the browser
					snW.quitBrowser();
				}
		
			}


	@DataProvider(name = "GLIM_Stry000000_Tc71")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc71");
		return arrayObject;
	}
}
