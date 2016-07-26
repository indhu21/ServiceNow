package com.punchit.scripts.K16Demo;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.LoginPage_knowlwge16;
import pages.MenuPage;
import pages.MenuPage_knowlwge16;
import testng.SuiteMethods;
import testng.SuiteMethods_ServiceNowFrontEnd;
import utils_DataDeletion.Delete;
import utils_DataDeletion.PrepareDeleteQuery;
import utils_DataInputProvideMySQL.DataInputProvider;
import utils_DataInputProvideMySQL.QueryDB;


public class K16Demo_CMDB_TC01 extends SuiteMethods_ServiceNowFrontEnd{


	@Test(dataProvider = "K16Demo_CMDB_TC01",groups="DemoCMDB")
	public void incidentAssignment (String User_id, String Password, String CI_Name,
			String operationalstatus1, String operationalstatus2) throws Exception {

		try {
			snW.launchApp(browserName, true);

			MenuPage_knowlwge16 home = 
					new LoginPage_knowlwge16()
			.loginAsForDemo(User_id, Password);

			home.clickApplication()
			.searchandClickFirstApplication(CI_Name)
			.verifyOperationalstatus(operationalstatus1)
			.selectOperationalstatus(operationalstatus2)
			.clickUpdate()
			.verifyApplicationTableColumnValue(operationalstatus2);

			home.clickLogoutdemo();


			status="PASS";

		} finally {
			//		snW.logout();
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "K16Demo_CMDB_TC01")
	public Object[][] fetchData() throws IOException {
		String[] Parameters={"User_id","Password","CI Name","Operational Status","Operational Status Changed"};
		Object[][] arrayObject = DataInputProvider.getDataValues("K16Demo_CMDB_TC01",Parameters);
		return arrayObject;
	}

}