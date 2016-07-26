package com.punchit.scripts.gileadim_V2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.SPARCPortalPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_Tc22 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_Tc22",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
								String regUser2,String regPwd2,
								String issue,String desc){

		// Pre-requisities
		
		// Step 0: Launch the application
					snW.launchApp(browserName, true);


		try {

			createAndVerifyPriority(regUser, regPwd, regUser2, regPwd2, issue, desc, "Me",  "Yes", "4 - Low");
			createAndVerifyPriority(regUser, regPwd, regUser2, regPwd2, issue, desc, "My Group",  "Yes", "4 - Low");
			createAndVerifyPriority(regUser, regPwd, regUser2, regPwd2, issue, desc, "My Department",  "Yes", "3 - Moderate");
			createAndVerifyPriority(regUser, regPwd, regUser2, regPwd2, issue, desc, "My Location",  "Yes", "3 - Moderate");
			createAndVerifyPriority(regUser, regPwd, regUser2, regPwd2, issue, desc, "Me",  "No", "3 - Moderate");
			createAndVerifyPriority(regUser, regPwd, regUser2, regPwd2, issue, desc, "My Group",  "No", "3 - Moderate");
			createAndVerifyPriority(regUser, regPwd, regUser2, regPwd2, issue, desc, "My Department",  "No", "2 - High");
			createAndVerifyPriority(regUser, regPwd, regUser2, regPwd2, issue, desc, "My Location",  "No", "2 - High");
			
			
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	private void createAndVerifyPriority(	String regUser, String regPwd,
											String regUser2,String regPwd2,
											String issue,String desc,
											String number, String areYou, 
											String priority){
		

		// Step 1: Login to the application as user 1
//		MenuPage home = 
//				new LoginPage() 
//				.loginAs(regUser, regPwd);
		
		SPARCPortalPage home = 
				new LoginSparcLCPage()
				.loginAsSparcportal1(regUser, regPwd);


		// Step 2A: Go to the Portal view
//		SPARCPortalPage SPARCMenu = 
//				home.clickSPARCPortalForFailure();
		
		IncidentPage incPage = 
				home.clickCreateIncidentForNegative()
							.RaiseIncident(number, issue, areYou, desc);
		
		String incNumber1 = 
				incPage.getIncidentNumber();

		// Verify the priority and save
//						 incPage.verifyPriorityFieldInSparc(priority)
//								.clickSave();
//							incPage.verifyPriorityFieldInSparcAndSave(priority);
//							home.clickSPARCHomeMenuForNegative() 
//								.clickLogout();

					incPage.changeUrl()
					.clickLogout();
					
//					snW.quitBrowser();			`
//					snW.launchApp(browserName, true);

				MenuPage home1 = new LoginPage()
					.loginAs(regUser2, regPwd2);
				home1.searchIncident(incNumber1)
//					.clickSetPriority()
//					.verifyPriorityField(priority)
					.isFieldsAutomaticallyGenerated(priority);
			
					// Logout			 
					home1.clickLogout();
			
//					snW.quitBrowser();			
	}


	@DataProvider(name = "GLIM_V2_Stry000000_Tc22")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_Tc22");
		return arrayObject;
	}
}
