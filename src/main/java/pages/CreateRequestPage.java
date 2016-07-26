package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class CreateRequestPage extends ServiceNowWrappers {
	
	private RemoteWebDriver driver;
	
	public CreateRequestPage(RemoteWebDriver driver){
		this.driver = driver;
		
		Wait(5000);
		switchToFrame("CraeteRequest_Name");
//		// Check that we're on the right page.
//		if (!isExistByXpath("FSS_RequestCategory_xpath")) {
//			Reporter.reportStep("This is not the Create Request Page.", "FAILURE");
//		}			

	}

	public CreateRequestPage verifyAllFields(String user, String category, String serviceType, String serviceItem) {
		
		if(!getAttributeByXpath("FSS_RequestAffectedUser_xpath", "value").equals(user))
			Reporter.reportStep("The Affected User name is not matched, hence failure.", "FAILURE");
		
		if(!getAttributeByXpath("FSS_RequestCategory_xpath", "value").equals(category))
			Reporter.reportStep("The Category is not matched, hence failure.", "FAILURE");
		
		if(!getAttributeByXpath("FSS_RequestServiceType_xpath", "value").equals(serviceType))
			Reporter.reportStep("The Service Type is not matched, hence failure.", "FAILURE");
		
		if(getAttributeByXpath("FSS_RequestServiceItem_xpath", "value").equals(serviceItem))
			Reporter.reportStep("The Values: Affected User: "+user+", Category: "+category+", Service Type: "+serviceType+", Service Item: "+serviceItem+" are matched as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Service Item is not matched, hence failure.", "FAILURE");
		
		return this;
	}
	
	public CreateRequestPage enterInvoiceandShortDes(String num, String dec) {
		
		if(enterByXpath("FSS_RequestInvoiceNum_xpath", num))
			Reporter.reportStep("The Value: "+num+" is entered in Invoice Number field as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Value: "+num+" is not entered in Invoice Number field, check snapshot.", "FAILURE");
		
		if(enterByXpath("FSS_RequestShortDec_xpath", dec))
			Reporter.reportStep("The Value: "+dec+" is entered in Short Description field as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Value: "+dec+" is not entered in Short Description field, check snapshot.", "FAILURE");
		
		return this;
		
	}

	public CreateRequestPage clickSubmit()
	{
		if(clickByXpath("FSS_MySPARC_SubmitButton_Xpath"))
			Reporter.reportStep("The Submit Button is clicked and The Request created successfully.", "SUCCESS");
		else
			Reporter.reportStep("User not able to click Submit Button", "FAILURE");

		return this;
	}
	
	public MenuPage navigateToLoin()
	{
		getDriver().get("https://sparctest.service-now.com");
		return new MenuPage(driver);
	}


}
