package com.punchit.scripts.OOBChange;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages_OutOfBox.LoginPage;
import testng.SuiteMethods;
import testng.SuiteMethods_ServiceNowFrontEnd;
import utils.DataInputProvider;

public class OOBChange_Stry000000_TC15 extends SuiteMethods_ServiceNowFrontEnd{

	@Test(dataProvider = "OOBChange_Stry000000_TC15", groups="OutOfBox")
	public void createIncident(String regUser, String regPwd, String filter){

		snW.launchApp(browserName, true);
//		snW.launchApp_ServiceNow(browserName, true,entityId);

		new LoginPage()
		.loginAs(regUser, regPwd)
		.enterFilter(filter)
		.clickopen()
		.clickFirstIncident()
		.uploadFile("OOBChange_Stry000000_TC15")
		.RemoveAttachment();
		status = "PASS";
	}


	@DataProvider(name = "OOBChange_Stry000000_TC15")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OOBChange_Stry000000_TC15");
		return arrayObject;
	}
}
