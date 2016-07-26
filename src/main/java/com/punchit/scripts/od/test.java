package com.punchit.scripts.od;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class test extends SuiteMethods {
			// Create Instance
		ServiceNowWrappers snW;

	   @Test(dataProvider = "OD_Stry0011296_TC01",groups="OpsDirector")
	   public void testName(String regUser, String regPwd) {

			// Pre-requisities
			snW = new ServiceNowWrappers(entityId);


			try {

				// Step 0: Launch the application
				snW.launchApp(browserName, true);

				// Step 1: Log in to application
				if (snW.login(regUser, regPwd))
					Reporter.reportStep("Step 1: The login with username:"
							+ regUser + " is successful", "SUCCESS");
				else
					Reporter.reportStep("Step 1: The login with username:"
							+ regUser + " is not successful", "FAILURE");

				// Step 2: Write a code to select the menu using 
				if(snW.selectMenu("RunBook", "Assigned_to_me"))
					Reporter.reportStep("Step 2: The Alert Console under OpsConsole - menu selected successfully","SUCCESS");
				else
					Reporter.reportStep("Step 2: The Alert Console under OpsConsole - menu could not be selected","FAILURE");

				// Switch to the main frame
				
				status = "PASS";

			} finally {
				// close the browser
				snW.quitBrowser();
				}

		}

		@DataProvider(name = "OD_Stry0011296_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider
					.getSheet("OD_Stry0011296_TC01");
			return arrayObject;
		}





}
