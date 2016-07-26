package com.punchit.scripts.gileadven;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;



import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

// for using ATU reporting -- added the listeners

public class Ven_GLOD_Stry0010145_Tc01 extends SuiteMethods {

	
	@Test(dataProvider = "Ven_GLOD_Stry0010145_Tc01")
	public void testName(String regUser, String regPwd, String closedBy) {

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
							home.clickAlertsByState1();
							home.clickAlertsByState()
								.clickAllLink()
								.clickCreatedAlert(firstAlertId)
								.verifyAlertState("Closed")
								.validateClosedValue(closedBy);
							
									
								home.clickMyAlertConsole()
									.verifyData();
							
							firstAlertId = 
									list.getAlertId();
									
									list.rightclickAlert(firstAlertId)
										.rightClickCloseAlert(firstAlertId, "Closed for Testing");
								
									home.clickAlertsByState1();
									home.clickAlertsByState()
									.clickAllLink()
									.clickCreatedAlert(firstAlertId)
									.verifyAlertState("Closed")
									.validateClosedValue(closedBy);
				
								
								home.clickLogout();
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "Ven_GLOD_Stry0010145_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("Ven_GLOD_Stry0010145_Tc01");
		return arrayObject;
	}



}