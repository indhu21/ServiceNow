package com.punchit.scripts.demosuite3;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.LoginPage_DEMO;
import pages.MenuPage;
import pages.MenuPage_DEMO;
import testng.SuiteMethods;
import testng.SuiteMethods_1;
import utils.DataInputProvider;

public class INC_Stry000000_TC08 extends SuiteMethods_1{

	@Test(dataProvider = "DEMOINC_Stry000000_TC08", groups="OutOfBox")
	public void createIncident(String regUser, String regPwd, String filter){

			snW.launchApp(browserName, true);

			MenuPage_DEMO home =	
					new LoginPage_DEMO()
					.loginAs(regUser, regPwd);
				home.clickClosed()
					.clickFirstIncident()
					.verifyIncidentFormOpens();
				
				home.clickLogout();
				
				status = "PASS";


	}



	@DataProvider(name = "DEMOINC_Stry000000_TC08")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("DEMOINC_Stry000000_TC08");
		return arrayObject;
	}
}
