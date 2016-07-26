package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class CertificationSchedulePage extends ServiceNowWrappers{
	private final RemoteWebDriver driver;

	public CertificationSchedulePage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMain();
	}

	public CertificationSchedulePage enterName(String name) {

		if(enterById("ScheduleDefinitions_Name_Id", name))
			Reporter.reportStep("The value "+name+" is entered in the Name field successfully.", "SUCCESS");
		else
			Reporter.reportStep("The value "+name+" is not entered in the Name field, hence failure..", "FAILURE");

		return this;
	}

	public CertificationSchedulePage enterAndChooseFilter(String filter) {

		if(enterAndChoose("ScheduleDefinitions_Filter_Xpath", filter))
			Reporter.reportStep("The value "+filter+" is entered in the Filter field successfully.", "SUCCESS");
		else
			Reporter.reportStep("The value "+filter+" is not entered in the Filter field, hence failure..", "FAILURE");

		return this;
	}

	public CertificationSchedulePage clickDisplayFieldUnlock() {

		if(!clickById("ScheduleDefinitions_DisplayFieldsUnlock_Id"))
			Reporter.reportStep("The Display Fileds Unlock Button is not cliked, hence failure.", "FAILURE");

		return this;
	}

	public CertificationSchedulePage clickDisplayFieldlock() {

		if(!clickById("ScheduleDefinitions_DisplayFieldslock_Id"))
			Reporter.reportStep("The Display Fileds Lock Button is not cliked, hence failure.", "FAILURE");

		return this;
	}

	public CertificationSchedulePage selectValuesFromDisplayFields(String[] values) {

		
		for (String string : values) {
			System.out.println(string);

			if(!selectByVisibleTextById("ScheduleDefinitions_DisplayFieldsSelect_Id", string))
				Reporter.reportStep("The Display Fileds Lock Button is not cliked, hence failure.", "FAILURE");

			if(!clickByXpath("ScheduleDefinitions_DisplayFieldsRight_Xpath"))
				Reporter.reportStep("The Right Arrow Button is not cliked, hence failure.", "FAILURE");

		}
		Reporter.reportStep("The Values "+convertStringArrayToString(values)+" are selected from Display fields successfully.", "SUCCESS");

		return this;
	}

	public CertificationSchedulePage selectValuesFromCertificationFields(String[] values) {

		for (String string : values) {
			System.out.println(string);
			if(!selectByVisibleTextById("ScheduleDefinitions_CertificationFieldsSelect_Id", string))
				Reporter.reportStep("The Display Fileds Lock Button is not cliked, hence failure.", "FAILURE");

			if(!clickByXpath("ScheduleDefinitions_CertificationFieldsRight_Xpath"))
				Reporter.reportStep("The Right Arrow Button is not cliked, hence failure.", "FAILURE");

		}
		Reporter.reportStep("The Values "+convertStringArrayToString(values)+" are selected from Certification fields successfully.", "SUCCESS");

		return this;
	}
	public CertificationSchedulePage clickCertificationFieldUnlock() {

		if(!clickById("ScheduleDefinitions_CertificationFieldsUnlock_Id"))
			Reporter.reportStep("The Display Fileds Unlock Button is not cliked, hence failure.", "FAILURE");

		return this;
	}

	public CertificationSchedulePage clickCertificationFieldlock() {

		if(!clickById("ScheduleDefinitions_CertificationFieldsLock_Id"))
			Reporter.reportStep("The Display Fileds Lock Button is not cliked, hence failure.", "FAILURE");

		return this;
	}
	
	public CertificationSchedulePage selectAssignmentType(String assType) {

		if(selectByVisibleTextById("ScheduleDefinitions_AssignmentType_Id", assType))
			Reporter.reportStep("The Assignment Type selected successfully with value "+assType+".", "SUCCESS");
		else
			Reporter.reportStep("The Assignment Type value "+assType+" is not selcted, hence failure.", "FAILURE");

		return this;
	}
	
	public CertificationSchedulePage enterAndChooseUser(String user) {

		if(enterAndChoose("ScheduleDefinitions_ScheduleUser_Xpath", user))
			Reporter.reportStep("The value "+user+" is entered in the Name field successfully.", "SUCCESS");
		else
			Reporter.reportStep("The value "+user+" is not entered in the Name field, hence failure..", "FAILURE");

		return this;
	}
	public CertificationSchedulePage enterDaysToComplete(String days) {

		if(enterById("ScheduleDefinitions_DaysToComplete_Id", days))
			Reporter.reportStep("The value "+days+" is entered in the Days to complete field successfully.", "SUCCESS");
		else
			Reporter.reportStep("The value "+days+" is not entered in the Days to complete field, hence failure..", "FAILURE");

		return this;
	}
	public CertificationSchedulePage selectRunFiled(String run) {

		if(selectByVisibleTextById("ScheduleDefinitions_Run_Id", run))
			Reporter.reportStep("The Run field selected successfully with value "+run+".", "SUCCESS");
		else
			Reporter.reportStep("The Run field value "+run+" is not selcted, hence failure.", "FAILURE");

		return this;
	}
	
	public CertificationSchedulePage clickExecuteNow() {

		if(clickById("ScheduleDefinitions_ExecuteNow_Id"))
			Reporter.reportStep("The Execute Now Button is not cliked successfully.", "SUCCESS");
		else
			Reporter.reportStep("The Execute Now Button is not cliked, hence failure.", "FAILURE");

		return this;
	}
	public CertificationSchedulePage rightClickAndSave() {

		if(!rightClickByXpath("CHN_Header_Xpath"))
			Reporter.reportStep("Right Click could not be performed to refresh the page","FAILURE");

		if (clickByXpath("RightClickSave_Xpath"))
			Reporter.reportStep("The Schedule is saved successfully","SUCCESS");
		else
			Reporter.reportStep("The Save could not be clicked","FAILURE");

		return this;
	}

	public CertificationSchedulePage rightClickReload() {

		if(!rightClickByXpath("CHN_Header_Xpath"))
			Reporter.reportStep("Right Click could not be performed to refresh the page","FAILURE");

		if (clickByXpath("RunBook_AllOpenRunbook_Reload_xpath"))
			Reporter.reportStep("The Form gets reloaded successfully.","SUCCESS");
		else
			Reporter.reportStep("The Form gets reloaded could not be reload, hence failure","FAILURE");

		return this;
	}
	public CertificationSchedulePage isAvailableCerrtificationTaskAndInstancesTab(){

		scrollToElementByXpath("ScheduleDefinitions_CertificationInstancesTab_Xpath");
		
		if(!isExistByXpath("ScheduleDefinitions_CertificationInstancesTab_Xpath"))
			Reporter.reportStep("The Certification Instance tab is not appeared, hence failure.", "FAILURE");

		if(isExistByXpath("ScheduleDefinitions_CertificationTaksTab_Xpath"))
			Reporter.reportStep("The Certification Instances and Certification Tasks tab is appeared as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Certification Tasks tab is not appeared, hence failure.", "FAILURE");

		return this;
	}

	
}
