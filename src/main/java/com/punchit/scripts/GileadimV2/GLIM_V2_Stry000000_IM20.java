package com.punchit.scripts.GileadimV2;

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

public class GLIM_V2_Stry000000_IM20 extends SuiteMethods {


	@Test(dataProvider = "GLIM_V2_Stry000000_IM20",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String filter1,
			String filter2, String searchItem, String addCIDescription){


		try {
			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			String[] elements = {	"Automated Job Failure",
					"Connectivity",
					"Data Issue",
					"Disk",
					"Error message", 
					"Integration Issue", 
					"IP Address",
					"Login failure", 
					"Memory",
					"Performance degradation",
					"Power",
					"Security breach",
			"Storage"};


			// Step 1: Login to the application
			MenuPage home = new LoginSparcLCPage().loginAs(regUser, regPwd);

			// Step 2: click on create new
			//				IncidentPage incident = 
			home.clickCreateNew()
			.enterConfigurationItemForSuccess(filter1)
			//						.clickConfigurationItemSpyGlass()
			//						.selectFilter(filter1, filter2, filter3)
			//						.clickFirstCIClass()
			//						.clickCIComponetSpyGlass()
			//						.verifyCIComponetSpyGlass(elements)
			//						.clickCIComponetSpyGlassAndverifyCIComponetSpyGlass(elements);
//			.clickCIComponetSpyGlassAndverifyCIcounts();
			.clickCIComponetSpyGlassAndverifyCIcountsAndRemaininIncidentPageWithWarning()

//			home.clickCreateNew()
			.enterConfigurationItemForSuccess(filter2)
			//					.clickConfigurationItemSpyGlass()
			//					.selectFilter(filter1, filter2, filter3)
			//					.clickFirstCIClass()
			//					.clickCIComponetSpyGlass()					
			//					.enterConfigurationItemAndClickCIComponentSpyglass(filter3)
			//					.clickCIComponetSpyGlassAndverifyCIComponetSpyGlass(elements);
//			.clickCIComponetSpyGlassAndverifyCIcounts();
			.clickCIComponetSpyGlassAndverifyCIcountsAndRemaininIncidentPageWithWarning()
			//					.verifyCIComponetSpyGlass(elements);


//			home.clickCreateNew()
			.enterConfigurationItemForSuccess(searchItem)
			//			.clickCIComponetSpyGlass()
			//			.enterConfigurationItemAndClickCIComponentSpyglass(searchItem)
			//			.verifyCIComponetSpyGlass(elements)
			//				.clickAndverifyCIComponetSpyGlass(elements);
//			.clickCIComponetSpyGlassAndverifyCIcounts();
			.clickCIComponetSpyGlassAndverifyCIcountsAndRemaininIncidentPageWithWarning()
			
			

//			home.clickCreateNew()
			.clickConfigurationItemSpyGlassandClickMissingCI()
			.addAddCIDescription(addCIDescription)



			//						.clickCIComponetSpyGlass()
			//						.verifyCIComponetSpyGlass(elements)
			//				.clickCIComponetSpyGlassAndverifyCIComponetSpyGlass(elements);
			.clickCIComponetSpyGlassAndverifyCIcountsWithWarning();

			status="PASS";


		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLIM_V2_Stry000000_IM20")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM20");
		return arrayObject;
	}
}
