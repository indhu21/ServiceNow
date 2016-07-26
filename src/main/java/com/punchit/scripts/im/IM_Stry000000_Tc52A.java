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

public class IM_Stry000000_Tc52A extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc52A",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String people,String abletowork,String desc,String vip,String activeci,String nonactiveci){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application as user 1
			LoginPage loginPage = new LoginPage();
			MenuPage home = loginPage.loginAs(regUser, regPwd);
			SPARCPortalPage SPARCPage=home.clickSPARCPortal();
			
		    CreateIncidentPage Cip=SPARCPage.clickCreateIncident();
		    Cip.verifyAffectedUserValue(regUser)
		    //.isAmendValues("Amy")
		   
		    .enterAffectedUser(vip).Wait(2000);
		    Cip.verifyAffectedUserColour("red")
		    	.isVipFlagExistsNearAffectedUser()
		     .hoverViewIcon(vip)
		    .verifyValidAndInvalidIssue(activeci,nonactiveci)
		    .verifyNumberOfPeopleImpactedOptions()
		    .selectNoOfPeopleImpacted(people)
		    .verifyAbleToWorkOptions()
		    .selectAreYouAbleToWork(abletowork)
		    
		    .enterValueInShortDesc(desc)
		    .enterValueInMoreInfo(desc)
		       .clickSubmitNewIncident().isStateOpen();
		    
	
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc52A")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc52A");
		return arrayObject;
	}
}
