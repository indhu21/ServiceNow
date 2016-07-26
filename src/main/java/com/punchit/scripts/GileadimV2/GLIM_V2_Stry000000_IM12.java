package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM12 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_IM12",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String configItem, String repCust, 
								String asgGroup, String desc, String asgTo,String errorMessage,
								String regUser1, String regPwd1) {

		// Pre-requisities

		try {

			launchApp(browserName, true);
			attachFile(regUser, regPwd, configItem, repCust, asgGroup, desc, asgTo, errorMessage);
			attachFile(repCust, regPwd, configItem, regUser, asgGroup, desc, asgTo, errorMessage);
			
			
				status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}
	}
	
	public void attachFile(	String regUser, String regPwd, String configItem, String repCust, 
							String asgGroup, String desc, String asgTo,String errorMessage) {

		try {
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
			
			IncidentPage incident = 
					home.clickCreateNewforFailure();
			
			String incNumber = 
					incident.getIncidentNumber();
			
			incident.createIncidentWithAssignedToAndOpenIncident(configItem, repCust, asgGroup, desc,incNumber,asgTo)
					.verifyAddAttachmentAvailable()
					.uploadFile("GLIM_V2_Stry000000_IM13")			
					.RemoveAttachment()			
					.verifyUploadErrorMessage("Data",errorMessage);
			
				home.clickLogout();	
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@DataProvider(name = "GLIM_V2_Stry000000_IM12")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM12");
		return arrayObject;
	}
}
