package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM01 extends SuiteMethods {

		@Test(dataProvider = "GLIM_V2_Stry000000_IM01",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd,String configItem, String repCust, 
								String asgGroup, String desc,String aUser, String causingCI1, String causingCI2, 
								String causingCI3,String causingCI4, String filter1, String filter2,
								String configItem1, String repCust1, String asgGroup1, String desc1, 
								String aUser1,String causingCI5, String causingCI6, String filter3, String filter4) {

	
		try {

			launchApp(browserName, true);

			MenuPage home =
					new LoginPage()
					.loginAs(regUser, regPwd);

			IncidentPage incident = 
					home.clickCreateNewforFailure();

			String incNumber = 
					incident.getIncidentNumber();
			System.out.println(incNumber);

			incident.createIncidentAndOpenIncident(configItem, repCust, asgGroup, desc, incNumber, aUser)
					.clickResolveIncidentWithAlertAccept()	
					.clickResolutionInformationForNegative()
					.verifyCausingCIMandatory()
					.clickCausingCISpyGlass()
					.selectFilter(filter1,filter2, causingCI1)
					.verifyRecordsDisplayedWithWarning(causingCI1)
					.clickCausingCISpyGlass()
					.selectFilter(filter1,filter2, causingCI2)
					.verifyRecordsDisplayedWithWarning(causingCI2)
					.clickCausingCISpyGlass()
					.selectFilter(filter1,filter2, causingCI3)
					.verifyRecordsDisplayedWithWarning(causingCI3)
					.clickCausingCISpyGlass()
					.selectFilter(filter1,filter2, causingCI4)
					.verifyRecordsDisplayedWithWarning(causingCI4)
					.clickCausingCISpyGlass()
					.selectFilter(filter3,filter4, causingCI5)
					.verifyNORecordsDisplayedWithWarning(causingCI5)
					.clickCausingCISpyGlass()
					.selectFilter(filter3,filter4, causingCI6)
					.verifyNORecordsDisplayedWithWarning(causingCI6);
//					snW.clickById("Save_Id");
			
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM01");
		return arrayObject;
	}
}
