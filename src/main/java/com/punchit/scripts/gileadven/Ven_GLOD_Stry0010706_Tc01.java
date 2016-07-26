package com.punchit.scripts.gileadven;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;



import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_Stry0010706_Tc01 extends SuiteMethods {

	@Test(dataProvider = "Ven_GLOD_Stry0010706_Tc01",groups="OpsDirector")
	public void acknowledgingUser(String regUser, String regPwd) {

		try {

			snW.launchApp(browserName, true);
			
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
				
			AlertsListPage alp= 
					home.clickOpsAlertConsole()
						.verifyIsRecordDisplays("Sumerian Capacity Alerts");

			String alertId =
					alp.getAlertId();	
			
					alp.rightClicktoacknowledge(alertId);
					home.clickMyAlertConsole()
						.clickMyAlertslink()
						.clickCreatedAlert(alertId)
						.validateAssignedTo("Test.OPD Operator1");
					home.clickLogout();
					
					status = "PASS";

		} finally{

			// close the browser
			snW.quitBrowser();	

		}

	}

	@DataProvider(name = "Ven_GLOD_Stry0010706_Tc01")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("Ven_GLOD_Stry0010706_Tc01");
		return arrayObject;
	}

}
