package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class ReportAnIssuePage extends ServiceNowWrappers {
	
	private RemoteWebDriver driver;
	
	public ReportAnIssuePage(RemoteWebDriver driver){
		
		
		this.driver = driver;

		// Check that we're on the right page.
		if (!isExistByXpath("FSS_MySparchead_Xpath")) {
			Reporter.reportStep("This is not the Report An Issue Page.", "FAILURE");
		}			

	}

	public CreateRequestPage clickCoding() {

		if(clickByXpath("FSS_MySPARC_Coding_Xpath"))
			Reporter.reportStep("The Coding link is clicked successfully.", "SUCCESS");
		else
			Reporter.reportStep("The Coding link is not clicked, check snapshot.", "FAILURE");

		Wait(5000);
		return new CreateRequestPage(driver);
	}
	
	
}
