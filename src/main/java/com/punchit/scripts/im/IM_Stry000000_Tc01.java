package com.punchit.scripts.im;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class IM_Stry000000_Tc01 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "IM_Stry000000_Tc01",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd,
							   String configItem, String repCust, 
							   String asgGroup, String desc,
							   String asgTo, String type,
							   String datetime, String reason, String causingCI,
							   String causingCiComp, String resolutioncode,
							   String resolutionNotes){

		// Pre-requisities
		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = new LoginPage().loginAs(regUser, regPwd);	
			
			
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
		incident.populateMandatoryFields(configItem, repCust, asgGroup, desc)
				.submitIncident()
				.searchIncident(incNumber)
				.clickFirstIncident()
				.isStateOpen()
				.clickNotes()
				.verifyNotesFields()
				.verifyNotesReadOnlyFields()
				.clickProcess()
				.verifyProcessFields()
				.verifyProcessReadOnlyFields()
				.verifyProcessEditableFields()
				.enterAssignedTo(asgTo)
				.clickSave()
				.isStateAssigned()
				.clickWIP()
				.isStateWIP()
				.isExistResolutionInformation()
				.clickResolutionInformation()
				.verifyResolutionInformationEditableFields()
				.clickActivityLog()
				.clickAndisActivityLogEditable()
				.clickOnHold()
				.clickHoldInformationTab()
				.verifyHoldInfoMandatoryFields()
				.enterAndSaveOnHoldInfo(type, datetime, reason)
				.clickSave()
				.isStateOnHold()
				.clickOnResume()
				.clickSave()
				.isStateWIP()
				.clickResolveIncident()
				.alertAcceptforResolve()
				.verifyAllMandatoryFieldsforResoInfor()
				.MandatoryFieldsforResolutionTab(causingCI, causingCiComp,resolutioncode, resolutionNotes)
				.clickSave()
				.isStateResolved()
				.isReopenIncidentDisplayed();
						
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "IM_Stry000000_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("IM_Stry000000_Tc01");
		return arrayObject;
	}
}
