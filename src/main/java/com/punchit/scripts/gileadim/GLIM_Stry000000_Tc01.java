package com.punchit.scripts.gileadim;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_Stry000000_Tc01 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_Stry000000_Tc01",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
								String configItem, String repCust, 
								String asgGroup, String desc,
								String asgTo, String type,
								String datetime, String reason, String causingCI,
								String causingCiComp, String resolutioncode,
								String resolutionNotes) throws ParseException{

		// Pre-requisities
		try {

			datetime=increamentOneDay();
			
			System.out.println(datetime);
			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home =
					new LoginPage()
					.loginAs(regUser, regPwd);

			// Step 2: Verify the Menus
			home.expandIncidentMenu()
				.verifyExistanceOfIncidentMenus();

			// Step 3: click on create new
			IncidentPage incident =
					home.clickCreateNew()
					.verifyAllReadOnlyFields()
					.verifyAllMandatoryFields()
					.enterConfigurationItem(configItem)
					.verifyAllNonMandatoryFields()
					.verifyTabs();
			// Take a note of the INC number.
			String incNumber = 
					incident.getIncidentNumber();
			//System.out.println(incNumber);

			// Step 8: Enter all Mandatory fields
			incident.createIncidentAndOpen(configItem, repCust, asgGroup, desc, incNumber)
					.isStateOpen()
					.clickNotes()
					.verifyNotesFields()
					.verifyNotesReadOnlyFields()
					.clickProcess()
					.verifyProcessFields()
					.verifyProcessReadOnlyFields()
					.verifyProcessEditableFields()
					.enterAssignedToSave(asgTo)
					.isStateAssigned()
					.clickWIPAndVerify()
					.isExistAndClickResolutionInformation()
					.verifyResolutionInformationEditableFields()
					.verifyResolutionInformationNonManFields()
					.clickAndisActivityLogEditable()
					.clickOnHold()
					.clickHoldInformationTab()
					.verifyHoldInfoMandatoryFields()
					.enterAndSaveOnHoldInfo(type, datetime, reason)
//					.clickSaveButton()
					.isStateOnHold()
					.clickOnResume()
//					.clickSaveButton()
					.isStateWIP()
					.clickResolveIncidentWithAlertAccept()
		//			.clickResolveIncident()
		//			.alertAcceptforResolve()
					.verifyAllMandatoryFieldsforResoInfor()
					.enterAllFieldsWithoutSave(causingCI, causingCiComp,resolutioncode, resolutionNotes)
					.clickSave()
					.isStateResolved()
					.isReopenIncidentDisplayed();

			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLIM_Stry000000_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc01");
		return arrayObject;
	}





}



