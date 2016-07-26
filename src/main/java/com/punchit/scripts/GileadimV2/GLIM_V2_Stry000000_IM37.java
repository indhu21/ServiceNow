package com.punchit.scripts.GileadimV2;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.SPARCPortalPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM37 extends SuiteMethods {

	@Test(dataProvider = "GLIM_V2_Stry000000_IM37",groups="IncidentManagement")
	public void createIncident( String regUser, String regPwd,
								String regUser1, String regPwd1){

		try {

				launchApp(browserName, true);
				
				SPARCPortalPage home=	
						new LoginPage()
						.loginAs(regUser, regPwd)
						.clickSPARCPortal();
					home.clickMyWork()
						.clickMyWorkTab()
						.rightClickStateHeadForMyWorkTab()
						.isClosedGroupAvailable()
						.clickMyWorkGroupTab()
						.rightClickStateHeadForMyGroupWorkTab()
						.isClosedGroupAvailable();
					
					home.clickSPARCHomeMenu()
						.clickLogout();
						
						new LoginPage()
						.loginAs(regUser1, regPwd1)
						.clickSPARCPortal();
					home.clickMyWork()
						.clickMyWorkTab()
						.rightClickStateHeadForMyWorkTab()
						.isClosedGroupAvailable()
						.clickMyWorkGroupTab()
						.rightClickStateHeadForMyGroupWorkTab()
						.isClosedGroupAvailable();
					
					home.clickSPARCHomeMenu()
						.clickLogout();

						
					status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLIM_V2_Stry000000_IM37")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM37");
		return arrayObject;
	}





}



