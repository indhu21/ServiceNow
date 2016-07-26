package com.punchit.scripts.im;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class IM_Stry000000_Tc70 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc70",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd, 
							   String CI, String reportCus,
			                   String assGroup, String shortDec,
			                   String assTo){

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
							home.clickCreateNew()
								.populateMandatoryFields(CI, reportCus, assGroup, shortDec)
								.enterAssignedTo(assTo);
		  String IncNum=incident.getIncidentNumber();
						incident.submitIncident()
								.searchIncident(IncNum)
								.clickFirstIncident()
								.clickResolveIncident()
								.alertAcceptforResolve()
								.isExistResolutionInformation()
								.isExistResolutionCodefield()
								.clickResolveIncident()
								.isAlertPresentforClickResolve();
				
								status = "PASS";
				} finally {
					// close the browser
					snW.quitBrowser();
				}
		
			}


	@DataProvider(name = "IM_Stry000000_Tc70")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc70");
		return arrayObject;
	}
}
