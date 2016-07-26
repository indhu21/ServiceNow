package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.ColorUtils;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class SparcMessagesPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public SparcMessagesPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToDefault();
		// Check that we're on the right page.
		if (!isExistByXpath("Sparc_MessageHeader_Xpath")) {
			Reporter.reportStep("This is not the Sparc Messages page", "FAILURE");
		}
	}

	
	public  LoginSparcLCPage clickSparcHome(){

		switchToDefault();
		if(!clickLink("SPARC_INC_HOME_Link"))
			Reporter.reportStep("The My Requests tab is not clicked or not found.", "FAILURE");
		Wait(5000);
		return new LoginSparcLCPage();
	
	}
	
	public SparcMessagesPage verifyMessage() {
		
		if(getTextByXpath("").contains(""))
			Reporter.reportStep("The Message does existed as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Messages is not found.", "SUCCESS");
		
		return this;
		
	}

}