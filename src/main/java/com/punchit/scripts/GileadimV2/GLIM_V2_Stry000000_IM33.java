package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.SPARCPortalPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM33 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_IM33",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
								String number,String issue,
								String ableToWork,String desc,
								String assignGroup){


				try {

					launchApp(browserName, true);
					
					SPARCPortalPage home=	
							new LoginPage()
							.loginAs(regUser, regPwd)
							.clickSPARCPortal();
					IncidentPage incident =	
						home.clickCreateIncident()
							.RaiseIncident1(number, issue, ableToWork, desc);
					String incNum = 
							incident.getIncidentNumber();
						home.clickSPARCHomeMenu()
							.searchIncidentFromMenu(incNum)
							.verifyAssignmentGroupField(assignGroup);
					
					status = "PASS";

				} finally {
					
					snW.quitBrowser();
				}

			}



	@DataProvider(name = "GLIM_V2_Stry000000_IM33")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM33");
		return arrayObject;
	}
}
