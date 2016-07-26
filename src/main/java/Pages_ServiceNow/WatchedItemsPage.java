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

public class WatchedItemsPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public WatchedItemsPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("FSS_FSSTasks_Number_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not the Watched Items page", "FAILURE");
		}
	}

	public WatchedItemsPage switchToMainFrame(){

		switchToFrame("Frame_WatchedItems");
		return this;
	}

	public WatchedItemsPage validateFSSTaskNumber(String incNumber)
	{
		String searchedNumber=getAttributeByXpath("FSS_FSSTasks_SearchedNumberAPO2_Xpath","value");
		System.out.println(searchedNumber);

		if(incNumber.equalsIgnoreCase(searchedNumber))
			Reporter_ServiceNow.reportStep("The new FSS task is saved successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("User not able to create new FSS task", "FAILURE");

		return this;
	}
	
	public  LoginSparcLCPage clickSparcHome(){

		resetImplicitWait(5);
		switchToDefault();
		if(!clickLink("SPARC_INC_HOME_Link"))
			Reporter_ServiceNow.reportStep("The My Requests tab is not clicked or not found.", "SUCCESS");
		Wait(5000);
		return new LoginSparcLCPage();

	}


}