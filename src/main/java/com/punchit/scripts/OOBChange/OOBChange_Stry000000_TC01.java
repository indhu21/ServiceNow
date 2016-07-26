package com.punchit.scripts.OOBChange;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages_OutOfBox.LoginPage;
import pages_OutOfBox.MenuPage;
import testng.SuiteMethods_ServiceNowFrontEnd;
import utils.DataInputProvider;

public class OOBChange_Stry000000_TC01 extends SuiteMethods_ServiceNowFrontEnd{

	@Test(dataProvider = "OOBChange_Stry000000_TC01", groups="OutOfBox")
	public void createIncident(String regUser, String regPwd, String filter, String type
			, String configItem, String assignmentGroup, String assignedTo ,
			String priority, String risk, String impact, String state, String state2){

		snW.launchApp(browserName, true);
//		snW.launchApp_ServiceNow(browserName, true,entityId);

		MenuPage home = 
				new LoginPage()
				.loginAs(regUser, regPwd);
			home.selectCreateNewForChange()
				.clickNormalType()
//				.selectType(type)
				.createChangeRequest(configItem, assignmentGroup, assignedTo, priority, risk, impact)
//				.rightClickonHeader()
				.verifyRequestApprovalNotAvailable()
				.clickCreatedChangeRequest()
				.verifyState(state)
				.enterConfigItemAssignmentGroupAssignedTo(configItem, assignmentGroup, assignedTo)
				.clickCloseTask()
				.clickCreatedChangeRequest()		
				.verifyState(state2);

		status = "PASS";
	}


	@DataProvider(name = "OOBChange_Stry000000_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OOBChange_Stry000000_TC01");
		return arrayObject;
	}
}
