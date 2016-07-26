package com.punchit.scripts.gileadodNew;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertPage;
import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;

public class GLOD_STRY0011182_Tc01 extends SuiteMethods {

	@Test(dataProvider="GLOD_STRY0010130_Tc01",groups="OpsDirector")

	public void assignAlert(String regUser, String regPwd, String alertState ,String alertState1 ) {
		try {

			snW.launchApp(browserName, true);	

			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);

			AlertsListPage list = 
					home.clickOpsAlertConsole()
					.verifyData();

			String child =
					list.getParentAlert();

			String parent = 
					list.getParentAlert();
			
			list.clickFirstAlertId(parent);
			
			String State = getDefaultValueByXpath("ALERTRECORD_AlertState_Xpath");
			System.out.println(State);
			
			list.clickByXpath("BackButton_Xpath");

			list.clickNewAlert()
			.rightclickAndLinktoParent(parent, child)
			.verifyChildCountInListView(parent)
			.verifyChildAlert(child, parent)
			.clickFirstAlertId(child);
			
			String State1 = getDefaultValueByXpath("ALERTRECORD_AlertState_Xpath");
			System.out.println(State1);
			
			if(State.equals(State1))
				Reporter.reportStep("The child alert’s state:"+ State1 +" is same as parent alerts state.","SUCCESS");
			else
				Reporter.reportStep("The child alert’s state is not same as parent alerts state.","FAILURE");
			
			home.clickLogout();

			status = "PASS";

		} finally{

			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name="GLOD_STRY0010130_Tc01")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010130_Tc01");
		return arrayObject;
	}	
}
