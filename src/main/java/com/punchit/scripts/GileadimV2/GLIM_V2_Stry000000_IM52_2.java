package com.punchit.scripts.GileadimV2;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM52_2 extends SuiteMethods {


	@Test(dataProvider = "GLIM_V2_Stry000000_IM52_2",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String configItem, String repCust, String desc,
								String Impact1, String Urgency1, String Priority1, String assignGroup, String asgGrpShPt, 
								String regUser1, String regPwd1, String p1Resp, String p1Reso, String inprog, String paused, 
								String compltd, String assTo, String type, String reason) throws ParseException{


		try {
			String datetime=increamentOneDay();

			String[] slA={p1Resp, p1Reso};
			String[] stage={inprog, inprog};
			
			String[] slA1={p1Resp, p1Reso};
			String[] stage1={compltd, paused};
			
			
			
			launchApp(browserName, true);

			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);

			IncidentPage incident =
				home.clickCreateNew();
				String incNumber = 
						incident.getIncidentNumber();
				
			incident.enterConfigurationItemForSuccess(configItem)
					.enterReportingCustomer(repCust)
					.enterShortDescriptionWithReport(desc)
					.clickPriority()
					.verifyPriorityWithAlert(Impact1, Urgency1, Priority1)
					.verifyAssignmentGroupField(assignGroup)
					.clickSave();
				home.clickLogout();
					new LoginPage()
					.loginAs(regUser1, regPwd1);
				
				home.clickAll()	
					.searchandClickIncident(incNumber)
					.changeAssGrpAndverifySLA(asgGrpShPt, slA, stage)
					.rightClickReload()
					.enterAssignedTo(assTo)
					.clickWIP()
					.clickOnHold()
					.clickHoldInformationTab()
					.enterAndSaveOnHoldInfo(type, datetime, reason)
					.verifySLATableItems(slA1, stage1);
				
				home.clickLogout();
				
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}




	@DataProvider(name = "GLIM_V2_Stry000000_IM52_2")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM52_2");
		return arrayObject;
	}
	
	
	
}
