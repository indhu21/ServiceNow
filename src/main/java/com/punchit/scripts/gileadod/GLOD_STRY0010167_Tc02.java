package com.punchit.scripts.gileadod;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0010167_Tc02 extends SuiteMethods {

	@Test(dataProvider = "GLOD_Stry0010167_Tc02")
	public void testName(	String regUser, String regPwd, String scenario, String scheduleType, 
							String shortDesc, String startHr, String startMn, String startSc, 
							String endHr, String endMn,String endSc) {

		try {
			
		snW.launchApp(browserName, true);
				MenuPage home = 
						new LoginPage()
						.loginAs(regUser, regPwd);
					
			AlertsListPage list = 
					home.clickOpsAlertConsole()
//						.verifyIsRecordDisplays(scenario);
						.verifyData()
			    		.clickNewAlert()
			    		.clickAlertFunnelIcon()
			    		.clickANDCondition()
			    		.addNinethFilter("Configuration Item", "is not empty")
			    		.clickRun()
			    		.verifyData();
			    		String alertId = 
			    				list.getAlertId();
			
				String alertprofile = 
						list.getColumnValue("Configuration item");
				
				String alertprofilelatest = 
						alertprofile+list.getCurrentTime();	
			    list.rightclickAlert(alertId)
					.suppressAlertUsingCI(alertId)
					.veriyfSuppressProfileName(alertprofile)
//					.
					.enterAllMandatoryFields(alertprofilelatest, shortDesc, scheduleType)
					.enterStartandEndTime(startHr, startMn, startSc, endHr, endMn, endSc)
					.clickSubmitButton(alertprofilelatest);
				home.clickAlertSuppressors()
					.verifyCreatedAlertSuppress(alertprofilelatest);
		
				home.clickLogout();

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLOD_Stry0010167_Tc02")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("GLOD_Stry0010167_Tc02");
		return arrayObject;
	}

	

}