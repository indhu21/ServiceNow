package com.punchit.scripts.gileadodNew;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertPage;
import pages.LoginPage;

import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLOD_STRY0010316_Tc01 extends SuiteMethods {

	@Test(dataProvider="GLOD_STRY0010316_Tc01",groups="OpsDirector")

	public void assignAlert(String regUser, String regPwd, String alertState ,String alertState1 ) {
		try {

			snW.launchApp(browserName, true);

			
			AlertPage ap =	new LoginPage()
					.loginAs(regUser, regPwd)
					.clickAlerts()
					.clickNewCI()
					.verifyAlertState(alertState);
//					String alertState =  getDefaultValueByXpath("ALERTRECORD_AlertState_Xpath");
					
					String alertNumber =  getAttributeByXpath("ALERTRECORD_Num_Xpath","value");
					System.out.println(alertNumber);
					ap.clickSubmit()
					.searchAndclickAlert(alertNumber)
					.clickAcknowledge()
					.verifyAlertState(alertState1);
					

			status = "PASS";

		} finally{

			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name="GLOD_STRY0010316_Tc01")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010316_Tc01");
		return arrayObject;
	}	
}
