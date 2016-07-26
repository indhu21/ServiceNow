package pages_OutOfBox;


import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class ChangeRequestPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public ChangeRequestPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("Back_Xpath")) {
			//			Reporter_ServiceNow.reportStep("This is not the Create New page", "FAILURE");
		}
	}

	public ChangeRequestPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}	

	public ChangeRequestPage verifyAllMandatoryFields(){

		// you need to change the mandatory fields when the application changes
		String[] mandatoryFields = {"CHN_ManRequestedToCT_Xpath","CHN_ManConfigItemCT_Xpath",
				"CHN_ManAssignmentGroupCT_Xpath",
		"CHN_ManAssignedToCT_Xpath"};

		String[] mandatoryFieldsDesc = {"Requested by","Configuration Item",
				"Assignment Group","Assigned To"};

		verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);
		return this;

	}

	public ChangeRequestPage selectType(String type) {

		if (!selectByVisibleTextByXpath("CHN_Type_Xpath", type))
			Reporter_ServiceNow.reportStep("The value: "+type+" could not Selected in Type field",
					"FAILURE");
		return this;

	}

	public ChangeRequestPage createChangeRequest(String configItem, String assignmentGroup, String assignedTo, String priority, String risk, String impact) {

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

	public ChangeRequestPage verifyChangeRequest(String category, String assignmentGroup, String assignedTo, String priority, String risk, String impact) {

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
			Reporter_ServiceNow.reportStep("The value: "+impact+" is not the default value in risk field",
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

		if (!clickByXpath("CHN_Save_Xpath"))
			Reporter_ServiceNow.reportStep("The  "+assignmentGroup+", "+assignedTo+", "+priority+" , "+risk+" , "+impact+" are entered and Saved successfully",
					"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The save button could not been clicked", "FAILURE");
		return this;

	}

	public ChangeRequestPage rightClickonHeader(){

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

	public ChangeRequestPage verifyRequestApprovalNotAvailable(){
		// Verify the tabs exists
		if(IsElementNotPresentByXpath("CHN_RequestApproval_Xpath"))	

			Reporter_ServiceNow.reportStep("Request Approval button is not available as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Request Approval button is available ", "FAILURE");
		return this;
	}

	public ChangeRequestPage clickCreatedChangeRequest(){
		// Verify the tabs exists
		scrollToElementByXpath("CHN_CreatedCHNRequest_Xpath");

		if(clickByXpath("CHN_CreatedCHNRequest_Xpath"))	
			Reporter_ServiceNow.reportStep("Newly Created Change Request is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Created Change Request could not be clicked", "FAILURE");
		return this;
	}

	public ChangeRequestPage verifyState(String state){
		// Verify the tabs exists
		if(getDefaultValueByXpath("CHN_StateStatus_Xpath").contains(state))
			Reporter_ServiceNow.reportStep("Routine change task is "+state+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Routine change task is "+state+"", "FAILURE");
		return this;
	}

	public ChangeRequestPage clickCloseTask(){
		// Verify the tabs exists
		if(clickByXpath("CHN_CloseTask_Xpath"))

			Reporter_ServiceNow.reportStep("Close task is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Close task could not be clicked", "FAILURE");
		return this;
	}

	public ChangeRequestPage enterConfigItemAssignmentGroupAssignedTo(String configItem, String assignmentGroup, String assignedTo){
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
	public ChangeRequestPage rightClickHeaderAndClickHistory(){

		if(!rightClickByXpath("CHN_Header_Xpath"))		
			Reporter_ServiceNow.reportStep("Right click could not be clicked ", "FAILURE");
		if(!clickByXpath("CHN_HeaderHistory_Xpath"))
			Reporter_ServiceNow.reportStep("The History is not clicked", "FAILURE");
		return this;
	}

	public ChangeRequestPage verifyHistoryCalendarList(){

		rightClickHeaderAndClickHistory();
		if(isExistByXpath("CHN_HistoryCalendar_Xpath"))
			Reporter_ServiceNow.reportStep("The Value Calendar is displayed under History as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value Calendar is not displayed under History, hence failure.", "FAILURE");

		if(isExistByXpath("CHN_HistoryList_Xpath"))
			Reporter_ServiceNow.reportStep("The Value List is displayed under History as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value List is not displayed under History, hence failure.", "FAILURE");

		return this;
	}
	public ChangeRequestPage verifyUrl(String url){

		if(!rightClickByXpath("CHN_Header_Xpath"))		
			Reporter_ServiceNow.reportStep("Right click could not be clicked ", "FAILURE");
		if(!clickByXpath("CHN_CopyUrl_Xpath"))
			Reporter_ServiceNow.reportStep("The Copy Url is not clicked", "FAILURE");


		//				String urltext = getTextByXpath("CHN_URL_Xpath");
		//				System.out.println(urltext);
		String text=getTextAndAcceptAlert();
		System.out.println(text);

		String urlOfSysId="gcm.addHref(\"Copy URL\", \"copyToClipboard('";
		System.out.println(urlOfSysId);
		String endOfUrl=");\");";
		System.out.println(endOfUrl);

		getTextAndAcceptAlert();

		String src = driver.getPageSource();

		//		src.substring(src.indexOf(urlOfSysId)+urlOfSysId.length(),src.indexOf(endOfUrl));
		System.out.println(src);


		//		System.out.println(text);
		if(src.contains(url)){
			alertAccept();
			Reporter_ServiceNow.reportStep("The Value: "+url+" is displayed under Copy Url as expected.", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The Value: "+url+" is not displayed under Copy Url, hence failure.", "FAILURE");

		return this;
	}

	public ChangeRequestPage CreateAndVerifyProblems(String configItem, String assignmentGroup, String assignedTo){

		scrollToElementByXpath("CHN_ProblemsTabNew_Xpath");

		if(clickByXpath("CHN_ProblemsTabNew_Xpath"))
			Reporter_ServiceNow.reportStep("The New Button under Problems Tab is clicked successfully and The New Problem page opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The New Button under Problems Tab is not clicked.", "FAILURE");

		String text=getAttributeByXpath("CHN_ProblemsNumber_Xpath", "value");

		if (!enterAndChoose("CHN_ProblemsConfigItem_Xpath", configItem))
			Reporter_ServiceNow.reportStep("The value: "+configItem+" could not entered in Configuration Item field", "FAILURE");

		if (!enterAndChoose("CHN_ProblemsAssignmentGroup_Xpath", assignmentGroup))
			Reporter_ServiceNow.reportStep("The value: "+assignmentGroup+"  could not be entered in Assignment Group field", "FAILURE");

		if (!enterAndChoose("CHN_ProblemsAssignedTo_Xpath",assignedTo))
			Reporter_ServiceNow.reportStep("The value: "+assignedTo+" could not be entered in Assigned To field", "FAILURE");

		if(clickByXpath("Submit_Xpath"))
			Reporter_ServiceNow.reportStep("The Values Configuration Item: "+configItem+", Assignment Group: "+assignmentGroup+", AssignTo: "+assignedTo+" entered in restecive fields and The submit Button is clicked successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Submit Button is not clicked or not clicked.", "FAILURE");

		scrollToElementByXpath("CHN_ProblemsTabNew_Xpath");

		if(!clickByXpath("CHN_ProblemsTabSortNumber_Xpath"))
			Reporter_ServiceNow.reportStep("The Number is not clicked or not found.","FAILURE");

		if(!isExistByXpath("CHN_ProblemsTabNumberSortIcon_Xpath")){
			clickByXpath("CHN_ProblemsTabSortNumber_Xpath");}

		if(getTextByXpath("CHN_ProblemsTabSortFirstNumber_Xpath").equals(text)){
			verifycolumnValue("State", "Open");
			Reporter_ServiceNow.reportStep("The Problem: "+text+" is added under Problems Tab with State: Open as expected.","SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The Problem: "+text+" is not added, check snapshot.","FAILURE");

		return new ChangeRequestPage(driver);
	}

	public ChangeRequestPage verifycolumnValue(String colName, String value) {


		int column=getColumnIndex("CHN_ProblemTableHead_Xpath", colName);

		String text=getTextByXpath("//h1[text()='Problems']/following::*[@class='vt']"+"["+column+"]", false);

		scrollToElementByXpath("CHN_ProblemTableHead_Xpath");

		//	System.out.println(getTextByXpath("(//*[@class='vt'])"+"["+column+"]", false));
		if(!text.equals(value))
			Reporter_ServiceNow.reportStep("The "+colName+" value: "+value+" is not matched, check snapshot.", "FAILURE");


		return this;

	}
	public ChangeRequestPage clickNormalType() {

		if(clickLink("CHN_ChangeTypeNormal_Link"))
			Reporter_ServiceNow.reportStep("The Normal Change type is clicked sucessfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Normal Change type is not clicked or not found.","FAILURE");

		return this;
	}
	public ChangeRequestPage clickEmergencyType() {

		if(clickLink("CHN_ChangeTypeEmergency_Link"))
			Reporter_ServiceNow.reportStep("The Emergency Change type is clicked sucessfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Emergency Change type is not clicked or not found.","FAILURE");

		return this;
	}
	public ChangeRequestPage clickStandardType() {

		if(clickLink("CHN_ChangeTypeStandard_Link"))
			Reporter_ServiceNow.reportStep("The Standard Change type is clicked sucessfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Standard Change type is not clicked or not found.","FAILURE");

		return this;
	}
	public ChangeRequestPage clickCreatedChangeRequestPlan(){

		Wait(5000);
		// Verify the tabs exists
		scrollToElementByXpath("CHN_CreatedCHNRequestPlan_Xpath");

		if(clickByXpath("CHN_CreatedCHNRequestPlan_Xpath"))	
			Reporter_ServiceNow.reportStep("Newly Created Plan Task is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Created Plan Task could not be clicked or not found.", "FAILURE");


		return this;
	}


	public ChangeRequestPage clickCreatedChangeRequestBuild(){
		// Verify the tabs exists
		Wait(5000);
		scrollToElementByXpath("CHN_CreatedCHNRequestBuild_Xpath");

		if(clickByXpath("CHN_CreatedCHNRequestBuild_Xpath"))	
			Reporter_ServiceNow.reportStep("Newly Created Build Task is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Created Build Task could not be clicked", "FAILURE");
		return this;
	}

	public ChangeRequestPage clickCreatedChangeRequestTest(){
		// Verify the tabs exists
		Wait(5000);
		scrollToElementByXpath("CHN_CreatedCHNRequestTest_Xpath");

		if(clickByXpath("CHN_CreatedCHNRequestTest_Xpath"))	
			Reporter_ServiceNow.reportStep("Newly Created Test Task is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Created Test Task could not be clicked", "FAILURE");
		return this;
	}
	public ChangeRequestPage clickback(){
		// Verify the tabs exists
		if(!clickByXpath("Back_Xpath"))
			Reporter_ServiceNow.reportStep("Back Button could not be clicked", "FAILURE");
		return this;
	}
	public ChangeRequestPage clickCreatedChangeRequestImplementation(){
		// Verify the tabs exists
		Wait(5000);
		scrollToElementByXpath("CHN_CreatedCHNRequestImplementation_Xpath");

		if(clickByXpath("CHN_CreatedCHNRequestImplementation_Xpath"))	
			Reporter_ServiceNow.reportStep("Newly Created Implementation Task is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Created Implementation Task could not be clicked", "FAILURE");
		return this;
	}

	public ChangeRequestPage clickNetworkStandardChanges() {

		if(clickLink("CHN_NetworkStandardChanges_Link"))
			Reporter_ServiceNow.reportStep("The Network Standard Changes is clicked sucessfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Network Standard Changes is not clicked or not found.","FAILURE");

		return this;
	}
	public ChangeRequestPage clickAddNewWork() {

		if(clickLink("CHN_AddNetwork_Link"))
			Reporter_ServiceNow.reportStep("The Add network switch to datacenter cabinet is clicked sucessfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Add network switch to datacenter cabinet is not clicked or not found.","FAILURE");

		return this;
	}

}






