package com.punchit.scripts.gileadim;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class GLIM_Stry000000_Tc44A extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc44A",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd, String CI, String reportCus,
								String assGroup, String shortDec, String assTo, String causingCI,
								String causingCIComponent, String resolutionCode ,String resolutionNotes,
								String statestatus, String sdUser, String gtest_AssigGroup2_Activemember1,
								String gtest_AssigGroup1_Activemember2){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = 
					new LoginPage()
			.loginAs(regUser, regPwd);
			
			IncidentPage incident = 
					home.clickCreateNewforFailure();
					
			String incNumber = 
					incident.getIncidentNumber();
			
			incident.createIncidentWithWorkInProcesswithourReport(CI, reportCus, assGroup, shortDec, incNumber, assTo)
					.clickResolutionInformationForNegative()
					.enterAllFieldsForResolveTicket(causingCI, causingCIComponent, resolutionCode, resolutionNotes, regUser)
					
					.isReopenButtonDisplayed(regUser);
			home.clickLogout();

			
			new LoginPage()
			.loginAs(gtest_AssigGroup2_Activemember1, regPwd)
			.clickAllWithoutReport()			
			.searchAndOpenIncident(incNumber)
			.isReopenIncidentDisplayedforUnassignedgroup(gtest_AssigGroup2_Activemember1);
			home.clickLogout();
			
			new LoginPage()
			.loginAs(sdUser, regPwd)
			.clickAllWithoutReport()			
			.searchAndOpenIncident(incNumber)
			.isReopenButtonDisplayed(sdUser);
			
		home.clickLogout();

			new LoginPage()
			.loginAs(gtest_AssigGroup1_Activemember2, regPwd)
			.clickAllWithoutReport()			
			.searchAndOpenIncident(incNumber)
			.isReopenButtonDisplayed(gtest_AssigGroup1_Activemember2);
			
			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_Stry000000_Tc44A")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc44A");
		return arrayObject;
	}
}
