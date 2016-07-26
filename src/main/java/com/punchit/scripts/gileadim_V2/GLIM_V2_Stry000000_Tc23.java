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

public class GLIM_V2_Stry000000_Tc23 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_Tc23",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String people,String abletowork,String desc,String CIA,String CIB, String assignmentGroup){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application as user 1
			LoginSparcLCPage LoginSparcLCPage = new LoginSparcLCPage();
			
			MenuPage home = LoginSparcLCPage.loginAs(regUser, regPwd);
			
			SPARCPortalPage SPARCPage=
					home.clickSPARCPortal();			
			IncidentPage incPage=SPARCPage.clickCreateIncident()					
			.RaiseIncident(people, CIA, abletowork, desc);
			String incNumber=incPage.getIncidentNumber();
			incPage.isStateOpen().clickSave();			
			SPARCPage.clickSPARCHomeMenu()
			.clickOpen().searchandClickIncident(incNumber)			
//			.searchIncident(incNumber)
//			.clickFirstIncident()
			.verifyAssignmentGroupField(assignmentGroup);
			home.clickSPARCPortal()
			.clickCreateIncident()
			.RaiseIncident(people, CIB, abletowork, desc);
			String incNumber2=incPage.getIncidentNumber();
			incPage.clickSave();
			SPARCPage.clickSPARCHomeMenu()
			.clickSparcLCPageLogout();
			LoginSparcLCPage.loginAs(regUser, regPwd)
			.clickOpen().searchandClickIncident(incNumber2)
//			.searchIncident(incNumber2)
//			.clickFirstIncident()
			.verifyAssignmentGroupFieldDoesNotExistBy(assignmentGroup);
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_Tc23")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_Tc23");
		return arrayObject;
	}
}
