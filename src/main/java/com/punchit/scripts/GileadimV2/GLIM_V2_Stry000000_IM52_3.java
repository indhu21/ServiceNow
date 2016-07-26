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

public class GLIM_V2_Stry000000_IM52_3 extends SuiteMethods {


	@Test(dataProvider = "GLIM_V2_Stry000000_IM52_3",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String configItem, String repCust, String desc,
								String Impact1, String Urgency1, String Priority1, String Impact2, String Urgency2, 
								String Priority2, String Urgency3, String Priority3, String Urgency4, String Priority4,
								String assignGroup, String asgGrp1, String regUser1, String regPwd1, String p1Resp,
								String p1Reso, String p2Resp, String p2Reso, String p3Resp, String p3Reso, String p4Resp, 
								String p4Reso, String inprog, String paused) throws ParseException{


		try {
			
			String[] slA={p1Resp, p1Reso};
			String[] stage={inprog, inprog};
			
			String[] slA1={p2Resp, p2Reso, p1Resp, p1Reso};
			String[] stage1={inprog, inprog, paused, paused};
			
			String[] slA2={p3Resp, p3Reso, p2Resp, p2Reso, p1Resp, p1Reso};
			String[] stage2={inprog, inprog, paused, paused, paused, paused};
			
			String[] slA3={p4Resp, p4Reso, p3Resp, p3Reso, p2Resp, p2Reso, p1Resp, p1Reso};
			String[] stage3={inprog, inprog, paused, paused, paused, paused, paused, paused};
			
			String[] slA4={p3Resp, p3Reso, p4Resp, p4Reso, p2Resp, p2Reso, p1Resp, p1Reso};
			String[] stage4={inprog, inprog, paused, paused, paused, paused, paused, paused};
			
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
					.changeAssGrpAndverifySLA(asgGrp1, slA, stage)
					.changepriorityAndverifySLA(Impact2, Urgency2, Priority2, slA1, stage1)
					.changepriorityAndverifySLA(Impact2, Urgency3, Priority3, slA2, stage2)
					.changepriorityAndverifySLA(Impact2, Urgency4, Priority4, slA3, stage3)
					.changepriorityAndverifySLA(Impact2, Urgency3, Priority3, slA4, stage4);
				
				home.clickLogout();
					
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}




	@DataProvider(name = "GLIM_V2_Stry000000_IM52_3")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM52_3");
		return arrayObject;
	}
	
	
	
}
