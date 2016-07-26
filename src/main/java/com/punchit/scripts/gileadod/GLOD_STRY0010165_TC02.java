package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertPage;
import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0010165_TC02 extends SuiteMethods{

	@Test(dataProvider = "GLOD_STRY0010165_TC02",groups="OpsDirector")
	public void alertProfileIncidentAssignmentGroup(String regUser, String regPwd, String revUser, String revPwd,
													String f2Section,String f3Section, String f2Condition, String f2Value, 
													String callerName){


try {
	

					snW.launchApp(browserName, true);
				
					MenuPage home =
							new LoginPage()
							.loginAs(regUser, regPwd);
/*				AlertsListPage	list = 
						home.clickOpsAlertConsole()
						.verifyIsRecordDisplays("Sumerian Capacity Alerts");
				
				String alertId = 
						list.getAlertId();
						
						list.clickColumnValue("Alert Profile", alertId)
							.selectReactionType("Create Incident")
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
						
						
					AlertPage alert = 
						home.clickMyAlert()
							.clickMyAlertslink()
							.clickCreatedAlert(alertId)
							.clickRunReaction();
						
						String incNumber = 
							alert.getIncNumber();
						
					   alert.enterCallerName(callerName)
							.clickUpdateButton();
						
					   home.clickIncidents()
							.clickCreatedIncident(incNumber);

						
						home.clickLogout();
	
						status = "PASS";
}
finally {
	// close the browser
	snW.quitBrowser();
}
}
	@DataProvider(name = "GLOD_STRY0010165_TC02")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010165_TC02");
		return arrayObject;
	}
}
