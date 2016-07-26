package pages;


import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter1;
import wrapper.ServiceNowWrappers;

public class SparcMenuPage_DEMO extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public SparcMenuPage_DEMO(RemoteWebDriver driver) {
		this.driver = driver;

		// Check that we're on the right page.
		if (!isExistByXpath("SPARC_MyProfile_Xpath")) {
			Reporter1.reportStep("This is not the home page", "FAILURE");
		}
	}

	public SparcMenuPage_DEMO switchToMenuFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Nav");

		return this;
	}

	public SPARCIncidentPage_DEMO clickCreateIncident() {

		if(clickByXpath("SPARCPORTAL_CreateNewINC_Xpath"))
			Reporter1.reportStep("The Create New Incident is clicked successfully", "SUCCESS");
		else
			Reporter1.reportStep("The Create New Incident could not be clicked", "FAILURE");

		return new SPARCIncidentPage_DEMO(driver);
	}
}
