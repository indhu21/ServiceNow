package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM42 extends SuiteMethods {


	@Test(dataProvider = "GLIM_V2_Stry000000_IM42",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd){


		try {

			snW.launchApp(browserName, true);
			
			String[] filter={"Active = false", "Assignment Group = Contact Center"};

			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
			
				home.clickClosed()
					.verifyFilterText(filter)
					.isNewButtonExists()
					.verifyData()
					.isFieldEditable()
					.addPersonalizedListColumn()
					.isDeleteIncidentUsingActionsOnSelectedRows()
					.isFilterEditable();
				home.clickClosedWithoutReport()
					.verifyFilterTextAterReset(filter);
			
			String[] filter1={"All"};
			
				home.expandIncidentMenu()
					.clickAll()
					.verifyFilterText(filter1)
					.isNewButtonExists()
					.isFieldEditable()
					.addPersonalizedListColumn()
					.isFilterEditable();

				home.clickAllWithoutReport()
					.verifyFilterText(filter1)
					.rightClickAndAssignToMe()
					.verifyAssignedToError(regUser.toUpperCase())
					.isDeleteIncidentUsingActionsOnSelectedRows();
			
			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM42")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM42");
		return arrayObject;
	}
}
