package com.punchit.scripts.GileadimV2;

import java.io.IOException;
import java.util.Arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM51_1 extends SuiteMethods {


	@Test(dataProvider = "GLIM_V2_Stry000000_IM51_1",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String configItem, String repCust, String desc,
								String Impact1, String Urgency1, String Priority1, String assignGroup, String asgGroup1, 
								String workNotes, String regUser1, String regPwd1, String dbAssGroup, String erpSecu,
								String hyperionL1, String p1Resp, String p1Reso, String p2Resp, String p2Reso,
								String p2BResp, String p2BReso, String p3Resp, String p3Reso, String p4Resp,
								String p4Reso, String inprog, String paused, String Impactsignificant,String UrgencyHigh1,
								String Priorityhigh2, String Urgency2Medium, String Priorit3moderate, String Urgency3Low,
								String Priorit4Low){


		try {

			String[] slA={p1Resp, p1Reso};
			String[] stage={inprog, inprog};
			System.out.println(Arrays.asList(slA));
			System.out.println(Arrays.asList(stage));
			
			String[] slA1={p2BResp, p2BReso, p1Reso,p1Resp};
			String[] stage1={inprog, inprog, paused, paused};
			System.out.println(Arrays.asList(slA1));
			System.out.println(Arrays.asList(stage1));
			
			String[] slA2={p2Reso, p2Resp, p2BResp, p2BReso, p1Resp, p1Reso};
			String[] stage2={inprog, inprog, paused, paused, paused, paused};
			System.out.println(Arrays.asList(slA2));
			System.out.println(Arrays.asList(stage2));
			
			String[] slA4={p3Resp, p3Reso, p2Reso, p2Resp, p2BResp, p2BReso, p1Resp, p1Reso};
			String[] stage4={inprog, inprog, paused, paused,paused, paused, paused, paused};
			System.out.println(Arrays.asList(slA4));
			System.out.println(Arrays.asList(stage4));
			
			String[] slA7={p4Resp, p4Reso, p3Resp, p3Reso, p2Reso, p2Resp, p2BResp, p2BReso, p1Resp, p1Reso};
			String[] stage7={inprog, inprog, paused, paused,paused, paused,paused, paused, paused, paused};
			System.out.println(Arrays.asList(slA7));
			System.out.println(Arrays.asList(stage7));
			
			String[] slA9={p2BReso, p2BResp, p4Resp, p4Reso, p3Resp, p3Reso, p2Resp, p2Reso, p1Resp, p1Reso};
			String[] stage9={inprog, inprog, paused, paused,paused, paused,paused, paused, paused, paused};
			System.out.println(Arrays.asList(slA9));
			System.out.println(Arrays.asList(stage9));
			
			String[] slA10={p2Reso, p2Resp, p4Resp, p4Reso, p3Resp, p3Reso, p2BResp, p2BReso, p1Resp, p1Reso};
			String[] stage10={inprog, inprog, paused, paused,paused, paused,paused, paused, paused, paused};
			
			System.out.println(Arrays.asList(slA10));
			System.out.println(Arrays.asList(stage10));
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
				
			//Assigning the group ERP L1		
				home.clickAll()	
					.searchandClickIncident(incNumber)
					.changeAssGrpAndverifySLA(asgGroup1, slA, stage)
//			Assigning the group Database ( Oracle EBS ) L1
					.changeAssGrpAndverifySLA(dbAssGroup, slA, stage)
			//Assigning the group  ERP Security L1
					.changeAssGrpAndverifySLA(erpSecu, slA, stage)
			//Assigning the group  Hyperion L1
					.changeAssGrpAndverifySLA(hyperionL1, slA, stage)
			//Change of priority from P1 to p2 with the Assignment group Hyperion L1	
					.changepriorityAndverifySLA(Impactsignificant, UrgencyHigh1, Priorityhigh2, slA1, stage1)
			//Change of group from Hyperion L1 to Database ( Oracle EBS ) L1	
					.changeAssGrpAndverifySLA(dbAssGroup, slA2, stage2)
			//Change of group from Database ( Oracle EBS ) L1 to ERP 1
					.changeAssGrpAndverifySLA(asgGroup1, slA2, stage2)
			//Change of priority from P2 to p3	
					.changepriorityAndverifySLA(Impactsignificant, Urgency2Medium, Priorit3moderate, slA4, stage4)
			//Change of Assignment group to Hyperion L1
					.changeAssGrpAndverifySLA(hyperionL1, slA4, stage4)
			//Change of Assignment group to ERP Security L1
					.changeAssGrpAndverifySLA(erpSecu, slA4, stage4)
			//Change of priority from P3 to p4	
					.changepriorityAndverifySLA(Impactsignificant, Urgency3Low, Priorit4Low, slA7, stage7)
			//Change of Assignment group to Hyperion L1
					.changeAssGrpAndverifySLA(hyperionL1, slA7, stage7)
			//Change of Priority from P4 – P2 under Hyperion L1
					.changepriorityAndverifySLA(Impactsignificant, UrgencyHigh1, Priorityhigh2, slA9, stage9)
			//Change of group from Hyperion L1 to Database ( Oracle EBS ) L1	
					.changeAssGrpAndverifySLA(dbAssGroup, slA10, stage10);	
				
				home.clickLogout();
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}




	@DataProvider(name = "GLIM_V2_Stry000000_IM51_1")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM51_1");
		return arrayObject;
	}
	
	
	
}
