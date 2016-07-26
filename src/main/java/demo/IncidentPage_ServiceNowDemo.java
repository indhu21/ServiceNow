package demo;

import org.openqa.selenium.remote.RemoteWebDriver;

import pages.IncidentPage;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class IncidentPage_ServiceNowDemo extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	private String incidentNumber;
	public IncidentPage_ServiceNowDemo(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
			Reporter.reportStep("This is not the Incident page", "FAILURE");
		}
	}

	public IncidentPage_ServiceNowDemo switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}	
	public String getIncidentNumber() {

		incidentNumber = getAttributeByXpath("CREATEINC_IncidentNumber_Xpath", "value");

		if(incidentNumber.equals(""))
			Reporter.reportStep("The incident number is blank for newly created incident", "FAILURE");

		return incidentNumber;
	}

	public IncidentsListPage_ServiceNowDemo createIncidentForDemo(String affuser, String cat, String subcat, 
			String impact, String urgency, String asngrp, String shrtdes) {

		if(!enterByXpath("CREATEINC_AffectedUser_Xpath", affuser))
			Reporter.reportStep("The Afftected User could not be entered.", "FAILURE");

		if(!selectByVisibleTextById("CREATEINC_Category_Id", cat))
			Reporter.reportStep("The Category could not be selected.", "FAILURE");

		Wait(5000);

		if(!selectByVisibleTextById("CREATEINC_SubCategory_Id", subcat))
			Reporter.reportStep("The Sub Category could not be selected.", "FAILURE");

		if(!selectByVisibleTextById("CREATEINC_Impact_Id", impact))
			Reporter.reportStep("The Impact could not be selected.", "FAILURE");

		if(!selectByVisibleTextById("CREATEINC_Urgency_Id", urgency))
			Reporter.reportStep("The Urgency could not be selected.", "FAILURE");

		if(!enterAndChoose("CREATEINC_AsgGroup_Xpath", asngrp))
			Reporter.reportStep("The Assignment Group could not be entered.", "FAILURE");

		if(!enterByXpath("CREATEINC_shortDesc_Xpath", shrtdes))
			Reporter.reportStep("The Update button could not be entered", "FAILURE");

		if(clickByXpath("Submit_Incident_Xpath"))
			Reporter.reportStep("All the values are entered in respective fields and submit button is clicked successfully.", "SUCCESS");
		else
			Reporter.reportStep("The Create Incident process failed. Check snapshot", "FAILURE");

		return new IncidentsListPage_ServiceNowDemo(driver);
	}
	public IncidentPage_ServiceNowDemo verifyAllReadOnlyFieldsForDemo(){

		// you need to change the read only fields when the application changes
		String[] ReadFields = {"CREATEINC_IncidentNumber_Xpath","CREATEINC_IncidentPriority_Xpath",
				"CREATEINC_Opened_Xpath","CREATEINC_OpenedBy_Xpath"};
		String[] ReadLabels = {"Number","Priority","Opened","Opened by"};


		// Verify read only
		verifyDisabledFieldsByXpath(ReadFields, ReadLabels);

		return this;

	}	
	public IncidentPage_ServiceNowDemo verifyAllMandatoryFieldsforDemo(){

		String[] ManFields = {"CREATEINC_AffectedUserStar_Xpath",
				"CREATEINC_AsgGroupStar_Xpath",
		"CREATEINC_shortDescStar_Xpath"};
		String[] ManLabels = {"Caller",
				"Assignment Group",
		"Short Description"};

		verifyMandatoryFields(ManFields, ManLabels);

		return this;

	}

	public IncidentPage_ServiceNowDemo verifyAllMandatoryFieldsforDemo1(){

		scrollToElementByXpath("CREATEINC_shortDescStar_Xpath");

		String[] ManFields = {"CREATEINC_shortDescStar_Xpath"};
		String[] ManLabels = {"Short Description"};

		verifyMandatoryFields(ManFields, ManLabels);

		return this;

	}
	public IncidentPage_ServiceNowDemo clickResolveIncidentWithAlertAcceptDemo(String clcode, String clnotes){

		if(clickByXpath("ResolveIncident_Xpath"))
		{
			String text=getTextAndAcceptAlert();
			Wait(5000);
			scrollToElementByXpath("WIP_ResolutionInformation_Xpath");
			Reporter.reportStep("Alert displayed with message - "+text, "SUCCESS");
		}
		else
			Reporter.reportStep("The Resolve Incident button is not available", "WARNING");

		if(!selectByVisibleTextById("CREATEINC_CloseCode_Id", clcode))
			Reporter.reportStep("Close code could not be selected","FAILURE");

		if(enterById("CREATEINC_CloseNotes_Id", clnotes))
			Reporter.reportStep("Close code and Close notes filled according to reference data","SUCCESS");
		else
			Reporter.reportStep("Close notes could not be selected","FAILURE");


		return this;
	}
	public IncidentPage_ServiceNowDemo enterCustomerComms(String CustomerComms) {

		enterByXpath("CREATEINC_CustomerComms_Xpath",CustomerComms);
		if(enterByXpath("CREATEINC_CustomerComms_Xpath",CustomerComms ))
			Reporter.reportStep("The value : "+CustomerComms+" is entered in Customer Comms field successfully", "SUCCESS");
		else	
			Reporter.reportStep("The value : "+CustomerComms+" could not be entered in Customer Comms field", "FAILURE");
		return this;
	}

	public IncidentPage_ServiceNowDemo clickResolveIncident (){

		// Verify the tabs exists
		if(clickByXpath("ResolveIncident_Xpath"))
			Reporter.reportStep("The Resolve Incident button is clicked successfully", "SUCCESS");
		else
			Reporter.reportStep("The Resolve Incident button is not available", "WARNING");

		return this;
	}

}





