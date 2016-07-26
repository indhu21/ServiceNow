package pages;


import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class AlertsSuppressorsListPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public AlertsSuppressorsListPage(RemoteWebDriver driver) {

		this.driver = driver;
		switchToMainFrame();
		// Check that we're on the right page.
		if(!(isExistByXpath("HomeTitle_Xpath")||isExistByXpath("NavBar2_Xpath"))) {
			Reporter.reportStep("This is not the Alerts Suppressors List Page.", "FAILURE");
		}
	}
	public AlertsSuppressorsListPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}
	public AlertSuppressorsPage clickSuppressorNewButton(){
		if(clickById("New_Button"))
			Reporter.reportStep("The New button is clicked as expected", "SUCCESS");
		else
			Reporter.reportStep("The New buttonh could not found.", "FAILURE");
		return new AlertSuppressorsPage(driver);
	}

	public AlertSuppressorsPage verifyCreatedAlertSuppress(String alert){

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Name"))
			Reporter.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",alert)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter.reportStep("The Created Alert Suppress: "+alert+" could not be entered", "FAILURE");

		if(!clickLink(alert, false))
			Reporter.reportStep("The Created Alert Suppress is not clicked or not found", "FAILURE");
		
		if((getAttributeById("ALERT_Suppress_Name_Id", "value")).equals(alert))
			Reporter.reportStep("The Created Alert Suppress: "+alert+" does exist as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Alert Suppress Name is does not matched.","FAILURE");

		return new AlertSuppressorsPage(driver);
	}
	public AlertSuppressorsPage verifyCreatedAlertSuppressDats(String alert){

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Name"))
			Reporter.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",alert))
			Reporter.reportStep("The Created Alert Suppress: "+alert+" could not be entered", "FAILURE");

		if(!clickLink(alert, false))
			Reporter.reportStep("The Created Alert Suppress is not clicked or not found", "FAILURE");
		
		if((getAttributeById("ALERT_Suppress_Name_Id", "value")).equals(alert))
			Reporter.reportStep("The Created Alert Suppress: "+alert+" does exist as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Alert Suppress Name is does not matched.","FAILURE");

		return new AlertSuppressorsPage(driver);
	}
	public AlertSuppressorsPage openAlertSuppress(String alert){

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Name"))
			Reporter.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",alert)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter.reportStep("The Created Alert Suppress: "+alert+" could not be entered", "FAILURE");

		if(!clickLink(alert, false))
			Reporter.reportStep("The Created Alert Suppress is not clicked or not found", "FAILURE");
		
		if(!(getAttributeById("ALERT_Suppress_Name_Id", "value")).equals(alert))
			Reporter.reportStep("The Alert Suppress Name is does not matched.","FAILURE");

		return new AlertSuppressorsPage(driver);
	}
}


