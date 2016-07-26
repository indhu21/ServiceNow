package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertPage;
import pages.AlertProfilePage;
import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0010114_TC01 extends SuiteMethods{

	
	
	@Test(dataProvider = "GLOD_STRY0010114_TC01",groups="OpsDirector")
	public void appProperties(String regUser, String regPwd, 
							  String scenario, String reactionType){
	
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
					 
						AlertProfilePage profile =
						 list.clickAlertProfileWithoutReport("Alert Profile", alertId);
						
						String ownGrp = 
								profile.getOwnGroup();
					 AlertPage alert =
							 profile.selectReactionTypeAndUpdate(reactionType)*/
					
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
					String ownGrp = 
						       list.getColumnValue("Alert Profile");
					AlertPage alert = 
							list.clickFirstAlertId(alertId)
					 		.clickAcknowledge()
							.verifyAndClickRunReaction()
					 		.verifyOwnGrpName(ownGrp);
							
						String alertedTask = 
								alert.getRelatedTaskNumber();
						
						home.clickMyAlertWithouReport()
							.clickMyAlertslinkWithoutReport()
							.clickCreatedAlert(alertId)
//							.verifyAlertState(alertRecordState)
//							.verifyRelatedTaskState(relTaskState)
							.verifyRelatedTaskNumber(alertedTask);
						
						home.clickIncidents()
							.searchandClickIncident(alertedTask)
							.verifyActivityLog(alertId);
							
						home.clickLogout();		
					
			            status="PASS";
	
	}
				finally {
					// close the browser
					snW.quitBrowser();
				}
	}
				@DataProvider(name = "GLOD_STRY0010114_TC01")
				public Object[][] fetchData() throws IOException {
					Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010114_TC01");
					return arrayObject;
				}
	}
	
