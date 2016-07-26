package com.punchit.scripts.GileadimV2;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM51_5 extends SuiteMethods {


	@Test(dataProvider = "GLIM_V2_Stry000000_IM51_5",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String configItem, String repCust, String desc,
								String Impact1, String Urgency1, String Priority1, String assignGroup, String asgGroup1, 
								String regUser1, String regPwd1, String p1Resp, String p1Reso, String inprog, 
								String compltd, String paused, String asgTo, String workNotes, String causingCI, String causingCIComponent,
								String resolutionCode, String resolutionNotes) throws ParseException{


		try {

			
			String[] slA={p1Resp, p1Reso};
			String[] stage={inprog, inprog};
			
			String[] slA1={p1Resp};
			String[] stage1={compltd};
			
			String[] slA2={p1Reso};
			String[] stage2={paused};
			
			String[] slA3={p1Reso};
			String[] stage3={compltd};
						
			
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
					.verifyPriority(Impact1, Urgency1, Priority1)
					.verifyAssignmentGroupField(assignGroup)
					.clickSave();
				home.clickLogout();
					new LoginPage()
					.loginAs(regUser1, regPwd1);
				
				home.clickAll()	
					.searchandClickIncident(incNumber)
					.changeAssGrpAndverifySLA(asgGroup1, slA, stage)
					.enterAssignedTo(asgTo)
					.clickNotes()
					.enterWorkNotes(workNotes)
					.clickSave()
					.verifySLATableItems(slA1, stage1)
					.clickResolveIncidentWithAlertAccept()
					.enterResolveFields(causingCI, causingCIComponent, resolutionCode, resolutionNotes)
					.verifySLATableItems(slA2, stage2)
					.clickReopen("test")
					.returMenuPage()
					.addNumberofDays(incNumber, slA2[0], regUser1, regPwd1, 3)
					.verifySLATableItems(slA3, stage3);
				
				home.clickLogout();
				
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}




	@DataProvider(name = "GLIM_V2_Stry000000_IM51_5")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM51_5");
		return arrayObject;
	}
	
	
	
}
