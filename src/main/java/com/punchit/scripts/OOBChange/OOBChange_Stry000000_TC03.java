package com.punchit.scripts.OOBChange;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages_OutOfBox.LoginPage;
import testng.SuiteMethods;
import testng.SuiteMethods_ServiceNowFrontEnd;
import utils.DataInputProvider;

public class OOBChange_Stry000000_TC03 extends SuiteMethods_ServiceNowFrontEnd{

	@Test(dataProvider = "OOBChange_Stry000000_TC03", groups="OutOfBox")
	public void createIncident(String regUser, String regPwd, String filter, String type
			, String configItem, String assignmentGroup, String assignedTo ,
			String priority, String risk, String impact, String state, String state2){

		//snW.launchApp(browserName, true);
		snW.launchApp_ServiceNow(browserName, true,entityId);

		new LoginPage()
		.loginAs(regUser, regPwd)
		.enterFilter(filter)
		.clickCreateNew()
//		.selectType(type)
		.clickEmergencyType()
		.createChangeRequest(configItem, assignmentGroup, assignedTo, priority, risk, impact)
		.verifyRequestApprovalNotAvailable()
		
		.clickEmergencyCreatedChangeRequestPlan()
//		.verifyState(state)
		.enterConfigItemAssignmentGroupAssignedTo(configItem, assignmentGroup, assignedTo)
		.clickCloseTask()
		.clickEmergencyCreatedChangeRequestPlan()		
		.verifyState(state2)
		.clickback()
		
		
		.clickEmergencyCreatedChangeRequestBuild()
//		.verifyState(state)
		.enterConfigItemAssignmentGroupAssignedTo(configItem, assignmentGroup, assignedTo)
		.clickCloseTask()
		.clickEmergencyCreatedChangeRequestBuild()		
		.verifyState(state2)
		.clickback()
		
		.clickEmergencyCreatedChangeRequestTest()
//		.verifyState(state)
		.enterConfigItemAssignmentGroupAssignedTo(configItem, assignmentGroup, assignedTo)
		.clickCloseTask()
		.clickEmergencyCreatedChangeRequestTest()		
		.verifyState(state2)
		.clickback()
		
		.clickEmergencyCreatedChangeRequestImplementation()
//		.verifyState(state)
		.enterConfigItemAssignmentGroupAssignedTo(configItem, assignmentGroup, assignedTo)
		.clickCloseTask()	
		.clickEmergencyCreatedChangeRequestImplementation()		
		.verifyState(state2)
		;

		status = "PASS";
	}


	@DataProvider(name = "OOBChange_Stry000000_TC03")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OOBChange_Stry000000_TC03");
		return arrayObject;
	}
}
