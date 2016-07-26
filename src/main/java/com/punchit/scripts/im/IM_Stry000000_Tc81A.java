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

public class IM_Stry000000_Tc81A extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc81A",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String people,String abletowork,String desc, String errorMessage){
		
		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application as user 1
			new LoginPage().loginAs(regUser, regPwd)
			.clickSPARCPortal()
			.clickCreateIncident().verifyAttachmentButtonexists()
			.uploadFile("IM_Stry000000_Tc81A")
			.RemoveAttachment().
			verifyUploadErrorMessage("data", errorMessage);
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "IM_Stry000000_Tc81A")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc81A");
		return arrayObject;
	}
}
