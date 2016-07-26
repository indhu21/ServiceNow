package Pages_ServiceNow;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter_ServiceNow;

public class RunbookListPage extends ListPage{


	private final RemoteWebDriver driver;


	public RunbookListPage(RemoteWebDriver driver) {

		super(driver);
		this.driver = driver;
		resetImplicitWait(5);
		switchToDefault();
		switchToMainFrame();
		// Check that we're on the right page.
		if (!isExistByXpath("NavBar1_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not the Runbook List page", "FAILURE");
		}

		resetImplicitWait(30);
	}

	public RunbookListPage rightClickRunbookAndAssignToMe(){

		verifyData();
		// Verify the tabs exists
		if(!rightClickByXpath("ALERTPROFILE_FirstAlert_Xpath"))		
			Reporter_ServiceNow.reportStep("Right click could not be clicked ", "FAILURE");

		// Verify the tabs exists
		if(clickByXpath("ALERT_AssignToMe_Xpath"))		
			Reporter_ServiceNow.reportStep("The Right click on First Runbook is successful and Assign to me clicked as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Assign to me could not be clicked", "FAILURE");
		return this;
	}
	public RunbookListPage isUpdateButtonnNotAvailable(){

		if(isExistById("Update_Button"))
			Reporter_ServiceNow.reportStep("The Update Button is available, hence failure.", "FAILURE");
		else
			Reporter_ServiceNow.reportStep("The Update Button is not available on the selected Runbook as expected.", "SUCCESS");

		return this;
	}
	public RunbookListPage selectFirstAndSecondCheckbox(){

		verifyData();
		// Verify the tabs exists
		if(clickByXpath("Checkbox_Xpath"))		
			Reporter_ServiceNow.reportStep("First check box could not be clicked ", "FAILURE");

		// Verify the tabs exists
		if(clickByXpath("Checkbox2_Xpath"))		
			Reporter_ServiceNow.reportStep("First two tickets checkbox are selected successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Assign to me could not be clicked", "FAILURE");
		return this;
	}

	public String getFirstRunbook() {

		String rbk=getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");

		if(rbk.equals(""))
			Reporter_ServiceNow.reportStep("Runbook", "FAILURE");
		return rbk;
	}
	public String getsecondRunbook() {

		String rbk=getTextByXpath("ALERTPROFILE_SecondAlert_Xpath");

		if(rbk.equals(""))
			Reporter_ServiceNow.reportStep("Runbook", "FAILURE");
		return rbk;
	}
	public RunbookListPage selectAssignToMefromActionseletectRows(String firstRBK, String seconRBK){

		if(selectByVisibleTextByXpath("All_Select_Xpath", "   Assign to me"))		
			Reporter_ServiceNow.reportStep("The following runbooks are "+firstRBK+", "+seconRBK+" Assign to me is successful.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value Assign to me could not be selected, hence failure.", "FAILURE");

		return this;
	}
	public RunbookListPage verifyRunbook(String firstRBK, String seconRBK){

		if(!isExistByLinkText(firstRBK,false))
			Reporter_ServiceNow.reportStep("The RunBook "+firstRBK+" is not displayed, hence failure.", "FAILURE");

		if(isExistByLinkText(firstRBK,false))
			Reporter_ServiceNow.reportStep("The following runbooks are "+firstRBK+", "+seconRBK+" are available in Assigned To Me Menu as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The RunBook "+seconRBK+" is not displayed, hence failure.", "FAILURE");
		return this;
	}
	
}
