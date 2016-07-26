package com.punchit.scripts.OOBChange;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages_OutOfBox.LoginPage;
import pages_OutOfBox.MenuPage;
import testng.SuiteMethods;
import testng.SuiteMethods_ServiceNowFrontEnd;
import utils.DataInputProvider;

public class OOBChange_Stry000000_TC14 extends SuiteMethods_ServiceNowFrontEnd{

	@Test(dataProvider = "OOBChange_Stry000000_TC14", groups="OutOfBox")
	public void createIncident(	String regUser, String regPwd){

		snW.launchApp(browserName, true);
//		snW.launchApp_ServiceNow(browserName, true,entityId);

		String[] expectedValue={"Open", "Work in Progress"};
		String[] expectedValue1={"Closed"};
		
		MenuPage home =
					new LoginPage()
					.loginAs(regUser, regPwd);
				home.selectOpenForChange()
					.verifycolumnValue("State", expectedValue);
				home.selectClosedForChange()
					.verifycolumnValue("State", expectedValue1);
					
				home.clickLogout();

		status = "PASS";


	}



	@DataProvider(name = "OOBChange_Stry000000_TC14")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OOBChange_Stry000000_TC14");
		return arrayObject;
	}
}
