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

public class GLIM_V2_Stry000000_IM52_5 extends SuiteMethods {


	@Test(dataProvider = "GLIM_V2_Stry000000_IM52_5",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String configItem, String repCust, String desc,
								String Impact1, String Urgency1, String Priority1, String assignGroup, 
								String regUser1, String regPwd1, String p2Resp, String p2Reso, 
								String inprog, String assTo) throws ParseException{


		try {
			
			String[] slA={p2Resp, p2Reso};
			String[] stage={inprog, inprog};
			
			System.out.println(slA[0]);
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
					.clickSave();
				home.clickLogout();
					new LoginPage()
					.loginAs(regUser1, regPwd1);
				
				home.clickAll()	
					.searchandClickIncident(incNumber)
					.changeAssGrpAndverifySLA(assignGroup, slA, stage)
					.clickSLAPreviewIcon(slA[0])
					.clickRunSLAButton()
					.verifyActualTime("00:01:00")
					.verifyActualTimeLeft("00:14:00")
				/*home.clickLogout();
					new LoginPage()
					.loginAs(regUser1, regPwd1);
				home.clickAll()	
					.searchandClickIncident(incNumber)*/
					.clickBackButton()
					.enterAssignedTo("**")
					.clickSave()
					.clickSLAPreviewIcon(slA[0])
					.clickRunSLAButton()
					.verifyActualTime("00:02:00")
					.verifyActualTimeLeft("23:50:00");

				home.clickLogout();
					
					
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}




	@DataProvider(name = "GLIM_V2_Stry000000_IM52_5")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM52_5");
		return arrayObject;
	}
	
	
	
}
