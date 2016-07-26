package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class FinancialSharedServicesPage extends ServiceNowWrappers {
	
	private RemoteWebDriver driver;
	
	public FinancialSharedServicesPage(RemoteWebDriver driver){
		
		
		this.driver = driver;

		// Check that we're on the right page.
		if (!isExistByXpath("FSS_MySparchead_Xpath")) {
			Reporter.reportStep("This is not the Financial service page.", "FAILURE");
		}			

	}

	public InvoicePage clickInvoice() {

		if(clickByXpath("FSS_MySPARC_Invoice_Xpath"))
			Reporter.reportStep("The Invoice link is clicked successfully.", "SUCCESS");
		else
			Reporter.reportStep("The Invoice link is not clicked, check snapshot.", "FAILURE");

		return new InvoicePage(driver);
	}
	
	
}
