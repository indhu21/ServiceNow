package com.punchit.scripts.gileadven;

import java.io.IOException;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_Stry0010709_Tc01 extends SuiteMethods {

	@Test(dataProvider = "Ven_GLOD_Stry0010709_Tc01")
	public void acknowledgingUser(String regUser, String regPwd){

		try{
			snW.launchApp(browserName, true);

			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
				String name = 
						home.getUserName();
				home.clickMyAlertConsole()
					.clickMyAlertslink()
					.verifyFilterCondition(name);
				home.clickLogout();
			
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}
	}

	@DataProvider(name = "Ven_GLOD_Stry0010709_Tc01")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("Ven_GLOD_Stry0010709_Tc01");
		return arrayObject;
	}

}
