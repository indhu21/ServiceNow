package Pages_ServiceNow;


import java.util.List;

import org.apache.poi.xwpf.usermodel.ISDTContents;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.thoughtworks.selenium.webdriven.commands.GetValue;

import utils.ColorUtils;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class StoragePoolsPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public StoragePoolsPage(RemoteWebDriver driver) {
		this.driver = driver;
		switchToMainFrame();		
		// Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not Pools Page", "FAILURE");
		}
	}

	public StoragePoolsPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");

		return this;
	}

	public StoragePoolsPage verifyAllMandatoryFields(){
		switchToFrame("Frame_Main");

		// you need to change the mandatory fields when the application changes
		String[] mandatoryFields = {"StrPools_NameMand_Xpath","StrPools_CIOwnerGroupMand_Xpath","StrPools_SystemManagerMand_Xpath"};

		String[] mandatoryFieldsDesc = {"Name","CIOwnerGroup","SystemManager"};

		verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);

		return this;

	}

	public StoragePoolsPage verifyAllStoragePoolsFields(){

		// you need to change the non mandatory fields when the application changes
		String[] Fields = { 
				"StrPools_Name_Xpath","StrPools_Manufacturer_Xpath","StrPools_GxP_Xpath","StrPools_Environment_Xpath",
				"StrPools_Location_Xpath","StrPools_AggregateName_Xpath","StrPools_Description_Xpath","StrPools_OperationalStatus_Xpath",
				"StrPools_CIOwnerGroup_Xpath","StrPools_SystemManager_Xpath","StrPools_SecondarySystemManager_Xpath"	};

		String[] FieldsDesc = {"Name"," Manufacturer"," GxP","Environment",
				"Location","Path","Description","Operational Status","CI Owner Group",
				"System Manager","Secondary System Manager"};

		verifyFieldsExistByXpath(Fields, FieldsDesc);

		return this;

	}


	public StoragePoolsPage verifyAllReadOnlyFields(){

		// you need to change the read only fields when the application changes
		String[] readOnlyFields = {"StrPools_OperationalStatus_Xpath"};

		String[] readOnlyFieldsDesc = {"Operational Status"};


		// Verify read only
		verifyDisabledFieldsByXpath(readOnlyFields, readOnlyFieldsDesc);

		return this;

	}

	public StoragePoolsPage enterName(String name) {

		if(enterByXpath("StrPools_Name_Xpath", name))
			Reporter_ServiceNow.reportStep("The Name: "+name+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Name: "+name+" not found / could not be entered", "FAILURE");

		return this;
	}

	public StoragePoolsPage enterAndChooseCIOwnerGroup(String CIOwnerGroup) {
		if(enterAndChoose("StrPools_CIOwnerGroup_Xpath", CIOwnerGroup))
			Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" not found / could not be entered", "FAILURE");

		return this;
	}

	public StoragePoolsPage enterAndChooseSystemManager(String SystemManager) {
		if(enterAndChoose("StrPools_SystemManager_Xpath", SystemManager))
			Reporter_ServiceNow.reportStep("The System Manager: "+SystemManager+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The System Manager: "+SystemManager+" not found / could not be entered", "FAILURE");

		return this;
	}

	public ListPage clickSubmit() {

		if(clickById("Submit_Id"))
			Reporter_ServiceNow.reportStep("The Submit button is clicked and CI is created successfully", "SUCCESS");
		else 
			Reporter_ServiceNow.reportStep("The Submit button could not be clicked or not found", "FAILURE");

		return new ListPage(driver);
	}

	public StoragePoolsPage selectGxP(String gxp) {
		if(selectByVisibleTextByXpath("StrPools_GxP_Xpath", gxp))
			Reporter_ServiceNow.reportStep("The type: "+gxp+" is selected in Gxp", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The type: "+gxp+" could not be selected in Gxp", "FAILURE");

		return this;
	}
	public StoragePoolsPage selectEnvironment(String environment) {
		if(selectByVisibleTextByXpath("StrPools_Environment_Xpath", environment))
			Reporter_ServiceNow.reportStep("The type: "+environment+" is selected in Environment", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The type: "+environment+" could not be selected in Environment", "FAILURE");

		return this;
	}

	public StoragePoolsPage enterAndChooseLocation(String location) {
		if(enterAndChoose("StrPools_Location_Xpath", location))
			Reporter_ServiceNow.reportStep("The value: "+location+" is entered  in Location", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The location: "+location+" not found / could not be entered", "FAILURE");

		return this;
	}

	public StoragePoolsPage clickSetBuild() {

		if(clickById("SA_Setbuild_Id"))
			Reporter_ServiceNow.reportStep("The Set to Build button is clicked successfully and task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Set to Build button could not be clicked or not found", "FAILURE");

		return this;
	}

	public StoragePoolsPage getBuildConfirmation(String text) {		
		if(!getTextByXpath("SA_BuildConfirmation_Xpath").startsWith(text))
			Reporter_ServiceNow.reportStep("The Proposal for CI modification already exist.", "FAILURE");
		return this;

	}

	public StoragePoolsPage clickApprove() {

		if(clickByXpath("Approve_Xpath"))
			Reporter_ServiceNow.reportStep("The approved button is clicked successfully and the task approved as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Approve button could not be clicked or not found", "FAILURE");

		return this;
	}


	public StoragePoolsPage clickAddCIRelationship() {

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


	public StoragePoolsPage clickSetRetire() {

		if(clickById("SA_SetRetire_Id"))
			Reporter_ServiceNow.reportStep("The Retire button is clicked successfully and task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Retire button could not be clicked or not found", "WARNING");

		return this;
	}

	public StoragePoolsPage clickDecommission() {

		if(clickById("SA_SetDecom_Id"))
			Reporter_ServiceNow.reportStep("The Decommission button is clicked successfully and task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Decommission button could not be clicked or not found", "WARNING");

		return this;
	}

	public StoragePoolsPage clickLinkName(String linkName){

		// click the first Incident Link
		scrollToElement(driver.findElement(By.linkText(linkName)));
		if(clickLink(linkName, false))
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item is clicked successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item could not be clicked", "WARNING");

		return new StoragePoolsPage(driver);
	}
	public StoragePoolsPage verifyAllFieldsReadOnly(){

		// you need to change the non mandatory fields when the application changes
		String[] Fields = { "StrPools_NameReadonly_Xpath",
				"StrPools_ManufacturerReadonly_Xpath",
				"StrPools_GxPReadonly_Xpath",				
				"StrPools_EnvironmentReadonly_Xpath",
				"StrPools_LocationReadonly_Xpath",
				"StrPools_AggregateNameReadonly_Xpath",
				"StrPools_DescReadonly_Xpath",
				"StrPools_OperStatusReadonly_Xpath",
				"StrPools_CIOwnerGroupReadonly_Xpath",
				"StrPools_SystemManagerReadonly_Xpath",
				"StrPools_SecSysManagerReadonly_Xpath"
		};

		String[] FieldsDesc = { "Name",
				"Manufacturer",
				"GxP",				
				"Environment",
				"Location",
				"Aggregate Name",
				"Description",
				"Operational Status",
				"CI Owner Group",
				"System Manager",
				"Secondary System Manager"				
		};

		verifyDisabledFieldsByXpath(Fields, FieldsDesc);

		return this;

	}	
	public StoragePoolsPage selectDependsOn() {

		if(clickByXpath("SA_DependOn_Xpath"))
			Reporter_ServiceNow.reportStep("The Depend On is selected in the Available Relationships successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Depend On could not be selected in the Available Relationships or not found", "FAILURE");

		Wait(5000);

		return this;
	}
	public StoragePoolsPage selectFirstAvailableCIs() {

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
	public StoragePoolsPage verifyDependsOnRelationshipAppears() {

		scrollToElementByXpath("SA_DependsOnRelationshipAppears_XPath");
		if(getTextByXpath("SA_DependsOnRelationshipAppears_XPath").contains("Depends on"))		
			Reporter_ServiceNow.reportStep("The depends on relationship appears under the related items bar as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The depends on relationship does not appears under the related items bar", "FAILURE");

		return this;
	}

	public StoragePoolsPage verifyUsedByRelationshipAppears() {
		scrollToElementByXpath("SA_DependsOnRelationshipAppears_XPath");
		if(getTextByXpath("SA_DependsOnRelationshipAppears_XPath").contains("Used by"))		
			Reporter_ServiceNow.reportStep("The Used By relationship appears under the related items bar as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The  Used By relationship does not appears under the related items bar", "FAILURE");
		return this;
	}

	public StoragePoolsPage clickSetActive() {

		if(!clickById("SA_SetActive_Id"))
			Reporter_ServiceNow.reportStep("The Set Active button could not be clicked or not found", "FAILURE");
		else{	
			verifyAlert();
			Reporter_ServiceNow.reportStep("The Set Active button is clicked successfully and the task is created and assigned to the System Manager as expected", "SUCCESS");}

		return this;
	}	

	public StoragePoolsPage enterChangeRequestId(String changeRequestId){

		Wait(2000);
		if(enterByXpath("CMDBAPP_ChangeRequestID_Xpath", changeRequestId))
			Reporter_ServiceNow.reportStep("The Change request Id value : "+changeRequestId+" is entered successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Change request Id value : "+changeRequestId+" could not be entered or not found", "FAILURE");

		return this;
	}	

	public StoragePoolsPage selectUsedBy() {

		if(clickByXpath("SA_UsedBy_Xpath"))
			Reporter_ServiceNow.reportStep("The Used By is selected in the Available Relationships successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Used By could not be selected in the Available Relationships or not found", "FAILURE");

		Wait(5000);

		return this;
	}
	public StoragePoolsPage verifyOperationalstatus(String operationalstatus){

		Wait(3000);

		if(getDefaultValueByXpath("StrPools_OperationalStatus_Xpath").contains(operationalstatus))
			Reporter_ServiceNow.reportStep("The operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The operational status value "+operationalstatus+" could not be matched", "FAILURE");

		return this; 

	}
	public StoragePoolsPage verifyReadOnlyOperationalstatus(String operationalstatus){

		Wait(1000);

		if(getDefaultValueByXpath("StrPools_OperStatusReadonly_Xpath").contains(operationalstatus))
			Reporter_ServiceNow.reportStep("The operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The operational status value could not be matched", "FAILURE");

		return this; 

	}
	public StoragePoolsPage clickORCondition() {

		if(!clickByXpath("CIS_ORCondition_Xpath"))				
			Reporter_ServiceNow.reportStep("The OR button  could not be clicked or not found", "FAILURE");

		return this;
	}

	public StoragePoolsPage clickANDCondition() {

		if(!clickByXpath("CIS_AndCondition1_Xpath"))				
			Reporter_ServiceNow.reportStep("The AND button  could not be clicked or not found", "FAILURE");
		return this;
	}

	public StoragePoolsPage deleteFilters(){	

		deleteAllFilters();
		return this;
	}

	public StoragePoolsPage verifyEnteredFileds( String gxp,String location, String environment){

		if(!getDefaultValueByXpath("StrPools_GxP_Xpath").contains(gxp))	
			Reporter_ServiceNow.reportStep("The "+gxp+" is NOT available in Gxp","FAILURE");

		if(!getAttributeByXpath("StrPools_Location_Xpath","value").contains(location))
			Reporter_ServiceNow.reportStep("The  "+location+" is NOT available in Location","FAILURE");

		if(getDefaultValueByXpath("StrPools_Environment_Xpath").contains( environment))
			Reporter_ServiceNow.reportStep("The values:"+gxp+","+location+","+environment+" are available in GxP,Location,Environment fields as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The  "+environment+" is NOT available in Environment","FAILURE");
		return this;
	}

	public StoragePoolsPage addFirstFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType1_Xpath", filterType, "CIS_FilterCondition1_Xpath", filterCondition, "CIS_FilterValueSelect1_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+ " "+ filterCondition+ " "+ filterValue+ " could not be selected","FAILURE");

		return this;
	}

	public StoragePoolsPage addSecondFilter(String filterType, String filterValue){
		if(!addFilters("CIS_FirstFilterType2_Xpath", filterType, "CIS_FilterCondition2_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue+" could not be selected","FAILURE");

		return this;
	}

	public StoragePoolsPage addthirdFilter(String filterType, String filterValue){
		if(!addFilters("CIS_FirstFilterType3_Xpath", filterType, "CIS_FilterCondition3_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue+" could not be selected","FAILURE");

		return this;
	}

	public StoragePoolsPage enterAllMandatoryFields(String name,String CIOwnerGroup,String systemManager) {
		if(!enterByXpath("StrPools_Name_Xpath", name))
			Reporter_ServiceNow.reportStep("The Name: "+name+" not found / could not be entered", "FAILURE");

		if(!enterAndChoose("StrPools_CIOwnerGroup_Xpath", CIOwnerGroup))   
			Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" not found / could not be entered", "FAILURE");

		if(enterAndChoose("StrPools_SystemManager_Xpath", systemManager))
			Reporter_ServiceNow.reportStep("All the Mandatory fields: "+name+" "+CIOwnerGroup+" "+systemManager+" are entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The System Manager: "+systemManager+" not found / could not be entered", "FAILURE");
		return this;

	}

	public StoragePoolsPage EnterGxpEnvLoc(String gxp,String environment, String location) {
		if(!selectByVisibleTextByXpath("StrPools_GxP_Xpath", gxp))
			Reporter_ServiceNow.reportStep("The type: "+gxp+" could not be selected in Gxp", "FAILURE");
		if(!selectByVisibleTextByXpath("StrPools_Environment_Xpath", environment))
			Reporter_ServiceNow.reportStep("The type: "+environment+" could not be selected in Environment", "FAILURE");
		if(enterAndChoose("StrPools_Location_Xpath", location))
			Reporter_ServiceNow.reportStep("The values: "+gxp+","+environment+","+location+" are entered in GxP,Environment,Location as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The location: "+location+" not found / could not be entered", "FAILURE");
		return this;
	}

	public StoragePoolsPage addFilterforOneValues(String filterType1, String filterCondition1, String filterValue1, 
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
	public StoragePoolsPage verifyAddCIRelationship() {

		scrollToElementByXpath("SA_AddCIRelationship_Xpath");

		if(isExistByXpath("SA_AddCIRelationship_Xpath"))
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon is appeared For Active CI as expected.", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon is appeared, hence failure.", "FAILURE");

		return this;
	}
	public StoragePoolsPage clickSetActiveforNegative() {

		if(!clickById("SA_SetActive_Id"))
			Reporter_ServiceNow.reportStep("The Set Active button could not be clicked or not found", "FAILURE");
		else{
			if(getTextByXpath("SA_BuildConfirmation_Xpath").endsWith("CI must have an upstream relationship defined before to be set active"))
				Reporter_ServiceNow.reportStep("The Set Active button is clicked successfully and "
						+ " The error message CI must have an upstream relationship defined before to be set active is appeared as expected", "SUCCESS");}

		return this;
	}	
	
}
