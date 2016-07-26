package com.punchit.scripts.im;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class IM_Stry000000_Tc72 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc72",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd, 
			String CI, String reportCus,
			String assGroup, String shortDec,
			String assTo, String filter1,
			String filter2, String filter3){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
			String[] ele={"Account unlocked", 
					"Cancelled - SR",
					"Cancelled by User",
					"Capacity Management",
					"Data retrieved/restored",
					"DNS restored",
					"Documentation updated"};
			String[] ele1={"Emergency Fix",
					"Passed to External Vendor",
					"Pwd Change", 
					"Software upgrade/install",
					"Training suggested",
					"Training/Kowledge provided",
					"Unable to reproduce"};
			//Step 1A: Navigate to incident and click assign to me 
			IncidentPage incident =
			home.clickCreateNew()
			.populateMandatoryFields(CI, reportCus, assGroup, shortDec)
			.enterAssignedTo(assTo);
			
			String IncNum=incident.getIncidentNumber();
			incident.submitIncident()
			.searchIncident(IncNum)
			.clickFirstIncident()
			.clickResolveIncident()
			.alertAcceptforResolve()
			.isExistResolutionInformation()
			.isExistResolutionCodefield()
			.clickCausingCISpyGlass()
			.selectFilter(filter1, filter2, filter3)
			.clickFirstName()
			.clickResolutioCodeSpyGlass()
			.verifyResolutionCodeSpyGlass(ele, false)
			.verifyResolutionCodeSpyGlass(ele1, true);

			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "IM_Stry000000_Tc72")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc72");
		return arrayObject;
	}
}
