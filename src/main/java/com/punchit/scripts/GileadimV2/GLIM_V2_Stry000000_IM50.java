package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM50 extends SuiteMethods {

	@Test(dataProvider = "GLIM_V2_Stry000000_IM50",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String configItem,
								String repCust, String asgGroup, String desc,
								String workNotes, String CustomerComms, String causingCI, 
								String causingCIComponent, String resolutionCode, 
								String resolutionNotes, String statestatus){

		try {

				launchApp(browserName, true);
			
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
			
			IncidentPage incident = 
					home.clickCreateNewforFailure();
			String masterIncident = 
					incident.getIncidentNumber();
					incident.createIncidentAndOpen(configItem, repCust, asgGroup, desc, masterIncident);
						home.clickCreateNewforFailure();
				String childIncident = 
						incident.getIncidentNumber();
					incident.createIncidentAndOpen(configItem, repCust, asgGroup, desc, childIncident)
							.craeteMasterIncident(masterIncident, childIncident)
							.clickSaveButton();
						home.clickOpenWithoutReport()
							.clickCreatedMasterIncident(masterIncident)
							.clickNotes()
							.enterWorkNotes(workNotes)
							.enterCustomerComms(CustomerComms)
							.clickSaveButton()
							.enterAssigned(regUser)
							.clickWIP()
							.clickResolutionInformationForNegative()
							.enterAllFields(causingCI, causingCIComponent, resolutionCode, resolutionNotes)
							.clickResolveIncident();
							
						home.clickOpenWithoutReport()
							.clickCreatedChildIncident(childIncident)
							.verifystatestatus(statestatus);
/*							.clickNotes()
							.verifyLatestWorkNotesForChild(workNotes);
					
*/					home.clickLogout();
						
					status = "PASS";
					
		} finally {
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM50")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM50");
		return arrayObject;
	}
}
