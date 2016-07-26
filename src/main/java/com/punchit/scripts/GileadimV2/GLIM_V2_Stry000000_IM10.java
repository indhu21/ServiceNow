package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import javax.tools.DocumentationTool.Location;

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

public class GLIM_V2_Stry000000_IM10 extends SuiteMethods {

	@Test(dataProvider = "GLIM_V2_Stry000000_IM10",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String regUser2, String regPwd2,
								String configItem, String repCust, String asgGroup, String desc,
								String causingCI, String causingCIComponent, String resolutionCode, 
								String resolutionNotes, String location, String phoneNUm){

		try {

			snW.launchApp(browserName, true);

			MenuPage home =
					new LoginPage()
					.loginAs(regUser, regPwd);
		
			IncidentPage incident = 
					home.clickOpenandClickFirstIncident();
				incident.hoverCallerId();
			
			MyProfilePage MyProfile = 
					home.selectMyProfile();
				MyProfile.verifyExtensionNumberFieldExistsandReadOnly()
						.enterPreferedLocAndPhone(location, phoneNUm)
						.verifyPreferedLocAndPhone(location, phoneNUm);
//						 .BacktoMainFrame();
			SPARCPortalPage SPARCPortal  = home
						.clickMyProfileofSPARCPortal();
//			switchToFrame("Frame_Main");
			SPARCPortal.verifyExtensionNumberFieldExistsandReadOnly()
						.enterPreferedLocAndPhone(location, phoneNUm)
						.verifyPreferedLocAndPhone(location, phoneNUm);
						
						changeUrlForIM();
						home.clickLogout();
				
						status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM10")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM10");
		return arrayObject;
	}
}
