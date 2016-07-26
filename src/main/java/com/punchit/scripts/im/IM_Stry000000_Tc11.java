package com.punchit.scripts.im;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;

public class IM_Stry000000_Tc11 extends SuiteMethods {
	// Create Instance

		@Test(dataProvider = "IM_Stry000000_Tc11",groups="IncidentManagement")
		public void createIncident(String regUser, String regPwd,String filtervalue
								   ) throws InterruptedException{
			
			// Pre-requisities
			
			try {

				// Step 0: Launch the application
				snW.launchApp(browserName, true);

				// Step 1: Login to the application
				MenuPage home = new LoginPage().loginAs(regUser, regPwd);

				// Step 2: click on create new
				IncidentPage incident = home.clickCreateNew();

				// Take a note of the INC number.
				String incNumber = incident.getIncidentNumber();
				System.out.println(incNumber);

				//Step 3: Set the configueation item based on the class
				try {
				incident.clickconfigurationItemLookUp().selectConfigurationItemBasedonClass(filtervalue);
				Reporter.reportStep("Configuration item with class of " + filtervalue + " is selected sucessfully" ,"SUCCESS");
				}
				catch(Exception e)
				{
				Reporter.reportStep("Configuration item with class of " + filtervalue + " could not be selected ","FAILURE");	
				}
				Thread.sleep(3000);
				// Go to CI Component field and click on the spyglass
				incident.clickCIComponentLookUp().verifyCIComponentApplicationLookUpValues();

				status="PASS";
			
				
			} finally {
				// close the browser
				snW.quitBrowser();
			}

		}


		@DataProvider(name = "IM_Stry000000_Tc11")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc11");
			return arrayObject;
		}
	}



