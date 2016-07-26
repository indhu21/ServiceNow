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

public class GLIM_V2_Stry000000_IM52_4 extends SuiteMethods {


	@Test(dataProvider = "GLIM_V2_Stry000000_IM52_4",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String configItem, String repCust, String desc,
								String Impact1, String Urgency1, String Priority1, String assignGroup, 
								String regUser1, String regPwd1, String p1Resp, String p1Reso, String p2Resp,
								String p2Reso, String inprog, String paused, String compltd, String assTo, 
								String type, String reason, String Impactsignificant, String UrgencyHigh1,
								String Priorityhigh2) throws ParseException{


		try {
			
			String[] slA={p1Resp, p1Reso};
			String[] stage={inprog, inprog};
			
			String[] slA1={p2Resp, p2Reso};
			String[] stage1={inprog, inprog};
			
			String[] slA2={p1Resp, p1Reso};
			String[] stage2={compltd, paused};
			
			String[] slA3={p2Resp, p1Reso};
			String[] stage3={compltd, paused};
			
			
			
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
					.clickSave();
				home.clickLogout();
					new LoginPage()
					.loginAs(regUser1, regPwd1);
			
			String[] values={p1Resp, inprog, "Gilead 24by7", "US/Pacific", incNumber, "", "", ""};
					
				home.clickAll()	
					.searchandClickIncident(incNumber)
					.changeAssGrpAndverifySLA(assignGroup, slA, stage)
					.clickSLAPreviewIcon(slA[0])
					.clickRunSLAButton();
					 verifyTime("00:02:00", "00:13:00", "00:01:00", "00:14:00");
			incident.verifySLAFields(values)
					.clickBackButton()
					.clickSLAPreviewIcon(slA[1])
					.clickRunSLAButton();
					verifyTime("00:10:00", "03:50:00", "00:05:00", "03:50:00");
				String[] values1={p1Resp, inprog, "Gilead 24by7", "US/Pacific", incNumber, "", "", ""};
			incident.verifySLAFields(values1)
					.clickBackButton()
					.changepriorityAndverifySLA(Impactsignificant, UrgencyHigh1, Priorityhigh2, slA1, stage1)
					.clickSLAPreviewIcon(slA1[0])
					.clickRunSLAButton();
					 verifyTime("00:05:00", "00:25:00", "00:05:00", "00:25:00");
				String[] values2={p1Resp, inprog, "Gilead 24by7", "US/Pacific", incNumber, "", "", ""}; 
			incident.verifySLAFields(values2)
					.clickBackButton()
					.clickSLAPreviewIcon(slA1[1])
					.clickRunSLAButton();
					verifyTime("00:12:00", "07:30:00", "00:12:00", "07:30:00");
				String[] values3={p1Resp, inprog, "Gilead 24by7", "US/Pacific", incNumber, "", "", ""};	
			incident.verifySLAFields(values3)
					.clickBackButton()
					.clickSLAPreviewIcon(slA[0])
					.clickRunSLAButton();
				String[] values4={p1Resp, "Cancelled", "Gilead 24by7", "US/Pacific", incNumber, "", "", ""};
			incident.verifySLAFields(values4)
					.clickBackButton()
					.clickSLAPreviewIcon(slA[1])
					.clickRunSLAButton();
				String[] values5={p1Resp, paused, "Gilead 24by7", "US/Pacific", incNumber, "", "", ""};	
			incident.verifySLAFields(values5)
					.clickBackButton()
//					doubt
					.changepriorityAndverifySLA(Impactsignificant, UrgencyHigh1, Priorityhigh2, slA3, stage3)
					.clickSLAPreviewIcon(slA3[0])
					.clickRunSLAButton();
					 verifyTime("00:02:00", "00:13:00", "00:01:00", "00:14:00");
				String[] values6={p1Resp, inprog, "Gilead 24by7", "US/Pacific", incNumber, "", "", ""};
			incident.verifySLAFields(values6)
					.clickBackButton()
					.clickSLAPreviewIcon(slA3[1])
					.clickRunSLAButton();
					verifyTime("00:10:00", "03:50:00", "00:05:00", "03:50:00");
				String[] values7={p1Resp, inprog, "Gilead 24by7", "US/Pacific", incNumber, "", "", ""};	
			incident.verifySLAFields(values7);

			home.clickLogout();
			
					
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}




	@DataProvider(name = "GLIM_V2_Stry000000_IM52_4")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM52_4");
		return arrayObject;
	}
	
	public void verifyTime(String time1, String time2, String time3, String time4) {
		
		
		try {
			new IncidentPage(driver)
			.verifyActualTime(time1)
			.verifyActualTimeLeft(time2)
			.verifyBussinessTime(time3)
			.verifyBussinessTimeLeft(time4);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
