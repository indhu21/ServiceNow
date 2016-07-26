package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM27 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_IM27",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String people,String abletowork,String desc, String errorMessage){
		
		try {

				launchApp(browserName, true);
			MenuPage home =	
				new LoginPage()
				.loginAs(regUser, regPwd);
			home.clickSPARCPortal()
				.verifyAttachmentButtonexistsinNewIncident()
				.uploadFileBelow50MB("Data50MB")
				.RemoveAttachment()
				.verifyUploadErrorMessage("data", errorMessage);
			home.clickLogout();
				
				status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLIM_V2_Stry000000_IM27")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM27");
		return arrayObject;
	}
}
