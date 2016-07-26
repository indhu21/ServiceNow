package com.punchit.scripts.OOBChange;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages_OutOfBox.LoginPage;
import testng.SuiteMethods_ServiceNowFrontEnd;
import utils.DataInputProvider;

public class OOBChange_Stry000000_TC06 extends SuiteMethods_ServiceNowFrontEnd{

	@Test(dataProvider = "OOBChange_Stry000000_TC06", groups="OutOfBox")
	public void createIncident(String regUser, String regPwd, String filter, String category, 
			String priority, String risk, String impact,String approval, String type, String state){

		snW.launchApp(browserName, true);
//		snW.launchApp_ServiceNow(browserName, true,entityId);

		new LoginPage()
		.loginAs(regUser, regPwd)
		.enterFilter(filter)
		.clickCreateNew()
		.clickNormalType()
		.verifyChangeRequest(category,priority, risk, impact,approval ,type ,state);

		status = "PASS";
	}


	@DataProvider(name = "OOBChange_Stry000000_TC06")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OOBChange_Stry000000_TC06");
		return arrayObject;
	}
}
