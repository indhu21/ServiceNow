package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM51_2 extends SuiteMethods {


	@Test(dataProvider = "GLIM_V2_Stry000000_IM51_2",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String configItem, String repCust, String desc,
								String Impact1, String Urgency1, String Priority1, String assignGroup, String asgGroup1, 
								String regUser1, String regPwd1, String p1Resp, String p1Reso, String p2Resp, String p2Reso,
								String p3Resp, String p3Reso, String p4Resp,String p4Reso, String inprog, String paused, 
								String compltd, String Impactsignificant,String UrgencyHigh1, String Priorityhigh2, 
								String Urgency2Medium, String Priorit3moderate, String Urgency3Low, String Priorit4Low, 
								String assTo, String causingCI, String causingCIComponent, String resolutionCode,
								String resolutionNotes, String cusComms){


		try {

			String[] slA={p1Resp, p1Reso};
			String[] stage={inprog, inprog};
			
			String[] slA1={p2Resp, p2Reso, p1Reso,p1Resp};
			String[] stage1={inprog, inprog, paused, paused};
			
			String[] slA2={p3Reso, p3Resp, p2Resp, p2Reso, p1Resp, p1Reso};
			String[] stage2={inprog, inprog, paused, paused, paused, paused};
			
			String[] slA3={p4Resp, p4Reso, p3Reso, p3Resp, p2Resp, p2Reso, p1Resp, p1Reso};
			String[] stage3={inprog, inprog, paused, paused,paused, paused, paused, paused};
			
			String[] slA4={p3Resp, p3Reso, p1Resp, p1Reso, p2Reso, p2Resp, p4Resp, p4Reso};
			String[] stage4={inprog, inprog, paused, paused,paused, paused,paused, paused};
			
			String[] slA5={p1Resp, p1Reso, p2Resp, p2Reso, p3Resp, p3Reso, p4Resp, p4Reso};
			String[] stage5={compltd, paused, compltd, paused, compltd, paused,compltd, paused};
			
			String[] slA6={p1Resp, p1Reso, p2Resp, p2Reso, p3Resp, p3Reso, p4Resp, p4Reso};
			String[] stage6={compltd, paused, compltd, paused, inprog, inprog,compltd, paused};
			
			
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
					.changeAssGrpAndverifySLA(asgGroup1, slA, stage)
					.changepriorityAndverifySLA(Impactsignificant, UrgencyHigh1, Priorityhigh2, slA1, stage1)
					.changepriorityAndverifySLA(Impactsignificant, Urgency2Medium, Priorit3moderate, slA2, stage2)
					.changepriorityAndverifySLA(Impactsignificant, Urgency3Low, Priorit4Low, slA3, stage3)
					.changepriorityAndverifySLA(Impactsignificant, Urgency2Medium, Priorit3moderate, slA4, stage4)
					.enterAssignedTo(assTo)
					.clickSave()
//					.clickWIPAndVerify()
					.clickResolveIncidentWithAlertAccept()
					.enterResolveFields(causingCI, "Connectivity", resolutionCode, resolutionNotes)
//					.clickSave()
					.isStateResolved()
					.verifySLATableItems(slA5, stage5)
					.clickReopen(cusComms)
					.verifySLATableItems(slA6, stage6);
				home.clickLogout();
				
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}




	@DataProvider(name = "GLIM_V2_Stry000000_IM51_2")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM51_2");
		return arrayObject;
	}
	
	
	
}
