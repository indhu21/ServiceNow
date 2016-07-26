package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class ServiceRequestsPage extends ServiceNowWrappers {
	
	private RemoteWebDriver driver;
	
	public ServiceRequestsPage(RemoteWebDriver driver){
		
		
		this.driver = driver;

		/*// Check that we're on the right page.
		if (!isExistByXpath("FSS_MySparchead_Xpath")) {
			Reporter.reportStep("This is not the Financial service page.", "FAILURE");
		}	*/		

	}

	public StandardITRequestsPage clicktandardITRequests() {

		if(clickByXpath("MySPARC_StandardITRequests_Xpath"))
			Reporter.reportStep("The Standard IT Requests link is clicked successfully.", "SUCCESS");
		else
			Reporter.reportStep("The Standard IT Requests link is not clicked, check snapshot.", "FAILURE");

		return new StandardITRequestsPage(driver);
	}
	
	
}
