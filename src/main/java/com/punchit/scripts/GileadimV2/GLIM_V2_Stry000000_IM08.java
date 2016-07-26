package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;

public class GLIM_V2_Stry000000_IM08 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_IM08",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String masterIncident, 
			String gTrackChange,String causingCI4,String causingCI3,
			String causingCI2,String causingCI1, String CI,
			String Reportingcustomer,String Group,String Description, String Assignto,
			String causingCIComponent, String resolutionCode,String resolutionNotes ,
			String DeviationNumber, String filterType, String filterCondition,
			String filterValue) {

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = 
					new LoginPage()
			.loginAs(regUser, regPwd);


			// Step 2: click on create new
			IncidentPage incident =
					home.clickCreateNewforFailure();
			String incNumber = incident.getIncidentNumber();
			System.out.println(incNumber);

			incident.createIncidentWithWorkInProcessAndOpenIncident(CI, Reportingcustomer, Group,
					Description, incNumber, Assignto)
					.clickMasterIncidentSpyglasswithReport()
					.selectFirstMasterIncident()
					.hoverMasterIncident()
					.enterGTrackChange(gTrackChange)
					.VerifySOXIsReadonOnly(causingCI3)
					.isNotSOXSystemChecked()
					.VerifySOXIsReadonOnly(causingCI4)
					.isSOXSystemChecked()
					.VerifyGXPIsReadonOnly(causingCI1)
					.isNotSOXSystemChecked()
					.clickProcess()
					.clickUpdateandVerifyDeviationFieldMandatory()
					.clickFirstIncident()
					.VerifyGXPIsReadonOnly(causingCI2)
					.isGXpSystemChecked()
					.enterAllFieldsWithResolveButtonWithAlertAccept(causingCI2, causingCIComponent, resolutionCode, resolutionNotes)
					.enterDeviationNumber(DeviationNumber)
					.clickResolveIncident();
				home.clickCreateNewforFailure();
				String incNumber1 = incident.getIncidentNumber();
				System.out.println(incNumber);
			incident.createIncidentAndOpen(CI, Reportingcustomer, Group, Description, incNumber1)
					.clickIncidentTabEdit()
					.clickANDCondition()
					.addSecondFilterbyEnterandChooseWithReport(filterType, filterCondition, filterValue)
					.clickRunFilter()
					.selectFirstAvailableincident()
					.clickIncidentTabEdit()
					.selectFirstselectedincident();
					

			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM08")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM08");
		return arrayObject;
	}
}
