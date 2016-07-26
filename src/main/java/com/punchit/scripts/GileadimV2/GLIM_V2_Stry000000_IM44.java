package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM44 extends SuiteMethods {

	@Test(dataProvider = "GLIM_V2_Stry000000_IM44",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
							   String regUser1, String regPwd1){

		try {

				launchApp(browserName, true);
			
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
			
				IncidentPage incident=
					home.verifyExistanceOfIncidentMenus()
						.clickOpen()
						.clickFunnelWithoutReport()
						.clickANDCondition()
						.addSecondFilterbyEnterandChoose("Assignment Group", "is", "TESM_INC_GROUP")
						.clickANDCondition()
						.addThirdFilter("State", "is", "Open")
						.clickRun()
						.clickFirstIncident()
						.verifyOpenButtons(regUser);
			
				String incNumber =
						incident.getIncidentNumber();
					home.clickLogout();
						
					new LoginPage()
						.loginAs(regUser1, regPwd1);
				
					home/*.verifyExistanceOfIncidentMenus()*/
						.clickAll()
						.clickCreatedIncident(incNumber)
						.verifyOpenButtons(regUser1);
					
					home.clickLogout();
						
					status = "PASS";
					
		} finally {
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM44")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM44");
		return arrayObject;
	}
}
