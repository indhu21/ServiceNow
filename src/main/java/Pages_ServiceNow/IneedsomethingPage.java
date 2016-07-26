package Pages_ServiceNow;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class IneedsomethingPage extends ServiceNowWrappers{
	private final RemoteWebDriver driver;

	public IneedsomethingPage(RemoteWebDriver driver) {
		this.driver = driver;

		// Check that we're on the right page.
		if (!isExistByXpath("FSS_MySaprcIneedHead_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not the I need something Page", "FAILURE");
		}			

	}

	public FinancialSharedServicesPage clickFinancialSharedServices() {

		if(clickByXpath("FSS_MySPARC_FinancialSharedServices_Xpath"))
			Reporter_ServiceNow.reportStep("The Financial Shared Services link is clicked successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Financial Shared Services link is not clicked, check snapshot.", "FAILURE");

		return new FinancialSharedServicesPage(driver);
	}

}
