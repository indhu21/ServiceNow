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

public class GLOD_STRY0010140_User extends SuiteMethods{
	
		@Test(dataProvider="GLOD_STRY0010140_User",groups="OpsDirector")
		public void implementSLA(String regUser, String regPwd) {
			try {
	
				snW.launchApp(browserName, true);
				
				MenuPage home = 
						new LoginPage()
						.loginAs(regUser, regPwd);	
				
						home.clickAlertProfileRegistrationNegative()
							.clickLogout();
	
				status = "PASS";

			} finally{

				// close the browser
				snW.quitBrowser();	
			}

		}
		@DataProvider(name="GLOD_STRY0010140_User")
		public Object[][] loginData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010140_User");
			return arrayObject;
		}
	}

