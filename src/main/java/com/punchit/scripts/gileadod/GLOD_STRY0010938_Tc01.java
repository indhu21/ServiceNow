package com.punchit.scripts.gileadod;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.AlertPage;
import pages.AlertsEnrichersListPage;
import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;



import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

// for using ATU reporting -- added the listeners

public class GLOD_STRY0010938_Tc01 extends SuiteMethods {

	@Test(dataProvider = "GLOD_Stry0010938_Tc01")
	public void testName(	String devRegUser, String devRegPwd, String label, 
							String order, String decoratorType, String recurBehav,
							String colletionType, String dotwalk_Expre, String oprRegUser, String oprRegPwd, String sceData) {

		try {

			String labelTm=label+snW.getCurrentTime();
			
			snW.launchApp(browserName, true);
			
			MenuPage home = 
					new LoginPage()
					.loginAs(devRegUser, devRegPwd);
			
				home.clickAdminAlertEnrichers()
					.clickEnrichersNewButton()
					.enterEnricherFormWithCollection(labelTm, order, decoratorType, recurBehav, colletionType, dotwalk_Expre)
					.clickSubmitButton();
//				home.clickLogout();
//					
//					new LoginPage()
//					.loginAs(oprRegUser, oprRegPwd)
				home.playScenarios(sceData)
					.clickOpsAlertConsole()
					.clickNewAlert()
					.sortAlertTable("Last Occurrence")
					.clickTopMostAlert()
					.verifyAlertDecorator(labelTm);
					home.clickLogout();

					status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLOD_Stry0010938_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("GLOD_Stry0010938_Tc01");
		return arrayObject;
	}



}