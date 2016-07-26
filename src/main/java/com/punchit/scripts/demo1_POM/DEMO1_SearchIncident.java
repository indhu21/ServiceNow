package com.punchit.scripts.demo1_POM;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import demo.LoginPage_ServiceNowDemo;
import demo.MenuPage_ServiceNowDemo;
import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import testng.SuiteMethods_1;
import testng.SuiteMethods_demo1;
import utils.DataInputProvider;
import utils.Reporter;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class DEMO1_SearchIncident extends SuiteMethods {

	
			@Test(dataProvider = "DEMO1_STRY0000005_TC01",groups="DemoIncident")
			public void searchIncident (String regUser, String regPwd, String valid,
					String invalid) throws COSVisitorException, IOException {
				
				
				try {
					
					launchApp(browserName, true);

					MenuPage_ServiceNowDemo home = 
										new LoginPage_ServiceNowDemo()
										.loginAs(regUser, regPwd);
			
							home.clickAll()
								.searchandClickIncidentDemoPositive(valid)
								.searchandClickIncidentDemoNegative(invalid);
							
							home.clickLogout();
							
					
					status="PASS";
						
					
				} finally {
					snW.switchToDefault();
					if(snW.isExistByXpath("Logout_Xpath"))
						{if (!snW.clickByXpath("Logout_Xpath"))
						Reporter.reportStep("The Log out could not be clicked","FAILURE");}
					snW.quitBrowser();
					}

		}

			@DataProvider(name = "DEMO1_STRY0000005_TC01")
			public Object[][] fetchData() throws IOException {
				Object[][] arrayObject = DataInputProvider.getSheet("DEMO1_STRY0000005_TC01");
				return arrayObject;
			}
}
