package com.punchit.scripts.gileadim_V2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;

public class GLIM_V2_Stry000000_Tc13 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_Tc13",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String masterIncident, 
			String gTrackChange,String causingCI4,String causingCI3,
			String causingCI2,String causingCI1, String CI,
			String Reportingcustomer,String Group,String Description, String Assignto
			,String causingCIComponent, String resolutionCode,String resolutionNotes , String DeviationNumber) {

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


					//			// Take a note of the INC number.
					//			String incNumber = incident.getIncidentNumber();
					//			System.out.println(incNumber);
					//
					//			// Step 8: Enter all Mandatory fields
					//			incident.populateMandatoryFields(CI, Reportingcustomer, Group, Description)
					//	          .enterAssignedTo(Assignto)
					//			.submitIncident();
					//			
					//			home.clickOpen().searchIncident(incNumber).clickFirstIncident();
					//
					//			if(snW.clickByXpath("CREATEINC_WIP_Xpath"))
					//		    	Reporter.reportStep("Work in Progress Button clicked successfully", "SUCCESS");
					//		    else 
					//		    	Reporter.reportStep("Work in Progress Button could not be clicked", "FAILURE");
					//		
					//			
					//			//home.clickWIP();
					//
					//			// Step 2: click on WIP		
					//			// home.clickWIP()
					//			home.clickWIP().searchIncident(incNumber).clickFirstIncident();
					//.clickFirstIncident()
					//					.clickProcess()
					//					.enterandChooseMasterIncident(masterIncident)

					.clickMasterIncidentSpyglass()
					.selectFirstMasterIncident()
					//					.clickMasterIncident()
					.hoverMasterIncident()
					.enterGTrackChange(gTrackChange)
					//					.clickResolutionInformation()
					//					.enterAndChooseCausingCI(causingCI4)
					//					.clickSave()
					//					.clickProcess()
					//					.verifySOXSystemDisabled()

					.VerifySOXIsReadonOnly(causingCI3)
					.isNotSOXSystemChecked()


					.VerifySOXIsReadonOnly(causingCI4)
					.isSOXSystemChecked()
					//					.clickResolutionInformation()
					//					.enterAndChooseCausingCI(causingCI3)
					//					.clickSave()
					//					.clickProcess()
					//					.verifySOXSystemDisabled()
					//					.isSOXSystemChecked()

					//					.clickResolutionInformation()
					//					.enterAndChooseCausingCI(causingCI2)
					//					.clickSave()
					//					.clickProcess()
					//					.verifyGxPSystemDisabled()
					//					.isGXpSystemChecked()

					.VerifyGXPIsReadonOnly(causingCI1)
					.isNotSOXSystemChecked()
					

					.clickProcess()
					.clickUpdateandVerifyDeviationFieldMandatory()
					.clickFirstIncident()
//					.clickResolveIncidentWithAlertAccept()
					

					.VerifyGXPIsReadonOnly(causingCI2)
					.isGXpSystemChecked()
//					.enterDeviationNumber(DeviationNumber)
//					.enterAllFieldsWithResolveButton(causingCI2, causingCIComponent, resolutionCode, resolutionNotes)
					.enterAllFieldsWithResolveButtonWithAlertAccept(causingCI2, causingCIComponent, resolutionCode, resolutionNotes)
//					.alertAcceptforResolve()
					.enterDeviationNumber(DeviationNumber)
					.clickResolveIncident()
					;
					//.enterAllFieldsWithResolveButtonWithDeviationNumber(causingCI2, causingCIComponent, resolutionCode, resolutionNotes, DeviationNumber);
			
			//					.clickResolutionInformation()
			//					.enterAndChooseCausingCI(causingCI1)
			//					.clickSave()
			//					.clickProcess()
			//					.verifyGxPSystemDisabled()
			//					.isGXpSystemChecked();
			//					.VerifyGXPIsReadonOnly(causingCI1)
			//					.isNotSOXSystemChecked();


			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_Tc13")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_Tc13");
		return arrayObject;
	}
}
