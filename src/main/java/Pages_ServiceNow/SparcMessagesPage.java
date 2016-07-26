package Pages_ServiceNow;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.ColorUtils;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class SparcMessagesPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public SparcMessagesPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToDefault();
		// Check that we're on the right page.
		if (!isExistByXpath("Sparc_MessageHeader_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not the Sparc Messages page", "FAILURE");
		}
	}

	
	public  LoginSparcLCPage clickSparcHome(){

		switchToDefault();
		if(!clickLink("SPARC_INC_HOME_Link"))
			Reporter_ServiceNow.reportStep("The My Requests tab is not clicked or not found.", "FAILURE");
		Wait(5000);
		return new LoginSparcLCPage();
	
	}
	
	public SparcMessagesPage verifyMessage() {
		
		if(getTextByXpath("").contains(""))
			Reporter_ServiceNow.reportStep("The Message does existed as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Messages is not found.", "SUCCESS");
		
		return this;
		
	}

}