package com.punchit.scripts.OOBIncident;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages_OutOfBox.LoginPage;
import pages_OutOfBox.MenuPage;
import testng.SuiteMethods;
import testng.SuiteMethods_ServiceNowFrontEnd;
import utils.DataInputProvider;

public class OOBIncident_Stry000000_TC09 extends SuiteMethods_ServiceNowFrontEnd{

	@Test(dataProvider = "OOBIncident_Stry000000_TC09", groups="OutOfBox")
	public void createIncident(String regUser, String regPwd, String filter){

			snW.launchApp(browserName, true);
			
			String[] expectedValue={"Closed"};

			MenuPage home =	
					new LoginPage()
					.loginAs(regUser, regPwd);
				home.clickClosed().verifycolumnValue("State", expectedValue);
//					.clickFirstIncident()
//					.verifyIncidentFormOpens();
				
//				home.clickLogout();
				
				status = "PASS";


	}



	@DataProvider(name = "OOBIncident_Stry000000_TC09")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OOBIncident_Stry000000_TC09");
		return arrayObject;
	}
}
