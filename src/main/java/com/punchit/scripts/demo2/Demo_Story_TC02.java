package com.punchit.scripts.demo2;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.LoginPage_knowlwge16;
import pages.MenuPage;
import pages.MenuPage_knowlwge16;
import testng.SuiteMethods;
import testng.SuiteMethods_demo1;
import utils.DataInputProvider;

public class Demo_Story_TC02 extends SuiteMethods{
	
	
	@Test(dataProvider = "Demo_Story_TC02",groups="DemoIncident")
	public void incidentAssignment (String regUser, String regPwd, String ciName,
									String operationalstatus1, String operationalstatus2) {

		try {
			launchApp(browserName, true);

			MenuPage_knowlwge16 home = 
						new LoginPage_knowlwge16()
						.loginAsForDemo(regUser, regPwd);
					
					home.clickApplication()
						.searchandClickFirstApplication(ciName)
						.verifyOperationalstatus(operationalstatus1)
						.selectOperationalstatus(operationalstatus2)
						.rightClickAndSave()
						.verifyOperationalstatus(operationalstatus2);
					
					home.clickLogoutdemo();
			

	} finally {
//		snW.logout();
		quitBrowser();
		}

}

@DataProvider(name = "Demo_Story_TC02")
public Object[][] fetchData() throws IOException {
	Object[][] arrayObject = DataInputProvider.getSheet("Demo_Story_TC02");
	return arrayObject;
}

}