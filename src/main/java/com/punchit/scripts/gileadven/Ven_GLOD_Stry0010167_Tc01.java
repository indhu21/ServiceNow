package com.punchit.scripts.gileadven;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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

// for using ATU reporting -- added the listeners

public class Ven_GLOD_Stry0010167_Tc01 extends SuiteMethods {

	@Test(dataProvider = "Ven_GLOD_Stry0010167_Tc01")
	public void testName(	String regUser, String regPwd, String scenario, String scheduleType,
							String shortDesc, String startHr, String startMn, String startSc, 
							String endHr, String endMn,String endSc) {

		try {
			
			
			snW.launchApp(browserName, true);
				MenuPage home = 
						new LoginPage()
						.loginAs(regUser, regPwd);
						
			AlertsListPage list = 
					home.clickOpsAlertConsole()
						.verifyIsRecordDisplays(scenario);
				
				String alertId = 
						list.getAlertId();
			
				String alertprofile = 
						list.getColumnValue("Alert Profile");
				
				String alertprofilelatest = 
						alertprofile+list.getCurrentTime();
					
				list.rightclickAlert(alertId)
						.suppressAlertUsingProfile(alertId)
						.veriyfSuppressProfileName(alertprofile)
						.enterAllMandatoryFields(alertprofilelatest, shortDesc, scheduleType)
						.enterStartandEndTime(startHr, startMn, startSc, endHr, endMn, endSc)
						.clickSubmitButton(alertprofilelatest);
					home.clickAlertSuppressors()
						.verifyCreatedAlertSuppress(alertprofilelatest);
			
					home.clickLogout();

						
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "Ven_GLOD_Stry0010167_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("Ven_GLOD_Stry0010167_Tc01");
		return arrayObject;
	}

	

}