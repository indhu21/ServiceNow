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

public class ServerWindowsPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;
	private String CMDBTaskNumber;

	public ServerWindowsPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();		

		// Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not Windows Server Page", "FAILURE");
		}

	}

	public ServerWindowsPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");
		return this;
	}

	public ServerWindowsPage verifyAllMandatoryFields(){

		// you need to change the mandatory fields when the application changes
		String[] mandatoryFields = {"WinSer_Name_Xpath",
				"WinSer_CIOwnerMand_Xpath",
		"WinSer_SysManagerMand_Xpath"};

		String[] mandatoryFieldsDesc = {"Name",
				"CI Owner Group","System Manager"};

		verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);
		return this;

	}

	public ServerWindowsPage verifyAllServerWindowsFields(){

		// you need to change the non mandatory fields when the application changes
		String[] Fields = { "WinSer_Name_Xpath",
				"WinSer_Manufacturer_Xpath",
				"WinSer_ModelID_Xpath",
				"WinSer_SerialNumber_Xpath",
				"WinSer_GxP_Xpath",
				"WinSer_Environment_Xpath",
				"WinSer_Location_Xpath",
				"WinSer_IpAddress_Xpath",
				"WinSer_Description_Xpath",
				"WinSer_OperationalStatus_Xpath",
				"WinSer_CIOwnerGroup_Xpath",
				"WinSer_SystemManager_Xpath",
				"WinSer_SecondarySystemManager_Xpath",
				"WinSer_OperatingSystem_Xpath",
				"WinSer_OSVersion_Xpath",
				"WinSer_OSServicePack_Xpath",
				"WinSer_RAM_Xpath",
				"WinSer_CPUSpeed_Xpath",
				"WinSer_CPUCount_Xpath",
				"WinSer_CPUCoreCount_Xpath"
		};

		String[] FieldsDesc = { "Name",				
				"Manufacturer",
				"Model ID",
				"Serial Number",
				"GxP",
				"Environment",
				"Location",
				"Ip Address",
				"Description",
				"Operational Status",
				"CI Owner Group",
				"System Manager ",
				"Secondary System Manage",
				"Operating System",
				"OS Version",
				"OSServicePack",
				"RAM",
				"CPU Speed",
				"CPU Count",
				"CPU Core Count"
		};

		verifyFieldsExistByXpath(Fields, FieldsDesc);
		return this;

	}


	public ServerWindowsPage verifyAllReadOnlyFields(){

		// you need to change the read only fields when the application changes
		String[] readOnlyFields = {"WinSer_OperationalStatus_Xpath"};

		String[] readOnlyFieldsDesc = {"Operational Status"};

		// Verify read only
		verifyDisabledFieldsByXpath(readOnlyFields, readOnlyFieldsDesc);
		return this;

	}

	public ServerWindowsPage enterName(String name) {
		if(enterByXpath("WinSer_Name_Xpath", name))
			Reporter_ServiceNow.reportStep("The Name: "+name+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Name: "+name+" not found / could not be entered", "FAILURE");
		return this;
	}

	public ServerWindowsPage enterAndChooseCIOwnerGroup(String CIOwnerGroup) {
		if(enterAndChoose("WinSer_CIOwnerGroup_Xpath", CIOwnerGroup))
			Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" not found / could not be entered", "FAILURE");
		return this;
	}

	public ServerWindowsPage enterAndChooseSystemManager(String SystemManager) {
		if(enterAndChoose("WinSer_SystemManager_Xpath", SystemManager))
			Reporter_ServiceNow.reportStep("The System Manager: "+SystemManager+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The System Manager: "+SystemManager+" not found / could not be entered", "FAILURE");
		return this;
	}

	public IncidentsListPage clickSubmit() {

		if(clickById("Submit_Id"))
			Reporter_ServiceNow.reportStep("The Submit button is clicked and CI is created successfully", "SUCCESS");
		else 
			Reporter_ServiceNow.reportStep("The Submit button could not be clicked or not found", "FAILURE");
		return new IncidentsListPage(driver);
	}

	public ServerWindowsPage selectEnvironment(String environment) {
		if(selectByVisibleTextByXpath("WinSer_Environment_Xpath", environment))
			Reporter_ServiceNow.reportStep("The type: "+environment+" is selected in Environment", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The type: "+environment+" could not be selected in Environment", "FAILURE");
		return this;
	}

	public ServerWindowsPage enterAndChooseLocation(String location) {
		if(enterAndChoose("WinSer_Location_Xpath", location))
			Reporter_ServiceNow.reportStep("The location: "+location+" is entered in Location", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The location: "+location+" not found / could not be entered", "FAILURE");
		return this;
	}

	public ServerWindowsPage clickSetBuild() {

		if(clickById("SA_Setbuild_Id"))
			Reporter_ServiceNow.reportStep("The Set to Build button is clicked successfully and task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Set to Build button could not be clicked or not found", "FAILURE");
		return this;
	}

	public ServerWindowsPage getBuildConfirmation(String text) {		

		if(!getTextByXpath("SA_BuildConfirmation_Xpath").startsWith(text))
			Reporter_ServiceNow.reportStep("The Proposal for CI modification already exist.", "FAILURE");
		return this;
	}

	public String getCMDBTaskNumber() {
		CMDBTaskNumber = getTextByXpath("SA_ApplicationNumber_Xpath");

		if(CMDBTaskNumber.equals(""))
			Reporter_ServiceNow.reportStep("The CMDB task number is blank for newly created incident", "FAILURE");
		return CMDBTaskNumber;
	}

	public ServerWindowsPage clickApprove() {

		if(clickByXpath("Approve_Xpath"))
			Reporter_ServiceNow.reportStep("The approved button is clicked successfully and the task approved as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Approve button could not be clicked or not found", "FAILURE");
		return this;
	}


	public ServerWindowsPage clickAddCIRelationship() {

		if(scrollToElementByXpath("SA_AddCIRelationship_Xpath"))
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon appeared as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon found", "FAILURE");

		
		if(clickByXpath("SA_AddCIRelationship_Xpath"))
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon is clicked successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon could not be clicked or not found", "FAILURE");
		return this;
	}


	public ServerWindowsPage clickSetRetire() {

		if(clickById("SA_SetRetire_Id"))
			Reporter_ServiceNow.reportStep("The Retire button is clicked successfully and task is created and assigned to the System Manager as expected.", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Retire button could not be clicked or not found", "WARNING");
		return this;
	}

	public ServerWindowsPage clickDecommission() {

		if(clickById("SA_SetDecom_Id"))
			Reporter_ServiceNow.reportStep("The Decommission button is clicked successfully and task is created and assigned to the System Manager as expected.", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Decommission button could not be clicked or not found", "WARNING");
		return this;
	}

	public ServerWindowsPage clickLinkName(String linkName){

		// click the first Incident Link
		scrollToElement(driver.findElement(By.linkText(linkName)));
		if(clickLink(linkName, false))
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item is clicked successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item could not be clicked", "WARNING");
		return new ServerWindowsPage(driver);
	}
	public ServerWindowsPage verifyAllFieldsReadOnly(){

		// you need to change the non mandatory fields when the application changes
		String[] Fields = { 
				"WinSer_NameReadOnly_Xpath",
				"WinSer_ManuReadOnly_Xpath",
				"WinSer_ModelIDReadOnly_Xpath",
				"WinSer_SerialNoReadOnly_Xpath",
				"WinSer_GxPReadOnly_Xpath",
				"WinSer_EnvironmentReadOnly_Xpath",
				"WinSer_LocationReadOnly_Xpath",
				"WinSer_IPAddressReadOnly_Xpath",
				"WinSer_DescReadOnly_Xpath",
				"WinSer_ReadOnlyOperationalStatus_Xpath",
				"WinSer_CIOwnerReadOnly_Xpath",
				"WinSer_SysManagerReadOnly_Xpath",
				"WinSer_SecSysManagerReadOnly_Xpath",
				"WinSer_OperatingSysReadOnly_Xpath",
				"WinSer_OSVersionReadOnly_Xpath",
				"WinSer_OSServicePackReadOnly_Xpath",
				"WinSer_RAMReadOnly_Xpath",
				"WinSer_CPUSpeedReadOnly_Xpath",
				"WinSer_CPUCountReadOnly_Xpath",
				"WinSer_CPUCoreCountReadOnly_Xpath"
		};

		String[] FieldsDesc = {
				"Name",				
				"Manufacturer",
				"Model ID",
				"Serial Number",
				"GxP",			
				"Environment",
				"Location",
				"Ip Address",
				"Description",
				"Operational Status",
				"CI Owner Group",
				"System Manager ",
				"Secondary System Manage",
				"Operating System",
				"OS Version",
				"OS Service Pack",
				"RAM",
				"CPU Speed",
				"CPU Count",
				"CPU Core COunt"
		};

		verifyDisabledFieldsByXpath(Fields, FieldsDesc);
		return this;
	}	
	public ServerWindowsPage selectDependsOn() {

		if(clickByXpath("SA_DependOn_Xpath"))
			Reporter_ServiceNow.reportStep("The Depend On is selected in the Available Relationships successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Depend On could not be selected in the Available Relationships or not found", "FAILURE");
		Wait(5000);
		return this;
	}
	public ServerWindowsPage selectFirstAvailableCIs() {

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
	public ServerWindowsPage verifyDependsOnRelationshipAppears() {

		scrollToElementByXpath("SA_DependsOnRelationshipAppears_XPath");
		if(getTextByXpath("SA_DependsOnRelationshipAppears_XPath").contains("Depends on"))		
			Reporter_ServiceNow.reportStep("The depends on relationship appears under the related items bar as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The depends on relationship does not appears under the related items bar", "FAILURE");
		return this;
	}

	public ServerWindowsPage verifyUsedByRelationshipAppears() {

		scrollToElementByXpath("SA_DependsOnRelationshipAppears_XPath");
		if(getTextByXpath("SA_DependsOnRelationshipAppears_XPath").contains("Used by"))		
			Reporter_ServiceNow.reportStep("The Used By relationship appears under the related items bar as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The  Used By relationship does not appears under the related items bar", "FAILURE");
		return this;
	}

	public ServerWindowsPage clickSetActive() {

		if(!clickById("SA_SetActive_Id"))
			Reporter_ServiceNow.reportStep("The Set Active button could not be clicked or not found", "FAILURE");
		else{
			verifyAlert();
			Reporter_ServiceNow.reportStep("The Set Active button is clicked successfully and task is created and assigned to the System Manager as expected.", "SUCCESS");}
		
		return this;
	}	

	public ServerWindowsPage enterChangeRequestId(String changeRequestId){

		Wait(2000);
		if(enterByXpath("CMDBAPP_ChangeRequestID_Xpath", changeRequestId))
			Reporter_ServiceNow.reportStep("The Change request Id value : "+changeRequestId+" is entered successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Change request Id value : "+changeRequestId+" could not be entered or not found", "FAILURE");
		return this;
	}	

	public ServerWindowsPage selectUsedBy() {

		if(clickByXpath("SA_UsedBy_Xpath"))
			Reporter_ServiceNow.reportStep("The Used By is selected in the Available Relationships successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Used By could not be selected in the Available Relationships or not found", "FAILURE");
		Wait(5000);
		return this;
	}
	public ServerWindowsPage verifyOperationalstatus(String operationalstatus){
		scrollToElementByXpath("WinSer_OperationalStatus_Xpath");
		Wait(3000);
		if(getDefaultValueByXpath("WinSer_OperationalStatus_Xpath").contains(operationalstatus))
			Reporter_ServiceNow.reportStep("The operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The operational status value "+operationalstatus+" could not be matched", "FAILURE");
		return this; 

	}
	public ServerWindowsPage verifyReadOnlyOperationalstatus(String operationalstatus){

		Wait(1000);

		if(getDefaultValueByXpath("WinSer_ReadOnlyOperationalStatus_Xpath").contains(operationalstatus))
			Reporter_ServiceNow.reportStep("The operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The operational status value could not be matched", "FAILURE");
		return this; 

	}
	public ServerWindowsPage clickORCondition() {

		if(!clickByXpath("CIS_ORCondition_Xpath"))				
			Reporter_ServiceNow.reportStep("The OR button  could not be clicked or not found", "FAILURE");
		return this;
	}

	public ServerWindowsPage clickANDCondition() {

		if(!clickByXpath("CIS_AndCondition1_Xpath"))				
			Reporter_ServiceNow.reportStep("The AND button  could not be clicked or not found", "FAILURE");



		return this;
	}

	public ServerWindowsPage addFirstFilter(String filterType, String filterCondition, String filterValue){
		if(addFilters("CIS_FirstFilterType1_Xpath", filterType, "CIS_FilterCondition1_Xpath", filterCondition, "CIS_FilterValueSelect1_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public ServerWindowsPage addSecondFilter(String filterType, String filterCondition, String filterValue){
		if(addFilters("CIS_FirstFilterType2_Xpath", filterType, "CIS_FilterCondition2_Xpath", filterCondition, "CIS_FilterValue2_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public ServerWindowsPage addThirdFilter(String filterType, String filterCondition, String filterValue){
		if(addFilters("CIS_FirstFilterType3_Xpath", filterType, "CIS_FilterCondition3_Xpath", filterCondition, "CIS_FilterValue3_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}
	public ServerWindowsPage addFourthFilter(String filterType, String filterCondition, String filterValue){
		if(addFilters("CIS_FirstFilterType4_Xpath", filterType, "CIS_FilterCondition4_Xpath", filterCondition, "CIS_FilterValue4_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public ServerWindowsPage addFifthFilter(String filterType, String filterValue){
		if(addFilters("CIS_FirstFilterType5_Xpath", filterType, "CIS_FilterCondition5_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue +" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public ServerWindowsPage addSixthFilter(String filterType, String filterValue){
		if(addFilters("CIS_FirstFilterType6_Xpath", filterType, "CIS_FilterCondition6_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue +" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}
	public ServerWindowsPage addThirdFilter(String filterType, String filterValue){
		if(addFilters("CIS_FirstFilterType3_Xpath", filterType, "CIS_FilterCondition3_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue +" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public ServerWindowsPage addFourthFilter(String filterType, String filterValue){
		if(addFilters("CIS_FirstFilterType4_Xpath", filterType, "CIS_FilterCondition4_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue +" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public ServerWindowsPage deleteFilters(){	

		deleteAllFilters();
		return this;
	}

	public ServerWindowsPage verifyEnteredFileds( String sysManager, String CIOwnerGroup, String location, String environment){

		if(!getAttributeByXpath("WinSer_SystemManager_Xpath","value").contains(sysManager))

			if(!getAttributeByXpath("WinSer_CIOwnerGroup_Xpath","value").contains(CIOwnerGroup))
				Reporter_ServiceNow.reportStep("The  "+CIOwnerGroup+" is NOT available in CI Owner Group","FAILURE");

		if(!getAttributeByXpath("WinSer_Location_Xpath","value").contains(location))
			Reporter_ServiceNow.reportStep("The  "+location+" is NOT available in Location","FAILURE");

		if(getDefaultValueByXpath("WinSer_Environment_Xpath").contains( environment))
			Reporter_ServiceNow.reportStep("The "+sysManager+","+CIOwnerGroup+","+location+","+environment+" are available in the fields as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The "+environment+" is NOT available in Environment","FAILURE");
		return this;
	}

	public ServerWindowsPage searchAvailableCI(String availableCI){
		if(!enterByXpath("RE_AvaiCI_Xpath",availableCI))
			Reporter_ServiceNow.reportStep("The Available CI could not be found","FAILURE");

		Wait(2000);

		return this;
	}

	public ServerWindowsPage enterAllMandatoryFields(String name,String CIOwnerGroup,String systemManager) {
		if(!enterByXpath("WinSer_Name_Xpath", name))
			Reporter_ServiceNow.reportStep("The Name: "+name+" not found / could not be entered", "FAILURE");

		if(!enterAndChoose("WinSer_CIOwnerGroup_Xpath", CIOwnerGroup))			
			Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" not found / could not be entered", "FAILURE");

		if(enterAndChoose("WinSer_SystemManager_Xpath", systemManager))
			Reporter_ServiceNow.reportStep("All the Mandatory fields "+name+" ,"+CIOwnerGroup+" ,"+systemManager+" are entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The System Manager: "+systemManager+" not found / could not be entered", "FAILURE");
		return this;

	}
	public ServerWindowsPage selectEnvironmentAndLocation(String environment, String location) {
		if(!selectByVisibleTextByXpath("WinSer_Environment_Xpath", environment))
			Reporter_ServiceNow.reportStep("The type: "+environment+" could not be selected in Environment", "FAILURE");
		if(enterAndChoose("WinSer_Location_Xpath", location))
			Reporter_ServiceNow.reportStep("The "+environment+" and "+location+" are entered in Environment and Location as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The location: "+location+" not found / could not be entered", "FAILURE");
		return this;
	}

	public ServerWindowsPage addFilterForDependsOn(String filterType, String filterCondition, String filterValue1,String filterValue2, String filterValue3, String filterValue4, String filterType5, String filterCondition5,String filterType6, String filterCondition6 ){
		deleteAllFilters();
		if(!addFilters("CIS_FirstFilterType1_Xpath", filterType, "CIS_FilterCondition1_Xpath", filterCondition, "CIS_FilterValueSelect1_Xpath", filterValue1))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue1 +" could not be selected","FAILURE");
		if(!clickByXpath("CIS_ORCondition_Xpath"))				
			Reporter_ServiceNow.reportStep("The OR button  could not be clicked or not found", "FAILURE");
		if(!addFilters("CIS_FirstFilterType2_Xpath", filterType, "CIS_FilterCondition2_Xpath", filterCondition, "CIS_FilterValue2_Xpath", filterValue2))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue2 +" could not be selected","FAILURE");
		if(!clickByXpath("CIS_ORCondition_Xpath"))				
			Reporter_ServiceNow.reportStep("The OR button  could not be clicked or not found", "FAILURE");
		if(!addFilters("CIS_FirstFilterType3_Xpath", filterType, "CIS_FilterCondition3_Xpath", filterCondition, "CIS_FilterValue3_Xpath", filterValue3))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue3 +" could not be selected","FAILURE");
		if(!clickByXpath("CIS_ORCondition_Xpath"))				
			Reporter_ServiceNow.reportStep("The OR button  could not be clicked or not found", "FAILURE");
		if(!addFilters("CIS_FirstFilterType4_Xpath", filterType, "CIS_FilterCondition4_Xpath", filterCondition, "CIS_FilterValue4_Xpath", filterValue4))
			Reporter_ServiceNow.reportStep("The Filter value: "+ filterType+" "+ filterCondition+" "+ filterValue4 +" could not be selected","FAILURE");
		if(!clickByXpath("CIS_AndCondition1_Xpath"))				
			Reporter_ServiceNow.reportStep("The AND button  could not be clicked or not found", "FAILURE");
		if(!addFilters("CIS_FirstFilterType5_Xpath", filterType5, "CIS_FilterCondition5_Xpath", filterCondition5))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType5+" "+ filterCondition5 +" could not be selected","FAILURE");
		if(!clickByXpath("CIS_AndCondition1_Xpath"))				
			Reporter_ServiceNow.reportStep("The AND button  could not be clicked or not found", "FAILURE");
		if(addFilters("CIS_FirstFilterType6_Xpath", filterType6, "CIS_FilterCondition6_Xpath", filterCondition6))
			Reporter_ServiceNow.reportStep("The Class values: "+ filterValue1+","+ filterValue2+","+filterValue3+", "+ filterValue4 +" and "+ filterType5+" "+ filterCondition5 +" and "+filterType6+" "+filterCondition6+" are setted successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Class value "+ filterType6+" "+ filterCondition6 +" could not be selected","FAILURE");

		return this;
	}


	public ServerWindowsPage addFilterForUsedBy(String filterType1, String filterCondition1, String filterValue1,String filterType2, String filterCondition2, String filterValue2, String filterType5, String filterCondition5,String filterType6, String filterCondition6){
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
	public ServerWindowsPage selectGXP(String gxp) {
		if(!selectByVisibleTextByXpath("WinSer_GxP_Xpath", gxp))
			Reporter_ServiceNow.reportStep("The type: "+gxp+" could not be selected in GXP", "FAILURE");
		return this;
	}
	
	public ServerWindowsPage verifyAddCIRelationship() {

		scrollToElementByXpath("SA_AddCIRelationship_Xpath");
		
		if(isExistByXpath("SA_AddCIRelationship_Xpath"))
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon appeared for Active CI as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon found", "FAILURE");

		return this;
	}
}
