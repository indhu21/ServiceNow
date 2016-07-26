package com.punchit.scripts.im;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.IncidentsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class IM_Stry000000_Tc17 extends SuiteMethods{
	// Create Instance
		ServiceNowWrappers snW;

		@Test(dataProvider = "IM_Stry000000_Tc17",groups="IncidentManagement")
		public void createIncident(String regUser, String regPwd,
								   String configItem){

			// Pre-requisities
			snW = new ServiceNowWrappers(entityId);

			try {

				// Step 0: Launch the application
				snW.launchApp(browserName, true);

				// Step 1: Login to the application
				MenuPage home = new LoginPage().loginAs(regUser, regPwd);
			
//				// Step 2: Verify the Menus
//				home.verifyExistanceOfIncidentMenus();
				
				// Step 3: click on create new
				IncidentPage incident21 = home.clickCreateNew();
				
				// Take a note of the INC number.
//				String incNumber = incident.getIncidentNumber();
//				System.out.println(incNumber);
				
				// Step 3: Type Scanner into the field
				//incident21.enterConfigurationItemForSuccess(configItem).Wait(2000);
				try {
					incident21.clickconfigurationItemLookUp().selectConfigurationItemBasedonClass(configItem);
					Reporter.reportStep("Configuration item with class of " + configItem + " is selected sucessfully" ,"SUCCESS");
					}
					catch(Exception e)
					{
					status="INSUFFICIENT DATA";
					Reporter.reportStep("Configuration item with class of " + configItem + " could not be selected ","FAILURE");	
					}
				// Go to CI Component field and click on the spyglass
				incident21.clickCIComponentLookUp().verifyCIComponentFieldLookUpValues();
				status = "PASS";
			}
			finally {
				// close the browser
				snW.quitBrowser();
			}

		}

		@DataProvider(name = "IM_Stry000000_Tc17")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc17");
			return arrayObject;
		}
	}
