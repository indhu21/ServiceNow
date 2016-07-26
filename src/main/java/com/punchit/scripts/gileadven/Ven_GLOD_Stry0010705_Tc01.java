package com.punchit.scripts.gileadven;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.WebElement;
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

public class Ven_GLOD_Stry0010705_Tc01 extends SuiteMethods {

	@Test(dataProvider = "Ven_GLOD_Stry0010705_Tc01",groups="OpsDirector")
	public void acknowledgingUser(String regUser, String regPwd) {

		try {

			snW.launchApp(browserName, true);

			MenuPage home = 
						new LoginPage()
						.loginAs(regUser, regPwd);
			AlertsListPage	list =
					home.clickUserAlertConsole();
//						.verifyIsRecordDisplays("Sumerian Capacity Alerts");
						
			String alertId = 
					list.getAlertId();
			
					list.rightClickAlertAndacknowledge(alertId)
						.checkAllAlertAndVerifyAcknowledge()
						.clickLogout();
					status = "PASS";

		} finally{

			// close the browser
			snW.quitBrowser();	

		}

	}

	@DataProvider(name = "Ven_GLOD_Stry0010705_Tc01")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("Ven_GLOD_Stry0010705_Tc01");
		return arrayObject;
	}

}
