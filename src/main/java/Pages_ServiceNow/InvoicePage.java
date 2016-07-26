package Pages_ServiceNow;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class InvoicePage extends ServiceNowWrappers {
	
	private RemoteWebDriver driver;
	
	public InvoicePage(RemoteWebDriver driver){
		
		
		this.driver = driver;

		// Check that we're on the right page.
		if (!isExistByXpath("FSS_MySparchead_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not the Invoice page.", "FAILURE");
		}			

	}

	public ReportAnIssuePage clickReportAnIssue() {

		if(clickByXpath("FSS_MySPARC_ReportAnIssue_Xpath"))
			Reporter_ServiceNow.reportStep("The Report An Issue link is clicked successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Report An Issue link is not clicked, check snapshot.", "FAILURE");

		return new ReportAnIssuePage(driver);
	}
	
	
}
