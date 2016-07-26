package com.punchit.scripts.gileadven;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertProfilePage;
import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_Stry0010167_Tc03 extends SuiteMethods {


	@Test(dataProvider = "Ven_GLOD_Stry0010167_Tc03")
	public void testName(	String regUser, String regPwd, String scenario, String scheduleType,
							String shortDesc, String startHr, String startMn, String startSc, 
							String endHr, String endMn, String endSc){

		try {

			snW.launchApp(browserName, true);
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
					
		AlertsListPage list = 
				home.clickOpsAlertConsole()
					.verifyIsRecordDisplays(scenario);
		
		String alertId = 
				list.getAlertId();
		
			AlertProfilePage profile = 
					list.clickColumnValue("Alert Profile", alertId);
						
			String alertCI =
					profile.getCIName();
					profile.clickUpdate();
		String alertCIlatest = 
				alertCI+list.getCurrentTime();
		
			list.rightclickAlert(alertId)
				.suppressAlertUsingAlertScope(alertId)
				.veriyfSuppressProfileName(alertCI)
				.enterAllMandatoryFields(alertCIlatest, shortDesc, scheduleType)
				.enterStartandEndTime(startHr, startMn, startSc, endHr, endMn, endSc)
				.clickSubmitButton(alertCIlatest);
			home.clickAlertSuppressors()
				.verifyCreatedAlertSuppress(alertCIlatest);
		
		home.clickLogout();


		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "Ven_GLOD_Stry0010167_Tc03")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("Ven_GLOD_Stry0010167_Tc03");
		return arrayObject;
	}



}