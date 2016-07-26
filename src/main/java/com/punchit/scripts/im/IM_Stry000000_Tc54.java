package com.punchit.scripts.im;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.SPARCPortalPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class IM_Stry000000_Tc54 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc54",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String people,String abletowork,String desc,String CIA,String CIB){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application as user 1
			LoginPage loginPage = new LoginPage();
			MenuPage home = loginPage.loginAs(regUser, regPwd);
			SPARCPortalPage SPARCPage=home.clickSPARCPortal();			
			IncidentPage incPage=SPARCPage.clickCreateIncident()					
			.RaiseIncident(people, CIA, abletowork, desc);
			String incNumber=incPage.getIncidentNumber();
			incPage.isStateOpen().clickSave();			
			SPARCPage.clickSPARCHomeMenu()
			.clickOpen()			
			.searchIncident(incNumber)
			.clickFirstIncident()
			.verifyAssignmentGroupField("Contact Center");
			home.clickSPARCPortal()
			.clickCreateIncident()
			.RaiseIncident(people, CIB, abletowork, desc);
			String incNumber2=incPage.getIncidentNumber();
			incPage.clickSave();

			SPARCPage.clickSPARCHomeMenu()
			.clickLogout();

			snW.quitBrowser();			
			snW.launchApp(browserName, true);

			loginPage.loginAs(regUser, regPwd)
			.clickOpen()
			.searchIncident(incNumber2)
			.clickFirstIncident()
			.verifyAssignmentGroupFieldDoesNotExistBy("Contact Center");
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc54")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc54");
		return arrayObject;
	}
}
