package Pages_ServiceNow;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class FinancialSharedServicesPage extends ServiceNowWrappers {
	
	private RemoteWebDriver driver;
	
	public FinancialSharedServicesPage(RemoteWebDriver driver){
		
		
		this.driver = driver;

		// Check that we're on the right page.
		if (!isExistByXpath("FSS_MySparchead_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not the Financial service page.", "FAILURE");
		}			

	}

	public InvoicePage clickInvoice() {

		if(clickByXpath("FSS_MySPARC_Invoice_Xpath"))
			Reporter_ServiceNow.reportStep("The Invoice link is clicked successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Invoice link is not clicked, check snapshot.", "FAILURE");

		return new InvoicePage(driver);
	}
	
	
}
