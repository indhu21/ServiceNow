package Pages_ServiceNow;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class IncidentsMapPage extends ServiceNowWrappers{
	private final RemoteWebDriver driver;

	public IncidentsMapPage(RemoteWebDriver driver) {
		this.driver = driver;
		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("INC_Map_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not Critical Incidents Map Page", "FAILURE");
		}			

	}
	public IncidentsMapPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");

		return this;
	}


	public IncidentsMapPage isMapPresent(){

		// Switch to the menu frame
		if (isExistByXpath("INC_Map_Xpath")) {
			Wait(2000);
			Reporter_ServiceNow.reportStep("Critical Incidents Map is displayed", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("Critical Incidents Map could not be displayed", "FAILURE");


		return this;
	}

	
	
	
	
}
