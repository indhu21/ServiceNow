package com.punchit.scripts.gileadod;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

public class GLOD_STRY0010136_TC01_V1 extends SuiteMethods {
	
	
	@Test(dataProvider = "GLOD_STRY0010136_TC01_V1",groups="OpsDirector")
	public void alertSuppressor (String regUser, String regPwd, String scheduleType, String shortDesc, 
								 String tablename, String document, String timeStart, String timeEnd, String name) {
		
		try {

			name=name+getCurrentTime();
			snW.launchApp(browserName, true);
			
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);	
			
					home.clickAlertSuppressors()
						.clickSuppressorNewButton()
						.enterNameAndShortDesc(name, shortDesc)
						.selectSuppressTargets(document, tablename)
						.selectScheduleType(scheduleType)
						.enterStartAndStopTime(timeStart, timeEnd)
						.clickSubmitButton()
						.openAlertSuppress(name)
						.verifyAlertSuppDatas(name, tablename, shortDesc, scheduleType, timeStart, timeEnd);
						
					home.clickLogout();

				status = "PASS";


		}
		finally {
			// close the browser
			snW.quitBrowser();
		}
		}
			@DataProvider(name = "GLOD_STRY0010136_TC01_V1")
			public Object[][] fetchData() throws IOException {
				Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010136_TC01_V1");
				return arrayObject;
			}
		}




