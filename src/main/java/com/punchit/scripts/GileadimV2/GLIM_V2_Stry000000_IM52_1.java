package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM52_1 extends SuiteMethods {


	@Test(dataProvider = "GLIM_V2_Stry000000_IM52_1",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String configItem, String repCust, String desc,
								String Impact1, String Urgency1, String Priority1, String assignGroup, String asgGroupGI, 
								String asgGrpShPt, String Asssubm, String regUser1, String regPwd1, String p1Resp, 
								String p1Reso, String p2Resp, String p2Reso, String inprog, String paused, 
								String compltd, String assTo, String Impactsignificant, String UrgencyHigh1, 
								String Priorityhigh2, String causingCI, String causingCIComponent, 
								String resolutionCode, String resolutionNotes, String cusComms){


		try {

			String[] slA={p1Resp, p1Reso};
			String[] stage={inprog, inprog};
			
			String[] slA1={p2Resp, p2Reso, p1Resp,p1Reso};
			String[] stage1={inprog, inprog, paused, paused};
			
			String[] slA21={p2Resp, p2Reso, p1Resp,p1Reso};
			String[] stage21={compltd, compltd, compltd, compltd};
			
			String[] slA2={p2Resp, p2Reso, p1Resp, p1Reso};
			String[] stage2={inprog, paused, inprog, paused};
			
			String[] slA3={p2Resp, p2Reso, p1Resp, p1Reso};
			String[] stage3={compltd, inprog, compltd, inprog};
			
			
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
					.changeAssGrpAndverifySLA(Asssubm, slA, stage)
					.changeAssGrpAndverifySLA(asgGroupGI, slA, stage)
					.changepriorityAndverifySLA(Impactsignificant, UrgencyHigh1, Priorityhigh2, slA1, stage1)
					.changeAssGrpAndverifySLA(asgGrpShPt, slA1, stage1)
					.enterAssignedTo(assTo)
					.clickSave()
					.verifySLATableItems(slA21, stage21)
					.clickWIPAndVerify()
					.clickResolveIncidentWithAlertAccept()
					.enterResolveFields(causingCI, causingCIComponent, resolutionCode, resolutionNotes)
					.clickSave()
					.isStateResolved()
					.verifySLATableItems(slA2, stage2)
					.clickReopen(cusComms)
					.verifySLATableItems(slA3, stage3);
				
				home.clickLogout();
				
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}




	@DataProvider(name = "GLIM_V2_Stry000000_IM52_1")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM52_1");
		return arrayObject;
	}
	
	
	
}
