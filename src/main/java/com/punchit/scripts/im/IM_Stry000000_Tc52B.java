package com.punchit.scripts.im;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.CreateIncidentPage;
import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.SPARCPortalPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class IM_Stry000000_Tc52B extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc52B",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String people,String issueWith ,String abletowork,String desc){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application as user 1
			LoginPage loginPage = new LoginPage();
			MenuPage home = loginPage.loginAs(regUser, regPwd);
			SPARCPortalPage SPARCPage=home.clickSPARCPortal();			

			IncidentPage incPage=SPARCPage.clickCreateIncident()
					.selectNoOfPeopleImpacted(people)
					.enterValueInIssueWith(issueWith)
					.selectAreYouAbleToWork(abletowork)
					.enterValueInShortDesc(desc)					
					.uploadFile("IM_Stry000000_Tc52B")
					.clickSubmitNewIncident();

			String incNumber=incPage.getIncidentNumber();
			System.out.println(incNumber);
			incPage.clickSave();

			SPARCPage.clickSPARCHomeMenu()
			.clickOpen()
			.searchIncident(incNumber)
			.clickFirstIncident()
			.verifyAttachmentUploadedFromSPARCportal();

			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc52B")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc52B");
		return arrayObject;
	}
}
