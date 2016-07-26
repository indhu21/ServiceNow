package pages;


import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class AlertsEnrichersListPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public AlertsEnrichersListPage(RemoteWebDriver driver) {

		this.driver = driver;
		switchToMainFrame();
		// Check that we're on the right page.
		if(!(isExistByXpath("HomeTitle_Xpath")||isExistByXpath("NavBar2_Xpath"))) {
			Reporter.reportStep("This is not the Alerts Enrichers List Page.", "FAILURE");
		}
	}
	public AlertsEnrichersListPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}
	public AlertEnrichersPage clickEnrichersNewButton(){
		if(clickById("New_Button"))
			Reporter.reportStep("The New button is clicked as expected", "SUCCESS");
		else
			Reporter.reportStep("The New buttonh could not found.", "FAILURE");
		return new AlertEnrichersPage(driver);
	}



}


