package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0010140_Operator extends SuiteMethods{
	
		@Test(dataProvider="GLOD_STRY0010140_Operator",groups="OpsDirector")
		public void implementSLA(String regUser, String regPwd,String name, String desc, String owningGroup, 
								 String value1,String value2, String sec) {
			try {
	
				snW.launchApp(browserName, true);
				
				MenuPage home = 
						new LoginPage()
						.loginAs(regUser, regPwd);	
				
						home.clickAlertProfileRegistration()
							.profileCreationWithDygrpNo(name, value1, value2, owningGroup, "Create Incident", desc)
							.enterHoldTime(sec)
							.profileSave();
						home.clickLogout();
	
				status = "PASS";

			} finally{

				// close the browser
				snW.quitBrowser();	
			}

		}
		@DataProvider(name="GLOD_STRY0010140_Operator")
		public Object[][] loginData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010140_Operator");
			return arrayObject;
		}
	}

