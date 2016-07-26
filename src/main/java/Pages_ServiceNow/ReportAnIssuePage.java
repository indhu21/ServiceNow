package Pages_ServiceNow;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class ReportAnIssuePage extends ServiceNowWrappers {
	
	private RemoteWebDriver driver;
	
	public ReportAnIssuePage(RemoteWebDriver driver){
		
		
		this.driver = driver;

		// Check that we're on the right page.
		if (!isExistByXpath("FSS_MySparchead_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not the Report An Issue Page.", "FAILURE");
		}			

	}

	public CreateRequestPage clickCoding() {

		if(clickByXpath("FSS_MySPARC_Coding_Xpath"))
			Reporter_ServiceNow.reportStep("The Coding link is clicked successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Coding link is not clicked, check snapshot.", "FAILURE");

		Wait(5000);
		return new CreateRequestPage(driver);
	}
	
	
}
