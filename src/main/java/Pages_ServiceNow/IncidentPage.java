package Pages_ServiceNow;


import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ISDTContents;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testng.SuiteMethods;
import utils.ColorUtils;
import utils.Reporter_ServiceNow;
import wrapper.GenericWrappers;
import wrapper.ServiceNowWrappers;

public class IncidentPage extends SuiteMethods{

	private final RemoteWebDriver driver;
	private String incidentNumber;
	private String incidentState;
	private String incidentPriority;
	private String missingCausingNumber;

	public IncidentPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not the Incident page", "FAILURE");
		}
	}

	public IncidentPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}	

	public IncidentPage verifyAllMandatoryFields(){

		// you need to change the mandatory fields when the application changes
		String[] mandatoryFields = {"CREATEINC_RepCustStar_Xpath",
				"CREATEINC_ConfigItemStar_Xpath",
				"CREATEINC_AsgGroupStar_Xpath",
		"CREATEINC_shortDescStar_Xpath"};

		String[] mandatoryFieldsDesc = {"Reporting Customer",
				"Configuration Item",
				"Assignment Group",
		"Short Description"};

		verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);

		return this;

	}
	public IncidentPage verifyAllNonMandatoryFields(){

		// you need to change the non mandatory fields when the application changes
		String[] nonMandatoryFields = { "CREATEINC_AffectedUserStar_Xpath",
				"CREATEINC_DescriptionStar_Xpath",
				"CREATEINC_ComponentStar_Xpath",
				"CREATEINC_AssignedToStar_Xpath",
		"CREATEINC_BusinesService_Xpath"};

		String[] nonMandatoryFieldsDesc = { "Affected User",
				"Description",
				"CI Component",
				"Assigned To",
		"Busines Service"};

		verifyNonMandatoryFields(nonMandatoryFields, nonMandatoryFieldsDesc);

		return this;

	}

	public IncidentPage verifyAllReadOnlyFields(){

		// you need to change the read only fields when the application changes
		String[] readOnlyFields = { "CREATEINC_IncidentNumber_Xpath",
				"CREATEINC_IncidentState_Xpath",
		"CREATEINC_IncidentPriority_Xpath"};

		String[] readOnlyFieldsDesc = { "Incident Number",
				"Incident State",
		"Incident Priority"};



		// Verify read only
		verifyDisabledFieldsByXpath(readOnlyFields, readOnlyFieldsDesc);

		return this;

	}

	public IncidentPage verifyTabs(){

		// you need to change the read only fields when the application changes
		String[] tabNames ={"CREATEINC_Notes_Xpath",
		"CREATEINC_Process_Xpath"};

		String[] tabDesc ={"Notes",
		"Process Tabs"};

		// Verify the tabs exists
		verifyFieldsExistByXpath(tabNames, tabDesc);

		return this;

	}

	public String getIncidentNumber() {

		incidentNumber = getAttributeByXpath("CREATEINC_IncidentNumber_Xpath", "value");

		if(incidentNumber.equals(""))
			Reporter_ServiceNow.reportStep("The incident number is blank for newly created incident", "FAILURE");

		return incidentNumber;
	}

	public IncidentPage enterConfigurationItem(String configItem) {

		if(!enterAndChoose("CREATEINC_ConfigItem_Xpath", configItem))
			Reporter_ServiceNow.reportStep("The configuration item: "+configItem+" not found / could not be entered", "FAILURE");
		Wait(2000);
		return this;
	}

	public IncidentPage enterConfigurationItemForSuccess(String configItem) {

		if(enterAndChoose("CREATEINC_ConfigItem_Xpath", configItem))
			Reporter_ServiceNow.reportStep("The configuration item: "+configItem+" found and entered successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The configuration item: "+configItem+" not found / could not be entered.", "FAILURE");

		return this;
	}

	public IncidentPage enterConfigurationItemForFailure(String configItem) {

		if(!enterAndChoose("CREATEINC_ConfigItem_Xpath", configItem))
			Reporter_ServiceNow.reportStep("The configuration item: "+configItem+" not found as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The configuration item: "+configItem+" found and entered.", "FAILURE");

		return this;
	}

	public IncidentPage enterReportingCustomer(String repCust) {

		if(!enterAndChoose("CREATEINC_RepCust_Xpath", repCust))
			Reporter_ServiceNow.reportStep("The reporting customer: "+repCust+" not found / could not be entered", "FAILURE");
		else
			Reporter_ServiceNow.reportStep("The reporting customer: "+repCust+" is entered", "SUCCESS");
		return this;
	}

	public IncidentPage enterAssignmentGroup(String asgGroup) {

		if(!enterAndChoose("CREATEINC_AsgGroup_Xpath", asgGroup))
			Reporter_ServiceNow.reportStep("The Assignment Group: "+asgGroup+" not found / could not be entered", "FAILURE");

		return this;
	}

	public IncidentPage enterAssignedTo(String asgTo) {

		if(enterAndChoose("CREATEINC_AssignedTo_Xpath", asgTo))
			Reporter_ServiceNow.reportStep("The Assigned To: "+asgTo+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Assigned To: "+asgTo+" not found / could not be entered", "FAILURE");

		return this;
	}

	public IncidentPage enterAffectedUser(String aUser) {

		if(enterAndChoose("CREATEINC_AffectedUser_Xpath", aUser))	
			Reporter_ServiceNow.reportStep("The Affected User is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Affected User: "+aUser+" not found / could not be entered", "FAILURE");

		return this;
	}

	public IncidentPage enterShortDescription(String desc) {

		if(!enterByXpath("CREATEINC_shortDesc_Xpath", desc))
			Reporter_ServiceNow.reportStep("The short description: "+desc+" could not be entered", "FAILURE");

		return this;
	}

	public IncidentPage enterBusinessServiceForSuccess(String configItem) {

		if(enterAndChoose("CREATEINC_ConfigItem_Xpath", configItem))
			Reporter_ServiceNow.reportStep("The configuration item: "+configItem+" found and entered", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The configuration item: "+configItem+" not found / could not be entered", "FAILURE");

		return this;
	}


	// The page allows the user to submit the incident form
	public IncidentsListPage submitIncident() {

		Wait(2000);
		clickByXpath("CREATEINC_Submit_Xpath");
		goOutOfFrame();

		if (isExistById("Welcome_Id"))
			Reporter_ServiceNow.reportStep("The Create Incident process is successful", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Create Incident process failed. Check snapshot", "FAILURE");

		return new IncidentsListPage(driver);
	}

	// The page allows the user to submit the incident form
	public IncidentPage saveIncident() {

		clickById("Save_Id");
		Wait(5000);

		if (getTextByXpath("CREATEINC_Pointer_Xpath").contains(getIncidentNumber()))
			Reporter_ServiceNow.reportStep("The Create Incident process is successful", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Create Incident process failed. Check snapshot", "FAILURE");
		return this;
	}

	// The page allows the user to submit the incident form
	public IncidentPage saveIncidentExpectingFailure()  {

		clickById("Save_Id");
		return this;
	}

	public IncidentsListPage createNewIncident(String configItem, String repCust, String asgGroup, String desc) {

		return  enterConfigurationItem(configItem).
				enterReportingCustomer(repCust).
				enterAssignmentGroup(asgGroup).
				enterShortDescription(desc).
				submitIncident();

	}

	public IncidentPage populateMandatoryFields(String configItem, String repCust, String asgGroup, String desc) {

		enterConfigurationItem(configItem).
		enterReportingCustomer1(repCust).
		enterAssignmentGroup(asgGroup).
		enterShortDescription(desc);

		Reporter_ServiceNow.reportStep("The Mandatory fields: Configuration Item, Reporting Customer, Assignment Group and Short Description of Create Incident are entered successfully", "SUCCESS");
		return this;

	}

	public IncidentPage verifyNotesFields(){

		// you need to change the read only fields when the application changes
		String[] notesFields = {"CREATEINC_Worknoteslist_Xpath",
				"CREATEINC_WorkNotes_Xpath",
				"CREATEINC_Latestworknotes_Xpath",
				"CREATEINC_CustomerWatchlist_Xpath",
				"CREATEINC_CustomerComms_Xpath",
		"CREATEINC_LatestCustomerUpdate_Xpath"};

		String[] descFields = {"Work Notes List",
				"Work Notes",
				"Latest Work Notes",
				"Customer Watch List",
				"Customer Comms",
		"Latest Customer Comms"};


		clickByXpath("CREATEINC_Notes_Xpath");
		//scrollPageDown();
		scrollToElementByXpath("CREATEINC_Latestworknotes_Xpath");

		// Verify the notes fields exists
		verifyFieldsExistByXpath(notesFields, descFields);
		return this;

	}

	public IncidentPage verifyNotesReadOnlyFields(){

		// you need to change the read only fields when the application changes
		String[] readOnlyFields = { "CREATEINC_Latestworknotes_Xpath",
		"CREATEINC_LatestCustomerUpdate_Xpath"};

		String[] readOnlyFieldsDesc = { "Latest Work Notes ",
		"Latest Customer Update"};

		clickByXpath("CREATEINC_Notes_Xpath");
		//scrollPageDown();
		scrollToElementByXpath("CREATEINC_Latestworknotes_Xpath");

		// Verify read only
		verifyDisabledFieldsByXpath(readOnlyFields,readOnlyFieldsDesc);
		return this;

	}

	public IncidentPage clickProcess() {
		if(clickByXpath("CREATEINC_Process_Xpath"))
			Reporter_ServiceNow.reportStep("The Process Tab is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Process Tab could not be entered", "FAILURE");
		return this;
	}

	public IncidentPage verifyProcessFields(){

		// you need to change the read only fields when the application changes
		String[] processFields = {  "CREATEINC_MasterIncident_Xpath",
				"CREATEINC_GTrackChange_Xpath",
				"CREATEINC_DeviationNumber_Xpath",
				"CREATEINC_GxPSystem_Xpath",
		"CREATEINC_SOXSystem_Xpath"};

		String[] processDesc = {  "Master Incident",
				"GTrack Change #",
				"Deviation Number",
				"GxP System",
		"SOX System"};

		clickByXpath("CREATEINC_Process_Xpath");
		//scrollPageDown();
		scrollToElementByXpath("CREATEINC_GxPSystem_Xpath");

		// Verify the notes fields exists
		verifyFieldsExistByXpath(processFields, processDesc);
		return this;

	}

	public IncidentPage verifyProcessReadOnlyFields(){

		// you need to change the read only fields when the application changes
		String[] readOnlyFields = { "CREATEINC_GxPSystem_Xpath",
		"CREATEINC_SOXSystem_Xpath"};

		String[] readOnlyFieldsDesc = { " GxP System",
		"SOX System"};

		clickByXpath("CREATEINC_Process_Xpath");
		//scrollPageDown();
		scrollToElementByXpath("CREATEINC_SOXSystem_Xpath");

		// Verify read only
		verifyDisabledFieldsByXpath(readOnlyFields, readOnlyFieldsDesc);

		return this;

	}

	public IncidentPage verifyProcessEditableFields(){

		// you need to change the read only fields when the application changes
		String[] editableFields = { "CREATEINC_MasterIncident_Xpath",
				"CREATEINC_GTrackChange_Xpath",
		"CREATEINC_DeviationNumber_Xpath"};

		String[] editableFieldsDesc = { "Master Incident",
				"GTrack Change #",
		"Deviation Number"};

		// Verify read only
		verifyEnabledFieldsByXpath(editableFields, editableFieldsDesc);

		return this;

	}

	public IncidentPage verifyErrorMessage(String errorMessage){

		//  System.out.println(getTextAndAcceptAlert());
		String text=driver.switchTo().alert().getText();
		alertAccept();
		if(text.equals(errorMessage))   
			Reporter_ServiceNow.reportStep("The error message :"+errorMessage+" appeared as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The error message :"+errorMessage+" did not appear", "FAILURE");

		return this; 

	}
	public IncidentPage isReportingCustomerDisabled() {

		String[] field = {"CREATEINC_RepCustLabel_Xpath"};
		String[] fieldDesc = {"Reporting Customer"};

		verifyDisabledFieldsByXpath(field, fieldDesc);
		return this;
	}

	public IncidentPage isAffectedUserDisbled() {

		String[] field = {"CREATEINC_AffectedUserLabel_Xpath"};
		String[] fieldDesc = {"Affected User"};

		verifyDisabledFieldsByXpath(field, fieldDesc);
		return this;
	}

	public IncidentPage isReportingCustomerEnabled() {

		String[] field = {"CREATEINC_RepCustLabel_Xpath"};
		String[] fieldDesc = {"Reporting Customer"};

		verifyEnabledFieldsByXpath(field, fieldDesc);
		return this;
	}

	public IncidentPage isAffectedUserEnabled() {

		String[] field = {"CREATEINC_AffectedUserLabel_Xpath"};
		String[] fieldDesc = {"Affected User"};

		verifyEnabledFieldsByXpath(field, fieldDesc);
		return this;
	}

	public IncidentPage clickCIComponentLookUp(){

		if(!clickById("CREATEINC_CIComponent_Lookup_Id"))
			Reporter_ServiceNow.reportStep("The CI Component - Look up could not be clicked.", "FAILURE");

		return this;
	}

	public IncidentPage verifyCIComponentLookUpValues(){

		Boolean bSuccess = true;
		switchToSecondWindow();
		switchToDefault();

		String[] elements = {"Automated Job Failure", "Connectivity", "Integration Issue", "Performance degradation", "Security breach"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI Component Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}

	//	public IncidentPage hoverCallerId(){
	//
	//		mouseOverById("CREATEINC_Caller_Id");
	//
	//		if(isExistById("CREATEINC_SysUserName_Id"))
	//			Reporter_ServiceNow.reportStep("The User Information displayed successfully", "SUCCESS");
	//		else	
	//			Reporter_ServiceNow.reportStep("The User Information is not on mouse Over", "FAILURE");
	//
	//		return this;
	//
	//	}

	public IncidentPage verifyBusinessServiceReadOnly(){

		// you need to change the read only fields when the application changes
		String[] readOnlyField = { "CREATEINC_BusinesService_Xpath"};
		String[] readOnlyFieldDesc = { "Business Service"};


		// Verify read only
		verifyDisabledFieldsByXpath(readOnlyField, readOnlyFieldDesc);
		return this;

	}

	public IncidentPage verifyBusinessServiceEditable(){

		// you need to change the enabled fields when the application changes
		String[] editableField = { "CREATEINC_BusinesService_Xpath"};
		String[] editableFieldDesc = { "Business Service"};


		// Verify read only
		verifyEnabledFieldsByXpath(editableField, editableFieldDesc);

		return this;

	}

	public IncidentPage verifyBusinessServiceEditable1(){

		// you need to change the enabled fields when the application changes
		String[] editableField = { "CREATEINC_BusinesService_Xpath"};
		String[] editableFieldDesc = { "Business Service"};


		// Verify read only
		verifyDisabledFieldsByXpath(editableField, editableFieldDesc);

		return this;

	}
	public IncidentPage verifyBusinessServiceContent(String sBusinessService){

		if(getAttributeByXpath("CREATEINC_BusinesService_Xpath","value").equals(sBusinessService))
			Reporter_ServiceNow.reportStep("The business service field has value: "+sBusinessService+" as expected" , "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The business service field do not have value: "+sBusinessService+"; hence failed" , "FAILURE");

		return this;

	}

	public IncidentPage hoverGroupId(){

		mouseOverById("CREATEINC_AssiGroup_Id");

		if(isExistById("CREATEINC_GroupEmail_Id"))
			Reporter_ServiceNow.reportStep("The Group Information displayed successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Group Information is not on mouse Over", "FAILURE");
		return this;

	}

	public IncidentPage enterAssignedToForSuccess(String asgTo) {

		if(enterAndChoose("CREATEINC_AssignedTo_Xpath", asgTo))
			Reporter_ServiceNow.reportStep("The Assigned To: "+asgTo+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Assigned To: "+asgTo+" not found / could not be entered", "FAILURE");
		return this;
	}

	public IncidentPage enterAssignedToForFailure(String asgTo) {

		if(!enterAndChoose("CREATEINC_AssignedTo_Xpath", asgTo))
			Reporter_ServiceNow.reportStep("The Assigned To: "+asgTo+" is not found as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Assigned To: "+asgTo+" is found and entered", "FAILURE");
		return this;
	}

	public IncidentPage verifyEditWorkNotesList(){

		// you need to change the read only fields when the application changes
		String[] tabNames ={"CREATEINC_Worknoteslist_Xpath","CREATEINC_AddMeIcon_Xpath"};

		String[] tabDesc ={"Edit Work Notes","Add me"};

		// Verify the tabs exists
		verifyFieldsExistByXpath(tabNames, tabDesc);
		return this;
	}
	public IncidentPage clickEditWorkNotesList() {

		if(clickByXpath("CREATEINC_Worknoteslist_Xpath"))
			Reporter_ServiceNow.reportStep("The Edit Work Notes List is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Edit Work Notes List could not be clicked", "FAILURE");
		return this;
	}

	public IncidentPage enterEditWorkNotesList(String asgTo) {

		if(enterAndChoose("CREATEINC_SelectUSerToWorkNotes_Xpath", asgTo))
			Reporter_ServiceNow.reportStep(""+asgTo+" is added to Work Notes List successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep(""+asgTo+" could not be added to Work Notes List", "FAILURE");
		return this;
	}

	public IncidentPage addMetoNotes(String user) {

		if(clickByXpath("CREATEINC_AddMeLocked_Xpath"))
			Reporter_ServiceNow.reportStep("The User: "+user+" is added to Work Notes List successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The User could not be added to Work Notes List ", "FAILURE");
		return this;
	}

	public IncidentPage lockNotes() {

		if(clickById("CREATEINC_LockNotes_Id"))
			Reporter_ServiceNow.reportStep("The lock Notes is clicked successfully ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The lock Notes could not be clicked ", "FAILURE");
		return this;
	}

	public IncidentPage unlockNotes() {

		if(clickById("CREATEINC_UnlockNotes_Id"))
			Reporter_ServiceNow.reportStep("The Unlock Notes is clicked successfully ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Unlock Notes could not be clicked  ", "FAILURE");
		return this;
	}

	public IncidentPage selectUserAndRemoveFromNotes(String user) {

		if(!clickById("CREATEINC_UnlockNotes_Id"))
			Reporter_ServiceNow.reportStep("The Unlock Notes could not be clicked  ", "FAILURE");

		if(!clickByXpath("CREATEINC_selectUSerFromWorkNotes_Xpath"))
			Reporter_ServiceNow.reportStep("The User could not be selected from Work Notes List ", "FAILURE");

		if(!clickByXpath("CREATEINC_RemoveUSerFromWorkNotes_Xpath"))		
			Reporter_ServiceNow.reportStep("The User could not be removed from Work Notes List ", "FAILURE");

		//		Wait(3000);
		//		if(!(isExistByXpath("CREATEINC_AddMeWorkNotesList_Xpath")))
		//			Reporter_ServiceNow.reportStep("The Add Me Button is appeared, hence failure.", "FAILURE");

		if(clickById("CREATEINC_LockNotes_Id"))
			Reporter_ServiceNow.reportStep("The User: "+user+" is removed from Work Notes List and Add Me Button disappeared as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The lock Notes could not be clicked ", "FAILURE");

		return this;
	}

	public IncidentPage isExistsAddUser() {

		if(isExistByXpath("CREATEINC_AddMeIcon_Xpath"))
			Reporter_ServiceNow.reportStep("The Add me icon appears successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Add me icon could not found", "FAILURE");
		return this;
	}
	public IncidentPage enterEditWorkNotesListForFailure(String asgTo) {

		if(!enterAndChoose("CREATEINC_SelectUSerToWorkNotes_Xpath", asgTo))
			Reporter_ServiceNow.reportStep("The User : "+asgTo+" is not found as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The User: "+asgTo+" is found and entered", "FAILURE");
		return this;
	}

	public IncidentPage verifyCustomerWatchlist(){

		// you need to change the read only fields when the application changes
		String[] tabNames ={"CREATEINC_CustomerWatchlist_Xpath","CREATEINC_AddMeIcon_Xpath"};

		String[] tabDesc ={"Edit Customer Watchlist","Add me"};

		// Verify the tabs exists
		verifyFieldsExistByXpath(tabNames, tabDesc);
		return this;
	}

	public IncidentPage clickCustomerWatchlist() {

		if(clickByXpath("CREATEINC_CustomerWatchlist_Xpath"))
			Reporter_ServiceNow.reportStep("The Edit Customer Watchlist is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Edit Customer Watchlist List could not be clicked", "FAILURE");
		return this;
	}


	public IncidentPage enterCustomerWatchlist(String asgTo) {

		if(enterAndChoose("CREATEINC_SelectUSerToCustomerWatchlist_Xpath", asgTo))
			Reporter_ServiceNow.reportStep(""+asgTo+" is added to Customer Watchlist  successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep(""+asgTo+" could not be added to Customer Watchlist ", "FAILURE");
		return this;
	}
	public IncidentPage enterCustomerWatchlistForFailure(String asgTo) {

		if(!enterAndChoose("CREATEINC_SelectUSerToCustomerWatchlist_Xpath", asgTo))
			Reporter_ServiceNow.reportStep(""+asgTo+" is not added to Customer Watchlist  as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep(""+asgTo+" is added to Customer Watchlist ", "FAILURE");
		return this;
	}

	public IncidentPage lockCustomerWatchlist() {

		if(clickById("CREATEINC_LockCustomerWatchlist_Id"))
			Reporter_ServiceNow.reportStep("The lock Customer Watchlist is clicked successfully ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The lock Customer Watchlist could not be clicked ", "FAILURE");
		return this;
	}

	public IncidentPage unlockCustomerWatchlist() {

		if(clickById("CREATEINC_UnCustomerWatchlist_Id"))
			Reporter_ServiceNow.reportStep("The Unlock Customer Watchlist is clicked successfully ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Unlock Customer Watchlist could not be clicked  ", "FAILURE");
		return this;
	}
	public IncidentPage selectUserAndRemoveFromCustomerWatchlist() {

		if(!clickByXpath("CREATEINC_selectUSerFromCustomerWatchlist_Xpath"))
			Reporter_ServiceNow.reportStep("The User could not be selected from Customer Watchlist", "FAILURE");

		if(clickByXpath("CREATEINC_RemoveUSerFromCustomerWatchlist_Xpath"))		
			Reporter_ServiceNow.reportStep("The User is removed from Customer Watchlist  successfully ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The User could not be removed from Customer Watchlist  ", "FAILURE");
		return this;
	}

	public IncidentPage verifyColorOfCustomerComms(){

		// this is to keep focus out of the edit/select field
		clickByXpath("CREATEINC_Notes_Xpath");
		//scrollPageDown();
		scrollToElementById("CREATEINC_CustomerComms_Id");

		String sVal = getBackgroundColorById("CREATEINC_CustomerComms_Id");
		//System.out.println(sVal);
		ColorUtils color = new ColorUtils();
		String colorval = color.getColorName(sVal);
		if(!colorval.equals(""))
			Reporter_ServiceNow.reportStep("The Color of the Customer Comms is "+colorval+" ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Color of the Customer Comms is "+colorval+" did not match ", "WARNING");
		return this;
	}

	public IncidentPage verifyColorOfWorkNotes(){

		// this is to keep focus out of the edit/select field
		clickByXpath("CREATEINC_Notes_Xpath");
		//scrollPageDown();
		scrollToElementById("CREATEINC_WorkNotes_Id");

		String sVal = getBackgroundColorById("CREATEINC_WorkNotes_Id");		
		ColorUtils color = new ColorUtils();
		String colorval = color.getColorName(sVal);
		if(colorval.equals("Green"))
			Reporter_ServiceNow.reportStep("The Color of the Work notes is "+colorval+" ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Color of the Work notes is "+colorval+" did not match ", "WARNING");
		return this;
	}

	public String getIncidentState() {

		incidentState = getDefaultValueByXpath("CREATEINC_IncidentState_Xpath");
		if(incidentState.equals(""))
			Reporter_ServiceNow.reportStep("The incident state is blank for newly created incident", "FAILURE");
		return incidentState;
	}

	public String getIncidentPriority() {

		incidentPriority = getDefaultValueByXpath("CREATEINC_IncidentPriority_Xpath");
		if(incidentPriority.equals(""))
			Reporter_ServiceNow.reportStep("The incident priority is blank for newly created incident", "FAILURE");
		return incidentPriority;
	}

	public IncidentPage verifyStateAndPriority(){

		// you need to change the read only fields when the application changes
		String[] tabNames ={"CREATEINC_IncidentState_Xpath","CREATEINC_IncidentPriority_Xpath"};

		String[] tabDesc ={"State is New","Priority is 3 - Moderate"};

		// Verify the tabs exists
		verifyFieldsExistByXpath(tabNames, tabDesc);
		return this;
	}

	/*	public IncidentPage verifyActivityLog(String assignedTo, String assGroup){

		String[] tabNames ={"CREATEINC_ActivlogAssignedTo_Xpath","CREATEINC_ActivlogAssignmentGroup_Xpath","CREATEINC_ActivlogPriority_Xpath" 
				,"CREATEINC_ActivlogShortDescription_Xpath" ,"CREATEINC_ActivlogState_Xpath"};

		String[] tabDesc ={assignedTo,assGroup,"3 - Moderate","Test Message","Assigned"};

		// Verify the tabs exists
//		verifyFieldsExistByXpath(tabNames, tabDesc);

		Boolean bReturn = true;
		for (int i=0; i < tabNames.length; i++) {
			if(!isExistByXpath(tabNames[i])){
				bReturn = false;
				Reporter_ServiceNow.reportStep("The field :"+tabDesc[i]+" is not displayed; hence failed","FAILURE");
			}			
		}

		if(bReturn)			
			Reporter_ServiceNow.reportStep("All the fields:"+convertStringArrayToString(tabDesc)+" are displayed in the activity log as expected","SUCCESS");

		return this;
	}
	 */
	// not correct way of doung // 
	/*	public IncidentPage uploadFile(String fileName) {

		boolean bFail = true;
		if(clickByXpath("Attachment_Xpath")){
			try {
				String filePath = System.getProperty("user.dir") + "/data/"+fileName+".xlsx";
				enterById("ChooseFiles_Id", filePath);

				clickByXpath("AttachFile_Xpath");
				Wait(10000);

				isExistByXpath("IsAttachmentUploaded");
				clickByXpath("CREATEINC_CloseUpload_Xpath");

				bFail = false;
				scrollToElementByXpath("CREATEINC_ManageAttachments_Xpath");
				Wait(1000);
				Reporter_ServiceNow.reportStep("The Attachment is Uploaded successfully ", "SUCCESS");
				clickById("Save_Id");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}			

		if(bFail)
			Reporter_ServiceNow.reportStep("The Attachment could not be Uploaded  ", "FAILURE");
		return this;
	}

	 */	
	public IncidentPage verifyAttachmentUploaded(){

		clickById("Save_Id");
		Wait(5000);

		String[] tabNames ={"CREATEINC_verifyAttachments_Xpath"};
		String[] tabDesc ={"Attachment has been attached."};
		scrollToElementByXpath("CREATEINC_ActivityLog_Xpath");

		// Verify the tabs exists
		verifyFieldsExistByXpath(tabNames, tabDesc);
		return this;
	}

	public IncidentPage RemoveAttachment() {
		boolean bFail = true;
		if(clickByXpath("Attachment_Xpath")){
			try {
				clickByXpath("CREATEINC_SelectAttach_Xpath");
				clickByXpath("CREATEINC_RemoveAttachment_Xpath");
				Wait(10000);
				clickByXpath("CREATEINC_CloseUpload_Xpath");
				clickByXpath("CREATEINC_Notes_Xpath");
				scrollToElementByXpath("CREATEINC_IncidentNumber_Xpath",0,-100);
				Wait(1000);
				bFail = false;
				//Reporter_ServiceNow.reportStep("The Attachment is removed ", "SUCCESS");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(bFail)
			Reporter_ServiceNow.reportStep("The Attachment could not be removed.", "FAILURE");
		else{
			scrollToElementByXpath("CREATEINC_IncidentNumber_Xpath",0,-100);
			Reporter_ServiceNow.reportStep("The Attachment is Removed successfully.", "SUCCESS");}

		return this;
	}

	public IncidentPage verifyAttachmentRemoved(){

		clickById("Save_Id");
		String[] tabNames ={"CREATEINC_verifyAttachments_Xpath"};
		String[] tabDesc ={"Attachment has been removed."};
		verifyFieldsExistByXpath(tabNames, tabDesc);
		return this;
	}

	public IncidentPage isWorkNotesAvailable(){

		// click the first Incident Link
		if(IsElementNotPresentByXpath("Inc_EnterWorkNotes_Xpath"))
			Reporter_ServiceNow.reportStep("The Work Notes is not available", "WARNING");
		else
			Reporter_ServiceNow.reportStep("The Work Notes is present", "SUCCESS");
		return this;
	}

	public IncidentPage isCustomerCommsAvailable(){		

		// click the first Incident Link
		if(IsElementNotPresentById("CREATEINC_CustomerComms_Id"))
			Reporter_ServiceNow.reportStep("The Customer Comms is not available", "WARNING");
		else
			Reporter_ServiceNow.reportStep("The Customer Comms is present", "SUCCESS");
		return this;
	}

	public IncidentPage clickNotes() {

		if(clickByXpath("CREATEINC_Notes_Xpath")){
			Wait(1000);
			Reporter_ServiceNow.reportStep("The Notes Tab is clicked successfully ", "SUCCESS");
		}else
			Reporter_ServiceNow.reportStep("The Notes Tab could not be clicked  ", "FAILURE");
		return this;
	}

	public IncidentPage enterWorkNotes(String workNotes) {

		// duplicating the code as webdriver haing issue with this field (not entering first time)
		enterByXpath("CREATEINC_WorkNotes_Xpath",workNotes);
		if(enterByXpath("CREATEINC_WorkNotes_Xpath",workNotes ))
			Reporter_ServiceNow.reportStep("The value : "+workNotes+" is entered in Work Notes field successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The value : "+workNotes+" could not be entered in Work Notes field", "FAILURE");
		return this;
	}
	public IncidentPage enterCustomerComms(String CustomerComms) {

		enterByXpath("CREATEINC_CustomerComms_Xpath",CustomerComms);
		if(enterByXpath("CREATEINC_CustomerComms_Xpath",CustomerComms ))
			Reporter_ServiceNow.reportStep("The value : "+CustomerComms+" is entered in Customer Comms field successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The value : "+CustomerComms+" could not be entered in Customer Comms field", "FAILURE");
		return this;
	}

	public IncidentPage clickSave() {

		if(clickById("Save_Id"))
			Reporter_ServiceNow.reportStep("The Save button is clicked successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Save button could not be clicked or not found", "FAILURE");
		return this;
	}
	public IncidentPage getLatestWorkNotes(String text) {

		if(!clickByXpath("CREATEINC_Notes_Xpath"))
			Reporter_ServiceNow.reportStep("The Notes Tab is not clicked or not clicked.", "FAILURE");
		//scrollPageDown();
		scrollToElementByXpath("CREATEINC_Latestworknotes_Xpath");

		if(!(getAttributeByXpath("CREATEINC_Latestworknotes_Xpath", "readonly")).equals("true"))
			Reporter_ServiceNow.reportStep("The Latest WorkNotes field is not read only, hence failure.", "FAILURE");

		if(getTextByXpath("CREATEINC_Latestworknotes_Xpath").contains(text))
			Reporter_ServiceNow.reportStep("The value :"+text+" does exist in Latest Work Notes field and the field is read only as expected.", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The value :"+text+" does not exist in Latest Work Notes field", "WARNING");
		return this;
	}

	public IncidentPage getLatestCustomerComms(String text) {

		clickByXpath("CREATEINC_Notes_Xpath");
		//scrollPageDown();
		scrollToElementByXpath("CREATEINC_LatestCustomerUpdate_Xpath");
		System.out.println((getAttributeByXpath("CREATEINC_LatestCustomerUpdate_Xpath", "readonly")));

		if(!(getAttributeByXpath("CREATEINC_LatestCustomerUpdate_Xpath", "readonly")).equals("true"))
			Reporter_ServiceNow.reportStep("The Latest Customer Notes field is not read only, hence failure.", "FAILURE");

		if( getTextByXpath("CREATEINC_LatestCustomerUpdate_Xpath").contains(text))
			Reporter_ServiceNow.reportStep("The value :"+text+"  does exist in Latest Customer Notes field and the field is read only as expected.", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The value :"+text+"  does not exist in Latest Customer Notes field", "FAILURE");
		return this;
	}

	public IncidentsListPage submit() {

		clickByXpath("Submit_Xpath");
		goOutOfFrame();
		if (isExistById("Welcome_Id"))
			Reporter_ServiceNow.reportStep("The Create Incident process is successful", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Create Incident process failed. Check snapshot", "FAILURE");
		return new IncidentsListPage(driver);
	}

	public IncidentPage getBackgroundofActivityWorkNotes(String text) {

		scrollToElementByXpath("CREATEINC_ActivityLog_Xpath");

		if(!clickByXpath("CREATEINC_ActivityLog_Xpath"))
			Reporter_ServiceNow.reportStep("The Activity Log could not be clicked  ", "FAILURE");

		String sVal = getBackgroundColorByXpath("WIP_ActivityWorkNotesBackground");		

		ColorUtils color = new ColorUtils();

		String colorval = color.getColorName(sVal);

		System.out.println(colorval);

		if(!colorval.equals(colorval))
			Reporter_ServiceNow.reportStep("The background colour doesnot matched with"+colorval+", hence failure.", "FAILURE");

		if(getTextByXpath("WIP_WorkNotesUpdate_Xpath").contains(text))	
			Reporter_ServiceNow.reportStep("The Value: "+text+" is updated in Activity log and background colour is "+colorval+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Value: "+text+" is not updated in activity log.", "FAILURE");

		return this;
	}

	public IncidentPage getBackgroundofActivityCustomerComms(String text) {

		scrollToElementByXpath("CREATEINC_ActivityLog_Xpath");

		if(!clickByXpath("CREATEINC_ActivityLog_Xpath"))
			Reporter_ServiceNow.reportStep("The Activity log is not clicked or not found.", "FAILURE");

		scrollToElementByXpath("WIP_WorkNotesUpdate_Xpath");

		String sVal = getBackgroundColorByXpath("WIP_ActivityCustomerCommsBackground");		

		ColorUtils color = new ColorUtils();

		String colorval = color.getColorName(sVal);

		System.out.println(colorval);
		if(!colorval.equals("Linen"))
			Reporter_ServiceNow.reportStep("The background colour is not matched with Red, hence failure.", "FAILURE");

		if(getTextByXpath("WIP_WorkNotesUpdate_Xpath").contains(text))	
			Reporter_ServiceNow.reportStep("The "+text+" is updated in Activity log and The background colour is Red as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The "+text+" is not updated in activity log.", "FAILURE");

		return this;
	}

	public IncidentPage getActivityText(String text) {

		clickByXpath("CREATEINC_ActivityLog_Xpath");
		//scrollPageDown();
		scrollToElementByXpath("WIP_WorkNotesUpdate_Xpath");

		if(getTextByXpath("WIP_WorkNotesUpdate_Xpath").contains(text))	
			Reporter_ServiceNow.reportStep("The "+text+" is updated in Activity log as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The "+text+" is not updated in activity log.", "WARNING");
		return this;
	}

	// The page allows the user to submit the incident form
	public IncidentPage submitIncidentExpectingFailure()  {
		clickByXpath("Submit_Xpath");
		return this;
	}

	// Babu wrote this method to click on WIP 
	// 02 November 2015
	public IncidentPage clickWIP() {

		clickByXpath("CREATEINC_WipButton_Xpath");

		if (getDefaultValueByXpath("CREATEINC_IncidentState_Xpath").equals("Work in Progress"))
			Reporter_ServiceNow.reportStep("The Work In Progress process is successful", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Work In Progress process failed. Check snapshot", "FAILURE");
		return this;
	}	

	public IncidentPage clickResolutionInformation(){

		if(clickByXpath("WIP_ResolutionInformation_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolution Information tab is clicked successfully ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Resolution Information tab could not be clicked  ", "FAILURE");
		return this;
	}

	public IncidentPage verifyResolutionInformationFieldsReadOnly(){

		// you need to change the read only fields when the application changes
		String[] readOnlyFields = { "WIP_RICausingCI_Xpath","WIP_RICausingCIComponent_Xpath"};
		String[] readOnlyFieldsDesc = { "Causing CI","Causing CI Component"};
		// Verify read only
		verifyDisabledFieldsByXpath(readOnlyFields,readOnlyFieldsDesc);
		return this;
	}

	public IncidentPage clickActivityLog() {

		scrollToElementByXpath("CREATEINC_ActivityLog_Xpath");
		if(clickByXpath("CREATEINC_ActivityLog_Xpath")){
			Wait(1000);
			Reporter_ServiceNow.reportStep("The Activity Log is clicked successfully ", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The Activity Log could not be clicked  ", "FAILURE");
		return this;
	}

	public IncidentPage verifyActivityLogDisplyed(){



		if(!clickByXpath("CREATEINC_ActivityLog_Xpath"))
			Reporter_ServiceNow.reportStep("The Activity log is not clicked or not found.", "FAILURE");

		scrollToElementByXpath("WIP_ALHeader_Xpath");
		// Verify the tabs exists
		if(isExistByXpath("WIP_ALHeader_Xpath"))
			Reporter_ServiceNow.reportStep("The Activity Log details:"+getTextByXpath("WIP_ALHeader_Xpath").substring(0, 100)+" are present as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Activity Log details are not available", "FAILURE");

		return this;
	}

	public IncidentPage clickResolveIncident (){

		// Verify the tabs exists
		if(clickByXpath("ResolveIncident_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolve Incident button is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Resolve Incident button is not available", "WARNING");

		return this;
	}

	public IncidentPage hoverMasterIncident() {

		switchToMainFrame();
		mouseOverById("WIP_ProcessMasterIncident_Id");
		if(isExistById("WIP_MasterIncConfiguarionItem_Id"))
			Reporter_ServiceNow.reportStep("The User Information displayed as expected.", "SUCCESS");
		else 
			Reporter_ServiceNow.reportStep("The User Information is not on mouse Over", "FAILURE");

		return this;
	}

	public IncidentPage enterCausingCI(String causingCI) {

		if(enterByXpath("WIP_RICausingCI_Xpath", causingCI))
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" is entered in Causing CI field successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" could not be entered in Causing CI field", "FAILURE");

		return this;
	}

	public IncidentPage enterAndChooseCausingCIComponent(String causingCIComponent) {

		if(enterAndChoose("WIP_RICausingCIComponentfield_Xpath", causingCIComponent)){
			Wait(3000);
			Reporter_ServiceNow.reportStep("The value : "+causingCIComponent+" is entered in Causing CI Component field successfully", "SUCCESS");}
		else	
			Reporter_ServiceNow.reportStep("The value : "+causingCIComponent+" could not be entered in Causing CI Component field", "FAILURE");

		return this;
	}

	public IncidentPage enterAndChooseResolutionCode(String resolutionCode) {

		if(enterAndChoose("WIP_RIResolutionCodefield_Xpath", resolutionCode)){
			Wait(3000);
			Reporter_ServiceNow.reportStep("The value : "+resolutionCode+" is entered in Resolution Code field successfully", "SUCCESS");}
		else	
			Reporter_ServiceNow.reportStep("The value : "+resolutionCode+" could not be entered in Resolution Code field", "FAILURE");
		return this;
	}

	public IncidentPage enterResolutionNotes(String resolutionNotes) {

		if(enterAndChoose("WIP_RIResolutionNotesfield_Xpath", resolutionNotes))
			Reporter_ServiceNow.reportStep("The value : "+resolutionNotes+" is entered in Resolution Notes field successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The value : "+resolutionNotes+" could not be entered in Resolution Notes field", "FAILURE");
		return this;
	}

	public IncidentPage enterGTrackChange(String gTrackChange) {

		if(enterByXpath("CREATEINC_GTrackChange_Xpath",gTrackChange ))
			Reporter_ServiceNow.reportStep("The value : "+gTrackChange+" is entered and User able to enter alpha numeric data in GTrack Change field as expected.", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The value : "+gTrackChange+" could not be entered in GTrack Change field", "FAILURE");
		return this;
	}

	public IncidentPage verifySOXSystemDisabled() {

		if(verifyAttributeTextByXpath("CREATEINC_SOXSystem_Xpath", "disabled", "true"))
			Reporter_ServiceNow.reportStep("The SOX System is in read only", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The SOX System is editable", "WARNING");
		return this;
	}

	public IncidentPage verifyGxPSystemDisabled() {

		if(verifyAttributeTextByXpath("CREATEINC_GxPSystem_Xpath", "disabled", "true"))
			Reporter_ServiceNow.reportStep("The GxP System is in read only", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The GxP System is editable", "FAILURE");
		return this;
	}

	public IncidentPage enterAndChooseCausingCI(String causingCI) {

		if(enterAndChoose("WIP_RICausingCI_Xpath", causingCI))
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" is entered in Causing CI field successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" could not be entered in Causing CI field", "FAILURE");
		return this;
	}
	public IncidentPage verifyResolutionInformationCausingCIIsEnabled(){

		// you need to change the read only fields when the application changes
		String[] readOnlyFields = {"WIP_RICausingCI_Xpath"};

		String[] readOnlyFieldsDesc = {"Causing CI"};

		// Verify read only
		verifyEnabledFieldsByXpath(readOnlyFields,readOnlyFieldsDesc);
		return this;
	}

	public IncidentPage enterAndVerifyCausingCI(String causingCI) {

		if(enterAndChoose("WIP_RICausingCI_Xpath", causingCI))
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" is NOT available on the list Causing CI list", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("Causing CI field is disabled ", "FAILURE");
		return this;
	}

	public IncidentPage clickCausingCIComponentSpyGlass() {

		if(clickByXpath("WIP_RICausingCIComponentSpyglass_Xpath")){

		}else	
			Reporter_ServiceNow.reportStep("The CausingCI Component Spyglass could not be clicked", "FAILURE");

		switchToNewWindow();
		Reporter_ServiceNow.reportStep("The CausingCI Component Spyglass is clicked successfully", "SUCCESS");
		return this;
	}

	public IncidentPage switchToNewWindow(){
		//	resetImplicitWait(5);
		switchToSecondWindow();

		return this;
	}

	//	public IncidentPage verifyCIComponentBusinessServiceLookUpValues(){
	//
	//		Boolean bSuccess = true;
	//		String[] elements = {"Automated Job Failure",
	//				"Connectivity",				
	//				"Integration Issue",					 
	//				"Performance degradation",
	//		"Security breach"};
	//
	//		for (int i = 0; i < elements.length; i++) {
	//			try {
	//				if(!isExist(driver.findElement(By.linkText(elements[i])))){
	//					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
	//					bSuccess = false;
	//				}
	//			} catch (Exception e) {
	//				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
	//				bSuccess = false;
	//			}
	//		}
	//
	//		if(bSuccess)
	//			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");
	//
	//		switchToPrimary();
	//		return this;
	//
	//	}

	public IncidentPage verifyCIComponentApplicationLookUpValues(){

		Boolean bSuccess = true;
		switchToSecondWindow();
		switchToDefault();

		String[] elements = {"Account locked",	
				"Automated Job Failure","Connectivity","Data Issue","Error message"	,
				"Integration Issue","Login failure","Memory","Not responding/Frozen",	
				"Performance degradation","Security breach","Storage","UI issue","Virus"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}

	public IncidentPage verifyCIComponentStorageLookUpValues(){

		Boolean bSuccess = true;
		String[] elements = {"Automated Job Failure",
				"Connectivity",	
				"Data Issue",	
				"Disk",
				"Error message",	
				"Integration Issue",	
				"IP Address",	
				"Login failure",
				"Memory",	
				"Performance degradation",	
				"Power",
				"Security breach",
		"Storage"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}

	public IncidentPage clickOnHold(){

		if(clickByXpath("CREATEINC_OnHold_Xpath"))
			Reporter_ServiceNow.reportStep("Incident On Hold is successful.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Incident On Hold could not be performed.", "FAILURE");
		return this;

	}

	public IncidentPage clickHoldInformationTab(){

		if(clickByXpath("CREATEINC_HoldInfo_Xpath")){
			Wait(1000);		
			Reporter_ServiceNow.reportStep("On Hold information tab is clicked.", "SUCCESS");
		}else
			Reporter_ServiceNow.reportStep("On Hold information tab could not be clicked.", "FAILURE");
		return this;

	}

	public IncidentPage verifyHoldInfoMandatoryFields(){

		// you need to change the mandatory fields when the application changes
		String[] mandatoryFields = {"CREATEINC_OnHoldUntilLabel_Xpath",
				"CREATEINC_OnHoldReasonLabel_Xpath",
		"CREATEINC_OnHoldTypeLabel_Xpath"};

		String[] mandatoryFieldsDesc = {"Hold Type",
				"On Hold Until",
		"On Hold Reason"};

		verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);
		return this;

	}

	public IncidentPage selectHoldType(String type) {

		if(!selectByVisibleTextByXpath("CREATEINC_OnHoldType_Xpath", type))
			Reporter_ServiceNow.reportStep("The On hold type: "+type+" could not be selected", "FAILURE");
		return this;
	}

	public IncidentPage enterOnHoldUntil(String datetime) {

		if(!enterByXpath("CREATEINC_OnHoldUntil_Xpath", datetime))
			Reporter_ServiceNow.reportStep("The On hold Until date time: "+datetime+" could not be entered", "FAILURE");
		return this;
	}

	public IncidentPage clickOnHoldUntil() {

		clickByXpath("CREATEINC_OnHoldUntil_Xpath");
		return this;
	}


	public IncidentPage enterOnHoldReason(String reason) {

		if(!enterByXpath("CREATEINC_OnHoldReason_Xpath", reason))
			Reporter_ServiceNow.reportStep("The On hold reason: "+reason+" could not be entered", "FAILURE");
		return this;
	}

	public IncidentPage saveOnHoldInfo() {

		clickById("Save_Id");
		Wait(5000);
		if (getTextByXpath("CREATEINC_Pointer_Xpath").contains(getIncidentNumber()))
			Reporter_ServiceNow.reportStep("The On Hold Information process is successful", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The On Hold Information process failed. Check snapshot", "FAILURE");
		return this;
	}

	public IncidentPage enterAndSaveOnHoldInfo(String type, String datetime, String reason){

		selectHoldType(type);
		enterOnHoldUntil(datetime);
		enterOnHoldReason(reason);
		clickSaveButton();
		Reporter_ServiceNow.reportStep("The On Hold Information: Hold Type, Hold Until, Hold Reason are entered and saved successfully.","SUCCESS" );
		return this;

	}

	public IncidentPage clickOnResume(){

		if(clickByXpath("CREATEINC_HoldResume_Xpath")){
			clickSaveButton();
			Reporter_ServiceNow.reportStep("The Work in Progress Icon is clicked and saved successfully.", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("Incident Work in Progres could not be performed.", "FAILURE");
		return this;		
	}

	public IncidentPage updateIncident() {

		clickById("Update_Button");
		Wait(5000);

		if (getTextByXpath("CREATEINC_Pointer_Xpath").contains(getIncidentNumber()))
			Reporter_ServiceNow.reportStep("The Update Incident process is successful", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Update Incident process failed. Check snapshot", "FAILURE");
		return this;
	}


	public IncidentPage enterandChooseMasterIncident(String masterIncident) {

		if(!clickByXpath("CREATEINC_Process_Xpath"))
			Reporter_ServiceNow.reportStep("The Process Tab could not be entered", "FAILURE");

		if(enterAndChoose("CREATEINC_MasterIncident_Xpath", masterIncident))
			Reporter_ServiceNow.reportStep("The Process Tab is clicked and The value is entered in Master Incident successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value could not be entered in Master Incident ", "FAILURE");
		return this;
	}

	public IncidentPage isVipFlagExistsNearAffectedUser(String vipUser){

		if(isExistByXpath("INC_VIP_Flag_Xpath"))
			Reporter_ServiceNow.reportStep("The VIP User:"+vipUser+" is entered and VIP Flag is displayed next to Affected User as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The VIP Flag is not displayed next to Affected User, hence failure.", "FAILURE");
		return this;

	}

	public IncidentPage isVipFlagExistsNearReportingCustomer(){

		if(isExistByXpath("INC_VIP_Flag_Xpath"))
			Reporter_ServiceNow.reportStep("The VIP Flag is displayed next to Reporting Customer as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The VIP Flag is not displayed next to Reporting Customer as expected", "FAILURE");
		return this;

	}
	public IncidentPage verifyPriorityFieldInSparc(String priority){

		if(getDefaultValueByXpath("CREATEINC_IncidentDetailsPriority_Xpath").equals(priority))
			Reporter_ServiceNow.reportStep("The Priority field value is "+priority+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Priority field value could not be matched ", "FAILURE");
		return this; 

	}

	public IncidentPage clickSetPriority() {

		if (clickByXpath("INC_SetPriority_Xpath"))
			Reporter_ServiceNow.reportStep("The Set priority Icon is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Set priority Icon  could not be clicked", "FAILURE");
		return this;
	}

	public IncidentPage verifyPriorityField(String priority){

		if(getDefaultValueByXpath("CREATEINC_IncidentPriority_Xpath").equals(priority))
			Reporter_ServiceNow.reportStep("The Priority field value is "+priority+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Priority field value could not be matched ", "FAILURE");
		return this; 

	}
	public IncidentPage isFieldsAutomaticallyGenerated(String priority){

		if(!getDefaultValueByXpath("CREATEINC_IncidentPriority_Xpath").equals(priority))
			Reporter_ServiceNow.reportStep("The Priority field value could not be matched ", "FAILURE");

		String[] fields={"INCPage_Impact_Xpath","INCPage_Urgent_Xpath","INCPage_Priority_Xpath"};
		String[] desc={" Impact","Urgent","Priority"};

		if(verifyFieldsExistByXpath(fields, desc))
			Reporter_ServiceNow.reportStep("The Priority matched with "+priority+" and All the Field: Impact,Urgent and Priority are automatically filled as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("All the Field: Impact,Urgent and Priority are not filled", "FAILURE");
		return this;

	}

	public IncidentPage verifyCiMandatory(){

		// you need to change the mandatory fields when the application changes
		String[] mandatoryFields = {"CREATEINC_ConfigItemStar_Xpath"};
		String[] mandatoryFieldsDesc = {"Configuration Item"};

		verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);
		return this;

	}
	public IncidentPage isSOXSystemChecked() {

		if(driver.findElement(By.id("ni.incident.u_sox_system")).isSelected())
			Reporter_ServiceNow.reportStep("The SOX System is Checked", "SUCCESS");
		else 
			Reporter_ServiceNow.reportStep("The SOX System is not checked", "WARNING");
		return this;
	}

	public IncidentPage isGXpSystemChecked() {

		if(driver.findElement(By.id("ni.incident.u_gxp_system")).isSelected())
			Reporter_ServiceNow.reportStep("The GxP System is Checked", "SUCCESS");
		else 
			Reporter_ServiceNow.reportStep("The GxP System is not checked", "WARNING");
		return this;
	}
	// Aman 
	public IncidentPage clickconfigurationItemLookUp(){

		if(!clickById("CreateInc_ConfigurationItem_spyglass_ID"))

			Reporter_ServiceNow.reportStep("The Configuration Item - Look up could not be clicked.", "FAILURE");
		return this;
	}


	public IncidentPage selectConfigurationItemBasedonClass(String value) throws InterruptedException{

		Boolean bSuccess = true;
		System.out.println("Before Window switch");
		switchToSecondWindow();
		System.out.println("After Window switch");

		Wait(1000);

		if(!clickById("CreateInc_ConfigurationItem_spyglass_Filter_ID"))
			Reporter_ServiceNow.reportStep("Filter Icon could not be clcked","FAILURE");
		Wait(2000);

		if(!selectByVisibleTextByXpath("CIS_FirstFilterType1_Xpath","Class"))
			Reporter_ServiceNow.reportStep("The Filter type "+ "Class" + " could not be selected","FAILURE");

		try {
			new Actions(driver).sendKeys(Keys.TAB,Keys.TAB).build().perform(); // Move to the next element
			WebElement ele = driver.switchTo().activeElement();
			ele.sendKeys(value);
		}
		catch(Exception e){
			//Reporter_ServiceNow.reportStep("Filter value could not be clcked","FAILURE");	
		}

		Wait(1000);
		if(!clickByXpath("CreateInc_ConfigurationItem_spyglass_Filter_Run_xpath"))
			//Reporter_ServiceNow.reportStep("Flter condition to select the Configuration item of class "+ value + " is set successfully ","SUCCESS");
			//else
			Reporter_ServiceNow.reportStep("Run button to Run the Filter conditions could not be set","FAILURE");	

		Thread.sleep(3000);
		System.out.println("After confirm");

		if(!clickByXpath("CreateInc_ConfigurationItem_firstconitem_xpath"))
		{

			Reporter_ServiceNow.reportStep("Configuration Item with class of  " +  value + " could not be selcted","FAILURE");
		}
		Wait(2000);
		//switchToDefault();
		switchToPrimary();
		switchToFrame("Frame_Main");
		return this;

	}
	public IncidentPage verifyCIComponentServerLookUpValues(){

		Boolean bSuccess = true;
		switchToSecondWindow();
		switchToDefault();

		String[] elements = {"Account locked",	
				"Automated Job Failure","Connectivity","Data Issue","Disk","Error message"	,
				"IP Address","Login failure","Memory","Not responding/Frozen",	
				"Performance degradation","Power","Security breach","Storage","Virus"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep(" All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}

	public IncidentPage verifyCIComponentDatabaseLookUpValues(){

		Boolean bSuccess = true;
		switchToSecondWindow();
		switchToDefault();

		String[] elements = {"Account locked",	
				"Automated Job Failure","Connectivity","Data Issue","Disk","Error message","Integration Issue",
				"IP Address","Login failure","Memory",	
				"Performance degradation","Security breach","Storage"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep(" All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}

	public IncidentPage verifyCIComponentFieldLookUpValues(){

		Boolean bSuccess = true;
		switchToSecondWindow();
		switchToDefault();

		String[] elements = {"Account locked", "Connectivity", "Data Issue", "Disk", "Error message", "IP Address", "Login failure", "Memory", "Not responding/Frozen", "Performance degradation", "Peripherals", "Power", "Security breach", "Stolen/Lost", "Storage", "UI issue", "Virus"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}

	public IncidentPage verifyCIComponentFieldLookUpValuesForMissingCI(){

		Boolean bSuccess = true;
		switchToSecondWindow();
		switchToDefault();

		String[] elements = {"Account locked", "Automated Job Failure", "Connectivity", "Data Issue", "Disk", "Error message", "Integration Issue", "IP Address", "Login failure", "Memory", "Not responding/Frozen", "Outage", "Performance degradation", "Peripherals", "Power", "Security breach", "Stolen/Lost", "Storage", "UI issue", "Virus"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}

	//Divya 
	public IncidentPage verifyCIComponentLookUpValues_Power(){

		Boolean bSuccess = true;
		switchToSecondWindow();
		switchToDefault();

		String[] elements = {"Outage","Security breach"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}

	public IncidentPage verifyCIComponentLookUpValues_Storage(){

		Boolean bSuccess = true;
		switchToSecondWindow();
		switchToDefault();

		String[] elements = {"Automated Job Failure", "Connectivity","Data Issue","Error message", "Integration Issue","IP Address","Login failure","Memory","Performance degradation","Power","Security breach","Storage"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;
	}

	public IncidentPage verifyCIComponentLookUpValues_Networks(){

		Boolean bSuccess = true;
		switchToSecondWindow();
		switchToDefault();

		String[] elements = {"Automated Job Failure", "Connectivity","Data Issue","Error message", "Integration Issue","IP Address","Login failure","Memory","Performance degradation","Power","Security breach","Virus"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;
	}

	public IncidentPage verifyUploadErrorMessage(String fileName, String errorMessage){

		if(clickByXpath("Attachment_Xpath")){
			try {
				String[] folder = getAbsolutePath().substring(1).split("/");
				String folderName= folder[0];		
				for (int i = 1; i < folder.length-2; i++) {
					folderName = folderName+"/"+folder[i];
				}				
				driver.setFileDetector(new LocalFileDetector());
				
				enterById("ChooseFiles_Id", folderName+"/data/"+fileName+".xlsx");

				//clickByXpath("AttachFile_Xpath");

				if(driver.switchTo().alert().getText().contains(errorMessage)){
					alertAccept();		
					Wait(2000);
					Reporter_ServiceNow.reportStep("The error message :"+errorMessage+" appeared as expected", "SUCCESS");
				}
				else
					Reporter_ServiceNow.reportStep("The error message :"+errorMessage+" did not appear", "FAILURE");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return this;	

	}

	public IncidentPage verifyAssignmentGroupField(String assignGroup){

		if(verifyAttributeTextByXpath("CREATEINC_AsgGroup_Xpath","value", assignGroup))
			Reporter_ServiceNow.reportStep("The Assignment Group field value is "+assignGroup+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Assignment Group field value could not be matched with "+assignGroup+" ", "FAILURE");
		return this; 

	}

	public IncidentPage verifyAssignmentGroupFieldDoesNotExistBy(String assignGroup){

		if(!verifyAttributeTextByXpath("CREATEINC_AsgGroup_Xpath","value", assignGroup))
			Reporter_ServiceNow.reportStep("The Assignment field is not automatically filled with "+assignGroup+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Assignment field is automatically filled with "+assignGroup+" ", "FAILURE");
		return this; 

	}

	public IncidentPage verifyAddAttachmentAvailable() {

		if(isExistByXpath("Attachment_Xpath"))
			Reporter_ServiceNow.reportStep("The Add attachment button is available to Attach documents as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Add attachment button is missing", "FAILURE");
		return this;
	}

	public IncidentPage verifyAttachmentUploadedFromSPARCportal(){

		String[] tabNames ={"SPARCPORTAL_AttachmentVerification_Xpath"};
		String[] tabDesc ={"Uploaded Attachment"};	

		// Verify the tabs exists
		verifyFieldsExistByXpath(tabNames, tabDesc);
		return this;
	}

	public IncidentPage isStateOpen() {

		if (getDefaultValueByXpath("CREATEINC_IncidentState_Xpath").equals("Open"))
			Reporter_ServiceNow.reportStep("The State field is Open as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The State field is not Open", "FAILURE");
		return this;
	}

	public IncidentPage alertAcceptforResolve() {

		alertAccept();
		return this;
	}

	public IncidentPage isExistResolutionInformation(){

		if(isExistByXpath("WIP_ResolutionInformation_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolution Information tab is appeared as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Resolution Information tab could not be appear.", "FAILURE");
		return this;
	}

	public IncidentPage isExistResolutionCodefield(){

		if(isExistByXpath("WIP_RIResolutionCodefield_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolution Code field is appeared as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Resolution Code field could not be appear.", "FAILURE");
		return this;
	}

	public IncidentPage isAlertPresentforClickResolve() {
		clickByXpath("ResolveIncident_Xpath");
		String alertText=getTextAlert();
		alertAcceptforResolve();
		Wait(5000);
		if(alertText.equals(""))
			//   Reporter_ServiceNow.reportStep("The Resolve Incident button is clicked successfully", "SUCCESS");
			//  else
			Reporter_ServiceNow.reportStep("The Resolve Incident button is not available", "WARNING");

		//System.out.println(getTextAndAcceptAlert());
		if(alertText.contains("The following mandatory fields are not filled in"))
			Reporter_ServiceNow.reportStep("The Resolve Incident button is clicked and the Warning pop up is appeared as mandatory fields are not filled as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Warning pop up could not be appear.", "FAILURE");
		return this;
	}

	public ListPage clickCausingCISpyGlass() {


		if(!clickByXpath("WIP_RICausingCISpyglass_Xpath"))
			Reporter_ServiceNow.reportStep("The Causing CI Spyglass could not be clicked", "FAILURE");
		else{
			switchToNewWindow();
			Reporter_ServiceNow.reportStep("The Causing CI Spyglass is clicked successfully", "SUCCESS");}
		return new ListPage(driver);
	}

	public ListPage clickResolutioCodeSpyGlass() {

		switchToMainFrame();
		if(!clickByXpath("INC_ResolutionCodeSpy_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolution Code Spyglass could not be clicked", "FAILURE");
		else{
			Wait(3000);
			switchToNewWindow();
			Reporter_ServiceNow.reportStep("The Resolution Code Spyglass is clicked successfully", "SUCCESS");}
		return new ListPage(driver);
	}

	public ListPage clickResolutioCodeSpyGlass1() {

		if(!clickByXpath("INC_ResolutionCodeSpy_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolution Code Spyglass could not be clicked", "FAILURE");
		else{
			Wait(3000);
			switchToNewWindow();}

		return new ListPage(driver);
	}


	public IncidentPage MandatoryFieldsforResolutionTab(String causingCI, String causingCIComponent, String resolutionCode, String resolutionNotes) {

		enterCausingCI(causingCI);
		Wait(5000);
		enterAndChooseResolutionCode(resolutionCode);
		Wait(5000);
		enterAndChooseCausingCIComponent(causingCIComponent);
		Wait(5000);
		enterResolutionNotes(resolutionNotes);
		return this;

	}

	public IncidentPage verifyCausingCIMandatory() {

		String[] mandatoryFields = {"WIP_RICausingCIMandatory_Xpath"};

		String[] mandatoryFieldsDesc = {"Causing CI"};

		verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);

		return this;
	}

	public IncidentPage clickResolutionCodeSpyGlass() {

		if(clickByXpath("INC_ResolutionCodeSpy_Xpath")){

		}else	
			Reporter_ServiceNow.reportStep("The Resolution Code Spyglass could not be clicked", "WARNING");

		switchToNewWindow();
		Reporter_ServiceNow.reportStep("The Resolution Code Spyglass is clicked successfully", "SUCCESS");

		return this;
	}

	public IncidentPage verifyResolutionCodeSpyGlass(String[] ele){
		Boolean bSuccess = true;
		for (int i = 0; i < ele.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(ele[i])))){
					Reporter_ServiceNow.reportStep("The field in MetaData CI: "+ele[i]+" is not available.", "WARNING");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in MetaData CI: "+ele[i]+" is not available.", "WARNING");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(ele)+" do exists in Resolution Code Spyglass list", "SUCCESS");
		switchToPrimary();
		return this;

	}

	public IncidentPage clickOkAddCIDesc(){

		// click the first Incident Link
		if(clickByXpath("EVENTS_OkButton_Xpath"))
			Reporter_ServiceNow.reportStep("The Ok button is clicked Successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Ok button could not be clicked", "FAILURE");

		return new IncidentPage(driver);
	}

	public IncidentPage clickCancelAddCIDesc(){

		// click the first Incident Link
		if(clickByXpath("WIP_RICancel_Xpath"))
			Reporter_ServiceNow.reportStep("The Cancel button is clicked Successfully and The Dialog box closed as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Cancel button could not be clicked", "FAILURE");
		return new IncidentPage(driver);
	}

	public IncidentPage enterAddCIDescription(String data){

		// click the first Incident Link
		if(enterByXpath("INC_AddCIDescription_Xpath", data))
			Reporter_ServiceNow.reportStep("The text: "+data+" is entered in Add CI Description Successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The text: "+data+" could not be entered in  Add CI Description", "FAILURE");

		return new IncidentPage(driver);
	}

	public IncidentPage enterResolutionInformationMandatoryfields(String resCode, String CausingCIComponent , String resolutionNotes){

		// click the first Incident Link
		if(!enterAndChoose("WIP_RIResolutionCodefield_Xpath", resCode))			
			Reporter_ServiceNow.reportStep("The "+resCode+" could not be entered in Resolution code field", "FAILURE");

		if(!enterAndChoose("WIP_RICausingCIComponentfield_Xpath", CausingCIComponent))			
			Reporter_ServiceNow.reportStep("The "+CausingCIComponent+" could not be entered in Causing CI Component field", "FAILURE");

		if(enterByXpath("WIP_RIResolutionNotesfield_Xpath", resolutionNotes))	
			Reporter_ServiceNow.reportStep("All the Mandatory fields of Resolution Information tab are entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The "+resolutionNotes+" could not be entered in Resolution Notes field", "FAILURE");

		return new IncidentPage(driver);
	}

	public String getmissingCausingCINumber() {

		missingCausingNumber = getTextByXpath("INC_missingCausingNumber_Xpath");
		if(missingCausingNumber.equals(""))
			Reporter_ServiceNow.reportStep("The Missing Causing CI Number is blank for newly created task", "FAILURE");
		return missingCausingNumber;
	}

	public IncidentPage switchToAddCIDesc(){

		// Switch to the menu frame
		switchToFrame("Frame_AddCIDecsription");
		return this;
	}	

	public IncidentPage enterCMDBConfItem(String confItem){

		// click the first Incident Link
		if(enterAndChoose("CMDB_ConfigurationItem_Xpath", confItem))
			Reporter_ServiceNow.reportStep("The "+confItem+"  is entered in Configuration Item Successfully", "SUCCESS");			
		else
			Reporter_ServiceNow.reportStep("The "+confItem+" could not be  entered in Configuration Item", "FAILURE");
		return this;
	}

	// The page allows the user to close Task
	public IncidentPage closeTask() {

		if(clickById("closeTask_Id")){
			Wait(5000);			
			Reporter_ServiceNow.reportStep("The Close Button is clicked successfully", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The Close Button could not be clicked. Check snapshot", "FAILURE");
		return this;
	}

	public IncidentPage verifyConfigItemValue(String confItem) {

		System.out.println(getAttributeByXpath("WIP_RICausingCIfield_Xpath", "value"));
		if (getAttributeByXpath("WIP_RICausingCIfield_Xpath", "value").equalsIgnoreCase(confItem))
			Reporter_ServiceNow.reportStep("The value : "+confItem+" appeared in Configuration Item field as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value : "+confItem+" didnot appeared in Configuration Item field", "FAILURE");
		return this;
	}
	public IncidentPage verifyCIComponentLookUpValues_Network(){
		Boolean bSuccess = true;

		switchToSecondWindow();
		switchToDefault();

		String[] elements = {"Automated Job Failure", "Connectivity","Data Issue","Disk, Error message", "Integration Issue","IP Address","Login failure","Memory","Performance degradation","Power","Security breach","Virus"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;
	}
	//	public IncidentPage verifyCausingCIComponentFieldLookUpValuesForServer(){
	//		Boolean bSuccess = true;
	//
	//		switchToSecondWindow();
	//		switchToDefault();
	//
	//		String[] elements = {"Account locked", "Automated Job Failure", "Connectivity", "Data Issue", "Disk", "Error message", "IP Address", "Login failure", "Memory", "Not responding/Frozen", "Performance degradation", "Power", "Security breach", "Storage", "Virus"};
	//
	//		for (int i = 0; i < elements.length; i++) {
	//			try {
	//				if(!isExist(driver.findElement(By.linkText(elements[i])))){
	//					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
	//					bSuccess = false;
	//				}
	//			} catch (Exception e) {
	//				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
	//				bSuccess = false;
	//			}
	//		}
	//
	//		if(bSuccess)
	//			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in Causing CI component Look up list", "SUCCESS");
	//
	//		switchToPrimary();
	//		return this;
	//
	//	}
	public IncidentPage verifyCausingCIComponentFieldLookUpValuesForDatabase(){
		Boolean bSuccess = true;

		switchToSecondWindow();
		switchToDefault();

		String[] elements = {"Account locked", "Automated Job Failure", "Connectivity", "Data Issue", "Disk", "Error message", "Integration Issue", "IP Address", "Login failure", "Memory", "Performance degradation", "Security breach", "Storage"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}
	public IncidentPage verifyCausingCIComponentFieldLookUpValuesForNetwork(){
		Boolean bSuccess = true;

		switchToSecondWindow();
		switchToDefault();

		String[] elements = {"Automated job failure", "Connectivity", "Data issue", "Error message", "Integration issue", "IP Address", "Login failure", "Memory", "Performance degradation", "Power", "Security breach", "Virus"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}
	public IncidentPage enterAndSearch()
	{	
		Boolean bSuccess=true;
		switchToSecondWindow();
		switchToDefault();
		String[] elements = {"Account locked","Automated Job Failure","Connectivity", "Data Issue", "Disk", "Error message", "Integration Issue", "IP Address", "Login failure", "Memory", "Not responding/Frozen", "Outage", "Performance degradation", "Peripherals", "Power", "Security breach", "Stolen/Lost", "Storage", "UI issue", "Virus"};
		//"Automated Job Failure", "Connectivity", "Data Issue", "Disk", "Error message", "Integration Issue", "IP Address", "Login failure", "Memory", "Not responding/Frozen", "Outage", "Performance degradation", "Peripherals", "Power", "Security breach", "Stolen/Lost", "Storage", "UI issue", "Virus"};

		for (int i = 0; i < elements.length; i++) {
			//System.out.println(elements.length);
			if(enterByXpath("Incident_ValueSearch_Xpath",elements[i]))
				Wait(3000);
			PresEnter();
			Wait(3000);
			String value=getTextByXpath("CreateInc_ConfigurationItem_firstconitem_xpath");
			System.out.println("Value is "+ value);
			if(!value.equalsIgnoreCase(elements[i]))
			{
				Reporter_ServiceNow.reportStep("The element "+ elements[i] + "Does not exist in the list ","FAILURE");
				bSuccess=false;

			}
		}
		if(bSuccess)
		{
			enterByXpath("Incident_ValueSearch_Xpath","Account locked");
			Wait(3000);
			PresEnter();
			Wait(3000);			
			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");
		}
		switchToPrimary();
		return this;
	}

	public IncidentPage isReopenIncidentDisplayed(){

		Wait(3000);
		if(isExistByXpath("CREATEINC_ReopenIncident_Xpath"))
			Reporter_ServiceNow.reportStep("The Reopen Incident button is present as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Reopen Incident button could not be found", "FAILURE");
		return this; 

	}
	public IncidentPage verifyResolutionInformationEditableFields(){

		// you need to change the read only fields when the application changes
		String[] editableFields = { "WIP_RICausingCI_Xpath",
				"WIP_RICausingCIComponentfield_Xpath",
				"WIP_RIResolutionCodefield_Xpath",
		"WIP_RIResolutionNotesfield_Xpath"};

		String[] editableFieldsDesc = { "Causing CI",
				"Causing CI Component",
				"Resolution Code",
		"Resolution Notes"};

		// Verify read only
		verifyEnabledFieldsByXpath(editableFields, editableFieldsDesc);
		return this;

	}
	//	public IncidentPage isActivityLogEditable(){
	//
	//		// Verify the tabs exists
	//		String[] fields = {"CREATEINC_ActivityLog_Xpath"};
	//		String[] desc = {"Activity Log"};
	//
	//		// double click on that field
	//		//doubleCickByXpath(fields[0]);
	//
	//		// check the fields are disable
	//		if(driver.findElementByXPath(objRep.getProperty("CREATEINC_ActivityLog_Xpath")).getTagName().equals("input"))
	//			verifyDisabledFieldsByXpath(fields,desc);
	//		else
	//			Reporter_ServiceNow.reportStep("The fields in the Activity log could not be edited as expected.", "SUCCESS");
	//		return this;
	//	}
	public IncidentPage verifyAllMandatoryFieldsforResoInfor(){

		// you need to change the mandatory fields when the application changes
		String[] mandatoryFields = { "WIP_RICausingCIManfield_Xpath",
				"WIP_RICausingCIComponentManfield_Xpath",
				"WIP_RIResolutionCodeManfield_Xpath",
		"WIP_RINotesManfield_Xpath"};

		String[] mandatoryFieldsDesc = { "Causing CI",
				"Causing CI Component",
				"Resolution Code",
		"Resolution Notes"};

		verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);

		return this;
	}
	public IncidentPage isStateAssigned() {

		if (getDefaultValueByXpath("CREATEINC_IncidentState_Xpath").equals("Assigned"))
			Reporter_ServiceNow.reportStep("The State field is Assigned as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The State field is not Assigned", "FAILURE");
		return this;
	}


	public IncidentPage isStateOnHold() {

		if (getDefaultValueByXpath("CREATEINC_IncidentState_Xpath").equals("On Hold"))
			Reporter_ServiceNow.reportStep("The State field is On Hold as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The State field is not On Hold", "FAILURE");
		return this;
	}


	public IncidentPage isStateWIP() {

		if (getDefaultValueByXpath("CREATEINC_IncidentState_Xpath").equals("Work in Progress"))
			Reporter_ServiceNow.reportStep("The State field is Work in Progress as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The State field is not Work in Progress", "FAILURE");
		return this;
	}
	public IncidentPage isStateResolved() {

		scrollToElementByXpath("CREATEINC_IncidentState_Xpath");
		if (getDefaultValueByXpath("CREATEINC_IncidentState_Xpath").equals("Resolved"))
			Reporter_ServiceNow.reportStep("The State field is Resolved as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The State field is not Resolved", "FAILURE");
		return this;
	}
	public IncidentPage verifyResolutionInformationNonManFields(){

		// you need to change the read only fields when the application changes
		String[] fields = { "WIP_RICausingCI_Xpath",
				"WIP_RICausingCIComponentfield_Xpath",
				"WIP_RIResolutionCodefield_Xpath",
		"WIP_RIResolutionNotesfield_Xpath"};

		String[] desc = { "Causing CI",
				"Causing CI Component",
				"Resolution Code",
		"Resolution Notes"};

		// Verify read only
		verifyNonMandatoryFields(fields, desc);

		return this;

	}
	public IncidentPage enterReportingCustomer1(String repCust) {

		if(!enterAndChoose("CREATEINC_RepCust_Xpath", repCust))
			Reporter_ServiceNow.reportStep("The reporting customer: "+repCust+" not found / could not be entered", "FAILURE");
		return this;
	}		
	public IncidentPage verifystatestatus(String statestatus){

		Wait(3000);
		if(getDefaultValueByXpath("CREATEINC_IncidentState_Xpath").contains(statestatus))
			Reporter_ServiceNow.reportStep("The state field is "+statestatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The state  "+statestatus+" could not be matched", "FAILURE");
		return this; 

	}

	public IncidentPage isReopenIncidentDisplayedforUnassignedgroup(String user){

		Wait(3000);
		if(IsElementNotPresentByXpath("CREATEINC_ReopenIncident_Xpath"))
			Reporter_ServiceNow.reportStep("The Reopen Incident button could not be found for the user "+user+" from Unassigned group as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Reopen Incident button is present", "FAILURE");
		return this; 

	}

	public IncidentsListPage clickConfigurationItemSpyGlass() {

		if(!clickById("CreateInc_ConfigurationItem_spyglass_ID"))
			Reporter_ServiceNow.reportStep("The Configuration Item Spyglass could not be clicked", "FAILURE");
		else{
			switchToSecondWindow();
			Reporter_ServiceNow.reportStep("The Configuration Item Spyglass is clicked successfully", "SUCCESS");}
		return new IncidentsListPage(driver);
	}



	public IncidentsListPage clickCIComponetSpyGlass() {

		if(!clickById("CREATEINC_CIComponent_Lookup_Id"))
			Reporter_ServiceNow.reportStep("The CI Component Spyglass could not be clicked", "FAILURE");
		else{
			//			switchToDefault();
			switchToSecondWindow();
			Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked successfully", "SUCCESS");}
		return new IncidentsListPage(driver);
	}

	public IncidentsListPage clickBusinessServiceSpyGlass(){

		if(!clickById("CreateInc_BusinessService_spyglass_ID"))

			Reporter_ServiceNow.reportStep("The Business Service - Look up could not be clicked.", "FAILURE");

		Wait(2000);
		switchToNewWindow();
		return new IncidentsListPage(driver);
	}

	public IncidentPage verifyBusinessService() {

		if((getCountOfElementsByXpath("CIS_SearchReferenceData_Xpath"))>1){

			Reporter_ServiceNow.reportStep("The Multiple business services is available as expected", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The Multiple business services could not be found", "FAILURE");
		return this;
	}

	public IncidentPage enterAndVerifyNonAvailableofCausingCI() {

		if(!getAttributeByXpath("WIP_RICausingCI_Xpath", "value").contains(""))
			Reporter_ServiceNow.reportStep("The value is NOT available on the list Causing CI list", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("Causing CI field is disabled ", "FAILURE");
		return this;
	}
	//	public IncidentPage uploadFile(String fileName) {
	//
	//		boolean bFail = true;
	//		if(clickByXpath("Attachment_Xpath")){
	//			try {
	//				String[] folder = getAbsolutePath().substring(1).split("/");
	//				String folderName = folder[0];
	//				for (int i = 1; i < folder.length - 2; i++) {
	//					folderName = folderName + "/" + folder[i];
	//				}
	//				
	//				enterById("ChooseFiles_Id", folderName + "/data/" + fileName + ".xlsx");
	//
	//
	//				clickByXpath("AttachFile_Xpath");
	//				Wait(10000);
	//
	//				isExistByXpath("IsAttachmentUploaded");
	//				clickByXpath("CREATEINC_CloseUpload_Xpath");
	//
	//				bFail = false;
	//				scrollToElementByXpath("CREATEINC_ManageAttachments_Xpath");
	//				Wait(1000);
	//				Reporter_ServiceNow.reportStep("The Attachment is Uploaded successfully ", "SUCCESS");
	//				clickById("Save_Id");
	//
	//			} catch (Exception e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//			}
	//		}   
	//
	//		if(bFail)
	//			Reporter_ServiceNow.reportStep("The Attachment could not be Uploaded  ", "FAILURE");
	//		return this;
	//	}

	public IncidentPage uploadFile(String fileName) {

		boolean bFail = true;
		if(clickByXpath("Attachment_Xpath")){
			driver.setFileDetector(new LocalFileDetector());
			try {
				String[] folder = getAbsolutePath().substring(1).split("/");
				String folderName = folder[0];
				for (int i = 1; i < folder.length - 2; i++) {
					folderName = folderName + "/" + folder[i];
				}
				//Runtime.getRuntime().exec("cmd /c start bitsadmin.exe /transfer upload http://the-internet.herokuapp.com/download/some-file.txt C:\\users\\administrator\\desktop\\fileupload.txt");

				//Runtime.getRuntime().exec("bitsadmin.exe /transfer \"JobName\" http://the-internet.herokuapp.com/download/some-file.txt d:\fileupload.txt");

				WebDriverWait wait = new WebDriverWait(driver, 60);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("attachFile")));

				//				enterById("ChooseFiles_Id", ("./data/"+fileName+".xlsx"));
				enterById("ChooseFiles_Id", folderName+"/data/"+fileName+".xlsx");
				//enterById("ChooseFiles_Id", "c:/users/administrator/desktop/fileupload.txt");

				Wait(10000);

				clickByXpath("AttachFile_Xpath");
				Wait(10000);

				isExistByXpath("IsAttachmentUploaded");
				clickByXpath("CREATEINC_CloseUpload_Xpath");

				bFail = false;
				scrollToElementByXpath("CREATEINC_ManageAttachments_Xpath");
				Wait(1000);
				Reporter_ServiceNow.reportStep("The Attachment is Uploaded successfully ", "SUCCESS");
				clickById("Save_Id");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}   

		if(bFail)
			Reporter_ServiceNow.reportStep("The Attachment could not be Uploaded  ", "FAILURE");
		return this;
	}

	public IncidentPage clickResolveIncidentWithAlertAccept (){

		// Verify the tabs exists
		if(clickByXpath("ResolveIncident_Xpath"))
		{
			alertAcceptforResolve();
			Wait(5000);
			scrollToElementByXpath("WIP_ResolutionInformation_Xpath");
			Reporter_ServiceNow.reportStep("The Resolve Incident button is clicked successfully", "SUCCESS");
		}
		else
			Reporter_ServiceNow.reportStep("The Resolve Incident button is not available", "WARNING");

		return this;
	}
	public IncidentPage createIncidentAndOpen(String configItem, String repCust, String asgGroup, String desc, String incNumber) {

		enterConfigurationItem(configItem).
		enterReportingCustomer1(repCust).
		enterAssignmentGroup(asgGroup).
		enterShortDescription(desc);

		if(!clickByXpath("Submit_Xpath"))
			Reporter_ServiceNow.reportStep("The Create Incident process failed. Check snapshot", "FAILURE");

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath", incNumber))
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");

		if(isExistByXpath("NoRecords_xpath")){
			status="Insuffient Data";
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "INSUFFICIENT DATA");}

		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Incident: "+incNumber+" is created and opened successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Incident: "+incNumber+" is not found or not clicked.", "FAILURE");

		return this;

	}

	public IncidentPage enterAssignedToSave(String asgTo) {

		if(!enterAndChoose("CREATEINC_AssignedTo_Xpath", asgTo))
			Reporter_ServiceNow.reportStep("The Assigned To: "+asgTo+" not found / could not be entered", "FAILURE");
		if(clickById("Save_Id"))
			Reporter_ServiceNow.reportStep("The Value: "+asgTo+" is entered in Assigned To field and The Save button is clicked successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Save button could not be clicked or not found", "FAILURE");

		return this;
	}

	public IncidentPage clickWIPAndVerify() {

		if(!clickByXpath("CREATEINC_WipButton_Xpath"))
			Reporter_ServiceNow.reportStep("The Work In Progress process button is not clicked or not found.", "FAILURE");

		if (getDefaultValueByXpath("CREATEINC_IncidentState_Xpath").equals("Work in Progress"))
			Reporter_ServiceNow.reportStep("The Work In Progress button is clicked and state changed to Work in Progress as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The State is not matvhed with 'Work In Progress process', Check snapshot", "FAILURE");
		return this;
	}	

	public IncidentPage isExistAndClickResolutionInformation(){

		if(!isExistByXpath("WIP_ResolutionInformation_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolution Information tab is not found, check snapshot.", "FAILURE");

		if(clickByXpath("WIP_ResolutionInformation_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolution Information tab is appeared and clicked as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Resolution Information tab could not be clicked  ", "FAILURE");
		return this;
	}
	public IncidentPage clickAndisActivityLogEditable(){

		scrollToElementByXpath("CREATEINC_ActivityLog_Xpath");

		if(!clickByXpath("CREATEINC_ActivityLog_Xpath"))
			Reporter_ServiceNow.reportStep("The Activity Log could not be clicked  ", "FAILURE");

		String[] fields = {"CREATEINC_ActivityLog_Xpath"};
		String[] desc = {"Activity Log"};

		if(driver.findElementByXPath(objRep.getProperty("CREATEINC_ActivityLog_Xpath")).getTagName().equals("input"))
			verifyDisabledFieldsByXpath(fields,desc);
		else
			Reporter_ServiceNow.reportStep("The fields in the Activity log could not be edited as expected.", "SUCCESS");
		return this;
	}

	public IncidentPage enterAllFields(String causingCI, String causingCIComponent, String resolutionCode, String resolutionNotes) {

		if(!enterAndChoose("WIP_RICausingCI_Xpath", causingCI))
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" could not be entered in Causing CI field", "FAILURE");

		if(!enterAndChoose("WIP_RIResolutionCodefield_Xpath", resolutionCode))
			Reporter_ServiceNow.reportStep("The value : "+resolutionCode+" could not be entered in Resolution Code field", "FAILURE");

		if(!enterAndChoose("WIP_RICausingCIComponentfield_Xpath", causingCIComponent))
			Reporter_ServiceNow.reportStep("The value : "+causingCIComponent+" could not be entered in Causing CI Component field", "FAILURE");

		if(!enterByXpath("WIP_RIResolutionNotesfield_Xpath", resolutionNotes))
			Reporter_ServiceNow.reportStep("The value : "+resolutionNotes+" could not be entered in Resolution Notes field", "FAILURE");

		if(clickById("Save_Id")){
			Wait(2000);
			scrollToElementByXpath("WIP_ResolutionInformation_Xpath");
			Reporter_ServiceNow.reportStep("The Values CausingCI: "+causingCI+", CausingCI Component: "+resolutionCode+", Resolution Code: "+resolutionCode+", Resolution Notes: "+resolutionNotes+" "
					+ "are entered in respective fields and saved successfully.", "SUCCESS");}
		else	
			Reporter_ServiceNow.reportStep("The Save button could not be clicked or not found", "FAILURE");		


		return this;

	}

	public IncidentPage clickSaveButton() {

		if(!clickById("Save_Id"))
			Reporter_ServiceNow.reportStep("The Save button could not be clicked or not found", "FAILURE");
		return this;
	}
	public IncidentPage createIncidentWithWorkInProcessAndOpenIncident(String configItem, String repCust, String asgGroup, String desc, String incNumber, String aUser) {

		enterConfigurationItem(configItem).
		enterReportingCustomer1(repCust).
		enterAssignmentGroup(asgGroup).
		enterShortDescription(desc).
		enterAssigned(aUser);

		if(!clickByXpath("Submit_Xpath"))
			Reporter_ServiceNow.reportStep("The Create Incident process failed. Check snapshot", "FAILURE");

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath", incNumber))
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");

		if(isExistByXpath("NoRecords_xpath")){
			status="Insuffient Data";
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");}

		if(!clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Incident: "+incNumber+" is not found or not clicked.", "FAILURE");

		if(!clickByXpath("CREATEINC_WipButton_Xpath"))
			Reporter_ServiceNow.reportStep("The Incident: "+incNumber+" is not found or not clicked.", "FAILURE");

		if (getDefaultValueByXpath("CREATEINC_IncidentState_Xpath").equals("Work in Progress"))
			Reporter_ServiceNow.reportStep("The Work in Progress Incident: "+incNumber+" Opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Incident: "+incNumber+" state is not matched with Work in Progress, hence failure.", "FAILURE");

		return this;

	}

	public IncidentPage VerifyResolutionInformationAndCodefieldExists(){

		// Verify the tabs exists
		if(clickByXpath("ResolveIncident_Xpath"))
		{
			alertAcceptforResolve();
			Wait(5000);

		}
		else
			Reporter_ServiceNow.reportStep("The Resolve Incident button is not available", "WARNING");

		if(!isExistByXpath("WIP_ResolutionInformation_Xpath"))   
			Reporter_ServiceNow.reportStep("The Resolution Information tab could not be appear.", "FAILURE");

		if(isExistByXpath("WIP_RIResolutionCodefield_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolve Incident button is clicked and Resolution Code field appeared in the Resolution Information tab as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Resolution Code field could not be appear.", "FAILURE");
		return this;
	}

	public ListPage enterCausingCIandClickResolutionTabCodeSpyglass(String causingCI) {

		if(!clickByXpath("WIP_ResolutionInformation_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolution Information tab is not clicked or not found.", "FAILURE");

		if(!enterAndChoose("WIP_RICausingCI_Xpath", causingCI))   
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" could not be entered in Causing CI field", "FAILURE");

		if(!clickByXpath("INC_ResolutionCodeSpy_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolution Code Spyglass could not be clicked", "FAILURE");
		else{
			Wait(3000);
			switchToNewWindow();
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" is entered in Causing CI field and the Resolution Code Spyglass is clicked successfully", "SUCCESS");}
		return new ListPage(driver);
	}

	public ListPage enterCausingCIandClickCausingCIComponentSpyGlass(String causingCI) {

		if(!clickByXpath("WIP_ResolutionInformation_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolution Information tab is not clicked or not found.", "FAILURE");

		if(!enterAndChoose("WIP_RICausingCI_Xpath", causingCI))   
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" could not be entered in Causing CI field", "FAILURE");
		Wait(5000);

		if(!clickByXpath("WIP_RICausingCIComponentSpyglass_Xpath"))
			Reporter_ServiceNow.reportStep("The Causing CI Component Spyglass could not be clicked", "FAILURE");
		else{
			Wait(3000);
			switchToNewWindow();
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" is entered in Causing CI field"
					+ " and, the Causing CI Component Spyglass is clicked successfully", "SUCCESS");}
		return new ListPage(driver);
	}
	public IncidentPage createIncidentWithAssignedToAndOpenIncident(String configItem, String repCust, String asgGroup, String desc, String incNumber, String aUser) {

		enterConfigurationItem(configItem).
		enterReportingCustomer1(repCust).
		enterAssignmentGroup(asgGroup).
		enterShortDescription(desc).
		enterAssigned(aUser);

		if(!clickByXpath("Submit_Xpath"))
			Reporter_ServiceNow.reportStep("The Create Incident process failed. Check snapshot", "FAILURE");

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath", incNumber))
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");

		if(isExistByXpath("NoRecords_xpath")){
			status="Insuffient Data";
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");}

		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Incident: "+incNumber+" is created and opened successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Incident: "+incNumber+" is not found or not clicked.", "FAILURE");

		return this;

	}

	public IncidentPage enterAssigned(String asgTo) {

		if(!enterAndChoose("CREATEINC_AssignedTo_Xpath", asgTo))
			Reporter_ServiceNow.reportStep("The Assigned To: "+asgTo+" not found / could not be entered", "FAILURE");

		return this;
	}


	public ListPage enterCausingCIandClickResolutionCodeSpyglass(String causingCI) {

		if(!enterAndChoose("WIP_RICausingCI_Xpath", causingCI))			
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" could not be entered in Causing CI field", "FAILURE");

		if(!clickByXpath("INC_ResolutionCodeSpy_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolution Code Spyglass could not be clicked", "FAILURE");
		else{
			Wait(3000);
			switchToNewWindow();
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" is entered in Causing CI field and the Resolution Code Spyglass is clicked successfully", "SUCCESS");}
		return new ListPage(driver);
	}
	public ListPage createIncident(String configItem, String repCust, String asgGroup, String desc, String incNumber) {

		enterConfigurationItem(configItem).
		enterReportingCustomer1(repCust).
		enterAssignmentGroup(asgGroup).
		enterShortDescription(desc);

		if(!clickByXpath("Submit_Xpath"))
			Reporter_ServiceNow.reportStep("The Create Incident process failed. Check snapshot", "FAILURE");

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath", incNumber))
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");

		if(isExistByXpath("NoRecords_xpath")){
			status="Insuffient Data";
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");}

		return new ListPage(driver);

	}

	public IncidentPage enterAffectedUserForFailure(String aUser) {

		if(!enterAndChoose("CREATEINC_AffectedUser_Xpath", aUser))
			Reporter_ServiceNow.reportStep("The Affected User: "+aUser+" not found / could not be entered", "FAILURE");

		return this;
	}
	public IncidentPage verifyWorkNotesAvailable(){

		scrollToElementById("CREATEINC_WorkNotes_Id");

		if(!clickByXpath("CREATEINC_Notes_Xpath"))
			Reporter_ServiceNow.reportStep("The Notes Tab is not clicked or not clicked.", "FAILURE");

		if(!IsElementNotPresentByXpath("Inc_EnterWorkNotes_Xpath"))
			Reporter_ServiceNow.reportStep("The Work Notes is not present", "FAILURE");

		String sVal = getBackgroundColorById("CREATEINC_WorkNotes_Id");		

		ColorUtils color = new ColorUtils();

		String colorval = color.getColorName(sVal);

		if(colorval.equals("Green"))
			Reporter_ServiceNow.reportStep("The WorkNotes is availabe and Color of the Work notes is "+colorval+" as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Color of the Work notes is "+colorval+" which is incorrect, hence failed ", "WARNING");

		return this;
	}
	public IncidentPage enterWorkNotesAndSave(String workNotes) {

		if(!clickByXpath("CREATEINC_Notes_Xpath"))
			Reporter_ServiceNow.reportStep("The Notes Tab could not be clicked  ", "FAILURE");
		// duplicating the code as webdriver haing issue with this field (not entering first time)
		enterByXpath("CREATEINC_WorkNotes_Xpath",workNotes);
		if(!enterByXpath("CREATEINC_WorkNotes_Xpath",workNotes ))
			Reporter_ServiceNow.reportStep("The value : "+workNotes+" could not be entered in Work Notes field", "FAILURE");
		if(clickById("Save_Id"))
			Reporter_ServiceNow.reportStep("The value : "+workNotes+" is entered in Work Notes field and saved successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Save Button is not clicked or not found.", "FAILURE");

		return this;
	}
	public IncidentPage verifyCusscmmsAvailable(){

		scrollToElementById("CREATEINC_WorkNotes_Id");

		if(!clickByXpath("CREATEINC_Notes_Xpath"))
			Reporter_ServiceNow.reportStep("The Notes Tab is not clicked or not found, hence failure.", "FAILURE");

		if(!IsElementNotPresentByXpath("CREATEINC_CustomerComms_Id"))
			Reporter_ServiceNow.reportStep("The Customer Comms is not present", "FAILURE");

		String sVal = getBackgroundColorById("CREATEINC_CustomerComms_Id");		

		ColorUtils color = new ColorUtils();

		String colorval = color.getColorName(sVal);

		if(colorval.equals("Linen"))
			Reporter_ServiceNow.reportStep("The Customer Comms is availabe and Color of the Customer Comms is Red as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Color of the Work notes is Red did not match ", "FAILURE");

		return this;
	}	

	public IncidentPage enterCustomerCommsAndSave(String CustomerComms) {


		if(!clickByXpath("CREATEINC_Notes_Xpath"))
			Reporter_ServiceNow.reportStep("The Notes Tab is not clicked or not found, hence failure.", "FAILURE");

		enterByXpath("CREATEINC_CustomerComms_Xpath",CustomerComms);
		if(!enterByXpath("CREATEINC_CustomerComms_Xpath",CustomerComms ))
			Reporter_ServiceNow.reportStep("The value : "+CustomerComms+" could not be entered in Customer Comms field", "FAILURE");
		if(clickById("Save_Id"))
			Reporter_ServiceNow.reportStep("The value : "+CustomerComms+" is entered in Customer Comms field and saved successfully.", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Save button could not be clicked or not found", "FAILURE");

		return this;
	}


	public IncidentPage enterAllFieldsWithResolveButton(String causingCI, String causingCIComponent, String resolutionCode, String resolutionNotes) {


		if(!clickByXpath("WIP_ResolutionInformation_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolution Information tab could not be clicked  ", "FAILURE");

		if(!enterAndChoose("WIP_RICausingCI_Xpath", causingCI))
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" could not be entered in Causing CI field", "FAILURE");

		if(!enterAndChoose("WIP_RIResolutionCodefield_Xpath", resolutionCode))
			Reporter_ServiceNow.reportStep("The value : "+resolutionCode+" could not be entered in Resolution Code field", "FAILURE");

		if(!enterAndChoose("WIP_RICausingCIComponentfield_Xpath", causingCIComponent))
			Reporter_ServiceNow.reportStep("The value : "+causingCIComponent+" could not be entered in Causing CI Component field", "FAILURE");

		if(!enterByXpath("WIP_RIResolutionNotesfield_Xpath", resolutionNotes))
			Reporter_ServiceNow.reportStep("The value : "+resolutionNotes+" could not be entered in Resolution Notes field", "FAILURE");

		if(clickByXpath("ResolveIncident_Xpath")){
			Wait(2000);
			scrollToElementByXpath("WIP_RICausingCI_Xpath");
			Reporter_ServiceNow.reportStep("The Values CausingCI: "+causingCI+", CausingCI Component: "+resolutionCode+", Resolution Code: "+resolutionCode+", Resolution Notes: "+resolutionNotes+" "
					+ "are entered in respective fields and Resolve Button clicked successfully.", "SUCCESS");}
		else	
			Reporter_ServiceNow.reportStep("The Save button could not be clicked or not found", "FAILURE");		


		return this;

	}

	public IncidentPage VerifySOXIsReadonOnly(String causingCI){

		if(!clickByXpath("WIP_ResolutionInformation_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolution Information tab could not be clicked  ", "FAILURE");
		if(!enterAndChoose("WIP_RICausingCI_Xpath", causingCI))
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" could not be entered in Causing CI field", "FAILURE");
		if(!clickById("Save_Id"))
			Reporter_ServiceNow.reportStep("The Save button could not be clicked or not found", "FAILURE");

		if(!clickByXpath("CREATEINC_Process_Xpath"))
			Reporter_ServiceNow.reportStep("The Process Tab could not be entered", "FAILURE");

		if(verifyAttributeTextByXpath("CREATEINC_SOXSystem_Xpath", "disabled", "true"))
			Reporter_ServiceNow.reportStep("The Value: "+causingCI+" is entered in Causing CI field and Saved successfully. The SOX System is read only under Process Tab as expected.", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The SOX System is editable", "FAILURE");
		return this;
	}
	public IncidentPage VerifyGXPIsReadonOnly(String causingCI){

		if(!clickByXpath("WIP_ResolutionInformation_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolution Information tab could not be clicked  ", "FAILURE");
		if(!enterAndChoose("WIP_RICausingCI_Xpath", causingCI))
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" could not be entered in Causing CI field", "FAILURE");
		if(!clickById("Save_Id"))
			Reporter_ServiceNow.reportStep("The Save button could not be clicked or not found", "FAILURE");

		if(!clickByXpath("CREATEINC_Process_Xpath"))
			Reporter_ServiceNow.reportStep("The Process Tab could not be entered", "FAILURE");

		if(verifyAttributeTextByXpath("CREATEINC_GxPSystem_Xpath", "disabled", "true"))
			Reporter_ServiceNow.reportStep("The Value: "+causingCI+" is entered in Causing CI field and Saved successfully. The GXP System is read only under Process Tab as expected.", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The SOX System is editable", "FAILURE");
		return this;
	}

	public IncidentPage enterAddCIDescriptionAndOk(String data){

		// click the first Incident Link
		if(!enterByXpath("INC_AddCIDescription_Xpath", data))
			Reporter_ServiceNow.reportStep("The text: "+data+" could not be entered in  Add CI Description", "FAILURE");

		if(clickByXpath("EVENTS_OkButton_Xpath"))
			Reporter_ServiceNow.reportStep("The text: "+data+" is entered in Add CI Description and The Ok button is clicked Successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Ok button could not be clicked", "FAILURE");

		return new IncidentPage(driver);
	}
	public IncidentPage submitIncidentAndOpen(String incNumber) {

		if(!clickByXpath("Submit_Xpath"))
			Reporter_ServiceNow.reportStep("The Submit Button is not clicke or not found. Check snapshot", "FAILURE");

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",incNumber))
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");

		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Incident: "+incNumber+" is created and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First Incident could not be clicked", "FAILURE");		

		return this;
	}
	public IncidentPage clickActivityLogForFailure() {

		scrollToElementByXpath("CREATEINC_ActivityLog_Xpath");
		if(!clickByXpath("CREATEINC_ActivityLog_Xpath"))
			Reporter_ServiceNow.reportStep("The Activity Log could not be clicked  ", "FAILURE");

		return this;
	}

	public IncidentPage verifyComments(String workNotes, String CustomerComms) {

		if(!clickByXpath("CREATEINC_Notes_Xpath"))
			Reporter_ServiceNow.reportStep("The Notes Tab is not clicked or not clicked.", "FAILURE");

		enterByXpath("CREATEINC_WorkNotes_Xpath",workNotes);
		if(!enterByXpath("CREATEINC_WorkNotes_Xpath",workNotes ))
			Reporter_ServiceNow.reportStep("The value : "+workNotes+" could not be entered in Work Notes field", "FAILURE");

		enterByXpath("CREATEINC_CustomerComms_Xpath",CustomerComms);
		if(!enterByXpath("CREATEINC_CustomerComms_Xpath",CustomerComms ))
			Reporter_ServiceNow.reportStep("The value : "+CustomerComms+" could not be entered in Customer Comms field", "FAILURE");

		if(clickById("Save_Id"))
			Reporter_ServiceNow.reportStep("The WorkNotes: "+workNotes+", Customer Comms: "+CustomerComms+" are entered and saved succesfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Save button could not be clicked or not found", "FAILURE");

		if(!clickByXpath("CREATEINC_ActivityLog_Xpath"))
			Reporter_ServiceNow.reportStep("The Activity log is not clicked or not found.", "FAILURE");

		scrollToElementByXpath("WIP_ALHeader_Xpath");

		if((getTextByXpath("WIP_ALHeader_Xpath").contains(workNotes))||(getTextByXpath("WIP_ALHeader_Xpath").contains(CustomerComms)))
			Reporter_ServiceNow.reportStep("The WorkNotes: "+workNotes+" and Customer Comms: "+CustomerComms+" are displayed under activity log as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Activity Log details are not available", "FAILURE");

		return this;	
	}
	public IncidentPage verifyPriorityFieldInSparcAndSave(String priority){

		if(!getDefaultValueByXpath("CREATEINC_IncidentDetailsPriority_Xpath").equals(priority))
			Reporter_ServiceNow.reportStep("The Priority field value could not be matched ", "FAILURE");

		if(clickById("Save_Id"))
			Reporter_ServiceNow.reportStep("The Priority field value is "+priority+" as expected and The Save button is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Save button could not be clicked or not found", "FAILURE");

		return this; 

	}
	public IncidentPage isNotSOXSystemChecked() {

		//		if(!(driver.findElement(By.id("ni.incident.u_sox_system")).isSelected()))
		if((getAttributeByXpath("INC_SOXGXP_Xpath", "value")).equals("true"))
			Reporter_ServiceNow.reportStep("The SOX System is Checked", "FAILURE");
		else 
			Reporter_ServiceNow.reportStep("The SOX System is not checked as expected.", "SUCCESS");
		return this;
	}

	public IncidentPage isNotGXpSystemChecked() {

		//		if(!(driver.findElement(By.id("ni.incident.u_gxp_system")).isSelected()))
		if((getAttributeByXpath("INC_PROGXP_Xpath", "value")).equals("true"))
			Reporter_ServiceNow.reportStep("The GxP System is Checked", "FAILURE");
		else 
			Reporter_ServiceNow.reportStep("The GxP System is not checked as expected.", "SUCCESS");
		return this;
	}	
	public IncidentsListPage enterConfigurationItemAndClickCIComponentSpyglass(String configItem) {

		if(!enterAndChoose("CREATEINC_ConfigItem_Xpath", configItem))			
			Reporter_ServiceNow.reportStep("The configuration item: "+configItem+" not found / could not be entered.", "FAILURE");


		if(!clickById("CREATEINC_CIComponent_Lookup_Id"))
			Reporter_ServiceNow.reportStep("The configuration item: "+configItem+" entered but CI Component Spyglass could not be clicked", "FAILURE");
		else{
			//			switchToDefault();
			switchToSecondWindow();
			Reporter_ServiceNow.reportStep("The configuration item: "+configItem+" found and entered and CI Component Spyglass is clicked successfully", "SUCCESS");}
		return new IncidentsListPage(driver);
	}

	public ListPage clickCIComponetSpyGlassAndverifyCIComponetSpyGlass(String[] elements){


		if(!clickById("CREATEINC_CIComponent_Lookup_Id"))
			Reporter_ServiceNow.reportStep("The CI Component Spyglass could not be clicked", "FAILURE");
		else{
			switchToSecondWindow();
		}

		Boolean bSuccess = true;

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i]))))/*{
		     Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and the field in CI component: "+elements[i]+" is not available in the CI configuration Look up list.", "FAILURE");
		     bSuccess = false;
		    }*/

					if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Value"))
						Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

				if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",elements[i]))
					Reporter_ServiceNow.reportStep("The Value: "+elements[i]+" is not entered in Value field.", "FAILURE");
				Wait(3000);
				if(!isExist(driver.findElement(By.linkText(elements[i]))))
					Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and the field in CI component: "+elements[i]+" is not available in the CI configuration Look up list.", "FAILURE");

			} 
			catch (Exception e) {
				Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and the field in CI component: "+elements[i]+" is not available in the CI configuration Look up list.", "FAILURE");
				bSuccess = false;
			}
		}

		deleteFilters();
		Wait(3000);
		if(bSuccess)
			Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return new ListPage(driver);
	}
	public IncidentPage clickCIComponentLookUpandVerifyCIComponentServerLookUpValues(){

		if(!clickById("CREATEINC_CIComponent_Lookup_Id"))
			Reporter_ServiceNow.reportStep("The CI Component - Look up could not be clicked.", "FAILURE");

		Boolean bSuccess = true;
		switchToSecondWindow();
		switchToDefault();

		String[] elements = {"Account locked",	
				"Automated Job Failure","Connectivity","Data Issue","Disk","Error message"	,
				"IP Address","Login failure","Memory","Not responding/Frozen",	
				"Performance degradation","Power","Security breach","Storage","Virus"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The CI Component spy glass is clicked and the field : "+elements[i]+" is not available in CI configuration Look up list.", "FAILURE");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The CI Component spy glass is clicked and the field : "+elements[i]+" is not available in CI configuration Look up list.", "FAILURE");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("The CI Component spy glass is clicked and All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}

	public IncidentPage clickCIComponentLookUpandVerifyCIComponentApplicationLookUpValues(){

		if(!clickById("CREATEINC_CIComponent_Lookup_Id"))
			Reporter_ServiceNow.reportStep("The CI Component - Look up could not be clicked.", "FAILURE");

		Boolean bSuccess = true;
		switchToSecondWindow();
		switchToDefault();

		String[] elements = {"Account locked",	
				"Automated Job Failure","Connectivity","Data Issue","Error message"	,
				"Integration Issue","Login failure","Memory","Not responding/Frozen",	
				"Performance degradation","Security breach","Storage","UI issue","Virus"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The CI Component spy glass is clicked and the field in CI component: "+elements[i]+" is not available.", "FAILURE");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The CI Component spy glass is clicked and the field in CI component: "+elements[i]+" is not available.", "FAILURE");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("The CI Component spy glass is clicked and All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}

	public IncidentPage clickConfigurationItemSpyGlassandEnterAddCIDescription(String data) {

		if(!clickById("CreateInc_ConfigurationItem_spyglass_ID"))
			Reporter_ServiceNow.reportStep("The Configuration Item Spyglass could not be clicked", "FAILURE");
		else{
			switchToSecondWindow();
			//		Reporter_ServiceNow.reportStep("The Configuration Item Spyglass is clicked successfully", "SUCCESS");
		}

		if(clickByXpath("INC_MissingCI_Xpath")){
			switchToPrimary();
			Wait(2000);
			//		Reporter_ServiceNow.reportStep("The Missing CI Button is clicked Successfully", "SUCCESS");
		}		
		else
			Reporter_ServiceNow.reportStep("The Missing CI Button could not be clicked", "FAILURE");	

		switchToFrame("Frame_Main");

		if(!enterByXpath("INC_AddCIDescription_Xpath", data))
			//		Reporter_ServiceNow.reportStep("The text: "+data+" is entered in Add CI Description Successfully", "SUCCESS");
			//	else
			Reporter_ServiceNow.reportStep("The text: "+data+" could not be entered in  Add CI Description", "FAILURE");

		if(clickByXpath("EVENTS_OkButton_Xpath"))
			Reporter_ServiceNow.reportStep("The Configuration Item Spyglass is clicked and The text: "+data+" is entered in Add CI Description and Ok button is clicked Successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Ok button could not be clicked", "FAILURE");

		return this;
	}

	public IncidentPage clickCIComponentLookUpandVerifyCIComponentLookUpValues(){

		if(!clickById("CREATEINC_CIComponent_Lookup_Id"))
			Reporter_ServiceNow.reportStep("The CI Component - Look up could not be clicked.", "FAILURE");

		Boolean bSuccess = true;
		switchToSecondWindow();
		switchToDefault();

		String[] elements = {"Automated Job Failure", "Connectivity", "Integration Issue", "Performance degradation", "Security breach"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The CI Component - Look up is clicked and the field in CI component: "+elements[i]+" is not available.", "WARNING");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The CI Component - Look up is clicked and the field in CI component: "+elements[i]+" is not available.", "WARNING");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("The CI Component - Look up is clicked and All the fields :"+convertStringArrayToString(elements)+" do exists in CI Component Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}

	public IncidentPage clickCausingCIAndSelectFilter(String filter1, String filter2, String filter3) {

		if(!clickByXpath("WIP_RICausingCISpyglass_Xpath"))
			Reporter_ServiceNow.reportStep("The Causing CI Spyglass could not be clicked", "FAILURE");
		else
			switchToNewWindow();
		Wait(3000);
		if(!clickByXpath("CI_EditFilter_Xpath"))
			Reporter_ServiceNow.reportStep("The Funnel icon could not be clicked", "FAILURE");

		if(!addNewFilterUsingSelect(filter1, filter2, filter3))
			Reporter_ServiceNow.reportStep("The Fliter value: "+filter1+" "+filter2+" "+filter3+" could not be selected", "FAILURE");

		if(!clickByXpath("Run_Xpath"))
			Reporter_ServiceNow.reportStep("The Fliter value "+filter1+" "+filter2+" "+filter3+" could not be selected", "FAILURE");

		if(isExistByXpath("CI_NoRecords_Xpath"))
			Reporter_ServiceNow.reportStep("There is no matching records for selected value, hence failure", "FAILURE");
		// click the first Incident Link
		if(clickByXpath("CI_FirstCI_Xpath")){
			switchToPrimary();
			Wait(2000);

			Reporter_ServiceNow.reportStep("The Fliter value: "+filter1+" "+filter2+" "+filter3+" is setted and the First link is clicked Successfully", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The First link in Causing CI could not be clicked", "FAILURE");

		return this;
	}
	public IncidentPage enterCMDBConfItemAndClose(String confItem){

		// click the first Incident Link
		if(!enterAndChoose("CMDB_ConfigurationItem_Xpath", confItem))
			Reporter_ServiceNow.reportStep("The "+confItem+" could not be  entered in Configuration Item", "FAILURE");

		if(!clickById("closeTask_Id"))
			Reporter_ServiceNow.reportStep("The Close Button could not be clicked. Check snapshot", "FAILURE");

		if(getDefaultValueByXpath("INC_ConfiState_Xpath").equals("Closed"))
			Reporter_ServiceNow.reportStep("The Able to add the information to the task and Task closed as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Close Button could not be clicked. Check snapshot", "FAILURE");

		return this;
	}
	public IncidentPage clickResolutionInformationForNegative(){

		if(!clickByXpath("WIP_ResolutionInformation_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolution Information tab could not be clicked  ", "FAILURE");
		return this;
	}
	public IncidentPage verifyColorOfCustomerCommsandClick(){

		// this is to keep focus out of the edit/select field
		clickByXpath("CREATEINC_Notes_Xpath");
		//scrollPageDown();
		scrollToElementById("CREATEINC_CustomerComms_Id");

		String sVal = getBackgroundColorById("CREATEINC_CustomerComms_Id");
		//System.out.println(sVal);
		ColorUtils color = new ColorUtils();
		String colorval = color.getColorName(sVal);
		if(!colorval.equals("Linen"))
			Reporter_ServiceNow.reportStep("The Color of the Customer Comms is "+colorval+" did not match ", "FAILURE");

		if(clickByXpath("CREATEINC_CustomerWatchlist_Xpath"))
			Reporter_ServiceNow.reportStep("The Customer Comms box color is red and Edit Customer Watchlist is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Edit Customer Watchlist List could not be clicked", "FAILURE");

		return this;
	}
	public IncidentPage enterCustomerWatchlistForActiveUser(String asgTo) {

		if(enterAndChoose("CREATEINC_SelectUSerToCustomerWatchlist_Xpath", asgTo))
			Reporter_ServiceNow.reportStep("The Active User with Role: "+asgTo+" is added to Customer Watchlist as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep(""+asgTo+" could not be added to Customer Watchlist ", "FAILURE");

		if(clickById("CREATEINC_LockCustomerWatchlist_Id"))
			Reporter_ServiceNow.reportStep("The lock button is is clicked successfully and the user name "+asgTo+" is added to the list as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The lock Customer Watchlist could not be clicked ", "FAILURE");
		return this;
	}
	public IncidentPage enterCustomerWatchlistForInActiveUser(String asgTo) {

		if(!enterAndChoose("CREATEINC_SelectUSerToCustomerWatchlist_Xpath", asgTo))
			Reporter_ServiceNow.reportStep("The Non Active user: "+asgTo+" is not added to Customer Watchlist  as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep(""+asgTo+" is added to Customer Watchlist ", "FAILURE");
		return this;
	}
	public IncidentPage enterCustomerWatchlistMyself(String asgTo) {

		if(!clickByXpath("CREATEINC_AddMeCustomerWatchlistLocked_Xpath"))
			Reporter_ServiceNow.reportStep("The lock Customer Watchlist could not be clicked ", "FAILURE");

		Wait(3000);
		if(!(isExistByXpath("CREATEINC_AddMeCustomerWatchlist_Xpath")))
			Reporter_ServiceNow.reportStep("The Add Me Button is clicked and The User: "+asgTo+" added Customer Watchlist and ‘add me’ button disappears as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Add Me Button is appeared, hence failure.", "FAILURE");

		return this;
	}

	public IncidentPage selectRemoveAndLockUserFromCustomerWatchlist1(String user) {

		if(!clickById("CREATEINC_UnCustomerWatchlist_Id"))
			Reporter_ServiceNow.reportStep("The Unlock Customer Watchlist could not be clicked  ", "FAILURE");

		if(!clickByXpath("CREATEINC_selectUSerFromCustomerWatchlist1_Xpath"))
			Reporter_ServiceNow.reportStep("The User could not be selected from Customer Watchlist", "FAILURE");

		if(!clickByXpath("CREATEINC_RemoveUSerFromCustomerWatchlist_Xpath"))		
			Reporter_ServiceNow.reportStep("The User could not be removed from Customer Watchlist  ", "FAILURE");

		Wait(3000);
		if(!(isExistByXpath("CREATEINC_AddMeCustomerWatchlist_Xpath")))
			Reporter_ServiceNow.reportStep("The Add Me Button is appeared, hence failure.", "FAILURE");

		if(clickById("CREATEINC_LockCustomerWatchlist_Id"))
			Reporter_ServiceNow.reportStep("The user: "+user+" is removed from the Customer Watchlist and add Me button is disappear as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The lock Customer Watchlist could not be clicked ", "FAILURE");

		return this;

	}

	public IncidentPage verifyColorAndClickWorkNotes(){

		// this is to keep focus out of the edit/select field
		clickByXpath("CREATEINC_Notes_Xpath");
		//scrollPageDown();
		scrollToElementById("CREATEINC_WorkNotes_Id");

		String sVal = getBackgroundColorById("CREATEINC_WorkNotes_Id");	

		ColorUtils color = new ColorUtils();

		String colorval = color.getColorName(sVal);

		if(!colorval.equals("Green"))
			Reporter_ServiceNow.reportStep("The Color of the Work notes is "+colorval+" did not match ", "FAILURE");

		if(clickByXpath("CREATEINC_Worknoteslist_Xpath"))
			Reporter_ServiceNow.reportStep("The WorkNotes color is Green and Edit Work Notes List is clicked as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Edit Work Notes List could not be clicked", "FAILURE");

		return this;
	}

	public IncidentPage enterEditWorkNotesListForInActiveUser(String asgTo) {

		if(!enterAndChoose("CREATEINC_SelectUSerToWorkNotes_Xpath", asgTo))
			Reporter_ServiceNow.reportStep("The Non Active User: "+asgTo+" is not added to Work Notes List as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Non Active User: "+asgTo+" is added to Work Notes List, hence failure.", "FAILURE");
		return this;
	}
	public IncidentPage enterEditWorkNotesListForActiveUser(String asgTo) {

		if(!enterAndChoose("CREATEINC_SelectUSerToWorkNotes_Xpath", asgTo))
			Reporter_ServiceNow.reportStep(""+asgTo+" could not be added to Work Notes List", "FAILURE");

		if(clickById("CREATEINC_LockNotes_Id"))
			Reporter_ServiceNow.reportStep("The Active User with no role: "+asgTo+" is added to Work Notes List successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The lock Notes could not be clicked  ", "FAILURE");		

		return this;
	}
	public IncidentPage enterNonGileademailInWorkNotes(String email) {

		if(!clickById("CREATEINC_UnlockNotes_Id"))
			Reporter_ServiceNow.reportStep("The Unlock Notes could not be clicked  ", "FAILURE");

		if(!enterAndChoose("CREATEINC_SelectUSerToWorkNotes_Xpath", email))
			Reporter_ServiceNow.reportStep("The Non Gilead email: "+email+" is not found as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Non Gilead email: "+email+" is found and entered", "FAILURE");
		return this;
	}
	public IncidentPage createIncidentWithAffectedUser(String configItem, String repCust, String asgGroup, String desc,String aUser, String incNumber, String regUser) {

		enterConfigurationItem(configItem).
		enterReportingCustomer1(repCust).
		enterAssignmentGroup(asgGroup).
		enterShortDescription(desc)
		.enterAffectedUserForFailure(aUser);

		//		if(!clickByXpath("Submit_Xpath"))
		//			Reporter_ServiceNow.reportStep("The Create Incident process failed. Check snapshot", "FAILURE");
		//
		//		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
		//			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");
		//
		//		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath", incNumber))
		//			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");
		//
		//		if(isExistByXpath("NoRecords_xpath")){
		//			status="Insuffient Data";
		//			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");}
		if(clickById("Save_Id"))
			Reporter_ServiceNow.reportStep("The Reporting Customer: "+ repCust+" and Affected User: "+aUser +" is entered and ticket is saved successfully.", "SUCCESS");
		//			Reporter_ServiceNow.reportStep("The User: "+regUser+" able to save the ticket and both Reporting Customer and Affected User visible as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Save Button is not clicked or not found.", "FAILURE");

		return this;

	}

	public IncidentPage enterAffectedUserWithoutReport(String aUser) {

		if(!enterAndChoose("CREATEINC_AffectedUser_Xpath", aUser))
			//			Reporter_ServiceNow.reportStep("The Affected User: "+aUser+"  is entered successfully", "SUCCESS");
			//		else
			Reporter_ServiceNow.reportStep("The Affected User: "+aUser+" not found / could not be entered", "FAILURE");

		return this;
	}

	public IncidentPage verify() {

		clickByXpath("Save_Id");

		if(isExistByXpath("CREATEINC_RepCust_Xpath"))
			Reporter_ServiceNow.reportStep("The reporting customer: not found / could not be entered", "FAILURE");
		else
			Reporter_ServiceNow.reportStep("The reporting customer:  is entered", "SUCCESS");

		return this;
	}

	public IncidentsListPage submitWithoutReport() {

		clickByXpath("Submit_Xpath");
		goOutOfFrame();
		if (!isExistById("Welcome_Id"))
			//			Reporter_ServiceNow.reportStep("The Create Incident process is successful", "SUCCESS");
			//		else
			Reporter_ServiceNow.reportStep("The Create Incident process failed. Check snapshot", "FAILURE");
		return new IncidentsListPage(driver);
	}
	public IncidentPage clickConfigurationItemSpyGlassandClickMissingCI() {

		if(!clickById("CreateInc_ConfigurationItem_spyglass_ID"))
			Reporter_ServiceNow.reportStep("The Configuration Item Spyglass could not be clicked", "FAILURE");
		else{
			switchToSecondWindow();
			//		Reporter_ServiceNow.reportStep("The Configuration Item Spyglass is clicked successfully", "SUCCESS");
		}

		if(clickByXpath("INC_MissingCI_Xpath")){
			switchToPrimary();
			Wait(2000);
			Reporter_ServiceNow.reportStep("The Configuration Item Spyglass is clicked and the Missing CI Button is selected Successfully", "SUCCESS");
		}		
		else
			Reporter_ServiceNow.reportStep("The Missing CI Button could not be clicked", "FAILURE");	

		return this;
	}
	public IncidentPage addAddCIDescription(String data) {

		switchToFrame("Frame_Main");

		if(enterByXpath("INC_AddCIDescription_Xpath", data))
			Reporter_ServiceNow.reportStep("The text: "+data+" is entered in Add CI Description Successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The text: "+data+" could not be entered in  Add CI Description", "FAILURE");

		if(!clickByXpath("EVENTS_OkButton_Xpath"))
			//			Reporter_ServiceNow.reportStep(" The text: "+data+" is entered in Add CI Description and Ok button is clicked Successfully", "SUCCESS");
			//		else
			Reporter_ServiceNow.reportStep("The Ok button could not be clicked", "FAILURE");

		return this;
	}
	public IncidentPage EnterAssorupAndHoverGroupId(String asgGroup){


		if(!enterAndChoose("CREATEINC_AsgGroup_Xpath", asgGroup))
			Reporter_ServiceNow.reportStep("The Assignment Group: "+asgGroup+" not found / could not be entered", "FAILURE");

		mouseOverById("CREATEINC_AssiGroup_Id");

		if(isExistById("CREATEINC_GroupEmail_Id"))
			Reporter_ServiceNow.reportStep("The Assignment Group: "+asgGroup+" is entered and The Group Information displayed as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Group Information is not on mouse Over", "FAILURE");


		return this;

	}
	//	public IncidentPage hoverCallerId(){
	//
	//		mouseOverById("CREATEINC_Caller_Id");
	//
	//		if(isExistById("CREATEINC_SysUserName_Id"))
	//			Reporter_ServiceNow.reportStep("The User Information displayed successfully", "SUCCESS");
	//		else	
	//			Reporter_ServiceNow.reportStep("The User Information is not on mouse Over", "FAILURE");
	//
	//		return this;
	//
	//	}
	public IncidentPage enterAffectedUserAndReportingCustomer(String repCust) {

		//		if(!enterAndChoose("CREATEINC_AffectedUser_Xpath", aUser))
		//			Reporter_ServiceNow.reportStep("The Affected User: "+aUser+" not found / could not be entered", "FAILURE");

		if(!enterAndChoose("CREATEINC_RepCust_Xpath", repCust))
			Reporter_ServiceNow.reportStep("The reporting customer: "+repCust+" not found / could not be entered", "FAILURE");
		else
			Reporter_ServiceNow.reportStep("The reporting customer: "+repCust+" is entered", "SUCCESS");
		return this;
	}


	public IncidentPage verifyReportingCustomerAffectedUser() {

		clickByXpath("Save_Id");

		if(isExistByXpath("CREATEINC_AffectedUser_Xpath") && !isExistByXpath("CREATEINC_RepCust_Xpath"))

			Reporter_ServiceNow.reportStep("The Reporting Customer field is no longer visible and Affected User contains the name", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The reporting customer visible", "FAILURE");

		return this;
	}
	public IncidentPage verifyPriority(String Impact1, String Urgency1, String Priority1) {

		if(!selectByVisibleTextByXpath("Incident_Impact_Xpath", Impact1))
			Reporter_ServiceNow.reportStep("Impact with value "+Impact1+" could not be selected", "FAILURE");

		Wait(2000);
		if(!selectByVisibleTextByXpath("Incident_Urgency_Xpath", Urgency1))
			Reporter_ServiceNow.reportStep("Urgency with value "+Urgency1+" could not be selected", "FAILURE");

		Wait(2000);

		String P1=getDefaultValueByXpath("Incident_Priority_Xpath");

		System.out.println(P1);

		if((Priority1).equalsIgnoreCase(P1))
			Reporter_ServiceNow.reportStep("The values Impact: "+Impact1+" Urgency: "+Urgency1+" are selected"
					+ " and The Priority field is Changed to "+P1+" as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Priority field is not set with "+Priority1+", check snapshot", "FAILURE");

		return this;
	} 
	public IncidentPage clickPriority() {

		Wait(1000);
		if(clickByXpath("Incident_SetPriorityButton_Xpath"))
			Reporter_ServiceNow.reportStep("The Set Priority Button is clicked. Impact and Urgency fields are visible as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Set Priority Button is not clicked or not found", "FAILURE");

		return this;
	}

	public IncidentPage verifyPriorityAndAcceptAlert(String Impact1, String Urgency1, String Priority1) {

		if(!selectByVisibleTextByXpath("Incident_Impact_Xpath", Impact1))
			Reporter_ServiceNow.reportStep("Impact with value "+Impact1+" could not be selected", "FAILURE");

		Wait(2000);
		if(!selectByVisibleTextByXpath("Incident_Urgency_Xpath", Urgency1))
			Reporter_ServiceNow.reportStep("Urgency with value "+Urgency1+" could not be selected", "FAILURE");

		Wait(2000);

		if(alertAccept())
			Wait(2000);
		else
			Reporter_ServiceNow.reportStep("The Alert is not generated, hence failure.", "FAILURE");

		String P1=getDefaultValueByXpath("Incident_Priority_Xpath");

		System.out.println(P1);

		if((Priority1).equalsIgnoreCase(P1))
			Reporter_ServiceNow.reportStep("The values Impact: "+Impact1+" Urgency: "+Urgency1+" are selected, Popup is appeared and accepted and The Priority field is Changed to '"+P1+"' as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Priority field is not set with "+Priority1+", check snapshot", "FAILURE");

		return this;
	}

	public IncidentPage enterComments(){

		if(!enterByXpath("CREATEINC_CustomerComms_Xpath", "Resolved the Incident"))
			Reporter_ServiceNow.reportStep("The Additional comments could not be entered","FAILURE");

		return this;
	}
	public IncidentsListPage clickResolveButton(){

		if(clickById("Incident_ResolInci_Id"))	
			Reporter_ServiceNow.reportStep("The Resolve Incident Button is clicked successfully and The incident resolved as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Resolve Incident Button is not clicked or not found.","FAILURE");

		return new IncidentsListPage(driver);
	}

	public IncidentsListPage clickCloseButton(){

		if(clickById("Incident_CloseInci_Id"))	
			Reporter_ServiceNow.reportStep("The Close Incident Button is clicked successfully and The incident Closed as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Close Incident Button is not clicked or not found.","FAILURE");

		return new IncidentsListPage(driver);
	}

	public IncidentPage verifyActivityLog(String assignedTo, String assGroup){

		String[] tabNames ={"CREATEINC_ActivlogAssignedTo_Xpath","CREATEINC_ActivlogAssignmentGroup_Xpath","CREATEINC_ActivlogPriority_Xpath" 
				,"CREATEINC_ActivlogShortDescription_Xpath" ,"CREATEINC_ActivlogState_Xpath"};

		String[] tabDesc ={assignedTo,assGroup,"3 - Moderate","Test Message","Assigned"};

		// Verify the tabs exists
		//  verifyFieldsExistByXpath(tabNames, tabDesc);

		Boolean bReturn = true;
		for (int i=0; i < tabNames.length; i++) {
			if(!isExistByXpath(tabNames[i])){
				bReturn = false;
				Reporter_ServiceNow.reportStep("The field :"+tabDesc[i]+" is not displayed; hence failed","FAILURE");
			}   
		}

		if(bReturn)   
			Reporter_ServiceNow.reportStep("All the fields:"+convertStringArrayToString(tabDesc)+" are displayed in the activity log as expected","SUCCESS");

		return this;
	}

	public IncidentPage createIncidentAndOpenWithoutReport(String configItem, String repCust, String asgGroup, String desc, String incNumber) {

		enterConfigurationItem(configItem).
		enterReportingCustomer1(repCust).
		enterAssignmentGroup(asgGroup).
		enterShortDescription(desc);

		if(!clickByXpath("Submit_Xpath"))
			Reporter_ServiceNow.reportStep("The Create Incident process failed. Check snapshot", "FAILURE");

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath", incNumber))
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");

		if(isExistByXpath("NoRecords_xpath")){
			status="Insuffient Data";
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");}

		if(!clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Incident: "+incNumber+" is not found or not clicked.", "FAILURE");

		return this;

	}

	public IncidentPage hoverCallerId(){

		mouseOverById("CREATEINC_Caller_Id");

		if(isExistById("CREATEINC_SysUserName_Id"))
			Reporter_ServiceNow.reportStep("The Affected User Information displayed successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Affected User Information is not on mouse Over", "FAILURE");

		return this;

	}
	public IncidentPage createIncidentWithoutReportingCus(String configItem, String repCust, String asgGroup, String desc,String aUser, String incNumber, String regUser) {

		enterConfigurationItem(configItem).

		enterAssignmentGroup(asgGroup).
		enterShortDescription(desc).
		enterAffectedUser(aUser);



		if(!clickByXpath("Submit_Xpath"))
			Reporter_ServiceNow.reportStep("The Create Incident process failed. Check snapshot", "FAILURE");

		String text=driver.switchTo().alert().getText();

		alertAccept();
		Wait(3000);	
		if(!text.equals("The following mandatory fields are not filled in: Reporting Customer"))   
			Reporter_ServiceNow.reportStep("The error message The following mandatory fields are not filled in: Reporting Customer did not appear", "FAILURE");
		else
			Reporter_ServiceNow.reportStep("The User: "+regUser+" not able to save the ticket while Reporting Customer information is missing.", "SUCCESS");

		return this;

	}
	public IncidentPage createIncidentWithoutaffectedUser(String configItem, String repCust, String asgGroup, String desc,String aUser, String incNumber, String regUser) {

		enterConfigurationItem(configItem).
		enterAssignmentGroup(asgGroup).
		enterReportingCustomer(repCust).
		enterShortDescription(desc);

		if(!clickByXpath("Submit_Xpath"))
			Reporter_ServiceNow.reportStep("The Create Incident process failed. Check snapshot", "FAILURE");

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath", incNumber))
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");

		if(isExistByXpath("NoRecords_xpath")){
			status="Insuffient Data";
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");}

		if(!clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Incident: "+incNumber+" is not found or not clicked.", "FAILURE");

		if(isExistByXpath("CREATEINC_AffectedUser_Xpath") && !isExistByXpath("CREATEINC_RepCust_Xpath"))
			Reporter_ServiceNow.reportStep("The User: "+regUser+" able to save the ticket and  Reporting Customer field is no longer visible and Affected User contains the '"+repCust+"' as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The reporting customer visible", "FAILURE");


		return this;

	}
	public IncidentPage enterAffectedUserAndSave(String aUser) {

		if(!enterAndChoose("CREATEINC_AffectedUser_Xpath", aUser))
			Reporter_ServiceNow.reportStep("The Affected User: "+aUser+" not found / could not be entered", "FAILURE");
		if(clickById("Save_Id"))
			Reporter_ServiceNow.reportStep("The Affected User field is amended with value "+aUser+" and ticket saved successfully.", "SUCCESS");
		//			Reporter_ServiceNow.reportStep("The Value: "+aUser+" is able amend in Affected user field and save the ticket as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Save button could not be clicked or not found", "FAILURE");			
		return this;
	}

	public IncidentPage enterReportingCustomerAndSave(String repCust) {

		if(!enterAndChoose("CREATEINC_RepCust_Xpath", repCust))
			Reporter_ServiceNow.reportStep("The reporting customer: "+repCust+" not found / could not be entered", "FAILURE");
		if(clickById("Save_Id"))
			Reporter_ServiceNow.reportStep("The Reporting Customer field is amended with value "+repCust+" and ticket is saved successfully.", "SUCCESS");
		//			Reporter_ServiceNow.reportStep("The Value: "+repCust+" is able amend in Reporting customer field and save the ticket as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Save button could not be clicked or not found", "FAILURE");	
		return this;
	}

	public IncidentPage enterAllFieldsWithScrollToResolveButton(String causingCI, String causingCIComponent, String resolutionCode, String resolutionNotes) {


		if(!clickByXpath("WIP_ResolutionInformation_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolution Information tab could not be clicked  ", "FAILURE");

		if(!enterAndChoose("WIP_RICausingCI_Xpath", causingCI))
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" could not be entered in Causing CI field", "FAILURE");

		if(!enterAndChoose("WIP_RIResolutionCodefield_Xpath", resolutionCode))
			Reporter_ServiceNow.reportStep("The value : "+resolutionCode+" could not be entered in Resolution Code field", "FAILURE");

		if(!enterAndChoose("WIP_RICausingCIComponentfield_Xpath", causingCIComponent))
			Reporter_ServiceNow.reportStep("The value : "+causingCIComponent+" could not be entered in Causing CI Component field", "FAILURE");

		if(!enterByXpath("WIP_RIResolutionNotesfield_Xpath", resolutionNotes))
			Reporter_ServiceNow.reportStep("The value : "+resolutionNotes+" could not be entered in Resolution Notes field", "FAILURE");

		if(clickByXpath("ResolveIncident_Xpath")){
			Wait(2000);
			scrollToElementByXpath("WIP_ResolutionInformation_Xpath");
			Reporter_ServiceNow.reportStep("The Values CausingCI: "+causingCI+", CausingCI Component: "+resolutionCode+", Resolution Code: "+resolutionCode+", Resolution Notes: "+resolutionNotes+" "
					+ "are entered in respective fields and Resolve Button clicked successfully.", "SUCCESS");}
		else 
			Reporter_ServiceNow.reportStep("The Save button could not be clicked or not found", "FAILURE");  


		return this;

	}

	public IncidentPage isVerifyBusinessServiceAvilable(){

		if(isExistByXpath("CREATEINC_BusinesService_Xpath"))
			Reporter_ServiceNow.reportStep("The Business Service is field ia avialble as expected..", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Business Service is not available hence failure.", "FAILURE");

		return this;

	}
	public IncidentPage enterEditWorkNotesListForUserWithNoRole(String asgTo) {

		if(!enterAndChoose("CREATEINC_SelectUSerToWorkNotes_Xpath", asgTo))
			Reporter_ServiceNow.reportStep("The Active User with no role: "+asgTo+" is not added to Work Notes List as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Active User with no role: "+asgTo+" is added to Work Notes List, hence failure.", "FAILURE");
		return this;
	}
	public IncidentPage selectRemoveAndLockUserFromCustomerWatchlist(String user) {

		if(clickById("CREATEINC_UnCustomerWatchlist_Id"))
			Reporter_ServiceNow.reportStep("The Lock Button is clicked successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Lock Button could not be clicked.", "FAILURE");

		if(!clickByXpath("CREATEINC_selectUSerFromCustomerWatchlist1_Xpath"))
			Reporter_ServiceNow.reportStep("The User could not be selected from Customer Watchlist", "FAILURE");

		if(!clickByXpath("CREATEINC_RemoveUSerFromCustomerWatchlist_Xpath"))		
			Reporter_ServiceNow.reportStep("The User could not be removed from Customer Watchlist  ", "FAILURE");

		if(!(isExistByXpath("CREATEINC_AddMeCustomerWatchlist_Xpath")))
			Reporter_ServiceNow.reportStep("The Add Me Button is not appeared, hence failure.", "FAILURE");

		if(clickById("CREATEINC_LockCustomerWatchlist_Id"))
			Reporter_ServiceNow.reportStep("The user: "+user+" is removed from the Customer Watchlist and add Me button is Reappear as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The lock Customer Watchlist could not be clicked ", "FAILURE");

		return this;

	}

	public IncidentPage createIncidentAndOpenIncident(String configItem, String repCust, String asgGroup, String desc, String incNumber, String aUser) {

		enterConfigurationItem(configItem).
		enterReportingCustomer1(repCust).
		enterAssignmentGroup(asgGroup).
		enterShortDescription(desc).
		enterAssigned(aUser);

		if(!clickByXpath("Submit_Xpath"))
			Reporter_ServiceNow.reportStep("The Create Incident process failed. Check snapshot", "FAILURE");

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath", incNumber))
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");

		if(isExistByXpath("NoRecords_xpath")){
			status="Insuffient Data";
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");}

		if(!clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Incident: "+incNumber+" is not found or not clicked.", "FAILURE");

		if(!clickByXpath("CREATEINC_WipButton_Xpath"))
			Reporter_ServiceNow.reportStep("The Incident: "+incNumber+" is not found or not clicked.", "FAILURE");

		if (getDefaultValueByXpath("CREATEINC_IncidentState_Xpath").equals("Work in Progress"))
			Reporter_ServiceNow.reportStep("The Created Incident: "+incNumber+" is opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Incident: "+incNumber+" state is not matched with Work in Progress, hence failure.", "FAILURE");

		return this;

	}
	public IncidentPage deleteFilters(){ 
		if(!clickByXpath("EditFilter_Xpath"))
			Reporter_ServiceNow.reportStep("The Filter icon could not be clicked", "FAILURE");

		deleteAllFilters();
		clickRun();
		return this;
	}
	public IncidentPage clickRun(){  

		if(!clickByXpath("Run_Xpath"))
			Reporter_ServiceNow.reportStep("Run Button could not been clicked", "FAILURE");

		return this;
	}

	public MenuPage changeUrl() {
		driver.get("https://sparctest.service-now.com/navpage.do");
		Wait(10000);
		return new MenuPage(driver);
	}
	public IncidentPage verifyBusinessServiceMandatory(){

		// you need to change the read only fields when the application changes
		String[] readOnlyField = { "CREATEINC_BusinesServiceMan_Xpath"};
		String[] readOnlyFieldDesc = { "Business Service"};


		// Verify read only
		verifyMandatoryFields(readOnlyField, readOnlyFieldDesc);
		return this;
	}
	public IncidentPage verifyBusinessServiceisBlankAndReadOnly(){

		if(!verifyAttributeTextByXpath("CREATEINC_BusinesService_Xpath", "readonly", "true"))
			Reporter_ServiceNow.reportStep("The business service field is not Read Only, hence failed" , "FAILURE");

		if(getAttributeByXpath("CREATEINC_BusinesService_Xpath","value").equals(""))
			Reporter_ServiceNow.reportStep("The business service field is blank and Read Only as expected" , "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The business service field is not blank, hence failed" , "FAILURE");

		return this;

	}
	public IncidentPage enterDeviationNumber(String DeviationNumber){

		if(!clickByXpath("CREATEINC_Process_Xpath"))
			Reporter_ServiceNow.reportStep("The Process Tab could not be entered", "FAILURE");

		if(enterByXpath("CREATEINC_DeviationNumber_Xpath", DeviationNumber))
			Reporter_ServiceNow.reportStep("The "+DeviationNumber+" is enetered in the Deviation Number field", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Deviation Number could not be entered", "FAILURE");

		return this;
	}
	public IncidentPage clickMasterIncidentSpyglass() {

		if(!clickByXpath("CREATEINC_Process_Xpath"))
			Reporter_ServiceNow.reportStep("The Process Tab could not be entered", "FAILURE");

		if(!clickByXpath("MasterIncident_xpath"))
			Reporter_ServiceNow.reportStep("The Master Incident spy glass could not be clicked ", "FAILURE");
		else
		{
			Wait(3000);
			switchToNewWindow();
			Reporter_ServiceNow.reportStep("The Master Incident spy glass is clicked", "SUCCESS");
		}

		return this;
	}
	public IncidentPage selectFirstMasterIncident() {
		if(clickByXpath("CI_FirstCI_Xpath"))
		{
			switchToNewWindow();
			Reporter_ServiceNow.reportStep("The First Master Incident is clicked ", "SUCCESS");
		}
		else
			Reporter_ServiceNow.reportStep("The First Master Incident could not be clicked", "FAILURE");

		return this;

	}
	public IncidentPage createIncidentWithWorkInProcesswithourReport(String configItem, String repCust, String asgGroup, String desc, String incNumber, String aUser) {

		enterConfigurationItem(configItem).
		enterReportingCustomer1(repCust).
		enterAssignmentGroup(asgGroup).
		enterShortDescription(desc).
		enterAssigned(aUser);

		if(!clickByXpath("Submit_Xpath"))
			Reporter_ServiceNow.reportStep("The Create Incident process failed. Check snapshot", "FAILURE");

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath", incNumber))
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");

		if(isExistByXpath("NoRecords_xpath")){
			status="Insuffient Data";
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");}

		if(!clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Incident: "+incNumber+" is not found or not clicked.", "FAILURE");

		if(!clickByXpath("CREATEINC_WipButton_Xpath"))
			Reporter_ServiceNow.reportStep("The Incident: "+incNumber+" is not found or not clicked.", "FAILURE");

		if (!getDefaultValueByXpath("CREATEINC_IncidentState_Xpath").equals("Work in Progress"))
			Reporter_ServiceNow.reportStep("The Incident: "+incNumber+" state is not matched with Work in Progress, hence failure.", "FAILURE");


		return this;

	}
	public IncidentPage enterAllFieldsForResolveTicket(String causingCI, String causingCIComponent, String resolutionCode, String resolutionNotes, String user) {

		if(!enterAndChoose("WIP_RICausingCI_Xpath", causingCI))
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" could not be entered in Causing CI field", "FAILURE");

		if(!enterAndChoose("WIP_RIResolutionCodefield_Xpath", resolutionCode))
			Reporter_ServiceNow.reportStep("The value : "+resolutionCode+" could not be entered in Resolution Code field", "FAILURE");

		if(!enterAndChoose("WIP_RICausingCIComponentfield_Xpath", causingCIComponent))
			Reporter_ServiceNow.reportStep("The value : "+causingCIComponent+" could not be entered in Causing CI Component field", "FAILURE");

		if(!enterByXpath("WIP_RIResolutionNotesfield_Xpath", resolutionNotes))
			Reporter_ServiceNow.reportStep("The value : "+resolutionNotes+" could not be entered in Resolution Notes field", "FAILURE");

		if(clickByXpath("ResolveIncident_Xpath")){
			Wait(2000);
			scrollToElementByXpath("WIP_RICausingCI_Xpath");
			Reporter_ServiceNow.reportStep("The user: "+user+" able to raise a ticket and set it to the state of resolved as expected.", "SUCCESS");}
		else	
			Reporter_ServiceNow.reportStep("The Save button could not be clicked or not found", "FAILURE");		


		return this;

	}
	public IncidentPage isReopenButtonDisplayed(String user){

		Wait(3000);
		if(isExistByXpath("CREATEINC_ReopenIncident_Xpath"))
			Reporter_ServiceNow.reportStep("The Reopen Incident button is present for the user: "+user+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Reopen Incident button could not be found", "FAILURE");
		return this; 

	}
	public IncidentPage verifyActivityLog(String Notes) {


		String activity=getTextByXpath("INCIDENT_WorkNotes_Xpath");

		scrollToElementByXpath("INCIDENT_WorkNotes_Xpath");

		if(activity.contains(Notes))
			Reporter_ServiceNow.reportStep("The Incident state in Activity Section is matched with "+ Notes +" successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("THe Incident state in Activity Section is not matched with "+ Notes +" hence, failure ","FAILURE");

		return this;

	}


	public IncidentPage clickReopen(String CustomerComms){

		Wait(2000);
		if(!clickByXpath("CREATEINC_ReopenIncident_Xpath"))
			Reporter_ServiceNow.reportStep("The Reopen Incident button is not available", "FAILURE");
		//  enterByXpath("CREATEINC_CustomerComms_Xpath",CustomerComms);
		if(!enterByXpath("CREATEINC_CustomerComms_Xpath",CustomerComms ))
			Reporter_ServiceNow.reportStep("The value : "+CustomerComms+" could not be entered in Customer Comms field", "FAILURE");  
		if(clickByXpath("CREATEINC_ReopenIncident_Xpath"))
			Reporter_ServiceNow.reportStep("The value : "+CustomerComms+" is entered in Customer Comments field and Reopen Button clicked successfully.", "SUCCESS");
		else 
			Reporter_ServiceNow.reportStep("The Reopen Incident button is not available", "WARNING");

		return this;
	}

	public IncidentPage enterAllFieldsWithoutSave(String causingCI, String causingCIComponent, String resolutionCode, String resolutionNotes) {

		if(!enterAndChoose("WIP_RICausingCI_Xpath", causingCI))
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" could not be entered in Causing CI field", "FAILURE");

		if(!enterAndChoose("WIP_RIResolutionCodefield_Xpath", resolutionCode))
			Reporter_ServiceNow.reportStep("The value : "+resolutionCode+" could not be entered in Resolution Code field", "FAILURE");

		if(!enterAndChoose("WIP_RICausingCIComponentfield_Xpath", causingCIComponent))
			Reporter_ServiceNow.reportStep("The value : "+causingCIComponent+" could not be entered in Causing CI Component field", "FAILURE");

		if(enterByXpath("WIP_RIResolutionNotesfield_Xpath", resolutionNotes)){
			scrollToElementByXpath("WIP_ResolutionInformation_Xpath");
			Reporter_ServiceNow.reportStep("The Values CausingCI: "+causingCI+", CausingCI Component: "+resolutionCode+", Resolution Code: "+resolutionCode+", Resolution Notes: "+resolutionNotes+" "
					+ "are entered in respective fields successfully.", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The value : "+resolutionNotes+" could not be entered in Resolution Notes field", "FAILURE");

		return this;
	}
	public ListPage clickAndverifyCIComponetSpyGlass(String[] elements){


		if(!clickById("CREATEINC_CIComponent_Lookup_Id"))
			Reporter_ServiceNow.reportStep("The CI Component Spyglass could not be clicked", "FAILURE");
		else{
			//   switchToDefault();
			switchToSecondWindow();
			//   Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked successfully", "SUCCESS");
		}

		//  if(verifyAllTexts("CI_FirstCI_Xpath", ele))
		//   Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(ele)+" do exists in CI Component Spyglass list", "SUCCESS");
		//  else
		//   Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(ele)+" does not exists in CI Component Spyglass list, hence failure", "FAILURE");

		Boolean bSuccess = true;

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and the field in CI component: "+elements[i]+" is not available in the CI configuration Look up list.", "FAILURE");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and the field in CI component: "+elements[i]+" is not available in the CI configuration Look up list.", "FAILURE");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return new ListPage(driver);
	}
	public IncidentsListPage clickUpdate() {
		if(clickByXpath("CREATEINC_Update_Xpath"))
			Reporter_ServiceNow.reportStep("The Update button is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Update button could not be entered", "FAILURE");
		return new IncidentsListPage(driver);
	}

	public IncidentPage enterAllFieldsWithResolveButtonWithAlertAccept(String causingCI, String causingCIComponent, String resolutionCode, String resolutionNotes) {


		if(!clickByXpath("WIP_ResolutionInformation_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolution Information tab could not be clicked  ", "FAILURE");

		if(!enterAndChoose("WIP_RICausingCI_Xpath", causingCI))
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" could not be entered in Causing CI field", "FAILURE");

		if(!enterAndChoose("WIP_RIResolutionCodefield_Xpath", resolutionCode))
			Reporter_ServiceNow.reportStep("The value : "+resolutionCode+" could not be entered in Resolution Code field", "FAILURE");

		if(!enterAndChoose("WIP_RICausingCIComponentfield_Xpath", causingCIComponent))
			Reporter_ServiceNow.reportStep("The value : "+causingCIComponent+" could not be entered in Causing CI Component field", "FAILURE");

		if(!enterByXpath("WIP_RIResolutionNotesfield_Xpath", resolutionNotes))
			Reporter_ServiceNow.reportStep("The value : "+resolutionNotes+" could not be entered in Resolution Notes field", "FAILURE");



		if(clickByXpath("ResolveIncident_Xpath")){
			Wait(2000);
			alertAcceptforResolve();
			scrollToElementByXpath("WIP_RICausingCI_Xpath");
			Reporter_ServiceNow.reportStep("The Values CausingCI: "+causingCI+", CausingCI Component: "+resolutionCode+", Resolution Code: "+resolutionCode+", Resolution Notes: "+resolutionNotes+" "
					+ "are entered in respective fields and Resolve Button clicked successfully.", "SUCCESS");}
		else	
			Reporter_ServiceNow.reportStep("The Save button could not be clicked or not found", "FAILURE");	

		return this;

	}

	public IncidentsListPage clickUpdateandVerifyDeviationFieldMandatory() {
		if(clickByXpath("CREATEINC_Update_Xpath"))
			Reporter_ServiceNow.reportStep("The Update button is clicked successfully and the Deviation number is not mandatory as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Update button could not be entered", "FAILURE");
		return new IncidentsListPage(driver);
	}


	public IncidentsListPage createIncidentForDemo(String affuser, String cat, String subcat, 
			String impact, String urgency, String asngrp, String shrtdes) {
		
		if(!enterByXpath("CREATEINC_AffectedUser_Xpath", affuser))
			Reporter_ServiceNow.reportStep("The Afftected User could not be entered.", "FAILURE");

		if(!selectByVisibleTextById("CREATEINC_Category_Id", cat))
			Reporter_ServiceNow.reportStep("The Category could not be selected.", "FAILURE");

		Wait(5000);
		
		if(!selectByVisibleTextById("CREATEINC_SubCategory_Id", subcat))
			Reporter_ServiceNow.reportStep("The Sub Category could not be selected.", "FAILURE");

		if(!selectByVisibleTextById("CREATEINC_Impact_Id", impact))
			Reporter_ServiceNow.reportStep("The Impact could not be selected.", "FAILURE");

		if(!selectByVisibleTextById("CREATEINC_Urgency_Id", urgency))
			Reporter_ServiceNow.reportStep("The Urgency could not be selected.", "FAILURE");

		if(!enterAndChoose("CREATEINC_AsgGroup_Xpath", asngrp))
			Reporter_ServiceNow.reportStep("The Assignment Group could not be entered.", "FAILURE");

		if(!enterByXpath("CREATEINC_shortDesc_Xpath", shrtdes))
			Reporter_ServiceNow.reportStep("The Update button could not be entered", "FAILURE");
		
		if(clickByXpath("Submit_Incident_Xpath"))
			Reporter_ServiceNow.reportStep("All the values are entered in respective fields and submit button is clicked successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Create Incident process failed. Check snapshot", "FAILURE");

		return new IncidentsListPage(driver);
	}
	public IncidentPage verifyAllReadOnlyFieldsForDemo(){

		// you need to change the read only fields when the application changes
		String[] ReadFields = {"CREATEINC_IncidentNumber_Xpath","CREATEINC_IncidentPriority_Xpath",
				"CREATEINC_Opened_Xpath","CREATEINC_OpenedBy_Xpath"};
		String[] ReadLabels = {"Number","Priority","Opened","Opened by"};


		// Verify read only
		verifyDisabledFieldsByXpath(ReadFields, ReadLabels);

		return this;

	}	
	public IncidentPage verifyAllMandatoryFieldsforDemo(){

		String[] ManFields = {"CREATEINC_AffectedUserStar_Xpath",
							  "CREATEINC_AsgGroupStar_Xpath",
							  "CREATEINC_shortDescStar_Xpath"};
		String[] ManLabels = {"Caller",
							  "Assignment Group",
							  "Short Description"};

		verifyMandatoryFields(ManFields, ManLabels);

		return this;

	}
	
	public IncidentPage verifyAllMandatoryFieldsforDemo1(){

		scrollToElementByXpath("CREATEINC_shortDescStar_Xpath");
		
		String[] ManFields = {"CREATEINC_shortDescStar_Xpath"};
		String[] ManLabels = {"Short Description"};

		verifyMandatoryFields(ManFields, ManLabels);

		return this;

	}
	public IncidentPage clickResolveIncidentWithAlertAcceptDemo(String clcode, String clnotes){

		if(clickByXpath("ResolveIncident_Xpath"))
		{
			String text=getTextAndAcceptAlert();
			Wait(5000);
			scrollToElementByXpath("WIP_ResolutionInformation_Xpath");
			Reporter_ServiceNow.reportStep("Alert displayed with message - "+text, "SUCCESS");
		}
		else
			Reporter_ServiceNow.reportStep("The Resolve Incident button is not available", "WARNING");

		if(!selectByVisibleTextById("CREATEINC_CloseCode_Id", clcode))
			Reporter_ServiceNow.reportStep("Close code could not be selected","FAILURE");

		if(enterById("CREATEINC_CloseNotes_Id", clnotes))
			Reporter_ServiceNow.reportStep("Close code and Close notes filled according to reference data","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Close notes could not be selected","FAILURE");

		
		return this;
	}
	public ListPage clickCIComponetSpyGlassAndverifyCIcounts(){


		  if(!clickById("CREATEINC_CIComponent_Lookup_Id"))
		   Reporter_ServiceNow.reportStep("The CI Component Spyglass could not be clicked", "FAILURE");
		  else{
		   switchToSecondWindow();
		  }

		  if(getCountOfElementsByXpath("Inc_CIcomponentValues_Xpath")>0)
		   Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and The values are listed in the CI configuration Look up list as expected.", "SUCCESS");
		  else
		   Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and The values is not listed in CI configuration Look up list, hence failure.", "FAILURE");

		  switchToPrimary();
		  
		  return new ListPage(driver);

		 }

public IncidentPage clickCIComponetSpyGlassAndverifyCIcounts1(){


	  if(!clickById("CREATEINC_CIComponent_Lookup_Id"))
	   Reporter_ServiceNow.reportStep("The CI Component Spyglass could not be clicked", "FAILURE");
	  else{
	   switchToSecondWindow();
	  }

	  if(getCountOfElementsByXpath("Inc_CIcomponentValues_Xpath")>0)
	   Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and The values are listed in the CI configuration Look up list as expected.", "SUCCESS");
	  else
	   Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and The values is not listed in CI configuration Look up list, hence failure.", "FAILURE");

	  switchToPrimary();
	  
	  return new IncidentPage(driver);

	 }

}





