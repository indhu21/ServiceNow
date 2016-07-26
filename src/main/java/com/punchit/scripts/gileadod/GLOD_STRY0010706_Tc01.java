package com.punchit.scripts.gileadod;

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

public class GLOD_STRY0010706_Tc01 extends SuiteMethods {

	@Test(dataProvider = "GLOD_Stry0010706_Tc01",groups="OpsDirector")
	public void acknowledgingUser(String regUser, String regPwd) {

		try {

			snW.launchApp(browserName, true);
			
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
				
			AlertsListPage alp= 
					home.clickOpsAlertConsole()
//						.verifyIsRecordDisplays("Sumerian Capacity Alerts");
						.verifyData();

			String alertId =
						alp.getAlertId();	
			        	 alp.clickNewAlert()
			        		.rightClicktoacknowledge1(alertId);
						home.clickMyAlertConsole()
							.clickMyAlertslink()
							.clickCreatedAlert(alertId)
							.validateAssignedTo("TESM_OPS_DIR_OPERATOR");
						home.clickLogout();
					
					status = "PASS";

		} finally{

			// close the browser
			snW.quitBrowser();	

		}

	}

	@DataProvider(name = "GLOD_Stry0010706_Tc01")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_Stry0010706_Tc01");
		return arrayObject;
	}

}
