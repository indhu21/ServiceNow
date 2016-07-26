package com.punchit.scripts.lc;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class LC_GapScript_03 extends SuiteMethods {

	@Test(dataProvider = "LC_GapScript_03",groups="CMDB")
	public void createIncident(	String regUser, String regPwd, String filter,
								String filter1, String assTo,  String state, String workNotes, 
								String workNotes1, String state1){

		try {
			
			launchApp(browserName, true);
			String[] filterText={filter, filter1};
			String[] values={"Number", "State", "Assigned To", "Short Description", "Percent complete", 
							"Certification Schedule", "Certification Instance"};
			MenuPage home = 
				new LoginPage()
					.loginAs(regUser, regPwd);	

				home.clickMyTasks()
					.verifyFilterTextForMyTask(filterText)
					.verifyAndAddValuesToSelect(values)
					.clickFirstLinkTask()
					.getTaskNumber()
					.checkMyTaskFields(assTo, state)
					.enterWorkNotes(workNotes)
					.clickUpdateButton()
					.searchAndClickFirstLinkTask()
					.clickClosedInCompleteAmdVerifyError()
					.enterWorkNotes(workNotes1)
					.clickClosedInComplete()
					.searchAndClickFirstLinkTask()
					.verifyState(state1);
					
				home.clickLogout();
					status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_GapScript_03")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_GapScript_03");
		return arrayObject;
	}
}
