package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLIM_Stry000000_Tc14 extends SuiteMethods {


	@Test(dataProvider = "GLIM_Stry000000_Tc14",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
			String filter1, String filter2, String filter3){

		try {
			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			String[] elements = {	"Automated Job Failure",
					"Connectivity",
					"Data Issue",
					"Error message", 
					"Integration Issue", 
					"IP Address",
					"Login failure", 
					"Memory",
					"Performance degradation",
					"Power",
					"Security breach",
			"Virus"};


			// Step 1: Login to the application
			MenuPage home = new LoginSparcLCPage().loginAs(regUser, regPwd);

			// Step 2: click on create new
			//				IncidentPage incident = 
			home.clickCreateNew()
			.enterConfigurationItemForSuccess(filter3)
			//						.clickConfigurationItemSpyGlass()
			//						.selectFilter(filter1, filter2, filter3)
			//						.clickFirstCIClass()
			//						.clickCIComponetSpyGlass()
			//						.verifyCIComponetSpyGlass(elements);
//			.clickCIComponetSpyGlassAndverifyCIComponetSpyGlass(elements);
			.clickCIComponetSpyGlassAndverifyCIcounts();
			status="PASS";


		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLIM_Stry000000_Tc14")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc14");
		return arrayObject;
	}
}
