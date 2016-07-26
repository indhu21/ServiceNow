package com.punchit.scripts.OOBIncident;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages_OutOfBox.LoginPage;
import pages_OutOfBox.MenuPage;
import testng.SuiteMethods;
import testng.SuiteMethods_ServiceNowFrontEnd;
import utils.DataInputProvider;

public class OOBIncident_Stry000000_TC10 extends SuiteMethods_ServiceNowFrontEnd{

	@Test(dataProvider = "OOBIncident_Stry000000_TC10", groups="OutOfBox")
	public void createIncident(String regUser, String regPwd, String filter){

			snW.launchApp(browserName, true);
			
			String[] expectedValue={"System Administrator"};
			
					MenuPage home =
					new LoginPage()
					.loginAs(regUser, regPwd);
				home.clickAssignedToMe()
					.verifycolumnValue("Assigned to", expectedValue);
//					.clickFirstIncident()
//					.verifyIncidentFormOpens();
//				home.clickLogout();
					status = "PASS";
	}



	@DataProvider(name = "OOBIncident_Stry000000_TC10")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OOBIncident_Stry000000_TC10");
		return arrayObject;
	}
}
