package Pages_ServiceNow;

import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class MyProfilePage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public MyProfilePage(RemoteWebDriver driver) {
		this.driver = driver;
		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("SPARCPORTAL_ExtensionNumber_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not the My Profile  Page", "FAILURE");
		}			

	}

	public MyProfilePage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");
		return this;
	}

	public MyProfilePage verifyExtensionNumberField(){

		// you need to change the mandatory fields when the application changes
		String[] extensionNumberField = {"SPARCPORTAL_ExtensionNumber_Xpath"};
		String[] desc = {"SPARCPORTAL ExtensionNumber"};

		verifyFieldsExistByXpath(extensionNumberField, desc);
		return this;

	}


	public MyProfilePage verifyExtensionNumberFieldReadOnly() {

		// you need to change the read only fields when the application changes
		String[] readOnlyFields = { "SPARCPORTAL_ExtensionNumber_Xpath"};
		String[] desc = {"SPARCPORTAL ExtensionNumber"};

		// Verify read only
		verifyDisabledFieldsByXpath(readOnlyFields, desc);
		return this;
	}

	public MenuPage BacktoMainFrame(){

		// Switch to the menu frame
		switchToDefault();
		return new MenuPage(driver);
	}
	public MyProfilePage verifyExtensionNumberFieldExistsandReadOnly(){

		Boolean bReturn = true;
	
		if(!isExistByXpath("SPARCPORTAL_ExtensionNumber_Xpath"))
			Reporter_ServiceNow.reportStep("Extension number does not exists","FAILURE");
		
		if(verifyAttributeTextByXpath("SPARCPORTAL_ExtensionNumber_Xpath", "readonly", "true")){
		}
		else if(verifyAttributeTextByXpath("SPARCPORTAL_ExtensionNumber_Xpath", "disabled", "true")){
		}
		else{	
			bReturn = false;
			Reporter_ServiceNow.reportStep("Extension number field is editable; hence failed","FAILURE");
		}	
		
		if(bReturn)
			Reporter_ServiceNow.reportStep("Extension number field exists and readonly as expected","SUCCESS");

		return this;

	}

}
