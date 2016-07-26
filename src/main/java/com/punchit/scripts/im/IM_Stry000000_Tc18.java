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

public class IM_Stry000000_Tc18 extends SuiteMethods{
	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "IM_Stry000000_Tc18",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
							   String configItem, String addCIDescription) throws InterruptedException{

		// Pre-requisities
		snW = new ServiceNowWrappers(entityId);

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);
		
			IncidentPage incident21 = home.clickCreateNew();
			
			
			// Step 3: Type Missing CI into the field
			incident21.enterConfigurationItemForSuccess(configItem).Wait(2000);;
			
			snW.getDriver().switchTo().activeElement();

	
			if(!snW.enterById("Incident_CreateNew_AddCIDescription_Id", addCIDescription))
				Reporter.reportStep("CI Description not added", "FAILURE");
			Thread.sleep(2000);
			snW.clickById("Ok_Id");
			
			// Go to CI Component field and click on the spyglass
			incident21.clickCIComponentLookUp().enterAndSearch();
			
			status = "PASS";
		}
		finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "IM_Stry000000_Tc18")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc18");
		return arrayObject;
	}
}
