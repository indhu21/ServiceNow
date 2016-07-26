package com.punchit.scripts.OOBIncident;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages_OutOfBox.LoginPage;
import testng.SuiteMethods_ServiceNowFrontEnd;
import utils.DataInputProvider;

public class OOBIncident_Stry000000_TC01 extends SuiteMethods_ServiceNowFrontEnd{

	@Test(dataProvider = "OOBIncident_Stry000000_TC01", groups="OutOfBox")
	public void createIncident(String regUser, String regPwd){

		snW.launchApp(browserName, true);

				new LoginPage()
				.enterUsernameWithReport(regUser)
				.enterPasswordenterUsernameWithReport(regPwd)
				.clickLogin();
//				.loginAs(regUser, regPwd)
//				.clickLogout();
				

		status = "PASS";


	}



	@DataProvider(name = "OOBIncident_Stry000000_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OOBIncident_Stry000000_TC01");
		return arrayObject;
	}
}
