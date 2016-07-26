package Pages_ServiceNow;


import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class CorrelationProfilesPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public CorrelationProfilesPage(RemoteWebDriver driver) {

		resetImplicitWait(5);
		this.driver = driver;
		switchToMainFrame();
		
		// Check that we're on the right page.
		if(!(isExistByXpath("HomeTitle_Xpath")||isExistByXpath("NavBar2_Xpath")||isExistByXpath("CorrProfReg_Xpath"))) {
			Reporter_ServiceNow.reportStep("This is not the Correlation Profiles page", "FAILURE");
		}
		resetImplicitWait(30);
	}

	public CorrelationProfilesPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}


	public CorrelationProfilesPage enterMandatoryFields(String name, String severity, String inAssGrp,
											String ownGrp, String desc, String impact) {

		if(!enterByXpath("CorrelatedProfile_Name_Xpath", name))
			Reporter_ServiceNow.reportStep("The "+name+" not entered to Name field.", "FAILURE");
		
		if(!enterAndChoose("CorrelatedProfile_OwningGroup_Xpath", ownGrp))
			Reporter_ServiceNow.reportStep("The Owning group not given to the correlated profile", "FAILURE");
		
		if(!enterAndChoose("CorrelatedProfile_IncidentAssignmentGroup_Xpath", inAssGrp))
			Reporter_ServiceNow.reportStep("The "+inAssGrp+" group not given to the correlated profile", "FAILURE");
		
		if(!selectByVisibleTextByXpath("CorrelatedProfile_CorrelatedAlertSeverity_Xpath", severity))
			Reporter_ServiceNow.reportStep("The "+severity+" not selected for Severity", "FAILURE");
		
		if(!selectByVisibleTextByXpath("CorrelatedProfile_ImpactToService_Xpath", impact))
			Reporter_ServiceNow.reportStep("The Outage not selected for Impact", "FAILURE");
		
		if(enterByXpath("CorrelatedProfile_ShortDescription_Xpath", desc))
			Reporter_ServiceNow.reportStep("The values Name: "+name+", Owning Group: "+ownGrp+", Incident Assignment Group: "+inAssGrp+", Correlated Alert Severity: "+severity+""
					+ ", Description: "+desc+", and Record saved successfully","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Short Description not given to the correlated profile", "FAILURE");
				return this;
	}
	
	public CorrelationProfilesPage clickSubmit() {
		
		if(!clickById("submit_button"))
			Reporter_ServiceNow.reportStep("The submit button is not clicked.", "FAILURE");
		
		if(isExistByXpath("CorrelatedProfile_CorrelationHead_Xpath1"))
			Reporter_ServiceNow.reportStep("The Correlation Profile is created and additional configuration options are shown as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The submit button is clicked but The Additional configuration options is not displayed, hence failure.", "FAILURE");
		
		return this;
	
	}
	
	public CorrelationProfilesPage verifyGroupByList(String[] list) {
		if(verifyListContent("CorrelatedProfile_GroupBy_Xpath", list))
			Reporter_ServiceNow.reportStep("The Values: "+convertStringArrayToString(list)+" do exist in Group By list as expected.", "SUCCESS");
		return this;
	}
	
	public CorrelationProfilesPage verifyStartCond(String[] list) {
		
		if(verifyListContent("CorrelatedProfile_StartConditionGrouping_Xpath", list))
			Reporter_ServiceNow.reportStep("All the elements :"+convertStringArrayToString(list)+" do exists in start Condition list", "SUCCESS");
	
		return this;
	
	}
	
public CorrelationProfilesPage selectReactionType(String reactionType) {
		
//	if(selectByVisibleTextById("CorrelatedProfile_ReactionType_Id", reactionType))
	if(selectByIndexByXpath("CorrelationProfile_ReactionType_Xpath", 1))
		Reporter_ServiceNow.reportStep("The Reaction Type selected as "+reactionType+" as expected.", "SUCCESS");
	else
		Reporter_ServiceNow.reportStep("The Reaction Type not selected as "+reactionType+", hence failure", "FAILURE");
		return this;
	}
	
public CorrelationProfilesPage selectGroupValues() {
	
	
	clickGroupAlertsWithoutReport();
	
	if(!clickByXpath("CorrelatonProfiles_ImpactedProfiles_Xpath"))
		Reporter_ServiceNow.reportStep("The Impacted profiles edit button could not be clicked", "FAILURE");
	
	if(!enterAndChoose("CorrelatonProfiles_ImpactedProfilesTextField_Xpath", "**"))
		Reporter_ServiceNow.reportStep("The value could not be entered", "FAILURE");
		
	if(!clickByXpath("CorrelatonProfiles_ImpactedProfilesLockbutton_Xpath"))
		 Reporter_ServiceNow.reportStep("The Impacted profiles lock button could not be selected", "FAILURE");

	if(!clickByXpath("CorrelatonProfiles_ImpactedCIs_Xpath"))
		Reporter_ServiceNow.reportStep("The Impacted profiles edit button could not be clicked", "FAILURE");
	
	if(!enterAndChoose("CorrelatonProfiles_ImpactedCIsTextField_Xpath", "*.com"))
		Reporter_ServiceNow.reportStep("The value could not be entered", "FAILURE");
		
	if(!clickByXpath("CorrelatonProfiles_ImpactedCIsLockbuton_Xpath"))
		 Reporter_ServiceNow.reportStep("The Impacted CIs lock button could not be selected", "FAILURE");
	
	if(!clickByXpath("CorrelatonProfiles_ImpactedAttributes_Xpath"))
		Reporter_ServiceNow.reportStep("The Impacted attribute edit button could not be clicked", "FAILURE");
	
	if(!enterAndChoose("CorrelatonProfiles_ImpactedAttributesTextField_Xpath", "**"))
		Reporter_ServiceNow.reportStep("The value could not be entered", "FAILURE");
		
	if(clickByXpath("CorrelatonProfiles_ImpactedAttributesLockbuton_Xpath"))
		Reporter_ServiceNow.reportStep("The Impacted Profiles, Impacted CIs and Impacted attributes are selected from drop down list as expected.", "SUCCESS");
	else
		Reporter_ServiceNow.reportStep("The Impacted profiles lock button could not be selected", "FAILURE");
	
	return this;
}

public CorrelationProfilesPage clickInsertedNewRowStartCondition() {
	
	int colum=getColumnIndex1("CorrelatedProfile_StartTableHead_Xpath", "Condition");
	
		System.out.println(colum);
		scrollToElementByXpath("((//h1[text()='Start Conditions'])/following::*[@class='vt list_add list_edit_new_row'])["+colum+"]", false);
		
	if(!doubleCickByXpath("((//h1[text()='Start Conditions'])/following::*[@class='vt list_add list_edit_new_row'])["+colum+"]", false))
		Reporter_ServiceNow.reportStep("The Double click could not be performed", "FAILURE");
	 
	waitUntillElementTobeVisible("StartCondition_Xpath");
	
	if(!enterAndChoose1("StartCondition_Xpath", "**"))
		Reporter_ServiceNow.reportStep("The First value count not be entered", "FAILURE");
	
//	if(!clickById("ALERTPROFILE_attribute_right_id"))
//		Reporter_ServiceNow.reportStep("The Ok Button could not be clicked", "FAILURE");
	
	colum=getColumnIndex1("CorrelatedProfile_StartTableHead_Xpath", "Operator");
	
	scrollToElementByXpath("((//h1[text()='Start Conditions'])/following::*[@class='vt list_add'])["+colum+"]", false);
	
	if(!doubleCickByXpath("((//h1[text()='Start Conditions'])/following::*[@class='vt list_add'])["+colum+"]", false))
		Reporter_ServiceNow.reportStep("The Double click could not be performed", "FAILURE");
	
	waitUntillElementTobeVisible("StartOperator_Xpath");
	if(!selectByIndexByXpath("StartOperator_Xpath", 1))
		Reporter_ServiceNow.reportStep("The First Value could not be selected", "FAILURE");
	
	if(!clickById("ALERTPROFILE_attribute_right_id"))
		Reporter_ServiceNow.reportStep("The Ok Button could not be clicked", "FAILURE");
	
	colum=getColumnIndex1("CorrelatedProfile_StartTableHead_Xpath", "Value");
	
	scrollToElementByXpath("((//h1[text()='Start Conditions'])/following::*[@class='vt list_add'])["+colum+"]", false);
	
	if(!doubleCickByXpath("((//h1[text()='Start Conditions'])/following::*[@class='vt list_add'])["+colum+"]", false))
		Reporter_ServiceNow.reportStep("The Double click could not be performed", "FAILURE");
	
	waitUntillElementTobeVisible("StartValue_Xpath");
	
	if(!enterByXpath("StartValue_Xpath", "2"))
		Reporter_ServiceNow.reportStep("The value could not be entered", "FAILURE");
	
	if(!clickById("ALERTPROFILE_attribute_right_id"))
		Reporter_ServiceNow.reportStep("The Ok Button could not be clicked", "FAILURE");
	
         
	Reporter_ServiceNow.reportStep("The New Row Inserted a new Start Condition as expected.", "SUCCESS");
	
	return this;
}
public CorrelationProfilesPage clickInsertedNewRowforStopCondtion() {
	
	clickStopConditionWithoutReport();
		
	int colum=getColumnIndex1("CorrelatedProfile_StopTableHead_Xpath", "Condition");
	
	scrollToElementByXpath("((//h1[text()='Stop Conditions'])/following::*[@class='vt list_add list_edit_new_row'])["+colum+"]", false);
	
	if(!doubleCickByXpath("((//h1[text()='Stop Conditions'])/following::*[@class='vt list_add list_edit_new_row'])["+colum+"]", false))
		Reporter_ServiceNow.reportStep("The Double click could not be performed", "FAILURE");
	 
	waitUntillElementTobeVisible("StartCondition_Xpath");
	
	if(!enterAndChoose1("StartCondition_Xpath", "**"))
		Reporter_ServiceNow.reportStep("The First value count not be entered", "FAILURE");
//	
//	if(!clickById("ALERTPROFILE_attribute_right_id"))
//		Reporter_ServiceNow.reportStep("The Impacted attribute edit button could not be clicked", "FAILURE");
//	
	colum=getColumnIndex1("CorrelatedProfile_StopTableHead_Xpath", "Operator");
	
	scrollToElementByXpath("((//h1[text()='Stop Conditions'])/following::*[@class='vt list_add'])["+colum+"]", false);
	
	if(!doubleCickByXpath("((//h1[text()='Stop Conditions'])/following::*[@class='vt list_add'])["+colum+"]", false))
		Reporter_ServiceNow.reportStep("The Double click could not be performed", "FAILURE");
	
	waitUntillElementTobeVisible("StartOperator_Xpath");
	
	if(!selectByIndexByXpath("StartOperator_Xpath", 1))
		Reporter_ServiceNow.reportStep("The First Value could not be selected", "FAILURE");
	
	if(!clickById("ALERTPROFILE_attribute_right_id"))
		Reporter_ServiceNow.reportStep("The Ok Button could not be clicked", "FAILURE");
	
	colum=getColumnIndex1("CorrelatedProfile_StopTableHead_Xpath", "Value");
	
	scrollToElementByXpath("((//h1[text()='Stop Conditions'])/following::*[@class='vt list_add'])["+colum+"]", false);
	
	if(!doubleCickByXpath("((//h1[text()='Stop Conditions'])/following::*[@class='vt list_add'])["+colum+"]", false))
		Reporter_ServiceNow.reportStep("The Double click could not be performed", "FAILURE");
	
	
	waitUntillElementTobeVisible("StartValue_Xpath");
	
	if(!enterByXpath("StartValue_Xpath", "2"))
		Reporter_ServiceNow.reportStep("The value could not be entered", "FAILURE");
	
	if(!clickById("ALERTPROFILE_attribute_right_id"))
		Reporter_ServiceNow.reportStep("The Ok Button could not be clicked", "FAILURE");
	
         
	Reporter_ServiceNow.reportStep("The New Row Inserted a new Stop Condition as expected.", "SUCCESS");
	
	return this;
}

public CorrelationProfilesPage clickInsertedNewRowforGroupedCIs() {
	
	clickGroupedCisWithoutReport();
	
	int colum=getColumnIndex1("CorrelatedProfile_GroupedCIS_Xpath", "Relationship");
	
	scrollToElementByXpath("((//h1[text()='Grouped CIs'])/following::*[@class='vt list_add list_edit_new_row'])["+colum+"]", false);
	
	if(!doubleCickByXpath("((//h1[text()='Grouped CIs'])/following::*[@class='vt list_add list_edit_new_row'])["+colum+"]", false))
		Reporter_ServiceNow.reportStep("The Double click could not be performed", "FAILURE");
	
	waitUntillElementTobeVisible("GroupedCIs_Xpath");
	
	if(!enterAndChoose1("GroupedCIs_Xpath", "**"))
		Reporter_ServiceNow.reportStep("The First value count not be entered", "FAILURE");
	
//	if(!clickById("ALERTPROFILE_attribute_right_id"))
//		Reporter_ServiceNow.reportStep("The Impacted attribute edit button could not be clicked", "FAILURE");
//	
	colum=getColumnIndex1("CorrelatedProfile_GroupedCIS_Xpath", "Direction");
	
	scrollToElementByXpath("((//h1[text()='Grouped CIs'])/following::*[@class='vt list_add'])["+colum+"]", false);
	
	if(!doubleCickByXpath("((//h1[text()='Grouped CIs'])/following::*[@class='vt list_add'])["+colum+"]", false))
		Reporter_ServiceNow.reportStep("The Double click could not be performed", "FAILURE");
	
	waitUntillElementTobeVisible("StartOperator_Xpath");
	
	if(!selectByIndexByXpath("StartOperator_Xpath", 1))
		Reporter_ServiceNow.reportStep("The First Value could not be selected", "FAILURE");
	
	if(!clickById("ALERTPROFILE_attribute_right_id"))
		Reporter_ServiceNow.reportStep("The Ok Button could not be clicked", "FAILURE");
	
	colum=getColumnIndex1("CorrelatedProfile_GroupedCIS_Xpath", "Starting CI");
	
	scrollToElementByXpath("((//h1[text()='Grouped CIs'])/following::*[@class='vt list_add'])["+colum+"]", false);
	
	if(!doubleCickByXpath("((//h1[text()='Grouped CIs'])/following::*[@class='vt list_add'])["+colum+"]", false))
		Reporter_ServiceNow.reportStep("The Double click could not be performed", "FAILURE");
	
	waitUntillElementTobeVisible("GroupedCIsStartingCI_Xpath");
	
	if(!enterAndChoose1("GroupedCIsStartingCI_Xpath", "**"))
		Reporter_ServiceNow.reportStep("The value could not be entered", "FAILURE");
//	
//	if(!clickById("ALERTPROFILE_attribute_right_id"))
//		Reporter_ServiceNow.reportStep("The Ok Button could not be clicked", "FAILURE");
//	
         
	Reporter_ServiceNow.reportStep("The New Row Inserted a new Groped CIs as expected.", "SUCCESS");
	
	return this;
}

	public CorrelationProfilesPage clickGroupAlertsWithoutReport() {
		
		if(!clickByXpath("CorrelatedProfile_GroupedAlertsTab_Xpath"))
			Reporter_ServiceNow.reportStep("The Grouped Alerts Tab could not be clicked", "FAILURE");
		return this;
	}
	public CorrelationProfilesPage clickStopConditionWithoutReport() {
		
		if(!clickByXpath("CorrelatedProfile_StopConditions_Xpath"))
			Reporter_ServiceNow.reportStep("The Stop Condtions Tab could not be clicked", "FAILURE");
		return this;
	}

	
	public CorrelationProfilesPage clickGroupedCisWithoutReport() {
		
		if(!clickByXpath("CorrelatedProfile_GroupedCis_Xpath"))
			Reporter_ServiceNow.reportStep("The Stop Condtions Tab could not be clicked", "FAILURE");
		return this;
	}

	

}
