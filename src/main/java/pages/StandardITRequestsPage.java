package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class StandardITRequestsPage extends ServiceNowWrappers {
	
	private RemoteWebDriver driver;
	
	public StandardITRequestsPage(RemoteWebDriver driver){
		
		
		this.driver = driver;

		// Check that we're on the right page.
		if (!isExistByXpath("FSS_MySparchead_Xpath")) {
			Reporter.reportStep("This is not the Invoice page.", "FAILURE");
		}			

	}

	public CreateIncidentPage clickCalendarRequest() {

		if(clickByXpath("MySPARC_CalendarRequest_Xpath"))
			Reporter.reportStep("The Calendar Request link is clicked successfully.", "SUCCESS");
		else
			Reporter.reportStep("The Calendar Request link is not clicked, check snapshot.", "FAILURE");

		return new CreateIncidentPage(driver);
	}
	
	
}
