package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.SPARCPortalPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_Stry000000_Tc52B extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc52B",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String people,String issueWith ,String abletowork,String desc){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application as user 1
			LoginPage loginPage = new LoginPage();
			MenuPage home = loginPage.loginAs(regUser, regPwd);
//			SPARCPortalPage SPARCPage=home.clickSPARCPortal();		
			SPARCPortalPage SPARCPage=home.clickSPARCPortalForFailure();

			IncidentPage incPage =
			SPARCPage.clickCreateIncidentForNegative()
					.RaiseIncident(people, issueWith, abletowork, desc)
					
//					.selectNoOfPeopleImpacted(people)
//					.enterValueInIssueWith(issueWith)
//					.selectAreYouAbleToWork(abletowork)
//					.enterValueInShortDesc(desc)					
					.uploadFile("GLIM_Stry000000_Tc52B");
//					.clickSubmitNewIncident();
            snW.Wait(3000);
			String incNumber=incPage.getIncidentNumber();
			System.out.println(incNumber);
			incPage.clickSaveButton();

			SPARCPage.clickSPARCHomeMenuForNegative()
//			.clickOpen()
//			.searchIncident(incNumber)
//			.clickFirstIncident()
			.searchIncidentFromMenu(incNumber)
			.verifyAttachmentUploadedFromSPARCportal();

			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_Stry000000_Tc52B")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc52B");
		return arrayObject;
	}
}
