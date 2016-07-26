package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class IneedsomethingPage extends ServiceNowWrappers{
	private final RemoteWebDriver driver;

	public IneedsomethingPage(RemoteWebDriver driver) {
		this.driver = driver;

		// Check that we're on the right page.
		if (!isExistByXpath("FSS_MySaprcIneedHead_Xpath")) {
			Reporter.reportStep("This is not the I need something Page", "FAILURE");
		}			

	}

	public FinancialSharedServicesPage clickFinancialSharedServices() {

		if(clickByXpath("FSS_MySPARC_FinancialSharedServices_Xpath"))
			Reporter.reportStep("The Financial Shared Services link is clicked successfully.", "SUCCESS");
		else
			Reporter.reportStep("The Financial Shared Services link is not clicked, check snapshot.", "FAILURE");

		return new FinancialSharedServicesPage(driver);
	}
	public ServiceRequestsPage clickServiceRequests() {

		if(clickByXpath("MySPARC_ServiceRequests_Xpath"))
			Reporter.reportStep("The Service Requests link is clicked successfully.", "SUCCESS");
		else
			Reporter.reportStep("The Service Requests link is not clicked, check snapshot.", "FAILURE");

		return new ServiceRequestsPage(driver);
	}

}
