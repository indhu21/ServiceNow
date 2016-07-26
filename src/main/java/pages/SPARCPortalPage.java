package pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class SPARCPortalPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public SPARCPortalPage(RemoteWebDriver driver) {
		this.driver = driver;

		// Check that we're on the right page.
		if (!isExistByXpath("SPARCPORTAL_MyProfile_Xpath")) {
			Reporter.reportStep("This is not the SPARC Portal Page", "FAILURE");
		}			

	}

	public SPARCPortalPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");
		return this;
	}

	public SPARCPortalPage clickMyProfile() {
		if(clickByXpath("SPARCPORTAL_MyProfile_Xpath"))
			Reporter.reportStep("The My Profile is clicked successfully", "SUCCESS");
		else
			Reporter.reportStep("The My Profile could not be clicked", "FAILURE");

		return this;
	}

	public SPARCPortalPage verifyExtensionNumberField(){

		// you need to change the mandatory fields when the application changes
		String[] extensionNumberField = {"SPARCPORTAL_ExtensionNumber_Xpath"};
		String[] desc = {"SPARCPORTAL ExtensionNumber"};

		verifyFieldsExistByXpath(extensionNumberField,desc);

		return this;

	}

	public CreateIncidentPage clickCreateIncident() {

		if(clickByXpath("SPARCPORTAL_CreateNewINC_Xpath"))
			Reporter.reportStep("The Create New Incident is clicked successfully", "SUCCESS");
		else
			Reporter.reportStep("The Create New Incident could not be clicked", "FAILURE");

		return new CreateIncidentPage(driver);
	}

	public MenuPage clickSPARCHomeMenu(){

		switchToDefault();
		if(clickLink("SPARC_INC_HOME_Link"))
			Reporter.reportStep("The SPARC Home Link is clicked successfully", "SUCCESS");
		else
			Reporter.reportStep("The SPARC Home could not been clicked", "FAILURE");

		return new MenuPage(driver);

	}

	public SPARCPortalPage verifyExtensionNumberFieldReadOnly() {

		// you need to change the read only fields when the application changes
		String[] readOnlyFields = { "SPARCPORTAL_ExtensionNumber_Xpath"};
		String[] desc = {"SPARCPORTAL ExtensionNumber"};

		// Verify read only
		verifyDisabledFieldsByXpath(readOnlyFields,desc);

		return this;
	}


	public IneedsomethingPage clickIneedSomething(){

		if(clickByXpath("FSS_MySPARC_INeedSomething_Xpath"))
			Reporter.reportStep("The I need something link is clicked successfully.", "SUCCESS");
		else
			Reporter.reportStep("The I need something link is not clicked, check snapshot.", "FAILURE");

		return new IneedsomethingPage(driver);

	}


	public MyItemsPage clickMyItems(){

		if(clickByXpath("MySPARC_MyItems_Xpath"))
			Reporter.reportStep("The My Items link is clicked successfully.", "SUCCESS");
		else
			Reporter.reportStep("The My Items link is not clicked, check snapshot.", "FAILURE");

		return new MyItemsPage(driver);

	}

	public CreateIncidentPage clickCreateIncidentForNegative() {

		if(!clickByXpath("SPARCPORTAL_CreateNewINC_Xpath"))
			Reporter.reportStep("The Create New Incident could not be clicked", "FAILURE");

		return new CreateIncidentPage(driver);
	}

	public MenuPage clickSPARCHomeMenuForNegative(){

		switchToDefault();
		if(!clickLink("SPARC_INC_HOME_Link"))
			Reporter.reportStep("The SPARC Home could not been clicked", "FAILURE");

		return new MenuPage(driver);

	}

	public CreateIncidentPage verifyAttachmentButtonexistsinNewIncident() {

		if(!clickByXpath("SPARCPORTAL_CreateNewINC_Xpath"))			
			Reporter.reportStep("The Create New Incident could not be clicked", "FAILURE");

		Wait(2000);
		switchToMainFrame();

		if (isExistByXpath("SPARCPORTAL_AddAttachment_Xpath"))
			Reporter.reportStep("The Create New Incident is clicked and the Attachment button is available as expected", "SUCCESS");
		else
			Reporter.reportStep("The Attachment button could not be found", "FAILURE");
		return new CreateIncidentPage(driver);

	}
	public SPARCPortalPage verifyExtensionNumberFieldExistsandReadOnly(){

		switchToMainFrame();
		//		switchToDefault();
		Boolean bReturn = true;

		if(!isExistByXpath("SPARCPORTAL_ExtensionNumber_Xpath"))
			Reporter.reportStep("Extension number does not exists","FAILURE");

		if(verifyAttributeTextByXpath("SPARCPORTAL_ExtensionNumber_Xpath", "readonly", "true")){
		}
		else if(verifyAttributeTextByXpath("SPARCPORTAL_ExtensionNumber_Xpath", "disabled", "true")){
		}
		else{ 
			bReturn = false;
			Reporter.reportStep("Extension number field is editable; hence failed","FAILURE");
		} 

		if(bReturn)
			Reporter.reportStep("Extension number field exists and readonly as expected","SUCCESS");

		return this;

	}

	public MyWorkListPage clickMyWork(){

		if(clickByXpath("Sparc_Mywork_Xpath"))
			Reporter.reportStep("The My Work is clicked successfully.", "SUCCESS");
		else
			Reporter.reportStep("The My Work is not clicked, check snapshot.", "FAILURE");

		return new MyWorkListPage(driver);

	}
	public SPARCPortalPage clickMySPARC(){

		switchToDefault();
		if(clickLink("SPARC_MySPARC_Link"))
			Reporter.reportStep("The My SPARC Link is clicked successfully", "SUCCESS");
		else
			Reporter.reportStep("The My SPARC could not been clicked", "FAILURE");

		return this;

	}
	public SPARCPortalPage enterPreferedLocAndPhone(String location, String phoneNUm) {

		if(!enterAndChoose("SelfServiceView_PreLocation_Xpath", location))
			Reporter.reportStep("The Preferred Location could not be entered, hence failure.","FAILURE");

		if(!enterById("SelfServiceView_PrePhoneNum_Id", phoneNUm))
			Reporter.reportStep("The Preferred Phone Number could not be entered, hence failure.","FAILURE");

		if(clickById("Save_Id"))
			Reporter.reportStep("The Preferred Location and Preferred Phone Number entered and saved successfully.","SUCCESS");
		else
			Reporter.reportStep("The Preferred Phone Number could not be entered, hence failure.","FAILURE");

		return this;
	}
	public SPARCPortalPage verifyPreferedLocAndPhone(String location, String phoneNUm) {

		if(!getAttributeByXpath("SelfServiceView_PreLocation_Xpath","value").equals(location))
			Reporter.reportStep("The Preferred Location could not be matched, hence failure.","FAILURE");

		if(getAttributeById("SelfServiceView_PrePhoneNum_Id","value").equals(phoneNUm))
			Reporter.reportStep("The Preferred Location and Preferred Phone Number matched as expected.","SUCCESS");
		else
			Reporter.reportStep("The Preferred Phone Number could not be matched, hence failure.","FAILURE");

		return this;
	}

		//Added on 13/6/2016
	public CreateIncidentPage clickSomethingIsBroken(){
		
		if(clickByXpath("SpracPortal_Somethingisbroken_Xpath"))
			Reporter.reportStep("The Something is Broken Link is clicked successfully.", "SUCCESS");
		else
			Reporter.reportStep("The Something is Broken Link is not clicked, check snapshot.", "FAILURE");

		return new CreateIncidentPage(driver);

	}
}
