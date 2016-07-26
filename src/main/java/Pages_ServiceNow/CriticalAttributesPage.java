package Pages_ServiceNow;

import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class CriticalAttributesPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public CriticalAttributesPage(RemoteWebDriver driver) {
		this.driver = driver;
		switchToMainFrame();		
		// Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not Critical Attributes Page.", "FAILURE");
		}
	}

	public CriticalAttributesPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");

		return this;
	}

	public CriticalAttributesPage verifyOperationalStatusAndOverrideFields(){

		// you need to change the non mandatory fields when the application changes
		String[] Fields = { "CricAttri_OperStatus_Xpath","CricAttri_Override_Xpath"};

		String[] FieldsDesc = {"Operational Status"	,"Override check box"};

		verifyFieldsExistByXpath(Fields, FieldsDesc);

		return this;

	}

	public CriticalAttributesPage enterName(String name) {

		if(enterByXpath("CricAttri_Name_Xpath", name))
			Reporter_ServiceNow.reportStep("The Name: "+name+" is entered in Name field successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Name: "+name+" could not be entered in Name field", "FAILURE");

		return this;
	}

	public CriticalAttributesPage enterAppliesTo(String appliesTo) {

		if(enterByXpath("CricAttri_AppliesTo_Xpath", appliesTo))
			Reporter_ServiceNow.reportStep("The value : "+appliesTo+" is entered in Applies to field successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value : "+appliesTo+" not found / could not be entered in Applies to", "FAILURE");

		return this;
	}

	public CriticalAttributesPage selectOperationalStatus(String operationalStatus) {
		if(selectByVisibleTextByXpath("CricAttri_OperStatus_Xpath", operationalStatus))
			Reporter_ServiceNow.reportStep("The type: "+operationalStatus+" is selected in Operational Status successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The type: "+operationalStatus+" could not be selected in Operational Status", "FAILURE");

		return this;
	}


	public CriticalAttributesPage clickBack() {

		if(clickByXpath("Back_Xpath"))
			Reporter_ServiceNow.reportStep("The Back Button is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Back Button could not found", "FAILURE");

		return this;
	}

	public CriticalAttributesPage clickSubmit() {

		if(clickById("Submit_Id"))
			Reporter_ServiceNow.reportStep("The Submit button is clicked successfully", "SUCCESS");
		else 
			Reporter_ServiceNow.reportStep("The Submit button could not be clicked or not found", "FAILURE");

		return this;
	}

	public CmdbListPage verifyCriticalAttributesdisplayed () {

		if(isExistByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The list of Critical Attributes are displayed as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Critical Attributes could not found", "FAILURE");

		return new CmdbListPage(driver);
	}


	public CriticalAttributesPage verifyErrorCode  () {

		if(getTextByXpath("CricAttri_critAttriEexistAlready_Xpath").contains("This critical attribute exist already") && getTextByXpath("CricAttri_InvalidInsert_Xpath").contains("Invalid insert"))
			Reporter_ServiceNow.reportStep("An error code is generated as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("An error code does not generated", "FAILURE");

		return this;
	}

	public CriticalAttributesPage verifyOverride  () {

		if(getAttributeByXpath("CricAttri_IsOverrideChecked_Xpath", "value").contains("false"))
			Reporter_ServiceNow.reportStep("Override check box is Blank", "SUCCESS");
		else if(getAttributeByXpath("CricAttri_IsOverrideChecked_Xpath", "value").contains("true"))
		{
			clickByXpath("CricAttri_CheckOverride_Xpath");
			Reporter_ServiceNow.reportStep("Override check box is Set as Blank", "SUCCESS");
		}

		return this;
	}


	public CriticalAttributesPage verifyChangeReferenceRequired  () {

		if(getAttributeByXpath("CricAttri_IsChangeReferenceRequiredChecked_Xpath", "value").contains("false"))
			Reporter_ServiceNow.reportStep("Change reference Required check box is Blank", "SUCCESS");
		else if(getAttributeByXpath("CricAttri_IsChangeReferenceRequiredChecked_Xpath", "value").contains("true"))
		{
			clickByXpath("CricAttri_CheckOverride_Xpath");
			Reporter_ServiceNow.reportStep("Change reference Required check box is Set as Blank", "SUCCESS");
		}


		return this;
	}

	public CriticalAttributesPage selectEnvironment(String environment) {
		if(selectByVisibleTextByXpath("CricAttri_environment_Xpath", environment))
			Reporter_ServiceNow.reportStep("The type: "+environment+" is selected in Environment successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The type: "+environment+" could not be selected in Environment", "FAILURE");

		return this;
	}

	public CriticalAttributesPage verifyAllFields(){

		// you need to change the non mandatory fields when the application changes
		String[] Fields = { "CricAttri_Name_Xpath","CricAttri_AppliesTo_Xpath","CricAttri_ChangeReferenceRequired_Xpath","CricAttri_OperStatus_Xpath","CricAttri_environment_Xpath","CricAttri_Override_Xpath"};

		String[] FieldsDesc = {"Name","Applies To","Change Reference Required","Operational Status","Environment","Override check box"};

		verifyFieldsExistByXpath(Fields, FieldsDesc);

		return this;

	}

	public CriticalAttributesPage clickBackANdverifyCriticalAttributesdisplayed() {

		if(!clickByXpath("Back_Xpath"))
			Reporter_ServiceNow.reportStep("The Back Button could not found", "FAILURE");

		if(isExistByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Back Button is clicked and The list of Critical Attributes are displayed as expected", "SUCCESS");
		else 
			Reporter_ServiceNow.reportStep("The Submit button could not be clicked or not found", "FAILURE");



		return this;
	}
	public CriticalAttributesPage verifyNewButton () {


		if(clickById("New_Button"))
			Reporter_ServiceNow.reportStep("The New button is clicked and New Critical Attribute form is opened as expected", "SUCCESS");				
		else
			Reporter_ServiceNow.reportStep("The New button could not been found or clicked", "FAILURE");

		return this;
	}
	public CriticalAttributesPage enterNameAppliesCRROpStatusEnviOR(String name, String appliesTo, String operationalStatus, String environment) {

		if(!enterByXpath("CricAttri_Name_Xpath", name))
			Reporter_ServiceNow.reportStep("The Name: "+name+" could not be entered in Name field", "FAILURE");
		if(!enterByXpath("CricAttri_AppliesTo_Xpath", appliesTo))
			Reporter_ServiceNow.reportStep("The value : "+appliesTo+" not found / could not be entered in Applies to", "FAILURE");
		if(!selectByVisibleTextByXpath("CricAttri_OperStatus_Xpath", operationalStatus))
			Reporter_ServiceNow.reportStep("The type: "+operationalStatus+" could not be selected in Operational Status", "FAILURE");
		if(selectByVisibleTextByXpath("CricAttri_environment_Xpath", environment))
			Reporter_ServiceNow.reportStep("The Name: "+name+", Applies To: "+appliesTo+", Operational Status: "+operationalStatus+", Environment: "+environment+" are selected as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The type: "+environment+" could not be selected in Environment", "FAILURE");

		return this;
	}
	public CriticalAttributesPage clickSubmitAndVerifyErrorCode() {

		if(!clickById("Submit_Id"))
			Reporter_ServiceNow.reportStep("The Submit button could not be clicked or not found", "FAILURE");

		if(getTextByXpath("CricAttri_critAttriEexistAlready_Xpath").contains("This critical attribute exist already") && getTextByXpath("CricAttri_InvalidInsert_Xpath").contains("Invalid insert"))
			Reporter_ServiceNow.reportStep("An error code is generated on clicking submit as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("An error code does not generated", "FAILURE");
		return this;
	}

	public CriticalAttributesPage verifyErrorMess()

	{
		String name=getAttributeByXpath("CricAttri_Name_Xpath", "value");
		String appliesTo=getTextByXpath("CricAttri_AppliesTo_Xpath");
		String operationalStatus=getDefaultValueByXpath("CricAttri_OperStatus_Xpath");
		String environment=getDefaultValueByXpath("CricAttri_environment_Xpath");
		String changeReff=getAttributeByXpath("CricAttri_IsChangeReferenceRequiredChecked_Xpath", "value");
		//		System.out.println(changeReff);
		String override=getAttributeByXpath("CricAttri_IsOverrideChecked_Xpath", "value");
		//		System.out.println(overide);
		Reporter_ServiceNow.reportStep("All the Field information Name: "+name+", AppliesTo: "+appliesTo+", Change reference Required value is: "+changeReff+", Operational Status: "+operationalStatus+", Environment: "+environment+", Override value is: "+override+" are noted", "SUCCESS");
		clickBack()
		.verifyNewButton()
		.enterNameAppliesCRROpStatusEnviOR(name, appliesTo, operationalStatus, environment);
		if(changeReff.equals("true")) {
			clickByXpath("CricAttri_ChangeReferenceRequired_Xpath");
			Reporter_ServiceNow.reportStep("Change Reference Required check box is checked", "SUCCESS");
		}
		else
			Reporter_ServiceNow.reportStep("Change Reference Required check box is set blank", "SUCCESS");

		if(override.equals("true")){
			clickByXpath("CricAttri_CheckOverride_Xpath");
			Reporter_ServiceNow.reportStep("Override check box is checked", "SUCCESS");
			}
		else
			Reporter_ServiceNow.reportStep("Override check box is set blank", "SUCCESS");
	
		clickSubmitAndVerifyErrorCode();


	return this;
}


}
