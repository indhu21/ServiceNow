package Pages_ServiceNow;

import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;

import testng.SuiteMethods;
import utils.QueryDB_ServiceNowFrontEnd;
import utils.Reporter_ServiceNow;

public class ApplicationPage_knowlwge16 extends SuiteMethods{
	private final RemoteWebDriver driver;

	public ApplicationPage_knowlwge16(RemoteWebDriver driver) {
		this.driver = driver;

	}

	public ApplicationPage_knowlwge16 verifyOperationalstatus(String operationalstatus){

		System.out.println(getDefaultValueByXpath("SA_OperationalStatus_Xpath"));
		if(getDefaultValueByXpath("SA_OperationalStatus_Xpath").equals(operationalstatus))
			Reporter_ServiceNow.reportStep("The operational status value is "+operationalstatus+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The operational status value is "+getDefaultValueByXpath("SA_OperationalStatus_Xpath")+" hence failed", "FAILURE");
		return this; 

	}
	public ApplicationPage_knowlwge16 selectOperationalstatus(String operationalstatus){


		if(selectByVisibleTextByXpath("SA_OperationalStatus_Xpath", operationalstatus))
			Reporter_ServiceNow.reportStep("The operational status value is changed to "+operationalstatus, "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The operational status value is not changed to "+operationalstatus, "FAILURE");
		return this; 

	}
	public ApplicationPage_knowlwge16 rightClickAndSave(){


		if(!rightClickByXpath("Alert_Head"))
			Reporter_ServiceNow.reportStep("The Right click could not be performed, hence failure.", "FAILURE");
		if(!clickByXpath("Computers_SaveRecord_Xpath"))
			//		Reporter_ServiceNow.reportStep("The Right click and Save link clicked successfully..", "SUCCESS");
			//	else
			Reporter_ServiceNow.reportStep("The Save link could not be clicked, hence failure.", "FAILURE");

		return this; 

	}
	
	public ListPage_knowlwge16 clickUpdate(){

		if(!clickById("Update_Button"))
			Reporter_ServiceNow.reportStep("The Update Button could not be clicked, hence failure.", "FAILURE");
		
		return new ListPage_knowlwge16(driver); 

	}
	
	

	

}
