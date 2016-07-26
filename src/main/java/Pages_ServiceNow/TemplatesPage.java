package Pages_ServiceNow;

import org.openqa.selenium.remote.RemoteWebDriver;

import ServiceNow_Integration.suitemethods;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class TemplatesPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver; 

	public TemplatesPage(RemoteWebDriver driver) {

		this.driver = driver;
		switchToMainFrame();
		resetImplicitWait(5);
		// Check that we're on the right page.
		if(!(isExistByXpath("HomeTitle_Xpath")||isExistByXpath("NavBar2_Xpath"))) {
			Reporter_ServiceNow.reportStep("This is not the Alerts List page", "FAILURE");
		}
		resetImplicitWait(30);
	}
	public TemplatesPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}

	public TemplatesPage enterAllMandatoryFields(String name, String sla) {

		if(!enterById("Runbook_Templates_new_name_entervalue_id", name))
			Reporter_ServiceNow.reportStep("The value "+name+" is not entered in name field, hence failure.", "FAILURE");

		if(enterAndChoose("Runbook_SLA_xpath", sla))
			Reporter_ServiceNow.reportStep("The First value is selected in SLA field, successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First value is not selected in SLA field, hence failure.", "FAILURE");

		return this;
	}
	public TemplatesListPage enterOwningGroupAndclickSumbit(String grp) {

		if(!enterAndChoose("Runbook_owningGroup_xpath", grp))
			Reporter_ServiceNow.reportStep("The First value is not selected in SLA field, hence failure.", "FAILURE");

		if(clickById("Submit_Id"))
			Reporter_ServiceNow.reportStep("The Owning group: "+grp+" is selected and Submit button is clicked successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First value is not selected in SLA field, hence failure.", "FAILURE");

			return new TemplatesListPage(driver);
	}
	
	public TemplatesPage verifyEnablefields() {

		String fields[]={"Runbook_SLA_xpath", "Runbook_owningGroup_xpath", "Runbook_Name_xpath", "Runbook_Active_xpath",
							"Runbook_Description_xpath", "Runbook_Type_xpath", "Runbook_Owner_xpath", "Runbook_AssGroup_xpath"};
			
		String desc[]={"SLA", "Owning Group", "Name", "Active", "Description", "Type", "Owner", "Default assignment group"};

		verifyEnabledFieldsByXpath(fields, desc);
		
			return this;
	}
	public TemplatesPage verifyDisablefields() {

		String fields[]={"Runbook_SLA_xpath", "Runbook_owningGroup_xpath", "Runbook_Name_xpath", "Runbook_Active_xpath",
							"Runbook_Description_xpath", "Runbook_Type_xpath", "Runbook_Owner_xpath", "Runbook_AssGroup_xpath"};
			
		String desc[]={"SLA", "Owning Group", "Name", "Active", "Description", "Type", "Owner", "Default assignment group"};

		verifyDisabledFieldsByXpath(fields, desc);
		
			return this;
	}
}
