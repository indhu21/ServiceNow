package pages;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class AlertPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;


	public AlertPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
			Reporter.reportStep("This is not the Alert page", "FAILURE");
		}
	}

	public AlertPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}

	public AlertPage selectRunBookandUpdate(String alertId){

		if(!selectByVisibleTextByXpath("ALERT_ReationType_Xpath", "Run Runbook"))
			Reporter.reportStep("Create Incident for Alert "+ alertId +" could not be selected","FAILURE");

		if(!clickById("CIS_UpdateButton_Id"))
			Reporter.reportStep("Update for Alert "+ alertId +" could not be clicked","FAILURE");

		Wait(5000);

		return this;
	}	
	public AlertPage enterReactionType(String reactionType){

		if((getAttributeByXpath("ALERTRECORD_ReactionType_Xpath", "value")).equals(reactionType))
			Reporter.reportStep("Alert Reaction Type is matched "+ reactionType +" successfully","SUCCESS");
		else
			Reporter.reportStep("Alert Reaction Type could not be matched "+ reactionType +" hence failure","FAILURE");

		return this;
	}	

	public AlertPage clickAcknowledge(){
		if(clickByXpath("Ackhowledge_button_Xpath"))
			Reporter.reportStep("The Acknowledge Button is Clicked successfully and alert is acknowldged as expected..","SUCCESS");
		else
			Reporter.reportStep("The Acknowledge Button is not Clicked, Check snapshot.","FAILURE");		
		return this;
	}	

	public AlertPage clickRunReaction(){
		Wait(5000);	

		if(clickByXpath("RunReaction_Xpath"))
			Reporter.reportStep("The Run Reaction Button is clicked successfully","SUCCESS");
		else	
			Reporter.reportStep("The Run Reaction Button could not be Clicked ","FAILURE");	
		return this;
	}	
	public AlertPage verifyAlertState(String alertState){

		scrollToElementByXpath("ALERTRECORD_AlertState_Xpath");

		String state=getDefaultValueByXpath("ALERTRECORD_AlertState_Xpath");

		if(state.contains(alertState))
			Reporter.reportStep("The Value: "+ alertState +" is matched with Alert State as expected.","SUCCESS");
		else
			Reporter.reportStep("The Value: "+ alertState +" is not matched with Alert State, hence failure.","FAILURE");

		return this;
	}

	public AlertPage verifyRelatedTaskState(String relatedTaskState){
		
		if(!clickByXpath("Alert_RelatedTaskTab_Xpath"))
			Reporter.reportStep("The Related Task Tab could not be clicked","FAILURE");
		
		scrollToElementByXpath("Alert_RelatedTable_Xpath");
		
		int column=getColumnIndex1("Alert_RelatedTable_Xpath", "State");

		scrollToElementByXpath("(//*[text()='Related Tasks']/following::*[@class='vt'])"+"["+column+"]", false);

		String relatedTask=getTextByXpath("(//*[text()='Related Tasks']/following::*[@class='vt'])"+"["+column+"]", false);

		if(relatedTask.contains(relatedTaskState))
			Reporter.reportStep("The Related Task State: "+ relatedTask +" is matched with "+relatedTaskState+", successfully","SUCCESS");
		else
			Reporter.reportStep("The Related Task State: "+ relatedTask +" not matched with "+relatedTaskState+", hence failure","FAILURE");
		return this;
	}
	public AlertPage verifyRelatedTaskNumber(String relatedTaskNumber){

		scrollToElementByXpath("Alert_RelatedTable_Xpath");

		int column=getColumnIndex1("Alert_RelatedTable_Xpath", "Task");

		System.out.println(column);
		scrollToElementByXpath("(//*[text()='Related Tasks']/following::*[@class='vt'])"+"["+column+"]", false);

		String relatedTask=getTextByXpath("(//*[text()='Related Tasks']/following::*[@class='vt'])"+"["+column+"]", false);

		System.out.println(relatedTask);
		if(relatedTaskNumber.contains(relatedTask))
			Reporter.reportStep("The Related Task Number: "+ relatedTask +" is matched with "+relatedTaskNumber+", successfully","SUCCESS");
		else
			Reporter.reportStep("The Related Task Number: "+ relatedTask +" not matched with "+relatedTaskNumber+", hence failure","FAILURE");
		return this;
	}

	public AlertPage verifyRelatedTaskState(String reactionState , String relTaskState  ){

		scrollToElementByXpath("ALERTPROFILE_RelatedState_Xpath");

		if(reactionState.contains(relTaskState)){
			Wait(500);
			Reporter.reportStep("The Related Task State: "+ relTaskState +" is matched as expected.","SUCCESS");}
		else
			Reporter.reportStep("The Related Task State: "+ relTaskState +" could not matched, hence failure","FAILURE");
		return this;
	}

	public AlertPage enterNameShortDescfilterOwningGroupAndClickSubmit(String name,String shortDescription,String filter,String owningGroup ){
		if (!enterById("CIS_Name_Id", name))
			Reporter.reportStep("The CI Scope Name is not be entered","FAILURE");

		if (!enterById("CIS_ShortDesc_Id", shortDescription))
			Reporter.reportStep("The CI Scope description is not entered","FAILURE");

		if (!selectByVisibleTextById("CIS_Filter_Id", filter))
			Reporter.reportStep("The CI Scope Filter is not selected","FAILURE");

		if (!enterAndChoose("CIS_OwningGroup_Xpath", owningGroup))
			Reporter.reportStep("The Owning Group is not selected","FAILURE");

		if (clickById("CIS_SubmitButton_Id"))
			Reporter.reportStep("The CI Scope Name:"+name+",CI Scope description:"+shortDescription+",CI Scope Filter:"+filter+",Owning Group:"+owningGroup+" are entered and the submit button is clicked successfully","SUCCESS");
		else
			Reporter.reportStep("The submit button is not clicked","FAILURE");
		return this;
	}

	private String scopeNum;
	public String getscopeNum() {
		scopeNum = getAttributeById("CIS_ScopeNumber_Id", "value");
		if(scopeNum.equals(""))
			Reporter.reportStep("The Scope Number is Blank", "FAILURE");
		return scopeNum;
	}

	public MenuPage selectFilterandSave(String fSection, String fCondition,String fValue ){
		if(!selectByIndexByXpath("CIS_CIClass_Xpath",1))
			Reporter.reportStep("In CI Class section could not be selected","FAILURE");

		Wait(5000);

		if(addNewFilterUsingSelect(fSection, fCondition, fValue))
			Reporter.reportStep("In CI Filter section "+ fSection +" " + fCondition +" " + fValue +" is selected successfully.","SUCCESS");
		else
			Reporter.reportStep("In CI Filter section "+ fSection +" " + fCondition +" " + fValue +" could not be selected.","FAILURE");

		// Step 8: Run the selected filter condition by clicking 

		if (clickById("CIS_UpdateButton_Id")) {
			Wait(5000);
			Reporter.reportStep("The Update is successful","SUCCESS");
		} else
			Reporter.reportStep("The Update button could not be clicked","FAILURE");

		if (!rightClickById("CIS_Menu_Id"))
			Reporter.reportStep("The Right click on Edit Tag could not be clicked","FAILURE");

		if (clickByXpath("CIS_SaveRecord_Xpath"))
			Reporter.reportStep("The Right click on Edit Tag is clicked and Save is clicked successfully","SUCCESS");
		else
			Reporter.reportStep("The Save could not be clicked","FAILURE");
		return new MenuPage(driver);
	}

	public AlertPage verifyUpdateButtonAvl(){
		if (IsElementNotPresentById("CIS_UpdateButton_Id"))
			Reporter.reportStep("The Update button could not been found as expected.","SUCCESS");
		else
			Reporter.reportStep("The Update button has been found.", "FAILURE");
		return this;
	}

	public MenuPage verifyRightclickNotFound(){
		if (!rightClickById("CIS_Menu_Id"))
			Reporter.reportStep("The Right click on menu could not be performed.","FAILURE");

		if (IsElementNotPresentByXpath("CIS_SaveRecord_Xpath"))
			Reporter.reportStep("The Save option could not been found as expected.","SUCCESS");
		else
			Reporter.reportStep("The Save option has been found.","FAILURE");		
		return new MenuPage(driver);
	}

	public MenuPage verifyDisabledFields(){

		String[] elements={"CIS_Number_Xpath","CIS_Name1_Xpath", "CIS_ParentScope_Xpath", "CIS_Owning_group_Xpath", "CIS_shortDesc_Xpath", "CIS_ScopeType_Xpath" };
		String[] values={"Number", "Name", "Parent Scope", "Owning Group", "Short Decription", "Scope Type"  };

		verifyDisabledFieldsByXpath(elements, values);
		return new MenuPage(driver);
	}

	public MenuPage verifyEnabledFields(){

		String[] elements={"CIS_Number_Xpath","CIS_Name1_Xpath", "CIS_ParentScope_Xpath", "CIS_Owning_group_Xpath", "CIS_shortDesc_Xpath", "CIS_ScopeType_Xpath" };
		String[] values={"Number", "Name", "Parent Scope", "Owning Group", "Short Decription", "Scope Type"  };

		verifyEnabledFieldsByXpath(elements, values);
		return new MenuPage(driver);
	}

	public AlertPage verifyCreatedAndAlertFirstOccurence(String created){

		scrollToElementByXpath("ALERT_ActivityLog_Xpath");

		//String alertFirstOccurence = getTextByXpath("ALERT_ActivityLog_Xpath");

		if(verifyTextInGroupTexts("ALERT_ActivityDates_Xpath", created)){
			Wait(500);
			Reporter.reportStep("The Created Date: "+created+" is matched in Activity Log as expected.","SUCCESS");}
		else
			Reporter.reportStep("The Created Date: "+created+" is not matched in Activity Log, hence failure.","FAILURE");
		return this;
	}

	public MenuPage verifyLastOccurenceAndAlertLastOccurence(String lastOccurence ){

		scrollToElementByXpath("ALERTRECURRENCES_AlertTime_Xpath");		

		String alertLastOccurence = getTextByXpath("ALERTRECURRENCES_AlertTime_Xpath");

		if(IsElementNotPresentByXpath("ALERT_Times_Xpath")){
			clickByXpath("ALERT_AlertTime_Xpath");}
		if(alertLastOccurence.contains(lastOccurence))
			Reporter.reportStep("The Last Occurence Date: "+ lastOccurence +" is matched in Alert Recurrences Scetion as expected.","SUCCESS");
		else
			Reporter.reportStep("The Last Occurence Date: "+ lastOccurence +" is not matched in Alert Recurrences Scetion, hence failure.","FAILURE");

		return new MenuPage(driver);
	}
	public AlertPage verifyMonitoringSystem(){

		String[] expectedValues = {"Monitoring System"};
		Wait(5000);

		scrollToElementByXpath("ALERT_RecrList_Xpath");
		
		if(!clickByXpath("ALERT_RecrList_Xpath"))
			Reporter.reportStep("List under to Alert Recurrence Section could not be clicked","FAILURE");

		// The expected value validation against the list of the items
		Wait(3000);
		
		if(verifyListContents("ALERT_SlushSelected_Xpath",expectedValues) || verifyListContents("ALERT_SlushAvailable_Xpath",expectedValues))
			Reporter.reportStep("Monitring system is availabe in the list","SUCCESS");
		else
			Reporter.reportStep("Monitring system could not be availabe in the list","FAILURE");

		// Click Ok
		if(!clickById("Ok_Id"))
			Reporter.reportStep("Ok button could not be clicked","FAILURE");

		return this;
	}

	public AlertsListPage verifygrpName(String grpName){
		Wait(3000);
		if(getAttributeById("ALERTPROFILE_GroupName_Id", "value").equals(grpName))
			Reporter.reportStep("The Alert Group Name matched with "+ grpName +" successfully","SUCCESS");
		else	
			Reporter.reportStep("The Alert Group Name could not be matched with "+ grpName,"FAILURE");

		// Step 4: Click Back
		if(!clickByXpath("Back_Xpath"))
			Reporter.reportStep("The Back Button under Alert Record could not be clicked","FAILURE");
		return new AlertsListPage(driver);
	}

	public MenuPage verifyReadOnlyFields(){

		Wait(5000);
		// Step 4: Verify all ReadOnly Fields
		String[] readOnlyFields={"ALERTRECORD_Num_Xpath", "ALERTRECORD_Assignto_Xpath", "ALERTRECORD_AlertProfile_Xpath", 
				"ALERTRECORD_ReactionType_Xpath", "ALERTRECORD_ConfItem_Xpath", "ALERTRECORD_AlertSeverity_Xpath", 
				"ALERTRECORD_AlertState_Xpath", "ALERTRECORD_ClosedBy_Xpath", "ALERTRECORD_ClosureCode_Xpath",
				"ALERTRECORD_Tally_Xpath", "ALERTRECORD_Rating_Xpath", "ALERTRECORD_ShortDesc_Xpath", "ALERTRECORD_AlertDesc_Xpath"};

		String[] readOnlyFieldsDesc = {"Reporting Customer",
				"Configuration Item",
				"Assignment Group",
		"Short Description"};


		verifyDisabledFieldsByXpath(readOnlyFields,readOnlyFieldsDesc);			


		return new MenuPage(driver);
	}

	public AlertPage validateAssignedTo(String fullName){

		if(getAttributeByXpath("ALERT_AssignedTo_Xpath","value").contains(fullName))
			Reporter.reportStep("The Assigned To field has the value of login name:"+fullName+" as expected","SUCCESS");
		else
			Reporter.reportStep("The Assigned To field does not have the value of login name :"+fullName,"FAILURE");			


		scrollToElementByXpath("ALERT_ActivityAssField_Xpath");

		if(getTextByXpath("ALERT_ActivityAssField_Xpath").contains(fullName))
			Reporter.reportStep("The Activity Log of alert record has the entry: "+fullName+" as expected","SUCCESS");
		else
			Reporter.reportStep("The Activity Log of alert record does not have the entry :"+fullName,"FAILURE");	


		return new AlertPage(driver);
	}

	public AlertPage isDeleteButtonExists(String regUser){
		if(IsElementNotPresentByXpath("Delete_Xpath"))
			Reporter.reportStep("The Delete option is not found for the username "+ regUser + " hence successful" , "SUCCESS");
		else
			Reporter.reportStep("The Delete option is found for the username "+ regUser +" hence failed","FAILURE");
		return new AlertPage(driver);
	}

	public AlertsListPage enterEnricherForm(String labelTm, String order, String decoratorType, String recurBehav, String operator , String modification){

		if(!enterById("ALERT_Label_Id", labelTm))
			Reporter.reportStep("Label: "+ labelTm +" in Alert Enrichers could not be entered","FAILURE");

		if(!clickById("ALERT_Active_Id"))
			Reporter.reportStep("Active in Alert Enrichers could not be clicked","FAILURE");

		if(!enterById("ALERT_Order_Id", order))
			Reporter.reportStep("Order: "+ order +" in Alert Enrichers could not be entered","FAILURE");

		if(!selectByVisibleTextById("ALERT_DecoratorType_Id", decoratorType))
			Reporter.reportStep("Decorator Type: "+ decoratorType +" in Alert Enrichers could not be selected","FAILURE");

		if(!selectByVisibleTextById("ALERT_RecurrenceBehaviour_Id", recurBehav ))
			Reporter.reportStep("Recurrence Behaviour: "+ recurBehav +" in Alert Enrichers could not be selected","FAILURE");

		Wait(2000);

		if(!addNewFilterUsingInput("Alert Attribute", "contains", "Node Status"))
			Reporter.reportStep("Alert Attribute contains Node Status could not be selected","FAILURE");

		if(!selectByVisibleTextById("ALERT_Operator_Id", operator))
			Reporter.reportStep("Operator: "+ operator +" in Alert Enrichers could not be selected","FAILURE");

		if(enterById("ALERT_Modification_Id", modification))
			Reporter.reportStep("The Values Label: "+ labelTm +", Order: "+ order +", Decorator Type: "+ decoratorType +", Recurrence Behaviour: "+ recurBehav +", Operator: "+ operator +", Modification: "+ modification +" are entered Alert Enrichers page successfully.","SUCCESS");
		else 
			Reporter.reportStep("Modification: "+ modification +" in Alert Enrichers could not be entered","FAILURE");

		if(clickById("Submit_Id"))
			Reporter.reportStep("Submit Button in Alert Enrichers is clicked successfully","SUCCESS");
		else
			Reporter.reportStep("Submit Button in Alert Enrichers could not be clicked ","FAILURE");

		return new AlertsListPage(driver);
	}

	public AlertPage compareReferenceData(String modification){

		String rate=getAttributeByXpath("ALERTRECORD_Rating_Xpath", "value");

		rate=rate.replaceAll(",", "");

		Wait(5000);	

		if(Integer.valueOf(rate)>=Integer.valueOf(modification))
			Reporter.reportStep("The Alert Rate is greater than "+ modification +" as expected.","SUCCESS");
		else
			Reporter.reportStep("The Alert Rate is not greater than "+ modification +", hence failure.","FAILURE");
		return new AlertPage(driver);
	}
	private String incNumber;

	public String getIncNumber() {
		incNumber = getAttributeByXpath("CREATEINC_IncidentNumber_Xpath", "value");
		if(incNumber.equals(""))
			Reporter.reportStep("The Incident Number condition is Blank", "FAILURE");
		return incNumber;
	}
	public AlertPage enterCallerName(String callerName) {

		if(enterAndChoose("CREATEINC_AffectedUser_Xpath", callerName))
			Reporter.reportStep("The Value: "+ callerName +" is entered in Caller Name field as expected.","SUCCESS");
		else
			Reporter.reportStep("The Caller Name "+ callerName +" is not entered","FAILURE");

		return this;

	}
	public AlertPage enterAssignmentGroup(String assignGrp) {

		if(enterAndChoose("CREATEINC_AsgGroup_Xpath", assignGrp))
			Reporter.reportStep("The Value: "+ assignGrp +" is entered in Assignment Group field as expected.","SUCCESS");
		else
			Reporter.reportStep("The Assignment Group "+ assignGrp +" is not entered","FAILURE");

		return this;
	}

	public AlertsListPage clickUpdateButton() {
		
		switchToMainFrame();

		if(clickById("CIS_UpdateButton_Id"))
			Reporter.reportStep("The Update Button is clicked successfully.","SUCCESS");
		else
			Reporter.reportStep("The Update Button is not clicked.","FAILURE");

		return new AlertsListPage(driver);
	}
	public AlertPage verifyActivityLog(String user1, String user2) {


		String activity=getTextByXpath("Activity_Xpath");

		if(verifyAttributeTextByXpath("ALERT_ActivityExpand_Xpath", "title", "Expand")){
			clickByXpath("ALERT_ActivityExpand_Xpath");
		}
			
		scrollToElementByXpath("ALERT_ActivityAssField_Xpath");
			System.out.println(activity);
		
		if(activity.contains("Assigned To: "+user1+"   was: "+user2))
			Reporter.reportStep("The Alert state in Activity Section is matched with Assigned To: "+user1+"   was: "+user2+" successfully", "SUCCESS");
		else
			Reporter.reportStep("THe Alert state in Activity Section is not matched with Assigned To: "+user1+"   was: "+user2+" hence, failure ","FAILURE");

		return this;
	}

	public AlertPage moveHasBreached(){		

		scrollToElementByXpath("ALERT_TaskSLA_SettingsIcon_Xpath");
		if(!clickByXpath("ALERT_TaskSLA_SettingsIcon_Xpath"))	
			Reporter.reportStep("The settings in Task SLA’s section could not be clicked","FAILURE");

		String[] expectedValues = {"Has breached"};
		Wait(5000);

		if(verifyListContents("ALERT_SlushSelected_Xpath",expectedValues))
			Reporter.reportStep("The value Has breached is exist in availabe list as expected.","SUCCESS");
		else{
			if(!selectByVisibleTextByXpath("ALERT_BreachInAvailable_Xpath", "Has breached"))
				Reporter.reportStep("The Value Has Breached is not present in either available or selected option", "FAILURE");

			if(clickByXpath("ALERT_MoveBreach_ToSelected_Xpath"))
				Reporter.reportStep("The value Has breached is exist in availabe list as expected.","SUCCESS");
			else
				Reporter.reportStep("The Move Button is not clicked or not found.","FAILURE");}

		if(!clickById("Ok_Id"))
			Reporter.reportStep("Ok button is not clicked or not found.","FAILURE");

		return this;
	}
	public AlertPage verifycolumnValue(String colName, String value) {

		Wait(5000);
		int column=getColumnIndex1("ALERT_TaskSLATableHead_Xpath", colName);

		scrollToElementByXpath("(//*[text()='Task SLAs']/following::*[@class='vt'])"+"["+column+"]", false);

		String text=getTextByXpath("(//*[text()='Task SLAs']/following::*[@class='vt'])"+"["+column+"]", false);

		scrollToElementByXpath("(//*[text()='Task SLAs']", false);
		//	System.out.println(getTextByXpath("(//*[@class='vt'])"+"["+column+"]", false));
		if(text.equalsIgnoreCase(value))
			Reporter.reportStep("The "+colName+" value: "+value+" is matched as expected.", "SUCCESS");
		else
			Reporter.reportStep("The "+colName+" value: "+value+" is not matched, check snapshot.", "FAILURE");

		return this;
	}

	public AlertPage verifyAlertSeverity(String alertSeverity){

		scrollToElementByXpath("ALERTRECORD_AlertSeverity_Xpath");

		String severity=getDefaultValueByXpath("ALERTRECORD_AlertSeverity_Xpath");

		if(severity.contains(alertSeverity))
			Reporter.reportStep("The Value: "+ alertSeverity +" is matched with Alert Severity as expected.","SUCCESS");
		else
			Reporter.reportStep("The Value: "+ alertSeverity +" is not matched with Alert Severity, hence failure.","FAILURE");

		return this;
	}

	public AlertPage verifyAlertClosureCode(String alertClosurecode){

		scrollToElementByXpath("ALERTRECORD_ClosureCode_Xpath");

		String closurecode=getDefaultValueByXpath("ALERTRECORD_ClosureCode_Xpath");

		if(closurecode.contains(alertClosurecode))
			Reporter.reportStep("The Value: "+ alertClosurecode +" is matched with Alert Closure Code as expected.","SUCCESS");
		else
			Reporter.reportStep("The Value: "+ alertClosurecode +" is not matched with Alert Closure Code, hence failure.","FAILURE");

		return this;
	}
	public AlertPage validateClosedValue(String closedBy){
		if(getAttributeByXpath("ALERTRECORD_ClosedBy_Xpath", "value").contains(closedBy))
			Reporter.reportStep("The Closed By value for is matched with "+ closedBy +" successfully","SUCCESS");
		else
			Reporter.reportStep("The Closed By value for is could not matched with "+ closedBy +" hence failure", "FAILURE");		

		return this;
	}

	public AlertPage enterandChooseAssignTo(String assignGrp) {
		
//		resetImplicitWait(5);
		if(enterAndChoose("ALERT_AssignedTo_Xpath", assignGrp))
			Reporter.reportStep("The Value: "+assignGrp+" is entered in AssignTo field as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Value: "+assignGrp+" is not entered in AssignTo field, hence failure.", "FAILURE");
		
//		switchToPrimary();
//		resetImplicitWait(30);
//		

/*		if(clickByXpath("LookUp_Icon_Xpath")){
			Wait(3000);
			switchToNewWindow();}
	else{
			Reporter.reportStep("The Assign To Spyglass is not clicked.", "FAILURE");}

			if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Name"))
				Reporter.reportStep("The Number could not be selected", "FAILURE");

			if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",assignGrp))
				Reporter.reportStep("The Assignmen Name: "+assignGrp+" is not entered", "FAILURE");

			if(clickByXpath("SecondWinFirstLink_Xpath"))
				Reporter.reportStep("The Value: "+assignGrp+" is selected in AssignTo field as expected.", "SUCCESS");
			else
				Reporter.reportStep("The Value: "+assignGrp+" is not selected in AssignTo field, hence failure.", "FAILURE");*/

		return this;
	}

	public AlertPage verifyAssignTo(String assignGrp) {

		if((getAttributeByXpath("ALERT_AssignedTo_Xpath","value")).contains(assignGrp))
			Reporter.reportStep("The Value: "+ assignGrp +" is matched with AssignTo field as expected.","SUCCESS");
		else
			Reporter.reportStep("The AssignTo Value: "+ assignGrp +" is not matched, hence failure.","FAILURE");

		return this;
	}

	public AlertPage verifyAlertDecorator(String name) {

		if(!selectByVisibleTextByXpath("ALERT_MetadataValue_Xpath", "Alert Decorator"))
			Reporter.reportStep("The Alert Decorator is not selected or not found.","FAILURE");

		if(!enterByXpathAndClick("ALERT_Metadatasearch_Xpath", name))
			Reporter.reportStep("The Alert Decorator: "+ name +"  is not entered for search.","FAILURE");

		Wait(2000);

		String alertDecorator=getTextByXpath("ALERT_MetadataDecorator_Xpath");

		if(alertDecorator.equals(name))
			Reporter.reportStep("The Alert Decorator: "+ name +"  in Metadata is matched with Label name as expected","SUCCESS");
		else
			Reporter.reportStep("The Alert Decorator: "+ name +"  in Metadata is not matched with label name, hence failure.","FAILURE");

		return this;

	}

	public AlertPage verifyreadOnlyFields() {


		String[] readOnlyFields={	"ALERTRECORD_Num_Xpath",
				/*"ALERTRECORD_Assignto_Xpath",*/
				"ALERTRECORD_AlertProfile_Xpath", 
				"ALERTRECORD_ReactionType_Xpath",
				"ALERTRECORD_ConfItem_Xpath",
				"ALERTRECORD_AlertSeverity_Xpath", 
				"ALERTRECORD_AlertState_Xpath", 
				"ALERTRECORD_ClosedBy_Xpath",
				"ALERTRECORD_ClosureCode_Xpath",
				"ALERTRECORD_Tally_Xpath", 
				"ALERTRECORD_Rating_Xpath",
				"ALERTRECORD_ShortDesc_Xpath", 
		"ALERTRECORD_AlertDesc_Xpath"};

		String[] readOnlyFieldsDesc = {	"Number", 
				/*"Assign To",*/
				"Alert Profile",
				"Reactio Type",
				"Configuration Item",
				"Severity",
				"State",
				"Close By",
				"Closure Code",
				"Tally",
				"Rating",
				"Short Description",
		"Description"};

		verifyDisabledFieldsByXpath(readOnlyFields,readOnlyFieldsDesc);

		return this;
	}
	public AlertPage IsAlertStateNotNew(){

		scrollToElementByXpath("ALERTRECORD_AlertState_Xpath");

		String state=getDefaultValueByXpath("ALERTRECORD_AlertState_Xpath");

		if(!state.contains("New"))
			Reporter.reportStep("The Alert State is not matched as New which is as expected.","SUCCESS");
		else
			Reporter.reportStep("The Alert State is matched as New , hence failure.","FAILURE");

		return this;
	}

	public AlertPage sortTableHeading(String value) {

		if(!clickByXpath("//h1[text()='Alert Recurrences']/following::a[text()='"+value+"']"))
			Reporter.reportStep("The "+value+" is not clicked, hence failure.","FAILURE");

		return this;


	}
	public String getRelatedTaskNumber() {
		String alertId = getTextByXpath("RunReaction_Text_Xpath");
		System.out.println(alertId);
		if(alertId.equals(""))
			Reporter.reportStep("The Related Task Number is Blank", "FAILURE");
		return alertId;
	}
	
	public AlertPage switchToNewWindow(){
		switchToSecondWindow();
			return this;
		}
	public AlertPage isAckNotPresent() {
		
		if (IsElementNotPresentByXpath("ALERT_Acknowledge_Xpath"))
			Reporter.reportStep("The Acknowledge button could not been found as expected.","SUCCESS");
		else
			Reporter.reportStep("The Acknowledge button has been found.", "FAILURE");
		return this;
}
	public AlertPage verifyAndClickRunReaction(){
		

		if(clickByXpath("RunReaction_Xpath"))
			Reporter.reportStep("The Run Reaction Button is visible and clicked successfully","SUCCESS");
		else	
			Reporter.reportStep("The Run Reaction Button could not be Clicked or not found.","FAILURE");	
		return this;
	}		
	public String  getOwnGroup() {
		
		String ownGroup = getAttributeByXpath("MyAlerts_AssignmentGroup_Xpath", "value");
				if(ownGroup.equals(""))
					Reporter.reportStep("The Owning Group is blank, hence failure..","FAILURE");
				
		return ownGroup;
	}
	public AlertPage verifyOwnGrpName(String grpName){
		
		if(getAttributeById("MyAlerts_AssignmentGroup_Xpath", "value").equals(grpName))
			Reporter.reportStep("The Alert Group Name matched with "+ grpName +" as expected.","SUCCESS");
		else	
			Reporter.reportStep("The Alert Group Name could not be matched with "+ grpName,"FAILURE");
		
		return this;
	}

	// Raj Added on 7 th June for OD10136
	public AlertsListPage clickSubmit(){
	if(clickById("Submit_Id"))
		Reporter.reportStep("Submit Button in Alert Enrichers is clicked successfully","SUCCESS");
	else
		Reporter.reportStep("Submit Button in Alert Enrichers could not be clicked ","FAILURE");
	return new AlertsListPage(driver);
	}
}


