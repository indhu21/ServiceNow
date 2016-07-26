package com.punchit.scripts.gileadven;

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




import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

// for using ATU reporting -- added the listeners

public class Ven_GLOD_Stry0010141_Tc01 extends SuiteMethods {

	@Test(dataProvider = "Ven_GLOD_Stry0010141_Tc01")
	public void testName(String regUser, String regPwd) {

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);	
				home.verifyOpsConsolesMenu()
					.clickOpsAlertConsole()
					.clickNewAlert()
					.addFirstFilterEnter1("Child Count", "greater than", "0")
					.verifyChildCount();
				
				home.clickOpsAlertConsole()
					.clickNewAlert()
					.addFirstFilterEnter1("Child Count", "is", "0")
					.verifyChildCount();
				


			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "Ven_GLOD_Stry0010141_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("Ven_GLOD_Stry0010141_Tc01");
		return arrayObject;
	}



}