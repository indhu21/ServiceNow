package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0010131_Tc01 extends SuiteMethods {
	
			
			@Test(dataProvider = "GLOD_STRY0010131_Tc01",groups="OpsDirector")
			public void appProperties(String regUser, String regPwd, String CloseCode, String state){
				
				try {
					
					snW.launchApp(browserName, true);
					
					MenuPage home = 
							new LoginPage()
							.loginAs(regUser, regPwd);
					
						AlertsListPage list = 
						home.clickOpsAlertConsole()
//							.verifyIsRecordDisplays("Punch Node Status Critical")
							.verifyData();
						String parent = 
								list.getParentAlert();
						System.out.println(parent);
						String child = 
								list.getChildAlert();
						System.out.println(child);
						list.createChildAlertwithoutReport(child, parent)
							.clickNewAlert()
							.rightClickCloseAlert(parent, CloseCode)
							.addFirstFilterEnter("Number", "is", parent)
							.verifyState(state)
							.clickHideIcon()
							.isChildCountClosed(child);
					
		    
		            status="PASS";
				
				}
				finally{
					// close the browser
					snW.quitBrowser();		
				}
			}

				@DataProvider(name = "GLOD_STRY0010131_Tc01")
				public Object[][] fetchData() throws IOException {
					Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010131_Tc01");
					return arrayObject;
				
				}
	}

