package Pages_ServiceNow;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class UPSPage extends ServiceNowWrappers{


	private final RemoteWebDriver driver;
	private String CMDBTaskNumber;

	public UPSPage(RemoteWebDriver driver) {
		this.driver = driver;
		switchToMainFrame();

		//Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not the PDUs Page", "FAILURE");
		}

	}
	public UPSPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");
		return this;
	}

	public UPSPage verifyAllFieldsforUPS(){

		// you need to change the non mandatory fields when the application changes
		String[] Fields = { "UPS_Name_Xpath", 
				"UPS_Manufacturer_Xpath",
				"UPS_ModelId_Xpath", 
				"UPS_SerialNumber_Xpath",
				"UPS_Location_Xpath", 
				"UPS_IPAddress_Xpath",
				"UPS_InputPower_Xpath", 
				"UPS_GoLiveDate_Xpath", 
				"UPS_ShortDescription_Xpath",
				"UPS_OperationalStatus_Xpath",
				"UPS_OwnerGroup_Xpath",
				"UPS_SystemManager_Xpath",
		"UPS_SecSystemManagerMan_Xpath"};

		String[] FieldsDesc = {"Name",
				"Manufacturer",
				"Model Id",
				"Serial Number",
				"Location",
				"IP Address",
				"Power Input",
				"Go Live Date",
				"Description",
				"Operational Status",
				"CI Owner Group",
				"System Manager",
				"Secondary System Manager"
		};
		verifyFieldsExistByXpath(Fields, FieldsDesc);
		return this;
	}

	public UPSPage verifyAllMandatoryFieldsforUPS(){

		// you need to change the mandatory fields when the application changes
		String[] mandatoryFields = {"UPS_Name_Xpath",
				"UPS_OwnerGroupReadOnly_Xpath",
		"UPS_SystemManagerManReadOnly_Xpath"};

		String[] mandatoryFieldsDesc = {"Name",
				"CI Owner Group","System Manager"};

		verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);

		return this;

	}
	public UPSPage verifyAllReadOnlyFieldsforUPS(){

		// you need to change the read only fields when the application changes
		String[] readOnlyFields = {"UPS_OperationalStatus_Xpath"};

		String[] readOnlyFieldsDesc = {"Operational Status"};

		// Verify read only
		verifyDisabledFieldsByXpath(readOnlyFields, readOnlyFieldsDesc);
		return this;
	}

	public UPSPage enterName(String name) {

		if(enterByXpath("UPS_Name_Xpath", name))
			Reporter_ServiceNow.reportStep("The Name: "+name+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Name: "+name+" not found / could not be entered", "FAILURE");
		return this;
	}
	public UPSPage enterAndChooseCIOwnerGroup(String CIOwnerGroup) {

		if(enterAndChoose("UPS_OwnerGroup_Xpath", CIOwnerGroup))
			Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" not found / could not be entered", "FAILURE");
		return this;
	}

	public UPSPage enterAndChooseSystemManager(String SystemManager) {

		if(enterAndChoose("UPS_SystemManager_Xpath", SystemManager))
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
	
	public UPSPage enterAndChooseLocation(String location) {

		if(enterAndChoose("UPS_Location_Xpath", location))
			Reporter_ServiceNow.reportStep("The location: "+location+" is entered successfull", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The location: "+location+" not found / could not be entered", "FAILURE");
		return this;
	}
	public UPSPage clickSetBuild() {

		if(clickById("SA_Setbuild_Id"))
			Reporter_ServiceNow.reportStep("The Set to Build button is clicked, task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Set to Build button could not be clicked or not found", "FAILURE");
		return this;
	}
	public UPSPage getBuildConfirmation(String text) {		

		if(!getTextByXpath("SA_BuildConfirmation_Xpath").startsWith(text))
			Reporter_ServiceNow.reportStep("The Proposal for CI modification already exist.", "FAILURE");
		return this;

	}

	public UPSPage verifyOperationalstatus(String operationalstatus){

		Wait(3000);
		if(getDefaultValueByXpath("UPS_OperationalStatus_Xpath").contains(operationalstatus))
			Reporter_ServiceNow.reportStep("The operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The operational status value "+operationalstatus+" could not be matched", "FAILURE");
		return this; 

	}
	
	public UPSPage clickLinkName(String linkName){
		Wait(2000);
		// click the first Incident Link
		scrollToElement(driver.findElement(By.linkText(linkName)));
		if(clickLink(linkName, false))
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item is clicked successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item could not be clicked", "WARNING");
		return new UPSPage(driver);
	}
	public UPSPage clickApprove() {

		if(clickByXpath("Approve_Xpath"))
			Reporter_ServiceNow.reportStep("The Approve button is clicked successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Approve button could not be clicked or not found", "FAILURE");
		return this;
	}

	public UPSPage enterChangeRequestId(String changeRequestId){

		Wait(2000);
		if(enterByXpath("CMDBAPP_ChangeRequestID_Xpath", changeRequestId))
			Reporter_ServiceNow.reportStep("The Change request Id value : "+changeRequestId+" is entered successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Change request Id value : "+changeRequestId+" could not be entered or not found", "FAILURE");
		return this;
	}	

	public UPSPage clickAddCIRelationship() {

		if(clickByXpath("SA_AddCIRelationship_Xpath"))
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon is clicked successfully and the relationship editor screen appeared as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon could not be clicked or not found", "FAILURE");
		return this;
	}

	public UPSPage selectDependsOn() {

		if(clickByXpath("SA_DependOn_Xpath"))
			Reporter_ServiceNow.reportStep("The Depend On is selected in the Available Relationships successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Depend On could not be selected in the Available Relationships or not found", "FAILURE");
		Wait(5000);
		return this;
	}

	public UPSPage deleteFilters(){	
		deleteAllFilters();
		return this;
	}

	public UPSPage addFirstFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType1_Xpath", filterType, "CIS_FilterCondition1_Xpath", filterCondition, "CIS_FilterValueSelect1_Xpath", filterValue))
								
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public UPSPage addSecondFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType2_Xpath", filterType, "CIS_FilterCondition2_Xpath", filterCondition, "CIS_FilterValueSelect2_Xpath", filterValue))
								
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public UPSPage addThirdFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType3_Xpath", filterType, "CIS_FilterCondition3_Xpath", filterCondition, "CIS_FilterValue3_Xpath", filterValue))
								
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}
	public UPSPage addSecondFilter(String filterType, String filterValue){
		if(!addFilters("CIS_FirstFilterType2_Xpath", filterType, "CIS_FilterCondition2_Xpath", filterValue))
								
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue+" could not be selected","FAILURE");

		return this;
	}


	public UPSPage addThirdFilter(String filterType, String filterValue){
		if(!addFilters("CIS_FirstFilterType3_Xpath", filterType, "CIS_FilterCondition3_Xpath", filterValue))
								
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue+" could not be selected","FAILURE");

		return this;
	}

	public UPSPage addFourthFilter(String filterType, String filterValue){
		if(addFilters("CIS_FirstFilterType4_Xpath", filterType, "CIS_FilterCondition4_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue+" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue+" could not be selected","FAILURE");

		return this;
	}

	public UPSPage clickORCondition() {

		if(!clickByXpath("CIS_ORCondition_Xpath"))				
			Reporter_ServiceNow.reportStep("The OR button  could not be clicked or not found", "FAILURE");
		return this;
	}

	public UPSPage clickANDCondition() {

		if(!clickByXpath("CIS_AndCondition1_Xpath"))				
			Reporter_ServiceNow.reportStep("The AND button  could not be clicked or not found", "FAILURE");
		return this;
	}
	public UPSPage selectFirstAvailableCIs() {

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
	public UPSPage verifyDependsOnRelationshipAppears() {

		scrollToElementByXpath("SA_DependsOnRelationshipAppears_XPath");
		if(getTextByXpath("SA_DependsOnRelationshipAppears_XPath").contains("Depends on"))		
			Reporter_ServiceNow.reportStep("The depends on relationship appears under the related items bar as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The depends on relationship does not appears under the related items bar", "FAILURE");
		return this;
	}
	public UPSPage selectUsedBy() {

		if(clickByXpath("SA_UsedBy_Xpath"))
			Reporter_ServiceNow.reportStep("The Used By is selected in the Available Relationships successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Used By could not be selected in the Available Relationships or not found", "FAILURE");

		Wait(5000);
		return this;
	}
	public UPSPage verifyUsedByRelationshipAppears() {

		scrollToElementByXpath("SA_DependsOnRelationshipAppears_XPath");
		if(getTextByXpath("SA_DependsOnRelationshipAppears_XPath").contains("Used by"))		
			Reporter_ServiceNow.reportStep("The Used By relationship appears under the related items bar as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The  Used By relationship does not appears under the related items bar", "FAILURE");
		return this;
	}
	public UPSPage clickSetActive() {

		if(!clickById("SA_SetActive_Id"))
			Reporter_ServiceNow.reportStep("The Set Active button could not be clicked or not found", "FAILURE");
		else{	
			verifyAlert();
			Reporter_ServiceNow.reportStep("The Set Active button is clicked and the task is created and assigned to the System Manager successfully", "SUCCESS");}
		return this;
	
	}	
	public UPSPage clickSetRetire() {

		if(clickById("SA_SetRetire_Id"))
			Reporter_ServiceNow.reportStep("The Retire button is clicked, task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Retire button could not be clicked or not found", "WARNING");
		return this;
	}

	public UPSPage clickDecommission() {

		if(clickById("SA_SetDecom_Id"))
			Reporter_ServiceNow.reportStep("The Decommission button is clicked, task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Decommission button could not be clicked or not found", "WARNING");
		return this;
	}

	public UPSPage verifyReadOnlyOperationalstatus(String operationalstatus){

		Wait(1000);
		if(getDefaultValueByXpath("UPS_OperationalStatus_Xpath").contains(operationalstatus))
			Reporter_ServiceNow.reportStep("The Operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Operational status value could not be matched", "FAILURE");
		return this; 

	}

	public UPSPage verifyOperationalstatusReadOnly(String operationalstatus){

		Wait(1000);
		if(getDefaultValueByXpath("UPS_ReadOnlyOperationalStatus_Xpath").contains(operationalstatus))
			Reporter_ServiceNow.reportStep("The Operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Operational status value could not be matched", "FAILURE");
		return this; 

	}

	public UPSPage verifyAllFieldsReadOnly(){

		// you need to change the non mandatory fields when the application changes
		String[] Fields = { "UPS_ReadOnlyName_Xpath",
				"UPS_ReadOnlyManufacturer_Xpath",
				"UPS_ReadOnlyModelId_Xpath",
				"UPS_ReadOnlySerialNumber_Xpath",
				"UPS_ReadOnlyLocation_Xpath",
				"UPS_ReadOnlyIPAddress_Xpath",
				"UPS_ReadOnlyInputPower_Xpath",
				"UPS_ReadOnlyGoLiveDate_Xpath",
				"UPS_ReadOnlyShortDescription_Xpath",
				"UPS_ReadOnlyOperationalStatus_Xpath",
				"UPS_ReadOnlyOwnerGroup_Xpath",
				"UPS_ReadOnlySystemManager_Xpath",
		"UPS_ReadOnlySystemManagerMan_Xpath"};

		String[] FieldsDesc = {"Name",
				"Manufacturer",
				"Model Id",
				"Serial Number",
				"Location",
				"IP Address",
				"Power Input",
				"Go Live Date",
				"Description",
				"Operational Status",
				"CI Owner Group",
				"System Manager",
				"Secondary System Manager"
		};

		verifyDisabledFieldsByXpath(Fields, FieldsDesc);

		return this;

	}	

	public UPSPage enterSerialNumber(String number) {

		if(enterByXpath("UPS_SerialNumber_Xpath", number))
			Reporter_ServiceNow.reportStep("The Serial Number: "+number+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Serial Number: "+number+" not found / could not be entered", "FAILURE");
		return this;
	}
	public UPSPage enterIPAddress(String ipAdd) {

		if(enterByXpath("UPS_IPAddress_Xpath", ipAdd))
			Reporter_ServiceNow.reportStep("The IP Address: "+ipAdd+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The IP Address: "+ipAdd+" not found / could not be entered", "FAILURE");
		return this;
	}
	public UPSPage clickLinkNameforUPS(String linkName){

		// click the first Incident Link
		if(clickLink(linkName, false))
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item is clicked successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item could not be clicked", "FAILURE");

		return new UPSPage(driver);
	}

	public UPSPage verifyalltext(String sNumber, String location, String ipAdd){
		if(!(getAttributeByXpath("UPS_SerialNumber_Xpath", "value")).equals(sNumber))
			Reporter_ServiceNow.reportStep("The Serial Number: "+ sNumber +" could not be matched, hence failure", "FAILURE");

		if(!(getAttributeByXpath("UPS_Location_Xpath", "value")).equals(location))
			Reporter_ServiceNow.reportStep("The Location: "+ location +" could not be matched, hence failure", "FAILURE");

		if((getAttributeByXpath("UPS_IPAddress_Xpath", "value")).equals(ipAdd))
			Reporter_ServiceNow.reportStep("The Serial Number: "+sNumber+", Location: "+location+", Ip Address: "+ipAdd+" are matched as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Value: "+ ipAdd +" could not be matched, hence failure", "FAILURE");

		return this;
	}

	public UPSPage saveUPS() {

		if (clickById("Save_Id"))
			Reporter_ServiceNow.reportStep("The CI record is saved successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The CI record could not saved ", "FAILURE");

		Wait(5000);
		return this;
	}

	public UPSPage saveUPSWithUpdatedCI(String appName2) {

		if (clickById("Save_Id"))
			Reporter_ServiceNow.reportStep("The CI record is saved  with the new value: "+appName2+" in the Support Group field successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The CI record could not saved ", "FAILURE");

		Wait(5000);
		return this;
	}

	public UPSPage checkShowAllRelationships() {

		Wait(2000);
		if(!clickByXpath("UPS_ShowAllRelationshipcheckbox_Xpath"))			
			Reporter_ServiceNow.reportStep("The Show All Relationship checkbox could not be selected or not found", "FAILURE");
		
		return this;
	}
	
	public UPSPage enterAllMAndatoryFields(String name, String CIOwnerGroup, String SystemManager) {

		if(!enterByXpath("UPS_Name_Xpath", name))
			Reporter_ServiceNow.reportStep("The Name: "+name+" not found / could not be entered", "FAILURE");		
		if(!enterAndChoose("UPS_OwnerGroup_Xpath", CIOwnerGroup))
			Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" not found / could not be entered", "FAILURE");
		if(enterAndChoose("UPS_SystemManager_Xpath", SystemManager))
			Reporter_ServiceNow.reportStep("All Mandatory fields: "+name+","+CIOwnerGroup+","+SystemManager+" are entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The System Manager: "+SystemManager+" not found / could not be entered", "FAILURE");
		return this;
	}
	public UPSPage enterSNumberLocIpAdd(String sNumber, String location, String ipAdd) {

		if(!enterByXpath("UPS_SerialNumber_Xpath", sNumber))
			Reporter_ServiceNow.reportStep("The Serial Number: "+sNumber+" not found / could not be entered", "FAILURE");
		if(!enterAndChoose("UPS_Location_Xpath", location))
			Reporter_ServiceNow.reportStep("The location: "+location+" not found / could not be entered", "FAILURE");
		if(enterByXpath("UPS_IPAddress_Xpath", ipAdd))
			Reporter_ServiceNow.reportStep("The Serial Number: "+sNumber+", Location: "+location+", Ip Address: "+ipAdd+" are entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The IP Address: "+ipAdd+" not found / could not be entered", "FAILURE");
		return this;
	}
	
	public UPSPage addFilterforDependsOn(String filterType1, String filterCondition1, String filterValue1, 
			String filterType2, String filterCondition2, String filterType3, String filterCondition3){

		deleteFilters()
		.addFirstFilter(filterType1, filterCondition1, filterValue1)       
		.clickANDCondition()
		.addSecondFilter(filterType2, filterCondition2)
		.clickANDCondition()
		.addthirdFilter(filterType3, filterCondition3);
		Reporter_ServiceNow.reportStep("The Class values: "+filterType1+" "+filterCondition1+" "+ filterValue1+", "+filterType2+" "+filterCondition2+", "+filterType3+" "+filterCondition3+" are selected successfully", "SUCCESS");
		return this;
	}
	
	public UPSPage addthirdFilter(String filterType, String filterValue){
		if(!addFilters("CIS_FirstFilterType3_Xpath", filterType, "CIS_FilterCondition3_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue+" could not be selected","FAILURE");

		return this;
	}
	
	public UPSPage addFilterForUsedBy(String filterType1, String filterCondition1, String filterValue1,String filterType2, String filterCondition2, String filterValue2, String filterType5, String filterCondition5,String filterType6, String filterCondition6){
		deleteAllFilters();
		if(!addFilters("CIS_FirstFilterType1_Xpath", filterType1, "CIS_FilterCondition1_Xpath", filterCondition1, "CIS_FilterValueSelect1_Xpath", filterValue1))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType1+" "+ filterCondition1+" "+ filterValue1 +" could not be selected","FAILURE");

		if(!clickByXpath("CIS_ORCondition_Xpath"))				
			Reporter_ServiceNow.reportStep("The OR button  could not be clicked or not found", "FAILURE");

		if(!addFilters("CIS_FirstFilterType2_Xpath", filterType2, "CIS_FilterCondition2_Xpath", filterCondition2, "CIS_FilterValueSelect2_Xpath", filterValue2))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType2+" "+ filterCondition2+" "+ filterValue2 +" could not be selected","FAILURE");

		if(!clickByXpath("CIS_AndCondition1_Xpath"))				
			Reporter_ServiceNow.reportStep("The AND button could not be clicked or not found", "FAILURE");

		if(!addFilters("CIS_FirstFilterType3_Xpath", filterType5, "CIS_FilterCondition3_Xpath", filterCondition5))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType5+" "+ filterCondition5+" could not be selected","FAILURE");

		if(!clickByXpath("CIS_AndCondition1_Xpath"))				
			Reporter_ServiceNow.reportStep("The AND button could not be clicked or not found", "FAILURE");

		if(addFilters("CIS_FirstFilterType4_Xpath", filterType6, "CIS_FilterCondition4_Xpath", filterCondition6))
			Reporter_ServiceNow.reportStep("The Filter value: "+ filterType1+" "+ filterCondition1+" "+ filterValue1 +","+ filterValue2+","+filterType5+" "+filterCondition5+", "+filterType6+", "+filterCondition6+"  are selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType6+" "+ filterCondition6+" could not be selected","FAILURE");
		return this;
	}
	public UPSPage enterSNumberIpAddwithoutRep(String sNumber, String ipAdd) {

		if(!enterByXpath("UPS_SerialNumber_Xpath", sNumber))
			Reporter_ServiceNow.reportStep("The Serial Number: "+sNumber+" not found / could not be entered", "FAILURE");
		if(!enterByXpath("UPS_IPAddress_Xpath", ipAdd))
			Reporter_ServiceNow.reportStep("The IP Address: "+ipAdd+" not found / could not be entered", "FAILURE");
		return this;
	}
	public UPSPage verifyAddCIRelationship() {

		scrollToElementByXpath("SA_AddCIRelationship_Xpath");

		if(isExistByXpath("SA_AddCIRelationship_Xpath"))
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon is appeared for Active CI as expected.", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon is appeared, hence failure.", "FAILURE");

		return this;
	}
}



