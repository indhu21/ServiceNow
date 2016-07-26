package pages;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class ControllersPage extends ServiceNowWrappers{


	private final RemoteWebDriver driver;
	private String CMDBTaskNumber;

	public ControllersPage(RemoteWebDriver driver) {
		this.driver = driver;
		switchToMainFrame();

		//Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
			Reporter.reportStep("This is not the Controllers Page", "FAILURE");
		}

	}
	public ControllersPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");
		return this;
	}

	public ControllersPage verifyAllReadOnlyFieldsforControllers(){

		// you need to change the non mandatory fields when the application changes
		String[] Fields = { "Controllers_ReadOnlyName_Xpath",
				"Controllers_ReadOnlyManufacturer_Xpath", 
				"Controllers_ReadOnlyLocation_Xpath",
				"Controllers_ReadOnlyWWNN_Xpath",
		"Controllers_ReadOnlyOperationalStatus_Xpath"};

		String[] FieldsDesc = {"Name",
				"Manufacturer",
				"Location",
				"WWNN",
		"Operational Status"};
		verifyDisabledFieldsByXpath(Fields, FieldsDesc);
		return this;
	}

	public ControllersPage verifyAllEnabledFieldsforControllers(){

		// you need to change the non mandatory fields when the application changes
		String[] Fields = { "Controllers_Environment_Xpath",
				"Controllers_GxP_Xpath", 
				"Controllers_DeviceID_Xpath",
				"Controllers_WWPN_Xpath",
				"Controllers_Description_Xpath",
				"Controllers_CIOwnerGroup_Xpath",
				"Controllers_SystemManager_Xpath",
		"Controllers_SecondarySystemManager_Xpath"};

		String[] FieldsDesc = {"Environment",
				"GXP",
				"Device ID",
				"WWPN",
				"Description",
				"CI Owner Group",
				"System Manager",
		"Secondary System Manager"};
		verifyEnabledFieldsByXpath(Fields, FieldsDesc);
		return this;
	}
	public ControllersPage isDeletButtonPresent(String userName){

		if(IsElementNotPresentByXpath("Delete_Xpath"))
			Reporter.reportStep("Delete option is Not found for the username "+ userName + ", as expected" , "SUCCESS");
		else
			Reporter.reportStep("Delete option is found for the username "+ userName +" hence failure","FAILURE");
	
		return this;
	}
	

}



