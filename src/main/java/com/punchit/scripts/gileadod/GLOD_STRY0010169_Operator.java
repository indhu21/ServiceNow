package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertsProfilesListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

// for using ATU reporting -- added the listeners

public class GLOD_STRY0010169_Operator extends SuiteMethods {
	
	@Test(dataProvider = "GLOD_STRY0010169_Operator")
	public void testName(	String regUser, String regPwd, String Search, 
							String Overrides, String NewSearch) {

		try {
			
			snW.launchApp(browserName, true);
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
				
			AlertsProfilesListPage list = 
						home.clickAlertProfiles();
				
				String alertProfile = 
									list.getAlertProfileName();
					
							list.clickFirstAlertProfile(alertProfile)
								.enterAndChooseOverrides(Overrides)
								.clickUpdateButton()
								.clickFirstAlertProfile(alertProfile)
								.clickOverrideinfoButton()
								.clickOverrideTab()
								.verifyProfileNameInOverridesTab(alertProfile);
							
							home.clickLogout();	
							
								status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLOD_STRY0010169_Operator")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("GLOD_STRY0010169_Operator");
		return arrayObject;
	}

	

}