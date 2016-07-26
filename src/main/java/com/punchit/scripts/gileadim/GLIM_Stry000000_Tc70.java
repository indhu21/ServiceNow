package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_Stry000000_Tc70 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc70",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd, 
			 String configItem, String repCust,
             String asgGroup, String desc,
             String asgTo){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
						MenuPage home = 
								new LoginPage()
								.loginAs(regUser, regPwd);
						
					//Step 1A: Navigate to incident and click assign to me 
					IncidentPage incident =
							home.clickCreateNewforFailure();
//								.populateMandatoryFields(CI, reportCus, assGroup, shortDec)
//								.enterAssignedTo(assTo);
					 String incNumber=incident.getIncidentNumber();
					
					incident.createIncidentWithAssignedToAndOpenIncident(configItem, repCust, asgGroup, desc,incNumber,asgTo)
					.VerifyResolutionInformationAndCodefieldExists()
//		 
//						incident.submitIncident()
//								.searchIncident(IncNum)
//								.clickFirstIncident()
//								.clickResolveIncidentWithAlertAccept()
//								.alertAcceptforResolve()
//								.isExistResolutionInformation()
//								.isExistResolutionCodefield()
//								.clickResolveIncident()
								.isAlertPresentforClickResolve();
				
								status = "PASS";
				} finally {
					// close the browser
					snW.quitBrowser();
				}
		
			}


	@DataProvider(name = "GLIM_Stry000000_Tc70")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc70");
		return arrayObject;
	}
}
