package com.punchit.scripts.gileadod;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0010133_CI extends SuiteMethods {
	
	
	@Test(dataProvider = "GLOD_STRY0010133_TC01_V2",groups="OpsDirector")
	public void alertSuppressor (String regUser, String regPwd, String scheduleType, String shortDesc,
								 String supresstype, String tablename, String document, String syssch,
								 String name) {
		
		
		try {

			name=name+getCurrentTime();
			
			snW.launchApp(browserName, true);
			
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
			
					home.clickAlertSuppressors()
						.clickSuppressorNewButton()
						.enterAllMandatoryFields(name, shortDesc, scheduleType)
						.selectSuppressTarget(document, tablename)
						.selectScheduleType(scheduleType)
						.enterSchudule(syssch)
						.rightClickAndSave()
						.verifyAlertSuppTarget(tablename);
					home.clickLogout();
					
					
			status = "PASS";			
			
		} finally {
			// close the browser
			snW.quitBrowser();
			}

}

	@DataProvider(name = "GLOD_STRY0010133_TC01_V2")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010133_TC01_V2");
		return arrayObject;
	}
}