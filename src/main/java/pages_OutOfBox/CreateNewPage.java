package pages_OutOfBox;


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

public class CreateNewPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public CreateNewPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("Back_Xpath")) {
//			Reporter_ServiceNow.reportStep("This is not the Create New page", "FAILURE");
		}
	}

	public CreateNewPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}	

	public CreateNewPage verifyAllMandatoryFields(){

		// you need to change the mandatory fields when the application changes
		String[] mandatoryFields = {"INC_AssignmentGroupMand_Xpath",
				"INC_AssignedToMand_Xpath",
		"INC_ConfigItemMand_Xpath"};

		String[] mandatoryFieldsDesc = {"Assignment Group",
				"Assigned To","Configuration Item"};

		verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);
		return this;

	}

	public CreateNewPage selectType(String type) {

		if (!selectByVisibleTextByXpath("CHN_Type_Xpath", type))
			Reporter_ServiceNow.reportStep("The value: "+type+" could not Selected in Type field",
					"FAILURE");
		return this;

	}

	public CreateNewPage createChangeRequest(String configItem, String assignmentGroup, String assignedTo, String priority, String risk, String impact) {

		if (!enterAndChoose("CHN_ConfigItem_Xpath", configItem))
			Reporter_ServiceNow.reportStep("The value: "+configItem+" could not entered in Configuration Item field",
					"FAILURE");

		if (!enterAndChoose("CHN_AssignmentGroup_Xpath", assignmentGroup))
			Reporter_ServiceNow.reportStep("The value: "+assignmentGroup+"  could not be entered in Assignment Group field",
					"FAILURE");

		if (!enterAndChoose("CHN_AssignedTo_Xpath",assignedTo ))
			Reporter_ServiceNow.reportStep("The value: "+assignedTo+" could not be entered in Assigned To field",
					"FAILURE");


		if (!selectByVisibleTextByXpath("CHN_Priority_Xpath", priority))			
			Reporter_ServiceNow.reportStep("The value: "+priority+"  could not be selected in Priority field",
					"FAILURE");

		if (!selectByVisibleTextByXpath("CHN_Risk_Xpath",risk ))			
			Reporter_ServiceNow.reportStep("The value: "+risk+"  could not be selected in Priority field",
					"FAILURE");

		if (!selectByVisibleTextByXpath("CHN_Impact_Xpath",impact ))			
			Reporter_ServiceNow.reportStep("The value: "+impact+"  could not be selected in Priority field",
					"FAILURE");


		if (!rightClickByXpath("CHN_Header_Xpath"))
			Reporter_ServiceNow.reportStep("Right click could not be done on the header",
					"FAILURE");

		if (clickByXpath("CHN_Save_Xpath"))
			Reporter_ServiceNow.reportStep("The "+configItem+" , "+assignmentGroup+", "+assignedTo+", "+priority+" , "+risk+" , "+impact+" are entered and Saved successfully",
					"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The save button could not been clicked", "FAILURE");
		return this;

	}

	public CreateNewPage verifyChangeRequest(String category, String priority, String risk, String impact, String approval, String type, String state
			) {

		if (!getDefaultValueByXpath("CHN_Category_Xpath").contains(category))
			Reporter_ServiceNow.reportStep("The value: "+category+" is not the default value in Category field",
					"FAILURE");

		if (!getDefaultValueByXpath("CHN_Priority_Xpath").contains(priority))
			Reporter_ServiceNow.reportStep("The value: "+priority+" is not the default value in priority field",
					"FAILURE");

		if (!getDefaultValueByXpath("CHN_Risk_Xpath").contains(risk))
			Reporter_ServiceNow.reportStep("The value: "+risk+" is not the default value in risk field",
					"FAILURE");

		if (!getDefaultValueByXpath("CHN_Impact_Xpath").contains(impact))
			Reporter_ServiceNow.reportStep("The value: "+impact+" is not the default value in risk field","FAILURE");

		if (!getDefaultValueByXpath("CHN_Approval_Xpath").contains(approval))
			Reporter_ServiceNow.reportStep("The value: "+approval+" is not the default value in Approval field or Approval field not available.","FAILURE");

		if (!getDefaultValueByXpath("CHN_Type_Xpath").contains(type))
			Reporter_ServiceNow.reportStep("The value: "+type+" is not the default value in Type field","FAILURE");

		if (getDefaultValueByXpath("CHN_State_Xpath").contains(state))
			Reporter_ServiceNow.reportStep("The  "+category+" , "+priority+" , "+risk+" , "+impact+" ,"+approval+" ,"+type+" , "+state+" are the Default value as expected",
					"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value: "+state+" is not the default value in State field", "FAILURE");
		return this;

	}

	public CreateNewPage rightClickonHeader(){


		// Verify the tabs exists
		if(rightClickByXpath("CHN_Header_Xpath")){		
			clickByXpath("CHN_Save_Xpath");
			Reporter_ServiceNow.reportStep("Right click on the Incident header and Save is performed sucessfully ", "SUCCESS");
		}
		else if(isExistByXpath("NoRecords_xpath"))
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");
		else
			Reporter_ServiceNow.reportStep("Right click could not be clicked ", "FAILURE");
		return this;
	}

	public CreateNewPage verifyRequestApprovalNotAvailable(){
		// Verify the tabs exists
		if(IsElementNotPresentByXpath("CHN_RequestApproval_Xpath"))	

			Reporter_ServiceNow.reportStep("Request Approval button is not available as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Request Approval button is available ", "FAILURE");
		return this;
	}

	public CreateNewPage clickCreatedChangeRequest(){
		// Verify the tabs exists
		scrollToElementByXpath("CHN_CreatedCHNRequest_Xpath");

		if(clickByXpath("CHN_CreatedCHNRequest_Xpath"))	
			Reporter_ServiceNow.reportStep("Newly Created Change Request is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Created Change Request could not be clicked", "FAILURE");
		return this;
	}

	public CreateNewPage verifyState(String state){
		// Verify the tabs exists
		System.out.println(getDefaultValueByXpath("CHN_StateStatus_Xpath"));
		if(getDefaultValueByXpath("CHN_StateStatus_Xpath").contains(state))
			Reporter_ServiceNow.reportStep("State is "+state+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("State is Not "+state+"", "FAILURE");
		return this;
	}

	public CreateNewPage clickCloseTask(){
		// Verify the tabs exists
		if(clickByXpath("CHN_CloseTask_Xpath"))

			Reporter_ServiceNow.reportStep("Close task is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Close task could not be clicked", "FAILURE");
		return this;
	}

	public CreateNewPage enterConfigItemAssignmentGroupAssignedTo(String configItem, String assignmentGroup, String assignedTo){
		if (!enterAndChoose("CHN_ConfigItemCT_Xpath", configItem))
			Reporter_ServiceNow.reportStep("The value: "+configItem+" could not entered in Configuration Item field",
					"FAILURE");

		if (!enterAndChoose("CHN_AssignmentGroupCT_Xpath", assignmentGroup))
			Reporter_ServiceNow.reportStep("The value: "+assignmentGroup+"  could not be entered in Assignment Group field",
					"FAILURE");

		if (!enterAndChoose("CHN_AssignedToCT_Xpath",assignedTo ))
			Reporter_ServiceNow.reportStep("The value: "+assignedTo+" could not be entered in Assigned To field",
					"FAILURE");
		return this;
	}

	public CreateNewPage clickCreatedChangeRequestPlan(){
		Wait(5000);
		// Verify the tabs exists
		scrollToElementByXpath("CHN_CreatedCHNRequestPlan_Xpath");

		if(clickByXpath("CHN_CreatedCHNRequestPlan_Xpath"))	
			Reporter_ServiceNow.reportStep("Newly Created Plan Task is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Created Plan Task could not be clicked", "FAILURE");
		return this;
	}


	public CreateNewPage clickCreatedChangeRequestBuild(){
		// Verify the tabs exists
		Wait(5000);
		scrollToElementByXpath("CHN_CreatedCHNRequestBuild_Xpath");

		if(clickByXpath("CHN_CreatedCHNRequestBuild_Xpath"))	
			Reporter_ServiceNow.reportStep("Newly Created Build Task is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Created Build Task could not be clicked", "FAILURE");
		return this;
	}

	public CreateNewPage clickCreatedChangeRequestTest(){
		// Verify the tabs exists
		Wait(5000);
		scrollToElementByXpath("CHN_CreatedCHNRequestTest_Xpath");

		if(clickByXpath("CHN_CreatedCHNRequestTest_Xpath"))	
			Reporter_ServiceNow.reportStep("Newly Created Test Task is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Created Test Task could not be clicked", "FAILURE");
		return this;
	}

	public CreateNewPage clickCreatedChangeRequestImplementation(){
		// Verify the tabs exists
		Wait(5000);
		scrollToElementByXpath("CHN_CreatedCHNRequestImplementation_Xpath");

		if(clickByXpath("CHN_CreatedCHNRequestImplementation_Xpath"))	
			Reporter_ServiceNow.reportStep("Newly Created Implementation Task is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Created Implementation Task could not be clicked", "FAILURE");
		return this;
	}

	public CreateNewPage clickback(){
		// Verify the tabs exists
		if(!clickByXpath("Back_Xpath"))
			Reporter_ServiceNow.reportStep("Back Button could not be clicked", "FAILURE");
		return this;
	}

	public CreateNewPage clickEmergencyCreatedChangeRequestPlan(){
		Wait(5000);
		// Verify the tabs exists
		scrollToElementByXpath("CHN_EmergencyCreatedCHNRequestPlan_Xpath");

		if(clickByXpath("CHN_EmergencyCreatedCHNRequestPlan_Xpath"))	
			Reporter_ServiceNow.reportStep("Newly Created Plan Task is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Created Plan Task could not be clicked", "FAILURE");
		return this;
	}


	public CreateNewPage clickEmergencyCreatedChangeRequestBuild(){
		// Verify the tabs exists
		Wait(5000);
		scrollToElementByXpath("CHN_EmergencyCreatedCHNRequestBuild_Xpath");

		if(clickByXpath("CHN_EmergencyCreatedCHNRequestBuild_Xpath"))	
			Reporter_ServiceNow.reportStep("Newly Created Build Task is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Created Build Task could not be clicked", "FAILURE");
		return this;
	}

	public CreateNewPage clickEmergencyCreatedChangeRequestTest(){
		// Verify the tabs exists
		Wait(5000);
		scrollToElementByXpath("CHN_EmergencyCreatedCHNRequestTest_Xpath");

		if(clickByXpath("CHN_EmergencyCreatedCHNRequestTest_Xpath"))	
			Reporter_ServiceNow.reportStep("Newly Created Test Task is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Created Test Task could not be clicked", "FAILURE");
		return this;
	}

	public CreateNewPage clickEmergencyCreatedChangeRequestImplementation(){
		// Verify the tabs exists
		Wait(5000);
		scrollToElementByXpath("CHN_EmergencyCreatedCHNRequestImplementation_Xpath");

		if(clickByXpath("CHN_EmergencyCreatedCHNRequestImplementation_Xpath"))	
			Reporter_ServiceNow.reportStep("Newly Created Implementation Task is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Created Implementation Task could not be clicked", "FAILURE");
		return this;
	}


	public CreateNewPage verifyRequestedBy(String requestedBy){
		System.out.println(getAttributeByXpath("CHN_RequestedBy_Xapth","value"));	
		
		if(getAttributeByXpath("CHN_RequestedBy_Xapth","value").contains(requestedBy))	
			Reporter_ServiceNow.reportStep("Requested By is the logged in user as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Requested By is not logged in user.", "FAILURE");
		return this;
	}

	public CreateNewPage hoverRequestedBy(){

		mouseOverByXpath("CHN_HoverRequestedBy_Xpath");	

		Wait(5000);

		if(isExistByXpath("CHN_RequestedByEmail_Xpath"))
			Reporter_ServiceNow.reportStep("Requested User Details displayed successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Requested User Details is not on mouse Over", "FAILURE");
		return this;

	}

	public CreateNewPage clickCheckConflicts(){

		if(clickLink("CHN_CheckConflicts"))	
			Reporter_ServiceNow.reportStep("Check Conflicts link is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Check Conflicts link could not be clicked", "FAILURE");
		return this;
	}

	public CreateNewPage verifyConflictError(String error){

		if(getTextByXpath("CHN_ConflictError_Xpath").contains(error))
			Reporter_ServiceNow.reportStep("The error message "+error+" appeared as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The error message didnot appear", "FAILURE");
		return this;
	}

	public CreateNewPage verifyConflictError2(String error){

		if(getTextByXpath("CHN_ConflictError2_Xpath").contains(error))
			Reporter_ServiceNow.reportStep("The error message "+error+" appeared as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The error message didnot appear", "FAILURE");
		return this;
	}

	public CreateNewPage enterplannedStartDate(String startDate){

		if(enterByXpath("CHN_PlannedStartDate_Xpath", startDate))
			Reporter_ServiceNow.reportStep("Planned Start Date "+startDate+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Planned Start Date could not be entered", "FAILURE");
		return this;
	}

	public CreateNewPage enterplannedEndDate(String endDate){

		if(enterByXpath("CHN_PlannedEndDate_Xpath", endDate))
			Reporter_ServiceNow.reportStep("Planned End Date "+endDate+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Planned End Date could not be entered", "FAILURE");
		return this;
	}

	public CreateNewPage verifyConflictMessage(String message){

		if(getTextByXpath("CHN_ConflictMessage_Xpath").contains(message))
			Reporter_ServiceNow.reportStep(""+message+" appeared as Expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The conflicts are not available", "FAILURE");
		return this;
	}

	public CreateNewPage clickCalculateRisk(){

		if(clickLink("CHN_CalculateRisk"))	
			Reporter_ServiceNow.reportStep("Check Conflicts link is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Check Conflicts link could not be clicked", "FAILURE");
		return this;
	}
	public CreateNewPage verifyDefaultSelectedType(String type){
		System.out.println(getDefaultValueByXpath("CHN_Type_Xpath"));
		if (getDefaultValueByXpath("CHN_Type_Xpath").contains(type))
//		if (getDefaultValueById("CHN_Type_Id").contains(type))
			Reporter_ServiceNow.reportStep("The value: "+type+" is the default value in Type field","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value: "+type+" is not the default value in Type field","FAILURE");
		return this;
	}

	public CreateNewPage verifylistofType(){

		String[] expectedValues = {"-- None --","Routine","Comprehensive","Emergency"};		

		if(verifyListContents("CHN_Type_Xpath", expectedValues))
			Reporter_ServiceNow.reportStep("The value: "+convertStringArrayToString(expectedValues)+" are the list of values in Type field","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The default list of values does not match with the Type field","FAILURE");			

		return this;
	}

	public CreateNewPage verifyDefaultSelectedApproval(String approval){
		if (getDefaultValueByXpath("CHN_Approval_Xpath").contains(approval))
			Reporter_ServiceNow.reportStep("The value: "+approval+" is the default value in Approval field","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value: "+approval+" is not the default value in Approval field","FAILURE");
		return this;
	}

	public CreateNewPage verifylistofApproval(){

		String[] expectedValues = {"Not Yet Requested","Requested","Approved","Rejected"};		

		if(verifyListContents("CHN_Approval_Xpath", expectedValues))
		{				
			Reporter_ServiceNow.reportStep("The value: "+convertStringArrayToString(expectedValues)+" are the list of values in Approval field","SUCCESS");

		}else
			Reporter_ServiceNow.reportStep("The default list of values does not match with the Approval field","FAILURE");			

		return this;
	}

	public CreateNewPage verifyDefaultSelectedState(String state){
		if (getDefaultValueByXpath("CHN_State_Xpath").contains(state))
			Reporter_ServiceNow.reportStep("The value: "+state+" is the default value in State field","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value: "+state+" is not the default value in State field","FAILURE");
		return this;
	}

	public CreateNewPage verifylistofState(){

		String[] expectedValues = {"Pending","Open","Work in Progress","Closed Complete"
				,"Closed Incomplete","Closed Skipped"};		

		if(verifyListContents("CHN_State_Xpath", expectedValues))
		{				
			Reporter_ServiceNow.reportStep("The value: "+convertStringArrayToString(expectedValues)+" are the list of values in State field","SUCCESS");

		}else
			Reporter_ServiceNow.reportStep("The default list of values does not match with the State field","FAILURE");			

		return this;
	}

	public CreateNewPage verifyDefaultSelectedPriority(String priority){
		if (getDefaultValueByXpath("CHN_Priority_Xpath").contains(priority))
			Reporter_ServiceNow.reportStep("The value: "+priority+" is the default value in Priority field","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value: "+priority+" is not the default value in Priority field","FAILURE");
		return this;
	}

	public CreateNewPage verifylistofPriority(){

		String[] expectedValues = {"-- None --","1 - Critical","2 - High","3 - Moderate","4 - Low","5 - Planning"};		

		if(verifyListContents("CHN_Priority_Xpath", expectedValues))
		{				
			Reporter_ServiceNow.reportStep("The value: "+convertStringArrayToString(expectedValues)+" are the list of values in Priority field","SUCCESS");

		}else
			Reporter_ServiceNow.reportStep("The default list of values does not match with the Priority field","FAILURE");			

		return this;
	}
	
	public CreateNewPage verifyDefaultSelectedRisk(String risk){
		if (getDefaultValueByXpath("CHN_Risk_Xpath").contains(risk))
			Reporter_ServiceNow.reportStep("The value: "+risk+" is the default value in Risk field","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value: "+risk+" is not the default value in Risk field","FAILURE");
		return this;
	}

	public CreateNewPage verifylistofRisk(){

		String[] expectedValues = {"-- None --","Very High","High","Moderate","Low","None"};		

		if(verifyListContents("CHN_Risk_Xpath", expectedValues))
		{				
			Reporter_ServiceNow.reportStep("The value: "+convertStringArrayToString(expectedValues)+" are the list of values in Risk field","SUCCESS");

		}else
			Reporter_ServiceNow.reportStep("The default list of values does not match with the Risk field","FAILURE");			

		return this;
	}
	
	public CreateNewPage verifyDefaultSelectedImpact(String impact){
		if (getDefaultValueByXpath("CHN_Impact_Xpath").contains(impact))
			Reporter_ServiceNow.reportStep("The value: "+impact+" is the default value in Impact field","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value: "+impact+" is not the default value in Impact field","FAILURE");
		return this;
	}

	public CreateNewPage verifylistofimpact(){

		String[] expectedValues = {"1 - High","2 - Medium","3 - Low"};		

		if(verifyListContents("CHN_Impact_Xpath", expectedValues))
		{				
			Reporter_ServiceNow.reportStep("The value: "+convertStringArrayToString(expectedValues)+" are the list of values in Impact field","SUCCESS");

		}else
			Reporter_ServiceNow.reportStep("The default list of values does not match with the Impact field","FAILURE");			

		return this;
	}
	
	public CreateNewPage verifyDefaultSelectedCategory(String category){
		if (getDefaultValueByXpath("CHN_Category_Xpath").contains(category))
			Reporter_ServiceNow.reportStep("The value: "+category+" is the default value in Category field","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value: "+category+" is not the default value in Category field","FAILURE");
		return this;
	}

	public CreateNewPage verifylistofCategory(){

		String[] expectedValues = {"-- None --","Hardware","Software","Business Service",
				"System Software","Applications Software","Network","Telecom","Documentation","Other"};		

		if(verifyListContents("CHN_Category_Xpath", expectedValues))
		{				
			Reporter_ServiceNow.reportStep("The value: "+convertStringArrayToString(expectedValues)+" are the list of values in Category field","SUCCESS");

		}else
			Reporter_ServiceNow.reportStep("The default list of values does not match with the Category field","FAILURE");			

		return this;
	}
	
	public CreateNewPage clickSubmit(){

		if(clickById("Submit_Id"))	
			Reporter_ServiceNow.reportStep("Submit Button is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Submit Button could not be clicked", "FAILURE");
		return this;
	}
	
	
	private String number;
	
	public String getNumber() {
		System.out.println(getAttributeByXpath("CHN_Number_Xpath", "value"));
		number = getAttributeByXpath("CHN_Number_Xpath", "value");
		if(number.equals(""))
			Reporter_ServiceNow.reportStep("The number is blank for newly created CMDB Task", "FAILURE");
		return number;
	}
	
	public CreateNewPage clickLinkname(String Link){

		if(clickLink(Link,false))	
			Reporter_ServiceNow.reportStep("Submit Button is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Submit Button could not be clicked", "FAILURE");
		return this;
	}	
	
	public CreateNewPage verifylistofChangeTaskState(){

		String[] expectedValues = {"Pending","Open","Work in Progress","Closed Complete"
				,"Closed Incomplete","Closed Skipped"};		

		if(verifyListContents("CHN_StateStatus_Xpath", expectedValues))
		{				
			Reporter_ServiceNow.reportStep("The value: "+convertStringArrayToString(expectedValues)+" are the list of values in State field","SUCCESS");

		}else
			Reporter_ServiceNow.reportStep("The default list of values does not match with the State field","FAILURE");			

		return this;
	}
	public CreateNewPage clickNormalType() {

		if(clickLink("CHN_ChangeTypeNormal_Link"))
			Reporter_ServiceNow.reportStep("The Normal Change type is clicked sucessfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Normal Change type is not clicked or not found.","FAILURE");

		return this;
	}
	public CreateNewPage clickEmergencyType() {

		if(clickLink("CHN_ChangeTypeEmergency_Link"))
			Reporter_ServiceNow.reportStep("The Emergency Change type is clicked sucessfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Emergency Change type is not clicked or not found.","FAILURE");

		return this;
	}
	public CreateNewPage clickStandardType() {

		if(clickLink("CHN_ChangeTypeStandard_Link"))
			Reporter_ServiceNow.reportStep("The Standard Change type is clicked sucessfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Standard Change type is not clicked or not found.","FAILURE");

		return this;
	}
}




