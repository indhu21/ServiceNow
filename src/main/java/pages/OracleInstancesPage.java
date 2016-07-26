package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class OracleInstancesPage extends ServiceNowWrappers{


	private final RemoteWebDriver driver;
	private String CMDBTaskNumber;
	private String CMDBTaskName;
	private String name;
	public OracleInstancesPage(RemoteWebDriver driver) {
		this.driver = driver;
		switchToMainFrame();

		//Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
			Reporter.reportStep("This is not the Oracle Instances Page", "FAILURE");
		}

	}
	public OracleInstancesPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");
		return this;
	}

	public OracleInstancesPage verifyAllFieldsforOracleIns(){

		// you need to change the non mandatory fields when the application changes
		String[] Fields = { "Oracle_Name_Xpath",
				"Oracle_Manufacturer_Xpath", 
				"Oracle_Gxp_Xpath",
				"Oracle_Sox_Xpath",
				"Oracle_BusinessCriticality_Xpath",
				"Oracle_Environment_Xpath", 
				"Oracle_Location_Xpath",
				"Oracle_Version_Xpath",
				"Oracle_Edition_Xpath",
				"Oracle_Component_Xpath",
				"Oracle_StandBy_Xpath",
				"Oracle_PointsToLinux_Xpath",
				"Oracle_GoLiveDate_Xpath",
				"Oracle_ShortDescription_Xpath",
				"Oracle_OperationalStatus_Xpath",
				"Oracle_OwnerGroup_Xpath",
				"Oracle_SystemManager_Xpath",
		"Oracle_SecSystemManager_Xpath"};

		String[] FieldsDesc = {"Name",
				"Manufacturer",
				"Gxp",
				"Sox",
				"Business Criticality",
				"Environment",
				"Location",
				"Version",
				"Edition",
				"Component",
				"Stand By",
				"Oracle mount points to Linux",
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


	public OracleInstancesPage verifyOperationalstatusReadOnly(String operationalstatus){

		Wait(1000);
		if(getDefaultValueByXpath("Oracle_OperationalStatus_Xpath").contains(operationalstatus))
			Reporter.reportStep("The operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter.reportStep("The operational status value could not be matched", "FAILURE");
		return this; 

	}
	public OracleInstancesPage verifyAllMandatoryFieldsforOracleIns(){

		// you need to change the mandatory fields when the application changes
		String[] mandatoryFields = {"Oracle_Name_Xpath",
				"Oracle_OwnerGroupMan_Xpath",
		"Oracle_SystemManagerMan_Xpath"};

		String[] mandatoryFieldsDesc = {"Name",
				"CI Owner Group","System Manager"};

		verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);

		return this;

	}
	public OracleInstancesPage verifyAllReadOnlyFieldsforOracleIns(){

		// you need to change the read only fields when the application changes
		String[] readOnlyFields = {"Oracle_OperationalStatus_Xpath"};

		String[] readOnlyFieldsDesc = {"Operational Status"};

		// Verify read only
		verifyDisabledFieldsByXpath(readOnlyFields, readOnlyFieldsDesc);
		return this;
	}

	//	public OracleInstancesPage enterName(String name) {
	//
	//		if(enterByXpath("Oracle_Name_Xpath", name))
	//			Reporter.reportStep("The Name: "+name+" is entered successfully", "SUCCESS");
	//		else
	//			Reporter.reportStep("The Name: "+name+" not found / could not be entered", "FAILURE");
	//		return this;
	//	}
	//	public OracleInstancesPage enterAndChooseCIOwnerGroup(String CIOwnerGroup) {
	//
	//		if(enterAndChoose("Oracle_OwnerGroup_Xpath", CIOwnerGroup))
	//			Reporter.reportStep("The CI Owner Group: "+CIOwnerGroup+" is entered successfully", "SUCCESS");
	//		else
	//			Reporter.reportStep("The CI Owner Group: "+CIOwnerGroup+" not found / could not be entered", "FAILURE");
	//		return this;
	//	}
	//
	//	public OracleInstancesPage enterAndChooseSystemManager(String SystemManager) {
	//
	//		if(enterAndChoose("Oracle_SystemManager_Xpath", SystemManager))
	//			Reporter.reportStep("The System Manager: "+SystemManager+" is entered successfully", "SUCCESS");
	//		else
	//			Reporter.reportStep("The System Manager: "+SystemManager+" not found / could not be entered", "FAILURE");
	//		return this;
	//	}
	//
	public CmdbListPage clickSubmit() {

		if(clickById("Submit_Id")){
			Wait(3000);
			Reporter.reportStep("The Submit button is clicked and CI is created successfully", "SUCCESS");}
		else	
			Reporter.reportStep("The Submit button could not be clicked or not found", "FAILURE");
		return new CmdbListPage(driver);
	}
	public OracleInstancesPage selectEnvironment(String environment) {

		if(selectByVisibleTextByXpath("Oracle_Environment_Xpath", environment))
			Reporter.reportStep("The type: "+environment+" is selected in Environment", "SUCCESS");
		else
			Reporter.reportStep("The type: "+environment+" could not be selected in Environment", "FAILURE");
		return this;
	}

	public OracleInstancesPage enterAndChooseLocation(String location) {

		if(enterAndChoose("Oracle_Location_Xpath", location))
			Reporter.reportStep("The location: "+location+" is entered successfull", "SUCCESS");
		else
			Reporter.reportStep("The location: "+location+" not found / could not be entered", "FAILURE");
		return this;
	}
	public OracleInstancesPage clickSetBuild() {

		if(clickById("SA_Setbuild_Id"))
			Reporter.reportStep("The Set to Build button is clicked, task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter.reportStep("The Set to Build button could not be clicked or not found", "FAILURE");
		return this;
	}
	public OracleInstancesPage getBuildConfirmation(String text) {		
	
		if(!getTextByXpath("SA_BuildConfirmation_Xpath").startsWith(text))
			Reporter.reportStep("The Proposal for CI modification already exist.", "FAILURE");
		return this;

	}

	public OracleInstancesPage verifyOperationalstatus(String operationalstatus){

		Wait(3000);
		if(getDefaultValueByXpath("Oracle_OperationalStatus_Xpath").contains(operationalstatus))
			Reporter.reportStep("The operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter.reportStep("The operational status value "+operationalstatus+" could not be matched", "FAILURE");
		return this; 

	}
	//Indhu added 20-11-2015
	public OracleInstancesPage clickLinkName(String linkName){
		Wait(2000);
		// click the first Incident Link
		scrollToElement(driver.findElement(By.linkText(linkName)));
		if(clickLink(linkName, false))
			Reporter.reportStep("The Value: "+ linkName +" under Configuration Item is clicked successfully", "SUCCESS");
		else	
			Reporter.reportStep("The Value: "+ linkName +" under Configuration Item could not be clicked", "FAILURE");
		return new OracleInstancesPage(driver);
	}
	public OracleInstancesPage clickApprove() {

		if(clickByXpath("Approve_Xpath"))
			Reporter.reportStep("The Approve button is clicked successfully and the task approved as expected", "SUCCESS");
		else	
			Reporter.reportStep("The Approve button could not be clicked or not found", "FAILURE");
		return this;
	}

	public OracleInstancesPage enterChangeRequestId(String changeRequestId){

		Wait(2000);
		if(enterByXpath("CMDBAPP_ChangeRequestID_Xpath", changeRequestId))
			Reporter.reportStep("The Change request Id value : "+changeRequestId+" is entered successfully", "SUCCESS");
		else	
			Reporter.reportStep("The Change request Id value : "+changeRequestId+" could not be entered or not found", "FAILURE");
		return this;
	}	

	public OracleInstancesPage clickAddCIRelationship() {
		
		if(scrollToElementByXpath("SA_AddCIRelationship_Xpath"))
			Reporter.reportStep("The Add CI Relationship icon appeared as expected", "SUCCESS");
		else	
			Reporter.reportStep("The Add CI Relationship icon found", "FAILURE");

		if(clickByXpath("SA_AddCIRelationship_Xpath"))
			Reporter.reportStep("The Add CI Relationship icon is clicked successfully and the relationship editor screen appeared as expected", "SUCCESS");
		else	
			Reporter.reportStep("The Add CI Relationship icon could not be clicked or not found", "FAILURE");
		return this;
	}

	public OracleInstancesPage selectDependsOn() {

		if(clickByXpath("SA_DependOn_Xpath"))
			Reporter.reportStep("The Depend On is selected in the Available Relationships successfully", "SUCCESS");
		else	
			Reporter.reportStep("The Depend On could not be selected in the Available Relationships or not found", "FAILURE");
		Wait(5000);
		return this;
	}

	public OracleInstancesPage deleteFilters(){	
		deleteAllFilters();
		return this;
	}

	public OracleInstancesPage addFirstFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType1_Xpath", filterType, "CIS_FilterCondition1_Xpath", filterCondition, "CIS_FilterValueSelect1_Xpath", filterValue))
			Reporter.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public OracleInstancesPage addSecondFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType2_Xpath", filterType, "CIS_FilterCondition2_Xpath", filterCondition, "CIS_FilterValue2_Xpath", filterValue))
			Reporter.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public OracleInstancesPage addThirdFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType3_Xpath", filterType, "CIS_FilterCondition3_Xpath", filterCondition, "CIS_FilterValue3_Xpath", filterValue))
			Reporter.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}
	public OracleInstancesPage addThirdFilter(String filterType, String filterValue){
		if(!addFilters("CIS_FirstFilterType3_Xpath", filterType, "CIS_FilterCondition3_Xpath", filterValue))
			Reporter.reportStep("The Filter value "+ filterType+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public OracleInstancesPage addFourthFilter(String filterType, String filterValue){
		if(!addFilters("CIS_FirstFilterType4_Xpath", filterType, "CIS_FilterCondition4_Xpath", filterValue))
			Reporter.reportStep("The Filter value "+ filterType+" " + filterValue +" could not be selected","FAILURE");

		return this;
	}

	public OracleInstancesPage addFourthFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType4_Xpath", filterType, "CIS_FilterCondition4_Xpath", filterCondition, "CIS_FilterValue4_Xpath", filterValue))
			Reporter.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public OracleInstancesPage addFifthFilter(String filterType, String filterValue){
		if(!addFilters("CIS_FirstFilterType5_Xpath", filterType, "CIS_FilterCondition5_Xpath", filterValue))
			Reporter.reportStep("The Filter value "+ filterType+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public OracleInstancesPage addSixthFilter(String filterType, String filterValue){
		if(!addFilters("CIS_FirstFilterType6_Xpath", filterType, "CIS_FilterCondition6_Xpath", filterValue))
			Reporter.reportStep("The Filter value "+ filterType+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public OracleInstancesPage clickORCondition() {

		if(!clickByXpath("CIS_ORCondition_Xpath"))				
			Reporter.reportStep("The OR button  could not be clicked or not found", "FAILURE");
		return this;
	}

	public OracleInstancesPage clickANDCondition() {

		if(!clickByXpath("CIS_AndCondition1_Xpath"))				
			Reporter.reportStep("The AND button  could not be clicked or not found", "FAILURE");
		return this;
	}
	public OracleInstancesPage selectFirstAvailableCIs() {

		if(!clickByXpath("SA_FirstAvailableCI_Xpath"))			
			Reporter.reportStep("The First Available CI could not be selected or not found", "FAILURE");

		if(!clickByXpath("ALERT_MoveBreach_ToSelected_Xpath"))
			Reporter.reportStep("The First Available CI could not be moved", "FAILURE");

		if(clickById("Ok_Id"))
			Reporter.reportStep("The Highlighted CI appeared in the box on the right as expected", "SUCCESS");
		else
			Reporter.reportStep("The Save button could not be clicked or not found", "FAILURE");
		return this;
	}
	public OracleInstancesPage verifyDependsOnRelationshipAppears() {

		scrollToElementByXpath("SA_DependsOnRelationshipAppears_XPath");
		if(getTextByXpath("SA_DependsOnRelationshipAppears_XPath").contains("Depends on"))		
			Reporter.reportStep("The depends on relationship appears under the related items bar as expected", "SUCCESS");
		else	
			Reporter.reportStep("The depends on relationship does not appears under the related items bar", "FAILURE");
		return this;
	}
	public OracleInstancesPage selectUsedBy() {

		if(clickByXpath("SA_UsedBy_Xpath"))
			Reporter.reportStep("The Used By is selected in the Available Relationships successfully", "SUCCESS");
		else	
			Reporter.reportStep("The Used By could not be selected in the Available Relationships or not found", "FAILURE");

		Wait(5000);
		return this;
	}
	public OracleInstancesPage verifyUsedByRelationshipAppears() {

		Wait(5000);
		scrollToElementByXpath("SA_DependsOnRelationshipAppears_XPath");

		if(getTextByXpath("SA_DependsOnRelationshipAppears_XPath").contains("Used by"))		
			Reporter.reportStep("The Used By relationship appears under the related items bar as expected", "SUCCESS");
		else	
			Reporter.reportStep("The  Used By relationship does not appears under the related items bar", "FAILURE");
		return this;
	}
	public OracleInstancesPage clickSetActive() {

		if(!clickById("SA_SetActive_Id"))
			Reporter.reportStep("The Set Active button could not be clicked or not found", "FAILURE");
		else{
		verifyAlert();
		Reporter.reportStep("The Set Active button is clicked and the task is created and assigned to the System Manager successfully", "SUCCESS");}
		return this;
	}	
	public OracleInstancesPage clickSetRetire() {

		if(clickById("SA_SetRetire_Id"))
			Reporter.reportStep("The Retire button is clicked, task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter.reportStep("The Retire button could not be clicked or not found", "WARNING");
		return this;
	}

	public OracleInstancesPage clickDecommission() {

		if(clickById("SA_SetDecom_Id"))
			Reporter.reportStep("The Decommission button is clicked, task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter.reportStep("The Decommission button could not be clicked or not found", "WARNING");
		return this;
	}

	public OracleInstancesPage verifyReadOnlyOperationalstatus(String operationalstatus){

		Wait(1000);
		if(getDefaultValueByXpath("Oracle_ReadOnlyOperationalStatus_Xpath").contains(operationalstatus))
			Reporter.reportStep("The operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter.reportStep("The operational status value could not be matched", "FAILURE");
		return this; 

	}
	public OracleInstancesPage verifyAllFieldsReadOnly(){

		// you need to change the non mandatory fields when the application changes
		String[] Fields = { "Oracle_ReadOnlyName_Xpath",
				"Oracle_ReadOnlyManufacturer_Xpath", 
				"Oracle_ReadOnlyGxP_Xpath",
				"Oracle_ReadOnlySOX_Xpath",
				"Oracle_ReadOnlyBusinessCriticality_Xpath",
				"Oracle_ReadOnlyEnvironment_Xpath",
				"Oracle_ReadOnlyLocation_Xpath",
				"Oracle_ReadOnlyVersion_Xpath",
				"Oracle_ReadOnlyEdition_Xpath", 
				"Oracle_ReadOnlyComponent_Xpath",
				"Oracle_ReadOnlyStandBy_Xpath", 
				"Oracle_ReadOnlyPointsToLinux_Xpath", 
				"Oracle_ReadOnlyGoLiveDate_Xpath",
				"Oracle_ReadOnlyDescription_Xpath",
				"Oracle_ReadOnlyOperationalStatus_Xpath",
				"Oracle_ReadOnlyCIOwnerGroup_Xpath", 
				"Oracle_ReadOnlySysManager_Xpath",
				"Oracle_ReadOnlySecSysManager_Xpath"
		};

		String[] FieldsDesc = { "Name",
				"Manufacturer",
				"GxP",
				"SOX",
				"Business Criticality",
				"Environment",
				"Location",
				"Version",
				"Edition",
				"Component",
				"Stand By",
				"Oracle mount points to Linux",
				"Go Live Date",
				"Short Description",
				"Operational Status",
				"CI Owner Group",
				"System Manager",
				"Secondary System Manage"
		};

		verifyDisabledFieldsByXpath(Fields, FieldsDesc);

		return this;

	}	
	public OracleInstancesPage verifyalltext(String environment, String location){

		if(!(getDefaultValueByXpath("Oracle_Environment_Xpath")).equals(environment))
			Reporter.reportStep("The Environment: "+ environment +" could not be matched, hence failure", "FAILURE");

		if((getAttributeByXpath("Oracle_Location_Xpath", "value")).equals(location))
			Reporter.reportStep("Environment: "+environment+", Location: "+location+" are matched as expected", "SUCCESS");
		else
			Reporter.reportStep("The Location: "+ location +" could not be matched, hence failure", "FAILURE");

		return this;
	}
	public OracleInstancesPage isPageEditable(String environment) {

		if(selectByVisibleTextByXpath("Oracle_Environment_Xpath", environment))
			Reporter.reportStep("Created Application record have write access as expected.", "SUCCESS");
		else
			Reporter.reportStep("Created Application record could not have write access.", "FAILURE");
		return this;
	}

	public OracleInstancesPage getTextFromScheduleChange(String Task) {

		scrollToElementByXpath("CI_ScheduledChange1_Xpath");

		if((getTextByXpath("CI_ScheduledChange_Xpath")).contains(Task))
			Reporter.reportStep("The created Task: "+Task+" is also available under the Scheduled changes section as expected.", "SUCCESS");
		else
			Reporter.reportStep("The created Task: "+Task+" could not available under the Scheduled changes, Check snapshot.", "FAILURE");
		return this;
	}

	public String getCMDBName() {
		CMDBTaskName = getAttributeByXpath("Oracle_Name_Xpath", "value");
		if(CMDBTaskName.equals(""))
			Reporter.reportStep("The task number is blank for newly created CMDB Task", "FAILURE");
		return CMDBTaskName;
	}	

	public OracleInstancesPage enterAllMandatoryFields(String name, String CIOwnerGroup, String SystemManager) {

		if(!enterByXpath("Oracle_Name_Xpath", name))
			Reporter.reportStep("The Name: "+name+" not found / could not be entered", "FAILURE");
		if(!enterAndChoose("Oracle_OwnerGroup_Xpath", CIOwnerGroup))
			Reporter.reportStep("The CI Owner Group: "+CIOwnerGroup+" not found / could not be entered", "FAILURE");
		if(enterAndChoose("Oracle_SystemManager_Xpath", SystemManager))
			Reporter.reportStep("All the Mandatory fields "+name+" "+CIOwnerGroup+" "+SystemManager+" is entered successfully", "SUCCESS");
		else
			Reporter.reportStep("The System Manager: "+SystemManager+" not found / could not be entered", "FAILURE");
		return this;
	}

	//Update

	public OracleInstancesPage selectOtherValues(String environment, String location) {

		if(!selectByVisibleTextByXpath("Oracle_Environment_Xpath", environment))
			Reporter.reportStep("The type: "+environment+" could not be selected in Environment", "FAILURE");

		if(enterAndChoose("Oracle_Location_Xpath", location))
			Reporter.reportStep("The values Environment: "+environment+", location: "+location+" are filled in respective fields successfully", "SUCCESS");
		else
			Reporter.reportStep("The location: "+location+" not found / could not be entered", "FAILURE");
		return this;
	}

	public OracleInstancesPage addFilterforFourValues(String filterType1, String filterCondition1, String filterValue1, String filterValue2, String filterValue3,  String filterValue4,
			String filterType5, String filterCondition5, String filterType6, String filterCondition6){

		deleteFilters()
		.addFirstFilter(filterType1, filterCondition1, filterValue1)       
		.clickANDCondition()
		.addSecondFilter(filterType1, filterCondition1, filterValue2)
		.clickANDCondition()
		.addThirdFilter(filterType1, filterCondition1, filterValue3)
		.clickANDCondition()
		.addFourthFilter(filterType1, filterCondition1, filterValue4)
		.clickANDCondition()
		.addFifthFilter(filterType5, filterCondition5)
		.clickANDCondition()
		.addSixthFilter(filterType6, filterCondition6);
		Reporter.reportStep("The Class values: "+filterType1+" "+filterCondition1+" "+ filterValue1+","+ filterValue2+", "+filterValue3 +", "+filterValue4+", "+filterType5+" "+filterCondition5+", "+filterType6+" "+filterCondition6+" are selected successfully", "SUCCESS");
		return this;
	}



	public OracleInstancesPage addFilterforTwoValues(String filterType1, String filterCondition1, String filterValue1, String filterValue2,
			String filterType5, String filterCondition5, String filterType6, String filterCondition6){
		deleteFilters()
		.addFirstFilter(filterType1, filterCondition1, filterValue1)       
		.clickANDCondition()
		.addSecondFilter(filterType1, filterCondition1, filterValue2)
		.clickANDCondition()
		.addThirdFilter(filterType5, filterCondition5)
		.clickANDCondition()
		.addFourthFilter(filterType6, filterCondition6);
		Reporter.reportStep("The Class values: "+filterType1+" "+filterCondition1+" "+ filterValue1+","+ filterValue2+", "+filterType5+" "+filterCondition5+", "+filterType6+" "+filterCondition6+" are selected successfully", "SUCCESS");
		return this;
	}

	public OracleInstancesPage VerifyGxpSoxBusinessValues(String gxp, String sox, String business)
	{

		if(!getDefaultValueByXpath("SA_GxP_Xpath").equals(gxp))
			Reporter.reportStep("The GxP Value could not be matched with "+gxp+", hence failure", "FAILURE");
		if(!getDefaultValueByXpath("SA_SOX_Xpath").equals(sox))
			Reporter.reportStep("The SOX Value could not be matched with "+sox+", hence failure", "FAILURE");
		if(getDefaultValueByXpath("SA_BusinessCriticality_Xpath").equals(business))
			Reporter.reportStep("The Values GxP:"+gxp+", SOX: "+gxp+", Business Criticality: "+business+" are matched as expected.", "FAILURE");
		else
			Reporter.reportStep("The Business Criticality Value could not be matched with "+business+", hence failure", "FAILURE");
		return this;
	}
	
	public OracleInstancesPage searchAvailableCI(String availableCI){
		if(!enterByXpath("RE_AvaiCI_Xpath",availableCI))
			Reporter.reportStep("The Available CI could not be found","FAILURE");
			Wait(2000);

		return this;
	}
	public String getName()
	{
		name=getAttributeByXpath("SA_Name_Xpath", "value");
		if(name.equals(""))
			Reporter.reportStep("The Name is blank for the Created CI, please check Snapshot", "FAILURE");
		return name;
	}
	public OracleInstancesPage verifyAddCIRelationship() {

		scrollToElementByXpath("SA_AddCIRelationship_Xpath");
		
		if(isExistByXpath("SA_AddCIRelationship_Xpath"))
			Reporter.reportStep("The Add CI Relationship icon appeared for Active CI as expected", "SUCCESS");
		else	
			Reporter.reportStep("The Add CI Relationship icon found", "FAILURE");

		return this;
	}
	
}


