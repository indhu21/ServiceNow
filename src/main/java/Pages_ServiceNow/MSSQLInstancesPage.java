package Pages_ServiceNow;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class MSSQLInstancesPage extends ServiceNowWrappers{
	private final RemoteWebDriver driver;
	private String CMDBTaskName;
	private String name;

	public MSSQLInstancesPage(RemoteWebDriver driver) {
		this.driver = driver;
		switchToMainFrame();

		//Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not the MSSQL Instances Page", "FAILURE");
		}

	}
	public MSSQLInstancesPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");
		return this;
	}

	public MSSQLInstancesPage verifyOperationalstatusReadOnly(String operationalstatus){

		Wait(1000);
		if(getDefaultValueByXpath("MSSQL_OperationalStatus_Xpath").contains(operationalstatus))
			Reporter_ServiceNow.reportStep("The operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The operational status value could not be matched", "FAILURE");
		return this; 

	}
	public MSSQLInstancesPage verifyAllMandatoryFieldsforMSSQLIns(){

		// you need to change the mandatory fields when the application changes
		String[] mandatoryFields = {"MSSQL_Name_Xpath",
				"MSSQL_OwnerGroupMan_Xpath",
		"MSSQL_SystemManagerMan_Xpath"};

		String[] mandatoryFieldsDesc = {"Name","CI Owner Group","System Manager"};

		verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);

		return this;

	}
	public MSSQLInstancesPage verifyAllReadOnlyFieldsforMSSQLIns(){

		// you need to change the read only fields when the application changes
		String[] readOnlyFields = {"MSSQL_OperationalStatus_Xpath"};

		String[] readOnlyFieldsDesc = {"Operational Status"};

		// Verify read only
		verifyDisabledFieldsByXpath(readOnlyFields, readOnlyFieldsDesc);
		return this;
	}

	public CmdbListPage clickSubmit() {

		if(clickById("Submit_Id")){
			Wait(3000);
			Reporter_ServiceNow.reportStep("The Submit button is clicked and CI is created successfully", "SUCCESS");}
		else	
			Reporter_ServiceNow.reportStep("The Submit button could not be clicked or not found", "FAILURE");
		return new CmdbListPage(driver);
	}
	public MSSQLInstancesPage selectEnvironment(String environment) {

		if(selectByVisibleTextByXpath("MSSQL_Environment_Xpath", environment))
			Reporter_ServiceNow.reportStep("The type: "+environment+" is selected in Environment", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The type: "+environment+" could not be selected in Environment", "FAILURE");
		return this;
	}

	public MSSQLInstancesPage enterAndChooseLocation(String location) {

		if(enterAndChoose("MSSQL_Location_Xpath", location))
			Reporter_ServiceNow.reportStep("The location: "+location+" is entered successfull", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The location: "+location+" not found / could not be entered", "FAILURE");
		return this;
	}
	public MSSQLInstancesPage clickSetBuild() {

		if(clickById("SA_Setbuild_Id"))
			Reporter_ServiceNow.reportStep("The Set to Build button is clicked, task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Set to Build button could not be clicked or not found", "FAILURE");
		return this;
	}
	public MSSQLInstancesPage getBuildConfirmation(String text) {		

		if(!getTextByXpath("SA_BuildConfirmation_Xpath").contains(text))
			Reporter_ServiceNow.reportStep("The Proposal for CI modification already exist.", "FAILURE");
		return this;
	}

	public MSSQLInstancesPage verifyOperationalstatus(String operationalstatus){

		Wait(3000);
		if(getDefaultValueByXpath("MSSQL_OperationalStatus_Xpath").contains(operationalstatus))
			Reporter_ServiceNow.reportStep("The operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The operational status value "+operationalstatus+" could not be matched", "FAILURE");
		return this; 

	}
	//Indhu added 20-11-2015
	public MSSQLInstancesPage clickLinkName(String linkName){
		Wait(2000);
		// click the first Incident Link
		scrollToElement(driver.findElement(By.linkText(linkName)));
		if(clickLink(linkName, false))
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item is clicked successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item could not be clicked", "FAILURE");
		return new MSSQLInstancesPage(driver);
	}
	public MSSQLInstancesPage clickApprove() {

		if(clickByXpath("Approve_Xpath"))
			Reporter_ServiceNow.reportStep("The Approve button is clicked successfully and the task approved as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Approve button could not be clicked or not found", "FAILURE");
		return this;
	}

	public MSSQLInstancesPage enterChangeRequestId(String changeRequestId){

		Wait(2000);
		if(enterByXpath("CMDBAPP_ChangeRequestID_Xpath", changeRequestId))
			Reporter_ServiceNow.reportStep("The Change request Id value : "+changeRequestId+" is entered successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Change request Id value : "+changeRequestId+" could not be entered or not found", "FAILURE");
		return this;
	}	

	public MSSQLInstancesPage clickAddCIRelationship() {

		if(clickByXpath("SA_AddCIRelationship_Xpath"))
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon is clicked successfully and the relationship editor screen appeared as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Add CI Relationship icon could not be clicked or not found", "FAILURE");
		return this;
	}

	public MSSQLInstancesPage selectDependsOn() {

		if(clickByXpath("SA_DependOn_Xpath"))
			Reporter_ServiceNow.reportStep("The Depend On is selected in the Available Relationships successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Depend On could not be selected in the Available Relationships or not found", "FAILURE");
		Wait(5000);
		return this;
	}

	public MSSQLInstancesPage deleteFilters(){	
		deleteAllFilters();
		return this;
	}

	public MSSQLInstancesPage addFirstFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType1_Xpath", filterType, "CIS_FilterCondition1_Xpath", filterCondition, "CIS_FilterValueSelect1_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public MSSQLInstancesPage addSecondFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType2_Xpath", filterType, "CIS_FilterCondition2_Xpath", filterCondition, "CIS_FilterValue2_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public MSSQLInstancesPage addThirdFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType3_Xpath", filterType, "CIS_FilterCondition3_Xpath", filterCondition, "CIS_FilterValue3_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}
	public MSSQLInstancesPage addThirdFilter(String filterType, String filterValue){
		if(!addFilters("CIS_FirstFilterType3_Xpath", filterType, "CIS_FilterCondition3_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public MSSQLInstancesPage addFourthFilter(String filterType, String filterValue){
		if(!addFilters("CIS_FirstFilterType4_Xpath", filterType, "CIS_FilterCondition4_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" " + filterValue +" could not be selected","FAILURE");

		return this;
	}

	public MSSQLInstancesPage addFourthFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType4_Xpath", filterType, "CIS_FilterCondition4_Xpath", filterCondition, "CIS_FilterValue4_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public MSSQLInstancesPage addFifthFilter(String filterType, String filterValue){
		if(!addFilters("CIS_FirstFilterType5_Xpath", filterType, "CIS_FilterCondition5_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public MSSQLInstancesPage addSixthFilter(String filterType, String filterValue){
		if(!addFilters("CIS_FirstFilterType6_Xpath", filterType, "CIS_FilterCondition6_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public MSSQLInstancesPage clickORCondition() {

		if(!clickByXpath("CIS_ORCondition_Xpath"))				
			Reporter_ServiceNow.reportStep("The OR button  could not be clicked or not found", "FAILURE");
		return this;
	}

	public MSSQLInstancesPage clickANDCondition() {

		if(!clickByXpath("CIS_AndCondition1_Xpath"))				
			Reporter_ServiceNow.reportStep("The AND button  could not be clicked or not found", "FAILURE");
		return this;
	}
	public MSSQLInstancesPage selectFirstAvailableCIs() {

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
	public MSSQLInstancesPage verifyDependsOnRelationshipAppears() {

		scrollToElementByXpath("SA_DependsOnRelationshipAppears_XPath");
		if(getTextByXpath("SA_DependsOnRelationshipAppears_XPath").contains("Depends on"))		
			Reporter_ServiceNow.reportStep("The depends on relationship appears under the related items bar as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The depends on relationship does not appears under the related items bar", "FAILURE");
		return this;
	}
	public MSSQLInstancesPage selectUsedBy() {

		if(clickByXpath("SA_UsedBy_Xpath"))
			Reporter_ServiceNow.reportStep("The Used By is selected in the Available Relationships successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Used By could not be selected in the Available Relationships or not found", "FAILURE");

		Wait(5000);
		return this;
	}
	public MSSQLInstancesPage verifyUsedByRelationshipAppears() {

		Wait(5000);
		scrollToElementByXpath("SA_DependsOnRelationshipAppears_XPath");

		if(getTextByXpath("SA_DependsOnRelationshipAppears_XPath").contains("Used by"))		
			Reporter_ServiceNow.reportStep("The Used By relationship appears under the related items bar as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The  Used By relationship does not appears under the related items bar", "FAILURE");
		return this;
	}
	public MSSQLInstancesPage clickSetActive() {

		if(!clickById("SA_SetActive_Id"))
			Reporter_ServiceNow.reportStep("The Set Active button could not be clicked or not found", "FAILURE");
		else{
			verifyAlert();
			Reporter_ServiceNow.reportStep("The Set Active button is clicked and the task is created and assigned to the System Manager successfully", "SUCCESS");}

		return this;
	}	
	public MSSQLInstancesPage clickSetRetire() {

		if(clickById("SA_SetRetire_Id"))
			Reporter_ServiceNow.reportStep("The Retire button is clicked, task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Retire button could not be clicked or not found", "WARNING");
		return this;
	}

	public MSSQLInstancesPage clickDecommission() {

		if(clickById("SA_SetDecom_Id"))
			Reporter_ServiceNow.reportStep("The Decommission button is clicked, task is created and assigned to the System Manager as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Decommission button could not be clicked or not found", "WARNING");
		return this;
	}

	public MSSQLInstancesPage verifyReadOnlyOperationalstatus(String operationalstatus){

		Wait(1000);
		if(getDefaultValueByXpath("MSSQL_ReadOnlyOperationalStatus_Xpath").contains(operationalstatus))
			Reporter_ServiceNow.reportStep("The operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The operational status value could not be matched", "FAILURE");
		return this; 

	}

	public MSSQLInstancesPage verifyalltext(String environment, String location){

		if(!(getDefaultValueByXpath("MSSQL_Environment_Xpath")).equals(environment))
			Reporter_ServiceNow.reportStep("The Environment: "+ environment +" could not be matched, hence failure", "FAILURE");

		if((getAttributeByXpath("MSSQL_Location_Xpath", "value")).equals(location))
			Reporter_ServiceNow.reportStep("Environment: "+environment+", Location: "+location+" are matched as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Location: "+ location +" could not be matched, hence failure", "FAILURE");

		return this;
	}
	public MSSQLInstancesPage isPageEditable(String environment) {

		if(selectByVisibleTextByXpath("MSSQL_Environment_Xpath", environment))
			Reporter_ServiceNow.reportStep("Created Application record have write access as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Created Application record could not have write access.", "FAILURE");
		return this;
	}

	public MSSQLInstancesPage getTextFromScheduleChange(String Task) {

		scrollToElementByXpath("CI_ScheduledChange1_Xpath");

		if((getTextByXpath("CI_ScheduledChange_Xpath")).contains(Task))
			Reporter_ServiceNow.reportStep("The created Task: "+Task+" is also available under the Scheduled changes section as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The created Task: "+Task+" could not available under the Scheduled changes, Check snapshot.", "FAILURE");
		return this;
	}

	public String getCMDBName() {
		CMDBTaskName = getAttributeByXpath("MSSQL_Name_Xpath", "value");
		if(CMDBTaskName.equals(""))
			Reporter_ServiceNow.reportStep("The task number is blank for newly created CMDB Task", "FAILURE");
		return CMDBTaskName;
	}	

	public MSSQLInstancesPage enterAllMandatoryFields(String name, String CIOwnerGroup, String SystemManager) {

		if(!enterByXpath("MSSQL_Name_Xpath", name))
			Reporter_ServiceNow.reportStep("The Name: "+name+" not found / could not be entered", "FAILURE");
		if(!enterAndChoose("MSSQL_OwnerGroup_Xpath", CIOwnerGroup))
			Reporter_ServiceNow.reportStep("The CI Owner Group: "+CIOwnerGroup+" not found / could not be entered", "FAILURE");
		if(enterAndChoose("MSSQL_SystemManager_Xpath", SystemManager))
			Reporter_ServiceNow.reportStep("All the Mandatory fields "+name+" "+CIOwnerGroup+" "+SystemManager+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The System Manager: "+SystemManager+" not found / could not be entered", "FAILURE");
		return this;
	}

	//Update

	public MSSQLInstancesPage selectOtherValues(String environment, String location) {

		if(!selectByVisibleTextByXpath("MSSQL_Environment_Xpath", environment))
			Reporter_ServiceNow.reportStep("The type: "+environment+" could not be selected in Environment", "FAILURE");

		if(enterAndChoose("MSSQL_Location_Xpath", location))
			Reporter_ServiceNow.reportStep("The values Environment: "+environment+", location: "+location+" are filled in respective fields successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The location: "+location+" not found / could not be entered", "FAILURE");
		return this;
	}

	public MSSQLInstancesPage addFilterforFourValues(String filterType1, String filterCondition1, String filterValue1, String filterValue2, String filterValue3,  String filterValue4,
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
		Reporter_ServiceNow.reportStep("The Class values: "+filterType1+" "+filterCondition1+" "+ filterValue1+","+ filterValue2+", "+filterValue3 +", "+filterValue4+", "+filterType5+" "+filterCondition5+", "+filterType6+" "+filterCondition6+" are selected successfully", "SUCCESS");
		return this;
	}



	public MSSQLInstancesPage addFilterforTwoValues(String filterType1, String filterCondition1, String filterValue1, String filterValue2,
			String filterType5, String filterCondition5, String filterType6, String filterCondition6){

		deleteFilters()
		.addFirstFilter(filterType1, filterCondition1, filterValue1)       
		.clickANDCondition()
		.addSecondFilter(filterType1, filterCondition1, filterValue2)
		.clickANDCondition()
		.addThirdFilter(filterType5, filterCondition5)
		.clickANDCondition()
		.addFourthFilter(filterType6, filterCondition6);
		Reporter_ServiceNow.reportStep("The Class values: "+filterType1+" "+filterCondition1+" "+ filterValue1+","+ filterValue2+", "+filterType5+" "+filterCondition5+", "+filterType6+" "+filterCondition6+" are selected successfully", "SUCCESS");
		return this;
	}

	public MSSQLInstancesPage VerifyGxpSoxBusinessValues(String gxp, String sox, String business)
	{

		if(!getDefaultValueByXpath("MSSQL_GxP_Xpath").equals(gxp))
			Reporter_ServiceNow.reportStep("The GxP Value could not be matched with "+gxp+", hence failure", "WARNING");
		if(!getDefaultValueByXpath("MSSQL_SOX_Xpath").equals(sox))
			Reporter_ServiceNow.reportStep("The SOX Value could not be matched with "+sox+", hence failure", "WARNING");
		if(getDefaultValueByXpath("MSSQL_BusinessCriticality_Xpath").equals(business))
			Reporter_ServiceNow.reportStep("The Values GxP:"+gxp+", SOX: "+gxp+", Business Criticality: "+business+" are matched as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Business Criticality Value could not be matched with "+business+", hence failure", "FAILURE");
		return this;
	}

	public MSSQLInstancesPage searchAvailableCI(String availableCI){
		if(!enterByXpath("RE_AvaiCI_Xpath",availableCI))
			Reporter_ServiceNow.reportStep("The Available CI could not be found","FAILURE");
		Wait(2000);

		return this;
	}
	public String getName()
	{
		name=getAttributeByXpath("SA_Name_Xpath", "value");
		if(name.equals(""))
			Reporter_ServiceNow.reportStep("The Name is blank for the Created CI, please check Snapshot", "FAILURE");
		return name;
	}

	public MSSQLInstancesPage verifyGxpSoxBusinessValuesbyFirstSelectedValue(String gxp, String sox, String business)
	{

		if(!getDefaultValueByXpath("MSSQL_GxP_Xpath").equals(gxp))
			Reporter_ServiceNow.reportStep("The GxP Value could not be matched with "+gxp+", hence failure", "FAILURE");
		if(!getDefaultValueByXpath("MSSQL_SOX_Xpath").equals(sox))
			Reporter_ServiceNow.reportStep("The SOX Value could not be matched with "+sox+", hence failure", "FAILURE");
		if(getDefaultValueByXpath("MSSQL_BusinessCriticality_Xpath").equals(business))
			Reporter_ServiceNow.reportStep("The Values GxP:"+gxp+", SOX: "+gxp+", Business Criticality: "+business+" are matched as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Business Criticality Value could not be matched with "+business+", hence failure", "FAILURE");
		return this;
	}

	public String getNameReadOnly()
	{
		name=getAttributeByXpath("SA_Namereadonly_Xpath", "value");
		if(name.equals(""))
			Reporter_ServiceNow.reportStep("The Name is blank for the Created CI, please check Snapshot", "FAILURE");
		return name;
	}

	public MSSQLInstancesPage verifybaseline()
	{

		System.out.println(getTextByXpath("MSSQL_Baselin_Xpath"));
		scrollToElementByXpath("MSSQL_Baselin_Xpath");
		
		if(!getTextByXpath("MSSQL_Baselin_Xpath").contains("SOX: Yes  was: No"))
			Reporter_ServiceNow.reportStep("The Baseline didnot matched with SOX: Yes  was: No, hence failure", "FAILURE");
		
		if(getTextByXpath("MSSQL_Baselin_Xpath").contains("GxP: Yes  was: No"))
			Reporter_ServiceNow.reportStep("The change of value for the related CI is appeares in the baseline as expected.", "FAILURE");
		else
			Reporter_ServiceNow.reportStep("The Baseline didnot matched with SOX: Yes  was: No, hence failure", "FAILURE");
		return this;
	}


}


