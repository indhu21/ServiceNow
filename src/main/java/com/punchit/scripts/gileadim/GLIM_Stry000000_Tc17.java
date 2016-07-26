package com.punchit.scripts.gileadim;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.IncidentsListPage;
import pages.ListPage;
import pages.LoginPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLIM_Stry000000_Tc17 extends SuiteMethods{

	@Test(dataProvider = "GLIM_Stry000000_Tc17",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
			String selectBy, String searchItem){


		try {
			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			
			String[] elements = {	"Account locked",
									"Connectivity",
									"Error message", 
									"IP Address",
									"Login failure", 
									"Memory",
									"Not responding/Frozen",
									"Performance degradation",
									"Peripherals",
									"Power",
									"Paper Jam",
									"Stolen/Lost"};
							
			

			// Step 1: Login to the application
			MenuPage home = 
					new LoginSparcLCPage()
			.loginAs(regUser, regPwd);

			// Step 2: click on create new
			home.clickCreateNew()
			.enterConfigurationItemForSuccess(searchItem)
			//			.clickCIComponetSpyGlass()
			//			.enterConfigurationItemAndClickCIComponentSpyglass(searchItem)
			//			.verifyCIComponetSpyGlass(elements)
//			.clickAndverifyCIComponetSpyGlass(elements);
			.clickCIComponetSpyGlassAndverifyCIcounts();
			status="PASS";


		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLIM_Stry000000_Tc17")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc17");
		return arrayObject;
	}
}
