package com.punchit.scripts.OOBChange;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages_OutOfBox.LoginPage;
import pages_OutOfBox.MenuPage;
import testng.SuiteMethods;
import testng.SuiteMethods_ServiceNowFrontEnd;
import utils.DataInputProvider;

public class OOBChange_Stry000000_TC19 extends SuiteMethods_ServiceNowFrontEnd{

	@Test(dataProvider = "OOBChange_Stry000000_TC19", groups="OutOfBox")
	public void createIncident(String regUser, String regPwd){

		snW.launchApp(browserName, true);
//		snW.launchApp_ServiceNow(browserName, true,entityId);

			MenuPage home =
					new LoginPage()
					.loginAs(regUser, regPwd);
				home.selectOpenForChange()
					.clickFirstRequest()
					.verifyHistoryCalendarList();
					
//				home.clickLogout();

		status = "PASS";


	}



	@DataProvider(name = "OOBChange_Stry000000_TC19")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OOBChange_Stry000000_TC19");
		return arrayObject;
	}
}
