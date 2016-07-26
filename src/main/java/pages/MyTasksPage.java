package pages;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class MyTasksPage extends ServiceNowWrappers{


	private final RemoteWebDriver driver;
	public static String taskNumber="";

	public MyTasksPage(RemoteWebDriver driver) {
		this.driver = driver;
		switchToMainFrame();

	}
	public MyTasksPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");
		return this;
	}

	public MyTasksPage checkMyTaskFields(String assTo, String state) {

		if(!getAttributeById("MyTasks_Number_Id", "value").equals(""))
			Reporter.reportStep("The Number field is empty, hence failure", "FAILURE");

		if(!getAttributeById("MyTasks_AssignedTo_Id", "value").equals(assTo))
			Reporter.reportStep("The Assign To field is not matched with "+assTo+", hence failure", "FAILURE");

		if(!getDefaultValueById("MyTasks_AssignedTo_Id").equals(state))
			Reporter.reportStep("The State field is not matched with "+state+", hence failure", "FAILURE");
		
		if(isExistByXpath("MyTasks_CertificationTasks_Xpath"))
			Reporter.reportStep("The Number should populated, Assigned To and State values mathched with "+assTo+", "
					+ ""+state+"and Certification Task displayed successfully.", "SUCCESS");
		else
			Reporter.reportStep("The Certification Task is not displayed, hence failure", "FAILURE");

		return this;

	}
	public MyTasksPage enterWorkNotes(String workNotes) {

		if(enterById("MyTasks_WorkNotes_Id", workNotes))
			Reporter.reportStep("The WorkNotes value "+workNotes+" is entered successfully.", "SUCCESS");
		else
			Reporter.reportStep("The WorkNotes value "+workNotes+" is not entered, hence failure", "FAILURE");

		return this;
	}
	public ListPage clickUpdateButton() {

		if(clickById("Update_Button"))
			Reporter.reportStep("The Update Button is clicked successfully.", "SUCCESS");
		else
			Reporter.reportStep("The Update Button is not clicked, hence failure", "FAILURE");

		return new ListPage(driver);
	}

	public MyTasksPage getTaskNumber() {

		taskNumber=getAttributeById("MyTasks_Number_Id", "value");

		if(!taskNumber.endsWith(""))
			Reporter.reportStep("The Task Number is blank, hence failure", "FAILURE");

		return this;
	}
	public MyTasksPage clickClosedInCompleteAmdVerifyError() {
		
		if(!clickById("MyTasks_ClosedIncomplete_Id"))
			Reporter.reportStep("The Close Incomplete is not clicked, hence failure", "FAILURE");

		if(getTextById("ATMe_ErrorMessage_Xpath").contains("Provide a reason for closing incomplete in work notes"))
			Reporter.reportStep("The Close Incomplete is clicked and Error message is displayed as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Error messgae is not displayed, hence failure", "FAILURE");
	
		
			return this;
	}
	
	public ListPage clickClosedInComplete() {
		
		if(clickById("MyTasks_ClosedIncomplete_Id"))
			Reporter.reportStep("The Close Incomplete is clicked successfully.", "SUCCESS");
		else
			Reporter.reportStep("The Close Incomplete is not clicked , hence failure", "FAILURE");
			
		return new ListPage(driver);
	}
	
	public ListPage verifyState(String state) {
		
		if(getDefaultValueById("MyTasks_AssignedTo_Id").equals(state))
			Reporter.reportStep("The State is matched with "+state+" as expected.", "SUCCESS");
		else
			Reporter.reportStep("The State field is not matched with "+state+", hence failure", "FAILURE");
			
		return new ListPage(driver);
	}

	
}



