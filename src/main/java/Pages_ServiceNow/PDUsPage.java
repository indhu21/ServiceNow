package Pages_ServiceNow;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class PDUsPage extends ServiceNowWrappers{


	private final RemoteWebDriver driver;
	private String CMDBTaskNumber;

	public PDUsPage(RemoteWebDriver driver) {
		this.driver = driver;
		switchToMainFrame();

		//Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not the PDUs Page", "FAILURE");
		}

	}
	public PDUsPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");
		return this;
	}

	public PDUsPage verifyAllFieldsforPDUs(){

		// you need to change the non mandatory fields when the application changes
		String[] Fields = { "PDUs_Name_Xpath", 
							"PDUs_Manufacturer_Xpath",
							"PDUs_ModelId_Xpath", 
							"PDUs_SerialNumber_Xpath",
							"PDUs_Location_Xpath", 
							"PDUs_IPAddress_Xpath",
							"PDUs_InputPower_Xpath", 
							"PDUs_GoLiveDate_Xpath", 
							"PDUs_ShortDescription_Xpath",
							"PDUs_OperationalStatus_Xpath",
							"PDUs_OwnerGroup_Xpath",
							"PDUs_SystemManager_Xpath",
							"PDUs_SystemManagerMan_Xpath"};

		String[] FieldsDesc = {"Name",
				"Manufacturer",
				"Model Id",
				"Serial Number",
				"Location",
				"IP Address",
				"Input Power",
				"Go Live Date",
				"Short Description",
				"Operational Status",
				"CI Owner Group",
				"System Manager",
				"Secondary System Manager"
		};
		verifyFieldsExistByXpath(Fields, FieldsDesc);
		return this;
	}

	public PDUsPage verifyAllMandatoryFieldsforPDUs(){

		// you need to change the mandatory fields when the application changes
		String[] mandatoryFields = {"PDUs_Name_Xpath",
				"PDUs_OwnerGroupReadOnly_Xpath",
		"PDUs_SystemManagerManReadOnly_Xpath"};

		String[] mandatoryFieldsDesc = {"Name",
				"CI Owner Group","System Manager"};

		verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);

		return this;

	}
	public PDUsPage verifyAllReadOnlyFieldsforPDUs(){

		// you need to change the read only fields when the application changes
		String[] readOnlyFields = {"PDUs_OperationalStatus_Xpath"};

		String[] readOnlyFieldsDesc = {"Operational Status"};

		// Verify read only
		verifyDisabledFieldsByXpath(readOnlyFields, readOnlyFieldsDesc);
		return this;
	}

	public PDUsPage enterName(String name) {

		if(enterByXpath("PDUs_Name_Xpath", name))
			Reporter_ServiceNow.reportStep("The Name: "+name+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Name: "+name+" not found / could not be entered", "FAILURE");
		return this;
	}
	public PDUsPage enterAndChooseCIOwnerGroup(String CIOwnerGroup) {

		if(enterAndChoose("PDUs_OwnerGroup_Xpath", CIOwnerGroup))
			Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" not found / could not be entered", "FAILURE");
		return this;
	}

	public PDUsPage enterAndChooseSystemManager(String SystemManager) {

		if(enterAndChoose("PDUs_SystemManager_Xpath", SystemManager))
			Reporter_ServiceNow.reportStep("The System Manager: "+SystemManager+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The System Manager: "+SystemManager+" not found / could not be entered", "FAILURE");
		return this;
	}

	public CmdbListPage clickSubmit() {

		if(clickById("Submit_Id"))
			Reporter_ServiceNow.reportStep("The Submit button is clicked and CI is created successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Submit button could not be clicked or not found", "FAILURE");
		return new CmdbListPage(driver);
	}
	public PDUsPage selectEnvironment(String environment) {

		if(selectByVisibleTextByXpath("PDUs_Environment_Xpath", environment))
			Reporter_ServiceNow.reportStep("The type: "+environment+" is selected in Environment", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The type: "+environment+" could not be selected in Environment", "FAILURE");
		return this;
	}

	public PDUsPage enterAndChooseLocation(String location) {

		if(enterAndChoose("PDUs_Location_Xpath", location))
			Reporter_ServiceNow.reportStep("The location: "+location+" is entered successfull", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The location: "+location+" not found / could not be entered", "FAILURE");
		return this;
	}
	public PDUsPage clickSetBuild() {

		if(clickById("SA_Setbuild_Id"))
			Reporter_ServiceNow.reportStep("The Set to Build button is clicked, task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Set to Build button could not be clicked or not found", "FAILURE");
		return this;
	}
	public PDUsPage getBuildConfirmation(String text) {		

		if( getTextByXpath("SA_BuildConfirmation_Xpath").contains(text))
			Reporter_ServiceNow.reportStep("The value :"+text+" does exist as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The value :"+text+" does not exist", "FAILURE");
		return this;
	}

	public PDUsPage verifyOperationalstatus(String operationalstatus){

		Wait(3000);
		if(getDefaultValueByXpath("PDUs_OperationalStatus_Xpath").contains(operationalstatus))
			Reporter_ServiceNow.reportStep("The operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The operational status value "+operationalstatus+" could not be matched", "FAILURE");
		return this; 

	}
	//Indhu added 20-11-2015
	public PDUsPage clickLinkName(String linkName){
		Wait(2000);
		// click the first Incident Link
		scrollToElement(driver.findElement(By.linkText(linkName)));
		if(clickLink(linkName, false))
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item is clicked successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item could not be clicked", "WARNING");
		return new PDUsPage(driver);
	}
	public PDUsPage clickApprove() {

		if(clickByXpath("Approve_Xpath"))
			Reporter_ServiceNow.reportStep("The Approve button is clicked successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Approve button could not be clicked or not found", "FAILURE");
		return this;
	}

	public PDUsPage enterChangeRequestId(String changeRequestId){

		Wait(2000);
		if(enterByXpath("CMDBAPP_ChangeRequestID_Xpath", changeRequestId))
			Reporter_ServiceNow.reportStep("The Change request Id value : "+changeRequestId+" is entered successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Change request Id value : "+changeRequestId+" could not be entered or not found", "FAILURE");
		return this;
	}	

	public PDUsPage clickAddCIRelationship() {

		if(clickByXpath("SA_AddCIRelationship_Xpath"))
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon is clicked successfully and the relationship editor screen appeared as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon could not be clicked or not found", "FAILURE");
		return this;
	}

	public PDUsPage selectDependsOn() {

		if(clickByXpath("SA_DependOn_Xpath"))
			Reporter_ServiceNow.reportStep("The Depend On is selected in the Available Relationships successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Depend On could not be selected in the Available Relationships or not found", "FAILURE");
		Wait(5000);
		return this;
	}

	public PDUsPage deleteFilters(){	
		deleteAllFilters();
		return this;
	}

	public PDUsPage addFirstFilter(String filterType, String filterCondition, String filterValue){
		if(addFilters("CIS_FirstFilterType1_Xpath", filterType, "CIS_FilterCondition1_Xpath", filterCondition, "CIS_FilterValueSelect1_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public PDUsPage addSecondFilter(String filterType, String filterCondition, String filterValue){
		if(addFilters("CIS_FirstFilterType2_Xpath", filterType, "CIS_FilterCondition2_Xpath", filterCondition, "CIS_FilterValueSelect2_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}
	
	public PDUsPage addThirdFilter(String filterType, String filterCondition, String filterValue){
		if(addFilters("CIS_FirstFilterType3_Xpath", filterType, "CIS_FilterCondition3_Xpath", filterCondition, "CIS_FilterValue3_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}
	public PDUsPage addSecondFilter(String filterType, String filterValue){
		if(addFilters("CIS_FirstFilterType2_Xpath", filterType, "CIS_FilterCondition2_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue+" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue+" could not be selected","FAILURE");

		return this;
	}

	
	public PDUsPage addThirdFilter(String filterType, String filterValue){
		if(addFilters("CIS_FirstFilterType3_Xpath", filterType, "CIS_FilterCondition3_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue+" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue+" could not be selected","FAILURE");

		return this;
	}

	public PDUsPage addFourthFilter(String filterType, String filterValue){
		if(addFilters("CIS_FirstFilterType4_Xpath", filterType, "CIS_FilterCondition4_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue+" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue+" could not be selected","FAILURE");

		return this;
	}

		public PDUsPage clickORCondition() {

		if(!clickByXpath("CIS_ORCondition_Xpath"))				
			Reporter_ServiceNow.reportStep("The OR button  could not be clicked or not found", "FAILURE");
		return this;
	}

	public PDUsPage clickANDCondition() {

		if(!clickByXpath("CIS_AndCondition1_Xpath"))				
			Reporter_ServiceNow.reportStep("The AND button  could not be clicked or not found", "FAILURE");
		return this;
	}
	public PDUsPage selectFirstAvailableCIs() {

		if(!clickByXpath("SA_FirstAvailableCI_Xpath"))			
			Reporter_ServiceNow.reportStep("The First Available CI could not be selected or not found", "FAILURE");

		if(!clickByXpath("ALERT_MoveBreach_ToSelected_Xpath"))
			Reporter_ServiceNow.reportStep("The First Available CI could not be moved", "FAILURE");

		if(clickById("Ok_Id"))
			Reporter_ServiceNow.reportStep("The Highlighted CI appeared in the box on the right as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Save button could not be clicked or not found", "FAILURE");
		return this;
	}
	public PDUsPage verifyDependsOnRelationshipAppears() {

		scrollToElementByXpath("SA_DependsOnRelationshipAppears_XPath");
		if(getTextByXpath("SA_DependsOnRelationshipAppears_XPath").contains("Depends on"))		
			Reporter_ServiceNow.reportStep("The depends on relationship appears under the related items bar as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The depends on relationship does not appears under the related items bar", "FAILURE");
		return this;
	}
	public PDUsPage selectUsedBy() {

		if(clickByXpath("SA_UsedBy_Xpath"))
			Reporter_ServiceNow.reportStep("The Used By is selected in the Available Relationships successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Used By could not be selected in the Available Relationships or not found", "FAILURE");

		Wait(5000);
		return this;
	}
	public PDUsPage verifyUsedByRelationshipAppears() {

		scrollToElementByXpath("SA_DependsOnRelationshipAppears_XPath");
		if(getTextByXpath("SA_DependsOnRelationshipAppears_XPath").contains("Used by"))		
			Reporter_ServiceNow.reportStep("The Used By relationship appears under the related items bar as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The  Used By relationship does not appears under the related items bar", "FAILURE");
		return this;
	}
	public PDUsPage clickSetActive() {

		if(!clickById("SA_SetActive_Id"))
			Reporter_ServiceNow.reportStep("The Set Active button could not be clicked or not found", "FAILURE");
		else{
		verifyAlert();
		Reporter_ServiceNow.reportStep("The Set Active button is clicked and the task is created and assigned to the System Manager successfully", "SUCCESS");}
		
		return this;
	}	
	public PDUsPage clickSetRetire() {

		if(clickById("SA_SetRetire_Id"))
			Reporter_ServiceNow.reportStep("The Retire button is clicked, task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Retire button could not be clicked or not found", "WARNING");
		return this;
	}

	public PDUsPage clickDecommission() {

		if(clickById("SA_SetDecom_Id"))
			Reporter_ServiceNow.reportStep("The Decommission button is clicked, task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Decommission button could not be clicked or not found", "WARNING");
		return this;
	}

	public PDUsPage verifyReadOnlyOperationalstatus(String operationalstatus){

		Wait(1000);
		if(getDefaultValueByXpath("PDUs_OperationalStatus_Xpath").contains(operationalstatus))
			Reporter_ServiceNow.reportStep("The Operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Operational status value could not be matched", "FAILURE");
		return this; 

	}
	
	public PDUsPage verifyOperationalstatusReadOnly(String operationalstatus){

		Wait(1000);
		if(getDefaultValueByXpath("PDUs_ReadOnlyOperationalStatus_Xpath").contains(operationalstatus))
			Reporter_ServiceNow.reportStep("The Operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Operational status value could not be matched", "FAILURE");
		return this; 

	}

	public PDUsPage verifyAllFieldsReadOnly(){

		// you need to change the non mandatory fields when the application changes
		String[] Fields = { "PDUs_ReadOnlyName_Xpath",
				"PDUs_ReadOnlyManufacturer_Xpath",
				"PDUs_ReadOnlyModelId_Xpath",
				"PDUs_ReadOnlySerialNumber_Xpath",
				"PDUs_ReadOnlyLocation_Xpath",
				"PDUs_ReadOnlyIPAddress_Xpath",
				"PDUs_ReadOnlyInputPower_Xpath",
				"PDUs_ReadOnlyGoLiveDate_Xpath",
				"PDUs_ReadOnlyShortDescription_Xpath",
				"PDUs_ReadOnlyOperationalStatus_Xpath",
				"PDUs_ReadOnlyOwnerGroup_Xpath",
				"PDUs_ReadOnlySystemManager_Xpath",
				"PDUs_ReadOnlySystemManagerMan_Xpath"};

		String[] FieldsDesc = {"Name",
				"Manufacturer",
				"Model Id",
				"Serial Number",
				"Location",
				"IP Address",
				"Input Power",
				"Go Live Date",
				"Short Description",
				"Operational Status",
				"CI Owner Group",
				"System Manager",
				"Secondary System Manager"
		};

		verifyDisabledFieldsByXpath(Fields, FieldsDesc);

		return this;

	}	
	
	public PDUsPage enterSerialNumber(String number) {

		if(enterByXpath("PDUs_SerialNumber_Xpath", number))
			Reporter_ServiceNow.reportStep("The Serial Number: "+number+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Serial Number: "+number+" not found / could not be entered", "FAILURE");
		return this;
	}
	public PDUsPage enterIPAddress(String ipAdd) {

		if(enterByXpath("PDUs_IPAddress_Xpath", ipAdd))
			Reporter_ServiceNow.reportStep("The IP Address: "+ipAdd+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The IP Address: "+ipAdd+" not found / could not be entered", "FAILURE");
		return this;
	}
	public PDUsPage clickLinkNameforPDUs(String linkName){

		// click the first Incident Link
		if(clickLink(linkName, false))
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item is clicked successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item could not be clicked", "FAILURE");

		return new PDUsPage(driver);
	}

	public PDUsPage verifyalltext(String Name, String sNumber, String location, String ipAdd, String group, String sysMan){

		// click the first Incident Link
		if(!(getAttributeByXpath("PDUs_Name_Xpath", "value")).equals(Name))
			Reporter_ServiceNow.reportStep("The Name: "+ Name +" could not be matched, hence failure", "FAILURE");
		
		if(!(getAttributeByXpath("PDUs_SerialNumber_Xpath", "value")).equals(sNumber))
			Reporter_ServiceNow.reportStep("The Serial Number: "+ sNumber +" could not be matched, hence failure", "FAILURE");
		
		if(!(getAttributeByXpath("PDUs_Location_Xpath", "value")).equals(location))
			Reporter_ServiceNow.reportStep("The Location: "+ location +" could not be matched, hence failure", "FAILURE");
		
		if(!(getAttributeByXpath("PDUs_IPAddress_Xpath", "value")).equals(ipAdd))
			Reporter_ServiceNow.reportStep("The Ip Address: "+ ipAdd +" could not be matched, hence failure", "FAILURE");
		
		if(!(getAttributeByXpath("PDUs_OwnerGroup_Xpath", "value")).equals(group))
			Reporter_ServiceNow.reportStep("The CI Owner Group: "+ group +" could not be matched, hence failure", "FAILURE");
		
		if((getAttributeByXpath("PDUs_SystemManager_Xpath","value")).equals(sysMan))
			Reporter_ServiceNow.reportStep("The Namer: "+ Name +", Serial Number: "+sNumber+", Location: "+location+", Ip Address: "+ipAdd+", CI Owner Group: "+group+", System Manager: "+sysMan+" are matched as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Value: "+ sysMan +" could not be matched, hence failure", "FAILURE");
			
			return this;
	}

	 public PDUsPage savePDUWithUpdatedCI(String appName2) {

	  if (clickById("Save_Id"))
	   Reporter_ServiceNow.reportStep("The CI record is saved  with the new value: "+appName2+" in the Support Group field successfully", "SUCCESS");
	  else
	   Reporter_ServiceNow.reportStep("The CI record could not saved ", "FAILURE");

	  Wait(5000);
	  return this;
	 }
	
	 public PDUsPage enterAllMAndatoryFields(String name, String CIOwnerGroup, String systemManager) {

			if(!enterByXpath("PDUs_Name_Xpath", name))
				Reporter_ServiceNow.reportStep("The Name: "+name+" not found / could not be entered", "FAILURE");		
			if(!enterAndChoose("PDUs_OwnerGroup_Xpath", CIOwnerGroup))
				Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" not found / could not be entered", "FAILURE");
			if(enterAndChoose("PDUs_SystemManager_Xpath", systemManager))
				Reporter_ServiceNow.reportStep("All Mandatory fields: "+name+","+CIOwnerGroup+","+systemManager+" are entered successfully", "SUCCESS");
			else
				Reporter_ServiceNow.reportStep("The System Manager: "+systemManager+" not found / could not be entered", "FAILURE");
			return this;
		}
	 public PDUsPage enterAndSaveCIOwnerGroup(String CIOwnerGroup) {

			if(!enterAndChoose("PDUs_OwnerGroup_Xpath", CIOwnerGroup))
				Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" not found / could not be entered", "FAILURE");		

			if (clickById("Save_Id"))
				Reporter_ServiceNow.reportStep("The CI record is saved with the new value: "+CIOwnerGroup+" in the Support Group field successfully", "SUCCESS");
			else
				Reporter_ServiceNow.reportStep("The CI record could not saved ", "FAILURE");

			return this;
		}

}



