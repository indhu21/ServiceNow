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
import utils.Reporter;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class IncidentPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public IncidentPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("Back_Xpath")) {
//			Reporter_ServiceNow.reportStep("This is not the Incident page", "FAILURE");
		}
	}

	public IncidentPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}	

	public IncidentPage isNotesAvailable(){

		// click the first Incident Link
		if(IsElementNotPresentByXpath("CREATEINC_WorkNotes_Xpath"))
			Reporter_ServiceNow.reportStep("The Notes is not available", "WARNING");
		else
			Reporter_ServiceNow.reportStep("The Notes is present", "SUCCESS");
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


	public MenuPage updateIncident( String ciValue) {
		
		if(!enterAndChoose("Inc_CI_Xpath", ciValue))
			Reporter_ServiceNow.reportStep("The Value: "+ciValue+" is not entered in Configuration Item field.", "FAILURE");	


		if(clickById("Update_Button"))	
		{
			
			Reporter_ServiceNow.reportStep("The Update Incident process is successful", "SUCCESS");
		}
		else
			Reporter_ServiceNow.reportStep("The Update Incident process failed. Check snapshot", "FAILURE");


		return new MenuPage(driver);
	}

	public IncidentPage getActivityText(String text) {

		//scrollPageDown();
		scrollToElementByXpath("INC_ActivityLog_Xpath");

		if(getTextByXpath("INC_ActivityLog_Xpath").contains(text))	
			Reporter_ServiceNow.reportStep("The "+text+" is updated in Activity log as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The "+text+" is not updated in activity log.", "WARNING");
		return this;
	}

	public IncidentPage verifyIncidentFormOpens() {

		if(isExistByXpath("Incident_Number_Xpath"))	

			Reporter_ServiceNow.reportStep("The Incident form is opened up successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Incident form could not be opened Check snapshot", "FAILURE");
		return this;
	}

	public IncidentPage verifyAllReadOnlyFields(){

		// you need to change the read only fields when the application changes
		String[] readOnlyFields = {"SPARCPORTAL_IncidentNumber_Xpath","SPARCPORTAL_IncidentState_Xpath",
				"SPARCPORTAL_AffectedUserLabel_Xpath","SPARCPORTAL_ShortDesc_Xpath",
				"SPARCPORTAL_Priority_Xpath","SPARCPORTAL_ConfigurationItem_Xpath"};

		String[] readOnlyFieldsDesc = {"Number","State","Affected User","Short Description","Priority","Configuration Item"};


		// Verify read only
		verifyDisabledFieldsByXpath(readOnlyFields, readOnlyFieldsDesc);


		return this;

	}

	public IncidentPage verifyCIMap() {

		if(clickByXpath("CHN_SHOWCIMAP_Xapth"))	{

			switchToSecondWindow();
			Wait(5000);
			isExistByXpath("CHN_GraphicalRepresentation_Xapth");			
			Reporter_ServiceNow.reportStep("The Graphical representation of the CI in the Configuration management database displayed successfully", "SUCCESS");
			switchToPrimary();
		}


		else
			Reporter_ServiceNow.reportStep("The Graphical representation could not be opened", "FAILURE");
		return this;
	}

	public IncidentPage uploadFile(String fileName) {
		boolean bFail = true;

		if (clickByXpath("Attachment_Xpath")) {
			try {

				String[] folder = getAbsolutePath().substring(1).split("/");
				String folderName = folder[0];
				for (int i = 1; i < folder.length - 2; i++) {
					folderName = folderName + "/" + folder[i];
				}

				enterById("ChooseFiles_Id", folderName + "/data/" + fileName + ".xlsx");

				//				String filePath = getAbsolutePath()+ "/data/" +fileName+".xlsx";
				//				enterById("ChooseFiles_Id", filePath);

				clickByXpath("AttachFile_Xpath");
				Wait(10000);

				isExistByXpath("IsAttachmentUploaded");
				clickByXpath("CloseUpload_Xpath");

				bFail = false;
				scrollToElementByXpath("ManageAttachments_Xpath");
				Wait(1000);
				Reporter_ServiceNow.reportStep("The Attachment is Uploaded successfully ", "SUCCESS");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (bFail)
			Reporter_ServiceNow.reportStep("The Attachment could not be Uploaded  ", "FAILURE");

		return this;
	}

	public IncidentPage RemoveAttachment() {
		boolean bFail = true;
		if (clickByXpath("Attachment_Xpath")) {
			try {
				clickByXpath("SelectAttach_Xpath");
				clickByXpath("RemoveAttachment_Xpath");
				Wait(5000);
				clickByXpath("CloseUpload_Xpath");
				Wait(1000);
				bFail = false;
				//				Reporter_ServiceNow.reportStep("The Attachment is removed successfully ", "SUCCESS");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (bFail)
			Reporter_ServiceNow.reportStep("The Attachment could not be removed  ", "FAILURE");

		return this;
	}
	public CreateNewPage clickChageRequestNew() {

		if(clickByXpath("CHN_ChangeRequestNew_Xpath"))	

			Reporter_ServiceNow.reportStep("New button of Change request is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("New button of Change request could not be clicked Check snapshot", "FAILURE");
		return new CreateNewPage(driver);
	}

	public String getIncident(){

		String incident=getAttributeByXpath("Incident_Number_Xpath", "value");

		if(incident.equals(""))
			Reporter_ServiceNow.reportStep("The Incident Number is blank, hence failed.", "FAILURE");
		return incident;
	}
	public IncidentPage verifyCategoryIsAlreadySelected() {

		String value=getDefaultValueByXpath("Inc_Category_Xpath");

		if(!value.equals(""))
			Reporter_ServiceNow.reportStep("The Category field is already selected as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Category field is empty, hence failure.", "FAILURE");

		return this;
	}

	public IncidentPage enterCaller(String caller) {

		if(enterAndChoose("Inc_Caller_Xpath", caller))
			Reporter_ServiceNow.reportStep("The Value: "+caller+" is entered in Caller field successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Value: "+caller+" is not entered or the Caller field not found.", "FAILURE");

		return this;
	}

	public IncidentPage verifyLocationIsAlreadySelected() {

		Wait(5000);
		String value=getAttributeByXpath("Inc_VerifyLocation_Xpath", "value");

		if(!value.equals(""))
			Reporter_ServiceNow.reportStep("The Location field is auto populated as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Location field is empty, hence failure.", "FAILURE");

		return this;
	}

	public IncidentPage enterAllFields(String category, String subCategory, String ciValue, String contactType, String State, String assGroup, String assignTo) {

		if(!selectByVisibleTextByXpath("Inc_Category_Xpath", category))
			Reporter_ServiceNow.reportStep("The Value: "+category+" is not selected in Category field.", "FAILURE");

		if(!selectByVisibleTextByXpath("Inc_SubCategory_Xpath", subCategory))
			Reporter_ServiceNow.reportStep("The Value: "+subCategory+" is not selected in SubCategory field.", "FAILURE");

		if(!enterAndChoose("Inc_CI_Xpath", ciValue))
			Reporter_ServiceNow.reportStep("The Value: "+ciValue+" is not entered in Configuration Item field.", "FAILURE");	

		if(!selectByVisibleTextByXpath("Inc_ContactType_Xpath", contactType))
			Reporter_ServiceNow.reportStep("The Value: "+contactType+" is not selected in Contact Type field.", "FAILURE");

		if(!selectByVisibleTextByXpath("Inc_State_Xpath", State))
			Reporter_ServiceNow.reportStep("The Value: "+State+" is not selected in State field.", "FAILURE");

		if(!enterAndChoose("Inc_AssignmentGroup_Xpath", assGroup))
			Reporter_ServiceNow.reportStep("The Value: "+assGroup+" is not entered in Assignment Group field.", "FAILURE");

		if(enterAndChoose("Inc_AssignTo_Xpath", assignTo))
			Reporter_ServiceNow.reportStep("The Values Category: "+category+", SubCategory: "+subCategory+", "
					+ "Configuration Item: "+ciValue+", Contact Type: "+contactType+", State: "+State+", "
					+ "Assignment Group: "+assGroup+",  Assign To: "+assignTo+" are filled in respective fields successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Value: "+assignTo+" is not selected in Assign To field.", "FAILURE");

		return this;
	}

	public IncidentPage selectImpactAndUrgency(String impact, String urgency) {
		if(!selectByVisibleTextByXpath("Inc_Impact_Xpath", impact))
			Reporter_ServiceNow.reportStep("The Value: "+impact+" is not selected in Impact field.", "FAILURE");

		if(selectByVisibleTextByXpath("Inc_Urgency_Xpath", urgency))
			Reporter_ServiceNow.reportStep("The Value: "+urgency+" is selected in Urgency field successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Value: "+urgency+" is not selected in Urgency field.", "FAILURE");
		return this;
	}

	public IncidentPage verifyPriorityIsReadonly() {

		String[] expectedvalues={"Inc_Priority_Xpath"};

		String[] desc={"Priority"};

		verifyDisabledFieldsByXpath(expectedvalues, desc);
		return this;

	}

	public IncidentPage enterSortDes(String shortDes) {

		if(enterByXpath("Inc_ShortDes_Xpath", shortDes))
			Reporter_ServiceNow.reportStep("The Value: "+shortDes+" is entered as in Short Description field successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Value: "+shortDes+" is not entered in Short Description, hence failure.", "FAILURE");

		return this;
	}
	public IncidentsListPage clickSubmit(String user) {

		if(clickByXpath("Submit_Xpath"))
			Reporter_ServiceNow.reportStep("The Submit Button is clicked successfully and the user: "+user+" able to create New Ticket as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Submit Button is not clicked or not found.", "FAILURE");

		return new IncidentsListPage(driver);
	}

	public IncidentPage verifyPriority(String priority) {

		if(getDefaultValueByXpath("Inc_Priority_Xpath").contains(priority))
			Reporter_ServiceNow.reportStep("The Value: "+priority+" is matched in Priority field as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Value: "+priority+" is not matched in Priority field, hence failure.", "FAILURE");
		return this;
	}
	public IncidentPage verifyAllMandatoryFields(){

		String[] mandatoryFields = {"Inc_AssignmentGroupMand_Xpath",
				"Inc_AssignedToMand_Xpath",
		"Inc_ConfigItemMand_Xpath"};

		String[] mandatoryFieldsDesc = {"Assignment Group",
				"Assigned To","Configuration Item"};

		verifyMandatoryFields_K16(mandatoryFields, mandatoryFieldsDesc);

		return this;

	}
	
	/**
	 * verify the mandatory fields
	 */
	public boolean verifyMandatoryFields_K16(String[] fields, String[] desc){
		Boolean bReturn = true;
		for (int i=0; i < fields.length; i++) {
			if(!verifyAttributeTextByXpath(fields[i], "mandatory", "true")){
				bReturn = false;
				Reporter_ServiceNow.reportStep("The field :"+desc[i]+" is not displayed as mandatory. Hence Failed.","FAILURE");
			}		
		}

		if(bReturn)
			Reporter_ServiceNow.reportStep("All the fields:"+convertStringArrayToString(desc)+" are mandatory as expected","SUCCESS");
		
		return bReturn;
	}

}





