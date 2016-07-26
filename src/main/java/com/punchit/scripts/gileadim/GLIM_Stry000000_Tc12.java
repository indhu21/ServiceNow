package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;

public class GLIM_Stry000000_Tc12 extends SuiteMethods{
	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc12",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,String filtervalue){
		
		// Pre-requisities
		
		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);

			// Step 2: click on create new
//			IncidentPage incident = 
					home.clickCreateNew()
						.enterConfigurationItemForSuccess(filtervalue)
//						.clickCIComponentLookUp()
//						.verifyCIComponentServerLookUpValues()
//						.clickCIComponentLookUpandVerifyCIComponentServerLookUpValues();
						.clickCIComponetSpyGlassAndverifyCIcounts();

//			// Take a note of the INC number.
//			String incNumber = incident.getIncidentNumber();
//			System.out.println(incNumber);
//
//			//Step 3: Set the configueation item based on the class
//			try {
//			incident.clickconfigurationItemLookUp().selectConfigurationItemBasedonClass(filtervalue);
//			Reporter.reportStep("Configuration item with class of " + filtervalue + " is selected successfully" ,"SUCCESS");
//			}
//			catch(Exception e)
//			{
//			Reporter.reportStep("Configuration item with class of " + filtervalue + " could not be selected ","FAILURE");	
//			}
//			
//			// Go to CI Component field and click on the spyglass
//			incident.clickCIComponentLookUp().verifyCIComponentServerLookUpValues();

			status="PASS";
		
			
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_Stry000000_Tc12")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc12");
		return arrayObject;
	}
}






