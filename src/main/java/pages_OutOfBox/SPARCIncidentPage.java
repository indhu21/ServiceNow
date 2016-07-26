package pages_OutOfBox;


import java.util.List;

import org.apache.poi.xwpf.usermodel.ISDTContents;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.ColorUtils;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class SPARCIncidentPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public SPARCIncidentPage(RemoteWebDriver driver) {
		this.driver = driver;

//		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("SPARC_MyProfile_Xpath")) {
//			Reporter_ServiceNow.reportStep("This is not the SPARC Incident page", "FAILURE");
			
		}
		switchToMainFrame();
	}

	public SPARCIncidentPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}		

	public SPARCIncidentPage verifyAllReadOnlyFields(){

		// you need to change the read only fields when the application changes
		String[] readOnlyFields = {"SPARCPORTAL_IncidentNumber_Xpath","SPARCPORTAL_IncidentState_Xpath",
				"SPARCPORTAL_AffectedUserLabel_Xpath","SPARCPORTAL_ShortDesc_Xpath",
				"SPARCPORTAL_Priority_Xpath","SPARCPORTAL_ConfigurationItem_Xpath"};

		String[] readOnlyFieldsDesc = {"Number","State","Affected User","Short Description","Priority","Configuration Item"};


		// Verify read only
		verifyDisabledFieldsByXpath(readOnlyFields, readOnlyFieldsDesc);


		return this;

	}

	public SPARCIncidentPage RaiseIncident(String number, String issue, String ableToWork, String desc) {

		if (!selectByVisibleTextByXpath("SPARCPORTAL_CN_PeopleIMpact_Xpath", number))
			Reporter_ServiceNow.reportStep("The value: " + number + " could not selected in 'number of people impacted' field",
					"FAILURE");

		if (!selectByVisibleTextByXpath("SPARCPORTAL_CN_AbleToWork_Xpath", ableToWork))
			Reporter_ServiceNow.reportStep("The value: " + ableToWork + "  could not be selected in 'Are you able to work' field",
					"FAILURE");

		if (!enterAndChoose("SPARCPORTAL_CN_IssueWith_Xpath", issue))
			Reporter_ServiceNow.reportStep("The value: " + issue + " could not be entered 'I Have An Issue' With field",
					"FAILURE");

		if (!enterByXpath("SPARCPORTAL_CN_ShortDesc_Xpath", desc))
			Reporter_ServiceNow.reportStep("The value: " + desc + " could not be entered in 'short description' field", "FAILURE");


		if (clickById("Submit_button"))
			Reporter_ServiceNow.reportStep("The "+number+" , "+ableToWork+", "+issue+", "+desc+" are entered and Submit button is clicked and New Incident Ticket is Created successfully",
					"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Submit button could not been clicked", "FAILURE");
		return this;

	}


}





