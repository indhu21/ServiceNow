package com.punchit.scripts.GileadimV2;

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

public class GLIM_V2_Stry000000_IM31 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_IM31",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
								String regUser2,String regPwd2,
								String issue,String desc){

		// Pre-requisities
		
		// Step 0: Launch the application
					


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
		
			try {
				launchApp(browserName, true);

SPARCPortalPage home = 
					new LoginSparcLCPage()
					.loginAsSparcportal1(regUser, regPwd);


IncidentPage incPage = 
					home.clickCreateIncidentForNegative()
								.RaiseIncident(number, issue, areYou, desc);

String incNumber1 = 
					incPage.getIncidentNumber();

						incPage.changeUrl()
						.clickLogout();

					MenuPage home1 = new LoginPage()
						.loginAs(regUser2, regPwd2);
					home1.searchIncident(incNumber1)
						.isFieldsAutomaticallyGenerated(priority);
						home1.clickLogout();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			finally {
				quitBrowser();
			}
			
	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM31")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM31");
		return arrayObject;
	}
}
