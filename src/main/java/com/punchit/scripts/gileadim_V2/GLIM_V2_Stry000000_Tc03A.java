package com.punchit.scripts.gileadim_V2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.IncidentsListPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.MyProfilePage;
import pages.SPARCPortalPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_Tc03A extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_Tc03A",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);
		
			// Step 2: click on Open Incident
			IncidentPage incident = home.clickOpenandClickFirstIncident();
//					.clickOpen();
//			
//			//Step 3: Click on an Incident ticket 
//			IncidentPage incident = incidentsList.clickFirstIncident();
			
			//hover over information button  next to affected user
			incident.hoverCallerId();
			
			MyProfilePage MyProfile = home.selectMyProfile();

			// Step 2: Check that you are able to see the “Extension number” field  and field is unable to edit 
			MyProfile.verifyExtensionNumberFieldExistsandReadOnly()
//			.verifyExtensionNumberField().verifyExtensionNumberFieldReadOnly()
			.BacktoMainFrame();

			// Step 3A: Go to the Portal view
			SPARCPortalPage SPARCPortal  = home
					.clickMyProfileofSPARCPortal();
					
//					.clickSPARCPortal();
//
//			// Step 3B: click on the “My Profile” button
//			SPARCPortal.clickMyProfile();
			
			switchToFrame("Frame_Main");
			

			// Step 4: Check that you are able to see the “Extension number” field and field is unable to edit 
			SPARCPortal.verifyExtensionNumberFieldExistsandReadOnly();
//			.verifyExtensionNumberField()
//			.verifyExtensionNumberFieldReadOnly();

			
					
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_Tc03A")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_Tc03A");
		return arrayObject;
	}
}
