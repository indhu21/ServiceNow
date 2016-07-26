package Pages_ServiceNow;

import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;

import testng.SuiteMethods;
import utils.Reporter_ServiceNow;

public class ApplicationPage extends SuiteMethods{
	private final RemoteWebDriver driver;

	public ApplicationPage(RemoteWebDriver driver) {
		this.driver = driver;
		
	}

	public ApplicationPage verifyOperationalstatus(String operationalstatus){

		
		if(getDefaultValueByXpath("SA_OperationalStatus_Xpath").contains(operationalstatus))
			Reporter_ServiceNow.reportStep("The operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The operational status value "+operationalstatus+" could not be matched", "FAILURE");
		return this; 

	}

public ApplicationPage selectOperationalstatus(String operationalstatus){

		
		if(selectByVisibleTextByXpath("SA_OperationalStatus_Xpath", operationalstatus))
			Reporter_ServiceNow.reportStep("The operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The operational status value "+operationalstatus+" could not be matched", "FAILURE");
		return this; 

	}
public ApplicationPage rightClickAndSave(){

	
	if(!rightClickByXpath("Alert_Head"))
		Reporter_ServiceNow.reportStep("The Right click could not be performed, hence failure.", "FAILURE");
	if(clickByXpath("Computers_SaveRecord_Xpath"))
		Reporter_ServiceNow.reportStep("The Right click and Save link clicked successfully..", "SUCCESS");
	else
		Reporter_ServiceNow.reportStep("The Save link could not be clicked, hence failure.", "FAILURE");
		
	return this; 

}


}
