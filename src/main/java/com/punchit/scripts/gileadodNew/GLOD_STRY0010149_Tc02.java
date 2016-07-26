package com.punchit.scripts.gileadodNew;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0010149_Tc02 extends SuiteMethods {
	@Test(dataProvider="GLOD_Stry0010149_Tc02",groups="OpsDirector")

	public void assignAlert(String regUser, String regPwd, String assignedTo, String verUser, String verPwd, String state,String progress) {
		try {

			snW.launchApp(browserName, true);

			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
				AlertsListPage list = 
						home.clickMyAlertConsole()
							.clickMyAlertslink()
//							.addNewFilterinAlertUsingSelect("State", "is", "Work in Progress")
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

	@DataProvider(name="GLOD_Stry0010149_Tc02")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_Stry0010149_Tc02");
		return arrayObject;
	}	
}
