package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.Keys;
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

public class GLOD_STRY0010114_TC02 extends SuiteMethods{

	
	@Test(dataProvider = "GLOD_STRY0010114_TC02",groups="OpsDirector")
	public void appProperties(String regUser, String regPwd, String scenario, 
							  String reactionType){
			
			try {
				snW.launchApp(browserName, true);

				MenuPage home = 
						new LoginPage()
						.loginAs(regUser, regPwd);	
				AlertsListPage	list = 
					home.clickOpsAlertConsole()		
//						.verifyIsRecordDisplays(scenario)
						.verifyData();
				
				String alertId = 
						list.getAlertId();
				 
					AlertProfilePage profile =
					 list.clickAlertProfileWithoutReport("Alert Profile", alertId);
					
					String ownGrp = 
							profile.getOwnGroup();
				 AlertPage alert =
						 profile.selectReactionTypeAndUpdate(reactionType)
						.clickFirstAlertId(alertId)
				 		.clickAcknowledge()
						.verifyAndClickRunReaction()
				 		.verifyOwnGrpName(ownGrp);
						
					String alertedTask = 
							alert.getRelatedTaskNumber();
					
					home.clickMyAlertWithouReport()
						.clickMyAlertslinkWithoutReport()
						.clickCreatedAlert(alertId)
//						.verifyAlertState(alertRecordState)
//						.verifyRelatedTaskState(relTaskState)
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
					@DataProvider(name = "GLOD_STRY0010114_TC02")
					public Object[][] fetchData() throws IOException {
						Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010114_TC02");
						return arrayObject;
					}
		}
		
