package pages;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class AlertPropertiesPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;


	public AlertPropertiesPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("Alert_PropertyHead_Xpath")) {
			Reporter.reportStep("This is not the Alert Properties Page", "FAILURE");
		}
	}

	public AlertPropertiesPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}

	public AlertPropertiesPage selectNewRecurrencesSeverity(){
		if(!selectByVisibleTextByXpath("AP_AlertSevRec_Xpath", "Severity is always updated with new Alert Recurrences"))
			Reporter.reportStep("The Severity is always updated with new Alert Recurrences is not Selected, hence failure.","FAILURE");
		if(clickByXpath("Save_Xpath"))
			Reporter.reportStep("The Severity is always updated with new Alert Recurrences is Selected and propeties are saved successfully.","SUCCESS");
		else
			Reporter.reportStep("The Save Button not clicked or not found.","FAILURE");

		Wait(5000);

		return this;
	}

	public AlertPropertiesPage verifysevRecValue(){

		scrollToElementByXpath("AP_AlertSevRec_Xpath");

		String sevRecValue = getDefaultValueByXpath("AP_AlertSevRec_Xpath");

		if(sevRecValue.equals("Severity is always updated with new Alert Recurrences"))
			Reporter.reportStep("The Severity is always updated with new Alert Recurrences is updated as expected.","SUCCESS");
		else
			Reporter.reportStep("The Severity is always updated with new Alert Recurrences is not updated.","FAILURE");

		return this;
	}

	public AlertPropertiesPage selectWorstSeverityEncountered(){
		if(!selectByVisibleTextByXpath("AP_AlertSevRec_Xpath", "Severity always shows the worst Severity encountered"))
			Reporter.reportStep("The Severity always shows the worst Severity encountered is not Selected, hence failure.","FAILURE");
		if(clickByXpath("Save_Xpath"))
			Reporter.reportStep("The Severity always shows the worst Severity encountered is Selected and propeties are saved as successfully.","SUCCESS");
		else
			Reporter.reportStep("The Save Button not clicked or not found.","FAILURE");
		Wait(5000);

		return this;
	}
	public AlertPropertiesPage verifyWorstSevRecValue(){

		String sevRecValue = getDefaultValueByXpath("AP_AlertSevRec_Xpath");

		scrollToElementByXpath("AP_AlertSevRec_Xpath");

		if(sevRecValue.equals("Severity always shows the worst Severity encountered"))
			Reporter.reportStep("The Severity always shows the worst Severity encountered is updated as expected.","SUCCESS");
		else
			Reporter.reportStep("The Severity always shows the worst Severity encountered is not Selected.","FAILURE");

		return this;
	}
	public AlertPropertiesPage enterAndSaveAlertCloseData(String value) {

		if (!enterById("Stale", value))
			Reporter.reportStep("The Stale property value could not be entered","FAILURE");

		if(clickByXpath("Save_Xpath"))
			Reporter.reportStep("The After this many days an alert will be closed as stale value "+value+" entered and saved successfully ","SUCCESS");
		else
			Reporter.reportStep("The Property not save, hence failed","FAILURE");

	return this;
	}
	public AlertPropertiesPage enterAndSaveEmptyValueAlertCloseData() {

		if (!enterById("Stale", ""))
			Reporter.reportStep("The Stale property value could not be entered","FAILURE");

		if(clickByXpath("Save_Xpath"))
			Reporter.reportStep("The After this many days an alert will be closed as stale entered with empty value and saved successfully ","SUCCESS");
		else
			Reporter.reportStep("The Property not save, hence failed","FAILURE");

	return this;
	}
	public AlertPropertiesPage verifyDefaultValueAlertCloseData() {

		String defval = getAttributeById("Stale", "value");
		if(defval.endsWith(""))
			defval="empty";
		if("7".equals(defval))
			Reporter.reportStep("The After this many days an alert will be closed as stale Default value: "+defval+" matched as expected","SUCCESS");
		else
			Reporter.reportStep("The Default value of After this many days an alert will be closed as stale is retained "+defval+" not as expected","FAILURE");
		
	return this;
	}
	public AlertPropertiesPage enterAndSaveAlertHistory(String value) {

		if (!enterByXpath("History_Xpath", value))
			Reporter.reportStep("The Stale property value could not be entered","FAILURE");

		if(clickByXpath("Save_Xpath"))
			Reporter.reportStep("The number of days history for an alert that is retained value "+value+" entered and saved successfully ","SUCCESS");
		else
			Reporter.reportStep("The Property not save, hence failed","FAILURE");

	return this;
	}
	public AlertPropertiesPage enterAndSaveEmptyValueAlertHistory() {

		if (!enterByXpath("History_Xpath", ""))
			Reporter.reportStep("The History property value could not be cleared","FAILURE");


		if(clickByXpath("Save_Xpath"))
			Reporter.reportStep("The number of days history for an alert that is retained cleared and saved successfully ","SUCCESS");
		else
			Reporter.reportStep("The Property not save, hence failed","FAILURE");

	return this;
	}
	public AlertPropertiesPage verifyDefaultValueAlertHistory() {

		String defval = getAttributeByXpath("History_Xpath", "value");
		if(defval.equals(""))
			defval="empty";
		if("30".equals(defval))
			Reporter.reportStep("Default value of the number of days history for an alert that is retained "+defval+" as expected","SUCCESS");
		else
			Reporter.reportStep("Default value of the number of days history for an alert that is retained "+defval+" not as expected","FAILURE");
		
	return this;
	}

}


