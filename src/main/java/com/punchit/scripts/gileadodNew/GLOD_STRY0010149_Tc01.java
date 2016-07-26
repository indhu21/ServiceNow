package com.punchit.scripts.gileadodNew;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLOD_STRY0010149_Tc01 extends SuiteMethods {

	@Test(dataProvider="GLOD_Stry0010149_Tc01",groups="OpsDirector")

	public void assignAlert(String regUser, String regPwd, String assignedTo, 
							String verUser, String verPwd, String verify) {
		try {

			snW.launchApp(browserName, true);

			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
				AlertsListPage list = 
						home.clickMyAlertConsole()
							.verifyData();
							
			String alertId =
					list.getAlertId();
			
						list.clickFirstAlertId(alertId)
							.enterandChooseAssignTo(assignedTo)
							.clickUpdateButton();
						
						home.clickLogout();
							
							new LoginPage()
							.loginAs(verUser,verPwd)
							.clickMyAlertConsole()
							.clickMyAlertslink()
							.clickCreatedAlert(alertId)
							.verifyAssignTo(assignedTo)
							.verifyActivityLog(verUser, regUser);
						home.clickLogout();	

			status = "PASS";

		} finally{

			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name="GLOD_Stry0010149_Tc01")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_Stry0010149_Tc01");
		return arrayObject;
	}	
}
