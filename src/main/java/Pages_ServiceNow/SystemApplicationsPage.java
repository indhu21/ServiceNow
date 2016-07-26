package Pages_ServiceNow;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class SystemApplicationsPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;
	private String CMDBTaskNumber;
	private String CMDBTaskName;
	private String name;
	public SystemApplicationsPage(RemoteWebDriver driver) {
		this.driver = driver;
		// Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not System/Applications Page", "FAILURE");
		}

		switchToMainFrame();
	}

	public SystemApplicationsPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");

		return this;
	}

	public SystemApplicationsPage verifyAllMandatoryFields(){

		// you need to change the mandatory fields when the application changes
		String[] mandatoryFields = {"SA_MandatoryName_Xpath",
				"SA_MandatoryCIOwnerGroup_Xpath",
		"SA_MandatorySystemManager_Xpath"};

		String[] mandatoryFieldsDesc = {"Name",
				"CI Owner Group","System Manager"};

		verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);
		return this;

	}

	public SystemApplicationsPage verifyAllSystemApplicationFields(){

		// you need to change the non mandatory fields when the application changes
		String[] Fields = { "SA_Name_Xpath",
				"SA_ApplicationCode_Xpath",
				"SA_Manufacturer_Xpath",
				"SA_GxP_Xpath",
				"SA_SOX_Xpath",
				"SA_Environment_Xpath",
				"SA_Location_Xpath",
				"SA_BusinessCriticality_Xpath",
				"SA_SystemGoLiveDate_Xpath",
				"SA_Description_Xpath",
				"SA_OperationalStatus_Xpath",
				"SA_CIOwnerGroup_Xpath",
				"SA_SystemManager_Xpath",
				"SA_SecondarySystemManage_Xpath",
				"SA_BusinessProcessOwner_Xpath",
				"SA_QAOwner_Xpath",
				"SA_ValidationLead_Xpath",
				"SA_QualityGroup_Xpath"
		};

		String[] FieldsDesc = { "Name",
				"Application Code",
				"Manufacturer",
				"GxP",
				"SOX",
				"Environment",
				"Location",
				"Business Criticality",
				"System Go Live Date",
				"Description",
				"Operational Status",
				"CI Owner Group",
				"System Manager ",
				"Secondary System Manage",
				"Business Process Owner",
				"QA Owner",
				"Validation Lead",
				"Quality Group"
		};

		verifyFieldsExistByXpath(Fields, FieldsDesc);
		return this;

	}


	public SystemApplicationsPage verifyAllReadOnlyFields(){

		// you need to change the read only fields when the application changes
		String[] readOnlyFields = {"SA_OperationalStatus_Xpath"};

		String[] readOnlyFieldsDesc = {"Operational Status"};


		// Verify read only
		verifyDisabledFieldsByXpath(readOnlyFields, readOnlyFieldsDesc);
		return this;

	}

	//	public SystemApplicationsPage enterName(String name) {
	//		if(enterByXpath("SA_Name_Xpath", name))
	//			Reporter_ServiceNow.reportStep("The Name: "+name+" is entered successfully", "SUCCESS");
	//		else
	//			Reporter_ServiceNow.reportStep("The Name: "+name+" not found / could not be entered", "FAILURE");
	//		return this;
	//	}
	//
	//	public SystemApplicationsPage enterAndChooseCIOwnerGroup(String CIOwnerGroup) {
	//		if(enterAndChoose("SA_CIOwnerGroup_Xpath", CIOwnerGroup))
	//			Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" is entered successfully", "SUCCESS");
	//		else
	//			Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" not found / could not be entered", "FAILURE");
	//		return this;
	//	}
	//
	//	public SystemApplicationsPage enterAndChooseSystemManager(String SystemManager) {
	//		if(enterAndChoose("SA_SystemManager_Xpath", SystemManager))
	//			Reporter_ServiceNow.reportStep("The System Manager: "+SystemManager+" is entered successfully", "SUCCESS");
	//		else
	//			Reporter_ServiceNow.reportStep("The System Manager: "+SystemManager+" not found / could not be entered", "FAILURE");
	//		return this;
	//	}

	public CmdbListPage clickSubmit() {

		if(clickById("Submit_Id"))
			Reporter_ServiceNow.reportStep("The Submit button is clicked and CI is created successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Submit button could not be clicked or not found", "FAILURE");
		return new CmdbListPage(driver);
	}


	public SystemApplicationsPage selectGxP(String gxp) {
		if(selectByVisibleTextByXpath("SA_GxP_Xpath",gxp))
			Reporter_ServiceNow.reportStep("The Value: "+gxp+" is selected in GxP successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Value: "+gxp+" could not be selected in GxP", "FAILURE");
		return this;
	}

	public SystemApplicationsPage selectSOX(String sox) {
		if(selectByVisibleTextByXpath("SA_SOX_Xpath", sox))
			Reporter_ServiceNow.reportStep("The Value: "+sox+" is selected in SOX successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Value: "+sox+" could not be selected in SOX", "FAILURE");
		return this;
	}

	public SystemApplicationsPage selectEnvironment(String environment) {
		if(selectByVisibleTextByXpath("SA_Environment_Xpath", environment))
			Reporter_ServiceNow.reportStep("The Value: "+environment+" is selected in Environment successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Value: "+environment+" could not be selected in Environment", "FAILURE");
		return this;
	}

	public SystemApplicationsPage enterAndChooseLocation(String location) {
		if(enterAndChoose("SA_Location_Xpath", location))
			Reporter_ServiceNow.reportStep("The location: "+location+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The location: "+location+" not found / could not be entered", "FAILURE");
		return this;
	}

	public SystemApplicationsPage selectBusinessCriticality(String businessCriticality) {
		if(selectByVisibleTextByXpath("SA_BusinessCriticality_Xpath", businessCriticality))
			Reporter_ServiceNow.reportStep("The Value: "+businessCriticality+" is selected in Business Criticality", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Value: "+businessCriticality+" could not be selected in Business Criticality", "FAILURE");
		return this;
	}

	public SystemApplicationsPage clickSetBuild() {

		if(clickById("SA_Setbuild_Id"))
			Reporter_ServiceNow.reportStep("The Set to Build button is clicked, task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Set to Build button could not be clicked or not found", "FAILURE");
		return this;
	}

	public SystemApplicationsPage getBuildConfirmation(String text) {		

		if(!getTextByXpath("SA_BuildConfirmation_Xpath").startsWith(text))
			Reporter_ServiceNow.reportStep("The Proposal for CI modification already exist.", "FAILURE");
		return this;
	}

	public SystemApplicationsPage clickApprove() {

		if(clickByXpath("Approve_Xpath"))
			Reporter_ServiceNow.reportStep("The approved button is clicked successfully and the task approved as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Approve button could not be clicked or not found", "FAILURE");
		return this;
	}


	public SystemApplicationsPage clickAddCIRelationship() {
		Wait(3000);
		scrollToElementByXpath("SA_AddCIRelationship_Xpath");

		if(clickByXpath("SA_AddCIRelationship_Xpath"))
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon is clicked successfully and the relationship editor screen appeared as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon could not be clicked or not found", "FAILURE");
		return this;
	}
	//indhu added
	public SystemApplicationsPage clickSetRetire() {

		if(clickById("SA_SetRetire_Id"))
			Reporter_ServiceNow.reportStep("The Retire button is clicked, task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Retire button could not be clicked or not found", "WARNING");
		return this;
	}

	public SystemApplicationsPage clickDecommission() {

		if(clickById("SA_SetDecom_Id"))
			Reporter_ServiceNow.reportStep("The Decommission button is clicked, task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Decommission button could not be clicked or not found", "WARNING");
		return this;
	}

	public SystemApplicationsPage clickLinkName(String linkName){

		Wait(2000);
		// click the first Incident Link
		scrollToElement(driver.findElement(By.linkText(linkName)));
		if(clickLink(linkName, false))
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item is clicked successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item could not be clicked", "WARNING");
		return new SystemApplicationsPage(driver);
	}
	public SystemApplicationsPage verifyAllFieldsReadOnly(){

		// you need to change the non mandatory fields when the application changes
		String[] Fields = { "SA_ReadOnlyName_Xpath",
				"SA_ReadOnlyApplicationCode_Xpath",
				"SA_ReadOnlyManufacturer_Xpath",
				"SA_ReadOnlyGxP_Xpath",
				"SA_ReadOnlySOX_Xpath",
				"SA_ReadOnlyEnvironment_Xpath",
				"SA_ReadOnlyLocation_Xpath",
				"SA_ReadOnlyBusinessCriticality_Xpath",
				"SA_ReadOnlySystemGoLiveDate_Xpath",
				"SA_ReadOnlyDescription_Xpath",
				"SA_ReadOnlyOperationalStatus_Xpath",
				"SA_ReadOnlyCIOwnerGroup_Xpath",
				"SA_ReadOnlySystemManager_Xpath",
				"SA_ReadOnlySecondarySystemManage_Xpath",
				"SA_ReadOnlyBusinessProcessOwner_Xpath",
				"SA_ReadOnlyQAOwner_Xpath",
				"SA_ReadOnlyValidationLead_Xpath",
				"SA_ReadOnlyQualityGroup_Xpath"
		};

		String[] FieldsDesc = { "Name",
				"Application Code",
				"Manufacturer",
				"GxP",
				"SOX",
				"Environment",
				"Location",
				"Business Criticality",
				"System Go Live Date",
				"Description",
				"Operational Status",
				"CI Owner Group",
				"System Manager ",
				"Secondary System Manage",
				"Business Process Owner",
				"QA Owner",
				"Validation Lead",
				"Quality Group"};

		verifyDisabledFieldsByXpath(Fields, FieldsDesc);

		return this;

	}	
	public SystemApplicationsPage selectDependsOn() {

		if(clickByXpath("SA_DependOn_Xpath"))
			Reporter_ServiceNow.reportStep("The Depend On is selected in the Available Relationships successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Depend On could not be selected in the Available Relationships or not found", "FAILURE");

		Wait(5000);
		return this;
	}
	public SystemApplicationsPage selectFirstAvailableCIs() {


		if(!clickByXpath("SA_FirstAvailableCI_Xpath"))			
			Reporter_ServiceNow.reportStep("The First Available CI could not be selected or not found", "FAILURE");

		if(!clickByXpath("ALERT_MoveBreach_ToSelected_Xpath"))
			Reporter_ServiceNow.reportStep("The First Available CI could not be moved", "FAILURE");
		Wait(3000);
		if(clickById("Ok_Id")){
			Reporter_ServiceNow.reportStep("The Highlighted CI appeared in the box on the right as expected", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The Save button could not be clicked or not found", "FAILURE");

		return this;
	}
	public SystemApplicationsPage verifyDependsOnRelationshipAppears() {
		Wait(5000);
		scrollToElementByXpath("SA_DependsOnRelationshipAppears_XPath");

		if(getTextByXpath("SA_DependsOnRelationshipAppears_XPath").contains("Depends on"))		
			Reporter_ServiceNow.reportStep("The depends on relationship appears under the related items bar as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The depends on relationship does not appears under the related items bar", "FAILURE");
		return this;
	}

	public SystemApplicationsPage verifyUsedByRelationshipAppears() {
		Wait(5000);
		scrollToElementByXpath("SA_DependsOnRelationshipAppears_XPath");
		if(getTextByXpath("SA_DependsOnRelationshipAppears_XPath").contains("Used by"))		
			Reporter_ServiceNow.reportStep("The Used By relationship appears under the related items bar as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The  Used By relationship does not appears under the related items bar", "FAILURE");
		return this;
	}

	public SystemApplicationsPage clickSetActive() {

		if(!clickById("SA_SetActive_Id"))
			Reporter_ServiceNow.reportStep("The Set Active button could not be clicked or not found", "FAILURE");
		else{
			verifyAlert();
			Reporter_ServiceNow.reportStep("The Set Active button is clicked and the task is created and assigned to the System Manager successfully", "SUCCESS");}
		return this;
	}	

	public SystemApplicationsPage enterChangeRequestId(String changeRequestId){

		Wait(2000);
		if(enterByXpath("CMDBAPP_ChangeRequestID_Xpath", changeRequestId))
			Reporter_ServiceNow.reportStep("The Change request Id value : "+changeRequestId+" is entered successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Change request Id value : "+changeRequestId+" could not be entered or not found", "FAILURE");
		return this;
	}	

	public SystemApplicationsPage selectUsedBy() {

		if(clickByXpath("SA_UsedBy_Xpath"))
			Reporter_ServiceNow.reportStep("The Used By is selected in the Available Relationships successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Used By could not be selected in the Available Relationships or not found", "FAILURE");

		Wait(5000);
		return this;
	}
	public SystemApplicationsPage verifyOperationalstatus(String operationalstatus){

		Wait(3000);
		if(getDefaultValueByXpath("SA_OperationalStatus_Xpath").contains(operationalstatus))
			Reporter_ServiceNow.reportStep("The operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The operational status value "+operationalstatus+" could not be matched", "FAILURE");
		return this; 

	}
	public SystemApplicationsPage verifyReadOnlyOperationalstatus(String operationalstatus){

		Wait(1000);
		if(getDefaultValueByXpath("SA_ReadOnlyOperationalStatus_Xpath").contains(operationalstatus))
			Reporter_ServiceNow.reportStep("The operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The operational status value could not be matched", "FAILURE");
		return this; 

	}
	public SystemApplicationsPage clickORCondition() {

		if(!clickByXpath("CIS_ORCondition_Xpath"))				
			Reporter_ServiceNow.reportStep("The OR button  could not be clicked or not found", "FAILURE");
		return this;
	}

	public SystemApplicationsPage clickANDCondition() {

		if(!clickByXpath("CIS_AndCondition1_Xpath"))				
			Reporter_ServiceNow.reportStep("The AND button  could not be clicked or not found", "FAILURE");
		return this;
	}


	public SystemApplicationsPage verifyEnteredFileds( String businessCriticality,String sox, String gxp, String location, String environment){	

		if(!(getDefaultValueByXpath("SA_BusinessCriticality_Xpath")).equals(businessCriticality))			
			Reporter_ServiceNow.reportStep("The "+businessCriticality+" is NOT available Business Criticality","FAILURE");

		if(!(getDefaultValueByXpath("SA_SOX_Xpath")).equals(sox))			
			Reporter_ServiceNow.reportStep("The "+sox+" is NOT available sox","FAILURE");

		if(!(getDefaultValueByXpath("SA_GxP_Xpath")).equals(gxp))			
			Reporter_ServiceNow.reportStep("The "+gxp+" is NOT available Gxp","FAILURE");

		if(!(getAttributeByXpath("SA_Location_Xpath","value")).equals(location))
			Reporter_ServiceNow.reportStep("The  "+location+" is NOT available in Location","FAILURE");

		if((getDefaultValueByXpath("SA_Environment_Xpath")).contains(environment))
			Reporter_ServiceNow.reportStep("The values Business Criticality: "+businessCriticality+", SOX: "+sox +", GXP: "+gxp+", Location: "+location+", Environment: "+environment+" are matched as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The  "+environment+" is NOT available in Location","FAILURE");

		return this;
	}

	public SystemApplicationsPage addFirstFilter(String filterType, String filterCondition, String filterValue){
		if(addFilters("CIS_FirstFilterType1_Xpath", filterType, "CIS_FilterCondition1_Xpath", filterCondition, "CIS_FilterValueSelect1_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public SystemApplicationsPage addSecondFilter(String filterType, String filterCondition, String filterValue){
		if(addFilters("CIS_FirstFilterType2_Xpath", filterType, "CIS_FilterCondition2_Xpath", filterCondition, "CIS_FilterValue2_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public SystemApplicationsPage addThirdFilter(String filterType, String filterCondition, String filterValue){
		if(addFilters("CIS_FirstFilterType3_Xpath", filterType, "CIS_FilterCondition3_Xpath", filterCondition, "CIS_FilterValue3_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}
	public SystemApplicationsPage addFourthFilter(String filterType, String filterCondition, String filterValue){
		if(addFilters("CIS_FirstFilterType4_Xpath", filterType, "CIS_FilterCondition4_Xpath", filterCondition, "CIS_FilterValue4_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public SystemApplicationsPage addFifthFilter(String filterType, String filterValue){
		if(addFilters("CIS_FirstFilterType5_Xpath", filterType, "CIS_FilterCondition5_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue +" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public SystemApplicationsPage addSixthFilter(String filterType, String filterValue){
		if(addFilters("CIS_FirstFilterType6_Xpath", filterType, "CIS_FilterCondition6_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue +" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public SystemApplicationsPage addSecondFilter(String filterType, String filterCondition){
		if(addFilters("CIS_FirstFilterType2_Xpath", filterType, "CIS_FilterCondition2_Xpath", filterCondition))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+" could not be selected","FAILURE");

		return this;
	}

	public SystemApplicationsPage addThirdFilter(String filterType, String filterCondition){
		if(addFilters("CIS_FirstFilterType3_Xpath", filterType, "CIS_FilterCondition3_Xpath", filterCondition))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" is selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" could not be selected","FAILURE");

		return this;
	}


	public SystemApplicationsPage deleteFilters(){	
		deleteAllFilters();
		return this;
	}

	public SystemApplicationsPage clickBusinessFunctionsTab() {

		if(clickByXpath("SA_BusinessFuntionTab_Xpath"))
			Reporter_ServiceNow.reportStep("The Business Functions Tab is selected successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Business Functions Tab could not be clicked or not found", "FAILURE");
		return this;
	}	


	public SystemApplicationsPage clickGroupsTab() {

		if(clickByXpath("SA_GroupsTab_Xpath"))
			Reporter_ServiceNow.reportStep("The Groups Tab is selected successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Business Functions Tab could not be clicked or not found", "FAILURE");
		return this;
	}	


	public SystemApplicationsPage clickTechnologyEnablersTab() {
		scrollToElementByXpath("SA_TechnologyTab_Xpath");
		if(clickByXpath("SA_TechnologyTab_Xpath")){
			Wait(5000);
			Reporter_ServiceNow.reportStep("The Technology Enablers Tab is selected successfully", "SUCCESS");}
		else	
			Reporter_ServiceNow.reportStep("The Technology Enablers Tab could not be clicked or not found", "FAILURE");
		return this;	}	


	public SystemApplicationsPage clickDepartmentsTab() {

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,20000)", "");
		Wait(500);

		if(clickByXpath("SA_DepartmentsTab_Xpath"))
			Reporter_ServiceNow.reportStep("The Departments Tab is selected successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Departments Tab could not be clicked or not found", "FAILURE");
		return this;
	}	

	public SystemApplicationsPage verifyRelatedLinks(){

		Wait(5000);
		String[] optionValues = getTextByXpath("SA_AvailableList_Xpath").split("\n");
		Wait(5000);
		if(!selectByIndexByXpath("SA_AvailableList_Xpath",0))
			Reporter_ServiceNow.reportStep("The "+optionValues[0]+" select value could not be selected", "FAILURE");


		if(clickByXpath("ALERT_MoveBreach_ToSelected_Xpath"))
			Reporter_ServiceNow.reportStep("The Highlighted CI appeared in the box on the right as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Right Arrow could not be clicked or not found", "FAILURE");


		String listValues = getTextByXpath("SA_SelectedList_Xpath");

		String[] value={listValues};
		if(!clickById("Save_Button_Id"))
			Reporter_ServiceNow.reportStep("The Save Button could not be clicked", "FAILURE");

		scrollToElementByXpath("SA_TechnologyTab_Xpath");
		if(!verifyAllText("ALERT_FirstAlert_Xpath", value))
			Reporter_ServiceNow.reportStep("The Save Button is clicked successfully and The Related Item: "+optionValues[0]+" was not added to the Related Links.", "FAILURE");
		else{
			Wait(2000);
			Reporter_ServiceNow.reportStep("The Related Item: "+optionValues[0]+" appeared under the Related Links as expected.", "SUCCESS");
		}
		return this;

	}

	public SystemApplicationsPage verifyRelatedLinksforTec(){

		Wait(5000);
		String[] optionValues = getTextByXpath("SA_AvailableList_Xpath").split("\n");
		Wait(5000);
		if(!selectByIndexByXpath("SA_AvailableList_Xpath",0))
			Reporter_ServiceNow.reportStep("The "+optionValues[0]+" select value could not be selected", "FAILURE");


		if(clickByXpath("ALERT_MoveBreach_ToSelected_Xpath"))
			Reporter_ServiceNow.reportStep("The First Highlighted CI appeared in the box on the right as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Right Arrow could not be clicked or not found", "FAILURE");

		if(!selectByIndexByXpath("SA_AvailableList_Xpath",1))
			Reporter_ServiceNow.reportStep("The "+optionValues[1]+" select value could not be selected", "FAILURE");


		if(clickByXpath("ALERT_MoveBreach_ToSelected_Xpath"))
			Reporter_ServiceNow.reportStep("The Second Highlighted CI appeared in the box on the right as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Right Arrow could not be clicked or not found", "FAILURE");

		String listValues = getTextByXpath("SA_SelectedList_Xpath");

		String[] value={listValues};
		if(!clickById("Save_Button_Id"))
			Reporter_ServiceNow.reportStep("The Save Button could not be clicked", "FAILURE");

		scrollToElementByXpath("SA_TechnologyTab_Xpath");
		if(!verifyAllText("ALERT_FirstAlert_Xpath", value))
			Reporter_ServiceNow.reportStep("The Multiple Related Item : "+optionValues[0]+" "+optionValues[1]+"was not added to the Related Links.", "FAILURE");
		else{
			Wait(2000);
			Reporter_ServiceNow.reportStep("The Save Button is clicked successfully and The Multiple Related Item : "+optionValues[0]+" "+optionValues[1]+" are appeared under the Related Links as expected.", "SUCCESS");
		}
		return this;
	}

	public SystemApplicationsPage clickEditButton1(){

		if(clickByXpath("Edit_Button1_Xpath"))
			Reporter_ServiceNow.reportStep("The Edit Button is clicked successfully and the Relationship Editor screen opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Edit Button could not be clicked or not found", "FAILURE");
		return this;	}

	public SystemApplicationsPage clickEditButton2(){

		if(clickByXpath("Edit_Button2_Xpath"))
			Reporter_ServiceNow.reportStep("The Edit Button is clicked successfully and the Relationship Editor screen opened as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Edit Button could not be clicked or not found", "FAILURE");
		return this;
	}

	public SystemApplicationsPage clickEditButton3(){

		if(clickByXpath("Edit_Button3_Xpath"))
			Reporter_ServiceNow.reportStep("The Edit Button is clicked successfully and the Relationship Editor screen opened as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Edit Button could not be clicked or not found", "FAILURE");
		return this;
	}

	public SystemApplicationsPage clickEditButton4(){

		if(clickByXpath("Edit_Button4_Xpath"))
			Reporter_ServiceNow.reportStep("The Edit Button is clicked successfully and the Relationship Editor screen opened as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Edit Button could not be clicked or not found", "FAILURE");
		return this;	}


	public SystemApplicationsPage changeLevel(String linkName){

		if(clickLink(linkName, false))
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Group Tab is clicked successfully and the group configuration screen will opened as expected.", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Group Tab could not be clicked", "FAILURE");

		String value=getDefaultValueByXpath("SA_LevelSelect_Xpath");
		if(selectByVisibleTextByXpath("SA_LevelSelect_Xpath", "Level 1"))
			Reporter_ServiceNow.reportStep("The Value: Level 1 is selected successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Value: Level 1 could not be clicked.", "FAILURE");

		if(clickById("CIS_UpdateButton_Id"))
			Reporter_ServiceNow.reportStep("The Update Button is clicked successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Update Button could not be clicked.", "FAILURE");

		//scrollToElementByXpath("SA_GroupsTab_Xpath");
		if(clickById("Save_Button_Id"))
			Reporter_ServiceNow.reportStep("The Save Button is clicked successfully and the level of the group updated on the related list.", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Save Button could not be clicked.", "FAILURE");

		return this;
	}

	public SystemApplicationsPage enterFilter(String name) {
		if(enterByXpath("SA_DepartFilter_Xpath", name))
			Reporter_ServiceNow.reportStep("The Value: "+name+" is entered in Collection Box successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Value: "+name+" not found / could not be entered", "FAILURE");
		return this;
	}

	public SystemApplicationsPage enterAndChooseSecSystem(String SecSystem) {
		if(enterAndChoose("SA_SecondarySystemManage_Xpath", SecSystem))
			Reporter_ServiceNow.reportStep("The Secondary System Manager: "+SecSystem+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Secondary System Manager: "+SecSystem+" not found / could not be entered", "FAILURE");
		return this;
	}

	public SystemApplicationsPage clickSave(){

		if(clickById("Save_Id"))
			Reporter_ServiceNow.reportStep("The Save Button is successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Save Button could not be clicked or not found", "FAILURE");
		return this;	}


	public SystemApplicationsPage verifyErrorMess(){

		if((getTextByXpath("SA_ErrorMess_Xpath")).contains("System Manager and Secondary System Manager can't be the same person"))
			Reporter_ServiceNow.reportStep("An invalid update error is generated as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("An invalid update error could be not be generate, check snapshot.", "FAILURE");
		return this;
	}

	public SystemApplicationsPage verifySecSystem(String secSystemManager){

		if((getAttributeByXpath("SA_SecondarySystemManage_Xpath","value")).contains(secSystemManager))			
			Reporter_ServiceNow.reportStep("The "+secSystemManager+" is matched in Secondary System Manager as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The "+secSystemManager+" is NOT available Secondary System Manager.","FAILURE");

		return this;
	}

	public SystemApplicationsPage verifyErrorMessage(){

		if((getTextByXpath("ErrorMessage_Xpath")).contains("Proposal for CI modification already exists for"))
			Reporter_ServiceNow.reportStep("The Error Message Proposal for CI modification already exists appeared as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Error Message Proposal for CI modification already exists could not be generated, check snapshot.", "FAILURE");
		return this;
	}

	public SystemApplicationsPage isDeletButtonNotPresent(String userName){

		if(IsElementNotPresentByXpath("Delete_Xpath"))
			Reporter_ServiceNow.reportStep("The Delete button does not exist for the user: "+ userName + " as expected" , "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Delete button does exist for the user: "+ userName + ", check snapshot","FAILURE");	

		return this;
	}

	public String getCMDBName() {
		CMDBTaskName = getAttributeByXpath("SA_Name_Xpath", "value");
		if(CMDBTaskName.equals(""))
			Reporter_ServiceNow.reportStep("The task Name is blank for newly created CMDB Task", "FAILURE");
		return CMDBTaskName;
	}	

	public SystemApplicationsPage verifySecSystemError(){

		if((getTextByXpath("ErrorMessage_Xpath")).contains("Proposal for CI modification already exist"))			
			Reporter_ServiceNow.reportStep("The Error Message Proposal for CI modification already exist is generated as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Error Message could not be generated, Check snapshot.","FAILURE");

		return this;
	}
	public SystemApplicationsPage enterAndChooseBusinessProcessOwner(String businessProcessOwner) {
		if(enterAndChoose("SA_BusinessProcessOwner_Xpath", businessProcessOwner))
			Reporter_ServiceNow.reportStep("The Business Process Owner: "+businessProcessOwner+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Business Process Owner:: "+businessProcessOwner+" not found / could not be entered", "FAILURE");
		return this;

	}

	public SystemApplicationsPage getAlertMessage(String errorMessage) {
		if (getTextAndAcceptAlert().contains(errorMessage)) {
			Wait(3000);
			Reporter_ServiceNow.reportStep("The Set Active button is clicked and The error message :" + errorMessage + " appeared as expected", "SUCCESS");
		} else
			Reporter_ServiceNow.reportStep("The error message :" + errorMessage + " did not appear", "FAILURE");

		return this;
	}

	public SystemApplicationsPage enterAndChooseQAOwner(String qaOwner) {
		if(enterAndChoose("SA_QAOwner_Xpath", qaOwner))
			Reporter_ServiceNow.reportStep("The QA Owner: "+qaOwner+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The QA Owner: "+qaOwner+" not found / could not be entered", "FAILURE");
		return this;

	}

	public SystemApplicationsPage enterAndChooseValidationLead(String validationlead) {
		if(enterAndChoose("SA_ValidationLead_Xpath", validationlead))
			Reporter_ServiceNow.reportStep("The Validation Lead: "+validationlead+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Validation Lead: "+validationlead+" not found / could not be entered", "FAILURE");
		return this;

	}

	public SystemApplicationsPage isAddCIRelationshipExists() {

		scrollToElementByXpath("SA_ToggleRelations_XPath");

		if(IsElementNotPresentByXpath("SA_AddCIRelationship_Xpath"))
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon is not available to establish relationships when the operational status is Decommissioned", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon could is available", "FAILURE");

		scrollToElementByXpath("SA_ReadOnlyName_Xpath");
		return this;
	}

	public SystemApplicationsPage clickSetActiveandVerifyErrormsgAppears() {

		if(!clickById("SA_SetActive_Id"))
			Reporter_ServiceNow.reportStep("The Set Active button could not be clicked or not found", "FAILURE");
		return this;
	}	

	public SystemApplicationsPage enterQAOwner(String qaOwner) {
		if(!enterByXpath("SA_QAOwner_Xpath", qaOwner))			
			Reporter_ServiceNow.reportStep("The QA Owner: "+qaOwner+" not found / could not be entered", "FAILURE");
		return this;

	}

	public SystemApplicationsPage enterValidationLead(String validationlead) {
		if(!enterByXpath("SA_ValidationLead_Xpath", validationlead))			
			Reporter_ServiceNow.reportStep("The Validation Lead: "+validationlead+" not found / could not be entered", "FAILURE");
		return this;

	}
	public SystemApplicationsPage enterAllMandatoryFields(String name,String CIOwnerGroup,String systemManager) {
		if(!enterByXpath("SA_Name_Xpath", name))
			Reporter_ServiceNow.reportStep("The Name: "+name+" not found / could not be entered", "FAILURE");

		if(!enterAndChoose("SA_CIOwnerGroup_Xpath", CIOwnerGroup))   
			Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" not found / could not be entered", "FAILURE");

		if(enterAndChoose("SA_SystemManager_Xpath", systemManager))
			Reporter_ServiceNow.reportStep("All the Mandatory fields Name: "+name+", CI OwnerGroup: "+CIOwnerGroup+", System Manager: "+systemManager+" are entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The System Manager: "+systemManager+" not found / could not be entered", "FAILURE");
		return this;

	}

	public SystemApplicationsPage verifyCMDBTaskInformation(String task) {


		int column1=getColumnIndex1("CMDBTask_TableHeading_Xpath", "Attribute");
		int column2=getColumnIndex1("CMDBTask_TableHeading_Xpath", "Old Value");
		int column3=getColumnIndex1("CMDBTask_TableHeading_Xpath", "New Value");

		String text1=getTextByXpath("(//*[@class='vt'])"+"["+column1+"]", false);
		String text2=getTextByXpath("(//*[@class='vt'])"+"["+column2+"]", false);
		String text3=getTextByXpath("(//*[@class='vt'])"+"["+column3+"]", false);

		//	System.out.println(getTextByXpath("(//*[@class='vt'])"+"["+column+"]", false));
		if(!text1.equals("manufacturer"))
			Reporter_ServiceNow.reportStep("The Attribute value: "+text1+" could not be matched with manufacturer, hence failed", "FAILURE");

		if(!text2.equals(""))
			Reporter_ServiceNow.reportStep("The Old Value: "+text2+" could not be matched with as empty, hence failed", "FAILURE");

		if(!text3.equals("Cisco"))
			Reporter_ServiceNow.reportStep("The New Value: "+text3+" could not be matched with Cisco, hence failed", "FAILURE");

		if(getTextByXpath("ListPage_ReconciliationCIsAll_Xpath").contains(task))
			Reporter_ServiceNow.reportStep("The CMDB Task number as "+task+", Attribute as manufacturer, Old Value as empty and New Value as Cisco are matched as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The CMDB Task could not be matched, hence failed", "FAILURE");

		return this;

	}
	public SystemApplicationsPage enterandChooseManufacturer(String manufacturer) {
		if(enterAndChoose("SA_Manufacturer_Xpath", manufacturer))
			Reporter_ServiceNow.reportStep("The Manufacturer: "+manufacturer+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Manufacturer: "+manufacturer+" not found / could not be entered", "FAILURE");
		return this;
	}


	public SystemApplicationsPage sysAppPageValues(String gxp, String sox, String environment, String location, String businessCriticality) {
		if(!selectByVisibleTextByXpath("SA_GxP_Xpath",gxp))
			Reporter_ServiceNow.reportStep("The type: "+gxp+" could not be selected in GxP", "FAILURE");
		if(!selectByVisibleTextByXpath("SA_SOX_Xpath", sox))
			Reporter_ServiceNow.reportStep("The type: "+sox+" could not be selected in SOX", "FAILURE");
		if(!selectByVisibleTextByXpath("SA_Environment_Xpath", environment))
			Reporter_ServiceNow.reportStep("The type: "+environment+" could not be selected in Environment", "FAILURE");
		if(!enterAndChoose("SA_Location_Xpath", location))
			Reporter_ServiceNow.reportStep("The location: "+location+" not found / could not be entered", "FAILURE");
		if(selectByVisibleTextByXpath("SA_BusinessCriticality_Xpath", businessCriticality))
			Reporter_ServiceNow.reportStep("The Values GxP: "+gxp+", SOX: "+sox+", Environment: "+environment+", Location: "+location+", Business Criticality: "+businessCriticality+" are filled in respective fields as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The type: "+businessCriticality+" could not be selected in Business Criticality", "FAILURE");

		return this;
	}

	public SystemApplicationsPage addFilterForCAR2_2(String filterType, String filterCondition, String filterValue1,String filterValue2, String filterValue3, String filterValue4){
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
		if(addFilters("CIS_FirstFilterType4_Xpath", filterType, "CIS_FilterCondition4_Xpath", filterCondition, "CIS_FilterValue4_Xpath", filterValue4))
			Reporter_ServiceNow.reportStep("The Class value: "+ filterValue1+","+ filterValue2+","+filterValue3+", "+ filterValue4 +" are setted successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value: "+ filterType+" "+ filterCondition+" "+ filterValue4 +" could not be selected","FAILURE");

		return this;
	}


	public SystemApplicationsPage enterAllOtherFields(String gxp, String sox, String environment, String location, String businessCriticality, String businessProcessOwner) {
		if(!selectByVisibleTextByXpath("SA_GxP_Xpath",gxp))			
			Reporter_ServiceNow.reportStep("The type: "+gxp+" could not be selected in GxP", "FAILURE");		
		if(!selectByVisibleTextByXpath("SA_SOX_Xpath", sox))
			Reporter_ServiceNow.reportStep("The type: "+sox+" could not be selected in SOX", "FAILURE");		
		if(!selectByVisibleTextByXpath("SA_Environment_Xpath", environment))
			Reporter_ServiceNow.reportStep("The type: "+environment+" could not be selected in Environment", "FAILURE");
		if(!enterAndChoose("SA_Location_Xpath", location))
			Reporter_ServiceNow.reportStep("The location: "+location+" not found / could not be entered", "FAILURE");		
		if(!selectByVisibleTextByXpath("SA_BusinessCriticality_Xpath", businessCriticality))
			Reporter_ServiceNow.reportStep("The type: "+businessCriticality+" could not be selected in Business Criticality", "FAILURE");
		if(enterAndChoose("SA_BusinessProcessOwner_Xpath", businessProcessOwner))
			Reporter_ServiceNow.reportStep("The GxP: "+gxp+" , Sox: "+sox+" , Location: "+location+",Environment: "+environment+",Business Criticality: "+businessCriticality+",Business Process Owner: "+businessProcessOwner+" are entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Business Process Owner:: "+businessProcessOwner+" not found / could not be entered", "FAILURE");
		return this;
	}
	public SystemApplicationsPage enterAndChooseQAOwnerAndValidationLead(String qaOwner, String validationlead) {
		if(!enterAndChoose("SA_QAOwner_Xpath", qaOwner))
			Reporter_ServiceNow.reportStep("The QA Owner: "+qaOwner+" not found / could not be entered", "FAILURE");
		if(enterAndChoose("SA_ValidationLead_Xpath", validationlead))
			Reporter_ServiceNow.reportStep("The QA Owner: "+qaOwner+" and Validation Lead: "+validationlead+" are entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Validation Lead: "+validationlead+" not found / could not be entered", "FAILURE");
		return this;
	}
	public SystemApplicationsPage enterQAOwnerAndValidationLead(String qaOwner, String validationlead ) {
		if(!enterByXpath("SA_QAOwner_Xpath", qaOwner))			
			Reporter_ServiceNow.reportStep("The QA Owner: "+qaOwner+" not found / could not be entered", "FAILURE");
		if(!enterByXpath("SA_ValidationLead_Xpath", validationlead))			
			Reporter_ServiceNow.reportStep("The Validation Lead: "+validationlead+" not found / could not be entered", "FAILURE");
		return this;

	}

	public SystemApplicationsPage addFilterForCAR2_3DependsOn(String filterType, String filterCondition, String filterValue1,String filterValue2, String filterValue3, String filterValue4, String filterType5, String filterCondition5,String filterType6, String filterCondition6 ){
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
			Reporter_ServiceNow.reportStep("The Class value: "+ filterValue1+","+ filterValue2+","+filterValue3+", "+ filterValue4 +" and "+ filterType5+" "+ filterCondition5 +" and "+filterType6+" "+filterCondition6+" are selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType6+" "+ filterCondition6 +" could not be selected","FAILURE");

		return this;
	}

	public SystemApplicationsPage addFilterForCAR2_3UsedBy(String filterType1, String filterCondition1, String filterValue1, String filterType2, String filterCondition2,String filterType3, String filterCondition3){
		deleteAllFilters();
		if(!addFilters("CIS_FirstFilterType1_Xpath", filterType1, "CIS_FilterCondition1_Xpath", filterCondition1, "CIS_FilterValueSelect1_Xpath", filterValue1))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType1+" "+ filterCondition1+" "+ filterValue1 +" could not be selected","FAILURE");
		if(!clickByXpath("CIS_AndCondition1_Xpath"))				
			Reporter_ServiceNow.reportStep("The AND button could not be clicked or not found", "FAILURE");
		if(!addFilters("CIS_FirstFilterType2_Xpath", filterType2, "CIS_FilterCondition2_Xpath", filterCondition2))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType2+" "+ filterCondition2+" could not be selected","FAILURE");
		if(!clickByXpath("CIS_AndCondition1_Xpath"))				
			Reporter_ServiceNow.reportStep("The AND button could not be clicked or not found", "FAILURE");
		if(addFilters("CIS_FirstFilterType3_Xpath", filterType3, "CIS_FilterCondition3_Xpath", filterCondition3))
			Reporter_ServiceNow.reportStep("The Filter value: "+ filterType1+" "+ filterCondition1+" "+ filterValue1 +","+ filterType2+" "+ filterCondition2+" ,"+filterType3+" "+filterCondition3+" are selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType3+" "+ filterCondition3+" could not be selected","FAILURE");


		return this;
	}
	public SystemApplicationsPage enterGxpSoxBusinessValues(String gxp, String sox, String business)
	{

		if(!selectByVisibleTextByXpath("SA_GxP_Xpath", gxp))
			Reporter_ServiceNow.reportStep("The GxP Value could not be selected with "+gxp+", hence failure", "FAILURE");
		if(!selectByVisibleTextByXpath("SA_SOX_Xpath", sox))
			Reporter_ServiceNow.reportStep("The SOX Value could not be selected with "+sox+", hence failure", "FAILURE");
		if(selectByVisibleTextByXpath("SA_BusinessCriticality_Xpath", business))
			Reporter_ServiceNow.reportStep("The Values GxP:"+gxp+", SOX: "+gxp+", Business Criticality: "+business+" are selected successfully.", "FAILURE");
		else
			Reporter_ServiceNow.reportStep("The Business Criticality Value could not be selected with "+business+" hence failure", "FAILURE");

		return this;

	}
	public SystemApplicationsPage addFilterForCAR2_2(String filterType, String filterCondition, String filterValue1,String filterValue2, String filterValue3, String filterValue4, String filterType5,String filterCondition5,String filterType6, String filterCondition6){
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
			Reporter_ServiceNow.reportStep("The Class value: "+ filterValue1+","+ filterValue2+","+filterValue3+", "+ filterValue4 +" and "+ filterType5+" "+ filterCondition5 +" and "+filterType6+" "+filterCondition6+" are selected successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType6+" "+ filterCondition6 +" could not be selected","FAILURE");

		return this;
	}

	public SystemApplicationsPage VerifyGxpValue(String gxp)
	{
		if((getDefaultValueByXpath("SA_GxP_Xpath")).equals(gxp))
			Reporter_ServiceNow.reportStep("The GxP value: "+gxp+" matched as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The GxP value :"+gxp+" could not matched, hence failure", "WARNING");
		return this;
	}
	public String getName()
	{
		name=getAttributeByXpath("SA_Name_Xpath", "value");

		if(name.equals(""))
			Reporter_ServiceNow.reportStep("The Name is blank for the Created CI, please check Snapshot", "FAILURE");
		return name;
	}


	public SystemApplicationsPage enterAvailableCIs(String name) {
		if(!enterByXpath("RE_AvaiCI_Xpath", name))
			Reporter_ServiceNow.reportStep("The Value: "+name+" not found / could not be entered", "FAILURE");
		Wait(3000);

		return this;
	}

	//indhu updated on 12-01-2016
	public SystemApplicationsPage verifySOXGxPBusinessCriticalityFileds( String sox, String gxp,String businessCriticality){	

		if(!(getDefaultValueByXpath("SA_BusinessCriticality_Xpath")).equals(businessCriticality))			
			Reporter_ServiceNow.reportStep("The "+businessCriticality+" is NOT available Business Criticality","FAILURE");

		if(!(getDefaultValueByXpath("SA_SOX_Xpath")).equals(sox))			
			Reporter_ServiceNow.reportStep("The "+sox+" is NOT available sox","FAILURE");

		if(!(getDefaultValueByXpath("SA_GxP_Xpath")).equals(gxp))			
			Reporter_ServiceNow.reportStep("The values Business Criticality: "+businessCriticality+", SOX: "+sox +", GXP: "+gxp+" are matched as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The  "+gxp+" is NOT available in GXP","WARNING");

		return this;
	}
	public SystemApplicationsPage enterSOXGxPBusinessCriticalityFileds(String gxp, String sox, String businessCriticality){

		if(!selectByVisibleTextByXpath("SA_GxP_Xpath",gxp))			
			Reporter_ServiceNow.reportStep("The type: "+gxp+" could not be selected in GxP", "FAILURE");		

		if(!selectByVisibleTextByXpath("SA_SOX_Xpath", sox))
			Reporter_ServiceNow.reportStep("The type: "+sox+" could not be selected in SOX", "FAILURE");		

		if(!selectByVisibleTextByXpath("SA_BusinessCriticality_Xpath", businessCriticality))
			Reporter_ServiceNow.reportStep("The type: "+businessCriticality+" could not be selected in Business Criticality", "FAILURE");
		return this;
	}
	public SystemApplicationsPage sysAppPageValues1(String gxp, String sox, String location, String businessCriticality) {
		if(!selectByVisibleTextByXpath("SA_GxP_Xpath",gxp))
			Reporter_ServiceNow.reportStep("The type: "+gxp+" could not be selected in GxP", "FAILURE");
		if(!selectByVisibleTextByXpath("SA_SOX_Xpath", sox))
			Reporter_ServiceNow.reportStep("The type: "+sox+" could not be selected in SOX", "FAILURE");
		if(!enterAndChoose("SA_Location_Xpath", location))
			Reporter_ServiceNow.reportStep("The location: "+location+" not found / could not be entered", "FAILURE");
		if(selectByVisibleTextByXpath("SA_BusinessCriticality_Xpath", businessCriticality))
			Reporter_ServiceNow.reportStep("The Values GxP: "+gxp+", SOX: "+sox+", Location: "+location+", Business Criticality: "+businessCriticality+" are filled in respective fields as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The type: "+businessCriticality+" could not be selected in Business Criticality", "FAILURE");

		return this;
	}
	public SystemApplicationsPage clickSaveAndverifyErrorMessage(){

		if(!clickById("Save_Id"))
			Reporter_ServiceNow.reportStep("The Save Button could not be clicked or not found", "FAILURE");

		if((getTextByXpath("ErrorMessage_Xpath")).contains("Proposal for CI modification already exists for"))
			Reporter_ServiceNow.reportStep("The Save Button is cliced successfully and The Error Message Proposal for CI modification already exists appeared as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Error Message Proposal for CI modification already exists could not be generated, check snapshot.", "FAILURE");
		return this;
	}	

	public SystemApplicationsPage selectGxPwithoutReport(String gxp) {
		if(!selectByVisibleTextByXpath("SA_GxP_Xpath",gxp))
			Reporter_ServiceNow.reportStep("The Value: "+gxp+" could not be selected in GxP", "FAILURE");
		return this;
	}
	public SystemApplicationsPage sysAppPageValuesAndSave(String gxp, String sox, String businessCriticality) {
		if(!selectByVisibleTextByXpath("SA_GxP_Xpath",gxp))
			Reporter_ServiceNow.reportStep("The type: "+gxp+" could not be selected in GxP", "FAILURE");
		if(!selectByVisibleTextByXpath("SA_SOX_Xpath", sox))
			Reporter_ServiceNow.reportStep("The type: "+sox+" could not be selected in SOX", "FAILURE");

		if(!selectByVisibleTextByXpath("SA_BusinessCriticality_Xpath", businessCriticality))
			Reporter_ServiceNow.reportStep("The type: "+businessCriticality+" could not be selected in Business Criticality", "FAILURE");

		if(clickById("Save_Id"))
			Reporter_ServiceNow.reportStep("The Values GxP: "+gxp+", SOX: "+sox+", Business Criticality: "+businessCriticality+" are filled in respective fields and Save Button is clicked as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Save Button could not be clicked or not found", "FAILURE");

		return this;
	}
	public SystemApplicationsPage clickSavewithoutReport(){

		if(!clickById("Save_Id"))
			Reporter_ServiceNow.reportStep("The Save Button could not be clicked or not found", "FAILURE");
		return this;	}


}
