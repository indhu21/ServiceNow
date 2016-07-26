package com.punchit.scripts.gileadodNew;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CIScopePage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLOD_STRY0010105_Tc01 extends SuiteMethods {

	
	@Test(dataProvider = "GLOD_STRY0010105_Tc01",groups="OpsDirector")
	public void createCIScope(	String regUser, String regPwd, String name, String shortDescription,
								String filter, String owningGroup, String f1Section){

	
		try {
			
			snW.launchApp(browserName, true);
			String[] expectedValues={"Windows Server", "Linux Server"};
			String[] expectedValues1={"Windows Server"};
			
			
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
		CIScopePage	cipage =
				home.clickCIScope()
					.enterAllFields(name, shortDescription, filter, owningGroup);
			 String cinum = 
			  cipage.getCINumber();
			  cipage.selectCIClassAndClickUpdate(f1Section)
					.verifyValues(expectedValues)
					.addNewFilterinAlertUsingSelect("Class", "is", "Windows Server")
					.clickUpdate()
					.verifyValues(expectedValues1);
				home.clickCIScopes()
					.searchAndclickScopeNumber(cinum)
					.verifyValues(expectedValues1);
				home.clickLogout();

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLOD_STRY0010105_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010105_Tc01");
		return arrayObject;
	}
}
