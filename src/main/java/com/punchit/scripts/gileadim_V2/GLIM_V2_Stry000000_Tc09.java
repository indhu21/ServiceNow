package com.punchit.scripts.gileadim_V2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_Tc09 extends SuiteMethods {
	
	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_Tc09",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
							   String AssGroup1,
							   String activeMember1 ,String activeMember2, String nonactiveMember ){
		
		// Pre-requisities
		
		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);
		
			// Step 2: click on create new
			IncidentPage incident = home.clickCreateNew();
			
			// Take a note of the INC number.
			String incNumber = incident.getIncidentNumber();
			System.out.println(incNumber);
			
			// Step 2: Enter Assignment Group into the Assignment Group field
//			incident.enterAssignmentGroup(AssGroup1).Wait(2000);
//			
//			//  Step 3: Hover over Group
//			incident.hoverGroupId();
		
			
			incident.EnterAssorupAndHoverGroupId(AssGroup1);
			//  Step 4: search Active member and Non-active member  in Assigned to field
			incident.enterAssignedToForSuccess(activeMember1)
					.enterAssignedToForSuccess(activeMember2)
					.enterAssignedToForFailure(nonactiveMember);
			
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_Tc09")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_Tc09");
		return arrayObject;
	}
}
