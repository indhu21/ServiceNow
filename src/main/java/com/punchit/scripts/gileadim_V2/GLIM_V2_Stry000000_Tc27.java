package com.punchit.scripts.gileadim_V2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import pages.IncidentsMapPage;

public class GLIM_V2_Stry000000_Tc27 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_Tc27",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: As an Incident user sign into backend and navigate Incident
			MenuPage home = new LoginPage().loginAs(regUser, regPwd).verifyOverviewDashBoard();
			
//			if (snW.selectMenuFromMainHeader("IncidentMenu", "INCMENU_OVERVIEW"))
//				Reporter.reportStep("The Overview  menu selected successfully","SUCCESS");
//			else
//				Reporter.reportStep("The Alert Console  menu could not be selected","FAILURE");
//			
//			snW.switchToFrame("Frame_Main");
//			
//			if(snW.IsElementPresentById("Incident_Overview_pagetitle_id"))
//				Reporter.reportStep("The Incident Overview dashboard is displayed successfully","SUCCESS");
//			else
//				Reporter.reportStep("The Incident Overview dashboard could not be displayed","FAILURE");
			
									
			//Step 2: Find and select Overview 
			home.expandIncidentMenu().clickIncidentsMapandVerifyMapExists();
			
//			.clickIncidentsMap()
//			
//			//Step 3: Find and select Critical Incidents Map
//		
//			.isMapPresent();
			
			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_Tc27")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_Tc27");
		return arrayObject;
	}
}
