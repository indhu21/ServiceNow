package Pages_ServiceNow;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class IncidentsWipPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public IncidentsWipPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("WIP_ConditionPresent_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not the Incident Work in Progress page", "FAILURE");
		}

		switchToMainFrame();

	}

	public IncidentsWipPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");

		return this;
	}

	public IncidentsWipPage clickWIPState(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");
		clickByXpath("WIP_SelectWIP_Xpath");

		return this;
	}

	public IncidentsWipPage clickFirstIncident(){

		if(isExistByXpath("NoRecords_xpath"))
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");
		
		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The First Incident is clicked Successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First Incident could not be clicked", "FAILURE");

		return this;
	}

	public IncidentsWipPage deleteFilter(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");
		clickByXpath("EditFilter_Xpath");
		deleteAllFilters();

		return this;
	}

	public IncidentsWipPage addFilter(String filterType,String filterCondition,String filterValue){

		// Switch to the menu frame
		switchToFrame("Frame_Main");
		addNewFilter("State", "is","Work in Progress");
		clickByXpath("Run_Xpath");

		return this;
	}

	public IncidentsWipPage clickWIPResolutionInformation(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");
		if(clickByXpath("WIP_ResolutionInformation_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolution Information tab is clicked successfully ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Resolution Information tab could not be clicked  ", "FAILURE");

		return this;
	}

	public IncidentsWipPage verifyResolotionInformationFieldsReadOnly(){

		// Switch to the menu frame
		switchToMainFrame();

		// you need to change the read only fields when the application changes
		String[] readOnlyFields = { "WIP_RICausingCI_Xpath","WIP_RICausingCIComponent_Xpath"};

		String[] readOnlyFieldsDesc = { "Causing CI","Causing CI Component"};

		// Verify read only
		verifyDisabledFieldsByXpath(readOnlyFields,readOnlyFieldsDesc);

		return this;
	}

	public IncidentsWipPage clickActivityLog() {
		if(clickByXpath("CREATEINC_ActivityLog_Xpath"))
			Reporter_ServiceNow.reportStep("The Activity Log is clicked successfully ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Activity Log could not be clicked  ", "FAILURE");

		return this;
	}

	public IncidentsWipPage verifyActivityLogDisplyed(){

		// Switch to the menu frame
		switchToMainFrame();

		// Verify the tabs exists
		if(isExistByXpath("WIP_ALHeader_Xpath"))
			Reporter_ServiceNow.reportStep("The Activity Log details are present", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Activity Log details are not available", "FAILURE");

		return this;
	}

	public IncidentsWipPage clickResolveIncident (){

		// Switch to the menu frame
		switchToMainFrame();

		// Verify the tabs exists
		if(clickByXpath("ResolveIncident_Xpath"))
			Reporter_ServiceNow.reportStep("The Resolve Incident button is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Resolve Incident button is not available", "WARNING");

		return this;
	}

	public IncidentsWipPage clickProcess() {
		if(clickByXpath("CREATEINC_Process_Xpath"))
			Reporter_ServiceNow.reportStep("The Process Tab is clicked successfully ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Activity Log could not be clicked  ", "FAILURE");

		return this;
	}

	public IncidentsWipPage hoverMasterIncident() {

		mouseOverById("WIP_ProcessMasterIncident_Id");
		if(isExistById("WIP_MasterIncConfiguarionItem_Id"))
			Reporter_ServiceNow.reportStep("The User Information displayed successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The User Information is not on mouse Over", "FAILURE");

		return this;
	}

	public IncidentsWipPage enterCausingCI(String causingCI) {

		if(enterByXpath("WIP_RICausingCI_Xpath", causingCI))
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" is entered in Causing CI field successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" could not be entered in Causing CI field", "WARNING");

		return this;
	}

	public IncidentsWipPage enterAndChooseCausingCIComponent(String causingCIComponent) {

		if(enterAndChoose("WIP_RICausingCIComponentfield_Xpath", causingCIComponent))
			Reporter_ServiceNow.reportStep("The value : "+causingCIComponent+" is entered in Causing CI Component field successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The value : "+causingCIComponent+" could not be entered in Causing CI Component field", "WARNING");

		return this;
	}

	public IncidentsWipPage enterAndChooseResolutionCode(String resolutionCode) {

		if(enterAndChoose("WIP_RIResolutionCodefield_Xpath", resolutionCode))
			Reporter_ServiceNow.reportStep("The value : "+resolutionCode+" is entered in Resolution Code field successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The value : "+resolutionCode+" could not be entered in Resolution Code field", "WARNING");

		return this;
	}

	public IncidentsWipPage enterResolutionNotes(String resolutionNotes) {

		if(enterAndChoose("WIP_RIResolutionNotesfield_Xpath", resolutionNotes))
			Reporter_ServiceNow.reportStep("The value : "+resolutionNotes+" is entered in Resolution Notes field successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The value : "+resolutionNotes+" could not be entered in Resolution Notes field", "WARNING");

		return this;
	}
	public IncidentsWipPage enterGTrackChange(String gTrackChange) {

		if(enterByXpath("CREATEINC_GTrackChange_Xpath",gTrackChange ))
			Reporter_ServiceNow.reportStep("The value : "+gTrackChange+" is entered in GTrack Change field successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The value : "+gTrackChange+" could not be entered in GTrack Change field", "WARNING");

		return this;
	}

	public IncidentsWipPage clickSave() {

		if(clickById("Save_Id"))
			Reporter_ServiceNow.reportStep("The save is clicked successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The save could not be clicked", "WARNING");

		return this;
	}

	public IncidentsWipPage verifySOXSystemDisabled() {

		if(verifyAttributeTextByXpath("CREATEINC_SOXSystem_Xpath", "disabled", "true"))
			Reporter_ServiceNow.reportStep("The SOX System is in read only", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The SOX System is editable", "WARNING");

		return this;
	}

	public IncidentsWipPage verifyGxPSystemDisabled() {

		if(verifyAttributeTextByXpath("CREATEINC_SOXSystem_Xpath", "disabled", "true"))
			Reporter_ServiceNow.reportStep("The SOX System is in read only", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The SOX System is editable", "WARNING");

		return this;
	}

	public IncidentsWipPage enterAndChooseCausingCI(String causingCI) {

		if(enterAndChoose("WIP_RICausingCI_Xpath", causingCI))
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" is entered in Causing CI field successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" could not be entered in Causing CI field", "FAILURE");

		return this;
	}
	public IncidentsWipPage verifyResolotionInformationCausingCIIsEnabled(){

		// Switch to the menu frame
		switchToMainFrame();

		// you need to change the read only fields when the application changes
		String[] readOnlyFields = {"WIP_RICausingCI_Xpath"};

		String[] readOnlyFieldsDesc = {"Causing CI"};

		// Verify read only
		verifyEnabledFieldsByXpath(readOnlyFields,readOnlyFieldsDesc);

		return this;
	}

	public IncidentsWipPage enterAndVerifyCausingCI(String causingCI) {

		if(enterAndChoose("WIP_RICausingCI_Xpath", causingCI))
			Reporter_ServiceNow.reportStep("The value : "+causingCI+" is NOT available on the list Causing CI list", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("Causing CI field is disabled ", "FAILURE");

		return this;
	}

	public IncidentsWipPage clickCausingCIComponentSpyGlass() {

		if(clickByXpath("WIP_RICausingCIComponentSpyglass_Xpath")){

		}else	
			Reporter_ServiceNow.reportStep("The CausingCI Component Spyglass could not be clicked", "WARNING");

		switchToNewWindow();
		Reporter_ServiceNow.reportStep("The CausingCI Component Spyglass is clicked successfully", "SUCCESS");

		return this;
	}

	public IncidentsWipPage switchToNewWindow(){

		// Switch to the menu frame
		switchToSecondWindow();


		return this;
	}

	public IncidentsWipPage verifyCIComponentBusinessServiceLookUpValues(){
		Boolean bSuccess = true;

		String[] elements = {"Automated Job Failure",
				"Connectivity",				
				"Integration Issue",					 
				"Performance degradation",
		"Security breach"};

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

	public IncidentsWipPage verifyCIComponentApplicationLookUpValues(){
		Boolean bSuccess = true;

		String[] elements = {"Account locked",	
				"Automated Job Failure","Connectivity","Data Issue","Error message"	,
				"Integration Issue","Login failure","Memory","Not responding/Frozen",	
				"Performance degradation","Security breach","Storage","UI issue","Virus"};

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
	
	public IncidentsWipPage verifyCIComponentStorageLookUpValues(){
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



}
