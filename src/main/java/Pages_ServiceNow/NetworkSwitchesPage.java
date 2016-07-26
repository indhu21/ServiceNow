package Pages_ServiceNow;


import java.util.List;

import org.apache.poi.xwpf.usermodel.ISDTContents;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.ColorUtils;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class NetworkSwitchesPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;
	private String CMDBTaskName;
	public NetworkSwitchesPage(RemoteWebDriver driver) {
		this.driver = driver;
		switchToMainFrame();		
		// Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not Network Switches Page", "FAILURE");
		}
	}

	public NetworkSwitchesPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");

		return this;
	}

	public NetworkSwitchesPage verifyAllMandatoryFields(){
		switchToFrame("Frame_Main");

		// you need to change the mandatory fields when the application changes
		String[] mandatoryFields = {"NWSwitches_NameMandatory_Xpath","NWSwitches_CIOwnerGroupMandatory_Xpath","NWSwitches_SysManagerMandatory_Xpath"};

		String[] mandatoryFieldsDesc = {"Name","CIOwnerGroup","SystemManager"};

		verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);

		return this;

	}

	public NetworkSwitchesPage verifyAllNetworkSwitchesFields(){

		// you need to change the non mandatory fields when the application changes
		String[] Fields = { "NWSwitches_Name_Xpath",				
				"NWSwitches_Manufacturer_Xpath",
				"NWSwitches_ModelID_Xpath",
				"NWSwitches_SerialNumber_Xpath",
				"NWSwitches_DeviceType_Xpath",
				"NWSwitches_GxP_Xpath",
				"NWSwitches_Location_Xpath",				
				"NWSwitches_IPAddress_Xpath",
				"NWSwitches_FirmwareVersion_Xpath",
				"NWSwitches_Desc_Xpath",
				"NWSwitches_OperStatus_Xpath",
				"NWSwitches_CIOwnerGroup_Xpath",
				"NWSwitches_SysManager_Xpath",
				"NWSwitches_SecSysManager_Xpath",
				"NWSwitches_BusinessProcessOwner_Xpath"
		};

		String[] FieldsDesc = {"Name","Manufacturer","Model ID","Serial Number","Device Type","GxP",
				"Location","IP Address","Firmware Version","Description","Operational Status","CI Owner Group",
				"System Manager","Secondary System Manager","Business Process Owner"
		};

		verifyFieldsExistByXpath(Fields, FieldsDesc);

		return this;

	}


	public NetworkSwitchesPage verifyAllReadOnlyFields(){

		// you need to change the read only fields when the application changes
		String[] readOnlyFields = {"NWSwitches_OperStatus_Xpath"};

		String[] readOnlyFieldsDesc = {"Operational Status"};


		// Verify read only
		verifyDisabledFieldsByXpath(readOnlyFields, readOnlyFieldsDesc);

		return this;

	}

	public NetworkSwitchesPage enterName(String name) {

		if(enterByXpath("NWSwitches_Name_Xpath", name))
			Reporter_ServiceNow.reportStep("The Name: "+name+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Name: "+name+" not found / could not be entered", "FAILURE");

		return this;
	}

	public NetworkSwitchesPage enterAndChooseCIOwnerGroup(String CIOwnerGroup) {
		if(enterAndChoose("NWSwitches_CIOwnerGroup_Xpath", CIOwnerGroup))
			Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" not found / could not be entered", "FAILURE");

		return this;
	}

	public NetworkSwitchesPage enterAndChooseSystemManager(String SystemManager) {
		if(enterAndChoose("NWSwitches_SysManager_Xpath", SystemManager))
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


	public NetworkSwitchesPage selectGxP(String gxp) {
		if(selectByVisibleTextByXpath("NWSwitches_GxP_Xpath", gxp))
			Reporter_ServiceNow.reportStep("The type: "+gxp+" is selected in GxP successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The type: "+gxp+" could not be selected in GxP", "FAILURE");

		return this;
	}


	public NetworkSwitchesPage enterAndChooseLocation(String location) {
		if(enterAndChoose("NWSwitches_Location_Xpath", location))
			Reporter_ServiceNow.reportStep("The location: "+location+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The location: "+location+" not found / could not be entered", "FAILURE");

		return this;
	}

	public NetworkSwitchesPage enterSerialNumber(String serialNumber) {
		if(enterByXpath("NWSwitches_SerialNumber_Xpath", serialNumber))
			Reporter_ServiceNow.reportStep("The SerialNumber: "+serialNumber+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The SerialNumber: "+serialNumber+" not found / could not be entered", "FAILURE");

		return this;
	}

	public NetworkSwitchesPage enterIPAddress(String IPAddress) {
		if(enterByXpath("NWSwitches_IPAddress_Xpath", IPAddress))
			Reporter_ServiceNow.reportStep("The IP Address: "+IPAddress+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The IP Address: "+IPAddress+" not found / could not be entered", "FAILURE");

		return this;
	}

	public NetworkSwitchesPage clickSetBuild() {

		if(clickById("SA_Setbuild_Id"))
			Reporter_ServiceNow.reportStep("The Set to Build button is clicked successfully and task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Set to Build button could not be clicked or not found", "FAILURE");

		return this;
	}

	public NetworkSwitchesPage getBuildConfirmation(String text) {		

		if( !getTextByXpath("SA_BuildConfirmation_Xpath").contains(text))			
			Reporter_ServiceNow.reportStep("The Proposal for CI modification already exist.", "FAILURE");

		return this;
	}

	public NetworkSwitchesPage clickApprove() {

		if(clickByXpath("Approve_Xpath"))
			Reporter_ServiceNow.reportStep("The approved button is clicked successfully and the task approved as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Approve button could not be clicked or not found", "FAILURE");

		return this;
	}


	public NetworkSwitchesPage clickAddCIRelationship() {
		
		if(scrollToElementByXpath("SA_AddCIRelationship_Xpath"))
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon appeared as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon found", "FAILURE");

	
		if(clickByXpath("SA_AddCIRelationship_Xpath"))
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon is clicked successfully and relationship editor screen appeared as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon could not be clicked or not found", "FAILURE");

		return this;
	}


	public NetworkSwitchesPage clickSetRetire() {

		if(clickById("SA_SetRetire_Id"))
			Reporter_ServiceNow.reportStep("The Retire button is clicked successfully and task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Retire button could not be clicked or not found", "WARNING");

		return this;
	}

	public NetworkSwitchesPage clickDecommission() {

		if(clickById("SA_SetDecom_Id"))
			Reporter_ServiceNow.reportStep("The Decommission button is clicked successfully and task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Decommission button could not be clicked or not found", "WARNING");

		return this;
	}

	public NetworkSwitchesPage clickLinkName(String linkName){

		// click the first Incident Link
		scrollToElement(driver.findElement(By.linkText(linkName)));
		if(clickLink(linkName, false))
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item could be clicked successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item could not be clicked", "WARNING");

		return new NetworkSwitchesPage(driver);
	}
	public NetworkSwitchesPage verifyAllFieldsReadOnly(){

		// you need to change the non mandatory fields when the application changes
		String[] Fields = { "NWSwitches_ReadOnlyName_Xpath",				
				"NWSwitches_ReadOnlyManufacturer_Xpath",
				"NWSwitches_ReadOnlyModelID_Xpath",
				"NWSwitches_ReadOnlySerialNumber_Xpath",
				"NWSwitches_ReadOnlyDeviceType_Xpath",
				"NWSwitches_ReadOnlyGxP_Xpath",
				"NWSwitches_ReadOnlyLocation_Xpath",				
				"NWSwitches_ReadOnlyIPAddress_Xpath",
				"NWSwitches_ReadOnlyFirmwareVersion_Xpath",
				"NWSwitches_ReadOnlyDesc_Xpath",
				"NWSwitches_ReadOnlyOperStatus_Xpath",
				"NWSwitches_ReadOnlyCIOwnerGroup_Xpath",
				"NWSwitches_ReadOnlySysManager_Xpath",
				"NWSwitches_ReadOnlySecSysManager_Xpath",
				"NWSwitches_ReadOnlyBusinessProcessOwner_Xpath"
		};

		String[] FieldsDesc = {"Name","Manufacturer","Model ID","Serial Number","Device Type","GxP",
				"Location","IP Address","Firmware Version","Description","Operational Status","CI Owner Group",
				"System Manager","Secondary System Manager","Business Process Owner"
		};

		verifyDisabledFieldsByXpath(Fields, FieldsDesc);

		return this;

	}	
	public NetworkSwitchesPage selectDependsOn() {

		if(clickByXpath("SA_DependOn_Xpath"))
			Reporter_ServiceNow.reportStep("The Depend On is selected in the Available Relationships successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Depend On could not be selected in the Available Relationships or not found", "FAILURE");

		Wait(5000);

		return this;
	}
	public NetworkSwitchesPage selectFirstAvailableCIs() {

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
	public NetworkSwitchesPage verifyDependsOnRelationshipAppears() {

		scrollToElementByXpath("SA_DependsOnRelationshipAppears_XPath");
		if(getTextByXpath("SA_DependsOnRelationshipAppears_XPath").contains("Depends on"))		
			Reporter_ServiceNow.reportStep("The depends on relationship appears under the related items bar as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The depends on relationship does not appears under the related items bar", "FAILURE");

		return this;
	}

	public NetworkSwitchesPage verifyUsedByRelationshipAppears() {

		scrollToElementByXpath("SA_DependsOnRelationshipAppears_XPath");
		if(getTextByXpath("SA_DependsOnRelationshipAppears_XPath").contains("Used by"))		
			Reporter_ServiceNow.reportStep("The Used By relationship appears under the related items bar as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The  Used By relationship does not appears under the related items bar", "FAILURE");

		return this;
	}

	public NetworkSwitchesPage clickSetActive() {

		if(!clickById("SA_SetActive_Id"))
			Reporter_ServiceNow.reportStep("The Set Active button could not be clicked or not found", "FAILURE");
		else{
		verifyAlert();
			Reporter_ServiceNow.reportStep("The Set Active button is clicked and the task is created and assigned to the System Manager successfully", "SUCCESS");}
		return this;
	}	

	public NetworkSwitchesPage enterChangeRequestId(String changeRequestId){

		Wait(2000);
		if(enterByXpath("CMDBAPP_ChangeRequestID_Xpath", changeRequestId))
			Reporter_ServiceNow.reportStep("The Change request Id value : "+changeRequestId+" is entered successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Change request Id value : "+changeRequestId+" could not be entered or not found", "FAILURE");

		return this;
	}	

	public NetworkSwitchesPage selectUsedBy() {

		if(clickByXpath("SA_UsedBy_Xpath"))
			Reporter_ServiceNow.reportStep("The Used By is selected in the Available Relationships successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Used By could not be selected in the Available Relationships or not found", "FAILURE");

		Wait(5000);

		return this;
	}
	public NetworkSwitchesPage verifyOperationalstatus(String operationalstatus){

		Wait(3000);

		if(getDefaultValueByXpath("NWSwitches_OperStatus_Xpath").contains(operationalstatus))
			Reporter_ServiceNow.reportStep("The operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The operational status value "+operationalstatus+" could not be matched", "FAILURE");

		return this; 

	}
	public NetworkSwitchesPage verifyReadOnlyOperationalstatus(String operationalstatus){

		Wait(1000);

		if(getDefaultValueByXpath("NWSwitches_ReadOnlyOperStatus_Xpath").contains(operationalstatus))
			Reporter_ServiceNow.reportStep("The operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The operational status value could not be matched", "FAILURE");

		return this; 

	}
	public NetworkSwitchesPage clickORCondition() {

		if(!clickByXpath("CIS_ORCondition_Xpath"))				
			Reporter_ServiceNow.reportStep("The OR button  could not be clicked or not found", "FAILURE");

		return this;
	}

	public NetworkSwitchesPage clickANDCondition() {

		if(!clickByXpath("CIS_AndCondition1_Xpath"))				
			Reporter_ServiceNow.reportStep("The AND button  could not be clicked or not found", "FAILURE");
		return this;
	}



	public NetworkSwitchesPage deleteFilters(){	

		deleteAllFilters();
		return this;
	}

	public NetworkSwitchesPage verifyEnteredFileds( String gxp, String location, String ipAddress, String serialNumber){

		if(!getDefaultValueByXpath("NWSwitches_GxP_Xpath").contains(gxp))			
			Reporter_ServiceNow.reportStep("The "+gxp+" is NOT available in Gxp","FAILURE");

		if(!getAttributeByXpath("NWSwitches_Location_Xpath","value").contains(location))
			Reporter_ServiceNow.reportStep("The  "+location+" is NOT available in Location","FAILURE");

		if(!getAttributeByXpath("NWSwitches_IPAddress_Xpath","value").contains(ipAddress))
			Reporter_ServiceNow.reportStep("The  "+ipAddress+" is NOT available in IP Address","FAILURE");		

		if(getAttributeByXpath("NWSwitches_SerialNumber_Xpath","value").contains(serialNumber))
			Reporter_ServiceNow.reportStep("The Values GXP: "+gxp+", Location: "+location+", Ip Address: "+ipAddress+", Serial Number: "+serialNumber+" are matched as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The  "+serialNumber+" is NOT available in IP Address","FAILURE");
		return this;
	}
	public NetworkSwitchesPage addFirstFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType1_Xpath", filterType, "CIS_FilterCondition1_Xpath", filterCondition, "CIS_FilterValueSelect1_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+ " "+filterCondition+" "+filterValue+" could not be selected","FAILURE");

		return this;
	}

	public NetworkSwitchesPage addSecondFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType2_Xpath", filterType, "CIS_FilterCondition2_Xpath", filterCondition, "CIS_FilterValueSelect2_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+ " "+filterCondition+" "+filterValue+" could not be selected","FAILURE");

		return this;
	}

	public NetworkSwitchesPage addSecondFilter(String filterType, String filterValue){
		if(!addFilters("CIS_FirstFilterType2_Xpath", filterType, "CIS_FilterCondition2_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+ " "+filterValue+" could not be selected","FAILURE");

		return this;
	}

	public NetworkSwitchesPage addthirdFilter(String filterType, String filterValue){
		if(!addFilters("CIS_FirstFilterType3_Xpath", filterType, "CIS_FilterCondition3_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+ " "+filterValue+" could not be selected","FAILURE");

		return this;
	}

	public NetworkSwitchesPage addFourthFilter(String filterType, String filterValue){
		if(!addFilters("CIS_FirstFilterType4_Xpath", filterType, "CIS_FilterCondition4_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+ " "+filterValue+" could not be selected","FAILURE");

		return this;
	}

	public NetworkSwitchesPage clickAppNameLink(String linkName){
		if(clickLink(linkName,false))
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item is clicked successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item could not be clicked", "FAILURE");

		return this;
	}
	public String getCMDBName() {
		CMDBTaskName = getAttributeByXpath("NWSwitches_Name_Xpath", "value");
		if(CMDBTaskName.equals(""))
			Reporter_ServiceNow.reportStep("The task number is blank for newly created CMDB Task", "FAILURE");
		return CMDBTaskName;
	}	
	public NetworkSwitchesPage enterAllMandatoryFields(String name, String CIOwnerGroup, String SystemManager) {

		if(!enterByXpath("NWSwitches_Name_Xpath", name))
			Reporter_ServiceNow.reportStep("The Name: "+name+" not found / could not be entered", "FAILURE");
		if(!enterAndChoose("NWSwitches_CIOwnerGroup_Xpath", CIOwnerGroup))
			Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" not found / could not be entered", "FAILURE");

		if(enterAndChoose("NWSwitches_SysManager_Xpath", SystemManager))
			Reporter_ServiceNow.reportStep("All the Mandatory fields Name: "+name+", CI OwnerGroup: "+CIOwnerGroup+", System Manager: "+SystemManager+" are entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The System Manager: "+SystemManager+" not found / could not be entered", "FAILURE");

		return this;
	}


	public NetworkSwitchesPage fillOtherFields(String gxp, String location, String serialNumber, String IPAddress) {
		if(!selectByVisibleTextByXpath("NWSwitches_GxP_Xpath", gxp))
			Reporter_ServiceNow.reportStep("The type: "+gxp+" could not be selected in GxP", "FAILURE");
		if(!enterAndChoose("NWSwitches_Location_Xpath", location))
			Reporter_ServiceNow.reportStep("The location: "+location+" not found / could not be entered", "FAILURE");
		if(!enterByXpath("NWSwitches_SerialNumber_Xpath", serialNumber))
			Reporter_ServiceNow.reportStep("The SerialNumber: "+serialNumber+" not found / could not be entered", "FAILURE");
		if(enterByXpath("NWSwitches_IPAddress_Xpath", IPAddress))
			Reporter_ServiceNow.reportStep("The Values GxP: "+gxp+", Location: "+location+", Serial Number: "+serialNumber+", IP Address: "+IPAddress+" are filled in respective fields successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The IP Address: "+IPAddress+" not found / could not be entered", "FAILURE");

		return this;
	}

	public NetworkSwitchesPage addFilterforTwoValues(String filterType1, String filterCondition1, String filterValue1, String filterValue2,
			String filterType3, String filterCondition3, String filterType4, String filterCondition4){

		deleteFilters()
		.addFirstFilter(filterType1, filterCondition1, filterValue1)       
		.clickANDCondition()
		.addSecondFilter(filterType1, filterCondition1, filterValue2)
		.clickANDCondition()
		.addthirdFilter(filterType3, filterCondition3)
		.clickANDCondition()
		.addFourthFilter(filterType4, filterCondition4);
		Reporter_ServiceNow.reportStep("The Class values: "+filterType1+" "+filterCondition1+" "+ filterValue1+","+ filterValue2+", "+filterType3+" "+filterCondition3+", "+filterType4+" "+filterCondition4+" are selected successfully", "SUCCESS");
		return this;
	}
	public NetworkSwitchesPage addFilterforOneValues(String filterType1, String filterCondition1, String filterValue1, 
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
	public NetworkSwitchesPage selectGxpWithouReport(String gxp) {
		if(!selectByVisibleTextByXpath("NWSwitches_GxP_Xpath", gxp))
			Reporter_ServiceNow.reportStep("The type: "+gxp+" could not be selected in GxP", "FAILURE");	
		return this;
}
	public NetworkSwitchesPage verifyAddCIRelationship() {

		scrollToElementByXpath("SA_AddCIRelationship_Xpath");

		if(isExistByXpath("SA_AddCIRelationship_Xpath"))
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon is appeared for Active CI as expected.", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon is appeared, hence failure.", "FAILURE");

		return this;
	}
}