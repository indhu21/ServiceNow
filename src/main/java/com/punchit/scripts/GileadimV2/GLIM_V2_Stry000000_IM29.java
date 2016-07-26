package com.punchit.scripts.GileadimV2;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.SPARCPortalPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM29 extends SuiteMethods {

	@Test(dataProvider = "GLIM_V2_Stry000000_IM29",groups="IncidentManagement")
	public void createIncident( String regUser, String regPwd,
								String assGrp, String moreDe){

		try {

				launchApp(browserName, true);
				
				SPARCPortalPage home=	
						new LoginPage()
						.loginAs(regUser, regPwd)
						.clickSPARCPortal();
				IncidentPage incident =	
					home.clickIneedSomething()
						.clickServiceRequests()
						.clicktandardITRequests()
						.clickCalendarRequest()
						.enterAssignmentGrp(assGrp)
						.enterMoreDetail(moreDe)
						.clickOrderNowButton()
						.clickRTMNumber();
			String taskNumber =		
				incident.getTaskNumber();
			
					home.clickMySPARC()
						.clickMyWork()
						.clickMyWorkGroupTab()
						.searchAndVerifyTask(taskNumber);
					home.clickSPARCHomeMenu()
						.clickLogout();

						
					status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLIM_V2_Stry000000_IM29")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM29");
		return arrayObject;
	}





}



