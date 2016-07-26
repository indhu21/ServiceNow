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
import wrapper.ServiceNowWrappers;

// for using ATU reporting -- added the listeners

public class GLOD_STRY0010115_Tc01 extends SuiteMethods {


	@Test(dataProvider = "GLOD_Stry0010115_Tc01")
	public void testName(	String regUser, String regPwd, String scenario, String reactionType, 
							String alertRecordState, String relTaskState) {

		try {
			snW.launchApp(browserName, true);

			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);	
			AlertsListPage	list = 
				home.clickOpsAlertConsole()		
//					.verifyIsRecordDisplays(scenario)
					.verifyData();
					
			String alertId = 
					list.getAlertId();
			 
				AlertPage alert =
				list.clickColumnValue("Alert Profile", alertId)
					.selectReactionType(reactionType)
					.clickUpdate()
					.clickFirstAlertId(alertId)
					.clickAcknowledge()
					.clickRunReaction();
				
				String alertedTask = 
						alert.getRelatedTaskNumber();
				
				home.clickMyAlert()
					.clickMyAlertslink()
					.clickCreatedAlert(alertId)
					.verifyAlertState(alertRecordState)
					.verifyRelatedTaskState(relTaskState)
					.verifyRelatedTaskNumber(alertedTask);
				home.clickLogout();			

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLOD_Stry0010115_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("GLOD_Stry0010115_Tc01");
		return arrayObject;
	}



}