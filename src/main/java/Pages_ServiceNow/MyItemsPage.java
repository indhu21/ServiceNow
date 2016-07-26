package Pages_ServiceNow;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class MyItemsPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public MyItemsPage(RemoteWebDriver driver) {
		this.driver = driver;
		// Check that we're on the right page.
		if (!isExistById("Welcome_Sparc_Id")) {
			Reporter_ServiceNow.reportStep("This is not the My Items Page.", "FAILURE");
		}
	}

	public ListPage clickMyRequestTab(){
	
		if(clickByXpath("FSS_MySPARC_MyRequests_Xpath"))
			Reporter_ServiceNow.reportStep("The My Requests tab clicked successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The My Requests tab is not clicked or not found.", "SUCCESS");
		return new ListPage(driver);
	
	}
	public ListPage clickWatchItemsTab(){
		
		if(clickByXpath("FSS_MySPARC_WatchedItems_Xpath"))
			Reporter_ServiceNow.reportStep("The Watched Items tab clicked successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Watched Items tab is not clicked or not found.", "SUCCESS");
		return new ListPage(driver);
	
	}

	public SparcMessagesPage clickViewMessages(){
		
		if(clickByXpath("FSS_MySPARC_ViewMessagesIcon_Xpath"))
			Reporter_ServiceNow.reportStep("The View Messages icon clicked successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The View Messages icon is not clicked or not found.", "SUCCESS");
		return new SparcMessagesPage(driver);
	
	}
}
