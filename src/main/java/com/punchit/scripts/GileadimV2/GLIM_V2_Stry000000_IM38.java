package com.punchit.scripts.GileadimV2;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.SPARCPortalPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM38 extends SuiteMethods {

	@Test(dataProvider = "GLIM_V2_Stry000000_IM38",groups="IncidentManagement")
	public void createIncident( String regUser, String regPwd, String filterType, 
								String filterCondition, String filterValue, String filterType1,
								String filterCondition1, String filterValue1, String workNotes){

		try {

			launchApp(browserName, true);

			MenuPage home = 
					new LoginSparcLCPage()
					.loginAs(regUser, regPwd);

				home.clickOpen()
					.clickFunnelWithoutReport()
					.clickANDCondition()
					.addSecondFilterbyEnterandChoose(filterType, filterCondition, filterValue)
					.clickANDCondition()
					.addThirdFilterbyEnterAndChooseWithOutReport(filterType1, filterCondition1, filterValue1)
					.clickRun()
					.clickFirstIncident()
					.enterWorkNotesAndSave(workNotes)
					.getLatestWorkNotes(workNotes)
					.uploadFile("GLIM_V2_Stry000000_IM43");
				home.clickLogout();	

		status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLIM_V2_Stry000000_IM38")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM38");
		return arrayObject;
	}





}



