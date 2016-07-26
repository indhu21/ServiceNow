package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM19 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_IM19",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
			String configItem1, String configItem2, String configItem3, String configItem4,
			String configItem5) throws InterruptedException{

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);

			// Step 2: click on create new
			IncidentPage incident = home.clickCreateNew()

					// Take a note of the INC number.
					//			String incNumber = incident.getIncidentNumber();
					//			System.out.println(incNumber);

					// Step 3: Type CI 1 into the field
					.enterConfigurationItemForSuccess(configItem1)

					// Able to select the CI with a business class of Business service
					//			incident.verifyBusinessServiceEditable().verifyBusinessServiceContent(businessService1);

					// Go to CI Component field and click on the spyglass
					//			incident.clickCIComponentLookUp().verifyCIComponentLookUpValues();
					//.clickCIComponentLookUpandVerifyCIComponentLookUpValues();
					.clickCIComponetSpyGlassAndverifyCIcounts1();

			incident.enterConfigurationItemForSuccess(configItem2)
			//			.clickCIComponentLookUpandVerifyCIComponentApplicationLookUpValues();
			.clickCIComponetSpyGlassAndverifyCIcountsWithWarning();

			incident.enterConfigurationItemForSuccess(configItem3)
			//			.clickCIComponentLookUp()
			//			.verifyCIComponentServerLookUpValues()
			//			.clickCIComponentLookUpandVerifyCIComponentServerLookUpValues();
			.clickCIComponetSpyGlassAndverifyCIcountsWithWarning();

			incident.enterConfigurationItemForSuccess(configItem4)
			//			.clickConfigurationItemSpyGlass()
			//			.selectFilter(filter1, filter2, filter3)
			//			.clickFirstCIClass()
			//			.clickCIComponetSpyGlass()
			//			.verifyCIComponetSpyGlass(elements);
			//			.clickCIComponetSpyGlassAndverifyCIComponetSpyGlass(elements);
			.clickCIComponetSpyGlassAndverifyCIcountsWithWarning();

			incident.enterConfigurationItemForSuccess(configItem5)
			//						.clickConfigurationItemSpyGlass()
			//						.selectFilter(filter1, filter2, filter3)
			//						.clickFirstCIClass()
			//						.clickCIComponetSpyGlass()
			//						.verifyCIComponetSpyGlass(elements);
			//			.clickCIComponetSpyGlassAndverifyCIComponetSpyGlass(elements);
			.clickCIComponetSpyGlassAndverifyCIcountsWithWarning();


			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}




	@DataProvider(name = "GLIM_V2_Stry000000_IM19")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM19");
		return arrayObject;
	}
}
