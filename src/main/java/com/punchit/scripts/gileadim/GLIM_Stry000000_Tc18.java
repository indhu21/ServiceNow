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
import wrapper.ServiceNowWrappers;

public class GLIM_Stry000000_Tc18 extends SuiteMethods{
	
	@Test(dataProvider = "GLIM_Stry000000_Tc18",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
			String configItem, String addCIDescription) throws InterruptedException{

			try {

			String[] elements = {"Account locked",
					"Automated Job Failure",
					"Connectivity", 
					"Data Issue", 
					"Disk", 
					"Error message",
					"Integration Issue",
					"IP Address",
					"Login failure",
					"Memory",
					"Not responding/Frozen",
					"Outage",
					"Performance degradation",
					"Peripherals",
					"Power", 
					"Security breach", 
					"Stolen/Lost",
					"Storage",
					"UI issue",
			"Virus"};
			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);


			home.clickCreateNew()
			//						.clickConfigurationItemSpyGlass()
			//						.clickMissingCI()
			//						.enterAddCIDescription(addCIDescription)
			//						.clickOkAddCIDesc()
//			.clickConfigurationItemSpyGlassandEnterAddCIDescription(addCIDescription)
			
			.clickConfigurationItemSpyGlassandClickMissingCI()
			.addAddCIDescription(addCIDescription)
			
			
			
			//						.clickCIComponetSpyGlass()
			//						.verifyCIComponetSpyGlass(elements)
//			.clickCIComponetSpyGlassAndverifyCIComponetSpyGlass(elements);
			.clickCIComponetSpyGlassAndverifyCIcounts();
			//			// Step 3: Type Missing CI into the field
			//			incident.enterConfigurationItemForSuccess(configItem).Wait(2000);;
			//			
			//			snW.getDriver().switchTo().activeElement();
			//
			//	
			//			if(!snW.enterById("Incident_CreateNew_AddCIDescription_Id", addCIDescription))
			//				Reporter.reportStep("CI Description not added", "FAILURE");
			//			Thread.sleep(2000);
			//			snW.clickById("Ok_Id");
			//			
			//			// Go to CI Component field and click on the spyglass
			//			incident.clickCIComponentLookUp().enterAndSearch();

			status = "PASS";
		}
		finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLIM_Stry000000_Tc18")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc18");
		return arrayObject;
	}
}
