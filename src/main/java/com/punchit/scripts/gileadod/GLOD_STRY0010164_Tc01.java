package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertPage;
import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

// for using ATU reporting -- added the listeners

public class GLOD_STRY0010164_Tc01 extends SuiteMethods {

	@Test(dataProvider = "GLOD_Stry0010164_Tc01")
	public void testName(	String regUser, String regPwd, String scenario, String reaType, 
							String callerName, String closeMess, String wrkNotes) {

		try {

			snW.launchApp(browserName, true);

			MenuPage home =
					new LoginPage()
					.loginAs(regUser, regPwd);
		/*AlertsListPage	list = 
				home.clickOpsAlertConsole()
					.verifyIsRecordDisplays(scenario);
		
		String alertId = 
				list.getAlertId();
				
				list.clickColumnValue("Alert Profile", alertId)
					.selectReactionType(reaType)
					.clickUpdate()
					.rightclickAlert(alertId)
					.rightClickAcknowledgeAlert(alertId);
			*/
			AlertsListPage	list = 
					home.clickOpsAlertConsole()
						.verifyData()
			    		.clickNewAlert()
			    		.clickAlertFunnelIcon()
			    		.clickANDCondition()
						.addNinethFilterByEnterValue("   Reaction Type", "is", "Create Incident")
						.clickRun()
						.verifyData();
			String alertId = 
			list.getAlertId();
			
			list.clickColumnValue("Alert Profile", alertId)
				.selectReactionType(reaType)
				.clickUpdate()
				.rightclickAlert(alertId)
				.rightClickAcknowledgeAlert(alertId);
		
				
				
			AlertPage alert = 
				home.clickMyAlert()
					.clickMyAlertslink()
					.clickCreatedAlert(alertId)
					.clickRunReaction();
				
				String incNumber = 
					alert.getIncNumber();
				
			   alert.enterCallerName(callerName)
					.clickUpdateButton()
					.rightclickAlert(alertId)
					.rightClickCloseAlert(alertId, closeMess)
					.verifyIsAlertNotDisplayed(alertId);
				
			   home.clickIncidents()
					.clickCreatedIncident(incNumber)
					.verifyActivityLog(wrkNotes);
				
				home.clickLogout();
			

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLOD_Stry0010164_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("GLOD_Stry0010164_Tc01");
		return arrayObject;
	}



}