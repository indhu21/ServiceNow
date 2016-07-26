package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM04 extends SuiteMethods {
	
	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_IM04",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String AssGroup1,
								String activeMember1 ,String activeMember2, String nonactiveMember ){
		
		// Pre-requisities
		
		try {

			snW.launchApp(browserName, true);

			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
		
			IncidentPage incident = 
					home.clickCreateNew();
			
			String incNumber = 
					incident.getIncidentNumber();
			System.out.println(incNumber);
			
			incident.EnterAssorupAndHoverGroupId(AssGroup1);
			incident.enterAssignedToForSuccess(activeMember1)
					.enterAssignedToForSuccess(activeMember2)
					.enterAssignedToForFailure(nonactiveMember);
					
			
			
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM04")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM04");
		return arrayObject;
	}
}
