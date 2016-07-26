package com.punchit.scripts.demosuite3;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.LoginPage_DEMO;
import testng.SuiteMethods_1;
import utils.DataInputProvider;

public class INC_Stry000000_TC01_a extends SuiteMethods_1{

	@Test(dataProvider = "DEMOINC_Stry000000_TC01_a", groups="OutOfBox")
	public void createIncident(String regUser, String regPwd){

		snW.launchApp(browserName, true);

				new LoginPage_DEMO()
				.loginAs(regUser, regPwd)
				.clickLogout();
				

		status = "PASS";


	}



	@DataProvider(name = "DEMOINC_Stry000000_TC01_a")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("DEMOINC_Stry000000_TC01_a");
		return arrayObject;
	}
}
