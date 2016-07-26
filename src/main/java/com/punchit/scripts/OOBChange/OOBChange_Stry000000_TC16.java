package com.punchit.scripts.OOBChange;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages_OutOfBox.CreateNewPage;
import pages_OutOfBox.LoginPage;
import testng.SuiteMethods;
import testng.SuiteMethods_ServiceNowFrontEnd;
import utils.DataInputProvider;

public class OOBChange_Stry000000_TC16 extends SuiteMethods_ServiceNowFrontEnd{

	@Test(dataProvider = "OOBChange_Stry000000_TC16", groups="OutOfBox")
	public void createIncident(String regUser, String regPwd, String filter, String state,String configItem ,String assignmentGroup ,String assignedTo){

		snW.launchApp(browserName, true);
//		snW.launchApp_ServiceNow(browserName, true,entityId);

		CreateNewPage cnp = new LoginPage()
		.loginAs(regUser, regPwd)
		.enterFilter(filter)
		.clickopen()
		.clickFirstIncident()
		.clickChageRequestNew()
		.verifyState(state)
		.verifylistofChangeTaskState()
		.enterConfigItemAssignmentGroupAssignedTo(configItem, assignmentGroup, assignedTo);
		String Num = cnp.getNumber();
		
		cnp.clickSubmit()
		.clickLinkname(Num)
		.verifyState(state);	
		
		status = "PASS";
	}


	@DataProvider(name = "OOBChange_Stry000000_TC16")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OOBChange_Stry000000_TC16");
		return arrayObject;
	}
}
