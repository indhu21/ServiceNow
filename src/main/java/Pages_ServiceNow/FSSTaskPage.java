package Pages_ServiceNow;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.ColorUtils;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class FSSTaskPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public FSSTaskPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("FSS_FSSTasks_Number_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not the FSS Task page", "FAILURE");
		}
	}

	public FSSTaskPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}

	public FSSTaskPage enterWorkNotes(String workNotes)
	{

		if(enterByXpath("FSS_FSSTasks_WorkNotes_Xpath", workNotes))
			Reporter_ServiceNow.reportStep("The value:"+workNotes+" is entered in Work Notes field as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Comments could not be enter in Work Notes, hence failure", "FAILURE");

		return this;
	}

	public FSSTaskPage verifyLatestWorkNotes(String workNotes)
	{
		scrollToElementByXpath("FSS_FSSTasks_LatestWorkNotes_Xpath");

		if((getTextByXpath("FSS_FSSTasks_LatestWorkNotes_Xpath")).contains(workNotes))
			Reporter_ServiceNow.reportStep("The Values: "+workNotes+" does exist in Latest Work Notes as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Values: "+workNotes+" does not exist in Latest Work Notes, hence failure..", "FAILURE");

		return this;
	}

	public FSSTaskPage clickSaveButton()
	{

		if((clickById("Save_Id")))
			Reporter_ServiceNow.reportStep("The Save Button is clicked Successfully and Task saved as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Save Button not found or clicked.", "FAILURE");

		return this;
	}

	public FSSTaskPage clickNotesTab()
	{

		if((!clickByXpath("FSS_FSSTasks_Notes_Xpath")))
			Reporter_ServiceNow.reportStep("The Notes Tab not found or clicked.", "FAILURE");

		return this;
	}

	//Updated by Sneha on 22-12-2015



	public FSSTaskPage enterAffectedUser(String affectedUser)
	{
		if(enterByXpath("FSS_FSSTasks_AffectedUser_Xpath", affectedUser))
			Reporter_ServiceNow.reportStep("The Value: "+affectedUser+" is entered in Affected User field as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("User not able to select the Affected User field", "FAILURE");
		Wait(2000);
		return this;

	}

	public FSSTaskPage enterCategoryTypeItem(String category, String type, String item)
	{
		if(!enterAndChoose("FSS_FSSTasks_Category_Xpath", category))
			Reporter_ServiceNow.reportStep("The User not able to enter the Category field", "FAILURE");
		
		if(!enterAndChoose("FSS_FSSTasks_Type_Xpath", type))
			Reporter_ServiceNow.reportStep("The User not able to select the Service Type field", "FAILURE");
			
		if(enterAndChoose("FSS_FSSTasks_Item_Xpath", item))
			Reporter_ServiceNow.reportStep("The Values Category: "+category+", Service Type: "+type+", Service Item: "+item+" are entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("User not able to select the Service Item field", "FAILURE");
		Wait(2000);
		return this;

	}

	public FSSTaskPage enterShortDescription(String shortDescription)
	{
		if(enterByXpath("FSS_FSSTasks_ShortDescription_Xpath", shortDescription))
			Reporter_ServiceNow.reportStep("The Value: "+shortDescription+" is entered in Short Description field as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("User not able to enter the Short Description field", "FAILURE");
		Wait(3000);
		return this;

	}

	public FSSTaskPage enterInvoiceNumber(String num)
	{
		if(enterByXpath("FSS_FSSTasks_InvoiceNumber_Xpath", num))
			Reporter_ServiceNow.reportStep("The Value: "+num+" is entered in Invoice Number field as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("User not able to enter the Invoice Number field", "FAILURE");
		Wait(2000);
		return this;

	}

	public ListPage clickSubmit()
	{
		if(clickById("FSS_FSSTasks_SubmitButton_Id"))
			Reporter_ServiceNow.reportStep("The Submit Button is clicked and The ticket created successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("User not able to click Submit Button", "FAILURE");

		return new ListPage(driver);
	}

//	public FSSTaskPage validateFSSTaskNumber(String incNumber)
//	{
//		String searchedNumber=getAttributeByXpath("FSS_FSSTasks_SearchedNumberAPO2_Xpath","value");
//		System.out.println(searchedNumber);
//
//		if(incNumber.equalsIgnoreCase(searchedNumber))
//			Reporter_ServiceNow.reportStep("The new FSS task "+incNumber+" is saved successfully", "SUCCESS");
//		else
//			Reporter_ServiceNow.reportStep("User not able to create new FSS task", "FAILURE");
//
//		return this;
//	}

	public FSSTaskPage clickActivityLogTab()
	{

		if(clickByXpath("FSS_FSSTasks_ActivityLog_Xpath"))
			Reporter_ServiceNow.reportStep("The Activity Log Tab clicked successfully and The Activity Log tab opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Activity Log tab is not clicked or not found.", "FAILURE");

		return this;
	}


	public FSSTaskPage verifyCommentsInActivityLogTab(String comments, String user)
	{

		scrollToElementByXpath("FSS_FSSTasks_CustomerComms_Xpath");

		if(getTextByXpath("FSS_FSSTasks_CustomerComms_Xpath").contains(comments))
			Reporter_ServiceNow.reportStep("The Comments: "+comments+" enetered by the user: "+user+" matched as expected. ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The comments could not not be matched", "SUCCESS");

		return this;
	}

	public FSSTaskPage clickCustomerWatchList()
	{

		if(clickByXpath("FSS_FSSTasks_CustomerWatchlist_Xpath"))
			Reporter_ServiceNow.reportStep("The Customer Watchlist clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Customer Watchlist is not clicked or not found.", "FAILURE");

		return this;
	}

	public FSSTaskPage enterCustomerWatchList(String customerWatchlist)
	{

		if(enterAndChoose("FSS_FSSTasks_EditCustomerWatchlist_Xpath", customerWatchlist))
			Reporter_ServiceNow.reportStep("The End User"+customerWatchlist+" is added to Customer Watchlist as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The End User is not entered, hence failure.", "FAILURE");

		return this;
	}

	public FSSTaskPage clickLock(){

		if(clickByXpath("FSS_FSSTasks_Lock_Xpath"))

			Reporter_ServiceNow.reportStep("The Lock Button is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Lock Button is not clicked or not found.", "FAILURE");

		return this;
	}

	public FSSTaskPage verifyCustomerWatchList(String user){

		scrollToElementByXpath("FSS_CustomerNonEditWatchlist_Xpath");

		if(getTextByXpath("FSS_CustomerNonEditWatchlist_Xpath").contains(user))

			Reporter_ServiceNow.reportStep("The user:"+user+" does exist in customer watch list as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The user:"+user+" does not exist in customer watch list..", "FAILURE");

		return this;
	}
	
	public FSSTaskPage upgradePriority(String value)
	{

		String priority=getDefaultValueByXpath("FSS_MySPARC_Priority_Xpath");

		if(selectByVisibleTextByXpath("FSS_MySPARC_Priority_Xpath", value))
			Reporter_ServiceNow.reportStep("The Priority value: "+value+" is upgraded from "+priority+" as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Priority value is not upgrade, check snapshot.", "FAILURE");

		return this;
	}

	public ListPage clickUpdateButton()
	{

		if((clickById("Update_Button")))
			Reporter_ServiceNow.reportStep("The Update Button is clicked Successfully and Task updated as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Update Button not found or clicked.", "FAILURE");

		return new ListPage(driver);
	}

	public FSSTaskPage updateState(String value)
	{

		String state=getDefaultValueByXpath("FSS_MyGroupsWork_State_Xpath");

		if(selectByVisibleTextByXpath("FSS_MyGroupsWork_State_Xpath", value))
			Reporter_ServiceNow.reportStep("The State value: "+value+" is updated from "+state+" as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The State value is not update, check snapshot.", "FAILURE");

		return this;
	}

	public FSSTaskPage updateAssignmentGroup(String value)
	{

		String assGroup=getAttributeByXpath("FSS_MyGroupsWork_AssignmentGroup_Xpath", "value");

		if(enterAndChoose("FSS_MyGroupsWork_AssignmentGroup_Xpath", value))
			Reporter_ServiceNow.reportStep("The Assignment Group value: "+value+" is updated from "+assGroup+" as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Assignment Group value is not update, check snapshot.", "FAILURE");

		return this;
	}

	public FSSTaskPage verifyAllMandatoryFields(){

		// you need to change the mandatory fields when the application changes
		String[] mandatoryFields = {"FSS_MyGroupsWork_ClosureCode_Xpath",
		"FSS_FSSTasks_CustomerComments_Xpath"};

		String[] mandatoryFieldsDesc = {"Closure Code",
		"Customer Comments"};

		verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);
		return this;

	}
	public FSSTaskPage enterCustomerComments(String coments)
	{

		if(enterByXpath("FSS_FSSTasks_CustomerComments_Xpath", coments))
			Reporter_ServiceNow.reportStep("The Customer comments "+coments+" is entered successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Customer comments "+coments+" is not entered or not found", "FAILURE");
		return this;
	}

	public FSSTaskPage verifyLatestCustomerComments(String coments)
	{

		scrollToElementByXpath("FSS_FSSTasks_LatestCustomerComments_Xpath");

		if((getTextByXpath("FSS_FSSTasks_LatestCustomerComments_Xpath")).contains(coments))
			Reporter_ServiceNow.reportStep("The Values: "+coments+" does exist in Latest Customer Comments as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Values: "+coments+" does not exist in Latest Customer Comments, hence failure..", "FAILURE");

		return this;
	}
	
	public FSSTaskPage selectClosureCode(String coments)
	{

		if(selectByVisibleTextByXpath("FSS_MyGroupsWork_ClosureCode_Xpath", coments))
			Reporter_ServiceNow.reportStep("The Closure code "+coments+" is selected successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Closure code "+coments+" is not selected or not found", "FAILURE");
		return this;
	}
	public FSSTaskPage clickClosureTab()
	{

		if((!clickByXpath("FSS_ClosureTab_Xpath")))
			Reporter_ServiceNow.reportStep("The Notes Tab not found or clicked.", "FAILURE");

		return this;
	}
	public MenuPage navigateToLoin()
	{
		getDriver().get("https://sparctest.service-now.com");
		return new MenuPage(driver);
	}

	
}