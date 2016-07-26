package com.punchit.scripts.gileadven;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;




import pages.AlertsProfilesListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

// for using ATU reporting -- added the listeners

public class Ven_GLOD_Stry0010169_Tc01 extends SuiteMethods {
	
	@Test(dataProvider = "Ven_GLOD_Stry0010169_Tc01")
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
								.verifyProfileNameInOverridesTab(alertProfile);
							
							home.clickLogout();	
							
								status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "Ven_GLOD_Stry0010169_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("Ven_GLOD_Stry0010169_Tc01");
		return arrayObject;
	}

	

}