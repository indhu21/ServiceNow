package pages;


import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class AlertDashBoardPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;


	public AlertDashBoardPage(RemoteWebDriver driver) {

		this.driver = driver;
		switchToMainFrame();
		// Check that we're on the right page.
		if (!isExistByXpath("AlertDashBoard_Title")) {
			Reporter.reportStep("This is not the Alerts DashBoard page", "FAILURE");
		}
	}
	public AlertDashBoardPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}
	public AlertDashBoardPage verifyIsSLAChartDisplayed(){

		if(IsElementPresentByXpath("ALERT_SLAChart_Xpath"))
			Reporter.reportStep("The Alerts Breached SLA does exist which is as expected.","SUCCESS");
		else	
			Reporter.reportStep("The Alerts Breached SLA does not exist, hence failure.","FAILURE");
			
		return this;
	}

	public AlertsListPage clickAlertsbreachedSLADashboard(){		
		
			scrollToElementByXpath("ALERT_SLAChart_Xpath");
			if(clickByXpath("ALERT_SLAChart_Xpath"))
				Reporter.reportStep("The Alerts breached SLA graph area has been clicked successfully and Alerts that breached SLA are listed as expected.","SUCCESS");
			else
				Reporter.reportStep("The Alerts breached SLA Dashboard could not be clicked","FAILURE");
			return new AlertsListPage(driver);
		}



	}


