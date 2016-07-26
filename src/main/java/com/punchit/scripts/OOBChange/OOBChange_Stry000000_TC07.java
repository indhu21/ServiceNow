package com.punchit.scripts.OOBChange;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages_OutOfBox.LoginPage;
import testng.SuiteMethods_ServiceNowFrontEnd;
import utils.DataInputProvider;

public class OOBChange_Stry000000_TC07 extends SuiteMethods_ServiceNowFrontEnd{

	@Test(dataProvider = "OOBChange_Stry000000_TC07", groups="OutOfBox")
	public void createIncident(String regUser, String regPwd, String filter, 
			String configItem, String assignmentGroup, String assignedTo ,
			String priority, String risk, String impact,String error,
			String startDate,String endDate,String endDate2,String error2, String message){

		snW.launchApp(browserName, true);
//		snW.launchApp_ServiceNow(browserName, true,entityId);

		new LoginPage()
		.loginAs(regUser, regPwd)
		.enterFilter(filter)
		.clickCreateNew()
		.clickNormalType()
		.createChangeRequest(configItem, assignmentGroup, assignedTo, priority, risk, impact)
		.clickCheckConflicts()
		.verifyConflictError(error)
		.enterplannedStartDate(startDate)
		.enterplannedEndDate(endDate)
		.clickCheckConflicts()
		.verifyConflictError2(error2)
		.enterplannedEndDate(endDate2)
		.clickCheckConflicts()
		.verifyConflictMessage(message);

		status = "PASS";
	}


	@DataProvider(name = "OOBChange_Stry000000_TC07")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OOBChange_Stry000000_TC07");
		return arrayObject;
	}
}
