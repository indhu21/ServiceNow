package com.punchit.scripts.OOBChange;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages_OutOfBox.LoginPage;
import pages_OutOfBox.MenuPage;
import testng.SuiteMethods;
import testng.SuiteMethods_ServiceNowFrontEnd;
import utils.DataInputProvider;

public class OOBChange_Stry000000_TC18 extends SuiteMethods_ServiceNowFrontEnd{

	@Test(dataProvider = "OOBChange_Stry000000_TC18", groups="OutOfBox")
	public void createIncident(String regUser, String regPwd, String url){

		snW.launchApp(browserName, true);
//		snW.launchApp_ServiceNow(browserName, true,entityId);

			MenuPage home =
					new LoginPage()
					.loginAs(regUser, regPwd);
				home.selectOpenForChange()
					.clickFirstRequest()
					.verifyUrl(url);
//				home.clickLogout();

		status = "PASS";


	}



	@DataProvider(name = "OOBChange_Stry000000_TC18")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OOBChange_Stry000000_TC18");
		return arrayObject;
	}
}
