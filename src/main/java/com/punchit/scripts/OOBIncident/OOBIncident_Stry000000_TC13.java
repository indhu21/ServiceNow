package com.punchit.scripts.OOBIncident;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages_OutOfBox.IncidentsListPage;
import pages_OutOfBox.LoginPage;
import pages_OutOfBox.MenuPage;
import testng.SuiteMethods;
import testng.SuiteMethods_ServiceNowFrontEnd;
import utils.DataInputProvider;

public class OOBIncident_Stry000000_TC13 extends SuiteMethods_ServiceNowFrontEnd{

	@Test(dataProvider = "OOBIncident_Stry000000_TC13", groups="OutOfBox")
	public void createIncident(String regUser, String regPwd, String filter, String workNotes, String ciValue){

		snW.launchApp(browserName, true);

		MenuPage home =	new LoginPage()
				.loginAs(regUser, regPwd);
			IncidentsListPage list = 
					home.clickOpen();

		String CMDBNum = 
				list.getIncTaskNumber();

				list.clickFirstIncident()
				.enterWorkNotes(workNotes)
				.updateIncident(ciValue)
				.clickOpen()
				.searchIncident(CMDBNum)
				.clickFirstIncident()
				.getActivityText(workNotes);
				
//			home.clickLogout();	
				
				status = "PASS";
	}

	@DataProvider(name = "OOBIncident_Stry000000_TC13")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OOBIncident_Stry000000_TC13");
		return arrayObject;
	}
}
