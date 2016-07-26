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
import wrapper.ServiceNowWrappers;

public class IM_Stry000000_Tc15 extends SuiteMethods {
	
	// Create Instance
		ServiceNowWrappers snW;

		@Test(dataProvider = "IM_STRYT15_TC01",groups="IncidentManagement")
		public void createIncident(String regUser, String regPwd,
								   String configItem){

			// Pre-requisities
			snW = new ServiceNowWrappers(entityId);

			try {
				// Step 0: Launch the application
				snW.launchApp(browserName, false);

				// Step 1: Login to the application
				MenuPage home = new LoginPage().loginAs(regUser, regPwd);
			
				// Step 2: Verify the Menus
				home.verifyExistanceOfIncidentMenus();
				
				// Step 3: click on create new
				IncidentPage incident = home.clickCreateNew();
				
				//Step 4: Enter configuration item
				try {
				     incident.clickconfigurationItemLookUp().selectConfigurationItemBasedonClass(configItem);
				     Reporter.reportStep("Configuration item with class of " + configItem + " is selected sucessfully" ,"SUCCESS");
				     }
				     catch(Exception e)
				     {
				status="INSUFFICIENT DATA";
				     Reporter.reportStep("Configuration item with class of " + configItem + " could not be selected ","FAILURE"); 
				     }
				//Step 5:Clicking on CI component field and checking the values
				incident.verifyCIComponentLookUpValues_Storage();
				
				status = "PASS";

			} finally {
				// close the browser
				snW.quitBrowser();
			}

		}

		@DataProvider(name = "IM_STRYT15_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("IM_STRYT15_TC01");
			return arrayObject;
		}
	}
