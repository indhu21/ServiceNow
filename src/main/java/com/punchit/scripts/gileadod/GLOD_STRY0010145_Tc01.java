package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

// for using ATU reporting -- added the listeners

public class GLOD_STRY0010145_Tc01 extends SuiteMethods {

	
	@Test(dataProvider = "GLOD_Stry0010145_Tc01")
	public void testName(String regUser, String regPwd, String closedBy, String closed) {

		try {

			snW.launchApp(browserName, true);

			MenuPage home =
					new LoginPage()
					.loginAs(regUser, regPwd);
				AlertsListPage list = 
						home.verifyOpsConsolesMenu()
							.clickMyAlertConsole()
							.verifyData();
							
				String firstAlertId = 
						list.getAlertId();
				
							list.rightclickAlert(firstAlertId)
								.rightClickCloseAsNoise(firstAlertId);
//							home.clickAlertsByState1();
							home.clickAlertsByState()
								.clickAllLink()
								.clickCreatedAlert(firstAlertId)
								.verifyAlertState(closed)
								.validateClosedValue(closedBy);
							
									
								home.clickMyAlertConsole()
									.verifyData();
							
							firstAlertId = 
									list.getAlertId();
									
									list.rightclickAlert(firstAlertId)
										.rightClickCloseAlert(firstAlertId, "Closed for Testing");
								
//									home.clickAlertsByState1();
									home.clickAlertsByState()
										.clickAllLink()
										.clickCreatedAlert(firstAlertId)
										.verifyAlertState(closed)
										.validateClosedValue(closedBy);
				
								
								home.clickLogout();
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLOD_Stry0010145_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("GLOD_Stry0010145_Tc01");
		return arrayObject;
	}



}