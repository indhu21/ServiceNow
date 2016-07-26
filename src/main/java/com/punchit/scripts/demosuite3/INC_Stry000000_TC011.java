package com.punchit.scripts.demosuite3;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentsListPage;
import pages.IncidentsListPage_DEMO;
import pages.LoginPage;
import pages.LoginPage_DEMO;
import pages.MenuPage;
import pages.MenuPage_DEMO;
import testng.SuiteMethods;
import testng.SuiteMethods_1;
import utils.DataInputProvider;

public class INC_Stry000000_TC011 extends SuiteMethods_1{

	@Test(dataProvider = "DEMOINC_Stry000000_TC011", groups="OutOfBox")
	public void createIncident(String regUser, String regPwd, String filter, String workNotes){

		snW.launchApp(browserName, true);

		MenuPage_DEMO home =	new LoginPage_DEMO()
				.loginAs(regUser, regPwd);
			IncidentsListPage_DEMO list = 
					home.clickOpen();

		String CMDBNum = 
				list.getIncTaskNumber();

				list.clickFirstIncident()
				.enterWorkNotes(workNotes)
				.updateIncident()
				.clickOpen()
				.searchIncident(CMDBNum)
				.clickFirstIncident()
				.getActivityText(workNotes);
				
			home.clickLogout();	
				
				status = "PASS";
	}

	@DataProvider(name = "DEMOINC_Stry000000_TC011")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("DEMOINC_Stry000000_TC011");
		return arrayObject;
	}
}
