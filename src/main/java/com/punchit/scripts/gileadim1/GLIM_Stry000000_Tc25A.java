package com.punchit.scripts.gileadim1;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_Stry000000_Tc25A extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc01",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
								String configItem, String repCust, 
								String asgGroup, String desc,
								String asgTo, String type,
								String datetime, String reason, String causingCI,
								String causingCiComp, String resolutioncode,
								String resolutionNotes){
		
		try {

			
			snW.launchApp(browserName, true);

			
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);	

			IncidentPage incident =
					home.clickCreateNewforFailure()
						.selectIncidentWithOpenState(configItem, repCust, asgGroup, desc, asgTo);
//						.
						
			
						
						
						
						
						status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLIM_Stry000000_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc01");
		return arrayObject;
	}





}



