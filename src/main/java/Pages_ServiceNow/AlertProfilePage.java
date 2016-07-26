package Pages_ServiceNow;


import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class AlertProfilePage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public AlertProfilePage(RemoteWebDriver driver) {

		this.driver = driver;
		resetImplicitWait(5);
		switchToMainFrame();
		// Check that we're on the right page.
		if(!(isExistByXpath("HomeTitle_Xpath")||isExistByXpath("NavBar2_Xpath")||isExistByXpath("ProfReg_Xpath"))) {
			Reporter_ServiceNow.reportStep("This is not the Alerts Profile page", "FAILURE");
		}
		resetImplicitWait(30);
	}

	public AlertProfilePage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}

	public AlertProfilePage selectReactionType(String reactionType) {

		scrollToElementByXpath("Reaction_TypeSelect_Xpath");

		if(selectByVisibleTextByXpath("Reaction_TypeSelect_Xpath", reactionType))
			Reporter_ServiceNow.reportStep("The value: "+reactionType+" is selected in Reaction Type field as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value: "+reactionType+" is not selected in Reaction Type, Check snapshot .","FAILURE");

		return this;
	}

	public AlertsProfilesListPage clickUpdateButton() {

		if(clickById("CIS_UpdateButton_Id"))
			Reporter_ServiceNow.reportStep("The Update Button is clicked successfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Update Button is not clicked.","FAILURE");

		return new AlertsProfilesListPage(driver);
	}

	public AlertProfilePage enterAndChooseOverrides(String Overrides) {

		if(enterAndChoose("ALERTPROFILE_Overrides_Xpath", Overrides))
			Reporter_ServiceNow.reportStep("The First Value is selected from Overrides field is successfully ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Overrides Group could not be entered","FAILURE");

		return this;

	}
	public AlertProfilePage verifyProfileNameInOverridesTab(String alertProfile) {

		scrollToElementByXpath("//h1[text()='Overriding Profiles']", false);
		
		if(isExistByXpath("//h1[text()='Overriding Profiles']/following::*[text()='"+alertProfile+"']", false))
			Reporter_ServiceNow.reportStep("The Value: "+alertProfile+" does exist under Overrides tab as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Value: "+alertProfile+" does not exist under Overrides tab, hence failure.","FAILURE");

		return this;

	}
	public AlertsListPage clickUpdate() {

		if(clickById("CIS_UpdateButton_Id"))
			Reporter_ServiceNow.reportStep("The Update Button is clicked successfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Update Button is not clicked.","FAILURE");

		return new AlertsListPage(driver);
	}
	public String getCIName() {

		String ci=getTextById("ALERTProfile_CIScope_Id");
		if(ci.equals(""))
			Reporter_ServiceNow.reportStep("CI value blank for selected alert, hence failure.","FAILURE");

		return ci;
	}
	public AlertProfilePage verifyAutoCloseValue(){

		String checkbox = getAttributeByXpath("AlertProfile_AutoClose_Xpath", "value");

		if(checkbox.equals("true"))
			Reporter_ServiceNow.reportStep("The Auto Closed for Node Status profile checked successfully","SUCCESS");
		else{
			if(clickByXpath("AlertProfile_AutoCloseclick_Xpath"))
				Reporter_ServiceNow.reportStep("The Auto Closed for Node Status profile checked successfully","SUCCESS");}

		return this;
	}
	public AlertProfilePage enterAndChooseAssGroup(String asGroup) {

		if(enterAndChoose("AlertProfile_OwninfGroup_Xpath", asGroup))
			Reporter_ServiceNow.reportStep("The Value: "+asGroup+" is selected from Owning Group field is successfully ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Owning Group could not be entered","FAILURE");

		return this;

	}	

	public AlertProfilePage selectSuppressTarget(String alertProfile) {

		if(clickByXpath("Alert_SuppressTargetSpy_Xpath"))
			Reporter_ServiceNow.reportStep("The Suppression Target Spy Class is not clicked or not found.","FAILURE");

		if(selectByVisibleTextByXpath("Alert_SuppressTargetSpy_Xpath", "Alert Profiles"))
			Reporter_ServiceNow.reportStep("The Table Name Alert Profiles is not selected  or not found.","FAILURE");

		if(enterAndChoose("DocumentText_Xpath", alertProfile))
			Reporter_ServiceNow.reportStep("The Value: "+alertProfile+" is selected in Document field successfully.","FAILURE");
		else
			Reporter_ServiceNow.reportStep("The Value: "+alertProfile+" is not  selected  or not found.","FAILURE");

		return this;
	}

	public AlertProfilePage verifyTriggerCondition(String triggervalue) {

		if (getDefaultValueById("trigCon").equals(triggervalue))
			Reporter_ServiceNow.reportStep("The Trigger Condition Value: "+triggervalue+" matched as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Trigger Condition Value: "+triggervalue+" does not match, hence failure.","FAILURE");

		return this;
	}

	public AlertProfilePage verifySeverity(String Severity) {

		if (getDefaultValueById("sevDedup").equals(Severity))
			Reporter_ServiceNow.reportStep("The Severity Deduplication Value :"+Severity+" matched as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Severity Deduplication Value: "+Severity+" does not as match, hence failure.","FAILURE");

		return this;
	}

	public AlertProfilePage verifyAutoClose(String autoClose) {


		if (getDefaultValueById("autoClose").equals(autoClose))
			Reporter_ServiceNow.reportStep("The Auto Close Value: "+autoClose+" matched as expected","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Auto Close default value not as expected","FAILURE");
		return this;
	}

	public AlertProfilePage verifyMandatoryfields() {

		String[] fileds={	"ALERTPROFILE_Registartion_CIscopes_mendatory_xpath", 
				"owningGroup", 
		"alertReaction"};
		String[] decs={	"CI Scopes", 
				"Owning Group", 
		"Alert Reaction"};

		verifyMandatoryFields(fileds, decs);

		return this;
	}


	public AlertProfilePage profileCreationWithDygrpYes(String name, String dyAssGrp, String inAssGrp,
			String ownGrp, String reactionType, String desc) {

		if(!enterById("Name", name))
			Reporter_ServiceNow.reportStep("The Name could not be entered", "FAILURE");

		if(!doubleCickByXpath("ALERTPROFILE_Registartion_CIscopes_xpath"))
			Reporter_ServiceNow.reportStep("The CI Scope could not be clicked", "FAILURE");

		if(!selectByVisibleTextById("Dy_Inc_Asn_Grp", dyAssGrp))
			Reporter_ServiceNow.reportStep("The Dynamic Incident Assignment Group Owning group could not be selected", "FAILURE");

		if(!selectByVisibleTextById("Inc_Asn_Area", inAssGrp))
			Reporter_ServiceNow.reportStep("The Incident Assignment Area could not be selected", "FAILURE");
		Wait(2000);

		if(!enterAndChoose("Alert_Profile_OwningGroup_Xpath_new_sparc", ownGrp))
			Reporter_ServiceNow.reportStep("The Owning group could not be entered", "FAILURE");

		if(!selectByVisibleTextById("Alert_Reaction", reactionType))
			Reporter_ServiceNow.reportStep("The Reaction Type could not be selected", "FAILURE");

		if(!enterById("Description", desc))
			Reporter_ServiceNow.reportStep("The Description could not be entered", "FAILURE");

		Wait(5000);

		if(!clickById("submit_button"))
			Reporter_ServiceNow.reportStep("The submit button is not clicked.", "FAILURE");
		Wait(5000);
		String profnum = getAttributeById("Prof_Num", "value");

		if (profnum != null)
			Reporter_ServiceNow.reportStep("The values Name: "+name+",Dynamic Incident Assignment Group: "+dyAssGrp+", Incident Assignment Area: "+inAssGrp+", Owning Group: "+ownGrp+""
					+ " Reaction Type: "+reactionType+", Description: "+desc+", and Record saved successfully","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Record could not be saved, hence failed","FAILURE");

		return this;
	}
	public AlertProfilePage verifyDefaultValues(String triggervalue, String Severity, String autoClose) {

		if (!getDefaultValueById("trigCon").equals(triggervalue))
			Reporter_ServiceNow.reportStep("The Trigger Condition Value: "+triggervalue+" does not match, hence failure.","FAILURE");

		if (!getDefaultValueById("sevDedup").equals(Severity))
			Reporter_ServiceNow.reportStep("The Severity Deduplication Value: "+Severity+" does not as match, hence failure.","FAILURE");

		if (getDefaultValueById("autoClose").equals(autoClose))
			Reporter_ServiceNow.reportStep("The Trigger Condition default Value: "+triggervalue+", Severity Deduplication default Value :"+Severity+", Auto Close default Value: "+autoClose+" matched as expected","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Auto Close default value not as expected","FAILURE");
		return this;
	}

	public AlertProfilePage profileCreationWithDygrpNo(String name, String dyAssGrp, String inAssGrp,
			String ownGrp, String reactionType, String desc) {

		if(!enterById("Name", name))
			Reporter_ServiceNow.reportStep("The Name could not be entered", "FAILURE");

		if(!doubleCickByXpath("ALERTPROFILE_Registartion_CIscopes_xpath"))
			Reporter_ServiceNow.reportStep("The CI Scope could not be clicked", "FAILURE");

		if(!selectByVisibleTextById("Dy_Inc_Asn_Grp", dyAssGrp))
			Reporter_ServiceNow.reportStep("The Dynamic Incident Assignment Group Owning group could not be selected", "FAILURE");

		waitUntillElementTobeVisible("Alert_Profile_IncidentAssignmentGroup_Xpath_new_sparc");
		
		if(!enterAndChoose("Alert_Profile_IncidentAssignmentGroup_Xpath_new_sparc", inAssGrp))
			Reporter_ServiceNow.reportStep("The Incident Assignment Area could not be entered", "FAILURE");
		Wait(2000);

		if(!enterAndChoose("Alert_Profile_OwningGroup_Xpath_new_sparc", ownGrp))
			Reporter_ServiceNow.reportStep("The Owning group could not be entered", "FAILURE");

		if(!selectByIndexByXpath("AlertProfile_ReactionType_Xpath", 1))
			Reporter_ServiceNow.reportStep("The Reaction Type could not be selected", "FAILURE");

		if(!enterById("Description", desc))
			Reporter_ServiceNow.reportStep("The Description could not be entered", "FAILURE");

		Wait(5000);

		if(!clickById("submit_button"))
			Reporter_ServiceNow.reportStep("The submit button is not clicked.", "FAILURE");
		Wait(5000);
		String profnum = getAttributeById("Prof_Num", "value");

		if (profnum != null)
			Reporter_ServiceNow.reportStep("The values Name: "+name+",Dynamic Incident Assignment Group: "+dyAssGrp+", Incident Assignment Area: "+inAssGrp+", Owning Group: "+ownGrp+""
					+ " Reaction Type: "+reactionType+", Description: "+desc+", and Record saved successfully","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Record could not be saved, hence failed","FAILURE");

		return this;
	}
	public AlertProfilePage selectCIClassAndClickUpdate(String f1Section){

		if(!selectByVisibleTextByXpath("CIS_CIClass_Xpath",f1Section))
			Reporter_ServiceNow.reportStep("In CI Class section "+ f1Section+ " could not be selected","FAILURE");

		if (clickById("CIS_UpdateButton_Id"))
			Reporter_ServiceNow.reportStep("The Value CI Class: "+ f1Section+ " is selected and The CI Scope is updated successfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Update button could not be clicked","FAILURE");

		return this;
	}

	public AlertProfilePage enterNameDesAutoCISco(String name, String desc, String autoclose) {

		if(!enterByXpath("Alert_Profile_Name_Xpath", name))
			Reporter_ServiceNow.reportStep("The "+name+" not given to the new alert profile", "FAILURE");

		if(!enterByXpath("Alert_Profile_Description_Xpath1", desc))
			Reporter_ServiceNow.reportStep("The Description: "+desc+" not given for the new alert profile", "FAILURE");

		if(!selectByVisibleTextById("autoClose", autoclose))
			Reporter_ServiceNow.reportStep("The Value: "+autoclose+" not selected for Autoclose", "FAILURE");

		if(doubleCickByXpath("ALERTPROFILE_Registartion_CIscopes_xpath"))
			Reporter_ServiceNow.reportStep("The Values: Name: "+name+", Short Description: "+desc+", Auto Close: "+autoclose+" and also first CI Scope is selected successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Step 6: CI Scope could not be selected", "FAILURE");

		return this;
	}

	public AlertProfilePage selectDynamic(String dynamic) {
		if(selectByVisibleTextById("Dy_Inc_Asn_Grp", dynamic))
			Reporter_ServiceNow.reportStep("The value: "+dynamic+" selected for Dynamic Assignment Group", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value: "+dynamic+" not selected for Dynamic Assignment Group", "FAILURE");

		return this;
	}

	public AlertProfilePage selectIncandOwnGrp(String incGrp, String ownGrp) {

		if(!enterAndChoose("Alert_Profile_IncidentAssignmentGroup_Xpath_new_sparc", incGrp))
			Reporter_ServiceNow.reportStep("The Incident Assignment Group not selected", "FAILURE");

		if(enterAndChoose("Alert_Profile_OwningGroup_Xpath_new_sparc", ownGrp))
			Reporter_ServiceNow.reportStep("The Values Incident Assignment Group: "+incGrp+", Owning Group: "+ownGrp+" is selected successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Owning Group could not be selected", "FAILURE");

		return this;
	}

	public AlertProfilePage clickSubmit() {
		if(clickById("submit_button"))
			Reporter_ServiceNow.reportStep("The Profile is created and workspace moved to profile configuration page as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Submit Button is not clicked, hence failure.", "FAILURE");

		return this;
	}
	public AlertProfilePage clickInsertedAlertConfigurations(String attribute) {

		int colum=getColumnIndex1("AlertProfile_AlertConfigurationsTableHead_Xpath", "Attribute");

		System.out.println(colum);
		
		if(!doubleCickByXpath("((//h1[text()='Alert Configurations'])/following::*[@class='vt list_add list_edit_new_row'])["+colum+"]", false))
			Reporter_ServiceNow.reportStep("The Impacted attribute edit button could not be clicked", "FAILURE");

		waitUntillElementTobeVisible("AlertConfiguration_AddAttribute_Xpath");
		
		if(enterAndChoose1("AlertConfiguration_AddAttribute_Xpath", attribute))
			Reporter_ServiceNow.reportStep("The First Value is selected in Alert Configurations successfully.", "SUCCESS");
	else
			Reporter_ServiceNow.reportStep("The First Value is not selected in Alert Configurations, hence failure", "FAILURE");

//		if(clickById("ALERTPROFILE_attribute_right_id")){
//			Reporter_ServiceNow.reportStep("The Value "+attribute+" is selected in Alert Configurations successfully.", "SUCCESS");}
//		else
//			Reporter_ServiceNow.reportStep("The Ok could not be clicked", "FAILURE");

		return this;
	}
	public String getProfileName() {

		String profnum = getAttributeById("Prof_Num", "value");

		if(profnum.equals(""))
			Reporter_ServiceNow.reportStep("The Profile Name is blank, hence failure", "FAILURE");

		return profnum;
	}
	public AlertProfilePage clicksendForApp() {

		if(clickByXpath("Alert_Profiles_SendForApproval_Xpath"))
			Reporter_ServiceNow.reportStep("The Send For Approval clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Send For Approval not clicked or not found.", "FAILURE");
		return this;
	}
	public AlertProfilePage profileCreationWithoutReport(String name, String dyAssGrp, String inAssGrp,
			String ownGrp, String reactionType, String desc) {

		if(!enterById("Name", name))
			Reporter_ServiceNow.reportStep("The Name could not be entered", "FAILURE");

		if(!doubleCickByXpath("ALERTPROFILE_Registartion_CIscopes_xpath"))
			Reporter_ServiceNow.reportStep("The CI Scope could not be clicked", "FAILURE");

		if(!selectByVisibleTextById("Dy_Inc_Asn_Grp", dyAssGrp))
			Reporter_ServiceNow.reportStep("The Dynamic Incident Assignment Group Owning group could not be selected", "FAILURE");

		if(!enterAndChoose("Alert_Profile_IncidentAssignmentGroup_Xpath_new_sparc", inAssGrp))
			Reporter_ServiceNow.reportStep("The Incident Assignment Area could not be entered", "FAILURE");
		Wait(2000);

		if(!enterAndChoose("Alert_Profile_OwningGroup_Xpath_new_sparc", ownGrp))
			Reporter_ServiceNow.reportStep("The Owning group could not be entered", "FAILURE");

		if(!selectByIndexByXpath("AlertProfile_ReactionType_Xpath", 1))
			Reporter_ServiceNow.reportStep("The Reaction Type could not be selected", "FAILURE");

		if(!enterById("Description", desc))
			Reporter_ServiceNow.reportStep("The Description could not be entered", "FAILURE");

		Wait(5000);

		if(!clickById("submit_button"))
			Reporter_ServiceNow.reportStep("The submit button is not clicked.", "FAILURE");

		return this;
	}
	public AlertProfilePage enterHoldTime(String sec) {

		scrollToElementByXpath("Alertprofile_HoldTimeSec_Xpath");
		
		if(enterByXpath("Alertprofile_HoldTimeSec_Xpath", sec))
			Reporter_ServiceNow.reportStep("The Alert Hold Time  is entered successfully  with value " +sec,"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Alert Hold Time  is not entered with value " +sec,"FAILURE");

		return this;
	}
	public AlertsListPage profileSave() {

		if(clickById("CIS_UpdateButton_Id"))
			Reporter_ServiceNow.reportStep("The Alert Profile is saved with user specified hold time as expected..","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Update Button is not clicked.","FAILURE");

		return new AlertsListPage(driver);
	}

	public AlertProfilePage selectCI() {

		if(!clickById("ALERTPROFILE_CIscopes_unlock_ID"))
			Reporter_ServiceNow.reportStep("The CI Scope Unlock button could not be clicked","FAILURE");

		if(enterAndChoose("Alert_CIScop_Xpath", "**"))
			Reporter_ServiceNow.reportStep("The CI Scope field is editable as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The CI Scope not editable, hence failure.","FAILURE");

		if(clickById("AlertPro_Lock_Id"))
			Reporter_ServiceNow.reportStep("The First CI Scope is selected successfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First CI Scope could not be selected.","FAILURE");

		return this;
	}
	public  String  getOwnGroup() {

		String grpName=getAttributeByXpath("", "value");
		if(grpName.equals(""))
			Reporter_ServiceNow.reportStep("The Owning Group is blank, hence failure","FAILURE");

		return grpName;

	}

	public AlertsListPage selectReactionTypeAndUpdate(String reactionType) {

		scrollToElementByXpath("Reaction_TypeSelect_Xpath");

		if(!selectByVisibleTextByXpath("Reaction_TypeSelect_Xpath", reactionType))
			Reporter_ServiceNow.reportStep("The value: "+reactionType+" is not selected in Reaction Type, Check snapshot .","FAILURE");

		if(clickById("CIS_UpdateButton_Id"))
			Reporter_ServiceNow.reportStep("The value: "+reactionType+" is selected in Reaction Type field and update button is clicked successfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Update Button is not clicked.","FAILURE");

		return new AlertsListPage(driver);
	}

	public AlertProfilePage doubleClickAlertReaction() {

		if(!doubleCickById("AlertReaction_Id"))
			Reporter_ServiceNow.reportStep("Double Click not performed, hence failure","FAILURE");
		else
			Reporter_ServiceNow.reportStep("The information icon next to Reaction Type box is double clicked successfully and New window to configure Reaction Type is launched as expected.","SUCCESS");

		return this;
	}

	public AlertProfilePage clickAlertReactionDefault() {

		if(getAttributeByXpath("AlertReaction_default_Xpath", "value").equals("true"))
			Reporter_ServiceNow.reportStep("The Configure new reaction data mark it as default as expected.","SUCCESS");
		else{
			clickById("AlertReaction_default_Id");
			Reporter_ServiceNow.reportStep("The Configure new reaction data mark it as default as expected.","SUCCESS");}

		return this;
	}
	public AlertsListPage ciProfileSave() {

		if(clickById("CIS_UpdateButton_Id"))
			Reporter_ServiceNow.reportStep("The Update button is clicked successfully and The Alert Profile is saved with new Reaction Type as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Update Button is not clicked.","FAILURE");

		return new AlertsListPage(driver);
	}

	public AlertProfilePage enterlable(String data) {

		if(enterById("Reaction_Lable_ID", data))
			Reporter_ServiceNow.reportStep("The value: "+data+" is entered in Label field as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value: "+data+" is not entered in Label, hence failure.","FAILURE");


		return this;
	}
	public AlertProfilePage verifyReactionType(String reactionType) {

		scrollToElementByXpath("AlertProfile_ReactionType_Xpath");

		if(selectByVisibleTextByXpath("AlertProfile_ReactionType_Xpath", reactionType))
			Reporter_ServiceNow.reportStep("The Created value: "+reactionType+" is available under Reaction Type field as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created value: "+reactionType+" is not available under Reaction Type field, hence failure.","FAILURE");

		return this;
	}
	public AlertProfilePage clickAlertReactionTab() {

		if(!clickByXpath("AlertProfiles_ReactionTab_Xpath"))
			Reporter_ServiceNow.reportStep("The Alert Reaction Tab is not clicked, hence failure.","FAILURE");

		return this;
	}
	public AlertProfilePage selectReactionType() {


		if(!selectByIndexByXpath("Reaction_TypeSelect_Xpath", 1))
			Reporter_ServiceNow.reportStep("The Reaction Type could not be selected, hence failure.","FAILURE");

		return this;
	}
	public AlertProfilePage IsOverridesReadOnly(String user) {

		if(verifyAttributeTextByXpath("AlertProfiles_Overrides_Xpath", "readonly", "true"))
			Reporter_ServiceNow.reportStep("The Overrides field is disable and The user: "+user+" not able to change the Overrides field as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Overrides Group is enable, hence failure.","FAILURE");

		return this;

	}
	public AlertProfilePage clickOverrideinfoButton() {

		if(!clickByXpath("AlertProfile_Overrides_Xpath"))
			Reporter_ServiceNow.reportStep("The Overrides info button is not clicked or not found, hence failure.","FAILURE");

		return this;

	}
	public AlertProfilePage clickOverrideTab() {

		if(!clickByXpath("AlertProfiles_OverridesTab_Xpath"))
			Reporter_ServiceNow.reportStep("The Overrides info button is not clicked or not found, hence failure.","FAILURE");

		return this;

	}

}
