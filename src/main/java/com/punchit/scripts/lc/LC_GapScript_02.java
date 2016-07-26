package com.punchit.scripts.lc;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class LC_GapScript_02 extends SuiteMethods {

	@Test(dataProvider = "LC_GapScript_02",groups="CMDB")
	public void createIncident(	String regUser, String regPwd, String displayValue1,
								String displayValue2,  String certificateValue1,
								String certificateValue2, String assType, String filter,
								String user, String run, String name, String days){

		try {

			String appName = name+snW.getCurrentTime();
			
			String[] values1={displayValue1, displayValue2};
			String[] values2={certificateValue1, certificateValue2};
			
			launchApp(browserName, true);

			MenuPage home = 
				new LoginPage()
					.loginAs(regUser, regPwd);	


				home.clickScheduleDefinitions()
					.clickNewButtonforCertificationSchedules()
					.clickDisplayFieldUnlock()
					.selectValuesFromDisplayFields(values1)
					.clickDisplayFieldlock()
					.clickCertificationFieldUnlock()
					.selectValuesFromCertificationFields(values2)
					.clickCertificationFieldlock()
					.selectAssignmentType(assType)
					.enterAndChooseFilter(filter)
					.enterAndChooseUser(user)
					.enterName(appName)
					.selectRunFiled(run)
					.enterDaysToComplete(days)
					.rightClickAndSave();
					
				home.clickLogout();
					status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_GapScript_02")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_GapScript_02");
		return arrayObject;
	}
}
