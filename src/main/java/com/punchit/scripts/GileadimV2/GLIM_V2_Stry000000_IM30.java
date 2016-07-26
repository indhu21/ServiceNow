package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.CreateIncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.SPARCPortalPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;

public class GLIM_V2_Stry000000_IM30 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_IM30",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd,String people,
								String abletowork,String desc,String vip,String activeci,
								String nonactiveci){


		try {

			snW.launchApp(browserName, true);

			LoginPage loginPage =
					new LoginPage();
			MenuPage home = 
					loginPage.loginAs(regUser, regPwd);
			SPARCPortalPage SPARCPage = 
					home.clickSPARCPortal();
			
		     CreateIncidentPage Cip = 
		    		 SPARCPage.clickCreateIncident();
		     
		     String value = snW.getAttributeByXpath(
		    		 "CREATEINC_AffectedUserValue_Xpath", "data-original-title");
		     
		     String user=regUser.toUpperCase()+" ";
		     
		     if ((value).equalsIgnoreCase(user))
		    	 Reporter.reportStep("The User: " + regUser + ""
		    	 		+ " is automatically filled in Affected User as expected ","SUCCESS");
			 else
			 	 Reporter.reportStep("The User: " + regUser+ ""
			 	 		+ " is differ from the automatically filled in Affected User unexpectedly ", "FAILURE");		
			 
			
		     Cip.enterAffectedUser(vip).Wait(2000);
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
			    .clickSubmitNewIncident()
			    .isStateOpen();
		    
	
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM30")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM30");
		return arrayObject;
	}
}
