package pages_OutOfBox;


import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class SparcMenuPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public SparcMenuPage(RemoteWebDriver driver) {
		this.driver = driver;

		// Check that we're on the right page.
		if (!isExistByXpath("SPARC_MyProfile_Xpath")) {
//			Reporter_ServiceNow.reportStep("This is not the home page", "FAILURE");
		}
	}

	public SparcMenuPage switchToMenuFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Nav");

		return this;
	}

	public SPARCIncidentPage clickCreateIncident() {

		if(clickByXpath("SPARCPORTAL_CreateNewINC_Xpath"))
			Reporter_ServiceNow.reportStep("The Create New Incident is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Create New Incident could not be clicked", "FAILURE");

		return new SPARCIncidentPage(driver);
	}

}
