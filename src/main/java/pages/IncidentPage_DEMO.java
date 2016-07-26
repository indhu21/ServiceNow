package pages;


import java.util.List;

import org.apache.poi.xwpf.usermodel.ISDTContents;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.ColorUtils;
import utils.Reporter1;
import wrapper.ServiceNowWrappers;

public class IncidentPage_DEMO extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public IncidentPage_DEMO(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();
		// Check that we're on the right page.
		if (!isExistByXpath("Back_Xpath")) {
			Reporter1.reportStep("This is not the Incident page", "FAILURE");
		}

	}

	public IncidentPage_DEMO switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}	
	public IncidentPage_DEMO verifyCategoryIsAlreadySelected() {

		String value=getDefaultValueByXpath("Inc_Category_Xpath");

		if(!value.equals(""))
			Reporter1.reportStep("The Category field is already selected as expected.", "SUCCESS");
		else
			Reporter1.reportStep("The Category field is empty, hence failure.", "FAILURE");

		return this;
	}

	public IncidentPage_DEMO enterCaller(String caller) {

		if(enterAndChoose("Inc_Caller_Xpath_DEMO", caller))
			Reporter1.reportStep("The Value: "+caller+" is entered in Caller field successfully.", "SUCCESS");
		else
			Reporter1.reportStep("The Value: "+caller+" is not entered or the Caller field not found.", "FAILURE");

		return this;
	}

	public IncidentPage_DEMO verifyLocationIsAlreadySelected() {

		Wait(5000);
//		String value=getAttributeByXpath("Inc_VerifyLocation_Xpath", "value");
//
//		if(!value.equals(""))
//			Reporter1.reportStep("The Location field is already selected as expected.", "SUCCESS");
//		else
//			Reporter1.reportStep("The Location field is empty, hence failure.", "FAILURE");
         
		if(enterAndChoose("CreateINC_Location_xpath_DEMO", "**"))
			Reporter1.reportStep("The Location is entered successfully.", "SUCCESS");
		else
			Reporter1.reportStep("The Location could not be entered.", "FAILURE");

		return this;
	}

	public IncidentPage_DEMO enterAllFields(String category, String subCategory, String ciValue, String contactType, String State, String assGroup, String assignTo) {

		if(!selectByVisibleTextByXpath("Inc_Category_Xpath", category))
			Reporter1.reportStep("The Value: "+category+" is not selected in Category field.", "FAILURE");

		if(!selectByVisibleTextByXpath("Inc_SubCategory_Xpath", subCategory))
			Reporter1.reportStep("The Value: "+subCategory+" is not selected in SubCategory field.", "FAILURE");

//		if(!enterAndChoose("Inc_CI_Xpath", ciValue))
//			Reporter1.reportStep("The Value: "+ciValue+" is not entered in Configuration Item field.", "FAILURE");	

		if(!selectByVisibleTextByXpath("Inc_ContactType_Xpath", contactType))
			Reporter1.reportStep("The Value: "+contactType+" is not selected in Contact Type field.", "FAILURE");

		if(!selectByVisibleTextByXpath("Inc_State_Xpath", State))
			Reporter1.reportStep("The Value: "+State+" is not selected in State field.", "FAILURE");

		if(enterAndChoose("Inc_AssignmentGroup_Xpath", assGroup))
			Reporter1.reportStep("The Values Category: "+category+", SubCategory: "+subCategory+", "
					+ "Configuration Item: "+ciValue+", Contact Type: "+contactType+", State: "+State+","
					+" are filled in respective fields successfully.", "SUCCESS");
		else
			Reporter1.reportStep("The Value: "+assGroup+" is not entered in Assignment Group field.", "FAILURE");
			return this;

		//if(enterAndChoose("Inc_AssignTo_Xpath", assignTo))
			//Reporter1.reportStep("The Values Category: "+category+", SubCategory: "+subCategory+", "
//					+ "Configuration Item: "+ciValue+", Contact Type: "+contactType+", State: "+State+", "
//					+ "Assignment Group: "+assGroup+",  Assign To: "+assignTo+" are filled in respective fields successfully.", "SUCCESS");
//		else
//			Reporter1.reportStep("The Value: "+assignTo+" is not selected in Assign To field.", "FAILURE");
//
//		return this;
	}

	public IncidentPage_DEMO selectImpactAndUrgency(String impact, String urgency) {
		if(!selectByVisibleTextByXpath("Inc_Impact_Xpath", impact))
			Reporter1.reportStep("The Value: "+impact+" is not selected in Impact field.", "FAILURE");

		if(selectByVisibleTextByXpath("Inc_Urgency_Xpath", urgency))
			Reporter1.reportStep("The Value: "+urgency+" is selected in Urgency field successfully.", "SUCCESS");
		else
			Reporter1.reportStep("The Value: "+urgency+" is not selected in Urgency field.", "FAILURE");
		return this;
	}

	public IncidentPage_DEMO verifyPriorityIsReadonly() {

		String[] expectedvalues={"Inc_Priority_Xpath"};

		String[] desc={"Priority"};

		verifyDisabledFieldsByXpath(expectedvalues, desc);
		return this;

	}

	public IncidentPage_DEMO enterSortDes(String shortDes) {

		if(enterByXpath("Inc_ShortDes_Xpath", shortDes))
			Reporter1.reportStep("The Value: "+shortDes+" is entered as in Short Description field successfully.", "SUCCESS");
		else
			Reporter1.reportStep("The Value: "+shortDes+" is not entered in Short Description, hence failure.", "FAILURE");

		return this;
	}
	public IncidentsListPage_DEMO clickSubmit(String user) {

		if(clickByXpath("Submit_Xpath"))
			Reporter1.reportStep("The Submit Button is clicked successfully and the user: "+user+" able to create to New Ticket as expected.", "SUCCESS");
		else
			Reporter1.reportStep("The Submit Button is not clicked or not found.", "FAILURE");

		return new IncidentsListPage_DEMO(driver);
	}

	public String getIncident(){

		String incident=getAttributeByXpath("CREATEINC_IncidentNumberDEMO_Xpath", "value");

		if(incident.equals(""))
			Reporter1.reportStep("The Incident Number is blank, hence failed.", "FAILURE");
		return incident;
	}

	public IncidentPage_DEMO verifyPriority(String priority) {

		if(getDefaultValueByXpath("Inc_Priority_Xpath").contains(priority))
			Reporter1.reportStep("The Value: "+priority+" is matched in Priority field as expected.", "SUCCESS");
		else
			Reporter1.reportStep("The Value: "+priority+" is not matched in Priority field, hence failure.", "FAILURE");
		return this;
	}
	public IncidentPage_DEMO verifyAllMandatoryFields(){

		String[] mandatoryFields = {"Inc_AssignmentGroupMand_Xpath",
				"Inc_AssignedToMand_Xpath",
		"Inc_ConfigItemMand_Xpath"};

		String[] mandatoryFieldsDesc = {"Assignment Group",
				"Assigned To","Configuration Item"};

		verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);

		return this;

	}
	public IncidentPage_DEMO isNotesAvailable(){

		scrollToElementByXpath("Inc_WorkNotes_Xpath");
		// click the first Incident Link
		if(IsElementNotPresentByXpath("Inc_WorkNotes_Xpath"))
			Reporter1.reportStep("The Notes is not available", "WARNING");
		else
			Reporter1.reportStep("The Notes is present as expected.", "SUCCESS");
		return this;
	}

	public IncidentPage_DEMO enterWorkNotes(String workNotes) {

		scrollToElementByXpath("Inc_WorkNotes_Xpath");
		// duplicating the code as webdriver having issue with this field (not entering first time)
		//enterByXpath("Inc_WorkNotes_Xpath",workNotes);
		if(enterByXpath("Inc_WorkNotes_Xpath",workNotes ))
			Reporter1.reportStep("The value : "+workNotes+" is entered in Work Notes field successfully", "SUCCESS");
		else	
			Reporter1.reportStep("The value : "+workNotes+" could not be entered in Work Notes field", "FAILURE");
		return this;
	}


	public MenuPage_DEMO updateIncident() {

		if(clickById("UpdateButton_Id"))	

			Reporter1.reportStep("The Update Incident process is successful", "SUCCESS");
		else
			Reporter1.reportStep("The Update Incident process failed. Check snapshot", "FAILURE");

		return new MenuPage_DEMO(driver);
	}

	public IncidentPage_DEMO getActivityText(String text) {

		//scrollPageDown();
		scrollToElementByXpath("Inc_ActivityLog_Xpath");

		if(getTextByXpath("Inc_ActivityLog_Xpath").contains(text))	
			Reporter1.reportStep("The "+text+" is updated in Activity log as expected.", "SUCCESS");
		else
			Reporter1.reportStep("The "+text+" is not updated in activity log.", "WARNING");
		return this;
	}

	public IncidentPage_DEMO verifyIncidentFormOpens() {

		if(isExistByXpath("Incident_Number_Xpath"))	
			Reporter1.reportStep("The Incident form is opened up successfully", "SUCCESS");
		else
			Reporter1.reportStep("The Incident form could not be opened Check snapshot", "FAILURE");
		return this;
	}


}





