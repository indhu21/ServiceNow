package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.SPARCPortalPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM28 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_IM28",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd,String people,
								String issueWith ,String abletowork,String desc){

		try {

			launchApp(browserName, true);

			LoginPage loginPage = 
					new LoginPage();
			MenuPage home = 
					loginPage.loginAs(regUser, regPwd);
			SPARCPortalPage SPARCPage = 
					home.clickSPARCPortalForFailure();

			IncidentPage incPage =
			SPARCPage.clickCreateIncidentForNegative()
					.RaiseIncident(people, issueWith, abletowork, desc)
					.uploadFile("GLIM_V2_Stry000000_IM33");
            	 snW.Wait(3000);
			String incNumber = 
				incPage.getIncidentNumber();
			System.out.println(incNumber);
					incPage.clickSaveButton();

				SPARCPage.clickSPARCHomeMenuForNegative()
						 .searchIncidentFromMenu(incNumber)
						 .verifyAttachmentUploadedFromSPARCportal();

			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM28")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM28");
		return arrayObject;
	}
}
