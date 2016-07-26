package com.punchit.scripts.lc;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class LC_CAR1_Stry000000_Tc01 extends SuiteMethods {

	// Create Instance
	@Test(dataProvider = "LC_CAR_Stry000000_Tc01",groups="CMDB")
	public void createIncident(String regUser, String regPwd,String name,
			String CIOwnerGroup, String systemManager, String CIOwnerGroup2
			){

		// Pre-requisities
		try {

			String appName = name+snW.getCurrentTime();

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home =
					new LoginSparcLCPage()
					.loginAs(regUser, regPwd);	

			// Step 2: Verify the Menus
			home.expandCMDBMenu()
//			.verifyExistanceOfCMDBOptions()
			.expandDataCenter()
			.clickPDUAndVerifyNewButtonExists()
			.clickNewButtonforPDUs()							
			.verifyAllMandatoryFieldsforPDUs()
			.enterAllMAndatoryFields(appName,CIOwnerGroup,systemManager)
			.clickSubmit()
			.searchNameAndClick(appName)
			.enterAndSaveCIOwnerGroup(CIOwnerGroup2);

			home.clickSparcLCPageLogout();
     
			new LoginSparcLCPage()
			.loginAs(systemManager, regPwd)
			.expandSPARCMessagesMenu()
			.clickMySPARCMessages()
//			.verifyISRecordDisplayed();
			.clickFirstCraetedMessage(systemManager);

			status = "PASS";

		} finally {
			// close the browser
			//snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_CAR_Stry000000_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_CAR_Stry000000_Tc01");
		return arrayObject;
	}
}
