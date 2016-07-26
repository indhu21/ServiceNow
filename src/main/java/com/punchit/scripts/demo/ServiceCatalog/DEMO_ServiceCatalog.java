package com.punchit.scripts.demo.ServiceCatalog;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class DEMO_ServiceCatalog extends  SuiteMethods  {

	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "Demo_ServiveCatalog")
	public void createIncident (String regUser, String regPwd, String title, String description
			) throws COSVisitorException, IOException {

		// Prerequisites
		snW = new ServiceNowWrappers(entityId);

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Log in to application
			if (snW.login_DEMO(regUser, regPwd))
				Reporter.reportStep("The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("The login with username:"+ regUser + " is not successful", "FAILURE");

			// Click Catalog	

			snW.enterByIdAndEnter("ServiceCata_FilterNavigator_Id", "Catalog");


			//Click Can We Help You
			snW.switchToMain();
			if (snW.clickByXpath("ServiceCata_HelpLink_Xpath"))
				Reporter.reportStep("The Link - Can We Help You under Catalog menu is clicked successfully","SUCCESS");
			else
				Reporter.reportStep("The Link - Can We Help You under Catalog menu could not be clicked","FAILURE");

			//Click Submit Idea
			snW.switchToMain();
			if (snW.clickByXpath("ServiceCata_SubmitIdea_Xpath"))
				Reporter.reportStep("The Link - Submit Idea is clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Link - Submit Idea could not be clicked","FAILURE");

			//Enter Title , Description and Click Submit
			if (!snW.submitIdea(title, description))
				Reporter.reportStep("Step 5: The Idea  could not be submitted ","FAILURE");
			
			status="PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "Demo_ServiveCatalog")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("Demo_ServiveCatalog");
		return arrayObject;
	}
}